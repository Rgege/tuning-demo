package com.allen.tuning.api;

import com.allen.tuning.common.annotation.Api;
import com.allen.tuning.common.exception.BizException;
import com.allen.tuning.common.exception.ParamException;
import com.allen.tuning.common.exception.SystemException;
import com.allen.tuning.entity.req.param.DeadLockReq;
import com.allen.tuning.entity.rsp.data.DeadLockRsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rui.xiong
 * @date 2020-07-09 15:07
 */
@Api(apiName = "dead.lock.api")
public class DeadLockApi extends AbstractApi<DeadLockReq, DeadLockRsp> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeadLockApi.class);
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    @Override
    protected void paramVerify(DeadLockReq param) throws ParamException {

    }

    @Override
    protected DeadLockRsp procBiz(DeadLockReq param) throws BizException, SystemException {
        Thread threadA = new Thread(() -> {
            String name = Thread.currentThread().getName();
            synchronized (lock1) {
                LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + name + "success get lock1");
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + name + "try to get lock2");
                synchronized (lock2) {
                    LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + name + "success get lock2");
                }
            }
        }, "DeadLock-A");
        Thread threadB = new Thread(() -> {
            String name = Thread.currentThread().getName();
            synchronized (lock2) {
                LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + name + "success get lock2");
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + name + "try to get lock1");
                synchronized (lock1) {
                    LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + name + "success get lock1");
                }
            }
        }, "DeadLock-B");
        threadA.start();
        threadB.start();

        return new DeadLockRsp();
    }

    @Override
    protected Class<DeadLockReq> getReqClass() {
        return DeadLockReq.class;
    }
}
