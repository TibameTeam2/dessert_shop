import com.member.model.MemberBean;
import org.junit.Test;

import java.lang.reflect.Field;
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
//        m.invoke(array,new Object());
//        System.out.println("array = " + array);

//        ArrayList<Integer> array1 = new ArrayList<Integer>();
//        m.invoke(array1,9.222);
//        System.out.println("array1 = " + array1);




//        MemberBean member =new MemberBean();
//        Class c = member.getClass();
//        Field[] fields = c.getDeclaredFields();
//        for(Field field : fields) {
//            System.out.println(field);
//        }
//        Field account = c.getDeclaredField("member_account");
//        account.setAccessible(true);
//        account.set(member,"123");
//        System.out.println("member = " + member);

//        Field status = c.getDeclaredField("member_status");
//        status.setAccessible(true);
//        status.set(member,"wreofcwefc");
//        System.out.println("member = " + member);


//        Method[] methods = c.getDeclaredMethods();
//        for(Method method : methods) {
//            System.out.println(method);
//        }








    }
}
