package defpackage;

import com.huawei.ui.commonui.base.BaseActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gcd {
    private static List<WeakReference<BaseActivity>> c = new ArrayList(10);
    private static Map<String, List<WeakReference<BaseActivity>>> d = new HashMap(10);

    public static void d(WeakReference<BaseActivity> weakReference) {
        c.add(weakReference);
    }
}
