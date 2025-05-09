package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jgs implements NotifyPhoneServiceCallback {

    /* renamed from: a, reason: collision with root package name */
    private static volatile jgs f13829a;
    private EarPhoneResponseCallback c;
    private jfq e;

    @Override // com.huawei.hwdevice.mainprocess.callback.NotifyPhoneServiceCallback
    public void executeResponse(int i, DeviceInfo deviceInfo, int i2, String str) {
        cuz cuzVar = new cuz();
        try {
            cuzVar = (cuz) new Gson().fromJson(str, new TypeToken<cuz>() { // from class: jgs.3
            }.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b("HwEarPhonePairManager", "getEarPhoneInfo e ", ExceptionUtils.d(e));
        }
        EarPhoneResponseCallback earPhoneResponseCallback = this.c;
        if (earPhoneResponseCallback != null) {
            earPhoneResponseCallback.onResponse(i, cuzVar);
        }
    }

    private jgs() {
        e();
    }

    public static jgs c() {
        if (f13829a == null) {
            synchronized (jgs.class) {
                if (f13829a == null) {
                    f13829a = new jgs();
                }
            }
        }
        return f13829a;
    }

    private void e() {
        jfq c = jfq.c();
        this.e = c;
        c.e("earphoneManager", this);
    }

    public void d(DeviceInfo deviceInfo, EarPhoneResponseCallback earPhoneResponseCallback) {
        this.c = earPhoneResponseCallback;
        this.e.d("earphoneManager", deviceInfo, 0, "start_pair_info");
    }

    public void b(DeviceInfo deviceInfo, EarPhoneResponseCallback earPhoneResponseCallback) {
        this.c = earPhoneResponseCallback;
        this.e.d("earphoneManager", deviceInfo, 0, "start_pair_info_back_device");
    }

    public void b(DeviceInfo deviceInfo) {
        this.e.d("earphoneManager", deviceInfo, 0, "retry_pair_info");
    }

    public void d(DeviceInfo deviceInfo) {
        this.e.d("earphoneManager", deviceInfo, 0, "skip_pair_info");
    }

    public void a(DeviceInfo deviceInfo, String str) {
        this.e.d("earphoneManager", deviceInfo, 0, "cancel_pair_ear_phone");
    }

    public void a(DeviceInfo deviceInfo, EarPhoneResponseCallback earPhoneResponseCallback) {
        this.c = earPhoneResponseCallback;
        this.e.d("earphoneManager", deviceInfo, 0, "pull_up_earphone_pair");
    }

    public void c(final DeviceInfo deviceInfo, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwEarPhonePairManager", "pullUpEarphonePair in. ");
        c().a(deviceInfo, new EarPhoneResponseCallback() { // from class: jgs.2
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
            public void onResponse(int i, cuz cuzVar) {
                LogUtil.a("HwEarPhonePairManager", "pullUpEarphonePair response. errorCode： ", Integer.valueOf(i));
                if (i == 11) {
                    c(cuzVar);
                }
            }

            private void c(final cuz cuzVar) {
                jgs.this.d(deviceInfo, new IBaseResponseCallback() { // from class: jgs.2.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i == 0) {
                            iBaseResponseCallback.d(i, cuzVar);
                        }
                    }
                });
            }
        });
    }

    public void e(DeviceInfo deviceInfo, EarPhoneResponseCallback earPhoneResponseCallback) {
        this.c = earPhoneResponseCallback;
        this.e.d("earphoneManager", deviceInfo, 0, "get_ear_phone_info");
    }

    public void c(DeviceInfo deviceInfo, EarPhoneResponseCallback earPhoneResponseCallback) {
        this.c = earPhoneResponseCallback;
        this.e.d("earphoneManager", deviceInfo, 0, "get_cached_ear_phone_info");
    }

    public boolean b(String str) {
        LogUtil.a("HwEarPhonePairManager", "isEarphonePairedWithPhone: ", str);
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            if (remoteDevice == null) {
                LogUtil.h("HwEarPhonePairManager", "isEarphonePairedWithPhone error. mBluetoothDevice is null.");
                return false;
            }
            int bondState = remoteDevice.getBondState();
            LogUtil.a("HwEarPhonePairManager", "isEarphonePairedWithPhone createBondDevice bondState: ", Integer.valueOf(bondState));
            return bondState == 12;
        } catch (IllegalArgumentException | SecurityException e) {
            LogUtil.b("HwEarPhonePairManager", "isEarphonePairedWithPhone exception:", ExceptionUtils.d(e));
            return false;
        }
    }

    public void d(DeviceInfo deviceInfo, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwEarPhonePairManager", "isEarphonePairedWithWatch in. ");
        c(deviceInfo, new EarPhoneResponseCallback() { // from class: jgs.5
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
            public void onResponse(int i, cuz cuzVar) {
                if (cuzVar == null) {
                    iBaseResponseCallback.d(1, null);
                } else if ((cuzVar.d() & 1) > 0) {
                    iBaseResponseCallback.d(0, cuzVar);
                } else {
                    iBaseResponseCallback.d(1, cuzVar);
                }
            }
        });
    }

    public void a(DeviceInfo deviceInfo) {
        this.e.d("earphoneManager", deviceInfo, 0, "pair_fail");
    }

    private void a() {
        this.e.a("earphoneManager");
    }

    public void d() {
        a();
        this.c = null;
        this.e = null;
        b();
    }

    private void b() {
        synchronized (jgs.class) {
            f13829a = null;
        }
    }
}
