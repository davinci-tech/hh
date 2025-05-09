package defpackage;

import com.huawei.hms.framework.common.ExceptionCode;
import java.util.Date;

/* loaded from: classes7.dex */
public final class svz {
    public static String d() {
        int nextInt = (svy.e().nextInt(ExceptionCode.CRASH_EXCEPTION) + ExceptionCode.CRASH_EXCEPTION) % ExceptionCode.CRASH_EXCEPTION;
        if (nextInt < 1000000) {
            nextInt += 1000000;
        }
        return swb.e(new Date(System.currentTimeMillis()), "yyyyMMddHHmmssSSS") + nextInt;
    }
}
