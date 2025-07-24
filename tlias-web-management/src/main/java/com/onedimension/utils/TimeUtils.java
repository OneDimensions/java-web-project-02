package com.onedimension.utils;

public final class TimeUtils {
    private TimeUtils() {}

    /**
     * 格式化数据库时间
     * @param time
     * @return
     */
    public static String formatTime(String time) {
        return time.substring(0, 10);
    }
}
