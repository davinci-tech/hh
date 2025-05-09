package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class pxd {

    /* renamed from: a, reason: collision with root package name */
    private int f16319a;
    private boolean ae;
    private int b;
    private int c;
    private int d;
    private int e;
    private int s;
    private pvz x;
    private List<pvz> y;
    private List<pvz> u = new ArrayList(16);
    private int am = 0;
    private int at = 0;
    private int ad = 0;
    private int ar = 0;
    private int v = 0;
    private int bb = 0;
    private int ay = 0;
    private int ap = 0;
    private int ab = 0;
    private int ac = 0;
    private int av = 0;
    private int an = 0;
    private int aa = 0;
    private int bc = 0;
    private List<String> ak = new ArrayList(16);
    private List<pvz> w = new ArrayList(16);
    private List<pvz> al = new ArrayList(16);
    private List<pvz> az = new ArrayList(16);
    private List<pvz> ao = new ArrayList(16);
    private List<pvz> af = new ArrayList(16);
    private int r = 0;
    private int aq = 0;
    private boolean ai = false;
    private double au = 0.0d;
    private String z = "";
    private String ba = "";
    private String ax = "";
    private String aw = "";
    private int h = 0;
    private int o = 0;
    private int l = 0;
    private int k = 0;
    private int t = 0;
    private int n = 0;
    private int g = 0;
    private int p = 0;
    private int i = 0;
    private int q = 0;
    private int j = 0;
    private int m = 0;
    private int aj = 0;
    private Context f = BaseApplication.getContext();
    private pxb ah = new pxb();
    private pxi ag = pxi.e();
    private pwx as = pwx.d(this.f);

    static /* synthetic */ int e(pxd pxdVar) {
        int i = pxdVar.aj + 1;
        pxdVar.aj = i;
        return i;
    }

    public void a(String str) {
        this.z = str;
    }

    public void d(String str) {
        this.ba = str;
    }

    public List<String> h() {
        return (List) ResultUtils.a(this.ak);
    }

    public int c() {
        int i = this.av;
        int i2 = i < 10 ? 100 - (i * 2) : 90 - i;
        if (i2 < 0) {
            i2 = 0;
        }
        return ((Integer) ResultUtils.a(Integer.valueOf((i == 0 && this.ap == 0) ? 0 : i2))).intValue();
    }

    public double o() {
        return ((Double) ResultUtils.a(Double.valueOf(this.au))).doubleValue();
    }

    public int i() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.aa))).intValue();
    }

    public int l() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.bc))).intValue();
    }

    /* renamed from: pxd$4, reason: invalid class name */
    class AnonymousClass4 implements CommonUiBaseResponse {
        final /* synthetic */ CommonUiBaseResponse b;

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "getMonthBaseTime back, errorCode = ", Integer.valueOf(i));
            CommonUiBaseResponse commonUiBaseResponse = this.b;
            if (commonUiBaseResponse != null) {
                commonUiBaseResponse.onResponse(i, obj);
            }
        }
    }

    public void d(long j, int i, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestCoreSleepDetailUiData paraUiDataType = ", Integer.valueOf(i));
        this.aj = 0;
        s();
        if (i == 1) {
            e(j, commonUiBaseResponse);
        } else if (i == 2) {
            d(j, commonUiBaseResponse);
        } else {
            if (i != 4) {
                return;
            }
            c(j, commonUiBaseResponse);
        }
    }

    private void e(long j, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "handleDayType enter, start time = ", Long.valueOf(j));
        d(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_HISTOGRAM, new CommonUiBaseResponse() { // from class: pxd.3
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                CommonUiBaseResponse commonUiBaseResponse2;
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestCoreSleepHistogramData back,errorCode = ", Integer.valueOf(i));
                pxd.e(pxd.this);
                LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "mRequestReportCount = ", Integer.valueOf(pxd.this.aj));
                if (pxd.this.aj != 2 || (commonUiBaseResponse2 = commonUiBaseResponse) == null) {
                    return;
                }
                commonUiBaseResponse2.onResponse(i, obj);
            }
        });
        b(j, new CommonUiBaseResponse() { // from class: pxd.2
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                CommonUiBaseResponse commonUiBaseResponse2;
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestCoreSleepDayTotalData back, errorCode = ", Integer.valueOf(i));
                pxd.e(pxd.this);
                LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "mRequestReportCount = ", Integer.valueOf(pxd.this.aj));
                if (pxd.this.aj != 2 || (commonUiBaseResponse2 = commonUiBaseResponse) == null) {
                    return;
                }
                commonUiBaseResponse2.onResponse(i, obj);
            }
        });
    }

    private void d(long j, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "handleWeekType enter, start time = ", Long.valueOf(j));
        d(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM, new CommonUiBaseResponse() { // from class: pxd.5
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "request week histogram back, errorCode = ", Integer.valueOf(i));
                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                if (commonUiBaseResponse2 != null) {
                    commonUiBaseResponse2.onResponse(i, obj);
                }
            }
        });
    }

    private void c(long j, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "handleYearType enter, start time = ", Long.valueOf(j));
        d(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM, new CommonUiBaseResponse() { // from class: pxd.1
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "request year histogram back, errorCode = ", Integer.valueOf(i));
                CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                if (commonUiBaseResponse2 != null) {
                    commonUiBaseResponse2.onResponse(i, obj);
                }
            }
        });
    }

    private void d(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestCoreSleepHistogramData enter, start time = ", Long.valueOf(j));
        if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM) {
            c(j, fitnessQueryId, commonUiBaseResponse);
        } else {
            a(j, fitnessQueryId, commonUiBaseResponse);
        }
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "Leave requestCoreSleepHistogramData");
    }

    private void e(List<SleepTotalData> list) {
        if (list != null) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "Enter processSleepHistogramData paramListData.size: ", Integer.valueOf(list.size()));
            for (int i = 0; i < list.size(); i++) {
                SleepTotalData sleepTotalData = list.get(i);
                c(i, sleepTotalData);
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "sleepTotalData.getType():", Integer.valueOf(sleepTotalData.getType()));
                if (sleepTotalData.getType() == 31) {
                    this.ai = true;
                }
            }
            return;
        }
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "paramListData is null! ");
    }

    private void c(int i, SleepTotalData sleepTotalData) {
        pvz pvzVar = new pvz();
        pvzVar.a(i);
        int i2 = i + 1;
        pvzVar.e(i2);
        pvzVar.d(sleepTotalData.getSleepDayTime());
        pvzVar.e(sleepTotalData.getCoreSleepFallTime());
        pvzVar.b(sleepTotalData.getCoreSleepWakeupTime());
        pvz pvzVar2 = new pvz();
        pvzVar2.a(i);
        pvzVar2.e(i2);
        pvz pvzVar3 = new pvz();
        pvzVar3.a(i);
        pvzVar3.e(i2);
        pvz pvzVar4 = new pvz();
        pvzVar4.a(i);
        pvzVar4.e(i2);
        pvz pvzVar5 = new pvz();
        pvzVar5.a(i);
        pvzVar5.e(i2);
        pvzVar.b(sleepTotalData.getDeepSleepTime());
        this.w.add(pvzVar);
        pvzVar3.b(sleepTotalData.getShallowSleepTime());
        this.al.add(pvzVar3);
        pvzVar2.b(sleepTotalData.getNoonSleepTime());
        this.af.add(pvzVar2);
        pvzVar4.b(sleepTotalData.getSlumberSleepTime());
        this.ao.add(pvzVar4);
        pvzVar5.b(sleepTotalData.getWakeupDuration());
        this.az.add(pvzVar5);
    }

    public void b(long j, final CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestCoreSleepDayTotalData, start time = ", Long.valueOf(j));
        pxi pxiVar = this.ag;
        if (pxiVar != null) {
            pxiVar.a(j, FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_STATISTIC, FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME, new CommonUiBaseResponse() { // from class: pxd.6
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestGetCoreSleepDetailData errorCode = ", Integer.valueOf(i));
                    pxd.this.r();
                    if (i == 0 && obj != null) {
                        List list = (List) obj;
                        if (list.size() != 0) {
                            pxd.this.b((List<SleepTotalData>) list);
                        }
                        LogUtil.a("FitnessOldCoreSleepDetailInteractor", "requestSleepDetailTotalData requestGetSleepDetailData : TT = ", Integer.valueOf(pxd.this.at), " DT = ", Integer.valueOf(pxd.this.ad), " ST = ", Integer.valueOf(pxd.this.ar), " WD = ", Integer.valueOf(pxd.this.bb), " ST = ", Integer.valueOf(pxd.this.ap), " WT = ", Integer.valueOf(pxd.this.ay), " DP = ", Integer.valueOf(pxd.this.ac), ", SF = ", Integer.valueOf(pxd.this.av), " S = ", Integer.valueOf(pxd.this.an), " FT = ", Integer.valueOf(pxd.this.aa), " WT = ", Integer.valueOf(pxd.this.bc), " DT = ", Integer.valueOf(pxd.this.v), " ST = ", Integer.valueOf(pxd.this.aq), " VD = ", Double.valueOf(pxd.this.au));
                    } else {
                        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "requestGetCoreSleepDetailData else.");
                    }
                    if (commonUiBaseResponse != null) {
                        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "updateResponse.onResponse !");
                        commonUiBaseResponse.onResponse(i, obj);
                    }
                }
            });
        }
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "Leave requestSleepDetailTotalData.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<SleepTotalData> list) {
        SleepTotalData sleepTotalData = list.get(0);
        this.at = sleepTotalData.getTotalSleepTime();
        this.ad = sleepTotalData.getDeepSleepTime();
        this.ar = sleepTotalData.getShallowSleepTime();
        this.bb = sleepTotalData.getWakeupDuration();
        this.ap = sleepTotalData.getSlumberSleepTime();
        this.v = sleepTotalData.getNoonSleepTime();
        this.ay = sleepTotalData.getWakeupTimes();
        this.ac = sleepTotalData.getDeepSleepPart();
        this.av = sleepTotalData.getSnoreFreq();
        this.an = sleepTotalData.getScore();
        this.aq = sleepTotalData.getType();
        this.au = sleepTotalData.getValidData();
        int fallTime = sleepTotalData.getFallTime();
        int i = fallTime + 1200;
        this.aa = i;
        if (i > 1440) {
            this.aa = fallTime - 240;
        }
        int wakeUpTime = sleepTotalData.getWakeUpTime();
        int i2 = wakeUpTime + 1200;
        this.bc = i2;
        if (i2 > 1440) {
            this.bc = wakeUpTime - 240;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.at = 0;
        this.ad = 0;
        this.ar = 0;
        this.bb = 0;
        this.ap = 0;
        this.v = 0;
        this.ay = 0;
        this.ac = 0;
        this.av = 0;
        this.an = 0;
        this.aa = 0;
        this.bc = 0;
        this.aq = 0;
        this.au = 0.0d;
    }

    private void t() {
        this.at = 0;
        this.am = 0;
        this.ad = 0;
        this.ar = 0;
        this.v = 0;
        this.bb = 0;
        this.ay = 0;
        this.ap = 0;
        this.ac = 0;
        this.av = 0;
        this.an = 0;
        this.aa = 0;
        this.bc = 0;
        this.aq = 0;
        this.ai = false;
        this.r = 0;
        this.h = 0;
        this.o = 0;
        this.k = 0;
        this.t = 0;
        this.l = 0;
        this.n = 0;
        this.g = 0;
        this.p = 0;
        this.i = 0;
        this.q = 0;
        this.j = 0;
        this.m = 0;
        this.b = 0;
        this.d = 0;
        this.e = 0;
    }

    private void s() {
        c("");
        this.aw = "";
    }

    public int e() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.ap))).intValue();
    }

    public int j() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.at))).intValue();
    }

    public int a() {
        int i = this.at;
        return ((Integer) ResultUtils.a(Integer.valueOf(i == 0 ? 0 : Math.round((this.ad / i) * 100.0f)))).intValue();
    }

    public int f() {
        int i = this.at;
        return ((Integer) ResultUtils.a(Integer.valueOf(i == 0 ? 0 : Math.round((this.ar / i) * 100.0f)))).intValue();
    }

    public int d() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.at == 0 ? 0 : (100 - f()) - a()))).intValue();
    }

    public int m() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.ay))).intValue();
    }

    private void d(List<pvz> list) {
        this.u = list;
    }

    public int b() {
        return ((Integer) ResultUtils.a(Integer.valueOf(this.n))).intValue();
    }

    public String g() {
        return (String) ResultUtils.a(this.ax);
    }

    private void c(String str) {
        this.ax = str;
    }

    public boolean k() {
        return ((Boolean) ResultUtils.a(Boolean.valueOf(this.ai))).booleanValue();
    }

    public void e(Date date, int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (this.as != null) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "enter requestSleepSuggestString()");
            s();
            this.as.a(date, i, new CommonUiBaseResponse() { // from class: pxe
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public final void onResponse(int i2, Object obj) {
                    pxd.this.a(commonUiBaseResponse, i2, obj);
                }
            });
        }
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "leave getSleepSuggestString()");
    }

    /* synthetic */ void a(CommonUiBaseResponse commonUiBaseResponse, int i, Object obj) {
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "getSleepAdvice day or week");
        e(i, obj, commonUiBaseResponse);
    }

    private void e(int i, Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        if (i == 0 && (obj instanceof CoreSleepTotalData)) {
            CoreSleepTotalData coreSleepTotalData = (CoreSleepTotalData) obj;
            c(a(coreSleepTotalData.getAdNum0(), coreSleepTotalData));
            this.aw = this.ah.i(coreSleepTotalData.getAdNum1());
        } else {
            c("");
            this.aw = "";
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(i, obj);
        }
    }

    private String a(int i, CoreSleepTotalData coreSleepTotalData) {
        if (i < 12000) {
            return b(i);
        }
        return d(i, coreSleepTotalData);
    }

    private String d(int i, CoreSleepTotalData coreSleepTotalData) {
        if (coreSleepTotalData == null) {
            return "";
        }
        switch ((i % 1000) / 100) {
            case 0:
                return this.f.getResources().getString(pwt.e(i), this.ah.c(2));
            case 1:
                return this.ah.b(i, coreSleepTotalData);
            case 2:
                return this.ah.g(i, coreSleepTotalData);
            case 3:
                return this.ah.h(i, coreSleepTotalData);
            case 4:
                return this.ah.e(i, coreSleepTotalData);
            case 5:
                return this.ah.c(i, coreSleepTotalData);
            case 6:
                return this.ah.d(i, coreSleepTotalData);
            case 7:
                return this.ah.a(i, coreSleepTotalData);
            case 8:
                int i2 = this.b;
                int i3 = this.d;
                return this.ah.a(i, coreSleepTotalData, i2 + i3 + this.e, b());
            default:
                return this.f.getResources().getString(pwt.e(i));
        }
    }

    private String b(int i) {
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " combineDayString index = ", Integer.valueOf(i));
        String string = this.f.getResources().getString(pwt.e(i));
        if (e(i, pwt.g())) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " ContainIndex totalSleepIndex");
            return String.format(Locale.ENGLISH, string, scn.a(j()));
        }
        if (e(i, pwt.d())) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " ContainIndex dreamRateIndex");
            return String.format(Locale.ENGLISH, string, UnitUtil.e(d(), 2, 0));
        }
        if (e(i, pwt.a())) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " ContainIndex fallAsleepIndex");
            return String.format(Locale.ENGLISH, string, this.z);
        }
        if (e(i, pwt.i())) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " ContainIndex wakeIndex");
            return String.format(Locale.ENGLISH, string, this.ba);
        }
        if (e(i, pwt.f())) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " ContainIndex wakeUpTimesIndex");
            String quantityString = this.f.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, m(), UnitUtil.e(m(), 1, 0));
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "wakeUpTimes: ", quantityString);
            return String.format(Locale.ENGLISH, string, quantityString);
        }
        if (e(i, pwt.c())) {
            return String.format(Locale.ENGLISH, string, UnitUtil.e(a(), 2, 0));
        }
        if (e(i, pwt.b())) {
            return String.format(Locale.ENGLISH, string, UnitUtil.e(0.0d, 1, 0));
        }
        if (!e(i, pwt.e())) {
            return string;
        }
        return String.format(Locale.ENGLISH, string, UnitUtil.e(c(), 1, 0) + " " + this.f.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, c()));
    }

    private boolean e(int i, int[] iArr) {
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " isContainIndex()  targetIndex = ", Integer.valueOf(i));
        for (int i2 : iArr) {
            if (i == i2) {
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " isContainIndex()  return true ");
                return true;
            }
        }
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " isContainIndex()  return false ");
        return false;
    }

    private void c(List<pvz> list) {
        this.z = "";
        this.ba = "";
        if (list == null || list.size() == 0) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "sleepHistogramData size = 0");
            return;
        }
        Map<String, Integer> b = pxb.b(list);
        if (b == null) {
            LogUtil.h("UIHLH_FitnessOldCoreSleepDetailInteractor", "timeMap is null!");
            return;
        }
        int intValue = b.get("core_sleep_end_time_key").intValue();
        int i = intValue / 60;
        int i2 = i + 20;
        if (i2 >= 24) {
            i2 = i - 4;
        }
        String a2 = scn.a(scn.t(i2) + ":" + scn.t(intValue % 60));
        this.z = a2;
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "advice mFallAsleepTime = ", a2);
        int intValue2 = b.get("core_sleep_end_time_key").intValue();
        int i3 = intValue2 / 60;
        int i4 = i3 + 20;
        if (i4 >= 24) {
            i4 = i3 - 4;
        }
        String a3 = scn.a(scn.t(i4) + ":" + scn.t(intValue2 % 60));
        this.ba = a3;
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "advice mWakeTime = ", a3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        int i3 = this.f16319a;
        if (i != 0) {
            int i4 = i3 / i;
            int i5 = i4 + 1200;
            if (i5 > 1440) {
                i5 = i4 - 240;
            }
            this.f16319a = i5;
        }
        int i6 = this.c;
        if (i2 != 0) {
            int i7 = i6 / i2;
            int i8 = i7 + 1200;
            if (i8 > 1440) {
                i8 = i7 - 240;
            }
            this.c = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.f16319a = 0;
        this.c = 0;
    }

    private void c(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, CommonUiBaseResponse commonUiBaseResponse) {
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "Enter getWeekBaseTime().");
        p();
        HiHealthManager.d(this.f).aggregateHiHealthData(this.ah.b(j, 7), new a(this, fitnessQueryId, j, commonUiBaseResponse, null));
    }

    /* renamed from: pxd$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[FitnessDataQueryDefine.FitnessQueryId.values().length];
            b = iArr;
            try {
                iArr[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_HISTOGRAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, final FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, final CommonUiBaseResponse commonUiBaseResponse) {
        if (fitnessQueryId == FitnessDataQueryDefine.FitnessQueryId.FITNESS_TYPE_SLEEP_DAY_HISTOGRAM) {
            j = jec.e(new Date(j * 1000));
        }
        long j2 = j;
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "getData queryId = ", fitnessQueryId);
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "getData paraStartTime = ", Long.valueOf(j2), " para StartDate = ", new Date(1000 * j2));
        pxi pxiVar = this.ag;
        if (pxiVar != null) {
            pxiVar.a(j2, fitnessQueryId, FitnessDataQueryDefine.FitnessSleepTotalDataId.FITNESS_UNSPECIFIC_SLEEP_TIME, new CommonUiBaseResponse() { // from class: pxd.8
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "getData response back.");
                    int i2 = AnonymousClass9.b[fitnessQueryId.ordinal()];
                    if (i2 != 1) {
                        if (i2 == 2) {
                            pxd.this.b(i, obj, commonUiBaseResponse);
                            return;
                        }
                        if (i2 != 3) {
                            if (i2 == 4) {
                                pxd.this.a(i, obj, commonUiBaseResponse);
                                return;
                            }
                            CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                            if (commonUiBaseResponse2 != null) {
                                commonUiBaseResponse2.onResponse(i, obj);
                                return;
                            }
                            return;
                        }
                    }
                    pxd.this.d(i, obj, commonUiBaseResponse);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        q();
        t();
        LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "handleYearData errorCode = ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof List)) {
            List<SleepTotalData> list = (List) obj;
            e(list);
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            for (SleepTotalData sleepTotalData : list) {
                if (sleepTotalData.getDeepSleepTime() > 0) {
                    i4 += sleepTotalData.getDeepSleepTime();
                    i2++;
                }
                if (sleepTotalData.getShallowSleepTime() > 0) {
                    i5 += sleepTotalData.getShallowSleepTime();
                    i3++;
                }
                if (sleepTotalData.getNoonSleepTime() > 0) {
                    i11 += sleepTotalData.getNoonSleepTime();
                    i10++;
                }
                if (sleepTotalData.getWakeupTimes() > 0) {
                    i8 += sleepTotalData.getWakeupTimes();
                    i6++;
                }
                if (sleepTotalData.getSlumberSleepTime() > 0) {
                    i7 += sleepTotalData.getSlumberSleepTime();
                    i9++;
                }
            }
            e(i2, i3, i4, i5);
            b(i6, i7, i8, i9);
            b(i10, i11);
            LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "Deep = ", Integer.valueOf(this.h), " Shallow = ", Integer.valueOf(this.o), " WakeTimes = ", Integer.valueOf(this.t), " NoonSleepTime = ", Integer.valueOf(this.m));
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(i, this.w);
        }
    }

    private void b(int i, int i2) {
        this.m = i2;
        if (i > 0) {
            this.m = Math.round(i2 / i);
        }
    }

    private void b(int i, int i2, int i3, int i4) {
        this.t = i3;
        if (i > 0) {
            this.t = Math.round(i3 / i);
        }
        this.l = i2;
        if (i4 > 0) {
            this.l = Math.round(i2 / i4);
        }
    }

    private void e(int i, int i2, int i3, int i4) {
        this.h = i3;
        if (i > 0) {
            this.h = Math.round(i3 / i);
        }
        this.o = i4;
        if (i2 > 0) {
            this.o = Math.round(i4 / i2);
        }
    }

    private void q() {
        this.w.clear();
        this.al.clear();
        this.az.clear();
        this.ao.clear();
        this.af.clear();
    }

    class d {

        /* renamed from: a, reason: collision with root package name */
        private int f16322a;
        private int aa;
        private int ab;
        private int ac;
        private int ad;
        private int ag;
        private int ah;
        private int ai;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int l;
        private int m;
        private int n;
        private int o;
        private int p;
        private int q;
        private int r;
        private int s;
        private int t;
        private int u;
        private int v;
        private int w;
        private int x;
        private int y;
        private int z;

        private d() {
            this.f16322a = 0;
            this.f = 0;
            this.g = 0;
            this.y = 0;
            this.i = 0;
            this.p = 0;
            this.r = 0;
            this.ad = 0;
            this.v = 0;
            this.q = 0;
            this.e = 0;
            this.m = 0;
            this.l = 0;
            this.z = 0;
            this.c = 0;
            this.ah = 0;
            this.ai = 0;
            this.ag = 0;
            this.u = 0;
            this.t = 0;
            this.x = 0;
            this.j = 0;
            this.ab = 0;
            this.aa = 0;
            this.h = 0;
            this.k = 0;
            this.o = 0;
            this.ac = 0;
            this.w = 0;
            this.n = 0;
            this.s = 0;
            this.d = 0;
        }

        /* synthetic */ d(pxd pxdVar, AnonymousClass4 anonymousClass4) {
            this();
        }

        static /* synthetic */ int a(d dVar, int i) {
            int i2 = dVar.u + i;
            dVar.u = i2;
            return i2;
        }

        static /* synthetic */ int af(d dVar) {
            int i = dVar.r;
            dVar.r = i + 1;
            return i;
        }

        static /* synthetic */ int ah(d dVar) {
            int i = dVar.e;
            dVar.e = i + 1;
            return i;
        }

        static /* synthetic */ int ai(d dVar) {
            int i = dVar.ad;
            dVar.ad = i + 1;
            return i;
        }

        static /* synthetic */ int aj(d dVar) {
            int i = dVar.l;
            dVar.l = i + 1;
            return i;
        }

        static /* synthetic */ int an(d dVar) {
            int i = dVar.m;
            dVar.m = i + 1;
            return i;
        }

        static /* synthetic */ int ao(d dVar) {
            int i = dVar.z;
            dVar.z = i + 1;
            return i;
        }

        static /* synthetic */ int b(d dVar, int i) {
            int i2 = dVar.ag + i;
            dVar.ag = i2;
            return i2;
        }

        static /* synthetic */ int c(d dVar, int i) {
            int i2 = dVar.ai + i;
            dVar.ai = i2;
            return i2;
        }

        static /* synthetic */ int d(d dVar, int i) {
            int i2 = dVar.ah + i;
            dVar.ah = i2;
            return i2;
        }

        static /* synthetic */ int e(d dVar, int i) {
            int i2 = dVar.t + i;
            dVar.t = i2;
            return i2;
        }

        static /* synthetic */ int f(d dVar, int i) {
            int i2 = dVar.j + i;
            dVar.j = i2;
            return i2;
        }

        static /* synthetic */ int g(d dVar, int i) {
            int i2 = dVar.x + i;
            dVar.x = i2;
            return i2;
        }

        static /* synthetic */ int h(d dVar, int i) {
            int i2 = dVar.ab + i;
            dVar.ab = i2;
            return i2;
        }

        static /* synthetic */ int i(d dVar) {
            int i = dVar.v;
            dVar.v = i + 1;
            return i;
        }

        static /* synthetic */ int i(d dVar, int i) {
            int i2 = dVar.n + i;
            dVar.n = i2;
            return i2;
        }

        static /* synthetic */ int j(d dVar) {
            int i = dVar.g;
            dVar.g = i + 1;
            return i;
        }

        static /* synthetic */ int j(d dVar, int i) {
            int i2 = dVar.s + i;
            dVar.s = i2;
            return i2;
        }

        static /* synthetic */ int k(d dVar) {
            int i = dVar.q;
            dVar.q = i + 1;
            return i;
        }

        static /* synthetic */ int k(d dVar, int i) {
            int i2 = dVar.h + i;
            dVar.h = i2;
            return i2;
        }

        static /* synthetic */ int l(d dVar, int i) {
            int i2 = dVar.o + i;
            dVar.o = i2;
            return i2;
        }

        static /* synthetic */ int m(d dVar, int i) {
            int i2 = dVar.d + i;
            dVar.d = i2;
            return i2;
        }

        static /* synthetic */ int n(d dVar, int i) {
            int i2 = dVar.aa + i;
            dVar.aa = i2;
            return i2;
        }

        static /* synthetic */ int o(d dVar) {
            int i = dVar.p;
            dVar.p = i + 1;
            return i;
        }

        static /* synthetic */ int o(d dVar, int i) {
            int i2 = dVar.k + i;
            dVar.k = i2;
            return i2;
        }

        static /* synthetic */ int p(d dVar) {
            int i = dVar.i;
            dVar.i = i + 1;
            return i;
        }

        static /* synthetic */ int r(d dVar) {
            int i = dVar.f;
            dVar.f = i + 1;
            return i;
        }

        static /* synthetic */ int r(d dVar, int i) {
            int i2 = dVar.ac + i;
            dVar.ac = i2;
            return i2;
        }

        static /* synthetic */ int t(d dVar, int i) {
            int i2 = dVar.w + i;
            dVar.w = i2;
            return i2;
        }

        static /* synthetic */ int w(d dVar) {
            int i = dVar.f16322a;
            dVar.f16322a = i + 1;
            return i;
        }

        static /* synthetic */ int z(d dVar) {
            int i = dVar.y;
            dVar.y = i + 1;
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj, CommonUiBaseResponse commonUiBaseResponse) {
        q();
        t();
        if (i == 0 && (obj instanceof List)) {
            List<SleepTotalData> list = (List) obj;
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", " handleWeekAndMonthData retListData.size = ", Integer.valueOf(list.size()));
            e(list);
            d dVar = new d(this, null);
            Iterator<SleepTotalData> it = list.iterator();
            while (it.hasNext()) {
                d(it.next(), dVar);
            }
            a(dVar);
            e(dVar);
        }
        if (commonUiBaseResponse != null) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "mDeepSleepHistogramData.size: ", Integer.valueOf(this.w.size()));
            commonUiBaseResponse.onResponse(i, this.w);
        }
    }

    private void d(SleepTotalData sleepTotalData, d dVar) {
        if (k()) {
            if (sleepTotalData.getType() == 31) {
                LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "is a Science Sleep data");
                a(sleepTotalData, dVar);
                if (sleepTotalData.getDeepSleepTime() > 0) {
                    d.d(dVar, sleepTotalData.getDeepSleepTime());
                    b(sleepTotalData, dVar);
                }
                if (sleepTotalData.getShallowSleepTime() > 0) {
                    d.c(dVar, sleepTotalData.getShallowSleepTime());
                    i(sleepTotalData, dVar);
                }
                if (sleepTotalData.getSlumberSleepTime() > 0) {
                    d.b(dVar, sleepTotalData.getSlumberSleepTime());
                    d.a(dVar, sleepTotalData.getSlumberSleepTime());
                    d.i(dVar);
                    d.j(dVar);
                }
                if (sleepTotalData.getScore() > 0) {
                    d.e(dVar, sleepTotalData.getScore());
                    d.k(dVar);
                }
                e(sleepTotalData, dVar);
                return;
            }
            return;
        }
        c(sleepTotalData, dVar);
    }

    private void i(SleepTotalData sleepTotalData, d dVar) {
        d.j(dVar, sleepTotalData.getShallowSleepTime());
        d.o(dVar);
        d.r(dVar);
    }

    private void b(SleepTotalData sleepTotalData, d dVar) {
        d.i(dVar, sleepTotalData.getDeepSleepTime());
        d.p(dVar);
        d.w(dVar);
    }

    private void e(d dVar) {
        float f = dVar.ah;
        float f2 = dVar.ai;
        float f3 = dVar.ag;
        if (dVar.f16322a > 0) {
            this.b = Math.round(f / dVar.f16322a);
        }
        if (dVar.f > 0) {
            this.e = Math.round(f2 / dVar.f);
        }
        if (dVar.g > 0) {
            this.d = Math.round(f3 / dVar.g);
        }
        LogUtil.c("UIHLH_FitnessOldCoreSleepDetailInteractor", "handleWeekAndMonthData:mTotalSleepTime = ", Integer.valueOf(dVar.x), " mDeepSleepTime = ", Integer.valueOf(dVar.n), " mShallowSleepTime = ", Integer.valueOf(dVar.s), " mDeepSleepPart = ", Integer.valueOf(dVar.j), " mWakeupDuration = ", Integer.valueOf(dVar.ab), " mWakeupTimes = ", Integer.valueOf(dVar.aa), " mScore = ", Integer.valueOf(dVar.t), " mBreathQualityData = ", Integer.valueOf(dVar.d), " mDaySleepTime = ", Integer.valueOf(dVar.h), " mDailyTotalSleepTime = ", Integer.valueOf(this.r), " mDailyDeepSleepTime = ", Integer.valueOf(this.h), " mDailyShallowSleepTime = ", Integer.valueOf(this.o), " mDailySleepPart = ", Integer.valueOf(this.k), " mDailyWakeupTimes = ", Integer.valueOf(this.t), " mDailyScore = ", Integer.valueOf(this.n), " mDailyBreathQuality = ", Integer.valueOf(this.j), " mDailyNoonSleepTime = ", Integer.valueOf(this.m), " mDailyFallTime = ", Integer.valueOf(this.g), " mDailyWakeUpTime = ", Integer.valueOf(this.p), " mDailyFallScore = ", Integer.valueOf(this.i), " mDailyWakeUpScore = ", Integer.valueOf(this.q));
    }

    private void a(d dVar) {
        this.r = dVar.x;
        if (dVar.y > 0) {
            this.r = Math.round(dVar.x / dVar.y);
        }
        this.h = dVar.n;
        if (dVar.i > 0) {
            this.h = Math.round(dVar.n / dVar.i);
        }
        this.o = dVar.s;
        if (dVar.p > 0) {
            this.o = Math.round(dVar.s / dVar.p);
        }
        this.k = dVar.j;
        if (dVar.r > 0) {
            this.k = Math.round(dVar.j / dVar.r);
        }
        this.t = dVar.ad > 0 ? Math.round(dVar.aa / dVar.ad) : dVar.aa;
        this.l = dVar.v > 0 ? Math.round(dVar.u / dVar.v) : dVar.u;
        this.n = dVar.q > 0 ? Math.round(dVar.t / dVar.q) : dVar.t;
        this.j = dVar.d;
        if (dVar.e > 0) {
            this.j = Math.round(dVar.d / dVar.e);
        }
        this.m = dVar.h;
        if (dVar.m > 0) {
            this.m = Math.round(dVar.h / dVar.m);
        }
        this.g = dVar.k;
        if (dVar.l > 0) {
            int round = Math.round(dVar.k / dVar.l);
            int i = round + 1200;
            this.g = i;
            if (i > 1440) {
                this.g = round - 240;
            }
            this.i = Math.round(dVar.o / dVar.l);
        }
        this.p = dVar.ac;
        if (dVar.z > 0) {
            int round2 = Math.round(dVar.ac / dVar.z);
            int i2 = round2 + 1200;
            this.p = i2;
            if (i2 > 1440) {
                this.p = round2 - 240;
            }
            this.q = Math.round(dVar.w / dVar.z);
        }
    }

    private void c(SleepTotalData sleepTotalData, d dVar) {
        if (sleepTotalData.getTotalSleepTime() > 0) {
            d.g(dVar, sleepTotalData.getTotalSleepTime());
            d.z(dVar);
        }
        if (sleepTotalData.getDeepSleepTime() > 0) {
            d.i(dVar, sleepTotalData.getDeepSleepTime());
            d.p(dVar);
        }
        if (sleepTotalData.getShallowSleepTime() > 0) {
            d.j(dVar, sleepTotalData.getShallowSleepTime());
            d.o(dVar);
        }
        if (sleepTotalData.getWakeupDuration() >= 0) {
            d.h(dVar, sleepTotalData.getWakeupDuration());
            this.bb = dVar.ab;
        }
        if (sleepTotalData.getWakeupTimes() > 0) {
            d.n(dVar, sleepTotalData.getWakeupTimes());
            d.ai(dVar);
        }
    }

    private void e(SleepTotalData sleepTotalData, d dVar) {
        int snoreFreq = sleepTotalData.getSnoreFreq();
        if (snoreFreq < 10) {
            dVar.c = 100 - (snoreFreq * 2);
        } else {
            dVar.c = 90 - snoreFreq;
        }
        if (dVar.c < 0 || (snoreFreq == 0 && sleepTotalData.getSlumberSleepTime() == 0)) {
            dVar.c = 0;
        }
        if (dVar.c > 0) {
            d.m(dVar, dVar.c);
            d.ah(dVar);
        }
    }

    private void a(SleepTotalData sleepTotalData, d dVar) {
        if (sleepTotalData.getTotalSleepTime() > 0) {
            d.g(dVar, sleepTotalData.getTotalSleepTime());
            d.z(dVar);
        }
        if (sleepTotalData.getDeepSleepPart() >= 20) {
            d.f(dVar, sleepTotalData.getDeepSleepPart());
            d.af(dVar);
        }
        if (sleepTotalData.getWakeupDuration() >= 0 && sleepTotalData.getTotalSleepTime() > 0) {
            d.h(dVar, sleepTotalData.getWakeupDuration());
            this.bb = dVar.ab;
        }
        if (sleepTotalData.getWakeupTimes() > 0 && sleepTotalData.getTotalSleepTime() > 0) {
            d.n(dVar, sleepTotalData.getWakeupTimes());
            d.ai(dVar);
        }
        if (sleepTotalData.getNoonSleepTime() > 0) {
            d.k(dVar, sleepTotalData.getNoonSleepTime());
            d.an(dVar);
        }
        if (sleepTotalData.getFallTime() > 0) {
            int fallTime = sleepTotalData.getFallTime();
            int i = fallTime + 1200;
            if (i > 1440) {
                i = fallTime - 240;
            }
            d.o(dVar, sleepTotalData.getFallTime());
            int i2 = i - this.f16319a;
            d.l(dVar, i2 * i2);
            d.aj(dVar);
        }
        if (sleepTotalData.getWakeUpTime() > 0) {
            int wakeUpTime = sleepTotalData.getWakeUpTime();
            int i3 = wakeUpTime + 1200;
            if (i3 > 1440) {
                i3 = wakeUpTime - 240;
            }
            d.r(dVar, sleepTotalData.getWakeUpTime());
            int i4 = i3 - this.c;
            d.t(dVar, i4 * i4);
            d.ao(dVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0081 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(int r7, java.lang.Object r8, com.huawei.ui.commonui.base.CommonUiBaseResponse r9) {
        /*
            r6 = this;
            r6.n()
            if (r7 != 0) goto L9d
            boolean r0 = r8 instanceof java.util.List
            if (r0 == 0) goto L9d
            java.util.List r8 = (java.util.List) r8
            int r0 = r8.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = " handleDayData retListData.size = "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "UIHLH_FitnessOldCoreSleepDetailInteractor"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L25
            return
        L25:
            r0 = 0
            r1 = r0
            r2 = r1
        L28:
            int r3 = r8.size()
            if (r1 >= r3) goto L84
            java.lang.Object r3 = r8.get(r1)
            com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData r3 = (com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData) r3
            int r4 = r3.getDeepSleepTime()
            r5 = 1
            if (r4 != r5) goto L42
            r2 = 65
            r6.s = r2
            r6.ae = r5
            goto L75
        L42:
            int r4 = r3.getShallowSleepTime()
            if (r4 != r5) goto L4f
            r2 = 66
            r6.s = r2
            r6.ae = r5
            goto L75
        L4f:
            int r4 = r3.getWakeupDuration()
            if (r4 != r5) goto L5c
            r2 = 67
            r6.s = r2
            r6.ae = r5
            goto L75
        L5c:
            int r4 = r3.getSlumberSleepTime()
            if (r4 != r5) goto L69
            r2 = 68
            r6.s = r2
            r6.ae = r5
            goto L75
        L69:
            int r4 = r3.getNoonSleepTime()
            if (r4 != r5) goto L77
            r2 = 69
            r6.s = r2
            r6.ae = r5
        L75:
            r2 = r1
            goto L79
        L77:
            r6.s = r0
        L79:
            r6.c(r8, r1, r3)
            if (r1 != 0) goto L81
            r6.d(r3)
        L81:
            int r1 = r1 + 1
            goto L28
        L84:
            boolean r0 = defpackage.koq.d(r8, r2)
            if (r0 == 0) goto L96
            java.lang.Object r8 = r8.get(r2)
            com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData r8 = (com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData) r8
            int r8 = r8.getDeviceType()
            r6.ab = r8
        L96:
            boolean r8 = r6.ae
            java.util.List<pvz> r0 = r6.y
            r6.c(r7, r8, r0, r9)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pxd.b(int, java.lang.Object, com.huawei.ui.commonui.base.CommonUiBaseResponse):void");
    }

    private void n() {
        this.ab = 0;
        this.ak.clear();
        this.am = 0;
        this.x = new pvz();
        this.s = 0;
        this.ae = false;
        this.y = new ArrayList(16);
    }

    private void c(List<SleepTotalData> list, int i, SleepTotalData sleepTotalData) {
        if (this.x.b() != -1 && this.x.e() != -1) {
            if (this.s != this.x.b()) {
                this.x.e(i);
                this.y.add(this.x);
                this.x = new pvz();
                if (this.ab == 0) {
                    this.ab = sleepTotalData.getDeviceType();
                }
            }
            if (i == list.size() - 1 && this.x.b() != -1) {
                this.x.e(ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL);
                if (this.ab == 0) {
                    this.ab = sleepTotalData.getDeviceType();
                }
                this.y.add(this.x);
            }
        }
        if (this.x.e() != -1 || this.s == 0) {
            return;
        }
        this.x.a(i);
        this.x.b(this.s);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData r19) {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r0 = r19.getNoonStartTime()
            java.lang.String r2 = r19.getNoonEndTime()
            android.content.Context r3 = r1.f
            java.lang.String r3 = defpackage.scn.a(r3)
            boolean r4 = android.text.TextUtils.isEmpty(r0)
            if (r4 != 0) goto Ld7
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto Ld7
            java.lang.String r4 = ","
            java.lang.String[] r2 = r2.split(r4)
            java.lang.String[] r0 = r0.split(r4)
            int r4 = r2.length
            r5 = 1
            if (r4 != r5) goto Ld4
            r4 = 0
            r0 = r0[r4]     // Catch: java.lang.NumberFormatException -> L3a
            long r8 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L3a
            r0 = r2[r4]     // Catch: java.lang.NumberFormatException -> L38
            long r10 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L38
            goto L4e
        L38:
            r0 = move-exception
            goto L3d
        L3a:
            r0 = move-exception
            r8 = 0
        L3d:
            java.lang.String r2 = "NumberFormatException"
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0}
            java.lang.String r2 = "UIHLH_FitnessOldCoreSleepDetailInteractor"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
            r10 = 0
        L4e:
            long r12 = r10 - r8
            r14 = 60000(0xea60, double:2.9644E-319)
            long r12 = r12 / r14
            r14 = 60
            long r6 = r12 / r14
            r16 = r10
            double r10 = (double) r6
            java.lang.String r0 = health.compact.a.UnitUtil.e(r10, r5, r4)
            long r10 = r12 % r14
            double r10 = (double) r10
            java.lang.String r2 = health.compact.a.UnitUtil.e(r10, r5, r4)
            r4 = 0
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            java.lang.String r5 = "   "
            if (r4 != 0) goto L9f
            android.content.Context r0 = r1.f
            android.content.res.Resources r0 = r0.getResources()
            int r4 = com.huawei.ui.main.R$string.IDS_hw_show_set_target_sport_time_unit
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r0 = r0.getString(r4, r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = defpackage.scn.c(r8)
            r2.append(r4)
            r2.append(r3)
            java.lang.String r3 = defpackage.scn.c(r16)
            r2.append(r3)
            r2.append(r5)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            goto Lcb
        L9f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = defpackage.scn.c(r8)
            r4.append(r6)
            r4.append(r3)
            java.lang.String r3 = defpackage.scn.c(r16)
            r4.append(r3)
            r4.append(r5)
            android.content.Context r3 = r1.f
            int r5 = com.huawei.ui.main.R$string.IDS_h_min_unit
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            java.lang.String r0 = r3.getString(r5, r0)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
        Lcb:
            java.util.List<java.lang.String> r2 = r1.ak
            r2.add(r0)
            int r0 = (int) r12
            r1.am = r0
            goto Ld7
        Ld4:
            r1.a(r2, r0)
        Ld7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pxd.d(com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.lang.String[] r20, java.lang.String[] r21) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            android.content.Context r0 = r1.f
            java.lang.String r3 = defpackage.scn.a(r0)
            r4 = 0
            r5 = r4
        Lc:
            int r0 = r2.length
            if (r5 >= r0) goto Lc3
            r0 = r21[r5]     // Catch: java.lang.NumberFormatException -> L1e
            long r8 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L1e
            r0 = r2[r5]     // Catch: java.lang.NumberFormatException -> L1c
            long r10 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L1c
            goto L32
        L1c:
            r0 = move-exception
            goto L21
        L1e:
            r0 = move-exception
            r8 = 0
        L21:
            java.lang.String r10 = "NumberFormatException"
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r10, r0}
            java.lang.String r10 = "UIHLH_FitnessOldCoreSleepDetailInteractor"
            com.huawei.hwlogsmodel.LogUtil.b(r10, r0)
            r10 = 0
        L32:
            long r12 = r10 - r8
            r14 = 60000(0xea60, double:2.9644E-319)
            long r12 = r12 / r14
            r14 = 60
            long r6 = r12 / r14
            double r14 = (double) r6
            r0 = 1
            java.lang.String r14 = health.compact.a.UnitUtil.e(r14, r0, r4)
            r18 = r14
            r16 = 60
            long r14 = r12 % r16
            double r14 = (double) r14
            java.lang.String r0 = health.compact.a.UnitUtil.e(r14, r0, r4)
            r14 = 0
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            java.lang.String r7 = "   "
            if (r6 != 0) goto L86
            android.content.Context r6 = r1.f
            android.content.res.Resources r6 = r6.getResources()
            int r14 = com.huawei.ui.main.R$string.IDS_hw_show_set_target_sport_time_unit
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r0 = r6.getString(r14, r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = defpackage.scn.c(r8)
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = defpackage.scn.c(r10)
            r6.append(r8)
            r6.append(r7)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            goto Lb4
        L86:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = defpackage.scn.c(r8)
            r6.append(r8)
            r6.append(r3)
            java.lang.String r8 = defpackage.scn.c(r10)
            r6.append(r8)
            r6.append(r7)
            android.content.Context r7 = r1.f
            int r8 = com.huawei.ui.main.R$string.IDS_h_min_unit
            r9 = r18
            java.lang.Object[] r0 = new java.lang.Object[]{r9, r0}
            java.lang.String r0 = r7.getString(r8, r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
        Lb4:
            java.util.List<java.lang.String> r6 = r1.ak
            r6.add(r0)
            int r0 = r1.am
            int r6 = (int) r12
            int r0 = r0 + r6
            r1.am = r0
            int r5 = r5 + 1
            goto Lc
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pxd.a(java.lang.String[], java.lang.String[]):void");
    }

    private void c(int i, boolean z, List<pvz> list, CommonUiBaseResponse commonUiBaseResponse) {
        ArrayList arrayList;
        if (list == null || list.size() <= 0) {
            arrayList = new ArrayList(16);
        } else {
            arrayList = new ArrayList(list);
        }
        if (z) {
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "dealIfHaveSleepData daySleepHistogramData.size =", Integer.valueOf(arrayList.size()));
            c(arrayList);
            d(arrayList);
        } else {
            arrayList.clear();
            d(arrayList);
        }
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(i, arrayList);
        }
    }

    static class a implements HiAggregateListener {
        private final FitnessDataQueryDefine.FitnessQueryId b;
        private final WeakReference<pxd> c;
        private final long d;
        private final CommonUiBaseResponse e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        /* synthetic */ a(pxd pxdVar, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, long j, CommonUiBaseResponse commonUiBaseResponse, AnonymousClass4 anonymousClass4) {
            this(pxdVar, fitnessQueryId, j, commonUiBaseResponse);
        }

        private a(pxd pxdVar, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, long j, CommonUiBaseResponse commonUiBaseResponse) {
            this.c = new WeakReference<>(pxdVar);
            this.b = fitnessQueryId;
            this.d = j;
            this.e = commonUiBaseResponse;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            if (this.c.get() == null) {
                return;
            }
            pxd pxdVar = this.c.get();
            if (koq.b(list)) {
                LogUtil.h("UIHLH_FitnessOldCoreSleepDetailInteractor", "getWeekBaseTime datas is empty");
                pxdVar.p();
                pxdVar.a(this.d, this.b, this.e);
                return;
            }
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "getWeekBaseTime sumDataList = ", Integer.valueOf(list.size()));
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (HiHealthData hiHealthData : list) {
                double d = hiHealthData.getDouble("core_sleep_fall_key");
                if (d != 0.0d) {
                    i5++;
                    i3 += nrq.e(d, hiHealthData.getTimeZone(), 1);
                }
                double d2 = hiHealthData.getDouble("core_sleep_wake_up_key");
                if (d2 != 0.0d) {
                    i6++;
                    i4 += nrq.e(d2, hiHealthData.getTimeZone(), 1);
                }
            }
            pxdVar.f16319a = i3;
            pxdVar.c = i4;
            pxdVar.e(i5, i6);
            LogUtil.a("UIHLH_FitnessOldCoreSleepDetailInteractor", "mBaseTimeWakeUp = ", Integer.valueOf(pxdVar.c), " mBaseTimeFall = ", Integer.valueOf(pxdVar.f16319a));
            pxdVar.a(this.d, this.b, this.e);
        }
    }
}
