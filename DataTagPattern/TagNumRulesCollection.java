package DataTagPattern;

import java.util.ArrayList;

public class TagNumRulesCollection {
    private ArrayList<TagNumRule> tags;

    public TagNumRulesCollection(ArrayList<Object> _tag) {
        this.tags = new ArrayList(_tag);
    }

    public String find(int num, String defaultValue) {
        for (TagNumRule item1 : tags) {
            if (item1.isSucces(num)) {
                return item1.tag.getValue();
            }
        }
        return defaultValue;
    }
}
