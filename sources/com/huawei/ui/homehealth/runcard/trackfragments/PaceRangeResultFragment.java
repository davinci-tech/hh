package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class PaceRangeResultFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9546a;
    private ImageView b;
    private RelativeLayout c;
    private Context d;
    private RelativeLayout e;
    private boolean f = true;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private OnResultItemClickListener j;

    public interface OnResultItemClickListener {
        void onResultItemClick(int i);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h("Track_PaceRangeResultFragment", "onCreateView view is null");
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.fragment_pace_range_result, viewGroup, false);
        dfE_(inflate);
        a();
        b();
        return inflate;
    }

    private void dfE_(View view) {
        this.c = (RelativeLayout) view.findViewById(R.id.layout_pace_range_distance);
        this.e = (RelativeLayout) view.findViewById(R.id.layout_pace_range_achievement);
        this.b = (ImageView) view.findViewById(R.id.img_pace_range_right_distance);
        this.f9546a = (ImageView) view.findViewById(R.id.img_pace_range_right_achievement);
        this.h = (HealthTextView) view.findViewById(R.id.tv_pace_range_right_distance);
        this.g = (HealthTextView) view.findViewById(R.id.tv_pace_range_label_achievement);
        this.i = (HealthTextView) view.findViewById(R.id.tv_pace_range_right_achievement);
    }

    private void a() {
        String str;
        FragmentActivity activity = getActivity();
        this.d = activity;
        a(activity);
        Bundle arguments = getArguments();
        String str2 = "";
        if (arguments != null) {
            String string = arguments.getString("achievement_value", "");
            str = arguments.getString("distance_value", "");
            str2 = string;
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(str2)) {
            b(str2);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e(str);
    }

    private void b() {
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.h.addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.PaceRangeResultFragment.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                PaceRangeResultFragment.this.b(!editable.toString().trim().equals(PaceRangeResultFragment.this.getString(R.string._2130843976_res_0x7f021948)));
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnResultItemClickListener onResultItemClickListener;
        OnResultItemClickListener onResultItemClickListener2;
        int id = view.getId();
        if (R.id.layout_pace_range_distance == id && (onResultItemClickListener2 = this.j) != null) {
            onResultItemClickListener2.onResultItemClick(1);
        }
        if (R.id.layout_pace_range_achievement == id && (onResultItemClickListener = this.j) != null && this.f) {
            onResultItemClickListener.onResultItemClick(2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        b(!this.h.getText().toString().trim().equals(getString(R.string._2130843976_res_0x7f021948)));
    }

    private void a(Context context) {
        if (LanguageUtil.bc(context)) {
            d(R.drawable._2131427841_res_0x7f0b0201);
            b(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            d(R.drawable._2131427842_res_0x7f0b0202);
            b(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    public void c(OnResultItemClickListener onResultItemClickListener) {
        this.j = onResultItemClickListener;
    }

    private void d(int i) {
        ImageView imageView = this.b;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
    }

    private void b(int i) {
        ImageView imageView = this.f9546a;
        if (imageView != null) {
            imageView.setImageResource(i);
        }
    }

    public void e(String str) {
        HealthTextView healthTextView = this.h;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public void b(String str) {
        HealthTextView healthTextView = this.i;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            c();
        } else {
            e();
        }
    }

    private void c() {
        c(ContextCompat.getColor(this.d, R.color._2131299236_res_0x7f090ba4));
        e(ContextCompat.getColor(this.d, R.color._2131299241_res_0x7f090ba9));
        a(true);
    }

    private void e() {
        c(ContextCompat.getColor(this.d, R.color._2131299244_res_0x7f090bac));
        e(ContextCompat.getColor(this.d, R.color._2131299244_res_0x7f090bac));
        a(false);
    }

    private void e(int i) {
        HealthTextView healthTextView = this.i;
        if (healthTextView != null) {
            healthTextView.setTextColor(i);
        }
    }

    private void c(int i) {
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.setTextColor(i);
        }
    }

    private void a(boolean z) {
        if (this.e != null) {
            this.f = z;
        }
    }
}
