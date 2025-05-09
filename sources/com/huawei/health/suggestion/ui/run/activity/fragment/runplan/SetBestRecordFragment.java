package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetBestRecordFragment;
import com.huawei.health.suggestion.ui.view.FilterFlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.timepicker.HealthHourSecondNumberPicker;
import com.huawei.ui.commonui.togglebutton.HealthToggleButton;
import defpackage.asc;
import defpackage.cam;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.mcy;
import defpackage.mdd;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class SetBestRecordFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private FilterFlowLayout f3338a;
    private mdd aa;
    private ImageView ab;
    private HealthProgressBar ad;
    private HealthButton c;
    private Context h;
    private RelativeLayout k;
    private HealthColumnLinearLayout m;
    private HealthColumnLinearLayout n;
    private int o;
    private HealthColumnRelativeLayout p;
    private HealthColumnRelativeLayout q;
    private HealthColumnLinearLayout t;
    private int u;
    private int v;
    private HealthHourSecondNumberPicker w;
    private RunPlanCreateActivity.OnNextPageListener x;
    private HealthTextView y;
    private String z;
    private int r = 18000;
    private int s = 180;
    private int g = 0;
    private List<Attribute> d = new ArrayList();
    private List<Attribute> i = new ArrayList();
    private int l = 0;
    private List<View> f = new ArrayList();
    private List<View> j = new ArrayList();
    private boolean b = false;
    private FilterFlowLayout.OnCheckChangeListener e = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetBestRecordFragment.3
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            int i2 = i - SetBestRecordFragment.this.o;
            if (i2 <= SetBestRecordFragment.this.f3338a.getChildCount() - 1) {
                if (i2 != SetBestRecordFragment.this.l) {
                    SetBestRecordFragment.this.v = i;
                    if (SetBestRecordFragment.this.f3338a.getChildAt(i2) instanceof ToggleButton) {
                        ToggleButton toggleButton = (ToggleButton) SetBestRecordFragment.this.f3338a.getChildAt(i2);
                        SetBestRecordFragment.this.z = toggleButton.getText().toString();
                        gdk.aLt_(SetBestRecordFragment.this.h, toggleButton, 0, 0);
                        if (!(SetBestRecordFragment.this.f3338a.getChildAt(SetBestRecordFragment.this.l) instanceof ToggleButton)) {
                            LogUtil.b("Suggestion_SetDistanceFragment", "lastBtn ", Integer.valueOf(SetBestRecordFragment.this.l), " is not ToggleButton.");
                            return;
                        }
                        gdk.aLs_(SetBestRecordFragment.this.h, (ToggleButton) SetBestRecordFragment.this.f3338a.getChildAt(SetBestRecordFragment.this.l), 0, 0);
                        SetBestRecordFragment.this.l = i2;
                        SetBestRecordFragment.this.e(i);
                        SetBestRecordFragment.this.m();
                        return;
                    }
                    LogUtil.b("Suggestion_SetDistanceFragment", "curBtn ", Integer.valueOf(i2), " is not ToggleButton.");
                    return;
                }
                LogUtil.a("Suggestion_SetDistanceFragment", "button is selected.");
                return;
            }
            LogUtil.b("Suggestion_SetDistanceFragment", "onChecked curBtnIndex > childCount.");
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            onChecked(i);
        }
    };

    public static SetBestRecordFragment d(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("planType", i);
        SetBestRecordFragment setBestRecordFragment = new SetBestRecordFragment();
        setBestRecordFragment.setArguments(bundle);
        return setBestRecordFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.u = arguments.getInt("planType");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.h = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_set_best_record, viewGroup, false);
        aKA_(inflate);
        asc.e().b(new Runnable() { // from class: gcy
            @Override // java.lang.Runnable
            public final void run() {
                SetBestRecordFragment.this.e();
            }
        });
        return inflate;
    }

    public /* synthetic */ void e() {
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        if (achieveDataApi == null) {
            LogUtil.b("Suggestion_SetDistanceFragment", "onCreateView achieveDataApi is null.");
            return;
        }
        mdd singleDayData = achieveDataApi.getSingleDayData();
        if (singleDayData == null) {
            LogUtil.h("Suggestion_SetDistanceFragment", "getSingleDayData dayData is null.");
            return;
        }
        this.aa = singleDayData;
        e(this.l + this.o);
        m();
    }

    private void aKA_(View view) {
        if (view == null) {
            LogUtil.b("Suggestion_SetDistanceFragment", "initView view is null.");
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.q = healthColumnRelativeLayout;
        this.k = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        this.ad = (HealthProgressBar) this.q.findViewById(R.id.tips_image_progress);
        this.ab = (ImageView) this.q.findViewById(R.id.tips_image);
        HealthTextView healthTextView = (HealthTextView) this.q.findViewById(R.id.tips_question);
        healthTextView.setText(R.string._2130848590_res_0x7f022b4e);
        this.f.add(healthTextView);
        this.t = (HealthColumnLinearLayout) this.q.findViewById(R.id.layout_summary);
        this.y = (HealthTextView) this.q.findViewById(R.id.tips_summary);
        HealthTextView healthTextView2 = (HealthTextView) this.q.findViewById(R.id.tips_comments);
        healthTextView2.setText(R.string._2130848589_res_0x7f022b4d);
        healthTextView2.setVisibility(0);
        this.f.add(healthTextView2);
        this.j.add(healthTextView2);
        HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) view.findViewById(R.id.layout_best_record);
        this.m = healthColumnLinearLayout;
        this.f.add(healthColumnLinearLayout);
        this.j.add(this.m);
        ((HealthSubHeader) view.findViewById(R.id.set_best_record_distance)).setSubHeaderBackgroundColor(ContextCompat.getColor(this.h, R.color._2131296971_res_0x7f0902cb));
        ((HealthSubHeader) view.findViewById(R.id.set_best_record_achievement)).setSubHeaderBackgroundColor(ContextCompat.getColor(this.h, R.color._2131296971_res_0x7f0902cb));
        f();
        this.f3338a = (FilterFlowLayout) view.findViewById(R.id.btn_group_best_record);
        i();
        t();
        this.p = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_multi_num_picker);
        j();
        HealthColumnLinearLayout healthColumnLinearLayout2 = (HealthColumnLinearLayout) view.findViewById(R.id.sug_layout_btn);
        this.n = healthColumnLinearLayout2;
        this.f.add(healthColumnLinearLayout2);
        this.j.add(this.n);
        BaseActivity.setViewSafeRegion(true, healthTextView2, this.m, this.n);
        HealthButton healthButton = (HealthButton) this.n.findViewById(R.id.btn_ok);
        this.c = healthButton;
        healthButton.setOnClickListener(this);
        ((HealthButton) this.n.findViewById(R.id.btn_forget)).setOnClickListener(this);
        this.b = true;
        aKB_(view);
        gdk.b(true, this.f);
        gdr.aLy_(this.h, 30, this.ad, this.ab);
    }

    private void aKB_(final View view) {
        if (view == null) {
            LogUtil.b("Suggestion_SetDistanceFragment", "setSpringViewHeight view is null.");
        } else {
            final View findViewById = view.findViewById(R.id.spring_view);
            findViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: gcx
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    SetBestRecordFragment.this.aKC_(view, findViewById);
                }
            });
        }
    }

    public /* synthetic */ void aKC_(View view, View view2) {
        if (this.b) {
            this.b = false;
            int max = Math.max(view.getHeight() - (this.m.getHeight() + this.n.getHeight()), this.q.getHeight());
            view2.setLayoutParams(new RelativeLayout.LayoutParams(-1, max));
            if (max > 0) {
                ((HealthScrollView) view.findViewById(R.id.best_record_scroll_view)).setOverScrollable(false);
            }
        }
    }

    private void i() {
        this.f3338a.setPadding((int) getResources().getDimension(R.dimen._2131362009_res_0x7f0a00d9), (int) getResources().getDimension(R.dimen._2131362563_res_0x7f0a0303), (int) (getResources().getDimension(R.dimen._2131362007_res_0x7f0a00d7) - getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8)), (int) getResources().getDimension(R.dimen._2131362563_res_0x7f0a0303));
        this.f3338a.setToggleButtonSourceId(R.layout.item_btn_create_plan);
        this.f3338a.d(this.h, this.i);
        this.f3338a.setOnCheckedChangeListener(this.e);
    }

    private void j() {
        e(this.l + this.o);
        m();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        FragmentActivity activity = getActivity();
        if (activity == null || !isAdded()) {
            LogUtil.b("Suggestion_SetDistanceFragment", "resetMultiNumberPicker activity is null.");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.SetBestRecordFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    SetBestRecordFragment.this.w = new HealthHourSecondNumberPicker(SetBestRecordFragment.this.h, SetBestRecordFragment.this.s, SetBestRecordFragment.this.r, SetBestRecordFragment.this.g);
                    SetBestRecordFragment.this.p.removeAllViews();
                    SetBestRecordFragment.this.p.addView(SetBestRecordFragment.this.w);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        switch (i) {
            case 1:
                LogUtil.a("Suggestion_SetDistanceFragment", "button is 1km.");
                o();
                break;
            case 2:
                LogUtil.a("Suggestion_SetDistanceFragment", "button is 3km.");
                r();
                break;
            case 3:
                LogUtil.a("Suggestion_SetDistanceFragment", "button is 5km.");
                n();
                break;
            case 4:
                LogUtil.a("Suggestion_SetDistanceFragment", "button is 10km.");
                q();
                break;
            case 5:
                LogUtil.a("Suggestion_SetDistanceFragment", "button is half marathon.");
                k();
                break;
            case 6:
                LogUtil.a("Suggestion_SetDistanceFragment", "button is marathon.");
                l();
                break;
        }
    }

    private void t() {
        String str;
        int childCount = this.f3338a.getChildCount();
        if (childCount == 0) {
            LogUtil.b("Suggestion_SetDistanceFragment", "initSelectedBtn mBtnFlowLayout item is null.");
            return;
        }
        if (this.f3338a.getChildAt(0) instanceof HealthToggleButton) {
            HealthToggleButton healthToggleButton = (HealthToggleButton) this.f3338a.getChildAt(0);
            if (healthToggleButton.getTag() instanceof String) {
                this.o = CommonUtils.h((String) healthToggleButton.getTag());
                if (this.u == 12) {
                    if (!(this.f3338a.getChildAt(2) instanceof HealthToggleButton)) {
                        return;
                    }
                    HealthToggleButton healthToggleButton2 = (HealthToggleButton) this.f3338a.getChildAt(2);
                    if (!(healthToggleButton2.getTag() instanceof String)) {
                        return;
                    } else {
                        str = (String) healthToggleButton2.getTag();
                    }
                } else {
                    int i = childCount - 2;
                    if (!(this.f3338a.getChildAt(i) instanceof HealthToggleButton)) {
                        return;
                    }
                    HealthToggleButton healthToggleButton3 = (HealthToggleButton) this.f3338a.getChildAt(i);
                    if (!(healthToggleButton3.getTag() instanceof String)) {
                        return;
                    } else {
                        str = (String) healthToggleButton3.getTag();
                    }
                }
                int h = CommonUtils.h(str);
                int i2 = h - this.o;
                this.l = i2;
                this.v = h;
                if (i2 > childCount - 1 || !(this.f3338a.getChildAt(i2) instanceof ToggleButton)) {
                    LogUtil.b("Suggestion_SetDistanceFragment", "setSelectedBtnStyle mBtnFlowLayout.getChildAt = ", Integer.valueOf(this.l), " is not ToggleButton.");
                    return;
                }
                ToggleButton toggleButton = (ToggleButton) this.f3338a.getChildAt(this.l);
                gdk.aLt_(this.h, toggleButton, 0, 0);
                this.z = toggleButton.getText().toString();
            }
        }
    }

    private void l() {
        mcy c;
        c(36000, 7299);
        mdd mddVar = this.aa;
        int i = 16200;
        if (mddVar != null && (c = mddVar.c()) != null) {
            i = e((int) c.e(), 16200);
        }
        this.g = i;
    }

    private void k() {
        mcy e;
        c(18000, 3452);
        mdd mddVar = this.aa;
        int i = 8400;
        if (mddVar != null && (e = mddVar.e()) != null) {
            i = e((int) e.e(), 8400);
        }
        this.g = i;
    }

    private void q() {
        mcy a2;
        c(9000, 1584);
        mdd mddVar = this.aa;
        int i = 4200;
        if (mddVar != null && (a2 = mddVar.a()) != null) {
            i = e((int) a2.e(), 4200);
        }
        this.g = i;
    }

    private void n() {
        mcy b;
        c(4500, 771);
        mdd mddVar = this.aa;
        int i = 2100;
        if (mddVar != null && (b = mddVar.b()) != null) {
            i = e((int) b.e(), 2100);
        }
        this.g = i;
    }

    private void r() {
        mcy d;
        c(2700, 440);
        mdd mddVar = this.aa;
        int i = 1200;
        if (mddVar != null && (d = mddVar.d()) != null) {
            i = e((int) d.e(), 1200);
        }
        this.g = i;
    }

    private void o() {
        c(900, 131);
        this.g = 420;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_SetDistanceFragment", "isHidden fragment.");
        } else {
            this.k.setVisibility(0);
            gdk.b(true, this.f);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.o()) {
            LogUtil.h("Suggestion_SetDistanceFragment", "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.c) {
            p();
            a(true);
        } else {
            a(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void p() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gdb
            @Override // java.lang.Runnable
            public final void run() {
                SetBestRecordFragment.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        int h = h();
        cam.d(this.h, g(), h, new Date().getTime());
        double c = cam.c(c(), h);
        cam.c(new int[]{cam.d(c), cam.a(c), cam.e(c), cam.c(c), cam.h(c), cam.b(c)});
    }

    private int h() {
        double d;
        double d2;
        double d3;
        int a2 = a();
        int i = this.v;
        if (i == 5 || i == 6) {
            return a2;
        }
        if (!UnitUtil.h()) {
            return this.v == 1 ? (int) (a2 * Math.pow(3.0d, 1.06d)) : a2;
        }
        int i2 = this.v;
        if (i2 == 1) {
            d = UnitUtil.d(1.0d, 3);
        } else if (i2 != 2) {
            if (i2 == 3) {
                d2 = UnitUtil.d(2.0d, 3);
                d3 = 5.0d;
            } else {
                d2 = UnitUtil.d(2.0d, 3);
                d3 = 10.0d;
            }
            d = d2 / d3;
        } else {
            d = UnitUtil.d(2.0d, 3) / 3.0d;
        }
        return (int) (a2 * Math.pow(d, 1.06d));
    }

    private int g() {
        int i = this.v;
        if (i == 5) {
            return 5;
        }
        if (i == 6) {
            return 6;
        }
        if (UnitUtil.h()) {
            return this.v == 1 ? 3 : 4;
        }
        int i2 = this.v;
        if (i2 != 3) {
            return i2 != 4 ? 0 : 2;
        }
        return 1;
    }

    private double c() {
        double d;
        int i = this.v;
        if (i == 5) {
            return 21097.5d;
        }
        if (i == 6) {
            return 42195.0d;
        }
        if (UnitUtil.h()) {
            if (this.v == 1) {
                d = UnitUtil.d(1.0d, 3);
            } else {
                d = UnitUtil.d(2.0d, 3);
            }
            return d * 1000.0d;
        }
        int i2 = this.v;
        if (i2 != 3) {
            return i2 != 4 ? 3000.0d : 10000.0d;
        }
        return 5000.0d;
    }

    private void a(boolean z) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.h("Suggestion_SetDistanceFragment", "setNextPage activity is null.");
            return;
        }
        RunPlanCreateActivity.OnNextPageListener onNextPageListener = this.x;
        if (onNextPageListener == null) {
            activity.finish();
            return;
        }
        onNextPageListener.backLock();
        gdk.b(false, this.j);
        e(z);
        gdk.aLq_(this.k, this.t, this.x, z);
    }

    private void f() {
        this.d.add(new Attribute(1, ffy.d(this.h, R.string._2130841421_res_0x7f020f4d, b(1))));
        this.d.add(new Attribute(2, ffy.d(this.h, R.string._2130841421_res_0x7f020f4d, b(3))));
        this.d.add(new Attribute(3, ffy.d(this.h, R.string._2130841421_res_0x7f020f4d, b(5))));
        this.d.add(new Attribute(4, ffy.d(this.h, R.string._2130841421_res_0x7f020f4d, b(10))));
        this.d.add(new Attribute(5, getString(R.string._2130841792_res_0x7f0210c0)));
        this.d.add(new Attribute(6, getString(R.string._2130841793_res_0x7f0210c1)));
        int i = this.u;
        if (i != 11) {
            if (i != 12) {
                switch (i) {
                    case 3:
                        d(0, 3);
                        break;
                    case 4:
                        d(1, 4);
                        break;
                    case 5:
                        d(2, 5);
                        break;
                    case 6:
                        d(3, 6);
                        break;
                }
            }
            this.i.addAll(this.d);
            return;
        }
        d(0, 2);
    }

    private String b(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    private void d(int i, int i2) {
        while (i < i2) {
            this.i.add(this.d.get(i));
            i++;
        }
    }

    public void d(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.x = onNextPageListener;
    }

    private void c(int i, int i2) {
        this.r = i - 1;
        this.s = i2;
    }

    private int e(int i, int i2) {
        return (i >= this.r || i <= this.s) ? i2 : i;
    }

    private void e(boolean z) {
        if (z) {
            this.y.setText(ffy.d(this.h, R.string._2130844476_res_0x7f021b3c, this.z, UnitUtil.a(a())));
            return;
        }
        this.y.setText(getString(R.string._2130848594_res_0x7f022b52));
    }

    public int a() {
        return this.w.getSelectedTime();
    }

    public int b() {
        return this.v;
    }
}
