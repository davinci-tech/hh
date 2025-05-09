package com.huawei.hms.push.utils;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.wearengine.sensor.DataResult;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public class DateUtil {
    private DateUtil() {
    }

    public static String parseMilliSecondToString(Long l) {
        if (l == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(l);
        } catch (Exception e) {
            HMSLog.e("DateUtil", "parseMilliSecondToString Exception.", e);
            return null;
        }
    }

    public static long parseUtcToMillisecond(String str) throws ParseException, StringIndexOutOfBoundsException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        return simpleDateFormat.parse(str.substring(0, str.indexOf(".")) + (str.substring(str.indexOf(".")).substring(0, 4) + "Z")).getTime();
    }
}
