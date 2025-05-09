package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class mgp {
    public static String e(int i) {
        switch (i) {
            case 2017:
                return "/web/annualHtml/annualReport.html";
            case 2018:
                return "/web/annualHtml/annualReport2018.html";
            case 2019:
                return "/web/annualHtml/annualReport2019.html";
            case 2020:
                return CommonUtil.cc() ? "/sandbox/cch5/testappCCH5/annualReport2020/dist/index.html" : CommonUtil.as() ? "/sandbox/cch5/health/annualReport2020/dist/index.html" : "/cch5/health/annualReport2020/dist/index.html";
            default:
                LogUtil.h("AnnualYearConstant", "getAnnualUrl error year = ", Integer.valueOf(i));
                return "";
        }
    }
}
