# Stock Gainers API

Spring Boot REST API to upload a CSV file containing stock data and return the top 5 gainers by percentage change.

## CSV Format
Expected columns:

`Symbol,Open,High,Low,Close`

## Run
```bash
mvn spring-boot:run
```

## API
`POST /api/stocks/gainers`

- Content-Type: `multipart/form-data`
- Form field: `file` (CSV file)

### Example cURL
```bash
curl -X POST http://localhost:8080/api/stocks/gainers \
  -F "file=@stocks.csv"
```

### Sample Response
```json
[
  { "symbol": "GOOG", "percentChange": 16.6666666667 },
  { "symbol": "TSLA", "percentChange": 10.0 }
]
```
