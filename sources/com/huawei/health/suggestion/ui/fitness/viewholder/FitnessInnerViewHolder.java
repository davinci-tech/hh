package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.gge;
import defpackage.ggm;
import defpackage.ggs;
import defpackage.gic;
import defpackage.mmp;
import defpackage.mod;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class FitnessInnerViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3210a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private ImageView e;
    private HealthTextView f;
    private float g;
    private HealthTextView i;
    private ImageView j;

    public FitnessInnerViewHolder(View view) {
        super(view);
        this.e = (ImageView) view.findViewById(R.id.sug_img_item_pic);
        this.b = (HealthTextView) view.findViewById(R.id.tv_name);
        this.f3210a = (HealthTextView) view.findViewById(R.id.tv_level);
        this.d = (HealthTextView) view.findViewById(R.id.tv_duration);
        this.c = (HealthTextView) view.findViewById(R.id.tv_calorie);
        this.f = (HealthTextView) view.findViewById(R.id.tv_train_number);
        this.j = (ImageView) view.findViewById(R.id.new_imageView);
        this.i = (HealthTextView) view.findViewById(R.id.sug_sub_title_htv);
        c();
    }

    public void b(FitWorkout fitWorkout, boolean z, boolean z2) {
        c(fitWorkout, z, z2, null);
    }

    public void c(final FitWorkout fitWorkout, boolean z, boolean z2, final Topic topic) {
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_FitnessInnerViewHolder", "setDataAndRefresh, fitWorkout == null");
            return;
        }
        this.itemView.setLayoutParams(e(z, z2, topic));
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessInnerViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_FitnessInnerViewHolder", "method:onclick (View view) ---fitWorkout.acquireId():", fitWorkout.acquireId(), "--fitWorkout.accquireVersion():", fitWorkout.accquireVersion());
                mmp mmpVar = new mmp(fitWorkout.acquireId());
                mmpVar.d("5");
                mod.b(arx.b(), fitWorkout, mmpVar);
                Topic topic2 = topic;
                if (topic2 != null) {
                    FitnessInnerViewHolder.this.b(topic2);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        a(fitWorkout);
    }

    private RecyclerView.LayoutParams e(boolean z, boolean z2, Topic topic) {
        ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.h("Suggestion_FitnessInnerViewHolder", "!(layoutParams instanceof RecyclerView.LayoutParams)");
            return null;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (z && z2) {
            layoutParams2.setMarginStart(this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e));
            if (topic != null) {
                layoutParams2.setMarginEnd(this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d));
            }
        } else if (z) {
            layoutParams2.setMarginStart(this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e));
        } else if (topic != null) {
            layoutParams2.setMarginStart(nrr.b(BaseApplication.getContext()));
            if (z2) {
                layoutParams2.setMarginEnd(this.itemView.getContext().getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d));
            }
        } else {
            layoutParams2.setMarginStart(0);
        }
        return layoutParams2;
    }

    private void a(FitWorkout fitWorkout) {
        if (LanguageUtil.j(this.itemView.getContext()) || LanguageUtil.p(this.itemView.getContext())) {
            if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
                this.j.setImageResource(R.drawable.pic_corner_new_watchwear);
                this.j.setVisibility(0);
            } else if (fitWorkout.acquireStage() == 0) {
                this.j.setImageResource(R.drawable._2131430906_res_0x7f0b0dfa);
                this.j.setVisibility(0);
            } else if (fitWorkout.acquireIsSupportDevice() == 0) {
                this.j.setImageResource(R.drawable.pic_corner_watchwear);
                this.j.setVisibility(0);
            } else {
                this.j.setVisibility(8);
            }
        } else {
            this.j.setVisibility(8);
        }
        if (!TextUtils.isEmpty(fitWorkout.acquirePicture())) {
            nrf.cIS_(this.e, fitWorkout.acquirePicture(), nrf.d, 0, R.drawable._2131427609_res_0x7f0b0119);
        } else {
            nrf.cIM_(R.drawable._2131427609_res_0x7f0b0119, this.e, nrf.d);
        }
        if (Utils.j()) {
            this.f.setText(UnitUtil.bCR_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", gic.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc));
        } else {
            this.f.setVisibility(8);
        }
        String d = gic.d(arx.b(), R.string._2130837534_res_0x7f02001e, gic.e(fitWorkout.acquireDuration()));
        String d2 = gic.d(arx.b(), R.string._2130837535_res_0x7f02001f, gic.a(gic.d(fitWorkout.acquireCalorie() * this.g)));
        this.b.setText(fitWorkout.acquireName());
        this.f3210a.setText(ggm.d(fitWorkout.acquireDifficulty()));
        this.d.setText(d);
        this.c.setText(d2);
        if (fitWorkout.isLongExplainVideoCourse()) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
        }
    }

    private void c() {
        this.g = ggs.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Topic topic) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", topic.acquireName());
        hashMap.put("position", 0);
        gge.e("1130015", hashMap);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            this.i.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
        this.i.setText(str);
        if (nsn.e(1.3f)) {
            LogUtil.a("Suggestion_FitnessInnerViewHolder", "bindSubTitleData,  isLargeFontScaleMode");
            if (this.b.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
                layoutParams.topMargin = this.b.getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
                this.b.setLayoutParams(layoutParams);
            }
            if (this.f.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
                layoutParams2.bottomMargin = this.f.getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
                this.f.setLayoutParams(layoutParams2);
            }
        }
    }
}
