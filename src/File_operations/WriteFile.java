package File_operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WriteFile {
	static FileWriter myWriter=null;
	public static void writeFile(HashMap<String, Integer> goodies, Integer value) {
		try {
//			 enter the path to write the file
			myWriter = new FileWriter("C:\\Users\\Raghu\\Desktop\\High peak\\Goodie\\output.txt");
			myWriter.write("The goodies selected for distribution are:\n\n");
			

			for (Map.Entry<String, Integer> en : goodies.entrySet()) {
				myWriter.write(en.getKey()+": "+en.getValue()+"\n");
			}
			myWriter.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is "+value);

			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		finally {
			try {
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writeFileMessage(String message)
	{
		try {
//			 enter the path to write the file
			myWriter = new FileWriter("C:\\Users\\Raghu\\Desktop\\High peak\\Goodie\\output.txt");
			
			myWriter.write(message);

		
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		finally {
			try {
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
