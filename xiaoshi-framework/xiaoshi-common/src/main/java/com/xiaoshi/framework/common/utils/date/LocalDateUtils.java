package com.xiaoshi.framework.common.utils.date;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 新日期工具类
 *
 * @author xiaoshi
 * @since 2024/11/14 下午10:56
 */
@UtilityClass
public class LocalDateUtils {

    // 日期格式
    public String PATTERN_DATE = "yyyy-MM-dd";

    public String PATTERN_DATE_MINI = "yyyyMMdd";

    public String PATTERN_TIME = "HH:mm:ss";

    public String PATTERN_TIME_MINI = "HHmmss";

    public String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public String PATTERN_DATETIME_MINI = "yyyyMMddHHmmss";

    // 新日期格式化
    public DateTimeFormatter DATE_FORMATTER_DATE = DateTimeFormatter.ofPattern(PATTERN_DATE);

    public DateTimeFormatter DATE_FORMATTER_DATE_MINI = DateTimeFormatter.ofPattern(PATTERN_DATE_MINI);

    public DateTimeFormatter DATE_FORMATTER_TIME = DateTimeFormatter.ofPattern(PATTERN_TIME);

    public DateTimeFormatter DATE_FORMATTER_TIME_MINI = DateTimeFormatter.ofPattern(PATTERN_TIME_MINI);

    public DateTimeFormatter DATE_FORMATTER_DATETIME = DateTimeFormatter.ofPattern(PATTERN_DATETIME);

    public DateTimeFormatter DATE_FORMATTER_DATETIME_MINI = DateTimeFormatter.ofPattern(PATTERN_DATETIME_MINI);

    public LocalDate nowDate() {
        return LocalDate.now();
    }

    public LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 添加年
     *
     * @param date 日期
     * @param num  年数
     * @return 设置后的日期
     */
    public static LocalDate plusYears(LocalDate date, int num) {
        return date.plusYears(num);
    }

    /**
     * 添加月
     *
     * @param date 日期
     * @param num  月数
     * @return 设置后的日期
     */
    public static LocalDate plusMonths(LocalDate date, int num) {
        return date.plusMonths(num);
    }

    /**
     * 添加周
     *
     * @param date 日期
     * @param num  周数
     * @return 设置后的日期
     */
    public static LocalDate plusWeeks(LocalDate date, int num) {
        return date.plusWeeks(num);
    }

    /**
     * 添加天
     *
     * @param date 日期
     * @param num  天数
     * @return 设置后的日期
     */
    public static LocalDate plusDays(LocalDate date, long num) {
        return date.plusDays(num);
    }

    /**
     * 增加日期
     *
     * @param date     日期
     * @param duration 时间段
     * @return 设置后的日期
     */
    public LocalDate plusDate(LocalDate date, Duration duration) {
        return date.plus(duration);
    }

    /**
     * 添加年
     *
     * @param dateTime 时间
     * @param num      年数
     * @return 设置后的时间
     */
    public static LocalDateTime plusYears(LocalDateTime dateTime, int num) {
        return dateTime.plusYears(num);
    }

    /**
     * 添加月
     *
     * @param dateTime 时间
     * @param num      月数
     * @return 设置后的时间
     */
    public static LocalDateTime plusMonths(LocalDateTime dateTime, int num) {
        return dateTime.plusMonths(num);
    }

    /**
     * 添加周
     *
     * @param dateTime 时间
     * @param num      周数
     * @return 设置后的时间
     */
    public static LocalDateTime plusWeeks(LocalDateTime dateTime, int num) {
        return dateTime.plusWeeks(num);
    }

    /**
     * 添加天
     *
     * @param dateTime 时间
     * @param num      天数
     * @return 设置后的时间
     */
    public static LocalDateTime plusDays(LocalDateTime dateTime, long num) {
        return dateTime.plusDays(num);
    }

    /**
     * 添加小时
     *
     * @param dateTime 时间
     * @param num      小时数
     * @return 设置后的时间
     */
    public static LocalDateTime plusHours(LocalDateTime dateTime, long num) {
        return dateTime.plusHours(num);
    }

    /**
     * 添加分钟
     *
     * @param dateTime 时间
     * @param num      分钟数
     * @return 设置后的时间
     */
    public static LocalDateTime plusMinutes(LocalDateTime dateTime, long num) {
        return dateTime.plusMinutes(num);
    }

    /**
     * 添加秒
     *
     * @param dateTime 时间
     * @param num      秒数
     * @return 设置后的时间
     */
    public static LocalDateTime plusSeconds(LocalDateTime dateTime, long num) {
        return dateTime.plusSeconds(num);
    }

    /**
     * 添加毫秒
     *
     * @param dateTime 时间
     * @param num      毫秒数
     * @return 设置后的时间
     */
    public static LocalDateTime plusMillis(LocalDateTime dateTime, long num) {
        return dateTime.plusNanos(num * 1_000_000);
    }

    /**
     * 添加纳秒
     *
     * @param dateTime 时间
     * @param num      纳秒数
     * @return 设置后的时间
     */
    public static LocalDateTime plusNanos(LocalDateTime dateTime, long num) {
        return dateTime.plusNanos(num);
    }

    /**
     * 增加时间
     *
     * @param dateTime 时间
     * @param duration 时间段
     * @return 设置后的时间
     */
    public LocalDateTime plusDateTime(LocalDateTime dateTime, Duration duration) {
        return dateTime.plus(duration);
    }

    /**
     * 减年
     *
     * @param date 日期
     * @param num  年数
     * @return 设置后的时间
     */
    public static LocalDate minusYears(LocalDate date, int num) {
        return date.minusYears(num);
    }

    /**
     * 减月
     *
     * @param date 日期
     * @param num  月数
     * @return 设置后的时间
     */
    public static LocalDate minusMonths(LocalDate date, int num) {
        return date.minusMonths(num);
    }

    /**
     * 减周
     *
     * @param date 日期
     * @param num  周数
     * @return 设置后的时间
     */
    public static LocalDate minusWeeks(LocalDate date, int num) {
        return date.minusWeeks(num);
    }

    /**
     * 减天
     *
     * @param date 日期
     * @param num  天数
     * @return 设置后的时间
     */
    public static LocalDate minusDays(LocalDate date, long num) {
        return date.minusDays(num);
    }

    /**
     * 减年
     *
     * @param dateTime 时间
     * @param num      年数
     * @return 设置后的时间
     */
    public static LocalDateTime minusYears(LocalDateTime dateTime, int num) {
        return dateTime.minusYears(num);
    }

    /**
     * 减月
     *
     * @param dateTime 时间
     * @param num      月数
     * @return 设置后的时间
     */
    public static LocalDateTime minusMonths(LocalDateTime dateTime, int num) {
        return dateTime.minusMonths(num);
    }

    /**
     * 减周
     *
     * @param dateTime 时间
     * @param num      周数
     * @return 设置后的时间
     */
    public static LocalDateTime minusWeeks(LocalDateTime dateTime, int num) {
        return dateTime.minusWeeks(num);
    }

    /**
     * 减天
     *
     * @param dateTime 时间
     * @param num      天数
     * @return 设置后的时间
     */
    public static LocalDateTime minusDays(LocalDateTime dateTime, long num) {
        return dateTime.minusDays(num);
    }

    /**
     * 减小时
     *
     * @param dateTime 时间
     * @param num      小时数
     * @return 设置后的时间
     */
    public static LocalDateTime minusHours(LocalDateTime dateTime, long num) {
        return dateTime.minusHours(num);
    }

    /**
     * 减分钟
     *
     * @param dateTime 时间
     * @param num      分钟数
     * @return 设置后的时间
     */
    public static LocalDateTime minusMinutes(LocalDateTime dateTime, long num) {
        return dateTime.minusMinutes(num);
    }

    /**
     * 减秒
     *
     * @param dateTime 时间
     * @param num      秒数
     * @return 设置后的时间
     */
    public static LocalDateTime minusSeconds(LocalDateTime dateTime, long num) {
        return dateTime.minusSeconds(num);
    }

    /**
     * 减毫秒
     *
     * @param dateTime 时间
     * @param num      毫秒数
     * @return 设置后的时间
     */
    public static LocalDateTime minusMillis(LocalDateTime dateTime, long num) {
        return dateTime.minusNanos(num * 1_000_000);
    }

    /**
     * 减纳秒
     *
     * @param dateTime 时间
     * @param num      纳秒数
     * @return 设置后的时间
     */
    public static LocalDateTime minusNanos(LocalDateTime dateTime, long num) {
        return dateTime.minusNanos(num);
    }

    /**
     * 减日期
     *
     * @param date     日期
     * @param duration 时间段
     * @return 设置后的时间
     */
    public LocalDate minusDate(LocalDate date, Duration duration) {
        return date.minus(duration);
    }

    /**
     * 减时间
     *
     * @param dateTime 时间
     * @param duration 时间段
     * @return 设置后的时间
     */
    public LocalDateTime minusDateTime(LocalDateTime dateTime, Duration duration) {
        return dateTime.minus(duration);
    }

    /**
     * 判断给定的日期是否在当前日期之前
     *
     * @param date 要判断的日期
     * @return 如果给定日期在当前日期之前，则返回true；否则返回false
     */
    public boolean beforeNow(LocalDate date) {
        return date.isBefore(nowDate());
    }

    /**
     * 判断给定的日期是否在当前日期之后
     *
     * @param date 要判断的日期
     * @return 如果给定日期在当前日期之后，则返回true；否则返回false
     */
    public boolean afterNow(LocalDate date) {
        return date.isAfter(nowDate());
    }

    /**
     * 判断给定的日期时间是否在当前日期时间之前
     *
     * @param dateTime 要判断的日期时间
     * @return 如果给定日期时间在当前日期时间之前，则返回true；否则返回false
     */
    public boolean beforeNow(LocalDateTime dateTime) {
        return dateTime.isBefore(nowDateTime());
    }

    /**
     * 判断给定的日期时间是否在当前日期时间之后
     *
     * @param dateTime 要判断的日期时间
     * @return 如果给定日期时间在当前日期时间之后，则返回true；否则返回false
     */
    public boolean afterNow(LocalDateTime dateTime) {
        return dateTime.isAfter(nowDateTime());
    }

    /**
     * 构建指定年月日的日期对象
     *
     * @param year  年份
     * @param mouth 月份
     * @param day   日期
     * @return 构建的LocalDate对象
     */
    public LocalDate buildDate(int year, int mouth, int day) {
        return LocalDate.of(year, mouth, day);
    }

    /**
     * 构建指定年月日的日期时间对象，时间默认为00:00:00
     *
     * @param year  年份
     * @param mouth 月份
     * @param day   日期
     * @return 构建的LocalDateTime对象
     */
    public LocalDateTime buildDateTime(int year, int mouth, int day) {
        return LocalDateTime.of(year, mouth, day, 0, 0, 0);
    }

    /**
     * 构建指定年月日时分秒的日期时间对象
     *
     * @param year   年份
     * @param mouth  月份
     * @param day    日期
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒
     * @return 构建的LocalDateTime对象
     */
    public LocalDateTime buildDateTime(int year, int mouth, int day, int hour, int minute, int second) {
        return LocalDateTime.of(year, mouth, day, hour, minute, second);
    }

    /**
     * 判断当前日期是否在指定的两个日期之间
     *
     * @param startDate 起始日期，如果为null，则返回false
     * @param endDate   结束日期，如果为null，则返回false
     * @return 如果当前日期在指定的两个日期之间，则返回true；否则返回false
     */
    public boolean isBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        LocalDate now = nowDate();
        return !now.isBefore(startDate) && !now.isAfter(endDate);
    }

    /**
     * 判断当前日期时间是否在指定的两个日期时间之间
     *
     * @param startTime 起始日期时间，如果为null，则返回false
     * @param endTime   结束日期时间，如果为null，则返回false
     * @return 如果当前日期时间在指定的两个日期时间之间，则返回true；否则返回false
     */
    public boolean isBetween(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }
        LocalDateTime now = nowDateTime();
        return !now.isBefore(startTime) && !now.isAfter(endTime);
    }

    /**
     * 获取指定日期所在的天的开始时间
     * 例如：2023-09-11 00:00:00,000
     *
     * @param date 日期
     * @return 天的开始时间
     */
    public LocalDateTime beginOfDay(LocalDateTime date) {
        return date.with(LocalTime.MIN);
    }

    /**
     * 获取指定日期所在的天的最后时间
     * 例如：2023-09-11 23:59:59,999
     *
     * @param date 日期
     * @return 天的结束时间
     */
    public LocalDateTime endOfDay(LocalDateTime date) {
        return date.with(LocalTime.MAX);
    }

    /**
     * 获取指定日期所在的月份的开始时间
     * 例如：2023-09-01 00:00:00,000
     *
     * @param date 日期
     * @return 月份的开始时间
     */
    public LocalDateTime beginOfMonth(LocalDateTime date) {
        return date.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取指定日期所在的月份的最后时间
     * 例如：2023-09-30 23:59:59,999
     *
     * @param date 日期
     * @return 月份的结束时间
     */
    public LocalDateTime endOfMonth(LocalDateTime date) {
        return date.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
    }

    /**
     * 时间转 Instant
     *
     * @param dateTime 时间
     * @return Instant
     */
    public Instant toInstant(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Instant 转 时间
     *
     * @param instant Instant
     * @return Instant
     */
    public LocalDateTime toDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * java.util.Date 转 时间
     *
     * @param date Date
     * @return LocalDateTime
     */
    public LocalDateTime toDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 毫秒数 转 时间
     *
     * @param milliseconds 毫秒数
     * @return LocalDateTime
     */
    public LocalDateTime toDateTime(long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    /**
     * localDateTime 转换成毫秒数
     *
     * @param dateTime 时间
     * @return long
     */
    public long toMilliseconds(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * localDate 转换成毫秒数
     *
     * @param date 日期
     * @return long
     */
    public long toMilliseconds(LocalDate date) {
        return toMilliseconds(date.atStartOfDay());
    }

    /**
     * 格式化日期
     *
     * @param date              未格式化的LocalDate对象
     * @param dateTimeFormatter 日期格式化器
     * @return 格式化后的日期字符串
     */
    public String formatDate(LocalDate date, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(date);
    }

    /**
     * 使用预定义的日期格式化器格式化日期
     *
     * @param date 未格式化的LocalDate对象
     * @return 格式化后的日期字符串
     */
    public String formatDate(LocalDate date) {
        return formatDate(date, DATE_FORMATTER_DATE);
    }

    /**
     * 使用预定义的简化日期格式化器格式化日期
     *
     * @param date 未格式化的LocalDate对象
     * @return 简化格式化后的日期字符串
     */
    public String formatDateMini(LocalDate date) {
        return formatDate(date, DATE_FORMATTER_DATE_MINI);
    }

    /**
     * 格式化日期和时间
     *
     * @param dateTime          未格式化的LocalDateTime对象
     * @param dateTimeFormatter 日期和时间格式化器
     * @return 格式化后的日期和时间字符串
     */
    public String formatDateTime(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(dateTime);
    }

    /**
     * 使用预定义的日期和时间格式化器格式化日期和时间
     *
     * @param dateTime 未格式化的LocalDateTime对象
     * @return 格式化后的日期和时间字符串
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_FORMATTER_DATETIME);
    }

    /**
     * 使用预定义的简化日期和时间格式化器格式化日期和时间
     *
     * @param dateTime 未格式化的LocalDateTime对象
     * @return 简化格式化后的日期和时间字符串
     */
    public String formatDateTimeMini(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DATE_FORMATTER_DATETIME_MINI);
    }

    /**
     * 格式化时间
     *
     * @param time              未格式化的LocalTime对象
     * @param dateTimeFormatter 时间格式化器
     * @return 格式化后的时间字符串
     */
    public String formatTime(LocalTime time, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(time);
    }

    /**
     * 使用预定义的时间格式化器格式化时间
     *
     * @param time 未格式化的LocalTime对象
     * @return 格式化后的时间字符串
     */
    public String formatTime(LocalTime time) {
        return formatTime(time, DATE_FORMATTER_TIME);
    }

    /**
     * 使用预定义的简化时间格式化器格式化时间
     *
     * @param time 未格式化的LocalTime对象
     * @return 简化格式化后的时间字符串
     */
    public String formatTimeMini(LocalTime time) {
        return formatTime(time, DATE_FORMATTER_TIME_MINI);
    }

    /**
     * 检查给定的日期是否已过期
     *
     * @param date 日期
     * @return 如果过期返回true，否则返回false
     */
    public boolean isExpired(LocalDate date) {
        return date.isBefore(nowDate());
    }

    /**
     * 检查给定的时间是否已过期
     *
     * @param dateTime 时间
     * @return 如果过期返回true，否则返回false
     */
    public boolean isExpired(LocalDateTime dateTime) {
        return dateTime.isBefore(nowDateTime());
    }

}
