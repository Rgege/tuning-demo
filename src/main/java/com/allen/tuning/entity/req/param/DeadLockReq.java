package com.allen.tuning.entity.req.param;

import java.io.Serializable;

/**
 * @author rui.xiong
 * @date 2020-07-09 15:09
 */
public class DeadLockReq implements Serializable {
    private static final long serialVersionUID = 1472551521176781423L;

    private Integer opt;

    public Integer getOpt() {
        return opt;
    }

    public void setOpt(Integer opt) {
        this.opt = opt;
    }
}
