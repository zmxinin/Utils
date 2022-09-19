package Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 传入String类型日期(ISO8601标准时间:yyyy-MM-dd'T'HH:mm:ss.SSS'Z')，返回字符串类型时间(yyyy-MM-dd HH:mm:ss)
 *
 * [根据传入的string类型的时间,按照对应的格式转换为 dtf1,然后转换为对应格式的时间类型，之后就可以转换为其他时间类型]
 */

public class StringParseDate {

    public static DateTime ISOdateStrParseDate(String ISOdateStr){
        DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//        DateTimeFormatter dtf1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

        DateTime dateTime= dtf1.parseDateTime(ISOdateStr);
        System.out.println("dateTime类型：" + dateTime);

        LocalDateTime localDateTime = dtf1.parseLocalDateTime(ISOdateStr);
        System.out.println("localDateTime类型：" + localDateTime);

        DateTime dateTime1 = dateTime.toDateTime();
        System.out.println("dateTime类型：" + dateTime1);

        LocalDateTime localDateTime1 = dateTime.toLocalDateTime();
        System.out.println("localDateTime类型：" + localDateTime1);

        Date date = dateTime.toDate();
        System.out.println("date类型：" + date);

        /**
         * 时间类型 转 string类型的时间
         */
        DateTimeFormatter dtf2= DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.toString(dtf2));
        System.out.println(localDateTime.toString(dtf2));

        return dateTime;
    }

    public static void main(String[] args) {
        DateTime dateTime = StringParseDate.ISOdateStrParseDate("2022-08-10T12:00:00.000+08:00");
    }
}
