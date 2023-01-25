package de.struma.SuperDuperMarkt.service;

import de.struma.SuperDuperMarkt.model.Artikel;
import de.struma.SuperDuperMarkt.modul.CSVReader;
import de.struma.SuperDuperMarkt.repository.ArtikelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ImportService {

    ArtikelRepository artikelRepository;
    CSVReader csvReader;
    public ImportService(ArtikelRepository artikelRepository, CSVReader csvReader){
        this.artikelRepository = artikelRepository;
        this.csvReader = csvReader;
    }

    public void readCsvToRepo(MultipartFile file) throws IOException {
        List<Artikel> result = parseArray(csvReader.readFile(file));
        artikelRepository.saveAllAndFlush(result);
    }

    public List<Artikel> parseArray(String[][] arr) {

        List<Artikel> artikels = new ArrayList<>();
        String[] checkAttributeArray = arr[0];
        int[] setAttributeArray = new int[6];
        setAttribute(setAttributeArray, checkAttributeArray);
        DateTimeFormatter german = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int i = 1; i < arr.length; i++) {
            Artikel setArtikel = new Artikel();
            for (int j = 0; j < arr[i].length; j++) {

                if(setAttributeArray[0]==j) // set Bezeichnung
                    setArtikel.setBezeichnung(arr[i][j].trim());
                if(setAttributeArray[1]==j){ // set Verfallsdatum
                    if(!arr[i][j].contains("null") || !arr[i][j].isEmpty())
                        setArtikel.setVerfallsDatum(LocalDate.parse(arr[i][j].trim(),german));
                }
                if(setAttributeArray[2]==j) // set Grundqualität
                    setArtikel.setGrundQualitaet(Integer.parseInt(arr[i][j].trim()));
                if(setAttributeArray[3]==j) // set Grundpreis
                    setArtikel.setGrundPreis(Double.parseDouble(arr[i][j].trim()));
                if(setAttributeArray[4]==j) // set Buchungsdatum
                    setArtikel.setBuchungsDatum(LocalDate.parse(arr[i][j].trim(),german));
                if(setAttributeArray[5]==j) // set Kategorie
                    setArtikel.setKategorie(arr[i][j].trim());
            }
            if(!setArtikel.getBezeichnung().isEmpty()&&!setArtikel.getKategorie().isEmpty())
                artikels.add(setArtikel);
        }
        return artikels;
    }

    private void setAttribute(int[] setAttribute, String[] input){
        for (int i = 0; i < input.length; i++) {
            if(input[i].toLowerCase().contains("Bezeichnung".toLowerCase()))
                setAttribute[0]=i;
            if(input[i].toLowerCase().contains("Verfallsdatum".toLowerCase()))
                setAttribute[1]=i;
            if(input[i].toLowerCase().contains("Grundqualität".toLowerCase()))
                setAttribute[2]=i;
            if(input[i].toLowerCase().contains("Preis".toLowerCase()))
                setAttribute[3]=i;
            if(input[i].toLowerCase().contains("BuchungsDatum".toLowerCase()))
                setAttribute[4]=i;
            if(input[i].toLowerCase().contains("Kategorie".toLowerCase()))
                setAttribute[5]=i;

        }
    }

}
