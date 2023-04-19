package DataTagPattern;

import java.util.ArrayList;

public class AndStrategy extends Strategy {
    private ArrayList<ITruthy> conditionsOrStrategies = new ArrayList();

    public AndStrategy(ArrayList _conditionsOrStrategies) {
        this.conditionsOrStrategies = _conditionsOrStrategies;
    }

    @Override
    public boolean isTruthy(int num) {
        for (ITruthy item1 : conditionsOrStrategies) {
            if (!item1.isTruthy(num)) {
                return false;
            }
        }
        return true;
    }
}
