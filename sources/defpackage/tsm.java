package defpackage;

import android.os.RemoteException;
import com.google.android.clockwork.companion.partnerapi.PartnerApi;
import com.huawei.hwbtsdk.btdatatype.callback.IBindPartnerServiceCallback;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class tsm {

    /* renamed from: a, reason: collision with root package name */
    private static tsm f17360a;
    private static final Object c = new Object();

    private tsm() {
    }

    public static tsm c() {
        tsm tsmVar;
        synchronized (c) {
            LogUtil.c("ReconnectManager", "getInstance() ");
            if (f17360a == null) {
                f17360a = new tsm();
            }
            tsmVar = f17360a;
        }
        return tsmVar;
    }

    public void a(final String str) {
        if (str == null) {
            LogUtil.c("ReconnectManager", "node id is null");
        } else {
            tsl.d().a(new IBindPartnerServiceCallback() { // from class: tsm.4
                @Override // com.huawei.hwbtsdk.btdatatype.callback.IBindPartnerServiceCallback
                public void onBinderResponse(PartnerApi partnerApi) {
                    LogUtil.c("ReconnectManager", "reconnect begin");
                    if (partnerApi != null) {
                        try {
                            LogUtil.c("ReconnectManager", "reconnect result is ", Boolean.valueOf(partnerApi.reconnectByNodeId(str)));
                        } catch (RemoteException e) {
                            LogUtil.e("ReconnectManager", "reconnectByNode error is ", e.getMessage());
                        }
                    }
                }
            });
        }
    }
}
