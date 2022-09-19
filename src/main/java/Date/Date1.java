package Date;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * 传入String类型日期(yyyy-MM-dd HH:mm:ss)，返回字符串类型时间（ISO8601标准时间）
 */

public class Date1 {

    public static String getISO8601TimestampFromDateStr(String timestamp){
        java.time.format.DateTimeFormatter dtf1 = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(timestamp,dtf1);
        ZoneOffset offset = ZoneOffset.of("+08:00");
        OffsetDateTime date = OffsetDateTime.of(ldt ,offset);
        java.time.format.DateTimeFormatter dtf2 = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        return date.format(dtf2 );
    }

    public static void main(String[] args) {
        String s = Date1.getISO8601TimestampFromDateStr("2022-08-20 13:00:20");
        System.out.println(s);

//        String startTime = DateUtil.parse("2022-08-20 12:02:12").toString("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//        System.out.println(startTime);

        long beginTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(beginTime);

    }

}
