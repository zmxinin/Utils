package JSON;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * JSON 对象与字符串的相互转化
 *
 * @author zmx
 * @date 2022/9/9 11:02
 */

public class JSONObjectAndJSONString {
    /**
     * JSON.parseObject()	        从字符串解析 JSON 对象
     * JSON.parseArray()	        从字符串解析 JSON 数组
     * JSON.toJSONString(obj/array)	将 JSON 对象或 JSON 数组转化为字符串
     */
    public static void test(){
        //从字符串解析JSON对象
        JSONObject obj = JSON.parseObject("{\"runoob\":\"菜鸟教程\"}");
        System.out.println(obj);
        //将JSON对象转化为字符串
        String objStr = JSON.toJSONString(obj);
        System.out.println(objStr);

        //从字符串解析JSON数组
        JSONArray arr = JSON.parseArray("[\"菜鸟教程\",\"RUNOOB\"]\n");
        System.out.println(arr);
        //将JSON数组转化为字符串
        String arrStr = JSON.toJSONString(arr);
        System.out.println(arrStr);
    }

    public static void main(String[] args) {
        JSONObjectAndJSONString.test();
    }
}
