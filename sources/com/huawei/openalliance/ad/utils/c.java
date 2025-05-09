package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.health.R;
import com.huawei.openalliance.ad.beans.metadata.CtrlExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fadata.PPSAbilityDataContent;
import com.huawei.openalliance.ad.fm;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.mr;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class c {
    public static boolean b(Integer num) {
        return num != null && num.intValue() == 7;
    }

    private static String b(Context context, ContentRecord contentRecord) {
        String str;
        int B = contentRecord.B();
        if (B == 0) {
            return null;
        }
        PromoteInfo af = contentRecord.af();
        if (B == 10) {
            if (af != null && af.getType() == 2 && !TextUtils.isEmpty(af.getName())) {
                return context.getResources().getString(R.string._2130851180_res_0x7f02356c, af.getName());
            }
            String string = context.getResources().getString(R.string._2130851180_res_0x7f02356c, "");
            if (string == null) {
                return null;
            }
            return string.trim();
        }
        if (B == 11) {
            return context.getResources().getString(R.string._2130851157_res_0x7f023555);
        }
        if (B == 8) {
            return context.getResources().getString(R.string._2130850996_res_0x7f0234b4);
        }
        if (af != null) {
            str = af.getName();
            if (af.getType() == 1) {
                boolean isEmpty = TextUtils.isEmpty(str);
                Resources resources = context.getResources();
                if (!isEmpty) {
                    return resources.getString(R.string._2130851092_res_0x7f023514, str);
                }
                String string2 = resources.getString(R.string._2130851092_res_0x7f023514, "");
                if (string2 == null) {
                    return null;
                }
                return string2.trim();
            }
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        AppInfo ae = contentRecord.ae();
        if (ae == null) {
            return null;
        }
        return (TextUtils.isEmpty(ae.getAppName()) || !i.a(context, ae.getPackageName())) ? str : ae.getAppName();
    }

    private static String b(Context context, PromoteInfo promoteInfo, boolean z) {
        if (promoteInfo == null || promoteInfo.getType() != 2 || TextUtils.isEmpty(promoteInfo.getName())) {
            return z ? context.getResources().getString(R.string._2130851041_res_0x7f0234e1) : context.getResources().getString(R.string._2130851035_res_0x7f0234db, context.getResources().getString(R.string._2130851180_res_0x7f02356c, ""));
        }
        Resources resources = context.getResources();
        Object[] objArr = {promoteInfo.getName()};
        return z ? resources.getString(R.string._2130851037_res_0x7f0234dd, objArr) : resources.getString(R.string._2130851035_res_0x7f0234db, objArr);
    }

    private static String b(Context context, int i) {
        return context.getResources().getString(i, context.getResources().getString(R.string._2130850996_res_0x7f0234b4));
    }

    public static boolean a(Integer num) {
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (intValue != 1 && intValue != 2 && intValue != 6) {
            switch (intValue) {
                case 12:
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean a(CtrlExt ctrlExt, Integer num) {
        return a(ctrlExt) && a(num);
    }

    public static boolean a(CtrlExt ctrlExt) {
        if (ctrlExt == null) {
            return false;
        }
        return "1".equals(ctrlExt.a());
    }

    public static boolean a(Context context, ContentRecord contentRecord, String str, Asset asset) {
        try {
            Pair<String, String> a2 = a(context, str, Constants.TPLATE_CACHE);
            if (a2 == null || TextUtils.isEmpty((CharSequence) a2.first) || TextUtils.isEmpty((CharSequence) a2.second)) {
                return false;
            }
            if (asset != null) {
                asset.b((String) a2.first);
            }
            if (contentRecord != null) {
                contentRecord.q((String) a2.second);
            }
            return true;
        } catch (Throwable th) {
            ho.b("AdDataUtil", "get path err: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean a(Context context, ContentRecord contentRecord, String str) {
        Pair<String, String> a2 = a(context, str, "normal");
        if (a2 == null || TextUtils.isEmpty((CharSequence) a2.first) || TextUtils.isEmpty((CharSequence) a2.second)) {
            return false;
        }
        if (contentRecord == null) {
            return true;
        }
        contentRecord.i((String) a2.first);
        contentRecord.q((String) a2.second);
        return true;
    }

    public static boolean a(Context context, DefaultTemplate defaultTemplate, String str, int i) {
        String str2;
        if (context == null) {
            return false;
        }
        String a2 = com.huawei.openalliance.ad.e.a();
        if (TextUtils.isEmpty(a2) || 30459301 > Integer.parseInt(a2)) {
            str2 = "uiengine not support";
        } else if (defaultTemplate == null || !defaultTemplate.c()) {
            str2 = "data is invalid";
        } else {
            if (a(i, defaultTemplate.a())) {
                if (defaultTemplate.b() == null) {
                    ho.b("AdDataUtil", "isShowV2Tpt, no fcCtl");
                    return true;
                }
                int intValue = defaultTemplate.b().intValue();
                int a3 = fm.a(context).a(str);
                ho.b("AdDataUtil", "isShowV2Tpt, tptFcCtl = %s, showTimes = %s", Integer.valueOf(intValue), Integer.valueOf(a3));
                return intValue > a3;
            }
            str2 = "templateId is invalid";
        }
        ho.b("AdDataUtil", str2);
        return false;
    }

    private static boolean a(int i, String str) {
        com.huawei.hms.ads.uiengine.e b = com.huawei.openalliance.ad.e.b();
        if (b == null) {
            return false;
        }
        try {
            return b.a(str, i, null);
        } catch (Throwable th) {
            ho.b("AdDataUtil", "check valid err: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean a() {
        String a2 = com.huawei.openalliance.ad.e.a();
        return !TextUtils.isEmpty(a2) && 30461200 <= Integer.parseInt(a2);
    }

    public static void a(Map<String, List<INativeAd>> map, String str, List<INativeAd> list) {
        if (map == null || TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return;
        }
        List<INativeAd> list2 = map.get(str);
        if (list2 != null) {
            list2.addAll(list);
        } else {
            map.put(str, list);
        }
    }

    public static String a(ContentRecord contentRecord, Context context, boolean z) {
        int B;
        PPSAbilityDataContent pPSAbilityDataContent;
        if (contentRecord == null || (B = contentRecord.B()) == 0 || B == 1 || B == 2) {
            return null;
        }
        if (B == 8) {
            return a(context, contentRecord.e());
        }
        if (B == 9 && (pPSAbilityDataContent = (PPSAbilityDataContent) be.b(contentRecord.aQ(), PPSAbilityDataContent.class, new Class[0])) != null) {
            return a(context, pPSAbilityDataContent.a(), z);
        }
        PromoteInfo af = contentRecord.af();
        if (B == 10) {
            return b(context, af, z);
        }
        if (B == 11) {
            return a(context, z);
        }
        if (af != null && af.getType() == 1) {
            return a(context, af, z);
        }
        AppInfo ae = contentRecord.ae();
        if (ae == null) {
            Resources resources = context.getResources();
            return z ? resources.getString(R.string._2130851038_res_0x7f0234de) : resources.getString(R.string._2130851034_res_0x7f0234da, context.getResources().getString(R.string._2130851107_res_0x7f023523));
        }
        if (!i.a(context, ae.getPackageName())) {
            return null;
        }
        if (TextUtils.isEmpty(ae.getAppName()) || !i.a(context, ae.getPackageName())) {
            Resources resources2 = context.getResources();
            return z ? resources2.getString(R.string._2130851038_res_0x7f0234de) : resources2.getString(R.string._2130851034_res_0x7f0234da, context.getResources().getString(R.string._2130851107_res_0x7f023523));
        }
        Resources resources3 = context.getResources();
        Object[] objArr = {ae.getAppName()};
        return z ? resources3.getString(R.string._2130851037_res_0x7f0234dd, objArr) : resources3.getString(R.string._2130851035_res_0x7f0234db, objArr);
    }

    private static String a(Context context, boolean z) {
        return context.getResources().getString(z ? R.string._2130851037_res_0x7f0234dd : R.string._2130851035_res_0x7f0234db, context.getResources().getString(R.string._2130851157_res_0x7f023555));
    }

    private static String a(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return z ? context.getResources().getString(R.string._2130851039_res_0x7f0234df) : context.getResources().getString(R.string._2130851035_res_0x7f0234db, context.getResources().getString(R.string._2130851097_res_0x7f023519, ""));
        }
        Resources resources = context.getResources();
        Object[] objArr = {str};
        return z ? resources.getString(R.string._2130851037_res_0x7f0234dd, objArr) : resources.getString(R.string._2130851035_res_0x7f0234db, objArr);
    }

    public static String a(Context context, ContentRecord contentRecord, int i) {
        if (context == null || contentRecord == null) {
            return null;
        }
        String b = b(context, contentRecord);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        Resources resources = context.getResources();
        Object[] objArr = {b};
        return i == 0 ? resources.getString(R.string._2130851171_res_0x7f023563, objArr) : resources.getString(R.string._2130851104_res_0x7f023520, objArr);
    }

    public static String a(Context context, ContentRecord contentRecord) {
        if (contentRecord != null && contentRecord.h() != null) {
            MetaData h = contentRecord.h();
            List<String> M = 2 == contentRecord.D() ? h.M() : (9 == contentRecord.D() || 12 == contentRecord.D()) ? h.L() : null;
            if (!bg.a(M)) {
                return az.b(context, M.get(0));
            }
        }
        return null;
    }

    private static String a(Context context, PromoteInfo promoteInfo, boolean z) {
        if (TextUtils.isEmpty(promoteInfo.getName())) {
            return z ? context.getResources().getString(R.string._2130851040_res_0x7f0234e0) : context.getResources().getString(R.string._2130851035_res_0x7f0234db, context.getResources().getString(R.string._2130851092_res_0x7f023514, ""));
        }
        Resources resources = context.getResources();
        Object[] objArr = {promoteInfo.getName()};
        return z ? resources.getString(R.string._2130851037_res_0x7f0234dd, objArr) : resources.getString(R.string._2130851035_res_0x7f0234db, objArr);
    }

    public static String a(Context context, int i) {
        int i2;
        String b = b(context, R.string._2130851033_res_0x7f0234d9);
        if (context == null) {
            return b;
        }
        if (i == 7 || i == 8) {
            i2 = R.string._2130851037_res_0x7f0234dd;
        } else {
            if (i != 12) {
                return b;
            }
            i2 = R.string._2130851035_res_0x7f0234db;
        }
        return b(context, i2);
    }

    public static Pair<String, String> a(Context context, String str, String str2) {
        CallResult a2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", str);
            jSONObject.put("cacheType", str2);
            a2 = mr.a(context).a(RTCMethods.QUERY_FILE_PATH, jSONObject.toString(), String.class);
        } catch (Throwable th) {
            ho.c("AdDataUtil", "getFilePathFromKit err: %s", th.getClass().getSimpleName());
        }
        if (200 == a2.getCode()) {
            ho.b("AdDataUtil", "getFilePathFromKit success");
            JSONObject jSONObject2 = new JSONObject((String) a2.getData());
            return new Pair<>(jSONObject2.optString("filePath"), jSONObject2.optString(MapKeyNames.CONTENT_DOWN_METHOD));
        }
        ho.b("AdDataUtil", "getFilePathFromKit fail");
        return null;
    }
}
