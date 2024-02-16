package br.com.rafaelbarros.ticket.repositories;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.domains.dtos.TicketModel;
import br.com.rafaelbarros.ticket.domains.repositories.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

class TicketRepositoryTest {

  @Mock
  private TicketRepository ticketRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListAvailableTickets() {
    PaginationFilter filters = new PaginationFilter();
    Pagination<TicketModel> expectedTickets = new Pagination<>();

    // Mocking the behavior of the repository
    when(ticketRepository.listAvailable(filters)).thenReturn(expectedTickets);

    // Calling the method under test
    Pagination<TicketModel> actualTickets = ticketRepository.listAvailable(filters);

    // Asserting the result
    assertEquals(expectedTickets, actualTickets);
  }

  // Test for loading pagination result
  @Test
  void testLoadPaginationResult() {
    // Create a pagination filter
    PaginationFilter filters = PaginationFilter.builder()
        .page(1)
        .size(10)
        .sort("id")
        .sort_order("asc")
        .build();

    // Create a list of ticket models
    List<TicketModel> ticketModels = new ArrayList<TicketModel>();
    ticketModels.add(TicketModel.builder()
        .id(1L)
        .title("Ticket 1")
        .status(1)
        .price(BigDecimal.valueOf(10.00))
        .qtyAvailable(10)
        .build());
    ticketModels.add(TicketModel.builder()
        .id(2L)
        .title("Ticket 2")
        .status(1)
        .price(BigDecimal.valueOf(20.00))
        .qtyAvailable(20)
        .build());
    ticketModels.add(TicketModel.builder()
        .id(3L)
        .title("Ticket 3")
        .status(1)
        .price(BigDecimal.valueOf(30.00))
        .qtyAvailable(30)
        .build());

    // Create a pagination object with the ticket models
    Pagination<TicketModel> expectedPagination = Pagination.<TicketModel>builder()
        .total(3)
        .page(1)
        .size(10)
        .data(ticketModels)
        .build();

    // Mock the behavior of the repository
    when(ticketRepository.listAvailable(filters)).thenReturn(expectedPagination);

    // Call the method under test
    Pagination<TicketModel> actualPagination = ticketRepository.listAvailable(filters);

    // Assert the result
    assertEquals(expectedPagination, actualPagination);
  }

}
