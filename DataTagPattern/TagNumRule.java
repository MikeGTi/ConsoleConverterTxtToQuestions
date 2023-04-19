package DataTagPattern;

public class TagNumRule {
    public Tag tag;
    private Strategy strategy;

    public TagNumRule(Tag _tag, Strategy _strategy) {
        this.tag = _tag;
        this.strategy = _strategy;
    }

    public boolean isSucces(int num) {
        return this.strategy.isTruthy(num);
    }
}
