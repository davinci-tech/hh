package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.multisimsdk.attacheddevicemanager.common.IAttachedDeviceManagerCallback;
import com.huawei.multisimsdk.attacheddevicemanager.common.IServiceChangedListener;
import com.huawei.multisimservice.IAttachedDeviceMultiSim;
import com.huawei.multisimservice.IServiceBinder;
import com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class lna {
    private static final HashMap<lnd, c> e = new HashMap<>();
    private static final ArrayList<lnd> b = new ArrayList<>();
    private static final ArrayList<IServiceChangedListener> d = new ArrayList<>();

    /* renamed from: a, reason: collision with root package name */
    private static int f14774a = 1;
    private static final Object c = new Object();
    private Context h = null;
    private HandlerThread j = null;
    private a k = null;
    private IServiceBinder g = null;
    private IAttachedDeviceMultiSim o = null;
    private String m = null;
    private boolean n = false;
    private IAttachedDeviceMultiSimCallback.Stub f = new IAttachedDeviceMultiSimCallback.Stub() { // from class: lna.2
        @Override // com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback
        public void getAttachedDeviceMultiSimInfo(MultiSimDeviceInfo multiSimDeviceInfo) throws RemoteException {
            loq.c("AttachedDeviceManager", "Wear Service start to callback manager, getAttachDeviceMultiSimInfo.");
            Bundle bundle = new Bundle();
            if (multiSimDeviceInfo != null) {
                bundle.putParcelable("deviceSimInfo", multiSimDeviceInfo);
                loq.d("AttachedDeviceManager", "Get attached device result code: " + multiSimDeviceInfo.getResultCode());
            } else {
                loq.b("AttachedDeviceManager", "Wear service support device sim info with unknown type.");
                MultiSimDeviceInfo multiSimDeviceInfo2 = new MultiSimDeviceInfo();
                multiSimDeviceInfo2.setResultCode(-8);
                bundle.putParcelable("deviceSimInfo", multiSimDeviceInfo2);
            }
            lna lnaVar = lna.this;
            lnaVar.bXI_(lnaVar.k, 7, null, bundle);
        }

        @Override // com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback
        public void downloadESimProfile(int i, List<SimInfo> list) throws RemoteException {
            loq.c("AttachedDeviceManager", "WearService start to callback AttachedDeviceManager, downloadESimProfile.");
            if (loq.b.booleanValue()) {
                loq.c("AttachedDeviceManager", "Download result code=" + i + ", profileInfoList=" + list);
            }
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", i);
            lna lnaVar = lna.this;
            lnaVar.bXI_(lnaVar.k, 5, list, bundle);
        }

        @Override // com.huawei.multisimservice.model.IAttachedDeviceMultiSimCallback
        public void deleteESimProfileNotify(int i) throws RemoteException {
            loq.c("AttachedDeviceManager", "WearService start to callback AttachedDeviceManager, deleteESimProfileNotify.");
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", i);
            lna lnaVar = lna.this;
            lnaVar.bXI_(lnaVar.k, 1, null, bundle);
        }
    };
    private ServiceConnection l = new ServiceConnection() { // from class: lna.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            loq.c("AttachedDeviceManager", "Service is connected");
            if (!lna.this.bXE_(componentName)) {
                loq.b("AttachedDeviceManager", "Check service identity failed, unbind service.");
                lna.this.f();
                return;
            }
            lna.this.g = IServiceBinder.Stub.asInterface(iBinder);
            try {
                iBinder.linkToDeath(lna.this.i, 0);
                if (lna.this.g != null) {
                    lna lnaVar = lna.this;
                    lnaVar.o = IAttachedDeviceMultiSim.Stub.asInterface(lnaVar.g.getServiceBinder("com.huawei.hwmultisim"));
                }
                if (lna.this.o != null) {
                    lna.this.o.registerCallback(lna.this.f);
                    int unused = lna.f14774a = 0;
                } else {
                    loq.b("AttachedDeviceManager", "Service is null");
                }
            } catch (RemoteException unused2) {
                int unused3 = lna.f14774a = 1;
                loq.b("AttachedDeviceManager", "ServiceConnected RemoteException occurred.");
            }
            lna.this.e(lna.f14774a);
            loq.d("AttachedDeviceManager", "MultiSimService connected status is: " + lna.f14774a);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            loq.c("AttachedDeviceManager", "MultiSim Service disconnected.");
            if (lna.this.o != null) {
                try {
                    lna.this.o.unRegisterCallback(lna.this.f);
                } catch (RemoteException unused) {
                    loq.b("AttachedDeviceManager", "UnRegisterCallback failed, remote exception occurred.");
                }
            } else {
                loq.b("AttachedDeviceManager", "Service is null");
            }
            lna.this.o = null;
            int unused2 = lna.f14774a = 1;
            lna.this.e(lna.f14774a);
        }
    };
    private IBinder.DeathRecipient i = new IBinder.DeathRecipient() { // from class: lna.5
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            int unused = lna.f14774a = 1;
            loq.b("AttachedDeviceManager", "MultiSimService binderDied.");
            if (lna.this.g != null) {
                lna.this.g.asBinder().unlinkToDeath(lna.this.i, 0);
                lna.this.o = null;
                lna.this.d();
            } else {
                loq.b("AttachedDeviceManager", "Binder is null.");
                int unused2 = lna.f14774a = 2;
                lna.this.e(lna.f14774a);
            }
        }
    };

    static class b {
        public static final lna d = new lna();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bXI_(Handler handler, int i, Object obj, Bundle bundle) {
        if (handler == null) {
            loq.b("AttachedDeviceManager", "Send message failed, handler is null.");
            return;
        }
        Looper looper = handler.getLooper();
        if (looper == null) {
            loq.b("AttachedDeviceManager", "Send message failed, looper is null.");
            return;
        }
        Thread thread = looper.getThread();
        if (thread == null) {
            loq.b("AttachedDeviceManager", "Send message failed, thread is null.");
            return;
        }
        if (!thread.isAlive()) {
            loq.b("AttachedDeviceManager", "Send message failed, the state of the thread is not alive.");
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.setData(bundle);
        if (obj != null) {
            obtainMessage.obj = obj;
        }
        handler.sendMessage(obtainMessage);
    }

    public static lna e() {
        lna lnaVar;
        synchronized (c) {
            lnaVar = b.d;
        }
        return lnaVar;
    }

    public int a(Context context) {
        loq.c("AttachedDeviceManager", "AttachedDeviceManager init");
        if (context == null) {
            loq.b("AttachedDeviceManager", "AttachedDeviceManager init failed, context is null.");
            return -1;
        }
        this.h = context.getApplicationContext();
        this.m = context.getPackageName();
        if (this.j == null) {
            HandlerThread handlerThread = new HandlerThread("AttachedDeviceManager");
            this.j = handlerThread;
            handlerThread.start();
        }
        if (this.k == null) {
            this.k = new a(this.j.getLooper());
        }
        return d();
    }

    public void h() {
        loq.c("AttachedDeviceManager", "AttachedDeviceManager is finished");
        f();
        this.h = null;
        this.m = null;
        this.k = null;
        HandlerThread handlerThread = this.j;
        if (handlerThread != null) {
            handlerThread.quit();
            this.j = null;
        }
        HashMap<lnd, c> hashMap = e;
        synchronized (hashMap) {
            hashMap.clear();
        }
        ArrayList<lnd> arrayList = b;
        synchronized (arrayList) {
            arrayList.clear();
        }
        ArrayList<IServiceChangedListener> arrayList2 = d;
        synchronized (arrayList2) {
            arrayList2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bXG_(Message message) {
        HashMap hashMap;
        if (message == null) {
            loq.b("AttachedDeviceManager", "Handle download profile callback failed, msg is null");
            return;
        }
        Bundle data = message.getData();
        ArrayList arrayList = (ArrayList) message.obj;
        HashMap<lnd, c> hashMap2 = e;
        synchronized (hashMap2) {
            hashMap = (HashMap) hashMap2.clone();
        }
        if (hashMap == null) {
            loq.d("AttachedDeviceManager", "Handle download profile callback failed, CommonCallbacks is null.");
            return;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            c cVar = (c) entry.getValue();
            if (2 == ((lnd) entry.getKey()).e()) {
                loq.d("AttachedDeviceManager", "Handle download profile callback, MSG_DOWNLOAD_PROFILE: get attached device multi-sim info.");
                bXI_(cVar, 5, arrayList, data);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bXF_(Message message) {
        HashMap hashMap;
        if (message == null) {
            loq.b("AttachedDeviceManager", "Handle delete profile callback, msg is null");
            return;
        }
        Bundle data = message.getData();
        HashMap<lnd, c> hashMap2 = e;
        synchronized (hashMap2) {
            hashMap = (HashMap) hashMap2.clone();
        }
        if (hashMap == null) {
            loq.d("AttachedDeviceManager", "Handle delete profile callback, CommonCallbacks is null.");
            return;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            c cVar = (c) entry.getValue();
            if (3 == ((lnd) entry.getKey()).e()) {
                loq.d("AttachedDeviceManager", "Handle delete profile callback, MSG_DELETE_PROFILE_CALLBACK: get attached device multi-sim info.");
                bXI_(cVar, 1, null, data);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bXH_(Message message) {
        HashMap hashMap;
        if (message == null) {
            loq.b("AttachedDeviceManager", "Handle get attached device multi-sim info failed, msg is null");
            return;
        }
        Bundle data = message.getData();
        HashMap<lnd, c> hashMap2 = e;
        synchronized (hashMap2) {
            hashMap = (HashMap) hashMap2.clone();
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            c cVar = (c) entry.getValue();
            if (((lnd) entry.getKey()).e() == 0) {
                loq.d("AttachedDeviceManager", "Handle get attached device multi-sim info, MSG_GET_DEVICE_INFO_CALLBACK: get attached device multi-sim info.");
                bXI_(cVar, 7, null, data);
            } else if (1 == ((lnd) entry.getKey()).e()) {
                loq.d("AttachedDeviceManager", "Handle get attached device multi-sim info, MSG_IS_NEED_DOWNLOAD_PROFILE_CALLBACK: whether need download profile.");
                bXI_(cVar, 3, null, data);
            }
        }
    }

    class a extends Handler {
        private a(Looper looper) {
            super(looper);
        }

        private void a(int i) {
            Message obtainMessage = obtainMessage();
            if (i == 0) {
                obtainMessage.what = 9;
                obtainMessage.arg1 = i;
                sendMessageDelayed(obtainMessage, 35000L);
            } else if (i == 4) {
                obtainMessage.what = 9;
                obtainMessage.arg1 = i;
                sendMessageDelayed(obtainMessage, 35000L);
            } else {
                if (i == 6) {
                    obtainMessage.what = 9;
                    obtainMessage.arg1 = i;
                    sendMessageDelayed(obtainMessage, 35000L);
                    return;
                }
                loq.b("AttachedDeviceManager", "Can not find type to send over time message.");
            }
        }

        private void d(int i) {
            loq.c("AttachedDeviceManager", "AttachedDeviceMgrHandler start to handle over time." + i + ", 6, intTime" + lna.this.n);
            if (i == 0) {
                c();
                return;
            }
            if (i == 4) {
                b();
            } else if (i != 6) {
                loq.b("AttachedDeviceManager", "Handle Over Time ERROR.");
            } else {
                e();
            }
        }

        private void e() {
            if (lna.this.n) {
                return;
            }
            try {
                MultiSimDeviceInfo multiSimDeviceInfo = new MultiSimDeviceInfo();
                multiSimDeviceInfo.setResultCode(-4);
                lna.this.f.getAttachedDeviceMultiSimInfo(multiSimDeviceInfo);
            } catch (RemoteException unused) {
                loq.b("AttachedDeviceManager", "Handle get multisim device info, remote exception occurred.");
            }
        }

        private void b() {
            try {
                lna.this.f.downloadESimProfile(-4, new ArrayList());
            } catch (RemoteException unused) {
                loq.b("AttachedDeviceManager", "Handle download profile overtime, remote exception occurred.");
            }
        }

        private void c() {
            try {
                lna.this.f.deleteESimProfileNotify(-4);
            } catch (RemoteException unused) {
                loq.b("AttachedDeviceManager", "Handle delete profile over time, remote exception has occurred.");
            }
        }

        private void a() {
            lna.this.n = false;
            if (!lna.this.k.hasMessages(6)) {
                a(6);
            }
            if (lna.this.o != null) {
                try {
                    lna.this.o.getAttachedDeviceMultiSimInfo();
                    return;
                } catch (RemoteException unused) {
                    loq.b("AttachedDeviceManager", "Handle get multi-sim device info failed, remote exception has occurred.");
                    return;
                }
            }
            loq.b("AttachedDeviceManager", "Handle get multi-sim device info failed, mService is null");
        }

        private void a(String str) {
            if (!lna.this.k.hasMessages(4)) {
                a(4);
            }
            if (lna.this.o != null) {
                try {
                    lna.this.o.downloadESimProfile(str);
                    return;
                } catch (RemoteException unused) {
                    loq.b("AttachedDeviceManager", "Handle download profile failed, remote exception has occurred.");
                    return;
                }
            }
            loq.b("AttachedDeviceManager", "Handle download profile failed, mService is null");
        }

        private void a(ArrayList<SimInfo> arrayList) {
            if (!lna.this.k.hasMessages(0)) {
                a(0);
            }
            if (lna.this.o != null) {
                try {
                    lna.this.o.deleteESimProfile(arrayList);
                    return;
                } catch (RemoteException unused) {
                    loq.b("AttachedDeviceManager", "Handle delete profile failed, remote exception has occurred.");
                    return;
                }
            }
            loq.b("AttachedDeviceManager", "Handle delete profile failed, mService is null");
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            loq.c("AttachedDeviceManager", "Handle message msg.what: " + message.what);
            Bundle data = message.getData();
            if (data == null) {
                loq.b("AttachedDeviceManager", "Handle message error, bundle is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                try {
                    ArrayList<SimInfo> parcelableArrayList = data.getParcelableArrayList("profileInfoList");
                    if (loq.b.booleanValue()) {
                        loq.b("AttachedDeviceManager", "Delete profile info list: " + parcelableArrayList);
                    }
                    a(parcelableArrayList);
                    return;
                } catch (IndexOutOfBoundsException unused) {
                    loq.b("AttachedDeviceManager", "profileInfoList IndexOutOfBoundsException");
                    return;
                }
            }
            if (i == 1) {
                lna.this.bXF_(message);
                return;
            }
            if (i == 4) {
                a(data.getString("activationCode"));
                return;
            }
            if (i == 5) {
                lna.this.bXG_(message);
                return;
            }
            if (i == 6) {
                a();
                return;
            }
            if (i != 7) {
                if (i != 9) {
                    return;
                }
                d(message.arg1);
            } else {
                lna.this.n = true;
                removeMessages(9);
                lna.this.bXH_(message);
            }
        }
    }

    class c extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            lnb bXJ_;
            int i;
            loq.c("AttachedDeviceManager", "AttachedDeviceMgrCallbackHandler handle message msg.what: " + message.what);
            Bundle data = message.getData();
            Object obj = message.obj;
            ArrayList<SimInfo> arrayList = new ArrayList<>();
            int i2 = message.what;
            if (i2 != 1) {
                i = 0;
                if (i2 == 5) {
                    if (obj instanceof ArrayList) {
                        ArrayList<SimInfo> arrayList2 = (ArrayList) obj;
                        if (arrayList2.size() > 0 && (arrayList2.get(0) instanceof SimInfo)) {
                            arrayList = arrayList2;
                        }
                    }
                    loq.d("AttachedDeviceManager", "Manager handle MSG, download profile callback.");
                    bXJ_ = bXK_(data, arrayList);
                    i = 2;
                } else if (i2 == 7) {
                    loq.d("AttachedDeviceManager", "Manager handle MSG, get device sim info callback.");
                    bXJ_ = bXL_(data);
                } else {
                    loq.b("AttachedDeviceManager", "AttachedDeviceMgrCallbackHandler handle message error occurred.");
                    return;
                }
            } else {
                loq.d("AttachedDeviceManager", "Manager handle MSG, delete profile callback.");
                bXJ_ = bXJ_(data);
                i = 3;
            }
            loq.d("AttachedDeviceManager", "AttachedDeviceMgrCallbackHandler type = " + i);
            synchronized (lna.e) {
                loq.c("AttachedDeviceManager", "start to do onInfoBack, return the result to the caller.");
                for (Object obj2 : lna.e.keySet()) {
                    if (obj2 instanceof lnd) {
                        lnd lndVar = (lnd) obj2;
                        if (i == lndVar.e()) {
                            a(lndVar, bXJ_);
                        }
                    }
                }
                loq.c("AttachedDeviceManager", "handle complete, remove callback.");
                synchronized (lna.b) {
                    Iterator it = lna.b.iterator();
                    while (it.hasNext()) {
                        lnd lndVar2 = (lnd) it.next();
                        if (lna.e.containsKey(lndVar2)) {
                            lna.e.remove(lndVar2);
                        }
                    }
                    lna.b.clear();
                }
            }
        }

        private void a(lnd lndVar, lnb lnbVar) {
            loq.c("AttachedDeviceManager", "Start to excute doCallback() function");
            if (lndVar == null || lnbVar == null) {
                loq.b("AttachedDeviceManager", "doCallback() excute failed, callback or commonResult is null.");
                return;
            }
            IAttachedDeviceManagerCallback d = lndVar.d();
            if (d == null) {
                loq.b("AttachedDeviceManager", "doCallback() excute failed, attachedDeviceManagerCallback is null.");
                return;
            }
            d.onInfoBack(lndVar.e(), lnbVar);
            synchronized (lna.b) {
                lna.b.add(lndVar);
            }
        }

        private lnb bXL_(Bundle bundle) {
            loq.c("AttachedDeviceManager", "Start to get the commonResult for get device sim info");
            if (bundle == null) {
                loq.b("AttachedDeviceManager", "GetCommonResultForGetAttachedDeviceSimInfo failed, bundle is null, RESULT_CODE_ERROR");
                bundle = new Bundle();
            }
            Parcelable parcelable = bundle.getParcelable("deviceSimInfo");
            if (parcelable instanceof MultiSimDeviceInfo) {
                return new lnb(0, (MultiSimDeviceInfo) parcelable);
            }
            loq.b("AttachedDeviceManager", "GetCommonResultForGetAttachedDeviceSimInfo error, can not change to MultiSimDeviceInfo.");
            MultiSimDeviceInfo multiSimDeviceInfo = new MultiSimDeviceInfo();
            multiSimDeviceInfo.setResultCode(-8);
            return new lnb(0, multiSimDeviceInfo);
        }

        private lnb bXK_(Bundle bundle, ArrayList<SimInfo> arrayList) {
            loq.c("AttachedDeviceManager", "Get commonResult for download profile");
            if (bundle == null) {
                loq.b("AttachedDeviceManager", "GetCommonResultForDownloadProfile failed, bundle is null, RESULT_CODE_ERROR");
                bundle = new Bundle();
            }
            return new lnb(2, bundle.getInt("resultCode"), arrayList);
        }

        private lnb bXJ_(Bundle bundle) {
            loq.c("AttachedDeviceManager", "Get commonResult for delete profile");
            if (bundle == null) {
                loq.b("AttachedDeviceManager", "GetCommonResultForDeleteProfile failed, bundle is null, RESULT_CODE_ERROR");
                bundle = new Bundle();
            }
            return new lnb(3, bundle.getInt("resultCode"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ArrayList arrayList;
        loq.d("AttachedDeviceManager", "Notify service connected status changed, service status = " + i);
        ArrayList<IServiceChangedListener> arrayList2 = d;
        synchronized (arrayList2) {
            arrayList = (ArrayList) arrayList2.clone();
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            IServiceChangedListener iServiceChangedListener = (IServiceChangedListener) arrayList.get(i2);
            if (iServiceChangedListener != null) {
                iServiceChangedListener.onStatusChanged(i);
            }
        }
    }

    public int d() {
        loq.c("AttachedDeviceManager", "Attempt to bindService: " + f14774a);
        if (this.h == null) {
            loq.b("AttachedDeviceManager", "Attempt to bindService failed, context is null, can't bind service.");
            return -1;
        }
        if (f14774a != 0) {
            loq.d("AttachedDeviceManager", "Service is not connected yet, re-bind service.");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.huawei.health", "com.huawei.multisimservice.MultiSimService"));
            try {
                this.h.bindService(intent, this.l, 1);
            } catch (SecurityException unused) {
                loq.b("AttachedDeviceManager", "Bind service exception occurred.");
                return -1;
            }
        }
        loq.d("AttachedDeviceManager", "Bind service succeed.");
        return 0;
    }

    public int f() {
        loq.c("AttachedDeviceManager", "Unbind service: " + f14774a);
        Context context = this.h;
        if (context == null) {
            loq.b("AttachedDeviceManager", "Unbind service failed, can't unbind service.");
            return -1;
        }
        if (f14774a == 0) {
            try {
                context.unbindService(this.l);
                this.o = null;
                f14774a = 1;
            } catch (IllegalArgumentException unused) {
                loq.b("AttachedDeviceManager", "Unbind service, IllegalArgumentException");
                return -1;
            }
        }
        e(f14774a);
        loq.d("AttachedDeviceManager", "Unbind service succeed.");
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bXE_(ComponentName componentName) {
        if (componentName == null) {
            loq.b("AttachedDeviceManager", "Check service identity failed, component name is null.");
            return false;
        }
        String packageName = componentName.getPackageName();
        boolean equals = "com.huawei.hwmultisim".equals(this.m);
        boolean equals2 = "com.huawei.bone".equals(packageName);
        boolean equals3 = "com.huawei.health".equals(packageName);
        loq.d("AttachedDeviceManager", "SdkInHwMultiSim = " + equals + ", sdkPkgName = " + this.m + ", servicePkgName = " + packageName);
        if (!equals) {
            return d(packageName);
        }
        if (!equals2 && !equals3) {
            loq.g("AttachedDeviceManager", "Check service identity failed: service Package name is not the same with hw wear app");
            return false;
        }
        Context context = this.h;
        if (context == null) {
            loq.b("AttachedDeviceManager", "Check service identity failed: context is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        String string = this.h.getString(R.string._2130851318_res_0x7f0235f6);
        String string2 = this.h.getString(R.string._2130851317_res_0x7f0235f5);
        String b2 = loo.b("1310403;132b3b330313231313760115605033g353:1b;57;;5e43g32323636330353023a033:35306637352031707f02210323353066373;203:7446525f54575e5f54022103233530663734203:605;565f544b5;565f023e033g353066373b20357;465244565:022;0325353066373a203e6756415g5:5f525`705e5g43525f4:032f243g0207030002070301020203016b243g0707030003050301020203016b0353023a033:35306637352031707f02210323353066373;203:7446525f54575e5f54022103233530663734203:605;565f544b5;565f023e033g353066373b20357;465244565:022;0325353066373a203e6756415g5:5f525`705e5g43525f4:03;2:e033g353:1b;57;;5e43g323232363330;2;g3303;2;:31;2;233;`eea3a0a6707342`7:f25f21g14410gb;5:236g3g75:03b1a153:5ag6e;:e72`a1g;a0e`a2g34a;;eb6g0ef655g671g5gb0516:e43725b37b`5ff7g3125;`07f2gb;4ff07f2:1e27422;fe7ff43f1;7fe5:6g701ab40e16ff4;1ee4::14a7031e14:4f:3:f4e320fg750`17b07g;a:65f76gf41f`eef0gb:b4a23554e2fgb`43130323332033g353:1b;57;;5e43g323236363330;2;2337;a`:33f31176;b`11;012:4g;febae;0g6g:4f3:6;2:efeefe5:45624b35g655;75fa;ba67a2:;e7:02:6:356f`:f3;7a03bg6b5b76a5gfggbb34`13bg3b4`g:a14361:04e3f:a0042:02e:e7::;5506f6;42;eg`g:7:72f3fa6;1g1;2`ae7bg7eg;`2a:;5`715:e11534g44:ff`e;f466567;:2e7a57456b:56f1067:53;6003;", string, string2);
        if (equals3) {
            b2 = loo.b("137a603;130:gb330313231313:33e13765ba:57eb3e3033g353:1b;57;;5e43g323236363303;2:;023a033:35306637352031707f02210323353066373;203:7446525f54575e5f54022103233530663734203:605;565f544b5;565f023e033g353066373b20357;465244565:022;0325353066373a203e6756415g5:5f525`705e5g43525f4:022703213530663730203a725f57415e5:576756525g0213032f353:1b;57;;5e43g323:3225225g5e515:5`56735;465244565:1f505e5g032f243g02020306010603050203070:6b243g00050306020;03050203070:6b03;2:;023a033:35306637352031707f02210323353066373;203:7446525f54575e5f54022103233530663734203:605;565f544b5;565f023e033g353066373b20357;465244565:022;0325353066373a203e6756415g5:5f525`705e5g43525f4:022703213530663730203a725f57415e5:576756525g0213032f353:1b;57;;5e43g323:3225225g5e515:5`56735;465244565:1f505e5g03;13213033g353:1b;57;;5e43g323232363330;1323g3303;1323;31;1323233f31`6ba:4b1a0b;b6::5110`gf35a;1a1g7ee6a26`be56a;53g6`4b0g5;::6ba3;513aa46b11ef4540b;b2bab30f24a562g2e`7g6`agabf:f40fffbe6b2g7g1ea40f4333102f3ga1255g3e`6gg:4f436eg55675`:gb0;fg7feb1```gg10;bg01f0:;12171a32:6ge32g:a:4171gae13:fgb;f775f370177a;7f5ae`b4:g4aa0`2:17`gg17;fgag533fee;e40332b;:b7`550ga;:43f01;;a:702617`052f;60a;eb1:f37f52faf5eagag;4`gag0ffa74a314a6;62agfbb20b10e70:54b303f474fb701561`aa07eggf5237:ae6353`;20ea3f:0e5abg:g05e7g76622:6fb0aa7:f:132bb5ge:46bf25:f127:36gf164:g4``0`0fb`76:7a27b`2:g4f0:`6`154313230b0;1323303;2eg032g3530662g3f372537270f`5:3gf;22a`0:f62b03032bg10`e0335g54gf303;2`g3530662g1037;2`603;2`1;3270f`5:3gf;22a`0:f62b03032bg10`e0335g54gf3b2;2:fb7;2:a03;2:;023a033:35306637352031707f02210323353066373;203:7446525f54575e5f54022103233530663734203:605;565f544b5;565f023e033g353066373b20357;465244565:022;0325353066373a203e6756415g5:5f525`705e5g43525f4:022703213530663730203a725f57415e5:576756525g0213032f353:1b;57;;5e43g323:3225225g5e515:5`56735;465244565:1f505e5g;13:33e13765ba:57eb3e3033`3530662g20373603303232ee033g353:1b;57;;5e43g323236363330;13232335gbg1e`6:3`e334:b`7`f3;56b;;7022g`25bg;0146b1a3031736445gg2g2e437b1:::0`;b207b;gegg67`6:262b35266521gag033575fb`e`:e57eb`1f06143:a306:e465be:5`2e657:6037f411;4af74:;b3700065`a``gg;g64`gf2e4ee2`337`a:51bg6:6a2gb11fe137gf277:3;;52aab:5;f3`470;2f22:`441a1`e11`0g3`;016b:e02`:4;4a265`:7442be55e7eea2bg::5`0eaf457e0a`63a4b1570`10041521e163::75b30abgeaefb52e;f0g1;:100;6aa`33;46b`f`::a5g:1b`75;e3g`63`3::4ee73:f0b16g`;g:55`13g6;;b:420ee124f1gf7:764b01`e10ge2be6;e7a`gg:7b;e15a`2`:3`6b62;0ga57g75060gg752374;`44e1`;::;;03;", string, string2);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 64);
            if (packageInfo.signatures == null) {
                loq.g("AttachedDeviceManager", "check service identity failed: signature is null");
                return false;
            }
            for (Signature signature : packageInfo.signatures) {
                if (signature != null && signature.toCharsString().equals(b2)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            loq.b("AttachedDeviceManager", "Get package info exception: NameNotFoundException.");
            return false;
        }
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(this.m)) {
            loq.b("AttachedDeviceManager", "isSameSoftware mSdkPackageName is null");
            return false;
        }
        boolean equals = this.m.equals(str);
        loq.c("AttachedDeviceManager", "isSameSoftware sdkServiceInSameApp：" + equals + ",mSdkPackageName：" + this.m + ",servicePkgName：" + str);
        return equals;
    }
}
