package defpackage;

import android.content.Context;
import java.io.File;

/* loaded from: classes7.dex */
public class lw {
    public static lw b;
    public Context c;

    public static boolean b() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    public static lw c() {
        if (b == null) {
            b = new lw();
        }
        return b;
    }

    public void c(Context context) {
        kv.c();
        this.c = context.getApplicationContext();
    }

    public Context d() {
        return this.c;
    }

    public String e() {
        return me.d(null, this.c);
    }

    public kv a() {
        return kv.c();
    }
}
