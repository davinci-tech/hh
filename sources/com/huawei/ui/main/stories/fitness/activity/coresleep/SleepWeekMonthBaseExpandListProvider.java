package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.embedded.a2;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepWeekMonthBaseExpandListProvider;
import defpackage.edo;
import defpackage.edx;
import defpackage.fdn;
import defpackage.fdo;
import defpackage.fdp;
import defpackage.fdq;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.pqg;
import defpackage.pwd;
import defpackage.qmx;
import defpackage.scn;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes9.dex */
public class SleepWeekMonthBaseExpandListProvider extends SleepBaseExpandListProvider {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected boolean isEffectiveDate() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void setSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        setProviderActive(fdpVar);
        sectionBean.e(this, fdpVar);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void updateDataUi() {
        if (this.mSleepViewData == null) {
            LogUtil.h(getTag(), "updateDataUi mSleepViewData null");
            return;
        }
        int i = AnonymousClass4.e[this.mSleepViewData.i().ordinal()];
        if (i == 1) {
            LogUtil.a(getTag(), "common sleep");
            h();
        } else if (i == 2) {
            LogUtil.a(getTag(), "manual sleep");
            i();
        } else if (i == 3) {
            LogUtil.a(getTag(), "phone sleep");
            k();
        } else if (i == 4) {
            LogUtil.a(getTag(), "core sleep");
            g();
        } else {
            LogUtil.a(getTag(), "other sleep types unknown");
        }
        sortBeanList();
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepWeekMonthBaseExpandListProvider$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            e = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static View drD_(Context context) {
        if (nsn.t()) {
            return LayoutInflater.from(context).inflate(R.layout.sleep_detail_item_layout_large_mode, (ViewGroup) null);
        }
        return LayoutInflater.from(context).inflate(R.layout.expand_report_ext_item_layout, (ViewGroup) null);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void handleTruSleepData(CharSequence[] charSequenceArr, SleepBaseExpandListProvider.c cVar) {
        if (cVar == null || koq.b(charSequenceArr, 3)) {
            return;
        }
        charSequenceArr[1] = "";
        String h = nsf.h(R$string.IDS_physiological_index_invalid);
        if (getSleepStatData().a(cVar.c)) {
            if ("TRUSLEEP_FIVE_SpO2".equals(cVar.c)) {
                h = pqg.c(qmx.i.get(cVar.c).intValue(), cVar.e[0], cVar.e[1]);
            } else {
                h = nsf.a(qmx.i.get(cVar.c).intValue(), cVar.e[1], UnitUtil.e(cVar.e[0], 1, 0), UnitUtil.e(cVar.e[1], 1, 0));
            }
        }
        SpannableString spannableString = new SpannableString(h);
        nsi.cKJ_(spannableString, nsf.b(R$string.IDS_base_line_percent, UnitUtil.e(cVar.e[0], 1, 0), UnitUtil.e(cVar.e[1], 1, 0)), nsf.b(R.dimen._2131362974_res_0x7f0a049e));
        charSequenceArr[2] = spannableString;
        charSequenceArr[3] = "";
    }

    private void g() {
        LogUtil.a(getTag(), "processWeekAndMonthData");
        if (this.mSleepViewData.j().h() > 0) {
            f();
        }
        if (this.mSleepViewData.j().ap() > 0) {
            this.mExpandReportListBean.e(new edx(BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_avg_noontime_sleep_new), d(this.mSleepViewData.j().ap(), 13, 13), this.mArrowRes, new View.OnClickListener() { // from class: ppm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepWeekMonthBaseExpandListProvider.this.drL_(view);
                }
            }).b(qmx.f16490a.get("NOON_SLEEP").intValue()));
        }
    }

    public /* synthetic */ void drL_(View view) {
        d(this.mSleepViewData.j().ap());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        if (this.mWeakRef == null) {
            LogUtil.a(getTag(), "updateCoreSleepDetailList, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (knitSleepDetailActivity == null) {
            LogUtil.h(getTag(), "updateCoreSleepDetailList, context is invalid");
            return;
        }
        j(knitSleepDetailActivity);
        if (this.mSleepViewData.j().ba() || getSleepStatData().ba()) {
            this.mExpandReportListBean.b(a().e(qmx.f16490a.get("TRUSLEEP_FIVE_BREATH_RATE").intValue()));
            this.mExpandReportListBean.b(b().e(qmx.f16490a.get("TRUSLEEP_FIVE_HEART_RATE").intValue()));
            this.mExpandReportListBean.b(d().e(qmx.f16490a.get("TRUSLEEP_FIVE_HRV").intValue()));
            this.mExpandReportListBean.b(e().e(qmx.f16490a.get("TRUSLEEP_FIVE_SpO2").intValue()));
        } else {
            this.mExpandReportListBean.e(getSleepPhysiologicalItem(knitSleepDetailActivity, "SLEEP_HEART_RATE").b(qmx.f16490a.get("SLEEP_HEART_RATE").intValue()));
            this.mExpandReportListBean.e(getSleepPhysiologicalItem(knitSleepDetailActivity, "SLEEP_BLOOD_OX").b(qmx.f16490a.get("SLEEP_BLOOD_OX").intValue()));
            this.mExpandReportListBean.e(getSleepPhysiologicalItem(knitSleepDetailActivity, "SLEEP_BREATH_RATIO").b(qmx.f16490a.get("SLEEP_BREATH_RATIO").intValue()));
        }
        this.mExpandReportListBean.b(d(knitSleepDetailActivity).e(qmx.f16490a.get("GO_BED_REGULARITY").intValue()));
        this.mExpandReportListBean.b(c(knitSleepDetailActivity).e(qmx.f16490a.get("FALL_ASLEEP_TIME").intValue()));
        this.mExpandReportListBean.b(b(knitSleepDetailActivity).e(qmx.f16490a.get("OFF_BED_REGULARITY").intValue()));
        this.mExpandReportListBean.b(g(knitSleepDetailActivity).e(qmx.f16490a.get("WAKE_UP_TIME").intValue()));
    }

    private void j(Context context) {
        this.mExpandReportListBean.b(getCoreNightSleepBean(context, R$string.IDS_fitness_core_sleep_avg_night_sleep_new).e(qmx.f16490a.get("SLEEP_DURATION").intValue()));
        this.mExpandReportListBean.b(getDeepSleepBean(context, R$string.IDS_fitness_core_sleep_avg_deep_sleep_percent_new).e(qmx.f16490a.get("DEEP_RATIO").intValue()));
        this.mExpandReportListBean.b(getLightSleepBean(context, R$string.IDS_fitness_core_sleep_avg_light_sleep_percent_new).e(qmx.f16490a.get("SHALLOW_RATIO").intValue()));
        this.mExpandReportListBean.b(getRemSleepBean(context, R$string.IDS_fitness_core_sleep_avg_rem_sleep_percent_new).e(qmx.f16490a.get("REM_RATIO").intValue()));
        this.mExpandReportListBean.b(getDeepSleepContinuityBean(context, R$string.IDS_fitness_core_sleep_avg_deep_sleep_continuity_new).e(qmx.f16490a.get("DEEP_CONT").intValue()));
        this.mExpandReportListBean.b(getWakeTimesBean(context, R$string.IDS_fitness_core_sleep_sleep_avg_latency_time_new).e(qmx.f16490a.get("AWAKE_NUMS").intValue()));
        this.mExpandReportListBean.b(getBreathQualityBean(context, R$string.IDS_fitness_core_sleep_avg_rdi_score_new).e(qmx.f16490a.get("BREATH_QUALITY").intValue()));
    }

    private edo a() {
        fdq sleepStatData = getSleepStatData();
        return b(sleepStatData.bm(), sleepStatData.be(), "TRUSLEEP_FIVE_BREATH_RATE", sleepStatData);
    }

    private edo b(int i, int i2, String str, fdq fdqVar) {
        String b = nsf.b(qmx.f.get(str).intValue(), "");
        String h = nsf.h(R$string.IDS_physiological_index_invalid);
        if (fdqVar.a(str)) {
            if ("TRUSLEEP_FIVE_SpO2".equals(str)) {
                h = pqg.c(qmx.i.get(str).intValue(), i, i2);
            } else {
                h = nsf.a(qmx.i.get(str).intValue(), i2, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0));
            }
        }
        return new edo(b, "", h, nsf.c(R.color._2131297869_res_0x7f09064d), this.mArrowRes, new SleepBaseExpandListProvider.d(this, getTag()).a(str, new int[]{i, i2}, 0, "", 0));
    }

    private edo b() {
        fdq sleepStatData = getSleepStatData();
        return b(sleepStatData.bi(), sleepStatData.bl(), "TRUSLEEP_FIVE_HEART_RATE", sleepStatData);
    }

    private edo d() {
        fdq sleepStatData = getSleepStatData();
        return b(sleepStatData.bk(), sleepStatData.bf(), "TRUSLEEP_FIVE_HRV", sleepStatData);
    }

    private edo e() {
        fdq sleepStatData = getSleepStatData();
        return b(sleepStatData.bq(), sleepStatData.bj(), "TRUSLEEP_FIVE_SpO2", sleepStatData);
    }

    private void k() {
        LogUtil.a(getTag(), "updatePhoneDataUi");
        if (this.mWeakRef == null) {
            LogUtil.a(getTag(), "updateMannerSleepDataUi, mWeakRef is invalid");
        } else if (this.mWeakRef.get() == null) {
            LogUtil.a(getTag(), "updateMannerSleepDataUi, context == null");
        } else {
            j();
        }
    }

    private void j() {
        LogUtil.a(getTag(), "processWeekAndMonthPhoneData mTotalDaySleepTime ", Integer.valueOf(this.mSleepViewData.f().w()));
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (this.mSleepViewData.f().w() > 0) {
            this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_fitness_core_sleep_avg_noontime_sleep_new), d(this.mSleepViewData.f().w(), 13, 13), this.mArrowRes, new View.OnClickListener() { // from class: ppf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepWeekMonthBaseExpandListProvider.this.drM_(view);
                }
            }).b(qmx.f16490a.get("NOON_SLEEP").intValue()));
        }
        if (this.mSleepViewData.f().h() > 0) {
            this.mExpandReportListBean.b(getSleepEfficientBean(knitSleepDetailActivity, getSleepEfficientValue(), R$string.IDS_avg_sleep_efficiency_new).e(qmx.f16490a.get("SLEEP_EFFICIENCY").intValue()));
            this.mExpandReportListBean.b(getBedTimeBean(getBedTime(), R$string.IDS_manual_sleep_average_bed_duration_new).e(qmx.f16490a.get("BED_TIME").intValue()));
            this.mExpandReportListBean.b(getSleepTimeBean(getItemTitle(c()[1], getFormatTime(c()[0] / 60, c()[0] % 60)), c()[0]).e(qmx.f16490a.get("SLEEP_DURATION").intValue()));
            this.mExpandReportListBean.b(c(knitSleepDetailActivity).e(qmx.f16490a.get("FALL_ASLEEP_TIME").intValue()));
            this.mExpandReportListBean.b(g(knitSleepDetailActivity).e(qmx.f16490a.get("WAKE_UP_TIME").intValue()));
            this.mExpandReportListBean.b(getSleepLatencyBean(getSleepLatency(), R$string.IDS_avg_sleep_latency_new).e(qmx.f16490a.get("SLEEP_LATENCY").intValue()));
            this.mExpandReportListBean.b(d(knitSleepDetailActivity).e(qmx.f16490a.get("GO_BED_REGULARITY").intValue()));
            this.mExpandReportListBean.b(b(knitSleepDetailActivity).e(qmx.f16490a.get("OFF_BED_REGULARITY").intValue()));
            this.mExpandReportListBean.b(getWakeTimesBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_sleep_avg_latency_time_new).e(qmx.f16490a.get("AWAKE_NUMS").intValue()));
        }
    }

    public /* synthetic */ void drM_(View view) {
        d(this.mSleepViewData.f().w());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(int i) {
        String e = UnitUtil.e((i - r0) / 60.0d, 1, 0);
        String e2 = UnitUtil.e(i % 60, 1, 0);
        ArrayList arrayList = (ArrayList) new pwd().a();
        if (this.mWeakRef == null || this.mWeakRef.get() == null) {
            LogUtil.a(getTag(), "gotoDayNoonSleepActivity, mWeakRef is invalid");
        } else {
            DaySleepActivity.a(this.mWeakRef.get(), e, e2, getDaySleepStatus(i), arrayList);
        }
    }

    private String d(int i, int i2, int i3) {
        if (i == 0) {
            return "";
        }
        int i4 = i / 60;
        int i5 = i % 60;
        String e = UnitUtil.e(i4, 1, 0);
        String e2 = UnitUtil.e(i5, 1, 0);
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i5, e2);
        if (i4 != 0) {
            quantityString = BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i4, e), quantityString);
        }
        drE_(new SpannableString(quantityString), quantityString.indexOf(e2), quantityString.indexOf(e), e2.length(), e.length(), i3, i2);
        return quantityString;
    }

    private void drE_(SpannableString spannableString, int i, int i2, int i3, int i4, int i5, int i6) {
        if (spannableString == null || spannableString.length() == 0) {
            return;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(i6, true), 0, spannableString.length(), 17);
        if (i >= 0) {
            int i7 = i3 + i;
            spannableString.setSpan(new AbsoluteSizeSpan(i5, true), i, i7, 17);
            spannableString.setSpan(Typeface.create(Constants.FONT, 0), i, i7, 17);
        }
        if (i2 >= 0) {
            int i8 = i4 + i2;
            spannableString.setSpan(new AbsoluteSizeSpan(i5, true), i2, i8, 17);
            spannableString.setSpan(Typeface.create(Constants.FONT, 0), i2, i8, 17);
        }
    }

    private void h() {
        LogUtil.a(getTag(), "updateCommonTotalDataUi");
        int t = this.mSleepViewData.c().t();
        this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_fitness_core_sleep_sleep_avg_latency_time_new), nsf.a(R.plurals._2130903101_res_0x7f03003d, t, Integer.valueOf(t)), -1, null).b(qmx.f16490a.get("AWAKE_NUMS").intValue()));
    }

    private void i() {
        LogUtil.a(getTag(), "updateMannerSleepDataUi");
        if (this.mWeakRef == null) {
            LogUtil.a(getTag(), "updateMannerSleepDataUi, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (knitSleepDetailActivity == null) {
            LogUtil.a(getTag(), "updateMannerSleepDataUi, context == null");
        } else {
            i(knitSleepDetailActivity);
        }
    }

    private void i(Context context) {
        this.mExpandReportListBean.b(getSleepEfficientBean(context, getSleepEfficientValue(), R$string.IDS_avg_sleep_efficiency_new).e(qmx.f16490a.get("SLEEP_EFFICIENCY").intValue()));
        this.mExpandReportListBean.b(getBedTimeBean(getBedTime(), R$string.IDS_manual_sleep_average_bed_duration_new).e(qmx.f16490a.get("BED_TIME").intValue()));
        this.mExpandReportListBean.b(getSleepTimeBean(getItemTitle(c()[1], getFormatTime(c()[0] / 60, c()[0] % 60)), c()[0]).e(qmx.f16490a.get("SLEEP_DURATION").intValue()));
        this.mExpandReportListBean.b(e(context).e(qmx.f16490a.get("GO_BED_TIME").intValue()));
        this.mExpandReportListBean.b(c(context).e(qmx.f16490a.get("FALL_ASLEEP_TIME").intValue()));
        this.mExpandReportListBean.b(g(context).e(qmx.f16490a.get("WAKE_UP_TIME").intValue()));
        this.mExpandReportListBean.b(f(context).e(qmx.f16490a.get("RISING_TIME").intValue()));
        this.mExpandReportListBean.b(d(context).e(qmx.f16490a.get("GO_BED_REGULARITY").intValue()));
        this.mExpandReportListBean.b(b(context).e(qmx.f16490a.get("OFF_BED_REGULARITY").intValue()));
        this.mExpandReportListBean.b(getSleepLatencyBean(getSleepLatency(), R$string.IDS_avg_sleep_latency_new).e(qmx.f16490a.get("SLEEP_LATENCY").intValue()));
    }

    private int[] c() {
        int[] iArr = {0, R$string.IDS_manual_sleep_average_sleep_duration};
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            iArr[0] = this.mSleepViewData.d().h();
            iArr[1] = R$string.IDS_manual_sleep_average_sleep_duration;
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            iArr[0] = this.mSleepViewData.f().h();
            iArr[1] = R$string.IDS_fitness_core_sleep_avg_night_sleep_new;
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            iArr[0] = this.mSleepViewData.j().h();
            iArr[1] = R$string.IDS_fitness_core_sleep_avg_night_sleep_new;
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            iArr[0] = this.mSleepViewData.c().h();
            iArr[1] = R$string.IDS_manual_sleep_average_sleep_duration;
        } else {
            LogUtil.a(getTag(), "getSleepTimeBean sleepType", this.mSleepViewData.i());
        }
        return iArr;
    }

    private edo e(final Context context) {
        int ae;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            ae = ((fdn) this.mSleepViewData.d()).v();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            ae = ((fdo) this.mSleepViewData.f()).ae();
        } else {
            LogUtil.h(getTag(), a2.d);
            return new edo();
        }
        if (ae == -1) {
            LogUtil.h(getTag(), "not satified");
            return new edo();
        }
        int i = (ae + 1200) % ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        ReleaseLogUtil.e(getTag(), "getAvgBedTimeBean getAvgPhoneBedTime is ", Integer.valueOf(i), " source ", this.mSleepViewData.i());
        final int d = scn.d(i);
        String d2 = pqg.d(context, d, true);
        int d3 = pqg.d(d);
        final String str = String.format(Locale.ROOT, "%02d", Integer.valueOf(i / 60)) + ":" + String.format(Locale.ROOT, "%02d", Integer.valueOf(i % 60));
        return new edo(getItemTitle(R$string.IDS_manual_sleep_average_bed_time_new, UnitUtil.e(a(str))), nsf.b(R$string.IDS_sleep_referece_title_smaller_string, UnitUtil.e(0)), d2, d3, this.mArrowRes, new View.OnClickListener() { // from class: ppe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepWeekMonthBaseExpandListProvider.this.drF_(context, str, d, view);
            }
        });
    }

    public /* synthetic */ void drF_(Context context, String str, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b(context, UnitUtil.e(a(str)), i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private int a(String str) {
        String[] split = str.split(":");
        try {
            if (split.length > 1) {
                return (Integer.parseInt(split[0]) * 3600) + (Integer.parseInt(split[1]) * 60);
            }
        } catch (NumberFormatException e) {
            LogUtil.b(getTag(), "getSeconds exception = ", e.getMessage());
        }
        return 0;
    }

    private void b(Context context, String str, int i) {
        LogUtil.a(getTag(), "setAvgBedTimeClickEvent");
        SleepTimeActivity.d(context, 1, str, i);
    }

    private edo c(final Context context) {
        int bd;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            bd = ((fdn) this.mSleepViewData.d()).u();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            bd = ((fdo) this.mSleepViewData.f()).ag();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            bd = ((fdq) this.mSleepViewData.j()).bd();
        } else {
            LogUtil.h(getTag(), a2.d);
            return new edo();
        }
        if (bd == -1) {
            LogUtil.h(getTag(), "not satified");
            return new edo();
        }
        int i = (bd + 1200) % ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        ReleaseLogUtil.e(getTag(), " getAvgFallSleepTimeBean dailyFallTime ", Integer.valueOf(i), " source ", this.mSleepViewData.i());
        final int d = scn.d(i);
        String d2 = pqg.d(context, d, true);
        int d3 = pqg.d(d);
        final String str = String.format(Locale.ROOT, "%02d", Integer.valueOf(i / 60)) + ":" + String.format(Locale.ROOT, "%02d", Integer.valueOf(i % 60));
        return new edo(getItemTitle(R$string.IDS_fitness_core_sleep_avg_start_sleep_new, UnitUtil.e(a(str))), nsf.b(R$string.IDS_sleep_referece_title_smaller_string, UnitUtil.e(0)), d2, d3, this.mArrowRes, new View.OnClickListener() { // from class: ppg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepWeekMonthBaseExpandListProvider.this.drG_(context, str, d, view);
            }
        });
    }

    public /* synthetic */ void drG_(Context context, String str, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            e(context, UnitUtil.e(a(str)), i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void e(Context context, String str, int i) {
        LogUtil.a(getTag(), "setFallSleepTimeClickEvent");
        SleepTimeActivity.d(context, 2, str, i);
    }

    private edo g(final Context context) {
        int bh;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            bh = ((fdn) this.mSleepViewData.d()).x();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            bh = ((fdo) this.mSleepViewData.f()).af();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            bh = ((fdq) this.mSleepViewData.j()).bh();
        } else {
            LogUtil.h(getTag(), a2.d);
            return new edo();
        }
        if (bh == -1) {
            LogUtil.h(getTag(), "not satified");
            return new edo();
        }
        int i = (bh + 1200) % ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        ReleaseLogUtil.e(getTag(), " getMeanWakingTimeBean meanWakingTime ", Integer.valueOf(i), " source ", this.mSleepViewData.i());
        final int r = scn.r(i);
        String d = pqg.d(context, r, true);
        int d2 = pqg.d(r);
        final String str = String.format(Locale.ROOT, "%02d", Integer.valueOf(i / 60)) + ":" + String.format(Locale.ROOT, "%02d", Integer.valueOf(i % 60));
        return new edo(getItemTitle(R$string.IDS_manual_sleep_mean_waking_time_new, UnitUtil.e(a(str))), nsf.b(R$string.IDS_sleep_referece_title_bigger_string, UnitUtil.e(21600)), d, d2, this.mArrowRes, new View.OnClickListener() { // from class: pph
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepWeekMonthBaseExpandListProvider.this.drJ_(context, str, r, view);
            }
        });
    }

    public /* synthetic */ void drJ_(Context context, String str, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            c(context, UnitUtil.e(a(str)), i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c(Context context, String str, int i) {
        LogUtil.a(getTag(), "setMeanWakingTimeClickEvent");
        SleepTimeActivity.d(context, 3, str, i);
    }

    private edo f(final Context context) {
        int ah;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            ah = ((fdn) this.mSleepViewData.d()).y();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            ah = ((fdo) this.mSleepViewData.f()).ah();
        } else {
            LogUtil.h(getTag(), a2.d);
            return new edo();
        }
        if (ah == -1) {
            LogUtil.h(getTag(), "not satified");
            return new edo();
        }
        int i = (ah + 1200) % ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        ReleaseLogUtil.e(getTag(), "getWakeUpTimeBean source ", this.mSleepViewData.i(), "wakeUpTime", Integer.valueOf(i));
        final int r = scn.r(i);
        String d = pqg.d(context, r, true);
        int d2 = pqg.d(r);
        final String str = String.format(Locale.ROOT, "%02d", Integer.valueOf(i / 60)) + ":" + String.format(Locale.ROOT, "%02d", Integer.valueOf(i % 60));
        return new edo(getItemTitle(R$string.IDS_fitness_core_sleep_avg_end_sleep_new, UnitUtil.e(a(str))), nsf.b(R$string.IDS_sleep_referece_title_bigger_string, UnitUtil.e(21600)), d, d2, this.mArrowRes, new View.OnClickListener() { // from class: ppc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepWeekMonthBaseExpandListProvider.this.drK_(context, str, r, view);
            }
        });
    }

    public /* synthetic */ void drK_(Context context, String str, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            d(context, UnitUtil.e(a(str)), i);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void d(Context context, String str, int i) {
        LogUtil.a(getTag(), "setWakeUpTimeClickEvent");
        SleepTimeActivity.d(context, 4, str, i);
    }

    private edo d(final Context context) {
        final int bg;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            bg = ((fdn) this.mSleepViewData.d()).z();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            bg = ((fdo) this.mSleepViewData.f()).aj();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            bg = ((fdq) this.mSleepViewData.j()).bg();
        } else {
            LogUtil.h(getTag(), a2.d);
            return new edo();
        }
        if (bg == -1) {
            LogUtil.h(getTag(), "not satified");
            return new edo();
        }
        ReleaseLogUtil.e(getTag(), " getBedTimeRegularityBean ", Integer.valueOf(bg), " source ", this.mSleepViewData.i());
        final int k = scn.k(bg);
        return new edo(getItemTitle(R$string.IDS_manual_sleep_bedtime_regularity, nsf.a(R.plurals._2130903221_res_0x7f0300b5, bg, UnitUtil.e(bg, 1, 0))), pqg.e(70, 100, 0), pqg.c(k, "TYPE_FALL_GO_TO_BED_REGULAR"), pqg.d(k), this.mArrowRes, new View.OnClickListener() { // from class: ppd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepWeekMonthBaseExpandListProvider.this.drH_(context, bg, k, view);
            }
        });
    }

    public /* synthetic */ void drH_(Context context, int i, int i2, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b(context, i, i2);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(Context context, int i, int i2) {
        LogUtil.a(getTag(), "setBedTimeRegularityClickEvent");
        SleepScoreTimesActivity.b(context, "TYPE_FALL_GO_TO_BED_REGULAR", i, i2);
    }

    private edo b(final Context context) {
        final int bo;
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            bo = ((fdn) this.mSleepViewData.d()).ac();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            bo = ((fdo) this.mSleepViewData.f()).ak();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            bo = ((fdq) this.mSleepViewData.j()).bo();
        } else {
            LogUtil.h(getTag(), a2.d);
            return new edo();
        }
        if (bo == -1) {
            LogUtil.h(getTag(), "not satified");
            return new edo();
        }
        ReleaseLogUtil.e(getTag(), "getEndSleepRegularityBean TYPE ", this.mSleepViewData.i(), " endSleepRegularity ", Integer.valueOf(bo));
        final int k = scn.k(bo);
        return new edo(getItemTitle(R$string.IDS_fitness_core_sleep_end_sleep_regularity, nsf.a(R.plurals._2130903221_res_0x7f0300b5, bo, UnitUtil.e(bo, 1, 0))), pqg.e(70, 100, 0), pqg.c(k, "TYPE_WAKE_UP_REGULAR"), pqg.d(k), this.mArrowRes, new View.OnClickListener() { // from class: ppi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepWeekMonthBaseExpandListProvider.this.drI_(context, bo, k, view);
            }
        });
    }

    public /* synthetic */ void drI_(Context context, int i, int i2, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a(context, i, i2);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a(Context context, int i, int i2) {
        SleepScoreTimesActivity.b(context, "TYPE_WAKE_UP_REGULAR", i, i2);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected String getTag() {
        return "R_Sleep_SleepWeekMonthBaseExpandListProvider";
    }
}
