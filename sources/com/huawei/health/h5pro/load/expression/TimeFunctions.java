package com.huawei.health.h5pro.load.expression;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.health.h5pro.utils.LogUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes3.dex */
public class TimeFunctions {
    @ExpressionFunction(alias = "getSecondsBeforeTime")
    public long getSecondsBeforeTime(long j, long j2, String str) {
        return c(j, j2, str) / 1000;
    }

    @ExpressionFunction(alias = "getMilliSecondsBeforeTime")
    public long getMilliSecondsBeforeTime(long j, long j2, String str) {
        return c(j, j2, str);
    }

    @ExpressionFunction(alias = "getCurrentTimeZone")
    public String getCurrentTimeZone() {
        return new SimpleDateFormat("Z").format(Calendar.getInstance().getTime());
    }

    @ExpressionFunction(alias = "getCurrentSeconds")
    public long getCurrentSeconds() {
        return new Date().getTime() / 1000;
    }

    @ExpressionFunction(alias = "getCurrentMilliSeconds")
    public long getCurrentMilliSeconds() {
        return new Date().getTime();
    }

    private long c(long j, long j2, String str) {
        char c;
        long j3;
        Calendar calendar = Calendar.getInstance();
        if (j > 0) {
            calendar.setTimeInMillis(j);
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 68) {
            if (str.equals("D")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 72) {
            if (str.equals("H")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 77) {
            if (str.equals("M")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == 83) {
            if (str.equals(ExifInterface.LATITUDE_SOUTH)) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode == 89) {
            if (str.equals("Y")) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode != 2460) {
            if (hashCode == 2470 && str.equals("MS")) {
                c = 6;
            }
            c = 65535;
        } else {
            if (str.equals("MI")) {
                c = 5;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                calendar.add(6, (int) (-j2));
                break;
            case 1:
                j3 = 3600000;
                j2 *= j3;
                calendar.setTimeInMillis(j - j2);
                break;
            case 2:
                calendar.add(2, (int) (-j2));
                break;
            case 3:
                j3 = 1000;
                j2 *= j3;
                calendar.setTimeInMillis(j - j2);
                break;
            case 4:
                calendar.add(1, (int) (-j2));
                break;
            case 5:
                j3 = 60000;
                j2 *= j3;
                calendar.setTimeInMillis(j - j2);
                break;
            case 6:
                calendar.setTimeInMillis(j - j2);
                break;
            default:
                LogUtil.e("H5PRO_TimeFunctions", "unknown intervalUnit: " + str);
                break;
        }
        return calendar.getTimeInMillis();
    }
}
