package defpackage;

import android.content.Context;

/* loaded from: classes5.dex */
public class krn extends krp {

    /* renamed from: a, reason: collision with root package name */
    private static krn f14559a;

    private krn(Context context, String str) {
        super(context, str);
    }

    public static krn c(Context context) {
        krn krnVar;
        synchronized (krn.class) {
            if (f14559a == null) {
                f14559a = new krn(context, "com.huawei.hwid.site_list_info");
            }
            krnVar = f14559a;
        }
        return krnVar;
    }
}
