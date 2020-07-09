package com.allen.tuning.api;

import com.allen.tuning.common.annotation.Api;
import com.allen.tuning.common.exception.BizException;
import com.allen.tuning.common.exception.ParamException;
import com.allen.tuning.common.exception.SystemException;
import com.allen.tuning.entity.req.param.EndlessLoopReq;
import com.allen.tuning.entity.rsp.data.EndlessLoopRsp;

/**
 * @author rui.xiong
 * @date 2020-07-09 09:39
 */
@Api(apiName = "endless.loop.api")
public class EndlessLoopApi extends AbstractApi<EndlessLoopReq, EndlessLoopRsp> {

    @Override
    protected void paramVerify(EndlessLoopReq param) throws ParamException {
        if (param.getLoopTime() == null) {
            param.setLoopTime(-1);
        }
    }

    @Override
    protected EndlessLoopRsp procBiz(EndlessLoopReq param) throws BizException, SystemException {
        final String prefix = "loop" + Thread.currentThread().getId() + "_";
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
                if (i == param.getLoopTime()) {
                    break;
                } else {
                    try {
                        //模拟业务处理
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        EndlessLoopRsp rsp = new EndlessLoopRsp();
        rsp.setThreadId(thread.getId());
        rsp.setThreadName(thread.getName());
        return rsp;
    }
}
