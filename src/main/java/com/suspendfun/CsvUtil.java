package com.suspendfun;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvUtil {
    public static <T> List<T> loadCsv(Class<T> type, CsvSchema csvSchema, InputStream inputStream) {
        ObjectReader objectReader = new CsvMapper().findAndRegisterModules().readerFor(type);
        try {
            return objectReader.with(csvSchema).<T>readValues(inputStream).readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
