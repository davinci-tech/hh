package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.service.IServiceProtocol;
import com.huawei.android.hicloud.sync.service.aidl.CtagInfoCompatible;
import com.huawei.android.hicloud.sync.service.aidl.ISyncService;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.LocalId;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.SyncDataCompatible;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructDataV107;
import com.huawei.common.Constant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class aau {
    private final String b;
    private final Context c;
    private ServiceConnection d;
    private ISyncService e;
    private int f;
    private final SyncProcessInterface g;

    /* renamed from: a, reason: collision with root package name */
    private boolean f131a = false;
    private boolean i = false;
    private boolean j = true;
    private boolean h = false;
    private List<ServiceConnection> o = new ArrayList();

    class a extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f132a;
        final /* synthetic */ List b;
        final /* synthetic */ List c;
        final /* synthetic */ String d;
        final /* synthetic */ List e;
        final /* synthetic */ String g;
        final /* synthetic */ boolean h;

        a(List list, List list2, List list3, List list4, String str, String str2, boolean z) {
            this.e = list;
            this.c = list2;
            this.f132a = list3;
            this.b = list4;
            this.d = str;
            this.g = str2;
            this.h = z;
        }

        private void d(boolean z) {
            abd.c("SyncServiceProtocol", "uploadDataV102 resolveTooLarge");
            try {
                a(aau.this.c((List<SyncData>) this.e, (List<SyncData>) this.c, (List<UnstructData>) this.f132a, (List<String>) this.b), abl.e(z));
                aau.this.e.uploadDataV102ForTransTooLarge(this.d, this.g, new byte[0], this.h, true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "uploadDataV102 resolveTooLarge error: " + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "uploadDataV102 resolveTooLarge error: " + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "uploadDataV102 resolveTooLarge error: JSONException");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "uploadDataV102");
            try {
                abd.c("SyncServiceProtocol", "uploadDataV102 parcelData size = " + aau.this.c((List<SyncData>) this.e, (List<SyncData>) this.c, (List<UnstructData>) this.f132a, (List<String>) this.b).length);
                if (r0.length >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    d(true);
                } else {
                    aau.this.e.uploadDataV102(this.d, this.g, this.e, this.c, this.f132a, this.b, this.h);
                }
            } catch (Exception e) {
                abd.c("SyncServiceProtocol", "uploadDataV102 Exception :" + e.getMessage());
                aau.this.e.uploadDataV102(this.d, this.g, this.e, this.c, this.f132a, this.b, this.h);
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.uploadDataV102ForTransTooLarge(this.d, this.g, bArr, this.h, false);
            } else {
                abd.b("SyncServiceProtocol", "uploadDataV102 syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            abd.c("SyncServiceProtocol", "uploadDataV102 handleTranDataTooLarge");
            d(false);
        }
    }

    class b extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f133a;
        final /* synthetic */ ISyncServiceCallback b;
        final /* synthetic */ List c;
        final /* synthetic */ List d;
        final /* synthetic */ List e;

        b(ISyncServiceCallback iSyncServiceCallback, String str, List list, List list2, List list3) {
            this.b = iSyncServiceCallback;
            this.f133a = str;
            this.d = list;
            this.c = list2;
            this.e = list3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "endSyncV100");
            aau.e(this.b, 300000L, "endSyncV100");
            aau.this.e.endSyncV100(this.f133a, this.d, this.c, this.e);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.endSyncForTransTooLarge(this.f133a, bArr, false);
            } else {
                abd.b("SyncServiceProtocol", "endSyncV100 syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aau.this.a((List<String>) this.d, (List<String>) this.c, (List<String>) this.e));
                aau.this.e.endSyncForTransTooLarge(this.f133a, new byte[0], true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "endSyncV100 error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "endSyncV100 error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "endSyncV100 error : JSONException");
            }
        }
    }

    class c extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f134a;
        final /* synthetic */ List b;
        final /* synthetic */ String c;
        final /* synthetic */ boolean d;
        final /* synthetic */ String e;
        final /* synthetic */ List i;

        c(String str, String str2, boolean z, String str3, List list, List list2) {
            this.e = str;
            this.f134a = str2;
            this.d = z;
            this.c = str3;
            this.b = list;
            this.i = list2;
        }

        private void b(boolean z) {
            abd.c("SyncServiceProtocol", "saveSyncResult resolveTooLarge");
            try {
                a(aau.this.d((List<SyncData>) this.b, (List<String>) this.i), abl.e(z));
                aau.this.e.saveSyncResultForTransTooLarge(this.e, this.f134a, new byte[0], this.d, true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "saveSyncResult resolveTooLarge error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "saveSyncResult resolveTooLarge error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "saveSyncResult resolveTooLarge error : JSONException");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "saveSyncResult, syncType = " + this.e + ", dataType = " + this.f134a + ", isUpload = " + this.d + ", extraMsg = " + this.c);
            if (aav.a(aau.this.c)) {
                aau.this.e.notifyMsg(this.e, this.f134a, this.c);
            }
            try {
                abd.c("SyncServiceProtocol", "saveSyncResult parcelData size = " + aau.this.d((List<SyncData>) this.b, (List<String>) this.i).length);
                if (r0.length >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    b(true);
                } else {
                    aau.this.e.saveSyncResult(this.e, this.f134a, this.b, this.i, this.d);
                }
            } catch (Exception e) {
                abd.c("SyncServiceProtocol", "saveSyncResult Exception :" + e.getMessage());
                aau.this.e.saveSyncResult(this.e, this.f134a, this.b, this.i, this.d);
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.saveSyncResultForTransTooLarge(this.e, this.f134a, bArr, this.d, false);
            } else {
                abd.b("SyncServiceProtocol", "syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            abd.c("SyncServiceProtocol", "saveSyncResult handleTranDataTooLarge");
            b(false);
        }
    }

    class d extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f135a;
        final /* synthetic */ List b;
        final /* synthetic */ List c;
        final /* synthetic */ List d;
        final /* synthetic */ String e;
        final /* synthetic */ String g;
        final /* synthetic */ boolean j;

        d(List list, List list2, List list3, List list4, String str, String str2, boolean z) {
            this.c = list;
            this.b = list2;
            this.f135a = list3;
            this.d = list4;
            this.e = str;
            this.g = str2;
            this.j = z;
        }

        private void a(boolean z) {
            abd.c("SyncServiceProtocol", "uploadDataV104 resolveTooLarge");
            try {
                a(aau.this.a((List<SyncDataCompatible>) this.c, (List<SyncDataCompatible>) this.b, (List<UnstructData>) this.f135a, (List<String>) this.d), abl.e(z));
                aau.this.e.uploadDataV102ForTransTooLarge(this.e, this.g, new byte[0], this.j, true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "uploadDataV104 resolveTooLarge error: " + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "uploadDataV104 resolveTooLarge error: " + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "uploadDataV104 resolveTooLarge error: JSONException");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "uploadDataV104");
            try {
                abd.c("SyncServiceProtocol", "uploadDataV104 parcelData size = " + aau.this.a((List<SyncDataCompatible>) this.c, (List<SyncDataCompatible>) this.b, (List<UnstructData>) this.f135a, (List<String>) this.d).length);
                if (r0.length >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    a(true);
                } else {
                    aau.this.e.uploadDataV104(this.e, this.g, this.c, this.b, this.f135a, this.d, this.j);
                }
            } catch (Exception e) {
                abd.c("SyncServiceProtocol", "uploadDataV104 Exception :" + e.getMessage());
                aau.this.e.uploadDataV104(this.e, this.g, this.c, this.b, this.f135a, this.d, this.j);
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.uploadDataV102ForTransTooLarge(this.e, this.g, bArr, this.j, false);
            } else {
                abd.b("SyncServiceProtocol", "uploadDataV104 syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            abd.c("SyncServiceProtocol", "uploadDataV104 handleTranDataTooLarge");
            a(false);
        }
    }

    class e extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f136a;
        final /* synthetic */ String b;
        final /* synthetic */ ISyncServiceCallback c;
        final /* synthetic */ List e;

        e(ISyncServiceCallback iSyncServiceCallback, String str, List list, List list2) {
            this.c = iSyncServiceCallback;
            this.b = str;
            this.e = list;
            this.f136a = list2;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "endSync");
            aau.e(this.c, 300000L, "endSync");
            aau.this.e.endSync(this.b, this.e, this.f136a);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.endSyncForTransTooLarge(this.b, bArr, false);
            } else {
                abd.b("SyncServiceProtocol", "endSync syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aau.this.c((List<String>) this.e, (List<String>) this.f136a));
                aau.this.e.endSyncForTransTooLarge(this.b, new byte[0], true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "endSync error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "endSync error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "endSync error : JSONException");
            }
        }
    }

    class f implements IServiceProtocol {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f137a;
        final /* synthetic */ String c;

        f(String str, String str2) {
            this.f137a = str;
            this.c = str2;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "updateCtag");
            aau.this.e.updateCtag(this.f137a, this.c);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class g extends com.huawei.android.hicloud.sync.service.w {
        final /* synthetic */ String c;
        final /* synthetic */ List d;
        final /* synthetic */ String e;

        g(String str, String str2, List list) {
            this.c = str;
            this.e = str2;
            this.d = list;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "downUnstructFile");
            int a2 = aal.a();
            if (a2 == 0) {
                a2 = 2;
            }
            aau.this.e.reportSDKVersionCode(a2);
            aau.this.e.downUnstructFile(this.c, this.e, this.d);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.downUnstructFileForTransTooLarge(this.c, this.e, bArr, false);
            } else {
                abd.b("SyncServiceProtocol", "syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aau.this.c((List<UnstructData>) this.d).toString().getBytes("UTF-8"));
                aau.this.e.downUnstructFileForTransTooLarge(this.c, this.e, new byte[0], true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "downUnstructFile:" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "downUnstructFile:" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "downUnstructFile error : JSONException");
            }
        }
    }

    class h extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f139a;
        final /* synthetic */ ISyncServiceCallback b;
        final /* synthetic */ int c;
        final /* synthetic */ String d;
        final /* synthetic */ int e;
        final /* synthetic */ int g;

        h(ISyncServiceCallback iSyncServiceCallback, String str, String str2, int i, int i2, int i3) {
            this.b = iSyncServiceCallback;
            this.f139a = str;
            this.d = str2;
            this.e = i;
            this.c = i2;
            this.g = i3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.e(this.b, 300000L, "checkRisk");
            aau.this.e.checkRisk(this.f139a, this.d, this.e, this.c, this.g);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class i extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f140a;
        final /* synthetic */ String e;

        i(String str, String str2) {
            this.f140a = str;
            this.e = str2;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.this.e.reportInfo(this.f140a, this.e);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class j implements ServiceConnection {
        final /* synthetic */ ISyncServiceCallback b;
        final /* synthetic */ IServiceProtocol c;
        final /* synthetic */ boolean e;

        class a extends TimerTask {
            a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                aau.this.b(Constant.SERVICE_CONNECT_MESSAGE);
            }
        }

        j(IServiceProtocol iServiceProtocol, boolean z, ISyncServiceCallback iSyncServiceCallback) {
            this.c = iServiceProtocol;
            this.e = z;
            this.b = iSyncServiceCallback;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            abd.c("SyncServiceProtocol", "service connected");
            if (aau.this.e == null) {
                abd.c("SyncServiceProtocol", "bindCloudService onServiceConnected new syncService");
                aau.this.e = ISyncService.Stub.asInterface(iBinder);
            }
            aau.this.h = true;
            try {
                aau.this.e(this.c, this.e, this.b);
            } catch (RemoteException unused) {
                abd.b("SyncServiceProtocol", "onServiceConnected error : RemoteException");
                new Timer().schedule(new a(), 1000L);
            } catch (Exception e) {
                abd.b("SyncServiceProtocol", "bindCloudService onServiceConnected error: Exception: " + e.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            abd.c("SyncServiceProtocol", "cloud service disconnected isNeedEndSync = " + aau.this.j);
            aau.this.f131a = false;
            aau.this.h = false;
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("resultCode", 0);
                this.b.handlerEventMsg(10013, 0, 0, bundle);
            } catch (RemoteException e) {
                abd.b("SyncServiceProtocol", "cloud service disconnected RemoteException = " + e.toString());
            }
            aau.this.b("onServiceDisconnected");
        }
    }

    class k extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f141a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String e;

        k(String str, String str2, String str3, String str4) {
            this.c = str;
            this.b = str2;
            this.e = str3;
            this.f141a = str4;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.this.e.reportSyncRsn(this.c, this.b, this.e, this.f141a);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class l extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f142a;
        final /* synthetic */ List b;
        final /* synthetic */ List c;
        final /* synthetic */ List d;
        final /* synthetic */ String e;
        final /* synthetic */ String g;
        final /* synthetic */ boolean h;

        l(List list, List list2, List list3, List list4, String str, String str2, boolean z) {
            this.c = list;
            this.b = list2;
            this.d = list3;
            this.f142a = list4;
            this.e = str;
            this.g = str2;
            this.h = z;
        }

        private void b(boolean z) {
            abd.c("SyncServiceProtocol", "uploadDataWithLost resolveTooLarge");
            try {
                a(aau.this.d((List<SyncDataCompatible>) this.c, (List<SyncDataCompatible>) this.b, (List<String>) this.d, (List<SyncDataCompatible>) this.f142a), abl.e(z));
                aau.this.e.uploadDataWithLostForTransTooLarge(this.e, this.g, new byte[0], this.h, true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "uploadDataWithLost resolveTooLarge error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "uploadDataWithLost resolveTooLarge error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "uploadDataWithLost resolveTooLarge error : JSONException");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "uploadDataWithLost execute");
            try {
                abd.c("SyncServiceProtocol", "uploadDataWithLost parcelData size = " + aau.this.d((List<SyncDataCompatible>) this.c, (List<SyncDataCompatible>) this.b, (List<String>) this.d, (List<SyncDataCompatible>) this.f142a).length);
                if (r0.length >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    b(true);
                } else {
                    aau.this.e.uploadDataWithLost(this.e, this.g, this.c, this.b, this.d, this.f142a, this.h);
                }
            } catch (Exception e) {
                abd.c("SyncServiceProtocol", "uploadDataWithLost Exception :" + e.getMessage());
                aau.this.e.uploadDataWithLost(this.e, this.g, this.c, this.b, this.d, this.f142a, this.h);
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.uploadDataWithLostForTransTooLarge(this.e, this.g, bArr, this.h, false);
            } else {
                abd.b("SyncServiceProtocol", "uploadDataWithLost : syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            abd.c("SyncServiceProtocol", "uploadDataWithLost handleTranDataTooLarge");
            b(false);
        }
    }

    class m extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f143a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        m(String str, String str2, String str3, String str4, String str5) {
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f143a = str4;
            this.b = str5;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.this.e.reportSyncInfo(this.c, this.d, this.e, this.f143a, this.b);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class n extends com.huawei.android.hicloud.sync.service.w {
        final /* synthetic */ String b;
        final /* synthetic */ List c;
        final /* synthetic */ String d;
        final /* synthetic */ ISyncServiceCallback e;

        n(ISyncServiceCallback iSyncServiceCallback, String str, String str2, List list) {
            this.e = iSyncServiceCallback;
            this.d = str;
            this.b = str2;
            this.c = list;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.e(this.e, 300000L, "getStructData");
            aau.this.e.getStructData(this.d, this.b, this.c);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.getStructDataForTransTooLarge(this.d, this.b, bArr, false);
            } else {
                abd.b("SyncServiceProtocol", "syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(this.c.toString().getBytes("UTF-8"));
                aau.this.e.getStructDataForTransTooLarge(this.d, this.b, new byte[0], true);
            } catch (RemoteException | UnsupportedEncodingException e) {
                abd.b("SyncServiceProtocol", "getStructData:" + e.toString());
            }
        }
    }

    class o extends com.huawei.android.hicloud.sync.service.w {
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        o(String str, String str2) {
            this.e = str;
            this.d = str2;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.this.e.getSyncLostList(this.e, this.d);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class p extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f145a;
        final /* synthetic */ String b;
        final /* synthetic */ ISyncServiceCallback c;
        final /* synthetic */ String d;
        final /* synthetic */ int e;
        final /* synthetic */ List j;

        p(int i, int i2, ISyncServiceCallback iSyncServiceCallback, String str, String str2, List list) {
            this.e = i;
            this.f145a = i2;
            this.c = iSyncServiceCallback;
            this.d = str;
            this.b = str2;
            this.j = list;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            int a2 = aal.a();
            if (a2 == 0) {
                a2 = 2;
            }
            aau.this.e.reportSDKVersionCode(a2);
            if (aal.d() == 0) {
                aal.a(aau.this.e.getHisyncVersionCode());
            }
            if (this.e != 1 || this.f145a != 1) {
                aau.e(this.c, 300000L, "startSync");
            }
            abd.c("SyncServiceProtocol", "startSync");
            aau.this.e.startSync(this.d, this.b, this.j, this.e);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.startSyncForTransTooLarge(this.d, this.b, bArr, this.e, false);
            } else {
                abd.b("SyncServiceProtocol", "syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            try {
                a(aau.this.a((List<LocalId>) this.j).toString().getBytes("UTF-8"));
                aau.this.e.startSyncForTransTooLarge(this.d, this.b, new byte[0], this.e, true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "startSync error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "startSync error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "startSync error : JSONException");
            }
        }
    }

    class q extends com.huawei.android.hicloud.sync.service.w {
        final /* synthetic */ ISyncServiceCallback e;

        q(ISyncServiceCallback iSyncServiceCallback) {
            this.e = iSyncServiceCallback;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "getHisyncNewVersion");
            aau.e(this.e, 300000L, "getHicloudNewVersion");
            aau aauVar = aau.this;
            aauVar.f = aauVar.e.getHisyncNewVersion();
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", 0);
            bundle.putInt("hicloud_new_version", aau.this.f);
            this.e.handlerEventMsg(10011, 0, 0, bundle);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class r extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ISyncServiceCallback f147a;

        r(ISyncServiceCallback iSyncServiceCallback) {
            this.f147a = iSyncServiceCallback;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "getHiCloudOldVersion");
            aau.e(this.f147a, 300000L, "getHicloudOldVersion");
            int hisyncVersionCode = aau.this.e.getHisyncVersionCode();
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", 0);
            bundle.putInt("hicloud_old_version", hisyncVersionCode);
            this.f147a.handlerEventMsg(10010, 0, 0, bundle);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class s extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f148a;
        final /* synthetic */ ISyncServiceCallback b;
        final /* synthetic */ int c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;
        final /* synthetic */ String g;

        s(int i, int i2, ISyncServiceCallback iSyncServiceCallback, String str, String str2, String str3) {
            this.f148a = i;
            this.c = i2;
            this.b = iSyncServiceCallback;
            this.e = str;
            this.d = str2;
            this.g = str3;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            if (this.f148a != 1 || this.c != 1) {
                aau.e(this.b, 300000L, "startSyncV100");
            }
            aau.this.e.reportSDKVersionCode(aal.a());
            abd.c("SyncServiceProtocol", "startSyncV100");
            aau.this.e.startSyncV100(this.e, this.d, this.g, this.f148a);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class t extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f149a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ ISyncServiceCallback d;
        final /* synthetic */ List e;
        final /* synthetic */ String g;

        t(int i, int i2, ISyncServiceCallback iSyncServiceCallback, String str, List list, String str2) {
            this.b = i;
            this.c = i2;
            this.d = iSyncServiceCallback;
            this.f149a = str;
            this.e = list;
            this.g = str2;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            aau.this.e.reportSDKVersionCode(aal.a());
            if (this.b != 1 || this.c != 1) {
                aau.e(this.d, 300000L, "startSyncV101");
            }
            abd.c("SyncServiceProtocol", "startSyncV101, dataType: " + this.f149a + ", local ctagInfo size = " + this.e.size());
            aau.this.e.startSyncV101(this.g, this.f149a, this.e, this.b);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class u extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f150a;
        final /* synthetic */ List b;
        final /* synthetic */ String c;
        final /* synthetic */ List d;
        final /* synthetic */ List e;
        final /* synthetic */ boolean f;

        u(List list, List list2, List list3, String str, String str2, boolean z) {
            this.e = list;
            this.b = list2;
            this.d = list3;
            this.f150a = str;
            this.c = str2;
            this.f = z;
        }

        private void b(boolean z) {
            abd.c("SyncServiceProtocol", "uploadData resolveTooLarge");
            try {
                a(aau.this.c((List<SyncData>) this.e, (List<SyncData>) this.b, (List<String>) this.d), abl.e(z));
                aau.this.e.uploadDataForTransTooLarge(this.f150a, this.c, new byte[0], this.f, true);
            } catch (RemoteException e) {
                e = e;
                abd.b("SyncServiceProtocol", "uploadData resolveTooLarge error :" + e.toString());
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                abd.b("SyncServiceProtocol", "uploadData resolveTooLarge error :" + e.toString());
            } catch (JSONException unused) {
                abd.b("SyncServiceProtocol", "uploadData resolveTooLarge error : JSONException");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "uploadData");
            try {
                abd.c("SyncServiceProtocol", "uploadData parcelData size = " + aau.this.c((List<SyncData>) this.e, (List<SyncData>) this.b, (List<String>) this.d).length);
                if (r0.length >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    b(true);
                } else {
                    aau.this.e.uploadData(this.f150a, this.c, this.e, this.b, this.d, this.f);
                }
            } catch (Exception e) {
                abd.c("SyncServiceProtocol", "uploadData Exception :" + e.getMessage());
                aau.this.e.uploadData(this.f150a, this.c, this.e, this.b, this.d, this.f);
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
            if (aau.this.e != null) {
                aau.this.e.uploadDataForTransTooLarge(this.f150a, this.c, bArr, this.f, false);
            } else {
                abd.b("SyncServiceProtocol", "syncService is null");
            }
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
            abd.c("SyncServiceProtocol", "uploadData handleTranDataTooLarge");
            b(false);
        }
    }

    class v extends com.huawei.android.hicloud.sync.service.w {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f151a;
        final /* synthetic */ ISyncServiceCallback c;
        final /* synthetic */ String d;
        final /* synthetic */ int e;

        v(String str, int i, ISyncServiceCallback iSyncServiceCallback, String str2) {
            this.d = str;
            this.e = i;
            this.c = iSyncServiceCallback;
            this.f151a = str2;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "notifySyncModel dataType = " + this.d + ", syncModel = " + this.e);
            aau.e(this.c, 300000L, "notifySyncModel");
            aau.this.e.notifySyncModel(this.f151a, this.d, this.e);
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", 0);
            this.c.handlerEventMsg(10022, 0, 0, bundle);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    class w extends com.huawei.android.hicloud.sync.service.w {
        final /* synthetic */ String b;
        final /* synthetic */ List d;

        w(String str, List list) {
            this.b = str;
            this.d = list;
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void execute() throws RemoteException {
            abd.c("SyncServiceProtocol", "getExceedLimitNum from server");
            aau.this.e.getExceedLimitNum(this.b, this.d);
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void executeBatches(byte[] bArr) throws RemoteException {
        }

        @Override // com.huawei.android.hicloud.sync.service.IServiceProtocol
        public void handleTranDataTooLarge() {
        }
    }

    public aau(Context context, String str, SyncProcessInterface syncProcessInterface) {
        this.c = context;
        this.b = str;
        this.g = syncProcessInterface;
    }

    private JSONArray d(List<UnstructDataV107> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                UnstructDataV107 unstructDataV107 = list.get(i2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", unstructDataV107.getId());
                jSONObject.put("unstruct_uuid", unstructDataV107.getUnstructUuid());
                jSONObject.put("name", unstructDataV107.getName());
                jSONObject.put("hash", unstructDataV107.getHash());
                jSONObject.put("version", unstructDataV107.getVersion());
                jSONObject.put("optType", unstructDataV107.getOptType());
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    private void a(IServiceProtocol iServiceProtocol, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "serviceConnection: isServiceConnected = " + this.h);
        if (this.h) {
            try {
                e(iServiceProtocol, z, iSyncServiceCallback);
                return;
            } catch (RemoteException e2) {
                abd.d("SyncServiceProtocol", "serviceConnection exception : " + e2.toString());
                d(iServiceProtocol, z, iSyncServiceCallback);
                return;
            } catch (Exception e3) {
                abd.b("SyncServiceProtocol", "serviceConnection error: Exception: " + e3.getMessage());
                return;
            }
        }
        d(iServiceProtocol, z, iSyncServiceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(IServiceProtocol iServiceProtocol, boolean z, ISyncServiceCallback iSyncServiceCallback) throws RemoteException {
        abd.c("SyncServiceProtocol", "onCloudServiceConnected: mCallbackRegistered = " + this.i);
        try {
            ISyncService iSyncService = this.e;
            if (iSyncService != null) {
                if (!this.i) {
                    this.i = iSyncService.registerCallback(iSyncServiceCallback, this.b);
                }
                abd.c("SyncServiceProtocol", "register callback, mCallbackRegistered:" + this.i);
                iServiceProtocol.execute();
            }
        } catch (TransactionTooLargeException e2) {
            abd.d("SyncServiceProtocol", "TransactionTooLargeException: " + e2.getMessage());
            iServiceProtocol.handleTranDataTooLarge();
        }
        if (z) {
            return;
        }
        e(iSyncServiceCallback);
    }

    public void b(ISyncServiceCallback iSyncServiceCallback) {
        synchronized (this) {
            a((IServiceProtocol) new r(iSyncServiceCallback), true, iSyncServiceCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray c(List<UnstructData> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                UnstructData unstructData = list.get(i2);
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

    private void d(IServiceProtocol iServiceProtocol, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "bindCloudService");
        j jVar = new j(iServiceProtocol, z, iSyncServiceCallback);
        this.d = jVar;
        this.o.add(jVar);
        a();
    }

    private void a() {
        abd.a("SyncServiceProtocol", "doBindService");
        try {
            Intent intent = new Intent();
            intent.setAction("com.huawei.android.hicloud.sync.synclogicservice");
            intent.setClassName("com.huawei.hidisk", "com.huawei.android.hicloud.sync.service.aidl.SyncLogicService");
            this.f131a = this.c.bindService(intent, this.d, 1);
        } catch (Exception e2) {
            abd.b("SyncServiceProtocol", "doBindService exception: Call app SyncEnd, " + e2.getMessage());
            if (this.g != null) {
                b("doBindService exception");
            }
        }
    }

    public void d(String str, String str2, List<String> list, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "Get struct data, type = " + str + ", dataType = " + str2 + ", list size = " + list.size());
        a((IServiceProtocol) new n(iSyncServiceCallback, str, str2, list), true, iSyncServiceCallback);
    }

    public void a(String str, String str2, List<CtagInfoCompatible> list, int i2, int i3, ISyncServiceCallback iSyncServiceCallback) {
        synchronized (this) {
            a((IServiceProtocol) new t(i2, i3, iSyncServiceCallback, str2, list, str), true, iSyncServiceCallback);
        }
    }

    public void a(ISyncServiceCallback iSyncServiceCallback) {
        synchronized (this) {
            a((IServiceProtocol) new q(iSyncServiceCallback), true, iSyncServiceCallback);
        }
    }

    public void a(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<String> list3, List<SyncDataCompatible> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "uploadDataWithLost");
        a((IServiceProtocol) new l(list, list2, list3, list4, str, str2, z), true, iSyncServiceCallback);
    }

    public void c(String str, String str2, List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<UnstructData> list3, List<String> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "uploadDataV104");
        a((IServiceProtocol) new d(list, list2, list3, list4, str, str2, z), true, iSyncServiceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] c(List<SyncData> list, List<SyncData> list2, List<String> list3) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("add", e(list));
        jSONObject.put("modify", e(list2));
        jSONObject.put("delete", list3.toString());
        return jSONObject.toString().getBytes("UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] d(List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<String> list3, List<SyncDataCompatible> list4) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("add", e(list));
        jSONObject.put("modify", e(list2));
        jSONObject.put("delete", list3.toString());
        jSONObject.put("lost", e(list4));
        return jSONObject.toString().getBytes("UTF-8");
    }

    public void e(ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "doUnbindService, mIsBound: " + this.f131a);
        try {
            if (this.f131a) {
                if (this.i && this.e != null) {
                    try {
                        abd.c("SyncServiceProtocol", "unregisterCallback");
                        this.e.unregisterCallback(iSyncServiceCallback, this.b);
                    } catch (RemoteException unused) {
                        abd.b("SyncServiceProtocol", "unregisterCallback error");
                    }
                    this.i = false;
                }
                abd.c("SyncServiceProtocol", "all serviceConnection list size = " + this.o.size());
                if (!this.o.isEmpty()) {
                    abd.c("SyncServiceProtocol", "doUnbindServiceList");
                    for (ServiceConnection serviceConnection : this.o) {
                        if (serviceConnection != null) {
                            abd.c("SyncServiceProtocol", "doUnbindService item");
                            Context context = this.c;
                            if (context != null) {
                                context.unbindService(serviceConnection);
                            } else {
                                abd.c("SyncServiceProtocol", "doUnbindServiceList mcontext is null");
                            }
                        } else {
                            abd.a("SyncServiceProtocol", "doUnbindServiceList->connection item is null");
                        }
                    }
                    this.j = false;
                    this.h = false;
                    this.f131a = false;
                    this.e = null;
                    this.o.clear();
                    return;
                }
                abd.a("SyncServiceProtocol", "doUnbindServiceList->connection list is empty");
            }
        } catch (Exception e2) {
            abd.b("SyncServiceProtocol", "doUnbindService failed , e = " + e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] a(List<SyncDataCompatible> list, List<SyncDataCompatible> list2, List<UnstructData> list3, List<String> list4) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("add", e(list));
        jSONObject.put("modify", e(list2));
        jSONObject.put("localFile", c(list3));
        jSONObject.put("delete", list4.toString());
        return jSONObject.toString().getBytes("UTF-8");
    }

    private JSONArray e(List<? extends SyncData> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (list == null) {
            return jSONArray;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            SyncData syncData = list.get(i2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("luid", syncData.getLuid());
            jSONObject.put("guid", syncData.getGuid());
            jSONObject.put("unstruct_uuid", syncData.getUnstruct_uuid());
            jSONObject.put("etag", syncData.getEtag());
            jSONObject.put("data", syncData.getData());
            jSONObject.put("uuid", syncData.getUuid());
            jSONObject.put("status", syncData.getStatus());
            jSONObject.put("hash", syncData.getHash());
            List<UnstructData> fileList = syncData.getFileList();
            List<UnstructData> downFileList = syncData.getDownFileList();
            List<UnstructData> deleteFileList = syncData.getDeleteFileList();
            JSONArray c2 = c(fileList);
            JSONArray c3 = c(downFileList);
            JSONArray c4 = c(deleteFileList);
            jSONObject.put("downFileList", c3);
            jSONObject.put("deleteFileList", c4);
            jSONObject.put("filelist", c2);
            if (this.f >= 104) {
                jSONObject.put("recycleStatus", syncData.getRecycleStatus());
                jSONObject.put("recycleTime", syncData.getRecycleTime());
                jSONObject.put("version", syncData.getVersion());
            }
            if (this.f >= 107) {
                jSONObject.put("extensionData", syncData.getExtensionData());
                jSONObject.put("extraParam", syncData.getExtraParam());
                List<UnstructDataV107> newFileList = syncData.getNewFileList();
                List<UnstructDataV107> newDownFileList = syncData.getNewDownFileList();
                List<UnstructDataV107> newDeleteFileList = syncData.getNewDeleteFileList();
                JSONArray d2 = d(newFileList);
                JSONArray d3 = d(newDownFileList);
                JSONArray d4 = d(newDeleteFileList);
                jSONObject.put("newDownFileList", d3);
                jSONObject.put("newDeleteFileList", d4);
                jSONObject.put("newFileList", d2);
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public void d(String str, String str2, List<LocalId> list, int i2, int i3, ISyncServiceCallback iSyncServiceCallback) {
        synchronized (this) {
            a((IServiceProtocol) new p(i2, i3, iSyncServiceCallback, str, str2, list), true, iSyncServiceCallback);
        }
    }

    public void d(String str, String str2, String str3, int i2, int i3, ISyncServiceCallback iSyncServiceCallback) {
        synchronized (this) {
            a((IServiceProtocol) new s(i2, i3, iSyncServiceCallback, str, str2, str3), true, iSyncServiceCallback);
        }
    }

    public void d(String str, String str2, int i2, ISyncServiceCallback iSyncServiceCallback) {
        synchronized (this) {
            a((IServiceProtocol) new v(str2, i2, iSyncServiceCallback, str), true, iSyncServiceCallback);
        }
    }

    public void b(String str, List<String> list, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "getExceedLimitNum");
        this.j = false;
        a((IServiceProtocol) new w(str, list), true, iSyncServiceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray a(List<LocalId> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                LocalId localId = list.get(i2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", localId.getId());
                jSONObject.put("dirty", localId.getDirty());
                jSONObject.put("havefile", localId.getHaveFile());
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    public void d(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "uploadData");
        a((IServiceProtocol) new u(list, list2, list3, str, str2, z), true, iSyncServiceCallback);
    }

    public void b(String str, String str2, List<SyncData> list, List<SyncData> list2, List<UnstructData> list3, List<String> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "uploadDataV102");
        a((IServiceProtocol) new a(list, list2, list3, list4, str, str2, z), true, iSyncServiceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] c(List<SyncData> list, List<SyncData> list2, List<UnstructData> list3, List<String> list4) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("add", e(list));
        jSONObject.put("modify", e(list2));
        jSONObject.put("localFile", c(list3));
        jSONObject.put("delete", list4.toString());
        return jSONObject.toString().getBytes("UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] d(List<SyncData> list, List<String> list2) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("saveData", e(list));
        if (list2 != null) {
            jSONObject.put("deleteList", list2.toString());
        }
        return jSONObject.toString().getBytes("UTF-8");
    }

    public void a(String str, String str2, List<SyncData> list, List<String> list2, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        c(this.b, str2, "", list, null, false, iSyncServiceCallback);
    }

    public void c(String str, String str2, String str3, List<SyncData> list, List<String> list2, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        a((IServiceProtocol) new c(str, str2, z, str3, list, list2), true, iSyncServiceCallback);
    }

    public void c(String str, List<String> list, List<String> list2, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "endSync, syncTpe = " + str + ", dataTypeList = " + list.toString() + ", dataTypeResultList = " + list2.toString());
        a((IServiceProtocol) new e(iSyncServiceCallback, str, list, list2), true, iSyncServiceCallback);
    }

    public void b(String str, String str2, ISyncServiceCallback iSyncServiceCallback) {
        a((IServiceProtocol) new f(str, str2), true, iSyncServiceCallback);
    }

    public void a(String str, List<String> list, List<String> list2, List<String> list3, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "endSyncV100, syncType = " + str + ", dataTypeList = " + list.toString() + ", dataTypeResultList = " + list2.toString());
        a((IServiceProtocol) new b(iSyncServiceCallback, str, list, list2, list3), true, iSyncServiceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] c(List<String> list, List<String> list2) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dataTypeList", list.toString());
        jSONObject.put("dataTypeResultList", list2.toString());
        return jSONObject.toString().getBytes("UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] a(List<String> list, List<String> list2, List<String> list3) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dataTypeList", list.toString());
        jSONObject.put("dataTypeResultList", list2.toString());
        jSONObject.put("dataTypeCtagList", list3.toString());
        return jSONObject.toString().getBytes("UTF-8");
    }

    public void e(String str, String str2, List<UnstructData> list, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "downUnstructFile");
        this.j = true;
        a((IServiceProtocol) new g(str, str2, list), true, iSyncServiceCallback);
    }

    public void a(String str, String str2, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "report: syncType = " + str + ", reportJson = " + str2);
        a(new i(str, str2), z, iSyncServiceCallback);
    }

    public void c(String str, String str2, int i2, int i3, int i4, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "checkRisk: syncType = " + str + ", dataType =" + str2 + ", addNum = " + i2 + ", delNum = " + i3 + ", updNum = " + i4);
        a((IServiceProtocol) new h(iSyncServiceCallback, str, str2, i2, i3, i4), true, iSyncServiceCallback);
    }

    public void c(String str, String str2, String str3, String str4, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "reportSyncRsn: syncType = " + str + ", packageName = " + str2 + ", versionName = " + str3 + ", reportJson = " + str4);
        a((IServiceProtocol) new k(str, str2, str3, str4), true, iSyncServiceCallback);
    }

    public void b(String str, String str2, String str3, String str4, String str5, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "reportSyncInfo syncType: " + str + " appId: " + str2 + " packageName: " + str3 + " versionName: " + str4);
        a((IServiceProtocol) new m(str, str2, str3, str4, str5), true, iSyncServiceCallback);
    }

    public static void e(ISyncServiceCallback iSyncServiceCallback, long j2, String str) {
        try {
            abd.c("SyncServiceProtocol", "sendTimeOutMessage");
            Bundle bundle = new Bundle();
            bundle.putLong("timeOut", j2);
            bundle.putString("timeOutMethod", str);
            iSyncServiceCallback.handlerEventMsg(PrebakedEffectId.RT_CALENDAR_DATE, 0, 0, bundle);
        } catch (RemoteException e2) {
            abd.b("SyncServiceProtocol", "sendTimeOutMessage error: " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (this.j) {
            abd.c("SyncServiceProtocol", str + ": Call app SyncEnd");
            abl.c(this.g);
            return;
        }
        abd.c("SyncServiceProtocol", str + ": no need EndSync");
        this.j = true;
    }

    public void c(String str, String str2, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("SyncServiceProtocol", "getSyncLostList: syncType = " + str + ", dataType = " + str2);
        a((IServiceProtocol) new o(str, str2), true, iSyncServiceCallback);
    }
}
