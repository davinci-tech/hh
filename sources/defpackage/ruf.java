package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelReq;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcloudmodel.model.unite.RunLevelDetail;
import com.huawei.hwcloudmodel.model.unite.UserRunLevelData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningDayData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelSomeDayData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.up.model.UserInfomation;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ruf {
    public static List<nmm> c() {
        Context context = BaseApplication.getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(-20.0f, -16.0f, ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e), context.getString(R$string.IDS_data_index_very_pool)));
        arrayList.add(new nmm(-16.0f, -6.0f, ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d), context.getString(R$string.IDS_data_index_pool)));
        arrayList.add(new nmm(-6.0f, 0.5f, ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88), context.getString(R$string.IDS_data_index_normal)));
        arrayList.add(new nmm(0.5f, 3.0f, ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89), context.getString(R$string.IDS_data_index_well)));
        arrayList.add(new nmm(3.0f, 5.0f, ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b), context.getString(R$string.IDS_data_index_very_well)));
        return arrayList;
    }

    public static List<nmm> c(Integer[] numArr) {
        Context context = BaseApplication.getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(numArr[0].intValue(), numArr[1].intValue(), ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e), context.getString(R$string.IDS_hwh_health_vo2max_level_verypoor)));
        arrayList.add(new nmm(numArr[1].intValue(), numArr[2].intValue(), ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d), context.getString(R$string.IDS_hwh_health_vo2max_level_poor)));
        arrayList.add(new nmm(numArr[2].intValue(), numArr[3].intValue(), ContextCompat.getColor(context, R.color._2131298956_res_0x7f090a8c), context.getString(R$string.IDS_hwh_health_vo2max_level_fair)));
        arrayList.add(new nmm(numArr[3].intValue(), numArr[4].intValue(), ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88), context.getString(R$string.IDS_hwh_health_vo2max_level_average)));
        arrayList.add(new nmm(numArr[4].intValue(), numArr[5].intValue(), ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89), context.getString(R$string.IDS_hwh_health_vo2max_level_good)));
        arrayList.add(new nmm(numArr[5].intValue(), numArr[6].intValue(), ContextCompat.getColor(context, R.color._2131298954_res_0x7f090a8a), context.getString(R$string.IDS_hwh_health_vo2max_level_verygood)));
        arrayList.add(new nmm(numArr[6].intValue(), numArr[7].intValue(), ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b), context.getString(R$string.IDS_hwh_health_vo2max_level_excellent)));
        return arrayList;
    }

    public static List<nmm> b() {
        Context context = BaseApplication.getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(20.0f, 22.0f, ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b), context.getString(R$string.IDS_data_running_index_level_d)));
        arrayList.add(new nmm(22.0f, 27.0f, ContextCompat.getColor(context, R.color._2131298954_res_0x7f090a8a), context.getString(R$string.IDS_data_running_index_level_c)));
        arrayList.add(new nmm(27.0f, 36.0f, ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89), context.getString(R$string.IDS_data_running_index_level_b)));
        arrayList.add(new nmm(36.0f, 45.0f, ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88), context.getString(R$string.IDS_data_running_index_level_a)));
        arrayList.add(new nmm(45.0f, 53.0f, ContextCompat.getColor(context, R.color._2131298956_res_0x7f090a8c), context.getString(R$string.IDS_data_running_index_level_s)));
        arrayList.add(new nmm(53.0f, 58.0f, ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d), context.getString(R$string.IDS_data_running_index_level_ss)));
        arrayList.add(new nmm(58.0f, 90.0f, ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e), context.getString(R$string.IDS_data_running_index_level_sss)));
        return arrayList;
    }

    public static List<nmm> e() {
        Context context = BaseApplication.getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new nmm(20.0f, 25.0f, ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b), context.getString(R$string.IDS_data_running_index_level_d)));
        arrayList.add(new nmm(25.0f, 30.0f, ContextCompat.getColor(context, R.color._2131298954_res_0x7f090a8a), context.getString(R$string.IDS_data_running_index_level_c)));
        arrayList.add(new nmm(30.0f, 40.0f, ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89), context.getString(R$string.IDS_data_running_index_level_b)));
        arrayList.add(new nmm(40.0f, 50.0f, ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88), context.getString(R$string.IDS_data_running_index_level_a)));
        arrayList.add(new nmm(50.0f, 60.0f, ContextCompat.getColor(context, R.color._2131298956_res_0x7f090a8c), context.getString(R$string.IDS_data_running_index_level_s)));
        arrayList.add(new nmm(60.0f, 65.0f, ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d), context.getString(R$string.IDS_data_running_index_level_ss)));
        arrayList.add(new nmm(65.0f, 90.0f, ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e), context.getString(R$string.IDS_data_running_index_level_sss)));
        return arrayList;
    }

    public static int i(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 3.0f) {
            return ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b);
        }
        if (f >= 0.5f) {
            return ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89);
        }
        if (f >= -6.0f) {
            return ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88);
        }
        if (f >= -16.0f) {
            return ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d);
        }
        if (f >= -20.0f) {
            return ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e);
        }
        return ContextCompat.getColor(context, R.color._2131299241_res_0x7f090ba9);
    }

    public static int b(Integer[] numArr, int i) {
        Context context = BaseApplication.getContext();
        if (i >= numArr[6].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b);
        }
        if (i >= numArr[5].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298954_res_0x7f090a8a);
        }
        if (i >= numArr[4].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89);
        }
        if (i >= numArr[3].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88);
        }
        if (i >= numArr[2].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298956_res_0x7f090a8c);
        }
        if (i >= numArr[1].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d);
        }
        if (i >= numArr[0].intValue()) {
            return ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e);
        }
        return ContextCompat.getColor(context, R.color._2131299241_res_0x7f090ba9);
    }

    public static int b(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 65.0f) {
            return ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e);
        }
        if (f >= 60.0f) {
            return ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d);
        }
        if (f >= 50.0f) {
            return ContextCompat.getColor(context, R.color._2131298956_res_0x7f090a8c);
        }
        if (f >= 40.0f) {
            return ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88);
        }
        if (f >= 30.0f) {
            return ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89);
        }
        if (f >= 25.0f) {
            return ContextCompat.getColor(context, R.color._2131298954_res_0x7f090a8a);
        }
        if (f > 0.0f) {
            return ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b);
        }
        return ContextCompat.getColor(context, R.color._2131299241_res_0x7f090ba9);
    }

    public static int d(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 58.0f) {
            return ContextCompat.getColor(context, R.color._2131298958_res_0x7f090a8e);
        }
        if (f >= 53.0f) {
            return ContextCompat.getColor(context, R.color._2131298957_res_0x7f090a8d);
        }
        if (f >= 45.0f) {
            return ContextCompat.getColor(context, R.color._2131298956_res_0x7f090a8c);
        }
        if (f >= 36.0f) {
            return ContextCompat.getColor(context, R.color._2131298952_res_0x7f090a88);
        }
        if (f >= 27.0f) {
            return ContextCompat.getColor(context, R.color._2131298953_res_0x7f090a89);
        }
        if (f >= 22.0f) {
            return ContextCompat.getColor(context, R.color._2131298954_res_0x7f090a8a);
        }
        if (f > 0.0f) {
            return ContextCompat.getColor(context, R.color._2131298955_res_0x7f090a8b);
        }
        return ContextCompat.getColor(context, R.color._2131299241_res_0x7f090ba9);
    }

    public static String f(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 65.0f) {
            return context.getString(R$string.IDS_data_running_index_level_sss);
        }
        if (f >= 60.0f) {
            return context.getString(R$string.IDS_data_running_index_level_ss);
        }
        if (f >= 50.0f) {
            return context.getString(R$string.IDS_data_running_index_level_s);
        }
        if (f >= 40.0f) {
            return context.getString(R$string.IDS_data_running_index_level_a);
        }
        if (f >= 30.0f) {
            return context.getString(R$string.IDS_data_running_index_level_b);
        }
        if (f >= 25.0f) {
            return context.getString(R$string.IDS_data_running_index_level_c);
        }
        return f > 0.0f ? context.getString(R$string.IDS_data_running_index_level_d) : "--";
    }

    public static String e(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 58.0f) {
            return context.getString(R$string.IDS_data_running_index_level_sss);
        }
        if (f >= 53.0f) {
            return context.getString(R$string.IDS_data_running_index_level_ss);
        }
        if (f >= 45.0f) {
            return context.getString(R$string.IDS_data_running_index_level_s);
        }
        if (f >= 36.0f) {
            return context.getString(R$string.IDS_data_running_index_level_a);
        }
        if (f >= 27.0f) {
            return context.getString(R$string.IDS_data_running_index_level_b);
        }
        if (f >= 22.0f) {
            return context.getString(R$string.IDS_data_running_index_level_c);
        }
        return f > 0.0f ? context.getString(R$string.IDS_data_running_index_level_d) : "--";
    }

    public static int a() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfomation(0);
        }
        return userInfo.getGender();
    }

    public static String g(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 3.0f) {
            return context.getString(R$string.IDS_data_index_very_well);
        }
        if (f >= 0.5f) {
            return context.getString(R$string.IDS_data_index_well);
        }
        if (f >= -6.0f) {
            return context.getString(R$string.IDS_data_index_normal);
        }
        if (f >= -16.0f) {
            return context.getString(R$string.IDS_data_index_pool);
        }
        return context.getString(R$string.IDS_data_index_very_pool);
    }

    public static String h(float f) {
        Context context = BaseApplication.getContext();
        if (f >= 3.0f) {
            return context.getString(R$string.IDS_data_index_very_well_explain);
        }
        if (f >= 0.5f) {
            return context.getString(R$string.IDS_data_day_tab_tip);
        }
        if (f >= -6.0f) {
            return context.getString(R$string.IDS_data_index_normal_explain);
        }
        if (f >= -16.0f) {
            return context.getString(R$string.IDS_data_index_pool_explain);
        }
        return context.getString(R$string.IDS_data_index_very_pool_explain);
    }

    public static HealthTextView dQV_(ViewGroup viewGroup, int i, Activity activity) {
        View findViewById = viewGroup.findViewById(i);
        if (findViewById instanceof HealthTextView) {
            return (HealthTextView) findViewById;
        }
        LogUtil.h("Track_RqDataUtils", "getTextView view is not instanceof HealthTextView");
        return new HealthTextView(activity);
    }

    public static void d(Context context, long j, long j2, final int i, final ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (context == null || responseCallback == null) {
            return;
        }
        GetRunLevelReq getRunLevelReq = new GetRunLevelReq();
        if (j2 > System.currentTimeMillis()) {
            j2 = System.currentTimeMillis();
        }
        getRunLevelReq.setStartDay(ggl.b(j));
        getRunLevelReq.setEndDay(ggl.b(j2));
        getRunLevelReq.setQueryType(i);
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        getRunLevelReq.setTypes(hashSet);
        LogUtil.a("Track_RqDataUtils", getRunLevelReq.toString());
        jbs.a(context.getApplicationContext()).d(getRunLevelReq, new ResultCallback<GetRunLevelRsp>() { // from class: ruf.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(GetRunLevelRsp getRunLevelRsp) {
                if (getRunLevelRsp == null) {
                    return;
                }
                ruf.e(ResponseCallback.this, getRunLevelRsp.getData(), i);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("Track_RqDataUtils", "getRunLevel error:", th.getMessage());
                ResponseCallback.this.onResult(-1, Collections.emptyMap());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(ResponseCallback<Map<Long, IStorageModel>> responseCallback, Map<Integer, RunLevelDetail> map, int i) {
        responseCallback.onResult(0, c(map, i));
    }

    private static Map<Long, IStorageModel> c(Map<Integer, RunLevelDetail> map, int i) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Map.Entry<Integer, RunLevelDetail> entry : map.entrySet()) {
                long e = e(entry.getKey().intValue(), i);
                hashMap.put(Long.valueOf(e), new nnw(c(entry.getValue()).getCurrentRunLevel()));
            }
        }
        return hashMap;
    }

    private static RunningDayData c(RunLevelDetail runLevelDetail) {
        RunningDayData runningDayData = new RunningDayData();
        if (runLevelDetail != null) {
            runningDayData.setCurrentRunLevel(runLevelDetail.getCurrentRunLevel());
            runningDayData.setRealRunLevel(runLevelDetail.getRealRunLevel());
        }
        return runningDayData;
    }

    private static long e(int i, int i2) {
        long g;
        try {
            Date parse = new SimpleDateFormat(i2 == 1 ? "yyyyMM" : "yyyyMMdd").parse(String.valueOf(i));
            if (i2 == 1) {
                g = a(parse).getTime();
            } else {
                g = jec.g(parse.getTime());
            }
            return g;
        } catch (ParseException e) {
            LogUtil.b(e.getMessage(), new Object[0]);
            return 0L;
        }
    }

    public static Date a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, Math.round(jec.r(date) / 2.0f));
        return calendar.getTime();
    }

    public static RunningStateIndexData c(GetRunLevelRsp getRunLevelRsp) {
        RunningStateIndexData runningStateIndexData = new RunningStateIndexData();
        if (getRunLevelRsp != null) {
            if (getRunLevelRsp.getUserRunLevelData() != null) {
                UserRunLevelData userRunLevelData = getRunLevelRsp.getUserRunLevelData();
                RunningLevelCurrentData runningLevelCurrentData = new RunningLevelCurrentData();
                runningLevelCurrentData.setCurrentRunLevel(userRunLevelData.getCurrentRunLevel());
                runningLevelCurrentData.setRanking(userRunLevelData.getRanking());
                runningLevelCurrentData.setTimeForFiveKm(userRunLevelData.getTimeForFiveKm());
                runningLevelCurrentData.setTimeForTenKm(userRunLevelData.getTimeForTenKm());
                runningLevelCurrentData.setHalfMarathon(userRunLevelData.getHalfMarathon());
                runningLevelCurrentData.setMarathon(userRunLevelData.getMarathon());
                runningLevelCurrentData.setLastRecordDay(userRunLevelData.getLastRecordDay());
                runningLevelCurrentData.setLastTotalPoint(userRunLevelData.getLastTotalPoint());
                runningLevelCurrentData.setLastCurrentRunLevel(userRunLevelData.getLastCurrentRunLevel());
                runningLevelCurrentData.setLastRanking(userRunLevelData.getLastRanking());
                runningLevelCurrentData.setLastFitness(userRunLevelData.getLastFitness());
                runningLevelCurrentData.setLastFatigue(userRunLevelData.getLastFatigue());
                runningLevelCurrentData.setLastCondition(userRunLevelData.getLastCondition());
                runningStateIndexData.setRunningLevelCurrentData(runningLevelCurrentData);
            }
            if (getRunLevelRsp.getData() != null) {
                HashMap hashMap = new HashMap();
                for (Map.Entry<Integer, RunLevelDetail> entry : getRunLevelRsp.getData().entrySet()) {
                    if (entry.getValue() != null) {
                        RunningLevelSomeDayData runningLevelSomeDayData = new RunningLevelSomeDayData();
                        runningLevelSomeDayData.setCondition(entry.getValue().getCondition());
                        runningLevelSomeDayData.setCurrentRunLevel(entry.getValue().getCurrentRunLevel());
                        runningLevelSomeDayData.setFitness(entry.getValue().getFitness());
                        runningLevelSomeDayData.setFatigue(entry.getValue().getFatigue());
                        runningLevelSomeDayData.setTotalPoint(entry.getValue().getTotalPoint());
                        hashMap.put(entry.getKey(), runningLevelSomeDayData);
                    }
                }
                runningStateIndexData.setData(hashMap);
            }
        }
        return runningStateIndexData;
    }

    public static String e(int i, float f, float f2, boolean z) {
        String e;
        StringBuilder sb = new StringBuilder();
        String str = Constants.LINK;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    if (LanguageUtil.bc(BaseApplication.getContext())) {
                        sb.append("");
                        sb.append(UnitUtil.e(f, 1, 1));
                        sb.append(" ≤");
                    } else {
                        sb.append("≥");
                        sb.append(UnitUtil.e(f, 1, 1));
                    }
                } else {
                    LogUtil.h("Track_RqDataUtils", "setZone wrong type!");
                }
            } else if (LanguageUtil.bc(BaseApplication.getContext())) {
                sb.append("");
                if (!z && f2 < 0.0f && LanguageUtil.b(BaseApplication.getContext())) {
                    e = UnitUtil.e(-f2, 1, 1) + Constants.LINK;
                } else {
                    e = UnitUtil.e(f2, 1, 1);
                }
                sb.append(e);
                sb.append(" >");
            } else {
                sb.append(HiDataFilter.DataFilterExpression.LESS_THAN);
                sb.append(UnitUtil.e(f2, 1, 1));
            }
        } else if (LanguageUtil.b(BaseApplication.getContext())) {
            d(f, f2, z, sb);
        } else {
            sb.append("");
            sb.append(UnitUtil.e(f, 1, 1));
            sb.append(" ");
            if (!z) {
                str = " ~ ";
            }
            sb.append(str);
            sb.append(" ");
            sb.append(UnitUtil.e(f2, 1, 1));
        }
        return sb.toString();
    }

    private static void d(float f, float f2, boolean z, StringBuilder sb) {
        String e;
        String e2;
        sb.append("");
        if (!z && f < 0.0f) {
            e = UnitUtil.e(-f, 1, 1);
        } else {
            e = UnitUtil.e(f, 1, 1);
        }
        sb.append(e);
        sb.append(" ");
        sb.append(z ? Constants.LINK : " ~ ");
        sb.append(" ");
        if (!z && f2 < 0.0f) {
            e2 = Constants.LINK + UnitUtil.e(-f2, 1, 1);
        } else {
            e2 = UnitUtil.e(f2, 1, 1);
        }
        sb.append(e2);
    }

    public static String a(float f) {
        double a2 = UnitUtil.a(f, 1);
        if (Math.abs(a2) == 0.0d) {
            return UnitUtil.e(0.0d, 1, 0);
        }
        return UnitUtil.e(a2, 1, 1);
    }

    public static String c(float f) {
        return UnitUtil.e(UnitUtil.a(f, 1), 1, 1);
    }

    public static void d(Context context, CustomTitleBar customTitleBar, final String str) {
        if (context == null || customTitleBar == null || TextUtils.isEmpty(str)) {
            return;
        }
        customTitleBar.setRightButtonDrawable(context.getResources().getDrawable(R.drawable._2131430377_res_0x7f0b0be9), nsf.h(R$string.IDS_lactate_tittlebar_top));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: ruf.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.a(900)) {
                    LogUtil.h("Track_RqDataUtils", "downRemindClick is fast click");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    caj.a().a(str);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }
}
