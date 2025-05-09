package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanDurationFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private Context f3335a;
    private RelativeLayout b;
    private List<View> c = new ArrayList();
    private List<View> d = new ArrayList();
    private HealthButton e;
    private HealthNumberPicker f;
    private ImageView g;
    private RunPlanCreateActivity.OnNextPageListener h;
    private HealthTextView i;
    private HealthColumnLinearLayout j;
    private HealthProgressBar m;

    public static PlanDurationFragment b() {
        return new PlanDurationFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3335a = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_set_duration, viewGroup, false);
        aKx_(inflate);
        return inflate;
    }

    private void aKx_(View view) {
        if (view == null) {
            LogUtil.b("Suggestion_SetPlanDurationFragment", "initView view is null.");
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.b = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.m = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.g = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(getString(R.string._2130848591_res_0x7f022b4f));
        this.c.add(healthTextView);
        this.j = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.i = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) view.findViewById(R.id.set_duration_num_picker);
        this.f = healthNumberPicker;
        BaseActivity.setViewSafeRegion(true, healthNumberPicker);
        this.c.add(this.f);
        this.d.add(this.f);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.sug_btn_next);
        this.e = healthButton;
        this.c.add(healthButton);
        this.d.add(this.e);
        a();
        gdk.b(true, this.c);
        gdr.aLy_(this.f3335a, 60, this.m, this.g);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_SetPlanDurationFragment", "isHidden fragment.");
        } else {
            this.b.setVisibility(0);
            gdk.b(true, this.c);
        }
    }

    private void a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 4; i < 17; i++) {
            arrayList.add(ffy.b(R.plurals._2130903485_res_0x7f0301bd, i, UnitUtil.e(i, 1, 0)));
        }
        this.f.setDisplayedValues((String[]) arrayList.toArray(new String[0]));
        this.f.setMinValue(0);
        this.f.setMaxValue(arrayList.size() - 1);
        this.f.setValue(4);
        this.f.setWrapSelectorWheel(false);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.PlanDurationFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentActivity activity = PlanDurationFragment.this.getActivity();
                if (activity != null && !nsn.a(500)) {
                    if (PlanDurationFragment.this.h != null) {
                        PlanDurationFragment.this.h.backLock();
                        gdk.b(false, PlanDurationFragment.this.d);
                        PlanDurationFragment.this.c();
                        gdk.aLq_(PlanDurationFragment.this.b, PlanDurationFragment.this.j, PlanDurationFragment.this.h, true);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    activity.finish();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("Suggestion_SetPlanDurationFragment", "mBtnNext onClick activity is null or click too fast.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public int d() {
        return this.f.getValue() + 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.i.setText(ffy.b(R.plurals._2130903311_res_0x7f03010f, d(), Integer.valueOf(d())));
    }

    public void d(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.h = onNextPageListener;
    }
}
