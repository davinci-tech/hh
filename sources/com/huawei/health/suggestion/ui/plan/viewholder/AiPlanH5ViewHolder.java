package com.huawei.health.suggestion.ui.plan.viewholder;

import android.os.SystemClock;
import android.view.View;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.section.view.CustomH5ProWebview;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bzs;

/* loaded from: classes4.dex */
public class AiPlanH5ViewHolder extends AbsFitnessViewHolder<IntPlan> {
    private CustomH5ProWebview e;

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void init(IntPlan intPlan) {
    }

    public AiPlanH5ViewHolder(View view) {
        super(view);
        LogUtil.a("Suggestion_AiPlanH5ViewHolder", "AiPlanH5ViewHolder create");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        CustomH5ProWebview customH5ProWebview = (CustomH5ProWebview) view.findViewById(R.id.int_plan_h5_web_view);
        this.e = customH5ProWebview;
        customH5ProWebview.setIsDisallowIntercept(true);
        PluginSuggestion.getInstance().setH5ProWebView(this.e);
        bzs.e().loadEmbeddedH5(this.e, "com.huawei.health.h5.ai-weight", new H5ProLaunchOption.Builder().addPath("#/main_page"));
        LogUtil.a("Suggestion_AiPlanH5ViewHolder", "AiPlanH5ViewHolder create time cost:", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    public void d(String str) {
        LogUtil.a("Suggestion_AiPlanH5ViewHolder", "refreshH5WebView mCustomH5ProWebView ", this.e, " sourceTag ", str);
        CustomH5ProWebview customH5ProWebview = this.e;
        if (customH5ProWebview == null) {
            return;
        }
        customH5ProWebview.execJs("window.nativeEvent&&window.nativeEvent('onResume')", null);
    }
}
