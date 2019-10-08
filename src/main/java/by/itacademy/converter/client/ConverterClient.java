package by.itacademy.converter.client;

import by.itacademy.converter.dto.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nbrb", url = "http://www.nbrb.by")
public interface ConverterClient {

    @GetMapping("API/ExRates/Rates/{abbreviation}?ParamMode=2")
    CurrencyDto getCurrency(@PathVariable(value = "abbreviation") String abbreviation);
}
