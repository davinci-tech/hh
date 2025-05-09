package com.huawei.health.suggestion.ui.plan.viewholder;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class IntAiDialogViewHolder extends AbsFitnessViewHolder<Object> {

    /* renamed from: a, reason: collision with root package name */
    private final HealthTextView f3286a;
    private final Animation b;
    private final HealthTextView c;
    private final ImageView d;
    private final HealthTextView e;

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public View.OnClickListener f3287a;
        public View.OnClickListener b;
        public int c;
        public String d;
        public String e;
        public String j;
    }

    public IntAiDialogViewHolder(View view) {
        super(view);
        this.d = (ImageView) view.findViewById(R.id.warm_up_men);
        this.f3286a = (HealthTextView) view.findViewById(R.id.coach_text);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.coach_yes);
        this.c = healthTextView;
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.coach_no);
        this.e = healthTextView2;
        healthTextView.setVisibility(8);
        healthTextView2.setVisibility(8);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        this.b = alphaAnimation;
        alphaAnimation.setDuration(500L);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    public void init(Object obj) {
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (eVar.c == 0) {
                this.d.setImageResource(R.drawable._2131429700_res_0x7f0b0944);
            } else {
                this.d.setImageResource(eVar.c);
            }
            this.f3286a.setText(eVar.e);
            this.f3286a.setAnimation(this.b);
            this.f3286a.startAnimation(this.b);
            if (eVar.j != null) {
                this.c.setVisibility(0);
                this.c.setText(eVar.j);
                if (eVar.b != null) {
                    this.c.setOnClickListener(eVar.b);
                }
                this.c.setAnimation(this.b);
                this.c.startAnimation(this.b);
            } else {
                this.c.setVisibility(8);
            }
            if (eVar.d != null) {
                this.e.setVisibility(0);
                this.e.setText(eVar.d);
                if (eVar.f3287a != null) {
                    this.e.setOnClickListener(eVar.f3287a);
                }
                this.e.setAnimation(this.b);
                this.e.startAnimation(this.b);
                return;
            }
            this.e.setVisibility(8);
        }
    }
}
