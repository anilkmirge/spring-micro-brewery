package next.monster.springmicrobrewery.web.controller;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import next.monster.springmicrobrewery.services.BeerService;
import next.monster.springmicrobrewery.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(BeerController.class)
public class BeerControllerTest {
  public static final String TEST_USER_ID = "admin";
  @MockBean
  BeerService beerService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  BeerDto validBeer;


  @BeforeEach
  public void setUp() {
    validBeer = BeerDto.builder().id(UUID.randomUUID())
            .beerName("Beer1")
            .beerStyle("PALE_ALE")
            .upc(76352375274L)
            .build();
  }

//  @WithMockUser(value = "admin")
//  @Test
//  public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
//    mockMvc.perform(get("/api/v1/beer/" +  validBeer.getId().toString()).contentType(MediaType.APPLICATION_JSON))
//          .andExpect(status().isOk());
//  }

//  There are a few lines of code that tells the system to mock security so you don’t need to generate a token before running this integration test.
//  The following lines tell the system to pretend we have a valid user and token already:
//      .with(user(TEST_USER_ID))
//      .with(csrf())

  @Test
  public void getBeerById() throws Exception {
    given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);
    mockMvc.perform(get("/api/v1/beer/" +
           validBeer.getId().toString()).with(user(TEST_USER_ID)).with(csrf()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
        .andExpect(jsonPath("$.beerName", is("Beer1")));
  }

  @Test
  public void handlePost() throws Exception {
    BeerDto beerDto = validBeer;
    beerDto.setId(null);
    BeerDto savedBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
    String beerToJson = objectMapper.writeValueAsString(beerDto);

    given(beerService.saveNewBeer(any())).willReturn(savedBeer);
    mockMvc.perform(post("/api/v1/beer").with(user(TEST_USER_ID)).with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerToJson))
            .andExpect(status().isCreated());
  }

  @Test
  public void handleUpdate() throws Exception {
    BeerDto beerDto = validBeer;
    beerDto.setId(null);
    String beerToJson = objectMapper.writeValueAsString(beerDto);
    mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString()).with(user(TEST_USER_ID)).with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerToJson))
            .andExpect(status().isNoContent());
    then(beerService).should().updateBeer(any(), any());
  }

}
