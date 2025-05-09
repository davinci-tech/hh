package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.TipsFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.utils.CustomChartTitleBar;

/* loaded from: classes8.dex */
public class TrackLineChartActivityTipsStepTwoFrag extends TipsFragment implements View.OnTouchListener, View.OnClickListener {
    private View.OnClickListener b = null;

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f3743a = null;

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
        View inflate = layoutInflater.inflate(R.layout.track_linechart_tips_frag_step_two, (ViewGroup) null);
        if (!(inflate instanceof RelativeLayout)) {
            LogUtil.b("Track_TrackLineChartActivityTipsFrag", "object is not instanceof RelativeLayout");
            return null;
        }
        RelativeLayout relativeLayout = (RelativeLayout) inflate;
        RelativeLayout relativeLayout2 = (RelativeLayout) relativeLayout.findViewById(R.id.step_two_click_tips);
        int a2 = CustomChartTitleBar.a(layoutInflater.getContext());
        ViewGroup.LayoutParams layoutParams = relativeLayout2.getLayoutParams();
        if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
            LogUtil.b("Track_TrackLineChartActivityTipsFrag", "objectLayoutParams is not instanceof LayoutParams");
            return null;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.height = a2;
        relativeLayout2.setLayoutParams(layoutParams2);
        relativeLayout.setOnTouchListener(this);
        relativeLayout.findViewById(R.id.all_content).setOnClickListener(this);
        relativeLayout.findViewById(R.id.tips_close_btn).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityTipsStepTwoFrag.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_TrackLineChartActivityTipsFrag", "onClick tips close");
                if (TrackLineChartActivityTipsStepTwoFrag.this.f3743a != null) {
                    TrackLineChartActivityTipsStepTwoFrag.this.f3743a.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return relativeLayout;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("Track_TrackLineChartActivityTipsFrag", "onClick frag,tips next");
        View.OnClickListener onClickListener = this.b;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.TipsFragment
    public void setOnNextClickListener(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.TipsFragment
    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.f3743a = onClickListener;
    }
}
