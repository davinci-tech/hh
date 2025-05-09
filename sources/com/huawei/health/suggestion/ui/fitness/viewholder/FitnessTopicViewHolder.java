package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.ActivityNotFoundException;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.ffy;
import defpackage.fis;
import defpackage.ggm;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.List;

/* loaded from: classes7.dex */
public class FitnessTopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private FitnessTopicDeleteModel f3220a;
    private HealthTextView b;
    private ImageView c;
    private ImageView d;
    private HealthCheckBox e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private View j;
    private float m;

    public FitnessTopicViewHolder(View view) {
        super(view);
        this.i = (HealthTextView) view.findViewById(R.id.tv_plan_peoples_num);
        this.h = (HealthTextView) view.findViewById(R.id.tv_fe_name);
        this.b = (HealthTextView) view.findViewById(R.id.tv_Kcal);
        this.g = (HealthTextView) view.findViewById(R.id.tv_parameter_num);
        this.f = (HealthTextView) view.findViewById(R.id.tv_level);
        this.c = (ImageView) view.findViewById(R.id.sug_img_item_pic);
        this.e = (HealthCheckBox) view.findViewById(R.id.sug_rv_checkbox);
        this.d = (ImageView) view.findViewById(R.id.new_imageView);
        this.j = view.findViewById(R.id.sug_view_space);
        b();
    }

    public void b(int i) {
        this.j.setVisibility(i);
    }

    public void e(FitnessTopicDeleteModel fitnessTopicDeleteModel, String str, boolean z, FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.b("Suggestion_FitnessTopicViewHolder", "setDataAndRefresh fitWorkout == null");
            return;
        }
        if (!Utils.o()) {
            this.h.setMaxLines(1);
            this.h.setSingleLine(true);
            this.h.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (Utils.j()) {
            this.i.setText(ffy.awT_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc));
        } else {
            this.i.setVisibility(8);
        }
        if (z && fitnessTopicDeleteModel != null) {
            this.f3220a = fitnessTopicDeleteModel;
            d();
        }
        b(fitWorkout);
        b(fitWorkout, str);
    }

    private void b(final FitWorkout fitWorkout, final String str) {
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessTopicViewHolder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FitnessTopicViewHolder.this.f3220a != null && FitnessTopicViewHolder.this.f3220a.issDeleteMode()) {
                    FitnessTopicViewHolder.this.e.performClick();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("Suggestion_FitnessTopicViewHolder", "method:onclick (View view) ---fitWorkout.acquireId():", fitWorkout.acquireId(), "--fitWorkout.accquireVersion():", fitWorkout.accquireVersion());
                if (FitnessTopicViewHolder.this.itemView.getParent() instanceof RecyclerView) {
                    try {
                        mmp mmpVar = new mmp(fitWorkout.acquireId());
                        mmpVar.d(str);
                        mod.b(((RecyclerView) FitnessTopicViewHolder.this.itemView.getParent()).getContext(), fitWorkout, mmpVar);
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("Suggestion_FitnessTopicViewHolder", "setDataAndRefresh ActivityNotFoundException.");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(FitWorkout fitWorkout) {
        String d = ffy.d(arx.b(), R.string._2130837535_res_0x7f02001f, moe.i(moe.b(fitWorkout.acquireCalorie() * this.m)));
        this.h.setText(fitWorkout.acquireName());
        if (fitWorkout.isLongExplainVideoCourse()) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
        this.b.setText(d);
        this.f.setText(ggm.d(fitWorkout.acquireDifficulty()));
        this.g.setText(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
        nrf.cIS_(this.c, fitWorkout.acquirePicture(), nrf.d, 0, R.drawable._2131431605_res_0x7f0b10b5);
        if (LanguageUtil.j(this.itemView.getContext()) || LanguageUtil.p(this.itemView.getContext())) {
            if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
                this.d.setImageResource(R.drawable.pic_corner_new_watchwear);
                this.d.setVisibility(0);
            } else if (fitWorkout.acquireStage() == 0) {
                this.d.setImageResource(R.drawable._2131430906_res_0x7f0b0dfa);
                this.d.setVisibility(0);
            } else if (fitWorkout.acquireIsSupportDevice() == 0) {
                this.d.setImageResource(R.drawable.pic_corner_watchwear);
                this.d.setVisibility(0);
            } else {
                this.d.setVisibility(8);
            }
        } else {
            this.d.setVisibility(8);
        }
        List<PriceTagBean> acquirePriceTagBeanList = fitWorkout.acquirePriceTagBeanList();
        if (StringUtils.i(mod.b(acquirePriceTagBeanList))) {
            this.d.setVisibility(0);
            nrf.cHI_(mod.b(acquirePriceTagBeanList), this.d, nrf.d);
        }
    }

    private void d() {
        LogUtil.a("Suggestion_FitnessTopicViewHolder", "mDeleteModel.issDeleteMode():", Boolean.valueOf(this.f3220a.issDeleteMode()), ":mDeleteModel.acquirePosition():", Integer.valueOf(this.f3220a.acquirePosition()));
        if (this.f3220a.issDeleteMode()) {
            this.e.setVisibility(0);
            if (this.f3220a.acquireSelects().contains(Integer.valueOf(this.f3220a.acquirePosition()))) {
                this.e.setChecked(true);
            } else {
                this.e.setChecked(false);
            }
        } else {
            this.e.setVisibility(8);
        }
        this.e.setTag(Integer.valueOf(this.f3220a.acquirePosition()));
        this.e.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.sug_rv_checkbox == view.getId()) {
            Integer num = (Integer) view.getTag();
            boolean isChecked = this.e.isChecked();
            LogUtil.a("Suggestion_FitnessTopicViewHolder", "before mDeleteModel.acquireSelects():", this.f3220a.acquireSelects(), "--isChecked:", Boolean.valueOf(isChecked));
            if (isChecked) {
                this.f3220a.acquireSelects().add(num);
            } else if (this.f3220a.acquireSelects().contains(num)) {
                this.f3220a.acquireSelects().remove(num);
            }
            LogUtil.a("Suggestion_FitnessTopicViewHolder", "after mDeleteModel.acquireSelects():", this.f3220a.acquireSelects());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        this.m = fis.d().b();
    }
}
