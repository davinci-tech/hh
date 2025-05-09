package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
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
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback;
import com.huawei.health.device.kit.wsp.task.ITaskService;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwbtsdk.linklayer.DataReceivedCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.chb;
import defpackage.cjf;
import defpackage.cjq;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class cgt extends GattMeasureController implements ITaskService {
    private static cgt g;
    private boolean ab;
    private volatile boolean ac;
    private ckm ak;
    private final d aq;
    private Bundle ar;
    private String at;
    private SharedPreferences au;
    private BleTaskQueueUtil ba;
    private int bc;
    private String bd;
    private int bf;
    private MultiPackageDataRecLogic bg;
    private String bj;
    private final cha bk;
    private final chh bm;
    private volatile BluetoothGatt h;
    private cfi o;
    private String p;
    private cgp q;
    private dfd r;
    private Bundle u;
    private byte[] w;
    private cgz z;
    private static final Object c = new Object();
    private static final Map<String, Set<UUID>> d = new HashMap();
    private static final String[] e = {"weight_device_clear_user_data", "weight_device_ota_update", "weight_measure_set_user", "bind_result", "manager_info_success", "manager_info_failed", "set_manager_info", "set_manager_info_result", "send_config_info", "config_info_result", "get_device_ssid", "get_device_ssid_result", "request_auth", "request_auth_pass", "request_auth_failed", "workkey_info", "request_auth_token", "real_time_weight_info", "send_real_time_weight_info", "real_time_weight_info_failed", "send_history_weight_info", "history_weight_info", "set_user_info_result", "set_weight_unit", "get_scale_version_code", "reset_wifi", "device_reset", "delete_user_data", "get_user_data", "get_weight_unit", "get_user_data_next", "get_scale_version", "get_user_data_again", "get_user_data_send_again", "send_wake_up", "bind_process_completed", "get_manager_info", "send_ssid", "send_wifi_password", "event_bus_disable_notification", "event_bus_current_user_changed", "event_bus_upload_ble_log", "event_bus_file_check", "event_bus_file_parameter", "event_bus_file_data_request", "event_bus_file_result_notify", "event_bus_send_ota_url", "weight_device_tlv_ota_update", "send_config_info_hag_2021"};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f707a = {"weight_device_clear_user_data", "weight_device_ota_update", "weight_measure_set_user", "bind_result", "manager_info_success", "manager_info_failed", "set_manager_info", "set_manager_info_result", "send_config_info", "config_info_result", "get_device_ssid", "get_device_ssid_result", "request_auth", "request_auth_pass", "request_auth_failed", "workkey_info", "request_auth_token", "real_time_weight_info", "send_real_time_weight_info", "real_time_weight_info_failed", "send_history_weight_info", "history_weight_info", "set_user_info_result", "set_weight_unit", "get_scale_version_code", "reset_wifi", "device_reset", "delete_user_data", "get_user_data", "get_weight_unit", "get_user_data_next", "get_scale_version", "get_user_data_again", "get_user_data_send_again", "send_wake_up", "bind_process_completed", "get_manager_info", "send_ssid", "send_wifi_password", "event_bus_disable_notification", "event_bus_current_user_changed", "event_bus_upload_ble_log", "event_bus_file_check", "event_bus_file_parameter", "event_bus_file_data_request", "event_bus_file_result_notify", "event_bus_send_ota_url", "weight_device_tlv_ota_update", "send_config_info_hag_2021"};
    private static Set<String> b = new HashSet();
    private MultiPackageDataRecLogic ap = null;
    private MultiPackageDataRecLogic j = null;
    private MultiPackageDataRecLogic ax = null;
    private MultiPackageDataRecLogic y = null;
    private MultiPackageDataRecLogic x = null;
    private MultiPackageDataRecLogic f = null;
    private HandlerThread v = new HandlerThread("HwWspMeasureController");
    private int aw = 0;
    private int l = 0;
    private volatile int ay = 0;
    private final Object aj = new Object();
    private final Object m = new Object();
    private volatile boolean ae = false;
    private volatile boolean ad = false;
    private boolean af = false;
    private boolean ai = false;
    private boolean ah = false;
    private boolean al = false;
    private boolean ag = false;
    private int be = -1;
    private int az = -1;
    private ArrayList<ckm> bl = new ArrayList<>(16);
    private ArrayList<ckm> bb = new ArrayList<>(16);
    private ArrayList<ckm> k = new ArrayList<>(16);
    private ArrayList<HealthData> as = new ArrayList<>(16);
    private ckc bh = ckc.a(BaseApplication.getContext());
    private boolean am = false;
    private Context n = BaseApplication.getContext();
    private int ao = 0;
    private boolean an = false;
    private izv i = null;
    private boolean aa = false;
    private EventBus.ICallback t = new EventBus.ICallback() { // from class: cgt.4
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            LogUtil.a("HwWspMeasureController", "HwWspMeasureController event bus event start: ", bVar.e());
            cgt.this.bm.e(new cje().i().a(bVar).e(cgt.this.bc).d(cgt.this.mDevice).d(cgt.this.aq).e(cgt.this.p).a(cgt.this.at).d(cgt.this.q).d(cgt.this.be).e());
        }
    };
    private BluetoothGattCallback s = new BluetoothGattCallback() { // from class: cgt.9
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "onConnectionStateChange status:", Integer.valueOf(i), " newState:", Integer.valueOf(i2));
            cgt.this.EW_(bluetoothGatt, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "onServicesDiscovered status:", Integer.valueOf(i), " type:", Integer.valueOf(cgt.this.bc));
            cgt.this.EV_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "onCharacteristicWrite status:", Integer.valueOf(i), " descriptor:", bluetoothGattCharacteristic.getUuid().toString());
            cgt.this.u();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (bluetoothGattCharacteristic != null) {
                if (!chb.af.contains(bluetoothGattCharacteristic.getUuid().toString()) && chb.ar.contains(bluetoothGattCharacteristic.getUuid().toString())) {
                    cgt.this.av.d(0, bluetoothGattCharacteristic);
                }
                cgt.this.e(new cjf.b().b(bluetoothGattCharacteristic.getUuid()).d(bluetoothGattCharacteristic.getValue()).d());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            LogUtil.a("HwWspMeasureController", "onDescriptorWrite status:", Integer.valueOf(i), " descriptor:", bluetoothGattDescriptor.getCharacteristic().getUuid().toString());
            cgt.this.u();
            cgt.this.bk.d(true, cgt.this.bc);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            LogUtil.a("HwWspMeasureController", "onCharacteristicRead uuid:", bluetoothGattCharacteristic.getUuid(), " read data:", cgt.this.bk.a(bluetoothGattCharacteristic.getValue()));
        }
    };
    private final IBaseResponseCallback av = new IBaseResponseCallback() { // from class: cgt.14
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj instanceof BluetoothGattCharacteristic) {
                BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) obj;
                if (bluetoothGattCharacteristic.getService() == null) {
                    return;
                }
                cpu.a("event_bus_disable_notification", bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid());
                return;
            }
            if (obj instanceof cjf) {
                cjf cjfVar = (cjf) obj;
                cpu.a("event_bus_disable_notification", cjfVar.b(), cjfVar.a());
            } else {
                LogUtil.h("HwWspMeasureController", "disableDisableCharacteristicNotification fail");
            }
        }
    };

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        return true;
    }

    private cgt() {
        this.ba = null;
        this.z = null;
        ckm ckmVar = new ckm();
        this.ak = ckmVar;
        ckmVar.setEndTime(System.currentTimeMillis());
        this.ba = new BleTaskQueueUtil(this);
        cha chaVar = new cha(this.ba);
        this.bk = chaVar;
        this.z = new cgz();
        this.bm = new chh(this.z, chaVar);
        this.v.start();
        this.aq = new d(this.v.getLooper());
        this.au = this.n.getSharedPreferences("weightUser", 0);
    }

    public class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            boolean z = false;
            if (i != 1) {
                if (i == 2) {
                    cgt.this.y();
                } else if (i == 3) {
                    cgt.this.mDevice.d();
                    LogUtil.a("HwWspMeasureController", "MSG_DISCOVER_SERVICE dierect connect");
                } else if (i == 4) {
                    cgt.this.bk.y();
                } else if (i == 7) {
                    LogUtil.a("HwWspMeasureController", "msg get version getScaleVersion");
                    cgt.this.bk.d(BleTaskQueueUtil.TaskType.GET_SCALE_VERSION, ciy.c().d());
                } else if (i == 8) {
                    cgt.this.bk.j();
                } else if (i == 10) {
                    LogUtil.h("R_Weight_HwWspMeasureController", "getHistoryWightData is fail!");
                    cgt.this.bk.e(new byte[]{1});
                } else if (i == 11) {
                    cgt.this.x();
                } else {
                    Fe_(message);
                }
            } else if (cgt.this.h != null && !cgt.this.ah) {
                try {
                    z = cgt.this.h.discoverServices();
                } catch (SecurityException e) {
                    LogUtil.b("HwWspMeasureController", "discoverServices, ", e.getMessage());
                }
                removeMessages(2);
                sendEmptyMessageDelayed(2, 20000L);
                ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "Attemping to start service discovery:", Boolean.valueOf(z));
            } else {
                ReleaseLogUtil.d("R_Weight_HwWspMeasureController", "MSG_DISCOVER_SERVICE mBluetoothGatt is null");
            }
            cgt.this.d(message.what);
        }

        private void Fe_(Message message) {
            if (ciy.c().e()) {
                return;
            }
            if (message.what >= 1000 && message.what <= BleTaskQueueUtil.TaskType.values().length + 1000) {
                if (message.obj == null || !(message.obj instanceof cjq)) {
                    return;
                }
                cjq cjqVar = (cjq) message.obj;
                LogUtil.a("HwWspMeasureController", "Resend data type:", cjqVar.f());
                cgt.this.write(cjqVar, cjqVar.e());
                return;
            }
            LogUtil.h("R_Weight_HwWspMeasureController", "MsgHandler default");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.aq.removeCallbacksAndMessages(null);
        synchronized (this.aj) {
            if (this.h != null) {
                try {
                    this.h.close();
                } catch (SecurityException e2) {
                    LogUtil.b("HwWspMeasureController", "handlerDetectTimeout SecurityException:", ExceptionUtils.d(e2));
                }
                LogUtil.a("HwWspMeasureController", "detect time out,close gatt:", this.h);
                this.h = null;
            }
        }
        this.aq.sendEmptyMessageDelayed(3, 3000L);
        ReleaseLogUtil.d("R_Weight_HwWspMeasureController", "MSG_DISCOVER_SERVICE timeout");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        this.bk.d();
        this.bk.k();
        Bundle bundle = new Bundle();
        bundle.putString("auth", "auth");
        EventBus.d(new EventBus.b("request_auth", bundle));
        ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "Service init and start send request auth.");
    }

    public void e(cjf cjfVar) {
        UUID a2 = cjfVar.a();
        byte[] d2 = cjfVar.d();
        LogUtil.a("HwWspMeasureController", "Device-->SDK:", HexUtils.d(d2), " Tag : ", a2.toString(), " type : ", Integer.valueOf(this.bc));
        b(a2);
        this.bk.c(a2, this.z, d2, this.al, this.bc);
        this.bk.a(a2, this.z, d2);
        a(a2, d2, cjfVar);
        e(a2, d2, cjfVar);
        if (a2.equals(chb.c)) {
            l(d2, cjfVar);
            return;
        }
        if (a2.equals(chb.o)) {
            h(d2, cjfVar);
            return;
        }
        if (a2.equals(chb.f)) {
            ad();
            return;
        }
        if (chb.k.equals(a2)) {
            g(d2, cjfVar);
            return;
        }
        if (chb.e.equals(a2)) {
            a(d2);
            return;
        }
        if (chb.as.equals(a2)) {
            LogUtil.c("HwWspMeasureController", " STRING_BLE_N_CHARACTERISTIC_UUID uuid:", a2, "data:", this.bk.a(d2));
            e(d2);
        } else if (chb.ai.equals(a2)) {
            d(d2);
        } else {
            LogUtil.c("HwWspMeasureController", "HwWspMeasureController onCharacteristicChanged end");
        }
    }

    private void ab() {
        this.i = new izv(1024, new DataReceivedCallback() { // from class: cgt.12
            @Override // com.huawei.hwbtsdk.linklayer.DataReceivedCallback
            public void onDataReceived(int i, byte[] bArr) {
                cpu.b(bArr);
            }
        });
    }

    private void e(UUID uuid, byte[] bArr, cjf cjfVar) {
        if (uuid.equals(chb.l)) {
            this.p = this.z.b(bArr);
            Bundle bundle = new Bundle();
            bundle.putString("deviceSsid", this.p);
            EventBus.d(new EventBus.b("get_device_ssid_result", bundle));
            return;
        }
        if (uuid.equals(chb.s)) {
            j(bArr, cjfVar);
            return;
        }
        if (uuid.equals(chb.d)) {
            i(bArr, cjfVar);
            return;
        }
        if (uuid.equals(chb.ag)) {
            o(bArr, cjfVar);
            return;
        }
        if (uuid.equals(chb.ae)) {
            e().d(true);
            m(bArr, cjfVar);
        } else {
            if (uuid.equals(chb.z)) {
                int e2 = this.z.e(bArr);
                if (e2 == 1) {
                    LogUtil.a("HwWspMeasureController", " no huid match");
                    this.aq.sendEmptyMessage(9);
                    return;
                } else {
                    this.bk.Fj_(e2, this.bc, this.af, this.ar, this.aq);
                    return;
                }
            }
            LogUtil.c("HwWspMeasureController", " handlerCharacterTwo end");
        }
    }

    private void a(UUID uuid, byte[] bArr, cjf cjfVar) {
        int i;
        if (chb.aa.equals(uuid)) {
            int e2 = this.z.e(bArr);
            Bundle bundle = new Bundle();
            bundle.putInt("ret", e2);
            bundle.putInt("weightUnit", this.az);
            LogUtil.a("HwWspMeasureController", "set weight unit resultAck = ", Integer.valueOf(e2), "; mTempUnitType = ", Integer.valueOf(this.az));
            EventBus.d(new EventBus.b("set_weight_unit_result", bundle));
            if (e2 != 0 || (i = this.az) == -1) {
                return;
            }
            this.be = i;
            return;
        }
        if (chb.t.equals(uuid)) {
            n(bArr, cjfVar);
            return;
        }
        if (chb.m.equals(uuid)) {
            int f = this.z.f(bArr);
            this.be = f;
            ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "get weight unit success, unitType = ", Integer.valueOf(f), "; mIsManualGetUnit:", Boolean.valueOf(this.ag));
            Bundle bundle2 = new Bundle();
            bundle2.putInt("weightUnit", this.be);
            EventBus.d(new EventBus.b("get_weight_unit_result", bundle2));
            this.ag = false;
            if (this.bf == -15) {
                byte[] bArr2 = new byte[31];
                byte[] d2 = this.z.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
                System.arraycopy(d2, 0, bArr2, 0, d2.length);
                Bundle bundle3 = new Bundle();
                bundle3.putByteArray(JsbMapKeyNames.H5_USER_ID, bArr2);
                EventBus.d(new EventBus.b("get_user_data", bundle3));
                return;
            }
            return;
        }
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController handlerCharacter end");
    }

    private void b(UUID uuid) {
        Integer num = chb.a().get(uuid);
        if (num != null) {
            this.aq.removeMessages(num.intValue() + 1000);
            LogUtil.a("HwWspMeasureController", " remove timeout msg");
        }
    }

    private void l(byte[] bArr, final cjf cjfVar) {
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiverBindResponse value = ", cvx.d(bArr));
        if (cpa.ar(this.at)) {
            a(bArr, cjfVar);
            return;
        }
        if (this.f == null) {
            this.f = new MultiPackageDataRecLogic("ReceiveBindResp", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.11
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    cgt.this.a(bArr2, cjfVar);
                    cgt.this.f = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.f.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr, cjf cjfVar) {
        this.av.d(0, cjfVar);
        Bundle bundle = new Bundle();
        if (bArr != null && bArr.length >= 3) {
            bundle.putByte("ret", bArr[0]);
            LogUtil.a("HwWspMeasureController", "onReceiverBindResponse ret = ", Byte.valueOf(bArr[0]));
            float b2 = cgf.b(bArr, 1, 2) / 100.0f;
            bundle.putFloat("weight_data", b2);
            ckm c2 = c(b2);
            if (bArr.length >= 15) {
                int i = 0;
                for (int i2 = 0; i2 < 12; i2 += 2) {
                    c2.b(i, cgf.b(bArr, i2 + 3, 2));
                    i++;
                }
                bundle.putSerializable("resis_data", c2);
            }
            boolean a2 = cji.a(this.bd, 18);
            LogUtil.a("HwWspMeasureController", "mUniqueId:", this.bd, "isSupportMultiChannel:", Boolean.valueOf(a2), "bufferData:", this.bk.a(bArr), "length:", Integer.valueOf(bArr.length));
            if (a2 && bArr.length >= 27) {
                c2.d(2);
                int i3 = 0;
                for (int i4 = 0; i4 < 12; i4 += 2) {
                    c2.c(i3, cgf.b(bArr, i4 + 15, 2));
                    i3++;
                }
                bundle.putSerializable("resis_data", c2);
            }
        } else {
            LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiverBindResponse bind failed!");
            bundle.putByte("ret", (byte) 2);
        }
        EventBus.d(new EventBus.b("bind_result", bundle));
    }

    private ckm c(float f) {
        ckm ckmVar = new ckm();
        ckmVar.e(false);
        ckmVar.setWeight(f);
        return ckmVar;
    }

    private void n(byte[] bArr, final cjf cjfVar) {
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiverGetUserDataResponse values = ", cvx.d(bArr));
        if (cpa.ar(this.at)) {
            d(bArr, cjfVar);
            return;
        }
        if (this.y == null) {
            this.y = new MultiPackageDataRecLogic("ReceiveGetUserDataResp", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.15
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    cgt.this.d(bArr2, cjfVar);
                    cgt.this.y = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.y.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(byte[] bArr, cjf cjfVar) {
        this.av.d(0, cjfVar);
        if (bArr != null && bArr.length == 60) {
            this.ao = 0;
            this.bk.j(bArr);
            return;
        }
        if (bArr != null && bArr.length == 1 && bArr[0] != 0) {
            int i = this.ao + 1;
            this.ao = i;
            if (i < 3) {
                EventBus.d(new EventBus.b("get_user_data_send_again"));
                return;
            }
            return;
        }
        LogUtil.h("R_Weight_HwWspMeasureController", "onReceiverGetUserDataResponse userData length is error or null");
        int i2 = this.ao + 1;
        this.ao = i2;
        if (i2 < 3) {
            EventBus.d(new EventBus.b("get_user_data_again"));
        }
    }

    private void o(byte[] bArr, final cjf cjfVar) {
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiverRealTimeResponse values = ", cvx.d(bArr));
        if (cpa.ar(this.at)) {
            b(bArr, cjfVar);
            return;
        }
        if (this.ax == null) {
            this.ax = new MultiPackageDataRecLogic("RequestRealTimeReceivePack", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.13
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    if (cgt.this.b(bArr2, cjfVar)) {
                        return;
                    }
                    cgt.this.ax = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.ax.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(byte[] bArr, cjf cjfVar) {
        this.av.d(0, cjfVar);
        this.ad = false;
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController values = ", cvx.d(bArr));
        if (bArr == null) {
            EventBus.d(new EventBus.b("real_time_weight_info_failed", new Bundle()));
            this.ax = null;
            return true;
        }
        if (bArr.length == 0) {
            EventBus.d(new EventBus.b("real_time_weight_info_failed", new Bundle()));
            this.ax = null;
            return true;
        }
        cpu.b(bArr, this.bd, this.o);
        return false;
    }

    private void m(byte[] bArr, final cjf cjfVar) {
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiverHistoryResponse values = ", cvx.d(bArr));
        if (cpa.ar(this.at)) {
            e(bArr, cjfVar);
            return;
        }
        if (this.x == null) {
            this.x = new MultiPackageDataRecLogic("ReceiverHistoryResponse", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.19
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    if (cgt.this.e(bArr2, cjfVar)) {
                        return;
                    }
                    cgt.this.x = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.x.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(byte[] bArr, cjf cjfVar) {
        this.av.d(0, cjfVar);
        this.ad = false;
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController values = ", cvx.d(bArr));
        if (bArr == null) {
            this.aq.removeMessages(10);
            this.aq.sendEmptyMessageDelayed(10, 500L);
            this.x = null;
            this.al = false;
            ending();
            return true;
        }
        cpu.a(bArr, this.o);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void EV_(android.bluetooth.BluetoothGatt r5, int r6) {
        /*
            r4 = this;
            java.lang.String r0 = "HwWspMeasureController"
            cgt$d r1 = r4.aq     // Catch: java.lang.SecurityException -> L74
            r2 = 0
            r1.removeCallbacksAndMessages(r2)     // Catch: java.lang.SecurityException -> L74
            int r1 = r4.bc     // Catch: java.lang.SecurityException -> L74
            r2 = -5
            if (r1 == r2) goto L6e
            r2 = -6
            if (r1 != r2) goto L11
            goto L6e
        L11:
            if (r6 != 0) goto L82
            java.util.UUID r6 = defpackage.chb.ao     // Catch: java.lang.SecurityException -> L74
            android.bluetooth.BluetoothGattService r6 = r5.getService(r6)     // Catch: java.lang.SecurityException -> L74
            r1 = 1
            r2 = 0
            r3 = 2
            if (r6 != 0) goto L46
            int r5 = r4.l     // Catch: java.lang.SecurityException -> L74
            r6 = 10
            if (r5 >= r6) goto L2f
            r5 = 200(0xc8, float:2.8E-43)
            r4.c(r5)     // Catch: java.lang.SecurityException -> L74
            int r5 = r4.l     // Catch: java.lang.SecurityException -> L74
            int r5 = r5 + r1
            r4.l = r5     // Catch: java.lang.SecurityException -> L74
            goto L45
        L2f:
            cgt$d r5 = r4.aq     // Catch: java.lang.SecurityException -> L74
            r5.sendEmptyMessage(r3)     // Catch: java.lang.SecurityException -> L74
            r4.l = r2     // Catch: java.lang.SecurityException -> L74
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.SecurityException -> L74
            java.lang.String r3 = "Re discover service failed, retry num:"
            r5[r2] = r3     // Catch: java.lang.SecurityException -> L74
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.SecurityException -> L74
            r5[r1] = r6     // Catch: java.lang.SecurityException -> L74
            com.huawei.hwlogsmodel.LogUtil.h(r0, r5)     // Catch: java.lang.SecurityException -> L74
        L45:
            return
        L46:
            r4.l = r2     // Catch: java.lang.SecurityException -> L74
            r4.h = r5     // Catch: java.lang.SecurityException -> L74
            r4.ay = r3     // Catch: java.lang.SecurityException -> L74
            android.bluetooth.BluetoothGatt r5 = r4.h     // Catch: java.lang.SecurityException -> L74
            r4.ER_(r5)     // Catch: java.lang.SecurityException -> L74
            cha r5 = r4.bk     // Catch: java.lang.SecurityException -> L74
            cxh r6 = r4.mDevice     // Catch: java.lang.SecurityException -> L74
            r5.a(r6, r3)     // Catch: java.lang.SecurityException -> L74
            com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil r5 = r4.ba     // Catch: java.lang.SecurityException -> L74
            r5.d()     // Catch: java.lang.SecurityException -> L74
            cgt$d r5 = r4.aq     // Catch: java.lang.SecurityException -> L74
            r6 = 11
            r5.removeMessages(r6)     // Catch: java.lang.SecurityException -> L74
            cgt$d r5 = r4.aq     // Catch: java.lang.SecurityException -> L74
            r2 = 100
            r5.sendEmptyMessageDelayed(r6, r2)     // Catch: java.lang.SecurityException -> L74
            r4.ah = r1     // Catch: java.lang.SecurityException -> L74
            goto L82
        L6e:
            if (r5 == 0) goto L73
            r5.close()     // Catch: java.lang.SecurityException -> L74
        L73:
            return
        L74:
            r5 = move-exception
            java.lang.String r6 = "onDiscovered SecurityException:"
            java.lang.String r5 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)
            java.lang.Object[] r5 = new java.lang.Object[]{r6, r5}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)
        L82:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cgt.EV_(android.bluetooth.BluetoothGatt, int):void");
    }

    private void ER_(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
            LogUtil.h("HwWspMeasureController", "cacheDeviceCapacites fail, gatt is null");
            return;
        }
        String address = bluetoothGatt.getDevice().getAddress();
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        if (koq.b(services)) {
            LogUtil.h("HwWspMeasureController", "cacheDeviceCapacites empty sevices");
        }
        HashSet hashSet = new HashSet();
        Iterator<BluetoothGattService> it = services.iterator();
        while (it.hasNext()) {
            List<BluetoothGattCharacteristic> characteristics = it.next().getCharacteristics();
            if (!koq.b(characteristics)) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                    if (bluetoothGattCharacteristic != null && bluetoothGattCharacteristic.getUuid() != null) {
                        hashSet.add(bluetoothGattCharacteristic.getUuid());
                    }
                }
            }
        }
        d.put(address, hashSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void EW_(BluetoothGatt bluetoothGatt, int i) {
        this.h = bluetoothGatt;
        if (i == 2) {
            ES_(bluetoothGatt);
        } else if (i == 0) {
            c();
        } else {
            ReleaseLogUtil.d("R_Weight_HwWspMeasureController", "onStateChange unknown");
        }
    }

    private void ES_(BluetoothGatt bluetoothGatt) {
        try {
            int i = this.bc;
            if (i == -5 || i == -6) {
                if (bluetoothGatt != null) {
                    bluetoothGatt.close();
                    ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "discover but close.");
                    return;
                }
                return;
            }
            if (this.ah) {
                ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "Service has been init.");
                return;
            }
            ReleaseLogUtil.d("R_Weight_HwWspMeasureController", "Service has not been init.");
            this.ac = true;
            this.bk.a(this.mDevice, 1);
            this.bd = this.h.getDevice().getAddress();
            c(600);
        } catch (SecurityException e2) {
            LogUtil.b("HwWspMeasureController", "doDeviceConnectedByGatt SecurityException:", ExceptionUtils.d(e2));
        }
    }

    public void e(String str) {
        this.bk.d();
        this.bk.k();
        ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "doDeviceConnectedByUds mType: ", Integer.valueOf(this.bc));
        int i = this.bc;
        if (i == -5 || i == -6) {
            LogUtil.a("R_Weight_HwWspMeasureController", "device connected by uds, but close.");
            return;
        }
        this.ay = 2;
        this.bk.a(this.mDevice, 2);
        this.ba.d();
        this.ac = true;
        this.bd = str;
        int i2 = this.bc;
        if (i2 == -1 || i2 == -4) {
            this.bk.y();
        } else if (i2 == -2) {
            this.bk.c(true);
        } else {
            LogUtil.h("HwWspMeasureController", "doDeviceConnectedByUds : unknown branch");
        }
    }

    public void c() {
        this.ay = 3;
        this.ah = false;
        LogUtil.a("HwWspMeasureController", "newState == BluetoothProfile.STATE_DISCONNECTED");
        this.bk.a(this.mDevice, 3);
        this.bk.a(this.mDevice, 4);
        cmp.e().b(false);
        this.ac = false;
        if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
            cleanup();
            return;
        }
        if (this.ab && !cpa.ar(this.at)) {
            p();
            this.ab = false;
            this.aq.sendEmptyMessage(3);
            return;
        }
        cleanup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (i == 9) {
            this.bk.a(this.mDevice, 15);
        } else {
            LogUtil.h("R_Weight_HwWspMeasureController", "processHandler default");
        }
    }

    public static cgt e() {
        cgt cgtVar;
        synchronized (c) {
            if (g == null) {
                g = new cgt();
            }
            cgtVar = g;
        }
        return cgtVar;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        ac();
        this.ad = false;
        this.bk.c(iHealthDeviceCallback);
        LogUtil.a("HwWspMeasureController", "HwWspMeasureController prepare start, isConnect:", Boolean.valueOf(this.ac), ", device:", Integer.valueOf(System.identityHashCode(healthDevice)));
        if (bundle != null) {
            if (this.af && this.ay == 1) {
                return false;
            }
            this.ar = bundle;
            this.bc = bundle.getInt("type");
            this.bf = bundle.getInt("userType");
            this.at = bundle.getString("productId", this.at);
            this.af = bundle.getBoolean("measureButtonOnClick", this.af);
            this.z.c(cpa.ar(this.at));
        }
        cjg.d().e(this.at, this.ba);
        ai();
        LogUtil.a("HwWspMeasureController", "HwWspMeasureController prepare mType : ", Integer.valueOf(this.bc), " isMeasureButtonClicked:", Boolean.valueOf(this.af), " isSyncCurrentTime:", Boolean.valueOf(this.an), " userDataType", Integer.valueOf(this.bf));
        this.bk.a(healthDevice, this.ay != 2 ? 3 : 2);
        if (bundle != null && bundle.getBoolean("queryStatusOnly")) {
            return false;
        }
        int ET_ = bundle != null ? ET_(bundle) : -1;
        if (this.af && this.an) {
            LogUtil.a("HwWspMeasureController", "HwWspMeasureController prepare setUserInfo");
            if (ET_ == 1) {
                EventBus.d(new EventBus.b("weight_measure_set_user", bundle));
            } else {
                EventBus.d(new EventBus.b("weight_measure_set_user"));
            }
        }
        if (this.bf == -15) {
            LogUtil.a("HwWspMeasureController", "HwWspMeasureController prepare get user data");
            if (this.an) {
                EventBus.d(new EventBus.b("get_user_data", bundle));
            } else {
                this.bk.Fh_(bundle);
                e().a(-1);
            }
        }
        return super.prepare(healthDevice, iHealthDeviceCallback, bundle);
    }

    private void ai() {
        int i = this.bc;
        if (i == -2 || i == -1) {
            this.ab = true;
        } else {
            this.ab = false;
        }
    }

    private int ET_(Bundle bundle) {
        int i = bundle != null ? bundle.getInt("guestUser", 0) : -1;
        if (i == 1) {
            this.u = bundle;
        }
        return i;
    }

    private void ac() {
        EventBus.d(this.t, 0, e);
    }

    public int k() {
        return this.ay;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void updateState(int i) {
        this.ay = i;
    }

    public boolean m() {
        return this.ai;
    }

    public void a(EventBus.b bVar) {
        if (bVar.Kl_() != null && bVar.Kl_().getBoolean("getNetworkDeviceVersion", false)) {
            LogUtil.h("HwWspMeasureController", "getNetworkDeviceVersion is true");
            this.aq.sendEmptyMessage(7);
            return;
        }
        LogUtil.h("HwWspMeasureController", "dealVersionCodeEvent mIsSyncCurrentTime：", Boolean.valueOf(this.an));
        cgp cgpVar = this.q;
        if (cgpVar != null) {
            Intent intent = new Intent();
            intent.putExtra("bleVersion", cgpVar.c());
            intent.putExtra("scaleVersion", cgpVar.a());
            intent.putExtra(HealthEngineRequestManager.PARAMS_DEVICE_SN, cgpVar.e());
            EventBus.d(new EventBus.b("set_scale_version_code", intent));
            LogUtil.a("HwWspMeasureController", "dealVersionCodeEvent publish action : set_scale_version_code");
            if (cpa.ah(this.at) || cpa.r(this.at)) {
                return;
            }
            if (this.w != null) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("huid", this.w);
                EventBus.d(new EventBus.b("manager_info_success", bundle));
                return;
            }
            this.aq.sendEmptyMessageDelayed(8, 200L);
            return;
        }
        if (this.an) {
            this.aq.sendEmptyMessageDelayed(7, 200L);
        } else {
            EventBus.d(new EventBus.b("get_version_code_fail", new Intent()));
        }
    }

    public void c(boolean z, ckm ckmVar, final byte[] bArr, int i) {
        int[] iArr = {i};
        if (!Utils.i()) {
            ckmVar.e(o());
        } else {
            cfi cfiVar = this.o;
            if (cfiVar == null || cfiVar.i() == null) {
                LogUtil.h("HwWspMeasureController", "HwWspMeasureController save history data mCurrentUser or uuid is null");
                return;
            }
            ckmVar.e(this.o.i());
        }
        if (!z) {
            this.ak = ckmVar.mo76clone();
            this.bk.a(this.mDevice, this.ak);
            return;
        }
        ckmVar.c(true);
        int i2 = iArr[0];
        int i3 = i2 + 2;
        iArr[0] = i3;
        if (bArr.length < i2 + 3) {
            LogUtil.h("HwWspMeasureController", "the count is", Integer.valueOf(i3), "the length is", Integer.valueOf(bArr.length));
            return;
        }
        int b2 = cgf.b(bArr, i3, 1);
        int i4 = iArr[0] + 1;
        iArr[0] = i4;
        final int b3 = cgf.b(bArr, i4, 1);
        LogUtil.a("HwWspMeasureController", "saveWeightData weightAndFatData userId:", ckmVar.n(), " weight:", Float.valueOf(ckmVar.getWeight()), " suspectedFlag:", Integer.valueOf(b2));
        if (b2 == 1) {
            LogUtil.a("HwWspMeasureController", "set suspectedFlag true !");
            ckmVar.b(true);
            if (cpa.ax(this.at)) {
                this.k.add(ckmVar);
                e(bArr, b3);
                return;
            }
        }
        this.bh.a(ckmVar, new IBaseResponseCallback() { // from class: cgq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i5, Object obj) {
                cgt.this.b(bArr, b3, i5, obj);
            }
        });
    }

    /* synthetic */ void b(byte[] bArr, int i, int i2, Object obj) {
        if (i2 != 0) {
            return;
        }
        e(bArr, i);
    }

    private void e(byte[] bArr, int i) {
        if (bArr.length != 28 && bArr.length != 40) {
            LogUtil.h("HwWspMeasureController", "weightDataBytes length is not 28 or 40!");
            return;
        }
        if (i == 0) {
            cpu.a(this.n, this.at);
            LogUtil.a("HwWspMeasureController", "get history weight data completed!");
            this.al = false;
            t();
            this.k.clear();
            ending();
            return;
        }
        LogUtil.h("HwWspMeasureController", "get history weight data not completed!");
    }

    public String o() {
        SharedPreferences sharedPreferences = this.au;
        if (sharedPreferences != null) {
            return sharedPreferences.getString("weightUser_id", "");
        }
        LogUtil.h("R_Weight_HwWspMeasureController", "mSharedPreferences is null");
        return "";
    }

    public void t() {
        synchronized (this) {
            this.bl.clear();
            this.bb.clear();
            this.as.clear();
            if (!Utils.i()) {
                this.bl.addAll(this.bh.b(o()));
            } else {
                this.bl.addAll(this.bh.b(MultiUsersManager.INSTANCE.getCurrentUser().i()));
            }
            LogUtil.a("HwWspMeasureController", "weight offline data size is :", Integer.valueOf(this.bl.size()));
            if (this.bl.size() > 0 || this.k.size() > 0) {
                ae();
            }
        }
    }

    private void ae() {
        Iterator<ckm> it = this.bl.iterator();
        while (it.hasNext()) {
            ckm next = it.next();
            next.e(false);
            if (next.q()) {
                this.bb.add(next);
            } else {
                this.as.add(next);
            }
        }
        dfd dfdVar = new dfd(10006);
        this.r = dfdVar;
        dfdVar.b(new WeightInsertStatusCallback() { // from class: cgt.16
            @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
            public void isSuccess(boolean z) {
                if (z) {
                    LogUtil.a("HwWspMeasureController", "HwWspMeasureController insert normal History data success");
                    if (cpa.ax(cgt.this.at)) {
                        LogUtil.a("HwWspMeasureController", "HwWspMeasureController start sync data");
                        ClaimWeightDataManager.INSTANCE.startSync();
                        return;
                    }
                    return;
                }
                LogUtil.h("R_Weight_HwWspMeasureController", "HwWspMeasureController insert normal History data fail");
            }
        });
        z();
        int size = this.bl.size();
        if (size > 0) {
            double doubleValue = new BigDecimal(this.bb.size() / size).setScale(2, RoundingMode.DOWN).doubleValue();
            LogUtil.a("HwWspMeasureController", "normal data size ==", Integer.valueOf(this.as.size()), "suspected data size ==", Integer.valueOf(this.bb.size()));
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(32);
            linkedHashMap.put("click", "1");
            linkedHashMap.put("time", String.valueOf(System.currentTimeMillis()));
            linkedHashMap.put("proportion", String.valueOf(doubleValue));
            OpAnalyticsUtil.getInstance().setEvent2nd(AnalyticsValue.HEALTH_PLUGIN_DEVICE_WEIGHT_BODYFATSCALE_OFFLINE_DATA_2060029.value(), linkedHashMap);
        }
    }

    private void z() {
        ArrayList<HealthData> arrayList = this.as;
        if (arrayList != null && arrayList.size() > 0) {
            LogUtil.a("HwWspMeasureController", "insert normal data to data platform");
            this.r.onDataChanged(this.mDevice, this.as);
            if (!Utils.i()) {
                d(o(), this.as);
            } else {
                d(MultiUsersManager.INSTANCE.getCurrentUser().i(), this.as);
            }
        } else {
            LogUtil.h("HwWspMeasureController", "suspected weight not insert");
        }
        if (!koq.b(this.k) && cpa.ax(this.at)) {
            ArrayList arrayList2 = new ArrayList(this.k.size());
            arrayList2.addAll(this.k);
            this.r.onDataChanged(this.mDevice, arrayList2);
        }
        ArrayList<ckm> arrayList3 = this.bb;
        if (arrayList3 == null || arrayList3.size() <= 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.at);
        EventBus.d(new EventBus.b("evebus_show_suspected_data_claim_dialog", bundle));
    }

    private void d(String str, ArrayList<HealthData> arrayList) {
        if (arrayList != null && str != null) {
            Iterator<HealthData> it = arrayList.iterator();
            while (it.hasNext()) {
                HealthData next = it.next();
                LogUtil.a("HwWspMeasureController", "mWeightDbHelper delete record time:", Long.valueOf(next.getStartTime()));
                this.bh.d(str, next.getStartTime());
            }
            return;
        }
        LogUtil.h("R_Weight_HwWspMeasureController", "list is empty or currentUid is empty");
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        LogUtil.a("HwWspMeasureController", "HwWspMeasureController cleanup");
        this.aq.removeCallbacksAndMessages(null);
        p();
        super.ending();
    }

    public void p() {
        LogUtil.a("HwWspMeasureController", "HwWspMeasureController setCleanData");
        u();
        a();
        if (cpa.ar(this.at)) {
            cpu.c(this.mDevice);
        }
        this.aa = false;
        this.bd = null;
        this.ah = false;
        this.ba.d();
        this.ad = false;
        this.q = null;
        this.ay = 0;
        this.bk.x();
        this.bk.c();
        this.w = null;
        this.ac = false;
        ciy.c().c(false);
        this.aq.removeCallbacksAndMessages(null);
        this.an = false;
        this.al = false;
        this.p = null;
        this.bf = -1;
        ag();
        this.i = null;
    }

    private void ag() {
        this.bg = null;
        this.ap = null;
        this.j = null;
        this.ax = null;
        this.y = null;
        this.x = null;
        this.f = null;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        int i;
        LogUtil.a("HwWspMeasureController", "HwWspMeasureController ending mType = ", Integer.valueOf(this.bc));
        boolean z = (this.al || (i = this.bc) == -4 || i == -1 || i == -2) ? false : true;
        if (this.bc == -5 || z) {
            LogUtil.a("HwWspMeasureController", "HwWspMeasureController free connect");
            u();
            a();
            EventBus.a(this.t, f707a);
            cleanup();
        }
    }

    public void a() {
        synchronized (this.aj) {
            if (this.h != null) {
                LogUtil.a("R_Weight_HwWspMeasureController", "HwWspMeasureController disconnect, ", this.h);
                try {
                    this.h.disconnect();
                    cpu.e(100L);
                    this.h.close();
                } catch (SecurityException e2) {
                    LogUtil.b("HwWspMeasureController", "closeGattCallback SecurityException:", ExceptionUtils.d(e2));
                }
                this.h = null;
            }
        }
    }

    private void c(int i) {
        d dVar = this.aq;
        if (dVar != null) {
            dVar.removeMessages(1);
            this.aq.sendEmptyMessageDelayed(1, i);
            ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "Send discoverService msg");
            return;
        }
        ReleaseLogUtil.d("R_Weight_HwWspMeasureController", "mMsgHandler is null");
    }

    private void aa() {
        synchronized (this.m) {
            this.ae = true;
            try {
                LogUtil.c("HwWspMeasureController", "get mCharacterLock wait ", Thread.currentThread().getName());
                this.m.wait(6000L);
            } catch (InterruptedException e2) {
                cpw.e(false, "HwWspMeasureController", "InterruptedException = ", e2.getMessage());
            }
            this.ae = false;
        }
        LogUtil.c("HwWspMeasureController", "mCharacterLock out ", Thread.currentThread().getName());
    }

    public void u() {
        synchronized (this.m) {
            if (this.ae) {
                LogUtil.c("HwWspMeasureController", "mCharacterLock notifyAll", Thread.currentThread().getName());
                this.m.notifyAll();
                this.ae = false;
            }
        }
    }

    private void g(byte[] bArr, final cjf cjfVar) {
        if (cpa.ar(this.at)) {
            f(bArr, cjfVar);
            return;
        }
        if (this.bg == null) {
            this.bg = new MultiPackageDataRecLogic("VersionInfoPack", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.2
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    if (cgt.this.f(bArr2, cjfVar)) {
                        return;
                    }
                    cgt.this.bg = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.bg.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f(byte[] bArr, cjf cjfVar) {
        this.av.d(0, cjfVar);
        LogUtil.a("HwWspMeasureController", " MultiPackageDataRecLogic VersionInfoPack :", this.bk.a(bArr));
        cgp c2 = cgz.c(bArr);
        if (this.at == null) {
            LogUtil.h("HwWspMeasureController", "onReceiveVersionCode mProductId is null");
            return true;
        }
        if (c2 != null) {
            coz.d("HwWspMeasureController", c2.c(), this.n, this.bd);
            cpu.b(c2, this.bd);
            Intent intent = new Intent();
            intent.putExtra("bleVersion", c2.c());
            intent.putExtra("scaleVersion", c2.a());
            intent.putExtra(HealthEngineRequestManager.PARAMS_DEVICE_SN, c2.e());
            EventBus.d(new EventBus.b("set_scale_version_code", intent));
            Bundle bundle = new Bundle();
            bundle.putBoolean("isManualGetUnit", false);
            bundle.putString(HealthEngineRequestManager.PARAMS_DEVICE_SN, c2.e());
            LogUtil.a("HwWspMeasureController", "onReceiveVersionCode status changed to GET_DEVICE_VERSION");
            this.bk.Fi_(this.mDevice, -16, bundle);
            EventBus.d(new EventBus.b("get_scale_version", bundle));
            if (this.bc == -2) {
                this.bk.c(c2.c(), c2.e(), this.at, this.bd);
            }
            this.q = c2;
            return false;
        }
        LogUtil.h("HwWspMeasureController", "onReceiveVersionCode deviceVersion is null");
        this.bg = null;
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("isManualGetUnit", false);
        EventBus.d(new EventBus.b("get_scale_version", bundle2));
        return true;
    }

    public void g() {
        BluetoothGattService service;
        synchronized (this.aj) {
            boolean z = true;
            LogUtil.a("HwWspMeasureController", "HwWspMeasureController enableOtaCharacter");
            if (this.h != null && (service = this.h.getService(chb.ak)) != null) {
                cjq cjqVar = new cjq(BleTaskQueueUtil.TaskType.GET_OTA_UPGRADE_RESULT, new byte[0]);
                cjqVar.c(new cjq.a(cjqVar, z) { // from class: cgt.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(z);
                        Objects.requireNonNull(cjqVar);
                        e(new byte[][]{BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE});
                    }
                });
                Fa_(service.getCharacteristic(chb.u), cjqVar);
                LogUtil.a("HwWspMeasureController", "HwWspMeasureController enable notification enableOtaCharacter");
            }
        }
    }

    public void d(byte[] bArr, boolean z) {
        this.bk.a(bArr, z);
    }

    private void ad() {
        this.an = true;
        Bundle bundle = this.u;
        if (bundle != null) {
            this.bk.Fl_(this.aq, this.bc, this.at, bundle);
        } else {
            this.bk.Fk_(this.aq, this.bc, this.at);
        }
    }

    private void a(byte[] bArr) {
        int e2 = this.z.e(bArr);
        ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "reset wifi resultAck = ", Integer.valueOf(e2));
        if (e2 == 0) {
            LogUtil.h("HwWspMeasureController", "reset wifi failed");
            this.ai = false;
        } else {
            this.ai = true;
        }
    }

    private void e(byte[] bArr) {
        boolean ar = cpa.ar(this.at);
        LogUtil.a("HwWspMeasureController", "onReceiveAcceptanceLog isSupportUds=", Boolean.valueOf(ar));
        if (ar) {
            cpu.b(bArr);
            return;
        }
        if (this.i == null) {
            ab();
        }
        this.i.e(bArr);
    }

    private void d(byte[] bArr) {
        int e2 = this.z.e(bArr);
        LogUtil.a("HwWspMeasureController", "[grs] sendGrsUrl action result :", Integer.valueOf(e2));
        if (e2 == 0) {
            LogUtil.a("HwWspMeasureController", "[grs] sendGrsUrl success");
            EventBus.d(new EventBus.b("event_bus_send_ota_url_success"));
        } else {
            LogUtil.a("HwWspMeasureController", "[grs] sendGrsUrl failed");
        }
    }

    private void h(byte[] bArr, final cjf cjfVar) {
        if (cpa.ar(this.at)) {
            c(bArr, cjfVar);
            return;
        }
        if (this.ap == null) {
            this.ap = new MultiPackageDataRecLogic("ManagerInfoPack", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.5
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    if (cgt.this.c(bArr2, cjfVar)) {
                        return;
                    }
                    cgt.this.ap = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.ap.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(byte[] bArr, cjf cjfVar) {
        this.ba.c(3);
        this.av.d(0, cjfVar);
        cjh d2 = this.z.d(bArr);
        if (d2 == null) {
            EventBus.d(new EventBus.b("manager_info_failed", new Bundle()));
            this.ap = null;
            this.aq.removeMessages(7);
            this.aq.sendEmptyMessageDelayed(7, 200L);
            return true;
        }
        byte[] d3 = d2.d();
        this.w = d3;
        LogUtil.a("HwWspMeasureController", "onReceiveManagerInfo mHuid: ", CommonUtil.l(cvx.d(d3)));
        Bundle bundle = new Bundle();
        bundle.putByteArray("huid", d2.d());
        bundle.putString("cloudDeviceID", d2.b());
        if (!TextUtils.isEmpty(d2.e())) {
            bundle.putString("accountInfo", d2.e());
        }
        EventBus.d(new EventBus.b("manager_info_success", bundle));
        return false;
    }

    private void j(byte[] bArr, final cjf cjfVar) {
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiveAuthResponse randA = ", cvx.d(bArr));
        if (this.z.e(bArr) == 0) {
            EventBus.d(new EventBus.b("request_auth_pass"));
            return;
        }
        if (this.j == null) {
            this.j = new MultiPackageDataRecLogic("RequestAuthReceivePack", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.1
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    cgt.this.av.d(0, cjfVar);
                    LogUtil.c("HwWspMeasureController", "HwWspMeasureController randA = ", cvx.d(bArr2));
                    if (bArr2 == null) {
                        EventBus.d(new EventBus.b("request_auth_failed", new Bundle()));
                        cgt.this.j = null;
                        return;
                    }
                    if (bArr2.length == 0) {
                        EventBus.d(new EventBus.b("request_auth_pass"));
                    } else if (bArr2.length >= 16) {
                        cpu.d(bArr2, cgt.this.n, cgt.this.bd);
                    } else {
                        EventBus.d(new EventBus.b("request_auth_failed", new Bundle()));
                    }
                    cgt.this.j = null;
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.j.c(bArr);
    }

    private void i(byte[] bArr, final cjf cjfVar) {
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController onReceiveAuthTokenResponse receiveData token = ", cvx.d(bArr));
        if (this.j == null) {
            this.j = new MultiPackageDataRecLogic("RequestAuthTokenReceivePack", new MultiPackageDataRecLogic.MultiPackageCb() { // from class: cgt.8
                @Override // com.huawei.health.device.kit.hwwsp.hagrid.bean.MultiPackageDataRecLogic.MultiPackageCb
                public void onDataReceiveDone(byte[] bArr2) {
                    cgt.this.av.d(0, cjfVar);
                    LogUtil.c("HwWspMeasureController", "HwWspMeasureController tokens = ", cvx.d(bArr2));
                    if (bArr2 == null) {
                        EventBus.d(new EventBus.b("request_auth_failed", new Bundle()));
                        cgt.this.j = null;
                    } else {
                        cpu.e(bArr2);
                        cgt.this.j = null;
                    }
                }
            }, this.mDevice.getAddress(), BaseApplication.getContext());
        }
        this.j.c(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] v() {
        byte[] e2;
        byte[] bArr;
        if (Utils.i()) {
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            e2 = cpu.e(accountInfo);
            if (e2 == null) {
                LogUtil.h("HwWspMeasureController", "sendUserInfo huid is null");
                return null;
            }
            String i = this.o.i();
            if (i == null) {
                LogUtil.h("HwWspMeasureController", "sendUserInfo uids is null");
                bArr = new byte[32];
            } else {
                bArr = i.equals(accountInfo) ? new byte[32] : cpu.e(this.o.i());
            }
        } else {
            e2 = cpu.e("0");
            bArr = new byte[32];
        }
        byte[] bArr2 = bArr;
        byte[] bArr3 = e2;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        hiAggregateOption.setFilter("0");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final float[] fArr = {0.0f};
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: cgt.6
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (!HiCommonUtil.d(list)) {
                    fArr[0] = list.get(0).getFloat("bodyWeight");
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("HwWspMeasureController", "readHiHealthData InterruptedException");
        }
        float m = this.o.m();
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser != null && fArr[0] != 0.0f && this.o.i() != null && this.o.i().equals(mainUser.i())) {
            LogUtil.a("HwWspMeasureController", "mCurrentUser is mainUser");
            m = fArr[0];
        }
        LogUtil.a("HwWspMeasureController", "mainUser last weight is ", Float.valueOf(fArr[0]), ", currentUser weight is ", Float.valueOf(m));
        cjd cjdVar = new cjd();
        cjdVar.b(cgs.e(this.o.g(), this.o.a()));
        cjdVar.c(bArr3);
        cjdVar.c((int) this.o.c());
        cjdVar.c(m);
        cjdVar.e(bArr2);
        cjdVar.e(this.o.d());
        if (cgs.d(this.bd)) {
            cjdVar.a(cgs.a(this.o.g()));
        }
        return this.z.e(bArr3, bArr2, this.aw, 0, d(cjdVar));
    }

    private cfi d(cjd cjdVar) {
        if (cjdVar.e() != 0) {
            this.aw = 1;
        } else {
            this.aw = cjdVar.e();
        }
        cpw.c(true, "HwWspMeasureController", "mHeight =", Integer.valueOf(cjdVar.c()), ",mSex = ", Integer.valueOf(this.aw), ",mAge = ", Integer.valueOf(cjdVar.d()), "huids = ", this.bk.a(cjdVar.a()), "uids =", this.bk.a(cjdVar.j()));
        return cpu.d(cjdVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(cfi cfiVar) {
        return cfiVar == null ? Constants.NULL : cfiVar.a(false);
    }

    private byte[] EU_(Bundle bundle) {
        byte[] e2;
        byte[] bArr;
        if (bundle == null) {
            return new byte[32];
        }
        cfi cfiVar = this.o;
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        this.o = MultiUsersManager.INSTANCE.getCurrentUser();
        LogUtil.a("HwWspMeasureController", "getUserInfoFromBundle beforeUser: ", c(cfiVar), "\n mainUser: ", c(mainUser), "\n mCurrentUser: ", c(this.o));
        if (Utils.i()) {
            String string = bundle.getString("huid");
            e2 = cpu.e(string);
            String string2 = bundle.getString("uid");
            LogUtil.a("HwWspMeasureController", "getUserInfoFromBundle huidStr:", string, " uidStr:", string2);
            if (string2 == null) {
                LogUtil.h("HwWspMeasureController", "sendUserInfo uids is null");
                bArr = new byte[32];
            } else if (string2.equals(string)) {
                LogUtil.a("HwWspMeasureController", "send main user");
                bArr = new byte[32];
            } else {
                bArr = cpu.e(string2);
            }
        } else {
            e2 = cpu.e("0");
            bArr = new byte[32];
        }
        byte[] bArr2 = bArr;
        byte[] bArr3 = e2;
        cfi d2 = d(cpu.Kp_(bundle, bArr3, bArr2, this.bd));
        int i = bundle.getInt("guestUser", 0);
        if (i == 1) {
            bundle.putInt("guestUser", 0);
        }
        return this.z.e(bArr3, bArr2, this.aw, i, d2);
    }

    public void Fd_(Bundle bundle) {
        if (!cpa.ar(this.at) && this.h == null) {
            LogUtil.h("R_Weight_HwWspMeasureController", "bluetooth gatt is null");
        } else if (bundle != null && !bundle.isEmpty()) {
            c(EU_(bundle));
        } else {
            ah();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("R_Weight_HwWspMeasureController", "User info is empty.");
            return;
        }
        ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "sendUserInfo");
        this.bk.h(bArr);
        LogUtil.c("HwWspMeasureController", "HwWspMeasureController getUserInfo form HiHealthAPI, userinfo= ", this.bk.a(bArr));
    }

    private void ah() {
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback<cfi>() { // from class: cgt.10
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, cfi cfiVar) {
                ReleaseLogUtil.e("R_Weight_HwWspMeasureController", "sendCurrentUserInfo");
                if (cfiVar != null && i == 0) {
                    cgt cgtVar = cgt.this;
                    LogUtil.a("HwWspMeasureController", "sendCurrentUserInfo mCurrentUser:", cgtVar.c(cgtVar.o), " objData:", cgt.this.c(cfiVar));
                    cgt.this.o = cfiVar;
                    cgt.this.c(cgt.this.v());
                    return;
                }
                LogUtil.h("HwWspMeasureController", "HwWspMeasureController sendUserInfo getCurrentUser fail, errorCode:", Integer.valueOf(i));
            }
        });
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        if (cpa.ar(this.at)) {
            return null;
        }
        return this.s;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c9, code lost:
    
        if (r4 != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cb, code lost:
    
        if (r3 == null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00cd, code lost:
    
        aa();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d0, code lost:
    
        r4 = r10.h.writeDescriptor(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d7, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d8, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.b("HwWspMeasureController", "re enable failed, ", r11.getMessage());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean Fa_(android.bluetooth.BluetoothGattCharacteristic r11, defpackage.cjq r12) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r1 = "HwWspMeasureController"
            if (r12 == 0) goto Lf3
            cjq$a r2 = r12.b()
            if (r2 == 0) goto Lf3
            cjq$a r2 = r12.b()
            byte[][] r2 = r2.c()
            if (r2 != 0) goto L17
            goto Lf3
        L17:
            cjq$a r2 = r12.b()
            boolean r2 = r2.b()
            r3 = 0
            r4 = r0
            r5 = r4
        L22:
            r6 = 3
            if (r5 >= r6) goto Lc9
            android.bluetooth.BluetoothGatt r6 = r10.h
            if (r6 == 0) goto Lbd
            android.bluetooth.BluetoothGatt r6 = r10.h     // Catch: java.lang.SecurityException -> L52
            boolean r4 = r6.setCharacteristicNotification(r11, r2)     // Catch: java.lang.SecurityException -> L52
            java.util.UUID r6 = defpackage.chb.ab     // Catch: java.lang.SecurityException -> L52
            android.bluetooth.BluetoothGattDescriptor r3 = r11.getDescriptor(r6)     // Catch: java.lang.SecurityException -> L52
            if (r3 == 0) goto L60
            cjq$a r6 = r12.b()     // Catch: java.lang.SecurityException -> L52
            byte[][] r6 = r6.c()     // Catch: java.lang.SecurityException -> L52
            int r7 = r6.length     // Catch: java.lang.SecurityException -> L52
            r8 = r0
        L41:
            if (r8 >= r7) goto L4b
            r9 = r6[r8]     // Catch: java.lang.SecurityException -> L52
            r3.setValue(r9)     // Catch: java.lang.SecurityException -> L52
            int r8 = r8 + 1
            goto L41
        L4b:
            android.bluetooth.BluetoothGatt r6 = r10.h     // Catch: java.lang.SecurityException -> L52
            boolean r4 = r6.writeDescriptor(r3)     // Catch: java.lang.SecurityException -> L52
            goto L60
        L52:
            r6 = move-exception
            java.lang.String r7 = "setCharacteristicNotificationWithRetry, "
            java.lang.String r6 = r6.getMessage()
            java.lang.Object[] r6 = new java.lang.Object[]{r7, r6}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)
        L60:
            if (r4 != 0) goto L79
            r6 = 100
            defpackage.cpu.e(r6)
            java.lang.String r6 = "retry num "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            java.lang.String r8 = "enable retry failed "
            java.lang.Object[] r6 = new java.lang.Object[]{r8, r6, r7}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            int r5 = r5 + 1
            goto L22
        L79:
            java.util.UUID r12 = r11.getUuid()
            java.util.Set<java.lang.String> r0 = defpackage.cgt.b
            int r0 = r0.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r5 = "setCharacteristicNotification uuid "
            java.lang.String r6 = "current enable uuid set  "
            java.lang.Object[] r12 = new java.lang.Object[]{r5, r12, r6, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r12)
            if (r2 == 0) goto La2
            java.util.Set<java.lang.String> r12 = defpackage.cgt.b
            java.util.UUID r11 = r11.getUuid()
            java.lang.String r11 = r11.toString()
            r12.add(r11)
            goto Lc9
        La2:
            java.util.Set<java.lang.String> r12 = defpackage.cgt.b
            java.util.UUID r0 = r11.getUuid()
            java.lang.String r0 = r0.toString()
            r12.remove(r0)
            java.lang.String r12 = "remove uuid from mEnableCharactisticSet"
            java.util.UUID r11 = r11.getUuid()
            java.lang.Object[] r11 = new java.lang.Object[]{r12, r11}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r11)
            goto Lc9
        Lbd:
            java.lang.String r11 = "enableWithRetry mBluetoothGatt is null !"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            java.lang.String r12 = "R_Weight_HwWspMeasureController"
            com.huawei.hwlogsmodel.LogUtil.h(r12, r11)
            return r0
        Lc9:
            if (r4 != 0) goto Lf2
            if (r3 == 0) goto Lf2
            r10.aa()
            android.bluetooth.BluetoothGatt r11 = r10.h     // Catch: java.lang.SecurityException -> Ld7
            boolean r4 = r11.writeDescriptor(r3)     // Catch: java.lang.SecurityException -> Ld7
            goto Le5
        Ld7:
            r11 = move-exception
            java.lang.String r12 = "re enable failed, "
            java.lang.String r11 = r11.getMessage()
            java.lang.Object[] r11 = new java.lang.Object[]{r12, r11}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r11)
        Le5:
            java.lang.String r11 = "re enable re "
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r4)
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r12}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r11)
        Lf2:
            return r4
        Lf3:
            java.lang.String r11 = "setCharacteristicNotificationWithRetry illegal taskData"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cgt.Fa_(android.bluetooth.BluetoothGattCharacteristic, cjq):boolean");
    }

    private boolean EZ_(BluetoothGattCharacteristic bluetoothGattCharacteristic, cjq cjqVar) {
        if (this.h == null || cjqVar == null || cjqVar.b() == null) {
            LogUtil.a("HwWspMeasureController", "setCharacteristicNotification mBluetoothGatt is null");
            return false;
        }
        LogUtil.c("HwWspMeasureController", "setCharacteristicNotification uuid ", bluetoothGattCharacteristic.getUuid(), " isEnable", Boolean.valueOf(cjqVar.b().b()));
        boolean Fa_ = Fa_(bluetoothGattCharacteristic, cjqVar);
        if (Fa_ && cjqVar.b().b()) {
            aa();
        }
        return Fa_;
    }

    private void c(UUID uuid, UUID uuid2, cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
        boolean z;
        BluetoothGattService bluetoothGattService;
        synchronized (this.aj) {
            z = false;
            if (this.h != null) {
                try {
                    bluetoothGattService = this.h.getService(uuid);
                } catch (SecurityException e2) {
                    LogUtil.b("HwWspMeasureController", "getService failed, ", e2.getMessage());
                    bluetoothGattService = null;
                }
                if (bluetoothGattService != null) {
                    BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid2);
                    if (characteristic != null) {
                        z = EZ_(characteristic, cjqVar);
                    }
                } else {
                    LogUtil.h("R_Weight_HwWspMeasureController", "enable BluetoothGattService is null");
                }
            } else {
                LogUtil.h("R_Weight_HwWspMeasureController", "enable mBluetoothGatt is null");
            }
        }
        if (z) {
            if (iAsynBleTaskCallback != null) {
                LogUtil.a("HwWspMeasureController", "disable notification success exec mTaskCallback");
                iAsynBleTaskCallback.success(null);
                return;
            }
            return;
        }
        if (iAsynBleTaskCallback != null) {
            iAsynBleTaskCallback.failed();
            LogUtil.a("HwWspMeasureController", "disable notification fail exec mTaskCallback");
        }
    }

    private boolean EY_(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        String str;
        int i = 1;
        boolean z = true;
        int i2 = 0;
        while (true) {
            if (i2 >= 3) {
                str = "HwWspMeasureController";
                break;
            }
            bluetoothGattCharacteristic.setValue(bArr);
            bluetoothGattCharacteristic.setWriteType(i);
            if (this.h != null) {
                try {
                    z = this.h.writeCharacteristic(bluetoothGattCharacteristic);
                } catch (SecurityException e2) {
                    LogUtil.b("HwWspMeasureController", "sendWithRetry, writeCharacteristic ", e2.getMessage());
                    z = false;
                }
                str = "HwWspMeasureController";
                LogUtil.a(str, "SDK-->Device:", HexUtils.d(bArr), " tag : ", bluetoothGattCharacteristic.getUuid().toString(), " ret is ", Boolean.valueOf(z), "controller : ", this, "mIsGetRealTime ", Boolean.valueOf(this.ad));
                if (z) {
                    break;
                }
                cpu.e(100L);
                LogUtil.a(str, "write package retry failed ", this.bk.a(bArr), "retry num ", Integer.valueOf(i2));
                i2++;
                i = 1;
            } else {
                LogUtil.h("R_Weight_HwWspMeasureController", "mBluetoothGatt is null !");
                return false;
            }
        }
        boolean z2 = z;
        if (z2) {
            return z2;
        }
        aa();
        try {
            z2 = this.h.writeCharacteristic(bluetoothGattCharacteristic);
        } catch (SecurityException e3) {
            LogUtil.b(str, "rewrite failed, ", e3.getMessage());
        }
        boolean z3 = z2;
        LogUtil.a(str, "rewrite SDK-->Device:", HexUtils.d(bArr), " tag : ", bluetoothGattCharacteristic.getUuid().toString(), "ret is ", Boolean.valueOf(z3), " controller:", this, " mIsGetRealTime", Boolean.valueOf(this.ad));
        return z3;
    }

    private boolean Fb_(BluetoothGattCharacteristic bluetoothGattCharacteristic, ArrayList<byte[]> arrayList) {
        if (this.h == null || this.mDevice == null) {
            LogUtil.h("R_Weight_HwWspMeasureController", "mBluetoothGatt or mDevice is null");
            return false;
        }
        if (bluetoothGattCharacteristic == null || koq.b(arrayList)) {
            LogUtil.h("HwWspMeasureController", "writeCharacteristic data is null or empty");
            return false;
        }
        Iterator<byte[]> it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext() && (z = EY_(bluetoothGattCharacteristic, it.next()))) {
            cpu.e(10L);
        }
        return z;
    }

    private boolean Fc_(BluetoothGattCharacteristic bluetoothGattCharacteristic, ArrayList<byte[]> arrayList) {
        if (this.h == null || this.mDevice == null || koq.b(arrayList)) {
            LogUtil.h("HwWspMeasureController", "mBluetoothGatt or mDevice or packages is null");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            return false;
        }
        if (this.i == null) {
            ab();
        }
        Iterator<byte[]> it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext() && (z = EY_(bluetoothGattCharacteristic, it.next()))) {
            cpu.e(3L);
        }
        return z;
    }

    public void b(boolean z) {
        LogUtil.a("HwWspMeasureController", "Begin to get weight unit isManualGetUnit:", Boolean.valueOf(z));
        this.ag = z;
        this.bk.r();
    }

    public void q() {
        if (this.ad) {
            LogUtil.h("HwWspMeasureController", "RealTimeWeight has been send.");
            return;
        }
        this.bk.s();
        LogUtil.a("HwWspMeasureController", "RealTimeWeight send, set mIsMeasureButtonClicked = false");
        this.af = false;
        this.ad = true;
    }

    private boolean b(cjq cjqVar) {
        if (cjqVar == null) {
            return false;
        }
        UUID uuid = chb.an;
        UUID uuid2 = chb.f;
        LogUtil.a("HwWspMeasureController", "writeDataOnce taskType:", cjqVar.f());
        chb.c cVar = chb.c().get(cjqVar.f());
        if (cVar != null) {
            uuid = cVar.e();
            uuid2 = cVar.c();
        }
        return e(uuid, uuid2, cjqVar);
    }

    private boolean e(UUID uuid, UUID uuid2, cjq cjqVar) {
        BluetoothGattService bluetoothGattService;
        boolean Fc_;
        synchronized (this.aj) {
            if (this.h == null) {
                LogUtil.h("HwWspMeasureController", "HwWspMeasureController mBluetoothGatt is null");
                return false;
            }
            try {
                bluetoothGattService = this.h.getService(uuid);
            } catch (SecurityException e2) {
                LogUtil.b("HwWspMeasureController", "writeDataSucceeded, getService ", e2.getMessage());
                bluetoothGattService = null;
            }
            LogUtil.a("HwWspMeasureController", "gattService is : %s", bluetoothGattService);
            if (bluetoothGattService == null) {
                LogUtil.h("HwWspMeasureController", "HwWspMeasureController write BluetoothGattService is null");
                return false;
            }
            BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid2);
            LogUtil.a("HwWspMeasureController", "uuid:", uuid2, "gattCharacteristic:", characteristic);
            if (characteristic != null && cjqVar.c() != null) {
                int i = AnonymousClass7.c[cjqVar.f().ordinal()];
                if (i == 1) {
                    if (this.i == null) {
                        ab();
                    }
                    Fc_ = Fc_(characteristic, cpu.b(this.i.c(cjqVar.c())));
                } else if (i == 2) {
                    Fc_ = Fb_(characteristic, cgd.c(cjqVar.c(), cjqVar.i(), this.mDevice.getAddress(), this.am));
                } else {
                    Fc_ = Fb_(characteristic, cgd.a(cjqVar.c(), cjqVar.i(), this.mDevice.getAddress(), this.am));
                }
                LogUtil.a("HwWspMeasureController", "Write key:", cjqVar.f().toString(), " result:", Boolean.valueOf(Fc_));
                return Fc_;
            }
            LogUtil.h("HwWspMeasureController", "HwWspMeasureController gattCharacteristic is null");
            return false;
        }
    }

    /* renamed from: cgt$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[BleTaskQueueUtil.TaskType.values().length];
            c = iArr;
            try {
                iArr[BleTaskQueueUtil.TaskType.WRITE_BLE_FILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[BleTaskQueueUtil.TaskType.SEND_HILINK_CONFIG_INFO_ENCRYPTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    public void write(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
        boolean b2;
        cjqVar.d(iAsynBleTaskCallback);
        if ((!cpa.ar(this.at) && this.h == null) || this.mDevice == null) {
            this.ah = false;
            LogUtil.h("R_Weight_HwWspMeasureController", "mBluetoothGatt or mDevice is null when write");
            if (iAsynBleTaskCallback != null) {
                iAsynBleTaskCallback.failed();
                return;
            }
            return;
        }
        if (cpa.ar(this.at)) {
            b2 = c(cjqVar);
        } else {
            b2 = b(cjqVar);
        }
        if (!b2) {
            if (iAsynBleTaskCallback != null) {
                iAsynBleTaskCallback.failed();
                LogUtil.a("R_Weight_HwWspMeasureController", "write fail execute mTaskCallback");
                return;
            }
            return;
        }
        if (cjqVar.h() > 0) {
            if (cjqVar.d() < 1) {
                Message obtain = Message.obtain();
                obtain.obj = cjqVar;
                obtain.what = cjqVar.f().ordinal() + 1000;
                cjqVar.b(cjqVar.d() + 1);
                this.aq.sendMessageDelayed(obtain, cjqVar.h());
            } else {
                LogUtil.h("HwWspMeasureController", "Max retry times:", Integer.valueOf(cjqVar.d()), " data type : ", cjqVar.f());
            }
        }
        if (iAsynBleTaskCallback != null) {
            iAsynBleTaskCallback.success(null);
            LogUtil.a("HwWspMeasureController", "write success execute mTaskCallback");
        }
    }

    private boolean c(cjq cjqVar) {
        if (cjqVar == null) {
            return false;
        }
        synchronized (this.aj) {
            if (this.mDevice == null) {
                LogUtil.h("HwWspMeasureController", "writeDataUds : mDevice is null");
                return false;
            }
            String address = this.mDevice.getAddress();
            if (!TextUtils.isEmpty(address)) {
                UniteDevice d2 = cpl.c().d(address);
                bir[] d3 = cjg.d(cjqVar);
                if (d3 != null && d3[0].g() != null) {
                    LogUtil.a("HwWspMeasureController", "writeDataUds : Write key:", cjqVar.f().toString(), "; and sendCommand : uuid = ", d3[0].b(), " data = ", cvx.d(d3[0].e()), " SendMode = ", Integer.valueOf(d3[0].g().value()));
                } else {
                    LogUtil.h("HwWspMeasureController", "writeDataUds : Write key: udsCommands is null or udsCommands[0].getSendMode() is null");
                }
                if (d3 != null && d3.length > 0 && chb.g.toString().equals(d3[0].b())) {
                    e(d2, d3);
                } else {
                    cjg.d().c(d2, d3);
                }
                return true;
            }
            LogUtil.h("HwWspMeasureController", "writeDataUds : macAddress is null");
            return false;
        }
    }

    private void e(UniteDevice uniteDevice, bir[] birVarArr) {
        cjg.d().c(uniteDevice, new bir[]{birVarArr[0]});
        if (birVarArr.length > 1) {
            ArrayList<bir> arrayList = new ArrayList<>(10);
            for (int i = 1; i < birVarArr.length; i++) {
                arrayList.add(birVarArr[i]);
            }
            this.bk.c(arrayList);
            this.bk.d(uniteDevice);
        }
    }

    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    public void enable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
        UUID uuid = chb.am;
        UUID uuid2 = chb.b;
        LogUtil.a("HwWspMeasureController", "enable BluetoothGattService");
        chb.c cVar = chb.c().get(cjqVar.f());
        if (cVar != null) {
            uuid = cVar.e();
            uuid2 = cVar.c();
        }
        if (cpa.ar(this.at)) {
            cpu.d(uuid, uuid2, cjqVar, iAsynBleTaskCallback, this.mDevice);
        } else {
            c(uuid, uuid2, cjqVar, iAsynBleTaskCallback);
        }
    }

    @Override // com.huawei.health.device.kit.wsp.task.ITaskService
    public void disable(cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback) {
        if (cjqVar == null || cjqVar.b() == null || cjqVar.FN_() == null) {
            LogUtil.h("HwWspMeasureController", "disable illegal task data");
            return;
        }
        Bundle FN_ = cjqVar.FN_();
        if (FN_ == null || FN_.getSerializable("service_uuid") == null || FN_.getSerializable("characteristic_uuid") == null) {
            LogUtil.h("HwWspMeasureController", "disable notification serviceUuid/characteristicUuid is null");
            return;
        }
        Serializable serializable = FN_.getSerializable("service_uuid");
        if (serializable instanceof UUID) {
            UUID uuid = (UUID) serializable;
            Serializable serializable2 = FN_.getSerializable("characteristic_uuid");
            if (serializable2 instanceof UUID) {
                UUID uuid2 = (UUID) serializable2;
                if (cpa.ar(this.at)) {
                    cpu.d(uuid, uuid2, cjqVar, iAsynBleTaskCallback, this.mDevice);
                    return;
                } else {
                    c(uuid, uuid2, cjqVar, iAsynBleTaskCallback);
                    return;
                }
            }
            LogUtil.h("HwWspMeasureController", "disable characteristicUuid is not set value");
            return;
        }
        LogUtil.h("HwWspMeasureController", "disable serviceUuid is not set value");
    }

    public boolean d(UUID uuid) {
        return cpu.e(this.mDevice, this.at, d, uuid);
    }

    public void c(byte[] bArr, UUID uuid, UUID uuid2) {
        synchronized (this.aj) {
            int i = this.bc;
            boolean z = (i == -6 || i == -5) ? false : true;
            if (this.h != null && z) {
                if (this.ay == 2) {
                    LogUtil.a("HwWspMeasureController", "sending heart beat!");
                    EX_(this.h.getService(uuid), bArr, uuid2);
                } else {
                    LogUtil.h("R_Weight_HwWspMeasureController", "mState != BluetoothProfile.STATE_CONNECTED");
                }
            } else {
                LogUtil.h("R_Weight_HwWspMeasureController", "mBluetoothGatt is null");
            }
        }
    }

    public void a(BleTaskQueueUtil.TaskType taskType, byte[] bArr) {
        this.bk.d(taskType, bArr);
    }

    private void EX_(BluetoothGattService bluetoothGattService, byte[] bArr, UUID uuid) {
        if (bluetoothGattService != null) {
            BluetoothGattCharacteristic characteristic = bluetoothGattService.getCharacteristic(uuid);
            if (characteristic != null) {
                characteristic.setValue(bArr);
                LogUtil.a("HwWspMeasureController", "Write key:", " isSuccess:", Boolean.valueOf(Fb_(characteristic, cgd.a(bArr, false, this.mDevice.getAddress(), this.am))));
                return;
            } else {
                LogUtil.h("R_Weight_HwWspMeasureController", "gattCharacteristic is null");
                return;
            }
        }
        LogUtil.h("R_Weight_HwWspMeasureController", "write BluetoothGattService is null");
    }

    public void b() {
        this.bk.b();
    }

    public void e(int i) {
        this.bc = i;
    }

    public String f() {
        return this.at;
    }

    public String j() {
        return this.bd;
    }

    public void a(boolean z) {
        this.am = z;
    }

    public void d(boolean z) {
        this.al = z;
    }

    public boolean l() {
        return this.al;
    }

    public void a(int i) {
        this.bf = i;
    }

    public void b(int i) {
        this.az = i;
    }

    public String n() {
        return this.bj;
    }

    public void d(String str) {
        this.bj = str;
    }

    public boolean h() {
        return this.aa;
    }

    public void e(boolean z) {
        this.aa = z;
    }

    public void b(IHealthDeviceCallback iHealthDeviceCallback) {
        this.bk.a(iHealthDeviceCallback);
    }

    public void w() {
        this.bk.x();
    }

    public boolean i() {
        return this.an;
    }

    public boolean s() {
        return cpu.a(this.o);
    }

    public void r() {
        this.be = -1;
    }

    public void d() {
        this.bk.a(this.mDevice, 1);
    }
}
