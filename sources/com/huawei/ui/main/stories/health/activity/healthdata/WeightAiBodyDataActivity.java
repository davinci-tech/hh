package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightAiBodyDataActivity;
import com.huawei.ui.main.stories.health.fragment.ArmCircumferenceFragment;
import com.huawei.ui.main.stories.health.fragment.BustGirthFragment;
import com.huawei.ui.main.stories.health.fragment.CalvesFragment;
import com.huawei.ui.main.stories.health.fragment.HipLineFragment;
import com.huawei.ui.main.stories.health.fragment.ThighGirthFragment;
import com.huawei.ui.main.stories.health.fragment.WaistGirthFragment;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.AiBodyShapeWaistToHipRatioFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyShapeFragment;
import com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback;
import defpackage.cfe;
import defpackage.koq;
import defpackage.nqx;
import defpackage.qku;
import defpackage.qsj;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class WeightAiBodyDataActivity extends WeightDataActivity {
    private String i = "startTime";
    private String f = "endTime";
    private long h = 0;

    @Override // com.huawei.ui.main.stories.health.activity.healthdata.WeightDataActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e.setTitleText(BaseApplication.getContext().getString(R$string.IDS_aibs_ai_body_shape_data));
        Serializable serializableExtra = getIntent().getSerializableExtra("WeightBean");
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.c("HealthWeight_WeightAiBodyDataActivity", "onCreate Intent is null");
            return;
        }
        int intExtra = intent.getIntExtra("position", 0);
        long longExtra = intent.getLongExtra(this.i, this.h);
        long longExtra2 = intent.getLongExtra(this.f, this.h);
        long j = this.h;
        if (longExtra != j && longExtra2 != j) {
            LogUtil.d("HealthWeight_WeightAiBodyDataActivity", "position is ", Integer.valueOf(intExtra), "startTime is ", Long.valueOf(longExtra), "endTime is ", Long.valueOf(longExtra2));
            qsj.d(longExtra, longExtra2, new d(this, intExtra));
        } else if (serializableExtra instanceof qku) {
            d();
            e(intExtra, (qku) serializableExtra);
            if (intExtra == 0 && koq.d(this.b, intExtra)) {
                d(this.b.get(intExtra).intValue());
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements WeightAndAiBodyShapeCallback {
        private int c;
        private WeakReference<WeightAiBodyDataActivity> e;

        d(WeightAiBodyDataActivity weightAiBodyDataActivity, int i) {
            this.e = new WeakReference<>(weightAiBodyDataActivity);
            this.c = i;
        }

        @Override // com.huawei.ui.main.stories.health.util.WeightAndAiBodyShapeCallback
        public void getWeightAndAIBodyShape(ArrayList<cfe> arrayList, ArrayList<qku> arrayList2) {
            if (koq.b(arrayList2)) {
                LogUtil.c("HealthWeight_WeightAiBodyDataActivity", "GetAiBodyShapeDataCallBack aiBodyData is null");
                return;
            }
            final qku qkuVar = arrayList2.get(0);
            if (qkuVar == null) {
                ReleaseLogUtil.a("HealthWeight_WeightAiBodyDataActivity", "GetAiBodyShapeDataCallBack getWeightAndAIBodyShape aiBodyShapeBean is null");
            } else {
                LogUtil.d("HealthWeight_WeightAiBodyDataActivity", "aiBodyShapeBean is ", qkuVar.toString());
                HandlerExecutor.a(new Runnable() { // from class: qeu
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeightAiBodyDataActivity.d.this.e(qkuVar);
                    }
                });
            }
        }

        public /* synthetic */ void e(qku qkuVar) {
            WeightAiBodyDataActivity weightAiBodyDataActivity = this.e.get();
            if (weightAiBodyDataActivity != null && !weightAiBodyDataActivity.isFinishing() && !weightAiBodyDataActivity.isDestroyed()) {
                weightAiBodyDataActivity.d();
                weightAiBodyDataActivity.e(this.c, qkuVar);
                if (this.c == 0 && koq.d(weightAiBodyDataActivity.b, this.c)) {
                    weightAiBodyDataActivity.d(weightAiBodyDataActivity.b.get(this.c).intValue());
                    return;
                }
                return;
            }
            ReleaseLogUtil.a("HealthWeight_WeightAiBodyDataActivity", "GetAiBodyShapeDataCallBack getWeightAndAIBodyShape activity ", weightAiBodyDataActivity);
        }
    }

    @Override // com.huawei.ui.main.stories.health.activity.healthdata.WeightDataActivity
    protected String c() {
        return AnalyticsValue.HEALTH_HOME_AI_BODY_DETAIL_TAB_2160131.value();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Context context = BaseApplication.getContext();
        this.f10092a.add(context.getString(R$string.IDS_hw_weight_body_shape));
        this.c.add(new BodyShapeFragment());
        this.b.add(27);
        this.f10092a.add(context.getString(R$string.IDS_weight_waist_to_hip_ratio));
        this.c.add(new AiBodyShapeWaistToHipRatioFragment());
        this.b.add(28);
        this.f10092a.add(context.getString(R$string.IDS_aibs_bust_girth));
        this.c.add(new BustGirthFragment());
        this.b.add(29);
        this.f10092a.add(context.getString(R$string.IDS_aibs_waist_girth));
        this.c.add(new WaistGirthFragment());
        this.b.add(30);
        this.f10092a.add(context.getString(R$string.IDS_aibs_arm_circumference));
        this.c.add(new HipLineFragment());
        this.b.add(31);
        this.f10092a.add(context.getString(R$string.IDS_aibs_large_arm));
        this.c.add(new ArmCircumferenceFragment());
        this.b.add(32);
        this.f10092a.add(context.getString(R$string.IDS_aibs_thigh_girth));
        this.c.add(new ThighGirthFragment());
        this.b.add(33);
        this.f10092a.add(context.getString(R$string.IDS_aibs_calves_girth));
        this.c.add(new CalvesFragment());
        this.b.add(34);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, qku qkuVar) {
        nqx nqxVar = new nqx(this, this.g, this.d);
        int i2 = 0;
        while (i2 < this.f10092a.size()) {
            Bundle bundle = new Bundle();
            Fragment fragment = this.c.get(i2);
            bundle.putSerializable(WeightBodyDataFragment.AI_BODY_SHAPE_BEAN, qkuVar);
            fragment.setArguments(bundle);
            nqxVar.c(this.d.c(this.f10092a.get(i2)), fragment, i == i2);
            i2++;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
