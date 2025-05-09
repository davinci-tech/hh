package com.huawei.health.suggestion.ui.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class AverageJumpTimeFragment extends BasketballSuggestionBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment
    public void b() {
        if (this.b == null) {
            return;
        }
        int[] valueList = this.b.getValueList();
        if (valueList == null || valueList.length == 0) {
            if (getActivity() != null) {
                getActivity().finish();
                return;
            }
            return;
        }
        super.b();
        this.h.setText(UnitUtil.e(valueList[0], 1, 0));
        this.g.setText(R$string.IDS_msec_unit);
        this.f.setText(getString(R$string.IDS_aw_version2_jump_time_question));
        StringBuilder sb = new StringBuilder(10);
        sb.append(getString(R$string.IDS_aw_version2_jump_time_answer_1));
        sb.append(System.lineSeparator());
        sb.append(String.format(getString(R$string.IDS_aw_version2_jump_time_answer_3), 900));
        this.i.setText(sb.toString());
        this.d.setText(getString(R$string.IDS_aw_version2_jump_time_answer_2));
        if (LanguageUtil.bc(this.f3228a)) {
            BitmapDrawable cKn_ = nrz.cKn_(this.f3228a, R$drawable.img_basketball_jump_time);
            if (cKn_ != null) {
                this.j.setImageDrawable(cKn_);
                return;
            }
            return;
        }
        this.j.setImageResource(R$drawable.img_basketball_jump_time);
    }
}
