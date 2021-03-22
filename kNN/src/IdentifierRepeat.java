public class IdentifierRepeat implements Comparable<IdentifierRepeat> {
    String identifier;
    int repeatQuantity;

    public IdentifierRepeat(String identifier, int repeatQuantity) {
        this.identifier = identifier;
        this.repeatQuantity = repeatQuantity;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getRepeatQuantity() {
        return repeatQuantity;
    }

    public void setRepeatQuantity(int repeatQuantity) {
        this.repeatQuantity = repeatQuantity;
    }

    @Override
    public int compareTo(IdentifierRepeat o) {
        return o.getRepeatQuantity() - this.repeatQuantity;
    }
}
