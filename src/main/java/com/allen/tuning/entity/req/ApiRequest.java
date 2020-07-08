package com.allen.tuning.entity.req;


/**
 * @author rui.xiong
 * @date 2020-07-07 17:42
 */
public class ApiRequest {

    private String apiName;

    private Object param;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "apiName='" + apiName + '\'' +
                ", param=" + param.toString() +
                '}';
    }
}
