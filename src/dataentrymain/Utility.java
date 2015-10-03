/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataentrymain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kent
 * This class provide utility methods for data validation and formatting input data. It also maintains some constant and static data which will be needed to update interface
 * 
 */
public class Utility {
	
	//String array that holds all the error messages corresponding to each field
	public static final String[] errorMessageList = {
			"First Name(Maximum 20 characters)",
			"Last Name(Maximum 20 characters)",
			"Middle Name(0 or 1 character)",
			"Address Line 1(Maximum 35 characters)",
			"Address Line 2(Optional and maximum 35 characters)",
			"City(Maximum 25 characters)",
			"State(Maximum 2 characters)",
			"Zip Code(Maximum 10 digits including '-')",
			"Phone number(10 to 21 characters and numeric)",
			"Email(Maximum 60 characters and must contain '@' and '.')",
			"Proof Of Purchase is always valid(Yes or no)",
			"Date(Maximum 20 characters)"
	};
	public static final String FN= "First Name(Maximum 20 characters)";
	public static final String LN= "Last Name(Maximum 20 characters)";
	public static final String MI= "Middle Name(0 or 1 character)";
	public static final String ADD1= "Address Line 1(Maximum 35 characters)";
	public static final String ADD2= "Address Line 2(Optional and maximum 35 characters)";
	public static final String CITY= "City(Maximum 25 characters)";
	public static final String STATE= "State(Maximum 2 characters)";
	public static final String ZIP= "Zip Code(Maximum 10 digits including '-')";
	public static final String PHONE= "Phone number(10 to 21 characters)";
	public static final String EML= "Email(Maximum 60 characters and must contain '@' and '.')";
	public static final String DATE= "Date(Maximum 20 characters)";
	
	//The record will be replaced by a record instance passed in by the Interface class
	private Record record;
	//We use fieldOrder to get the index of each field when we need
    private static final String[] fieldOrder = Record.fieldOrder;
    //{"firstName","lastName","MI","addressLine1","addressLine2","city","state","zipcode","phone","email","proofAttached","date"};
    //the int array to mark the state of each field. 0 means invalid input. 1 means valid. It will be passed to Interface class for it to determine which message to print
    int[] fieldInformation = {0,0,0,0,0,0,0,0,0,0,0,0};
    
    
    public Utility(Record record) {
        this.record = record;       
    }
    //Validate each field and return the information of which field are not valid
    public int[] getFieldInformation(){
    	checkFN();
    	checkLN();
    	checkMI();
    	checkAdd1();
    	checkAdd2();
    	checkCity();
    	checkState();
    	checkZipCode();
    	checkPhone();
    	checkEmail();
    	checkProof();
    	checkDate();
    	return fieldInformation;
    }
    //Method to check if there already a record with same First Name and Last Name and Middle Name in the file, return true if founded
    public boolean checkDupRecord(List<Record> recordLst, Record record){
    	for(Record tmp:recordLst){
    		//Check if the tmp record first name and last name and MI are all identical, if so return false
    		if(tmp.getFirstName().equalsIgnoreCase(record.getFirstName())&&tmp.getLastName().equalsIgnoreCase(record.getLastName())&&tmp.getMI().equalsIgnoreCase(record.getMI())){
    			return true;
    		}
    	}
		return false;
    	
    }
    //Return the formatted record. Format is processed in each check method and update in the global record instance
    public Record getFormattedRec(){
    	return this.record;
    }
    
    //Check first name
    private void checkFN(){
    	int firstNameIndex = Arrays.asList(fieldOrder).indexOf("firstName");
    	String input = record.getFirstName();
    	input = input.replace(" ", "");
    	//The field must be less than 20 characters and must not be 0 in length(Eliminate spaces)
    	if(input.length()<20&&input.length()>0){
    		fieldInformation[firstNameIndex]=1;
    		record.setFirstName(input);
    	}
    	
    }
    
    //Check last name
    private void checkLN(){
    	int lastNameIndex = Arrays.asList(fieldOrder).indexOf("lastName");
    	String input = record.getLastName();
    	input = input.replace(" ", "");
    	//The field must be less than 20 characters and must not be 0 in length(Eliminate spaces)
    	if(input.length()<20&&input.length()>0){
    		fieldInformation[lastNameIndex]=1;
    		record.setLastName(input);
    	}
    }
    //Check middle name
    private void checkMI(){
    	int MIIndex = Arrays.asList(fieldOrder).indexOf("MI");
    	String input = record.getMI();
    	input = input.replace(" ", "");
    	if(input.length()<=1){
    		fieldInformation[MIIndex]=1;
    		if(input.length()==0){
    			record.setMI("N/A");
    		}else{
    			record.setMI(input);
    		}
    			
    		
    	}

    }
    
    //Check address line 1
    private void checkAdd1(){
    	int add1Index = Arrays.asList(fieldOrder).indexOf("addressLine1");
    	String input = record.getAddressLine1();
    	String tempStr = input.replace(" ", "");
    	if(tempStr.length()!=0&&input.length()<=35){
    		fieldInformation[add1Index]=1;
    		record.setAddressLine1(input);
    	}

    }
    
    //Check address line 1
    private void checkAdd2(){
    	int add2Index = Arrays.asList(fieldOrder).indexOf("addressLine2");
    	String input = record.getAddressLine2();
    	String tempStr = input.replace(" ", "");
    	if(input.length()<=35){
    		fieldInformation[add2Index]=1;
    		if(tempStr.length()==0){
        		record.setAddressLine2("N/A");
    		}else{
    			record.setAddressLine1(input);
    		}
    		
    	}

    }
    
    //Check field "City"
    private void checkCity(){
    	int cityIndex = Arrays.asList(fieldOrder).indexOf("city");
    	String input = record.getCity();
    	String tmpStr = input.replace(" ", "");
    	if(tmpStr.length()!=0&&input.length()<=25){
    		fieldInformation[cityIndex]=1;  
    		record.setCity(input);
    	}
    }
    
    //Check field "State"
    private void checkState(){
    	int stateIndex = Arrays.asList(fieldOrder).indexOf("state");
    	String input = record.getState();
    	String tmpStr = input.replace(" ", "");
    	if(tmpStr.length()!=0&&input.length()==2){
    		fieldInformation[stateIndex]=1;
    		record.setState(input);
    	}
    }
    
  //Check field "Zip Code"
    private void checkZipCode(){
    	int zipIndex = Arrays.asList(fieldOrder).indexOf("zipcode");
    	String input = record.getZipcode();
    	if(input.length()>10|input.length()<5) 
    		return;
    	if(input.contains("-")){
    		String tmpStr = input.replace("-", "");
    		if(!tmpStr.matches("[-+]?\\d+(\\.\\d+)?"))
    			return;   		
    	}
    	fieldInformation[zipIndex]=1;
    	record.setZipcode(input);

    }
  //Check field "Phone"
    private void checkPhone(){
    	int phoneIndex = Arrays.asList(fieldOrder).indexOf("phone");
    	String input = record.getPhone();
    	try{
    		Long.parseLong(input);
    		//Integer.parseInt(input);
    	}catch(NumberFormatException nfe){
    		System.out.println(input);
    		return;
    	}
    	if(input.length()>9&&input.length()<21){
    		fieldInformation[phoneIndex]=1;
    		record.setPhone(input);
    	}
    }
    
  //Check field "Email"
    private void checkEmail(){
    	int emailIndex = Arrays.asList(fieldOrder).indexOf("email");
    	String input = record.getEmail();
    	if(input.contains("@")&&input.contains(".")&&input.length()<60){
    		fieldInformation[emailIndex]=1;
    		record.setEmail(input);  

    	}

    }
    
  //Check proof
    private void checkProof(){
    	int proofIndex = Arrays.asList(fieldOrder).indexOf("proofAttached");
    	String input = String.valueOf(record.isProofAttached());
		fieldInformation[proofIndex]=1;
		record.setProofAttached(Boolean.valueOf(input));


    }
    
    //Check the date input
    private void checkDate(){
    	int dateIndex = Arrays.asList(fieldOrder).indexOf("date");
    	String input = record.getDate();
    	if(input.length()==10){
    		fieldInformation[dateIndex]=1;
    		record.setDate(input);
    	}



    }
}
