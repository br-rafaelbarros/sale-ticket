package br.com.rafaelbarros.ticket.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationFilter {

  private Integer page;
  private Integer size;
  private String sort;
  private String sort_order;

}
