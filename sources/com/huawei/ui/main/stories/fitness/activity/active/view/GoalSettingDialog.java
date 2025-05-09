package com.huawei.ui.main.stories.fitness.activity.active.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.view.GoalSettingDialog;
import defpackage.ixx;
import defpackage.nip;
import defpackage.nir;
import defpackage.niw;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.phi;
import defpackage.piq;
import defpackage.pxp;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class GoalSettingDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private int f9737a;
    private int b;
    private RelativeLayout c;
    private HealthButton d;
    private int e;
    private boolean f;
    private Context g;
    private TextView h;
    private int i;
    private HealthNumberPicker j;
    private LinearLayout k;
    private List<phi> l;
    private TextView m;
    private phi n;
    private ThreeCircleView o;
    private View r;
    private TextView t;

    static /* synthetic */ int a(GoalSettingDialog goalSettingDialog) {
        int i = goalSettingDialog.f9737a;
        goalSettingDialog.f9737a = i + 1;
        return i;
    }

    public GoalSettingDialog(Context context) {
        super(context, R.style.DialogFullScreen, 0);
        this.f9737a = 0;
        this.l = new ArrayList();
        this.g = context;
        d();
        a();
        this.f = nsn.ag(this.g);
    }

    private void a() {
        LogUtil.a("GoalSettingDialog", "initView");
        this.r = ((LayoutInflater) this.g.getSystemService("layout_inflater")).inflate(R.layout.goal_setting_dialog, (ViewGroup) null);
        b();
        c();
        e();
        setContentView(this.r);
        i();
        n();
    }

    private void d() {
        phi d = d(0, nsf.h(R$string.IDS_active_caloric), nsf.h(R$string.IDS_three_leaf_set_target_9), R.plurals._2130903372_res_0x7f03014c, R.color._2131298865_res_0x7f090a31);
        phi d2 = d(1, nsf.h(R$string.IDS_active_workout), String.format(Locale.ENGLISH, nsf.h(R$string.IDS_three_leaf_set_target_10), 150, 75), R.plurals._2130903373_res_0x7f03014d, R.color._2131298866_res_0x7f090a32);
        phi d3 = d(2, nsf.h(R$string.IDS_active_hours), String.format(Locale.ENGLISH, nsf.h(R$string.IDS_three_leaf_set_target_11), 1), R.plurals._2130903374_res_0x7f03014e, R.color._2131298864_res_0x7f090a30);
        d3.dpG_(this.g.getDrawable(R.drawable._2131428368_res_0x7f0b0410));
        d3.d(nsf.h(R$string.IDS_three_leaf_set_target_7));
        d3.c(this.g.getColor(R.color._2131298786_res_0x7f0909e2));
        this.l.add(d);
        this.l.add(d2);
        this.l.add(d3);
        this.n = this.l.get(0);
    }

    private phi d(int i, String str, String str2, int i2, int i3) {
        phi phiVar = new phi();
        phiVar.d(nip.c(i));
        phiVar.b(str);
        phiVar.e(str2);
        phiVar.b(i2);
        phiVar.e(i3);
        phiVar.b(nip.a(i));
        return phiVar;
    }

    private void b() {
        this.k = (LinearLayout) this.r.findViewById(R.id.goal_setting_drag_layout);
        this.c = (RelativeLayout) this.r.findViewById(R.id.goal_setting_drag_view);
        this.o = (ThreeCircleView) this.r.findViewById(R.id.goal_setting_three_circle_view);
        this.m = (TextView) this.r.findViewById(R.id.goal_title_text);
        this.h = (TextView) this.r.findViewById(R.id.goal_description_text);
        this.t = (TextView) this.r.findViewById(R.id.goal_setting_unit);
    }

    private void c() {
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) this.r.findViewById(R.id.goal_setting_number_picker);
        this.j = healthNumberPicker;
        healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: pio
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public final void onValuePicked(int i, int i2) {
                GoalSettingDialog.this.c(i, i2);
            }
        });
    }

    public /* synthetic */ void c(int i, int i2) {
        phi phiVar = this.n;
        phiVar.d(CommonUtil.m(this.g, phiVar.f()[this.j.getValue()]));
        LogUtil.a("GoalSettingDialog", "type now = ", Integer.valueOf(this.f9737a), "; getGoalValue = ", Integer.valueOf(this.n.a()));
    }

    private void e() {
        piq piqVar = new piq();
        final HealthViewPager healthViewPager = new HealthViewPager(this.g);
        healthViewPager.setAdapter(piqVar);
        final HealthDotsPageIndicator healthDotsPageIndicator = (HealthDotsPageIndicator) this.r.findViewById(R.id.goal_setting_indicator);
        healthDotsPageIndicator.setViewPager(healthViewPager);
        HealthButton healthButton = (HealthButton) this.r.findViewById(R.id.goal_setting_button);
        this.d = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.view.GoalSettingDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GoalSettingDialog.this.k();
                if (GoalSettingDialog.this.f9737a < GoalSettingDialog.this.l.size() - 1) {
                    GoalSettingDialog.a(GoalSettingDialog.this);
                    GoalSettingDialog goalSettingDialog = GoalSettingDialog.this;
                    goalSettingDialog.n = (phi) goalSettingDialog.l.get(GoalSettingDialog.this.f9737a);
                    healthViewPager.setCurrentItem(GoalSettingDialog.this.f9737a);
                    healthDotsPageIndicator.onPageSelected(GoalSettingDialog.this.f9737a);
                    GoalSettingDialog goalSettingDialog2 = GoalSettingDialog.this;
                    goalSettingDialog2.c(goalSettingDialog2.f9737a);
                    GoalSettingDialog goalSettingDialog3 = GoalSettingDialog.this;
                    goalSettingDialog3.b(goalSettingDialog3.f9737a);
                    GoalSettingDialog goalSettingDialog4 = GoalSettingDialog.this;
                    goalSettingDialog4.b(2, goalSettingDialog4.f9737a);
                } else {
                    GoalSettingDialog goalSettingDialog5 = GoalSettingDialog.this;
                    goalSettingDialog5.b(3, goalSettingDialog5.f9737a + 1);
                    GoalSettingDialog.this.h();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ImageView imageView = (ImageView) this.r.findViewById(R.id.goal_setting_cancel);
        imageView.setBackground(this.g.getResources().getDrawable(R.drawable._2131427689_res_0x7f0b0169));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.view.GoalSettingDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("GoalSettingDialog", "cancel setting");
                GoalSettingDialog.this.dismiss();
                GoalSettingDialog goalSettingDialog = GoalSettingDialog.this;
                goalSettingDialog.b(1, goalSettingDialog.f9737a + 1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        int a2 = this.n.a();
        LogUtil.a("GoalSettingDialog", "setGoalView. type = ", Integer.valueOf(i), "; goal = ", Integer.valueOf(a2));
        String[] f = this.n.f();
        this.m.setText(this.n.h());
        this.h.setText(this.n.g());
        this.t.setText(this.g.getResources().getQuantityString(this.n.i(), a2, ""));
        this.j.setSelectorPaintColor(ContextCompat.getColor(this.g, this.n.b()));
        this.j.setDisplayedValues(f);
        this.j.setMaxValue(f.length - 1);
        this.j.setMinValue(0);
        int c = nip.c(a2, f);
        LogUtil.a("GoalSettingDialog", "setGoalView position = ", Integer.valueOf(c), "; mPickArray.length = ", Integer.valueOf(f.length));
        this.j.setValue(c);
        this.j.setWrapSelectorWheel(true);
        j();
        a(i);
    }

    private void j() {
        if (this.n.dpF_() != null) {
            this.d.setBackground(this.n.dpF_());
        }
        if (this.n.d() != 0) {
            this.d.setTextColor(this.n.d());
        }
        if (TextUtils.isEmpty(this.n.e())) {
            return;
        }
        this.d.setText(this.n.e());
    }

    private void a(int i) {
        int i2;
        int a2 = this.n.a();
        int i3 = 0;
        if (i == 0) {
            i2 = 0;
        } else {
            if (i != 1) {
                if (i == 2) {
                    i2 = 0;
                    i3 = a2;
                    a2 = 0;
                } else {
                    LogUtil.h("GoalSettingDialog", "updateCircleProgress wrong type. type = ", Integer.valueOf(i));
                    a2 = 0;
                }
            }
            i2 = a2;
            a2 = 0;
        }
        this.o.c("firstCircle", a2, a2);
        this.o.c("secondCircle", i2, i2);
        this.o.c("thirdCircle", i3, i3);
    }

    private void n() {
        c(0);
        b(0);
        LogUtil.a("GoalSettingDialog", "settingDialog start");
        if (LoginInit.getInstance(this.g).isBrowseMode()) {
            LogUtil.a("GoalSettingDialog", "not login, use defaultGoal.");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("900200007");
        arrayList.add("900200008");
        arrayList.add("900200009");
        niw.b(arrayList, 0, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.active.view.GoalSettingDialog.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                Object[] objArr = new Object[2];
                objArr[0] = "settingDialog onResponse: ";
                objArr[1] = obj == null ? null : obj.toString();
                LogUtil.a("GoalSettingDialog", objArr);
                if (!(obj instanceof HashMap)) {
                    LogUtil.h("GoalSettingDialog", "onResponse: objData is not instanceof HashMap");
                    return;
                }
                Map map = (Map) obj;
                int a2 = GoalSettingDialog.this.a(0, "900200007", (Map<String, nir>) map);
                int a3 = GoalSettingDialog.this.a(1, "900200008", (Map<String, nir>) map);
                int a4 = GoalSettingDialog.this.a(2, "900200009", (Map<String, nir>) map);
                ((phi) GoalSettingDialog.this.l.get(0)).d(a2 / 1000);
                ((phi) GoalSettingDialog.this.l.get(1)).d(a3);
                ((phi) GoalSettingDialog.this.l.get(2)).d(a4);
                GoalSettingDialog.this.a(a2, a3, a4);
                HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.active.view.GoalSettingDialog.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GoalSettingDialog.this.c(GoalSettingDialog.this.f9737a);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3) {
        this.e = i / 1000;
        this.i = i2;
        this.b = i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: pis
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                GoalSettingDialog.this.c(i, obj);
            }
        }, "");
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            f();
            ThreeCircleConfigUtil.b(true);
            dismiss();
            return;
        }
        LogUtil.h("GoalSettingDialog", "setGoalAndFinish login fail. ErrorCode = ", Integer.valueOf(i));
    }

    private void f() {
        int a2 = this.l.get(0).a() * 1000;
        int a3 = this.l.get(1).a();
        int a4 = this.l.get(2).a();
        nip.e("900200007", a2);
        nip.e("900200008", a3);
        nip.e("900200009", a4);
        LogUtil.a("GoalSettingDialog", "saveGoal caloricGoal = ", Integer.valueOf(a2), "; intensityGoal = ", Integer.valueOf(a3), "; activityHourGoal = ", Integer.valueOf(a4));
        e(a2, a3, a4);
        b(a2, a3, a4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        pxp.d(7, i != 1 ? i == 2 ? 3 : 4 : 2);
    }

    private void b(int i, int i2, int i3) {
        d(this.e, i / 1000, 4);
        d(this.i, i2, 2);
        d(this.b, i3, 3);
    }

    private void d(int i, int i2, int i3) {
        pxp.c(i3, 7, i == i2 ? 2 : pxp.d(i, i2, i3, 0), i2);
    }

    private void e(int i, int i2, int i3) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("900200007", Integer.valueOf(i));
        hashMap.put("900200008", Integer.valueOf(i2));
        hashMap.put("900200009", Integer.valueOf(i3));
        ObserverManagerUtil.c("GOAL_UPDATE_FROM_DIALOG", hashMap);
    }

    private void i() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, "translationY", 1200.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat);
        animatorSet.setDuration(400L);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, "alpha", 1.0f, 0.1f);
        ofFloat.setDuration(400L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.k, "alpha", 0.1f, 1.0f);
        ofFloat2.setDuration(400L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.start();
    }

    private void o() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (!nsn.ag(this.g)) {
            attributes.width = ScrollUtil.c(this.g);
        }
        attributes.y = 0;
        window.setAttributes(attributes);
        window.setGravity(80);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i, String str, Map<String, nir> map) {
        int c = nip.c(i);
        if (map == null) {
            LogUtil.h("GoalSettingDialog", "goalMap is null, return");
            return c;
        }
        if (map.get(str) != null) {
            int e = map.get(str).e();
            LogUtil.a("GoalSettingDialog", "get recommended type: ,", Integer.valueOf(i), "; goal = ", Integer.valueOf(e));
            return e == 0 ? c : e;
        }
        LogUtil.h("GoalSettingDialog", "fail to get RecommendGoal, type = ", Integer.valueOf(i));
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("page", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_CIRCLE_RING_GUIDE_1040114.value(), hashMap, 0);
    }

    private void g() {
        int i;
        LogUtil.h("GoalSettingDialog", "setCenterCardLayout() ");
        View view = this.r;
        if (view == null) {
            LogUtil.h("GoalSettingDialog", "setCenterCardLayout() mTextContainer is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (nsn.ag(this.g)) {
            HealthColumnSystem healthColumnSystem = new HealthColumnSystem(BaseApplication.getContext());
            i = (int) (healthColumnSystem.c() + healthColumnSystem.g() + healthColumnSystem.a());
            LogUtil.a("GoalSettingDialog", "setCenterCardLayout() healthColumnSystem count = ", Integer.valueOf(healthColumnSystem.f()), " SingleColumnWidth:", Float.valueOf(healthColumnSystem.g()));
            LogUtil.a("GoalSettingDialog", "setCenterCardLayout() marginLeft = ", Integer.valueOf(i));
        } else {
            i = 0;
        }
        if (layoutParams2 != null) {
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            this.r.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog
    public void configDialog() {
        if (this.f) {
            g();
        }
    }
}
