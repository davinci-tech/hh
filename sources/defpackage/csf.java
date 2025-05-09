package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.AuthorizeSubUserInfo;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceExitAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUnbindReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.csa;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class csf {
    private static final String[] c = {"e4b0b1d5-2003-4d88-8b5f-c4f64542040b", "a8ba095d-4123-43c4-a30a-0240011c58de"};

    public static void d(Context context) {
        ArrayList<ctk> e = ctq.e();
        if (koq.b(e)) {
            LogUtil.b("WifiDeviceControl", "setMetricOrImperialUnit deviceIdList is null");
            return;
        }
        Iterator<ctk> it = e.iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            if (f(next.getProductId())) {
                c(context, next.d());
            }
        }
    }

    private static void c(Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "WifiDeviceControl", "setMetricOrImperialUnit deviceId is null");
            return;
        }
        boolean h = UnitUtil.h();
        cpw.a(true, "WifiDeviceControl", "current setting unit is ", Boolean.valueOf(h));
        ArrayList arrayList = new ArrayList(16);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        if (h) {
            hashMap.put("weightUnit", "2");
        } else {
            hashMap.put("weightUnit", "1");
        }
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(str);
        jbs.a(context).d(wifiDeviceControlDataModelReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csf.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
                if (z) {
                    cpw.a(false, "WifiDeviceControl", "set unit success ");
                    return;
                }
                if (cloudCommonReponse != null) {
                    int intValue = cloudCommonReponse.getResultCode().intValue();
                    cpw.a(false, "WifiDeviceControl", "errorCode = ", Integer.valueOf(intValue), " | errorDes = ", cloudCommonReponse.getResultDesc());
                    if (intValue == 112000000) {
                        csf.m(str);
                    }
                }
            }
        });
    }

    public static boolean d() {
        ArrayList<String> c2;
        return CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false) && (c2 = cjx.e().c()) != null && c2.size() > 0;
    }

    public static ArrayList<dcz> c() {
        ArrayList<String> a2;
        ArrayList<dcz> arrayList = new ArrayList<>(16);
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false) && (a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_WEIGHT)) != null && a2.size() > 0) {
            Iterator<String> it = a2.iterator();
            while (it.hasNext()) {
                dcz d = ResourceManager.e().d(it.next());
                if (d != null && n(d.t())) {
                    arrayList.add(d);
                }
            }
        }
        return arrayList;
    }

    private static boolean n(String str) {
        cpw.c(false, "WifiDeviceControl", "isSupportPressureWifiDevice productId : ", str);
        for (String str2 : c) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static void a(Context context) {
        ArrayList<ctk> a2;
        if (context == null) {
            cpw.a(false, "WifiDeviceControl", "goToUnBindWiFiDevice context is null");
            return;
        }
        if (!CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false) || (a2 = cjx.e().a()) == null || a2.size() == 0) {
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
        if (a2.size() > 0) {
            MeasurableDevice c2 = ceo.d().c(a2.get(0).getSerialNumber(), false);
            if (c2 instanceof ctk) {
                Bundle bundle = new Bundle();
                intent.setAction("SWITCH_PLUGINDEVICE");
                bundle.putString("arg1", "DeviceInfoList");
                ctk ctkVar = (ctk) c2;
                bundle.putString("productId", ctkVar.getProductId());
                ContentValues contentValues = new ContentValues();
                contentValues.put("productId", ctkVar.getProductId());
                contentValues.put("uniqueId", ctkVar.getUniqueId());
                bundle.putParcelable("commonDeviceInfo", contentValues);
                intent.putExtras(bundle);
            } else {
                cpw.a(false, "WifiDeviceControl", "goToUnBindWiFiDevice device is null");
            }
        } else {
            intent.putExtra("kind", HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
            intent.putExtra("view", "WiFiBindDevice");
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WifiDeviceControl", "goToUnBindWiFiDevice exception");
        }
    }

    public static void c(Map<String, String> map, String str, Context context) {
        if (map == null || context == null) {
            LogUtil.h("WifiDeviceControl", "setUserInfo userInfo or context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "setUserInfo sid is null");
            return;
        }
        String str2 = map.get("uid");
        if (TextUtils.isEmpty(str2) || str2.equals(MultiUsersManager.INSTANCE.getMainUser().i())) {
            map.put("uid", "0");
        }
        ArrayList<ctk> a2 = cjx.e().a();
        if (koq.c(a2)) {
            Iterator<ctk> it = a2.iterator();
            while (it.hasNext()) {
                ctk next = it.next();
                if (!cgs.d(cpa.h(next.getSerialNumber()))) {
                    map.remove("month");
                }
                a(map, next.d(), str, context);
            }
            return;
        }
        LogUtil.h("WifiDeviceControl", "setUserInfo not bind wifi device");
    }

    public static void a(Map<String, String> map, final String str, String str2, Context context) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("WifiDeviceControl", "setUserInfo userInfoMap is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "setUserInfo deviceId is null");
            return;
        }
        String c2 = c(str2, str);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("WifiDeviceControl", "setUserInfo newSid is null");
            return;
        }
        LogUtil.a("WifiDeviceControl", "setUserInfo newSid:", c2, " deviceId: ", CommonUtil.l(str), " uid: ", Boolean.valueOf("0".equals(map.get("uid"))));
        Map<String, String> a2 = a(map, str);
        ArrayList arrayList = new ArrayList(16);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setData(a2);
        deviceServiceInfo.setSid(c2);
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDevId(str);
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        jbs.a(context).d(wifiDeviceControlDataModelReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csf.2
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str3, boolean z) {
                if (z) {
                    LogUtil.a("WifiDeviceControl", "setUserInfo success");
                    return;
                }
                if (cloudCommonReponse != null) {
                    int intValue = cloudCommonReponse.getResultCode().intValue();
                    LogUtil.h("WifiDeviceControl", "setUserInfo errorCode:", Integer.valueOf(intValue), "|errorDesc:", cloudCommonReponse.getResultDesc());
                    if (intValue == 112000000) {
                        csf.m(str);
                        return;
                    }
                    return;
                }
                LogUtil.h("WifiDeviceControl", "setUserInfo response is null");
            }
        });
    }

    private static String c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            cpw.d(false, "WifiDeviceControl", "getSid sid is ", Boolean.valueOf(TextUtils.isEmpty(str)), " or deviceID is ", Boolean.valueOf(TextUtils.isEmpty(cpw.d(str2))));
            return null;
        }
        HealthDevice e = cjx.e().e(str2);
        if (!(e instanceof ctk)) {
            LogUtil.h("WifiDeviceControl", "healthDevice is not instanceof WiFiDevice");
            return null;
        }
        if (((ctk) e).b().k() != 2) {
            return str.indexOf("_") >= 0 ? str.substring(0, str.indexOf("_")) : str;
        }
        if (str.indexOf("_") != -1) {
            return str;
        }
        return str + "_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
    }

    public static void c(String str, boolean z) {
        Iterator<cfi> it = MultiUsersManager.INSTANCE.getAllUser().iterator();
        while (it.hasNext()) {
            c(str, it.next(), z);
        }
    }

    private static void c(String str, cfi cfiVar, boolean z) {
        String str2;
        Map<String, String> e = e(str, cfiVar, z);
        if (a(cfiVar)) {
            cfiVar.a(cts.b);
        }
        Iterator<ctk> it = cjx.e().a().iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = "";
                break;
            }
            ctk next = it.next();
            if (TextUtils.equals(next.d(), str)) {
                str2 = next.getSerialNumber();
                break;
            }
        }
        if (cgs.d(cpa.h(str2))) {
            e.put("month", String.valueOf(cgs.a(cfiVar.g())));
        }
        a(e, str, cfiVar.j(), BaseApplication.getContext());
    }

    private static Map<String, String> e(String str, cfi cfiVar, boolean z) {
        String i;
        LogUtil.a("WifiDeviceControl", "getUserParam isHagridDevice:", Boolean.valueOf(z));
        HashMap hashMap = new HashMap(16);
        hashMap.put("id", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        hashMap.put("uid", a(cfiVar) ? String.valueOf(0) : cfiVar.i());
        hashMap.put(CommonConstant.KEY_GENDER, String.valueOf((int) cff.c(cfiVar.c())));
        hashMap.put("age", String.valueOf(cgs.e(cfiVar.g(), cfiVar.a())));
        hashMap.put("height", String.valueOf(cfiVar.d()));
        hashMap.put("isDelete", String.valueOf(0));
        if (a(cfiVar)) {
            i = String.valueOf(0);
        } else {
            i = cfiVar.i();
        }
        if (z) {
            hashMap.put("currentWeight", String.valueOf(cfiVar.m()));
        } else {
            String e = e(str, i);
            if (e != null && !TextUtils.isEmpty(e)) {
                LogUtil.a("WifiDeviceControl", "getUserParam deviceUser is not null");
                String j = j(e);
                String d = d(e);
                String a2 = a(j, z);
                if (!TextUtils.isEmpty(a2) && !"0".equals(a2)) {
                    hashMap.put("currentWeight", a2);
                } else {
                    LogUtil.a("WifiDeviceControl", "getUserParam currentWeight is null");
                    hashMap.put("currentWeight", String.valueOf(cfiVar.m()));
                }
                if (!TextUtils.isEmpty(d) && !"0".equals(d)) {
                    hashMap.put("bodyRes", d);
                }
            } else {
                LogUtil.h("WifiDeviceControl", "getUserParam deviceUser is null");
                hashMap.put("currentWeight", String.valueOf(cfiVar.m()));
            }
        }
        return hashMap;
    }

    private static String a(String str, boolean z) {
        float parseFloat;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "getUserWeightData weight is null");
            return null;
        }
        try {
            parseFloat = Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("WifiDeviceControl", "getUserWeightData NumberFormatException");
        }
        if (parseFloat != 0.0f && !Float.isNaN(parseFloat)) {
            int i = z ? 100 : 10;
            LogUtil.a("WifiDeviceControl", "getUserWeightData weightDivision:", Integer.valueOf(i));
            return new BigDecimal(str).divide(new BigDecimal(String.valueOf(i))).toPlainString();
        }
        LogUtil.h("WifiDeviceControl", "getUserWeightData weight is error");
        return null;
    }

    private static boolean a(cfi cfiVar) {
        String i = cfiVar.i();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (i == null) {
            LogUtil.h("WifiDeviceControl", "isMainUser uuid is null");
            return true;
        }
        if ("0".equals(i) || "".equals(i)) {
            LogUtil.h("WifiDeviceControl", "isMainUser:", i);
            return true;
        }
        if (!Constants.NULL.equalsIgnoreCase(i) && !i.equals(accountInfo)) {
            return false;
        }
        LogUtil.h("WifiDeviceControl", "isMainUser:", i);
        return true;
    }

    private static Map<String, String> a(Map<String, String> map, String str) {
        String str2 = map.get("currentWeight");
        HashMap hashMap = new HashMap(16);
        hashMap.putAll(map);
        float f = nsn.f(str2);
        if (f != 0.0f && !Float.isNaN(f)) {
            int i = g(str) ? 100 : 10;
            LogUtil.a("WifiDeviceControl", "setUserInfoWeightData weightDivision:", Integer.valueOf(i), "|deviceId:", cpw.d(str));
            LogUtil.c("WifiDeviceControl", "setUserInfoWeightData weight = ", Float.valueOf(f), ",devId = ", str);
            hashMap.put("currentWeight", new BigDecimal(str2).multiply(new BigDecimal(String.valueOf(i))).toPlainString());
        } else {
            hashMap.remove("currentWeight");
            LogUtil.h("WifiDeviceControl", "setUserInfoWeightData weight is error");
        }
        return hashMap;
    }

    public static void e() {
        ArrayList<String> c2 = cjx.e().c();
        if (c2 == null || c2.size() <= 0) {
            cpw.e(false, "WifiDeviceControl", "getPressureCalibrateList deviceIdList is null");
            return;
        }
        Iterator<String> it = c2.iterator();
        while (it.hasNext()) {
            String next = it.next();
            d(next, ctq.i(next));
        }
    }

    public static void d(String str, String str2) {
        cpw.a(false, "WifiDeviceControl", "getPressureCalibrateList");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            cpw.a(false, "WifiDeviceControl", "getPressureCalibrateList deviceId or productId is empty");
            return;
        }
        final String str3 = "pressure_calibrate_hrv_userinfo_" + str2;
        if (!TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), str3))) {
            cpw.a(false, "WifiDeviceControl", "getPressureCalibrateList hrvUserInfo is not empty");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(str);
        wifiDeviceServiceInfoReq.setSid("devResult");
        jbs.a(cpp.a()).b(wifiDeviceServiceInfoReq, new ICloudOperationResult<WifiDeviceServiceInfoRsp>() { // from class: csf.5
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str4, boolean z) {
                if (!z || wifiDeviceServiceInfoRsp == null || wifiDeviceServiceInfoRsp.getDeviceServiceInfo() == null || wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
                    return;
                }
                Iterator<DeviceServiceInfo> it = wifiDeviceServiceInfoRsp.getDeviceServiceInfo().iterator();
                while (it.hasNext()) {
                    Map<String, String> data = it.next().getData();
                    if (!data.isEmpty()) {
                        for (Map.Entry<String, String> entry : data.entrySet()) {
                            csf.d(str3, entry.getKey(), entry);
                        }
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, String str2, Map.Entry<String, String> entry) {
        if (TextUtils.equals(str2, "hrvUserInfo")) {
            j(str, entry.getValue());
        }
        if (TextUtils.equals(str2, "hrvUserInfo1")) {
            j(str, entry.getValue());
        }
        if (TextUtils.equals(str2, "hrvUserInfo2")) {
            j(str, entry.getValue());
        }
        if (TextUtils.equals(str2, "hrvUserInfo3")) {
            j(str, entry.getValue());
        }
        if (TextUtils.equals(str2, "hrvUserInfo4")) {
            j(str, entry.getValue());
        }
    }

    private static void j(String str, String str2) {
        String valueOf = String.valueOf(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        if (TextUtils.isEmpty(str2)) {
            cpw.a(false, "WifiDeviceControl", "handleUserHrvInfo hrvUserInfo is empty");
            return;
        }
        if (TextUtils.isEmpty(valueOf)) {
            cpw.a(false, "WifiDeviceControl", "handleUserHrvInfo huid is empty");
            return;
        }
        if (str2.contains(":")) {
            cpw.a(false, "WifiDeviceControl", "handleUserHrvInfo mHrvUserInfo is new");
            int indexOf = str2.indexOf(":");
            if (indexOf == -1) {
                LogUtil.b("WifiDeviceControl", "handleUserHrvInfo index = INVALID_NUMBER_TYPE");
                return;
            } else if (valueOf.equals(str2.substring(0, indexOf).trim())) {
                g(str, str2.substring(str2.indexOf(":") + 1, str2.length()));
                return;
            } else {
                cpw.a(false, "WifiDeviceControl", "handleUserHrvInfo huid is not same");
                return;
            }
        }
        cpw.a(false, "WifiDeviceControl", "handleUserHrvInfo mHrvUserInfo is old");
        g(str, str2);
    }

    private static void g(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), str, str2, (StorageParams) null);
    }

    public static void m(String str) {
        cjx.e().f(str);
    }

    public static void b() {
        cpw.a(false, "WifiDeviceControl", "confirmDevUserInfo in");
        ArrayList<String> c2 = cjx.e().c();
        if (c2 != null && c2.size() > 0) {
            cpw.a(false, "WifiDeviceControl", "confirmDevUserInfo deviceIdList size:", Integer.valueOf(c2.size()));
            Iterator<String> it = c2.iterator();
            while (it.hasNext()) {
                String next = it.next();
                c(next);
                a(next, "devUserInfoNew");
            }
            return;
        }
        cpw.a(false, "WifiDeviceControl", "confirmDevUserInfo deviceIdList is null");
    }

    private static void b(String str, csa.e eVar) {
        LogUtil.a("WifiDeviceControl", "confirmDeviceUserInfo");
        String e = e(str);
        List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        Map<String, String> c2 = eVar.c();
        Iterator<cfi> it = allUser.iterator();
        while (it.hasNext()) {
            String i = it.next().i();
            if (String.valueOf(0).equals(i)) {
                i = accountInfo;
            }
            if (c2.containsKey(i)) {
                c(e, i, c2.get(i), str);
            } else {
                LogUtil.h("WifiDeviceControl", "confirmDeviceUserInfo key ", i, " user is not has");
            }
        }
        EventBus.d(new EventBus.b("device_user_success"));
    }

    private static void c(String str, String str2, String str3, String str4) {
        String b = b(str, str2, str4);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        cpw.c(false, "WifiDeviceControl", "uid is ", str2, "value is:", str3);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), b, str3, (StorageParams) null);
    }

    private static String b(String str, String str2, String str3) {
        return "wifi_devuserinfo_" + str + str2 + str3;
    }

    public static void a(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            cpw.e(false, "WifiDeviceControl", "devId is null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            cpw.e(false, "WifiDeviceControl", "sid is null");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(str);
        wifiDeviceServiceInfoReq.setSid(str2);
        jbs.a(cpp.a()).b(wifiDeviceServiceInfoReq, new ICloudOperationResult<WifiDeviceServiceInfoRsp>() { // from class: csf.4
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str3, boolean z) {
                int i;
                String str4;
                if (wifiDeviceServiceInfoRsp != null) {
                    LogUtil.c("WifiDeviceControl", "getDeviceUserInfo rsp:", wifiDeviceServiceInfoRsp.toString());
                }
                if (z) {
                    if (wifiDeviceServiceInfoRsp != null && wifiDeviceServiceInfoRsp.getDeviceServiceInfo() != null && !wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
                        csf.e(str, str2, wifiDeviceServiceInfoRsp);
                        return;
                    } else {
                        if ("devUserInfoNew".equals(str2)) {
                            csf.a(str, "devUserInfo");
                            return;
                        }
                        return;
                    }
                }
                if (wifiDeviceServiceInfoRsp != null) {
                    i = wifiDeviceServiceInfoRsp.getResultCode().intValue();
                    str4 = wifiDeviceServiceInfoRsp.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str4 = "getDeviceUserInfo() occured unknown error";
                }
                cpw.d(false, "WifiDeviceControl", "getDeviceUserInfo() errCode = ", Integer.valueOf(i), ", getDeviceUserInfo() resultDesc:", str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, String str2, WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp) {
        for (DeviceServiceInfo deviceServiceInfo : wifiDeviceServiceInfoRsp.getDeviceServiceInfo()) {
            String userData = deviceServiceInfo.getUserData();
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (!TextUtils.isEmpty(userData)) {
                cpw.a(false, "WifiDeviceControl", "saveDeviceUser dev user info is new Version");
                csa csaVar = new csa();
                csaVar.e(userData);
                csa.e c2 = csaVar.c(accountInfo);
                if (c2 != null) {
                    b(str, c2);
                } else {
                    if (g(str)) {
                        LogUtil.a("WifiDeviceControl", "saveDeviceUser hagrid device delete local data");
                        b(str);
                        EventBus.d(new EventBus.b("device_user_success"));
                    }
                    cpw.a(false, "WifiDeviceControl", "saveDeviceUser not found UserData for new device");
                }
            } else {
                Map<String, String> data = deviceServiceInfo.getData();
                if (data != null) {
                    cpw.a(false, "WifiDeviceControl", "saveDeviceUser dev user info is old Version");
                    csa csaVar2 = new csa();
                    csaVar2.d(data);
                    csa.e c3 = csaVar2.c(accountInfo);
                    if (c3 != null) {
                        b(str, c3);
                    } else {
                        cpw.a(false, "WifiDeviceControl", "saveDeviceUser not found UserData for old device");
                    }
                } else {
                    if ("devUserInfoNew".equals(str2)) {
                        a(str, "devUserInfo");
                    }
                    cpw.a(false, "WifiDeviceControl", "saveDeviceUser not dev user info");
                }
            }
        }
    }

    public static String e(String str, String str2) {
        if (String.valueOf(0).equals(str2)) {
            str2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        }
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), b(e(str), str2, str));
    }

    public static String e(String str) {
        String str2 = "huawei";
        if (!"huawei".equals(h(str))) {
            str2 = "honor";
            if (!"honor".equals(h(str))) {
                str2 = g(str) ? "hygride" : "otherScale";
            }
        }
        LogUtil.a("WifiDeviceControl", "getScaleType() scaleType:", str2);
        return str2;
    }

    public static void b(String str) {
        Iterator<cfi> it = MultiUsersManager.INSTANCE.getAllUser().iterator();
        while (it.hasNext()) {
            b(str, it.next().i());
        }
    }

    public static void b(String str, String str2) {
        if (String.valueOf(0).equals(str2)) {
            str2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        }
        SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), b(e(str), str2, str));
    }

    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "WifiDeviceControl", "devUserInfo is null");
            return null;
        }
        String[] split = str.split("\\|");
        if (split.length >= 4) {
            return split[2];
        }
        return null;
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "WifiDeviceControl", "devUserInfo is null");
            return null;
        }
        String[] split = str.split("\\|");
        if (split.length >= 4) {
            return split[3];
        }
        return null;
    }

    public static void LF_(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (context == null) {
            cpw.a(false, "WifiDeviceControl", "showNoWiFiDeviceDialog context is null");
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            cpw.a(false, "WifiDeviceControl", "showNoWiFiDeviceDialog deviceId is null");
            m(str);
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string.IDS_device_wifi_device_does_not_exist);
        builder.czz_(R.string._2130841130_res_0x7f020e2a, onClickListener);
        builder.czC_(R.string.IDS_device_bind_device, onClickListener2);
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public static String h(String str) {
        String i = ctq.i(str);
        if (TextUtils.isEmpty(i)) {
            cpw.a(false, "WifiDeviceControl", "get productId error");
            return null;
        }
        if (i.equals("e4b0b1d5-2003-4d88-8b5f-c4f64542040b")) {
            return "huawei";
        }
        if (i.equals("a8ba095d-4123-43c4-a30a-0240011c58de")) {
            return "honor";
        }
        cpw.d(false, "WifiDeviceControl", "isHuaweiOrHonorScale default");
        return null;
    }

    public static boolean g(String str) {
        String i = ctq.i(str);
        if (TextUtils.isEmpty(i)) {
            LogUtil.a("WifiDeviceControl", "get productId error");
            return false;
        }
        return cpa.x(i);
    }

    public static void e(final String str, final CommBaseCallbackInterface commBaseCallbackInterface) {
        if (commBaseCallbackInterface == null) {
            cpw.a(false, "WifiDeviceControl", "getDeviceSubUser callback is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "WifiDeviceControl", "getDeviceSubUser deviceId error");
            commBaseCallbackInterface.onResult(-1, "", null);
        } else {
            cpw.a(false, "WifiDeviceControl", "getDeviceSubUser deviceId:", cpw.d(str));
            WifiDeviceGetAuthorizeSubUserReq wifiDeviceGetAuthorizeSubUserReq = new WifiDeviceGetAuthorizeSubUserReq();
            wifiDeviceGetAuthorizeSubUserReq.setDevId(str);
            jbs.a(cpp.a()).a(wifiDeviceGetAuthorizeSubUserReq, new ICloudOperationResult<WifiDeviceGetAuthorizeSubUserRsp>() { // from class: csf.6
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void operationResult(WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp, String str2, boolean z) {
                    csf.a(wifiDeviceGetAuthorizeSubUserRsp, z, str, commBaseCallbackInterface);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp, boolean z, String str, CommBaseCallbackInterface commBaseCallbackInterface) {
        int i;
        String str2;
        if (z) {
            crw crwVar = new crw();
            crwVar.d(str);
            if (wifiDeviceGetAuthorizeSubUserRsp != null) {
                List<AuthorizeSubUserInfo> authorizeSubUserList = wifiDeviceGetAuthorizeSubUserRsp.getAuthorizeSubUserList();
                if (authorizeSubUserList != null && authorizeSubUserList.size() > 0) {
                    for (AuthorizeSubUserInfo authorizeSubUserInfo : authorizeSubUserList) {
                        crwVar.e(authorizeSubUserInfo.getSubHuid(), authorizeSubUserInfo.getNickName(), authorizeSubUserInfo.getUserAccount());
                    }
                }
                if (crwVar.b() != null && crwVar.b().size() > 0) {
                    ctq.c(crwVar);
                } else {
                    ctq.d(str);
                }
            } else {
                ctq.d(str);
                cpw.d(false, "WifiDeviceControl", "getDeviceSubUser() get Sub User is null");
            }
            commBaseCallbackInterface.onResult(0, "", crwVar);
            return;
        }
        if (wifiDeviceGetAuthorizeSubUserRsp != null) {
            i = wifiDeviceGetAuthorizeSubUserRsp.getResultCode().intValue();
            str2 = wifiDeviceGetAuthorizeSubUserRsp.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "getDeviceSubUser() occured unknown error ";
        }
        commBaseCallbackInterface.onResult(i, str2, null);
        cpw.d(false, "WifiDeviceControl", "getDeviceSubUser() errCode = ", Integer.valueOf(i), ", getDeviceSubUser() resultDesc:", str2);
    }

    public static void c(String str) {
        cpw.a(false, "WifiDeviceControl", "getMultiAccountAbility");
        String str2 = "support_multi_account_" + str;
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", str2);
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", l(str) + "_" + str2);
        if (TextUtils.isEmpty(b) || "-1".equals(b) || TextUtils.isEmpty(b2)) {
            cpw.a(false, "WifiDeviceControl", "getMultiAccountAbility start");
            c(str, (CommBaseCallbackInterface) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str, String str2, CommBaseCallbackInterface commBaseCallbackInterface) {
        Iterator<DeviceServiceInfo> it = wifiDeviceServiceInfoRsp.getDeviceServiceInfo().iterator();
        while (it.hasNext()) {
            Map<String, String> data = it.next().getData();
            if (data != null) {
                SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", str2, data.get("multiAccount"), (StorageParams) null);
                SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", l(str) + "_" + str2, moj.e(data), (StorageParams) null);
                if (commBaseCallbackInterface != null) {
                    commBaseCallbackInterface.onResult(0, "", null);
                }
            }
        }
    }

    public static void c(final String str, final CommBaseCallbackInterface commBaseCallbackInterface) {
        cpw.a(false, "WifiDeviceControl", "getMultiAccountCapability");
        if (TextUtils.isEmpty(str)) {
            cpw.e(false, "WifiDeviceControl", "getMultiAccountCapability deviceId is null");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(str);
        wifiDeviceServiceInfoReq.setSid("devCapabilitySet");
        final String str2 = "support_multi_account_" + str;
        jbs.a(BaseApplication.getContext()).b(wifiDeviceServiceInfoReq, new ICloudOperationResult<WifiDeviceServiceInfoRsp>() { // from class: csf.7
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, String str3, boolean z) {
                if (z) {
                    if (wifiDeviceServiceInfoRsp != null && wifiDeviceServiceInfoRsp.getDeviceServiceInfo() != null && !wifiDeviceServiceInfoRsp.getDeviceServiceInfo().isEmpty()) {
                        cpw.e(false, "WifiDeviceControl", "getMultiAccountCapability isSuccess");
                        csf.a(wifiDeviceServiceInfoRsp, str, str2, commBaseCallbackInterface);
                    } else {
                        cpw.a(false, "WifiDeviceControl", "getMultiAccountCapability info is empty.");
                        csf.e(wifiDeviceServiceInfoRsp, commBaseCallbackInterface, str2);
                    }
                    cpw.a(false, "WifiDeviceControl", "getMultiAccountCapability res: ", wifiDeviceServiceInfoRsp, ", text: ", str3);
                    return;
                }
                cpw.d(false, "WifiDeviceControl", "getMultiAccountCapability status fail. text: ", str3, ", res: ", wifiDeviceServiceInfoRsp);
                csf.e(wifiDeviceServiceInfoRsp, commBaseCallbackInterface, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(WifiDeviceServiceInfoRsp wifiDeviceServiceInfoRsp, CommBaseCallbackInterface commBaseCallbackInterface, String str) {
        int i;
        String str2;
        SharedPreferenceManager.e(BaseApplication.getContext(), "wifi_weight_device", str, "-1", (StorageParams) null);
        if (wifiDeviceServiceInfoRsp != null) {
            i = wifiDeviceServiceInfoRsp.getResultCode().intValue();
            str2 = wifiDeviceServiceInfoRsp.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "handleMultiAccountError() occured unknown error";
        }
        if (commBaseCallbackInterface != null) {
            commBaseCallbackInterface.onResult(i, str2, null);
        }
    }

    public static void a(int i) {
        String str;
        cpw.a(false, "WifiDeviceControl", "sendEventToKaKa");
        if (i == 82 || i == 85) {
            str = "e4b0b1d5-2003-4d88-8b5f-c4f64542040b";
        } else {
            if (i != 84 && i != 86) {
                cpw.e(false, "WifiDeviceControl", "sendEventToKaKa productId is error");
                return;
            }
            str = "a8ba095d-4123-43c4-a30a-0240011c58de";
        }
        dcz d = ResourceManager.e().d(str);
        if (d == null || d.q().size() <= 0) {
            cpw.e(false, "WifiDeviceControl", "sendEventToKaKa productInfo is empty");
            return;
        }
        String b = d.n().b();
        cpw.c(false, "WifiDeviceControl", "deviceName is ", b);
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, b);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, d.l().name());
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_MEASURE_TODAY_WEIGHT), hashMap);
    }

    public static void a(Context context, ctk ctkVar, dcz dczVar) {
        cpw.a(false, "WifiDeviceControl", "sendBindDeviceEventToKaKa.");
        if (context == null || ctkVar == null) {
            cpw.e(false, "WifiDeviceControl", "sendBindDeviceEventToKaKa context or wifiDevice is null.");
            return;
        }
        if (dczVar == null) {
            cpw.e(false, "WifiDeviceControl", "sendBindDeviceEventToKaKa productInfo is null.");
            return;
        }
        if ("sent".equals(SharedPreferenceManager.b(context, String.valueOf(10000), "wife_device_send_event_to_kaka_" + dczVar.g()))) {
            cpw.a(false, "WifiDeviceControl", "sendBindDeviceEventToKaKa have send kaka info.");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("macAddress", dis.b(ctkVar.getAddress()));
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dczVar.n().b());
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, dczVar.l());
        hashMap.put("deviceId", dczVar.g());
        hashMap.put("productId", dczVar.t());
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(1500), hashMap);
        SharedPreferenceManager.e(context, String.valueOf(10000), "wife_device_send_event_to_kaka_" + dczVar.g(), "sent", (StorageParams) null);
    }

    public static void c(String str, String str2, ICloudOperationResult iCloudOperationResult) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("WifiDeviceControl", "setHagridAutoUpgrade autoUpgrade is null");
            return;
        }
        if (!"1".equals(str2) && !"0".equals(str2)) {
            LogUtil.h("WifiDeviceControl", "setHagridAutoUpgrade autoUpgrade is error,", str2);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "setHagridAutoUpgrade deviceId is null");
            ArrayList<ctk> e = ctq.e();
            if (koq.b(e)) {
                LogUtil.h("WifiDeviceControl", "setHagridAutoUpgrade allWifiDevices is null");
                return;
            }
            LogUtil.a("WifiDeviceControl", "setHagridAutoUpgrade devices size:", Integer.valueOf(e.size()));
            Iterator<ctk> it = e.iterator();
            while (it.hasNext()) {
                ctk next = it.next();
                if (cpa.x(next.getProductId())) {
                    String d = next.d();
                    LogUtil.a("WifiDeviceControl", "setHagridAutoUpgrade wifiDeviceId:", cpw.d(d));
                    e(d, str2, iCloudOperationResult);
                }
            }
            return;
        }
        LogUtil.a("WifiDeviceControl", "setHagridAutoUpgrade autoUpgrade:", str2);
        e(str, str2, iCloudOperationResult);
    }

    private static void e(String str, String str2, ICloudOperationResult iCloudOperationResult) {
        LogUtil.a("WifiDeviceControl", "sendHagrideAutoUpgtadeStatus autoUpgrade", str2);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("upgrade_auto", str2);
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(str);
        jbs.a(BaseApplication.getContext()).d(wifiDeviceControlDataModelReq, (ICloudOperationResult<CloudCommonReponse>) iCloudOperationResult);
    }

    public static void d(String str, String str2, boolean z) {
        if (!"1".equals(str2) && !"0".equals(str2)) {
            LogUtil.h("WifiDeviceControl", "setHagridBindSuccessParameter autoUpgrade is error,", str2);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "setHagridBindSuccessParameter deviceId is null");
            ArrayList<ctk> e = ctq.e();
            if (koq.b(e)) {
                LogUtil.h("WifiDeviceControl", "setHagridBindSuccessParameter allWifiDevices is null");
                return;
            }
            LogUtil.a("WifiDeviceControl", "setHagridBindSuccessParameter devices size:", Integer.valueOf(e.size()));
            Iterator<ctk> it = e.iterator();
            while (it.hasNext()) {
                ctk next = it.next();
                if (cpa.x(next.getProductId())) {
                    String d = next.d();
                    LogUtil.a("WifiDeviceControl", "setHagridBindSuccessParameter wifiDeviceId:", cpw.d(d));
                    e(d, str2, z);
                }
            }
            return;
        }
        e(str, str2, z);
    }

    private static void e(final String str, final String str2, boolean z) {
        LogUtil.a("WifiDeviceControl", "sendHagridBindSuccessParameter autoUpgradeStatus:", str2, "|isOpen:", Boolean.valueOf(z));
        String str3 = z ? "1" : "0";
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("dft", str3);
        hashMap.put("upgrade_auto", str2);
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(str);
        jbs.a(BaseApplication.getContext()).d(wifiDeviceControlDataModelReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csf.8
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str4, boolean z2) {
                if (z2) {
                    csc.a(str, str2);
                    LogUtil.a("WifiDeviceControl", "sendHagridBindSuccessParameter success ");
                } else if (cloudCommonReponse != null) {
                    cpw.d(false, "WifiDeviceControl", "sendHagridBindSuccessParameter errorCode:", Integer.valueOf(cloudCommonReponse.getResultCode().intValue()));
                } else {
                    cpw.d(false, "WifiDeviceControl", "sendHagridBindSuccessParameter cloudCommonReponse is null");
                }
            }
        });
    }

    public static void e(String str, ICloudOperationResult iCloudOperationResult) {
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("ROOT");
        if (TextUtils.isEmpty(url)) {
            LogUtil.a("WifiDeviceControl", "[grs][syncGrsUrl] grsUrl is empty.ignore");
            return;
        }
        LogUtil.a("WifiDeviceControl", "[grs][syncGrsUrl] begin syncGrsUrl ", str, url);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("set_ota_url", url);
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(str);
        jbs.a(BaseApplication.getContext()).d(wifiDeviceControlDataModelReq, (ICloudOperationResult<CloudCommonReponse>) iCloudOperationResult);
    }

    public static void e(boolean z) {
        cpw.a(false, "WifiDeviceControl", "setUserCollectionSwitch isOpen:", Boolean.valueOf(z));
        ArrayList<ctk> e = ctq.e();
        if (koq.b(e)) {
            LogUtil.h("WifiDeviceControl", "setUserCollectionSwitch exit: no wifi device connected!");
            return;
        }
        LogUtil.a("WifiDeviceControl", "setUserCollectionSwitch devices size:", Integer.valueOf(e.size()));
        Iterator<ctk> it = e.iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            if (!f(next.getProductId()) && next.b().k() == 1) {
                i(next.d(), z ? "1" : "0");
            }
        }
    }

    private static void i(String str, String str2) {
        LogUtil.h("WifiDeviceControl", "sendUserCollectionSwitch status:", str2);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("dft", str2);
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(str);
        jbs.a(BaseApplication.getContext()).d(wifiDeviceControlDataModelReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csf.9
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str3, boolean z) {
                if (z) {
                    LogUtil.a("WifiDeviceControl", "sendUserCollectionSwitch success ");
                } else if (cloudCommonReponse != null) {
                    cpw.d(false, "WifiDeviceControl", "sendUserCollectionSwitch errorCode =", Integer.valueOf(cloudCommonReponse.getResultCode().intValue()));
                }
            }
        });
    }

    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "a8ba095d-4123-43c4-a30a-0240011c58de".equals(str) || "e4b0b1d5-2003-4d88-8b5f-c4f64542040b".equals(str);
    }

    public static void c(String str, ICloudOperationResult iCloudOperationResult) {
        cpw.a(false, "WifiDeviceControl", "getHagridDeviceUnit in");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "getHagridDeviceUnit deviceId is null");
            return;
        }
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        wifiDeviceServiceInfoReq.setDevId(str);
        wifiDeviceServiceInfoReq.setSid("devCapabilitySet");
        jbs.a(BaseApplication.getContext()).b(wifiDeviceServiceInfoReq, (ICloudOperationResult<WifiDeviceServiceInfoRsp>) iCloudOperationResult);
    }

    public static void i(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("WifiDeviceControl", "unBindDevice deviceid is empty");
            return;
        }
        WifiDeviceUnbindReq wifiDeviceUnbindReq = new WifiDeviceUnbindReq();
        wifiDeviceUnbindReq.setDevId(str);
        LogUtil.a("WifiDeviceControl", "unBindDevice :", cpw.d(str));
        jbs.a(cpp.a()).c(wifiDeviceUnbindReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csf.10
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
                int i;
                String str3;
                LogUtil.a("WifiDeviceControl", "unBindDevice :", Boolean.valueOf(z));
                if (z) {
                    return;
                }
                if (cloudCommonReponse != null) {
                    i = cloudCommonReponse.getResultCode().intValue();
                    str3 = cloudCommonReponse.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str3 = "unBindDevice() occured unknown error";
                }
                LogUtil.h("WifiDeviceControl", " unBindDevice error:", Integer.valueOf(i), ", unBindDevice() resultDesc:", str3);
            }
        });
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "unBindDevice deviceid is empty");
            return;
        }
        WifiDeviceExitAuthorizeSubUserReq wifiDeviceExitAuthorizeSubUserReq = new WifiDeviceExitAuthorizeSubUserReq();
        wifiDeviceExitAuthorizeSubUserReq.setDevId(str);
        LogUtil.a("WifiDeviceControl", "exitWifiDeviceAuthorizeSubUser :", cpw.d(str));
        jbs.a(cpp.a()).e(wifiDeviceExitAuthorizeSubUserReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csf.1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
                int i;
                String str3;
                LogUtil.a("WifiDeviceControl", "exitWifiDeviceAuthorizeSubUser :", Boolean.valueOf(z));
                if (z) {
                    return;
                }
                if (cloudCommonReponse != null) {
                    i = cloudCommonReponse.getResultCode().intValue();
                    str3 = cloudCommonReponse.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str3 = "exitWifiDeviceAuthorizeSubUser() occured unknown error";
                }
                LogUtil.h("WifiDeviceControl", " unBindDevice error:", Integer.valueOf(i), ", exitWifiDeviceAuthorizeSubUser() resultDesc:", str3);
            }
        });
    }

    public static int a(String str, int i) {
        LogUtil.a("WifiDeviceControl", "enter getSupportDeviceCapabilityByValue");
        if (TextUtils.isEmpty(str) || i < 0) {
            LogUtil.h("WifiDeviceControl", "address or capabilityValue of device is error");
            return -100;
        }
        String k = k(str);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "wifi_weight_device", l(k) + "_support_multi_account_" + k);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("WifiDeviceControl", "the capability of weight fat in sp is empty");
            return -100;
        }
        Map<Integer, String> e = e(moj.a(b));
        if (e.size() == 0) {
            LogUtil.h("WifiDeviceControl", "the capability value map is empty");
            return -100;
        }
        String str2 = e.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("WifiDeviceControl", "the current capability value is empty");
            return -100;
        }
        try {
            return Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
            LogUtil.b("WifiDeviceControl", "isSupportDeviceCapabilityByValue NumberFormatException");
            return -100;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Map<Integer, String> e(Map<String, String> map) {
        char c2;
        LogUtil.a("WifiDeviceControl", "enter getCapabilityValueMap");
        HashMap hashMap = new HashMap(16);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            key.hashCode();
            switch (key.hashCode()) {
                case -2082094211:
                    if (key.equals("supportSmoothimpedance")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1370668340:
                    if (key.equals("supportHwBtOTA")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1117344980:
                    if (key.equals("supportMultiFreqMeas")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 971472922:
                    if (key.equals("support8electrodeMeasOnly")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1164107134:
                    if (key.equals("supportEncryptJSON")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1959081518:
                    if (key.equals("supportYouthMeas")) {
                        c2 = 5;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                hashMap.put(43, entry.getValue());
            } else if (c2 == 1) {
                hashMap.put(19, entry.getValue());
            } else if (c2 == 2) {
                hashMap.put(18, entry.getValue());
            } else if (c2 == 3) {
                hashMap.put(39, entry.getValue());
            } else if (c2 == 4) {
                hashMap.put(24, entry.getValue());
            } else if (c2 == 5) {
                hashMap.put(20, entry.getValue());
            } else {
                LogUtil.h("WifiDeviceControl", "getCapabilityValueMap getKey is empty");
            }
        }
        return hashMap;
    }

    private static String k(String str) {
        LogUtil.a("WifiDeviceControl", "enter getWeightFatDeviceIdByAddress");
        Iterator<ctk> it = cjx.e().a().iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            if (str.equals(cpa.h(next.getSerialNumber()))) {
                return next.d();
            }
        }
        return "";
    }

    private static String l(String str) {
        LogUtil.a("WifiDeviceControl", "enter getDeviceSoftwareVersionByAddress");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WifiDeviceControl", "getDeviceSoftwareVersionByDeviceId deviceId is empty");
            return "";
        }
        Iterator<ctk> it = cjx.e().a().iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            if (str.equals(next.d())) {
                LogUtil.a("WifiDeviceControl", "deviceId equals tempDeviceId");
                return next.b().o();
            }
        }
        return "";
    }
}
