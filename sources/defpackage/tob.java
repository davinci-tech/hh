package defpackage;

import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class tob<T extends WearEngineBinderClient> implements WearEngineClientInner.ReleaseConnectionCallback {
    private WeakReference<T> b;

    public tob(WeakReference<T> weakReference) {
        this.b = weakReference;
    }

    @Override // com.huawei.wearengine.WearEngineClientInner.ReleaseConnectionCallback
    public void onReleaseConnection() {
        WeakReference<T> weakReference = this.b;
        if (weakReference == null) {
            tov.d("WearEngineReleaseConnectCallback", "onReleaseConnection mWeakReference is null");
            return;
        }
        T t = weakReference.get();
        if (t != null) {
            t.clearBinderProxy();
        }
    }
}
