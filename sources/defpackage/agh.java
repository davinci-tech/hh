package defpackage;

import android.text.TextUtils;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class agh {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, InstallCallback> f197a = new ConcurrentHashMap();
    private static Map<String, MarketInfo> d = new ConcurrentHashMap();

    public static void e(String str) {
        if (str == null) {
            return;
        }
        d.remove(str);
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f197a.remove(str);
    }

    public static MarketInfo d(String str) {
        if (str == null) {
            return null;
        }
        return d.get(str);
    }

    public static void e(String str, MarketInfo marketInfo) {
        if (str == null) {
            return;
        }
        d.put(str, marketInfo);
    }

    public static String a(InstallCallback installCallback) {
        if (installCallback == null) {
            return "";
        }
        String uuid = UUID.randomUUID().toString();
        f197a.put(uuid, installCallback);
        return uuid;
    }

    public static InstallCallback a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return f197a.get(str);
    }
}
