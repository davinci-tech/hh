package com.huawei.ui.homehealth.stepscard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes6.dex */
public abstract class EmptyStepsCardData extends AbstractBaseCardData {
    private static final String TAG = "Step_EmptyStepsCardData";
    protected Context mContext;

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    public EmptyStepsCardData(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            LogUtil.b(TAG, "getCardViewHolder layoutInflater == null");
            layoutInflater = LayoutInflater.from(BaseApplication.getContext());
        }
        StepsCardViewHolder stepsCardViewHolder = new StepsCardViewHolder(layoutInflater.inflate(R.layout.layout_step_card_container, viewGroup, false), this.mContext, false);
        BaseActivity.setViewSafeRegion(true, stepsCardViewHolder.getHomeStepCardLayout());
        return stepsCardViewHolder;
    }
}
