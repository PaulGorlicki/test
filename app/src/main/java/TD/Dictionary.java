package TD;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary{
    private String name;
    private Map<String, String> translations;
    private boolean empty;

    public Dictionary() {

    }
    public Dictionary(String name) {
        this.name = name;
        this.empty = true;
        this.translations = new HashMap<String,String>();
    }

    public void addTranslation(String fr, String an) {
        this.translations.put(fr,an);
        this.empty = false;

    }

    public String getTranslation(String fr) {
        Set<String> keys = translations.keySet();
        for (String key : keys) {
            String english = translations.get(key);
            if (fr == translations.get(key)) {
                return english;
            }
        }
        return null;
    }

    public boolean getEmpty() {
        return this.empty;
    }
    public String getName() {
        return this.name;
    }
}
