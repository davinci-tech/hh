package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.callback.FrameReceiver;
import health.compact.a.LogUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class sps {
    private Map<String, FrameReceiver> e;

    private sps() {
        this.e = new ConcurrentHashMap(10);
    }

    public static sps a() {
        return b.f17213a;
    }

    public Map<String, FrameReceiver> e() {
        return this.e;
    }

    public void c(String str, FrameReceiver frameReceiver) {
        if (TextUtils.isEmpty(str) || frameReceiver == null) {
            return;
        }
        LogUtil.a("ListenerManagement", "registerListener put: ", str);
        this.e.put(str, frameReceiver);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.a("ListenerManagement", "unregisterListener remove: ", str);
        this.e.remove(str);
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static sps f17213a = new sps();
    }
}
