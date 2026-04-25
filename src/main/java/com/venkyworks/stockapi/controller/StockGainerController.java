package com.venkyworks.stockapi.controller;

import com.venkyworks.stockapi.model.StockGainerResponse;
import com.venkyworks.stockapi.service.StockGainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockGainerController {

    private final StockGainerService stockGainerService;

    public StockGainerController(StockGainerService stockGainerService) {
        this.stockGainerService = stockGainerService;
    }

    @PostMapping("/gainers")
    public ResponseEntity<List<StockGainerResponse>> uploadAndGetTopGainers(
            @RequestParam("file") MultipartFile file) {
        List<StockGainerResponse> response = stockGainerService.getTopGainers(file);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }
}
