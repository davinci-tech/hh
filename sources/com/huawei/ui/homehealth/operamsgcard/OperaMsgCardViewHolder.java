package com.huawei.ui.homehealth.operamsgcard;

import android.content.Context;
import android.view.View;
import android.widget.ViewFlipper;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;

/* loaded from: classes6.dex */
public class OperaMsgCardViewHolder extends CardViewHolder {
    public ViewFlipper b;

    public OperaMsgCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.opera_msg_flipper);
        this.b = viewFlipper;
        BaseActivity.setViewSafeRegion(true, viewFlipper);
    }
}
