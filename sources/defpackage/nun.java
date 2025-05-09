package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class nun {
    private static boolean d;

    public static boolean b(String str, List<String> list) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BluetoothAutoScanUtil", "filterToNames(), deviceName is null");
            return false;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("BluetoothAutoScanUtil", "filterToNames(), mNameFilters is null");
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        LogUtil.a("BluetoothAutoScanUtil", "after toUpperCase deviceName: ", upperCase);
        Iterator<String> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (!TextUtils.isEmpty(next) && upperCase.contains(next.toUpperCase(Locale.ENGLISH))) {
                z = true;
                break;
            }
        }
        LogUtil.a("BluetoothAutoScanUtil", "filterToNames(), isFlagFilter: ", Boolean.valueOf(z));
        if (!z) {
            int c = jfu.c(str);
            LogUtil.a("BluetoothAutoScanUtil", "filterToNames(), flagFilter productType: ", Integer.valueOf(c));
            if (c != -1) {
                return true;
            }
        }
        return z;
    }

    public static void d(boolean z) {
        d = z;
    }

    public static boolean b() {
        return d;
    }
}
