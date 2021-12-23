package com.aplan.task;

import org.springframework.stereotype.Component;

@Component("warnTask")
public class WarnTask {

    /**
     * 未留样
     */
    public void unReserved() {
        System.out.println("unReserved");
    }

    /**
     * 未溯源
     */
    public void unSource() {
        System.out.println("unSource");
    }

    /**
     * 未检测
     */
    public void unCheck() {
        System.out.println("unCheck");
    }



}
