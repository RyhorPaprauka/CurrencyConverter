package by.itacademy.converter.controller;

import by.itacademy.converter.dto.ConverterResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void convertRUB() throws Exception {
        MvcResult result = mockMvc.perform(post("/convert")
                .content("{ \"from\":\"RUB\", \"to\":\"EUR\",\"amount\":1000}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        ConverterResponseDto dto = objectMapper.readValue(json, ConverterResponseDto.class);

        assertEquals("EUR", dto.getCurrency());
        assertEquals(14, dto.getAmount().intValue());
    }

    @Test
    public void convertBYN() throws Exception {
        MvcResult result = mockMvc.perform(post("/convert")
                .content("{ \"from\":\"BYN\", \"to\":\"KZT\",\"amount\":1000}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        ConverterResponseDto dto = objectMapper.readValue(json, ConverterResponseDto.class);

        assertEquals("KZT", dto.getCurrency());
        assertEquals(185186, dto.getAmount().intValue());
    }

    @Test
    public void convertToBYN() throws Exception {
        MvcResult result = mockMvc.perform(post("/convert")
                .content("{ \"from\":\"EUR\", \"to\":\"BYN\",\"amount\":1000}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String json = result.getResponse().getContentAsString();
        ConverterResponseDto dto = objectMapper.readValue(json, ConverterResponseDto.class);

        assertEquals("BYN", dto.getCurrency());
        assertEquals(2271, dto.getAmount().intValue());
    }
}