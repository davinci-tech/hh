package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepDayExpandListProvider;
import defpackage.edo;
import defpackage.edx;
import defpackage.fdp;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.nsn;
import defpackage.pqg;
import defpackage.pqr;
import defpackage.qmx;
import defpackage.scn;
import defpackage.scx;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class SleepDayExpandListProvider extends SleepBaseExpandListProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f9810a = new ArrayList<String>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepDayExpandListProvider.5
        {
            add("heart_rate_button");
            add("custom.blood.oxygen.switch");
        }
    };

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void setSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            pqr.c(new Date(fdpVar.g()), new c(this, sectionBean, fdpVar));
        } else {
            setProviderActive(fdpVar);
            sectionBean.e(this, fdpVar);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected boolean isEffectiveDate() {
        return (this.mSleepViewData == null || this.mSleepViewData.g() == 0) ? false : true;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void updateDataUi() {
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            h();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            LogUtil.a("R_Sleep_DayExpandListProvider", "day core sleep");
            e();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            LogUtil.a("R_Sleep_DayExpandListProvider", "day phone sleep");
            f();
        } else if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            LogUtil.a("R_Sleep_DayExpandListProvider", "common sleep");
            i();
        }
        sortBeanList();
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void handleTruSleepData(CharSequence[] charSequenceArr, SleepBaseExpandListProvider.c cVar) {
        if (cVar == null || koq.b(charSequenceArr, 3)) {
            return;
        }
        charSequenceArr[1] = d(cVar.c, cVar.e);
        charSequenceArr[2] = c(cVar.c, cVar.b);
        charSequenceArr[3] = d(cVar.d, cVar.f9806a);
    }

    private CharSequence d(String str, int[] iArr) {
        String h;
        int intValue = qmx.i.get(str).intValue();
        if (this.mSleepViewData.j().d(str)) {
            if (intValue != R$string.IDS_base_line_percent) {
                h = nsf.a(qmx.i.get(str).intValue(), iArr[1], UnitUtil.e(iArr[0], 1, 0), UnitUtil.e(iArr[1], 1, 0));
            } else {
                h = pqg.c(qmx.i.get(str).intValue(), iArr[0], iArr[1]);
            }
        } else {
            h = nsf.h(R$string.IDS_base_line_frequency_empty);
        }
        return nsf.b(R$string.IDS_bese_line_description, h);
    }

    private CharSequence c(String str, int i) {
        String h;
        String h2;
        int intValue = qmx.j.get(str).intValue();
        if (intValue != -1) {
            if (this.mSleepViewData.j().c(str)) {
                h = nsf.a(intValue, i, UnitUtil.e(i, 1, 0));
            } else {
                h = nsf.a(intValue, 0, nsf.h(R$string.IDS_physiological_index_invalid));
            }
        } else if (this.mSleepViewData.j().c(str)) {
            h = UnitUtil.e(i, 2, 0);
        } else {
            h = nsf.h(R$string.IDS_physiological_index_invalid);
        }
        SpannableString spannableString = new SpannableString(h);
        if (this.mSleepViewData.j().c(str)) {
            h2 = UnitUtil.e(i, 1, 0);
        } else {
            h2 = nsf.h(R$string.IDS_physiological_index_invalid);
        }
        nsi.cKJ_(spannableString, h2, nsf.b(R.dimen._2131362974_res_0x7f0a049e));
        return spannableString;
    }

    private CharSequence d(CharSequence charSequence, int i) {
        SpannableString spannableString = new SpannableString(charSequence);
        if (!(charSequence instanceof String)) {
            ReleaseLogUtil.e(getTag(), "unknow class");
            return "";
        }
        if (!TextUtils.isEmpty(charSequence)) {
            spannableString.setSpan(new ForegroundColorSpan(i), 0, charSequence.length(), 33);
        }
        return spannableString;
    }

    private void i() {
        LogUtil.a("R_Sleep_DayExpandListProvider", "updateCommonTotalDataUi");
        int t = this.mSleepViewData.c().t();
        this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_details_sleep_sleep_latency_time), nsf.a(R.plurals._2130903101_res_0x7f03003d, t, Integer.valueOf(t)), -1, null).b(qmx.f16490a.get("AWAKE_NUMS").intValue()));
        c(this.mSleepViewData.c().h());
    }

    private void c(int i) {
        LogUtil.a("R_Sleep_DayExpandListProvider", "setSleepDataToCommonSleep");
        if (i / 60 >= 15) {
            scx.e(BaseApplication.getContext(), this.mRequestDay, "EXCE_NORMALSLEEP__TIME_ERROR");
        }
    }

    private void f() {
        LogUtil.a("R_Sleep_DayExpandListProvider", "processDayCoreSleepData");
        if (this.mWeakRef == null || this.mWeakRef.get() == null) {
            LogUtil.a("R_Sleep_DayExpandListProvider", "updateMannerSleepDataUi, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (this.mSleepViewData.f().h() > 0) {
            if (this.mSleepViewData.f().k() > 0) {
                this.mExpandReportListBean.b(getBedTimeBean(getBedTime(), R$string.IDS_manual_sleep_bed_time).e(qmx.f16490a.get("BED_TIME").intValue()));
                this.mExpandReportListBean.b(getSleepEfficientBean(knitSleepDetailActivity, getSleepEfficientValue(), R$string.IDS_manual_sleep_sleep_efficiency).e(qmx.f16490a.get("SLEEP_EFFICIENCY").intValue()));
                this.mExpandReportListBean.b(getSleepLatencyBean(getSleepLatency(), R$string.IDS_sleep_latency_title).e(qmx.f16490a.get("SLEEP_LATENCY").intValue()));
            }
            this.mExpandReportListBean.b(getWakeTimesBean(knitSleepDetailActivity, R$string.IDS_details_sleep_sleep_latency_time).e(qmx.f16490a.get("AWAKE_NUMS").intValue()));
            this.mExpandReportListBean.b(getPhoneNightSleepBean(this.mSleepViewData.f().h(), R$string.IDS_fitness_core_sleep_night_sleep).e(qmx.f16490a.get("SLEEP_DURATION").intValue()));
        }
        e(this.mSleepViewData.f().w());
        if (this.mSleepViewData.f().w() != 0) {
            if (this.mSleepViewData.f().ad()) {
                this.mExpandReportListBean.e(nsf.b(R$string.IDS_sleep_day_sleep_phone_remainders, nsf.a(R.plurals._2130903199_res_0x7f03009f, 3, UnitUtil.e(3.0d, 1, 0))));
            } else {
                this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_fitness_total_sleep_data_title), scn.d(knitSleepDetailActivity, this.mSleepViewData.f().h() + this.mSleepViewData.f().w()), -1, null).b(qmx.f16490a.get("TOTAL_SLEEP").intValue()));
            }
        }
        j();
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference c;
        private final SectionBean d;
        private final fdp e;

        public c(SleepDayExpandListProvider sleepDayExpandListProvider, SectionBean sectionBean, fdp fdpVar) {
            this.c = new WeakReference(sleepDayExpandListProvider);
            this.d = sectionBean;
            this.e = fdpVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WeakReference weakReference = this.c;
            if (weakReference == null || !(weakReference.get() instanceof SleepDayExpandListProvider)) {
                LogUtil.b("R_Sleep_DayExpandListProvider", "mWeakRef is null");
                return;
            }
            SleepBaseExpandListProvider sleepBaseExpandListProvider = (SleepBaseExpandListProvider) this.c.get();
            if (i == 0 && ((Map) obj).containsKey("detect_abnormal")) {
                LogUtil.a("R_Sleep_DayExpandListProvider", "DETECT_ABNORMAL_KEY");
                sleepBaseExpandListProvider.mIsActive = false;
                this.d.e(sleepBaseExpandListProvider, this.e);
            } else {
                sleepBaseExpandListProvider.setProviderActive(this.e);
                this.d.e(sleepBaseExpandListProvider, this.e);
            }
        }
    }

    private void h() {
        LogUtil.a("R_Sleep_DayExpandListProvider", "updateMannerSleepDataUi");
        if (this.mWeakRef == null) {
            LogUtil.h("R_Sleep_DayExpandListProvider", "updateMannerSleepDataUi, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (knitSleepDetailActivity == null) {
            LogUtil.h("R_Sleep_DayExpandListProvider", "updateMannerSleepDataUi context == null");
        } else {
            a(knitSleepDetailActivity);
        }
    }

    private void a(Context context) {
        String str;
        int i;
        this.mExpandReportListBean.b(getSleepEfficientBean(context, getSleepEfficientValue(), R$string.IDS_manual_sleep_sleep_efficiency).e(qmx.f16490a.get("SLEEP_EFFICIENCY").intValue()));
        this.mExpandReportListBean.b(getBedTimeBean(getBedTime(), R$string.IDS_manual_sleep_bed_time).e(qmx.f16490a.get("BED_TIME").intValue()));
        if (this.mSleepViewData.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
            i = this.mSleepViewData.d().h();
            str = getItemTitle(R$string.IDS_fitness_core_sleep_sleep_duration, getFormatTime(i / 60, i % 60));
        } else {
            str = "";
            i = 0;
        }
        this.mExpandReportListBean.b(getSleepTimeBean(str, i).e(qmx.f16490a.get("SLEEP_DURATION").intValue()));
        this.mExpandReportListBean.b(getSleepLatencyBean(getSleepLatency(), R$string.IDS_sleep_latency_title).e(qmx.f16490a.get("SLEEP_LATENCY").intValue()));
    }

    private void e() {
        String b2;
        LogUtil.a("R_Sleep_DayExpandListProvider", "processDayCoreSleepData");
        Context context = BaseApplication.getContext();
        if (this.mSleepViewData.j().h() > 0) {
            g();
        }
        e(this.mSleepViewData.j().ap());
        if (this.mSleepViewData.j().ap() > 0) {
            if (this.mSleepViewData.j().bb()) {
                if (VersionControlUtil.isSupportSleepManagement() && this.mIsOpen) {
                    LogUtil.a("R_Sleep_DayExpandListProvider", "secientific noon sleep only, after turn on sleep management");
                    this.mSectionBean.e(this, null);
                    return;
                } else {
                    if (this.mSleepViewData.j().ap() > 180) {
                        b2 = nsf.h(R$string.IDS_core_sleep_suggesttion_nullstatus_content3);
                    } else {
                        b2 = nsf.b(R$string.IDS_core_sleep_suggesttion_nullstatus_content, nsf.a(R.plurals._2130903199_res_0x7f03009f, 3, 3));
                    }
                    this.mExpandReportListBean.e(b2);
                }
            } else {
                this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_fitness_total_sleep_data_title), scn.d(context, this.mSleepViewData.j().h() + this.mSleepViewData.j().ap()), -1, null).b(qmx.f16490a.get("TOTAL_SLEEP").intValue()));
            }
        }
        b(this.mSleepViewData.j().h() + this.mSleepViewData.j().ap());
        j();
    }

    private void b(int i) {
        if (i / 60 >= 15) {
            scx.e(BaseApplication.getContext(), this.mRequestDay, "EXCE_TRUSLEEP__TIME_ERROR");
        }
    }

    private void g() {
        if (this.mWeakRef == null || this.mWeakRef.get() == null) {
            LogUtil.a("R_Sleep_DayExpandListProvider", "updateCoreSleepDetailList, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        this.mExpandReportListBean.b(getCoreNightSleepBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_night_sleep).e(qmx.f16490a.get("SLEEP_DURATION").intValue()));
        c(knitSleepDetailActivity);
        this.mExpandReportListBean.b(getDeepSleepBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_deep_sleep_percent).e(qmx.f16490a.get("DEEP_RATIO").intValue()));
        e(knitSleepDetailActivity);
        this.mExpandReportListBean.b(getLightSleepBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_light_sleep_percent).e(qmx.f16490a.get("SHALLOW_RATIO").intValue()));
        b(knitSleepDetailActivity);
        this.mExpandReportListBean.b(getRemSleepBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_rem_sleep_percent).e(qmx.f16490a.get("REM_RATIO").intValue()));
        this.mExpandReportListBean.b(getDeepSleepContinuityBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_deep_sleep_continuity).e(qmx.f16490a.get("DEEP_CONT").intValue()));
        this.mExpandReportListBean.b(getWakeTimesBean(knitSleepDetailActivity, R$string.IDS_details_sleep_sleep_latency_time).e(qmx.f16490a.get("AWAKE_NUMS").intValue()));
        d(knitSleepDetailActivity);
        this.mExpandReportListBean.b(getBreathQualityBean(knitSleepDetailActivity, R$string.IDS_fitness_core_sleep_rdi_score).e(qmx.f16490a.get("BREATH_QUALITY").intValue()));
        if (this.mSleepViewData.j().ba() || getSleepStatData().ba()) {
            this.mExpandReportListBean.b(b().e(qmx.f16490a.get("TRUSLEEP_FIVE_BREATH_RATE").intValue()));
            this.mExpandReportListBean.b(a().e(qmx.f16490a.get("TRUSLEEP_FIVE_HEART_RATE").intValue()));
            this.mExpandReportListBean.b(d().e(qmx.f16490a.get("TRUSLEEP_FIVE_HRV").intValue()));
            this.mExpandReportListBean.b(c().e(qmx.f16490a.get("TRUSLEEP_FIVE_SpO2").intValue()));
            return;
        }
        this.mExpandReportListBean.e(getSleepPhysiologicalItem(knitSleepDetailActivity, "SLEEP_HEART_RATE").b(qmx.f16490a.get("SLEEP_HEART_RATE").intValue()));
        this.mExpandReportListBean.e(getSleepPhysiologicalItem(knitSleepDetailActivity, "SLEEP_BLOOD_OX").b(qmx.f16490a.get("SLEEP_BLOOD_OX").intValue()));
        this.mExpandReportListBean.e(getSleepPhysiologicalItem(knitSleepDetailActivity, "SLEEP_BREATH_RATIO").b(qmx.f16490a.get("SLEEP_BREATH_RATIO").intValue()));
    }

    private edo a() {
        return b(this.mSleepViewData.j().r(), new int[]{this.mSleepViewData.j().am(), this.mSleepViewData.j().ag()}, this.mSleepViewData.j().ab(), "TRUSLEEP_FIVE_HEART_RATE");
    }

    private edo d() {
        return b(this.mSleepViewData.j().t(), new int[]{this.mSleepViewData.j().aj(), this.mSleepViewData.j().ad()}, this.mSleepViewData.j().ac(), "TRUSLEEP_FIVE_HRV");
    }

    private edo c() {
        return b(this.mSleepViewData.j().s(), new int[]{this.mSleepViewData.j().as(), this.mSleepViewData.j().af()}, this.mSleepViewData.j().av(), "TRUSLEEP_FIVE_SpO2");
    }

    private void d(Context context) {
        int w = this.mSleepViewData.j().w();
        boolean z = this.mSleepViewData.j().h() > 0;
        if (w >= 70 || !z) {
            return;
        }
        scx.e(context, this.mRequestDay, "EXCE_BREATH__SCORE_ERROR");
    }

    private void c(Context context) {
        int v = this.mSleepViewData.j().v();
        boolean z = this.mSleepViewData.j().h() > 0;
        if (v > 90 && z) {
            scx.e(context, this.mRequestDay, "EXCE_DEEPSLEEP__MIX _ERROR");
        } else if (v < 5 && z) {
            scx.e(context, this.mRequestDay, "EXCE_DEEPSLEEP__MIN_ERROR");
        } else {
            LogUtil.a(getTag(), "deepSleepRateNum is ok");
        }
    }

    private void e(Context context) {
        int ax = this.mSleepViewData.j().ax();
        boolean z = this.mSleepViewData.j().h() > 0;
        if (ax > 90 && z) {
            scx.e(context, this.mRequestDay, "EXCE_LIGHTSLEEP__MIX _ERROR");
        } else if (ax < 5 && z) {
            scx.e(context, this.mRequestDay, "EXCE_LIGHTSLEEP__MIN_ERROR");
        } else {
            LogUtil.a("R_Sleep_DayExpandListProvider", "lightSleepRateNum is ok");
        }
    }

    private void b(Context context) {
        int ax = this.mSleepViewData.j().ax();
        boolean z = this.mSleepViewData.j().h() > 0;
        if (ax > 90 && z) {
            scx.e(context, this.mRequestDay, "EXCE_LIGHTSLEEP__MIX _ERROR");
        } else if (ax < 5 && z) {
            scx.e(context, this.mRequestDay, "EXCE_LIGHTSLEEP__MIN_ERROR");
        } else {
            LogUtil.a("R_Sleep_DayExpandListProvider", "lightSleepRateNum is ok");
        }
    }

    private edo b() {
        return b(this.mSleepViewData.j().p(), new int[]{this.mSleepViewData.j().an(), this.mSleepViewData.j().z()}, this.mSleepViewData.j().u(), "TRUSLEEP_FIVE_BREATH_RATE");
    }

    private edo b(int i, int[] iArr, int i2, String str) {
        String c2 = c(i, str);
        int a2 = a(i, iArr, str);
        String c3 = c(i, iArr, str);
        SpannableString drb_ = drb_(iArr, i2, str, i);
        edo edoVar = new edo(c2, drb_, c3, a2, this.mArrowRes, new SleepBaseExpandListProvider.d(this, getTag()).a(str, iArr, i, c3, a2));
        if (TextUtils.isEmpty(drb_)) {
            edoVar.b(false);
        }
        return edoVar;
    }

    private String c(int i, int[] iArr, String str) {
        int b2;
        return (this.mSleepViewData.j().c(str) && this.mSleepViewData.j().d(str) && (b2 = scn.b(i, iArr)) != -1) ? pqg.c(b2, str) : "";
    }

    private int a(int i, int[] iArr, String str) {
        int b2;
        if (this.mSleepViewData.j().c(str) && this.mSleepViewData.j().d(str) && (b2 = scn.b(i, iArr)) != -1) {
            return pqg.c(b2);
        }
        return nsf.c(R.color._2131297869_res_0x7f09064d);
    }

    private String c(int i, String str) {
        String h;
        int intValue = qmx.j.get(str).intValue();
        if (intValue != -1) {
            if (this.mSleepViewData.j().c(str)) {
                h = nsf.a(intValue, i, UnitUtil.e(i, 1, 0));
            } else {
                h = nsf.a(intValue, 0, nsf.h(R$string.IDS_physiological_index_invalid));
            }
        } else if (this.mSleepViewData.j().c(str)) {
            h = UnitUtil.e(i, 2, 0);
        } else {
            h = nsf.h(R$string.IDS_physiological_index_invalid);
        }
        return getItemTitle(qmx.f.get(str).intValue(), h);
    }

    private void e(final int i) {
        LogUtil.a("R_Sleep_DayExpandListProvider", "setDaySleepViewList");
        BaseApplication.getContext();
        final ArrayList arrayList = new ArrayList();
        if (this.mSleepViewData.h() instanceof CopyOnWriteArrayList) {
            arrayList.addAll(this.mSleepViewData.h());
        }
        if (koq.b(arrayList)) {
            LogUtil.a("R_Sleep_DayExpandListProvider", "daySleepDataList is null");
            return;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_fitness_core_sleep_noontime_sleep), (String) arrayList.get(i2), this.mArrowRes, new View.OnClickListener() { // from class: pmm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SleepDayExpandListProvider.this.drc_(i, arrayList, view);
                }
            }).b(qmx.f16490a.get("NOON_SLEEP").intValue()));
        }
    }

    public /* synthetic */ void drc_(int i, ArrayList arrayList, View view) {
        a(i, arrayList);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(int i, ArrayList<String> arrayList) {
        String e = UnitUtil.e((i - r0) / 60.0d, 1, 0);
        String e2 = UnitUtil.e(i % 60, 1, 0);
        if (this.mWeakRef == null) {
            LogUtil.a(getTag(), "gotoDayNoonSleepActivity, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        if (knitSleepDetailActivity == null) {
            LogUtil.a(getTag(), "gotoDayNoonSleepActivity, context == null");
        }
        DaySleepActivity.a(knitSleepDetailActivity, e, e2, getDaySleepStatus(i), arrayList);
    }

    private void j() {
        LogUtil.a("R_Sleep_DayExpandListProvider", "setWakeupFeeling");
        int ac = this.mSleepViewData.f().ac();
        LogUtil.a("R_Sleep_DayExpandListProvider", "wake up feeling index: " + ac);
        if (ac != 0) {
            this.mExpandReportListBean.e(new edx(BaseApplication.getContext().getString(R$string.IDS_wakeup_feel_title), a(ac), -1, null).b(qmx.f16490a.get("WAKE_UP_FEELING").intValue()));
        }
    }

    private String a(int i) {
        LogUtil.a("R_Sleep_DayExpandListProvider", "getWakeUpFeeling");
        if (i == 1) {
            return nsf.h(R$string.IDS_wakeup_feel_energized);
        }
        if (i != 2) {
            return i != 3 ? "" : nsf.h(R$string.IDS_wakeup_feel_drowsy);
        }
        return nsf.h(R$string.IDS_wakeup_feel_sleepy);
    }

    private SpannableString drb_(int[] iArr, int i, String str, int i2) {
        String h;
        String c2 = c(i2, str);
        int a2 = a(i2, iArr, str);
        String c3 = c(i2, iArr, str);
        String b2 = nsf.b(R$string.IDS_base_switch_description, nsf.h(qmx.h.get(str).intValue()));
        int intValue = qmx.i.get(str).intValue();
        if (this.mSleepViewData.j().d(str)) {
            if (intValue != R$string.IDS_base_line_percent) {
                h = nsf.a(qmx.i.get(str).intValue(), iArr[1], UnitUtil.e(iArr[0], 1, 0), UnitUtil.e(iArr[1], 1, 0));
            } else {
                h = pqg.c(qmx.i.get(str).intValue(), iArr[0], iArr[1]);
            }
        } else if (!jdl.ac(this.mSleepViewData.g())) {
            h = nsf.h(R$string.IDS_base_line_frequency_empty);
        } else if (!"TRUSLEEP_FIVE_SpO2".equals(str) && !"TRUSLEEP_FIVE_HEART_RATE".equals(str)) {
            String a3 = nsf.a(R.plurals._2130903429_res_0x7f030185, i, UnitUtil.e(i, 1, 0));
            if (i > 0) {
                h = nsf.b(R$string.IDS_show_base_line_remainder_day, a3);
            } else {
                h = nsf.h(R$string.IDS_base_line_frequency_empty);
            }
        } else {
            d(new a(this, str, c2, b2).c(iArr, i, i2, c3, a2));
            return new SpannableString("");
        }
        return makeSubTitleSpannable(h, b2, str, iArr);
    }

    private void d(final a aVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: pmp
            @Override // java.lang.Runnable
            public final void run() {
                SleepDayExpandListProvider.a(SleepDayExpandListProvider.a.this);
            }
        });
    }

    public static /* synthetic */ void a(a aVar) {
        HiUserPreference userPreference;
        String b2 = aVar.b();
        b2.hashCode();
        if (b2.equals("TRUSLEEP_FIVE_HEART_RATE")) {
            userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(f9810a.get(0));
        } else if (b2.equals("TRUSLEEP_FIVE_SpO2")) {
            userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(f9810a.get(1));
        } else {
            ReleaseLogUtil.d("R_Sleep_DayExpandListProvider", "unKonw itemType: ", b2);
            return;
        }
        if (userPreference == null) {
            ReleaseLogUtil.c("R_Sleep_DayExpandListProvider", "switch status is empty");
        } else {
            LogUtil.a("R_Sleep_DayExpandListProvider", "switch result: ", userPreference);
            aVar.onResponse(0, userPreference);
        }
    }

    public static class a implements CommonUiBaseResponse {

        /* renamed from: a, reason: collision with root package name */
        private boolean f9811a = false;
        private String b;
        private String c;
        private int d;
        private int[] e;
        private String f;
        private String g;
        private WeakReference<SleepBaseExpandListProvider> h;
        private int i;
        private int j;

        a(SleepBaseExpandListProvider sleepBaseExpandListProvider, String str, String str2, String str3) {
            this.h = new WeakReference<>(sleepBaseExpandListProvider);
            this.c = str;
            this.f = str2;
            this.g = str3;
        }

        public a c(int[] iArr, int i, int i2, String str, int i3) {
            this.e = iArr;
            this.d = i;
            this.j = i2;
            this.b = str;
            this.i = i3;
            this.f9811a = true;
            return this;
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            String str;
            SleepBaseExpandListProvider sleepBaseExpandListProvider = this.h.get();
            if (sleepBaseExpandListProvider == null || !(obj instanceof HiUserPreference)) {
                ReleaseLogUtil.d("R_Sleep_DayExpandListProvider", "provider: ", sleepBaseExpandListProvider, " objectData: ", obj);
                return;
            }
            if (!this.f9811a) {
                ReleaseLogUtil.e("R_Sleep_DayExpandListProvider", "init status is false");
                return;
            }
            if ("1".equals(((HiUserPreference) obj).getValue())) {
                int i2 = this.d;
                String a2 = nsf.a(R.plurals._2130903429_res_0x7f030185, i2, UnitUtil.e(i2, 1, 0));
                if (this.d > 0) {
                    str = nsf.b(R$string.IDS_show_base_line_remainder_day, a2);
                } else {
                    str = nsf.h(R$string.IDS_base_line_frequency_empty);
                }
            } else {
                str = "";
            }
            SpannableString makeSubTitleSpannable = sleepBaseExpandListProvider.makeSubTitleSpannable(str, this.g, this.c, this.e);
            if (TextUtils.isEmpty(str)) {
                String h = nsf.h(qmx.h.get(this.c).intValue());
                int cKD_ = nsi.cKD_(makeSubTitleSpannable, h);
                makeSubTitleSpannable.setSpan(new b(sleepBaseExpandListProvider, this.c, this.f, this.g).d(this.d, this.e, this.j, this.b, this.i), cKD_, h.length() + cKD_, 17);
            }
            ObserverManagerUtil.c(ObserveLabels.REFRESH_REPORT_BEAN, makeSubTitleSpannable, this.f);
        }

        public String b() {
            return this.c;
        }
    }

    static class b extends ClickableSpan {

        /* renamed from: a, reason: collision with root package name */
        private String f9812a;
        private int b;
        private boolean c = false;
        private int[] d;
        private String e;
        private WeakReference<SleepBaseExpandListProvider> f;
        private int g;
        private int h;
        private String i;
        private String j;

        public b(SleepBaseExpandListProvider sleepBaseExpandListProvider, String str, String str2, String str3) {
            this.f = new WeakReference<>(sleepBaseExpandListProvider);
            this.e = str;
            this.j = str2;
            this.i = str3;
        }

        public b d(int i, int[] iArr, int i2, String str, int i3) {
            this.b = i;
            this.d = iArr;
            this.h = i2;
            this.f9812a = str;
            this.g = i3;
            this.c = true;
            return this;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            if (nsn.o()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (!this.c) {
                ReleaseLogUtil.e("R_Sleep_DayExpandListProvider", "init status is false");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            SleepBaseExpandListProvider sleepBaseExpandListProvider = this.f.get();
            if (sleepBaseExpandListProvider == null) {
                ReleaseLogUtil.d("R_Sleep_DayExpandListProvider", "provider is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            String str = this.e;
            str.hashCode();
            if (str.equals("TRUSLEEP_FIVE_HEART_RATE")) {
                AppRouter.zi_(Uri.parse("huaweischeme://healthapp/router/heartRateSwitch")).j();
                ObserverManagerUtil.d(new d(sleepBaseExpandListProvider, this.e, this.j, this.i).b(this.b, this.d, this.h, this.f9812a, this.g), ObserveLabels.HEART_RATE_SWITCH_STATUS);
            } else if (str.equals("TRUSLEEP_FIVE_SpO2")) {
                AppRouter.zi_(Uri.parse("huaweischeme://healthapp/router/bloodOxygenSwitch")).j();
                ObserverManagerUtil.d(new d(sleepBaseExpandListProvider, this.e, this.j, this.i).b(this.b, this.d, this.h, this.f9812a, this.g), ObserveLabels.SPO2_MONITOR_SWITCH_STATUS);
            } else {
                ReleaseLogUtil.d("R_Sleep_DayExpandListProvider", "unKnow type: ", this.e);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(nsf.c(nrt.a(BaseApplication.getContext()) ? R.color._2131299098_res_0x7f090b1a : R.color._2131299327_res_0x7f090bff));
            textPaint.setUnderlineText(false);
        }
    }

    static class d implements Observer {

        /* renamed from: a, reason: collision with root package name */
        private String f9813a;
        private boolean b = false;
        private int[] c;
        private SleepBaseExpandListProvider d;
        private int e;
        private int f;
        private String g;
        private int h;
        private String i;
        private String j;

        d(SleepBaseExpandListProvider sleepBaseExpandListProvider, String str, String str2, String str3) {
            this.d = sleepBaseExpandListProvider;
            this.f9813a = str;
            this.i = str2;
            this.g = str3;
        }

        public d b(int i, int[] iArr, int i2, String str, int i3) {
            this.e = i;
            this.c = iArr;
            this.f = i2;
            this.j = str;
            this.h = i3;
            this.b = true;
            return this;
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!this.b) {
                ReleaseLogUtil.e("R_Sleep_DayExpandListProvider", "init status is false");
                return;
            }
            if (this.d != null) {
                Object obj = objArr[0];
                if (obj instanceof Integer) {
                    if (((Integer) obj).intValue() != 1) {
                        ReleaseLogUtil.d("switch status is ", (Integer) objArr[0]);
                        return;
                    }
                    String str2 = this.f9813a;
                    str2.hashCode();
                    if (str2.equals("TRUSLEEP_FIVE_HEART_RATE")) {
                        int i = this.e;
                        ObserverManagerUtil.c(ObserveLabels.REFRESH_REPORT_BEAN, this.d.makeSubTitleSpannable(nsf.b(R$string.IDS_show_base_line_remainder_day, nsf.a(R.plurals._2130903429_res_0x7f030185, i, UnitUtil.e(i, 1, 0))), this.g, this.f9813a, this.c), this.i);
                        return;
                    }
                    if (str2.equals("TRUSLEEP_FIVE_SpO2")) {
                        int i2 = this.e;
                        ObserverManagerUtil.c(ObserveLabels.REFRESH_REPORT_BEAN, this.d.makeSubTitleSpannable(nsf.b(R$string.IDS_show_base_line_remainder_day, nsf.a(R.plurals._2130903429_res_0x7f030185, i2, UnitUtil.e(i2, 1, 0))), this.g, this.f9813a, this.c), this.i);
                        return;
                    }
                    ReleaseLogUtil.d("R_Sleep_DayExpandListProvider", "unKnow type: ", this.f9813a);
                    return;
                }
            }
            ReleaseLogUtil.d("R_Sleep_DayExpandListProvider", "provider is null");
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected String getTag() {
        return "R_Sleep_DayExpandListProvider";
    }
}
