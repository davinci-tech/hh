package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$string;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class SleepTaskShareView extends BasicHealthModelShareView {
    public SleepTaskShareView(Context context) {
        super(context);
    }

    public SleepTaskShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SleepTaskShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTaskBenefit(int i) {
        if (i == 6) {
            this.d.setVisibility(0);
            this.d.setText(R$string.IDS_getup_regularly_no_data_content);
        } else if (i == 100) {
            String e = UnitUtil.e(30, 1, 0);
            this.d.setVisibility(0);
            this.d.setText(this.b.getString(R$string.IDS_restrict_naps_no_data, e, e));
        } else if (i == 101) {
            this.d.setVisibility(0);
            this.d.setText(this.b.getString(R$string.IDS_regular_work_and_rest_no_data));
        } else {
            this.d.setVisibility(8);
        }
    }

    public void setTaskText(int i, int i2) {
        int i3;
        String string;
        this.f.setVisibility(8);
        if (i == 6) {
            i3 = R$string.IDS_get_up_accumulation;
            string = this.b.getString(com.huawei.basichealthmodel.R$string.IDS_health_model_wake_up);
        } else if (i == 100) {
            i3 = R$string.IDS_limited_nap_accumulation;
            string = this.b.getString(R$string.IDS_restrict_naps);
        } else if (i != 101) {
            string = "";
            i3 = 0;
        } else {
            i3 = R$string.IDS_regular_work_and_rest_accumulation;
            string = this.b.getString(R$string.IDS_regular_work_and_rest);
        }
        this.c.setText(d(this.b.getString(i3, this.b.getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i2, Integer.valueOf(i2))), string, UnitUtil.e(i2, 1, 0)));
    }
}
