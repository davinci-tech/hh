package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateUtils;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.dqn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class eeu {
    public static String b(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.add(12, -(calendar.get(12) % i));
        long timeInMillis = calendar.getTimeInMillis();
        calendar.add(12, i);
        return DateUtils.formatDateRange(BaseApplication.getContext(), timeInMillis, calendar.getTimeInMillis(), 129);
    }

    public static String d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        long timeInMillis = calendar.getTimeInMillis();
        calendar.add(11, 1);
        return DateUtils.formatDateRange(BaseApplication.getContext(), timeInMillis, calendar.getTimeInMillis(), 129);
    }

    public static long d(String str) {
        try {
            Date parse = new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(str);
            if (parse == null) {
                LogUtil.h("SectionChartUtils", "getTimestampByString date is null ", str);
                return System.currentTimeMillis();
            }
            return parse.getTime();
        } catch (ParseException unused) {
            LogUtil.h("SectionChartUtils", "getTimestampByString ParseException ", str);
            return System.currentTimeMillis();
        }
    }

    public static int c(int i, int i2) {
        ArrayList arrayList = new ArrayList(b());
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) != null) {
                int c = ((dqn) arrayList.get(size)).c();
                if (c == 1) {
                    if (c(i, i2, ((dqn) arrayList.get(size)).a())) {
                        return 1;
                    }
                } else if (c == 2) {
                    if (b(i, i2, ((dqn) arrayList.get(size)).a())) {
                        return 2;
                    }
                } else if (c != 3) {
                    continue;
                } else {
                    List<dqn.b> a2 = ((dqn) arrayList.get(size)).a();
                    if (koq.b(a2)) {
                        LogUtil.b("SectionChartUtils", "getBloodPressureType allHighGradeList null");
                        return 0;
                    }
                    for (int size2 = a2.size() - 1; size2 >= 0; size2--) {
                        if (a2.get(size2) != null) {
                            int e = a2.get(size2).e();
                            if (e == 2) {
                                if (koq.b(a2, size2)) {
                                    LogUtil.b("SectionChartUtils", "allHighGradeList OutOfBounds 2");
                                    return 0;
                                }
                                if (b(i, i2, a2, size2)) {
                                    return 5;
                                }
                            } else if (e == 1) {
                                if (koq.b(a2, size2)) {
                                    LogUtil.b("SectionChartUtils", "allHighGradeList OutOfBounds 1");
                                    return 0;
                                }
                                if (c(i, i2, a2, size2)) {
                                    return 4;
                                }
                            } else if (e == 0) {
                                if (koq.b(a2, size2)) {
                                    LogUtil.b("SectionChartUtils", "allHighGradeList OutOfBounds 0");
                                    return 0;
                                }
                                if (e(i, i2, a2, size2)) {
                                    return 3;
                                }
                            } else {
                                LogUtil.b("SectionChartUtils", "getBloodPressureType gradeIndex error");
                            }
                        }
                    }
                }
            }
        }
        LogUtil.c("SectionChartUtils", "bloodType,0");
        return 0;
    }

    public static String b(int i, List<Object> list) {
        int e = e(i);
        return (list == null || list.size() <= e || !(list.get(e) instanceof String)) ? "" : String.valueOf(list.get(e));
    }

    public static int e(int i) {
        if (i > 0 && i < 30) {
            return 0;
        }
        if (i >= 30 && i < 60) {
            return 1;
        }
        if (i >= 60 && i < 80) {
            return 2;
        }
        if (i >= 80 && i < 100) {
            return 3;
        }
        LogUtil.a("SectionChartUtils", "invalid stress value, please check.");
        return 0;
    }

    public static boolean c(int i, int i2, List<dqn.b> list) {
        if (koq.b(list)) {
            LogUtil.b("SectionChartUtils", "isIdeal idealGradeList null");
            return false;
        }
        dqn.b bVar = list.get(0);
        if (bVar == null) {
            LogUtil.b("SectionChartUtils", "isIdeal categorization null");
            return false;
        }
        List<Integer> a2 = bVar.a();
        List<Integer> b = bVar.b();
        if (a(a2)) {
            LogUtil.b("SectionChartUtils", "isIdeal sysList OutOfBounds");
            return false;
        }
        int intValue = a2.get(0).intValue();
        int intValue2 = a2.get(1).intValue();
        if (a(b)) {
            LogUtil.b("SectionChartUtils", "isIdeal diaList OutOfBounds");
            return false;
        }
        return i <= intValue2 && i >= intValue && i2 <= b.get(1).intValue() && i2 >= b.get(0).intValue();
    }

    public static boolean b(int i, int i2, List<dqn.b> list) {
        return d(i, i2, list, 0);
    }

    public static boolean e(int i, int i2, List<dqn.b> list, int i3) {
        return d(i, i2, list, i3);
    }

    public static boolean c(int i, int i2, List<dqn.b> list, int i3) {
        return d(i, i2, list, i3);
    }

    public static boolean b(int i, int i2, List<dqn.b> list, int i3) {
        if (koq.b(list)) {
            LogUtil.b("SectionChartUtils", "isHigh allHighGradeList null");
            return false;
        }
        dqn.b bVar = list.get(i3);
        if (bVar == null) {
            LogUtil.b("SectionChartUtils", "isHigh categorization null");
            return false;
        }
        List<Integer> a2 = bVar.a();
        List<Integer> b = bVar.b();
        if (koq.b(a2, 0)) {
            LogUtil.b("SectionChartUtils", "isHigh sysList OutOfBounds");
            return false;
        }
        int intValue = a2.get(0).intValue();
        if (!koq.b(b, 0)) {
            return i >= intValue || i2 >= b.get(0).intValue();
        }
        LogUtil.b("SectionChartUtils", "isHigh diaList OutOfBounds");
        return false;
    }

    public static ArrayList<dqn> b() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            if (Utils.o()) {
                LogUtil.c("SectionChartUtils", "getInternationalGradeList");
                return dqu.b().g();
            }
            LogUtil.c("SectionChartUtils", "getDefaultGradeList");
            return dqu.b().e();
        }
        LogUtil.c("SectionChartUtils", "isNetworkConnected");
        if (koq.b(dqu.b().f())) {
            LogUtil.c("SectionChartUtils", "getGradeList is null ");
            if (Utils.o()) {
                LogUtil.c("SectionChartUtils", "getGradeList is null return getInternationalGradeList");
                return dqu.b().g();
            }
            LogUtil.c("SectionChartUtils", "getGradeList is null return getDefaultGradeList");
            return dqu.b().e();
        }
        LogUtil.c("SectionChartUtils", " return getGradeList");
        return dqu.b().f();
    }

    public static boolean a(List<Integer> list) {
        return koq.b(list, 0) || koq.b(list, 1);
    }

    public static boolean d(int i, int i2, List<dqn.b> list, int i3) {
        if (koq.b(list)) {
            LogUtil.b("SectionChartUtils", "bloodList null");
            return false;
        }
        dqn.b bVar = list.get(i3);
        if (bVar == null) {
            LogUtil.b("SectionChartUtils", "categorization null");
            return false;
        }
        List<Integer> a2 = bVar.a();
        List<Integer> b = bVar.b();
        if (a(a2)) {
            LogUtil.b("SectionChartUtils", " sysList OutOfBounds");
            return false;
        }
        int intValue = a2.get(0).intValue();
        int intValue2 = a2.get(1).intValue();
        if (a(b)) {
            LogUtil.b("SectionChartUtils", " diaList OutOfBounds");
            return false;
        }
        int intValue3 = b.get(0).intValue();
        int intValue4 = b.get(1).intValue();
        boolean z = i <= intValue2 && i >= intValue;
        boolean z2 = i2 <= intValue4 && i2 >= intValue3;
        if (bVar.c() == 0) {
            if (!z || !z2) {
                return false;
            }
        } else if (!z && !z2) {
            return false;
        }
        return true;
    }

    public static int[] a() {
        int[] iArr = {0, 1, 2, 3, 4, 5};
        ArrayList<dqn> b = b();
        if (koq.b(b)) {
            LogUtil.a("SectionChartUtils", "getBloodPressureGradeTypeList: list is empty");
            return iArr;
        }
        ArrayList arrayList = new ArrayList(b);
        if (koq.d(arrayList, 3)) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                dqn dqnVar = (dqn) arrayList.get(size);
                if (dqnVar != null && dqnVar.c() == 3) {
                    List<dqn.b> a2 = dqnVar.a();
                    if (koq.b(a2)) {
                        LogUtil.b("SectionChartUtils", "getBloodPressureGradeTypeList, gradeList is empty");
                        return iArr;
                    }
                    int size2 = a2.size();
                    LogUtil.a("SectionChartUtils", "getBloodPressureGradeTypeList, count: ", Integer.valueOf(size2));
                    if (size2 == 1) {
                        return new int[]{0, 1, 2, 3};
                    }
                    if (size2 == 2) {
                        return new int[]{0, 1, 2, 3, 4};
                    }
                    LogUtil.a("SectionChartUtils", "getBloodPressureGradeTypeList, default list");
                }
            }
        }
        return iArr;
    }

    public static int c() {
        int[] a2 = a();
        if (a2 == null || a2.length == 0) {
            return 3;
        }
        return a2.length;
    }

    public static String b(int i, int i2) {
        return c(c(i, i2));
    }

    public static String c(int i) {
        String[] e = e();
        if (e == null || e.length <= i) {
            LogUtil.h("SectionChartUtils", "getGradeDes，type is out of bound");
            return "";
        }
        return e[i];
    }

    public static String[] e() {
        String[] strArr;
        ArrayList<dqn> arrayList = new ArrayList(b());
        LogUtil.a("SectionChartUtils", "featureInfoList,", Integer.valueOf(arrayList.size()));
        ArrayList arrayList2 = new ArrayList();
        for (dqn dqnVar : arrayList) {
            if (dqnVar == null) {
                LogUtil.h("SectionChartUtils", "featureInfo is null");
            } else {
                List<dqn.b> a2 = dqnVar.a();
                if (dqnVar.c() != 0 && !koq.b(a2)) {
                    arrayList2.addAll(a2);
                }
            }
        }
        int size = arrayList2.size();
        Resources resources = BaseApplication.getContext().getResources();
        String[] strArr2 = {resources.getString(R$string.IDS_bloodpressure_degree_low), resources.getString(R$string.IDS_hw_normal_overseas), resources.getString(R$string.IDS_hw_normal_high_value_overseas), resources.getString(R$string.IDS_hw_high_overseas)};
        if (size > 3) {
            if (!Utils.o()) {
                strArr = (String[]) Arrays.copyOf(strArr2, 6);
                strArr[0] = resources.getString(R$string.IDS_bloodpressure_degree_low);
                strArr[1] = resources.getString(R$string.IDS_hw_normal);
                strArr[2] = resources.getString(R$string.IDS_hw_normal_high_value);
                strArr[3] = resources.getString(R$string.IDS_hw_mild);
                strArr[4] = resources.getString(R$string.IDS_hw_moderate);
                strArr[5] = resources.getString(R$string.IDS_hw_high);
            } else if (c() == 5) {
                strArr = (String[]) Arrays.copyOf(strArr2, 5);
                if (Utils.a(BaseApplication.getContext())) {
                    strArr[1] = resources.getString(R$string.IDS_hw_normal_level_value_jp);
                    strArr[2] = resources.getString(R$string.IDS_hw_normal_high_level_value_jp);
                    strArr[3] = resources.getString(R$string.IDS_hw_high_level_value_jp);
                    strArr[4] = resources.getString(R$string.IDS_hw_high_value_jp);
                } else {
                    strArr[3] = resources.getString(R$string.IDS_hw_high_level_1);
                    strArr[4] = resources.getString(R$string.IDS_hw_high_level_2);
                }
            } else if (c() == 6) {
                strArr = (String[]) Arrays.copyOf(strArr2, 6);
                strArr[3] = resources.getString(R$string.IDS_hw_high_level_1);
                strArr[4] = resources.getString(R$string.IDS_hw_high_level_2);
                strArr[5] = resources.getString(R$string.IDS_hw_high_level_3);
            } else {
                LogUtil.b("SectionChartUtils", "getBloodPressureGradeDesList: categorizationListSize: ", Integer.valueOf(size));
                b(strArr2);
            }
            strArr2 = strArr;
            b(strArr2);
        }
        return strArr2;
    }

    public static int d(int i) {
        int c = c();
        Context context = BaseApplication.getContext();
        if (i == 0) {
            return ContextCompat.getColor(context, R.color._2131296523_res_0x7f09010b);
        }
        if (i == 1) {
            return ContextCompat.getColor(context, R.color._2131296521_res_0x7f090109);
        }
        if (i == 2) {
            return ContextCompat.getColor(context, R.color._2131296530_res_0x7f090112);
        }
        if (i == 3) {
            if (c == 6) {
                return ContextCompat.getColor(context, R.color._2131296526_res_0x7f09010e);
            }
            if (c == 5) {
                return ContextCompat.getColor(context, R.color._2131296528_res_0x7f090110);
            }
            return ContextCompat.getColor(context, R.color._2131296519_res_0x7f090107);
        }
        if (i != 4) {
            if (i == 5) {
                return ContextCompat.getColor(context, R.color._2131296519_res_0x7f090107);
            }
            return ContextCompat.getColor(context, R.color.emui_accent);
        }
        if (c == 6) {
            return ContextCompat.getColor(context, R.color._2131296528_res_0x7f090110);
        }
        return ContextCompat.getColor(context, R.color._2131296519_res_0x7f090107);
    }

    private static void b(String[] strArr) {
        if ((!LanguageUtil.aw(BaseApplication.getContext()) && !LanguageUtil.s(BaseApplication.getContext())) || strArr == null || strArr.length == 0) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (!TextUtils.isEmpty(str) && str.contains(Constants.LEFT_BRACKET_ONLY)) {
                StringBuilder sb = new StringBuilder(str);
                strArr[i] = sb.insert(sb.indexOf(Constants.LEFT_BRACKET_ONLY), "\n").toString();
            }
        }
    }
}
