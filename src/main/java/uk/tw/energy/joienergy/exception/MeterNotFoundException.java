package uk.tw.energy.joienergy.exception;

public class MeterNotFoundException extends RuntimeException {
  public MeterNotFoundException(String meterId) {
    super("Meter " + meterId + " is not found.");
  }
}
