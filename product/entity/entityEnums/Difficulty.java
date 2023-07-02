package product.entity.entityEnums;

public enum Difficulty {
    EASY("Easy"), MIDDLE("Middle"), HARD("Hard");
    private final String name;

    Difficulty(String _value) {
        this.name = _value;
    }

    @Override
    public String toString() {
        return name;
    }
}
