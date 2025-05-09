package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public class cmu implements EventBus.ICallback {

    /* renamed from: a, reason: collision with root package name */
    private String f790a;
    private String b;
    private cmv c;
    private HagridDeviceManagerFragment d;

    public void c(cmv cmvVar, HagridDeviceManagerFragment hagridDeviceManagerFragment, String str, String str2) {
        this.c = cmvVar;
        this.d = hagridDeviceManagerFragment;
        this.f790a = str;
        this.b = str2;
    }

    @Override // com.huawei.health.device.util.EventBus.ICallback
    public void onEvent(EventBus.b bVar) {
        String e = bVar.e();
        Bundle Kl_ = bVar.Kl_();
        LogUtil.h("HagridDeviceEventManager", "mEventCallback action = ", e);
        if (Kl_ != null) {
            IM_(e, Kl_);
            IN_(e, Kl_);
        }
    }

    private void IM_(String str, Bundle bundle) {
        if ("get_device_ssid_result".equals(str)) {
            String string = bundle.getString("deviceSsid");
            if (this.c == null) {
                LogUtil.h("R_Weight_HagridDeviceEventManager", "EVEBUS_GET_DEVICE_SSID_RESULT onEvent myHandler is null.");
                return;
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", string, (StorageParams) null);
            if (this.d.getHuidType() == 1) {
                LogUtil.a("R_Weight_HagridDeviceEventManager", "ssid get success, and get manager success, return");
                return;
            } else {
                this.c.sendEmptyMessage(5);
                return;
            }
        }
        if ("manager_info_success".equals(str)) {
            this.d.setHuidType(1);
            IL_(bundle);
            return;
        }
        if ("manager_info_failed".equals(str)) {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "get manager info fail !");
            e();
            return;
        }
        if ("set_weight_unit_result".equals(str)) {
            cnn.b().Jn_(bundle);
            return;
        }
        if ("device_reset_result".equals(str)) {
            int i = bundle.getInt("ret");
            LogUtil.a("HagridDeviceEventManager", "set device reset ", Integer.valueOf(i));
            if (i == 0 && (cpa.ah(this.b) || cpa.r(this.b))) {
                cnc.b().Jd_("unbind_local_device", new Bundle());
                return;
            } else {
                cnc.b().Jd_("unbind_local_device", new Bundle());
                return;
            }
        }
        LogUtil.h("R_Weight_HagridDeviceEventManager", "HagridDeviceManagerFragment mEventCallback else procesEventActionOne");
    }

    private void e() {
        if (this.d.getHuidType() == 1) {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "get manager success, no refresh");
            return;
        }
        this.d.setHuidType(2);
        this.d.setHuid(null);
        c(false);
        cmv cmvVar = this.c;
        if (cmvVar == null) {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "EVEBUS_MANAGER_INFO_FAILED onEvent myHandler is null.");
        } else {
            cmvVar.sendEmptyMessage(2);
        }
    }

    private void IN_(String str, Bundle bundle) {
        if ("get_weight_unit_result".equals(str)) {
            cnn.b().Jm_(bundle);
            return;
        }
        if ("multi_user_auto_cancle_dialog".equals(str)) {
            cmv cmvVar = this.c;
            if (cmvVar == null) {
                LogUtil.h("R_Weight_HagridDeviceEventManager", "EVEBUS_WIFI_DEVICE_MULTI_USER_DIALOG onEvent myHandler is null.");
                return;
            } else {
                cmvVar.obtainMessage(6).sendToTarget();
                return;
            }
        }
        if ("sub_user_unauthorize_notification".equals(str)) {
            b();
            return;
        }
        if ("wifi_scale_auth_refresh".equals(str)) {
            cmv cmvVar2 = this.c;
            if (cmvVar2 == null) {
                LogUtil.h("R_Weight_HagridDeviceEventManager", "EVEBUS_WIFI_SCALE_AUTH_REFRESH onEvent myHandler is null.");
                return;
            } else {
                cmvVar2.obtainMessage(8).sendToTarget();
                this.c.obtainMessage(6).sendToTarget();
                return;
            }
        }
        if ("event_bus_sub_user_exit_device".equals(str)) {
            cnc.b().a(ceo.d().d(this.f790a, false));
        } else if ("event_bus_config_wifi".equals(str)) {
            LogUtil.h("HagridDeviceEventManager", "EVENTBUS_CONFIG_WIFI onEvent myHandler is null.");
            this.c.sendEmptyMessage(10);
        } else {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "HagridDeviceManagerFragment mEventCallback else procesEventActionTwo");
        }
    }

    private void IL_(Bundle bundle) {
        byte[] bArr = null;
        this.d.setHuid(null);
        if (bundle != null) {
            try {
                if (bundle.getByteArray("huid") != null) {
                    bArr = bundle.getByteArray("huid");
                    String string = bundle.getString("cloudDeviceID");
                    c(bArr != null && bArr.length > 0);
                    if (!TextUtils.isEmpty(string)) {
                        string = string.trim();
                    }
                    this.d.setHuid(bArr);
                    this.d.setDevId(string);
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("HagridDeviceEventManager", "HagridDeviceManagerFragment dealEventReceiveManageInfo ArrayIndexOutOfBoundsException");
            }
        }
        if (bArr != null) {
            String e = ddh.c().e(bArr);
            LogUtil.c("HagridDeviceEventManager", "main huid ", e);
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(accountInfo)) {
                try {
                    boolean startsWith = e.startsWith(HexUtils.d(accountInfo.getBytes("UTF-8")));
                    this.d.setMainUser(startsWith);
                    LogUtil.a("HagridDeviceEventManager", "mIsMainUser: ", Boolean.valueOf(startsWith));
                } catch (UnsupportedEncodingException e2) {
                    LogUtil.b("HagridDeviceEventManager", "dealEventReceiveManageInfo error :", e2.getMessage());
                }
            }
            LogUtil.a("HagridDeviceEventManager", "mIsMainUser: ");
        } else {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "dealEventReceiveManageInfo huid is null");
        }
        if (this.c == null) {
            this.c = new cmv(this.d);
        }
        this.c.sendEmptyMessage(2);
    }

    private void c(boolean z) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("device_has_manager");
        stringBuffer.append(this.f790a);
        deviceCloudSharePreferencesManager.b(stringBuffer.toString(), z);
    }

    public void b() {
        if (this.d.getWiFiDevice() == null) {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "getAuthorizeSubUserFromCloud mWiFiDevice is null");
        } else if (c()) {
            LogUtil.h("R_Weight_HagridDeviceEventManager", "getAuthorizeSubUserFromCloud is sub devices");
        } else {
            csf.e(this.d.getWiFiDevice().d(), new CommBaseCallbackInterface() { // from class: cmw
                @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface
                public final void onResult(int i, String str, Object obj) {
                    cmu.this.b(i, str, obj);
                }
            });
        }
    }

    /* synthetic */ void b(int i, String str, Object obj) {
        cmv cmvVar;
        if (i == 0 && obj != null && (cmvVar = this.c) != null) {
            cmvVar.obtainMessage(6).sendToTarget();
        } else {
            LogUtil.h("HagridDeviceEventManager", "getAuthorizeSubUserFromCloud errCode:", Integer.valueOf(i));
        }
    }

    private boolean c() {
        if (this.d.getWiFiDevice() == null || this.d.getWiFiDevice().b().k() != 2) {
            return false;
        }
        MeasurableDevice d = ceo.d().d(this.f790a, true);
        return d == null || (d instanceof ctk);
    }
}
