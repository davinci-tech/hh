package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class oau {
    public static void b(boolean z) {
        HashMap hashMap = new HashMap(16);
        if (z) {
            jqi.a().setSwitchSetting("wlan_auto_update", "1", null);
            hashMap.put("type", "1");
        } else {
            jqi.a().setSwitchSetting("wlan_auto_update", "2", null);
            hashMap.put("type", "0");
        }
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090006.value(), hashMap, 0);
    }

    public static void a(boolean z, String str) {
        HashMap hashMap = new HashMap(16);
        if (z) {
            jqi.a().setSwitchSetting("auto_open_wlan_status", "1", str, null);
            hashMap.put("type", "1");
        } else {
            jqi.a().setSwitchSetting("auto_open_wlan_status", "2", str, null);
            hashMap.put("type", "0");
        }
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_WLAN_TRANSMIT_1090096.value(), hashMap, 0);
        LogUtil.a("DeviceBiAnalyticsUtil", "autoOpenWlanTransmitBiEvent end");
    }

    public static void c(String str, int i) {
        if (i != 0) {
            HashMap hashMap = new HashMap(16);
            hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
            hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, "HDK_WEAR");
            hashMap.put("error_type", String.valueOf(i));
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090010.value(), hashMap, 0);
            return;
        }
        LogUtil.h("DeviceBiAnalyticsUtil", "errorCode is 0");
    }

    public static void d(boolean z) {
        HashMap hashMap = new HashMap(16);
        if (z) {
            hashMap.put("type", 1);
        } else {
            hashMap.put("type", 2);
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PAIRING_MODE_1090035.value(), hashMap, 0);
    }

    public static void b() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("type", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.KID_ACCOUNT_PAIR_STUDENT_BAND.value(), hashMap, 0);
    }

    public static void e(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("type", String.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.USER_SELECT_PAIR_MODE.value(), hashMap, 0);
    }

    public static void b(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    public static void d(String str, String str2, boolean z) {
        LogUtil.a("DeviceBiAnalyticsUtil", "saveAutoInstallCheck save AutoOnCheckedListener");
        HashMap hashMap = new HashMap(16);
        if (z) {
            oaf.b(BaseApplication.getContext()).c(str, true);
            jqi.a().setSwitchSetting("auto_update_status", "true", str2, null);
            hashMap.put("type", "1");
        } else {
            oaf.b(BaseApplication.getContext()).c(str, false);
            jqi.a().setSwitchSetting("auto_update_status", "false", str2, null);
            hashMap.put("type", "0");
        }
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090037.value(), hashMap, 0);
    }

    public static void b(Map<String, Object> map, int i) {
        if (map == null) {
            return;
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090001.value(), map, 0);
        String str = map.get("devicename") instanceof String ? (String) map.get("devicename") : "";
        map.clear();
        map.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
        map.put(DeviceCategoryFragment.DEVICE_TYPE, "HDK_WEAR");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_2060005.value(), map, 0);
        map.put("from", Integer.valueOf(a(i)));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_SUCCEED_WHITE_2060087.value(), map, 0);
    }

    private static int a(int i) {
        Integer num = new HashMap<Integer, Integer>() { // from class: oau.1
            {
                put(100003, 1);
                put(100008, 2);
                put(100009, 2);
                put(100001, 3);
                put(100007, 4);
                put(100010, 5);
            }
        }.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static void b(int i) {
        LogUtil.a("DeviceBiAnalyticsUtil", "addClickDotting is click :", Integer.valueOf(i));
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_2060105.value(), hashMap, 0);
    }

    public static void e(String str, String str2, String str3) {
        HashMap hashMap = new HashMap(16);
        if (TextUtils.isEmpty(str2)) {
            str2 = Constants.LINK;
        }
        hashMap.put("kind_name", str2);
        hashMap.put("product_id", str);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.a(str));
        hashMap.put("source", str3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PAIR_GUIDE_2170021.value(), hashMap, 0);
    }

    public static void a(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("device_name_key", str);
        hashMap.put("device_emui_version_key", str2);
        if (z) {
            hashMap.put("is_has_resources_key", "1");
        } else {
            hashMap.put("is_has_resources_key", "0");
        }
        String value = AnalyticsValue.OOBE_IS_NORMAL_DISPLAY1090055.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        for (Map.Entry entry : hashMap.entrySet()) {
            String str3 = (String) entry.getKey();
            Object value2 = entry.getValue();
            if (value2 != null) {
                linkedHashMap.put(str3, value2.toString());
            } else {
                LogUtil.h("DeviceBiAnalyticsUtil", "the mapValue is null when mapKey is:", str3);
            }
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(value, linkedHashMap);
    }

    public static void d(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HOME_UPDATE_OPEN_2010089.value(), hashMap, 0);
    }

    public static void a() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.APP_MARKET_ENTRANCE_KEY.value(), hashMap, 0);
    }

    public static void d() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_TAB_MUSIC_2010103.value(), hashMap, 0);
    }

    public static void g() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_TAB_WALLET_2010104.value(), hashMap, 0);
    }

    public static void e(String str) {
        jqi.a().setSwitchSetting("wlan_auto_update", "1", null);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090006.value(), hashMap, 0);
    }

    public static void c(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap, 0);
    }

    public static void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("id", str);
        hashMap.put("name", str2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_R1_PRO_HEADSET_OPEN_HEART_INDEX_2060042.value(), hashMap, 0);
    }

    public static void a(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(16);
        if (deviceInfo != null) {
            hashMap.put("BT", jqh.d(deviceInfo).d());
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090004.value(), hashMap, 0);
    }

    public static void d(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(16);
        if (deviceInfo != null) {
            hashMap.put("BT", jqh.d(deviceInfo).d());
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010034.value(), hashMap, 0);
    }

    public static void c() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.CLICK_STUDENT_INFO_CARD.value(), hashMap, 0);
    }

    public static void b(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("BT", jqh.d(deviceInfo).d());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010033.value(), hashMap, 0);
    }

    public static void e() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_AW70_MODE_SELECTION_2060027.value(), hashMap, 0);
    }

    public static void c(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(16);
        if (deviceInfo != null) {
            hashMap.put("BT", jqh.d(deviceInfo).d());
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010047.value(), hashMap, 0);
        HashMap hashMap2 = new HashMap(16);
        hashMap2.put("click", "1");
        if (deviceInfo != null) {
            hashMap2.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, deviceInfo.getDeviceName());
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap2, 0);
        ixx.d().a(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
    }

    public static void e(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap(16);
        if (deviceInfo != null) {
            hashMap.put("BT", jqh.d(deviceInfo).d());
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010001.value(), hashMap, 0);
    }

    public static void c(int i, String str) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String c = bkx.c();
        String str2 = Build.MODEL;
        if (!TextUtils.isEmpty(str)) {
            linkedHashMap.put("deviceName", str);
        }
        linkedHashMap.put("monitorType", String.valueOf(i));
        linkedHashMap.put("timestamp", valueOf);
        linkedHashMap.put("serialNumber", c);
        linkedHashMap.put("phoneMode", str2);
        linkedHashMap.put("androidOS", String.valueOf(Build.VERSION.SDK_INT));
        linkedHashMap.put("app_version", String.valueOf(CommonUtil.d(BaseApplication.getContext())));
        LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>();
        linkedHashMap2.put(valueOf, linkedHashMap.toString());
        OpAnalyticsUtil.getInstance().setEvent2nd("80020005", linkedHashMap2);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(valueOf, linkedHashMap.toString());
        bls.a(linkedHashMap3.toString(), str, 0, null);
    }

    public static void a(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("type", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DEVICE_HOME_ITEM_CLICK_EVENT_VALUE.value(), hashMap, 0);
    }

    public static void d(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DELETE_DEVICE_EVENT_VALUE.value(), hashMap, 0);
    }

    public static void c(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.DEVICE_SYSTEM_AND_UPDATE_EVENT_VALUE.value(), hashMap, 0);
    }

    public static void e(Map<String, Object> map) {
        if (map == null) {
            LogUtil.a("DeviceBiAnalyticsUtil", "setStressPageEvent content is null");
            return;
        }
        iyb iybVar = new iyb();
        HashMap hashMap = new HashMap();
        hashMap.put("pageId", "Stress_0005");
        iybVar.e(hashMap);
        iybVar.d(map);
        ixx.d().a(BaseApplication.getContext(), AnalyticsValue.ENTRY_MY_WATCH_FACE_H2160001005.value(), iybVar, 0);
        LogUtil.a("DeviceBiAnalyticsUtil", "setStressPageEvent content：", map);
    }
}
