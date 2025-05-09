package com.huawei.hms.framework.network.grs.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes4.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4538a = "e";
    public static final Set<String> b = Collections.unmodifiableSet(new a(16));

    public static String b(Context context, com.huawei.hms.framework.network.grs.e.a aVar, String str, GrsBaseInfo grsBaseInfo, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Logger.w(f4538a, "routeBy must be not empty string or null.");
            return null;
        }
        if (!"no_route".equals(str) && !"unconditional".equals(str)) {
            return a(context, aVar, str, grsBaseInfo, z);
        }
        Logger.v(f4538a, "routeBy equals NO_ROUTE_POLICY");
        return "no_route_country";
    }

    class a extends HashSet<String> {
        a(int i) {
            super(i);
            add("ser_country");
            add("reg_country");
            add("issue_country");
            add("geo_ip");
        }
    }

    private static String a(Context context, com.huawei.hms.framework.network.grs.e.a aVar, String str, GrsBaseInfo grsBaseInfo, boolean z) {
        String str2;
        StringBuilder sb;
        String serCountry = grsBaseInfo.getSerCountry();
        String regCountry = grsBaseInfo.getRegCountry();
        String issueCountry = grsBaseInfo.getIssueCountry();
        for (String str3 : str.split(HiDataFilter.DataFilterExpression.BIGGER_THAN)) {
            if (b.contains(str3.trim())) {
                if (!"ser_country".equals(str3.trim()) || TextUtils.isEmpty(serCountry) || "UNKNOWN".equals(serCountry)) {
                    if ("reg_country".equals(str3.trim()) && !TextUtils.isEmpty(regCountry) && !"UNKNOWN".equals(regCountry)) {
                        Logger.i(f4538a, "current route_by is regCountry and routerCountry is: " + regCountry);
                        return regCountry;
                    }
                    if ("issue_country".equals(str3.trim()) && !TextUtils.isEmpty(issueCountry) && !"UNKNOWN".equals(issueCountry)) {
                        Logger.i(f4538a, "current route_by is issueCountry and routerCountry is: " + issueCountry);
                        return issueCountry;
                    }
                    if ("geo_ip".equals(str3.trim())) {
                        serCountry = new com.huawei.hms.framework.network.grs.g.b(context, aVar, grsBaseInfo).a(z);
                        str2 = f4538a;
                        sb = new StringBuilder("current route_by is geo_ip and routerCountry is: ");
                    }
                } else {
                    str2 = f4538a;
                    sb = new StringBuilder("current route_by is serCountry and routerCountry is: ");
                }
                sb.append(serCountry);
                Logger.i(str2, sb.toString());
                return serCountry;
            }
        }
        return "";
    }
}
