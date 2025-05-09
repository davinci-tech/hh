package com.huawei.health.sport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.sport.model.CardGuideViewModel;
import com.huawei.health.sport.view.CalorieConsumptionGuideCard;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.ffc;
import defpackage.grz;
import defpackage.nsf;
import health.compact.a.LogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class CalorieConsumptionGuideCard extends GuideUserLayoutCard {

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f2998a;
    private HealthImageView b;
    private CustomAlertDialog c;
    private HealthImageView d;
    private CardGuideViewModel e;

    public CalorieConsumptionGuideCard(Context context) {
        super(context);
    }

    public CalorieConsumptionGuideCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CalorieConsumptionGuideCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void e(LifecycleOwner lifecycleOwner, CardGuideViewModel cardGuideViewModel) {
        if (lifecycleOwner == null || cardGuideViewModel == null) {
            LogUtil.a("CalorieConsumptionGuideCard", "lifecycleOwner or calorieConsumptionViewModel is null");
            return;
        }
        this.e = cardGuideViewModel;
        cardGuideViewModel.b();
        if (this.e.d() == null) {
            LogUtil.a("CalorieConsumptionGuideCard", "getCalorieConsumptionData is null");
        } else {
            this.e.d().observe(lifecycleOwner, new Observer<CardGuideViewModel.b>() { // from class: com.huawei.health.sport.view.CalorieConsumptionGuideCard.1
                @Override // androidx.lifecycle.Observer
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onChanged(CardGuideViewModel.b bVar) {
                    if (CalorieConsumptionGuideCard.this.getVisibility() != 0) {
                        LogUtil.c("CalorieConsumptionGuideCard", "CalorieConsumptionGuideCard visibility is changed");
                        CalorieConsumptionGuideCard.this.e.c(0);
                    }
                    LogUtil.c("CalorieConsumptionGuideCard", "CalorieConsumptionViewModel data is changed");
                    CalorieConsumptionGuideCard calorieConsumptionGuideCard = CalorieConsumptionGuideCard.this;
                    calorieConsumptionGuideCard.e(calorieConsumptionGuideCard.a(bVar));
                    CalorieConsumptionGuideCard.this.setCheckInImageView(bVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ffc a(CardGuideViewModel.b bVar) {
        ffc ffcVar = new ffc();
        ffcVar.e(nsf.b(R$string.IDS_hwh_sport_calorie_consumption_this, nsf.a(R.plurals._2130903380_res_0x7f030154, (int) bVar.a(), UnitUtil.e(bVar.a(), 1, 0))));
        ffcVar.c(BaseApplication.e().getResources().getString(R$string.IDS_hwh_calorie_consumption));
        ffcVar.b(BaseApplication.e().getResources().getString(R$string.IDS_hwh_sport_calorie_record_tips));
        ffcVar.a(BaseApplication.e().getResources().getString(R$string.IDS_hwh_go_record_diet));
        ffcVar.e(R.drawable._2131431640_res_0x7f0b10d8);
        return ffcVar;
    }

    @Override // com.huawei.health.sport.view.GuideUserLayoutCard
    public void e() {
        this.e.c(1);
        grz.f("CalorieConsumptionGuideCard");
        CustomAlertDialog customAlertDialog = this.c;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
    }

    @Override // com.huawei.health.sport.view.GuideUserLayoutCard
    public void b() {
        CustomAlertDialog customAlertDialog = this.c;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
    }

    @Override // com.huawei.health.sport.view.GuideUserLayoutCard
    public void axg_(View view) {
        this.d = (HealthImageView) view.findViewById(R.id.image_start);
        this.b = (HealthImageView) view.findViewById(R.id.image_middle);
        this.f2998a = (HealthImageView) view.findViewById(R.id.image_end);
    }

    public void setCustomViewDialog(CustomAlertDialog customAlertDialog) {
        this.c = customAlertDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckInImageView(final CardGuideViewModel.b bVar) {
        HandlerExecutor.e(new Runnable() { // from class: fgh
            @Override // java.lang.Runnable
            public final void run() {
                CalorieConsumptionGuideCard.this.b(bVar);
            }
        });
    }

    public /* synthetic */ void b(CardGuideViewModel.b bVar) {
        LogUtil.c("CalorieConsumptionGuideCard", "setCheckInImageView");
        axa_(this.d, bVar.c());
        axa_(this.b, bVar.b());
        axa_(this.f2998a, bVar.d());
    }

    private void axa_(ImageView imageView, int i) {
        if (i == 1) {
            imageView.setImageResource(R.drawable._2131431695_res_0x7f0b110f);
            return;
        }
        if (i == 2) {
            imageView.setImageResource(R.drawable._2131431697_res_0x7f0b1111);
        } else if (i == 3) {
            imageView.setImageResource(R.drawable._2131431696_res_0x7f0b1110);
        } else {
            LogUtil.a("CalorieConsumptionGuideCard", "setCheckInMealView pictureType = ", Integer.valueOf(i));
        }
    }
}
