package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import defpackage.arx;
import defpackage.ffy;
import defpackage.gge;
import defpackage.ggm;
import defpackage.gnm;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nsj;
import defpackage.nsn;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class FitSeriesCourseViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private static final Rect f3203a = new Rect(16, 24, a.C, 75);
    private ImageView b;
    private ImageView c;
    private Bitmap d;
    private ImageView e;
    private HealthButton f;
    private RelativeLayout g;
    private RelativeLayout h;
    private Context i;
    private HealthTextView j;
    private HealthTextView k;
    private FrameLayout l;
    private RelativeLayout m;
    private HealthTextView n;
    private int o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;

    public FitSeriesCourseViewHolder(View view) {
        super(view);
        this.l = (FrameLayout) view.findViewById(R.id.series_course_layout);
        this.c = (ImageView) view.findViewById(R.id.sug_img_item_picture);
        this.f = (HealthButton) view.findViewById(R.id.bt_more);
        this.j = (HealthTextView) view.findViewById(R.id.tv_title);
        this.n = (HealthTextView) view.findViewById(R.id.tv_subtitle);
        this.b = (ImageView) view.findViewById(R.id.iv_background_img_01);
        this.r = (HealthTextView) view.findViewById(R.id.train_name_01);
        this.k = (HealthTextView) view.findViewById(R.id.tv_difficulty_01);
        this.t = (HealthTextView) view.findViewById(R.id.tv_train_time_01);
        this.e = (ImageView) view.findViewById(R.id.iv_background_img_02);
        this.q = (HealthTextView) view.findViewById(R.id.train_name_02);
        this.s = (HealthTextView) view.findViewById(R.id.tv_difficulty_02);
        this.p = (HealthTextView) view.findViewById(R.id.tv_train_time_02);
        this.m = (RelativeLayout) view.findViewById(R.id.title_rl);
        this.g = (RelativeLayout) view.findViewById(R.id.course_rl_01);
        this.h = (RelativeLayout) view.findViewById(R.id.course_rl_02);
        this.i = view.getContext();
    }

    public void e(final Topic topic) {
        a(topic);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.h("FitSeriesCourseViewHolder", "FitnessSeriesCourseViewFragmentHolder is fast click");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                Intent intent = new Intent(FitSeriesCourseViewHolder.this.i, (Class<?>) FitnessSeriesCourseDetailsActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("intent_key_topicid", topic.acquireId());
                intent.putExtra("intent_key_topicname", topic.acquireName());
                intent.putExtra("intent_key_description", topic.getDescription());
                gnm.aPB_(FitSeriesCourseViewHolder.this.i, intent);
                FitSeriesCourseViewHolder.this.c(topic.acquireId());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c(topic);
        if (koq.b(topic.acquireWorkoutList())) {
            LogUtil.h("FitSeriesCourseViewHolder", "topic.acquireWorkoutList() is null ");
            return;
        }
        if (topic.acquireWorkoutList().size() >= 2) {
            FitWorkout fitWorkout = topic.acquireWorkoutList().get(0);
            aFp_(this.b, this.r, this.k, this.t, fitWorkout);
            aFq_(fitWorkout, this.g, topic.acquireName());
            FitWorkout fitWorkout2 = topic.acquireWorkoutList().get(1);
            aFp_(this.e, this.q, this.s, this.p, fitWorkout2);
            aFq_(fitWorkout2, this.h, topic.acquireName());
        }
    }

    private void c(final Topic topic) {
        String topicBackImgUrl = topic.getTopicBackImgUrl();
        if (!TextUtils.isEmpty(topicBackImgUrl)) {
            nrf.cIk_(topicBackImgUrl, this.c, R.drawable._2131427609_res_0x7f0b0119, new AsyncLoadDrawableCallback() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseViewHolder.2
                @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
                public void getDrawable(Drawable drawable) {
                    FitSeriesCourseViewHolder.this.aFr_(topic, drawable);
                }
            });
            return;
        }
        nrf.cIM_(R.drawable._2131427609_res_0x7f0b0119, this.c, nrf.d);
        this.j.setTextColor(this.itemView.getResources().getColor(R$color.textColorPrimary));
        this.n.setTextColor(this.itemView.getResources().getColor(R$color.textColorSecondary));
        this.j.setText(TextUtils.isEmpty(topic.acquireName()) ? "" : topic.acquireName());
        this.n.setText(TextUtils.isEmpty(topic.getSubtitle()) ? "" : topic.getSubtitle());
    }

    private void aFp_(ImageView imageView, HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3, FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h("FitSeriesCourseViewHolder", "setSeriesCourseDate(),fitWorkout is null");
            return;
        }
        healthTextView.setText(TextUtils.isEmpty(fitWorkout.acquireName()) ? "" : fitWorkout.acquireName());
        String topicPreviewPicUrl = fitWorkout.getTopicPreviewPicUrl();
        if (!TextUtils.isEmpty(topicPreviewPicUrl)) {
            nrf.cIS_(imageView, topicPreviewPicUrl, nrf.e, 0, R.drawable._2131427609_res_0x7f0b0119);
        } else {
            nrf.cIM_(R.drawable._2131427609_res_0x7f0b0119, imageView, nrf.e);
        }
        healthTextView2.setText(ggm.d(fitWorkout.acquireDifficulty()));
        healthTextView3.setText(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
    }

    private void aFq_(final FitWorkout fitWorkout, RelativeLayout relativeLayout, final String str) {
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitSeriesCourseViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mmp mmpVar = new mmp(fitWorkout.acquireId());
                mmpVar.d("8");
                mmpVar.f(str);
                mod.b(FitSeriesCourseViewHolder.this.i, fitWorkout, mmpVar);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(Topic topic) {
        if (topic != null) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("date", nsj.d());
        hashMap.put("topicId", Integer.valueOf(i));
        gge.e("1130032", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aFr_(final Topic topic, final Drawable drawable) {
        HandlerExecutor.e(new Runnable() { // from class: frw
            @Override // java.lang.Runnable
            public final void run() {
                FitSeriesCourseViewHolder.this.aFs_(drawable, topic);
            }
        });
    }

    public /* synthetic */ void aFs_(Drawable drawable, Topic topic) {
        Bitmap cHF_ = nrf.cHF_(drawable);
        this.d = cHF_;
        this.o = nrf.a(nrf.cJl_(cHF_, f3203a));
        int i = R$color.textColorPrimary;
        int i2 = R$color.textColorSecondary;
        if (this.o == -16777216) {
            i = R$color.textColorPrimaryInverse;
            i2 = R$color.textColorPrimaryInverse;
        }
        this.j.setTextColor(this.itemView.getResources().getColor(i));
        this.j.setText(TextUtils.isEmpty(topic.acquireName()) ? "" : topic.acquireName());
        this.n.setTextColor(this.itemView.getResources().getColor(i2));
        this.n.setText(TextUtils.isEmpty(topic.getSubtitle()) ? "" : topic.getSubtitle());
    }
}
