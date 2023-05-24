package BusinessLayer;

import java.util.HashMap;

public interface HtmlWrappable {
    public String toHTML();
    public HashMap<String,String> getHtmlTagFieldMap();
}
