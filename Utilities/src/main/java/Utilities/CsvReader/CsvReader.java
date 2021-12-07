package Utilities.CsvReader;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String> GetColumnByHeader(String CsvFilePath,String columnName) {
        List<String> values = null;
        try {
            values = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\" + CsvFilePath));
            String line;
            int index = 0;

            line = br.readLine();
            String[] col = line.split(",");
            for (int i = 0; i < col.length; i++) {
                if (col[i].equals(columnName)) {
                    index  = i;
                    break;
                }
            }
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cols = line.split(",");
                values.add(cols[index]);
                System.out.println("Name:" + cols[index]);
            }
        }
        catch(IOException e)
        {
            System.out.println("Failed to open and read csv file.");
        }
        return values;
    }
    public static String GetVariableByHeader(String CsvFilePath,String columnName,int rowIndex) {
        List<String> values = null;
        try {
            values = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\" + CsvFilePath));
            String line;
            int index = 0;

            line = br.readLine();
            String[] col = line.split(",");
            for (int i = 0; i < col.length; i++) {
                if (col[i].equals(columnName)) {
                    index = i;
                    break;
                }
            }
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cols = line.split(",");
                values.add(cols[index]);
                System.out.println("Name:" + cols[index]);
            }
        }
        catch(IOException e)
        {
            System.out.println("Failed to open and read csv file.");
        }
        return values.get(rowIndex);
    }
}

