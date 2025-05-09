package com.huawei.health.suggestion.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.adapter.SportAllCourseListAdapter;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.ffy;
import defpackage.fot;
import defpackage.gge;
import defpackage.ggm;
import defpackage.ggr;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.mwt;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.HarmonyBuild;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SportAllCourseListAdapter extends PolymericDataAdapter {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3070a;
    private ArrayList<String> b;
    private ArrayList<String> c;
    private ArrayList<String> d;
    private boolean e;
    private String h;
    private String i;
    private HealthCheckBox j;

    public SportAllCourseListAdapter(List<Workout> list, RecyclerView recyclerView, int i, boolean z, ArrayList<String> arrayList, String str, String str2, boolean z2) {
        super(list, recyclerView, i);
        this.c = new ArrayList<>(10);
        this.d = new ArrayList<>(10);
        this.e = z;
        this.b = arrayList;
        this.h = str;
        this.i = str2;
        this.f3070a = z2;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setRecycleHolder(RecyclerHolder recyclerHolder, final Workout workout, final int i) {
        if (workout == null || !(workout instanceof FitWorkout)) {
            LogUtil.h("Sug_SportAllCourseListAdapter", "convert, workout is null.");
            return;
        }
        LogUtil.a("Sug_SportAllCourseListAdapter", "convert, holder.itemView.getWidth() = ", Integer.valueOf(recyclerHolder.itemView.getWidth()));
        final FitWorkout fitWorkout = (FitWorkout) workout;
        if (fitWorkout.getCourseDefineType() == 1) {
            e(recyclerHolder, fitWorkout, i);
            return;
        }
        a(recyclerHolder, fitWorkout);
        final HealthCheckBox healthCheckBox = (HealthCheckBox) recyclerHolder.cwK_(R.id.plan_add_course_checkbox);
        healthCheckBox.setChecked(this.c.contains(fitWorkout.acquireId()));
        healthCheckBox.setClickable(false);
        if (this.e || this.f3070a) {
            healthCheckBox.setVisibility(0);
            if (healthCheckBox.isChecked() && this.f3070a) {
                this.j = healthCheckBox;
            }
            recyclerHolder.cwK_(R.id.recycle_item).setOnClickListener(new View.OnClickListener() { // from class: fjs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SportAllCourseListAdapter.this.ayL_(fitWorkout, healthCheckBox, view);
                }
            });
            return;
        }
        healthCheckBox.setVisibility(8);
        recyclerHolder.cwK_(R.id.recycle_item).setOnClickListener(new View.OnClickListener() { // from class: fjv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportAllCourseListAdapter.this.ayM_(workout, i, view);
            }
        });
    }

    public /* synthetic */ void ayL_(FitWorkout fitWorkout, HealthCheckBox healthCheckBox, View view) {
        b(fitWorkout, healthCheckBox);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void ayM_(Workout workout, int i, View view) {
        onItemClick(workout, i);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setCardBackground(ImageView imageView, Workout workout, int i) {
        if (!(workout instanceof FitWorkout)) {
            LogUtil.b("Sug_SportAllCourseListAdapter", "workout type is not FitWorkout.");
            return;
        }
        FitWorkout fitWorkout = (FitWorkout) workout;
        String str = imageView.getTag() instanceof String ? (String) imageView.getTag() : null;
        Map<String, Object> a2 = ggr.a(fitWorkout, String.valueOf(this.mNavigationId), this.h, i + 1);
        if (fitWorkout.getCourseDefineType() == 1) {
            int c = c(CommonUtils.g(fitWorkout.acquireId()));
            if (b(str, Integer.toString(c))) {
                imageView.setTag(Integer.toString(c));
                nrf.cIO_(ContextCompat.getDrawable(this.mContext, c), imageView, nrf.d);
                ggr.c(a2, "1130068");
                return;
            }
            return;
        }
        if (b(str, fitWorkout.acquirePicture())) {
            imageView.setTag(fitWorkout.acquirePicture());
            nrf.cHI_(fitWorkout.acquirePicture(), imageView, nrf.d);
            ggr.c(a2, "1130068");
        }
    }

    private boolean b(String str, String str2) {
        return TextUtils.isEmpty(str) || !str.equals(str2);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void setNewPic(ImageView imageView, Workout workout) {
        if (!(workout instanceof FitWorkout)) {
            LogUtil.b("Sug_SportAllCourseListAdapter", "workout type is not FitWorkout.");
            return;
        }
        FitWorkout fitWorkout = (FitWorkout) workout;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = -2;
        ayJ_(imageView, fitWorkout);
        List<PriceTagBean> acquirePriceTagBeanList = fitWorkout.acquirePriceTagBeanList();
        if (fitWorkout.getCornerImgDisplay() == 1) {
            String b = mod.b(acquirePriceTagBeanList);
            if (!TextUtils.isEmpty(b)) {
                layoutParams.height = (int) imageView.getResources().getDimension(R.dimen._2131362959_res_0x7f0a048f);
                imageView.setVisibility(0);
                nrf.cIK_(b, imageView, 0.0f, nrf.d, 0.0f, 0.0f);
            }
        }
        imageView.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericDataAdapter
    public void onItemClick(Workout workout, int i) {
        if (nsn.o() || !(workout instanceof FitWorkout)) {
            LogUtil.h("Sug_SportAllCourseListAdapter", "mItemView click too fast.");
            return;
        }
        FitWorkout fitWorkout = (FitWorkout) workout;
        int i2 = this.mNavigationId;
        int i3 = i + 1;
        ggr.e(ggr.a(fitWorkout, String.valueOf(i2), this.h, i3));
        mmp mmpVar = new mmp(workout.acquireId());
        mmpVar.g(fitWorkout.getCourseDefineType());
        mmpVar.d("0");
        mmpVar.j(String.valueOf(gge.b(this.h)));
        mmpVar.o(String.valueOf(this.mNavigationId));
        mmpVar.l(String.valueOf(i3));
        mod.b(arx.b(), fitWorkout, mmpVar);
    }

    public ArrayList<String> d() {
        return this.c;
    }

    public ArrayList<String> a() {
        return this.d;
    }

    private void ayJ_(ImageView imageView, FitWorkout fitWorkout) {
        if (fitWorkout.getCourseDefineType() == 1) {
            imageView.setVisibility(8);
            return;
        }
        if (!LanguageUtil.j(this.mContext) && !LanguageUtil.p(this.mContext)) {
            imageView.setVisibility(8);
            return;
        }
        if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
            imageView.setImageResource(R.drawable.pic_corner_new_watchwear);
            imageView.setVisibility(0);
        } else if (fitWorkout.acquireStage() == 0) {
            imageView.setImageResource(R.drawable._2131430906_res_0x7f0b0dfa);
            imageView.setVisibility(0);
        } else if (fitWorkout.acquireIsSupportDevice() == 0) {
            imageView.setImageResource(R.drawable.pic_corner_watchwear);
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    private void a(RecyclerHolder recyclerHolder, FitWorkout fitWorkout) {
        recyclerHolder.a(R.id.tv_custom, 8);
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_level);
        HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_parameter_num);
        HealthTextView healthTextView3 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_Kcal);
        healthTextView2.setVisibility(0);
        healthTextView2.setText(ffy.d(healthTextView2.getContext(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
        if (fitWorkout.isLongExplainVideoCourse()) {
            setLevelAndParameterNumAndCalorieState(healthTextView, healthTextView3, healthTextView2, 8, 0);
        } else {
            healthTextView.setText(ggm.d(fitWorkout.acquireDifficulty()));
            float b = moe.b(fitWorkout.acquireCalorie() * this.mWeight);
            healthTextView3.setText(ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) b, moe.i(b)));
            setLevelAndParameterNumAndCalorieState(healthTextView, healthTextView3, healthTextView2, 0, healthTextView2.getContext().getResources().getDimensionPixelSize(R.dimen._2131363575_res_0x7f0a06f7));
        }
        HealthTextView healthTextView4 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name);
        healthTextView4.setTextColor(getTextColor(R.color._2131299238_res_0x7f090ba6));
        healthTextView4.setTypeface(nsk.cKP_());
        a(fitWorkout, recyclerHolder);
        TextView textView = (TextView) recyclerHolder.cwK_(R.id.tv_plan_peoples_num);
        textView.setTextColor(getTextColor(R.color._2131299238_res_0x7f090ba6));
        textView.setTextSize(getTextSize(R.dimen._2131365068_res_0x7f0a0ccc));
        textView.setText(ffy.awT_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc));
    }

    private void a(FitWorkout fitWorkout, RecyclerHolder recyclerHolder) {
        int modelType = mwt.d().getModelType();
        ReleaseLogUtil.e("Sug_SportAllCourseListAdapter", "getIsAi:", Integer.valueOf(fitWorkout.getIsAi()), "modelType: ", Integer.valueOf(modelType), "harmony：", Boolean.valueOf(HarmonyBuild.d));
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.course_tag);
        if (fitWorkout.getIsAi() == 1 && modelType != -1) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    private void e(RecyclerHolder recyclerHolder, final FitWorkout fitWorkout, final int i) {
        recyclerHolder.a(R.id.tv_custom, 0);
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_level);
        HealthTextView healthTextView2 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_Kcal);
        HealthTextView healthTextView3 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_parameter_num);
        healthTextView3.setVisibility(8);
        setLevelAndParameterNumAndCalorieState(healthTextView, healthTextView2, healthTextView3, 8, 0);
        HealthTextView healthTextView4 = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name);
        healthTextView4.setTextColor(getTextColor(R.color._2131299236_res_0x7f090ba4));
        healthTextView4.setTypeface(nsk.cKO_());
        TextView textView = (TextView) recyclerHolder.cwK_(R.id.tv_plan_peoples_num);
        textView.setVisibility(0);
        textView.setTextColor(getTextColor(R.color._2131299244_res_0x7f090bac));
        textView.setTextSize(getTextSize(!nsn.e(1.3f) ? R.dimen._2131365063_res_0x7f0a0cc7 : R.dimen._2131365067_res_0x7f0a0ccb));
        textView.setText(fot.b(fitWorkout.getPublishDate()));
        if (nsn.t()) {
            textView.setVisibility(8);
        }
        recyclerHolder.cwK_(R.id.recycle_item).setOnClickListener(new View.OnClickListener() { // from class: fjt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportAllCourseListAdapter.this.ayK_(fitWorkout, i, view);
            }
        });
    }

    public /* synthetic */ void ayK_(FitWorkout fitWorkout, int i, View view) {
        onItemClick(fitWorkout, i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private int c(long j) {
        long j2 = j % 3;
        return j2 == 0 ? R.drawable.pic_custom_yellow : j2 == 1 ? R.drawable.pic_custom_red : R.drawable.pic_custom_blue;
    }

    private void b(FitWorkout fitWorkout, HealthCheckBox healthCheckBox) {
        if (this.e) {
            e(fitWorkout, healthCheckBox);
        } else if (this.f3070a) {
            c(fitWorkout, healthCheckBox);
        }
    }

    private void c(FitWorkout fitWorkout, HealthCheckBox healthCheckBox) {
        LogUtil.a("Sug_SportAllCourseListAdapter", "replace workout ", fitWorkout.acquireId());
        if (healthCheckBox.isChecked()) {
            this.c.clear();
            this.d.clear();
            healthCheckBox.setChecked(false);
            this.j = null;
            c();
            return;
        }
        if (this.j != null) {
            this.c.clear();
            this.d.clear();
            this.j.setChecked(false);
        }
        this.c.add(fitWorkout.acquireId());
        this.d.add(fitWorkout.acquireName());
        healthCheckBox.setChecked(true);
        this.j = healthCheckBox;
        c();
    }

    private void e(FitWorkout fitWorkout, HealthCheckBox healthCheckBox) {
        LogUtil.a("Sug_SportAllCourseListAdapter", "set workout ", fitWorkout.acquireId(), " ", Boolean.valueOf(healthCheckBox.isChecked()));
        if (healthCheckBox.isChecked()) {
            if (this.c.contains(fitWorkout.acquireId())) {
                this.c.remove(fitWorkout.acquireId());
                this.d.remove(fitWorkout.acquireName());
            }
            healthCheckBox.setChecked(false);
            c();
            return;
        }
        if (!this.c.contains(fitWorkout.acquireId())) {
            ArrayList<String> arrayList = this.b;
            if (arrayList != null && arrayList.contains(fitWorkout.acquireId())) {
                nrh.b(this.mContext, R.string._2130848701_res_0x7f022bbd);
                return;
            } else {
                this.c.add(fitWorkout.acquireId());
                this.d.add(fitWorkout.acquireName());
            }
        }
        healthCheckBox.setChecked(true);
        c();
    }

    private void c() {
        if (koq.c(this.c)) {
            ObserverManagerUtil.c("PLAN_CONFIRM_VISIBILITY", true);
        } else {
            ObserverManagerUtil.c("PLAN_CONFIRM_VISIBILITY", false);
        }
    }
}
