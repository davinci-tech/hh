package com.huawei.health.suggestion.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fragment.FootStrikePatternFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.TotalDataRectView;
import defpackage.caj;
import defpackage.nsn;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class FootStrikePatternFragment extends PostureSuggestBaseFragment {
    private HealthTextView k;
    private HealthTextView n;
    private TotalDataRectView s;
    private HealthTextView t;

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 6;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    protected void aGe_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_FootStrikePatternFragment", "initLayout rootView == null");
            return;
        }
        this.s = (TotalDataRectView) view.findViewById(R.id.proportion_bar);
        this.n = (HealthTextView) view.findViewById(R.id.running_posture_avg_foot_strike_pattern_fore_value);
        this.t = (HealthTextView) view.findViewById(R.id.running_posture_avg_foot_strike_pattern_whole_value);
        this.k = (HealthTextView) view.findViewById(R.id.running_posture_avg_foot_strike_pattern_hind_value);
        super.aGe_(view);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        Resources resources = getResources();
        layoutParams.setMargins(resources.getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b), resources.getDimensionPixelSize(R.dimen._2131363473_res_0x7f0a0691), resources.getDimensionPixelSize(R.dimen._2131364634_res_0x7f0a0b1a), -resources.getDimensionPixelSize(R.dimen._2131363458_res_0x7f0a0682));
        view.findViewById(R.id.sug_running_posture_item).setLayoutParams(layoutParams);
        this.l.setVisibility(8);
        this.g.setVisibility(8);
        this.f.setVisibility(8);
        this.h.setText(getString(R$string.IDS_bolt_avg_foot_strike_pattern));
        this.d.setVisibility(0);
        this.d.setOnClickListener(new View.OnClickListener() { // from class: fsp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FootStrikePatternFragment.this.aFX_(view2);
            }
        });
        view.findViewById(R.id.sug_running_posture_item_proportinbar).setVisibility(0);
    }

    public /* synthetic */ void aFX_(View view) {
        if (nsn.o()) {
            LogUtil.h("Suggestion_FootStrikePatternFragment", "remind is click fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            caj.a().a("STRIKE_PATTERN");
            d(28);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    public void e() {
        if (this.o == null) {
            if (getActivity() != null) {
                getActivity().finish();
                return;
            }
            return;
        }
        int[] acquireValueList = this.o.acquireValueList();
        if (acquireValueList == null || acquireValueList.length != 3) {
            if (getActivity() != null) {
                getActivity().finish();
                return;
            }
            return;
        }
        this.s.setColors(getResources().getColor(R$color.track_detail_running_posture_color_one), getResources().getColor(R$color.track_detail_running_posture_color_two), getResources().getColor(R$color.track_detail_running_posture_color_three));
        this.s.setViewData(acquireValueList[0], acquireValueList[1], acquireValueList[2]);
        this.s.postInvalidate();
        String e = UnitUtil.e(acquireValueList[0], 2, 0);
        String e2 = UnitUtil.e(acquireValueList[1], 2, 0);
        String e3 = UnitUtil.e(acquireValueList[2], 2, 0);
        this.n.setText(UnitUtil.bCR_(getContext(), "\\d+.\\d+|\\d+", e, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        this.t.setText(UnitUtil.bCR_(getContext(), "\\d+.\\d+|\\d+", e2, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        this.k.setText(UnitUtil.bCR_(getContext(), "\\d+.\\d+|\\d+", e3, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        this.i.setText(getString(this.o.acquireLevelLongTip()));
        this.j.setText(getString(this.o.acquireAdvice()));
    }
}
