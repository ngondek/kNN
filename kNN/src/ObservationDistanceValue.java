public class ObservationDistanceValue implements Comparable<ObservationDistanceValue> {
    private String identifier;
    private double value;

    public ObservationDistanceValue(String identifier, double value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public int compareTo(ObservationDistanceValue o) {
         if(this.getValue() - o.value > 0){
             return 1;
         }else  if (this.getValue() - o.value <0)
             return -1;
         else
             return 0;
    }

    @Override
    public String toString() {
        return this.identifier + " " + value;
    }
}
