package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class nce {

    /* renamed from: a, reason: collision with root package name */
    private static List<nch> f15244a;
    private static List<nca> d;

    public static boolean d() {
        File file = new File(ncf.c("simInfo_mapping.xml"));
        File file2 = new File(ncf.c("esim_open_method.xml"));
        if (!file.exists() || !file2.exists()) {
            return false;
        }
        LogUtil.a("SimMatchRuleUtils", "simInfoFile and openMethodFile exists");
        return true;
    }

    public static nca e(Map<String, Object> map) {
        if (map == null) {
            LogUtil.h("SimMatchRuleUtils", "getEsimOpenMethod simCardInfo is null");
            return null;
        }
        String c = c(map);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("SimMatchRuleUtils", "getEsimOpenMethod operatorKey is empty");
            return null;
        }
        List<nca> b = b();
        if (b == null || b.size() <= 0) {
            return null;
        }
        for (nca ncaVar : b) {
            if (ncaVar.e().equals(c)) {
                return ncaVar;
            }
        }
        return null;
    }

    private static String c(Map<String, Object> map) {
        List<nch> a2 = a();
        String str = "";
        if (a2 == null || a2.size() == 0) {
            LogUtil.h("SimMatchRuleUtils", "getRuleOperatorKey simInfoList is null or empty");
            return "";
        }
        String d2 = b(map).d();
        for (int i = 0; i < a2.size(); i++) {
            nch nchVar = a2.get(i);
            if (a(nchVar.d(), d2)) {
                if (nchVar.b() == 1) {
                    String c = nchVar.c();
                    LogUtil.a("SimMatchRuleUtils", "getRuleOperatorKey is matched operatorKey = ", c);
                    return c;
                }
                if (TextUtils.isEmpty(str)) {
                    str = nchVar.c();
                    LogUtil.a("SimMatchRuleUtils", "getRuleOperatorKey is matched first operatorKey = ", str);
                }
            }
        }
        return str;
    }

    private static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("SimMatchRuleUtils", "isEqualsMccMnc ruleMccMnc or cardMccMnc is empty");
            return false;
        }
        if (str.length() > 3 && str2.length() > 3) {
            try {
                int parseInt = Integer.parseInt(str.substring(0, 3));
                int parseInt2 = Integer.parseInt(str.substring(3));
                int parseInt3 = Integer.parseInt(str2.substring(0, 3));
                int parseInt4 = Integer.parseInt(str2.substring(3));
                if (parseInt == parseInt3 && parseInt2 == parseInt4) {
                    return true;
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("SimMatchRuleUtils", "isEqualsMccMnc NumberFormatException");
            }
        }
        return false;
    }

    private static nch b(Map<String, Object> map) {
        nch nchVar = new nch();
        Object obj = map.get("mccmnc");
        nchVar.a(obj instanceof String ? (String) obj : "");
        return nchVar;
    }

    public static List<nca> b() {
        return d;
    }

    public static void c(List<nca> list) {
        d = list;
    }

    public static List<nch> a() {
        return f15244a;
    }

    public static void e(List<nch> list) {
        f15244a = list;
    }
}
