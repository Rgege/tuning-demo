package com.allen.tuning.common.util;


import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.allen.tuning.common.exception.JsonException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * A JSON util that depend on JackSon
 *
 * @author rui.xiong
 * @date 2020-07-08 16:19
 */
public class JsonUtils {

    /**
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
     * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
     * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
     * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
     * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
     * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
     */
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 解决实体未包含字段反序列化时抛出异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 允许属性名称没有引号
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 允许单引号
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * 将一个object转换为json, 可以使一个java对象，也可以使集合
     *
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
     */

    /**
     * json to Java实体
     *
     * @param json
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T toJavaObj(String json, Class<T> beanType) throws JsonException {
        T t = null;
        try {
            t = mapper.readValue(json, beanType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
        return t;
    }

    public static <T> T toJavaObj(InputStream inputStream, Class<T> beanType) throws JsonException {
        T t = null;
        try {
            t = mapper.readValue(inputStream, beanType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
        return t;
    }
    /**
     * json to map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) throws JsonException {
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
        return map;
    }

    /**
     * json to list
     *
     * @param json
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> beanType) throws JsonException {
        List<T> list = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
            list = mapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
        return list;
    }

    /**
     * 获取对应属性
     *
     * @param jsonString json字符串
     * @param key        属性名称
     * @return
     */
    public static String getValue(String jsonString, String key) throws JsonException {
        String result = null;
        try {
            JsonNode node = mapper.readTree(jsonString);
            JsonNode resProNode = node.get(key);
            result = JsonUtils.toJsonString(resProNode);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
        return result;
    }
}
