package com.allen.tuning.api.factory;

import com.allen.tuning.TuningDemoApplication;
import com.allen.tuning.api.AbstractApi;
import com.allen.tuning.common.annotation.Api;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author rui.xiong
 * @date 2020-07-08 14:54
 */
@Component
public class ApiFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private static ConcurrentHashMap<String, AbstractApi> apiMap = new ConcurrentHashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, AbstractApi> map = applicationContext.getBeansOfType(AbstractApi.class);

        if (null == map) {
            return;
        }

        Iterator<Map.Entry<String, AbstractApi>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, AbstractApi> apiEntry = entryIterator.next();
            AbstractApi api = apiEntry.getValue();
            Api apiAnno = null;
            if ((apiAnno = api.getClass().getAnnotation(Api.class)) != null) {
                api.setParamNotNull(apiAnno.paramNotNull());
                apiMap.put(apiAnno.apiName(), api);
            }
        }

    }

    /**
     * 根据apiName获取对应Api
     * @param apiName
     * @return
     */
    public static AbstractApi getApiByName(String apiName) {
        return apiMap.get(apiName);
    }


}
