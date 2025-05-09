package defpackage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.health.R;
import com.huawei.health.device.callback.ScaleDialogCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUnbindReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.dialog.CustomButtonMenuDialog;
import defpackage.coy;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class coy {
    public void d(String str, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        if (str == null) {
            LogUtil.h("HagrideDeviceFragmentHelper", " proccessUnbind deviceId is null");
            return;
        }
        WifiDeviceUnbindReq wifiDeviceUnbindReq = new WifiDeviceUnbindReq();
        wifiDeviceUnbindReq.setDevId(str);
        LogUtil.a("HagrideDeviceFragmentHelper", "onClickUnBind :", cpw.a(str));
        jbs.a(cpp.a()).c(wifiDeviceUnbindReq, iCloudOperationResult);
    }

    public void JX_(ContentValues contentValues, ctk ctkVar, dcz dczVar) {
        if (contentValues == null || TextUtils.isEmpty(contentValues.getAsString("uniqueId")) || TextUtils.isEmpty(contentValues.getAsString("productId"))) {
            LogUtil.h("HagrideDeviceFragmentHelper", "unBindLocalWiFiDevice illegal device info !");
            return;
        }
        String asString = contentValues.getAsString("productId");
        String asString2 = contentValues.getAsString("uniqueId");
        LogUtil.a("HagrideDeviceFragmentHelper", " unBindLocalWiFiDevice currentDevice mBindProductId:", cpw.d(asString));
        LogUtil.a("HagrideDeviceFragmentHelper", " unBindLocalWiFiDevice currentDevice mBindUniqueId:", cpw.d(asString2));
        d(asString, asString2);
        LogUtil.a("HagrideDeviceFragmentHelper", " unBindLocalWiFiDevice removeDeviceFromProfile is pass");
        MeasurableDevice d = ceo.d().d(asString2, true);
        if (!TextUtils.isEmpty(asString) && ResourceManager.e().d(asString) != null) {
            MeasureController measureController = cjx.e().g(ResourceManager.e().d(asString).s()).getMeasureController();
            Bundle bundle = new Bundle();
            bundle.putInt("type", -5);
            bundle.putString("productId", asString);
            if (measureController != null) {
                measureController.prepare(d, null, bundle);
            } else {
                LogUtil.h("HagrideDeviceFragmentHelper", "the measurement can be performed only on the device");
            }
            e(ctkVar, asString, dczVar);
            cjx.e().e(asString, asString2, -6);
            cjx.e().b(asString, asString2);
            cjx.e().o(asString2);
            cpa.j(asString2, "");
            ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).resetBandUpdate(asString, asString2);
            c(dczVar.n().b());
            if (ctkVar != null) {
                ClaimWeightDataManager.INSTANCE.unRegisterCallBack(getClass().getSimpleName());
            }
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append("weightUnit");
            if (!TextUtils.isEmpty(asString2)) {
                stringBuffer.append(asString2);
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), stringBuffer.toString(), "", (StorageParams) null);
            return;
        }
        LogUtil.h("HagrideDeviceFragmentHelper", "get productInfo error by productId");
    }

    private void c(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap, 0);
    }

    private void e(ctk ctkVar, String str, dcz dczVar) {
        LogUtil.h("HagrideDeviceFragmentHelper", "configurationUnbindInformation");
        Context context = BaseApplication.getContext();
        if (ctkVar != null) {
            SharedPreferenceManager.e(context, "wifi_weight_device", "support_multi_account_" + ctkVar.d(), "", (StorageParams) null);
            SharedPreferenceManager.e(context, "wifi_weight_device", "health_is_wifi_add_member_first_" + ctkVar.d(), "false", (StorageParams) null);
        } else {
            LogUtil.h("HagrideDeviceFragmentHelper", "configurationUnbindInformation, device of wifi is null");
        }
        cgt.e().b();
        SharedPreferenceManager.e(context, String.valueOf(10000), "pressure_calibrate_hrv_userinfo_" + str, "", (StorageParams) null);
        SharedPreferenceManager.e(context, String.valueOf(10000), "wife_device_send_event_to_kaka_" + dczVar.g(), "", (StorageParams) null);
        SharedPreferenceManager.e(context, String.valueOf(10000), "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", "", (StorageParams) null);
    }

    public void a(final Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null) {
            LogUtil.h("HagrideDeviceFragmentHelper", "chooseShowOpenNoticeBarMsgSwitchDialog, context is null");
            iBaseResponseCallback.d(-1, "context is null");
            return;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "health_msg_switch_noticebar");
        LogUtil.a("HagrideDeviceFragmentHelper", "chooseShowOpenNoticeBarMsgSwitchDialog, switchStatus = ", b);
        if (TextUtils.isEmpty(b)) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "health_msg_switch_noticebar", "1", new StorageParams());
        }
        if ("0".equals(b)) {
            cqh.c().d(context, new ScaleDialogCallback() { // from class: cou
                @Override // com.huawei.health.device.callback.ScaleDialogCallback
                public final void operationResult(int i) {
                    coy.d(context, iBaseResponseCallback, i);
                }
            });
        } else {
            iBaseResponseCallback.d(-1, "switch isn't close");
        }
    }

    static /* synthetic */ void d(Context context, IBaseResponseCallback iBaseResponseCallback, int i) {
        if (i == 0) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "health_msg_switch_noticebar", "1", new StorageParams());
            iBaseResponseCallback.d(0, "click positive button");
        } else {
            SharedPreferenceManager.e(context, Integer.toString(10000), "health_msg_switch_noticebar", "0", new StorageParams());
            iBaseResponseCallback.d(0, "click negative button");
        }
    }

    public static void b(String str, String str2) {
        LogUtil.a("HagrideDeviceFragmentHelper", "uploadDeviceToCloud, uniqueId ", cpw.d(str2));
        MeasurableDevice d = ceo.d().d(str2, false);
        if (d == null) {
            LogUtil.h("HagrideDeviceFragmentHelper", " uploadDeviceToCloud device not exist");
            return;
        }
        String e = e(d, str);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("HagrideDeviceFragmentHelper", "can not upload device, device id is null");
            return;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HagrideDeviceFragmentHelper", " uploadDeviceToCloud productInfo is null");
            return;
        }
        String str3 = dcx.d(str, d2.n().b()) + dij.b(e);
        LogUtil.a("HagrideDeviceFragmentHelper", "uploadDeviceToCloud device unique id is", cpw.a(e), "name", str3);
        crt.d(e, str3, d);
    }

    public static void d(String str, String str2) {
        MeasurableDevice d = ceo.d().d(str2, false);
        crt.a(e(d, str), d instanceof ctk ? "cloud" : "local");
    }

    private static String e(HealthDevice healthDevice, String str) {
        if (healthDevice == null) {
            LogUtil.h("HagrideDeviceFragmentHelper", "getSmartLifeProfileDeviceId device is null");
            return "";
        }
        String uniqueId = healthDevice.getUniqueId();
        if (TextUtils.isEmpty(uniqueId)) {
            LogUtil.h("HagrideDeviceFragmentHelper", "getSmartLifeProfileDeviceId uniqueId is empty");
            return "";
        }
        if (cpa.ah(str)) {
            if (!(healthDevice instanceof MeasurableDevice)) {
                return uniqueId;
            }
            MeasurableDevice measurableDevice = (MeasurableDevice) healthDevice;
            return !TextUtils.isEmpty(measurableDevice.getSerialNumber()) ? measurableDevice.getSerialNumber() : uniqueId;
        }
        String l = cpa.l(uniqueId);
        if (!TextUtils.isEmpty(l)) {
            return l;
        }
        if (healthDevice instanceof ctk) {
            return ((ctk) healthDevice).b().m();
        }
        LogUtil.h("HagrideDeviceFragmentHelper", "getSmartLifeProfileDeviceId fail to get device id");
        return l;
    }

    public static boolean e(String str) {
        LogUtil.a("HagrideDeviceFragmentHelper", "getDeviceManagerStatus uniqueId: ", CommonUtil.l(str));
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("device_has_manager");
        stringBuffer.append(str);
        return deviceCloudSharePreferencesManager.e(stringBuffer.toString());
    }

    public static boolean JU_(byte[] bArr, Activity activity, String str) {
        if (bArr != null) {
            String e = ddh.c().e(bArr);
            LogUtil.a("HagrideDeviceFragmentHelper", "main huid = ", cpw.d(e));
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (!TextUtils.isEmpty(e) && !TextUtils.isEmpty(accountInfo)) {
                try {
                    return e.startsWith(HexUtils.d(accountInfo.getBytes("UTF-8")));
                } catch (UnsupportedEncodingException e2) {
                    LogUtil.b(str, "getCurrentUserIsMainUser error :", e2.getMessage());
                }
            } else {
                LogUtil.h(str, "mIsMainUser isEmpty");
            }
        } else {
            LogUtil.h(str, "getCurrentUserIsMainUser huid is null");
        }
        return false;
    }

    public static void c(Context context) {
        if (context == null) {
            LogUtil.h("R_Weight_HagrideDeviceFragmentHelper", "showWiFiEnableDialog context is null");
            return;
        }
        String string = context.getString(R.string._2130841130_res_0x7f020e2a);
        String string2 = context.getString(R.string._2130842176_res_0x7f021240);
        if (string.length() > 10 || string2.length() > 10) {
            e(context);
        } else {
            d(context);
        }
    }

    private static void e(final Context context) {
        int i;
        int i2;
        if (Utils.o()) {
            i = R.string._2130840662_res_0x7f020c56;
            i2 = R.string.IDS_device_wifi_tip_over_sea;
        } else {
            i = R.string._2130840661_res_0x7f020c55;
            i2 = R.string.IDS_device_request_auto_sync_data_open_wifi_tip_msg;
        }
        CustomButtonMenuDialog b = new CustomButtonMenuDialog.Builder(context).c(i).a(i2).cyw_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cpc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                coy.JV_(view);
            }
        }).cyw_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.health.device.ui.util.HagrideDeviceFragmentHelper$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                coy.JW_(context, view);
            }
        }).b();
        b.setCancelable(false);
        if (b.isShowing()) {
            return;
        }
        b.show();
    }

    static /* synthetic */ void JV_(View view) {
        LogUtil.a("HagrideDeviceFragmentHelper", "showWiFiEnableDialog() do nothing");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void JW_(Context context, View view) {
        b(context);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void d(final Context context) {
        cqh.c().e(context, new ScaleDialogCallback() { // from class: com.huawei.health.device.ui.util.HagrideDeviceFragmentHelper$$ExternalSyntheticLambda1
            @Override // com.huawei.health.device.callback.ScaleDialogCallback
            public final void operationResult(int i) {
                coy.e(context, i);
            }
        });
    }

    public static /* synthetic */ void e(Context context, int i) {
        if (i == 0) {
            b(context);
        } else {
            LogUtil.a("HagrideDeviceFragmentHelper", "showCustomOpenWifiTextAlertDialog() do nothing");
        }
    }

    private static void b(Context context) {
        if (context.getSystemService("wifi") instanceof WifiManager) {
            if (((WifiManager) context.getSystemService("wifi")).setWifiEnabled(true)) {
                LogUtil.a("HagrideDeviceFragmentHelper", "showWiFiEnableDialog() request share device");
            } else {
                cub.k(context);
            }
        }
    }
}
