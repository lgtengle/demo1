package com.zfkr.spring.scheduled;

import com.zfkr.spring.common.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-15 10:05
 */
@Component
public class ScheduledTask {
    int n = 0;
    
    @Scheduled(fixedDelay = 1000)
    public void testScheduled1(){
        System.out.println(Thread.currentThread().getName()+"----我执行了。。。1---" + DateUtils.now());
        if (n >= 0){

            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            n++;
        }
    }

    @Scheduled(fixedDelay = 3000)
    public void testScheduled2(){
        System.err.println(Thread.currentThread().getName()+"----我执行了。。。2---" + DateUtils.now());
        /*try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
