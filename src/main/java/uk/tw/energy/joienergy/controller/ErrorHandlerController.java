package uk.tw.energy.joienergy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.tw.energy.joienergy.exception.MeterDuplicatedException;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(MeterDuplicatedException.class)
  ResponseStatusException meterDuplicatedHandler(MeterDuplicatedException ex) {
    return new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
  }
}
