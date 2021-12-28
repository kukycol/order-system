package com.aplan.grab.baidu.image;

public class BaiduImagesDemo {


    public static void main(String[] args) throws Exception {
        int threadNumber = 1;
        for (int i = 1; i <= threadNumber; i++) {
            int startPage = threadNumber > 1 ? (50 * (i - 1)) + 1 : 1;
            int endPage = i * 50;
            RunnableDemo r1 = new RunnableDemo(String.valueOf(i),startPage ,endPage) ;
            r1.start();
        }

    }





}
