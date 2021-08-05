package uk.tw.energy.joienergy.controller;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.tw.energy.joienergy.exception.MeterNotFoundException;
import uk.tw.energy.joienergy.service.AccountService;
import uk.tw.energy.joienergy.service.PricePlanService;

@RestController
@RequestMapping("/price-plans")
@Validated
public class PricePlanComparatorController {


  public static final String PRICE_PLAN_ID = "pricePlanId";
  public static final String PRICE_PLAN_COMPARISONS = "pricePlanComparisons";
  private final AccountService accountService;
  private final PricePlanService pricePlanService;

  public PricePlanComparatorController(
      AccountService accountService, PricePlanService pricePlanService) {
    this.accountService = accountService;
    this.pricePlanService = pricePlanService;
  }

  @GetMapping("/compare-all/{smartMeterId}")
  public ResponseEntity<Map<String, Object>> calculatedCostForEachPricePlan(
      @PathVariable String smartMeterId) {
    final String pricePlanId = accountService
        .getPricePlanIdForSmartMeterId(smartMeterId);

    final Map<String, BigDecimal> comparedPricePlan = pricePlanService
        .getConsumptionCostOfElectricityReadingsForEachPricePlan(smartMeterId)
        .orElseThrow(() -> new MeterNotFoundException(smartMeterId));

    final HashMap<String, Object> comparedResult = new HashMap<>();
    comparedResult.put(PRICE_PLAN_ID, pricePlanId);
    comparedResult.put(PRICE_PLAN_COMPARISONS, comparedPricePlan);

    return ResponseEntity.ok(comparedResult);
  }

  @GetMapping("/recommend/{smartMeterId}")
  public ResponseEntity<Map<String, BigDecimal>>
  recommendCheapestPricePlans(@PathVariable String smartMeterId,
      @RequestParam(name = "limit", required = false) @Min(1) @Max(3) Integer limit) {

    final Map<String, BigDecimal> pricePlans = pricePlanService
        .getConsumptionCostOfElectricityReadingsForEachPricePlan(smartMeterId)
        .orElseThrow(() -> new MeterNotFoundException(smartMeterId));

    final Map<String, BigDecimal> recommendedPricePlan = pricePlans.entrySet().stream()
        .sorted(Entry.comparingByValue(Comparator.naturalOrder()))
        .limit(limit)
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

    return ResponseEntity.ok(recommendedPricePlan);
  }
}
