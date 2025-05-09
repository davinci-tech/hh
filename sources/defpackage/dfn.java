package defpackage;

import android.content.Context;
import com.huawei.health.R;

/* loaded from: classes3.dex */
public class dfn {
    public static String e(Context context, short s) {
        if (context == null) {
            return "";
        }
        String string = context.getString(R.string.IDS_device_measureactivity_result_normal);
        if (s == Short.MIN_VALUE) {
            return context.getString(R.string.IDS_device_measure_error_result_error);
        }
        if (s == 0) {
            return context.getString(R.string.IDS_device_measureactivity_result_low);
        }
        if (s != 1) {
            return s != 2 ? string : context.getString(R.string.IDS_device_measureactivity_result_high);
        }
        return context.getString(R.string.IDS_device_measureactivity_result_normal);
    }
}
