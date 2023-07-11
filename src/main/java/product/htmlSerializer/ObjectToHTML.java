package product.htmlSerializer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectToHTML {

    private Map<String,String> fieldTagMap;

    public ObjectToHTML(Map<String,String> fieldTagMap) {
        this.fieldTagMap = fieldTagMap;
    }

    public void setFieldTagMap(Map<String, String> fieldTagMap) {
        this.fieldTagMap = fieldTagMap;
    }

    public String convertToHTML(Object o){
        try{
            return getHTMLtoString(o);
        }catch (Exception e){
            throw new RuntimeException("converting to HTML has been broken");
        }
    }

    private String getHTMLtoString(Object o) throws Exception {
        Map<String,String> htmlElements = new HashMap<>();
        htmlElements = getHTMLelements(o);

        StringBuilder stringBuilder = new StringBuilder();
                    //stringBuilder.append("\n");

        for(Map.Entry<String, String> entry : htmlElements.entrySet()){
            if (fieldTagMap.containsKey(entry.getKey())){
                String taggedFieldValue = fieldTagMap.get(entry.getKey());
                stringBuilder.append(taggedFieldValue.replace("dummyValue",entry.getValue())).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private Map<String,String> getHTMLelements(Object o) throws Exception{
        Map<String,String> htmlElements = new HashMap<>();

        Class<? extends Object> class1 = o.getClass();

        /*Class.getDeclaredMethods()
        for (Method method : class1.getDeclaredMethods()){
            method.setAccessible(true);
            if (method.isAnnotationPresent(HTMLelement.class)){
                htmlElements.put(method.getName(), String.valueOf(method.get(o)));
            }
        }*/

        for (Field field : class1.getDeclaredFields()){
            field.setAccessible(true);
            if (field.isAnnotationPresent(HTMLelement.class)){
                htmlElements.put(getKey(field), String.valueOf(field.get(o)));
            }
        }

        if (htmlElements.isEmpty()){
            Class<? extends Object> class2 = class1.getSuperclass();
            for (Field field : class2.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(HTMLelement.class)) {
                    htmlElements.put(getKey(field), String.valueOf(field.get(o)));
                }
            }
        }
        return htmlElements;
    }


    private String getKey(Field field){
        HTMLelement htmlElement = field.getAnnotation(HTMLelement.class);
        if (htmlElement.key().equals("")){
            return field.getName();
        } else {
            return htmlElement.key();
        }
    }

}
