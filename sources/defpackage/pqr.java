package defpackage;

import android.util.SparseArray;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.SleepMonitorAlgorithmApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.bzl;
import defpackage.pqr;
import health.compact.a.Services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class pqr {
    private static int l(int i) {
        if (i == 2 || i == 3 || i == 4 || i == 6 || i == 7) {
            return 1;
        }
        if (i == 8 || i == 9 || i == 10 || i == 12) {
            return 2;
        }
        return (i == 13 || i == 14) ? 3 : 4;
    }

    private static boolean r(int i) {
        return i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 12 || i == 13 || i == 14 || i == 1;
    }

    private static List<String> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_1));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_2));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_3));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_4));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_5));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_6));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_7, 30, 2));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_8));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_9));
        return arrayList;
    }

    private static List<String> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_10, 30));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_11, 10));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_12));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_13));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_14));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_15));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_9));
        return arrayList;
    }

    private static List<String> o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_17));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_18));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_19, 6));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_20, 6));
        return arrayList;
    }

    private static List<String> k() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_21));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_22));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_23));
        return arrayList;
    }

    private static List<String> l() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_24));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_25));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_26));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_27));
        return arrayList;
    }

    private static List<String> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_28));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_29));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_30));
        return arrayList;
    }

    private static List<String> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_31));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_32));
        return arrayList;
    }

    private static List<String> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_33));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_34));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_35));
        return arrayList;
    }

    private static List<String> i() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_36));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_13));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_38));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_39));
        return arrayList;
    }

    private static List<String> m() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_40, 6, 8));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_41));
        return arrayList;
    }

    private static List<String> n() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_42, 30));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_43));
        return arrayList;
    }

    private static List<String> j() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_44));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_45));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_46));
        return arrayList;
    }

    private static List<String> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_sleep_input_text13));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_sleep_input_text14));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_sleep_input_text15));
        return arrayList;
    }

    public static String f(int i) {
        int i2;
        if (i > 85 && i <= 100) {
            i2 = R$string.IDS_excellent;
        } else if (i >= 75 && i <= 85) {
            i2 = R$string.IDS_good;
        } else if (i > 60 && i < 75) {
            i2 = R$string.IDS_general;
        } else {
            i2 = R$string.IDS_need_improving;
        }
        return BaseApplication.getContext().getString(i2);
    }

    public static int g(int i) {
        if (i > 60) {
            return BaseApplication.getContext().getColor(R.color._2131299095_res_0x7f090b17);
        }
        return BaseApplication.getContext().getColor(R.color._2131299096_res_0x7f090b18);
    }

    public static String b(int i) {
        int i2;
        if (i > 85 && i <= 100) {
            i2 = R$string.IDS_day_quality_summary_4;
        } else if (i >= 75 && i <= 85) {
            i2 = R$string.IDS_day_quality_summary_3;
        } else if (i > 60 && i < 75) {
            i2 = R$string.IDS_day_quality_summary_2;
        } else {
            i2 = R$string.IDS_day_quality_summary_1;
        }
        return BaseApplication.getContext().getString(i2);
    }

    public static String b(int i, int i2, boolean z, int i3) {
        LogUtil.a("PhoneSleepSuggestUtils", "sleepTag: ", Integer.valueOf(i), ", sdkLevel: ", Integer.valueOf(i2));
        int i4 = 0;
        switch (i) {
            case 1:
                i4 = d(i2, R$string.IDS_day_analysis_result_29);
                break;
            case 2:
                i4 = d(i2, R$string.IDS_day_analysis_result_3, R$string.IDS_day_analysis_result_2, R$string.IDS_day_analysis_result_1);
                break;
            case 3:
                i4 = d(i2, R$string.IDS_day_analysis_result_6, R$string.IDS_day_analysis_result_5, R$string.IDS_day_analysis_result_4);
                break;
            case 4:
                i4 = d(i2, R$string.IDS_day_analysis_result_9, R$string.IDS_day_analysis_result_8, R$string.IDS_day_analysis_result_7);
                break;
            case 5:
            case 11:
            default:
                return "";
            case 6:
                i4 = d(i2, R$string.IDS_day_analysis_result_12, R$string.IDS_day_analysis_result_11, R$string.IDS_day_analysis_result_10);
                break;
            case 7:
                i4 = d(i2, R$string.IDS_day_analysis_result_15, R$string.IDS_day_analysis_result_14, R$string.IDS_day_analysis_result_13);
                break;
            case 8:
                i4 = d(i2, R$string.IDS_day_analysis_result_18, R$string.IDS_day_analysis_result_17, R$string.IDS_day_analysis_result_16);
                break;
            case 9:
                i4 = d(i2, 0, R$string.IDS_day_analysis_result_20, R$string.IDS_day_analysis_result_19);
                break;
            case 10:
                i4 = d(i2, 0, R$string.IDS_day_analysis_result_22, R$string.IDS_day_analysis_result_21);
                break;
            case 12:
                i4 = d(i2, 0, R$string.IDS_day_analysis_result_24, R$string.IDS_day_analysis_result_23);
                break;
            case 13:
                if (z) {
                    i4 = d(i2, R$string.IDS_day_analysis_result_25, R$string.IDS_day_analysis_result_26);
                    break;
                }
                break;
            case 14:
                if (i3 < 360) {
                    i4 = d(i2, R$string.IDS_day_analysis_result_27, R$string.IDS_day_analysis_result_28);
                    break;
                }
                break;
            case 15:
                i4 = d(i2, R$string.IDS_sleep_input_text12, R$string.IDS_sleep_input_text11, R$string.IDS_sleep_input_text10);
                break;
        }
        return i4 != 0 ? BaseApplication.getContext().getString(i4) : "";
    }

    private static int d(int i, int... iArr) {
        if (i == 1) {
            return iArr[0];
        }
        if (i == 2) {
            return iArr[1];
        }
        if (i != 3) {
            return 0;
        }
        return iArr[2];
    }

    public static String c(int i) {
        List<String> j;
        new ArrayList();
        switch (i) {
            case 1:
                j = j();
                break;
            case 2:
                j = c();
                break;
            case 3:
                j = a();
                break;
            case 4:
                j = o();
                break;
            case 5:
            case 11:
            default:
                j = d();
                break;
            case 6:
                j = k();
                break;
            case 7:
                j = l();
                break;
            case 8:
                j = g();
                break;
            case 9:
                j = b();
                break;
            case 10:
                j = h();
                break;
            case 12:
                j = i();
                break;
            case 13:
                j = m();
                break;
            case 14:
                j = n();
                break;
            case 15:
                j = f();
                break;
        }
        return j.size() == 0 ? "" : j.get(b(0, j.size() - 1));
    }

    private static List<String> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_44));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_45));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_46));
        return arrayList;
    }

    private static List<String> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_1));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_2));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_3));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_4));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_5));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_6));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_7, 30, 2));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_9));
        arrayList.add(BaseApplication.getContext().getString(R$string.IDS_day_sleep_suggestion_43));
        return arrayList;
    }

    public static String e(int i) {
        if (i != 2) {
            return i != 3 ? "" : BaseApplication.getContext().getString(R$string.IDS_sleep_input_text16);
        }
        return e().get(b(0, r2.size() - 1));
    }

    public static void a(final Date date, final Date date2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(e(date));
        hiDataReadOption.setEndTime(c(date2));
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.EFFECTIVE_SLEEP_DURATION.value()});
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: pqr.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("PhoneSleepSuggestUtils", "getDailySleepRegularity onResult: errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (IBaseResponseCallback.this == null) {
                    LogUtil.b("PhoneSleepSuggestUtils", "callback is null");
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "getTemperatureData convert fail, data = ";
                    objArr[1] = Boolean.valueOf(obj == null);
                    LogUtil.h("PhoneSleepSuggestUtils", objArr);
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PhoneSleepSuggestUtils", "existOxygenRemindData map is null");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.EFFECTIVE_SLEEP_DURATION.value());
                if (obj2 instanceof List) {
                    List list = (List) obj2;
                    LogUtil.a("PhoneSleepSuggestUtils", "getDailySleepRegularity ", " list size ", Integer.valueOf(list.size()));
                    arrayList.addAll(list);
                }
                if (arrayList.size() != 0) {
                    String e = pqr.e(pqr.e(date), pqr.c(date2), (int) ((HiHealthData) arrayList.get(arrayList.size() - 1)).getValue());
                    LogUtil.a("PhoneSleepSuggestUtils", "callback successful, sleepRegularityDesc", e);
                    IBaseResponseCallback.this.d(0, e);
                    return;
                }
                IBaseResponseCallback.this.d(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(long j, long j2, long j3) {
        int regularScore = ((SleepMonitorAlgorithmApi) Services.c("SleepMonitor", SleepMonitorAlgorithmApi.class)).getRegularScore(new bzl.c().d(j).b(j2).a(j3).d());
        if (regularScore <= 10) {
            return BaseApplication.getContext().getString(R$string.IDS_irregular_sleep);
        }
        if (regularScore > 10 && regularScore <= 20) {
            return BaseApplication.getContext().getString(R$string.IDS_fairly_regular_sleep);
        }
        if (regularScore <= 20 || regularScore > 40) {
            return (regularScore <= 40 || regularScore > 50) ? "" : BaseApplication.getContext().getString(R$string.IDS_very_regular_sleep);
        }
        return BaseApplication.getContext().getString(R$string.IDS_more_regular_sleep);
    }

    public static String c(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        if (i == 0 && i2 == 0 && i3 == 0) {
            return d(z);
        }
        int i7 = i4 - i;
        int i8 = i5 - i2;
        int i9 = i6 - i3;
        if (Math.abs(i7) >= 90 || Math.abs(i8) >= 90 || Math.abs(i9) >= 90) {
            return (Math.abs(i7) > 90 || Math.abs(i8) > 90 || Math.abs(i9) > 90) ? b(i7, i8, i9, z) : "";
        }
        int i10 = ((i7 + i8) + i9) / 3;
        if (i10 <= 30 && z) {
            return BaseApplication.getContext().getString(R$string.IDS_week_data_difference_2);
        }
        if (i10 <= 60 && z) {
            return BaseApplication.getContext().getString(R$string.IDS_week_data_difference_3);
        }
        if (i10 <= 90 && z) {
            return BaseApplication.getContext().getString(R$string.IDS_week_data_difference_4);
        }
        if (i10 <= 30) {
            return BaseApplication.getContext().getString(R$string.IDS_month_data_difference_2);
        }
        if (i10 <= 60) {
            return BaseApplication.getContext().getString(R$string.IDS_month_data_difference_3);
        }
        return i10 <= 90 ? BaseApplication.getContext().getString(R$string.IDS_month_data_difference_4) : "";
    }

    public static String d(boolean z) {
        if (z) {
            return BaseApplication.getContext().getString(R$string.IDS_week_data_difference_1);
        }
        return BaseApplication.getContext().getString(R$string.IDS_month_data_difference_1);
    }

    private static String b(int i, int i2, int i3, boolean z) {
        String string = Math.abs(i) > 90 ? BaseApplication.getContext().getString(R$string.IDS_two_parts, "", j(i)) : "";
        if (Math.abs(i2) > 90) {
            string = BaseApplication.getContext().getString(R$string.IDS_two_parts, string, m(i2));
        }
        if (Math.abs(i3) > 90) {
            string = BaseApplication.getContext().getString(R$string.IDS_two_parts, string, n(i3));
        }
        if (z) {
            return BaseApplication.getContext().getString(R$string.IDS_week_compared_to_last_week, string);
        }
        return BaseApplication.getContext().getString(R$string.IDS_month_compared_to_last_month, string);
    }

    private static String j(int i) {
        String d = d(i);
        String a2 = a(i);
        if (i < 0) {
            return BaseApplication.getContext().getString(R$string.IDS_early_bedtime, d, a2);
        }
        return BaseApplication.getContext().getString(R$string.IDS_delayed_bedtime, d, a2);
    }

    private static String m(int i) {
        String d = d(i);
        String a2 = a(i);
        if (i < 0) {
            return BaseApplication.getContext().getString(R$string.IDS_wake_up_early, d, a2);
        }
        return BaseApplication.getContext().getString(R$string.IDS_wake_up_delay, d, a2);
    }

    private static String n(int i) {
        String d = d(i);
        String a2 = a(i);
        if (i < 0) {
            return BaseApplication.getContext().getString(R$string.IDS_decrease_sleep_duration, d, a2);
        }
        return BaseApplication.getContext().getString(R$string.IDS_increase_sleep_duration, d, a2);
    }

    public static String d(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, Math.abs(i) / 60, Integer.valueOf(Math.abs(i) / 60));
    }

    public static String a(int i) {
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, Math.abs(i) % 60, Integer.valueOf(Math.abs(i) % 60));
    }

    public static String c(int i, int i2, boolean z) {
        if (i > 85 && i <= 100) {
            return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_26);
        }
        if (i >= 75 && i <= 85) {
            return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_25);
        }
        if (i <= 60 || i >= 75) {
            if (z) {
                return s(i2);
            }
            return h(i2);
        }
        if (z) {
            return p(i2);
        }
        return i(i2);
    }

    private static String p(int i) {
        switch (i) {
            case 2:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_13);
            case 3:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_14);
            case 4:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_15);
            case 5:
            case 11:
            default:
                return "";
            case 6:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_16);
            case 7:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_17);
            case 8:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_18);
            case 9:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_19);
            case 10:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_20);
            case 12:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_21);
            case 13:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_22);
            case 14:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_23);
        }
    }

    private static String s(int i) {
        switch (i) {
            case 2:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_1);
            case 3:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_2);
            case 4:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_3);
            case 5:
            case 11:
            default:
                return "";
            case 6:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_4);
            case 7:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_5);
            case 8:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_6);
            case 9:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_7);
            case 10:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_8);
            case 12:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_9);
            case 13:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_10);
            case 14:
                return BaseApplication.getContext().getString(R$string.IDS_week_summary_of_sleep_11);
        }
    }

    private static String i(int i) {
        switch (i) {
            case 2:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_13);
            case 3:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_14);
            case 4:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_15);
            case 5:
            case 11:
            default:
                return "";
            case 6:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_16);
            case 7:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_17);
            case 8:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_18);
            case 9:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_19);
            case 10:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_20);
            case 12:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_21);
            case 13:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_22);
            case 14:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_23);
        }
    }

    private static String h(int i) {
        switch (i) {
            case 2:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_1);
            case 3:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_2);
            case 4:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_3);
            case 5:
            case 11:
            default:
                return "";
            case 6:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_4);
            case 7:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_5);
            case 8:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_6);
            case 9:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_7);
            case 10:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_8);
            case 12:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_9);
            case 13:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_10);
            case 14:
                return BaseApplication.getContext().getString(R$string.IDS_month_summary_of_sleep_11);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long e(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long c(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(int i, int i2, int i3, int i4) {
        if (r(i) && !r(i2)) {
            return i;
        }
        if (!r(i) && r(i2)) {
            return i2;
        }
        int l = l(i);
        int l2 = l(i2);
        return l < l2 ? i : l > l2 ? i2 : i3 > i4 ? i : i3 < i4 ? i4 : new int[]{i, i2}[b(0, 1)];
    }

    public static void c(Date date, Date date2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(e(date));
        hiDataReadOption.setEndTime(c(date2));
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.SLEEP_SYMPTOMS.value()});
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: pqr.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("PhoneSleepSuggestUtils", "getDailySleepTag onResult: errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (IBaseResponseCallback.this == null) {
                    LogUtil.b("PhoneSleepSuggestUtils", "callback is null");
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "getTemperatureData convert fail, data = ";
                    objArr[1] = Boolean.valueOf(obj == null);
                    LogUtil.h("PhoneSleepSuggestUtils", objArr);
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PhoneSleepSuggestUtils", "existOxygenRemindData map is null");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.SLEEP_SYMPTOMS.value());
                if (obj2 instanceof List) {
                    List list = (List) obj2;
                    LogUtil.a("PhoneSleepSuggestUtils", "getDailySleepTag ", " list size ", Integer.valueOf(list.size()));
                    arrayList.addAll(list);
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    try {
                        JSONObject jSONObject = new JSONObject(((HiHealthData) arrayList.get(i3)).getMetaData());
                        int c = pqr.c(jSONObject.getInt("sym1_idx"), jSONObject.getInt("sym2_idx"), jSONObject.getInt("sym1_lvl"), jSONObject.getInt("sym2_lvl"));
                        if (c != 1) {
                            arrayList2.add(Integer.valueOf(c));
                        }
                    } catch (JSONException unused) {
                        IBaseResponseCallback.this.d(-1, null);
                    }
                }
                LogUtil.a("PhoneSleepSuggestUtils", "callback successful, getMaxCountValue", Integer.valueOf(pqr.c(arrayList2)));
                IBaseResponseCallback.this.d(0, Integer.valueOf(pqr.c(arrayList2)));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(List<Integer> list) {
        HashMap hashMap = new HashMap();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (hashMap.get(Integer.valueOf(intValue)) == null) {
                hashMap.put(Integer.valueOf(intValue), 1);
            } else {
                hashMap.put(Integer.valueOf(intValue), Integer.valueOf(((Integer) hashMap.get(Integer.valueOf(intValue))).intValue() + 1));
            }
        }
        int intValue2 = ((Integer) Collections.max(hashMap.values())).intValue();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            if (((Integer) entry.getValue()).intValue() == intValue2) {
                arrayList.add((Integer) entry.getKey());
            }
        }
        return ((Integer) arrayList.get(b(0, arrayList.size() - 1))).intValue();
    }

    public static void d(Date date, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(e(date));
        hiDataReadOption.setEndTime(c(date));
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.SLEEP_SYMPTOMS.value()});
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: pqr.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (IBaseResponseCallback.this == null) {
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "getTemperatureData convert fail, data = ";
                    objArr[1] = Boolean.valueOf(obj == null);
                    LogUtil.h("PhoneSleepSuggestUtils", objArr);
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PhoneSleepSuggestUtils", "existOxygenRemindData map is null");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.SLEEP_SYMPTOMS.value());
                if (obj2 instanceof List) {
                    List list = (List) obj2;
                    LogUtil.a("PhoneSleepSuggestUtils", "getSleepTag ", " list size ", Integer.valueOf(list.size()));
                    arrayList.addAll(list);
                }
                try {
                    JSONObject jSONObject = new JSONObject(((HiHealthData) arrayList.get(arrayList.size() - 1)).getMetaData());
                    int i3 = jSONObject.getInt("sym1_idx");
                    int i4 = jSONObject.getInt("sym1_lvl");
                    int i5 = jSONObject.getInt("sym2_idx");
                    int i6 = jSONObject.getInt("sym2_lvl");
                    int c = pqr.c(i3, i5, i4, i6);
                    if (c != i3) {
                        i4 = i6;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("sleep_tag", Integer.valueOf(c));
                    hashMap.put("sdk_type", Integer.valueOf(i4));
                    IBaseResponseCallback.this.d(0, hashMap);
                } catch (JSONException unused) {
                    IBaseResponseCallback.this.d(-1, null);
                }
            }
        });
    }

    private static int b(int i, int i2) {
        return nsg.b((i2 - i) + 1) + i;
    }

    public static void c(Date date, IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr = {DicDataTypeUtil.DataType.SLEEP_END_REASON.value(), DicDataTypeUtil.DataType.BED_TIME.value(), DicDataTypeUtil.DataType.RISING_TIME.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(e(date));
        hiDataReadOption.setEndTime(c(date));
        hiDataReadOption.setType(iArr);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new AnonymousClass5(iBaseResponseCallback));
    }

    /* renamed from: pqr$5, reason: invalid class name */
    class AnonymousClass5 implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IBaseResponseCallback f16234a;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass5(IBaseResponseCallback iBaseResponseCallback) {
            this.f16234a = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (this.f16234a == null) {
                return;
            }
            if (!(obj instanceof SparseArray)) {
                Object[] objArr = new Object[2];
                objArr[0] = "getTemperatureData convert fail, data = ";
                objArr[1] = Boolean.valueOf(obj == null);
                LogUtil.h("PhoneSleepSuggestUtils", objArr);
                this.f16234a.d(-1, null);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                LogUtil.h("PhoneSleepSuggestUtils", "existOxygenRemindData map is null");
                this.f16234a.d(-1, null);
                return;
            }
            List arrayList = new ArrayList();
            if (sparseArray.get(DicDataTypeUtil.DataType.SLEEP_END_REASON.value()) instanceof List) {
                arrayList = (List) sparseArray.get(DicDataTypeUtil.DataType.SLEEP_END_REASON.value());
            }
            int value = arrayList.size() != 0 ? (int) ((HiHealthData) arrayList.get(arrayList.size() - 1)).getValue() : 0;
            List arrayList2 = new ArrayList();
            if (sparseArray.get(DicDataTypeUtil.DataType.BED_TIME.value()) instanceof List) {
                arrayList2 = (List) sparseArray.get(DicDataTypeUtil.DataType.BED_TIME.value());
            }
            List arrayList3 = new ArrayList();
            if (sparseArray.get(DicDataTypeUtil.DataType.RISING_TIME.value()) instanceof List) {
                arrayList3 = (List) sparseArray.get(DicDataTypeUtil.DataType.RISING_TIME.value());
            }
            if (value == 0) {
                this.f16234a.d(-1, null);
                return;
            }
            if (value == 1) {
                HashMap hashMap = new HashMap();
                hashMap.put("detect_abnormal", BaseApplication.getContext().getString(R$string.IDS_sleep_end_reason_2));
                this.f16234a.d(0, hashMap);
                return;
            }
            if (value == 2 && arrayList2.size() != 0 && arrayList3.size() != 0) {
                long value2 = (long) ((HiHealthData) arrayList2.get(arrayList2.size() - 1)).getValue();
                long value3 = (long) ((HiHealthData) arrayList3.get(arrayList3.size() - 1)).getValue();
                final IBaseResponseCallback iBaseResponseCallback = this.f16234a;
                pqr.b(value2, value3, new IBaseResponseCallback() { // from class: pqo
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i3, Object obj2) {
                        pqr.AnonymousClass5.b(IBaseResponseCallback.this, i3, obj2);
                    }
                });
                return;
            }
            if (value == 3 && arrayList3.size() != 0) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("detect_abnormal", BaseApplication.getContext().getString(R$string.IDS_sleep_end_reason_3, pqr.b((long) ((HiHealthData) arrayList3.get(arrayList3.size() - 1)).getValue())));
                this.f16234a.d(0, hashMap2);
                return;
            }
            this.f16234a.d(-1, null);
        }

        static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
            LogUtil.a("PhoneSleepSuggestUtils", "errCode: ", Integer.valueOf(i), ", objData: ", obj);
            HashMap hashMap = new HashMap();
            if (i == 0) {
                hashMap.put("detect_abnormal", (String) obj);
                hashMap.put("detect_abnormal_jump", BaseApplication.getContext().getString(R$string.IDS_sleep_to_set_up));
                iBaseResponseCallback.d(0, hashMap);
                return;
            }
            iBaseResponseCallback.d(-1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return new SimpleDateFormat("HH:mm").format(calendar.getTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        final int[] iArr = {22104, 22101, 22103};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(iArr);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: pqr.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                long j3;
                long j4;
                if (IBaseResponseCallback.this == null) {
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "getTemperatureData convert fail, data = ";
                    objArr[1] = Boolean.valueOf(obj == null);
                    LogUtil.h("PhoneSleepSuggestUtils", objArr);
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("PhoneSleepSuggestUtils", "existOxygenRemindData map is null");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                for (int i3 : iArr) {
                    Object obj2 = sparseArray.get(i3);
                    if (obj2 instanceof List) {
                        List list = (List) obj2;
                        LogUtil.a("PhoneSleepSuggestUtils", "getInterruptSleepEndReason ", " list size ", Integer.valueOf(list.size()));
                        arrayList.addAll(list);
                    }
                }
                int size = arrayList.size();
                b[] bVarArr = new b[size];
                for (int i4 = 0; i4 < size; i4++) {
                    bVarArr[i4] = new b(((HiHealthData) arrayList.get(i4)).getStartTime(), ((HiHealthData) arrayList.get(i4)).getEndTime());
                }
                Arrays.sort(bVarArr);
                int i5 = 0;
                while (true) {
                    if (i5 >= size - 1) {
                        j3 = 0;
                        j4 = 0;
                        break;
                    }
                    int i6 = i5 + 1;
                    if (bVarArr[i6].e - bVarArr[i5].e > 60000) {
                        j3 = bVarArr[i5].d;
                        j4 = bVarArr[i6].e;
                        break;
                    }
                    i5 = i6;
                }
                if (j3 != 0 && j4 != 0) {
                    LogUtil.a("PhoneSleepSuggestUtils", "interrupt time exist");
                    IBaseResponseCallback.this.d(0, BaseApplication.getContext().getString(R$string.IDS_sleep_end_reason_1, pqr.b(j3), pqr.b(j4)));
                } else {
                    LogUtil.a("PhoneSleepSuggestUtils", "interrupt time not exist");
                    IBaseResponseCallback.this.d(-1, null);
                }
            }
        });
    }

    public static String a(fdp fdpVar, int i, boolean z, int i2) {
        return b(i, d(i, fdpVar), z, i2);
    }

    public static int d(fdp fdpVar) {
        List<Integer> c = c(fdpVar);
        if (c.isEmpty()) {
            return 0;
        }
        if (!c.contains(2) && !c.contains(3) && !c.contains(4) && !c.contains(15)) {
            return c.contains(13) ? 13 : 0;
        }
        if (c.contains(13)) {
            c.remove(new Integer(13));
        }
        LogUtil.a("PhoneSleepSuggestUtils", "tags: ", c);
        return c.get(b(0, c.size() - 1)).intValue();
    }

    private static List<Integer> c(fdp fdpVar) {
        ArrayList arrayList = new ArrayList();
        if (fdpVar.d().i() >= 30) {
            arrayList.add(2);
        }
        float h = fdpVar.d().h();
        if (h < 360.0f) {
            arrayList.add(3);
        }
        if (h < 360.0f) {
            arrayList.add(4);
        }
        if (fdpVar.d().t() < 0.85f) {
            arrayList.add(15);
        }
        if (h > 600.0f) {
            arrayList.add(13);
        }
        return arrayList;
    }

    private static int d(int i, fdp fdpVar) {
        LogUtil.a("PhoneSleepSuggestUtils", "sleepTag: ", Integer.valueOf(i));
        if (i == 2) {
            int i2 = fdpVar.d().i();
            if (i2 >= 120) {
                return 3;
            }
            if (i2 >= 60 && i2 < 120) {
                return 2;
            }
            if (i2 >= 30 && i2 < 60) {
                return 1;
            }
        } else if (i == 3) {
            int h = fdpVar.d().h();
            int g = fdpVar.d().g();
            if (g < 480 && h < 300.0f) {
                return 3;
            }
            if (g < 510 && h < 330.0f) {
                return 2;
            }
            if (g < 540 && h < 360.0f) {
                return 1;
            }
        } else if (i == 4) {
            float h2 = fdpVar.d().h();
            if (h2 < 240.0f) {
                return 3;
            }
            if (h2 >= 240.0f && h2 < 300.0f) {
                return 2;
            }
            if (h2 >= 300.0f && h2 < 360.0f) {
                return 1;
            }
        } else if (i == 13) {
            float h3 = fdpVar.d().h();
            if (h3 > 600.0f && h3 <= 720.0f) {
                return 2;
            }
            if (h3 > 720.0f) {
                return 1;
            }
        } else {
            if (i != 15) {
                return 0;
            }
            float t = fdpVar.d().t();
            if (t != 0.0f) {
                if (t < 0.7f) {
                    return 3;
                }
                if (t >= 0.7f && t < 0.8f) {
                    return 2;
                }
                if (t >= 0.8f && t < 0.85f) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static String b(fdp fdpVar) {
        int z = ((fdn) fdpVar.d()).z();
        int ac = ((fdn) fdpVar.d()).ac();
        if (z <= 70 || ac <= 70) {
            return c(Math.min(z, ac), z < ac);
        }
        return c(Math.max(z, ac), z > ac);
    }

    private static String c(int i, boolean z) {
        if (z) {
            return k(i);
        }
        return o(i);
    }

    private static String k(int i) {
        if (i < 35) {
            return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text24);
        }
        if (i < 70) {
            return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text25);
        }
        if (i < 85) {
            return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text26);
        }
        return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text27);
    }

    private static String o(int i) {
        if (i < 35) {
            return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text28);
        }
        if (i < 70) {
            return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text29);
        }
        if (i < 85) {
            return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text30);
        }
        return BaseApplication.getContext().getString(R$string.IDS_sleep_input_text31);
    }

    static class b implements Comparable<b> {
        long d;
        long e;

        public b(long j, long j2) {
            this.e = j;
            this.d = j2;
        }

        @Override // java.lang.Comparable
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            long j = this.e;
            long j2 = bVar.e;
            if (j > j2) {
                return 1;
            }
            return j < j2 ? -1 : 0;
        }
    }
}
