package com.huawei.health.suggestion.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class VerticalLoadingRateFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 8;
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
            }
        } else {
            super.e();
            d();
            this.d.setVisibility(0);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fragment.VerticalLoadingRateFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o()) {
                        LogUtil.h("Suggestion_PostureFragment", "remind is click fast");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        caj.a().a("VERTICAL_LOADING_RATE");
                        VerticalLoadingRateFragment.this.d(25);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
        }
    }

    private void d() {
        this.h.setText(getString(R$string.IDS_bolt_avg_peak_impact_rate));
        String e = UnitUtil.e(this.o.getValue(), 1, 1);
        String quantityString = this.c.getResources().getQuantityString(R.plurals._2130903324_res_0x7f03011c, (int) this.o.getValue(), "");
        if (LanguageUtil.bp(this.c)) {
            this.l.setText(quantityString);
            Typeface create = Typeface.create(this.c.getString(R.string._2130851582_res_0x7f0236fe), 0);
            this.l.setTextSize(2, 14.0f);
            this.l.setMinHeight(nsn.c(this.c, 35.0f));
            this.l.setGravity(80);
            this.l.setTypeface(create);
            this.g.setTextSize(1, 35.0f);
            Typeface create2 = Typeface.create(this.c.getString(R.string._2130851581_res_0x7f0236fd), 0);
            this.g.setMinHeight(nsn.c(this.c, 35.0f));
            this.g.setTypeface(create2);
            this.g.setText(e);
            return;
        }
        this.l.setText(e);
        this.g.setText(quantityString);
    }
}
