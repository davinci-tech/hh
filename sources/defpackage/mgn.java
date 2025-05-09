package defpackage;

import android.content.Context;
import com.google.gson.JsonSyntaxException;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.bean.AnnualReportStepResult;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mgn extends BaseCalculator {

    /* renamed from: a, reason: collision with root package name */
    private PluginAchieveAdapter f14977a = getPluginAchieveAdapter();
    private Context d = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        a(i);
        e(i);
    }

    public void a(final int i) {
        LogUtil.a("PLGACHIEVE_StepCalculator", "getAnnualStepData enter");
        long b = mht.b(i, true);
        long b2 = mht.b(i, false);
        this.f14977a.readBestStepDayOfYear(this.d, b, b2, new AchieveCallback() { // from class: mgn.2
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                mgn.this.e(i, (int) obj);
            }
        });
        this.f14977a.readEveryMonthStepsOfYear(this.d, b, b2, new AchieveCallback() { // from class: mgn.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                mgn.this.c(i, obj);
            }
        });
        this.f14977a.fetchSequenceDataBySportType(this.d, b, b2, 257, new AchieveCallback() { // from class: mgs
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public final void onResponse(int i2, Object obj) {
                mgn.this.d(i, i2, obj);
            }
        });
    }

    /* synthetic */ void d(int i, int i2, Object obj) {
        List<HiHealthData> list;
        LogUtil.a("PLGACHIEVE_StepCalculator", "getAnnualStepData: ret -> " + i2);
        if (koq.e(obj, HiHealthData.class)) {
            list = (List) obj;
        } else {
            LogUtil.h("PLGACHIEVE_StepCalculator", "getAnnualStepData: data is not list");
            list = null;
        }
        insertData(i, EnumAnnualType.REPORT_STEP.value(), e(list));
        LogUtil.a("PLGACHIEVE_StepCalculator", "getAnnualStepData: end");
    }

    private Map<Integer, String> e(List<HiHealthData> list) {
        HiTrackMetaData hiTrackMetaData;
        HashMap hashMap = new HashMap(2);
        int i = 0;
        if (!koq.b(list)) {
            Iterator<HiHealthData> it = list.iterator();
            while (it.hasNext()) {
                try {
                    hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(it.next().getMetaData(), HiTrackMetaData.class);
                } catch (JsonSyntaxException e) {
                    LogUtil.b("PLGACHIEVE_StepCalculator", "computeWalkData: exception -> " + e.getMessage());
                    hiTrackMetaData = null;
                }
                if (hiTrackMetaData == null || mhu.b(hiTrackMetaData)) {
                    LogUtil.h("PLGACHIEVE_StepCalculator", "computeWalkData: Invalid data -> " + hiTrackMetaData);
                } else {
                    i++;
                }
            }
        } else {
            LogUtil.h("PLGACHIEVE_StepCalculator", "computeWalkData: walkDataList is null");
        }
        hashMap.put(3010, String.valueOf(i));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void e(int i, T t) {
        if (!(t instanceof AnnualReportStepResult)) {
            LogUtil.a("PLGACHIEVE_StepCalculator", "readBestStepDayOfYear obj not instanceof AnnualReportStepResult");
            return;
        }
        try {
            AnnualReportStepResult annualReportStepResult = (AnnualReportStepResult) t;
            int overGoal = annualReportStepResult.getOverGoal();
            if (i <= 2019) {
                insertData(i, EnumAnnualType.REPORT_SUMARY.value(), AuthCode.StatusCode.PERMISSION_NOT_EXIST, String.valueOf(overGoal));
            } else {
                insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_RTSP_CONN, String.valueOf(overGoal));
            }
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 3003, String.valueOf(annualReportStepResult.getValue()));
            insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_CONN_FAIL, String.valueOf(mht.a(annualReportStepResult.getTimestamp())));
            int c2 = mgx.c(annualReportStepResult.getReachStepDayList());
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 10026, String.valueOf(c2));
            LogUtil.a("PLGACHIEVE_StepCalculator", "maxStepContinuousDays reachDays == ", Integer.valueOf(c2));
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_StepCalculator", "readBestStepDayOfYear data ClassCastException");
        }
    }

    private void d(int i, List<HiHealthData> list) {
        int i2;
        if (list == null || list.size() < 2) {
            i2 = -1;
        } else {
            long startTime = ((HiHealthData) Collections.max(list, new c())).getStartTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(startTime);
            i2 = calendar.get(2) + 1;
        }
        insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC, String.valueOf(i2));
    }

    private void e(final int i) {
        LogUtil.a("PLGACHIEVE_StepCalculator", "enter getAnnualAvgStepData");
        if (this.f14977a == null) {
            LogUtil.h("PLGACHIEVE_StepCalculator", "mAchieveAdapter is null,return");
            return;
        }
        this.f14977a.getSumYearData(this.d, mht.b(2015, true), mht.b(i, false), 19, new AchieveCallback() { // from class: mgn.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                mgn.this.e((mgn) obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void e(T t, int i) {
        if (t == null) {
            LogUtil.h("PLGACHIEVE_StepCalculator", "saveAnnualAvgData is null,return");
            return;
        }
        try {
            List list = (List) t;
            if (koq.b(list)) {
                return;
            }
            int size = list.size();
            StringBuilder sb = new StringBuilder(size);
            LogUtil.a("PLGACHIEVE_StepCalculator", "saveAnnualAvgData size ", Integer.valueOf(size));
            for (int i2 = 0; i2 < size; i2++) {
                HiHealthData hiHealthData = (HiHealthData) list.get(i2);
                sb.append(mht.d(hiHealthData.getLong("start_time")) + "_" + mht.d(hiHealthData.getDouble("sport_walk_step_sum")));
                if (i2 != size - 1) {
                    sb.append(",");
                }
            }
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 9006, sb.toString());
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_StepCalculator", "getWeightData data ClassCastException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void c(int i, T t) {
        if (!(t instanceof List)) {
            LogUtil.h("PLGACHIEVE_StepCalculator", "getSeasonStep data error.");
            return;
        }
        List<HiHealthData> list = (List) t;
        d(i, list);
        a(i, list);
    }

    private void a(int i, List<HiHealthData> list) {
        if (list == null || list.size() < 2) {
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 3009, String.valueOf(0));
            insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_UPDATE, String.valueOf(-1));
            return;
        }
        Map<Integer, Float> c2 = mhv.c(list);
        int b = mhv.b(c2);
        if (c2 == null || !c2.containsKey(Integer.valueOf(b))) {
            return;
        }
        insertData(i, EnumAnnualType.REPORT_STEP.value(), 3009, String.valueOf(c2.get(Integer.valueOf(b)).intValue()));
        insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_UPDATE, String.valueOf(b));
    }

    public static class c implements Serializable, Comparator<HiHealthData> {
        private static final long serialVersionUID = 2022707229507542336L;

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return mlg.e(hiHealthData == null ? 0.0f : hiHealthData.getFloat("sum"), hiHealthData2 != null ? hiHealthData2.getFloat("sum") : 0.0f);
        }
    }
}
