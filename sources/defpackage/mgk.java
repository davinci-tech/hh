package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.bean.AnnualReport;
import com.huawei.pluginachievement.report.bean.AnnualReportMarathon;
import com.huawei.pluginachievement.report.bean.AnnualReportRun;
import com.huawei.pluginachievement.report.bean.MarathonGradleDetail;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mgk extends BaseCalculator {
    private CountDownLatch e = null;
    private int b = 0;
    private int c = 0;
    private PluginAchieveAdapter d = getPluginAchieveAdapter();

    /* renamed from: a, reason: collision with root package name */
    private Context f14974a = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
    }

    public void a(int i) {
        this.e = new CountDownLatch(i);
    }

    public void b() {
        CountDownLatch countDownLatch = this.e;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void a() {
        CountDownLatch countDownLatch = this.e;
        if (countDownLatch != null) {
            mht.c(countDownLatch, 8000L);
        }
    }

    public void d(int i, final int i2) {
        LogUtil.a("PLGACHIEVE_RunCalculator", "enter getAnnualRunSumData");
        if (this.d == null) {
            mht.d(this.e);
            LogUtil.h("PLGACHIEVE_RunCalculator", "achieveAdapter is null,return");
            return;
        }
        long b = mht.b(i2, true);
        long b2 = mht.b(i2, false);
        this.d.fetchSequenceDataBySportType(this.f14974a, b, b2, 258, new AchieveCallback() { // from class: mgk.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i3, Object obj) {
                mgk.this.a(obj, i2);
                mgk.this.b();
            }
        });
        if (i2 >= 2022) {
            int i3 = i2 - 1;
            this.d.fetchSequenceDataBySportType(this.f14974a, mht.b(i3, true), mht.b(i3, false), 258, new AchieveCallback() { // from class: mgk.2
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i4, Object obj) {
                    mgk.this.b(obj, i2);
                    mgk.this.b();
                }
            });
        }
        this.d.getTrackSumDistanceData(this.f14974a, mht.b(i, true), b2, 11, new AchieveCallback() { // from class: mgk.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i4, Object obj) {
                mgk.this.e(obj, i2);
                mgk.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj, int i) {
        try {
            ArrayList arrayList = new ArrayList(16);
            if (obj instanceof List) {
                arrayList.addAll((List) obj);
                if (koq.b(arrayList)) {
                    LogUtil.h("PLGACHIEVE_RunCalculator", "computeLastData dataInfos is empty");
                    return;
                }
                HashMap hashMap = new HashMap(2);
                int i2 = i - 1;
                mho.e(arrayList, i2, hashMap);
                if (hashMap.containsKey(Integer.valueOf(i2))) {
                    LogUtil.a("PLGACHIEVE_RunCalculator", "saveRunCompare paceMap ", hashMap.toString(), " lastYearPace ", Integer.valueOf(i2));
                    int intValue = ((Integer) hashMap.get(Integer.valueOf(i2))).intValue();
                    this.c = intValue;
                    a(i, this.b, intValue);
                    return;
                }
                return;
            }
            LogUtil.h("PLGACHIEVE_RunCalculator", "computeLastData data is not correct.");
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_RunCalculator", "getTrackListByRunType data ClassCastException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void e(T t, int i) {
        if (t == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "getTrackSumDistanceData obj null");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            if (t instanceof List) {
                arrayList.addAll((List) t);
                b(mho.b((List<HiHealthData>) arrayList), i);
            } else {
                LogUtil.h("PLGACHIEVE_RunCalculator", "computeTrackSumDistanceData data is not correct.");
            }
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_RunCalculator", "getTrackListByRunType ClassCastException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void a(T t, int i) {
        if (t == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "getTrackListData obj null");
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(16);
            if (t instanceof List) {
                arrayList.addAll((List) t);
                if (koq.b(arrayList)) {
                    LogUtil.h("PLGACHIEVE_RunCalculator", "computeTrackListData dataInfos is empty");
                    return;
                }
                HashMap hashMap = new HashMap(2);
                c(mho.e(arrayList, i, hashMap), i);
                if (hashMap.containsKey(Integer.valueOf(i))) {
                    LogUtil.a("PLGACHIEVE_RunCalculator", "saveRunCompare paceMap ", hashMap.toString(), " currentYear ", Integer.valueOf(i));
                    int intValue = ((Integer) hashMap.get(Integer.valueOf(i))).intValue();
                    this.b = intValue;
                    a(i, intValue, this.c);
                    return;
                }
                return;
            }
            LogUtil.h("PLGACHIEVE_RunCalculator", "computeTrackListData data is not correct.");
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_RunCalculator", "getTrackListByRunType data ClassCastException");
        }
    }

    private void a(int i, int i2, int i3) {
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 10025, String.valueOf(i2 - i3));
    }

    private void b(Map<String, Integer> map, int i) {
        int i2 = 0;
        int intValue = (map == null || !map.containsKey(String.valueOf(i))) ? 0 : map.get(String.valueOf(i)).intValue();
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2001, String.valueOf(intValue));
        if (i <= 2019) {
            insertData(i, EnumAnnualType.REPORT_RUN.value(), MainLoginCallBack.PHASE_NOCLOUD_NOLOGIN, mho.a(map));
            return;
        }
        if (map != null) {
            int i3 = i - 1;
            if (map.containsKey(String.valueOf(i3))) {
                i2 = map.get(String.valueOf(i3)).intValue();
            }
        }
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2007, String.valueOf(mht.d(intValue, i2, 3000)));
    }

    private void c(AnnualReport annualReport, int i) {
        if (annualReport == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "saveRunAndMarathonData annualReport is null");
            return;
        }
        AnnualReportRun reportRun = annualReport.getReportRun();
        AnnualReportMarathon reportMarathon = annualReport.getReportMarathon();
        d(reportRun, i);
        d(reportMarathon, i);
    }

    private void d(AnnualReportRun annualReportRun, int i) {
        if (annualReportRun == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "saveRunData annualReportRun is null");
            return;
        }
        addStarData("runStar", mho.d(annualReportRun.acquireTotalDistance()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2003, String.valueOf(annualReportRun.acquireMaxDistance()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2004, String.valueOf(annualReportRun.acquireMaxDistanceDay()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2002, String.valueOf(annualReportRun.acquireNumberOfTimes()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2005, String.valueOf(annualReportRun.acquireTimeOfDay()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2006, String.valueOf(annualReportRun.acquireDescription()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 10022, String.valueOf(annualReportRun.getRunNumberOfDays()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 10023, String.valueOf(annualReportRun.getMaxRunContinuousDays()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 10024, String.valueOf(annualReportRun.getBestPace()));
    }

    private void d(AnnualReportMarathon annualReportMarathon, int i) {
        if (annualReportMarathon == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "saveMarathonData annualReportMarathon is null");
        } else if (i <= 2019) {
            b(annualReportMarathon, i);
        } else {
            c(annualReportMarathon, i);
        }
    }

    private void b(AnnualReportMarathon annualReportMarathon, int i) {
        if (annualReportMarathon == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "saveMarathonData2019 annualReportMarathon is null");
            return;
        }
        String e = HiJsonUtil.e(annualReportMarathon.getHalfMarathon());
        String e2 = HiJsonUtil.e(annualReportMarathon.getFullMarathon());
        insertData(i, EnumAnnualType.REPORT_MARATHON.value(), 9004, e);
        insertData(i, EnumAnnualType.REPORT_MARATHON.value(), 9005, e2);
    }

    private void c(AnnualReportMarathon annualReportMarathon, int i) {
        if (annualReportMarathon == null) {
            LogUtil.h("PLGACHIEVE_RunCalculator", "saveMarathonData2020 annualReportMarathon is null");
            return;
        }
        MarathonGradleDetail halfMarathon = annualReportMarathon.getHalfMarathon();
        MarathonGradleDetail fullMarathon = annualReportMarathon.getFullMarathon();
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 9004, String.valueOf(halfMarathon.getTimes()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2008, String.valueOf(mht.d(halfMarathon.getBestRecord())));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2009, String.valueOf(halfMarathon.getBestTime()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 9005, String.valueOf(fullMarathon.getTimes()));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2010, String.valueOf(mht.d(fullMarathon.getBestRecord())));
        insertData(i, EnumAnnualType.REPORT_RUN.value(), 2011, String.valueOf(fullMarathon.getBestTime()));
    }
}
