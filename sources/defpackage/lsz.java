package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.openplatform.abl.log.util.h;

/* loaded from: classes5.dex */
public class lsz {
    public static String d() {
        String a2 = h.a("ro.product.model");
        return TextUtils.isEmpty(a2) ? Build.MODEL : a2;
    }
}
