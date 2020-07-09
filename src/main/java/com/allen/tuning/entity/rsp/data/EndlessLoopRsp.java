package com.allen.tuning.entity.rsp.data;

import com.allen.tuning.entity.rsp.AbstractData;

/**
 * @author rui.xiong
 * @date 2020-07-09 09:40
 */
public class EndlessLoopRsp extends AbstractData {
    private static final long serialVersionUID = -4800672212931719889L;
    /**
     * 线程ID
     */
    private Long threadId;
    /**
     * 线程名称
     */
    private String threadName;

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
