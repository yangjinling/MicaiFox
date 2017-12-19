package com.micai.fox.util;

import android.os.Handler;
import android.os.Message;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            passDate.getPassDate(((String) msg.obj));
        }
    };
    private static onPassDate passDate;

    public interface onPassDate {
        void getPassDate(String data);
    }

    private DateUtil() {
    }

    ;

    /**
     * 枚举日期格式
     *
     * @author Administrator
     */
    public enum DatePattern {
        /**
         * 格式："yyyy-MM-dd HH:mm:ss"
         */
        ALL_TIME {
            public String getValue() {
                return "yyyy.MM.dd HH:mm:ss";
            }
        },
        ALL_TIMES {
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },
        /**
         * 格式："yyyy-MM"
         */
        ONLY_MONTH {
            public String getValue() {
                return "yyyy-MM";
            }
        },
        /**
         * 格式："yyyy-MM-dd"
         */
        ONLY_DAY {
            public String getValue() {
                return "yyyy-MM-dd";
            }
        },
        /**
         * 格式："yyyy-MM-dd"
         */
        ONLY_DAYS {
            public String getValue() {
                return "yyyy.MM.dd";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH"
         */
        ONLY_HOUR {
            public String getValue() {
                return "yyyy-MM-dd HH";
            }
        },
        /**
         * 格式："yyyy-MM-dd HH:mm"
         */
        ONLY_MINUTE

                {
                    public String getValue() {
                        return "yyyy-MM-dd HH:mm";
                    }
                },
        /**
         * 格式："MM-dd"
         */
        ONLY_MONTH_DAY

                {
                    public String getValue() {
                        return "MM-dd";
                    }
                },
        /**
         * 格式："MM-dd HH:mm"
         */
        ONLY_MONTH_SEC

                {
                    public String getValue() {
                        return "MM-dd HH:mm";
                    }
                },
        /**
         * 格式："HH:mm:ss"
         */
        ONLY_TIME

                {
                    public String getValue() {
                        return "HH:mm:ss";
                    }
                },
        /**
         * 格式："HH:mm"
         */
        ONLY_HOURS

                {
                    public String getValue() {
                        return "HH:mm";
                    }
                };

        public abstract String getValue();
    }

    /**
     * 获取当前时间
     *
     * @return 返回当前时间，格式2015-12-3	10:54:21
     */

    public static String getNowDate(DatePattern pattern) {
        String dateString = null;
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        LogUtil.e("YJL", "" + System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        dateString = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        LogUtil.e("YJL", "dateString-----" + dateString);
        return dateString;
    }


    public static void getWebsiteDatetime(final int type, final onPassDate onPassDates) {
        passDate = onPassDates;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");// 取得资源对象
                    URLConnection uc = url.openConnection();// 生成连接对象
                    uc.connect();// 发出连接
                    long ld = uc.getDate();// 读取网站日期时间
//                    Config.getInstance().setDate("" + ld);
                    Date date = new Date(ld);// 转换为标准时间对象
                    SimpleDateFormat sdf = null;
                    if (type == 0) {
                        sdf = new SimpleDateFormat(DatePattern.ALL_TIME.getValue(), Locale.CHINA);// 输出北京时间
                    } else if (type == 1) {
                        sdf = new SimpleDateFormat(DatePattern.ONLY_HOURS.getValue(), Locale.CHINA);
                    } else if (type == 2) {
                        sdf = new SimpleDateFormat(DatePattern.ONLY_DAYS.getValue(), Locale.CHINA);// 输出北京时间
                    }else if (type ==3) {
                        sdf = new SimpleDateFormat(DatePattern.ALL_TIMES.getValue(), Locale.CHINA);// 输出北京时间
                    }
                    LogUtil.e("YJL----", "date====" + sdf.format(date));
                    String dates = sdf.format(date);
                    Message message = handler.obtainMessage();
                    message.obj = dates;
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 将一个日期字符串转换成Data对象
     *
     * @param dateString 日期字符串
     * @param pattern    转换格式
     * @return 返回转换后的日期对象
     */
    public static Date stringToDate(String dateString, DatePattern pattern) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        try {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将date转换成字符串
     *
     * @param date    日期
     * @param pattern 日期的目标格式
     * @return
     */
    public static String dateToString(Date date, DatePattern pattern) {
        String string = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        string = simpleDateFormat.format(date);
        return string;
    }

    /**
     * 获取指定日期周几
     *
     * @param date 指定日期
     * @return 返回值为： "周日", "周一", "周二", "周三", "周四", "周五", "周六"
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return weekDays[week];
    }

    /**
     * 获取指定日期对应周几的序列
     *
     * @param date 指定日期
     * @return 周一：1	周二：2	周三：3	周四：4	周五：5	周六：6	周日：7
     */
    public static int getIndexWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) {
            return 7;
        } else {
            return --index;
        }
    }

    /**
     * 返回当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月号
     *
     * @return
     */
    public static int getNowDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取本月份的天数
     *
     * @param
     * @param
     * @return
     */
    public static int getNowDaysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return daysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE) + 1);
    }

    /**
     * 获取指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 对应天数
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 == 0) || year % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

}