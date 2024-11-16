package com.xiaoshi.framework.common.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.xiaoshi.framework.common.utils.date.DateUtils;
import com.xiaoshi.framework.common.utils.date.LocalDateUtils;
import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * JSON 工具类
 *
 * @author xiaoshi
 * @since 2024/11/15 上午12:49
 */
@Slf4j
public class JsonUtils {

    private static final ObjectMapper MAPPER = getObjectMapper();

    /**
     * 构建 Jackson 自定义配置
     *
     * @return Jackson2ObjectMapperBuilderCustomizer
     */
    public static Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder
                    // 序列化时，对象为 null，是否抛异常
                    .failOnEmptyBeans(false)
                    // 反序列化时，json 中包含 pojo 不存在属性时，是否抛异常
                    .failOnUnknownProperties(false)
                    // 禁止将 java.util.Date、Calendar 序列化为数字(时间戳)
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    // 设置 java.util.Date, Calendar 序列化、反序列化的格式
                    .dateFormat(new SimpleDateFormat(DateUtils.PATTERN_DATETIME));

            // Jackson 序列化 long类型为String，解决后端返回的Long类型在前端精度丢失的问题
            builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            builder.serializerByType(BigDecimal.class, ToStringSerializer.instance);

            // 配置 Jackson 反序列化 LocalDateTime、LocalDate、LocalTime 时使用的格式
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(LocalDateUtils.DATE_FORMATTER_DATETIME));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(LocalDateUtils.DATE_FORMATTER_DATE));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(LocalDateUtils.DATE_FORMATTER_TIME));

            // 配置 Jackson 序列化 LocalDateTime、LocalDate、LocalTime 时使用的格式
            builder.serializers(new LocalDateTimeSerializer(LocalDateUtils.DATE_FORMATTER_DATETIME));
            builder.serializers(new LocalDateSerializer(LocalDateUtils.DATE_FORMATTER_DATE));
            builder.serializers(new LocalTimeSerializer(LocalDateUtils.DATE_FORMATTER_TIME));
        };
    }

    /**
     * 根据 Jackson2ObjectMapperBuilderCustomizer 构建 ObjectMapper
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        jackson2ObjectMapperBuilderCustomizer().customize(builder);
        return builder.build();
    }

    /**
     * 将对象转换为JSON字符串
     * 如果对象为null，则返回null
     * 如果转换过程中发生错误，则记录错误日志并返回null
     *
     * @param object 要转换为JSON字符串的对象
     * @return JSON字符串或null
     */
    public static String toJsonString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json format error", e);
            return null;
        }
    }

    /**
     * 将对象转换为JSON格式的字节数组
     * 如果对象为null，则返回null
     * 如果转换过程中发生错误，则记录错误日志并返回null
     *
     * @param object 要转换为JSON格式字节数组的对象
     * @return JSON格式的字节数组或null
     */
    public static byte[] toJsonByte(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.error("json format error", e);
            return null;
        }
    }

    /**
     * 将对象转换为格式化的JSON字符串
     * 如果对象为null，则返回null
     * 如果转换过程中发生错误，则记录错误日志并返回null
     *
     * @param object 要转换为格式化JSON字符串的对象
     * @return 格式化的JSON字符串或null
     */
    public static String toJsonPrettyString(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json format error", e);
            return null;
        }
    }

    /**
     * 解析JSON字符串成指定类型的对象
     * 如果文本为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param text  JSON字符串
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型泛型
     * @return 指定类型的对象或null
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            log.error("json parse error, text: {}", text, e);
            return null;
        }
    }

    /**
     * 解析JSON字符串成指定类型的对象
     * 如果文本为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param text JSON字符串
     * @param type 目标对象类型
     * @param <T>  目标对象类型泛型
     * @return 指定类型的对象或null
     */
    public static <T> T parseObject(String text, Type type) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, MAPPER.getTypeFactory().constructType(type));
        } catch (IOException e) {
            log.error("json parse error, text: {}", text, e);
            return null;
        }
    }

    /**
     * 解析JSON格式字节数组成指定类型的对象
     * 如果字节数组为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param bytes JSON格式字节数组
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型泛型
     * @return 指定类型的对象或null
     */
    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (CheckUtils.isEmpty(bytes)) {
            return null;
        }
        try {
            return MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error("json parse error", e);
            return null;
        }
    }

    /**
     * 解析JSON字符串成指定类型的对象
     * 如果文本为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param text          JSON字符串
     * @param typeReference 类型引用
     * @param <T>           目标对象类型泛型
     * @return 指定类型的对象或null
     */
    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            log.error("json parse error, text: {}", text, e);
            return null;
        }
    }

    /**
     * 解析JSON字符串成指定类型的对象，如果解析失败，则返回null
     *
     * @param text          字符串
     * @param typeReference 类型引用
     * @return 指定类型的对象
     */
    public static <T> T parseObjectQuietly(String text, TypeReference<T> typeReference) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            log.error("json parse error, text: {}", text, e);
            return null;
        }
    }

    /**
     * 解析JSON字符串成指定类型的对象列表
     * 如果文本为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param text  JSON字符串
     * @param clazz 列表中对象的类型
     * @param <T>   列表中对象的类型泛型
     * @return 对象列表或null
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("json parse error, text: {}", text, e);
            return null;
        }
    }

    /**
     * 解析JSON字符串成JsonNode对象
     * 如果文本为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param text JSON字符串
     * @return JsonNode对象或null
     */
    public static JsonNode parseTree(String text) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readTree(text);
        } catch (IOException e) {
            log.error("json parse error, text: {}", text, e);
            return null;
        }
    }

    /**
     * 解析JSON格式字节数组成JsonNode对象
     * 如果字节数组为空，则返回null
     * 如果解析过程中发生错误，则记录错误日志并返回null
     *
     * @param text JSON格式字节数组
     * @return JsonNode对象或null
     */
    public static JsonNode parseTree(byte[] text) {
        if (CheckUtils.isEmpty(text)) {
            return null;
        }
        try {
            return MAPPER.readTree(text);
        } catch (IOException e) {
            log.error("json parse error", e);
            return null;
        }
    }

}
