package defpackage;

import android.text.TextUtils;
import android.util.LruCache;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.x2;
import defpackage.ipy;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes4.dex */
public class irt {
    private LruCache<String, ipy.c> d;

    public irt() {
        String e2 = SharedPreferenceManager.e("redirect_info_cache", x2.REDIRECT_INFO, "");
        if (TextUtils.isEmpty(e2)) {
            this.d = new LruCache<>(20);
        } else {
            this.d = (LruCache) HiJsonUtil.b(e2, new TypeToken<LruCache<String, ipy.c>>() { // from class: irt.5
            }.getType());
        }
    }

    static class e {
        private static final irt c = new irt();
    }

    public static irt c() {
        return e.c;
    }

    public ipy.c e(String str) {
        if (str == null) {
            return null;
        }
        ipy.c cVar = this.d.get(str);
        if (cVar == null || !cVar.isExpired()) {
            return cVar;
        }
        this.d.remove(str);
        d();
        return null;
    }

    public void c(String str, ipy.c cVar) {
        if (str == null) {
            return;
        }
        cVar.setExpiredTimestamp(System.currentTimeMillis() + 86400000);
        cVar.setSecret(null);
        this.d.put(str, cVar);
        d();
    }

    public void d() {
        SharedPreferenceManager.c("redirect_info_cache", x2.REDIRECT_INFO, HiJsonUtil.e(this.d));
    }
}
