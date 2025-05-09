package com.huawei.indoorequip.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.btsportdevice.callback.DataLifecycle;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import defpackage.dks;
import defpackage.jdx;
import defpackage.koq;
import defpackage.kzc;
import defpackage.lau;
import defpackage.lbv;
import health.compact.a.HuaweiHealth;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class IndoorEquipConnectedManager implements DataLifecycle {
    private Context c;
    private String d;
    private BluetoothAdapter e;
    private Handler f;
    private OnNfcConnectListener g;
    private kzc i;
    private long n = 0;
    private long o = 0;
    private boolean j = false;
    private String l = "Other";
    private QrCodeOrNfcInfo h = null;

    /* renamed from: a, reason: collision with root package name */
    private String f6423a = "";
    private String b = "";

    public interface OnNfcConnectListener {
        void onQrCodeOrNfcInfoSet(QrCodeOrNfcInfo qrCodeOrNfcInfo);

        void onStartTimeChange(long j);
    }

    @Override // com.huawei.btsportdevice.callback.DataLifecycle
    public void onPause() {
    }

    @Override // com.huawei.btsportdevice.callback.DataLifecycle
    public void onResume() {
    }

    @Override // com.huawei.btsportdevice.callback.DataLifecycle
    public void onStart() {
    }

    public IndoorEquipConnectedManager(Context context, OnNfcConnectListener onNfcConnectListener, Handler handler) {
        if (context == null) {
            this.c = BaseApplication.getContext();
        } else {
            this.c = context;
        }
        this.g = onNfcConnectListener;
        this.f = handler;
    }

    public QrCodeOrNfcInfo e() {
        return this.h;
    }

    public long d() {
        return this.n;
    }

    @Override // com.huawei.btsportdevice.callback.DataLifecycle
    public void onDestroy() {
        LogUtil.a("IndoorEquipConnectedManager", "onDestroy");
    }

    @Override // com.huawei.btsportdevice.callback.DataLifecycle
    public void init() {
        this.h = null;
        this.i = kzc.n();
        if (!(this.c.getSystemService("bluetooth") instanceof BluetoothManager)) {
            LogUtil.h("IndoorEquipConnectedManager", "not instance of BluetoothManager");
            return;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) this.c.getSystemService("bluetooth");
        if (bluetoothManager != null) {
            this.e = bluetoothManager.getAdapter();
        }
    }

    private void a(QrCodeOrNfcInfo qrCodeOrNfcInfo) {
        if (this.i.y()) {
            LogUtil.a("IndoorEquipConnectedManager", "in connectBtByNfc: WillNotResponseNfcAndQrcode is true, return");
            return;
        }
        this.h = qrCodeOrNfcInfo;
        d(qrCodeOrNfcInfo);
        BluetoothAdapter bluetoothAdapter = this.e;
        if (bluetoothAdapter == null) {
            LogUtil.h("IndoorEquipConnectedManager", "in connectBtByNfc: mBluetoothAdapter is null");
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            LogUtil.h("IndoorEquipConnectedManager", "in connectBtByNfc: mBluetoothAdapter not enabled");
            return;
        }
        Handler handler = this.f;
        if (handler == null) {
            LogUtil.h("IndoorEquipConnectedManager", "in connectBtByNfc: mHandler is null");
            return;
        }
        if (this.h == null) {
            handler.sendEmptyMessage(1001);
            return;
        }
        handler.sendEmptyMessage(311);
        int length = this.h.getBtMac().length();
        String str = Constants.LINK;
        if (length != 0) {
            Context context = this.c;
            String str2 = this.l;
            if (!this.j) {
                str = this.h.getBtName();
            }
            lbv.b(context, str2, str, this.h);
            c(this.h.getBtMac());
            return;
        }
        lbv.b(this.c, this.l, Constants.LINK, this.h);
        a(this.h.getBtName());
    }

    public void b(String str) {
        if (lbv.a()) {
            QrCodeOrNfcInfo analysisQrCodeOrNfc = QrCodeOrNfcInfo.analysisQrCodeOrNfc(str);
            this.h = analysisQrCodeOrNfc;
            if (analysisQrCodeOrNfc == null) {
                LogUtil.b("IndoorEquipConnectedManager", "connectBtByNfc data error ");
                return;
            }
            analysisQrCodeOrNfc.setDataSource(1);
            this.j = lbv.f(str);
            this.d = this.h.getDeviceType();
            this.o = SystemClock.elapsedRealtime();
            if ("".equals(dks.a("stype", str))) {
                this.l = "NFC";
            }
            a(this.h);
            b(this.o);
            LogUtil.a("IndoorEquipConnectedManager", "send LocalBroadcast action is CONNECT_BT_BY_NFC, ISFTMP: ", Boolean.valueOf(this.j));
            this.n = System.currentTimeMillis();
            if (str == null || !str.contains("&tvn=")) {
                return;
            }
            String c = lbv.c(str);
            if (TextUtils.isEmpty(c)) {
                return;
            }
            Intent intent = new Intent("com.huawei.health.BROADCAST_INTENT_CONNECT_TV");
            intent.putExtra("KEY_OF_TV_DEVICE_NAME", c);
            LocalBroadcastManager.getInstance(this.c).sendBroadcast(intent);
        }
    }

    private void b(long j) {
        OnNfcConnectListener onNfcConnectListener = this.g;
        if (onNfcConnectListener != null) {
            onNfcConnectListener.onStartTimeChange(j);
        }
    }

    private void d(QrCodeOrNfcInfo qrCodeOrNfcInfo) {
        OnNfcConnectListener onNfcConnectListener = this.g;
        if (onNfcConnectListener != null) {
            onNfcConnectListener.onQrCodeOrNfcInfoSet(qrCodeOrNfcInfo);
        }
    }

    private void bTA_(Intent intent) {
        if (lbv.a()) {
            BluetoothAdapter bluetoothAdapter = this.e;
            if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                LogUtil.h("IndoorEquipConnectedManager", "in connectBtByEmuiScan: mBluetoothAdapter not enabled");
                return;
            }
            if (this.h == null) {
                d(1001);
                return;
            }
            if ("ScanBtnAPP".equals(intent.getStringExtra("START_SPORT_SOURCE"))) {
                LogUtil.a("IndoorEquipConnectedManager", "start sport from bluetooth scan");
                this.l = "ScanBtnAPP";
            } else {
                LogUtil.a("IndoorEquipConnectedManager", "start sport from qrCode");
                this.h.setDataSource(2);
                this.l = "ScanApp";
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.o = elapsedRealtime;
            b(elapsedRealtime);
            d(this.h);
            if (this.i.y()) {
                LogUtil.b("IndoorEquipConnectedManager", "in connectBtByEmuiScan: WillNotResponseNfcAndQrcode is true, return");
                return;
            }
            boolean isEmpty = TextUtils.isEmpty(this.h.getBtMac());
            String str = Constants.LINK;
            if (!isEmpty) {
                Context context = this.c;
                String str2 = this.l;
                if (!this.j) {
                    str = this.h.getBtName();
                }
                lbv.b(context, str2, str, this.h);
                c(this.h.getBtMac());
            } else {
                lbv.b(this.c, this.l, Constants.LINK, this.h);
                a(this.h.getBtName());
            }
            LogUtil.a("IndoorEquipConnectedManager", "CONNECT_BT_BY_EMUI_SCAN, isFtmp: ", Boolean.valueOf(this.j));
            this.n = System.currentTimeMillis();
            String tvName = this.h.getTvName();
            if (TextUtils.isEmpty(tvName)) {
                return;
            }
            Intent intent2 = new Intent("com.huawei.health.BROADCAST_INTENT_CONNECT_TV");
            intent2.putExtra("KEY_OF_TV_DEVICE_NAME", tvName);
            LocalBroadcastManager.getInstance(this.c).sendBroadcast(intent2);
        }
    }

    private void a(String str) {
        this.f6423a = str;
        LogUtil.a("IndoorEquipConnectedManager", "connectBtByName");
        c(1, this.f6423a);
    }

    private void c(String str) {
        this.b = str;
        LogUtil.a("IndoorEquipConnectedManager", "connectBtByMac");
        c(0, this.b);
    }

    private void c(final int i, final String str) {
        this.i.c(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING);
        jdx.b(new Runnable() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedManager.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("IndoorEquipConnectedManager", "mFitnessClient is not null and will connect");
                lau.d().c(IndoorEquipConnectedManager.this.d);
                if (i == 0) {
                    lau.d().d(IndoorEquipConnectedManager.this.j, str);
                } else {
                    lau.d().a(IndoorEquipConnectedManager.this.j, str);
                }
                IndoorEquipConnectedManager.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        SharedPreferences.Editor edit = this.c.getSharedPreferences("IndoorEquipServiceRunning", 0).edit();
        edit.putString("lastConnectData", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        edit.apply();
    }

    public void d(final boolean z) {
        if (AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED.equals(this.i.m())) {
            this.i.c(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTING);
            LogUtil.a("IndoorEquipConnectedManager", "disconnectBt");
            jdx.b(new Runnable() { // from class: com.huawei.indoorequip.activity.IndoorEquipConnectedManager.1
                @Override // java.lang.Runnable
                public void run() {
                    lau.d().c(z);
                }
            });
        }
    }

    public void bTC_(Intent intent) {
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
        if (parcelableArrayExtra != null && parcelableArrayExtra.length > 0) {
            if (!a()) {
                LogUtil.a("IndoorEquipConnectedManager", "onNewIntent, mBlueConnectState is ", this.i.m());
                d(1005);
                return;
            }
            Parcelable parcelable = parcelableArrayExtra[0];
            String str = null;
            NdefMessage ndefMessage = parcelable instanceof NdefMessage ? (NdefMessage) parcelable : null;
            if (ndefMessage != null) {
                try {
                    if (koq.e(ndefMessage.getRecords(), 0) && ndefMessage.getRecords()[0] != null) {
                        str = new String(ndefMessage.getRecords()[0].getPayload(), "UTF-8");
                    }
                } catch (UnsupportedEncodingException unused) {
                    LogUtil.b("IndoorEquipConnectedManager", "disposeNdefDiscovered,UnsupportedEncodingException");
                    return;
                }
            }
            if (str == null) {
                LogUtil.b("IndoorEquipConnectedManager", "payLoad is null");
                return;
            }
            if (str.length() > 100) {
                LogUtil.a("IndoorEquipConnectedManager", "payload from NFC is too long");
                d(1002);
                return;
            }
            a(15);
            if (this.i.q()) {
                b(str);
                return;
            } else {
                d(1004);
                LogUtil.h("IndoorEquipConnectedManager", "onCreate, mService is still not running");
                return;
            }
        }
        LogUtil.b("IndoorEquipConnectedManager", "onNewIntent, Parcelable is null");
    }

    public void e(String str) {
        if (a()) {
            a(15);
            if (this.i.q()) {
                b(str);
                return;
            } else {
                d(1004);
                LogUtil.h("IndoorEquipConnectedManager", "onNewIntent, mService is still not running");
                return;
            }
        }
        d(1005);
        LogUtil.a("IndoorEquipConnectedManager", "session is running already");
    }

    public void bTD_(Intent intent) {
        QrCodeOrNfcInfo bTB_ = bTB_(intent);
        this.h = bTB_;
        if (!TextUtils.isEmpty(bTB_.getBtMac()) || !TextUtils.isEmpty(this.h.getBtName())) {
            LogUtil.a("IndoorEquipConnectedManager", "onNewIntent, start by emui scan");
            if (!a()) {
                d(1005);
                LogUtil.a("IndoorEquipConnectedManager", "session is running already");
                return;
            }
            a(15);
            if (this.i.q()) {
                bTA_(intent);
                return;
            } else {
                d(1004);
                LogUtil.h("IndoorEquipConnectedManager", "mService is still not running");
                return;
            }
        }
        d(1001);
        LogUtil.a("IndoorEquipConnectedManager", "onNewIntent but intent is not necessary to dispose");
    }

    private QrCodeOrNfcInfo bTB_(Intent intent) {
        QrCodeOrNfcInfo qrCodeOrNfcInfo = new QrCodeOrNfcInfo();
        if (intent != null) {
            qrCodeOrNfcInfo.setPid(intent.getStringExtra("PID_FROM_QRCODE"));
            String stringExtra = intent.getStringExtra("DEVICE_TYPE_INDEX");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "31";
            }
            this.d = stringExtra;
            qrCodeOrNfcInfo.setDeviceType(stringExtra);
            qrCodeOrNfcInfo.setBtMac(intent.getStringExtra("BLE_FROM_QRCODE"));
            qrCodeOrNfcInfo.setBtType("BLE");
            qrCodeOrNfcInfo.setBtName(intent.getStringExtra("BLENAME_FROM_QRCODE"));
            qrCodeOrNfcInfo.setTvName(intent.getStringExtra("TVNAME_FROM_QRCODE"));
            String stringExtra2 = intent.getStringExtra("PROTOCOL_FROM_QRCODE");
            this.j = TextUtils.isEmpty(stringExtra2) || "2".equals(stringExtra2);
            if (!TextUtils.isEmpty(qrCodeOrNfcInfo.getBtMac()) && lbv.g(qrCodeOrNfcInfo.getBtMac())) {
                qrCodeOrNfcInfo.setBtMac(lbv.e(qrCodeOrNfcInfo.getBtMac()));
            }
        }
        return qrCodeOrNfcInfo;
    }

    public void bTF_(Intent intent) {
        LogUtil.a("IndoorEquipConnectedManager", "onCreate, start by emui scan or click entrance btn");
        QrCodeOrNfcInfo bTB_ = bTB_(intent);
        this.h = bTB_;
        if (!TextUtils.isEmpty(bTB_.getBtMac()) || !TextUtils.isEmpty(this.h.getBtName())) {
            lbv.b(HuaweiHealth.a(), "ScanAPP", this.h.getBtMac());
            bTA_(intent);
        } else {
            lbv.b(HuaweiHealth.a(), "Click", this.h.getBtMac());
            LogUtil.a("IndoorEquipConnectedManager", "onCreate, start but all extra strings are null");
            d(1004);
        }
    }

    public DeviceInformation bTE_(Intent intent) {
        DeviceInformation deviceInformation = new DeviceInformation();
        if (intent == null) {
            return deviceInformation;
        }
        if (this.h == null) {
            this.h = bTB_(intent);
        }
        deviceInformation.setDeviceType(lbv.a(this.d));
        deviceInformation.setManufacturerString(intent.getStringExtra(ProfileRequestConstants.MANU));
        deviceInformation.setModelString(intent.getStringExtra("model"));
        return deviceInformation;
    }

    private boolean a() {
        kzc n = kzc.n();
        this.i = n;
        return AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED.equals(n.m()) || "unknown".equals(this.i.m());
    }

    private void d(int i) {
        Handler handler = this.f;
        if (handler != null) {
            handler.sendEmptyMessage(i);
        }
    }

    private void a(int i) {
        for (int i2 = 0; !this.i.q() && i2 < i; i2++) {
            SystemClock.sleep(100L);
        }
    }

    public boolean b() {
        return this.j;
    }
}
