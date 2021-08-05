package uk.tw.energy.joienergy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
@RequestMapping("/price-plan")
public class PricePlanComparatorController {

  @GetMapping("/compare-all/{smartMeterId}")
  public ResponseEntity calculatedCostForEachPricePlan(@PathVariable String smartMeterId) {
    throw new NotImplementedException();
  }

  @GetMapping("/recommend/{smartMeterId}")
  public ResponseEntity<List<Entry<String, BigDecimal>>>
  recommendCheapestPricePlans(@PathVariable String smartMeterId,
      @RequestParam(name = "limit", required = false) Integer limit) {
    throw new NotImplementedException();
  }
}
