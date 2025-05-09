package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.TipsFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class TrackLineChartActivityTipsStepOneFrag extends TipsFragment implements View.OnTouchListener, View.OnClickListener {
    private View.OnClickListener d = null;
    private View.OnClickListener c = null;

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater not found.");
        }
        LogUtil.a("Track_TrackLineChartActivityTipsFrag", "onCreateView--------");
        View inflate = layoutInflater.inflate(R.layout.track_linechart_tips_frag_step_one, (ViewGroup) null);
        if (!(inflate instanceof LinearLayout)) {
            LogUtil.b("Track_TrackLineChartActivityTipsFrag", "object is not instanceof LinearLayout");
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) inflate;
        linearLayout.setOnTouchListener(this);
        linearLayout.findViewById(R.id.all_content).setOnClickListener(this);
        linearLayout.findViewById(R.id.tips_close_btn).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityTipsStepOneFrag.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_TrackLineChartActivityTipsFrag", "onClick tips close");
                if (TrackLineChartActivityTipsStepOneFrag.this.c != null) {
                    TrackLineChartActivityTipsStepOneFrag.this.c.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return linearLayout;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("Track_TrackLineChartActivityTipsFrag", "onClick frag,tips next");
        View.OnClickListener onClickListener = this.d;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.TipsFragment
    public void setOnNextClickListener(View.OnClickListener onClickListener) {
        this.d = onClickListener;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.TipsFragment
    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.c = onClickListener;
    }
}
