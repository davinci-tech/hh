package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class scx {
    private static Map<String, String> d = new HashMap<String, String>() { // from class: scx.5
        private static final long serialVersionUID = -8160320276311542531L;

        {
            put("EXCE_SCORE__ERROR", HiAnalyticsConstant.KeyAndValue.NUMBER_01);
            put("EXCE_LIGHTSLEEP__MIN_ERROR", a.t);
            put("EXCE_LIGHTSLEEP__MIX _ERROR", "03");
            put("EXCE_DEEPSLEEP__MIN_ERROR", "04");
            put("EXCE_DEEPSLEEP__MIX _ERROR", "05");
            put("EXCE_REMLEEP__MIN_ERROR", "06");
            put("EXCE_REMSLEEP__MIX _ERROR", "07");
            put("EXCE_BREATH__SCORE_ERROR", "08");
            put("EXCE_TRUSLEEP__TIME_ERROR", "09");
            put("EXCE_NORMALSLEEP__TIME_ERROR", "10");
        }
    };

    public static void e(Context context, String str, String str2) {
        if (CommonUtil.bv() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (d(new SimpleDateFormat("yyyy-MM-dd").format(jec.e()), str, "yyyy-MM-dd") + 1 > 7) {
            LogUtil.a("UIHLH_FitnessUpgUtils", "the time is > 7 days");
            return;
        }
        ArrayList<String> d2 = d(str2);
        String str3 = d.get(str2);
        if (koq.c(d2)) {
            if (c(d2, str)) {
                return;
            }
            d2.add(str);
            Collections.sort(d2);
            if (d2.size() > 7) {
                d2.remove(0);
            }
            jsd.b(context, "031302", str2, str3, str);
            c(context, str2, d2);
            return;
        }
        jsd.b(context, "031302", str2, str3, str);
        d2.add(str);
        c(context, str2, d2);
    }

    private static ArrayList<String> d(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        String b = sdk.c().b(str);
        if (TextUtils.isEmpty(b)) {
            return arrayList;
        }
        try {
            return (ArrayList) new Gson().fromJson(b, new TypeToken<ArrayList<String>>() { // from class: scx.2
            }.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.a("UIHLH_FitnessUpgUtils", "JsonSyntaxException ", e.getMessage());
            return arrayList;
        }
    }

    private static boolean c(ArrayList<String> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            String str2 = arrayList.get(i);
            if (str2 != null && str2.equals(str)) {
                LogUtil.a("UIHLH_FitnessUpgUtils", "the time already has been write down");
                return true;
            }
        }
        return false;
    }

    private static void c(Context context, String str, ArrayList<String> arrayList) {
        DeviceInfo a2 = jpt.a("UIHLH_FitnessUpgUtils");
        if (a2 == null) {
            a2 = jpu.d("UIHLH_FitnessUpgUtils");
        }
        if (a2 == null || !koq.c(arrayList)) {
            return;
        }
        try {
            sdk.c().c(new Gson().toJson(arrayList), str);
        } catch (JsonIOException unused) {
            LogUtil.b("UIHLH_FitnessUpgUtils", "setSleepUpgInformation JsonIOException");
        }
    }

    private static int d(String str, String str2, String str3) {
        Date e = ggl.e(str, str3);
        Date e2 = ggl.e(str2, str3);
        if (e2 == null || e == null) {
            return 0;
        }
        return Math.abs(gib.i(e.getTime()) - gib.i(e2.getTime()));
    }
}
