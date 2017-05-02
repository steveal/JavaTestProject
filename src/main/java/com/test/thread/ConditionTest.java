package com.test.thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
public class ConditionTest {
    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
 
        // 创建并发访问的账户
        Account myAccount = test.new Account("95599200901215522", 10000);
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread t1 = test.new DrawThread("张三", myAccount, 11000);
        Thread t2 = test.new SaveThread("李四", myAccount, 3600);
        Thread t3 = test.new DrawThread("王五", myAccount, 2700);
        Thread t4 = test.new SaveThread("老张", myAccount, 600);
        Thread t5 = test.new DrawThread("老牛", myAccount, 1300);
        Thread t6 = test.new SaveThread("胖子", myAccount, 2000);
 
        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        // 关闭线程池
        pool.shutdown();
    }
 
    /**
     * 存款线程类
     */
    public class SaveThread extends Thread {
        private String name; // 操作人
        private Account account; // 账户
        private int x; // 存款金额
 
        SaveThread(String name, Account account, int x) {
            this.name = name;
            this.account = account;
            this.x = x;
        }
 
        public void run() {
            account.saving(x, name);
        }
    }
 
    /**
     * 取款线程类
     */
    public class DrawThread extends Thread {
        private String name; // 操作人
        private Account account; // 账户
        private int x; // 存款金额
 
        DrawThread(String name, Account account, int x) {
            this.name = name;
            this.account = account;
            this.x = x;
        }
 
        public void run() {
            account.drawing(x, name);
        }
    }
 
    /**
     * 银行账户
     */
    public class Account {
        private String id; // 账号
        private int cash; // 账户余额
        private Lock lock = new ReentrantLock(); // 账户锁
        private Condition _save = lock.newCondition(); // 存款条件
        private Condition _draw = lock.newCondition(); // 取款条件
 
        Account(String id, int cash) {
            this.id = id;
            this.cash = cash;
        }
 
        /**
         * 存款
         *
         * @param x 操作金额
         * @param name 操作人
         */
        public void saving(int x, String name) {
            lock.lock(); // 获取锁
            if (x > 0) {
                cash += x; // 存款
                System.out.println(name + "存款" + x + "，当前余额为" + cash);
            }
            _draw.signalAll(); // 唤醒所有等待的取款线程。
            lock.unlock(); // 释放锁
        }
 
        /**
         * 取款
         *
         * @param x
         *            操作金额
         * @param name
         *            操作人
         */
        public void drawing(int x, String name) {
            lock.lock(); // 获取锁
            try {
                if (cash - x < 0) {
                    System.out.println(name + "取款失败[余额不足]，取款" + x + "，当前余额为" + cash);
                    _draw.await(); // 阻塞取款操作
                } else {
                    cash -= x; // 取款
                    System.out.println(name + "取款" + x + "，当前余额为" + cash);
                }
                _save.signalAll(); // 唤醒所有等待的存款操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // 释放锁
            }
        }
    }
     
}