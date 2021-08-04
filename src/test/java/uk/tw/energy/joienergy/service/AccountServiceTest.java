package uk.tw.energy.joienergy.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.HashMap;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AccountServiceTest {

  public static final String SMART_METER_ID = "smart-meter-0";
  public static final String PRICE_PLAN_ID = "price-plan-0";

  @Test
  void getPricePlanIdForSmartMeterId_whenSpecifyValidMeterId() {
    // Arrange
    final HashMap<String, String> smartMeterToPricePlanAccounts = new HashMap<>();
    smartMeterToPricePlanAccounts.put(SMART_METER_ID, PRICE_PLAN_ID);
    final AccountService accountService = new AccountService(smartMeterToPricePlanAccounts);

    // Act
    final String pricePlanId = accountService
        .getPricePlanIdForSmartMeterId(SMART_METER_ID);

    // Assert
    assertThat(pricePlanId).isEqualTo(PRICE_PLAN_ID);
  }

  @Disabled("This case is not cover yet")
  @Test
  void getPricePlanIdForSmartMeterId_whenSpecifyInValidMeterId() {
    // Arrange
    final HashMap<String, String> smartMeterToPricePlanAccounts = new HashMap<>();
    smartMeterToPricePlanAccounts.put(SMART_METER_ID, PRICE_PLAN_ID);
    final AccountService accountService = new AccountService(smartMeterToPricePlanAccounts);

    // Act
    final String pricePlanId = accountService
        .getPricePlanIdForSmartMeterId("smart-meter-xxx");

    // Assert
    assertThat(pricePlanId).isEqualTo(PRICE_PLAN_ID);
  }
}