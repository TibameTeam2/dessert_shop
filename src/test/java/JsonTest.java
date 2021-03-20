import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JsonTest {
    @Test
    public void json() throws JsonProcessingException {
        String str = "{\"data\":{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"ret\":0}";

        ObjectMapper mapper = new ObjectMapper();
//        把JSON字串轉JsonNode(可以直接用path遍歷)
        JsonNode root = mapper.readTree(str);

        System.out.println(root.path("data").path("birth_day"));

        JsonNode data = root.path("data");

        JsonNode birth_day = data.path("birth_day");
        System.out.println(birth_day.asInt());

        JsonNode birth_month = data.path("birth_month");
        System.out.println(birth_month.asInt());

        JsonNode msg = root.path("msg");
        System.out.println(msg.textValue());


    }
}
