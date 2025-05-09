package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class stn {
    public static boolean a(std stdVar, String str) {
        if (str.equals("over_sea_vip_card") && !b(stdVar)) {
            stq.b("QuerySupportedCard", "isSupportMembershipCard area not supported");
            return false;
        }
        stq.b("QuerySupportedCard", "updateIssuerControlCache");
        if (!c()) {
            stq.b("QuerySupportedCard", "updateIssuerControlCache local cache valid");
            return a(d(), str);
        }
        if (!svx.d()) {
            return a(d(), str);
        }
        sut c = c(stdVar);
        swj swjVar = new swj(ssz.e(), d(stdVar));
        c.b(true);
        suh processTask = swjVar.processTask(c);
        stq.b("QuerySupportedCard", "updateIssuerControlCache query server returnCode=" + processTask.g);
        if (processTask.g != 0) {
            return false;
        }
        c(processTask.e());
        return a(processTask.e(), str);
    }

    public static void e() {
        suj b = suj.b(ssz.e());
        String str = new String[]{"VirtualCard"}[0];
        if (b.a("NEW_ISSUER_CTRL_SUB_SDK" + str)) {
            b.e("NEW_ISSUER_CTRL_SUB_SDK" + str);
        }
        if (b.a("NEW_ISSUER_CTRL_QUERY_TIME_SUB_SDK" + str)) {
            b.e("NEW_ISSUER_CTRL_QUERY_TIME_SUB_SDK" + str);
        }
    }

    private static boolean a(String str, String str2) {
        if (str2.equals("over_sea_vip_card")) {
            return b(str);
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str3 : str.split("\\|")) {
            if (!TextUtils.isEmpty(str3) && str3.contains("#")) {
                String[] split = str3.split("#");
                if (str2.equals(split[0])) {
                    boolean equals = String.valueOf(0).equals(split[1]);
                    stq.b("QuerySupportedCard", "isSupportCard result=" + equals + ", issuerId=" + str2 + ", flag=" + split[1]);
                    return equals;
                }
            }
        }
        return false;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        for (String str2 : str.split("\\|")) {
            if (!TextUtils.isEmpty(str2) && str2.contains("#")) {
                String[] split = str2.split("#");
                if ("over_sea_vip_card".equals(split[0])) {
                    stq.b("QuerySupportedCard", "checkOverseaVIPIssuerAndFlag flag=" + split[1]);
                    return !String.valueOf(0).equals(split[1]);
                }
            }
        }
        return true;
    }

    private static boolean c(long j, long j2) {
        return Math.abs(j2 - j) >= 3600000;
    }

    private static sut c(std stdVar) {
        sut sutVar = new sut();
        sutVar.f(stdVar.a());
        sutVar.h(stdVar.i());
        sutVar.g(stdVar.f());
        sutVar.d(stdVar.n());
        sutVar.b("1");
        sutVar.j("1");
        sutVar.e(stdVar.d());
        sutVar.c(stdVar.c());
        return sutVar;
    }

    private static boolean c() {
        if (c(suj.b(ssz.e()).d("NEW_ISSUER_CTRL_QUERY_TIME_SUB_SDKVirtualCard", (Long) 0L).longValue(), System.currentTimeMillis())) {
            return true;
        }
        if (TextUtils.isEmpty(suj.b(ssz.e()).e("NEW_ISSUER_CTRL_SUB_SDKVirtualCard", ""))) {
            stq.b("QuerySupportedCard", "isNeedQueryServer cache invalid serverModule");
            return true;
        }
        return TextUtils.isEmpty(d());
    }

    private static String d(std stdVar) {
        return svg.d(ssz.e(), "VIRTUALCARD", stdVar.d());
    }

    private static void c(String str) {
        stq.b("QuerySupportedCard", "saveCache issuerAndFlag=" + str);
        suj.b(ssz.e()).a("NEW_ISSUER_CTRL_SUB_SDKVirtualCard", str);
        suj.b(ssz.e()).a("NEW_ISSUER_CTRL_QUERY_TIME_SUB_SDKVirtualCard", Long.valueOf(System.currentTimeMillis()));
    }

    private static String d() {
        return suj.b(ssz.e()).e("NEW_ISSUER_CTRL_SUB_SDKVirtualCard", "");
    }

    private static boolean b(std stdVar) {
        return !"CN".equals(stdVar.d());
    }
}
