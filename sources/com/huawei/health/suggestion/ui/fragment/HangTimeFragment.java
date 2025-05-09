package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.suggestion.ui.fragment.HangTimeFragment;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class HangTimeFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 1;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
        if (this.o == null) {
            return;
        }
        super.e();
        this.h.setText(getString(R$string.IDS_aw_version2_average_jump_time));
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length == 0) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
                return;
            }
            return;
        }
        this.l.setText(UnitUtil.e(acquireValueList[0], 1, 0));
        this.g.setText(getString(R$string.IDS_msec_unit));
        c(SportDetailChartDataType.HANG_TIME);
        this.d.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: fst
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HangTimeFragment.this.aGc_(view);
            }
        });
    }

    public /* synthetic */ void aGc_(View view) {
        caj.a().a("HANG_TIME");
        d(16);
        ViewClickInstrumentation.clickOnView(view);
    }
}
