package defpackage;

import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class qqw {
    public static String b(cfe cfeVar) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_BodyCompositionSuggestUtils", "initBodyComposition WeightBean is null");
            return "";
        }
        LogUtil.a("HealthWeight_BodyCompositionSuggestUtils", "initBodyComposition Pole is ", Integer.valueOf(cfeVar.aa()));
        if (cfeVar.aa() == 2) {
            LogUtil.a("HealthWeight_BodyCompositionSuggestUtils", "initBodyComposition Age is ", Integer.valueOf(cfeVar.e()));
            if (doj.d(cfeVar.e())) {
                return a(cfeVar, BaseApplication.getContext().getResources().getStringArray(R.array._2130968721_res_0x7f040091));
            }
            return d(cfeVar, BaseApplication.getContext().getResources().getStringArray(R.array._2130968720_res_0x7f040090));
        }
        return b(cfeVar, BaseApplication.getContext().getResources().getStringArray(R.array._2130968722_res_0x7f040092));
    }

    private static String b(cfe cfeVar, String[] strArr) {
        int doubleOrIntLevelByType = (int) cfeVar.getDoubleOrIntLevelByType(1);
        int doubleOrIntLevelByType2 = (int) cfeVar.getDoubleOrIntLevelByType(10);
        int doubleOrIntLevelByType3 = (int) cfeVar.getDoubleOrIntLevelByType(5);
        int b = doj.b(cfeVar.j(), Utils.o(), cfeVar.an(), cfeVar.e());
        String str = "IDS_weight_assess_" + b + doubleOrIntLevelByType + doubleOrIntLevelByType3 + doubleOrIntLevelByType2;
        LogUtil.a("HealthWeight_BodyCompositionSuggestUtils", "initComprehensiveAdviceId xmlId is ", Integer.valueOf(b), Integer.valueOf(doubleOrIntLevelByType), Integer.valueOf(doubleOrIntLevelByType3), Integer.valueOf(doubleOrIntLevelByType2));
        return a(str, strArr);
    }

    private static String d(cfe cfeVar, String[] strArr) {
        int doubleOrIntLevelByType = (int) cfeVar.getDoubleOrIntLevelByType(1);
        int doubleOrIntLevelByType2 = (int) cfeVar.getDoubleOrIntLevelByType(10);
        int doubleOrIntLevelByType3 = (int) cfeVar.getDoubleOrIntLevelByType(5);
        byte an = cfeVar.an();
        int e = cfeVar.e();
        int e2 = doj.e(an, cfeVar.af(), e);
        int b = doj.b(cfeVar.j(), Utils.o(), an, e);
        int d = doj.d(an, cfeVar.f());
        int ac = (int) cfeVar.ac();
        int i = ac == 0 ? ac : 1;
        String str = "IDS_weight_assess_" + doubleOrIntLevelByType + doubleOrIntLevelByType2 + doubleOrIntLevelByType3 + e2 + b + d + i;
        LogUtil.a("HealthWeight_BodyCompositionSuggestUtils", "initEightPoleComprehensiveAdviceId xmlId is ", Integer.valueOf(doubleOrIntLevelByType), Integer.valueOf(doubleOrIntLevelByType2), Integer.valueOf(doubleOrIntLevelByType3), Integer.valueOf(e2), Integer.valueOf(b), Integer.valueOf(d), Integer.valueOf(i));
        return a(str, strArr);
    }

    private static String a(cfe cfeVar, String[] strArr) {
        byte an = cfeVar.an();
        int doubleOrIntLevelByType = (int) cfeVar.getDoubleOrIntLevelByType(1);
        int doubleOrIntLevelByType2 = (int) cfeVar.getDoubleOrIntLevelByType(10);
        int doubleOrIntLevelByType3 = (int) cfeVar.getDoubleOrIntLevelByType(5);
        int e = doj.e(an, cfeVar.af(), cfeVar.e());
        int b = doj.b(cfeVar.j(), Utils.o(), cfeVar.an(), cfeVar.e());
        int ac = (int) cfeVar.ac();
        return a("IDS_weight_assess_" + doubleOrIntLevelByType + doubleOrIntLevelByType2 + doubleOrIntLevelByType3 + e + b + (ac == 0 ? ac : 1), strArr);
    }

    private static String a(String str, String[] strArr) {
        try {
            String d = d(str, strArr);
            LogUtil.a("HealthWeight_BodyCompositionSuggestUtils", "arrayId is ", d);
            return BaseApplication.getContext().getResources().getString(dcx.e(d));
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthWeight_BodyCompositionSuggestUtils", "resource NotFoundException");
            return "";
        }
    }

    private static String d(String str, String[] strArr) {
        String str2;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = "";
                break;
            }
            str2 = strArr[i];
            if (str2.startsWith(str)) {
                break;
            }
            i++;
        }
        String[] split = str2.split("\\|");
        if (koq.b(split, 1)) {
            LogUtil.h("HealthWeight_BodyCompositionSuggestUtils", "queryXmlId temps is Array Index Out Of Bound");
            return "";
        }
        return split[1];
    }
}
