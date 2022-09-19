package Date;

import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * date 转换为 LocalDateTime
 *
 * @author zmx
 * @date 2022/9/17 17:01
 */

public class DateToLocalDateTime {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);

        //将此日期对象转换为瞬间
        Instant instant = date.toInstant();
        //获取系统默认时区。
        ZoneId zoneId = ZoneId.systemDefault();
        //转为LocalDateTime
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        System.out.println(localDateTime);
    }
}
