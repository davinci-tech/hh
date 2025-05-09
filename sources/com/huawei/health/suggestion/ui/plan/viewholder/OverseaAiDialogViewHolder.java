package com.huawei.health.suggestion.ui.plan.viewholder;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class OverseaAiDialogViewHolder extends AbsFitnessViewHolder<Object> {

    /* renamed from: a, reason: collision with root package name */
    private final HealthTextView f3294a;
    private final Animation c;
    private final HealthTextView d;

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public String f3295a;
        public View.OnClickListener b;
        public String e;
    }

    public OverseaAiDialogViewHolder(View view) {
        super(view);
        this.f3294a = (HealthTextView) view.findViewById(R.id.oversea_guide_text);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.oversea_guide_ok);
        this.d = healthTextView;
        healthTextView.setVisibility(8);
        healthTextView.setAllCaps(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        this.c = alphaAnimation;
        alphaAnimation.setDuration(500L);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    public void init(Object obj) {
        if (obj instanceof e) {
            e eVar = (e) obj;
            this.f3294a.setText(eVar.f3295a);
            this.f3294a.setAnimation(this.c);
            this.f3294a.startAnimation(this.c);
            if (eVar.e != null) {
                this.d.setVisibility(0);
                this.d.setText(eVar.e);
                if (eVar.b != null) {
                    this.d.setOnClickListener(eVar.b);
                }
                this.d.setAnimation(this.c);
                this.d.startAnimation(this.c);
                return;
            }
            this.d.setVisibility(8);
        }
    }
}
