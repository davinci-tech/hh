package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.suggestion.ui.view.FilterFlowLayout;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.share.HiHealthError;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.togglebutton.HealthToggleButton;
import defpackage.asb;
import defpackage.asc;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.koq;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class ConfirmDistanceFragment extends BaseFragment {
    private HealthTextView b;
    private Context c;
    private FilterFlowLayout d;
    private HealthColumnLinearLayout f;
    private RelativeLayout h;
    private RunPlanCreateActivity.OnNextPageListener k;
    private HealthTextView l;
    private int n;
    private ImageView o;
    private HealthProgressBar q;
    private int m = 0;
    private List<View> g = new ArrayList();
    private List<View> i = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<Attribute> f3334a = new ArrayList();
    private int j = -1;
    private FilterFlowLayout.OnCheckChangeListener e = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.ConfirmDistanceFragment.1
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            FragmentActivity activity = ConfirmDistanceFragment.this.getActivity();
            if (activity != null) {
                if (i == 0) {
                    ConfirmDistanceFragment.this.m = 25;
                } else if (i == 1) {
                    ConfirmDistanceFragment.this.m = 75;
                } else if (i == 2) {
                    ConfirmDistanceFragment.this.m = 125;
                } else if (i == 3) {
                    ConfirmDistanceFragment.this.m = 175;
                } else if (i == 4) {
                    ConfirmDistanceFragment.this.m = 250;
                } else {
                    ConfirmDistanceFragment.this.m = 350;
                }
                if (ConfirmDistanceFragment.this.d.getChildAt(i) instanceof HealthToggleButton) {
                    HealthToggleButton healthToggleButton = (HealthToggleButton) ConfirmDistanceFragment.this.d.getChildAt(i);
                    gdk.aLt_(ConfirmDistanceFragment.this.c, healthToggleButton, R.drawable._2131430718_res_0x7f0b0d3e, 0);
                    if (ConfirmDistanceFragment.this.j != -1 && ConfirmDistanceFragment.this.j != i && (ConfirmDistanceFragment.this.d.getChildAt(ConfirmDistanceFragment.this.j) instanceof HealthToggleButton)) {
                        gdk.aLs_(ConfirmDistanceFragment.this.c, (HealthToggleButton) ConfirmDistanceFragment.this.d.getChildAt(ConfirmDistanceFragment.this.j), R.drawable._2131430719_res_0x7f0b0d3f, R.color._2131299241_res_0x7f090ba9);
                    }
                    ConfirmDistanceFragment.this.j = i;
                    ConfirmDistanceFragment.this.l.setText(ffy.d(ConfirmDistanceFragment.this.c, ConfirmDistanceFragment.this.n, healthToggleButton.getText().toString()));
                    if (ConfirmDistanceFragment.this.k != null) {
                        ConfirmDistanceFragment.this.k.backLock();
                        gdk.b(false, ConfirmDistanceFragment.this.i);
                        gdk.aLq_(ConfirmDistanceFragment.this.h, ConfirmDistanceFragment.this.f, ConfirmDistanceFragment.this.k, true);
                        return;
                    }
                    activity.finish();
                    return;
                }
                LogUtil.b("Suggestion_ConfirmDistanceFragment", "onChecked mRunDayFlowLayout curButtonIndex is invalid.");
                return;
            }
            LogUtil.h("Suggestion_ConfirmDistanceFragment", "mBtnGroupListener onChecked activity is null.");
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            onChecked(i);
        }
    };

    public static ConfirmDistanceFragment c() {
        return new ConfirmDistanceFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_confirm_distance, viewGroup, false);
        aKv_(inflate);
        return inflate;
    }

    private void aKv_(View view) {
        if (view == null) {
            return;
        }
        this.b = (HealthTextView) view.findViewById(R.id.tips_comments);
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.h = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.q = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.o = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(R.string._2130848586_res_0x7f022b4a);
        this.g.add(healthTextView);
        this.l = (HealthTextView) view.findViewById(R.id.tips_summary);
        b();
        this.f = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        View findViewById = view.findViewById(R.id.body_view);
        BaseActivity.setViewSafeRegion(true, findViewById);
        this.g.add(findViewById);
        this.i.add(findViewById);
        aKu_(view);
        this.d = (FilterFlowLayout) view.findViewById(R.id.btn_group_confirm_distance);
        e();
        gdk.aLu_(view, healthColumnRelativeLayout, findViewById, null, getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446));
        gdk.b(true, this.g);
        gdr.aLy_(this.c, 15, this.q, this.o);
    }

    private void aKu_(View view) {
        String[] strArr;
        if (view == null) {
            return;
        }
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.tv_distance);
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this.c, R.color._2131296971_res_0x7f0902cb));
        if (UnitUtil.h()) {
            strArr = new String[]{ffy.d(this.c, R.string._2130848587_res_0x7f022b4b, c(30)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(31), c(60)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(61), c(90)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(91), c(120)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(121), c(180)), ffy.d(this.c, R.string._2130848588_res_0x7f022b4c, c(180))};
            healthSubHeader.setHeadTitleText(getString(R.string._2130842416_res_0x7f021330));
            this.n = R.string._2130841422_res_0x7f020f4e;
        } else {
            strArr = new String[]{ffy.d(this.c, R.string._2130848587_res_0x7f022b4b, c(50)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(51), c(100)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(101), c(150)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(HiHealthError.ERR_WRONG_DEVICE), c(200)), ffy.d(this.c, R.string._2130845600_res_0x7f021fa0, c(201), c(300)), ffy.d(this.c, R.string._2130848588_res_0x7f022b4c, c(300))};
            healthSubHeader.setHeadTitleText(getString(R.string._2130842415_res_0x7f02132f));
            this.n = R.string._2130841421_res_0x7f020f4d;
        }
        for (int i = 0; i < strArr.length; i++) {
            this.f3334a.add(new Attribute(i, strArr[i]));
        }
    }

    private void e() {
        this.d.setPadding((int) getResources().getDimension(R.dimen._2131362009_res_0x7f0a00d9), (int) getResources().getDimension(R.dimen._2131362563_res_0x7f0a0303), (int) (getResources().getDimension(R.dimen._2131362007_res_0x7f0a00d7) - getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8)), (int) getResources().getDimension(R.dimen._2131362563_res_0x7f0a0303));
        this.d.setToggleButtonSourceId(R.layout.item_confirm_distance_button);
        this.d.d(this.c, this.f3334a);
        this.d.setOnCheckedChangeListener(this.e);
    }

    private void b() {
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.ConfirmDistanceFragment.4
            @Override // java.lang.Runnable
            public void run() {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
                calendar.set(14, 0);
                long timeInMillis = calendar.getTimeInMillis();
                calendar.set(5, calendar.get(5) - 30);
                asb.d().c(calendar.getTimeInMillis(), timeInMillis - 1, new int[]{2}, new String[]{"Track_Run_Distance_Sum"}, new d(ConfirmDistanceFragment.this));
            }
        });
    }

    static class d implements HiAggregateListener {
        WeakReference<ConfirmDistanceFragment> d;

        d(ConfirmDistanceFragment confirmDistanceFragment) {
            this.d = new WeakReference<>(confirmDistanceFragment);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ConfirmDistanceFragment confirmDistanceFragment = this.d.get();
            if (confirmDistanceFragment == null) {
                LogUtil.b("Suggestion_ConfirmDistanceFragment", "LastThirtyDaysRunDistanceCallback onResult fragment is null.");
                return;
            }
            if (koq.b(list, 0)) {
                LogUtil.b("Suggestion_ConfirmDistanceFragment", "onResult datas is null. errCode = ", Integer.valueOf(i), ", anchor= ", Integer.valueOf(i2));
                confirmDistanceFragment.c(0.0f);
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            if (hiHealthData != null) {
                confirmDistanceFragment.c(hiHealthData.getFloat("Track_Run_Distance_Sum"));
            } else {
                LogUtil.b("Suggestion_ConfirmDistanceFragment", "onResult hiHealthData is null.");
                confirmDistanceFragment.c(0.0f);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("Suggestion_ConfirmDistanceFragment", "onResultIntent:", Integer.valueOf(i), "errorCode:", Integer.valueOf(i2), "anchor:", Integer.valueOf(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        String b;
        FragmentActivity activity = getActivity();
        if (activity == null || !isAdded()) {
            LogUtil.b("Suggestion_ConfirmDistanceFragment", "setComments activity is null.");
            return;
        }
        if (UnitUtil.h()) {
            int e = (int) UnitUtil.e(f / 1000.0f, 3);
            b = ffy.b(R.plurals._2130903109_res_0x7f030045, e, UnitUtil.e(e, 1, 0));
        } else {
            int i = (int) (f / 1000.0f);
            b = ffy.b(R.plurals._2130903108_res_0x7f030044, i, UnitUtil.e(i, 1, 0));
        }
        String d2 = ffy.d(this.c, R.string._2130848665_res_0x7f022b99, b);
        int b2 = b(d2);
        int length = d2.length() - b(new StringBuffer(d2).reverse().toString());
        final SpannableString spannableString = new SpannableString(d2);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.c, R.color._2131299241_res_0x7f090ba9)), b2, length, 34);
        spannableString.setSpan(new TypefaceSpan(getString(R.string._2130851581_res_0x7f0236fd)), b2, length, 34);
        spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131365076_res_0x7f0a0cd4)), b2, length, 34);
        activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.ConfirmDistanceFragment.5
            @Override // java.lang.Runnable
            public void run() {
                ConfirmDistanceFragment.this.b.setText(spannableString);
                ConfirmDistanceFragment.this.b.setVisibility(0);
            }
        });
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = Pattern.compile("\\d").matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    private String c(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_ConfirmDistanceFragment", "isHidden fragment.");
        } else {
            this.h.setVisibility(0);
            gdk.b(true, this.g);
        }
    }

    public void e(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.k = onNextPageListener;
    }

    public int a() {
        return this.m;
    }
}
