package com.suspendfun;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CsvService {
    private final List<CsvRecord> records;
    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public CsvService(ResourceLoader resourceLoader) {
        logger.info("Loading data");
        var schema = CsvSchema.builder().build();
        try {
            var csv = resourceLoader.getResource("classpath:data/file.csv").getInputStream();
            records = CsvUtil.loadCsv(CsvRecord.class, schema, csv);
            logger.info(String.format("Loaded %s records", records.size()));
        } catch (IOException e) {
            logger.severe("Cannot load. " + e);
            throw new RuntimeException(e);
        }
    }

    public List<CsvRecord> generate() {
        return records;
    }
}
