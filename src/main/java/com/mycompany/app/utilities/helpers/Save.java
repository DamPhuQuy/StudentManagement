package com.mycompany.app.utilities.helpers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.mycompany.app.utilities.animation.Effect;
import com.mycompany.app.utilities.io.InformMessage;
import com.opencsv.CSVWriter;

public class Save {
    private Save() {
        // Private constructor to prevent instantiation
    }
    
    public static void saveLine(String path, List<String[]> data) {
        System.out.println("Saving data to the database..."); 
        Effect.progressBar();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            try (CSVWriter csvWriter = new CSVWriter(writer)) {
                for (String[] line : data) {
                    csvWriter.writeNext(line); 
                }
            }
        } catch (FileNotFoundException fnf) {
            InformMessage.error("File not found: " + fnf.getMessage());
        } catch (IOException ioe) {
            InformMessage.error("Error writing file: " + ioe.getMessage());
        } catch (Exception e) {
            InformMessage.error("An unexpected error occurred: " + e.getMessage());
        } finally {
            InformMessage.success("Data saving completed.");
        }
    }
}
