package by.itacademy.converter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class CurrencyDto {

    @JsonProperty("Cur_Abbreviation")
    private String name;
    @JsonProperty("Cur_OfficialRate")
    private BigDecimal rate;
    @JsonProperty("Cur_Scale")
    private BigDecimal scale;

    public BigDecimal getExactRate() {
        return rate.divide(scale, RoundingMode.CEILING);
    }
}
