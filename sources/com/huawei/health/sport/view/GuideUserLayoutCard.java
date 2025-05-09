package com.huawei.health.sport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.sport.view.GuideUserLayoutCard;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.ffc;
import health.compact.a.LogUtil;

/* loaded from: classes4.dex */
public class GuideUserLayoutCard extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3002a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthImageView e;

    public void axg_(View view) {
    }

    public void b() {
    }

    public void e() {
    }

    public GuideUserLayoutCard(Context context) {
        super(context);
        e(context);
    }

    public GuideUserLayoutCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e(context);
    }

    public GuideUserLayoutCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e(context);
    }

    private void e(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.guide_card_dialog, this);
        HealthImageView healthImageView = (HealthImageView) findViewById(R.id.exit_label);
        ((LinearLayout) findViewById(R.id.detail_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.sport.view.GuideUserLayoutCard.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("DietDiaryEnergyCard", "goToDietDiaryFastRecord");
                GuideUserLayoutCard.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        healthImageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.sport.view.GuideUserLayoutCard.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("DietDiaryEnergyCard", "customViewDialog dismiss");
                GuideUserLayoutCard.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d = (HealthTextView) findViewById(R.id.guide_title_text);
        this.b = (HealthTextView) findViewById(R.id.main_description_text);
        this.f3002a = (HealthTextView) findViewById(R.id.detail_describe_text);
        this.e = (HealthImageView) findViewById(R.id.image_label);
        this.c = (HealthTextView) findViewById(R.id.guide_text);
        axg_(inflate);
    }

    public void e(final ffc ffcVar) {
        HandlerExecutor.e(new Runnable() { // from class: fgi
            @Override // java.lang.Runnable
            public final void run() {
                GuideUserLayoutCard.this.c(ffcVar);
            }
        });
    }

    public /* synthetic */ void c(ffc ffcVar) {
        LogUtil.c("DietDiaryEnergyCard", "mDietDiaryEnergyCard is show");
        setVisibility(0);
        setMainDescription(ffcVar.e());
        setTitle(ffcVar.d());
        setDetailDescription(ffcVar.a());
        setGuide(ffcVar.b());
        setImageView(ffcVar.c());
    }

    private void setMainDescription(String str) {
        this.b.setText(str);
    }

    private void setTitle(String str) {
        this.d.setText(str);
    }

    private void setDetailDescription(String str) {
        this.f3002a.setText(str);
    }

    private void setGuide(String str) {
        this.c.setText(str);
    }

    private void setImageView(int i) {
        this.e.setBackground(ContextCompat.getDrawable(BaseApplication.e(), i));
    }
}
