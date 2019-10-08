package by.itacademy.converter.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConverterRequestDto {

    private String from;
    private String to;
    private BigDecimal amount;
}
