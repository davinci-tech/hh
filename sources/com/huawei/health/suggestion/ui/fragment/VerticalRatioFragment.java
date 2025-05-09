package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.suggestion.ui.fragment.VerticalRatioFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class VerticalRatioFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 6;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    protected void e() {
        super.e();
        if (this.o == null) {
            return;
        }
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length == 0) {
            if (getActivity() != null) {
                getActivity().finish();
                return;
            }
            return;
        }
        super.e();
        this.h.setText(getString(R$string.IDS_bolt_average_vertical_amplitude_rate));
        this.l.setText(UnitUtil.e(this.o.getValue(), 2, 1));
        this.g.setText("");
        this.d.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: fsw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerticalRatioFragment.this.aGi_(view);
            }
        });
    }

    public /* synthetic */ void aGi_(View view) {
        caj.a().a("VERTICAL_STRIKE_RATE");
        d(20);
        ViewClickInstrumentation.clickOnView(view);
    }
}
