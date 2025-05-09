package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.suggestion.ui.view.FilterFlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.togglebutton.HealthToggleButton;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class TrainDateFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f3343a;
    private HealthProgressBar ad;
    private Context b;
    private int d;
    private HealthButton e;
    private boolean[] g;
    private HealthColumnLinearLayout h;
    private boolean[] i;
    private RelativeLayout j;
    private HealthCardView l;
    private RunPlanCreateActivity.OnNextPageListener m;
    private HealthSubHeader n;
    private FilterFlowLayout o;
    private FilterFlowLayout q;
    private List<Integer> r;
    private HealthTextView s;
    private HealthSubHeader t;
    private ImageView u;
    private RelativeLayout v;
    private int w;
    private int[] x;
    private HealthTextView y;
    private List<View> c = new ArrayList();
    private List<View> f = new ArrayList();
    private FilterFlowLayout.OnCheckChangeListener p = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TrainDateFragment.5
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            int childCount = TrainDateFragment.this.q.getChildCount();
            if (i >= 0 && i <= TrainDateFragment.this.g.length - 1 && i <= childCount - 1) {
                boolean[] zArr = (boolean[]) TrainDateFragment.this.g.clone();
                TrainDateFragment.this.g[i] = !TrainDateFragment.this.g[i];
                TrainDateFragment trainDateFragment = TrainDateFragment.this;
                int a2 = trainDateFragment.a(trainDateFragment.g);
                if (a2 > 6 || a2 < 2) {
                    TrainDateFragment.this.g = (boolean[]) zArr.clone();
                    nrh.d(TrainDateFragment.this.b, ffy.d(TrainDateFragment.this.b, R.string._2130848721_res_0x7f022bd1, UnitUtil.e(2.0d, 1, 0), UnitUtil.e(6.0d, 1, 0)));
                }
                if (TrainDateFragment.this.q.getChildAt(i) instanceof HealthToggleButton) {
                    HealthToggleButton healthToggleButton = (HealthToggleButton) TrainDateFragment.this.q.getChildAt(i);
                    boolean z = TrainDateFragment.this.g[i];
                    if (z) {
                        gdk.aLt_(TrainDateFragment.this.b, healthToggleButton, R.drawable._2131431617_res_0x7f0b10c1, 0);
                    } else {
                        gdk.aLs_(TrainDateFragment.this.b, healthToggleButton, R.drawable._2131431616_res_0x7f0b10c0, 0);
                    }
                    TrainDateFragment.this.a(healthToggleButton, i, z);
                    return;
                }
                LogUtil.b("Suggestion_TrainDateFragment", "onChecked mRunDayFlowLayout curButtonIndex is invalid.");
                return;
            }
            LogUtil.b("Suggestion_TrainDateFragment", "RunDay onChecked curButtonIndex is invalid.");
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            onChecked(i);
        }
    };
    private FilterFlowLayout.OnCheckChangeListener k = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TrainDateFragment.3
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            int childCount = TrainDateFragment.this.o.getChildCount();
            if (i >= 0 && i <= TrainDateFragment.this.i.length - 1 && i <= childCount - 1) {
                boolean[] zArr = (boolean[]) TrainDateFragment.this.i.clone();
                TrainDateFragment.this.i[i] = !TrainDateFragment.this.i[i];
                TrainDateFragment trainDateFragment = TrainDateFragment.this;
                int a2 = trainDateFragment.a(trainDateFragment.i);
                if (a2 > 5 || a2 < 0) {
                    TrainDateFragment.this.i = (boolean[]) zArr.clone();
                    nrh.d(TrainDateFragment.this.b, ffy.d(TrainDateFragment.this.b, R.string._2130848722_res_0x7f022bd2, UnitUtil.e(0.0d, 1, 0), UnitUtil.e(5.0d, 1, 0)));
                }
                if (TrainDateFragment.this.o.getChildAt(i) instanceof HealthToggleButton) {
                    HealthToggleButton healthToggleButton = (HealthToggleButton) TrainDateFragment.this.o.getChildAt(i);
                    boolean z = TrainDateFragment.this.i[i];
                    if (z) {
                        gdk.aLt_(TrainDateFragment.this.b, healthToggleButton, R.drawable._2131431617_res_0x7f0b10c1, 0);
                    } else {
                        gdk.aLs_(TrainDateFragment.this.b, healthToggleButton, R.drawable._2131431616_res_0x7f0b10c0, 0);
                    }
                    TrainDateFragment.this.a(healthToggleButton, i, z);
                    return;
                }
                LogUtil.b("Suggestion_TrainDateFragment", "onChecked mRunDayFlowLayout curButtonIndex is invalid.");
                return;
            }
            LogUtil.b("Suggestion_TrainDateFragment", "PowerDay onChecked curButtonIndex is invalid.");
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            onChecked(i);
        }
    };

    public static TrainDateFragment a(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        TrainDateFragment trainDateFragment = new TrainDateFragment();
        trainDateFragment.setArguments(bundle);
        return trainDateFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = arguments.getInt("type", 0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_train_date, viewGroup, false);
        aKP_(inflate);
        return inflate;
    }

    private void aKP_(View view) {
        if (view == null) {
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.j = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.ad = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.u = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        RelativeLayout relativeLayout = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.tips_layout);
        this.v = relativeLayout;
        this.f.add(relativeLayout);
        this.f.add(this.u);
        this.f.add(this.ad);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(getString(R.string._2130848574_res_0x7f022b3e));
        this.c.add(healthTextView);
        LogUtil.a("Suggestion_TrainDateFragment", "mEditTrainType:", Integer.valueOf(this.d));
        this.h = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.s = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        this.y = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary_comments);
        healthColumnRelativeLayout.findViewById(R.id.tips_explain).setVisibility(0);
        HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_comments);
        this.c.add(healthColumnLinearLayout);
        this.f.add(healthColumnLinearLayout);
        c(healthColumnRelativeLayout);
        HealthColumnLinearLayout healthColumnLinearLayout2 = (HealthColumnLinearLayout) view.findViewById(R.id.train_date_layout);
        aKO_(view, healthColumnLinearLayout2);
        a();
        g();
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.layout_bottom_btn);
        this.e = (HealthButton) relativeLayout2.findViewById(R.id.sug_btn_next);
        if (this.d == 1) {
            healthTextView.setText(R.string._2130848575_res_0x7f022b3f);
            this.e.setText(R.string._2130848576_res_0x7f022b40);
        }
        e();
        gdk.aLu_(view, healthColumnRelativeLayout, healthColumnLinearLayout2, relativeLayout2, 0);
        this.c.add(relativeLayout2);
        this.f.add(relativeLayout2);
        BaseActivity.setViewSafeRegion(true, healthColumnLinearLayout, healthColumnLinearLayout2);
        gdk.b(true, this.c);
        gdr.aLy_(this.b, 100, this.ad, this.u);
    }

    private void aKO_(View view, HealthColumnLinearLayout healthColumnLinearLayout) {
        this.c.add(healthColumnLinearLayout);
        this.f.add(healthColumnLinearLayout);
        this.t = (HealthSubHeader) view.findViewById(R.id.subHeader_run_day_tips);
        this.q = (FilterFlowLayout) view.findViewById(R.id.layout_btn_group_run_day);
        this.n = (HealthSubHeader) view.findViewById(R.id.subHeader_power_day_tips);
        this.l = (HealthCardView) view.findViewById(R.id.power_day_cardview);
        if (Utils.o()) {
            this.n.setVisibility(8);
            this.l.setVisibility(8);
        }
        this.o = (FilterFlowLayout) view.findViewById(R.id.layout_btn_group_power_day);
    }

    private void c(HealthColumnRelativeLayout healthColumnRelativeLayout) {
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_explain_one);
        healthTextView.setVisibility(0);
        healthTextView.setText(ffy.d(this.b, R.string._2130848564_res_0x7f022b34, 1));
        HealthTextView healthTextView2 = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_explain_two);
        healthTextView2.setVisibility(0);
        healthTextView2.setText(ffy.d(this.b, R.string._2130848565_res_0x7f022b35, 2));
    }

    private void a() {
        String[] strArr = {this.b.getString(R.string._2130837537_res_0x7f020021), this.b.getString(R.string._2130837538_res_0x7f020022), this.b.getString(R.string._2130837539_res_0x7f020023), this.b.getString(R.string._2130837540_res_0x7f020024), this.b.getString(R.string._2130837541_res_0x7f020025), this.b.getString(R.string._2130837542_res_0x7f020026), this.b.getString(R.string._2130837536_res_0x7f020020)};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            arrayList.add(new Attribute(i, strArr[i]));
        }
        this.g = new boolean[7];
        this.i = new boolean[7];
        int dimension = (int) getResources().getDimension(R.dimen._2131362009_res_0x7f0a00d9);
        int dimension2 = (int) getResources().getDimension(R.dimen._2131362565_res_0x7f0a0305);
        int dimension3 = (int) (getResources().getDimension(R.dimen._2131362007_res_0x7f0a00d7) - getResources().getDimension(R.dimen._2131363241_res_0x7f0a05a9));
        int dimension4 = (int) getResources().getDimension(R.dimen._2131362565_res_0x7f0a0305);
        this.q.setPadding(dimension, dimension2, dimension3, dimension4);
        this.q.setToggleButtonSourceId(R.layout.item_circular_btn);
        this.q.d(BaseApplication.e(), arrayList);
        this.q.setOnCheckedChangeListener(this.p);
        this.o.setPadding(dimension, dimension2, dimension3, dimension4);
        this.o.setToggleButtonSourceId(R.layout.item_circular_btn);
        this.o.d(BaseApplication.e(), arrayList);
        this.o.setOnCheckedChangeListener(this.k);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_TrainDateFragment", "isHidden fragment.");
        } else {
            this.j.setVisibility(0);
            gdk.b(true, this.c);
        }
    }

    private void e() {
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TrainDateFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentActivity activity = TrainDateFragment.this.getActivity();
                if (activity != null && !nsn.a(500)) {
                    if (TrainDateFragment.this.m != null) {
                        TrainDateFragment.this.m.backLock();
                        gdk.b(false, TrainDateFragment.this.f);
                        TrainDateFragment.this.i();
                        gdk.aLq_(TrainDateFragment.this.j, TrainDateFragment.this.h, TrainDateFragment.this.m, true);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    activity.finish();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("Suggestion_TrainDateFragment", "mBtnNext onClick activity is null or click too fast.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void e(List<Integer> list, List<Integer> list2, int i) {
        this.r = list;
        this.f3343a = list2;
        this.w = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Arrays.fill(this.g, false);
        Arrays.fill(this.i, false);
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= 7) {
                break;
            }
            this.g[i] = i == 0 || i == 2 || i == 5;
            boolean[] zArr = this.i;
            if (i != 1 && i != 3) {
                z = false;
            }
            zArr[i] = z;
            i++;
        }
        int i2 = this.w;
        if (i2 == 2) {
            this.g[4] = true;
        } else if (i2 == 3) {
            boolean[] zArr2 = this.g;
            zArr2[1] = true;
            zArr2[4] = true;
            this.i[6] = true;
        }
        int a2 = a(this.g);
        int a3 = a(this.i);
        this.t.setHeadTitleText(getResources().getQuantityString(R.plurals._2130903478_res_0x7f0301b6, a2, Integer.valueOf(a2)));
        this.t.setSubHeaderBackgroundColor(ContextCompat.getColor(this.b, R.color._2131296971_res_0x7f0902cb));
        this.n.setHeadTitleText(getResources().getQuantityString(R.plurals._2130903479_res_0x7f0301b7, a3, Integer.valueOf(a3)));
        this.n.setSubHeaderBackgroundColor(ContextCompat.getColor(this.b, R.color._2131296971_res_0x7f0902cb));
        if (this.d == 1) {
            b();
        }
        if (Utils.o()) {
            Arrays.fill(this.i, false);
        }
        h();
    }

    private void b() {
        if (koq.c(this.r)) {
            Arrays.fill(this.g, false);
            for (Integer num : this.r) {
                int intValue = num.intValue();
                boolean[] zArr = this.g;
                if (intValue - 1 < zArr.length) {
                    zArr[num.intValue() - 1] = true;
                }
            }
        }
        Arrays.fill(this.i, false);
        if (koq.c(this.f3343a)) {
            for (Integer num2 : this.f3343a) {
                int intValue2 = num2.intValue();
                boolean[] zArr2 = this.i;
                if (intValue2 - 1 < zArr2.length) {
                    zArr2[num2.intValue() - 1] = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(boolean[] zArr) {
        if (zArr == null) {
            return 0;
        }
        int i = 0;
        for (boolean z : zArr) {
            if (z) {
                i++;
            }
        }
        return i;
    }

    private void h() {
        int childCount = this.q.getChildCount();
        for (int i = 0; i <= childCount - 1; i++) {
            if (!(this.q.getChildAt(i) instanceof HealthToggleButton)) {
                LogUtil.b("Suggestion_TrainDateFragment", "mRunDayFlowLayout curBtnIndex is not HealthToggleButton.");
                return;
            }
            HealthToggleButton healthToggleButton = (HealthToggleButton) this.q.getChildAt(i);
            boolean[] zArr = this.g;
            if (i > zArr.length - 1) {
                LogUtil.b("Suggestion_TrainDateFragment", "curBtnIndex is out of mIsTrainRunSelectArrays.");
                return;
            }
            boolean z = zArr[i];
            if (z) {
                gdk.aLt_(this.b, healthToggleButton, R.drawable._2131431617_res_0x7f0b10c1, 0);
            } else {
                gdk.aLs_(this.b, healthToggleButton, R.drawable._2131431616_res_0x7f0b10c0, 0);
            }
            a(healthToggleButton, i, z);
        }
        int childCount2 = this.o.getChildCount();
        for (int i2 = 0; i2 <= childCount2 - 1; i2++) {
            if (!(this.o.getChildAt(i2) instanceof HealthToggleButton)) {
                LogUtil.b("Suggestion_TrainDateFragment", "mPowerDayFlowLayout curBtnIndex is not HealthToggleButton.");
                return;
            }
            HealthToggleButton healthToggleButton2 = (HealthToggleButton) this.o.getChildAt(i2);
            boolean[] zArr2 = this.i;
            if (i2 > zArr2.length - 1) {
                LogUtil.b("Suggestion_TrainDateFragment", "curBtnIndex is out of mIsPowerRunSelectArrays.");
                return;
            }
            boolean z2 = zArr2[i2];
            if (z2) {
                gdk.aLt_(this.b, healthToggleButton2, R.drawable._2131431617_res_0x7f0b10c1, 0);
            } else {
                gdk.aLs_(this.b, healthToggleButton2, R.drawable._2131431616_res_0x7f0b10c0, 0);
            }
            a(healthToggleButton2, i2, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthToggleButton healthToggleButton, int i, boolean z) {
        if (this.x == null) {
            ReleaseLogUtil.b("Suggestion_TrainDateFragment", "setCheckedContentDescription mWeekArray is null");
            this.x = new int[]{R.string._2130841437_res_0x7f020f5d, R.string._2130841539_res_0x7f020fc3, R.string._2130841558_res_0x7f020fd6, R.string._2130841538_res_0x7f020fc2, R.string._2130841414_res_0x7f020f46, R.string._2130841468_res_0x7f020f7c, R.string._2130841537_res_0x7f020fc1};
        }
        if (i >= 0) {
            int[] iArr = this.x;
            if (i < iArr.length) {
                jcf.bEx_(healthToggleButton, nsf.h(iArr[i]), z, CheckBox.class);
                return;
            }
        }
        ReleaseLogUtil.a("Suggestion_TrainDateFragment", "setCheckedContentDescription mWeekArray ", this.x, " index ", Integer.valueOf(i));
    }

    public List<Integer> d() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.g[i]) {
                arrayList.add(Integer.valueOf(i + 1));
            }
        }
        return arrayList;
    }

    public List<Integer> c() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.i[i]) {
                arrayList.add(Integer.valueOf(i + 1));
            }
        }
        return arrayList;
    }

    public void d(int i) {
        this.w = i;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b("Suggestion_TrainDateFragment", "update activity is null.");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.TrainDateFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    TrainDateFragment.this.g();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        String[] strArr = {this.b.getString(R.string._2130841437_res_0x7f020f5d), this.b.getString(R.string._2130841539_res_0x7f020fc3), this.b.getString(R.string._2130841558_res_0x7f020fd6), this.b.getString(R.string._2130841538_res_0x7f020fc2), this.b.getString(R.string._2130841414_res_0x7f020f46), this.b.getString(R.string._2130841468_res_0x7f020f7c), this.b.getString(R.string._2130841537_res_0x7f020fc1)};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (this.g[i]) {
                arrayList.add(strArr[i]);
            }
        }
        this.s.setText(ffy.d(this.b, R.string._2130848578_res_0x7f022b42, getString(R.string._2130848579_res_0x7f022b43), d(arrayList)));
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < 7; i2++) {
            if (this.i[i2]) {
                arrayList2.add(strArr[i2]);
            }
        }
        if (arrayList2.size() == 0) {
            this.y.setVisibility(8);
            return;
        }
        this.y.setVisibility(0);
        this.y.setText(ffy.d(this.b, R.string._2130848578_res_0x7f022b42, getString(R.string._2130848580_res_0x7f022b44), d(arrayList2)));
    }

    private String d(List<String> list) {
        int i = 0;
        if (koq.b(list, 0)) {
            LogUtil.b("Suggestion_TrainDateFragment", "selectConcatenatedString selectedDay is invalid.");
            return "";
        }
        int size = list.size();
        String str = list.get(0);
        if (size > 1) {
            while (i < size - 1) {
                i++;
                str = ffy.d(this.b, R.string._2130844422_res_0x7f021b06, str, list.get(i));
            }
        }
        return str;
    }

    public void c(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.m = onNextPageListener;
    }
}
