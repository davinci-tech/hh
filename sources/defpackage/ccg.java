package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.MainActivity;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity;
import com.huawei.ui.device.activity.adddevice.PairingGuideActivity;
import com.huawei.ui.device.activity.notification.NotificationSettingActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ccg {
    private static DeviceSettingsInteractors c;
    private static Rect d;
    private static HashMap<String, String> b = new HashMap<>(16);

    /* renamed from: a, reason: collision with root package name */
    private static DeviceCapability f609a = null;

    public static void CW_(Uri uri, String str, Context context, Rect rect, boolean z) {
        LogUtil.a("wear_JumpUtil", "smartLifeJump()");
        if (str == null) {
            LogUtil.h("wear_JumpUtil", "smartLifeJump() hiLinkDeviceType is null");
            return;
        }
        if (uri == null) {
            LogUtil.h("wear_JumpUtil", "smartLifeJump() smartLifeInfo is null");
            return;
        }
        if (context == null) {
            LogUtil.h("wear_JumpUtil", "smartLifeJump() context is null");
            return;
        }
        if (rect != null) {
            d = rect;
        }
        try {
            String queryParameter = uri.getQueryParameter("SubMAC");
            String queryParameter2 = uri.getQueryParameter("ProductID");
            LogUtil.a("wear_JumpUtil", "smartLifeJump() smartLifeInfo result is ", queryParameter);
            DeviceInfo e = e(context, queryParameter, queryParameter2);
            String queryParameter3 = uri.getQueryParameter("Opcode");
            if (!TextUtils.isEmpty(queryParameter3) && queryParameter3.equals("Delete")) {
                LogUtil.a("wear_JumpUtil", "smartLifeJump() operate is jump remove device");
                d(context, e, z);
                return;
            }
            if (!TextUtils.isEmpty(queryParameter3) && queryParameter3.equals("Add")) {
                LogUtil.a("wear_JumpUtil", "smartLifeJump() operate is jump bind device");
                if (obb.a(context)) {
                    a(context, queryParameter2, str);
                    return;
                } else {
                    c(context, queryParameter2, str);
                    return;
                }
            }
            if (!TextUtils.isEmpty(queryParameter3) && queryParameter3.equals("MessageNotification")) {
                LogUtil.a("wear_JumpUtil", "smartLifeJump() operate is jump MessageNotification");
                e(context);
            } else {
                LogUtil.a("wear_JumpUtil", "smartLifeJump() operate is jump info");
                e(context, e, z);
            }
        } catch (UnsupportedOperationException unused) {
            LogUtil.b("wear_JumpUtil", "smartLifeJump error is UnsupportedOperationException");
        }
    }

    private static void a(Context context, String str, String str2) {
        if (bkz.e(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList())) {
            c(context);
            return;
        }
        LogUtil.a("wear_JumpUtil", "bindDevice smartProductId is ", str, " hiLinkDeviceType is ", str2);
        if (a(str) != null) {
            LogUtil.a("wear_JumpUtil", "bindDevice localDevice name is ", a(str).c());
            a(context, str);
            return;
        }
        cvc e = e(str);
        if (e == null) {
            LogUtil.a("wear_JumpUtil", "bindDevice ezPluginInfo is null");
            c(context);
        } else {
            c(context, new jnx(e.f().ae(), ntt.a(e)));
        }
    }

    private static void c(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.adddevice.AllDeviceListActivity");
            intent.setFlags(AppRouterExtras.COLDSTART);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private static void a(Context context, String str) {
        jnx a2 = a(str);
        if (a2 == null) {
            LogUtil.h("wear_JumpUtil", "intentPairGuideLocale deviceInfo is null");
            return;
        }
        if (a2.d() == 10) {
            String c2 = a2.c();
            ArrayList arrayList = new ArrayList(16);
            if (TextUtils.equals(c2, "PORSCHE DESIGN")) {
                arrayList.add("85acddd2-2a69-462b-9766-61602023199b");
                b.put("85acddd2-2a69-462b-9766-61602023199b", "3");
            } else {
                arrayList.add("e5ae9c59-0db8-4624-9862-09725412fff2");
                b.put("e5ae9c59-0db8-4624-9862-09725412fff2", "3");
            }
            try {
                Intent intent = new Intent(context, (Class<?>) DevicePairGuideSecondActivity.class);
                intent.putExtra("uuid_list", arrayList);
                intent.putExtra("kind_id", "wear_watch");
                intent.putExtra("pair_guide_array", b);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
                return;
            }
        }
        ArrayList<String> arrayList2 = new ArrayList<>(16);
        arrayList2.add(jfu.e(a2.d()));
        if (bkz.e(arrayList2)) {
            LogUtil.h("wear_JumpUtil", "uuidList is empty");
            return;
        }
        try {
            Intent intent2 = new Intent(context, (Class<?>) PairingGuideActivity.class);
            intent2.putStringArrayListExtra("uuid_list", arrayList2);
            intent2.putExtra("kind_id", b(str));
            intent2.putExtra("pair_guide", "2");
            context.startActivity(intent2);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e2.getMessage());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String b(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case 1477787:
                if (str.equals("0050")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1477788:
                if (str.equals("0051")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1477791:
                if (str.equals("0054")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1477793:
                if (str.equals("0056")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1477798:
                if (str.equals("004Z")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        return (c2 == 0 || c2 == 1 || c2 == 2 || c2 == 3 || c2 == 4) ? "wear_band" : "wear_watch";
    }

    private static void c(Context context, jnx jnxVar) {
        ArrayList<String> arrayList = new ArrayList<>(16);
        String d2 = jfu.d(jnxVar.d());
        arrayList.add(d2);
        if (bkz.e(arrayList)) {
            LogUtil.h("wear_JumpUtil", "uuidList is empty");
            c(context);
            return;
        }
        cuw e = jfu.e(d2);
        if (e == null) {
            LogUtil.h("wear_JumpUtil", "deviceInfoNew is null");
            c(context);
            return;
        }
        String str = e.i() == 1 ? "wear_band" : "wear_watch";
        try {
            Intent intent = new Intent(context, (Class<?>) PairingGuideActivity.class);
            intent.putStringArrayListExtra("uuid_list", arrayList);
            intent.putExtra("kind_id", str);
            intent.putExtra("pair_guide", "2");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e2.getMessage());
        }
    }

    private static DeviceInfo e(Context context, String str, String str2) {
        int i;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("wear_JumpUtil", "getDeviceInfoBySubMac() subMac or smartProductId is isEmpty");
            return null;
        }
        LogUtil.a("wear_JumpUtil", "getDeviceInfoBySubMac smartProductId is ", str2);
        List<DeviceInfo> c2 = DeviceSettingsInteractors.d(context).c(str);
        if (c2 == null || c2.isEmpty()) {
            LogUtil.h("wear_JumpUtil", "getDeviceInfoBySubMac() resultList is null or empty");
            return null;
        }
        if (c2.size() == 1) {
            LogUtil.a("wear_JumpUtil", "getDeviceInfoBySubMac Matching subMac device only one");
            return c2.get(0);
        }
        if (a(str2) != null) {
            LogUtil.a("wear_JumpUtil", "getDeviceInfoBySubMac localDevice name is ", a(str2).c());
            i = a(str2).d();
        } else {
            cvc e = e(str2);
            if (e != null) {
                LogUtil.a("wear_JumpUtil", "getDeviceInfoBySubMac() ezPluginInfo not null");
                i = new jnx(e.f().ae(), ntt.a(e)).d();
            } else {
                i = 0;
            }
        }
        LogUtil.a("wear_JumpUtil", "getDeviceInfoBySubMac() deviceType is ", Integer.valueOf(i));
        for (DeviceInfo deviceInfo : c2) {
            if (deviceInfo.getProductType() == i) {
                LogUtil.a("wear_JumpUtil", "getDeviceInfoBySubMac() deviceType Matching");
                return deviceInfo;
            }
        }
        return c2.get(0);
    }

    private static void e(Context context) {
        String str;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "wear_JumpUtil");
        List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "wear_JumpUtil");
        boolean z = false;
        if (bkz.e(deviceList)) {
            deviceList = deviceList2;
        } else if (!cvt.c(deviceList.get(0).getProductType()) && !bkz.e(deviceList2)) {
            deviceList.addAll(deviceList2);
        }
        if (bkz.e(deviceList)) {
            LogUtil.h("wear_JumpUtil", "getConnectDevice() no device connected");
            a(context);
            return;
        }
        c = DeviceSettingsInteractors.d(context);
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            DeviceInfo next = it.next();
            DeviceCapability e = c.e(next.getDeviceIdentify());
            f609a = e;
            if (e != null && e.isMessageAlert()) {
                str = next.getDeviceIdentify();
                if (next.getPowerSaveModel() == 1) {
                    z = true;
                }
            } else {
                LogUtil.h("wear_JumpUtil", "getConnectDevice() no DeviceCapability is null or isMessageAlert is false");
            }
        }
        if (TextUtils.isEmpty(str) || z) {
            LogUtil.a("wear_JumpUtil", "getConnectDevice() deviceMac is empty");
            a(context);
        } else {
            LogUtil.a("wear_JumpUtil", "getConnectDevice() deviceMac not empty");
            b(context, str);
        }
    }

    private static void d(Context context, DeviceInfo deviceInfo, boolean z) {
        LogUtil.a("wear_JumpUtil", "jumpDeleteWearDevice enter");
        if (deviceInfo == null) {
            LogUtil.h("wear_JumpUtil", "jumpDeleteWearDevic deviceInfo is null");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(context, WearHomeActivity.class);
            intent.setClassName(context, "com.huawei.ui.homewear21.home.WearHomeActivity");
            intent.putExtra("device_id", deviceInfo.getDeviceIdentify());
            intent.putExtra("FROM_SMART_LIFE", true);
            intent.putExtra("Delete", true);
            intent.setSourceBounds(d);
            intent.putExtra("isClick", z);
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private static void c(Context context, String str, String str2) {
        LogUtil.a("wear_JumpUtil", "bindWearDevice smartProductId is ", str, " hiLinkDeviceType is ", str2);
        if (a(str) != null) {
            LogUtil.a("wear_JumpUtil", "bindWearDevice localDevice name is ", a(str).c());
            e(context, a(str));
            return;
        }
        cvc e = e(str);
        if (e == null) {
            LogUtil.a("wear_JumpUtil", "bindWearDevice ezPluginInfo is null");
            d(context, str2);
        } else {
            e(context, new jnx(e.f().ae(), ntt.a(e)));
        }
    }

    public static void d(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity");
            if (str.equals("06E")) {
                intent.putExtra(TemplateStyleRecord.STYLE, 2);
            } else if (str.equals("06D")) {
                intent.putExtra(TemplateStyleRecord.STYLE, 1);
            } else {
                LogUtil.h("wear_JumpUtil", "startBindDeviceList other type");
            }
            intent.putExtra("isHeartRateDevice", 0);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private static void e(Context context, DeviceInfo deviceInfo, boolean z) {
        LogUtil.a("wear_JumpUtil", "smartLifeHomeJump enter");
        if (deviceInfo == null) {
            LogUtil.h("wear_JumpUtil", "smartLifeHomeJump deviceInfo is null");
            a(context);
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 2) {
            LogUtil.a("wear_JumpUtil", "smartLifeHomeJump() jump to deviceInfo");
            try {
                Intent intent = new Intent();
                intent.setClass(context, WearHomeActivity.class);
                intent.setClassName(context, "com.huawei.ui.homewear21.home.WearHomeActivity");
                intent.putExtra("device_id", deviceInfo.getDeviceIdentify());
                intent.putExtra("FROM_SMART_LIFE", true);
                intent.setSourceBounds(d);
                intent.putExtra("isClick", z);
                intent.setFlags(32768);
                context.startActivity(intent);
                if (context instanceof Activity) {
                    ((Activity) context).overridePendingTransition(0, 0);
                    return;
                }
                return;
            } catch (ActivityNotFoundException e) {
                LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
                return;
            }
        }
        a(context, deviceInfo, z);
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), MainActivity.class.getName());
            intent.setFlags(AppRouterExtras.COLDSTART);
            intent.putExtra(Constants.HOME_TAB_NAME, "DEVICE");
            intent.putExtra("SHORTCUT", "SC_DEVICE");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e：", e.getMessage());
        }
    }

    private static void a(Context context, DeviceInfo deviceInfo, boolean z) {
        LogUtil.a("wear_JumpUtil", "smartLifeJump() jumpToDeviceConnect");
        try {
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
            intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, deviceInfo.getDeviceName());
            intent.putExtra("device_identify", deviceInfo.getDeviceIdentify());
            intent.putExtra("device_picID", jfu.c(deviceInfo.getProductType()).v());
            intent.putExtra("FROM_SMART_LIFE", true);
            intent.setSourceBounds(d);
            intent.putExtra("isClick", z);
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, deviceInfo.getProductType());
            intent.setFlags(32768);
            context.startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(0, 0);
            }
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private static void e(Context context, jnx jnxVar) {
        String b2;
        if (jnxVar == null) {
            LogUtil.h("wear_JumpUtil", "device productName is null");
            return;
        }
        if (jnxVar.d() == 10) {
            b2 = jnxVar.c();
        } else {
            b2 = oae.c(BaseApplication.getContext()).b(jnxVar.d());
        }
        LogUtil.a("wear_JumpUtil", "device productName is ", b2);
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setClassName(context, "com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity");
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, jnxVar.d());
            intent.putExtra("isFromWear", true);
            LogUtil.a("wear_JumpUtil", HwPayConstant.KEY_PRODUCTNAME, b2);
            if (b2 != null && b2.equals("PORSCHE DESIGN")) {
                intent.putExtra("isPorc", true);
                intent.putExtra("IS_PROC", true);
            }
            intent.putExtra("dname", b2);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private static jnx a(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("004Q", new jnx("HUAWEI WATCH 2", 10));
        hashMap.put("004U", new jnx("PORSCHE DESIGN", 10));
        hashMap.put("004W", new jnx("荣耀手表S1", 8));
        hashMap.put("0050", new jnx("华为手环 B3青春版", 14));
        hashMap.put("0051", new jnx("华为运动手环", 15));
        hashMap.put("004Z", new jnx("华为手环", 7));
        hashMap.put("0054", new jnx("荣耀手环 3", 13));
        hashMap.put("0056", new jnx("荣耀畅玩手环 A2", 12));
        if (hashMap.containsKey(str)) {
            return (jnx) hashMap.get(str);
        }
        return null;
    }

    public static cvc e(String str) {
        List<msa> d2 = EzPluginManager.a().d();
        if (d2 == null || d2.isEmpty()) {
            LogUtil.a("wear_JumpUtil", "bindWearDevice hiLinkDeviceList is null or hiLinkDeviceList is empty");
            return c(str);
        }
        Iterator<msa> it = d2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String b2 = it.next().b();
            if (!((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(b2)) {
                LogUtil.h("wear_JumpUtil", "bindWearDevice ezPluginInfo isPluginAvaiable false");
            } else if (c(b2, str) != null) {
                LogUtil.a("wear_JumpUtil", "bindWearDevice ezPluginInfo not null");
                break;
            }
        }
        return c(str);
    }

    private static cvc c(String str) {
        List<cvk> indexList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getIndexList();
        cvc cvcVar = null;
        if (indexList == null || indexList.isEmpty()) {
            LogUtil.a("wear_JumpUtil", "descriptionInfo hiLinkDeviceList is null or hiLinkDeviceList is empty");
            return null;
        }
        Iterator<cvk> it = indexList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String e = it.next().e();
            if (!((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(e)) {
                LogUtil.h("wear_JumpUtil", "bindWearDevice descriptionInfo isPluginAvaiable false");
            } else {
                cvcVar = c(e, str);
                if (cvcVar != null) {
                    LogUtil.a("wear_JumpUtil", "bindWearDevice descriptionInfo not null");
                    break;
                }
            }
        }
        return cvcVar;
    }

    private static cvc c(String str, String str2) {
        LogUtil.a("wear_JumpUtil", "gePluginInfo product is ", str, " productId is ", str2);
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid == null) {
            LogUtil.h("wear_JumpUtil", "gePluginInfo pluginInfo is null");
            return null;
        }
        cvj f = pluginInfoByUuid.f();
        if (f == null) {
            LogUtil.h("wear_JumpUtil", "gePluginInfo pluginInfoForWear is empty");
            return null;
        }
        if (!TextUtils.isEmpty(f.b())) {
            LogUtil.a("wear_JumpUtil", "gePluginInfo AiTipsProduct is", f.b());
            try {
            } catch (JSONException unused) {
                LogUtil.b("wear_JumpUtil", "getPluginInfo JSONException");
            }
            if (new JSONObject(f.b()).has("ai_tips_product_" + str2)) {
                return pluginInfoByUuid;
            }
            return null;
        }
        LogUtil.h("wear_JumpUtil", "gePluginInfo AiTipsProduct is empty");
        return null;
    }

    private static void b(Context context, String str) {
        try {
            Intent intent = new Intent(context, (Class<?>) NotificationSettingActivity.class);
            intent.putExtra("device_id", str);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("wear_JumpUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }
}
