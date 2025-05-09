package defpackage;

import android.text.TextUtils;
import com.huawei.wearengine.auth.AuthListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class tnz {
    private static volatile tnz b;
    private Map<String, AuthListener> c = new ConcurrentHashMap();

    private tnz() {
    }

    public static tnz b() {
        if (b == null) {
            synchronized (tnz.class) {
                if (b == null) {
                    b = new tnz();
                }
            }
        }
        return b;
    }

    public void b(String str, AuthListener authListener) {
        if (TextUtils.isEmpty(str) || authListener == null || this.c == null) {
            return;
        }
        tos.a("AuthCallbackManage", "putCallbackToMap mAuthListenerMap != null");
        this.c.put(str, authListener);
    }

    public AuthListener c(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.e("AuthCallbackManage", "getCallbackFromMap key == null");
            return null;
        }
        if (!this.c.containsKey(str)) {
            tos.e("AuthCallbackManage", "getCallbackFromMap mAuthListenerMap not containsKey");
            return null;
        }
        AuthListener authListener = this.c.get(str);
        this.c.remove(str);
        return authListener;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.e("AuthCallbackManage", "removeCallbackFromMap key isEmpty");
        } else if (!this.c.containsKey(str)) {
            tos.e("AuthCallbackManage", "removeCallbackFromMap mAuthListenerMap not containsKey");
        } else {
            this.c.remove(str);
        }
    }
}
