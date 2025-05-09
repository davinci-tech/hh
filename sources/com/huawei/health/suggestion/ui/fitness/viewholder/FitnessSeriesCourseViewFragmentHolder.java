package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessSeriesCourseViewAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.gge;
import defpackage.koq;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessSeriesCourseViewFragmentHolder extends AbsFitnessViewHolder<List<Topic>> implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private FitnessSeriesCourseViewAdapter f3217a;
    private HealthSubHeader c;
    private HealthRecycleView d;
    private LinearLayout e;

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void init(List<Topic> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessSeriesCourseViewFragmentHolder", "init view holder topic data is null");
            return;
        }
        e(list);
        FitnessSeriesCourseViewAdapter fitnessSeriesCourseViewAdapter = new FitnessSeriesCourseViewAdapter(list);
        this.f3217a = fitnessSeriesCourseViewAdapter;
        this.d.setAdapter(fitnessSeriesCourseViewAdapter);
        this.d.setHasFixedSize(true);
        this.d.setNestedScrollingEnabled(false);
        setRecyclerViewLayout(this.itemView.getContext(), this.d);
        b(list);
        this.c.setMoreViewClickListener(this);
    }

    private void b(List<Topic> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessSeriesCourseViewFragmentHolder", "setDataAndRefresh(),topics is empty");
        } else {
            this.f3217a.c(list);
        }
    }

    private void e(List<Topic> list) {
        if (koq.c(list)) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
            LogUtil.h("Suggestion_FitnessSeriesCourseViewFragmentHolder", "setGroupVisibility(),topics is empty");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (isFastClick()) {
            LogUtil.h("Suggestion_FitnessSeriesCourseViewFragmentHolder", "FitnessSeriesCourseViewFragmentHolder is fast click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        Intent intent = new Intent(view.getContext(), (Class<?>) FitnessSeriesCourseActivity.class);
        intent.addFlags(268435456);
        try {
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Suggestion_FitnessSeriesCourseViewFragmentHolder", "onClick ActivityNotFoundException.");
        }
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("entrance", this.itemView.getContext().getString(R.string._2130848533_res_0x7f022b15));
        hashMap.put("position", 1);
        gge.e("1130015", hashMap);
    }
}
