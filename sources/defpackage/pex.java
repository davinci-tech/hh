package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pex {
    private static final Object b = new Object();
    private static volatile pex e;

    /* renamed from: a, reason: collision with root package name */
    private DeviceInfo f16102a;
    private boolean c;
    private Context d;

    private pex(Context context) {
        this.d = context;
    }

    public static pex a() {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new pex(BaseApplication.getContext());
                }
            }
        }
        return e;
    }

    public String c(Context context, int i, String str) {
        String d = d(CommonUtil.u(), context, i, str);
        Uri parse = Uri.parse(d);
        LogUtil.a("HelpInteractor", "adaptUrlInMessageCenter path:", parse.getPath(), " query:", parse.getQuery());
        return d;
    }

    private String e(Context context, DeviceInfo deviceInfo) {
        this.f16102a = deviceInfo;
        String string = this.d.getString(R$string.IDS_app_help_b2_url_temp);
        if (this.f16102a == null) {
            LogUtil.h("HelpInteractor", "adaptUrl mCurrentDeviceInfo == null");
            return string;
        }
        String d = d(CommonUtil.u(), context, this.f16102a.getProductType(), "");
        LogUtil.a("HelpInteractor", "adaptUrl = ", d);
        return d;
    }

    public String b(int i, Context context) {
        String str;
        String str2;
        if (context != null) {
            Configuration configuration = context.getResources().getConfiguration();
            str = configuration.locale.getLanguage();
            str2 = configuration.locale.getCountry();
        } else {
            str = "";
            str2 = "";
        }
        return c(i, str + Constants.LINK + str2);
    }

    private String c(int i, String str) {
        Context context = BaseApplication.getContext();
        GRSManager a2 = GRSManager.a(context);
        String commonCountryCode = a2.getCommonCountryCode();
        this.c = "CN".equalsIgnoreCase(commonCountryCode);
        return e(a2.getNoCheckUrl("domainTipsResDbankcdnNew", commonCountryCode), str, e(context, i));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String d(String str, Context context, int i, String str2) {
        char c;
        GRSManager a2 = GRSManager.a(context);
        String commonCountryCode = a2.getCommonCountryCode();
        this.c = "CN".equalsIgnoreCase(commonCountryCode);
        if (cvz.c(this.f16102a)) {
            LogUtil.a("HelpInteractor", "isNewHonorHelp");
            return b(jfu.c(i).r(), str);
        }
        String noCheckUrl = a2.getNoCheckUrl("domainTipsResDbankcdnNew", commonCountryCode);
        String e2 = e(context, i);
        LogUtil.a("HelpInteractor", "getHelpUrl language:", str, " deviceName:", e2, " country:", commonCountryCode);
        e2.hashCode();
        switch (e2.hashCode()) {
            case -548961006:
                if (e2.equals("honor-aw70")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50838165:
                if (e2.equals("huawei-aw70-pro")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 65376728:
                if (e2.equals("Crius")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 71339733:
                if (e2.equals("Janus")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 80572835:
                if (e2.equals("Talos")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 80697840:
                if (e2.equals("Terra")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 784168373:
                if (e2.equals("huawei-aw70")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 923849586:
                if (e2.equals("honor-aw70-pro")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 987437085:
                if (e2.equals("Fortuna")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 6:
            case 7:
                return e(noCheckUrl, str, e2);
            case 2:
            case 3:
            case 4:
            case 5:
            case '\b':
                String c2 = c(str, noCheckUrl, e2);
                LogUtil.a("HelpInteractor", "getHelpUrl url :", c2);
                return c2;
            default:
                String c3 = c(i, str, str2);
                if (!"".equals(c3)) {
                    return c3;
                }
                String b2 = b(jfu.c(i).l(), str);
                if (!"".equals(b2)) {
                    return b2;
                }
                String noCheckUrl2 = a2.getNoCheckUrl("domainTipsResDbankcdn", commonCountryCode);
                LogUtil.a("HelpInteractor", "getHelpUrl helpUrl is null");
                return d(noCheckUrl2, str, e2);
        }
    }

    private String c(int i, String str, String str2) {
        cvj f;
        if (!TextUtils.isEmpty(str2)) {
            LogUtil.a("HelpInteractor", "getHelpUrlFromHilinkId hilinkId:", str2);
        } else {
            DeviceInfo deviceInfo = this.f16102a;
            if (deviceInfo != null) {
                str2 = deviceInfo.getHiLinkDeviceId();
            }
            return "";
        }
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        if (pluginInfoByDeviceType == null || (f = pluginInfoByDeviceType.f()) == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(f.b()).getString("ai_tips_product_" + str2));
            if (jSONObject.has("wear_help_url")) {
                String string = jSONObject.getString("wear_help_url");
                LogUtil.a("HelpInteractor", "WERA_HELP_URL is ", string);
                return c(str, string);
            }
        } catch (JSONException e2) {
            LogUtil.b("HelpInteractor", "WERA_HELP_URL error ", e2.getMessage());
        }
        return "";
    }

    private String c(String str, String str2) {
        LogUtil.a("HelpInteractor", "getUrlByLang mIsNationalDomainName:", Boolean.valueOf(this.c));
        if (this.c) {
            if (str != null && str.contains(Constants.LINK)) {
                String[] split = str.split(Constants.LINK);
                if (split.length > 0 && MLAsrConstants.LAN_ZH.equalsIgnoreCase(split[0])) {
                    return String.format(Locale.ENGLISH, str2, "zh-CN") + "&cid=11065";
                }
            }
            return String.format(Locale.ENGLISH, str2, "en-US");
        }
        if (TextUtils.equals(str, "zh-CN") || TextUtils.equals(str, "zh-rCN")) {
            return String.format(Locale.ENGLISH, str2, "zh-CN");
        }
        return String.format(Locale.ENGLISH, str2, str);
    }

    private String c(String str, String str2, String str3) {
        if (this.c) {
            if (TextUtils.equals(str, "zh-CN") || TextUtils.equals(str, "zh-rCN")) {
                return String.format(Locale.ENGLISH, str2 + "/%s/EMUI8.0/C001B001/%s/index.html", str3, "zh-CN");
            }
            return String.format(Locale.ENGLISH, str2 + "/%s/EMUI8.0/C001B001/%s/index.html", str3, "en-US");
        }
        if (TextUtils.equals(str, "zh-CN") || TextUtils.equals(str, "zh-rCN")) {
            return String.format(Locale.ENGLISH, str2 + "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=%s", "zh-CN", str3);
        }
        return String.format(Locale.ENGLISH, str2 + "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=%s", str, str3);
    }

    private String b(Map<String, String> map, String str) {
        if (map == null) {
            LogUtil.h("HelpInteractor", "getDefaultHelpUrl helpUrlMap is null");
            return "";
        }
        if (str != null && str.contains(Constants.LINK)) {
            String[] split = str.split(Constants.LINK);
            if (split.length > 0 && MLAsrConstants.LAN_ZH.equalsIgnoreCase(split[0])) {
                return map.get("zh_rCN");
            }
        }
        return String.format(Locale.ENGLISH, map.get("en_us"), str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private String e(String str, String str2, String str3) {
        char c;
        char c2;
        if (TextUtils.equals(str2, "zh-CN") || TextUtils.equals(str2, "zh-rCN")) {
            str3.hashCode();
            switch (str3.hashCode()) {
                case -548961006:
                    if (str3.equals("honor-aw70")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 50838165:
                    if (str3.equals("huawei-aw70-pro")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 784168373:
                    if (str3.equals("huawei-aw70")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 923849586:
                    if (str3.equals("honor-aw70-pro")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                return c(str2, str, "AW70_honor");
            }
            if (c == 1) {
                return b(str, str2, "AW70_B39");
            }
            if (c == 2) {
                return c(str2, str, "AW70");
            }
            if (c == 3) {
                return b(str, str2, "AW70_B39HN");
            }
            return c("zh-CN", str, "AW70");
        }
        str3.hashCode();
        switch (str3.hashCode()) {
            case -548961006:
                if (str3.equals("honor-aw70")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 50838165:
                if (str3.equals("huawei-aw70-pro")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 784168373:
                if (str3.equals("huawei-aw70")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 923849586:
                if (str3.equals("honor-aw70-pro")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            return c(str2, str, "AW70_honor");
        }
        if (c2 == 1) {
            return b(str, str2, "AW70_B39");
        }
        if (c2 == 2) {
            return c(str2, str, "AW70");
        }
        if (c2 == 3) {
            return b(str, str2, "AW70_B39HN");
        }
        return c(str2, str, "AW70");
    }

    private String b(String str, String str2, String str3) {
        if (TextUtils.equals(str2, "zh-CN") || TextUtils.equals(str2, "zh-rCN")) {
            if (this.c) {
                return str + String.format(Locale.ENGLISH, "/SmartWear/%s/EMUI8.0/C001B001/%s/index.html", str3, "zh-CN");
            }
            return str + String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=%s", "zh-CN", str3);
        }
        if (this.c) {
            return str + String.format(Locale.ENGLISH, "/SmartWear/%s/EMUI8.0/C001B001/%s/index.html", str3, "en-US");
        }
        return str + String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=%s", str2, str3);
    }

    private String d(String str, String str2, String str3) {
        LogUtil.a("HelpInteractor", "mIsNationalDomainName:", Boolean.valueOf(this.c));
        if (this.c) {
            if (str2 != null && str2.contains(Constants.LINK)) {
                String[] split = str2.split(Constants.LINK);
                if (split.length > 0 && MLAsrConstants.LAN_ZH.equalsIgnoreCase(split[0])) {
                    return String.format(Locale.ENGLISH, str + "/SmartWear/%s/EMUI8.0/C001B001/%s/index.html", str3, "zh-CN");
                }
            }
            return String.format(Locale.ENGLISH, str + "/SmartWear/%s/EMUI8.0/C001B001/%s/index.html", str3, "en-US");
        }
        if (TextUtils.equals(str2, "zh-CN") || TextUtils.equals(str2, "zh-rCN")) {
            return String.format(Locale.ENGLISH, str + "/handbook/SmartWear/%s/EMUI8.0/C001B001/%s/index.html", str3, "zh-CN");
        }
        return String.format(Locale.ENGLISH, str + "/handbook/SmartWear/%s/EMUI8.0/C001B001/%s/index.html", str3, "en-US");
    }

    private String e(Context context, int i) {
        switch (i) {
            case 3:
                return "huawei-watch";
            case 4:
            case 6:
            case 9:
            case 11:
            default:
                return c(i);
            case 5:
                String country = context != null ? context.getResources().getConfiguration().locale.getCountry() : "";
                return ("RU".equals(country) || "FR".equals(country) || FaqConstants.OPEN_TYPE_IN.equals(country)) ? "b0-honor" : "b0";
            case 7:
                return "B3";
            case 8:
                return "Metis";
            case 10:
                return "Leo";
            case 12:
                return "AW61";
            case 13:
                return "NYX";
            case 14:
                return "Grus";
            case 15:
                return "Eris";
        }
    }

    private String c(int i) {
        if (i == 1) {
            return "b2";
        }
        if (i == 11) {
            return "AM_R1";
        }
        if (i == 16) {
            return "Janus";
        }
        if (i == 34) {
            return "HUAWEI_WATCH_GT2";
        }
        if (i == 23) {
            return "huawei-aw70";
        }
        if (i == 24) {
            return "honor-aw70";
        }
        if (i == 36) {
            return "huawei-aw70-pro";
        }
        if (i == 37) {
            return "honor-aw70-pro";
        }
        switch (i) {
            case 18:
                return "Crius";
            case 19:
                return "Terra";
            case 20:
                return "Talos";
            case 21:
                return "Fortuna";
            default:
                return d(i);
        }
    }

    public String d(DeviceInfo deviceInfo) {
        String e2 = e(this.d, deviceInfo);
        Object[] objArr = new Object[4];
        objArr[0] = "getDeviceHelpUrl deviceName:";
        objArr[1] = deviceInfo != null ? deviceInfo.getDeviceName() : "null name";
        objArr[2] = ", helpUrl:";
        objArr[3] = e2;
        LogUtil.c("HelpInteractor", objArr);
        return e2;
    }

    private String d(int i) {
        return i != 35 ? i != 55 ? i != 44 ? i != 45 ? "" : "Andes_B29" : "Andes_B19" : "Terra_B0" : "HONOR_MagicWatch2";
    }
}
