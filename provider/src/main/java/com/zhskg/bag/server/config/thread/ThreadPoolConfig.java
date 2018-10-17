package com.zhskg.bag.server.config.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jean
 * @date 2018/10/7
 * dese:线程池
 */
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {


    @Bean(name = "threadPool")
    public ThreadPoolExecutor getPool(){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1000,2000,
                1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                // new ArrayBlockingQueue(1000,Boolean.TRUE),
                new ThreadFactoryBuilder()
                        .setDaemon(true)
                        .setNameFormat("detail_thread-%d")
                        .build(),
                new ThreadPoolExecutor.DiscardPolicy());
        return pool;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(300);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("async-taskExecutor");

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

}
