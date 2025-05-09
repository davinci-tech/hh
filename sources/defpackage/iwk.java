package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealthservice.auth.WhiteListApp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.UriConstants;
import health.compact.a.GRSManager;
import health.compact.a.HiCommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class iwk {
    private static final List<String> d;

    static {
        ArrayList arrayList = new ArrayList(10);
        d = arrayList;
        arrayList.add("com.huawei.ah100");
        arrayList.add("com.huawei.bone");
        arrayList.add("com.huawei.ohos.health.device");
    }

    static boolean b(String str) {
        boolean contains = d.contains(str);
        LogUtil.a("Debug_HiWhiteListAppUtil", "isWhiteListApp, packageName is ", str, " isWhiteApp is ", Boolean.valueOf(contains));
        return !HiCommonUtil.b(str) && contains;
    }

    public static void a() {
        b();
    }

    private static void b() {
        HashMap hashMap = new HashMap(16);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add("com.huawei.android.hms.health.profile.readonly");
        arrayList.add("com.huawei.android.hms.health.health.wgt");
        arrayList.add("com.huawei.android.hms.health.health.wgt.readonly");
        WhiteListApp c = c("com.huawei.ah100", arrayList);
        hashMap.put(c.getPackageName(), c);
        ikw.b().c(hashMap);
    }

    private static WhiteListApp c(String str, List<String> list) {
        WhiteListApp whiteListApp = new WhiteListApp();
        whiteListApp.setPackageName(str);
        String valueOf = String.valueOf(System.currentTimeMillis());
        whiteListApp.setAppId(valueOf);
        whiteListApp.setAccessToken(str);
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainWwwHuawei");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h("Debug_HiWhiteListAppUtil", "createWhiteListApp url is empty");
            return whiteListApp;
        }
        String b = b(list, str, url);
        LogUtil.a("Debug_HiWhiteListAppUtil", "appScopes are ", b);
        whiteListApp.setAppScopes(b);
        String a2 = a(list, valueOf, url);
        LogUtil.a("Debug_HiWhiteListAppUtil", "userScopes are ", a2);
        whiteListApp.setUserScopes(a2);
        return whiteListApp;
    }

    private static String b(List<String> list, String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        ArrayList arrayList = new ArrayList(10);
        sb.append("{\"scopes\":[{\"id\":13,\"name\":\"SCOPE_ACCOUNT_BASEPROFILE\",\"permissions\":[\"com.huawei.android.hms.account.getBaseProfile\"],\"uri\":\"");
        sb.append(str2);
        sb.append("/auth/account/base.profile\"}");
        for (String str3 : list) {
            if (HiScopeUtil.c(str3) && !arrayList.contains(str3)) {
                String a2 = a(str3, str2);
                String d2 = d(str3, str2);
                if (!HiCommonUtil.b(a2)) {
                    sb.append(a2);
                }
                if (!HiCommonUtil.b(d2)) {
                    sb.append(d(str3, str2));
                }
                arrayList.add(str3);
            }
        }
        sb.append(",{\"id\":9999,\"name\":\"DEFAULT_SCOPE\",\"permissions\":[\"com.huawei.android.hms.account.getOpenID\"],\"uri\":\"");
        sb.append(str2);
        sb.append("/default/scopes\"}],\"certFingerprint\":\"");
        sb.append(str);
        sb.append("\",\"appAttr\":{\"appCnName\":\"");
        sb.append(str);
        sb.append("\"}}");
        return sb.toString();
    }

    private static String a(List<String> list, String str, String str2) {
        StringBuilder sb = new StringBuilder(16);
        ArrayList arrayList = new ArrayList(10);
        sb.append("{\"scope\":\"");
        sb.append(str2);
        sb.append(UriConstants.BASE_ACCOUNT_SCOPE);
        for (String str3 : list) {
            if (HiScopeUtil.c(str3) && !arrayList.contains(str3)) {
                String e = e(str3, str2);
                String b = b(str3, str2);
                if (!HiCommonUtil.b(e)) {
                    sb.append(e);
                }
                if (!HiCommonUtil.b(b)) {
                    sb.append(d(str3, str2));
                }
                arrayList.add(str3);
            }
        }
        sb.append("\",\"open_id\":\"com.huawei.ah100\",\"expire_in\":788400000,\"client_id\":\"");
        sb.append(str);
        sb.append("\"}");
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String a(String str, String str2) {
        char c;
        StringBuilder sb = new StringBuilder(16);
        str.hashCode();
        switch (str.hashCode()) {
            case -1993046373:
                if (str.equals("com.huawei.android.hms.health.sport")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -669970862:
                if (str.equals("com.huawei.android.hms.health.health.slp.readonly")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -544871803:
                if (str.equals("com.huawei.android.hms.health.health.wgt.readonly")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 87038942:
                if (str.equals("com.huawei.android.hms.health.health.slp")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 87042635:
                if (str.equals("com.huawei.android.hms.health.health.wgt")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1102931808:
                if (str.equals("com.huawei.android.hms.health.profile.readonly")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1536976949:
                if (str.equals("com.huawei.android.hms.health.sport.readonly")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1927199504:
                if (str.equals("com.huawei.android.hms.health.profile")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1942470019:
                if (str.equals("com.huawei.android.hms.health.health.hr")) {
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
                sb.append(",{\"id\":103,\"name\":\"SCOPE_HEALTH_SPORT\",\"permissions\":[\"com.huawei.android.hms.health.sport\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/sport\"}");
                break;
            case 1:
                sb.append(",{\"id\":108,\"name\":\"SCOPE_HEALTH_HEALTH_SLP_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.health.slp.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.slp.readonly\"}");
                break;
            case 2:
                sb.append(",{\"id\":106,\"name\":\"SCOPE_HEALTH_HEALTH_WGT_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.health.wgt.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.wgt.readonly\"}");
                break;
            case 3:
                sb.append(",{\"id\":107,\"name\":\"SCOPE_HEALTH_HEALTH_SLP\",\"permissions\":[\"com.huawei.android.hms.health.health.slp\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.slp\"}");
                break;
            case 4:
                sb.append(",{\"id\":105,\"name\":\"SCOPE_HEALTH_HEALTH_WGT\",\"permissions\":[\"com.huawei.android.hms.health.health.wgt\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.wgt\"}");
                break;
            case 5:
                sb.append(",{\"id\":102,\"name\":\"SCOPE_HEALTH_PROFILE_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.profile.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/profile.readonly\"}");
                break;
            case 6:
                sb.append(",{\"id\":104,\"name\":\"SCOPE_HEALTH_SPORT_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.sport.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/sport.readonly\"}");
                break;
            case 7:
                sb.append(",{\"id\":101,\"name\":\"SCOPE_HEALTH_PROFILE\",\"permissions\":[\"com.huawei.android.hms.health.profile\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/profile\"}");
                break;
            case '\b':
                sb.append(",{\"id\":109,\"name\":\"SCOPE_HEALTH_HEALTH_HR\",\"permissions\":[\"com.huawei.android.hms.health.health.hr\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.hr\"}");
                break;
            default:
                return null;
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String d(String str, String str2) {
        char c;
        StringBuilder sb = new StringBuilder(16);
        str.hashCode();
        switch (str.hashCode()) {
            case -2032896435:
                if (str.equals("com.huawei.android.hms.health.health.hr.readonly")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1974441614:
                if (str.equals("com.huawei.android.hms.health.health.bg.readonly")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1777928109:
                if (str.equals("com.huawei.android.hms.health.health.bf.readonly")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1715814336:
                if (str.equals("com.huawei.android.hms.health.health.ecg.readonly")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1539745260:
                if (str.equals("com.huawei.android.hms.health.motionpath")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 87025200:
                if (str.equals("com.huawei.android.hms.health.health.ecg")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1678470364:
                if (str.equals("com.huawei.android.hms.health.motionpath.readonly")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1942469821:
                if (str.equals("com.huawei.android.hms.health.health.bf")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1942469822:
                if (str.equals("com.huawei.android.hms.health.health.bg")) {
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
                sb.append(",{\"id\":110,\"name\":\"SCOPE_HEALTH_HEALTH_HR_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.health.hr.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.hr.readonly\"}");
                break;
            case 1:
                sb.append(",{\"id\":114,\"name\":\"SCOPE_HEALTH_HEALTH_BG_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.health.bg.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.bg.readonly\"}");
                break;
            case 2:
                sb.append(",{\"id\":116,\"name\":\"SCOPE_HEALTH_HEALTH_BF_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.health.bf.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.bf.readonly\"}");
                break;
            case 3:
                sb.append(",{\"id\":112,\"name\":\"SCOPE_HEALTH_HEALTH_ECG_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.health.ecg.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.ecg.readonly\"}");
                break;
            case 4:
                sb.append(",{\"id\":117,\"name\":\"SCOPE_HEALTH_MOTIONPATH\",\"permissions\":[\"com.huawei.android.hms.health.motionpath\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/motionpath\"}");
                break;
            case 5:
                sb.append(",{\"id\":111,\"name\":\"SCOPE_HEALTH_HEALTH_ECG\",\"permissions\":[\"com.huawei.android.hms.health.health.ecg\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.ecg\"}");
                break;
            case 6:
                sb.append(",{\"id\":118,\"name\":\"SCOPE_HEALTH_MOTIONPATH_READONLY\",\"permissions\":[\"com.huawei.android.hms.health.motionpath.readonly\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/motionpath.readonly\"}");
                break;
            case 7:
                sb.append(",{\"id\":115,\"name\":\"SCOPE_HEALTH_HEALTH_BF\",\"permissions\":[\"com.huawei.android.hms.health.health.bf\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.bf\"}");
                break;
            case '\b':
                sb.append(",{\"id\":113,\"name\":\"SCOPE_HEALTH_HEALTH_BG\",\"permissions\":[\"com.huawei.android.hms.health.health.bg\"],\"uri\":\"");
                sb.append(str2);
                sb.append("/health/health.bg\"}");
                break;
            default:
                return null;
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String e(String str, String str2) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1993046373:
                if (str.equals("com.huawei.android.hms.health.sport")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -669970862:
                if (str.equals("com.huawei.android.hms.health.health.slp.readonly")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -544871803:
                if (str.equals("com.huawei.android.hms.health.health.wgt.readonly")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 87038942:
                if (str.equals("com.huawei.android.hms.health.health.slp")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 87042635:
                if (str.equals("com.huawei.android.hms.health.health.wgt")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1102931808:
                if (str.equals("com.huawei.android.hms.health.profile.readonly")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1536976949:
                if (str.equals("com.huawei.android.hms.health.sport.readonly")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1927199504:
                if (str.equals("com.huawei.android.hms.health.profile")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1942470019:
                if (str.equals("com.huawei.android.hms.health.health.hr")) {
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
                return " " + str2 + "/health/sport";
            case 1:
                return " " + str2 + UriConstants.READ_SLEEP_DATA;
            case 2:
                return " " + str2 + UriConstants.READ_WEIGHT;
            case 3:
                return " " + str2 + "/health/health.slp";
            case 4:
                return " " + str2 + "/health/health.wgt";
            case 5:
                return " " + str2 + UriConstants.READ_PERSONAL_PROFILE;
            case 6:
                return " " + str2 + UriConstants.READ_SPORTS_DATA;
            case 7:
                return " " + str2 + UriConstants.READ_WRITE_PERSONAL_PROFILE;
            case '\b':
                return " " + str2 + "/health/health.hr";
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String b(String str, String str2) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2032896435:
                if (str.equals("com.huawei.android.hms.health.health.hr.readonly")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1974441614:
                if (str.equals("com.huawei.android.hms.health.health.bg.readonly")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1777928109:
                if (str.equals("com.huawei.android.hms.health.health.bf.readonly")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1715814336:
                if (str.equals("com.huawei.android.hms.health.health.ecg.readonly")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1539745260:
                if (str.equals("com.huawei.android.hms.health.motionpath")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 87025200:
                if (str.equals("com.huawei.android.hms.health.health.ecg")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1678470364:
                if (str.equals("com.huawei.android.hms.health.motionpath.readonly")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1942469821:
                if (str.equals("com.huawei.android.hms.health.health.bf")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1942469822:
                if (str.equals("com.huawei.android.hms.health.health.bg")) {
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
                return " " + str2 + UriConstants.READ_HEART_RATE;
            case 1:
                return " " + str2 + UriConstants.READ_BLOOD_SUGAR;
            case 2:
                return " " + str2 + "/health/health.bf.readonly";
            case 3:
                return " " + str2 + "/health/health.ecg.readonly";
            case 4:
                return " " + str2 + "/health/motionpath";
            case 5:
                return " " + str2 + "/health/health.ecg";
            case 6:
                return " " + str2 + UriConstants.READ_MOVEMENT_TRACKS;
            case 7:
                return " " + str2 + "/health/health.bf";
            case '\b':
                return " " + str2 + "/health/health.bg";
            default:
                return null;
        }
    }
}
