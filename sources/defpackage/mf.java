package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;

/* loaded from: classes7.dex */
public class mf {
    public static String a(Context context) {
        if (EnvUtils.d()) {
            return "https://mobilegw.alipaydev.com/mgw.htm";
        }
        if (EnvUtils.e()) {
            return "https://mobilegw.dl.alipaydev.com/mgw.htm";
        }
        if (context == null) {
            return kj.c;
        }
        String str = kj.c;
        return TextUtils.isEmpty(str) ? kj.c : str;
    }
}
