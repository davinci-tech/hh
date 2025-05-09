package com.huawei.ui.main.stories.health.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.ceo;
import defpackage.cpm;
import defpackage.dqn;
import defpackage.dqo;
import defpackage.dqq;
import defpackage.dqu;
import defpackage.drl;
import defpackage.eeu;
import defpackage.jdr;
import defpackage.jfv;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qhm;
import defpackage.qif;
import defpackage.qkg;
import defpackage.qkv;
import defpackage.rrb;
import defpackage.rrf;
import defpackage.skh;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class BloodPressureUtil {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f10257a = false;
    private static float d = 16.67f;

    /* loaded from: classes9.dex */
    public interface IIconCallBack {
        void onReceive(HashMap<Integer, Integer> hashMap);
    }

    public static int a(int i) {
        if ((i & (-128)) != 0) {
            return i;
        }
        if (i == 1) {
            return 128;
        }
        if (i == 2) {
            return 256;
        }
        if (i == 3) {
            return 512;
        }
        if (i == 4) {
            return 1024;
        }
        if (i == 5) {
            return 2048;
        }
        if (i == 7) {
            return 8192;
        }
        if (i != 8) {
            return i != 100 ? 4096 : 1073741824;
        }
        return 16384;
    }

    public static int b(int i) {
        if (i < 20) {
            return 0;
        }
        return i > 60 ? 5 : 1;
    }

    public static ArrayList<dqn> a() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            f10257a = true;
            if (Utils.o()) {
                LogUtil.c("BloodPressureUtil", "getInternationalGradeList");
                return dqu.b().g();
            }
            LogUtil.c("BloodPressureUtil", "getDefaultGradeList");
            return dqu.b().e();
        }
        LogUtil.c("BloodPressureUtil", "isNetworkConnected");
        if (koq.b(dqu.b().f())) {
            LogUtil.c("BloodPressureUtil", "getGradeList is null ");
            f10257a = true;
            if (Utils.o()) {
                LogUtil.c("BloodPressureUtil", "getGradeList is null return getInternationalGradeList");
                return dqu.b().g();
            }
            LogUtil.c("BloodPressureUtil", "getGradeList is null return getDefaultGradeList");
            return dqu.b().e();
        }
        f10257a = false;
        LogUtil.c("BloodPressureUtil", " return getGradeList");
        return dqu.b().f();
    }

    public static String c(int i) {
        int c;
        String string;
        String string2;
        Context context = BaseApplication.getContext();
        SpannableString spannableString = new SpannableString(context.getString(R$string.IDS_hw_pressure_learn_more));
        if (a() == null) {
            return !Utils.o() ? d(context.getString(R$string.IDS_blood_pressure_grading_description, 2018, spannableString)) : d(context.getString(R$string.IDS_blood_pressure_grading_description_International, 2018, spannableString));
        }
        if (f10257a) {
            c = Utils.o() ? 1 : 0;
        } else {
            c = dqu.b().c();
        }
        LogUtil.c("BloodPressureUtil", "CountryType," + c);
        switch (c) {
            case 0:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_source, 2024, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_guard_description_source, 2024);
                    break;
                }
            case 1:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_International, 2018, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_ESH, UnitUtil.a(2018.0d, 1, 0, false));
                    break;
                }
            case 2:
                LogUtil.b("BloodPressureUtil", "2018 European Standard");
                string = "";
                break;
            case 3:
                LogUtil.b("BloodPressureUtil", "2017 American Standard");
                string = "";
                break;
            case 4:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_peru, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_PE);
                    break;
                }
            case 5:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_philippines, 2020, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_PH, UnitUtil.a(2020.0d, 1, 0, false));
                    break;
                }
            case 6:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_saudiarabia, 2018, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_SA, UnitUtil.a(2018.0d, 1, 0, false));
                    break;
                }
            case 7:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_chile, 2010, 15, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_CL, UnitUtil.a(2010.0d, 1, 0, false), UnitUtil.e(15.0d, 1, 0));
                    break;
                }
            case 8:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_uae, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_AE);
                    break;
                }
            case 9:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_Kuwait, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_KW);
                    break;
                }
            case 10:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_bahrain, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_BH);
                    break;
                }
            case 11:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_qatar, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_QA);
                    break;
                }
            case 12:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_oman, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_OM);
                    break;
                }
            case 13:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_newzealand, 2016, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_NZ, UnitUtil.a(2016.0d, 1, 0, false));
                    break;
                }
            case 14:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_thailand, 2020, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_TH, UnitUtil.a(2020.0d, 1, 0, false));
                    break;
                }
            case 15:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_colombia, 2018, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_CO, UnitUtil.a(2018.0d, 1, 0, false));
                    break;
                }
            case 16:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_southafrica, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_ZA);
                    break;
                }
            case 17:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_mexicano, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_MX);
                    break;
                }
            case 18:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_australia, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_AU);
                    break;
                }
            case 19:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_Jordan, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_AR_KW);
                    break;
                }
            case 20:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_mauritius, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_MU);
                    break;
                }
            case 21:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_cambodia, spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_KH);
                    break;
                }
            case 22:
                if (i == 0) {
                    string2 = context.getString(R$string.IDS_blood_pressure_grading_description_general, spannableString);
                } else {
                    string2 = context.getString(R$string.IDS_hw_hypotension_des_source_general);
                }
                string = c(string2);
                break;
            case 23:
            default:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_description_International, 2018, spannableString);
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_ESH, UnitUtil.a(2018.0d, 1, 0, false));
                }
                LogUtil.b("BloodPressureUtil", "The CountryType description is invalidation");
                break;
            case 24:
                if (i == 0) {
                    string = context.getString(R$string.IDS_blood_pressure_grading_des_japan, 2019, UnitUtil.e(2.0d, 1, 0), UnitUtil.e(5.0d, 1, 0), spannableString);
                    break;
                } else {
                    string = context.getString(R$string.IDS_hw_hypotension_des_source_JP, 2019);
                    break;
                }
        }
        return d(string);
    }

    private static String c(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace("《", "").replace("》", "");
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("BloodPressureUtil", "description is invalid");
            return "";
        }
        int[] iArr = {2010, 2016, 2018, 2019, 2020};
        String str2 = str;
        for (int i = 0; i < 5; i++) {
            int i2 = iArr[i];
            String valueOf = String.valueOf(i2);
            if (str.contains(valueOf)) {
                str2 = str.replace(valueOf, skh.b(i2));
            }
        }
        return str2;
    }

    public static Drawable dHx_(Context context, int i, int i2) {
        if (context == null) {
            LogUtil.h("BloodPressureUtil", "context is null");
            return null;
        }
        if (i2 == 1) {
            return ContextCompat.getDrawable(context, R.drawable._2131428079_res_0x7f0b02ef);
        }
        if (i2 == 2) {
            return ContextCompat.getDrawable(context, R.drawable._2131428083_res_0x7f0b02f3);
        }
        if (i2 == 3) {
            if (i == 6) {
                return ContextCompat.getDrawable(context, R.drawable._2131428081_res_0x7f0b02f1);
            }
            if (i == 5) {
                return ContextCompat.getDrawable(context, R.drawable._2131428082_res_0x7f0b02f2);
            }
            return ContextCompat.getDrawable(context, R.drawable._2131428078_res_0x7f0b02ee);
        }
        if (i2 != 4) {
            if (i2 == 5) {
                return ContextCompat.getDrawable(context, R.drawable._2131428078_res_0x7f0b02ee);
            }
            return ContextCompat.getDrawable(context, R.drawable._2131428080_res_0x7f0b02f0);
        }
        if (i == 6) {
            return ContextCompat.getDrawable(context, R.drawable._2131428082_res_0x7f0b02f2);
        }
        return ContextCompat.getDrawable(context, R.drawable._2131428078_res_0x7f0b02ee);
    }

    public static long e(long j) {
        return b(j) + 150000;
    }

    public static long b(long j) {
        return (((j / 1000) / 60) / 5) * 300000;
    }

    public static long a(long j) {
        return b(j) + 300000;
    }

    public static String a(double d2) {
        return UnitUtil.e(d2, 1, 0);
    }

    public static void c(SectionBean sectionBean, qhm qhmVar) {
        if (koq.b(qhmVar.c())) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(qhmVar);
        }
    }

    public static List<qkv> b() {
        Context context = BaseApplication.getContext();
        ArrayList arrayList = new ArrayList(14);
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_sports_housework), true, 32768, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_body_activity), true, 128, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_eating), true, 512, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_no_medication_taken), true, 131072, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_mood_swings), true, 65536, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_lack_of_sleep), true, 262144, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_smoke), true, 256, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_drink), h() && (LanguageUtil.h(context) || LanguageUtil.p(context)), 1024, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_coffee), true, 2048, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_temperature_drop), true, 524288, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_other), true, 1048576, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_measure_no_action), false, 4096, true));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_pressure_get_up), false, 8192, false));
        arrayList.add(new qkv(context.getString(R$string.IDS_blood_pressure_before_bedtime), false, 16384, false));
        return arrayList;
    }

    public static List<String> c(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(30002), "BLOOD_PRESSURE_CUSTOM_ACTION_LIST");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(b)) {
            jdr.d(b, context.getClassLoader(), "1", arrayList);
        }
        return arrayList;
    }

    public static void d(Context context, List<String> list) {
        if (context == null || list == null) {
            return;
        }
        SharedPreferenceManager.e(context, Integer.toString(30002), "BLOOD_PRESSURE_CUSTOM_ACTION_LIST", jdr.b(list, "1"), new StorageParams(0));
    }

    public static void d(int i, List<qkv> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            qkv qkvVar = list.get(i2);
            if ((qkvVar.c() & i) == qkvVar.c()) {
                qkvVar.c(true);
                qkvVar.e(true);
                if (qkvVar.e()) {
                    qkvVar.e(false);
                }
                Context context = BaseApplication.getContext();
                if (!LanguageUtil.h(context) && !LanguageUtil.p(context) && qkvVar.c() == 1024) {
                    qkvVar.e(false);
                }
            }
        }
    }

    public static int a(List<qkv> list) {
        if (list == null) {
            LogUtil.a("BloodPressureUtil", "actionList is null");
            return 0;
        }
        LogUtil.a("BloodPressureUtil", "getBeforeActiveIndex, ", list.toString());
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            qkv qkvVar = list.get(i2);
            if (qkvVar.i() && qkvVar.f()) {
                i |= qkvVar.c();
            }
        }
        if (i == 0) {
            i = 4096;
        }
        LogUtil.a("BloodPressureUtil", "getBeforeActiveIndex, result=", Integer.valueOf(i));
        return i;
    }

    public static List<String> e(List<qkv> list) {
        if (list == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            qkv qkvVar = list.get(i);
            if (qkvVar.b() && qkvVar.i()) {
                arrayList.add(qkvVar.d());
            }
        }
        return arrayList;
    }

    public static void d(List<qkv> list) {
        Iterator<qkv> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().i()) {
                return;
            }
        }
        for (qkv qkvVar : list) {
            if (qkvVar.e()) {
                qkvVar.c(true);
            }
        }
    }

    public static boolean c(List<qkg> list, List<qkg> list2) {
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).a(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int a(int i, int i2) {
        int c = eeu.c();
        if (c == 5) {
            d = 20.0f;
        } else if (c == 4) {
            d = 25.0f;
        } else {
            d = 16.67f;
        }
        ArrayList<dqn> a2 = a();
        LogUtil.c("BloodPressureUtil", "bloodPressureList," + a2.size());
        ArrayList arrayList = new ArrayList(a2);
        int c2 = eeu.c(i, i2);
        if (c2 == 0) {
            float max = Math.max(b(i, i2, ((dqn) arrayList.get(0)).a()), 0.0f);
            float f = d;
            return Math.max((int) Math.floor((max * f) + (f * c2)), 0);
        }
        if (c2 == 1) {
            float max2 = Math.max(b(i, i2, 0, ((dqn) arrayList.get(1)).a()), 0.0f);
            float f2 = d;
            return Math.min((int) Math.ceil((max2 * f2) + (f2 * c2)), (int) (d * (c2 + 1)));
        }
        if (c2 == 2) {
            float max3 = Math.max(b(i, i2, 0, ((dqn) arrayList.get(2)).a()), 0.0f);
            float f3 = d;
            return Math.min((int) Math.ceil((max3 * f3) + (f3 * c2)), (int) (d * (c2 + 1)));
        }
        if (c2 == 3 || c2 == 4 || c2 == 5) {
            float b = b(i, i2, c2 - 3, ((dqn) arrayList.get(3)).a());
            float f4 = d;
            return Math.min((int) Math.ceil((b * f4) + (f4 * c2)), Math.min((int) (d * (c2 + 1)), 100));
        }
        LogUtil.a("BloodPressureUtil", "invalid convertType, ", Integer.valueOf(c2));
        return 0;
    }

    public static float b(int i, int i2, int i3, List<dqn.b> list) {
        if (koq.b(list, i3)) {
            LogUtil.h("BloodPressureUtil", Integer.valueOf(i3), " out of bounds");
            return 0.0f;
        }
        dqn.b bVar = list.get(i3);
        List<Integer> a2 = bVar.a();
        int intValue = a2.get(0).intValue();
        int intValue2 = a2.get(1).intValue() <= 300 ? a2.get(1).intValue() : 300;
        List<Integer> b = bVar.b();
        int intValue3 = b.get(0).intValue();
        float f = (i - intValue) / (intValue2 - intValue);
        float intValue4 = (i2 - intValue3) / ((b.get(1).intValue() <= 200 ? b.get(1).intValue() : 200) - intValue3);
        if (f <= 0.0f) {
            f = 0.0f;
        }
        if (intValue4 <= 0.0f) {
            intValue4 = 0.0f;
        }
        int i4 = (f < 0.0f ? 0 : 1) + (intValue4 >= 0.0f ? 1 : 0);
        if (i4 == 0) {
            return 0.0f;
        }
        return Math.max(Math.min((f + intValue4) / i4, 1.0f), 0.0f);
    }

    public static float b(int i, int i2, List<dqn.b> list) {
        dqn.b bVar = list.get(0);
        float intValue = (i - 40) / (bVar.a().get(1).intValue() - 40);
        float intValue2 = (i2 - 30) / (bVar.b().get(1).intValue() - 30);
        if (intValue <= 0.0f) {
            intValue = 0.0f;
        }
        if (intValue2 <= 0.0f) {
            intValue2 = 0.0f;
        }
        int i3 = (intValue <= 0.0f ? 0 : 1) + (intValue2 > 0.0f ? 1 : 0);
        if (i3 == 0) {
            return 0.0f;
        }
        return Math.max(Math.min((intValue + intValue2) / i3, 1.0f), 0.0f);
    }

    public static int[] a(Context context) {
        int c = eeu.c();
        int[] iArr = {ContextCompat.getColor(context, R.color._2131296521_res_0x7f090109), ContextCompat.getColor(context, R.color._2131296530_res_0x7f090112), ContextCompat.getColor(context, R.color._2131296519_res_0x7f090107)};
        if (c == 5) {
            int[] copyOf = Arrays.copyOf(iArr, 4);
            copyOf[2] = ContextCompat.getColor(context, R.color._2131296528_res_0x7f090110);
            copyOf[3] = ContextCompat.getColor(context, R.color._2131296519_res_0x7f090107);
            return copyOf;
        }
        if (c == 6) {
            int[] copyOf2 = Arrays.copyOf(iArr, 5);
            copyOf2[2] = ContextCompat.getColor(context, R.color._2131296526_res_0x7f09010e);
            copyOf2[3] = ContextCompat.getColor(context, R.color._2131296528_res_0x7f090110);
            copyOf2[4] = ContextCompat.getColor(context, R.color._2131296519_res_0x7f090107);
            return copyOf2;
        }
        health.compact.a.util.LogUtil.d("BloodPressureUtil", "count: ", Integer.valueOf(c));
        return iArr;
    }

    public static void d(final int[] iArr, final IIconCallBack iIconCallBack) {
        final HashMap hashMap = new HashMap(16);
        HiDataSourceFetchOption e = HiDataSourceFetchOption.builder().a(1).c(iArr).e();
        LogUtil.a("BloodPressureUtil", "fetchDataSource start");
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(e, new HiDataClientListener() { // from class: qql
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list) {
                BloodPressureUtil.d(BloodPressureUtil.IIconCallBack.this, hashMap, iArr, list);
            }
        });
    }

    public static /* synthetic */ void d(IIconCallBack iIconCallBack, HashMap hashMap, int[] iArr, List list) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("fetchDataSource end clientList size = ");
        sb.append(list == null ? 0 : list.size());
        objArr[0] = sb.toString();
        LogUtil.a("BloodPressureUtil", objArr);
        if (koq.b(list)) {
            iIconCallBack.onReceive(null);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            HiHealthClient hiHealthClient = (HiHealthClient) list.get(i);
            if (rrb.c(hiHealthClient)) {
                hashMap.put(Integer.valueOf(iArr[i]), Integer.valueOf(rrf.d()));
            } else {
                hashMap.put(Integer.valueOf(iArr[i]), Integer.valueOf(rrf.a(hiHealthClient.getHiDeviceInfo() != null ? String.valueOf(hiHealthClient.getHiDeviceInfo().getDeviceType()) : "")));
            }
        }
        iIconCallBack.onReceive(hashMap);
    }

    public static boolean e() {
        return EnvironmentInfo.k() || Utils.l() || !(!Utils.o() || d() || qif.b()) || (Utils.o() && LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode());
    }

    public static boolean d() {
        boolean z;
        boolean z2;
        ArrayList<cpm> a2 = jfv.a();
        if (koq.c(a2)) {
            LogUtil.a("BloodPressureUtil", "connected device size: ", a2);
            Iterator<cpm> it = a2.iterator();
            z = false;
            z2 = false;
            while (it.hasNext()) {
                cpm next = it.next();
                if (next != null && next.d() != null) {
                    LogUtil.a("BloodPressureUtil", "deviceInfo: ", next.toString());
                    if (next.d().toLowerCase().contains("huawei watch d") || next.d().toLowerCase().contains("huawei molly") || next.d().toLowerCase().contains("huawei watch b9") || next.i() == 73) {
                        z = true;
                    }
                    if (next.i() == 86 || next.i() == 87) {
                        z2 = true;
                    }
                }
            }
        } else {
            z = false;
            z2 = false;
        }
        boolean z3 = !koq.b(ceo.d().d(HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE));
        LogUtil.a("BloodPressureUtil", "hasMolly: ", Boolean.valueOf(z), ", hasBPDevice: ", Boolean.valueOf(z3), ", hasLuca: ", Boolean.valueOf(z2));
        return z || z3 || z2;
    }

    public static boolean h() {
        String accountInfo = LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.d("BloodPressureUtil", "countryCode is error: ", accountInfo);
            return true;
        }
        for (String str : nsf.i(R.array._2130968688_res_0x7f040070)) {
            if (accountInfo.equals(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean c() {
        if (CommonUtil.bv()) {
            return !koq.b(dqu.b().d());
        }
        return true;
    }

    public static void f() {
        dqo a2 = drl.a("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.blood-pressure");
        if (a2 == null || a2.a() == null) {
            LogUtil.b("BloodPressureUtil", "featureByFile or getExtInfo() is null");
            return;
        }
        Map a3 = a2.a();
        if (!(a3 instanceof LinkedTreeMap)) {
            LogUtil.h("BloodPressureUtil", "extInfoObject is not LinkedTreeMap");
            return;
        }
        LinkedTreeMap linkedTreeMap = (LinkedTreeMap) a3;
        LogUtil.c("BloodPressureUtil", "extInfo," + linkedTreeMap);
        Object obj = linkedTreeMap.get("typeList");
        if (obj instanceof ArrayList) {
            Object obj2 = linkedTreeMap.get("dynamicBloodPressureTypeList");
            Object obj3 = linkedTreeMap.get("descriptionid");
            if (obj3 == null) {
                LogUtil.b("BloodPressureUtil", "objectId null");
                return;
            }
            int f = (int) nsn.f(obj3.toString());
            LogUtil.c("BloodPressureUtil", "descriptionId," + f);
            if (obj2 instanceof ArrayList) {
                dqu.b().d(HiJsonUtil.e(obj2));
            }
            try {
                ArrayList<dqn> arrayList = (ArrayList) HiJsonUtil.b(HiJsonUtil.e(obj), new TypeToken<ArrayList<dqn>>() { // from class: com.huawei.ui.main.stories.health.util.BloodPressureUtil.3
                }.getType());
                ArrayList<dqq> arrayList2 = new ArrayList<>();
                if (obj2 instanceof ArrayList) {
                    arrayList2 = (ArrayList) HiJsonUtil.b(HiJsonUtil.e(obj2), new TypeToken<ArrayList<dqq>>() { // from class: com.huawei.ui.main.stories.health.util.BloodPressureUtil.4
                    }.getType());
                }
                if (arrayList == null) {
                    LogUtil.h("BloodPressureUtil", "get typeList list is null");
                    return;
                }
                LogUtil.c("BloodPressureUtil", "typeList," + arrayList.size() + "typeList," + arrayList, "dynamicBloodPressureTypeList," + arrayList2.size() + "dynamicBloodPressureTypeList," + arrayList2);
                if (koq.d(arrayList, 3)) {
                    e(arrayList.get(3));
                }
                dqu.b().b(arrayList);
                dqu.b().b(f);
                dqu.b().a(arrayList2);
            } catch (JsonSyntaxException e) {
                LogUtil.b("BloodPressureUtil", "JsonSyntaxException in get typeList: ", e.getMessage());
            } catch (NullPointerException unused) {
                LogUtil.b("BloodPressureUtil", "Json NullPointerException");
            }
        }
    }

    private static void e(dqn dqnVar) {
        if (dqnVar == null) {
            LogUtil.b("BloodPressureUtil", "bloodPressureFeatureInfo is null");
            return;
        }
        List<dqn.b> a2 = dqnVar.a();
        if (koq.b(a2)) {
            LogUtil.b("BloodPressureUtil", "Categorization list is null");
            return;
        }
        for (int i = 0; i < a2.size(); i++) {
            dqn.b bVar = a2.get(i);
            if (bVar != null) {
                bVar.e(i);
            }
        }
    }
}
