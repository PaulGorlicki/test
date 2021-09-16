package TD;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class DictionaryTest{
    Dictionary dicTest = new Dictionary("test");
    @Test 
    public void testDictionaryName() {
        assertThat(dicTest.getName(), equalTo("test"));
    }
    @Test
    public int isEmpty() {
        assertThat(dicTest.getName(), equalTo(""));
        return 0;
    }
    @Test 
    public void testOneTranslation() {
        dicTest.addTranslation("contre", "against");
        assertThat(dicTest.getTranslation("contre"), equalTo("against"));
} 
    @Test 
    public void testOneTranslation2() {
        dicTest.addTranslation("oui", "yes");
        assertThat(dicTest.getTranslation("oui"), equalTo("yes"));
} 

    @Test
    public void getTranslationTest() {
        assertThat(dicTest.getName(), equalTo(dicTest.getTranslation(dicTest.getName())));
    }
}


