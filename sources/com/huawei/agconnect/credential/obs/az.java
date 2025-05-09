package com.huawei.agconnect.credential.obs;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.analytics.connector.ConnectorManager;
import com.huawei.hms.analytics.instance.CallBack;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class az implements ay {

    /* renamed from: a, reason: collision with root package name */
    bb f1760a;
    private ConnectorManager b;

    @Override // com.huawei.agconnect.credential.obs.ay
    public final void syncOaid(final aw awVar) {
        if (awVar == null) {
            return;
        }
        ConnectorManager connectorManager = this.b;
        if (connectorManager == null) {
            awVar.result(-101, "Ha sdk init failed");
            return;
        }
        try {
            connectorManager.syncOaid(new CallBack() { // from class: com.huawei.agconnect.credential.obs.az.1
                public final void onResult(int i, String str) {
                    awVar.result(i, str);
                }
            });
        } catch (Throwable th) {
            Log.w("BridgeInstance", "syncOaid error, " + th.getMessage());
        }
    }

    @Override // com.huawei.agconnect.credential.obs.ay
    public final void c(boolean z) {
        ConnectorManager connectorManager = this.b;
        if (connectorManager != null) {
            try {
                connectorManager.setAnalyticsEnabled(z);
            } catch (Throwable th) {
                Log.w("BridgeInstance", "setAnalyticsEnabled: " + th.getMessage());
            }
        }
    }

    @Override // com.huawei.agconnect.credential.obs.ay
    public final Map<String, String> b(boolean z) {
        ConnectorManager connectorManager = this.b;
        return connectorManager != null ? connectorManager.getUserProfiles(z) : new HashMap();
    }

    @Override // com.huawei.agconnect.credential.obs.ay
    public final void a(boolean z) {
        ConnectorManager connectorManager = this.b;
        if (connectorManager != null) {
            connectorManager.setEnableAndroidID(Boolean.valueOf(z));
        }
    }

    @Override // com.huawei.agconnect.credential.obs.ay
    public final void a(String str, Bundle bundle) {
        ConnectorManager connectorManager = this.b;
        if (connectorManager != null) {
            connectorManager.onEvent(str, bundle);
        }
    }

    final void a(Context context, bb bbVar) {
        this.f1760a = bbVar;
        try {
            if (TextUtils.isEmpty(bbVar.c)) {
                this.b = new ConnectorManager(context, bbVar.f1764a, bbVar.b);
            } else {
                this.b = new ConnectorManager(context, bbVar.f1764a, bbVar.b, bbVar.c);
            }
        } catch (Exception | NoClassDefFoundError unused) {
            Log.e("BridgeInstance", "createInstance error");
            this.b = null;
        }
    }

    @Override // com.huawei.agconnect.credential.obs.ay
    public final void a() {
        ConnectorManager connectorManager = this.b;
        if (connectorManager != null) {
            connectorManager.onReport();
        }
    }

    @Override // com.huawei.agconnect.credential.obs.ay
    public final Map<String, String[]> a(String str) {
        if (this.b == null) {
            return new HashMap();
        }
        if (TextUtils.isEmpty(str)) {
            Log.w("BridgeInstance", "Invalid parameter.");
            return new HashMap();
        }
        try {
            return this.b.getDataUploadSiteInfo(str);
        } catch (Throwable th) {
            Log.w("BridgeInstance", "getDataUploadSiteInfo error. " + th.getMessage());
            return new HashMap();
        }
    }

    public az(Context context, bb bbVar) {
        a(context, bbVar);
    }
}
