package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.huawei.basichealthmodel.ui.adapter.HealthGoalAdapter;
import com.huawei.basichealthmodel.ui.view.HealthGoalView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.aya;
import defpackage.aza;
import defpackage.azi;
import defpackage.ban;

/* loaded from: classes8.dex */
public class HealthGoalView extends FrameLayout implements HealthGoalAdapter.HealthPlanListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthGoalAdapter f1923a;
    private HealthButton b;
    private int c;
    private String d;

    public HealthGoalView(Context context) {
        super(context);
        b(context);
    }

    public HealthGoalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context);
    }

    public HealthGoalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context);
    }

    private void b(final Context context) {
        View.inflate(context, R.layout.health_goal_layout, this);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.health_create_goal_recycler);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(context));
        healthRecycleView.setIsScroll(false);
        healthRecycleView.setIsConsumption(true);
        HealthGoalAdapter healthGoalAdapter = new HealthGoalAdapter(this);
        this.f1923a = healthGoalAdapter;
        healthRecycleView.setAdapter(healthGoalAdapter);
        this.b = (HealthButton) findViewById(R.id.health_create_goal_next);
        b(0.3f, false);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: azb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthGoalView.this.lz_(context, view);
            }
        });
    }

    public /* synthetic */ void lz_(Context context, View view) {
        if (TextUtils.isEmpty(this.d)) {
            LogUtil.a("HealthLife_HealthGoalView", "mJumpPlanParams is empty");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            azi.b(context, "com.huawei.health.h5.blood-pressure", this.d);
            aza.d(AnalyticsValue.HEALTH_MODEL_CHALLENGE_CHANGE_2119076, this.c);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void setGoalData() {
        HealthGoalAdapter healthGoalAdapter = this.f1923a;
        if (healthGoalAdapter != null) {
            healthGoalAdapter.c(ban.c());
        }
    }

    @Override // com.huawei.basichealthmodel.ui.adapter.HealthGoalAdapter.HealthPlanListener
    public void onPlanClick(aya ayaVar) {
        if (ayaVar == null) {
            LogUtil.h("HealthLife_HealthGoalView", "onPlanClick bean is null");
            b(0.3f, false);
            return;
        }
        boolean z = ayaVar.c() != 0;
        int a2 = ayaVar.a();
        this.c = a2;
        this.d = z ? ban.d(a2) : "";
        b(z ? 1.0f : 0.3f, z);
    }

    private void b(float f, boolean z) {
        this.b.setAlpha(f);
        this.b.setClickable(z);
    }
}
