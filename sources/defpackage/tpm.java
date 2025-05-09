package defpackage;

import android.os.SystemClock;
import android.util.LruCache;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class tpm {
    protected long c = 300000;
    private LruCache<String, Map<String, tpp>> e;

    public tpm(int i) {
        this.e = new LruCache<>(i);
    }

    public void b(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            return;
        }
        Map<String, tpp> map = this.e.get(str);
        if (map == null) {
            map = new HashMap<>(16);
            this.e.put(str, map);
        }
        map.put(str2, new tpp(str, str2, str3, SystemClock.elapsedRealtime()));
    }
}
