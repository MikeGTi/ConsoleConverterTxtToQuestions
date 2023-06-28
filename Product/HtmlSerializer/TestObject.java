package Product.HtmlSerializer;

import Product.HtmlWrap.HtmlWrappable;

import java.util.HashMap;

public class TestObject implements HtmlWrappable {
    @HTMLelement
    private String name;
    @HTMLelement(key = "Counter")
    private int count;
    @HTMLelement(key = "Буква")
    private char ch;

    public TestObject(String name, int count, char ch) {
        this.name = name;
        this.count = count;
        this.ch = ch;
    }

    @Override
    public String toHTML() {
        return null;
    }

    @Override
    public HashMap<String, String> getHtmlTagFieldMap() {
        HashMap<String,String> tagsMap = new HashMap<>();
        tagsMap.put("name","<div>dummyValue</div>");
        tagsMap.put("count","<div>dummyValue</div>");
        tagsMap.put("Counter","<div>dummyValue</div>");
        tagsMap.put("ch","<div>dummyValue</div>");
        tagsMap.put("Буква","<div>dummyValue</div>");
        return tagsMap;
    }
}
