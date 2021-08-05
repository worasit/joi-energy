package uk.tw.energy.joienergy.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorDto {

  private String message;
  private LocalDate time;
  private HttpStatus httpStatus;
}
