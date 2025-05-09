package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.baseapi.pluginaudiodevice.ResultCallback;
import com.huawei.health.baseapi.pluginaudiodevice.StateCallback;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.ecologydevice.callback.NemoDeviceCallback;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.ecologydevice.util.BrUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import defpackage.dcp;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class dcp {
    public void d(final String str, final NemoDeviceCallback nemoDeviceCallback) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            LogUtil.h("NemoDeviceManager", "Discover And Bond, Mac is Wrong.");
            nemoDeviceCallback.bondError(-1);
            return;
        }
        final BrUtils brUtils = new BrUtils();
        if (brUtils.d()) {
            LogUtil.a("NemoDeviceManager", "Bluetooth Opened.");
            a(str, brUtils, nemoDeviceCallback);
        } else {
            brUtils.a(new BrUtils.OnBrOpenListener() { // from class: dcp.2
                @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrOpenListener
                public void onStateError() {
                }

                @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrOpenListener
                public void onStateOff() {
                }

                @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrOpenListener
                public void onStateOn() {
                    dcp.this.a(str, brUtils, nemoDeviceCallback);
                }
            });
        }
    }

    public void d(Context context, final String str) {
        if (context == null || !BluetoothAdapter.checkBluetoothAddress(str)) {
            LogUtil.h("NemoDeviceManager", "Context is null or mac is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("huaweischeme://healthapp/audio?action=detail"));
        Bundle bundle = new Bundle();
        bundle.putString("mac", str);
        bundle.putString("productId", "ZAA6");
        intent.putExtras(bundle);
        intent.addFlags(268435456);
        bzt.c().launchActivity(context, intent, new AppBundleLauncher.InstallCallback() { // from class: dcl
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context2, Intent intent2) {
                return dcp.this.TJ_(str, context2, intent2);
            }
        });
        e("", "SMART_HEADPHONES", false);
        ReleaseLogUtil.e("DEVMGR_NemoDeviceManager", "intoNemoPage enter, mac:", CommonUtil.l(str));
        ((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).setDevicesUseTime(str);
    }

    /* synthetic */ boolean TJ_(String str, Context context, Intent intent) {
        LogUtil.a("NemoDeviceManager", "Plugin Called.");
        e(context, str);
        return true;
    }

    private void e(final Context context, final String str) {
        bzt.c().initAudioDeviceKitAdapter(context, new StateCallback() { // from class: dco
            @Override // com.huawei.health.baseapi.pluginaudiodevice.StateCallback
            public final void onResult(int i, Object obj) {
                dcp.this.e(context, str, i, (String) obj);
            }
        });
    }

    /* synthetic */ void e(Context context, String str, int i, String str2) {
        if (i == 1) {
            a(context, str);
        } else if (i == 2) {
            bzt.c().unregisterConnectStatus(context, str);
        }
    }

    /* renamed from: dcp$5, reason: invalid class name */
    class AnonymousClass5 implements ResultCallback<String> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f11590a;
        final /* synthetic */ Context c;

        AnonymousClass5(Context context, String str) {
            this.c = context;
            this.f11590a = str;
        }

        @Override // com.huawei.health.baseapi.pluginaudiodevice.ResultCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            bzt.c().unregisterConnectStatus(this.c, this.f11590a);
            cwu cwuVar = new cwu();
            cwuVar.d(this.f11590a, str, "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c", "");
            cwuVar.b(this.f11590a, str, "4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c");
            dew.c("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c", this.f11590a, str);
        }

        @Override // com.huawei.health.baseapi.pluginaudiodevice.ResultCallback
        public void onFailure(int i, String str) {
            bzt c = bzt.c();
            final Context context = this.c;
            final String str2 = this.f11590a;
            c.registerConnectStatus(context, str2, new StateCallback() { // from class: dct
                @Override // com.huawei.health.baseapi.pluginaudiodevice.StateCallback
                public final void onResult(int i2, Object obj) {
                    dcp.AnonymousClass5.this.e(context, str2, i2, (String) obj);
                }
            });
        }

        /* synthetic */ void e(Context context, String str, int i, String str2) {
            if (i != 1) {
                return;
            }
            dcp.this.a(context, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str) {
        bzt.c().obtainDeviceSn(context, str, new AnonymousClass5(context, str));
    }

    private void e(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, str);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, str2);
        hashMap.put("data_type", Boolean.valueOf(z));
        ixx.d().d(BaseApplication.getContext(), "2170023", hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final BrUtils brUtils, final NemoDeviceCallback nemoDeviceCallback) {
        BluetoothDevice Wa_ = brUtils.Wa_(str);
        if (Wa_ != null) {
            if (nemoDeviceCallback != null) {
                nemoDeviceCallback.bondSuccess(Wa_);
                dko.b("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c", str, str);
                return;
            }
            return;
        }
        LogUtil.a("NemoDeviceManager", "Start bind.");
        BluetoothDevice Wb_ = brUtils.Wb_(str);
        if (Wb_ != null) {
            TI_(Wb_, brUtils, new NemoDeviceCallback() { // from class: dcp.4
                @Override // com.huawei.health.ecologydevice.callback.NemoDeviceCallback
                public void bondSuccess(BluetoothDevice bluetoothDevice) {
                    nemoDeviceCallback.bondSuccess(bluetoothDevice);
                }

                @Override // com.huawei.health.ecologydevice.callback.NemoDeviceCallback
                public void bondError(int i) {
                    LogUtil.a("NemoDeviceManager", "remote bondDevice error");
                    if (i != 1) {
                        dcp.this.c(str, brUtils, nemoDeviceCallback);
                    } else {
                        nemoDeviceCallback.bondError(i);
                    }
                }
            });
        } else {
            c(str, brUtils, nemoDeviceCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str, final BrUtils brUtils, final NemoDeviceCallback nemoDeviceCallback) {
        brUtils.c(new BrUtils.OnBrDiscoverListener() { // from class: dcp.3
            @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrDiscoverListener
            public boolean onDiscoverResult(BluetoothDevice bluetoothDevice) {
                String address = bluetoothDevice.getAddress();
                LogUtil.a("NemoDeviceManager", "discover mac:", CommonUtil.l(address));
                if (!str.equals(address)) {
                    return false;
                }
                LogUtil.a("NemoDeviceManager", "Device is discovered");
                dcp.this.TI_(bluetoothDevice, brUtils, nemoDeviceCallback);
                return true;
            }

            @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrDiscoverListener
            public void onDiscoverUnResult() {
                LogUtil.a("NemoDeviceManager", "discover device timeout");
                NemoDeviceCallback nemoDeviceCallback2 = nemoDeviceCallback;
                if (nemoDeviceCallback2 != null) {
                    nemoDeviceCallback2.bondError(1);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void TI_(BluetoothDevice bluetoothDevice, BrUtils brUtils, final NemoDeviceCallback nemoDeviceCallback) {
        brUtils.e();
        LogUtil.a("NemoDeviceManager", "start bondDevice");
        brUtils.VZ_(bluetoothDevice, new BrUtils.OnBrBondListener() { // from class: dcp.1
            @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrBondListener
            public void onBondSuccess(BluetoothDevice bluetoothDevice2) {
                LogUtil.a("NemoDeviceManager", "bondDevice success");
                String address = bluetoothDevice2.getAddress();
                dko.b("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c", address, address);
                NemoDeviceCallback nemoDeviceCallback2 = nemoDeviceCallback;
                if (nemoDeviceCallback2 != null) {
                    nemoDeviceCallback2.bondSuccess(bluetoothDevice2);
                }
            }

            @Override // com.huawei.health.ecologydevice.util.BrUtils.OnBrBondListener
            public void onBondError(int i) {
                LogUtil.a("NemoDeviceManager", "bondDevice error:", Integer.valueOf(i));
                NemoDeviceCallback nemoDeviceCallback2 = nemoDeviceCallback;
                if (nemoDeviceCallback2 == null) {
                    return;
                }
                if (i == 12000) {
                    nemoDeviceCallback2.bondError(1);
                } else {
                    nemoDeviceCallback2.bondError(2);
                }
            }
        });
    }
}
