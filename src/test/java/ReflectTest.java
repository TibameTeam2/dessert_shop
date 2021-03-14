import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectTest {
    @Test
    public void arrayTest() throws Exception {
        ArrayList<Integer> array = new ArrayList<Integer>();

        array.add(10);
        array.add(20);
//        array.add("hello");
        System.out.println(array);











//        Class c = array.getClass();
//        Method m = c.getMethod("add", Object.class);
//        m.invoke(array,"hello");
//        m.invoke(array,9.222);
//        System.out.println(array);

    }
}
