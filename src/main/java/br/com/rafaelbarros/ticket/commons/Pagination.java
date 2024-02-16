package br.com.rafaelbarros.ticket.commons;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {

  private Integer page;
  private Integer size;
  private Integer total;
  private Integer totalPages;
  private List<T> data;

}
