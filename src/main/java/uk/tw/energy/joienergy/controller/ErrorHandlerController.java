package uk.tw.energy.joienergy.controller;

import java.time.LocalDate;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.tw.energy.joienergy.dto.ErrorDto;
import uk.tw.energy.joienergy.exception.MeterDuplicatedException;
import uk.tw.energy.joienergy.exception.MeterNotFoundException;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MeterDuplicatedException.class)
  public ResponseEntity<ErrorDto> meterDuplicatedHandler(MeterDuplicatedException ex) {
    final ErrorDto errorDto = ErrorDto.builder().httpStatus(HttpStatus.CONFLICT)
        .time(LocalDate.now())
        .message(ex.getMessage()).build();
    return ResponseEntity.internalServerError().body(errorDto);
  }

  @ResponseStatus(
      value = HttpStatus.CONFLICT,
      reason = "Meter not found"
  )
  @ExceptionHandler(MeterNotFoundException.class)
  public void meterDuplicatedHandler(MeterNotFoundException ex) {
  }
}
