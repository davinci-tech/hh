package com.huawei.health.sport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.sport.model.CalorieConsumptionViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.grz;
import health.compact.a.LogUtil;

/* loaded from: classes8.dex */
public class CalorieConsumptionCard extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2996a;
    private CustomAlertDialog b;
    private ImageView c;
    private ImageView d;
    private HealthTextView e;
    private ImageView g;
    private LinearLayout h;
    private View j;

    public CalorieConsumptionCard(Context context) {
        super(context);
        c(context);
    }

    public CalorieConsumptionCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    public CalorieConsumptionCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c(context);
    }

    private void c(Context context) {
        this.j = LayoutInflater.from(context).inflate(R.layout.sug_fitness_calorie_consumption_dialog, this);
        this.d = (ImageView) findViewById(R.id.message_morning);
        this.g = (ImageView) findViewById(R.id.message_afternoon);
        this.f2996a = (ImageView) findViewById(R.id.message_evening);
        this.c = (ImageView) findViewById(R.id.diet_exit);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.calorie_consumption);
        this.h = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.sport.view.CalorieConsumptionCard.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("DietDiaryEnergyCard", "goToDietDiaryFastRecord");
                CalorieConsumptionCard.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.sport.view.CalorieConsumptionCard.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("DietDiaryEnergyCard", "customViewDialog dismiss");
                if (CalorieConsumptionCard.this.b != null) {
                    CalorieConsumptionCard.this.b.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.e = (HealthTextView) findViewById(R.id.current_exercise_consumption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        grz.f("DietDiaryEnergyCard");
        CustomAlertDialog customAlertDialog = this.b;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
    }

    public void setCustomViewDialog(CustomAlertDialog customAlertDialog) {
        this.b = customAlertDialog;
    }

    private void setCalorieConsumption(String str) {
        this.e.setText(str);
    }

    private void setCheckInImageView(CalorieConsumptionViewModel.c cVar) {
        awZ_(this.d, cVar.e());
        awZ_(this.g, cVar.a());
        awZ_(this.f2996a, cVar.b());
    }

    private void awZ_(ImageView imageView, int i) {
        if (i == 1) {
            imageView.setImageResource(R.drawable._2131431695_res_0x7f0b110f);
        } else if (i == 2) {
            imageView.setImageResource(R.drawable._2131431697_res_0x7f0b1111);
        } else {
            imageView.setImageResource(R.drawable._2131431696_res_0x7f0b1110);
        }
    }
}
