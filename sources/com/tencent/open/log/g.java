package com.tencent.open.log;

import android.text.format.Time;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.Constants;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes7.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public static final g f11363a = new g();

    public String a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        long j2 = j % 1000;
        Time time = new Time();
        time.set(j);
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append('/');
        sb.append(time.format("%Y-%m-%d %H:%M:%S"));
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        if (j2 < 10) {
            sb.append("00");
        } else if (j2 < 100) {
            sb.append('0');
        }
        sb.append(j2);
        sb.append(" [");
        if (thread == null) {
            sb.append("N/A");
        } else {
            sb.append(thread.getName());
        }
        sb.append("][");
        sb.append(str);
        sb.append("] ");
        sb.append(str2);
        sb.append('\n');
        if (th != null) {
            sb.append("* Exception : \n");
            sb.append(Log.getStackTraceString(th));
            sb.append('\n');
        }
        return sb.toString();
    }

    public final String a(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 32 ? Constants.LINK : "A" : ExifInterface.LONGITUDE_EAST : "W" : "I" : "D" : ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
    }
}
