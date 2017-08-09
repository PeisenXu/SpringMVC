package com.api.util;

import com.api.exception.SenaThrowRunTimeException;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Sena on 2017/2/28.
 */
public class TimeUtil {
    public final static String format1 = "MM/dd/yyyy HH:mm:ss.SSS";
    public final static String format2 = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String format3 = "MM/dd/yyyy";
    public final static String format4 = "MM/dd/yy";
    public final static String format5 = "yyyy/MM/dd";
    public final static String format6 = "M/d/yyyy";
    public final static String format7 = "MM/d/yyyy";
    public final static String format8 = "M/dd/yyyy";
    public final static String format9 = "MM-dd-yyyy";
    public final static String format10 = "yyyy-MM-dd";

    private final static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private final static String dateFormatFull = "yyyy-MM-dd HH:mm:ss.SSS";
    private final static String formatMMddyyyy = "MMddyyyy";
    private final static String[] dateFormats = new String[]{"MM/dd/yyyy", "yyyy/MM/dd", "MM-dd-yyyy", "yyyy-MM-dd", "M/dd/yyyy", "M/d/yyyy", "MM/d/yyyy"};
    private static DateFormat format = new SimpleDateFormat(dateFormat);
    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    public static String getUTCTimeStr() {
        StringBuffer UTCTimeBuffer = new StringBuffer();

        Calendar cal = Calendar.getInstance();

        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);

        int dstOffset = cal.get(Calendar.DST_OFFSET);

        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day);
        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute).append(":").append(second);
        try {
            Date date = format.parse(UTCTimeBuffer.toString());
            return format.format(date);
        } catch (ParseException e) {
            logger.info("ParseException", e);

        }
        return null;
    }

    @Deprecated
    public static Date getDate(String s) {
        return new DateTime(s).toDate();
    }

    private static final String separator1 = "/";
    private static final String separator2 = "-";

    /**
     * 只解析日期部分
     *
     * @return
     */
    public static Date parseDate(String s) {
        String[] strs;
        String format = null;
        String separator = null;
        if (s.contains(separator1))
            separator = separator1;
        else if (s.contains(separator2))
            separator = separator2;
        if (StringUtil.isEmptyOrBlank(separator) || s.split(separator).length < 3)
            throw new UnsupportedOperationException("Unsupported date format.");

        strs = s.split(separator);
        int index = 3;
        for (int i = 0; i < 3; i++) {
            if (strs[i].length() == 4) {
                index = i;
                break;
            }
        }
        if (index == 3) {
            format = String.format("%s" + separator + "%s" + separator + "%s", "MM", "dd", "yy");
        } else {
            if (index == 0)
                format = String.format("%s" + separator + "%s" + separator + "%s", "yyyy", "MM", "dd");
            if (index == 2)
                format = String.format("%s" + separator + "%s" + separator + "%s", "MM", "dd", "yyyy");
        }
        if (StringUtil.isEmptyOrBlank(format))
            throw new UnsupportedOperationException("Unsupported date format.");
        return parse(s, format);
    }

    /**
     * 按照指定格式解析日期
     *
     * @param s
     * @param format
     * @return
     */
    public static Date parse(String s, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(s);
        } catch (ParseException e) {
            throw new SenaThrowRunTimeException("Parse date failed.", e);
        }
    }

    public static Date getNow() {
        return new DateTime().toDate();
    }

    public static Date getUtcNow() {
        return new DateTime(DateTimeZone.UTC).toLocalDateTime().toDate();
    }

    public static String getUtcNowStr() {
        Date utcNow = getUtcNow();
        return format(utcNow, dateFormatFull);
    }

    public static Date getLocalNow(String timezone) {
        return new DateTime(DateTimeZone.forID(TimeUtil.formatTimeZone(timezone))).toDate();
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 判断日期年份是否正确
     *
     * @param date
     * @return
     */
    public static boolean isValidYear(Date date) {
        return getYear(date) > 1980;
    }

    public static Date addYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    public static Date addMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    public static Date addWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    public static Date addDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    public static Date addMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    public static String formatTimeZone(String timeZone) {
        for (String availableId : TimeZone.getAvailableIDs()) {
            if (availableId.equals(timeZone)) {
                return availableId;
            }
        }
        return "America/Los_Angeles";
    }

    public static String format(Date date) {
        return format(date, dateFormatFull);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyyMMdd");

    public static boolean timeBetweenFromTo(DateTime time, DateTime from, DateTime to) {
        if ((from.isBefore(time) || dateTimeFormat.print(from).equals(dateTimeFormat.print(time))) &&
                (to.isAfter(time) || dateTimeFormat.print(to).equals(dateTimeFormat.print(time)))) {
            return true;
        }
        return false;
    }

    public static String getStartOfDay(String start, SimpleDateFormat format2) throws ParseException {
        return format2.format(new DateTime(format2.parse(start)).withTime(0, 0, 0, 000).toDate());
    }

    public static String getEndOfDay(String end, SimpleDateFormat format2) throws ParseException {
        return format2.format(new DateTime(format2.parse(end)).withTime(23, 59, 59, 999).toDate());
    }

    /**
     * 判断两个时间的日期部分是否相同
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equalsDate(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return false;

        DateTime jodaDate1 = new DateTime(date1);
        DateTime jodaDate2 = new DateTime(date2);

        return jodaDate1.getYear() == jodaDate2.getYear()
                && jodaDate1.getMonthOfYear() == jodaDate2.getMonthOfYear()
                && jodaDate1.getDayOfMonth() == jodaDate2.getDayOfMonth();
    }

    public static boolean isValidDate(String dateStr, String format) {
        try {
            parse(dateStr, format);
            return true;
        } catch (SenaThrowRunTimeException e) {
            if (e.getCause() instanceof ParseException) {
                return false;
            }
            throw e;
        }
    }

    /**
     * 判断字符串是否是日期格式
     *
     * @param dateStr
     * @return
     */
    public static boolean isValidDate(String dateStr) {
        int time = 0;
        for (String format : dateFormats) {
            try {
                parse(dateStr, format);
            } catch (Exception e) {
                time++;
            }
        }
        return time < dateFormats.length;
    }

    /**
     * 通过出生日期计算年龄
     *
     * @param birthday
     * @return
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);

        if (cal.before(birthday)) {
            throw new UnsupportedOperationException("The birthDay is before Now.");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * 计算学生的年龄
     *
     * @param completedDate 快照完成日期
     * @param birthDate     生日
     * @param toDate        周期结束日期
     * @return
     */
    public static Integer getAgeMonth(String completedDate, String birthDate, long toDate) {
        Integer ageMonth;
        Long completed;
        if (StringUtil.isEmptyOrBlank(completedDate)) {
            completed = TimeUtil.getNow().getTime();
        } else {
            completed = TimeUtil.parseDate(completedDate).getTime();
        }
        Date birthday = TimeUtil.parseDate(birthDate);
        //学生年龄（月份）
        if (completed != 0) {
            ageMonth = TimeUtil.getMonth(birthday, new Date(completed));
        } else {
            if (toDate != 0 && toDate > birthday.getTime())
                ageMonth = TimeUtil.getMonth(birthday, new Date(toDate));
            else
                ageMonth = TimeUtil.getMonth(birthday, TimeUtil.getUtcNow());
        }
        return ageMonth;
    }

    public static String getAgeByAgeMonth(Integer ageMonth) {
        if (ageMonth == null || ageMonth == 0)
            return "0";
        int year = ageMonth / 12;
        int month = ageMonth % 12;
        String result = "";
        if (year > 0) {
            result += year;
            if (year == 1) {
                result += " year ";
            } else {
                result += " years ";
            }
        }
        if (month > 0) {
            result += month;
            if (month == 1) {
                result += " month";
            } else {
                result += " months";
            }
        }
        return result.trim();
    }

    /**
     * 通过生日计算年龄（按月份）
     *
     * @param from
     * @param to
     * @return
     */
    public static int getMonth(Date from, Date to) {
        Calendar fromCalendar = Calendar.getInstance();
        Calendar toCalendar = Calendar.getInstance();
        fromCalendar.setTime(from);
        toCalendar.setTime(to);
        int fromDay = fromCalendar.get(Calendar.DAY_OF_MONTH);
        int toDay = toCalendar.get(Calendar.DAY_OF_MONTH);
        int fromMonth = fromCalendar.get(Calendar.MONTH) + 1;
        int toMonth = toCalendar.get(Calendar.MONTH) + 1;
        int fromYear = fromCalendar.get(Calendar.YEAR);
        int toYear = toCalendar.get(Calendar.YEAR);

        int year = toYear - fromYear;
        int month = toMonth - fromMonth;

        if (toDay < fromDay)
            month--;
        return year * 12 + month;
    }

    public static Date getCutoffDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 将UTC时间转成指定时区的时间
     *
     * @param utcDate
     * @param timezone
     * @return
     */
    public static Date convertUtcToLocal(Date utcDate, String timezone) {
        timezone = formatTimeZone(timezone);
        return convertTimeZone(utcDate, TimeZone.getTimeZone("UTC"), TimeZone.getTimeZone(timezone));
    }

    /**
     * http://stackoverflow.com/questions/2891361/how-to-set-time-zone-of-a-java-util-date
     * Converts the given <code>date</code> from the <code>fromTimeZone</code> to the
     * <code>toTimeZone</code>.  Since java.util.Date has does not really store time zome
     * information, this actually converts the date to the date that it would be in the
     * other time zone.
     *
     * @param date
     * @param fromTimeZone
     * @param toTimeZone
     * @return
     */
    public static Date convertTimeZone(Date date, TimeZone fromTimeZone, TimeZone toTimeZone) {
        long fromTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, fromTimeZone);
        long toTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, toTimeZone);

        return new Date(date.getTime() + (toTimeZoneOffset - fromTimeZoneOffset));
    }

    /**
     * Calculates the offset of the <code>timeZone</code> from UTC, factoring in any
     * additional offset due to the time zone being in daylight savings time as of
     * the given <code>date</code>.
     *
     * @param date
     * @param timeZone
     * @return
     */
    private static long getTimeZoneUTCAndDSTOffset(Date date, TimeZone timeZone) {
        long timeZoneDSTOffset = 0;
        if (timeZone.inDaylightTime(date)) {
            timeZoneDSTOffset = timeZone.getDSTSavings();
        }

        return timeZone.getRawOffset() + timeZoneDSTOffset;
    }


}
