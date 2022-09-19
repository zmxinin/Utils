package Date;

import java.text.SimpleDateFormat;

/**
 * 当前时间转换为Long类型的毫秒数 （eg：1661306920180）
 *
 * @author zmx
 * @date 2022/8/24 9:50
 */

public class DateToLong {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long totalMilliSeconds = System.currentTimeMillis();
        System.out.println(totalMilliSeconds);
    }
}

