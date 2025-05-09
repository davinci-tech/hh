package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.suggestion.ui.fragment.LandingImpactFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class LandingImpactFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 3;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
        if (this.o == null) {
            return;
        }
        super.e();
        this.h.setText(getString(R$string.IDS_running_posture_avg_ground_impact_acceleration));
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length == 0) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        } else {
            this.l.setText(UnitUtil.e(acquireValueList[0], 1, 0));
            this.g.setText(getString(R$string.IDS_gravity_unit));
            this.d.setVisibility(0);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: fsv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LandingImpactFragment.this.aGd_(view);
                }
            });
        }
    }

    public /* synthetic */ void aGd_(View view) {
        caj.a().a("LANDING_IMPACT");
        d(8);
        ViewClickInstrumentation.clickOnView(view);
    }
}
