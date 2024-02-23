package br.com.rafaelbarros.ticket.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;
import br.com.rafaelbarros.ticket.domains.dtos.TicketModel;
import br.com.rafaelbarros.ticket.domains.repositories.TicketRepository;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTests {

  @Mock
  private TicketRepository ticketRepository;

  @InjectMocks
  private TicketServiceImpl ticketService;

  @BeforeEach
  public void setUp() {
  }

  @DisplayName("Test listAvailable")
  @Test
  public void givenListAvailableWhenGetThenReturnOk() throws InternalError, BusinessException {

    doReturn(Pagination.<TicketModel>builder()
        .data(List.of(TicketModel.builder()
            .id(1L)
            .title("Test")
            .status(1)
            .price(BigDecimal.valueOf(10.00))
            .qtyAvailable(10)
            .build()))
        .page(1)
        .size(10)
        .totalPages(1)
        .build())
        .when(ticketRepository).listAvailable(any(PaginationFilter.class));

    Pagination<TicketResponseBody> response = ticketService.listAvailable(PaginationFilter.builder().build());
    assert (response.getData().size() == 1);
    assert (response.getData().get(0).getId() == 1L);
    assert (response.getData().get(0).getTitle().equals("Test"));
    assert (response.getData().get(0).getStatus() == 1);
    assert (response.getData().get(0).getPrice().equals(BigDecimal.valueOf(10.00)));
    assert (response.getData().get(0).getQtyAvailable() == 10);

  }

}
