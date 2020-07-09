package com.allen.tuning.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rui.xiong
 * @date 2020-07-09 13:12
 */
@Configuration
public class ThreadPoolConfig {
    private static final long ONE_H = 1000L * 60 * 60;
    private static final long ONE_M = 1000L * 60;

    @Bean("infiniteLoopThreadPool")
    public ThreadPoolExecutor infiniteLoopThreadPool() {
        ThreadPoolExecutor infiniteLoopThreadPool = new ThreadPoolExecutor(
                2,
                2,
                ONE_H,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(2),
                new CustomNameThreadFactory("InfiniteLoop")
        );
        return infiniteLoopThreadPool;
    }


    static class CustomNameThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomNameThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name + "_" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
