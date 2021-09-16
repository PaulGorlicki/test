package TD;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class DictionaryTest{
    Dictionary dicTest = new Dictionary();
    @Test 
    public void testDictionaryName() {
        assertThat(dicTest.getName(), equalTo("Example"));
    }
    @Test
    public void isEmpty() {
        assertThat(dicTest.getName(), equalTo(""));
    }
}

