/**
 * 功能描述
 *
 * @author zmx
 * @date 2023/1/7
 */

public class MyThread implements Runnable{
    @Override
    public void run() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1,1);
        for (int i = 0; i < 20; i++) {
            long id = idWorker.nextId();
            System.out.println(Thread.currentThread().getName()+id+"-----"+Long.toString(id).length()+"-----"+Long.toBinaryString(id));
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread,"a---");
        Thread thread2 = new Thread(myThread,"b---");
        thread1.start();
        thread2.start();
    }
}
