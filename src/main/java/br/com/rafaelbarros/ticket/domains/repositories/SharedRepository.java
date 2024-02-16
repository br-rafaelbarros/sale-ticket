package br.com.rafaelbarros.ticket.domains.repositories;

import java.util.List;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;

public final class SharedRepository {

  public static <T> Pagination<T> buildPaginationResult(PaginationFilter filters, Integer total, List<T> listItems) {
    return Pagination.<T>builder()
        .total(total)
        .page(filters.getPage())
        .size(filters.getSize())
        .totalPages((int) Math.ceil(total / filters.getSize()) + 1)
        .data(listItems)
        .build();
  }

}
