package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseDetailsViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.ffy;
import defpackage.fis;
import defpackage.ggm;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.UnitUtil;

/* loaded from: classes7.dex */
public class FitSeriesCourseDetailsViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3202a;
    private ImageView b;
    private HealthTextView c;
    private HealthTextView d;
    private ImageView e;
    private HealthTextView f;
    private float g;
    private HealthTextView j;

    public FitSeriesCourseDetailsViewHolder(View view) {
        super(view);
        this.e = (ImageView) view.findViewById(R.id.sug_img_item_pic);
        this.f = (HealthTextView) view.findViewById(R.id.tv_name);
        this.c = (HealthTextView) view.findViewById(R.id.tv_level);
        this.f3202a = (HealthTextView) view.findViewById(R.id.tv_time);
        this.d = (HealthTextView) view.findViewById(R.id.tv_calorie);
        this.j = (HealthTextView) view.findViewById(R.id.tv_train_number);
        this.b = (ImageView) view.findViewById(R.id.new_imageView);
        d();
    }

    public void e(final FitWorkout fitWorkout, final String str) {
        b(fitWorkout);
        a(fitWorkout);
        this.f.setText(TextUtils.isEmpty(fitWorkout.acquireName()) ? "" : fitWorkout.acquireName());
        e(fitWorkout);
        this.f3202a.setText(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
        SpannableString awT_ = ffy.awT_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc);
        if (nsn.s()) {
            this.j.setVisibility(8);
        } else {
            this.j.setVisibility(0);
            this.j.setText(awT_);
        }
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: fru
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitSeriesCourseDetailsViewHolder.aFn_(FitWorkout.this, str, view);
            }
        });
    }

    public static /* synthetic */ void aFn_(FitWorkout fitWorkout, String str, View view) {
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d("8");
        mmpVar.f(str);
        mod.b(BaseApplication.getContext(), fitWorkout, mmpVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(FitWorkout fitWorkout) {
        if (fitWorkout.isLongExplainVideoCourse()) {
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            if (this.f3202a.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f3202a.getLayoutParams();
                layoutParams.setMarginStart(0);
                this.f3202a.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        this.c.setText(ggm.d(fitWorkout.acquireDifficulty()));
        this.d.setText(ffy.d(arx.b(), R.string._2130837535_res_0x7f02001f, moe.i(moe.b(fitWorkout.acquireCalorie() * this.g))));
    }

    private void b(FitWorkout fitWorkout) {
        if (!TextUtils.isEmpty(fitWorkout.acquirePicture())) {
            nrf.cIS_(this.e, fitWorkout.acquirePicture(), nrf.d, 0, R.drawable._2131427609_res_0x7f0b0119);
        } else {
            nrf.cIM_(R.drawable._2131427609_res_0x7f0b0119, this.e, nrf.d);
        }
    }

    private void d() {
        this.g = fis.d().b();
    }

    private void a(FitWorkout fitWorkout) {
        if (this.b == null || fitWorkout == null) {
            return;
        }
        if (fitWorkout.getCornerImgDisplay() == 1) {
            String b = mod.b(fitWorkout.acquirePriceTagBeanList());
            if (!TextUtils.isEmpty(b)) {
                this.b.setVisibility(0);
                nrf.cIK_(b, this.b, 0.0f, nrf.d, 0.0f, 0.0f);
                return;
            }
        }
        this.b.setVisibility(8);
    }
}
