package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider;
import defpackage.edx;
import defpackage.fdl;
import defpackage.fdn;
import defpackage.fdo;
import defpackage.fdp;
import defpackage.fdq;
import defpackage.nsf;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class SleepYearExpandListProvider extends SleepBaseExpandListProvider {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected void handleTruSleepData(CharSequence[] charSequenceArr, SleepBaseExpandListProvider.c cVar) {
    }

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
        if (getType() != 1) {
            b();
        }
    }

    private void b() {
        LogUtil.a("R_Sleep_YearExpandListProvider", "updateYearMultiSleepDataUi");
        if (this.mWeakRef == null || this.mWeakRef.get() == null) {
            LogUtil.a("R_Sleep_YearExpandListProvider", "updateYearMultiSleepDataUi, mWeakRef is invalid");
            return;
        }
        KnitSleepDetailActivity knitSleepDetailActivity = this.mWeakRef.get();
        String itemTitle = getItemTitle(R$string.IDS_manual_sleep_average_sleep_duration, getFormatTime(c() / 60, c() % 60));
        if (getType() == 2) {
            c(itemTitle, knitSleepDetailActivity);
            return;
        }
        if (getType() == 3) {
            int bp = ((fdq) this.mSleepViewData.j()).bp();
            this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_manual_sleep_sleep_wear_core), nsf.a(R.plurals.IDS_user_profile_achieve_num_day, bp, UnitUtil.e(bp, 1, 0)), -1, null));
            this.mExpandReportListBean.b(getSleepTimeBean(itemTitle, c()));
        } else {
            if (getType() == 4) {
                int am = ((fdo) this.mSleepViewData.f()).am();
                this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_manual_sleep_phone_sleep), nsf.a(R.plurals.IDS_user_profile_achieve_num_day, am, UnitUtil.e(am, 1, 0)), -1, null));
                this.mExpandReportListBean.b(getSleepTimeBean(itemTitle, c()));
                this.mExpandReportListBean.b(getBedTimeBean(getBedTime(), R$string.IDS_manual_sleep_average_bed_duration_new));
                this.mExpandReportListBean.b(getSleepLatencyBean(getSleepLatency(), R$string.IDS_avg_sleep_latency_new));
                this.mExpandReportListBean.b(getSleepEfficientBean(knitSleepDetailActivity, getSleepEfficientValue(), R$string.IDS_avg_sleep_efficiency_new));
                return;
            }
            if (getType() == 5) {
                int v = ((fdl) this.mSleepViewData.c()).v();
                this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_manual_sleep_wear_normal_sleep), nsf.a(R.plurals.IDS_user_profile_achieve_num_day, v, UnitUtil.e(v, 1, 0)), -1, null));
                this.mExpandReportListBean.b(getSleepTimeBean(itemTitle, c()));
                return;
            }
            LogUtil.a("R_Sleep_YearExpandListProvider", "no type match");
        }
    }

    private void c(String str, Context context) {
        int ad = ((fdn) this.mSleepViewData.d()).ad();
        this.mExpandReportListBean.e(new edx(nsf.h(R$string.IDS_manual_sleep_title), nsf.a(R.plurals.IDS_user_profile_achieve_num_day, ad, UnitUtil.e(ad, 1, 0)), -1, null));
        this.mExpandReportListBean.b(getSleepTimeBean(str, c()));
        this.mExpandReportListBean.b(getBedTimeBean(getBedTime(), R$string.IDS_manual_sleep_average_bed_duration_new));
        this.mExpandReportListBean.b(getSleepLatencyBean(getSleepLatency(), R$string.IDS_avg_sleep_latency_new));
        this.mExpandReportListBean.b(getSleepEfficientBean(context, getSleepEfficientValue(), R$string.IDS_avg_sleep_efficiency_new));
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    public void setProviderActive(fdp fdpVar) {
        this.mIsActive = fdpVar.m();
        int type = getType();
        boolean z = false;
        if (type == 2) {
            if (this.mIsActive && ((fdn) fdpVar.d()).ad() > 0) {
                z = true;
            }
            this.mIsActive = z;
            return;
        }
        if (type == 3) {
            if (this.mIsActive && ((fdq) fdpVar.j()).bp() > 0) {
                z = true;
            }
            this.mIsActive = z;
            return;
        }
        if (type == 4) {
            if (this.mIsActive && ((fdo) fdpVar.f()).am() > 0) {
                z = true;
            }
            this.mIsActive = z;
            return;
        }
        if (type == 5) {
            if (this.mIsActive && ((fdl) fdpVar.c()).v() > 0) {
                z = true;
            }
            this.mIsActive = z;
            return;
        }
        LogUtil.a("R_Sleep_YearExpandListProvider", "onSetSectionBeanData, getType: ", Integer.valueOf(getType()));
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    public int getBedTime() {
        int k;
        if (getType() == 4) {
            k = this.mSleepViewData.f().k();
        } else if (getType() == 2) {
            k = this.mSleepViewData.d().k();
        } else {
            k = getType() == 3 ? this.mSleepViewData.j().k() : 0;
        }
        LogUtil.a("R_Sleep_YearExpandListProvider", "getBedTime deviceType ", this.mSleepViewData.i(), " sleepBedTime ", Integer.valueOf(k));
        return k;
    }

    private int c() {
        if (getType() == 3) {
            return this.mSleepViewData.j().h();
        }
        if (getType() == 4) {
            return this.mSleepViewData.f().h();
        }
        if (getType() == 2) {
            return this.mSleepViewData.d().h();
        }
        if (getType() == 5) {
            return this.mSleepViewData.c().h();
        }
        return this.mSleepViewData.c().h();
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    public int getSleepLatency() {
        if (getType() == 4) {
            return this.mSleepViewData.f().i();
        }
        if (getType() == 2) {
            return this.mSleepViewData.d().i();
        }
        if (getType() == 3) {
            return this.mSleepViewData.j().i();
        }
        return -1;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    public int getSleepEfficientValue() {
        if (getType() == 4) {
            return this.mSleepViewData.f().j();
        }
        if (getType() == 2) {
            return this.mSleepViewData.d().j();
        }
        if (getType() == 3) {
            return this.mSleepViewData.j().j();
        }
        return -1;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepBaseExpandListProvider
    protected String getTag() {
        return "R_Sleep_YearExpandListProvider";
    }
}
