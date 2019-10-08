package by.itacademy.converter.controller;

import by.itacademy.converter.dto.ConverterRequestDto;
import by.itacademy.converter.dto.ConverterResponseDto;
import by.itacademy.converter.service.ConverterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ConverterController {

    private final ConverterService converterService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ConverterResponseDto> convert(@RequestBody ConverterRequestDto converterRequestDto) {
        ConverterResponseDto response = converterService.convert(converterRequestDto);
        return ResponseEntity.ok(response);
    }
}
