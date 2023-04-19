package DataTagPattern;

public class Tag {
    private String value;

    public Tag(String _value) {
        this.value = _value;
    }

    public Tag(int _value) {
        this.value = Integer.toString(_value);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
