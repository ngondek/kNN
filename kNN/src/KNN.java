import java.util.*;

public class KNN {
    String fileNameTestSet;
    String fileNameTrainingSet;
    List<Observation> trainingSet;
    List<Observation> testSet;
    List<Observation> answaerSet;
    int k;

    KNN(){
        trainingSet = new ArrayList<>();
        testSet = new ArrayList<>();
        answaerSet = new ArrayList<>();
    }

    public void start(){
        readK();
        readFilesNames();
        trainingSet = DataReader.readTrainingData(fileNameTrainingSet);
        testSet = DataReader.readTestData(fileNameTestSet,trainingSet.get(0).data.length);
        answaerSet = DataReader.readTrainingData(fileNameTestSet);
        classifyAll();
        showResults();
        checkAnswears();
        readNewData();
    }

    public void readK(){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj k:");
        k = Integer.parseInt(input.nextLine());
    }

    void readFilesNames(){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj nazwe pliku train-set ");
        fileNameTrainingSet = input.nextLine();
        System.out.println("Podaj nazwe pliku test-set ");
        fileNameTestSet = input.nextLine();
    }

    public void classifyAll(){
        for(Observation observation : testSet){
            observation.setIdentifier(classifyObservation(observation.getData()));
        }
    }

    public String classifyObservation(double[] newObservation) {

        ObservationDistanceValue[] observationDistanceValues = getObservationDistanceValues(newObservation);

        ObservationDistanceValue[] kMiniObservationDistanceValues = getKMiniObservationDistanceValues(observationDistanceValues);

        Map<String, Integer> potentialIdentifiersQuantities = new HashMap<>();
        for(ObservationDistanceValue observationDistanceValue : kMiniObservationDistanceValues){
            potentialIdentifiersQuantities.put(observationDistanceValue.getIdentifier(),0);
        }

        for(ObservationDistanceValue observationDistanceValue : kMiniObservationDistanceValues){
            for(String identifier : potentialIdentifiersQuantities.keySet()) {
                if (observationDistanceValue.getIdentifier().equals(identifier))
                    potentialIdentifiersQuantities.put(identifier,potentialIdentifiersQuantities.get(identifier)+1);
            }
        }


        return potentialIdentifiersQuantities.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

    }

    ObservationDistanceValue[] getObservationDistanceValues(double[] newObservation){
        ObservationDistanceValue[] observationDistanceValues = new ObservationDistanceValue[trainingSet.size()];

        for (int i = 0; i < observationDistanceValues.length; i++) {
            double lenght = 0;
            for (int j = 0; j < newObservation.length; j++) {
                lenght += Math.pow(trainingSet.get(i).getData()[j] - newObservation[j], 2);
            }
            observationDistanceValues[i] = new ObservationDistanceValue(trainingSet.get(i).getIdentifier(), lenght);
        }
        return observationDistanceValues;
    }

    ObservationDistanceValue[] getKMiniObservationDistanceValues (ObservationDistanceValue[] observationDistanceValues){
        Arrays.sort(observationDistanceValues);
        ObservationDistanceValue[] kMiniObservationDistanceValues = new ObservationDistanceValue[k];
        for(int i =0; i <kMiniObservationDistanceValues.length; i++){
            kMiniObservationDistanceValues[i] = observationDistanceValues[i];
        }
        return kMiniObservationDistanceValues;
    }


    void showResults(){
        for(Observation observation : testSet){
            System.out.println(observation);
        }
    }

    void checkAnswears(){
        double dataLength = testSet.size();
        double correct = 0;
        for (int i = 0 ; i < testSet.size(); i++) {
           if(answaerSet.get(i).getIdentifier().equals(testSet.get(i).identifier))
               correct++;
        }
        double correctFactor = correct/dataLength*100.0;
        System.out.println( "Procent prawidłowych odpowiedzi: " + correctFactor + " " + '%' );
    }

    void readNewData(){
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("Podaj wartosci atrybutow oddzielone przecinkiem");
            String[] splittedLine = input.nextLine().split(",");
            double[] list = new double[splittedLine.length];
            if(splittedLine.length == trainingSet.get(0).data.length) {
                for (int i = 0; i < splittedLine.length; i++) {
                    list[i] = Double.parseDouble(splittedLine[i]);
                }
                System.out.println(classifyObservation(list));
            }
            else
                System.out.println("Niepoprawna liczba wartosci atrybutow");
        }
    }

}
