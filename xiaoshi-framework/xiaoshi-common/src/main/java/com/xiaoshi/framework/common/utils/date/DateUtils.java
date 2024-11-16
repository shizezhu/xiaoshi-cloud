package com.xiaoshi.framework.common.utils.date;

import lombok.experimental.UtilityClass;
import org.dromara.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAmount;
import java.util.Date;

/**
 * 旧日期工具类
 *
 * @author xiaoshi
 * @since 2024/11/14 下午10:56
 */
@UtilityClass
public class DateUtils extends DateUtil {

    // 日期格式
    public String PATTERN_DATE = "yyyy-MM-dd";

    public String PATTERN_DATE_MINI = "yyyyMMdd";

    public String PATTERN_TIME = "HH:mm:ss";

    public String PATTERN_TIME_MINI = "HHmmss";

    public String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public String PATTERN_DATETIME_MINI = "yyyyMMddHHmmss";

    // 老日期格式化，使用ThreadLocal保证线程安全
    public ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT_DATE = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_DATE));

    public ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT_DATE_MINI = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_DATE_MINI));

    public ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT_TIME = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_TIME));

    public ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT_TIME_MINI = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_TIME_MINI));

    public ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT_DATETIME = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_DATETIME));

    public ThreadLocal<SimpleDateFormat> SIMPLE_FORMAT_DATETIME_MINI = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_DATETIME_MINI));

    /**
     * 添加年
     *
     * @param date 时间
     * @param num  年数
     * @return 设置后的时间
     */
    public Date plusYears(Date date, int num) {
        return plus(date, Period.ofYears(num));
    }

    /**
     * 添加月
     *
     * @param date 时间
     * @param num  月数
     * @return 设置后的时间
     */
    public Date plusMonths(Date date, int num) {
        return plus(date, Period.ofMonths(num));
    }

    /**
     * 添加周
     *
     * @param date 时间
     * @param num  周数
     * @return 设置后的时间
     */
    public Date plusWeeks(Date date, int num) {
        return plus(date, Period.ofWeeks(num));
    }

    /**
     * 添加天
     *
     * @param date 时间
     * @param num  天数
     * @return 设置后的时间
     */
    public Date plusDays(Date date, long num) {
        return plus(date, Duration.ofDays(num));
    }

    /**
     * 添加小时
     *
     * @param date 时间
     * @param num  小时数
     * @return 设置后的时间
     */
    public Date plusHours(Date date, long num) {
        return plus(date, Duration.ofHours(num));
    }

    /**
     * 添加分钟
     *
     * @param date 时间
     * @param num  分钟数
     * @return 设置后的时间
     */
    public Date plusMinutes(Date date, long num) {
        return plus(date, Duration.ofMinutes(num));
    }

    /**
     * 添加秒
     *
     * @param date 时间
     * @param num  秒数
     * @return 设置后的时间
     */
    public Date plusSeconds(Date date, long num) {
        return plus(date, Duration.ofSeconds(num));
    }

    /**
     * 添加毫秒
     *
     * @param date 时间
     * @param num  毫秒数
     * @return 设置后的时间
     */
    public Date plusMillis(Date date, long num) {
        return plus(date, Duration.ofMillis(num));
    }

    /**
     * 添加纳秒
     *
     * @param date 时间
     * @param num  纳秒数
     * @return 设置后的时间
     */
    public Date plusNanos(Date date, long num) {
        return plus(date, Duration.ofNanos(num));
    }

    /**
     * 日期添加时间量
     *
     * @param date   时间
     * @param amount 时间量
     * @return 设置后的时间
     */
    public Date plus(Date date, TemporalAmount amount) {
        Instant instant = date.toInstant();
        return Date.from(instant.plus(amount));
    }

    /**
     * 减少年
     *
     * @param date 时间
     * @param num  年数
     * @return 设置后的时间
     */
    public Date minusYears(Date date, int num) {
        return minus(date, Period.ofYears(num));
    }

    /**
     * 减少月
     *
     * @param date 时间
     * @param num  月数
     * @return 设置后的时间
     */
    public Date minusMonths(Date date, int num) {
        return minus(date, Period.ofMonths(num));
    }

    /**
     * 减少周
     *
     * @param date 时间
     * @param num  周数
     * @return 设置后的时间
     */
    public Date minusWeeks(Date date, int num) {
        return minus(date, Period.ofWeeks(num));
    }

    /**
     * 减少天
     *
     * @param date 时间
     * @param num  天数
     * @return 设置后的时间
     */
    public Date minusDays(Date date, long num) {
        return minus(date, Duration.ofDays(num));
    }

    /**
     * 减少小时
     *
     * @param date 时间
     * @param num  小时数
     * @return 设置后的时间
     */
    public Date minusHours(Date date, long num) {
        return minus(date, Duration.ofHours(num));
    }

    /**
     * 减少分钟
     *
     * @param date 时间
     * @param num  分钟数
     * @return 设置后的时间
     */
    public Date minusMinutes(Date date, long num) {
        return minus(date, Duration.ofMinutes(num));
    }

    /**
     * 减少秒
     *
     * @param date 时间
     * @param num  秒数
     * @return 设置后的时间
     */
    public Date minusSeconds(Date date, long num) {
        return minus(date, Duration.ofSeconds(num));
    }

    /**
     * 减少毫秒
     *
     * @param date 时间
     * @param num  毫秒数
     * @return 设置后的时间
     */
    public Date minusMillis(Date date, long num) {
        return minus(date, Duration.ofMillis(num));
    }

    /**
     * 减少纳秒
     *
     * @param date 时间
     * @param num  纳秒数
     * @return 设置后的时间
     */
    public Date minusNanos(Date date, long num) {
        return minus(date, Duration.ofNanos(num));
    }

    /**
     * 日期减少时间量
     *
     * @param date   时间
     * @param amount 时间量
     * @return 设置后的时间
     */
    public Date minus(Date date, TemporalAmount amount) {
        Instant instant = date.toInstant();
        return Date.from(instant.minus(amount));
    }

    /**
     * 日期时间格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public String formatDateTime(Date date) {
        return SIMPLE_FORMAT_DATETIME.get().format(date);
    }

    /**
     * 日期时间格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public String formatDateTimeMini(Date date) {
        return SIMPLE_FORMAT_DATETIME_MINI.get().format(date);
    }

    /**
     * 日期格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public String formatDate(Date date) {
        return SIMPLE_FORMAT_DATE.get().format(date);
    }

    /**
     * 日期格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public String formatDateMini(Date date) {
        return SIMPLE_FORMAT_DATE_MINI.get().format(date);
    }

    /**
     * 时间格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public String formatTime(Date date) {
        return SIMPLE_FORMAT_TIME.get().format(date);
    }

    /**
     * 时间格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    public String formatTimeMini(Date date) {
        return SIMPLE_FORMAT_TIME_MINI.get().format(date);
    }

    /**
     * 日期格式化
     *
     * @param date    时间
     * @param pattern 表达式
     * @return 格式化后的时间
     */
    public String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public Date parse(String dateStr, String pattern) {
        return parse(dateStr, new SimpleDateFormat(pattern));
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param format  格式
     * @return 时间
     */
    public Date parse(String dateStr, SimpleDateFormat format) {
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 转换成 date
     *
     * @param dateTime LocalDateTime
     * @return Date
     */
    public Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 转换成 date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 比较2个时间差
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 时间间隔
     */
    public Duration between(Date startDate, Date endDate) {
        return Duration.between(startDate.toInstant(), endDate.toInstant());
    }
}
