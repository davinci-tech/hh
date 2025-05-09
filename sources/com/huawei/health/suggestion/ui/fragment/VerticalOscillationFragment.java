package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.suggestion.ui.fragment.VerticalOscillationFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class VerticalOscillationFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 5;
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
        this.h.setText(getString(R$string.IDS_bolt_avg_vertical_amplitude));
        this.l.setText(UnitUtil.e(c(this.o.getValue()), 1, 1));
        if (!UnitUtil.h()) {
            this.g.setText(R$string.IDS_cm);
        } else {
            this.g.setText(R$string.IDS_ins);
        }
        this.d.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: fsx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VerticalOscillationFragment.this.aGh_(view);
            }
        });
    }

    public /* synthetic */ void aGh_(View view) {
        caj.a().a("PARAMETER_VERTICAL_OSCILLATION");
        d(18);
        ViewClickInstrumentation.clickOnView(view);
    }

    private float c(float f) {
        return !UnitUtil.h() ? f : (float) UnitUtil.e(f, 0);
    }
}
