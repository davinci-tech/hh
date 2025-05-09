package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.suggestion.ui.fragment.GroundHangTimeRateFragment;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class GroundHangTimeRateFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 2;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
        if (this.o == null) {
            return;
        }
        super.e();
        this.h.setText(getString(R$string.IDS_motiontrack__average_ground_to_air_ratio));
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length == 0) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        this.l.setText(UnitUtil.e(acquireValueList[0] * 0.01f, 1, 1));
        this.g.setVisibility(8);
        c(SportDetailChartDataType.GROUND_HANG_TIME_RATE);
        this.d.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: fso
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GroundHangTimeRateFragment.this.aGb_(view);
            }
        });
    }

    public /* synthetic */ void aGb_(View view) {
        caj.a().a("IMPACT_HANG_TIME_RATE");
        d(17);
        ViewClickInstrumentation.clickOnView(view);
    }
}
