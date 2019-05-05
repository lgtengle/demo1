package com.lg.springweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-28 11:23
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/longRequest")
    public String longRequest(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "1";
    }
}
