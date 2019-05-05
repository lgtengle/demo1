package com.lg.springweb.controller;

import com.lg.springweb.common.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-17 16:20
 */
@RestController
@RequestMapping("/threadpool")
public class TestThreadPoolController {

    ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(3);
    int n = 0;

    @PostConstruct
    public void init(){
        /*threadPoolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("测试任务推迟执行1 " + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT));
                    if (n <= 0){
                        try {
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        n++;
                    }
                }
                ,1, 3, TimeUnit.SECONDS);*/
        threadPoolExecutor.scheduleWithFixedDelay(() -> {
                    System.err.println(Thread.currentThread().getName() + "---测试延时任务执行1 " + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT));
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ,1, 5, TimeUnit.SECONDS);

        threadPoolExecutor.scheduleWithFixedDelay(() -> {
                    System.err.println(Thread.currentThread().getName() + "---测试延时任务执行3 " + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT));
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ,1, 5, TimeUnit.SECONDS);

        /*threadPoolExecutor.scheduleAtFixedRate(() -> {
                    System.out.println("测试周期任务执行1 " + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT));
                }
                ,1, 15, TimeUnit.SECONDS);*/
    }

    @GetMapping("/addTask")
    public String addTask(){
        threadPoolExecutor.scheduleWithFixedDelay(() -> System.out.println(Thread.currentThread().getName() + "---测试任务推迟执行--------------" + DateUtils.getCurrentDateTime(DateUtils.DATETIME_FORMAT))
                ,1, 3, TimeUnit.SECONDS);
        return "success";
    }

//    @Scheduled(cron = "0 */1 * * * ?")
//    public void testScheduled(){
//        System.out.println("定时任务开始执行了。。。。");
//    }

    @GetMapping("/shutdown")
    public String testThreadPoolShutdown(){
        threadPoolExecutor.shutdown();
        System.out.println("关闭成功");
        return "success";
    }
}
