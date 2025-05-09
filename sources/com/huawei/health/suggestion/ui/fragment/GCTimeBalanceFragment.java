package com.huawei.health.suggestion.ui.fragment;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.suggestion.ui.fragment.GCTimeBalanceFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import defpackage.caj;
import defpackage.nsi;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes8.dex */
public class GCTimeBalanceFragment extends PostureSuggestBaseFragment {
    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3233a = 4;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment
    protected void e() {
        super.e();
        if (this.o == null) {
            return;
        }
        float value = this.o.getValue();
        if (value < 0.0f) {
            if (getActivity() != null) {
                getActivity().finish();
            }
        } else {
            super.e();
            this.h.setText(getString(R$string.IDS_bolt_balance_avg_left_right_touches));
            c(value);
            this.d.setVisibility(0);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: fsq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GCTimeBalanceFragment.this.aFZ_(view);
                }
            });
        }
    }

    public /* synthetic */ void aFZ_(View view) {
        caj.a().a("PARAMETER_GROUND_TIME_BALANCE");
        d(19);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(float f) {
        String e = UnitUtil.e(f, 2, 1);
        String e2 = UnitUtil.e(100.0f - f, 2, 1);
        String string = getResources().getString(R$string.IDS_bolt_balance_rate_left_right_value, e, e2);
        try {
            if (LanguageUtil.bp(this.c)) {
                c(string, e, e2);
                return;
            }
            if (LanguageUtil.b(this.c)) {
                a(f, string, e, e2);
                return;
            }
            int indexOf = string.indexOf(e);
            int lastIndexOf = string.lastIndexOf(e2);
            int length = e.length();
            int length2 = e2.length();
            SpannableString spannableString = new SpannableString(string);
            nsi.cKG_(spannableString, 0, indexOf - 1, new AbsoluteSizeSpan(14, true));
            nsi.cKG_(spannableString, (indexOf + length) - 1, lastIndexOf - 1, new AbsoluteSizeSpan(14, true));
            nsi.cKG_(spannableString, (lastIndexOf + length2) - 1, string.length(), new AbsoluteSizeSpan(14, true));
            this.l.setText(spannableString);
        } catch (IndexOutOfBoundsException unused) {
            LogUtil.b("Suggestion_PostureFragment", "setPostureItemValue is exception");
            this.l.setText(string);
        }
    }

    private void aFY_(int i, int i2, SpannableString spannableString) {
        if (i < 0 || i >= i2 || i2 >= spannableString.length()) {
            return;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), i, i2, 33);
    }

    private void c(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str.replace("%", ""));
        int indexOf = str.indexOf(str2.replace("%", "").trim());
        int lastIndexOf = str.lastIndexOf(str3.replace("%", "").trim());
        sb.insert(indexOf - 1, "%");
        sb.insert(lastIndexOf - 1, "%");
        int length = str2.length();
        SpannableString spannableString = new SpannableString(sb.toString());
        aFY_(0, indexOf, spannableString);
        aFY_(indexOf + length, lastIndexOf, spannableString);
        this.l.setText(spannableString);
    }

    private void a(float f, String str, String str2, String str3) {
        int indexOf = str.indexOf(str2);
        if (f == 5.0f) {
            indexOf = str.lastIndexOf(str2);
        }
        int lastIndexOf = str.lastIndexOf(str3);
        if (f == 100.0f || f == 95.0f) {
            lastIndexOf = str.indexOf(str3);
        }
        if (f != 50.0f) {
            int i = lastIndexOf;
            lastIndexOf = indexOf;
            indexOf = i;
        }
        int length = str2.length();
        int length2 = str3.length();
        SpannableString spannableString = new SpannableString(str);
        aFY_(0, indexOf - 1, spannableString);
        aFY_((indexOf + length2) - 1, lastIndexOf - 1, spannableString);
        aFY_((lastIndexOf + length) - 1, str.length(), spannableString);
        this.l.setText(spannableString);
    }
}
