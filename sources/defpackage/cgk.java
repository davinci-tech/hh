package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.health.R;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.kit.hwch.ch18.bean.WeightCmd;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.WeightOfflineDataSelectActivity;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/* loaded from: classes3.dex */
public class cgk extends GattMeasureController {
    private static cgk e;
    private TimerTask ag;
    private String aj;
    private Timer ak;
    private d am;
    private BluetoothGattService ao;
    private a ap;
    private BluetoothGattCharacteristic aq;
    private SharedPreferences ar;
    private TimerTask at;
    private int au;
    private Timer av;
    private int ax;
    private String bc;
    private BluetoothGatt f;
    private dfd g;
    private HealthDevice k;
    private WeightCmd l;
    private cgp o;
    private boolean q;
    private boolean t;
    private static final Map<Integer, Integer> d = Collections.unmodifiableMap(new HashMap<Integer, Integer>() { // from class: cgk.5
        {
            put(1, 0);
            put(0, 1);
        }
    });

    /* renamed from: a, reason: collision with root package name */
    private static final Object f701a = new Object();
    private int r = 173;
    private int b = 29;
    private int as = 1;
    private HandlerThread n = new HandlerThread("HWChMeasureController");
    private boolean ad = false;
    private boolean x = false;
    private boolean ac = false;
    private boolean v = false;
    private boolean w = false;
    private boolean p = false;
    private boolean af = false;
    private boolean u = false;
    private ckc ba = ckc.a(BaseApplication.getContext());
    private Context j = BaseApplication.getContext();
    private ArrayList<ckm> ay = new ArrayList<>(16);
    private ArrayList<ckm> aw = new ArrayList<>(16);
    private ArrayList<HealthData> al = new ArrayList<>(16);
    private ArrayList<IHealthDeviceCallback> i = new ArrayList<>(16);
    private boolean ai = false;
    private int h = 0;
    private int an = 2000;
    private boolean z = false;
    private boolean ae = false;
    private boolean s = false;
    private boolean ah = false;
    private boolean y = false;
    private boolean ab = false;
    private boolean aa = false;
    private BluetoothGattCallback c = new BluetoothGattCallback() { // from class: cgk.4
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            LogUtil.a("HWChMeasureController", "HWChMeasureController on status ", Integer.valueOf(i), " newState ", Integer.valueOf(i2));
            cgk.this.EJ_(bluetoothGatt, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            LogUtil.a("HWChMeasureController", "HWChMeasureController onDescriptorWrite");
            cgk.this.b();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            LogUtil.a("HWChMeasureController", "Device-->SDK : ", HexUtils.d(bluetoothGattCharacteristic.getValue()), " , tag : ", uuid);
            if (cgk.this.ax != 2) {
                return;
            }
            LogUtil.a("HWChMeasureController", "mState == BluetoothProfile.STATE_CONNECTED");
            cgk.this.EH_(bluetoothGattCharacteristic, uuid);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            LogUtil.a("HWChMeasureController", "HWChMeasureController onServicesDiscovered");
            cgk.this.am.removeCallbacksAndMessages(null);
            if (i == 0) {
                LogUtil.c("HWChMeasureController", "HWChMeasureController GATT_SUCCESS");
                cgk.this.EL_(bluetoothGatt);
                return;
            }
            LogUtil.b("HWChMeasureController", "HWChMeasureController onServicesDiscovered failed");
            Bundle bundle = new Bundle();
            bundle.putString(BleConstants.MEASUREKIT_ID, cgk.this.mDevice.getMeasureKitUuid());
            LogUtil.bRh_(907127003, "HWChMeasureController", bundle, false, "No GATT mService found.", bundle);
            cgk cgkVar = cgk.this;
            cgkVar.e(cgkVar.k, 8);
        }
    };
    private EventBus.ICallback m = new EventBus.ICallback() { // from class: cgk.2
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            LogUtil.a("HWChMeasureController", " action is ", bVar.e());
            if ("weight_device_clear_user_data".equals(bVar.e())) {
                cgk.this.s = true;
                if (cgk.this.ac && cgk.this.v && cgk.this.w) {
                    LogUtil.a("HWChMeasureController", "send clear user data === 1");
                    cgk.this.z = true;
                    cgk.this.ae = false;
                    cgk.this.b(cgg.a(), WeightCmd.ACK_CLEAR_USER_INFO);
                    return;
                }
                LogUtil.a("HWChMeasureController", " mIsReceiveUserCheck && mIsReceiveClockCheck && mIsReceiveCustomCheck false");
                return;
            }
            if ("weight_device_ota_update".equals(bVar.e())) {
                Intent Km_ = bVar.Km_();
                cgm.d().d(Km_.getStringExtra("scalePath"), Km_.getStringExtra("blePath"));
                return;
            }
            if ("get_scale_version_code".equals(bVar.e())) {
                LogUtil.a("HWChMeasureController", " action is EVEBUS_SINGLE_GET_SCALE_VERSION_CODE");
                if (cgk.this.o == null) {
                    cgk.this.e(cgg.d());
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("bleVersion", cgk.this.o.b());
                intent.putExtra("scaleVersion", cgk.this.o.a());
                EventBus.d(new EventBus.b("set_scale_version_code", intent));
                return;
            }
            LogUtil.a("HWChMeasureController", "unknown event.getAction() ", bVar.e());
        }
    };

    static /* synthetic */ int l(cgk cgkVar) {
        int i = cgkVar.h;
        cgkVar.h = i + 1;
        return i;
    }

    private cgk() {
        h();
        this.ar = this.j.getSharedPreferences("weightUser", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EH_(BluetoothGattCharacteristic bluetoothGattCharacteristic, String str) {
        if ("0000faa1-0000-1000-8000-00805f9b34fb".equalsIgnoreCase(str)) {
            LogUtil.c("HWChMeasureController", "HWChMeasureController write data" + Arrays.toString(bluetoothGattCharacteristic.getValue()));
            return;
        }
        if ("0000faa2-0000-1000-8000-00805f9b34fb".equalsIgnoreCase(str)) {
            boolean z = !this.af && this.au == -6;
            if (this.au == -5 || z) {
                ending();
                return;
            }
            LogUtil.a("HWChMeasureController", "not ending");
            a(true);
            cgn a2 = cgg.a(bluetoothGattCharacteristic.getValue());
            LogUtil.a("HWChMeasureController", " HWChMeasureController parseResult ", Integer.valueOf(a2.b()));
            Object d2 = a2.d();
            int b = a2.b();
            if (b == 1) {
                this.p = true;
            } else {
                this.p = false;
            }
            byte[] value = bluetoothGattCharacteristic.getValue();
            e(b);
            c(b, a2, d2);
            e(b, a2, d2, value);
            a(b, a2, d2, value);
            d(b, a2, d2, value);
            WeightCmd c = a2.c();
            byte[] e2 = a2.e();
            if (e2 == null || e2.length <= 0) {
                LogUtil.a("HWChMeasureController", "feedBackDatas is null or empty");
                return;
            } else if (c != null) {
                LogUtil.a("HWChMeasureController", "targetAck safeguardSendData ", c.name());
                b(e2, c);
                return;
            } else {
                LogUtil.a("HWChMeasureController", "writeCmdBytes");
                e(e2);
                return;
            }
        }
        LogUtil.a("HWChMeasureController", "unknown characterId");
    }

    private void d(int i, cgn cgnVar, Object obj, byte[] bArr) {
        switch (i) {
            case 19:
                cgnVar.d(cgg.e());
                cgnVar.b(WeightCmd.ACK_SET_CLOCK);
                break;
            case 20:
                if (!this.ab) {
                    this.ab = true;
                    cgm.d().c(cgnVar.b(), bArr, obj);
                    break;
                } else {
                    LogUtil.a("HWChMeasureController", "mIsSendShPckFirst true");
                    break;
                }
            case 21:
                if (!this.aa) {
                    this.aa = true;
                    cgm.d().c(cgnVar.b(), bArr, obj);
                    break;
                } else {
                    LogUtil.a("HWChMeasureController", "mIsSendShPckSecond true");
                    break;
                }
        }
    }

    private void a(int i, cgn cgnVar, Object obj, byte[] bArr) {
        switch (i) {
            case 13:
                this.ad = false;
                break;
            case 14:
                LogUtil.a("HWChMeasureController", "Received the alarm response");
                break;
            case 15:
                if (!this.y) {
                    this.y = true;
                    this.l = WeightCmd.ACK_GET_OTA_UPGRADE_REQ;
                    cgm.d().c(cgnVar.b(), bArr, obj);
                    break;
                } else {
                    LogUtil.a("HWChMeasureController", "mIsReadyOTA true");
                    break;
                }
            case 17:
                this.af = false;
                e();
                LogUtil.a("HWChMeasureController", "receiver histroy data done");
                break;
            case 18:
                this.l = WeightCmd.ACK_USER_AUTH;
                if (obj instanceof Boolean) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    this.ac = true;
                    if (!booleanValue) {
                        d(cgnVar);
                        break;
                    } else {
                        LogUtil.a("HWChMeasureController", "Distributed clock synchronization");
                        cgnVar.d(cgg.e());
                        cgnVar.b(WeightCmd.ACK_SET_CLOCK);
                        break;
                    }
                } else {
                    LogUtil.h("HWChMeasureController", "checkResult is not instanceof Boolean");
                    break;
                }
        }
    }

    private void d(cgn cgnVar) {
        LogUtil.a("HWChMeasureController", "Bind instructions issued by the user");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!Utils.i()) {
            LogUtil.a("HWChMeasureController", "TYPE_USER_CHECK userId == ", c());
            cgnVar.d(cgg.b(c()));
        } else {
            cgnVar.d(cgg.b(accountInfo));
        }
        cgnVar.b(WeightCmd.ACK_SET_CUSTOM);
    }

    private void e(int i, cgn cgnVar, Object obj, byte[] bArr) {
        switch (i) {
            case 6:
                this.l = WeightCmd.ACK_SET_CLOCK;
                LogUtil.a("HWChMeasureController", "Complete instructions received time synchronization");
                this.v = true;
                a(cgnVar, (cgo) null);
                cgnVar.b(WeightCmd.ACK_SET_CUSTOM);
                break;
            case 7:
                this.af = true;
                if (obj instanceof cgo) {
                    cgo cgoVar = (cgo) obj;
                    e(this.k, d(cgoVar));
                    if (this.q) {
                        a(cgnVar, cgoVar);
                        break;
                    }
                }
                break;
            case 8:
                d(obj);
                break;
            case 9:
                this.l = WeightCmd.ACK_SET_OTA;
                cgm.d().c(cgnVar.b(), bArr, obj);
                break;
            case 10:
                l();
                this.l = WeightCmd.ACK_SET_OTA;
                cgm.d().c(cgnVar.b(), bArr, obj);
                break;
        }
    }

    private void d(Object obj) {
        this.af = true;
        if (obj instanceof cgo) {
            cgo cgoVar = (cgo) obj;
            if (!Utils.i()) {
                cgoVar.c(c());
            } else {
                cgoVar.c(MultiUsersManager.INSTANCE.getCurrentUser().i());
            }
            this.ba.a(cgoVar);
            LogUtil.a("HWChMeasureController", "receiver histroy data");
        }
    }

    private void c(int i, cgn cgnVar, Object obj) {
        if (i == 3) {
            this.l = WeightCmd.ACK_SET_CUSTOM;
            this.w = true;
            if (this.z) {
                this.z = false;
                this.s = false;
                a(cgnVar, (cgo) null);
                this.ae = true;
                cgnVar.b(WeightCmd.ACK_SET_CUSTOM);
                return;
            }
            f();
            return;
        }
        if (i == 4 && (obj instanceof cgp)) {
            this.o = (cgp) obj;
            if ("33123f39-7fc1-420b-9882-a4b0d6c61100".equals(this.aj)) {
                this.o.d(r3.a() - 32768);
            } else {
                LogUtil.a("HWChMeasureController", "DEVICE_ABROAD_HUAWEI_WEIGHT ! = (mProductId)");
            }
            Intent intent = new Intent();
            intent.putExtra("bleVersion", this.o.b());
            intent.putExtra("scaleVersion", this.o.a());
            coz.d("HWChMeasureController", d(this.o.a()), this.j, this.bc);
            cpa.j(this.bc, d(this.o.a()));
            EventBus.d(new EventBus.b("set_scale_version_code", intent));
        }
    }

    private void f() {
        int i = this.au;
        if (i == -1) {
            LogUtil.a("HWChMeasureController", "waiting instruction, maintain connection");
            if (this.ae) {
                this.ae = false;
                e(this.mDevice, -3);
                return;
            } else {
                e(cgg.d());
                return;
            }
        }
        if (i == -2) {
            LogUtil.a("HWChMeasureController", "user authentication is completed, the binding of success");
            e(cgg.d());
            e(this.mDevice, -2);
            return;
        }
        e(cgg.d());
    }

    private void e(int i) {
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.ad = false;
            this.ah = false;
            l();
            n();
            w();
            s();
            e(this.k, 13);
            return;
        }
        this.ah = true;
        boolean z = !this.af && this.au == -6;
        if (this.au == -5 || z) {
            ending();
            return;
        }
        LogUtil.a("HWChMeasureController", "parseResult not ending");
        Object[] objArr = new Object[2];
        objArr[0] = "TYPE_WAKEUP ";
        objArr[1] = Boolean.valueOf((this.ad && this.ac) ? false : true);
        LogUtil.a("HWChMeasureController", objArr);
        boolean z2 = this.ad;
        if ((!z2 || !this.ac) && this.au != -6) {
            this.ad = true;
            t();
            p();
        } else {
            Object[] objArr2 = new Object[2];
            objArr2[0] = "parseResult (!mIsSendWakeup || !mIsReceiveUserCheck) ";
            objArr2[1] = Boolean.valueOf((z2 && this.ac) ? false : true);
            LogUtil.a("HWChMeasureController", objArr2);
        }
        e(this.k, 14);
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.ax == 2) {
            a(true);
            this.am.postDelayed(new Runnable() { // from class: cgk.3
                @Override // java.lang.Runnable
                public void run() {
                    boolean z = !cgk.this.af && cgk.this.au == -6;
                    if (cgk.this.au == -5 || z) {
                        cgk.this.ending();
                        return;
                    }
                    LogUtil.a("HWChMeasureController", "HWChMeasureController not onDescriptorWrite 1");
                    if (!cgk.this.ac && cgk.this.au != -6) {
                        cgk.this.t();
                        cgk.this.p();
                    } else {
                        LogUtil.a("HWChMeasureController", "HWChMeasureController not onDescriptorWrite 2");
                    }
                }
            }, 1000L);
        } else {
            LogUtil.a("HWChMeasureController", "mState != BluetoothProfile.STATE_CONNECTED");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EJ_(BluetoothGatt bluetoothGatt, int i) {
        this.ad = false;
        o();
        this.f = bluetoothGatt;
        if (i != 2) {
            if (i == 0) {
                m();
                return;
            } else {
                LogUtil.a("HWChMeasureController", "new state else", Integer.valueOf(i));
                return;
            }
        }
        o();
        this.ax = 2;
        d dVar = this.am;
        if (dVar != null) {
            dVar.removeMessages(1);
            this.am.sendEmptyMessageDelayed(1, 500L);
        } else {
            LogUtil.a("HWChMeasureController", "mMsgHandler == null");
        }
        this.u = true;
        this.bc = this.f.getDevice().getAddress();
        e(this.mDevice, 2);
    }

    public static cgk d() {
        cgk cgkVar;
        synchronized (f701a) {
            if (e == null) {
                e = new cgk();
            } else {
                LogUtil.a("HWChMeasureController", "sInstance != null");
            }
            cgkVar = e;
        }
        return cgkVar;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        LogUtil.a("HWChMeasureController", "HWChMeasureController start mState ", Integer.valueOf(this.ax));
        return true;
    }

    private void h() {
        this.n.start();
        this.am = new d(this.n.getLooper());
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        int i;
        LogUtil.a("HWChMeasureController", "HWChMeasureController cleanup ", "mIsSyncData:", Boolean.valueOf(this.af), "; mType== ", Integer.valueOf(this.au));
        boolean z = (this.af || (i = this.au) == -4 || i == -1) ? false : true;
        if (this.au == -5 || z) {
            r();
            this.ax = 0;
            this.ae = false;
            this.u = false;
            this.z = false;
            w();
            s();
            return;
        }
        LogUtil.a("HWChMeasureController", "no cleanup");
    }

    private void r() {
        LogUtil.a("HWChMeasureController", "HWChMeasureController setCleanData ", "mIsSyncData:", Boolean.valueOf(this.af), "; mType= ", Integer.valueOf(this.au));
        this.x = false;
        this.ao = null;
        this.aq = null;
        this.s = false;
        this.ah = false;
        this.bc = null;
        synchronized (f701a) {
            this.i.clear();
        }
        l();
        w();
        a aVar = this.ap;
        if (aVar != null) {
            aVar.c(false);
        } else {
            LogUtil.a("HWChMeasureController", "mRun == null");
        }
        this.ad = false;
        this.o = null;
        o();
        s();
        i();
        EventBus.a(this.m, "weight_device_clear_user_data", "weight_device_ota_update", "get_scale_version_code");
    }

    private void l() {
        this.y = false;
        this.ab = false;
        this.aa = false;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        d dVar;
        int i;
        LogUtil.a("HWChMeasureController", "HWChMeasureController ending mIsSyncData:" + this.af + "; mType= " + this.au);
        boolean z = (this.af || (i = this.au) == -4 || i == -1) ? false : true;
        if (this.au == -5 || z) {
            LogUtil.a("HWChMeasureController", "HWChMeasureController ending");
            r();
            if (this.n != null && (dVar = this.am) != null) {
                dVar.removeCallbacksAndMessages(null);
                this.am.removeCallbacks(this.ap);
            } else {
                LogUtil.a("HWChMeasureController", "mHandlerThread or mMsgHandler = null");
            }
            super.ending();
        }
    }

    private void a(IHealthDeviceCallback iHealthDeviceCallback) {
        LogUtil.a("HWChMeasureController", "registerCallBack----start----");
        synchronized (f701a) {
            if (iHealthDeviceCallback == null) {
                LogUtil.a("HWChMeasureController", "registerCallBack callback is null");
            } else if (!this.i.contains(iHealthDeviceCallback)) {
                LogUtil.a("HWChMeasureController", "registerCallBack add success");
                this.i.add(iHealthDeviceCallback);
            }
        }
    }

    public void e(IHealthDeviceCallback iHealthDeviceCallback) {
        synchronized (f701a) {
            if (iHealthDeviceCallback != null) {
                if (this.i.contains(iHealthDeviceCallback)) {
                    this.i.remove(iHealthDeviceCallback);
                }
            }
            Object[] objArr = new Object[4];
            objArr[0] = "callback != null ";
            objArr[1] = Boolean.valueOf(iHealthDeviceCallback != null);
            objArr[2] = " mCallBacks.contains(callback) ";
            objArr[3] = Boolean.valueOf(this.i.contains(iHealthDeviceCallback));
            LogUtil.a("HWChMeasureController", objArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HealthDevice healthDevice, int i) {
        synchronized (f701a) {
            Iterator<IHealthDeviceCallback> it = this.i.iterator();
            while (it.hasNext()) {
                IHealthDeviceCallback next = it.next();
                if (next != null) {
                    next.onStatusChanged(healthDevice, i);
                } else {
                    LogUtil.a("HWChMeasureController", "callback == null");
                }
            }
        }
    }

    private void e(HealthDevice healthDevice, HealthData healthData) {
        synchronized (f701a) {
            Iterator<IHealthDeviceCallback> it = this.i.iterator();
            while (it.hasNext()) {
                IHealthDeviceCallback next = it.next();
                if (next != null) {
                    next.onDataChanged(healthDevice, healthData);
                } else {
                    LogUtil.a("HWChMeasureController", "callback == null");
                }
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        EventBus.d(this.m, 0, "weight_device_clear_user_data", "weight_device_ota_update", "get_scale_version_code");
        LogUtil.a("HWChMeasureController", "HWChMeasureController prepare");
        if (iHealthDeviceCallback != null) {
            a(iHealthDeviceCallback);
        } else {
            LogUtil.a("HWChMeasureController", "cb == null");
        }
        if (bundle != null) {
            this.au = bundle.getInt("type");
            this.aj = bundle.getString("productId");
            this.t = bundle.getBoolean("clearData", false);
            this.q = bundle.getBoolean("activeMeasure", false);
            LogUtil.a("HWChMeasureController", "HWChMeasureController prepare type", Integer.valueOf(this.au), "; mIsActiveMeasure=", Boolean.valueOf(this.q));
        } else {
            this.t = false;
            this.au = 0;
        }
        q();
        if (healthDevice == null) {
            return false;
        }
        e(healthDevice, this.ah ? 14 : 13);
        e(healthDevice, this.u ? 2 : 3);
        g();
        this.k = healthDevice;
        cgg.c(healthDevice.getAddress());
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            LogUtil.a("super.prepare false", new Object[0]);
            return false;
        }
        if (bundle != null) {
            this.r = bundle.getInt("height");
            this.as = bundle.getInt("sex");
            this.b = bundle.getInt("age");
            LogUtil.a("HWChMeasureController", "HWChMeasureController productId is:", this.aj);
            return true;
        }
        LogUtil.h("HWChMeasureController", "HWChMeasureController args is null");
        return true;
    }

    private void q() {
        int i = this.au;
        if (i == -4 || i == -2) {
            this.an = 2000;
            this.x = true;
        } else {
            this.an = 8000;
            this.x = false;
        }
    }

    private void g() {
        HiHealthManager.d(cpp.a()).fetchUserData(new HiCommonListener() { // from class: cgk.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                List list;
                if (obj != null) {
                    try {
                    } catch (ClassCastException e2) {
                        LogUtil.c("HWChMeasureController", "HWChMeasureController _prepare--ClassCastException", e2.getMessage());
                    }
                    if (obj instanceof List) {
                        list = (List) obj;
                        cgk.this.e((List<HiUserInfo>) list);
                    }
                    list = null;
                    cgk.this.e((List<HiUserInfo>) list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("HWChMeasureController", "HWChMeasureController onFailure");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiUserInfo> list) {
        if (list != null) {
            for (HiUserInfo hiUserInfo : list) {
                if (hiUserInfo.getRelateType() == 0) {
                    this.r = hiUserInfo.getHeight();
                    this.as = hiUserInfo.getGender();
                    this.b = hiUserInfo.getAge();
                    return;
                }
                LogUtil.a("HWChMeasureController", "prepareUserData hiUserInfo.getRelateType() ", Integer.valueOf(hiUserInfo.getRelateType()));
            }
            return;
        }
        LogUtil.a("HWChMeasureController", "prepareUserData userInfos == null");
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        LogUtil.a("HWChMeasureController", "HWChMeasureController getGattCallbackImpl");
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EI_(BluetoothGatt bluetoothGatt, d dVar) {
        if (bluetoothGatt != null) {
            LogUtil.a("HWChMeasureController", "start to close gatt...");
            try {
                bluetoothGatt.close();
            } catch (SecurityException e2) {
                LogUtil.b("HWChMeasureController", "closeBluetoothGattAndRemoveMsg SecurityException:", ExceptionUtils.d(e2));
            }
            this.f = null;
        } else {
            LogUtil.a("HWChMeasureController", "closeBluetoothGattAndRemoveMsg bleGatt == null");
        }
        if (this.am != null) {
            dVar.removeCallbacksAndMessages(null);
        } else {
            LogUtil.a("HWChMeasureController", "mMsgHandler == null");
        }
    }

    private void m() {
        LogUtil.a("HWChMeasureController", "HeartRateMeasureController setBlueGattStatus type == ", Integer.valueOf(this.au));
        this.ax = 0;
        this.ae = false;
        this.af = false;
        this.u = false;
        this.z = false;
        this.ao = null;
        w();
        s();
        i();
        LogUtil.b("HWChMeasureController", "HWChMeasureController onConnectionStateChange STATUS_DISCONNECTED 1");
        if (this.x) {
            LogUtil.a("HWChMeasureController", "HeartRateMeasureController mIsFirstConnect enter");
            EI_(this.f, this.am);
            d dVar = this.am;
            if (dVar != null) {
                dVar.sendEmptyMessageDelayed(4, 500L);
            } else {
                LogUtil.a("HWChMeasureController", "mMsgHandler == null");
            }
        } else {
            LogUtil.a("HWChMeasureController", "mIsFirstConnect false");
        }
        e(this.k, 3);
    }

    private String d(int i) {
        return (i / 4096) + "." + ((i / 256) % 16) + "." + (i % 256);
    }

    private void a(cgn cgnVar, cgo cgoVar) {
        cgu cguVar = new cgu();
        if (!Utils.i()) {
            cguVar.b(c());
            LogUtil.c("HWChMeasureController", "HWChMeasureController getSendData = ", c());
        } else {
            String i = MultiUsersManager.INSTANCE.getCurrentUser().i();
            if (!TextUtils.isEmpty(i)) {
                cguVar.b(i);
                LogUtil.c("HWChMeasureController", "HWChMeasureController setUserIdToDevice getSendData = ", MultiUsersManager.INSTANCE.getCurrentUser().i());
            } else {
                LogUtil.b("HWChMeasureController", "the current user's uuid is null send data stop");
                return;
            }
        }
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser != null) {
            cguVar.e(currentUser.a());
            cguVar.c(currentUser.d());
            this.as = currentUser.c();
            LogUtil.a("HWChMeasureController", "user name: ", currentUser.h(), ", uuid: ", currentUser.i());
        } else {
            LogUtil.a("HWChMeasureController", "user info is null.");
            cguVar.e(this.b);
            cguVar.c(this.r);
        }
        cguVar.d(a(this.as));
        if (cgoVar != null) {
            LogUtil.a("HWChMeasureController", "getSendData measureResult writeBack.");
            cguVar.e(cgoVar.i());
        } else {
            List<cfe> singleUserWeightData = WeightDataManager.INSTANCE.getSingleUserWeightData(MultiUsersManager.INSTANCE.getCurrentUser().i(), true);
            if (singleUserWeightData.size() > 0 && singleUserWeightData.get(0) != null) {
                cguVar.e((float) singleUserWeightData.get(0).ax());
            } else {
                cguVar.e(60.0f);
            }
        }
        cgnVar.d(cgg.b(cguVar));
    }

    private int a(int i) {
        Integer num = d.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private HealthData d(cgo cgoVar) {
        ckm ckmVar = new ckm();
        ckmVar.setWeight(cgoVar.i());
        ckmVar.setBodyFatRat(cgoVar.c());
        ckmVar.f(cgoVar.g());
        ckmVar.setStartTime(System.currentTimeMillis());
        ckmVar.setEndTime(System.currentTimeMillis());
        if (!Utils.i()) {
            ckmVar.e(c());
        } else {
            cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
            if (currentUser != null) {
                String i = currentUser.i();
                LogUtil.c("HWChMeasureController", "measureDataToHealthData userId = ", i);
                ckmVar.e(i);
            }
        }
        return ckmVar;
    }

    public void EL_(BluetoothGatt bluetoothGatt) {
        LogUtil.a("HWChMeasureController", "HWChMeasureController initService");
        this.f = bluetoothGatt;
        if (this.ao != null) {
            return;
        }
        LogUtil.a("HWChMeasureController", "mService == null");
        if (bluetoothGatt == null) {
            LogUtil.h("HWChMeasureController", "initService mBluetoothGatt is null.");
            return;
        }
        try {
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("0000faa0-0000-1000-8000-00805f9b34fb"));
            this.ao = service;
            if (service != null) {
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.fromString("0000faa2-0000-1000-8000-00805f9b34fb"));
                this.aq = characteristic;
                if (characteristic == null) {
                    ReleaseLogUtil.d("R_HWChMeasureController", "initService mReadCharacteristic == null");
                    return;
                }
                bluetoothGatt.setCharacteristicNotification(characteristic, true);
                BluetoothGattDescriptor descriptor = this.aq.getDescriptor(UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID));
                if (descriptor != null) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    bluetoothGatt.writeDescriptor(descriptor);
                } else {
                    LogUtil.a("HWChMeasureController", "descriptor == null");
                }
                LogUtil.c("HWChMeasureController", "HWChMeasureController mHealthDevice.getAddress():", this.k.getAddress());
            }
        } catch (SecurityException e2) {
            LogUtil.b("HWChMeasureController", "initService SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        SharedPreferences.Editor edit;
        if (!Utils.i()) {
            if (this.au == -2) {
                SharedPreferences sharedPreferences = this.ar;
                if (sharedPreferences != null) {
                    edit = sharedPreferences.edit();
                } else {
                    edit = this.j.getSharedPreferences("weightUser", 0).edit();
                }
                if (TextUtils.isEmpty(c())) {
                    edit.putString("weightUser_id", System.currentTimeMillis() + "");
                    edit.commit();
                } else {
                    LogUtil.a("HWChMeasureController", "getNoCloudUserId() is no empty");
                }
                LogUtil.c("HWChMeasureController", "HWChMeasureController setUserIdToDevice getCurrentUser = " + c());
                b(cgg.a(c()), WeightCmd.ACK_USER_AUTH);
                return;
            }
            LogUtil.c("HWChMeasureController", "HWChMeasureController setUserIdToDevice getCurrentUser2 = " + c());
            b(cgg.a(c()), WeightCmd.ACK_USER_AUTH);
            return;
        }
        b(cgg.a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)), WeightCmd.ACK_USER_AUTH);
    }

    private void a(boolean z) {
        synchronized (this) {
            Timer timer = this.av;
            if (timer != null) {
                timer.cancel();
                this.av = null;
            } else {
                LogUtil.a("HWChMeasureController", "timer == null");
            }
            TimerTask timerTask = this.at;
            if (timerTask != null) {
                timerTask.cancel();
                this.at = null;
            } else {
                LogUtil.a("HWChMeasureController", "mTask == null");
            }
            if (!z && this.au != -1) {
                LogUtil.a("HWChMeasureController", "not start timer");
            }
            this.av = new Timer("HWChMeasureController");
            TimerTask timerTask2 = new TimerTask() { // from class: cgk.10
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    cgk.this.e(cgg.c());
                }
            };
            this.at = timerTask2;
            this.av.schedule(timerTask2, 2000L, 2000L);
        }
    }

    public void e(byte[] bArr) {
        BluetoothGatt bluetoothGatt = this.f;
        if (bluetoothGatt != null) {
            if (this.ax == 2) {
                EK_(bluetoothGatt.getService(UUID.fromString("0000faa0-0000-1000-8000-00805f9b34fb")), bluetoothGatt, bArr);
                return;
            } else {
                LogUtil.a("HWChMeasureController", "mState != BluetoothProfile.STATE_CONNECTED");
                return;
            }
        }
        LogUtil.a("HWChMeasureController", "mBluetoothGatt is null");
    }

    private void EK_(BluetoothGattService bluetoothGattService, BluetoothGatt bluetoothGatt, byte[] bArr) {
        String str;
        if (bluetoothGattService != null) {
            try {
                BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(UUID.fromString("0000faa1-0000-1000-8000-00805f9b34fb"));
                if (characteristic != null) {
                    characteristic.setValue(bArr);
                    characteristic.setWriteType(1);
                    boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(characteristic);
                    Object[] objArr = new Object[6];
                    objArr[0] = "SDK-->Device : ";
                    objArr[1] = HexUtils.d(bArr);
                    if (characteristic.getUuid() == null) {
                        str = "tag : null";
                    } else {
                        str = "tag : " + characteristic.getUuid().toString();
                    }
                    objArr[2] = str;
                    objArr[3] = "Write key:";
                    objArr[4] = " isSuccess:";
                    objArr[5] = Boolean.valueOf(writeCharacteristic);
                    LogUtil.a("HWChMeasureController", objArr);
                    return;
                }
                LogUtil.a("HWChMeasureController", "gattCharacteristic == null");
                return;
            } catch (SecurityException e2) {
                LogUtil.b("HWChMeasureController", "sendCmdToDevice SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        LogUtil.a("HWChMeasureController", "write BluetoothGattService is null");
    }

    private void w() {
        synchronized (this) {
            a(false);
        }
    }

    private void i() {
        synchronized (this) {
            BluetoothGatt bluetoothGatt = this.f;
            if (bluetoothGatt != null) {
                try {
                    bluetoothGatt.disconnect();
                    bluetoothGatt.close();
                } catch (SecurityException e2) {
                    LogUtil.b("HWChMeasureController", "disConnectBle SecurityException:", ExceptionUtils.d(e2));
                }
            } else {
                LogUtil.a("HWChMeasureController", "disConnectBle mBluetoothGatt is null");
            }
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        synchronized (this) {
            s();
            this.ak = new Timer("HWChMeasureController");
            TimerTask timerTask = new TimerTask() { // from class: cgk.9
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    LogUtil.a("HWChMeasureController", "HWChMeasureController startMeasureTimeTask 1");
                    cgk.this.k();
                }
            };
            this.ag = timerTask;
            long j = this.an;
            this.ak.schedule(timerTask, j, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.t) {
            LogUtil.a("HWChMeasureController", "HWChMeasureController startMeasureTimeTask 2");
            if (!this.ac) {
                LogUtil.a("HWChMeasureController", "HWChMeasureController startMeasureTimeTask 3");
                t();
                return;
            }
            if (this.v && this.w) {
                if (this.s) {
                    LogUtil.a("HWChMeasureController", "send clear user data");
                    this.z = true;
                    this.ae = false;
                    b(cgg.a(), WeightCmd.ACK_CLEAR_USER_INFO);
                    return;
                }
                LogUtil.a("HWChMeasureController", "no send clear user data ");
                return;
            }
            t();
            return;
        }
        if (this.p) {
            if (!this.ac || !this.v || !this.w) {
                t();
                return;
            } else {
                LogUtil.a("HWChMeasureController", "no send setUserIdToDevice");
                return;
            }
        }
        LogUtil.a("HWChMeasureController", "mIsAgainSendData false");
    }

    private void s() {
        Timer timer = this.ak;
        if (timer != null) {
            timer.cancel();
            this.ak = null;
        } else {
            LogUtil.a("HWChMeasureController", "stopMeasureTimeTask mMeasureTimer == null");
        }
        TimerTask timerTask = this.ag;
        if (timerTask != null) {
            timerTask.cancel();
            this.ag = null;
        } else {
            LogUtil.a("HWChMeasureController", "stopMeasureTimeTask mMeasureTask == null");
        }
    }

    private void o() {
        this.p = false;
        this.ac = false;
        this.v = false;
        this.w = false;
        this.u = false;
    }

    public String c() {
        SharedPreferences sharedPreferences = this.ar;
        if (sharedPreferences != null) {
            return sharedPreferences.getString("weightUser_id", "");
        }
        LogUtil.a("HWChMeasureController", "mSharedPreferences == null");
        return "";
    }

    private void n() {
        if (!Utils.i()) {
            b(cgg.e(c()), WeightCmd.ACK_GET_RECORD);
        } else {
            b(cgg.e(MultiUsersManager.INSTANCE.getCurrentUser().i()), WeightCmd.ACK_GET_RECORD);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr, WeightCmd weightCmd) {
        LogUtil.a("HWChMeasureController", "safeguardSendData ", weightCmd.name());
        a aVar = this.ap;
        if (aVar != null) {
            aVar.c(false);
            this.am.removeCallbacks(this.ap);
        } else {
            LogUtil.a("HWChMeasureController", "safeguardSendData mRun == null");
        }
        this.ap = new a(bArr, weightCmd);
        this.l = weightCmd;
        LogUtil.a("HWChMeasureController", "executorService.execute(mRun)");
        this.am.postDelayed(this.ap, 500L);
    }

    public void e() {
        synchronized (this) {
            this.ay.clear();
            this.aw.clear();
            this.al.clear();
            if (!Utils.i()) {
                this.ay.addAll(this.ba.b(c()));
            } else {
                this.ay.addAll(this.ba.b(MultiUsersManager.INSTANCE.getCurrentUser().i()));
            }
            LogUtil.a("HWChMeasureController", "weight offline data size is :", Integer.valueOf(this.ay.size()));
            if (this.ay.size() > 0) {
                Iterator<ckm> it = this.ay.iterator();
                while (it.hasNext()) {
                    ckm next = it.next();
                    next.e(false);
                    if (next.q()) {
                        this.aw.add(next);
                    } else {
                        this.al.add(next);
                    }
                }
                this.g = new dfd(10006);
                j();
                int size = this.ay.size();
                if (size > 0) {
                    double doubleValue = new BigDecimal(this.aw.size() / size).setScale(2, RoundingMode.DOWN).doubleValue();
                    LogUtil.a("HWChMeasureController", "normal data size ==", this.al.size() + "suspected data size ==" + this.aw.size());
                    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(3);
                    linkedHashMap.put("click", "1");
                    linkedHashMap.put("time", String.valueOf(System.currentTimeMillis()));
                    linkedHashMap.put("proportion", String.valueOf(doubleValue));
                    OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.HEALTH_PLUGIN_DEVICE_WEIGHT_BODYFATSCALE_OFFLINE_DATA_2060029.value(), linkedHashMap);
                }
            } else {
                d dVar = this.am;
                if (dVar != null) {
                    dVar.sendEmptyMessage(10);
                } else {
                    LogUtil.a("HWChMeasureController", "queryHasOfflineData mMsgHandler == null");
                }
            }
        }
    }

    private void j() {
        ArrayList<HealthData> arrayList = this.al;
        if (arrayList != null && arrayList.size() > 0) {
            LogUtil.a("HWChMeasureController", "insert to data platform");
            this.g.onDataChanged(this.k, this.al);
            if (!Utils.i()) {
                a(c(), this.al);
            } else {
                a(MultiUsersManager.INSTANCE.getCurrentUser().i(), this.al);
            }
            this.ai = true;
        } else {
            LogUtil.a("HWChMeasureController", "suspected weight not insert");
        }
        ArrayList<ckm> arrayList2 = this.aw;
        if (arrayList2 != null && arrayList2.size() > 0) {
            d dVar = this.am;
            if (dVar != null) {
                dVar.sendEmptyMessage(11);
                return;
            } else {
                LogUtil.a("HWChMeasureController", "insertToDataPlatform mMsgHandler == null");
                return;
            }
        }
        if (this.ai) {
            if (cpa.ax(this.aj)) {
                Context context = this.j;
                nrh.d(context, context.getResources().getString(R.string.IDS_device_offline_sync_data_done));
            } else {
                Context context2 = this.j;
                nrh.d(context2, context2.getResources().getString(R.string.IDS_device_sync_data_done_toast));
            }
            d dVar2 = this.am;
            if (dVar2 != null) {
                dVar2.sendEmptyMessage(10);
                return;
            } else {
                LogUtil.a("HWChMeasureController", "insertToDataPlatform MSG_SYNC_DATA_DONE mMsgHandler == null");
                return;
            }
        }
        LogUtil.a("HWChMeasureController", "insertToDataPlatform mIsSyncDone false");
    }

    private void a(String str, ArrayList<HealthData> arrayList) {
        if (arrayList != null && str != null) {
            Iterator<HealthData> it = arrayList.iterator();
            while (it.hasNext()) {
                this.ba.d(str, it.next().getStartTime());
            }
            return;
        }
        LogUtil.a("HWChMeasureController", "list is empty or currentUid is empty");
    }

    public boolean a() {
        return this.u;
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("HWChMeasureController", "HWChMeasureController receive msg : ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    removeMessages(2);
                    if (cgk.this.h < 2) {
                        cgk.l(cgk.this);
                        cgk cgkVar = cgk.this;
                        cgkVar.EI_(cgkVar.f, cgk.this.am);
                        cgk.this.am.sendEmptyMessageDelayed(3, 500L);
                    } else {
                        LogUtil.b("HWChMeasureController", "HWChMeasureController Times IS OUT! The mConnectTryNum = ", Integer.valueOf(cgk.this.h));
                    }
                } else if (i == 3) {
                    cgk.this.mDevice.connectAsync(null);
                }
            } else if (cgk.this.f != null) {
                sendEmptyMessageDelayed(2, PreConnectManager.CONNECT_INTERNAL);
                try {
                    LogUtil.a("HWChMeasureController", "Attemping to start service discovery:", Boolean.valueOf(cgk.this.f.discoverServices()));
                } catch (SecurityException e) {
                    LogUtil.b("HWChMeasureController", "handleMessage SecurityException:", ExceptionUtils.d(e));
                }
            } else {
                LogUtil.a("HWChMeasureController", "MSG_DISCOVER_SERVICE mBluetoothGatt == null");
            }
            cgk.this.b(message.what);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i == 4) {
            this.mDevice.d();
            return;
        }
        if (i == 10) {
            LogUtil.a("HWChMeasureController", "HWChMeasureController MSG_SYNC_DATA_DONE mType==", Integer.valueOf(this.au));
            int i2 = this.au;
            if (i2 != -1 && i2 != -2) {
                cleanup();
                ending();
                return;
            } else {
                LogUtil.a("HWChMeasureController", "MSG_SYNC_DATA_DONE not cleanup and ending");
                return;
            }
        }
        if (i != 11) {
            return;
        }
        if (BaseApplication.isRunningForeground() && this.j != null) {
            if (cpa.ax(this.aj)) {
                Context context = this.j;
                nrh.d(context, context.getResources().getString(R.string.IDS_device_offline_sync_data_done));
            } else {
                Context context2 = this.j;
                nrh.d(context2, context2.getResources().getString(R.string.IDS_device_sync_data_done_toast));
            }
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), WeightOfflineDataSelectActivity.class.getName());
            intent.putExtra("productId", this.aj);
            intent.addFlags(335544320);
            try {
                this.j.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("HWChMeasureController", "startActivity exception");
            }
        } else {
            LogUtil.a("HWChMeasureController", "MSG_HAS_SUSPECTED_DATA BaseApplication.isRunningForeground() false");
        }
        this.am.sendEmptyMessage(10);
    }

    class a implements Runnable {
        private final byte[] c;
        private final WeightCmd d;
        private boolean e = true;

        a(byte[] bArr, WeightCmd weightCmd) {
            this.c = bArr;
            this.d = weightCmd;
            LogUtil.a("HWChMeasureController", "SendRunCmd ", "ack ", weightCmd.name());
        }

        public void c(boolean z) {
            this.e = z;
            LogUtil.a("HWChMeasureController", "mIsRunning ", Boolean.valueOf(z));
        }

        @Override // java.lang.Runnable
        public void run() {
            cgk.this.l = null;
            LogUtil.a("HWChMeasureController", "run isRunning ", Boolean.valueOf(this.e));
            for (int i = 0; this.e && i < 3; i++) {
                if (cgk.this.l == this.d) {
                    LogUtil.a("HWChMeasureController", "mExpectAck == enumAck");
                    return;
                }
                LogUtil.a("HWChMeasureController", "mExpectAck != enumAck");
                LogUtil.a("HWChMeasureController", "SendRunCmd enumAck ", this.d.name(), " mExpectAck ");
                cgk.this.e(this.c);
            }
        }
    }
}
