package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.device.activity.autoscandevice.BtAutoScanActivity;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class nuo {
    private static nuo d;
    private oae h;
    private Context i;
    private iyr j;
    private ExtendHandler n;
    private static final Object c = new Object();
    private static ExecutorService b = Executors.newFixedThreadPool(1);
    private ArrayList<BluetoothDevice> e = new ArrayList<>(16);

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<BluetoothDevice> f15505a = new ArrayList<>(16);
    private HashMap<String, Integer> k = new HashMap<>(0);
    private HashMap<String, String> o = new HashMap<>(16);
    private DownloadResultCallBack g = new DownloadResultCallBack() { // from class: nuo.3
        @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack
        public void setDownloadStatus(int i, int i2) {
            LogUtil.a("BtAutoScanManager", "setDownloadStatus status: ", Integer.valueOf(i));
            if (i == 0) {
                return;
            }
            if (i == 1) {
                nuo.this.n.sendEmptyMessage(1);
            }
            ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).onDownloadIndexAllDestroy();
        }
    };
    private BtDeviceDiscoverCallback f = new BtDeviceDiscoverCallback() { // from class: nuo.4
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr) {
            if (Math.abs(i) < 65 && bluetoothDeviceNode != null) {
                try {
                    BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
                    if (btDevice.getName() != null) {
                        LogUtil.a("BtAutoScanManager", "scan rssi name: ", btDevice.getName(), "rssi: ", Integer.valueOf(i));
                    }
                    if (bArr != null) {
                        if (nuo.this.h.a(cvx.d(bArr))) {
                            nuo.this.cPp_(btDevice, bArr);
                        } else {
                            LogUtil.h("BtAutoScanManager", "scan not in device: ", btDevice.getName());
                            return;
                        }
                    }
                    LogUtil.a("BtAutoScanManager", "scan in device: ", btDevice.getName());
                    nuo.this.cPo_(btDevice, i);
                } catch (SecurityException e) {
                    LogUtil.b("BtAutoScanManager", "onDeviceDiscovered SecurityException:", ExceptionUtils.d(e));
                }
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
            LogUtil.a("BtAutoScanManager", "autoScan onDeviceDiscoveryFinished");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
            LogUtil.a("BtAutoScanManager", "autoScan onDeviceDiscoveryCanceled");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i, String str) {
            LogUtil.a("BtAutoScanManager", "autoScan onFailure");
        }
    };

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            LogUtil.a("BtAutoScanManager", "handleMessage msg.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                nuo.this.d();
                return true;
            }
            if (i == 3) {
                nuo.this.j.b();
                nuo.this.n.removeMessages(1);
                nuo.this.f();
                LogUtil.a("BtAutoScanManager", "autoScan List after mBtScanDeviceList is ", nuo.this.f15505a);
                if (nuo.this.f15505a.size() > 0) {
                    nuo.this.a();
                }
                return true;
            }
            if (i == 4) {
                nuo.this.j.b();
                nuo.this.n.removeMessages(1);
                return true;
            }
            if (i == 5) {
                nuo.this.c(20);
                return true;
            }
            LogUtil.c("BtAutoScanManager", "handleMessage default");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cPp_(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (TextUtils.isEmpty(bluetoothDevice.getName()) || this.o.containsKey(bluetoothDevice.getName())) {
            return;
        }
        String b2 = this.h.b(cvx.d(bArr));
        LogUtil.a("BtAutoScanManager", "deviceName:", bluetoothDevice.getName(), "getDeviceProductId:", b2);
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        this.o.put(bluetoothDevice.getName(), b2);
    }

    private nuo(Context context) {
        this.i = null;
        this.n = null;
        if (context == null) {
            LogUtil.h("0xA0200008", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtAutoScanManager", "init BtAutoScanManager with context is null.");
            return;
        }
        this.i = context;
        this.j = iyr.c(context);
        this.h = oae.c(context);
        this.n = HandlerCenter.yt_(new c(), "BtAutoScanManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ArrayList<BluetoothDevice> arrayList = this.e;
        if (arrayList == null || arrayList.isEmpty()) {
            LogUtil.h("BtAutoScanManager", "autoScan List mBtScanDeviceList is null or size is zero.");
            return;
        }
        for (int i = 0; i < this.e.size(); i++) {
            try {
                BluetoothDevice bluetoothDevice = this.e.get(i);
                if (bluetoothDevice != null && bluetoothDevice.getName() != null && c(bluetoothDevice.getName())) {
                    this.f15505a.add(bluetoothDevice);
                }
            } catch (SecurityException e) {
                LogUtil.b("BtAutoScanManager", "setLastDeviceList SecurityException:", ExceptionUtils.d(e));
                return;
            }
        }
    }

    public static nuo e() {
        nuo nuoVar;
        synchronized (c) {
            if (d == null) {
                LogUtil.h(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtAutoScanManager", "mBtAutoScanManagerInstance is null.");
                d = new nuo(BaseApplication.getContext());
            }
            nuoVar = d;
        }
        return nuoVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("BtAutoScanManager", "Enter scanBleDevice.");
        this.j.d(this.f);
        this.n.sendEmptyMessage(3, 5000L);
    }

    public void c() {
        if (LoginInit.getInstance(this.i).isBrowseMode()) {
            LogUtil.h("BtAutoScanManager", "isBrowseMode not allow pair");
            sqo.a("user is not login");
            return;
        }
        boolean b2 = new GuideInteractors(this.i).b();
        LogUtil.a("BtAutoScanManager", "startAutoScan isChecked: ", Boolean.valueOf(b2));
        if (b2) {
            sqo.a("user close auto scan");
            return;
        }
        LogUtil.a("BtAutoScanManager", "startAutoScan isHuaweiSystem: ", Boolean.valueOf(CommonUtil.bh()));
        ArrayList<BluetoothDevice> arrayList = this.e;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<BluetoothDevice> arrayList2 = this.f15505a;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        int d2 = this.j.d();
        LogUtil.a("BtAutoScanManager", "startAutoScan btSwitchState: ", Integer.valueOf(d2));
        if (d2 != 3) {
            return;
        }
        if (koq.b(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList())) {
            if (!CommonUtil.aa(BaseApplication.getContext())) {
                LogUtil.h("BtAutoScanManager", "handleNeedDownloadCase net is error");
                return;
            } else {
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).addDownloadIndexAllCallBack(this.g);
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexAll();
                return;
            }
        }
        this.n.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cPo_(BluetoothDevice bluetoothDevice, int i) {
        boolean z;
        synchronized (this) {
            try {
                Iterator<BluetoothDevice> it = this.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    BluetoothDevice next = it.next();
                    if (next != null && next.getAddress() != null && next.getAddress().equals(bluetoothDevice.getAddress())) {
                        z = true;
                        break;
                    }
                }
                LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BtAutoScanManager", "addDeviceToList isRepeat: ", Boolean.valueOf(z));
            } catch (SecurityException e) {
                LogUtil.b("BtAutoScanManager", "addDeviceToList SecurityException:", ExceptionUtils.d(e));
            }
            if (z) {
                return;
            }
            String name = bluetoothDevice.getName();
            boolean b2 = nun.b(name, jfu.e());
            LogUtil.a("BtAutoScanManager", "addDeviceToList isFilterName: ", Boolean.valueOf(b2));
            if (!b2) {
                b2 = nun.b(name, b());
            }
            boolean c2 = c(name);
            LogUtil.a("BtAutoScanManager", "addDeviceToList deviceName: ", name, ";isFilterName: ", Boolean.valueOf(b2), ";isDeviceInList: ", Boolean.valueOf(c2));
            if (b2 && c2) {
                this.k.put(bluetoothDevice.getName(), Integer.valueOf(i));
                this.e.add(bluetoothDevice);
            }
        }
    }

    private List<String> b() {
        ArrayList arrayList = new ArrayList(16);
        try {
            ArrayList<cve> arrayList2 = new ArrayList(16);
            arrayList2.addAll(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList());
            for (cve cveVar : arrayList2) {
                if (cveVar.d() == 0 && cveVar.r() != null && cveVar.r().startsWith(PutDataRequest.WEAR_URI_SCHEME)) {
                    arrayList.addAll(cveVar.e());
                }
            }
            return arrayList;
        } catch (ConcurrentModificationException unused) {
            LogUtil.b("BtAutoScanManager", "getFilterName, ConcurrentModificationException");
            return arrayList;
        }
    }

    public String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BtAutoScanManager", "deviceName is null");
            return "";
        }
        String str2 = this.o.get(str);
        LogUtil.a("BtAutoScanManager", "getProductIdFromMap productId:", str2);
        return str2;
    }

    private boolean c(String str) {
        LogUtil.a("BtAutoScanManager", "isDeviceInList deviceName: ", str);
        for (String str2 : this.h.b()) {
            LogUtil.a("BtAutoScanManager", "isDeviceInList currentUsedDeviceName: ", str2);
            if (str2 != null && str2.equalsIgnoreCase(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("BtAutoScanManager", "Enter intentScanListActivity mBtScanDeviceShowList: ", this.f15505a);
        if (!com.huawei.haf.application.BaseApplication.j()) {
            LogUtil.h("BtAutoScanManager", "intentScanListActivity health app is not running foreground");
            return;
        }
        LogUtil.a("BtAutoScanManager", "MainActivity BaseApplication.isRunningForeground: ", Boolean.valueOf(BaseApplication.isRunningForeground()));
        if (BaseApplication.isRunningForeground()) {
            boolean h = CommonUtil.h(this.i, "com.huawei.health.MainActivity");
            LogUtil.a("BtAutoScanManager", "MainActivity isForeground is ", Boolean.valueOf(h));
            if (!h || !nun.b()) {
                LogUtil.h("BtAutoScanManager", "cardDeviceFragment is not foreground");
                return;
            }
            DeviceInfo d2 = jpt.d("BtAutoScanManager");
            if (d2 != null && HwVersionManager.c(BaseApplication.getContext()).o(d2.getDeviceIdentify())) {
                LogUtil.a("BtAutoScanManager", "wear device is OTAing");
                return;
            }
            try {
                LogUtil.h("BtAutoScanManager", "AW70 device is OTAing else");
                Intent intent = new Intent(this.i, (Class<?>) BtAutoScanActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("bluetooth_list", this.f15505a);
                intent.putExtra(TemplateStyleRecord.STYLE, 1);
                intent.putExtra("device_rssi_map", this.k);
                this.i.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("BtAutoScanManager", "activityNotFoundException");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        LogUtil.a("BtAutoScanManager", "Enter downloadInfo deviceType: ", Integer.valueOf(i));
        ExecutorService executorService = b;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        b.execute(new Runnable() { // from class: nuo.5
            @Override // java.lang.Runnable
            public void run() {
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByDeviceType(jfu.h(i) ? "SMART_BAND" : "SMART_WATCH");
            }
        });
    }
}
