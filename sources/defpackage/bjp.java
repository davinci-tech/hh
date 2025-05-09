package defpackage;

import android.text.TextUtils;
import health.compact.a.LogUtil;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bjp {
    private ConcurrentHashMap<String, bjq> d;

    private bjp() {
        this.d = new ConcurrentHashMap<>();
    }

    public static bjp d() {
        return a.c;
    }

    public boolean i(String str) {
        return this.d.containsKey(str);
    }

    public void a(String str, int i) {
        this.d.put(str, new bjq(str));
        if (i == 2) {
            this.d.get(str).d(true);
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("HiChainLiteAuthManager", "clearHiChainLiteParams: deviceIdentify is empty");
        } else {
            this.d.remove(str);
        }
    }

    public boolean h(String str) {
        if (this.d.containsKey(str)) {
            return this.d.get(str).h();
        }
        return false;
    }

    public String a(String str) {
        if (this.d.containsKey(str)) {
            return this.d.get(str).e();
        }
        LogUtil.a("HiChainLiteAuthManager", "getNextAuthenticateKey: hiChainLiteParams is not exist.");
        return "";
    }

    public void d(String str, String str2) {
        if (this.d.containsKey(str)) {
            this.d.get(str).b(str2);
        }
    }

    public void d(String str, int i) {
        if (this.d.containsKey(str)) {
            this.d.get(str).b(i);
        } else {
            LogUtil.e("HiChainLiteAuthManager", "device does not a hichainlite device when setEncryptAlgorithmType.");
        }
    }

    public int c(String str) {
        if (this.d.containsKey(str)) {
            return this.d.get(str).a();
        }
        LogUtil.e("HiChainLiteAuthManager", "device does not a hichainlite device when getEncryptAlgorithmType.");
        return 0;
    }

    public void a(String str, String str2) {
        if (this.d.containsKey(str2)) {
            this.d.get(str2).e(str);
        } else {
            LogUtil.e("HiChainLiteAuthManager", "device does not a hichainlite device when setReconnectionKey.");
        }
    }

    public String g(String str) {
        if (this.d.containsKey(str)) {
            return this.d.get(str).b();
        }
        LogUtil.e("HiChainLiteAuthManager", "device does not a hichainlite device when getReconnectionKey.");
        return "";
    }

    public void e(String str, String str2) {
        if (this.d.containsKey(str)) {
            this.d.get(str).c(str2);
        } else {
            LogUtil.e("HiChainLiteAuthManager", "device does not a hichainlite device when setFirstAuthenticateKey");
        }
    }

    public String e(String str) {
        bjq bjqVar;
        if (this.d.containsKey(str) && (bjqVar = this.d.get(str)) != null) {
            return bjqVar.d();
        }
        LogUtil.e("HiChainLiteAuthManager", "device does not a hichainlite device when getFirstAuthenticateKey.");
        return "";
    }

    public byte[] b(String str) {
        bjq bjqVar;
        if (this.d.containsKey(str) && (bjqVar = this.d.get(str)) != null) {
            byte[] c = bjqVar.c();
            if (c == null || c.length == 0) {
                c(str, bjr.a(str, 2));
            }
            return bjqVar.c();
        }
        LogUtil.a("HiChainLiteAuthManager", "getCurrentEncryptionKey: hiChainLiteParams is not exist.");
        return null;
    }

    public void c(String str, String str2) {
        if (this.d.containsKey(str)) {
            bjq bjqVar = this.d.get(str);
            if (bjqVar != null) {
                bjqVar.c(blq.a(str2));
                return;
            }
            return;
        }
        LogUtil.e("HiChainLiteAuthManager", "setCurrentEncryptionKey: hiChainLiteParams is not exist.");
    }

    static class a {
        private static bjp c = new bjp();
    }
}
