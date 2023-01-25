package de.struma.SuperDuperMarkt.modul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Slf4j
@Component
public class CSVReader {

    public String[][] readFile(MultipartFile file) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();

        String [][] data = new String[lines][];

        int row = 0;
        reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            data[row++] = line.split(",");
        }
        reader.close();
        return data;
    }
}
