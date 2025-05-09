package com.huawei.health.suggestion.ui.run.activity.fragment.runplan;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.asc;
import defpackage.ffy;
import defpackage.gdk;
import defpackage.gdr;
import defpackage.glz;
import defpackage.gmc;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes4.dex */
public class UserInfoFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f3344a;
    private HealthProgressBar aa;
    private HealthButton b;
    private HealthTextView c;
    private int d;
    private int e;
    private Context f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthColumnRelativeLayout k;
    private String l;
    private HealthColumnRelativeLayout n;
    private RelativeLayout o;
    private HealthColumnLinearLayout p;
    private HealthColumnRelativeLayout q;
    private RunPlanCreateActivity.OnNextPageListener s;
    private HealthColumnRelativeLayout t;
    private UserInfomation u;
    private HealthTextView v;
    private HealthTextView w;
    private UserProfileMgrApi x;
    private ImageView z;
    private List<View> j = new ArrayList();
    private List<View> i = new ArrayList();
    private c r = new c(this);
    private boolean m = false;
    private Handler y = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.UserInfoFragment.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                UserInfoFragment.this.j();
            }
        }
    };

    public static UserInfoFragment d() {
        return new UserInfoFragment();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.x = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f = getContext();
        View inflate = layoutInflater.inflate(R.layout.sug_frag_user_info, viewGroup, false);
        aKS_(inflate);
        e();
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.activity.fragment.runplan.UserInfoFragment.3
            @Override // java.lang.Runnable
            public void run() {
                if (UserInfoFragment.this.x == null) {
                    LogUtil.h("Suggestion_UserInfoFragment", "getWeight : mUserProfileMgrApi is null.");
                    return;
                }
                UserInfoFragment userInfoFragment = UserInfoFragment.this;
                userInfoFragment.u = userInfoFragment.x.getUserInfo();
                UserInfoFragment.this.y.sendMessage(UserInfoFragment.this.y.obtainMessage(0));
            }
        });
        return inflate;
    }

    private void aKS_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_UserInfoFragment", "initLayout view is null.");
            return;
        }
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.layout_tips);
        this.aa = (HealthProgressBar) healthColumnRelativeLayout.findViewById(R.id.tips_image_progress);
        this.z = (ImageView) healthColumnRelativeLayout.findViewById(R.id.tips_image);
        this.o = (RelativeLayout) healthColumnRelativeLayout.findViewById(R.id.layout_bubbles);
        HealthTextView healthTextView = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_question);
        healthTextView.setText(R.string._2130848585_res_0x7f022b49);
        this.j.add(healthTextView);
        this.p = (HealthColumnLinearLayout) healthColumnRelativeLayout.findViewById(R.id.layout_summary);
        this.v = (HealthTextView) healthColumnRelativeLayout.findViewById(R.id.tips_summary);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.card_view_info);
        this.j.add(healthCardView);
        this.i.add(healthCardView);
        BaseActivity.setViewSafeRegion(true, healthCardView);
        HealthColumnRelativeLayout healthColumnRelativeLayout2 = (HealthColumnRelativeLayout) view.findViewById(R.id.user_gender);
        this.k = healthColumnRelativeLayout2;
        ((HealthTextView) healthColumnRelativeLayout2.findViewById(R.id.tv_key)).setText(R.string._2130837629_res_0x7f02007d);
        this.h = (HealthTextView) this.k.findViewById(R.id.tv_value);
        HealthColumnRelativeLayout healthColumnRelativeLayout3 = (HealthColumnRelativeLayout) view.findViewById(R.id.user_birth_date);
        this.n = healthColumnRelativeLayout3;
        ((HealthTextView) healthColumnRelativeLayout3.findViewById(R.id.tv_key)).setText(R.string._2130837634_res_0x7f020082);
        this.c = (HealthTextView) this.n.findViewById(R.id.tv_value);
        HealthColumnRelativeLayout healthColumnRelativeLayout4 = (HealthColumnRelativeLayout) view.findViewById(R.id.user_height);
        this.t = healthColumnRelativeLayout4;
        ((HealthTextView) healthColumnRelativeLayout4.findViewById(R.id.tv_key)).setText(R.string._2130841526_res_0x7f020fb6);
        this.g = (HealthTextView) this.t.findViewById(R.id.tv_value);
        HealthColumnRelativeLayout healthColumnRelativeLayout5 = (HealthColumnRelativeLayout) view.findViewById(R.id.user_weight);
        this.q = healthColumnRelativeLayout5;
        ((HealthTextView) healthColumnRelativeLayout5.findViewById(R.id.tv_key)).setText(R.string._2130841529_res_0x7f020fb9);
        this.w = (HealthTextView) this.q.findViewById(R.id.tv_value);
        if (LanguageUtil.bc(this.f)) {
            Drawable drawable = ContextCompat.getDrawable(this.f, R.drawable._2131427841_res_0x7f0b0201);
            this.k.findViewById(R.id.user_info_image_view).setBackground(drawable);
            this.n.findViewById(R.id.user_info_image_view).setBackground(drawable);
            this.t.findViewById(R.id.user_info_image_view).setBackground(drawable);
            this.q.findViewById(R.id.user_info_image_view).setBackground(drawable);
        }
        gdk.aLu_(view, healthColumnRelativeLayout, healthCardView, (RelativeLayout) view.findViewById(R.id.sug_layout_btn), 0);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.sug_btn_next);
        this.b = healthButton;
        this.j.add(healthButton);
        this.i.add(this.b);
        gdk.b(true, this.j);
        gdr.aLy_(this.f, 5, this.aa, this.z);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            LogUtil.a("Suggestion_UserInfoFragment", "isHidden fragment.");
        } else {
            this.o.setVisibility(0);
            gdk.b(true, this.j);
        }
    }

    private void e() {
        this.k.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.m) {
            this.m = false;
            if (c()) {
                ThirdPartyLoginManager.getInstance().queryUserInfo(new b(this), false);
                glz.b("UserInfoFragment");
            } else {
                this.x.sync(new e(this));
            }
        }
    }

    static class e implements CommonCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<UserInfoFragment> f3345a;

        e(UserInfoFragment userInfoFragment) {
            this.f3345a = new WeakReference<>(userInfoFragment);
        }

        @Override // com.huawei.up.callback.CommonCallback
        public void onSuccess(Bundle bundle) {
            UserInfoFragment userInfoFragment = this.f3345a.get();
            if (userInfoFragment == null) {
                LogUtil.b("Suggestion_UserInfoFragment", "SynchronousDataCallback fragment is null.");
                return;
            }
            LogUtil.a("Suggestion_UserInfoFragment", "hw sync userinfo success");
            if (userInfoFragment.y == null || userInfoFragment.x == null) {
                return;
            }
            userInfoFragment.u = userInfoFragment.x.getUserInfo();
            userInfoFragment.y.sendEmptyMessage(0);
        }

        @Override // com.huawei.up.callback.CommonCallback
        public void onFail(int i) {
            LogUtil.a("Suggestion_UserInfoFragment", "hw sync userinfo fail:", Integer.valueOf(i));
        }
    }

    static class b implements IBaseResponseCallback {
        WeakReference<UserInfoFragment> c;

        b(UserInfoFragment userInfoFragment) {
            this.c = new WeakReference<>(userInfoFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            UserInfoFragment userInfoFragment = this.c.get();
            if (userInfoFragment != null) {
                if (userInfoFragment.y == null || userInfoFragment.x == null || i != 0) {
                    return;
                }
                userInfoFragment.u = userInfoFragment.x.getUserInfo();
                userInfoFragment.y.sendEmptyMessage(0);
                return;
            }
            LogUtil.b("Suggestion_UserInfoFragment", "QueryUserInfoCallback fragment is null.");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        FragmentActivity activity = getActivity();
        if (activity == null || nsn.a(500)) {
            LogUtil.h("Suggestion_UserInfoFragment", "onClick activity is null or click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        RunPlanCreateActivity.OnNextPageListener onNextPageListener = this.s;
        if (onNextPageListener == null) {
            activity.finish();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.b) {
            onNextPageListener.backLock();
            gdk.b(false, this.i);
            g();
            gdk.aLq_(this.o, this.p, this.s, true);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.k) {
            f();
        } else if (view == this.n) {
            h();
        } else {
            aKR_(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aKR_(View view) {
        if (view == null || this.u == null) {
            LogUtil.h("Suggestion_UserInfoFragment", "clickHeightOrWeight view or mUserInfo is null.");
            return;
        }
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.loadAccountExtData(this.u);
        if (view == this.t) {
            gmc.aPb_(getActivity(), userInfomation, this.r);
        } else if (view == this.q) {
            gmc.aPd_(getActivity(), userInfomation, this.r);
        } else {
            LogUtil.b("Suggestion_UserInfoFragment", "LayoutClickListener view not define.");
        }
    }

    private void f() {
        if (i()) {
            this.m = true;
            if (c()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo(getActivity(), null);
                return;
            } else {
                gmc.aPf_(getActivity());
                return;
            }
        }
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.loadAccountData(this.u);
        gmc.aPa_(getActivity(), 20, userInfomation, this.r);
    }

    private void h() {
        if (i()) {
            this.m = true;
            if (c()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo(getActivity(), null);
                return;
            } else {
                gmc.aPf_(getActivity());
                return;
            }
        }
        gmc.aOZ_(getActivity(), this.f3344a, this.e, this.d, this.r);
    }

    private boolean i() {
        if (TextUtils.isEmpty(this.l)) {
            this.l = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN);
        }
        return !"1".equals(this.l);
    }

    private boolean c() {
        return CommonUtil.z(getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        if (f < 10.0f || f > 250.0f) {
            LogUtil.b("Suggestion_UserInfoFragment", "updateWeight weight is invalid.");
            f = 60.0f;
        }
        String d2 = gmc.d(this.f, f);
        this.u.setWeight(f);
        this.w.setText(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i < 50 || i > 250) {
            LogUtil.b("Suggestion_UserInfoFragment", "updateHeight height is invalid.");
            i = 170;
        }
        String b2 = gmc.b(this.f, i);
        this.u.setHeight(i);
        this.g.setText(b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        String c2 = gmc.c(this.f, str);
        if (e(nsn.e(str))) {
            nrh.c(this.f.getApplicationContext(), this.f.getResources().getString(R.string._2130837709_res_0x7f0200cd));
        } else {
            this.u.setBirthday(str);
            this.c.setText(c2);
        }
    }

    private boolean e(int i) {
        Calendar calendar = Calendar.getInstance();
        this.f3344a = i / 10000;
        int i2 = calendar.get(1);
        if (this.f3344a > i2) {
            return true;
        }
        this.e = (i % 10000) / 100;
        int i3 = calendar.get(2);
        if (this.f3344a == i2 && this.e > i3) {
            return true;
        }
        this.d = i % 100;
        return this.f3344a == i2 && this.e == i3 && this.d > calendar.get(5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 0) {
            this.h.setText(R.string._2130837640_res_0x7f020088);
        } else if (i == 1) {
            this.h.setText(R.string._2130837639_res_0x7f020087);
        } else {
            this.h.setText(R.string._2130837846_res_0x7f020156);
        }
        this.u.setGender(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        UserInfomation userInfomation = this.u;
        if (userInfomation == null) {
            LogUtil.b("Suggestion_UserInfoFragment", "initUserInfo mUserInfo is null.");
            return;
        }
        if (!userInfomation.isGenderValid()) {
            this.u.setGender(2);
        }
        a(this.u.getGender());
        c(this.u.getBirthday());
        b(this.u.getHeight());
        a(this.u.getWeight());
    }

    static class c implements IBaseResponseCallback {
        WeakReference<UserInfoFragment> d;

        c(UserInfoFragment userInfoFragment) {
            this.d = new WeakReference<>(userInfoFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            UserInfoFragment userInfoFragment = this.d.get();
            if (userInfoFragment == null) {
                LogUtil.h("Suggestion_UserInfoFragment", "ShowSetValueDialogListener fragment is null.");
                return;
            }
            if (i == -1) {
                gmc.a(userInfoFragment.f);
                return;
            }
            if (!(obj instanceof UserInfomation)) {
                LogUtil.h("Suggestion_UserInfoFragment", "ShowSetValueDialogListener objData is invalid.");
                return;
            }
            UserInfomation userInfomation = (UserInfomation) obj;
            if (i == 20) {
                userInfoFragment.a(gdr.c(userInfomation.getGenderOrDefaultValue()));
                return;
            }
            if (i == 3) {
                userInfoFragment.c(userInfomation.getBirthday());
                return;
            }
            if (i == 0) {
                if (UnitUtil.h()) {
                    userInfoFragment.b(userInfomation.getHeight());
                } else {
                    userInfoFragment.b(userInfomation.getHeight() + 50);
                }
                userInfoFragment.b();
                return;
            }
            if (i == 1) {
                if (UnitUtil.h()) {
                    userInfoFragment.a((float) UnitUtil.i(userInfomation.getWeight() + 22.0f));
                } else {
                    userInfoFragment.a(userInfomation.getWeight() + 10.0f);
                }
                userInfoFragment.b();
                return;
            }
            LogUtil.h("Suggestion_UserInfoFragment", "errorCode not define");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.x.setUserInfo(this.u, new d());
    }

    static class d implements ICloudOperationResult<Boolean> {
        d() {
        }

        @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void operationResult(Boolean bool, String str, boolean z) {
            LogUtil.a("Suggestion_UserInfoFragment", "object = ", bool, ", text = ", str, ", isSuccess = ", Boolean.valueOf(z));
        }
    }

    private void g() {
        this.v.setText(ffy.d(this.f, R.string._2130848581_res_0x7f022b45, this.h.getText(), this.c.getText(), this.g.getText(), this.w.getText()));
    }

    public UserInfomation a() {
        return this.u;
    }

    public void b(RunPlanCreateActivity.OnNextPageListener onNextPageListener) {
        this.s = onNextPageListener;
    }
}
