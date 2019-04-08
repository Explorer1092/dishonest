package com.wtmcb.dishonest.spider.job;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by WangGang on 2019-04-08.
 * Email: wanggang1@vipkid.com.cn
 */
public class SpiderExecutor {

    private static final Executor SPIDER_EXECUTOR;
    private static final int CORE_SIZE = 4;
    private static final int MAX_SIZE = 4;
    private static final BlockingQueue WORK_QUEUE = new LinkedBlockingQueue();
    private static final String NAME_PREFIX = "WTMCB";
    private static final ThreadFactory THREAD_FACTORY;

    static {
        THREAD_FACTORY = new ThreadFactory() {
            private final AtomicInteger poolNumber = new AtomicInteger(1);
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            SecurityManager s = System.getSecurityManager();
            ThreadGroup group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            String threadNamePrefix = NAME_PREFIX + poolNumber.getAndIncrement() + "-thread-";

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(group, r,
                        threadNamePrefix + threadNumber.getAndIncrement(),
                        0);
                if (t.isDaemon()) {
                    t.setDaemon(false);
                }
                if (t.getPriority() != Thread.NORM_PRIORITY) {
                    t.setPriority(Thread.NORM_PRIORITY);
                }
                return t;
            }
        };
        SPIDER_EXECUTOR = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE,
                20, TimeUnit.SECONDS, WORK_QUEUE, THREAD_FACTORY,
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static void pushTask(Runnable runnable){
        SPIDER_EXECUTOR.execute(runnable);
    }

}
