import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public static List<Observation> readTrainingData(String fileName){
        List<Observation> observations = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(",");
                String decisionAttribute = splittedLine[splittedLine.length -1];
                double[] attributes = new double[splittedLine.length-1];
                for(int i =0; i<attributes.length; i++){
                    attributes[i] = Double.parseDouble(splittedLine[i]);
                }
                observations.add(new Observation(decisionAttribute, attributes));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return observations;
    }

    public static List<Observation> readTestData(String fileName, int attributesQuantity){
        List<Observation> observations = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(",");

                double[] attributes = new double[attributesQuantity];
                for(int i =0; i<attributesQuantity; i++){
                    attributes[i] = Double.parseDouble(splittedLine[i]);
                }
                observations.add(new Observation(null,attributes));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return observations;
    }
}
