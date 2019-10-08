package by.itacademy.converter.service;

import by.itacademy.converter.client.ConverterClient;
import by.itacademy.converter.dto.ConverterRequestDto;
import by.itacademy.converter.dto.ConverterResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConverterService {

    private static final String NATIONAL_CURRENCY = "BYN";
    private final ConverterClient converterClient;

    public ConverterResponseDto convert(ConverterRequestDto requestDto) {
        BigDecimal byn = requestDto.getFrom().equals(NATIONAL_CURRENCY)
                ? requestDto.getAmount()
                : convertToByn(requestDto);


        BigDecimal rate = requestDto.getTo().equals(NATIONAL_CURRENCY)
                ? new BigDecimal(1)
                : converterClient.getCurrency(requestDto.getTo()).getExactRate();

        return ConverterResponseDto.builder()
                .currency(requestDto.getTo())
                .amount(byn.divide(rate, RoundingMode.CEILING))
                .build();
    }

    private BigDecimal convertToByn(ConverterRequestDto requestDto) {
        BigDecimal rate = converterClient.getCurrency(requestDto.getFrom()).getExactRate();
        return requestDto.getAmount().multiply(rate);
    }
}
