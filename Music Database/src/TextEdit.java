import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class TextEdit {
	public static void main(String args[]){
		System.out.println("main");

		try {
			TextEdit.reformat("Database.txt", "Easy_To_Read_Database.txt");
		} catch (IOException e) {
			System.out.println("File not found");
		}
		System.out.print("Finished");
	}


	public static void reformat(String inputFileName, String outputFileName) throws IOException{
		BufferedReader reader;
		PrintWriter writer;
		String[] arr;
		String currentLine;
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
			writer = new PrintWriter(outputFileName);
			
			currentLine = reader.readLine();
			while(null != currentLine){
				arr = currentLine.split("\\)");
				writer.print(arr[0]);
				for(int i = 1; i < arr.length; i++){
					writer.println(arr[i] + ")" + "\n");
				}
				currentLine = reader.readLine();
			}
			
			reader.close();
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void printCode(String[] arr, PrintWriter writer){
		for (int i = 0; i < arr.length; i++){
			writer.println("INSERT" + arr[i]+ "\n");
		}  

	}
}
