package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.stories.health.activity.healthdata.AthleticAbilityEmptyActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.ruf;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class PaceRangeRunFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9547a;
    private HealthTextView b;
    private HealthTextView c;
    private Context d;
    private HealthTextView e;
    private HealthSwitchButton f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private LinearLayout k;
    private ImageView l;
    private RunningStateIndexData m;
    private OnRuntItemClickListener n;
    private boolean o;

    public interface OnRuntItemClickListener {
        void onRunItemClick(int i, boolean z);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h("Track_PaceRangeRunFragment", "onCreateView view is null");
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_pace_range_run, viewGroup, false);
        this.d = getActivity();
        dfF_(inflate);
        e();
        d();
        return inflate;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.f == compoundButton) {
            this.o = z;
            LogUtil.a("Track_PaceRangeRunFragment", "onCheckedChanged isChecked is ", Boolean.valueOf(z));
            OnRuntItemClickListener onRuntItemClickListener = this.n;
            if (onRuntItemClickListener != null) {
                onRuntItemClickListener.onRunItemClick(1, z);
            }
            e(z);
            b(z);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void e(boolean z) {
        if (z) {
            this.k.setClickable(false);
            this.h.setTextColor(ContextCompat.getColor(this.d, R.color._2131299244_res_0x7f090bac));
            this.g.setTextColor(ContextCompat.getColor(this.d, R.color._2131299244_res_0x7f090bac));
        } else {
            this.k.setClickable(true);
            this.h.setTextColor(ContextCompat.getColor(this.d, R.color._2131299236_res_0x7f090ba4));
            this.g.setTextColor(ContextCompat.getColor(this.d, R.color._2131299241_res_0x7f090ba9));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnRuntItemClickListener onRuntItemClickListener;
        int id = view.getId();
        if (R.id.hcrl_running_ability_index_container == id && (onRuntItemClickListener = this.n) != null) {
            onRuntItemClickListener.onRunItemClick(2, this.o);
        }
        if (R.id.htv_current_run == id) {
            Intent intent = new Intent(this.d, (Class<?>) RunningDataActivity.class);
            intent.putExtra("running_level_data", this.m);
            startActivity(intent);
        }
        if (R.id.htv_learn_run == id) {
            Intent intent2 = new Intent(this.d, (Class<?>) AthleticAbilityEmptyActivity.class);
            intent2.putExtra("athletic_ability_empty_flag", "running_no_record");
            startActivity(intent2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e(OnRuntItemClickListener onRuntItemClickListener) {
        this.n = onRuntItemClickListener;
    }

    private void dfF_(View view) {
        this.f = (HealthSwitchButton) view.findViewById(R.id.hwb_auto_calc_toggle);
        this.k = (LinearLayout) view.findViewById(R.id.hcrl_running_ability_index_container);
        this.h = (HealthTextView) view.findViewById(R.id.htv_running_ability_index);
        this.g = (HealthTextView) view.findViewById(R.id.htv_running_ability_index_value);
        this.l = (ImageView) view.findViewById(R.id.img_pace_range_right_run);
        this.j = (HealthTextView) view.findViewById(R.id.htv_wearing_device);
        this.e = (HealthTextView) view.findViewById(R.id.htv_base_newest_run);
        this.b = (HealthTextView) view.findViewById(R.id.htv_no_wearing_device);
        this.c = (HealthTextView) view.findViewById(R.id.htv_current_run);
        this.i = (HealthTextView) view.findViewById(R.id.htv_professional_guide);
        this.f9547a = (HealthTextView) view.findViewById(R.id.htv_learn_run);
    }

    private void e() {
        c(getActivity());
        Bundle arguments = getArguments();
        boolean z = false;
        if (arguments != null) {
            Parcelable parcelable = arguments.getParcelable("running_level_data");
            if (parcelable instanceof RunningStateIndexData) {
                this.m = (RunningStateIndexData) parcelable;
            }
            z = arguments.getBoolean("setting_auto_calc_key_status", false);
            this.o = z;
            this.f.setChecked(z);
            e(z);
        }
        b(z);
    }

    private void d() {
        this.f.setOnCheckedChangeListener(this);
        this.k.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f9547a.setOnClickListener(this);
    }

    private void c(Context context) {
        if (LanguageUtil.bc(context)) {
            c(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            c(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void b(boolean z) {
        float c = c();
        d(c, z);
        c(c, z);
    }

    private float c() {
        RunningStateIndexData runningStateIndexData = this.m;
        if (runningStateIndexData == null || runningStateIndexData.getRunningLevelCurrentData() == null) {
            return 0.0f;
        }
        return this.m.getRunningLevelCurrentData().getLastCurrentRunLevel();
    }

    private void d(float f, boolean z) {
        String str;
        String a2 = ruf.a(f);
        String string = getString(R.string._2130843976_res_0x7f021948);
        if (z) {
            str = f == 0.0f ? getString(R.string._2130844074_res_0x7f0219aa) : a2;
        } else {
            str = string;
        }
        d(str);
        Object[] objArr = new Object[1];
        if (f == 0.0f) {
            a2 = string;
        }
        objArr[0] = a2;
        String string2 = getString(R.string._2130845433_res_0x7f021ef9, objArr);
        String string3 = getString(R.string._2130841418_res_0x7f020f4a);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) string2);
        spannableStringBuilder.append((CharSequence) (LanguageUtil.h(this.d) ? "" : " "));
        spannableStringBuilder.append((CharSequence) string3);
        spannableStringBuilder.setSpan(new UrlSpanNoUnderline(spannableStringBuilder.toString()), string2.length(), spannableStringBuilder.length(), 18);
        this.c.setText(spannableStringBuilder);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void c(float f, boolean z) {
        if (z) {
            if (f == 0.0f) {
                b();
                return;
            } else {
                a();
                return;
            }
        }
        if (f == 0.0f) {
            i();
        } else {
            h();
        }
    }

    public void d(String str) {
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    private void c(int i) {
        ImageView imageView = this.l;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
    }

    private void h() {
        this.j.setVisibility(8);
        this.e.setVisibility(8);
        this.b.setVisibility(8);
        this.i.setVisibility(8);
        this.c.setVisibility(0);
        this.f9547a.setVisibility(8);
    }

    private void i() {
        this.j.setVisibility(8);
        this.e.setVisibility(8);
        this.b.setVisibility(8);
        this.i.setVisibility(0);
        this.c.setVisibility(8);
        this.f9547a.setVisibility(0);
    }

    private void a() {
        this.j.setVisibility(8);
        this.e.setVisibility(0);
        this.b.setVisibility(8);
        this.i.setVisibility(8);
        this.c.setVisibility(0);
        this.f9547a.setVisibility(8);
    }

    private void b() {
        this.j.setVisibility(0);
        this.e.setVisibility(8);
        this.b.setVisibility(8);
        this.i.setVisibility(8);
        this.c.setVisibility(8);
        this.f9547a.setVisibility(0);
    }

    class UrlSpanNoUnderline extends URLSpan {
        UrlSpanNoUnderline(String str) {
            super(str);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(ContextCompat.getColor(PaceRangeRunFragment.this.d, R.color._2131299061_res_0x7f090af5));
            textPaint.setUnderlineText(false);
        }
    }
}
