package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightBodyDataActivity;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BasalMetabolicRateFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyAgeFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyMassIndexFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyShapeFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyTypeFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyWeightFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BoneMineralContentFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.FatFreeFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.FatRateFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.HeartRateFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.MuscleMassFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.PressureIndexFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.ProteinFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.RelativeAppendicularSkeletalMuscleFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.SkeletalMuscleFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.TotalBodyWaterFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.VisceralFatGradeFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.WaistToHipRatioFragment;
import com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback;
import defpackage.cfe;
import defpackage.koq;
import defpackage.nqx;
import defpackage.qku;
import defpackage.qrd;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.HiDateUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class WeightBodyDataActivity extends WeightDataActivity {
    private boolean h;
    private boolean i;
    private boolean l;
    private int o;
    private int n = 0;
    private long m = 0;
    private boolean j = false;
    private boolean f = false;

    @Override // com.huawei.ui.main.stories.health.activity.healthdata.WeightDataActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        HiAggregateOption hiAggregateOption;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("HealthWeight_WeightBodyDataActivity", "onCreate Intent is null");
            return;
        }
        this.n = intent.getIntExtra("start_type", this.n);
        this.m = intent.getLongExtra("start_time", this.m);
        this.j = intent.getBooleanExtra("is_show_change", this.j);
        this.f = intent.getBooleanExtra("isGuestMeasure", this.f);
        this.i = !qrp.d();
        this.l = intent.getBooleanExtra("isWeight", false);
        this.h = intent.getBooleanExtra("isBodyType", false);
        this.o = intent.getIntExtra("position", 0);
        cfe cfeVar = (cfe) intent.getSerializableExtra("WeightBean");
        LogUtil.a("HealthWeight_WeightBodyDataActivity", "onCreate mStartType ", Integer.valueOf(this.n), " mStartTime ", Long.valueOf(this.m), " mIsShowChange ", Boolean.valueOf(this.j), " mIsGuestMeasure ", Boolean.valueOf(this.f), " mIsHideCard ", Boolean.valueOf(this.i), " mIsWeight ", Boolean.valueOf(this.l), " mIsBodyType ", Boolean.valueOf(this.h), " mPosition ", Integer.valueOf(this.o), " weightBean ", cfeVar);
        if (cfeVar == null) {
            long longExtra = intent.getLongExtra("startTime", 0L);
            long longExtra2 = intent.getLongExtra("endTime", 0L);
            boolean booleanExtra = intent.getBooleanExtra("showTips", false);
            LogUtil.a("HealthWeight_WeightBodyDataActivity", "onCreate startTime ", Long.valueOf(longExtra), " endTime ", Long.valueOf(longExtra2), " isAiBodyShape ", Boolean.valueOf(booleanExtra));
            HiAggregateOption hiAggregateOption2 = new HiAggregateOption();
            hiAggregateOption2.setTimeRange(longExtra, longExtra2);
            hiAggregateOption2.setAggregateType(1);
            hiAggregateOption2.setGroupUnitType(0);
            hiAggregateOption2.setCount(1);
            if (booleanExtra) {
                hiAggregateOption = new HiAggregateOption();
                hiAggregateOption.setTimeRange(HiDateUtil.t(longExtra), HiDateUtil.f(longExtra2));
                hiAggregateOption.setAggregateType(1);
                hiAggregateOption.setGroupUnitType(0);
                hiAggregateOption.setCount(1);
                hiAggregateOption.setSortOrder(1);
            } else {
                hiAggregateOption = null;
            }
            qsj.e(MultiUsersManager.INSTANCE.getCurrentUser(), hiAggregateOption2, hiAggregateOption, new d(this));
            return;
        }
        c(cfeVar);
    }

    /* loaded from: classes6.dex */
    static class d implements WeightAndAiBodyShapeCallback {
        private final WeakReference<WeightBodyDataActivity> e;

        d(WeightBodyDataActivity weightBodyDataActivity) {
            this.e = new WeakReference<>(weightBodyDataActivity);
        }

        @Override // com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback
        public void getWeightAndAIBodyShape(ArrayList<cfe> arrayList, ArrayList<qku> arrayList2) {
            if (koq.b(arrayList)) {
                LogUtil.h("HealthWeight_WeightBodyDataActivity", "InnerWeightAndAiBodyShapeCallback list ", arrayList);
                return;
            }
            WeightBodyDataActivity weightBodyDataActivity = this.e.get();
            if (weightBodyDataActivity == null || weightBodyDataActivity.isFinishing() || weightBodyDataActivity.isDestroyed()) {
                LogUtil.h("HealthWeight_WeightBodyDataActivity", "InnerWeightAndAiBodyShapeCallback getWeightAndAIBodyShape activity ", weightBodyDataActivity);
                return;
            }
            cfe cfeVar = arrayList.get(0);
            qsj.e(cfeVar, koq.c(arrayList2) ? arrayList2.get(0) : null);
            weightBodyDataActivity.c(cfeVar);
        }
    }

    @Override // com.huawei.ui.main.stories.health.activity.healthdata.WeightDataActivity
    protected String c() {
        return AnalyticsValue.HEALTH_HOME_BODY_DETAIL_TAB_2030065.value();
    }

    private int a() {
        int indexOf = (this.b == null || this.b.isEmpty()) ? 0 : this.b.indexOf(13);
        LogUtil.a("HealthWeight_WeightBodyDataActivity", "getBodyTypePosition position ", Integer.valueOf(indexOf));
        return indexOf;
    }

    private void o(cfe cfeVar) {
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightBodyDataActivity", "initFragment WeightBean is null");
            return;
        }
        g(cfeVar);
        a(cfeVar);
        l(cfeVar);
        p(cfeVar);
        s(cfeVar);
        q(cfeVar);
        v(cfeVar);
        h(cfeVar);
        d(cfeVar);
        e(cfeVar);
        t(cfeVar);
        f(cfeVar);
        r(cfeVar);
        m(cfeVar);
        i(cfeVar);
        b(cfeVar);
        n(cfeVar);
        k(cfeVar);
    }

    private void g(cfe cfeVar) {
        if (cfeVar.isVisible(0, this.i)) {
            this.f10092a.add(qrd.i(0));
            this.b.add(0);
            this.c.add(new BodyWeightFragment());
        }
    }

    private void a(cfe cfeVar) {
        if (cfeVar.isVisible(2, this.i)) {
            this.f10092a.add(qrd.b(0));
            this.b.add(2);
            this.c.add(new BodyMassIndexFragment());
        }
    }

    private void l(cfe cfeVar) {
        if (cfeVar.isVisible(1, this.i)) {
            this.f10092a.add(qrd.j(0));
            this.b.add(1);
            this.c.add(new FatRateFragment());
        }
    }

    private void p(cfe cfeVar) {
        if (cfeVar.isVisible(10, this.i)) {
            this.f10092a.add(qrd.l(0));
            this.b.add(10);
            this.c.add(new SkeletalMuscleFragment());
        }
    }

    private void s(cfe cfeVar) {
        if (cfeVar.isVisible(5, this.i)) {
            this.f10092a.add(qrd.q(0));
            this.b.add(5);
            this.c.add(new VisceralFatGradeFragment());
        }
    }

    private void q(cfe cfeVar) {
        if (cfeVar.isVisible(26, this.i)) {
            this.f10092a.add(qrd.o(0));
            this.b.add(26);
            this.c.add(new RelativeAppendicularSkeletalMuscleFragment());
        }
    }

    private void v(cfe cfeVar) {
        if (cfeVar.isVisible(25, this.i)) {
            if (cfeVar.ao() > 0.0d) {
                this.f10092a.add(getResources().getString(R$string.IDS_weight_waist_to_hip_ratio));
            } else {
                this.f10092a.add(qrd.r(0));
            }
            this.b.add(25);
            this.c.add(new WaistToHipRatioFragment());
        }
    }

    private void h(cfe cfeVar) {
        if (cfeVar.isVisible(13, this.i)) {
            this.f10092a.add(qrd.c(0));
            this.b.add(13);
            this.c.add(new BodyTypeFragment());
        }
    }

    private void d(cfe cfeVar) {
        if (cfeVar.isVisible(27, this.i)) {
            this.f10092a.add(qrd.d(0));
            this.b.add(27);
            this.c.add(new BodyShapeFragment());
        }
    }

    private void e(cfe cfeVar) {
        if (cfeVar.isVisible(4, this.i)) {
            this.f10092a.add(qrd.a(0));
            this.b.add(4);
            this.c.add(new BasalMetabolicRateFragment());
        }
    }

    private void t(cfe cfeVar) {
        if (cfeVar.isVisible(3, this.i)) {
            this.f10092a.add(qrd.s(0));
            this.b.add(3);
            this.c.add(new TotalBodyWaterFragment());
        }
    }

    private void f(cfe cfeVar) {
        if (cfeVar.isVisible(7, this.i)) {
            this.f10092a.add(qrd.h(0));
            this.b.add(7);
            this.c.add(new BoneMineralContentFragment());
        }
    }

    private void r(cfe cfeVar) {
        if (cfeVar.isVisible(8, this.i)) {
            this.f10092a.add(qrd.n(0));
            this.b.add(8);
            this.c.add(new ProteinFragment());
        }
    }

    private void m(cfe cfeVar) {
        if (cfeVar.isVisible(6, this.i)) {
            this.f10092a.add(qrd.k(0));
            this.b.add(6);
            this.c.add(new MuscleMassFragment());
        }
    }

    private void i(cfe cfeVar) {
        if (cfeVar.isVisible(14, this.i)) {
            this.f10092a.add(qrd.g(0));
            this.b.add(14);
            this.c.add(new FatFreeFragment());
        }
    }

    private void b(cfe cfeVar) {
        if (cfeVar.isVisible(9, this.i)) {
            this.f10092a.add(qrd.e(0));
            this.b.add(9);
            this.c.add(new BodyAgeFragment());
        }
    }

    private void n(cfe cfeVar) {
        if (cfeVar.isVisible(11, this.i)) {
            this.f10092a.add(qrd.f(0));
            this.b.add(11);
            this.c.add(new HeartRateFragment());
        }
    }

    private void k(cfe cfeVar) {
        if (cfeVar.isVisible(12, this.i)) {
            this.f10092a.add(qrd.m(0));
            this.b.add(12);
            this.c.add(new PressureIndexFragment());
        }
    }

    private void e(int i, cfe cfeVar) {
        nqx nqxVar = new nqx(this, this.g, this.d);
        int i2 = 0;
        while (i2 < this.f10092a.size()) {
            Bundle bundle = new Bundle();
            bundle.putInt("start_type", this.n);
            bundle.putLong("start_time", this.m);
            bundle.putBoolean("is_show_change", this.j);
            bundle.putSerializable("WeightBean", cfeVar);
            bundle.putBoolean("isGuestMeasure", this.f);
            bundle.putBoolean("is_oversea", Utils.o());
            Fragment fragment = this.c.get(i2);
            fragment.setArguments(bundle);
            nqxVar.c(this.d.c(this.f10092a.get(i2)), fragment, i == i2);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public void c(final cfe cfeVar) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: qew
                @Override // java.lang.Runnable
                public final void run() {
                    WeightBodyDataActivity.this.c(cfeVar);
                }
            });
            return;
        }
        o(cfeVar);
        int a2 = this.l ? this.o : this.h ? a() : this.o + 1;
        if (a2 == 0 && koq.d(this.b, a2)) {
            d(this.b.get(a2).intValue());
        }
        e(a2, cfeVar);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
