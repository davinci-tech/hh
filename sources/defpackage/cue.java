package defpackage;

import android.text.TextUtils;
import com.huawei.health.ecologydevice.manager.ResourceManager;

/* loaded from: classes3.dex */
public class cue {
    public static boolean d(dcz dczVar) {
        return dczVar != null && dczVar.x().c() == 8;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return d(ResourceManager.e().d(str));
    }
}
