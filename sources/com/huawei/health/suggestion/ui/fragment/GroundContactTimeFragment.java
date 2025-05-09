package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.suggestion.ui.fragment.GroundContactTimeFragment;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class GroundContactTimeFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 0;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
        if (this.o == null) {
            return;
        }
        super.e();
        this.h.setText(getString(R$string.IDS_running_posture_avg_ground_contact_time));
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length == 0) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        } else {
            this.l.setText(UnitUtil.e(acquireValueList[0], 1, 0));
            this.g.setText(getString(R$string.IDS_msec_unit));
            c(SportDetailChartDataType.GROUND_CONTACT_TIME);
            this.d.setVisibility(0);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: fsr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GroundContactTimeFragment.this.aGa_(view);
                }
            });
        }
    }

    public /* synthetic */ void aGa_(View view) {
        caj.a().a("GROUND_CONTACT_TIME");
        d(7);
        ViewClickInstrumentation.clickOnView(view);
    }
}
