package File_operations;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import Logic.GoodiesLogic;

public class ReadFile {
	public static void readFile() {
		File myObj;
		Scanner myReader = null;
		HashMap<String, Integer> goodies= new HashMap();
		boolean firstLine=true;
		Integer numberOfEmployee=null;

		try {
			//			Enter the path to write the file
			myObj = new File("C:\\Users\\Raghu\\Desktop\\High peak\\Goodie\\input.txt");
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				String value=ReadFile.getPrice(data.substring(data.indexOf(':', 0)+1));

				if(value!=null && !data.isBlank()&& !firstLine)
				{

					String productName=ReadFile.getProductName(data);
					goodies.put(productName, Integer.parseInt(value));
				}
				if(firstLine) {
					numberOfEmployee=Integer.parseInt(value);
					firstLine=false;
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}finally {
			myReader.close();
		}

		if(numberOfEmployee>goodies.size())
		{
			WriteFile.writeFileMessage("Number of employees are greater than number of goodies");
		}
		else if(numberOfEmployee==0)
		{
			WriteFile.writeFileMessage("Number of employees should be greater than 0");	
		}
		else {
			GoodiesLogic goodiesLogic = new GoodiesLogic();
			goodiesLogic.findGoodies(goodies, numberOfEmployee);
		}

	}


	private static String getPrice(String sample)
	{

		char[] chars = sample.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(char c : chars){
			if(Character.isDigit(c)){
				sb.append(c);
			}
		}
		return sb.toString().matches(".*\\d.*") ? sb.toString():null;
	}

	private static String getProductName(String data)
	{
		return data.substring(0,data.indexOf(':', 0));
	}



}
