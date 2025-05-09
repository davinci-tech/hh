package defpackage;

import android.os.Bundle;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class lak {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<String, MessageOrStateCallback> f14724a = new ConcurrentHashMap<>();

    public final void a(String str, MessageOrStateCallback messageOrStateCallback) {
        LogUtil.a("Track_IDEQ_FitnessMessagePublisher", "callbackKey = " + str);
        ConcurrentHashMap<String, MessageOrStateCallback> concurrentHashMap = this.f14724a;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(str, messageOrStateCallback);
        }
    }

    public boolean d(String str) {
        LogUtil.a("Track_IDEQ_FitnessMessagePublisher", "unregisterDataOrStateCallBack = " + str);
        ConcurrentHashMap<String, MessageOrStateCallback> concurrentHashMap = this.f14724a;
        if (concurrentHashMap != null && !concurrentHashMap.isEmpty()) {
            this.f14724a.remove(str);
        }
        ConcurrentHashMap<String, MessageOrStateCallback> concurrentHashMap2 = this.f14724a;
        return concurrentHashMap2 == null || concurrentHashMap2.isEmpty();
    }

    public final void a() {
        this.f14724a.clear();
    }

    public void bUy_(int i, Bundle bundle) {
        ConcurrentHashMap<String, MessageOrStateCallback> concurrentHashMap = this.f14724a;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<MessageOrStateCallback> it = this.f14724a.values().iterator();
        while (it.hasNext()) {
            it.next().onNewMessage(i, bundle);
        }
    }

    public void b(String str) {
        ConcurrentHashMap<String, MessageOrStateCallback> concurrentHashMap = this.f14724a;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        LogUtil.c("Track_IDEQ_FitnessMessagePublisher", "notifyFitnessDeviceDataOrStateCallback, state is ", str);
        Iterator<MessageOrStateCallback> it = this.f14724a.values().iterator();
        while (it.hasNext()) {
            it.next().onStateChange(str);
        }
    }
}
