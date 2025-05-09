package defpackage;

import com.huawei.wearengine.auth.AuthInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class pfs {
    private Map<String, AuthInfo> e = new ConcurrentHashMap(16);

    public void b(String str, AuthInfo authInfo) {
        if (str == null || authInfo == null) {
            return;
        }
        if (this.e == null) {
            this.e = new ConcurrentHashMap(16);
        }
        this.e.put(str, authInfo);
    }

    public AuthInfo a(String str) {
        if (str == null) {
            return null;
        }
        if (this.e == null) {
            this.e = new ConcurrentHashMap(6);
        }
        if (!this.e.containsKey(str)) {
            return null;
        }
        AuthInfo authInfo = this.e.get(str);
        this.e.remove(str);
        return authInfo;
    }

    public Map<String, AuthInfo> a() {
        return this.e;
    }
}
