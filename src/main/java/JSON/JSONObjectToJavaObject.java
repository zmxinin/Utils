package JSON;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * 从 JSON 对象到 Java 变量的解码过程: 首先从 JSON 格式的字符串中构造一个 JSON 对象，之后依次读取字符串、整数、布尔值以及数组，最后分别打印
 *
 * @author zmx
 * @date 2022/9/9 10:52
 */

public class JSONObjectToJavaObject {

    public static void test(){
        JSONObject object = JSONObject.parseObject("{\"boolean\":true,\"string\":\"string\",\"list\":[1,2,3],\"int\":2}");
        //string
        String s = object.getString("string");
        System.out.println(s);
        //int
        int i = object.getIntValue("int");
        System.out.println(i);
        //boolean
        boolean b = object.getBooleanValue("boolean");
        System.out.println(b);
        //list
        List<Integer> integers = JSON.parseArray(object.getJSONArray("list").toJSONString(),Integer.class);
        integers.forEach(System.out::println);
        //null
        System.out.println(object.getString("null"));
    }

    public static void main(String[] args) {
        JSONObjectToJavaObject.test();
    }
}
