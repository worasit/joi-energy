package uk.tw.energy.joienergy.exception;

public class MeterDuplicatedException extends RuntimeException {
  public MeterDuplicatedException(String meterId) {
    super("Meter " + meterId + " is already existed.");
  }
}
