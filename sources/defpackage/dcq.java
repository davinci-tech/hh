package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.io.File;
import java.util.Locale;

/* loaded from: classes3.dex */
public class dcq {
    private static final Object c = new Object();
    private static dcq d;

    private dcq() {
        LogUtil.a("PluginDevice_PathManager", "Enter getInstance()");
    }

    public static dcq b() {
        dcq dcqVar;
        synchronized (c) {
            if (d == null) {
                d = new dcq();
            }
            dcqVar = d;
        }
        return dcqVar;
    }

    public boolean a(String str) {
        File file = new File(cos.e + str);
        return file.exists() && file.delete();
    }

    public boolean d(String str) {
        return new File(b(str)).exists() && (new File(i(str)).exists() && new File(a(str, null)).exists());
    }

    public String e(String str) {
        StringBuilder sb = new StringBuilder(10);
        if (!CommonUtil.x(str)) {
            if (f(str)) {
                sb = c(c(c(c(sb, cos.e, 5000), str, 5000), File.separator, 5000), str, 5000);
            } else {
                sb = c(c(sb, cos.d, 5000), str, 5000);
            }
        }
        return sb.toString();
    }

    public String b(String str) {
        StringBuilder c2;
        StringBuilder sb = new StringBuilder(10);
        if (f(str)) {
            c2 = c(c(c(c(c(c(sb, cos.e, 5000), str, 5000), File.separator, 5000), str, 5000), File.separator, 5000), "product.xml", 5000);
        } else {
            c2 = c(c(c(c(sb, cos.d, 5000), str, 5000), File.separator, 5000), "product.xml", 5000);
        }
        return c2.toString();
    }

    public String a(String str, String str2) {
        if (CommonUtil.x(str) || CommonUtil.x(str2)) {
            LogUtil.h("PluginDevice_PathManager", "getImgPath productId or imgName more than MAX_STRING_LENGTH");
            return null;
        }
        String b = b(str, str2);
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String c2 = c(str, str2);
        if (!TextUtils.isEmpty(c2)) {
            return c2;
        }
        StringBuilder sb = new StringBuilder(10);
        if (f(str)) {
            StringBuilder c3 = c(c(c(c(c(c(sb, cos.e, 5000), str, 5000), File.separator, 5000), str, 5000), File.separator, 5000), "img", 5000);
            if (str2 != null) {
                c3 = c(c(c(c3, File.separator, 5000), str2, 5000), ".png", 5000);
            }
            return c3.toString();
        }
        StringBuilder c4 = c(c(c(c(sb, cos.d, 5000), str, 5000), File.separator, 5000), "img", 5000);
        if (str2 != null) {
            c4 = c(c(c(c4, File.separator, 5000), str2, 5000), ".png", 5000);
        }
        return c4.toString();
    }

    public String c(String str) {
        String c2;
        if (TextUtils.isEmpty(str) || (c2 = CommonUtil.c(c(c(c(c(c(c(new StringBuilder(10), cos.e, 5000), str, 5000), File.separator, 5000), str, 5000), File.separator, 5000), "H5/dist/index.html", 5000).toString())) == null || !new File(c2).exists()) {
            return "";
        }
        return Constants.PREFIX_FILE + c2;
    }

    public String i(String str) {
        return d(str, "");
    }

    public String d(String str, String str2) {
        StringBuilder c2;
        String language = Locale.getDefault().getLanguage();
        if (TextUtils.isEmpty(str2)) {
            str2 = language;
        }
        if (f(str)) {
            String j = j(str2);
            String str3 = cos.e + str + File.separator + str + File.separator;
            String c3 = CommonUtil.c(str3 + "lang/strings" + str2 + WatchFaceConstant.XML_SUFFIX);
            String c4 = CommonUtil.c(str3 + "lang/strings" + str2 + com.huawei.openalliance.ad.constant.Constants.LINK + j + WatchFaceConstant.XML_SUFFIX);
            return (c4 == null || !new File(c4).exists()) ? (c3 == null || !new File(c3).exists()) ? e(str3, str2) : c3 : c4;
        }
        StringBuilder c5 = c(c(c(new StringBuilder(10), cos.d, 5000), str, 5000), File.separator, 5000);
        if (MLAsrConstants.LAN_ZH.equals(str2) && LanguageUtil.m(BaseApplication.getContext())) {
            c2 = c(c5, "lang/stringszh-rCN.xml", 5000);
        } else {
            c2 = c(c5, "lang/strings.xml", 5000);
        }
        return c2.toString();
    }

    private String e(String str, String str2) {
        String c2 = CommonUtil.c(str + "lang/strings" + e(BaseApplication.getContext(), str2) + WatchFaceConstant.XML_SUFFIX);
        if (c2 != null && new File(c2).exists()) {
            return c2;
        }
        if (MLAsrConstants.LAN_ZH.equals(str2) && LanguageUtil.m(BaseApplication.getContext())) {
            return str + "lang/stringszh-rCN.xml";
        }
        return str + "lang/strings.xml";
    }

    private String j(String str) {
        String country = Locale.getDefault().getCountry();
        if (MLAsrConstants.LAN_ZH.equals(str)) {
            return LanguageUtil.m(BaseApplication.getContext()) ? "CN" : "HK".equals(country) ? "HK" : "TW";
        }
        if ("my".equals(str)) {
            return a();
        }
        LogUtil.a("PluginDevice_PathManager", "is other language");
        return country;
    }

    private String a() {
        return "Qaag".equals(Locale.getDefault().getScript()) ? "ZG" : "MM";
    }

    private String e(Context context, String str) {
        char c2;
        String h = h(str);
        if (h != null) {
            return h;
        }
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 3459) {
            if (str.equals("lo")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode == 3500) {
            if (str.equals("my")) {
                c2 = 1;
            }
            c2 = 65535;
        } else if (hashCode == 3670) {
            if (str.equals("si")) {
                c2 = 2;
            }
            c2 = 65535;
        } else if (hashCode != 3749) {
            if (hashCode == 3886 && str.equals(MLAsrConstants.LAN_ZH)) {
                c2 = 4;
            }
            c2 = 65535;
        } else {
            if (str.equals("uz")) {
                c2 = 3;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            return "lo-LA";
        }
        if (c2 != 1) {
            return c2 != 2 ? c2 != 3 ? c2 != 4 ? "" : LanguageUtil.m(context) ? "zh-CN" : "zh-TW" : "uz-UZ" : "si-LK";
        }
        return "my-" + a();
    }

    private String h(String str) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 3129) {
            if (str.equals("az")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode == 3149) {
            if (str.equals("bo")) {
                c2 = 1;
            }
            c2 = 65535;
        } else if (hashCode == 3241) {
            if (str.equals("en")) {
                c2 = 2;
            }
            c2 = 65535;
        } else if (hashCode == 3248) {
            if (str.equals("eu")) {
                c2 = 3;
            }
            c2 = 65535;
        } else if (hashCode == 3301) {
            if (str.equals("gl")) {
                c2 = 4;
            }
            c2 = 65535;
        } else if (hashCode == 3404) {
            if (str.equals("jv")) {
                c2 = 5;
            }
            c2 = 65535;
        } else if (hashCode == 3414) {
            if (str.equals("ka")) {
                c2 = 6;
            }
            c2 = 65535;
        } else if (hashCode == 3424) {
            if (str.equals("kk")) {
                c2 = 7;
            }
            c2 = 65535;
        } else if (hashCode != 3426) {
            if (hashCode == 3679 && str.equals("sr")) {
                c2 = '\t';
            }
            c2 = 65535;
        } else {
            if (str.equals("km")) {
                c2 = '\b';
            }
            c2 = 65535;
        }
        switch (c2) {
            case 0:
                return "az-AZ";
            case 1:
                return "bo-CN";
            case 2:
                return "en-GB";
            case 3:
                return "eu-ES";
            case 4:
                return "gl-ES";
            case 5:
                return "jv-ID";
            case 6:
                return "ka-GE";
            case 7:
                return "kk-KZ";
            case '\b':
                return "km-KH";
            case '\t':
                return "b+sr+Latn";
            default:
                return null;
        }
    }

    private String c(String str, String str2) {
        String d2;
        if (!CommonUtil.x(str) && !CommonUtil.x(str2)) {
            return (TextUtils.isEmpty(str2) || (d2 = d(str, str2, false)) == null || !new File(d2).exists()) ? "" : d2;
        }
        LogUtil.h("PluginDevice_PathManager", "isOldProductImgFileExists productId or imgName more than MAX_STRING_LENGTH");
        return "";
    }

    private String b(String str, String str2) {
        String d2;
        if (!CommonUtil.x(str) && !CommonUtil.x(str2)) {
            return (TextUtils.isEmpty(str2) || (d2 = d(str, str2, true)) == null || !new File(d2).exists()) ? "" : d2;
        }
        LogUtil.a("PluginDevice_PathManager", "isNewProductImgFileExists productId or imgName more than MAX_STRING_LENGTH");
        return "";
    }

    private String d(String str, String str2, boolean z) {
        StringBuilder c2;
        StringBuilder sb = new StringBuilder(10);
        if (z) {
            c2 = c(c(c(sb, cos.e, 5000), str, 5000), File.separator, 5000);
        } else {
            c2 = c(sb, cos.d, 5000);
        }
        return CommonUtil.c(c(c(c(c(c(c(c2, str, 5000), File.separator, 5000), "img", 5000), File.separator, 5000), str2, 5000), ".png", 5000).toString());
    }

    private boolean f(String str) {
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourDevice(str)) {
            return false;
        }
        String c2 = CommonUtil.c(cos.e + str + File.separator + str + File.separator + "product.xml");
        if (c2 == null) {
            return false;
        }
        return new File(c2).exists();
    }

    private StringBuilder c(StringBuilder sb, String str, int i) {
        if (sb != null && str != null && sb.length() + str.length() < i) {
            sb.append(str);
        }
        return sb;
    }
}
