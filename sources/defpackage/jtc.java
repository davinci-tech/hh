package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import androidx.core.app.NotificationManagerCompat;
import com.google.gson.Gson;
import com.huawei.datatype.DeviceCommand;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwbtsdk.btdatatype.callback.IAddDeviceStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.HwAddDeviceInterface;
import com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.wearmgr.outofprocess.datatype.BluetoothType;
import com.huawei.wearmgr.phoneprocess.adapterapi.WearMgrAdapterApi;
import health.compact.a.CommonUtil;
import health.compact.a.HwEncryptUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class jtc implements HwDeviceMgrInterface {
    private static final Object b = new Object();
    private static boolean d = false;
    private static jtc e;
    private e c;
    private String g;
    private iyl h;
    private jtd k;
    private kcw l;
    private volatile boolean m;
    private kct n;
    private DeviceStateCallback o;
    private String p;
    private kig q;
    private String r;
    private snq u;

    /* renamed from: a, reason: collision with root package name */
    private SparseArray<HwAddDeviceInterface> f14062a = new SparseArray<>();
    private boolean t = false;
    private BroadcastReceiver v = new BroadcastReceiver() { // from class: jtc.5
        private String e = "";

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z;
            if (intent != null) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "currentAction: ", intent.getAction());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                String format = simpleDateFormat.format(new Date());
                if ("android.intent.action.TIME_TICK".equals(intent.getAction())) {
                    if (jtc.this.k != null) {
                        jtc.this.k.d(jtc.this.getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "processMemoryCheck"));
                    }
                    if ("".equals(this.e)) {
                        this.e = format;
                        return;
                    }
                    try {
                        Date parse = simpleDateFormat2.parse(format);
                        Date parse2 = simpleDateFormat2.parse(this.e);
                        long time = parse.getTime() - parse2.getTime();
                        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "currentTime:", parse, ",mLaterTime:", parse2, ",timeTap:", Long.valueOf(time));
                        long j = (time / 1000) / 60;
                        if (j > 25 || j < -25) {
                            this.e = format;
                            z = true;
                        } else {
                            this.e = format;
                            return;
                        }
                    } catch (ParseException unused) {
                        this.e = format;
                        return;
                    }
                } else {
                    this.e = format;
                    z = false;
                }
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "isNeedSetTime: ", Boolean.valueOf(z));
                if (jtc.this.k != null) {
                    jtc.this.k.bJn_(intent, z);
                }
            }
        }
    };
    private DeviceStatusChangeCallback j = new DeviceStatusChangeCallback() { // from class: jtc.1
        @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
        public void onConnectStatusChanged(DeviceInfo deviceInfo, int i, int i2) {
            if (deviceInfo == null) {
                LogUtil.b("DEVMGR_HwUniteDeviceMgr", "DeviceInfo is null");
                return;
            }
            if (deviceInfo.getDeviceType() > 0) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "onConnectStatusChanged enter");
                UniteDevice a2 = jtc.this.k.a(deviceInfo.getDeviceMac());
                if (a2 == null) {
                    a2 = new UniteDevice();
                    a2.setDeviceInfo(deviceInfo);
                }
                jtc.this.a(blc.c(a2), i);
                jtc.this.b(deviceInfo.getDeviceMac(), i2);
            }
        }
    };
    private MessageReceiveCallback s = new MessageReceiveCallback() { // from class: jtc.2
        @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
        public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
        }

        @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
        public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
            byte b2;
            if (deviceInfo == null || biuVar == null || biuVar.a() == null) {
                LogUtil.b("DEVMGR_HwUniteDeviceMgr", "DeviceInfo or DataFrame is null");
                return;
            }
            byte[] a2 = biuVar.a();
            if (a2.length > 2) {
                if (a2[0] == 1 && ((b2 = a2[1]) == 5 || b2 == 50)) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "parseSynchronizeTimeDataResult");
                    jtc.this.a(biuVar);
                }
                byte b3 = a2[0];
                if (b3 == 27 || b3 == 39) {
                    ReleaseLogUtil.e("DEVMGR_HwUniteDeviceMgr", "response sid: ", Integer.valueOf(b3), " cid: ", Integer.valueOf(a2[1]));
                }
                if (a2[0] == 1 && a2[1] == 58) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "5.1.58 parse result, deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceMac()));
                    ExternalDeviceCapability a3 = bjx.a().a(deviceInfo.getDeviceMac());
                    UniteDevice uniteDevice = new UniteDevice();
                    uniteDevice.setIdentify(deviceInfo.getDeviceMac());
                    uniteDevice.setCapability(a3);
                    uniteDevice.setDeviceInfo(deviceInfo);
                    jut.c().d(blc.c(uniteDevice), biuVar);
                }
            }
            if (deviceInfo.getDeviceType() > 0) {
                UniteDevice uniteDevice2 = new UniteDevice();
                uniteDevice2.setDeviceInfo(deviceInfo);
                jtc.this.c(blc.c(uniteDevice2), biuVar.a().length, biuVar.a());
            }
            jtc.this.k.d(jss.b(biuVar.a()));
        }
    };
    private DeviceCompatibleCallback i = new DeviceCompatibleCallback() { // from class: jtc.3
        @Override // com.huawei.devicesdk.callback.DeviceCompatibleCallback
        public void adapterOperate(List<String> list) {
            new jsk().a(list);
            jtf.d().e();
        }
    };
    private Context f = BaseApplication.getContext();

    /* JADX INFO: Access modifiers changed from: private */
    public void a(biu biuVar) {
        if (biuVar == null || biuVar.a() == null) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "DataFrame is null");
            return;
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "parseTimeDataResult");
        bmj e2 = bhh.e(biuVar.a());
        if (e2 == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "synchronizeTimeDataResult tlvFather is null");
            return;
        }
        for (bmi bmiVar : e2.b()) {
            if (bli.e(bmiVar.e()) == 1) {
                int e3 = bli.e(bmiVar.c());
                int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                int abs = Math.abs(currentTimeMillis - e3);
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "synchronizeTimeData currentTime:", Integer.valueOf(currentTimeMillis), ",replyTime:", Integer.valueOf(e3), "synchronizeTimeData offset:", Integer.valueOf(abs));
                if (abs >= 2 && !d) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "checkTimeOffset offset: need resendCmd");
                    d = true;
                    this.k.f();
                    return;
                }
                d = false;
                return;
            }
        }
    }

    private jtc() {
        this.k = null;
        this.u = null;
        this.h = null;
        this.k = jtd.b();
        this.h = iyl.d();
        snq c = snq.c();
        this.u = c;
        this.k.d(c);
        this.n = kct.c();
        this.l = kcw.a();
        this.c = new e(Looper.getMainLooper());
        this.r = UUID.randomUUID().toString();
        this.p = UUID.randomUUID().toString();
        this.g = UUID.randomUUID().toString();
        snq snqVar = this.u;
        if (snqVar != null) {
            snqVar.registerHandshakeFilter(jtn.b().a());
            this.u.registerDeviceCompatibleListener(this.g, this.i);
            this.u.a(this.f);
            this.u.registerDeviceStateListener(this.r, this.j);
            this.u.registerDeviceMessageListener(this.p, this.s);
        }
        t();
        this.q = new kig();
        this.f14062a.put(2, new jsw());
        this.f14062a.put(1, new jtb());
        this.f14062a.put(0, new jsv());
        m();
    }

    public static jtc c() {
        jtc jtcVar;
        synchronized (b) {
            if (e == null) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "HwUniteDeviceMgr init.");
                e = new jtc();
            }
            jtcVar = e;
        }
        return jtcVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void addDevice(DeviceParameter deviceParameter, IAddDeviceStateCallback iAddDeviceStateCallback, String str) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter addDevice().");
        if (deviceParameter == null || deviceParameter.getNameFilter() == null || iAddDeviceStateCallback == null) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "deviceParameter is null.");
            return;
        }
        kjs.d().b(2);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> e2 = this.n.e();
        if (c(deviceParameter, e2)) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "addDevice device relation is assistant, enter switch device.");
            switchDevice(e2, deviceParameter.getMac());
        } else {
            d(deviceParameter, iAddDeviceStateCallback);
        }
    }

    private void d(DeviceParameter deviceParameter, IAddDeviceStateCallback iAddDeviceStateCallback) {
        if (!TextUtils.isEmpty(deviceParameter.getMac())) {
            if (jsn.d(deviceParameter.getProductType())) {
                bhh.e(deviceParameter.getMac(), deviceParameter.getProductPin());
                this.k.c(deviceParameter);
                return;
            } else {
                jsz.b().addDevice(deviceParameter, iAddDeviceStateCallback, "");
                return;
            }
        }
        int bluetoothType = deviceParameter.getBluetoothType();
        if (bluetoothType == 0) {
            jsz.b().addDevice(deviceParameter, iAddDeviceStateCallback, "");
        } else if (bluetoothType == 1 || bluetoothType == 2) {
            this.f14062a.get(deviceParameter.getBluetoothType()).addDeviceHelper(deviceParameter);
        } else {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "switch other status");
        }
    }

    private boolean c(DeviceParameter deviceParameter, List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list) {
        String e2 = jta.d().e(deviceParameter.getMac());
        Iterator<com.huawei.health.devicemgr.business.entity.DeviceInfo> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().getDeviceIdentify().equalsIgnoreCase(deviceParameter.getMac()) && "assistant_relationship".equalsIgnoreCase(e2)) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void connectDevice(DeviceInfo deviceInfo, int i) {
        if (deviceInfo != null) {
            if (!TextUtils.isEmpty(jta.d().e(deviceInfo.getDeviceMac()))) {
                List<com.huawei.health.devicemgr.business.entity.DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "connectDevice");
                d(deviceInfo, deviceList);
                kct.c().d(this.k.e(deviceInfo, deviceList), true);
            }
            if (!jsn.d(deviceInfo.getDeviceType())) {
                jsz.b().connectDevice(deviceInfo, 0);
                return;
            } else {
                d(deviceInfo);
                return;
            }
        }
        jsz.b().connectDevice(null, i);
        c(i);
    }

    private void d(DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter connectDeviceDirectly().");
        if (deviceInfo.getDeviceMac() == null) {
            return;
        }
        kjs.d().b(2);
        this.m = true;
        jtd jtdVar = this.k;
        if (jtdVar != null) {
            jtdVar.a(new UniteDevice().build(deviceInfo.getDeviceMac(), deviceInfo, null), false, ConnectMode.GENERAL);
        } else {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "connectDeviceDirectly mHwHandleUniteDeviceMgr is null");
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void unPair(List<String> list, boolean z) {
        ReleaseLogUtil.d("DEVMGR_HwUniteDeviceMgr", "unPair: ", Boolean.valueOf(z));
        a(list);
        if (z) {
            c(list);
            jsz.b().unPair(list, true);
            jtf.d().e();
        } else {
            d(list);
            jsz.b().unPair(list, false);
        }
    }

    private void a(List<String> list) {
        UniteDevice a2;
        if (list == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "reportDeviceUnPairData macList is null");
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && (a2 = this.k.a(str)) != null && a2.getDeviceInfo() != null) {
                bmw.e(100026, StringUtils.c((Object) a2.getDeviceInfo().getDeviceName()), String.valueOf(a2.getDeviceInfo().getDeviceConnectState()), "");
            }
        }
    }

    private void d(List<String> list) {
        jtd jtdVar = this.k;
        if (jtdVar == null) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "disconnectDevice mHwHandleUniteDeviceMgr is null");
            return;
        }
        List<UniteDevice> h = jtdVar.h();
        if (list == null) {
            Iterator<UniteDevice> it = h.iterator();
            while (it.hasNext()) {
                this.k.a(it.next());
            }
            return;
        }
        for (String str : list) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("DEVMGR_HwUniteDeviceMgr", "disconnectDevice mac is empty");
            } else {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "disconnectDevice mac: ", CommonUtil.l(str));
                for (UniteDevice uniteDevice : h) {
                    if (uniteDevice == null || uniteDevice.getDeviceInfo() == null || uniteDevice.getDeviceInfo().getDeviceMac() == null) {
                        LogUtil.h("DEVMGR_HwUniteDeviceMgr", "disconnectDevice parameter error");
                    } else if (uniteDevice.getDeviceInfo().getDeviceMac().equals(str)) {
                        this.k.a(uniteDevice);
                        if (uniteDevice.getDeviceInfo().getDeviceBtType() == 2) {
                            bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(uniteDevice.getDeviceInfo().getDeviceMac()));
                        }
                    }
                }
            }
        }
    }

    private void c(List<String> list) {
        if (this.k == null) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "removeDevice mHwHandleUniteDeviceMgr is null");
            return;
        }
        if (list == null) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "deleteDevice macList is null");
            for (UniteDevice uniteDevice : this.k.h()) {
                if (uniteDevice == null || uniteDevice.getDeviceInfo() == null) {
                    LogUtil.h("DEVMGR_HwUniteDeviceMgr", "removeDevice parameter error");
                } else {
                    a(uniteDevice.getDeviceInfo().getDeviceMac());
                    kih.e().e(uniteDevice.getDeviceInfo().getDeviceMac(), 20230830);
                }
            }
            return;
        }
        boolean a2 = SharedPreferenceManager.a(Integer.toString(1000), "wearable_unpair_reconnection", false);
        this.t = a2;
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "mIsNotificationHideScene: ", Boolean.valueOf(a2));
        for (String str : list) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("DEVMGR_HwUniteDeviceMgr", "removeDevice mac is empty");
            } else {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "removeDevice mac: ", CommonUtil.l(str));
                kih.e().e(str, 20230830);
                a(str);
            }
        }
        if (this.t) {
            SharedPreferenceManager.e(Integer.toString(1000), "wearable_unpair_reconnection", false);
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "set WEARABLE_UNPAIR_RECONNECTION false");
            this.t = false;
        }
    }

    private void c(int i) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "forceConnectDevice enter", Integer.valueOf(i));
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        if (d2.size() == 0) {
            return;
        }
        boolean z = (BluetoothType.AW.getValue() & i) == BluetoothType.AW.getValue();
        boolean z2 = (BluetoothType.BR.getValue() & i) == BluetoothType.BR.getValue();
        boolean z3 = (BluetoothType.BLE.getValue() & i) == BluetoothType.BLE.getValue();
        boolean z4 = (BluetoothType.DUAL.getValue() & i) == BluetoothType.DUAL.getValue();
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "isConnectAw", Boolean.valueOf(z), "isConnectBr", Boolean.valueOf(z2), "isConnectBle", Boolean.valueOf(z3), "isConnectAual", Boolean.valueOf(z4));
        for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : d2) {
            if (i != BluetoothType.BLE.getValue() || bmc.b(deviceInfo.getDeviceName(), deviceInfo.getDeviceIdentify())) {
                int deviceBluetoothType = deviceInfo.getDeviceBluetoothType();
                if (z || deviceBluetoothType != 0) {
                    if (z2 || deviceBluetoothType != 1) {
                        if (z3 || deviceBluetoothType != 2) {
                            if (z4 || deviceBluetoothType != 4) {
                                if (deviceInfo.getDeviceConnectState() != 2 && deviceInfo.getDeviceActiveState() == 1) {
                                    UniteDevice a2 = this.k.a(deviceInfo.getDeviceIdentify());
                                    if (deviceBluetoothType == 0) {
                                        if (deviceInfo.getDeviceConnectState() != 1) {
                                            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Start to connect AW device.");
                                            this.k.a(a2, true, ConnectMode.GENERAL);
                                            return;
                                        }
                                        return;
                                    }
                                    if (deviceBluetoothType == 2 || this.k.e(deviceInfo)) {
                                        this.k.a(a2, true, ConnectMode.GENERAL);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void registerDeviceStateCallback(DeviceStateCallback deviceStateCallback) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter registerDeviceStateCallback");
        this.o = deviceStateCallback;
        this.k.d(deviceStateCallback);
    }

    private void a(String str) {
        boolean z;
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "removeCurrentDevice getDeviceInfoListHandle");
        Iterator<com.huawei.health.devicemgr.business.entity.DeviceInfo> it = d2.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().getDeviceIdentify().equalsIgnoreCase(str)) {
                z = true;
                break;
            }
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "removeCurrentDevice isAdded:", Boolean.valueOf(z));
        if (z) {
            if (this.t) {
                bkw.d().c(str);
                bkw.d().sK_(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str));
                return;
            } else {
                d(str);
                return;
            }
        }
        this.k.c(str, true);
    }

    private void s() {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "sendDeviceListChangeBroadcast.");
        this.f.sendBroadcast(new Intent("com.huawei.bone.action.DEVICE_LIST_CHANGED"), LocalBroadcast.c);
    }

    private void d(String str) {
        boolean z;
        com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = null;
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "clearDeviceByMac");
        Iterator<com.huawei.health.devicemgr.business.entity.DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.huawei.health.devicemgr.business.entity.DeviceInfo next = it.next();
            String deviceIdentify = next.getDeviceIdentify();
            if (deviceIdentify != null && deviceIdentify.equalsIgnoreCase(str)) {
                it.remove();
                deviceInfo = next;
                break;
            }
        }
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "deleteDeviceInfo is null.");
            return;
        }
        s();
        this.k.b(deviceInfo);
        UniteDevice a2 = this.k.a(deviceInfo.getDeviceIdentify());
        if (a2 != null) {
            this.n.c(str);
            this.u.unPairDevice(a2);
            z = true;
        } else {
            z = false;
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "clearDeviceByMac isUdsDevice is ", Boolean.valueOf(z));
        if (z) {
            b(deviceList, deviceInfo);
            kjs.d().e(deviceList);
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void sendDeviceData(DeviceCommand deviceCommand) {
        if (deviceCommand == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "sendDeviceData() deviceCommand is null.");
            return;
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "sendDeviceData ServiceID:", Integer.valueOf(deviceCommand.getServiceID()), ", CommandID:", Integer.valueOf(deviceCommand.getCommandID()));
        if (((byte) deviceCommand.getServiceID()) == 1 && ((byte) deviceCommand.getCommandId()) == 48) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "sendDeviceData, get 5.1.48 command content");
            a(deviceCommand);
            return;
        }
        HashSet hashSet = new HashSet();
        Map<String, UniteDevice> e2 = this.k.e(hashSet);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> deviceList = jsz.b().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "sendDeviceData");
        if (!TextUtils.isEmpty(deviceCommand.getmIdentify())) {
            if (hashSet.contains(deviceCommand.getmIdentify()) || b(deviceCommand)) {
                d(deviceCommand);
                return;
            } else {
                jsz.b().sendDeviceData(deviceCommand);
                return;
            }
        }
        b(e2, deviceList, deviceCommand);
    }

    private boolean b(DeviceCommand deviceCommand) {
        return ((byte) deviceCommand.getServiceID()) == 26 && ((byte) deviceCommand.getCommandId()) == 6;
    }

    private void b(Map<String, UniteDevice> map, List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list, DeviceCommand deviceCommand) {
        if (map.size() + list.size() == 2) {
            for (UniteDevice uniteDevice : map.values()) {
                c(deviceCommand, uniteDevice, cvt.c(uniteDevice.getDeviceInfo().getDeviceType()));
            }
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : list) {
                c(deviceCommand, deviceInfo.getDeviceIdentify(), cvt.c(deviceInfo.getProductType()));
            }
            return;
        }
        if (map.size() + list.size() == 1) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "sendDeviceData has only one device");
            if (map.size() == 1) {
                Iterator<UniteDevice> it = map.values().iterator();
                if (it.hasNext()) {
                    deviceCommand.setmIdentify(it.next().getIdentify());
                    d(deviceCommand);
                    return;
                }
                return;
            }
            jsz.b().sendDeviceData(deviceCommand);
            return;
        }
        LogUtil.c("DEVMGR_HwUniteDeviceMgr", "sendDeviceData go to else branch");
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public List<com.huawei.health.devicemgr.business.entity.DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "getDeviceList enter mode: ", hwGetDevicesMode, " tag: ", str);
        ArrayList arrayList = new ArrayList(24);
        if (hwGetDevicesMode == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getDeviceList mode is null.");
            return arrayList;
        }
        switch (AnonymousClass4.d[hwGetDevicesMode.ordinal()]) {
            case 1:
                return f();
            case 2:
                return n();
            case 3:
                return e();
            case 4:
                return i();
            case 5:
                return a(hwGetDevicesParameter);
            case 6:
                return j();
            case 7:
                return b();
            case 8:
                return k();
            case 9:
                return l();
            default:
                LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getDeviceList unsupport mode.");
                return arrayList;
        }
    }

    /* renamed from: jtc$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HwGetDevicesMode.values().length];
            d = iArr;
            try {
                iArr[HwGetDevicesMode.ALL_DEVICES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HwGetDevicesMode.CONNECTED_MAIN_DEVICES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HwGetDevicesMode.ACTIVE_MAIN_DEVICES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[HwGetDevicesMode.CONNECTED_AW70_DEVICES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[HwGetDevicesMode.IDENTIFY_DEVICES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                d[HwGetDevicesMode.CONNECTED_DEVICES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                d[HwGetDevicesMode.ACTIVE_DEVICES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                d[HwGetDevicesMode.MAIN_FOLLOW_DEVICES.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                d[HwGetDevicesMode.FOLLOWED_DEVICES.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void registerAdapter(WearMgrAdapterApi wearMgrAdapterApi) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "registerAdapter.");
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        if (d2.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : d2) {
                if ("assistant_relationship".equals(deviceInfo.getRelationship())) {
                    deviceInfo.setDeviceConnectState(3);
                    deviceInfo.setDeviceActiveState(0);
                }
            }
            arrayList.addAll(d2);
        }
        arrayList.addAll(jsz.b().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, ""));
        if (arrayList.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getAllDeviceList deviceInfoList size is zero.");
        }
        return jta.d().d(arrayList);
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> n() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "return getConnectedMainDeviceList() size is 0");
            return arrayList;
        }
        for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
            if (deviceInfo.getDeviceConnectState() == 2 && "main_relationship".equals(deviceInfo.getRelationship())) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "getConnectedMainDeviceList device name ", deviceInfo.getDeviceName());
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> j() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f != null && f.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
                if (deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "getConnectedDeviceList device name ", deviceInfo.getDeviceName());
                    arrayList.add(deviceInfo);
                }
            }
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "return getConnectedDeviceList() size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> i() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f != null && f.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
                if (deviceInfo.getDeviceConnectState() == 2 && cvt.c(deviceInfo.getProductType())) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getConnectedAw70DeviceList deviceInfoList size is zero.");
        }
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> b() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f != null && f.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
                if (deviceInfo.getDeviceActiveState() == 1) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getActiveDeviceList deviceInfoListBaks size is zero.");
        }
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> e() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
                if (deviceInfo.getDeviceActiveState() == 1 && "main_relationship".equals(deviceInfo.getRelationship())) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getActiveMainDeviceList deviceInfoListBaks size is zero.");
        }
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> a(HwGetDevicesParameter hwGetDevicesParameter) {
        ArrayList arrayList = new ArrayList(24);
        if (hwGetDevicesParameter == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getSpecificDeviceList parameter is null.");
            return arrayList;
        }
        String deviceIdentify = hwGetDevicesParameter.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "igetSpecificDeviceList identify of parameter is null.");
            return arrayList;
        }
        String replaceAll = deviceIdentify.replaceAll(":", "");
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f.size() != 0) {
            Iterator<com.huawei.health.devicemgr.business.entity.DeviceInfo> it = f.iterator();
            String str = "";
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                com.huawei.health.devicemgr.business.entity.DeviceInfo next = it.next();
                String deviceIdentify2 = next.getDeviceIdentify();
                if (deviceIdentify2 != null) {
                    str = deviceIdentify2.replaceAll(":", "");
                }
                if (str.contains(replaceAll)) {
                    arrayList.add(next);
                    break;
                }
            }
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "return getSpecificDeviceList() size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> k() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f != null && f.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
                if (deviceInfo.getDeviceActiveState() == 1 && ("main_relationship".equals(deviceInfo.getRelationship()) || "followed_relationship".equals(deviceInfo.getRelationship()))) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getMainAndFollowDeviceList deviceInfoListBaks size is zero.");
        }
        return arrayList;
    }

    private List<com.huawei.health.devicemgr.business.entity.DeviceInfo> l() {
        ArrayList arrayList = new ArrayList(24);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> f = f();
        if (f != null && f.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : f) {
                if (deviceInfo.getDeviceActiveState() == 1 && "followed_relationship".equals(deviceInfo.getRelationship())) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        if (arrayList.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getFollowedDeviceList() deviceInfoListBaks size is zero.");
        }
        return arrayList;
    }

    private void c(DeviceCommand deviceCommand, String str, boolean z) {
        if (deviceCommand.getServiceID() == 23 && deviceCommand.getCommandID() == 15) {
            if (z) {
                deviceCommand.setmIdentify(str);
                jsz.b().sendDeviceData(deviceCommand);
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        deviceCommand.setmIdentify(str);
        jsz.b().sendDeviceData(deviceCommand);
    }

    private void c(DeviceCommand deviceCommand, UniteDevice uniteDevice, boolean z) {
        if (deviceCommand.getServiceID() == 23 && deviceCommand.getCommandID() == 15) {
            if (z) {
                deviceCommand.setmIdentify(uniteDevice.getIdentify());
                d(deviceCommand);
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        deviceCommand.setmIdentify(uniteDevice.getIdentify());
        d(deviceCommand);
    }

    private void d(DeviceCommand deviceCommand) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "sendDeviceCommand enter");
        if (jcg.d(deviceCommand.getmIdentify()) && deviceCommand.getServiceID() != 9) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "OTA update, other command can't send...");
            return;
        }
        if (deviceCommand.getCommandID() == 13 && deviceCommand.getServiceID() == 1) {
            this.k.c();
        }
        this.k.c(deviceCommand);
    }

    private void a(DeviceCommand deviceCommand) {
        if (deviceCommand == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "getCommandContentAndNotifyUserSelect() deviceCommand is null.");
            return;
        }
        if (((byte) deviceCommand.getServiceID()) == 1 && ((byte) deviceCommand.getCommandId()) == 48) {
            byte[] dataContent = deviceCommand.getDataContent();
            if (!(jtn.b().a(deviceCommand.getmIdentify()) instanceof jtl)) {
                LogUtil.h("DEVMGR_HwUniteDeviceMgr", "Command is not instanceof StartupGuideUserSettingCommand");
            } else {
                ((jtl) jtn.b().a(deviceCommand.getmIdentify())).a(dataContent);
            }
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void switchDevice(List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list, String str) {
        ReleaseLogUtil.e("DEVMGR_HwUniteDeviceMgr", "Enter switchDevice");
        if (list == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "Parameter is incorrect.");
            return;
        }
        Iterator<com.huawei.health.devicemgr.business.entity.DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            com.huawei.health.devicemgr.business.entity.DeviceInfo next = it.next();
            if (next != null && next.getDeviceIdentify().equals(str)) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "switchDevice report");
                bmw.e(100057, next.getDeviceName(), "", "");
                break;
            }
        }
        this.k.c();
        if (this.k.d().size() == 0) {
            jsz.b().switchDevice(list, str);
            return;
        }
        if (list.size() != getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "switchDevice").size()) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "Deleting devices is not supported");
        } else {
            c(list, str);
        }
    }

    private void c(List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list, String str) {
        Iterator<Integer> it = this.k.b(list, str).iterator();
        while (it.hasNext()) {
            com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = list.get(it.next().intValue());
            if (this.k.c(deviceInfo)) {
                if (cvt.c(deviceInfo.getProductType())) {
                    this.n.d(deviceInfo);
                } else {
                    this.n.d(deviceInfo, true);
                }
                DeviceInfo deviceInfo2 = new DeviceInfo();
                if (jsn.d(deviceInfo.getProductType())) {
                    this.k.a(this.k.a(deviceInfo.getDeviceIdentify()), true, ConnectMode.GENERAL);
                } else {
                    deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
                    deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
                    deviceInfo2.setDeviceMac(deviceInfo.getDeviceIdentify());
                    deviceInfo2.setDeviceType(deviceInfo.getProductType());
                    jsz.b().connectDevice(deviceInfo2, 0);
                }
            }
        }
    }

    private void d(DeviceInfo deviceInfo, List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list) {
        Object[] objArr = new Object[4];
        objArr[0] = "Enter handleSameTypeDevice(). deviceInfo isNull :";
        objArr[1] = Boolean.valueOf(deviceInfo == null);
        objArr[2] = ", deviceInfoList isNull :";
        objArr[3] = Boolean.valueOf(list == null);
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", objArr);
        if (deviceInfo == null || list == null) {
            return;
        }
        if (this.l.c(deviceInfo.getDeviceType(), deviceInfo.getDeviceMac())) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "handleSameTypeDevice device is follow and is not aw70");
            return;
        }
        if (cvt.c(deviceInfo.getDeviceType())) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo2 : list) {
                if (cvt.c(deviceInfo2.getProductType()) && deviceInfo2.getDeviceActiveState() == 1 && !deviceInfo.getDeviceMac().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "handleSameTypeDevice has active aw70 device");
                    e(deviceInfo2.getDeviceIdentify());
                }
            }
            return;
        }
        for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo3 : list) {
            if (!cvt.a(deviceInfo3.getProductType(), deviceInfo3.getAutoDetectSwitchStatus()) && deviceInfo3.getDeviceActiveState() == 1 && !deviceInfo.getDeviceMac().equalsIgnoreCase(deviceInfo3.getDeviceIdentify()) && !this.l.c(deviceInfo3.getProductType(), deviceInfo3.getDeviceIdentify())) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "handleSameTypeDevice has active band mode device");
                f(deviceInfo3);
            }
        }
    }

    private void f(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        if (this.l.a(deviceInfo)) {
            return;
        }
        e(deviceInfo.getDeviceIdentify());
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void unRegisterDeviceStateCallback(DeviceStateCallback deviceStateCallback) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "enter unRegisterDeviceStateCallback");
        this.o = null;
        this.k.i();
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public void destroy() {
        synchronized (b) {
            c((jtc) null);
        }
        kcw kcwVar = this.l;
        if (kcwVar != null) {
            kcwVar.c();
        }
        kct kctVar = this.n;
        if (kctVar != null) {
            kctVar.b();
        }
        jtd jtdVar = this.k;
        if (jtdVar != null) {
            jtdVar.a();
        }
        Context context = this.f;
        if (context != null) {
            try {
                context.unregisterReceiver(this.v);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("DEVMGR_HwUniteDeviceMgr", "destroy IllegalArgumentException");
            }
        }
        snq snqVar = this.u;
        if (snqVar != null) {
            snqVar.unregisterDeviceStateListener(this.r);
            this.u.unregisterDeviceMessageListener(this.p);
            this.u.unregisterDeviceCompatibleListener(this.g);
        }
        kkp.c().e();
        if (jsz.b().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "udsdestroy").size() == 0) {
            jsz.b().destroy();
        }
    }

    public void b(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "btDeviceInfo is null");
            return;
        }
        if (i == 2) {
            j(deviceInfo);
            this.u.updateDeviceAfterSimulatConnected(deviceInfo.getDeviceIdentify());
            String e2 = jta.d().e(deviceInfo.getDeviceIdentify());
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "simulatNotifyConnectionStateChanged deviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " deviceRelation: ", e2);
            if ("main_relationship".equals(e2) && cwi.c(deviceInfo, 219)) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "simulatNotifyConnectionStateChanged Connected device notification level register");
                khi.c().d();
            }
        }
        e(deviceInfo, i);
    }

    /* loaded from: classes5.dex */
    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 55) {
                nrh.d(jtc.this.f.getApplicationContext(), jtc.this.f.getString(R$string.IDS_blite_guide_paire_fail_no_hilink_device_string));
            } else {
                if (i != 56) {
                    return;
                }
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "unPairDevice fail.");
            }
        }
    }

    private void t() {
        if (this.f != null) {
            this.f.registerReceiver(this.v, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
            IntentFilter intentFilter = new IntentFilter("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            this.f.registerReceiver(this.v, intentFilter);
            this.f.registerReceiver(this.v, new IntentFilter("android.intent.action.DATE_CHANGED"));
        }
    }

    private void b(List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list, com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo2;
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter resetActiveDevice.");
        if (list.isEmpty()) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "resetActiveDevice usedDeviceList is empty.");
            return;
        }
        if ("followed_relationship".equals(deviceInfo.getRelationship()) && !cvt.c(deviceInfo.getProductType())) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "resetActiveDevice is follow.");
            return;
        }
        ArrayList arrayList = new ArrayList(24);
        for (int i = 0; i < list.size(); i++) {
            if ("main_relationship".equals(list.get(i).getRelationship()) && list.get(i).getDeviceActiveState() == 1) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "active device name: ", list.get(i).getDeviceName());
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (!arrayList.isEmpty()) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "resetActiveDevice exist active device.");
            return;
        }
        Iterator<com.huawei.health.devicemgr.business.entity.DeviceInfo> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                deviceInfo2 = it.next();
                if ("main_relationship".equalsIgnoreCase(jta.d().e(deviceInfo2.getDeviceIdentify()))) {
                    break;
                }
            } else {
                deviceInfo2 = null;
                break;
            }
        }
        if (deviceInfo2 != null) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "resetActiveDevice activeMainDevice fuzzData: ", CommonUtil.l(deviceInfo2.getDeviceIdentify()));
            if (this.k.e(deviceInfo2.getDeviceIdentify())) {
                DeviceInfo deviceInfo3 = new DeviceInfo();
                deviceInfo3.setDeviceBtType(deviceInfo2.getDeviceBluetoothType());
                deviceInfo3.setDeviceName(deviceInfo2.getDeviceName());
                deviceInfo3.setDeviceMac(deviceInfo2.getDeviceIdentify());
                deviceInfo3.setDeviceType(deviceInfo2.getProductType());
                d(deviceInfo3, getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "resetActiveDevice"));
                d(deviceInfo3);
                return;
            }
            jsz.b().b(deviceInfo2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "btDeviceInfo is null state:", Integer.valueOf(i));
            return;
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "enter onDeviceConnectionStateChanged with state:", Integer.valueOf(i), " deviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        if (i == 33) {
            jta.d().c(jsz.b(com.huawei.haf.application.BaseApplication.e()).getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DEVMGR_HwUniteDeviceMgr"), "DEVMGR_HwUniteDeviceMgr");
        }
        if (i == 1) {
            izn.c().b(deviceInfo, System.currentTimeMillis());
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "mDeviceStateCallback device start connecting, time:", Long.valueOf(System.currentTimeMillis()));
        }
        c(deviceInfo, i);
    }

    private DeviceCapability e(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        UniteDevice a2;
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify()) || (a2 = this.k.a(deviceInfo.getDeviceIdentify())) == null) {
            return null;
        }
        return blc.a(a2);
    }

    private void o(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter sendConnectStatus");
        DeviceCapability e2 = e(deviceInfo);
        if (e2 != null && e2.isSupportConnectStatus()) {
            izf e3 = iyo.e();
            e3.e(deviceInfo.getDeviceIdentify());
            this.k.d(e3);
            return;
        }
        LogUtil.h("DEVMGR_HwUniteDeviceMgr", "not support connect status");
    }

    private void c(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "onDeviceConnectionStateChanged btState: ", Integer.valueOf(i));
        if (deviceInfo == null) {
            return;
        }
        if (i == 2) {
            this.n.d(deviceInfo, false);
            g(deviceInfo);
            j(deviceInfo);
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "isSupportConnectNewPhone: ", Boolean.valueOf(cwi.c(deviceInfo, 223)), " deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            n(deviceInfo);
            o(deviceInfo);
            c(deviceInfo);
        }
        if (i == 4) {
            jut.c().d(deviceInfo);
            jut.c().e(deviceInfo);
        }
        this.n.e(deviceInfo, i);
        r();
        if (CommonUtil.ce() && this.k != null) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "onDeviceConnectionStateChanged is support wearProduct and uniteDeviceMgr is not null");
            cvw.c(f(), "DEVMGR_HwUniteDeviceMgr");
        }
        if ("assistant_relationship".equals(deviceInfo.getRelationship())) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "sendConnectStateBroadcast intent is assistant device.");
        } else {
            e(deviceInfo, i);
        }
    }

    private void c(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        jut.c().b(deviceInfo);
        jut.c().c(deviceInfo);
    }

    private void g(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        String relationship = deviceInfo.getRelationship();
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "preProcessLogicBeforeConnectedBroadcast relationship is empty:", Boolean.valueOf(TextUtils.isEmpty(relationship)));
        if (TextUtils.isEmpty(relationship)) {
            String e2 = jta.d().e(deviceInfo.getDeviceIdentify());
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "preProcessLogicBeforeConnectedBroadcast deviceIdentify:", CommonUtil.l(deviceInfo.getDeviceIdentify()), " deviceRelation: ", e2);
            if ("main_relationship".equals(e2)) {
                if (cwi.c(deviceInfo, 191)) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "preProcessLogicBeforeConnectedBroadcast device sync register callback");
                    kdi.c().d();
                }
                if (cwi.c(deviceInfo, 219)) {
                    LogUtil.a("DEVMGR_HwUniteDeviceMgr", "preProcessLogicBefore Connected device notification level register");
                    khi.c().d();
                }
            }
        }
    }

    private void n(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 182);
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "isSupportReverseCapability: ", Boolean.valueOf(c));
        if (c) {
            byte[] o = o();
            if (o == null || o.length == 0) {
                LogUtil.h("DEVMGR_HwUniteDeviceMgr", "sendReverseCapability capability is error.");
                return;
            }
            izf b2 = iyo.b(o);
            b2.e(deviceInfo.getDeviceIdentify());
            this.k.d(b2);
            return;
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "sendReverseCapability capability is not support.");
    }

    private byte[] o() {
        List<Integer> d2 = jrk.c().d();
        if (d2.size() - 1 < 0) {
            return new byte[0];
        }
        int intValue = d2.get(d2.size() - 1).intValue();
        byte[] bArr = new byte[intValue != 0 ? (intValue / 8) + 1 : 0];
        Iterator<Integer> it = d2.iterator();
        while (it.hasNext()) {
            int intValue2 = it.next().intValue();
            int i = intValue2 / 8;
            bArr[i] = (byte) ((1 << (intValue2 % 8)) | bArr[i]);
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "getReverseCapabilityBytes result: ", blq.d(bArr));
        return bArr;
    }

    private void j(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 104);
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "onDeviceConnectionStateChanged, isSupportConfigManager: ", Boolean.valueOf(c));
        if (c) {
            jzd.b().d(deviceInfo);
        }
    }

    private void e(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i) {
        if (i == 1) {
            iyg.e(deviceInfo.getDeviceName());
        }
        if (i != 2) {
            jcg.d(deviceInfo.getDeviceIdentify(), false);
            kdl.c().a();
            deviceInfo.setDeviceConnectState(i);
        } else {
            d(deviceInfo);
        }
        l(deviceInfo);
    }

    private void d(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        kdi.c().b(deviceInfo);
        kca.c().e(deviceInfo);
        kdl.c().b();
        jcg.b(deviceInfo.getDeviceIdentify(), true);
    }

    private void l(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter sendConnectStateBroadcast(). ", Integer.valueOf(bih.d()));
        if (deviceInfo == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "In UDS-sendConnectStateBroadcast(), btDeviceInfo is null");
            return;
        }
        DeviceStateCallback deviceStateCallback = this.o;
        if (deviceStateCallback != null) {
            deviceStateCallback.onConnectStatusChanged(deviceInfo);
        }
        kkj.d(deviceInfo);
        jqh.c(deviceInfo);
        jtu.a(deviceInfo);
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> deviceList = getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "sendConnectStateBroadcast");
        com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo2 = deviceList.size() > 0 ? deviceList.get(0) : null;
        this.f.sendOrderedBroadcast(bJo_(deviceInfo), LocalBroadcast.c);
        jtd jtdVar = this.k;
        if (jtdVar != null) {
            a(deviceInfo, jtdVar.d());
        }
        if (deviceInfo2 != null && deviceInfo.getDeviceIdentify().equals(deviceInfo2.getDeviceIdentify())) {
            String string = this.f.getSharedPreferences("deviceUpdateSharedPreferences", 0).getString("aw70UpdateCheckTime", "");
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter aw70 sendConnectStateBroadcast(). aw70 mAppCheckTime:", string);
            if (!this.k.b(string)) {
                Intent intent = new Intent("com.huawei.bone.action.UPDATE_DEVICE");
                intent.putExtra("is_current_device_aw70", true);
                this.f.sendBroadcast(intent, LocalBroadcast.c);
            }
        } else if (deviceInfo.getDeviceConnectState() == 2) {
            bih.e(1000000);
        }
        if (deviceInfo.getDeviceConnectState() == 2) {
            jrp.e(deviceInfo.getDeviceIdentify(), 1);
        }
    }

    private Intent bJo_(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter createDeviceStateIntent deviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()), ", connectState: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        Intent intent = new Intent("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intent.setExtrasClassLoader(com.huawei.health.devicemgr.business.entity.DeviceInfo.class.getClassLoader());
        intent.putExtra("connect_error_code", bih.d());
        intent.putExtra("isDeviceConnectDirectly", this.m);
        if (deviceInfo.getDeviceConnectState() != 1) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "createDeviceStateIntent mIsDeviceConnectDirectly reset.");
            this.m = false;
        }
        Bundle bundle = new Bundle();
        deviceInfo.setRelationship(jta.d().e(deviceInfo.getDeviceIdentify()));
        bundle.putParcelable("deviceinfo", deviceInfo);
        bundle.putInt("wear_device_list_size", this.k.d().size());
        intent.putExtras(bundle);
        return intent;
    }

    private void a(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, List<com.huawei.health.devicemgr.business.entity.DeviceInfo> list) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "EMUI SendConnectBroadcastToEmui, btDeviceInfo.getDeviceConnectState, ", Integer.valueOf(deviceInfo.getDeviceConnectState()), " fuzzDeviceIdentify: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
        mbo.a().a(deviceInfo);
        h(deviceInfo);
        kkg.e(deviceInfo);
        kkg.a(deviceInfo);
        kkg.d(deviceInfo);
        kkk.a(this.f, deviceInfo);
        kjk.e(deviceInfo);
        kcm.a(deviceInfo);
        if (deviceInfo.getDeviceConnectState() == 2) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter sendConnectStateBroadcast isScreenOn:", Boolean.valueOf(ScreenUtil.a()));
            this.k.b(list);
            if (!deviceInfo.getDeviceIdentify().equalsIgnoreCase("AndroidWear")) {
                if (!TextUtils.isEmpty(deviceInfo.getNodeId())) {
                    return;
                }
                try {
                    Intent intent = new Intent("com.huawei.iconnect.action.DEVICE_BOND_STATE_CHANGED");
                    intent.setComponent(new ComponentName("com.huawei.iconnect", "com.huawei.iconnect.MessageIntentService"));
                    if (jss.b()) {
                        intent.putExtra("DEVICE_ID", jss.e(deviceInfo.getDeviceIdentify()));
                    } else {
                        intent.putExtra("com.huawei.iconnect.extra.DEVICE_MAC_ADDRESS", deviceInfo.getDeviceIdentify());
                    }
                    intent.putExtra("android.bluetooth.device.extra.BOND_STATE", 12);
                    intent.putExtra("com.huawei.iconnect.extra.PACKAGE_NAME", "com.huawei.health");
                    this.f.startService(intent);
                } catch (SecurityException unused) {
                    LogUtil.b("DEVMGR_HwUniteDeviceMgr", "0xA0200008", ",securityException");
                }
                LogUtil.c("DEVMGR_HwUniteDeviceMgr", "EMUI SendConnectBroadcastToEmui, send BOND_BONDED broadcast, getDeviceIdentify:", this.h.e(deviceInfo.getDeviceIdentify()));
            } else {
                LogUtil.c("DEVMGR_HwUniteDeviceMgr", "EMUI SendConnectBroadcastToEmui, don't need send broadcast for AndroidWear");
            }
        }
        b(deviceInfo);
    }

    private void b(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "cancelNpsNotify enter");
        if (deviceInfo.getDeviceConnectState() != 2) {
            return;
        }
        String b2 = SharedPreferenceManager.b(this.f, String.valueOf(10004), HwNpsManager.KEY_NPS_SHOW);
        String b3 = (Utils.o() || deviceInfo.getDeviceIdType() == 1) ? b(deviceInfo.getSecurityDeviceId()) : "";
        if (TextUtils.isEmpty(b2) || !d(b3, deviceInfo)) {
            NotificationManagerCompat.from(this.f).cancel(781507976);
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "cancelNotify");
        }
    }

    private boolean d(String str, com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        return str.equals(deviceInfo.getUdidFromDevice()) || str.equals(deviceInfo.getDeviceIdentify()) || str.equals(deviceInfo.getDeviceUdid()) || str.equals((Utils.o() || deviceInfo.getDeviceIdType() == 1) ? b(deviceInfo.getSecurityDeviceId()) : "");
    }

    private String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "deviceId is null return!");
            return "";
        }
        HwEncryptUtil c = HwEncryptUtil.c(BaseApplication.getContext());
        String b2 = c != null ? c.b("09F98935DF23B3E011F5638614670662IrzLoccccR72B/H4EI3GKB6ny7lTZGH7IB4hQWa2qra9LliDA6e9/qgL/9yUjVL0") : "";
        if (TextUtils.isEmpty(b2)) {
            return "";
        }
        try {
            byte[] bytes = b2.getBytes(StandardCharsets.UTF_8);
            byte[] bytes2 = str.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "HMACSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return URLEncoder.encode(Base64.encodeToString(mac.doFinal(bytes2), 2), "UTF-8");
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException unused) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "UnsupportedEncodingException");
            return "";
        }
    }

    private void h(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "notifyRemoteCameraCapability enter");
        if (deviceInfo.getDeviceConnectState() != 2) {
            return;
        }
        kin b2 = kin.b();
        boolean a2 = b2.a(deviceInfo);
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "notifyRemoteCameraCapability isSupportControlCapability:", Boolean.valueOf(a2));
        if (a2) {
            b2.e(deviceInfo);
            return;
        }
        Map<String, DeviceCapability> queryDeviceCapability = queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "DEVMGR_HwUniteDeviceMgr");
        if (queryDeviceCapability.isEmpty()) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "notifyRemoteCameraCapability, deviceCapabilityHashMaps is empty");
            return;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null || !deviceCapability.isSupportRemoteCamera()) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "device not support remoteCamera.");
            return;
        }
        if (CommonUtil.bi() || (CommonUtil.bf() && !Utils.o())) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "isMoreThanEmui80");
            a(deviceInfo, 1, 42, new byte[]{1, 1, 0});
        } else {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "lower than Emui8.1");
        }
    }

    private void a(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i, int i2, byte[] bArr) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(i);
        deviceCommand.setCommandID(i2);
        deviceCommand.setDataLen(bArr.length);
        deviceCommand.setDataContent(bArr);
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        this.k.c(deviceCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo, int i, byte[] bArr) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "onDataReceivedMethod not handshake report data.");
        if (KitWearBinderUtil.d(deviceInfo, bArr)) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "isForThemeData");
            return;
        }
        DeviceStateCallback deviceStateCallback = this.o;
        if (deviceStateCallback != null) {
            deviceStateCallback.onDataReceived(deviceInfo, i, bArr);
        } else {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "mDeviceStateClientCallback is null.");
        }
        kih.e().c(deviceInfo, bArr);
        kig kigVar = this.q;
        if (kigVar != null) {
            kigVar.b(deviceInfo, bArr);
        }
    }

    private void e(String str) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "disconnectOldDevice fuzzyMac: ", CommonUtil.l(str));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.k.e(str)) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "disconnectOldDevice isNewDeviceList");
            this.k.c(str);
        } else {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "disconnectOldDevice disconnectDevice");
            jsz.b().a(str);
        }
        for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "disconnectOldDevice")) {
            if (deviceInfo != null && str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                i(deviceInfo);
                return;
            }
        }
    }

    private void i(com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceBluetoothType() != 2 || iyl.d().c(deviceInfo.getDeviceIdentify())) {
            return;
        }
        LogUtil.h("DEVMGR_HwUniteDeviceMgr", "removeBTDeviceInstance Remove bond device fail.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "dealWithConnectErrorCode: deviceIdentify is incorrect");
            return;
        }
        if (i == 50014) {
            c(str);
            return;
        }
        if (i == 50054) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "dealWithConnectErrorCode: missing hilink device id");
            p();
        } else if (i == 70034) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "dealWithConnectErrorCode: unbind failed");
            q();
        } else {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "dealWithConnectErrorCode default");
        }
    }

    private void c(String str) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pairing.HwBtDialogActivity"));
            intent.setFlags(268435456);
            intent.putExtra(TemplateStyleRecord.STYLE, 1);
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, 1);
            intent.putExtra("content", 7);
            intent.putExtra("hichain_late_intent_key", str);
            this.f.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("DEVMGR_HwUniteDeviceMgr", "secretKeyLost ActivityNotFoundException.");
        }
    }

    private static void c(jtc jtcVar) {
        e = jtcVar;
    }

    private void r() {
        if (this.k.h().size() == 0) {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "getUsedDevicesFromStorage size is 0.");
            jte.a(false, true);
        } else {
            LogUtil.a("DEVMGR_HwUniteDeviceMgr", "getUsedDevicesFromStorage size is 1.");
            jte.a(true, true);
        }
    }

    private void p() {
        e eVar = this.c;
        if (eVar != null) {
            eVar.sendEmptyMessage(55);
        }
    }

    private void q() {
        e eVar = this.c;
        if (eVar != null) {
            eVar.sendEmptyMessage(56);
        }
    }

    private void m() {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "Enter migrateDmsDeviceList().");
        Map<String, String> a2 = SharedPreferenceManager.a(this.f, String.valueOf(1000));
        if (a2 == null || a2.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "map is empty.");
            return;
        }
        Set<String> d2 = SharedPreferenceManager.d(this.f, String.valueOf(1000));
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "migrateDmsDeviceList size is ", Integer.valueOf(d2.size()));
        Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = (com.huawei.health.devicemgr.business.entity.DeviceInfo) new Gson().fromJson(it.next().getValue(), com.huawei.health.devicemgr.business.entity.DeviceInfo.class);
            if (deviceInfo != null && jsn.d(deviceInfo.getProductType())) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "migrateDmsDeviceList deviceName is ", deviceInfo.getDeviceName());
                c(deviceInfo.getDeviceIdentify(), deviceInfo);
            } else if (deviceInfo != null) {
                LogUtil.a("DEVMGR_HwUniteDeviceMgr", "migrateDmsDeviceList isRemaining deviceName is ", deviceInfo.getDeviceName());
                d2.remove(deviceInfo.getDeviceIdentify());
                z = true;
            }
        }
        e(d2, z);
    }

    private void c(String str, com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "updateUdsDevice enter.");
        if (str == null) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "identify is null.");
            return;
        }
        DeviceInfo deviceInfo2 = new DeviceInfo();
        deviceInfo2.setDeviceMac(str);
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
        deviceInfo2.setDeviceType(deviceInfo.getProductType());
        deviceInfo2.setDeviceConnectState(deviceInfo.getDeviceConnectState());
        deviceInfo2.setLastConnectedTime(deviceInfo.getLastConnectedTime());
        deviceInfo2.setUsing(deviceInfo.getDeviceActiveState() == 1);
        bio.a(str, deviceInfo2);
        bix bixVar = new bix();
        bixVar.d(str);
        bixVar.d(ConnectMode.GENERAL);
        bixVar.a(false);
        bio.c(str, bixVar);
    }

    private void e(Set<String> set, boolean z) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "deleteDmsDeviceList deleteDmsDeviceList size ", Integer.valueOf(set.size()), "isRemaining is ", Boolean.valueOf(z));
        if (set.size() == 0) {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "Dms device is not delete.");
            return;
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            SharedPreferenceManager.c(this.f, String.valueOf(1000), it.next());
        }
        if (!z) {
            jte.a(false, false);
        }
        jte.a(true, true);
    }

    @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface
    public Map<String, DeviceCapability> queryDeviceCapability(int i, String str, String str2) {
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "enter queryDeviceCapability queryType：", Integer.valueOf(i), "; queryContent:", CommonUtil.l(str), "; tag: ", str2);
        Map<String, DeviceCapability> hashMap = new HashMap<>(4);
        if (i == 1) {
            b(str, hashMap);
        } else if (i == 2) {
            hashMap = g();
        } else if (i == 3) {
            hashMap = d();
        } else if (i == 4) {
            d(hashMap);
        } else if (i == 5) {
            hashMap = h();
        } else {
            LogUtil.h("DEVMGR_HwUniteDeviceMgr", "queryDeviceCapability queryType others");
        }
        LogUtil.a("DEVMGR_HwUniteDeviceMgr", "queryDeviceCapability queryType：", Integer.valueOf(i), ";size:", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    private void b(String str, Map<String, DeviceCapability> map) {
        Map<String, DeviceCapability> d2 = d();
        for (Map.Entry<String, DeviceCapability> entry : d2.entrySet()) {
            String key = entry.getKey();
            if (d2.containsKey(key) && TextUtils.equals(key, str)) {
                map.put(key, entry.getValue());
                return;
            }
        }
    }

    private void d(Map<String, DeviceCapability> map) {
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        if (d2.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : d2) {
                if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getRelationship().equals("main_relationship") && !cvt.c(deviceInfo.getProductType())) {
                    LogUtil.c("DEVMGR_HwUniteDeviceMgr", "queryDeviceCapability device name ", deviceInfo.getDeviceName());
                    map.put(deviceInfo.getDeviceIdentify(), blc.a(this.k.a(deviceInfo.getDeviceIdentify())));
                }
            }
        }
        for (Map.Entry<String, DeviceCapability> entry : jsz.b().e().entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key)) {
                map.put(key, entry.getValue());
            }
        }
    }

    private Map<String, DeviceCapability> d() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b().queryDeviceCapability(3, "", "DEVMGR_HwUniteDeviceMgr");
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        Map<String, UniteDevice> e2 = this.k.e();
        if (d2.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : d2) {
                DeviceCapability e3 = this.k.e(deviceInfo, e2);
                if (e3 != null) {
                    queryDeviceCapability.put(deviceInfo.getDeviceIdentify(), e3);
                }
            }
        }
        Map<String, DeviceCapability> b2 = blc.b();
        if (b2 != null && b2.size() != 0) {
            for (Map.Entry<String, DeviceCapability> entry : b2.entrySet()) {
                if (!queryDeviceCapability.containsKey(entry.getKey())) {
                    queryDeviceCapability.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return queryDeviceCapability;
    }

    private Map<String, DeviceCapability> g() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b().queryDeviceCapability(2, "", "DEVMGR_HwUniteDeviceMgr");
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        Map<String, UniteDevice> e2 = this.k.e();
        if (d2.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : d2) {
                DeviceCapability e3 = this.k.e(deviceInfo, e2);
                if (deviceInfo.getDeviceConnectState() == 2 && e3 != null) {
                    queryDeviceCapability.put(deviceInfo.getDeviceIdentify(), e3);
                }
            }
        }
        return queryDeviceCapability;
    }

    private Map<String, DeviceCapability> h() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b().queryDeviceCapability(2, "", "DEVMGR_HwUniteDeviceMgr");
        List<com.huawei.health.devicemgr.business.entity.DeviceInfo> d2 = this.k.d();
        Map<String, UniteDevice> e2 = this.k.e();
        if (d2.size() != 0) {
            for (com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo : d2) {
                DeviceCapability e3 = this.k.e(deviceInfo, e2);
                if (deviceInfo.getDeviceConnectState() == 2 && deviceInfo.getRelationship().equals("main_relationship") && e3 != null) {
                    queryDeviceCapability.put(deviceInfo.getDeviceIdentify(), e3);
                }
            }
        }
        return queryDeviceCapability;
    }

    public boolean a() {
        return this.m;
    }
}
