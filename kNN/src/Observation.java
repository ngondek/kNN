public class Observation {
    String identifier;
    double [] data;

    public Observation(String identifier, double[] data) {
        this.identifier = identifier;
        this.data = data;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String show = "";
        for(int i =0; i<data.length; i++){
            show += data[i] + " ";
        }
        show += " " + this.identifier;
        return show;
    }
}
