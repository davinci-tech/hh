package com.huawei.ui.homehealth.functionsetcard;

import android.content.Context;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ohy;
import defpackage.oia;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class FunctionSetCardData extends EmptyFunctionSetCardData implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private FunctionSetCardViewHolder f9421a;

    @Override // com.huawei.ui.homehealth.functionsetcard.EmptyFunctionSetCardData, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    public FunctionSetCardData(Context context) {
        super(context);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.EmptyFunctionSetCardData, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        FunctionSetCardViewHolder functionSetCardViewHolder = (FunctionSetCardViewHolder) super.getCardViewHolder(viewGroup, layoutInflater);
        this.f9421a = functionSetCardViewHolder;
        return functionSetCardViewHolder;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        FunctionSetCardViewHolder functionSetCardViewHolder = this.f9421a;
        if (functionSetCardViewHolder == null) {
            LogUtil.h("FunctionSetCardData", "FunctionSetCardData : onResume()mFunctionSetCardViewHolder is null");
            return;
        }
        functionSetCardViewHolder.b();
        ReleaseLogUtil.e("FunctionSetCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        FunctionSetCardViewHolder functionSetCardViewHolder = this.f9421a;
        if (functionSetCardViewHolder != null) {
            functionSetCardViewHolder.a();
        }
        ohy.b();
        oia.e();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        FunctionSetCardViewHolder functionSetCardViewHolder = this.f9421a;
        if (functionSetCardViewHolder != null) {
            functionSetCardViewHolder.i();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "FunctionSetCardData";
    }
}
