package br.com.rafaelbarros.ticket.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;
import br.com.rafaelbarros.ticket.services.TicketService;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TicketControllerTest {

  @MockBean
  private TicketService ticketService;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
  }

  @DisplayName("Test listAvailable")
  @Test
  public void givenListAvailableWhenGetThenReturnOk() throws Exception {

    doReturn(Pagination.<TicketResponseBody>builder()
        .data(List.of(TicketResponseBody.builder()
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
        .when(ticketService).listAvailable(any(PaginationFilter.class));

    mockMvc.perform(get("/v1/tickets"))
        .andExpect(status().isOk());

  }

}
