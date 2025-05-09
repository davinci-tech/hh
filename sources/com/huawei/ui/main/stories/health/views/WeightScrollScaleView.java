package com.huawei.ui.main.stories.health.views;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.R;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;

/* loaded from: classes7.dex */
public class WeightScrollScaleView extends ScrollScaleView {
    public WeightScrollScaleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public WeightScrollScaleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        setCustomStartColor(getContext().getColor(R.color._2131299374_res_0x7f090c2e));
        this.d = -1;
    }

    @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView
    public void setSelectedPosition(int i) {
        super.setSelectedPosition(c(i));
    }

    public int c(int i) {
        if (i < 0) {
            i = 0;
        }
        return i > getSumScale() + (-1) ? getSumScale() - 1 : i;
    }
}
