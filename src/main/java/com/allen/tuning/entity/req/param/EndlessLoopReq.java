package com.allen.tuning.entity.req.param;

import java.io.Serializable;

/**
 * @author rui.xiong
 * @date 2020-07-09 09:35
 */
public class EndlessLoopReq implements Serializable {

    private static final long serialVersionUID = -8490721581321643143L;
    /**
     * 循环次数
     */
    private Integer loopTime;

    public Integer getLoopTime() {
        return loopTime;
    }

    public void setLoopTime(Integer loopTime) {
        this.loopTime = loopTime;
    }
}
