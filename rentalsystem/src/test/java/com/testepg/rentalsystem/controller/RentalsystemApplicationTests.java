package com.testepg.rentalsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.testepg.rentalsystem.exceptions.RestNotFoundException;
import com.testepg.rentalsystem.service.IRentalService;
import com.testepg.rentalsystem.vo.Game;
import com.testepg.rentalsystem.vo.Rental;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RentalsystemApplicationTests {

	RentalController controller;
	
	@Mock
	private IRentalService mockRentalService;
	
    private MockMvc mockMvc;
    
    private static final String RENTING_PATH = "/rental";
    private static final String RETURN_PATH = "/return/game/1/user/2/2019-09-19";
    
    @Before
	public void setUp() throws Exception {
		controller = new RentalController(mockRentalService);
		mockMvc = standaloneSetup(controller).build();
	}
    
    @Test
    public void rentingGames_whenResourceNotFoundExceptionIsThrown_thenRestNotFoundExceptionIsReturned() throws Exception {
    	
    	// Given
    	Rental rental = new Rental(new Long(1), Arrays.asList(new Game(new Long(1), 1)));
		String rentalJSON = mapeoJson(rental);
    	
		// When/Then
		when(mockRentalService.rentingGames(any())).thenThrow(RestNotFoundException.class);
		
    	mockMvc.perform(post(RENTING_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(rentalJSON))
    			.andExpect(status().isNotFound());
    }
    
    @Test
    public void rentingGames_whenInputDateIsGivenByService_thenOkHttpCodeIsReturned() throws Exception {
    	
    	// Given
    	Rental rental = new Rental(new Long(1), Arrays.asList(new Game(new Long(1), 1), 
    														  new Game(new Long(2), 5)));
    	String rentalJson = mapeoJson(rental);
    	
    	// When/Then 
    	when(mockRentalService.rentingGames(rental)).thenReturn(anyInt());
    	
    	mockMvc.perform(post(RENTING_PATH).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(rentalJson))
    			.andExpect(status().isOk());
    }
    
    @Test
    public void returnGameRented_whenResourceNotFoundExceptionIsThrown_thenRestNotFoundExceptionIsReturned() throws Exception {
    	
		// When/Then
		when(mockRentalService.returnGameRented(any(), any(), any())).thenThrow(RestNotFoundException.class);
    	mockMvc.perform(get(RETURN_PATH)).andExpect(status().isNotFound());
    }
    
    @Test
    public void returnGameRented_whenInputDateIsGivenByService_thenOkHttpCodeIsReturned() throws Exception {
    	
    	// When/Then 
    	when(mockRentalService.returnGameRented(any(), any(), any())).thenReturn(10L);
    	
    	mockMvc.perform(get(RETURN_PATH)).andExpect(status().isOk());
    }
    
    public static <T> String mapeoJson(T object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		return ow.writeValueAsString(object);
	}

}
