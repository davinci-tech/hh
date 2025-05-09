package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ErrorCode;
import health.compact.a.CompileParameterUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public class cwa {
    private static final SparseIntArray b;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        b = sparseIntArray;
        sparseIntArray.put(21, R.string.IDS_select_device_b1_name);
        sparseIntArray.put(22, R.string.IDS_select_device_b2_name);
        sparseIntArray.put(39, R.string.IDS_app_display_name_gemini);
        sparseIntArray.put(36, R.string.IDS_app_display_name_w1);
        sparseIntArray.put(37, R.string.IDS_app_display_name_w1);
        sparseIntArray.put(42, R.string.IDS_app_display_name_nys);
        sparseIntArray.put(47, R.string.IDS_app_display_name_eris);
        sparseIntArray.put(51, R.string.IDS_messagecenter_color_band_name);
        sparseIntArray.put(23, R.string.IDS_messagecenter_color_band_name);
        sparseIntArray.put(43, R.string._2130849806_res_0x7f02300e);
        sparseIntArray.put(46, R.string.IDS_app_display_name_leo);
        sparseIntArray.put(61, R.string.IDS_app_display_name_k2);
        sparseIntArray.put(62, R.string.IDS_app_display_name_k2);
        sparseIntArray.put(74, R.string.IDS_app_display_name_janus);
        sparseIntArray.put(81, R.string.IDS_app_display_name_aw);
        sparseIntArray.put(83, R.string.IDS_app_display_name_honor_aw);
        sparseIntArray.put(90, R.string._2130849031_res_0x7f022d07);
        sparseIntArray.put(91, R.string._2130849039_res_0x7f022d0f);
        sparseIntArray.put(436, R.string._2130850281_res_0x7f0231e9);
        sparseIntArray.put(437, R.string._2130850285_res_0x7f0231ed);
        sparseIntArray.put(438, R.string._2130850286_res_0x7f0231ee);
        sparseIntArray.put(439, R.string._2130850282_res_0x7f0231ea);
        sparseIntArray.put(440, R.string._2130850283_res_0x7f0231eb);
        sparseIntArray.put(441, R.string._2130850284_res_0x7f0231ec);
        sparseIntArray.put(ErrorCode.ERROR_CODE_NO_USERINFO, R.string._2130850285_res_0x7f0231ed);
        sparseIntArray.put(g2.n, R.string._2130850286_res_0x7f0231ee);
        sparseIntArray.put(WorkoutRecord.COURSE_TYPE_YOGA, R.string._2130850283_res_0x7f0231eb);
        sparseIntArray.put(445, R.string._2130850283_res_0x7f0231eb);
        sparseIntArray.put(446, R.string._2130850283_res_0x7f0231eb);
        sparseIntArray.put(447, R.string._2130850283_res_0x7f0231eb);
        sparseIntArray.put(448, R.string._2130850283_res_0x7f0231eb);
        sparseIntArray.put(449, R.string._2130850283_res_0x7f0231eb);
    }

    private static ProductMapInfo d(String str, String str2) {
        ProductMapParseUtil.b(BaseApplication.getContext());
        List<ProductMapInfo> b2 = ProductMap.b(str, str2);
        if (koq.b(b2)) {
            LogUtil.a("DevicesNameSwitch", "productmap is null or empty");
            return null;
        }
        return b2.get(0);
    }

    public static String a(String str) {
        LogUtil.a("DevicesNameSwitch", "getDeviceNameForProdId prodId = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DevicesNameSwitch", "prodId is empty");
            return "";
        }
        ProductMapInfo d = d("smartProductId", str);
        if (d == null) {
            return "";
        }
        String b2 = d.b();
        LogUtil.c("DevicesNameSwitch", "getDeviceNameForProdId marketingName:", b2);
        if (TextUtils.isEmpty(b2)) {
            return "";
        }
        String[] split = b2.split("####");
        if (split.length != 2) {
            return split.length == 1 ? split[0] : "";
        }
        if (LanguageUtil.m(BaseApplication.getContext())) {
            return split[1];
        }
        return split[0];
    }

    public static String e(int i, Context context, String str) {
        return d(i, context, str, "");
    }

    public static String d(int i, Context context, String str, String str2) {
        String str3 = "";
        if (context == null) {
            LogUtil.h("DevicesNameSwitch", "context == null");
            return "";
        }
        if (i == 0) {
            LogUtil.h("DevicesNameSwitch", "invalid device type id");
            return a(str2);
        }
        LogUtil.a("DevicesNameSwitch", "getDeviceProductName type:", Integer.valueOf(i));
        ProductMapParseUtil.b(context);
        List<ProductMapInfo> b2 = ProductMap.b("deviceId", String.valueOf(i));
        if (koq.c(b2)) {
            String b3 = b2.get(0).b();
            LogUtil.c("DevicesNameSwitch", "getDeviceProductName marketingName:", b3);
            if (!TextUtils.isEmpty(b3)) {
                str3 = c(i, context, b3, str);
            }
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = d(i, context, str);
        }
        return TextUtils.isEmpty(str3) ? a(str2) : str3;
    }

    private static String d(int i, Context context, String str) {
        Resources resources = context.getResources();
        HashSet hashSet = new HashSet(Arrays.asList(81, 83, 90, 91));
        HashSet hashSet2 = new HashSet(Arrays.asList(35, 41, 44, 45, 74, 78, 77, 97));
        HashSet hashSet3 = new HashSet(Arrays.asList(79, 80));
        if (hashSet.contains(Integer.valueOf(i))) {
            return c(context, i);
        }
        if (hashSet2.contains(Integer.valueOf(i))) {
            return e(context, i);
        }
        if (hashSet3.contains(Integer.valueOf(i))) {
            return d(context, i);
        }
        return QN_(i, resources, str);
    }

    private static String c(int i, Context context, String str, String str2) {
        String[] split = str.split("####");
        if (split.length == 2) {
            if (LanguageUtil.m(context)) {
                return split[1];
            }
            return split[0];
        }
        if (split.length == 1) {
            return split[0];
        }
        return d(i, context, str2);
    }

    private static String d(Context context, int i) {
        if (i != 79) {
            return i != 80 ? "" : nsn.j(context);
        }
        return nsn.u(context);
    }

    private static String c(Context context, int i) {
        if (i == 81) {
            return nsn.o(context);
        }
        if (i == 83) {
            return nsn.m(context);
        }
        if (i != 90) {
            return i != 91 ? "" : nsn.k(context);
        }
        return nsn.n(context);
    }

    private static String e(Context context, int i) {
        if (i == 35) {
            return nsn.a(context);
        }
        if (i == 41) {
            return nsn.t(context);
        }
        if (i == 74) {
            return nsn.l(context);
        }
        if (i != 97) {
            if (i == 44) {
                return nsn.e(context);
            }
            if (i == 45) {
                return "GRUS";
            }
            if (i != 77) {
                return i != 78 ? "" : nsn.x(context);
            }
        }
        return nsn.b(context);
    }

    private static String QM_(int i, String str, Resources resources) {
        int i2 = b.get(i);
        LogUtil.a("DevicesNameSwitch", Integer.valueOf(i2));
        if (i2 == 0) {
            String e = e(i);
            LogUtil.a("DevicesNameSwitch", e);
            if (!TextUtils.isEmpty(e)) {
                return e;
            }
            i2 = resources.getIdentifier("IDS_app_name_health", "string", str);
        }
        return resources.getString(i2);
    }

    public static String a(int i, Context context, String str) {
        return a(i, context, str, "");
    }

    public static String a(int i, Context context, String str, String str2) {
        if (context == null) {
            return "";
        }
        String d = d(i, context, str, str2);
        Resources resources = context.getResources();
        return resources.getString(resources.getIdentifier("IDS_app_name_health", "string", str)).equals(d) ? "" : d;
    }

    private static String e(int i) {
        LogUtil.a("DevicesNameSwitch", Integer.valueOf(i));
        cvc pluginInfoByHiType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByHiType(i);
        if (pluginInfoByHiType == null || pluginInfoByHiType.f() == null) {
            LogUtil.a("DevicesNameSwitch", "getDefaultName pluginInfo pluginInfo.getWearDeviceInfo is null");
            return null;
        }
        if (CompileParameterUtil.a("IS_RELEASE_VERSION", false)) {
            return pluginInfoByHiType.f().ae();
        }
        return pluginInfoByHiType.f().bj();
    }

    private static String QL_(int i, String str, Resources resources) {
        LogUtil.a("DevicesNameSwitch", Integer.valueOf(i));
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        if (pluginInfoByDeviceType == null || pluginInfoByDeviceType.f() == null) {
            LogUtil.a("DevicesNameSwitch", "getDefaultName pluginInfo pluginInfo.getWearDeviceInfo is null");
            return resources.getString(resources.getIdentifier("IDS_app_name_health", "string", str));
        }
        String ae = pluginInfoByDeviceType.f().ae();
        return TextUtils.isEmpty(ae) ? resources.getString(resources.getIdentifier("IDS_app_name_health", "string", str)) : ae;
    }

    private static String QN_(int i, Resources resources, String str) {
        if (i == 92) {
            return QL_(44, str, resources);
        }
        if (i == 93) {
            return QL_(45, str, resources);
        }
        return QM_(i, str, resources);
    }
}
