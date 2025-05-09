package defpackage;

import android.text.TextUtils;
import health.compact.a.CommonUtil;

/* loaded from: classes.dex */
public class ggg {
    public static int a() {
        String b = ash.b("coachGenderConfig");
        if (TextUtils.isEmpty(b)) {
            return 1;
        }
        return CommonUtil.h(b);
    }
}
