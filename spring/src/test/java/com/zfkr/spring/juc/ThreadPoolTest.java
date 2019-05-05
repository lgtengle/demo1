package com.zfkr.spring.juc;

import com.zfkr.spring.common.DateUtils;
import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-15 11:55
 */
public class ThreadPoolTest {

    public static void main(String[] args){
        System.out.println(DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT));
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        threadPoolExecutor.scheduleWithFixedDelay(() -> System.err.println("测试延时任务执行1" + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT))
                ,2, 5, TimeUnit.SECONDS);

        threadPoolExecutor.scheduleAtFixedRate(() -> System.out.println("测试周期任务推迟执行1" + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT))
                ,2, 5, TimeUnit.SECONDS);
    }

    @Test
    public void testThread(){
        Thread thread = new Thread(() -> {
            System.out.println("哈哈哈哈。。。。");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("哦哦哦哦哦哦。。。。");
        });
//        thread.setDaemon(true);
        thread.start();

        System.out.println("嗯嗯嗯嗯呃呃呃");
    }

    @Test
    public void testScheduledThreadPoolExecutor(){
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        threadPoolExecutor.scheduleWithFixedDelay(() -> System.out.println("测试任务推迟执行1")
        ,1, 5, TimeUnit.SECONDS);



        /*threadPoolExecutor.scheduleAtFixedRate(() -> {
            System.out.println("测试定时执行任务");
        }, 0, 10, TimeUnit.SECONDS);*/
        //System.out.println(System.nanoTime());
    }

    @Test
    public void testSiftUp(){
        /*for (int i = 0; i < 10; i++) {
            Object e = new Object();
            if (i == 0) {
                queue[0] = e;
                setIndex(e, 0);
            } else {
                siftUp(i, e);
            }
        }*/
    }

    /*private Object[] queue =
            new Object[16];

    private void siftUp(int k, Object key) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo(e) >= 0)
                break;
            queue[k] = e;
            setIndex(e, k);
            k = parent;
        }
        queue[k] = key;
        setIndex(key, k);
    }

    private void setIndex(Object f, int idx) {
    }*/

    @Test
    public void testTimer(){
        Timer timer = new Timer();
    }
}
