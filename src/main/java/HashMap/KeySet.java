package HashMap;

import java.util.HashMap;
import java.util.Set;

/**
 * 功能描述
 *
 * @author zmx
 * @date 2022/8/23 21:54
 */

public class KeySet {
    public static void main(String[] args) {
        HashMap<Integer,String> hashMap = new HashMap<>();
        hashMap.put(1,"1");
        hashMap.put(2,"2");
        hashMap.put(3,"3");
        Set<Integer> keySet = hashMap.keySet();//获取key集合
        System.out.print("第一次遍历结果");
        for (Integer i:keySet) {
            System.out.print(i+"\t");//遍历keySet
        }
        System.out.println();

        System.out.print("第二次遍历结果");
        keySet.remove(2);//为什么执行到这步竟然把map的value也给删除了？    我只是操作了遍历出来的key集合阿
        //重新遍历key发现map里的<2,"2">已经被删除了
        Set<Integer> keySet2 = hashMap.keySet();
        for (Integer i:keySet2) {
            System.out.print(i+"\t");
        }
    }
}
