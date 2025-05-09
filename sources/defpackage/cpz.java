package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.dcz;
import health.compact.a.CommonUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cpz {
    public static void c(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", String.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_BIND_TYPE_2060026.value(), hashMap, 0);
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiWifiDeviceBindType bindType: ", Integer.valueOf(i), " map: ", hashMap.toString());
    }

    public static void f(String str) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiWifiConfigNetworkAddress deviceAddress: ", CommonUtil.l(str));
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("macAddress", str);
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_DUAL_MODE_ADD_SUCCESS_2060039.value(), hashMap, 0);
    }

    public static void d(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", String.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_BIND_TYPE_2060026.value(), hashMap, 0);
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiSingleWifiDeviceBindType bindType: ", Integer.valueOf(i), " map: ", hashMap.toString());
    }

    public static void e(String str, dcz dczVar) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiSingleWifiDeviceBindSuccessCount uniqueId: ", CommonUtil.l(str));
        MeasurableDevice d = ceo.d().d(str, false);
        if (d instanceof ctk) {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            b(dczVar, (ctk) d, hashMap);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_DEVICE_BIND_2060024.value(), hashMap, 0);
        }
    }

    public static void b(String str) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiBluetoothPairSuccess productId: ", CommonUtil.l(str));
        boolean ah = cpa.ah(str);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(!ah ? 1 : 0));
        hashMap.put("deviceId", Integer.valueOf(cpa.j(str)));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_DUAL_MODE_ADD_SUCCESS_2060038.value(), hashMap, 0);
    }

    private static void b(dcz dczVar, ctk ctkVar, Map<String, Object> map) {
        String b;
        if (TextUtils.isEmpty(ctkVar.getAddress()) || (b = dis.b(ctkVar.getAddress())) == null || TextUtils.isEmpty(b) || b.length() <= 24) {
            return;
        }
        map.put("macAddress", b.substring(0, 24));
        map.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dczVar.n().b());
        map.put(DeviceCategoryFragment.DEVICE_TYPE, dczVar.l().name());
    }

    public static void h() {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiWifiDeviceOtaUpgrade enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_OTA_UPGRADE_2060023.value(), hashMap, 0);
    }

    public static void a(String str) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiAutoMeasureWeight productId: ", CommonUtil.l(str));
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            dcz.b n = d.n();
            if (n != null) {
                hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, n.b());
            }
            HealthDevice.HealthDeviceKind l = d.l();
            if (l != null) {
                hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, l.name());
            }
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HOME_MEASURE_SUCCESS_2060022.value(), hashMap, 0);
    }

    public static void d(String str) {
        String str2;
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiWeightMeasureResult productId: ", CommonUtil.l(str));
        dcz d = ResourceManager.e().d(str);
        if (d != null) {
            String e = dks.e(str, d.n().b());
            str2 = d.l() != null ? d.l().name() : null;
            r2 = e;
        } else {
            LogUtil.h("PluginDevice_ScaleBiAnalyticsHelper", "onCreateView info is null");
            str2 = null;
        }
        LogUtil.c("PluginDevice_ScaleBiAnalyticsHelper", "WeightResultFragment deviceName is " + r2);
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, r2);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, str2);
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        hashMap.put("prodId", dij.e(str));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_MEASURE_SUCCEED_2060011.value(), hashMap, 0);
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_MEASURE_TODAY_WEIGHT), hashMap);
    }

    public static void b() {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiAbnormalDataConfirmBiEvent enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HOME_WEIGHT_ABNORMAL_DATA_CONFIRM_2030068.value(), hashMap, 0);
    }

    public static void d() {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiMeasureForPlan enter");
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120034.value(), hashMap, 0);
    }

    public static void Kr_(String str, String str2, String str3, ContentValues contentValues) {
        String str4;
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiHagridDeviceOtaUpgrade enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", cpa.c.get(str));
        hashMap.put("oldVersion", str2);
        hashMap.put("newVersion", str3);
        if (contentValues != null && !TextUtils.isEmpty(contentValues.getAsString("uniqueId"))) {
            MeasurableDevice d = ceo.d().d(contentValues.getAsString("uniqueId"), false);
            ctk ctkVar = d instanceof ctk ? (ctk) d : null;
            if (ctkVar != null) {
                str4 = ctkVar.d();
                hashMap.put("deviceId", str4);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_UP_OTA_SUCCESS_2060041.value(), hashMap, 0);
            }
            LogUtil.h("PluginDevice_ScaleBiAnalyticsHelper", "setBiHagridDeviceOtaUpgrade device is null");
        }
        str4 = "";
        hashMap.put("deviceId", str4);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_UP_OTA_SUCCESS_2060041.value(), hashMap, 0);
    }

    public static void Ks_(String str, String str2, ContentValues contentValues) {
        String str3;
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiHagridOtaCheckUpgrade enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", cpa.c.get(str));
        hashMap.put("deviceVersion", str2);
        if (contentValues != null && !TextUtils.isEmpty(contentValues.getAsString("uniqueId"))) {
            MeasurableDevice d = ceo.d().d(contentValues.getAsString("uniqueId"), false);
            if (d instanceof ctk) {
                str3 = ((ctk) d).d();
                hashMap.put("deviceId", str3);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_CHECK_UP_OTA_SUCCESS_2060040.value(), hashMap, 0);
            }
        }
        str3 = "";
        hashMap.put("deviceId", str3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_CHECK_UP_OTA_SUCCESS_2060040.value(), hashMap, 0);
    }

    public static void d(com.huawei.hihealth.device.open.HealthDevice healthDevice, String str, dcz dczVar) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiCommonBindSuccessAnalytics enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("macAddress", dis.b(healthDevice.getAddress()));
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.e(str, dczVar.n().b()));
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, dczVar.l().name());
        hashMap.put("prodId", dij.e(str));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_2060005.value(), hashMap, 0);
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "macAddress fuzzyData: ", CommonUtil.l(dis.b(healthDevice.getAddress())));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_WHITE_2060087.value(), hashMap, 0);
    }

    public static void d(String str, String str2) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiStartOpenService enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("id", str);
        hashMap.put("name", str2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_OPEN_SERVICE_2060021.value(), hashMap, 0);
    }

    public static void a() {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiOpenAppHelpActivity enter");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010034.value(), new HashMap(16), 0);
    }

    public static void e() {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiCallHotLine enter");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010046.value(), new HashMap(16), 0);
    }

    public static void c() {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiEnterPollenClub enter");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010042.value(), new HashMap(16), 0);
    }

    public static void c(Map<String, Object> map) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiResetBandUpdate enter");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), map, 0);
    }

    public static void a(String str, String str2) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiRequestDeviceShareClickEvent enter");
        HashMap hashMap = new HashMap(16);
        MeasurableDevice d = !TextUtils.isEmpty(str) ? ceo.d().d(str, false) : null;
        String deviceName = d != null ? d.getDeviceName() : "";
        hashMap.put("click", "1");
        String k = cpa.k(str);
        if (TextUtils.isEmpty(k)) {
            k = cpa.k(str2);
        }
        hashMap.put("device_version", k);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, deviceName);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_REQUEST_DEVICE_SHARE_2060053.value(), hashMap, 0);
    }

    public static void a(Map<String, Object> map) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiDeviceBindGuideSuccessPair enter");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_2060005.value(), map, 0);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_WHITE_2060087.value(), map, 0);
    }

    public static void e(String str) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiDualModelBluetoothPairSuccess enter");
        boolean ah = cpa.ah(str);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(!ah ? 1 : 0));
        hashMap.put("deviceId", Integer.valueOf(cpa.j(str)));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_HAGRID_DEVICE_DUAL_MODE_ADD_SUCCESS_2060038.value(), hashMap, 0);
    }

    public static void c(String str) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiWeightMeasureStart enter");
        HashMap hashMap = new HashMap(16);
        dcz d = ResourceManager.e().d(str);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, (d == null || d.n() == null) ? null : dks.e(str, d.n().b()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BEGIN_MEASURE_2060006.value(), hashMap, 0);
    }

    public static void e(dcz dczVar, String str) {
        LogUtil.a("PluginDevice_ScaleBiAnalyticsHelper", "setBiBindUiClickEvent enter");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (dczVar != null && dczVar.n() != null) {
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dczVar.n().b());
        } else {
            LogUtil.h("PluginDevice_ScaleBiAnalyticsHelper", "setBiBindUiClickEvent mProductInfo is null");
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    public static void e(Context context, HealthDevice healthDevice, String str, String str2) {
        if (healthDevice == null) {
            LogUtil.h("PluginDevice_ScaleBiAnalyticsHelper", "setDeviceVersionBi device is null");
            return;
        }
        ixz ixzVar = new ixz();
        ixzVar.a(cpa.g.get(str));
        ixzVar.b(cpa.d(healthDevice.getAddress()));
        String k = cpa.k(str2);
        coz.d("PluginDevice_ScaleBiAnalyticsHelper", k, context, str2);
        if (TextUtils.isEmpty(k)) {
            k = cpa.k(str);
        }
        ixzVar.c(k);
        ixx.d().b(ixzVar);
    }
}
