package JSON;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从 Java 变量到 JSON 格式的编码过程: 首先建立一个 JSON 对象，然后依次添加字符串、整数、布尔值以及数组
 *
 * @author zmx
 * @date 2022/9/8 9:19
 */

public class JavaObjectToJSON {

    public static void test() {
        JSONObject object = new JSONObject();
        //string
        object.put("string","string");
        //int
        object.put("int",2);
        //boolean
        object.put("boolean",true);
        //array
        List<Integer> integers = Arrays.asList(1,2,3);
        object.put("list",integers);
        //null
        object.put("null",null);
        System.out.println(object);
    }

    public static void main(String[] args) {
        JavaObjectToJSON.test();
    }
}
