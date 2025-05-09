package com.huawei.health.suggestion.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fragment.EversionExcursionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.caj;

/* loaded from: classes8.dex */
public class EversionExcursionFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 4;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
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
        this.h.setText(getString(R$string.IDS_bolt_avg_eversion_excursion));
        HealthTextView healthTextView = this.l;
        Resources resources = getResources();
        int i = acquireValueList[0];
        healthTextView.setText(resources.getQuantityString(R.plurals._2130903247_res_0x7f0300cf, i, Integer.valueOf(i)));
        this.g.setText("");
        this.d.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: fss
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EversionExcursionFragment.this.aFO_(view);
            }
        });
    }

    public /* synthetic */ void aFO_(View view) {
        caj.a().a("VALGUS_AMPLITUDE");
        d(26);
        ViewClickInstrumentation.clickOnView(view);
    }
}
