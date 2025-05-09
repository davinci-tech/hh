package defpackage;

import android.content.Context;
import android.os.RemoteException;
import androidx.core.app.NotificationCompat;
import com.huawei.appgallery.coreservice.api.ApiClient;
import com.huawei.appgallery.coreservice.c;
import com.huawei.appgallery.coreservice.f;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCRequest;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.RequestHeader;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.RequireVersion;
import com.huawei.appmarket.framework.coreservice.DataHolder;
import com.huawei.appmarket.framework.coreservice.Status;
import com.huawei.appmarket.framework.coreservice.a;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class aeu<C extends BaseIPCRequest> implements f {
    private static final List<String> e;

    /* renamed from: a, reason: collision with root package name */
    private final C f192a;
    private final Context b;

    @Override // com.huawei.appgallery.coreservice.f
    public final void a(ApiClient apiClient, a.AbstractBinderC0043a abstractBinderC0043a) {
        String str;
        if (this.f192a == null) {
            e(abstractBinderC0043a, 14);
            return;
        }
        c b = b(apiClient);
        if (b != null) {
            DataHolder dataHolder = new DataHolder();
            RequestHeader requestHeader = new RequestHeader();
            requestHeader.b(this.b.getPackageName());
            requestHeader.a(this.f192a.getMediaPkg());
            RequireVersion requireVersion = (RequireVersion) this.f192a.getClass().getAnnotation(RequireVersion.class);
            if (requireVersion != null) {
                requestHeader.a(requireVersion.value());
            }
            dataHolder.a(requestHeader);
            dataHolder.a(this.f192a.getMethod());
            dataHolder.a((DataHolder) this.f192a);
            try {
                b.a(dataHolder, abstractBinderC0043a);
                return;
            } catch (Exception e2) {
                str = "sync call ex:" + e2.getMessage();
            }
        } else {
            str = "can not find client";
        }
        afu.a(NotificationCompat.CATEGORY_TRANSPORT, str);
        e(abstractBinderC0043a, 8);
    }

    private void e(a aVar, int i) {
        if (aVar != null) {
            try {
                aVar.call(new Status(i));
            } catch (RemoteException e2) {
                afu.e(NotificationCompat.CATEGORY_TRANSPORT, "default failed call failed", e2);
            }
        }
    }

    private c b(ApiClient apiClient) {
        if (!(apiClient instanceof aeo)) {
            apiClient = apiClient.getDelegate();
            if (!(apiClient instanceof aeo)) {
                return null;
            }
        }
        return ((aeo) apiClient).a();
    }

    public aeu(Context context, C c) {
        this.b = context;
        this.f192a = c;
    }

    static {
        ArrayList arrayList = new ArrayList();
        e = arrayList;
        arrayList.add("method.cancelTask");
        arrayList.add("method.pauseTask");
        arrayList.add("method.queryTasks");
        arrayList.add("method.registerDownloadCallback");
        arrayList.add("method.resumeTask");
        arrayList.add("method.startDownloadTask");
        arrayList.add("method.unregisterDownloadCallback");
    }
}
