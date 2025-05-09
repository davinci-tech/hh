package com.huawei.health.sport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class AiFitnessPlanEnergyReplacementCard extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f2995a;
    private TextView c;

    public AiFitnessPlanEnergyReplacementCard(Context context) {
        super(context);
        c(context);
    }

    public AiFitnessPlanEnergyReplacementCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    public AiFitnessPlanEnergyReplacementCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c(context);
    }

    private void c(Context context) {
        LayoutInflater.from(context).inflate(R.layout.ai_fitness_plan_energy_replacement_card, this);
        this.f2995a = findViewById(R.id.jump_to_int_plan_btn);
        this.c = (TextView) findViewById(R.id.current_sport_consumption_text);
    }

    public void setCalorieConsumption(float f) {
        this.c.setText(getContext().getString(R$string.IDS_hw_int_plan_exercise_consumption, getContext().getString(R$string.IDS_kcal_unit_no_space, UnitUtil.e(Math.round(f / 1000.0f), 1, 0))));
    }

    public void setJumpBtnClickListener(View.OnClickListener onClickListener) {
        this.f2995a.setOnClickListener(onClickListener);
    }
}
