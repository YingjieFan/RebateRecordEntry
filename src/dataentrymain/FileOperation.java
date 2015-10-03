/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataentrymain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kent
 * The records.txt file path is defaulted to the root directory. If no existing file found a new file will be created.
 * This class will accomplish file operation task. When the program initialize the we use getAllRecords() to get a List of records saved in the txt file.
 * After that all the records are maintained in Interface.java for flexible operation. 
 * When user hit exit, the file is saved to the record.txt file through saveFile() method.
 */
public class FileOperation {

    private List<Record> records; 
    File file;
    
    //Public function return the List of records extracted from txt file
    public List<Record> getAllRecords() {
        return this.records;
    }
    
    public void saveFile(List<Record> modifiedRecords){
    	this.records = modifiedRecords;
        PrintWriter writer = null;
        try {
            //Erase the orignal content of the File
            writer = new PrintWriter(file);
            writer.print("");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
        
        // The name of the file to open.
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(file);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            for(Record record:records){
            bufferedWriter.write(record.getFirstName());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getLastName());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getMI());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getAddressLine1());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getAddressLine2());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getCity());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getState());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getZipcode());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getPhone());
            bufferedWriter.write(";");
            bufferedWriter.write(record.getEmail());
            bufferedWriter.write(";");
            bufferedWriter.write(String.valueOf(record.isProofAttached()));
            bufferedWriter.write(";");
            bufferedWriter.write(record.getDate());
            bufferedWriter.newLine();           
            }

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + file.toString() + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public FileOperation() {
                                System.out.println("Haha");

        //If file not exist, create a new one
        records = new ArrayList<>();
        file = new File("records.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
            //
            // Create a new Scanner object which will read the data from the 
            // file passed in. To check if there are more line to read from it
            // we check by calling the scanner.hasNextLine() method. We then
            // read line one by one till all line is read.
            //
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Record tmpRec = new Record();
                String line = scanner.nextLine();
                String[] temp = line.split(";");
                tmpRec.setFirstName(temp[0]);
                tmpRec.setLastName(temp[1]);
                tmpRec.setMI(temp[2]);
                tmpRec.setAddressLine1(temp[3]);
                tmpRec.setAddressLine2(temp[4]);
                tmpRec.setCity(temp[5]);
                tmpRec.setState(temp[6]);
                tmpRec.setZipcode(temp[7]);
                tmpRec.setPhone(temp[8]);
                tmpRec.setEmail(temp[9]);
                tmpRec.setProofAttached(Boolean.valueOf(temp[10]));
                tmpRec.setDate(temp[11]);
                records.add(tmpRec);
            }
        } catch (FileNotFoundException e) {
            
        }
        }
        
    }

}
