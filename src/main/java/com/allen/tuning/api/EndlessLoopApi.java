package com.allen.tuning.api;

import com.allen.tuning.common.annotation.Api;
import com.allen.tuning.common.exception.BizException;
import com.allen.tuning.common.exception.ParamException;
import com.allen.tuning.common.exception.SystemException;
import com.allen.tuning.entity.req.param.EndlessLoopReq;
import com.allen.tuning.entity.rsp.data.EndlessLoopRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author rui.xiong
 * @date 2020-07-09 09:39
 */
@Api(apiName = "endless.loop.api")
public class EndlessLoopApi extends AbstractApi<EndlessLoopReq, EndlessLoopRsp> {
    @Autowired
    @Qualifier("infiniteLoopThreadPool")
    private ThreadPoolExecutor executor;


    @Override
    protected void paramVerify(EndlessLoopReq param) throws ParamException {
        if (param.getLoopTime() == null) {
            param.setLoopTime(-1);
        }
    }

    @Override
    protected EndlessLoopRsp procBiz(EndlessLoopReq param) throws BizException, SystemException {
        executor.execute(() -> {
            int i = 0;
            for (;;) {
                i++;
                if (i == param.getLoopTime()) {
                    break;
                }
                try {
                    URL baidu=new URL("https://www.baidu.com");
                    baidu.getContent();
                    URLConnection connection=baidu.openConnection();
                    connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        EndlessLoopRsp rsp = new EndlessLoopRsp();
        return rsp;
    }

    @Override
    protected Class<EndlessLoopReq> getReqClass() {
        return EndlessLoopReq.class;
    }

}
