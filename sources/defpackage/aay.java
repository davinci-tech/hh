package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.huawei.android.hicloud.sync.service.IServiceProtocol;
import com.huawei.android.hicloud.sync.service.aidl.IRequireDownService;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import com.huawei.android.hicloud.sync.service.w;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class aay {

    /* renamed from: a, reason: collision with root package name */
    private IRequireDownService f154a;
    private final String b;
    private final Context c;
    private ServiceConnection d;
    private final String e;
    private final ISyncServiceCallback f;
    private final String k;
    private boolean g = false;
    private boolean j = false;
    private final List<ServiceConnection> i = new ArrayList();
    private final String h = abl.a();

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            aay aayVar = aay.this;
            aayVar.b(aayVar.k, "onCloudServiceConnected RemoteException");
        }
    }

    class b extends w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f156a;
        final /* synthetic */ String b;
        final /* synthetic */ boolean c;
        final /* synthetic */ boolean d;
        final /* synthetic */ String e;
        final /* synthetic */ String g;

        b(String str, String str2, List list, boolean z, boolean z2, String str3) {
            this.b = str;
            this.e = str2;
            this.f156a = list;
            this.d = z;
            this.c = z2;
            this.g = str3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("DownloadServiceProtocol", "downLoadFile execute");
            aay.this.f154a.downLoadFile(this.b, this.e, this.f156a, this.d, this.c, this.g, aay.this.h);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            aay.this.f154a.downLoadFileForTransTooLarge(this.b, this.e, this.d, this.c, this.g, bArr, false, aay.this.h);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aay.this.c((List<UnstructData>) this.f156a));
                aay.this.f154a.downLoadFileForTransTooLarge(this.b, this.e, this.d, this.c, this.g, new byte[0], true, aay.this.h);
            } catch (RemoteException e) {
                e = e;
                abd.b("DownloadServiceProtocol", "downLoadFile error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("DownloadServiceProtocol", "downLoadFile error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("DownloadServiceProtocol", "downLoadFile error : JSONException");
            }
        }
    }

    class c implements ServiceConnection {
        final /* synthetic */ IServiceProtocol b;

        c(IServiceProtocol iServiceProtocol) {
            this.b = iServiceProtocol;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            abd.d("DownloadServiceProtocol", "onBindingDied pkgName is：" + componentName.getPackageName());
            aay.this.g = false;
            aay aayVar = aay.this;
            aayVar.b(aayVar.k, "serviceBindingDied");
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            abd.c("DownloadServiceProtocol", "service connected");
            if (aay.this.f154a == null) {
                abd.c("DownloadServiceProtocol", "onServiceConnected new downloadService");
                aay.this.f154a = IRequireDownService.Stub.asInterface(iBinder);
            }
            aay.this.a();
            aay.this.g = true;
            aay.this.c(this.b);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            abd.d("DownloadServiceProtocol", "onServiceDisconnected pkgName is：" + componentName.getPackageName());
            aay.this.g = false;
            aay aayVar = aay.this;
            aayVar.b(aayVar.k, "ServiceDisconnected");
        }
    }

    class d extends w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f157a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String e;

        d(String str, String str2, List list, String str3) {
            this.e = str;
            this.c = str2;
            this.f157a = list;
            this.b = str3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("DownloadServiceProtocol", "pauseDownloadFile execute");
            aay.this.f154a.pauseDownloadFile(this.e, this.c, this.f157a, this.b, aay.this.h);
            aay.this.b(this.b);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            aay.this.f154a.pauseDownloadFileForTransTooLarge(this.e, this.c, this.b, bArr, false, aay.this.h);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aay.this.c((List<UnstructData>) this.f157a));
                aay.this.f154a.pauseDownloadFileForTransTooLarge(this.e, this.c, this.b, new byte[0], true, aay.this.h);
            } catch (RemoteException e) {
                e = e;
                abd.b("DownloadServiceProtocol", "pauseDownloadFile error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("DownloadServiceProtocol", "pauseDownloadFile error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("DownloadServiceProtocol", "pauseDownloadFile error : JSONException");
            }
        }
    }

    class e extends w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f158a;
        final /* synthetic */ List b;
        final /* synthetic */ String c;
        final /* synthetic */ String e;

        e(String str, String str2, List list, String str3) {
            this.f158a = str;
            this.c = str2;
            this.b = list;
            this.e = str3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("DownloadServiceProtocol", "startDownloadFile execute");
            aay.this.f154a.startDownloadFile(this.f158a, this.c, this.b, this.e, aay.this.h);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            aay.this.f154a.startDownloadFileForTransTooLarge(this.f158a, this.c, this.e, bArr, false, aay.this.h);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aay.this.c((List<UnstructData>) this.b));
                aay.this.f154a.startDownloadFileForTransTooLarge(this.f158a, this.c, this.e, new byte[0], true, aay.this.h);
            } catch (RemoteException e) {
                e = e;
                abd.b("DownloadServiceProtocol", "startDownloadFile error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("DownloadServiceProtocol", "startDownloadFile error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("DownloadServiceProtocol", "startDownloadFile error : JSONException");
            }
        }
    }

    class f extends w {
        final /* synthetic */ List b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        f(String str, String str2, List list, String str3) {
            this.c = str;
            this.d = str2;
            this.b = list;
            this.e = str3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("DownloadServiceProtocol", "cancelDownloadFile execute");
            aay.this.f154a.cancelDownloadFile(this.c, this.d, this.b, this.e, aay.this.h);
            aay.this.b(this.e);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            aay.this.f154a.cancelDownloadFileForTransTooLarge(this.c, this.d, this.e, bArr, false, aay.this.h);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aay.this.c((List<UnstructData>) this.b));
                aay.this.f154a.cancelDownloadFileForTransTooLarge(this.c, this.d, this.e, new byte[0], true, aay.this.h);
            } catch (RemoteException e) {
                e = e;
                abd.b("DownloadServiceProtocol", "cancelDownloadFile error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("DownloadServiceProtocol", "cancelDownloadFile error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("DownloadServiceProtocol", "cancelDownloadFile error : JSONException");
            }
        }
    }

    public aay(Context context, String str, String str2, String str3, ISyncServiceCallback iSyncServiceCallback) {
        this.c = context;
        this.b = str;
        this.e = str2;
        this.f = iSyncServiceCallback;
        this.k = str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            abd.c("DownloadServiceProtocol", "registerCallBack mCallbackRegisted = " + this.j);
            IRequireDownService iRequireDownService = this.f154a;
            if (iRequireDownService == null || this.j) {
                return;
            }
            this.j = iRequireDownService.registerSingleCallback(this.f, this.b, this.h);
            abd.c("DownloadServiceProtocol", "new registerCallBack mCallbackRegisted = " + this.j);
        } catch (RemoteException e2) {
            abd.b("DownloadServiceProtocol", " register callback Exception." + e2.getMessage());
        }
    }

    private void h() {
        abd.c("DownloadServiceProtocol", "all serviceConnection list size = " + this.i.size());
        if (this.i.isEmpty()) {
            abd.a("DownloadServiceProtocol", "unbindAllConnection->connection list is empty");
            return;
        }
        abd.c("DownloadServiceProtocol", "unbindAllConnection");
        for (ServiceConnection serviceConnection : this.i) {
            if (serviceConnection != null) {
                abd.c("DownloadServiceProtocol", "unbindAllConnection item");
                Context context = this.c;
                if (context != null) {
                    context.unbindService(serviceConnection);
                } else {
                    abd.c("DownloadServiceProtocol", "unbindAllConnection mcontext is null");
                }
            } else {
                abd.a("DownloadServiceProtocol", "unbindAllConnection->connection item is null");
            }
        }
        this.g = false;
        this.f154a = null;
        this.i.clear();
    }

    private void i() {
        if (!this.j || this.f154a == null) {
            return;
        }
        try {
            abd.c("DownloadServiceProtocol", "unregisterSingleCallback");
            this.f154a.unregisterSingleCallback(this.f, this.b, this.h);
        } catch (RemoteException unused) {
            abd.b("DownloadServiceProtocol", "unregisterSingleCallback error");
        }
        this.j = false;
    }

    private void b(IServiceProtocol iServiceProtocol) {
        if (d()) {
            c(iServiceProtocol);
        } else {
            d(iServiceProtocol);
        }
    }

    private boolean d() {
        IRequireDownService iRequireDownService = this.f154a;
        boolean z = iRequireDownService != null && iRequireDownService.asBinder().isBinderAlive();
        abd.c("DownloadServiceProtocol", "isServiceConnected = " + z);
        return z;
    }

    public String b() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IServiceProtocol iServiceProtocol) {
        try {
            iServiceProtocol.execute();
        } catch (TransactionTooLargeException e2) {
            abd.d("DownloadServiceProtocol", "TransactionTooLargeException: " + e2.getMessage());
            iServiceProtocol.handleTranDataTooLarge();
        } catch (Exception e3) {
            abd.b("DownloadServiceProtocol", "onCloudServiceConnected bindCloudService error: Exception: " + e3.getMessage());
            new Timer().schedule(new a(), 1000L);
        }
    }

    private void e() {
        abd.a("DownloadServiceProtocol", "doBindService");
        try {
            Intent intent = new Intent();
            intent.setAction("com.huawei.android.hicloud.sync.requireDownlogicservice");
            intent.setClassName("com.huawei.hidisk", "com.huawei.android.hicloud.sync.service.aidl.RequireDownLogicService");
            this.g = this.c.bindService(intent, this.d, 1);
        } catch (Exception e2) {
            abd.b("DownloadServiceProtocol", "doBindService exception:" + e2.getMessage());
            b(this.k, "doBindService exception");
        }
    }

    private void d(IServiceProtocol iServiceProtocol) {
        abd.c("DownloadServiceProtocol", "bindCloudService");
        c cVar = new c(iServiceProtocol);
        this.d = cVar;
        this.i.add(cVar);
        e();
    }

    public void b(String str, String str2, List<UnstructData> list, String str3) {
        StringBuilder sb = new StringBuilder("pauseDownloadFile dataType = ");
        sb.append(str2);
        sb.append(", sessionId = ");
        sb.append(str3);
        sb.append(", files = ");
        sb.append(list == null ? "0" : Integer.valueOf(list.size()));
        abd.c("DownloadServiceProtocol", sb.toString());
        b(new d(str, str2, list, str3));
    }

    public void c() {
        abd.c("DownloadServiceProtocol", "doUnbindService, mIsBind: " + this.g);
        try {
            if (this.g) {
                i();
                h();
            }
        } catch (Exception e2) {
            abd.b("DownloadServiceProtocol", "doUnbindService failed , e = " + e2.toString());
        }
    }

    private JSONArray a(List<UnstructData> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                UnstructData unstructData = list.get(i);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", unstructData.getId());
                jSONObject.put("unstruct_uuid", unstructData.getUnstruct_uuid());
                jSONObject.put("name", unstructData.getName());
                jSONObject.put("hash", unstructData.getHash());
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    public void d(String str, String str2, List<UnstructData> list, String str3) {
        StringBuilder sb = new StringBuilder("startDownloadFile dataType = ");
        sb.append(str2);
        sb.append(", sessionId = ");
        sb.append(str3);
        sb.append(", files = ");
        sb.append(list == null ? "0" : Integer.valueOf(list.size()));
        abd.c("DownloadServiceProtocol", sb.toString());
        b(new e(str, str2, list, str3));
    }

    public void e(String str, String str2, List<UnstructData> list, boolean z, boolean z2, String str3) {
        StringBuilder sb = new StringBuilder("downLoadFile dataType = ");
        sb.append(str2);
        sb.append(", needProgress = ");
        sb.append(z);
        sb.append(", forceDownload = ");
        sb.append(z2);
        sb.append(", sessionId = ");
        sb.append(str3);
        sb.append(", files = ");
        sb.append(list == null ? "0" : Integer.valueOf(list.size()));
        abd.c("DownloadServiceProtocol", sb.toString());
        b(new b(str, str2, list, z, z2, str3));
    }

    public void a(String str, String str2, List<UnstructData> list, String str3) {
        StringBuilder sb = new StringBuilder("cancelDownloadFile dataType = ");
        sb.append(str2);
        sb.append(", sessionId = ");
        sb.append(str3);
        sb.append(", files = ");
        sb.append(list == null ? "0" : Integer.valueOf(list.size()));
        abd.c("DownloadServiceProtocol", sb.toString());
        b(new f(str, str2, list, str3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] c(List<UnstructData> list) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("download_file", a(list));
        return jSONObject.toString().getBytes("UTF-8");
    }

    public void b(String str, String str2) {
        try {
            abd.c("DownloadServiceProtocol", "sendDownloadErrorMessage sessionId = " + str + ", errorMsg = " + str2);
            Bundle bundle = new Bundle();
            bundle.putString("session_id", str);
            bundle.putString("syncType", this.b);
            bundle.putString("dataType", this.e);
            bundle.putString("callbackUuid", this.h);
            bundle.putString("errorMsg", str2);
            this.f.handlerEventMsg(10023, 0, 0, bundle);
        } catch (RemoteException e2) {
            abd.b("DownloadServiceProtocol", "sendDownloadErrorMessage error: " + e2.getMessage());
        }
    }

    public void b(String str) {
        try {
            abd.c("DownloadServiceProtocol", "sendFinishMessage sessionId = " + str);
            Bundle bundle = new Bundle();
            bundle.putString("session_id", str);
            bundle.putString("syncType", this.b);
            bundle.putString("dataType", this.e);
            bundle.putString("callbackUuid", this.h);
            this.f.handlerEventMsg(10024, 0, 0, bundle);
        } catch (RemoteException e2) {
            abd.b("DownloadServiceProtocol", "sendFinishMessage error: " + e2.getMessage());
        }
    }
}
