package com.allen.tuning.entity.rsp.data;

import com.allen.tuning.entity.rsp.AbstractData;

/**
 * @author rui.xiong
 * @date 2020-07-09 15:10
 */
public class DeadLockRsp extends AbstractData {
    private static final long serialVersionUID = 1843308450865411045L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
