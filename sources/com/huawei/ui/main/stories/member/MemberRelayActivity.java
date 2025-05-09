package com.huawei.ui.main.stories.member;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.stories.member.MemberRelayActivity;
import com.huawei.ui.main.stories.soical.MemberCenterFragment;
import defpackage.ase;
import defpackage.dqj;
import defpackage.gpn;
import defpackage.jdx;
import defpackage.nsa;
import health.compact.a.Services;

/* loaded from: classes.dex */
public class MemberRelayActivity extends BaseActivity {
    private boolean d;
    private Intent e;
    private Fragment f;
    private UserMemberInfo i;
    private int j = 7;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10393a = true;
    private boolean b = false;
    private boolean h = false;
    private boolean c = false;
    private String g = "";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.member_relay_container);
        this.i = gpn.a();
        d();
        b();
    }

    private void d() {
        Intent intent = getIntent();
        this.e = intent;
        String stringExtra = intent.getStringExtra("memberTabRelayUri");
        this.j = this.e.getIntExtra("from", 7);
        e(stringExtra);
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String d = nsa.d(str);
        this.g = d;
        LogUtil.a("MemberRelayActivity", "handleUrl url：", d);
        Uri parse = Uri.parse(d);
        this.h = parse.getBooleanQueryParameter("scrollDown", false);
        this.c = parse.getBooleanQueryParameter("popUpTypeSelect", false);
        String stringExtra = this.e.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM);
        String stringExtra2 = this.e.getStringExtra("fromPageTitle");
        String queryParameter = parse.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = queryParameter;
        }
        if (stringExtra == null) {
            stringExtra = "";
        }
        dqj.g(stringExtra);
        String queryParameter2 = parse.getQueryParameter("fromPageTitle");
        if (TextUtils.isEmpty(stringExtra2)) {
            stringExtra2 = queryParameter2;
        }
        if (stringExtra2 == null) {
            stringExtra2 = "";
        }
        dqj.c(stringExtra2);
        String queryParameter3 = parse.getQueryParameter("pullOrder");
        if (queryParameter3 == null) {
            queryParameter3 = "";
        }
        dqj.o(queryParameter3);
        String queryParameter4 = parse.getQueryParameter("resourceName");
        if (queryParameter4 == null) {
            queryParameter4 = "";
        }
        dqj.q(queryParameter4);
        String queryParameter5 = parse.getQueryParameter("resourceId");
        if (queryParameter5 == null) {
            queryParameter5 = "";
        }
        dqj.l(queryParameter5);
        String queryParameter6 = parse.getQueryParameter("payResourceType");
        if (queryParameter6 == null) {
            queryParameter6 = "";
        }
        dqj.k(queryParameter6);
        String queryParameter7 = parse.getQueryParameter("benefitName");
        dqj.b(queryParameter7 != null ? queryParameter7 : "");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("MemberRelayActivity", "onResume");
        Fragment fragment = this.f;
        if (fragment instanceof MemberIntroFragment) {
            ((MemberIntroFragment) fragment).onVisibilityChange(true);
            ((MemberIntroFragment) this.f).e(this.h);
            ((MemberIntroFragment) this.f).b(this.c);
            ((MemberIntroFragment) this.f).a(this.g);
        }
        Fragment fragment2 = this.f;
        if (fragment2 instanceof MemberCenterFragment) {
            ((MemberCenterFragment) fragment2).onVisibilityChange(true);
            ((MemberCenterFragment) this.f).c(this.c);
            ((MemberCenterFragment) this.f).c(this.g);
        }
        if (!this.f10393a) {
            ObserverManagerUtil.c("REFRESH_MEMBER_INFO_CARD_VIEW", true);
            a();
        }
        this.f10393a = false;
        ObserverManagerUtil.c("MEMBER_PAGE_IS_VISIBLE_TO_USER", true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("MemberRelayActivity", "onActivityResult requestCode: ", Integer.valueOf(i));
        if (intent == null) {
            LogUtil.a("MemberRelayActivity", "onActivityResult data: ", intent);
            return;
        }
        int intExtra = intent.getIntExtra("shoppingResult", -1);
        LogUtil.a("MemberRelayActivity", "onActivityResult shoppingResult: ", Integer.valueOf(intExtra));
        if (intExtra != 0) {
            return;
        }
        ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", true);
        c();
    }

    private void e() {
        dqj.q("");
        dqj.l("");
        dqj.o("");
        dqj.g("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void c() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: riq
                @Override // java.lang.Runnable
                public final void run() {
                    MemberRelayActivity.this.c();
                }
            });
        }
        Fragment fragment = this.f;
        if (fragment != null) {
            a(fragment);
        }
        this.b = true;
        MemberCenterFragment memberCenterFragment = new MemberCenterFragment();
        this.f = memberCenterFragment;
        LogUtil.a("MemberRelayActivity", "switch mVipFragment ", memberCenterFragment.getClass());
        Bundle bundle = new Bundle();
        bundle.putBoolean("IS_SHOW_TITLE_RETURN_BUTTOM", true);
        this.f.setArguments(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.isDestroyed()) {
            return;
        }
        supportFragmentManager.beginTransaction().replace(R.id.member_relay_activity_layout, this.f).commitNowAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.b) {
            this.b = false;
            return;
        }
        boolean b = b(this.i);
        Fragment fragment = this.f;
        if (fragment != null) {
            a(fragment);
        }
        boolean c = ase.c();
        this.d = c;
        Fragment memberCenterFragment = (b || c) ? new MemberCenterFragment() : new MemberIntroFragment();
        this.f = memberCenterFragment;
        LogUtil.a("MemberRelayActivity", "initFragment ", memberCenterFragment.getClass());
        i();
        h();
        Bundle bundle = new Bundle();
        bundle.putBoolean("IS_SHOW_TITLE_RETURN_BUTTOM", true);
        this.f.setArguments(bundle);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.isDestroyed()) {
            LogUtil.b("MemberRelayActivity", "initFragment fm is destroyed");
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.member_relay_activity_layout, this.f).commitNowAllowingStateLoss();
        }
    }

    private void a(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.isDestroyed()) {
            return;
        }
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.remove(fragment);
        beginTransaction.commitAllowingStateLoss();
    }

    private void i() {
        Fragment fragment;
        UserMemberInfo userMemberInfo = this.i;
        if (userMemberInfo == null || (fragment = this.f) == null || !(fragment instanceof MemberIntroFragment)) {
            return;
        }
        if (userMemberInfo.getMemberFlag() == -1) {
            ((MemberIntroFragment) this.f).d(0);
        } else {
            if (this.i.getNowTime() <= this.i.getExpireTime() || this.d) {
                return;
            }
            ((MemberIntroFragment) this.f).d(2);
        }
    }

    private void h() {
        Fragment fragment;
        UserMemberInfo userMemberInfo = this.i;
        if (userMemberInfo == null || (fragment = this.f) == null || !(fragment instanceof MemberCenterFragment)) {
            return;
        }
        if (userMemberInfo.getAutoRenew() == 1) {
            ((MemberCenterFragment) this.f).a(0);
        } else {
            ((MemberCenterFragment) this.f).a(1);
        }
    }

    private boolean b(Object obj) {
        if (!(obj instanceof UserMemberInfo)) {
            return false;
        }
        UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
        return userMemberInfo.getMemberFlag() == 1 && !gpn.d(userMemberInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("MemberRelayActivity", "checkVipUser");
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.member.MemberRelayActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    MemberRelayActivity.this.a();
                }
            });
        } else {
            ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new AnonymousClass1());
        }
    }

    /* renamed from: com.huawei.ui.main.stories.member.MemberRelayActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements VipCallback {
        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
        }

        AnonymousClass1() {
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            LogUtil.a("MemberRelayActivity", "checkVipUser onSuccess: mUserMemberInfo ", MemberRelayActivity.this.i, " result ", obj);
            if (obj instanceof UserMemberInfo) {
                ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", obj);
                if (MemberRelayActivity.this.i == null || MemberRelayActivity.this.d(obj)) {
                    MemberRelayActivity.this.i = (UserMemberInfo) obj;
                    LogUtil.a("MemberRelayActivity", "checkVipUser refresh");
                    HandlerExecutor.e(new Runnable() { // from class: rit
                        @Override // java.lang.Runnable
                        public final void run() {
                            MemberRelayActivity.AnonymousClass1.this.d();
                        }
                    });
                }
            }
        }

        public /* synthetic */ void d() {
            MemberRelayActivity.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Object obj) {
        if ((this.f instanceof MemberIntroFragment) && b(obj)) {
            return true;
        }
        return (!(this.f instanceof MemberCenterFragment) || b(obj) || this.d) ? false : true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
