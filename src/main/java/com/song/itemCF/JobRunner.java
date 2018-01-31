package com.song.itemCF;

public class JobRunner {

    public static void main(String[] args) {
        int status1 = -1;
        int status2 = -1;
        int status3 = -1;
        int status4 = -1;
        int status5 = -1;

        status1 = new MR1().run();

        if (status1 == 1) {
            System.out.println("step1运行成功，开始运行step2。。。");
            status2 = new MR2().run();
        } else {
            System.out.println("step1运行失败！");
        }

        if (status2 == 1) {
            System.out.println("step2运行成功，开始运行step3。。。");
            status3 = new MR3().run();
        } else {
            System.out.println("step2运行失败！");
        }

        if (status3 == 1) {
            System.out.println("step3运行成功，开始运行step4。。。");
            status4 = new MR4().run();
        } else {
            System.out.println("step3运行失败！");
        }

        if (status4 == 1) {
            System.out.println("step4运行成功，开始运行step5。。。");
            status5 = new MR5().run();
        } else {
            System.out.println("step4运行失败！");
        }

        if (status5 == 1) {
            System.out.println("step5运行成功，程序结束");
        } else {
            System.out.println("step5运行失败！");
        }


    }
}
