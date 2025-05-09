package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.health.R;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hms.hihealth.data.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class lbv {

    /* renamed from: a, reason: collision with root package name */
    private static final String f14753a = mrv.d;
    private static final String c;
    private static final String d;

    public static int a(int i) {
        if (i == 1) {
            return R.drawable._2131429793_res_0x7f0b09a1;
        }
        if (i == 2) {
            return R.drawable._2131429794_res_0x7f0b09a2;
        }
        return 0;
    }

    private static int a(int i, float f) {
        return -1;
    }

    private static float b(float f) {
        if (f < 3.0f) {
            return 3.0f;
        }
        if (f > 13.0f) {
            return 13.0f;
        }
        return f;
    }

    private static int b(int i, float f) {
        if (f < 8.0f || i <= 0) {
            return -1;
        }
        return i > 20 ? 2 : 0;
    }

    public static boolean b(int i) {
        return i == 264 || i == 283;
    }

    private static int c(int i, float f) {
        if (f < 8.0f || i <= 0) {
            return -1;
        }
        return i > 3 ? 2 : 0;
    }

    private static int d(int i, float f) {
        if (f < 8.0f || i <= 0) {
            return -1;
        }
        if (i < 5) {
            return 1;
        }
        return i > 20 ? 2 : 0;
    }

    public static int e(int i) {
        if (i == 0) {
            return 13;
        }
        if (i == 1) {
            return 12;
        }
        if (i != 2) {
            return -1;
        }
        return MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY;
    }

    private static int e(int i, float f) {
        if (f < 8.0f || i <= 0) {
            return -1;
        }
        if (i >= 70 || f <= 10.0f) {
            return (i <= 140 || f >= 14.0f) ? 0 : 2;
        }
        return 1;
    }

    private static int j(int i, float f) {
        if (f < 8.0f || i <= 0) {
            return -1;
        }
        return i > 300 ? 2 : 0;
    }

    public static boolean j(int i) {
        return i == 264 || i == 265;
    }

    static {
        String d2 = drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", (String) null);
        d = d2;
        c = d2 + File.separator;
    }

    protected lbv() {
        throw new UnsupportedOperationException();
    }

    public static String e(double d2, int i, int i2) {
        if (i == 1) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(i2);
            numberFormat.setMinimumFractionDigits(i2);
            return numberFormat.format(d2);
        }
        if (i != 2) {
            return "";
        }
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMaximumFractionDigits(i2);
        percentInstance.setMinimumFractionDigits(i2);
        return percentInstance.format(d2 / 100.0d);
    }

    public static String e(byte[] bArr) {
        if (bArr == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "bytes == null");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString.toUpperCase(Locale.ENGLISH)).append(" ");
        }
        return stringBuffer.toString();
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "in == null || length == 0");
            return "";
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (!g(upperCase)) {
            return "";
        }
        return upperCase.substring(0, 2) + ":" + upperCase.substring(2, 4) + ":" + upperCase.substring(4, 6) + ":" + upperCase.substring(6, 8) + ":" + upperCase.substring(8, 10) + ":" + upperCase.substring(10, 12);
    }

    public static String j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "in == null || length == 0");
            return "";
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        return g(upperCase) ? upperCase : i(upperCase);
    }

    private static String i(String str) {
        String[] split;
        if (!str.contains(":") || (split = str.split(":")) == null || split.length != 6) {
            return "";
        }
        String str2 = split[0] + split[1] + split[2] + split[3] + split[4] + split[5];
        return g(str2) ? str2 : "";
    }

    public static boolean g(String str) {
        if (!TextUtils.isEmpty(str) && str.length() <= 12) {
            return Pattern.compile("^([A-F0-9]{2}){6}$").matcher(str).find();
        }
        LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "in == null || length >12");
        return false;
    }

    public static boolean a() {
        if (CommonUtil.bh()) {
            return CommonUtil.at();
        }
        return true;
    }

    public static void b(Context context, String str, String str2) {
        if (context == null || str == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || launchBy == null");
            return;
        }
        LogUtil.c("Track_IDEQ_UtilsForIndrEqp", "tickBiWhenActivityStart, launchBy:", str);
        HashMap hashMap = new HashMap(3);
        hashMap.put("prodId", e(str2, ""));
        hashMap.put("macAddress", dis.b(str2));
        hashMap.put("launchBy", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_ACTIVITY_START_2170001.value(), hashMap, 0);
    }

    public static void b(Context context, String str, String str2, QrCodeOrNfcInfo qrCodeOrNfcInfo) {
        if (context == null || str2 == null || qrCodeOrNfcInfo == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || name == null || qrCodeOrNfcInfo == null");
            return;
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBIWhenConnectStart, source:", str);
        HashMap hashMap = new HashMap(4);
        hashMap.put("source", str);
        hashMap.put("brocastName", str2);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, Constants.LINK);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, qrCodeOrNfcInfo.getDeviceType());
        hashMap.put("macAddress", dis.b(qrCodeOrNfcInfo.getBtMac()));
        String pid = qrCodeOrNfcInfo.getPid();
        if (TextUtils.isEmpty(pid)) {
            hashMap.put("prodId", e(qrCodeOrNfcInfo.getBtMac(), str2));
        } else {
            hashMap.put("prodId", pid);
        }
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_CONNECT_START_2170002.value(), hashMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(6);
        linkedHashMap.put("event", "start_connect");
        linkedHashMap.put("ftms", Constants.LINK);
        linkedHashMap.put("equipType_Brand_Model", Constants.LINK);
        linkedHashMap.put("macAfterSha256", Constants.LINK);
        linkedHashMap.put("invalidData", Constants.LINK);
        linkedHashMap.put("realUseTime", Constants.LINK);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_USE_INDOOR_EQUIP_80050001.value(), linkedHashMap);
    }

    public static void b(Context context, lao laoVar) {
        String str;
        if (context == null || laoVar == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context = null || devicesInfoBean == null");
            return;
        }
        String k = laoVar.k();
        String h = laoVar.h();
        String j = laoVar.j();
        String e = laoVar.e();
        if (e == null || e.length() == 0 || Constants.LINK.equals(e)) {
            str = Constants.LINK;
        } else {
            str = !c(context) ? "OVERSEA_USER" : dis.b(j(e));
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBIWhenConnectResult, result:", k, ", ftms:", h, ", name:", j);
        HashMap hashMap = new HashMap(7);
        hashMap.put("result", k);
        hashMap.put("ftms", h);
        hashMap.put("brocastName", j);
        String g = laoVar.g();
        hashMap.put("equipType", g);
        String c2 = laoVar.c();
        hashMap.put("equipBrand", c2);
        String d2 = laoVar.d();
        hashMap.put("equipModel", d2);
        hashMap.put("macAfterSha256", str);
        hashMap.put("macAfterSha256_2", dis.b(e));
        hashMap.put("prodId", e(e, j));
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_CONNECT_RESULT_2170003.value(), hashMap, 0);
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_CONNECT_RESULT_WHITE_2170017.value(), hashMap, 0);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(6);
        linkedHashMap.put("event", k);
        linkedHashMap.put("ftms", h);
        linkedHashMap.put("equipType_Brand_Model", g + "_" + c2 + "_" + d2);
        linkedHashMap.put("macAfterSha256", str);
        linkedHashMap.put("invalidData", Constants.LINK);
        linkedHashMap.put("realUseTime", Constants.LINK);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_USE_INDOOR_EQUIP_80050001.value(), linkedHashMap);
    }

    public static void a(String str, String str2, String str3, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("prodId", str);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, str3);
        hashMap.put("course_id", str2);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEART_RATE_CONTROL_2170049.value(), hashMap, 0);
    }

    private static String e(String str, String str2) {
        MeasurableDevice d2 = ceo.d().d(str, false);
        String prodId = d2 != null ? ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(d2.getProductId()) : "";
        return TextUtils.isEmpty(prodId) ? h(str2) : prodId;
    }

    private static String h(String str) {
        String d2 = d(str);
        if (TextUtils.isEmpty(d2)) {
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "broadcastName is empty");
            return "";
        }
        return ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(d2);
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "broadcastName is empty");
            return "";
        }
        List<cve> deviceInfoByBluetooth = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(str);
        if (!koq.c(deviceInfoByBluetooth)) {
            return "";
        }
        List<String> ac = deviceInfoByBluetooth.get(0).ac();
        return koq.c(ac) ? ac.get(0) : "";
    }

    public static void c(Context context, lao laoVar) {
        if (context == null || laoVar == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || devicesInfoBean == null");
            return;
        }
        String m = laoVar.m();
        String a2 = laoVar.a();
        String b = laoVar.b();
        String e = laoVar.e();
        String str = Constants.LINK;
        if (e != null && e.length() != 0 && !Constants.LINK.equals(e)) {
            str = !c(context) ? "OVERSEA_USER" : dis.b(j(e));
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBIWhenDisconnect, willReconnect:", m, ", breakBySelf:", a2, ", connectedBefore:", b);
        HashMap hashMap = new HashMap(7);
        hashMap.put("willRecon", m);
        hashMap.put("reconTimesThisBreakBefore", Integer.valueOf(laoVar.i()));
        hashMap.put("reconTimesTotalBefore", Integer.valueOf(laoVar.n()));
        hashMap.put("breakBySelf", a2);
        hashMap.put("connectedBefore", b);
        hashMap.put("brocastName", laoVar.j());
        hashMap.put("macAfterSha256", str);
        hashMap.put("macAfterSha256_2", dis.b(e));
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_DISCONNECT_2170004.value(), hashMap, 0);
    }

    public static void e(ProductMapInfo productMapInfo, int i, String str, int i2) {
        if (productMapInfo == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "productMapInfo == null");
            return;
        }
        String h = productMapInfo.h();
        dcz d2 = ResourceManager.e().d(h);
        if (d2 == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "productInfo is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("kind_name", d2.l() != null ? d2.l().name() : "");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, dks.a(h));
        hashMap.put("data_type", Integer.valueOf(i2));
        hashMap.put("prodId", productMapInfo.f());
        hashMap.put("macAddress", dis.b(str));
        hashMap.put("duration_time", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_ROPE_DEVICE_DATA_2170022.value(), hashMap, 0);
    }

    public static void a(Context context, lao laoVar, int i) {
        if (context == null || laoVar == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || devicesInfoBean == null");
            return;
        }
        boolean p = laoVar.p();
        String e = laoVar.e();
        String str = Constants.LINK;
        if (e != null && e.length() != 0 && !Constants.LINK.equals(e)) {
            str = !c(context) ? "OVERSEA_USER" : dis.b(j(e));
        }
        String str2 = p + "";
        String d2 = d(laoVar);
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBIWhenFinishSession, reason:", d2, ", invalidData:", str2, ", sportTarget: ", Integer.valueOf(i));
        HashMap hashMap = new HashMap(16);
        hashMap.put("reason", d2);
        hashMap.put("invalidData", str2);
        long l = laoVar.l();
        hashMap.put("realUseTime", Long.toString(l));
        String j = laoVar.j();
        hashMap.put("brocastName", j);
        hashMap.put("macAfterSha256", str);
        hashMap.put("macAfterSha256_2", dis.b(e));
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, laoVar.d());
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, laoVar.g());
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_SN, laoVar.f());
        hashMap.put("prodId", e(laoVar.e(), j));
        if (i != -1) {
            hashMap.put(Wpt.MODE, Integer.valueOf(i));
        }
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_FINISH_SESSION_2170006.value(), hashMap, 0);
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_USE_INDOOR_EQUIP_80050001.value(), d(str, d2, str2, l));
    }

    public static void e(String str, String str2, String str3) {
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "biEventForRopePerformance entry");
        HashMap hashMap = new HashMap();
        hashMap.put("macAddress", dis.b(str2));
        hashMap.put("page_id", str3);
        hashMap.put("prodId", dij.e(str));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_ROPE_TWO_LEVEL_PAGE_2170040.value(), hashMap, 0);
    }

    private static String d(lao laoVar) {
        boolean t = laoVar.t();
        boolean o = laoVar.o();
        boolean r = laoVar.r();
        boolean q = laoVar.q();
        return (t && o && !r && q) ? "PressPhoneBtn" : (!t || o || r || q) ? (t || !o || r || q) ? (!t || !o || r || q) ? DeviceInfo.STR_TYPE_UNKNOWN : "PressEquipBtn" : "UnexpectedBreak" : "PressEndBtn(2BtnDialog)";
    }

    private static LinkedHashMap<String, String> d(String str, String str2, String str3, long j) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(6);
        linkedHashMap.put("event", str2);
        linkedHashMap.put("ftms", Constants.LINK);
        linkedHashMap.put("equipType_Brand_Model", Constants.LINK);
        linkedHashMap.put("macAfterSha256", str);
        linkedHashMap.put("invalidData", str3);
        linkedHashMap.put("realUseTime", Long.toString(j));
        return linkedHashMap;
    }

    public static void a(Context context, String str) {
        if (context == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context = null");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("prodId", e(str, ""));
        hashMap.put("macAddress", dis.b(str));
        hashMap.put("hrFromWear", "1");
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_HR_FROM_WEAR_2170007.value(), hashMap, 0);
    }

    public static void c(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || clickBtn == null");
            return;
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBiWhenClickBtn, clickBtn:", str);
        HashMap hashMap = new HashMap(1);
        hashMap.put("clickBtn", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_CLICK_BTN_2170008.value(), hashMap, 0);
    }

    public static void d(Context context, String str) {
        if (context == null || str == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || lifeCycle = null");
            return;
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBiWhenActivityLifecycleChange, lifeCycle:", str);
        HashMap hashMap = new HashMap(1);
        hashMap.put("lifeCycle", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_ACTIVITY_LIFECYCLE_CHANGE_2170009.value(), hashMap, 0);
    }

    public static void e(Context context, String str, String str2) {
        if (context == null || str == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null || lifeCycle == null");
            return;
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBiWhenLandActivityLifecycleChange, lifeCycle:", str);
        HashMap hashMap = new HashMap(3);
        hashMap.put("prodId", e(str2, ""));
        hashMap.put("macAddress", dis.b(str2));
        hashMap.put("lifeCycle", str);
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_LAND_ACTIVITY_LIFECYCLE_CHANGE_2170012.value(), hashMap, 0);
    }

    public static void e(Context context, String str) {
        if (context == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context ==null");
            return;
        }
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "tickBIWhenUseTV");
        HashMap hashMap = new HashMap(2);
        hashMap.put("useTV", "1");
        hashMap.put("prodId", e(str, ""));
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_USE_TV_2170013.value(), hashMap, 0);
    }

    public static void a(Context context, boolean z) {
        if (context == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null");
            return;
        }
        LogUtil.c("Track_IDEQ_UtilsForIndrEqp", "tickBiWhenShowPrivacyDialog, isAgreeOrNot:", Boolean.valueOf(z));
        HashMap hashMap = new HashMap(1);
        hashMap.put("isAgreeOrNot", Boolean.valueOf(z));
        ixx.d().d(context, AnalyticsValue.HEALTH_GYM_EQUIP_AGREE_OR_NOT_2170015.value(), hashMap, 0);
    }

    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("proto=2") || str.contains("ftmp=1");
    }

    public static String c(String str) {
        String substring;
        if (str == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "payload == null");
            return "";
        }
        int indexOf = str.indexOf("&tvn=");
        if (indexOf == -1 || (substring = str.substring(indexOf + 4)) == null) {
            return "";
        }
        int indexOf2 = substring.indexOf("&");
        int indexOf3 = substring.indexOf(";");
        if (indexOf2 != -1) {
            return substring.substring(1, indexOf2);
        }
        if (indexOf3 != -1) {
            return substring.substring(1, indexOf3);
        }
        return substring.substring(1);
    }

    public static String b(String str) {
        String substring;
        int indexOf;
        if (str == null) {
            LogUtil.h("Track_IDEQ_UtilsForIndrEqp", "payload == null");
            return "";
        }
        try {
            substring = str.substring(str.indexOf(RecordAction.ACT_COST_TIME_TAG));
            indexOf = substring.indexOf("&");
        } catch (IndexOutOfBoundsException e) {
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "no devicetype(t=) in NFC payload, use default type,", e.getLocalizedMessage());
        }
        return indexOf < 0 ? "31" : substring.substring(2, indexOf);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005a, code lost:
    
        if (r7.equals(com.huawei.operation.ble.BleConstants.SPORT_TYPE_BIKE) == false) goto L30;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(java.lang.String r7) {
        /*
            r0 = 0
            if (r7 != 0) goto Lf
            java.lang.String r7 = "deviceType is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r1 = "Track_IDEQ_UtilsForIndrEqp"
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return r0
        Lf:
            r7.hashCode()
            int r1 = r7.hashCode()
            r2 = 49750(0xc256, float:6.9715E-41)
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == r2) goto L54
            r0 = 49776(0xc270, float:6.9751E-41)
            if (r1 == r0) goto L49
            switch(r1) {
                case 49772: goto L3e;
                case 49773: goto L33;
                case 49774: goto L28;
                default: goto L27;
            }
        L27:
            goto L5c
        L28:
            java.lang.String r0 = "262"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L31
            goto L5c
        L31:
            r0 = r4
            goto L5d
        L33:
            java.lang.String r0 = "261"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L3c
            goto L5c
        L3c:
            r0 = r5
            goto L5d
        L3e:
            java.lang.String r0 = "260"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L47
            goto L5c
        L47:
            r0 = r6
            goto L5d
        L49:
            java.lang.String r0 = "264"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L52
            goto L5c
        L52:
            r0 = r3
            goto L5d
        L54:
            java.lang.String r1 = "259"
            boolean r7 = r7.equals(r1)
            if (r7 != 0) goto L5d
        L5c:
            r0 = -1
        L5d:
            if (r0 == 0) goto L76
            if (r0 == r6) goto L73
            if (r0 == r5) goto L70
            if (r0 == r4) goto L6d
            if (r0 == r3) goto L6a
            r7 = 264(0x108, float:3.7E-43)
            return r7
        L6a:
            r7 = 281(0x119, float:3.94E-43)
            return r7
        L6d:
            r7 = 283(0x11b, float:3.97E-43)
            return r7
        L70:
            r7 = 274(0x112, float:3.84E-43)
            return r7
        L73:
            r7 = 273(0x111, float:3.83E-43)
            return r7
        L76:
            r7 = 265(0x109, float:3.71E-43)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lbv.a(java.lang.String):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int b(String str, int i, float f) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1655237874:
                if (str.equals("touch time")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -224906426:
                if (str.equals("eversion angle")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 940791295:
                if (str.equals("ground impact")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1009164552:
                if (str.equals("step frequency")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1662601174:
                if (str.equals("ground shock peak")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1752831281:
                if (str.equals("swing angle")) {
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
            return j(i, f);
        }
        if (c2 == 1) {
            return d(i, f);
        }
        if (c2 == 2) {
            return b(i, f);
        }
        if (c2 == 3) {
            return a(i, f);
        }
        if (c2 == 4) {
            return c(i, f);
        }
        if (c2 != 5) {
            return -1;
        }
        return e(i, f);
    }

    public static boolean a(Context context) {
        if (context != null) {
            return LanguageUtil.m(context) && !Utils.o() && CommonUtil.y(context) && !UnitUtil.h();
        }
        LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null");
        return false;
    }

    public static boolean c(Context context) {
        if (context != null) {
            return !Utils.o() && CommonUtil.y(context) && (LoginInit.getInstance(context).getSiteId() == 1);
        }
        LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "context == null");
        return false;
    }

    public static Drawable bVS_(Context context, int i) {
        if (context == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "getLandingWayImgRes method,context is null!");
            return null;
        }
        if (LanguageUtil.bc(context)) {
            BitmapDrawable cKn_ = nrz.cKn_(context, i);
            if (cKn_ != null) {
                return cKn_;
            }
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "getLandingWayImgRes method,create mirrorImg failed!");
            return context.getResources().getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }

    public static Drawable bVR_(Context context, int i) {
        if (context == null) {
            LogUtil.b("Track_IDEQ_UtilsForIndrEqp", "getIconImgRes method,context is null!");
            return null;
        }
        if (LanguageUtil.bc(context)) {
            BitmapDrawable cKn_ = nrz.cKn_(context, i);
            if (cKn_ != null) {
                return cKn_;
            }
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "getIconImgRes method,create btConnectingRtl failed!");
            return context.getResources().getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }

    public static boolean b() {
        if (LanguageUtil.al(BaseApplication.getContext()) && !Utils.o()) {
            return false;
        }
        if (!CommonUtil.cg()) {
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "isOverseaForIndoorEquip!");
            return true;
        }
        LogUtil.h("Track_IDEQ_UtilsForIndrEqp", "is not overseaForIndoorEquip!");
        return false;
    }

    public static String d(int i, Context context) {
        if (context == null) {
            LogUtil.h("Track_IDEQ_UtilsForIndrEqp", "setHeartRateUnitText context null");
            return "";
        }
        return context.getResources().getString(R.string._2130840332_res_0x7f020b0c, context.getResources().getQuantityString(R.plurals._2130903250_res_0x7f0300d2, i, "").trim());
    }

    public static String e(int i, Context context) {
        if (context == null) {
            LogUtil.h("Track_IDEQ_UtilsForIndrEqp", "setHeartRateUnitText context null");
            return "";
        }
        if (nsn.ag(context)) {
            return context.getResources().getString(R.string._2130840232_res_0x7f020aa8);
        }
        return context.getResources().getString(R.string._2130840332_res_0x7f020b0c, context.getResources().getQuantityString(R.plurals._2130903250_res_0x7f0300d2, i, "").trim());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String e(String str, boolean z, boolean z2) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case 1630:
                if (str.equals("31")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 49750:
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 49772:
                if (str.equals("260")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 49773:
                if (str.equals("261")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 49776:
                if (str.equals(BleConstants.SPORT_TYPE_TREADMILL)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            return c(z, z2);
        }
        if (c2 == 1) {
            if (z) {
                return c + "pic_pad_indoor_bike_1.webp";
            }
            return c + "pic_indoor_bike_1.webp";
        }
        if (c2 == 2) {
            if (z) {
                return c + "pic_pad_cross_trainer_1.webp";
            }
            return c + "pic_cross_trainer_1.webp";
        }
        if (c2 == 3) {
            if (z) {
                return c + "pic_pad_rowing_machine_1.webp";
            }
            return c + "pic_rowing_machine_1.webp";
        }
        if (c2 != 4) {
            return "";
        }
        if (z) {
            return c + "pic_pad_walking_machine_1.webp";
        }
        return c + "pic_walking_machine_1.webp";
    }

    private static String c(boolean z, boolean z2) {
        if (z) {
            if (z2) {
                return c + "pic_runner_contact_4.webp";
            }
            return c + "pic_runner_contact_5.webp";
        }
        if (z2) {
            return c + "pic_runner_contact_1.webp";
        }
        return c + "pic_runner_contact_2.webp";
    }

    public static int a(float f) {
        return ((int) (b(f) * (-40.0f))) + 720;
    }

    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        if (nsn.ae(context)) {
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "isPad");
            return false;
        }
        if (nsn.ag(context)) {
            LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "isWideScreen");
            return false;
        }
        Configuration configuration = context.getResources().getConfiguration();
        int i = DisplayMetrics.DENSITY_DEVICE_STABLE;
        LogUtil.a("Track_IDEQ_UtilsForIndrEqp", "densityDpi = ", Integer.valueOf(configuration.densityDpi), ", deviceDpi = ", Integer.valueOf(i));
        return configuration.densityDpi > i;
    }

    public static boolean c(int i) {
        return !CommonUtil.bd() || UnitUtil.h() || (CommonUtil.bf() && i != 264) || Utils.o();
    }

    public static String d(int i) {
        return i != 265 ? i != 281 ? i != 283 ? i != 273 ? i != 274 ? "08F" : "0C1" : "0C0" : "095" : "092" : "0BF";
    }
}
