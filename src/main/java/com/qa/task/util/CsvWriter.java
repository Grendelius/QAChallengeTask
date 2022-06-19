package com.qa.task.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@UtilityClass
public class CsvWriter {

    @SneakyThrows
    public static <T> File writeCsv(String fileName, String[] headers, List<String[]> values) {
        var tempFile = File.createTempFile(fileName, ".csv");
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(tempFile),
                CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader(headers).build())) {

            for (String[] value : values) {
                printer.printRecord(value);
            }
            return tempFile;

        } catch (IOException e) {
            throw new AssertionError("Impossible to create %s file".formatted(fileName));
        }
    }

}
