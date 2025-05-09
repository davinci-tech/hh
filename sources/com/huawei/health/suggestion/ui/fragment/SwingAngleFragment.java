package com.huawei.health.suggestion.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fragment.SwingAngleFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.caj;

/* loaded from: classes8.dex */
public class SwingAngleFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 5;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
        if (this.o == null) {
            return;
        }
        super.e();
        this.h.setText(getString(R$string.IDS_running_posture_avg_swing_angle));
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length == 0) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        } else {
            HealthTextView healthTextView = this.l;
            Resources resources = getResources();
            int i = acquireValueList[0];
            healthTextView.setText(resources.getQuantityString(R.plurals._2130903247_res_0x7f0300cf, i, Integer.valueOf(i)));
            this.d.setVisibility(0);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: fsu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SwingAngleFragment.this.aGg_(view);
                }
            });
        }
    }

    public /* synthetic */ void aGg_(View view) {
        caj.a().a("OSCILLATING_ANGLE");
        d(27);
        ViewClickInstrumentation.clickOnView(view);
    }
}
