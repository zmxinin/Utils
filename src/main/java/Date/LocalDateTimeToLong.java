package Date;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * LocalDateTime转Long
 *
 * @author zmx
 * @date 2022/9/17 16:32
 */

public class LocalDateTimeToLong {

    public static void main(String[] args) {
        long beginTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(beginTime);
    }
}
