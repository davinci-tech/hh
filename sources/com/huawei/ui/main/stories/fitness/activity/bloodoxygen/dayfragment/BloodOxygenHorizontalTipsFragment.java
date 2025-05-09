package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment;

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
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalTipsFragment;

/* loaded from: classes9.dex */
public class BloodOxygenHorizontalTipsFragment extends TipsFragment implements View.OnTouchListener, View.OnClickListener {
    private View.OnClickListener d = null;
    private View.OnClickListener b = null;

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("BloodOxygenHorizontalTipsFragment", "onCreateView()");
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        View inflate = layoutInflater.inflate(R.layout.track_linechart_tips_frag_horizontal_blood_oxygen, (ViewGroup) null);
        if (!(inflate instanceof RelativeLayout)) {
            LogUtil.b("BloodOxygenHorizontalTipsFragment", "inflate is not instanceof RelativeLayout");
            return relativeLayout;
        }
        RelativeLayout relativeLayout2 = (RelativeLayout) inflate;
        RelativeLayout relativeLayout3 = (RelativeLayout) relativeLayout2.findViewById(R.id.step_two_click_tips);
        int a2 = CustomChartTitleBar.a(layoutInflater.getContext());
        ViewGroup.LayoutParams layoutParams = relativeLayout3.getLayoutParams();
        layoutParams.height = a2;
        relativeLayout3.setLayoutParams(layoutParams);
        relativeLayout2.setOnTouchListener(this);
        relativeLayout2.findViewById(R.id.all_content).setOnClickListener(this);
        relativeLayout2.findViewById(R.id.tips_close_btn).setOnClickListener(new View.OnClickListener() { // from class: pkd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodOxygenHorizontalTipsFragment.this.dqk_(view);
            }
        });
        return relativeLayout2;
    }

    public /* synthetic */ void dqk_(View view) {
        LogUtil.a("BloodOxygenHorizontalTipsFragment", "onClick tips close");
        View.OnClickListener onClickListener = this.b;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("BloodOxygenHorizontalTipsFragment", "onClick frag,tips next");
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
        this.b = onClickListener;
    }
}
