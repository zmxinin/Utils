import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

/**
 * @author try
 * @version 1.0.0
 * @Description TODO
 * @createTime 2022年04月29日
 */
public class SnowflakeIdWorker {

    // ==============================Fields===========================================
    /** 开始时间截 (2022-04-29) */
    private final long twepoch = 1673080455L;//1651214721361L
    /** 机器id所占的位数 */
    private final long machineIdBits = 5L;

    /** 数据标识id所占的位数 */
    private final long dataCenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxmachineId = -1L ^ (-1L << machineIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /** 序列在id中占的位数 */
    private final long serialNumBits = 12L;

    /** 机器ID向左移12位 */
    private final long machineIdShift = serialNumBits;

    /** 数据标识id向左移17位(12+5) */
    private final long dataCenterIdShift = serialNumBits + machineIdBits;

    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = serialNumBits + machineIdBits + dataCenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long serialNumMask = -1L ^ (-1L << serialNumBits);

    /** 工作机器ID(0~31) */
    private long machineId;

    /** 数据中心ID(0~31) */
    private long dataCenterId;

    /** 毫秒内序列(0~4095) */
    private long serialNum = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    private static SnowflakeIdWorker idWorker;

//    static {
//        idWorker = new SnowflakeIdWorker(getWorkId(),getDataCenterId());
//    }

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param machineId 工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long machineId, long dataCenterId) {
        if (machineId > maxmachineId || machineId < 0) {
            throw new IllegalArgumentException(String.format("machineId can't be greater than %d or less than 0", maxmachineId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            serialNum = (serialNum + 1) & serialNumMask;
            //毫秒内序列溢出
            if (serialNum == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            serialNum = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (machineId << machineIdShift)
                | serialNum;
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return Instant.now().getEpochSecond();
    }

    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0,31);
        }
    }

    private static Long getDataCenterId() {
        int[] ints = StringUtils.toCodePoints(getHostName());
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }

    /**
     * 获取 hostName
     *   SystemUtils.getHostName() 在mac系统为空处理
     * @return
     */
    public static String getHostName() {
        //获取当前操作系统名称,例如:windows xp,linux 等;
        String osName = System.getProperty("os.name").toLowerCase();
        String hostName = null;
        if(osName.startsWith("mac")){
            try {
                hostName = InetAddress.getLocalHost().getHostName().toUpperCase();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                hostName = "N/A";
            }
        }else{
            hostName = SystemUtils.getHostName();
        }
        return hostName;
    }
    /**
     * 静态工具类
     *
     * @return
     */
    public static synchronized Long generateId(){
        long id = idWorker.nextId();
        return id;
    }


    /**
     * 生成Id
     * @return
     */
    public static Integer generateIdNum(){
        long id = SnowflakeIdWorker.generateId();//得到id 很长且高位很长部分是一样的数
        StringBuilder sb=new StringBuilder(id+"");
        StringBuilder reverse = sb.reverse();//将id翻转：我们发现id很长，且高位很长部分是一样的数
        id=new Long(reverse.toString())/1000;//切去部分长度
        while(id>1999999999){//1999999999以内的10位或9位或8位id;....
            id/=10;
        }
        Integer num = Integer.parseInt(id+"");
        return num;
    }

    public static void main(String[] args) {
//        // 订单ID生成测试，机器ID指定第0台
//        for(int i=0;i < 10; i++){
//
//            System.out.println(generateIdNum());
//        }

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
        for (int i = 0; i < 20; i++) {
            long id = idWorker.nextId();
            System.out.println(id+"-----"+Long.toString(id).length()+"-----"+Long.toBinaryString(id));
        }

    }


}

