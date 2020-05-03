package com.suspendfun;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CsvService csvService;

    public CustomerController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/transactions")
    public List<CsvRecord> transactions() {
        return csvService.generate();
    }
}
