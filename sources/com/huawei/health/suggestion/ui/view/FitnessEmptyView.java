package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes7.dex */
public class FitnessEmptyView extends FrameLayout {
    private HealthTextView d;
    private ImageView e;

    public FitnessEmptyView(Context context) {
        super(context);
        c(context);
    }

    public FitnessEmptyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    public FitnessEmptyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c(context);
    }

    private void c(Context context) {
        View inflate = View.inflate(context, R.layout.sug_fitness_have_norecord, this);
        this.e = (ImageView) inflate.findViewById(R.id.iv_sug_fitness_no_data);
        this.d = (HealthTextView) inflate.findViewById(R.id.sug_fitnes_nodata);
        inflate.findViewById(R.id.sug_reco_workoutlist_nodata).setVisibility(0);
    }

    public void e() {
        if (NetworkUtil.i()) {
            this.e.setImageResource(R.drawable._2131429871_res_0x7f0b09ef);
            this.d.setText(R.string._2130848453_res_0x7f022ac5);
        } else {
            this.e.setImageResource(R.drawable._2131430308_res_0x7f0b0ba4);
            this.d.setText(R.string._2130840733_res_0x7f020c9d);
        }
        setVisibility(0);
    }

    public void a() {
        if (getVisibility() == 0) {
            setVisibility(8);
        }
    }
}
