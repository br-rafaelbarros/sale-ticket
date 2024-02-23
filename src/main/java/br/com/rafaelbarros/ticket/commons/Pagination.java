package br.com.rafaelbarros.ticket.commons;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {

  @Schema(description = "Current page", example = "1")
  private Integer page;
  @Schema(description = "Number of elements per page", example = "10")
  private Integer size;
  @Schema(description = "Total of elements", example = "100")
  private Integer total;
  @Schema(description = "Total of pages", example = "10")
  private Integer totalPages;
  @Schema(description = "Array of specific element type")
  private List<T> data;
  private String error;

}
