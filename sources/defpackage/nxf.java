package defpackage;

import android.app.Activity;
import android.app.AppOpsManager;
import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.pairing.HwBtDialogActivity;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class nxf {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f15539a = new Object();
    private static nxf b;
    private IAddDeviceStateAIDLCallback d;
    private int f;
    private DeviceParameter g;
    private Context h;
    private boolean k;
    private boolean l;
    private HwBtDialogActivity.DialogAidlCallback m;
    private int q;
    private List<String> t;
    private iyl c = null;
    private boolean o = false;
    private boolean n = false;
    private List<BluetoothDeviceNode> e = new ArrayList(16);
    private final BtSwitchStateCallback i = new BtSwitchStateCallback() { // from class: nxf.4
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            LogUtil.a("HwDialogUtil", "During add device then receive BT Switch state: ", Integer.valueOf(i));
            if (i == 1) {
                if (nxf.this.c != null) {
                    nxf.this.c.e(nxf.this.i);
                } else {
                    LogUtil.h("HwDialogUtil", "onBtSwitchStateCallback bluetooth is off, mBtDeviceMgrUtil is null.");
                }
                try {
                    if (nxf.this.d != null) {
                        nxf.this.d.onAddDeviceState(2);
                        return;
                    }
                    return;
                } catch (RemoteException unused) {
                    LogUtil.b("HwDialogUtil", "mBtSwitchStateByAddDeviceCallback RemoteException.");
                    return;
                }
            }
            if (i == 2) {
                LogUtil.a("HwDialogUtil", "onBtSwitchStateCallback bluetooth is turning off.");
                return;
            }
            if (i == 3) {
                if (nxf.this.c != null) {
                    nxf.this.c.e(nxf.this.i);
                }
                nxf.c(nxf.this.h).d("");
            } else if (i == 4) {
                LogUtil.a("HwDialogUtil", "onBtSwitchStateCallback bluetooth is turning on.");
            } else {
                LogUtil.h("HwDialogUtil", "onBtSwitchStateCallback btSwitchState is other.");
            }
        }
    };
    private BtDeviceDiscoverCallback j = new BtDeviceDiscoverCallback() { // from class: nxf.1
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr) {
            if (bluetoothDeviceNode != null) {
                bluetoothDeviceNode.setRssi(i);
                bluetoothDeviceNode.setTimeStamp(System.currentTimeMillis());
                if (nxf.this.f != 2 || bluetoothDeviceNode.getDeviceType() != 3) {
                    nxf.this.c(bluetoothDeviceNode);
                } else {
                    LogUtil.h("HwDialogUtil", "Dual Mode device no add.");
                }
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryFinished() {
            LogUtil.a("HwDialogUtil", "onDeviceDiscoveryFinished.");
            if (nxf.this.m != null) {
                nxf.this.m.onScanFinished();
            }
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onDeviceDiscoveryCanceled() {
            LogUtil.a("HwDialogUtil", "onDeviceDiscoveryCanceled.");
        }

        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtDeviceDiscoverCallback
        public void onFailure(int i, String str) {
            LogUtil.a("HwDialogUtil", "onFailure.");
        }
    };

    private nxf(Context context) {
        this.h = context;
    }

    public void e(iyl iylVar) {
        this.c = iylVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDeviceNode bluetoothDeviceNode) {
        HwBtDialogActivity.DialogAidlCallback dialogAidlCallback;
        synchronized (this) {
            String b2 = b(bluetoothDeviceNode);
            if (CommonUtil.as() && TextUtils.isEmpty(b2)) {
                LogUtil.h("HwDialogUtil", "addScanDeviceToList current version is beta, or deviceName is empty.");
                return;
            }
            if (!e(bluetoothDeviceNode, b2) && b(b2)) {
                int a2 = a(b2);
                synchronized (f15539a) {
                    this.e.add(a2, bluetoothDeviceNode);
                }
                if (Thread.currentThread() == Looper.getMainLooper().getThread() && (dialogAidlCallback = this.m) != null) {
                    dialogAidlCallback.onSetList(this.e, true, a2);
                    return;
                }
                n();
            }
        }
    }

    private int a(String str) {
        synchronized (f15539a) {
            int size = this.e.size();
            if (TextUtils.isEmpty(str)) {
                return size;
            }
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= size) {
                    i = i2;
                    break;
                }
                try {
                    BluetoothDevice btDevice = this.e.get(i).getBtDevice();
                    String name = btDevice != null ? btDevice.getName() : "";
                    if (TextUtils.isEmpty(name)) {
                        break;
                    }
                    if (!e(name) && e(str)) {
                        break;
                    }
                    i2 = i + 1;
                    i = i2;
                } catch (SecurityException e) {
                    LogUtil.b("HwDialogUtil", "getDeviceAddLocation SecurityException:", ExceptionUtils.d(e));
                }
            }
            i2 = i;
            return i2;
        }
    }

    private ArrayList<String> g() {
        ArrayList<String> arrayList = new ArrayList<>(16);
        if (CompileParameterUtil.a("IS_SUPPORT_NEW_ADD_MODE", false)) {
            arrayList.addAll(this.t);
            iyg.a(this.q, arrayList, this.n);
        }
        return arrayList;
    }

    private boolean b(String str) {
        ArrayList<String> g = g();
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwDialogUtil", "isAddScanDeviceToList deviceName is null and return true.");
            return true;
        }
        Iterator<String> it = g.iterator();
        boolean z2 = false;
        while (true) {
            if (!it.hasNext()) {
                z = z2;
                break;
            }
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                z2 = true;
            } else if (!CommonUtil.as() || str.contains(Constants.LINK)) {
                if (!str.toUpperCase(Locale.ENGLISH).contains(next.toUpperCase(Locale.ENGLISH))) {
                    continue;
                } else {
                    if (!this.l) {
                        break;
                    }
                    z2 = c(str);
                }
            }
        }
        LogUtil.a("HwDialogUtil", "filterScanDeviceName isExistDeviceList isAddDevice: ", Boolean.valueOf(z));
        return z;
    }

    private List<String> m() {
        if (!this.l) {
            return null;
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i : cux.b()) {
            arrayList.addAll(jfu.b(i));
        }
        return arrayList;
    }

    private boolean c(String str) {
        List<String> m = m();
        boolean z = false;
        if (m != null && !m.isEmpty()) {
            for (String str2 : m) {
                if (TextUtils.isEmpty(str2) || str.toUpperCase(Locale.ENGLISH).contains(str2.toUpperCase(Locale.ENGLISH))) {
                    z = true;
                }
            }
        }
        return z;
    }

    private boolean e(BluetoothDeviceNode bluetoothDeviceNode, String str) {
        boolean z;
        BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
        Iterator<BluetoothDeviceNode> it = this.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            BluetoothDeviceNode next = it.next();
            if (next != null && next.getBtDevice() != null && next.getBtDevice().getAddress() != null && btDevice != null && next.getBtDevice().getAddress().equals(btDevice.getAddress())) {
                c(next, str);
                z = true;
                break;
            }
        }
        LogUtil.a("HwDialogUtil", "addScanDeviceToList isExistFlag: ", Boolean.valueOf(z));
        return z;
    }

    private void c(BluetoothDeviceNode bluetoothDeviceNode, String str) {
        LogUtil.a("HwDialogUtil", "addDeviceToList, updateDeviceList");
        if (bluetoothDeviceNode.getBtDevice().getName() == null && TextUtils.isEmpty(bluetoothDeviceNode.getRecordName()) && str != null && CommonUtil.ar()) {
            if (!e(str)) {
                LogUtil.h("HwDialogUtil", "updateDeviceList device name is error.");
            } else {
                bluetoothDeviceNode.setRecordName(str);
                n();
            }
        }
    }

    private boolean e(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        Iterator<String> it = this.t.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                z = true;
            } else if (upperCase.contains(next.toUpperCase(Locale.ENGLISH))) {
                z = true;
                break;
            }
        }
        LogUtil.a("HwDialogUtil", "isSelectedDeviceName isSelectedDevice: ", Boolean.valueOf(z));
        return z;
    }

    private void n() {
        HwBtDialogActivity.DialogAidlCallback dialogAidlCallback = this.m;
        if (dialogAidlCallback != null) {
            dialogAidlCallback.onSetList(this.e, false, 0);
        }
    }

    private String b(BluetoothDeviceNode bluetoothDeviceNode) {
        String name;
        try {
            BluetoothDevice btDevice = bluetoothDeviceNode.getBtDevice();
            String str = "";
            if (!TextUtils.isEmpty(bluetoothDeviceNode.getNodeId())) {
                name = bluetoothDeviceNode.getDisplayName();
            } else {
                name = btDevice != null ? btDevice.getName() : "";
                if (TextUtils.isEmpty(name)) {
                    name = bluetoothDeviceNode.getRecordName();
                }
            }
            Object[] objArr = new Object[4];
            objArr[0] = "getScanDeviceName deviceName :";
            objArr[1] = name;
            objArr[2] = "，address :";
            if (btDevice != null) {
                str = CommonUtil.l(btDevice.getAddress());
            }
            objArr[3] = str;
            LogUtil.a("HwDialogUtil", objArr);
            return name;
        } catch (SecurityException e) {
            LogUtil.b("HwDialogUtil", "getScanDeviceName SecurityException:", ExceptionUtils.d(e));
            return bluetoothDeviceNode.getRecordName();
        }
    }

    public void d(DeviceParameter deviceParameter) {
        this.g = deviceParameter;
        if (deviceParameter != null) {
            this.f = deviceParameter.getBluetoothType();
            this.t = deviceParameter.getNameFilter();
            this.q = deviceParameter.getProductType();
            this.l = deviceParameter.isSupportHeartRate();
            boolean isBand = deviceParameter.isBand();
            this.n = isBand;
            LogUtil.a("HwDialogUtil", "registerParameter mIsBand: ", Boolean.valueOf(isBand));
        }
    }

    public static nxf c(Context context) {
        nxf nxfVar;
        synchronized (f15539a) {
            if (b == null) {
                LogUtil.h("HwDialogUtil", "sHwDialogUtil is null.");
                b = new nxf(context);
            }
            nxfVar = b;
        }
        return nxfVar;
    }

    public void b(IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) {
        this.d = iAddDeviceStateAIDLCallback;
    }

    public String a() {
        BluetoothDevice bDi_;
        LogUtil.a("HwDialogUtil", "Enter getHFPConnectedDeviceName().");
        try {
            iyl iylVar = this.c;
            if (iylVar != null && (bDi_ = iylVar.bDi_(this.t)) != null) {
                LogUtil.a("HwDialogUtil", "The wanted device name: ", bDi_.getName());
                return bDi_.getName();
            }
        } catch (SecurityException e) {
            LogUtil.b("HwDialogUtil", "getHfpConnectedDeviceName SecurityException:", ExceptionUtils.d(e));
        }
        return "";
    }

    public void b(boolean z) {
        LogUtil.a("HwDialogUtil", "Enter enableBTSwitch isEnable: ", Boolean.valueOf(z));
        if (z) {
            iyl iylVar = this.c;
            if (iylVar != null) {
                iylVar.bDe_(this.i, null);
                return;
            }
            return;
        }
        try {
            IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback = this.d;
            if (iAddDeviceStateAIDLCallback != null) {
                iAddDeviceStateAIDLCallback.onAddDeviceState(1);
            }
        } catch (RemoteException unused) {
            LogUtil.b("HwDialogUtil", "enableBtSwitch RemoteException");
        }
    }

    public void d() {
        BluetoothDevice bDi_;
        LogUtil.a("HwDialogUtil", "Enter connectHFPConnectedDevice().");
        iyl iylVar = this.c;
        if (iylVar == null || (bDi_ = iylVar.bDi_(this.t)) == null) {
            return;
        }
        try {
            if (bDi_.getName() != null) {
                String name = bDi_.getName();
                int c = jfu.c(name);
                int d = jfu.c(c).d();
                LogUtil.a("HwDialogUtil", "connectHfpConnectedDevice deviceName: ", name, ",ProductType: ", Integer.valueOf(c), ",blueToothType: ", Integer.valueOf(d));
                this.g.setProductType(c);
                this.g.setDeviceNameInfo(name);
                this.g.setBluetoothType(d);
                d(bDi_.getAddress());
            }
        } catch (SecurityException e) {
            LogUtil.b("HwDialogUtil", "connectHfpConnectedDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void b() {
        try {
            IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback = this.d;
            if (iAddDeviceStateAIDLCallback != null) {
                iAddDeviceStateAIDLCallback.onAddDeviceState(4);
            }
        } catch (RemoteException unused) {
            LogUtil.a("HwDialogUtil", "enableScan occur RemoteException.");
        }
    }

    public void a(HwBtDialogActivity.DialogAidlCallback dialogAidlCallback) {
        this.m = dialogAidlCallback;
    }

    public void f() {
        if (c() != 3) {
            LogUtil.h("HwDialogUtil", "startScanDevices bluetooth switch is not on.");
            return;
        }
        LogUtil.a("HwDialogUtil", "startScanDevices mBtType:", Integer.valueOf(this.f));
        synchronized (f15539a) {
            List<BluetoothDeviceNode> list = this.e;
            if (list != null) {
                list.clear();
            }
        }
        HwBtDialogActivity.DialogAidlCallback dialogAidlCallback = this.m;
        if (dialogAidlCallback != null) {
            dialogAidlCallback.onSetList(this.e, false, 0);
        }
        this.c.e(this.t, this.f, this.j);
        HwBtDialogActivity.DialogAidlCallback dialogAidlCallback2 = this.m;
        if (dialogAidlCallback2 != null) {
            dialogAidlCallback2.onSetNameFilter(this.t);
        }
    }

    public int c() {
        LogUtil.a("HwDialogUtil", "Enter getBTSwitchState().");
        return this.c.g();
    }

    public boolean b(Context context) {
        this.k = true;
        int i = this.f;
        if (i != 2 && i != 1) {
            return false;
        }
        if (!e()) {
            if (this.f == 1 && CommonUtil.bh() && !PermissionUtil.c()) {
                return true;
            }
            d(context);
        }
        return this.k;
    }

    private void d(Context context) {
        a(context);
        this.o = true;
        this.k = false;
    }

    public boolean j() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public boolean i() {
        Object systemService = this.h.getSystemService("appops");
        if (systemService instanceof AppOpsManager) {
            int checkOp = ((AppOpsManager) systemService).checkOp("android:coarse_location", Process.myUid(), this.h.getPackageName());
            LogUtil.a("HwDialogUtil", "isLocationEnable checkOp: ", Integer.valueOf(checkOp));
            r2 = checkOp == 0;
            LogUtil.a("HwDialogUtil", "isLocationEnable res:", Boolean.valueOf(r2), "0：allowed other：no permission.");
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context) {
        if (((Activity) context).isFinishing()) {
            return;
        }
        PermissionUtil.b(context, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(context) { // from class: nxf.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("HwDialogUtil", "permission allowed");
                nxf.this.k = true;
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                super.onDenied(str);
                nxf.this.a(context);
                nxf.this.k = false;
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                if (!((Activity) context).isFinishing()) {
                    super.onForeverDenied(permissionType, new View.OnClickListener() { // from class: nxf.5.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            ((Activity) context).finish();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }, new View.OnClickListener() { // from class: nxf.5.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (context instanceof Activity) {
                                ((Activity) context).finish();
                            }
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                }
                nxf.this.k = false;
            }
        });
    }

    public boolean e() {
        return PermissionUtil.e(this.h, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
    }

    public void a(long j, int i) {
        LogUtil.a("HwDialogUtil", "Enter connectSelectedDevice id: ", Long.valueOf(j), ";bluetoothType is: ", Integer.valueOf(i), ", mBtDeviceList.size():", Integer.valueOf(this.e.size()));
        if (j < 0) {
            return;
        }
        if (j < this.e.size()) {
            int i2 = (int) j;
            BluetoothDevice btDevice = this.e.get(i2).getBtDevice();
            if (btDevice != null) {
                try {
                    LogUtil.a("HwDialogUtil", "connectSelectedDevice id: ", Long.valueOf(j), ";name is: ", btDevice.getName());
                    d(i);
                    c(j);
                    return;
                } catch (SecurityException e) {
                    LogUtil.b("HwDialogUtil", "connectSelectedDevice SecurityException:", ExceptionUtils.d(e));
                    return;
                }
            }
            if (this.e.get(i2).getNodeId() != null) {
                LogUtil.a("HwDialogUtil", "connectSelectedDevice id: ", Long.valueOf(j), ";node is: ", iyl.d().e(this.e.get(i2).getNodeId()));
                if (!nrj.b()) {
                    try {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.NotifyActivity"));
                        intent.setFlags(268435456);
                        intent.putExtra("dialog_style", 102);
                        intent.putExtra("device_id", j);
                        this.h.startActivity(intent);
                        return;
                    } catch (ActivityNotFoundException e2) {
                        LogUtil.b("HwDialogUtil", "ActivityNotFoundException :", ExceptionUtils.d(e2));
                        return;
                    }
                }
                c(j);
                return;
            }
            return;
        }
        nrh.e(BaseApplication.getContext(), R.string.IDS_device_mgr_device_pair_guide_tips);
    }

    private void c(long j) {
        LogUtil.a("HwDialogUtil", "Enter connectDevice.");
        int i = (int) j;
        if (i < 0 || i >= this.e.size()) {
            LogUtil.h("HwDialogUtil", "connectDevice id is error.");
            return;
        }
        try {
            BluetoothDevice btDevice = this.e.get(i).getBtDevice();
            iyl iylVar = this.c;
            if (iylVar != null) {
                iylVar.b();
            }
            if (btDevice != null && btDevice.getName() != null) {
                String name = btDevice.getName();
                int c = jfu.c(name);
                int d = jfu.c(c).d();
                LogUtil.a("HwDialogUtil", "connectDevice deviceName: ", name, ",ProductType: ", Integer.valueOf(c), ",blueToothType: ", Integer.valueOf(d));
                this.g.setProductType(c);
                this.g.setDeviceNameInfo(name);
                this.g.setBluetoothType(d);
                d(btDevice.getAddress());
                return;
            }
            this.g.setProductType(10);
            this.g.setProductType(0);
            d("AndroidWear");
        } catch (SecurityException e) {
            LogUtil.b("HwDialogUtil", "connectDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void d(int i) {
        if (i == -1 || i == this.f) {
            return;
        }
        this.f = i;
    }

    public boolean h() {
        Object systemService = this.h.getSystemService("location");
        if (!(systemService instanceof LocationManager)) {
            return false;
        }
        LocationManager locationManager = (LocationManager) systemService;
        boolean isProviderEnabled = locationManager.isProviderEnabled(GeocodeSearch.GPS);
        LogUtil.a("HwDialogUtil", "isGpsLocationEnable isGpsEnable: ", Boolean.valueOf(isProviderEnabled));
        if (iyg.e() || iyg.b()) {
            return isProviderEnabled;
        }
        boolean isProviderEnabled2 = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        LogUtil.a("HwDialogUtil", "isGpsLocationEnable isNetWorkEnable: ", Boolean.valueOf(isProviderEnabled2));
        return isProviderEnabled || isProviderEnabled2;
    }

    public void d(String str) {
        LogUtil.a("HwDialogUtil", "Enter goAddDevice.");
        if (!TextUtils.isEmpty(str)) {
            this.g.setMac(str);
        }
        try {
            jfq.c().b(this.g, "", this.d);
        } catch (RemoteException unused) {
            LogUtil.b("HwDialogUtil", "goAddDevice occur RemoteException.");
        }
    }
}
