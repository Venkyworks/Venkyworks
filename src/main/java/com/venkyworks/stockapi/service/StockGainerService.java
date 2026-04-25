package com.venkyworks.stockapi.service;

import com.venkyworks.stockapi.model.StockGainerResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StockGainerService {

    private static final int TOP_N = 5;

    public List<StockGainerResponse> getTopGainers(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("CSV file is required.");
        }

        List<StockGainerResponse> gainers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                if (firstLine && line.toLowerCase().startsWith("symbol,")) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;

                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue;
                }

                String symbol = parts[0].trim();
                if (symbol.isEmpty()) {
                    continue;
                }

                try {
                    double open = Double.parseDouble(parts[1].trim());
                    double close = Double.parseDouble(parts[4].trim());

                    if (open == 0.0) {
                        continue;
                    }

                    double percentageChange = ((close - open) / open) * 100.0;
                    gainers.add(new StockGainerResponse(symbol, percentageChange));
                } catch (NumberFormatException ignored) {
                    // Skip invalid numeric rows.
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read CSV file.", e);
        }

        return gainers.stream()
                .sorted(Comparator.comparingDouble(StockGainerResponse::percentChange).reversed())
                .limit(TOP_N)
                .toList();
    }
}
