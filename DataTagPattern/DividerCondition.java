package DataTagPattern;

public class DividerCondition implements ITruthy {
    private int divider;

    public DividerCondition(int _divider) {
        this.divider = _divider;
    }

    @Override
    public boolean isTruthy(int num) {
        return num % this.divider == 0;
    }
}
