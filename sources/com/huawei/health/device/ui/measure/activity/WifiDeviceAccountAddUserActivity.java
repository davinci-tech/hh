package com.huawei.health.device.ui.measure.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.activity.WifiDeviceAccountAddUserActivity;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.AuthorizeSubUserInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceShareByMainUserReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.edittext.HealthIconTextLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.api.UpApi;
import defpackage.crx;
import defpackage.ctq;
import defpackage.exh;
import defpackage.jbs;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class WifiDeviceAccountAddUserActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f2237a;
    private HealthButton b;
    private View d;
    private HealthIconTextLayout e;
    private CommonDialog21 g;
    private LinearLayout i;
    private ImageView k;
    private LinearLayout l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private crx p;
    private c s;
    private CustomTitleBar t;
    private int c = 9;
    private String j = "";
    private boolean h = true;
    private int r = 0;
    private ViewTreeObserver.OnGlobalLayoutListener f = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: cly
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            WifiDeviceAccountAddUserActivity.this.b();
        }
    };

    static class c extends StaticHandler<WifiDeviceAccountAddUserActivity> {
        c(WifiDeviceAccountAddUserActivity wifiDeviceAccountAddUserActivity) {
            super(wifiDeviceAccountAddUserActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: HZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(WifiDeviceAccountAddUserActivity wifiDeviceAccountAddUserActivity, Message message) {
            if (wifiDeviceAccountAddUserActivity == null || wifiDeviceAccountAddUserActivity.isDestroyed() || wifiDeviceAccountAddUserActivity.isFinishing()) {
                LogUtil.h("WifiDeviceAccountAddUserActivity", "UiHandler activity is dead");
                return;
            }
            if (message == null) {
                LogUtil.h("WifiDeviceAccountAddUserActivity", "UiHandler msg is null");
            } else if (message.what == 1) {
                wifiDeviceAccountAddUserActivity.e();
                wifiDeviceAccountAddUserActivity.j();
            } else {
                LogUtil.h("WifiDeviceAccountAddUserActivity", "UiHandler what is other");
            }
        }
    }

    public /* synthetic */ void b() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int height = rect.height();
        int i = this.r;
        if (i == 0) {
            this.r = height;
            return;
        }
        if (i != height) {
            int i2 = i - height;
            this.r = height;
            this.d.setVisibility(0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1, i2);
            layoutParams.height = i2;
            this.d.setLayoutParams(layoutParams);
            if (i2 <= 0) {
                this.o.setVisibility(0);
            } else {
                this.o.setVisibility(8);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_account_adduser);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.f);
        this.f2237a = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getStringExtra("deviceId");
        }
        this.s = new c(this);
        d();
        c();
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.my_qrcode_title_layout);
        this.t = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: clq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDeviceAccountAddUserActivity.this.HX_(view);
            }
        });
        HealthIconTextLayout healthIconTextLayout = (HealthIconTextLayout) findViewById(R.id.hw_device_adduser_edit_layout);
        this.e = healthIconTextLayout;
        healthIconTextLayout.getEditText().setSingleLine();
        this.e.getImageView().setVisibility(8);
        this.e.setOnIconClickListener(new View.OnClickListener() { // from class: clr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDeviceAccountAddUserActivity.this.HY_(view);
            }
        });
        this.k = (ImageView) findViewById(R.id.hw_share_sub_head);
        this.n = (HealthTextView) findViewById(R.id.hw_share_sub_nickname);
        this.m = (HealthTextView) findViewById(R.id.hw_share_sub_id);
        this.b = (HealthButton) findViewById(R.id.hw_device_share_button);
        this.l = (LinearLayout) findViewById(R.id.hw_share_search);
        this.i = (LinearLayout) findViewById(R.id.hw_share_result);
        this.o = (HealthTextView) findViewById(R.id.hw_device_adduser_title);
        this.d = findViewById(R.id.hw_device_bottom_view_gap);
        this.b.setOnClickListener(this);
    }

    public /* synthetic */ void HX_(View view) {
        LogUtil.a("WifiDeviceAccountAddUserActivity", "mTitleBar leftButton onClick");
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void HY_(View view) {
        this.e.getEditText().setText("");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        this.e.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceAccountAddUserActivity.5
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                int length = charSequence.length();
                LogUtil.a("WifiDeviceAccountAddUserActivity", "onTextChanged start:", Integer.valueOf(i), ",content length:", Integer.valueOf(length));
                if (length > 1) {
                    WifiDeviceAccountAddUserActivity.this.b.setEnabled(true);
                    WifiDeviceAccountAddUserActivity.this.e.getImageView().setVisibility(0);
                } else {
                    WifiDeviceAccountAddUserActivity.this.e.getImageView().setVisibility(8);
                    WifiDeviceAccountAddUserActivity.this.b.setEnabled(false);
                }
            }
        });
        this.e.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cll
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                WifiDeviceAccountAddUserActivity.this.HW_(view, z);
            }
        });
    }

    public /* synthetic */ void HW_(View view, boolean z) {
        if (z) {
            this.o.setVisibility(8);
        } else {
            this.o.setVisibility(0);
        }
        ViewScrollInstrumentation.focusChangeOnView(view, z);
    }

    private void d(String str) {
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("WifiDeviceAccountAddUserActivity", "showLoadDataDialog error: activity is finishing");
            return;
        }
        if (this.g == null) {
            new CommonDialog21(this, R.style.app_update_dialogActivity);
            this.g = CommonDialog21.a(this);
        }
        this.g.setCancelable(false);
        this.g.e(str);
        this.g.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CommonDialog21 commonDialog21 = this.g;
        boolean z = commonDialog21 != null && commonDialog21.isShowing();
        if (isFinishing() || !z) {
            return;
        }
        this.g.dismiss();
        this.g = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        nrh.b(this.f2237a, R.string.IDS_device_share_fail);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.hw_device_share_button) {
            if (nsn.a(1500)) {
                LogUtil.h("WifiDeviceAccountAddUserActivity", "click too fast return.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.a("WifiDeviceAccountAddUserActivity", "Click the invite button.", " mIsHideResult: ", Boolean.valueOf(this.h));
            if (this.h) {
                c(this.e.getEditText().getText().toString());
            } else {
                WifiDeviceGetAuthorizeSubUserReq wifiDeviceGetAuthorizeSubUserReq = new WifiDeviceGetAuthorizeSubUserReq();
                wifiDeviceGetAuthorizeSubUserReq.setDevId(this.j);
                jbs.a(this.f2237a).a(wifiDeviceGetAuthorizeSubUserReq, new ICloudOperationResult() { // from class: clu
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    public final void operationResult(Object obj, String str, boolean z) {
                        WifiDeviceAccountAddUserActivity.this.d((WifiDeviceGetAuthorizeSubUserRsp) obj, str, z);
                    }
                });
            }
        } else {
            LogUtil.a("WifiDeviceAccountAddUserActivity", "onClick is nothing");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void d(WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp, String str, boolean z) {
        if (z) {
            List<AuthorizeSubUserInfo> authorizeSubUserList = wifiDeviceGetAuthorizeSubUserRsp.getAuthorizeSubUserList();
            LogUtil.a("WifiDeviceAccountAddUserActivity", "subUserList size: ", Integer.valueOf(authorizeSubUserList.size()));
            if (authorizeSubUserList == null) {
                LogUtil.h("WifiDeviceAccountAddUserActivity", "subUserList is null.");
            }
            if (authorizeSubUserList != null && authorizeSubUserList.size() == this.c) {
                nrh.e(this.f2237a, R.string.IDS_device_wifi_my_qrcode_add_member_exceeding_limit);
            } else {
                i();
            }
        }
    }

    private void c(String str) {
        if (TextUtils.isEmpty(str)) {
            nrh.b(this.f2237a, R.string.IDS_device_share_account_fail);
            return;
        }
        d(getResources().getString(R.string._2130841415_res_0x7f020f47));
        if (this.s == null) {
            this.s = new c(this);
        }
        this.s.removeMessages(1);
        this.s.sendEmptyMessageDelayed(1, 5000L);
        ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).requestFindUserInfo(0, str, new UserInfoCallback<exh.b>() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceAccountAddUserActivity.4
            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void infoCallback(exh.b bVar) {
                WifiDeviceAccountAddUserActivity.this.s.removeMessages(1);
                WifiDeviceAccountAddUserActivity.this.e();
                if (bVar == null) {
                    nrh.c(WifiDeviceAccountAddUserActivity.this.f2237a, R.string.IDS_device_share_user_fail);
                } else {
                    LogUtil.a("WifiDeviceAccountAddUserActivity", "getUserHuid searchHmsUserData userId:", Long.valueOf(bVar.a()));
                    WifiDeviceAccountAddUserActivity.this.d(bVar);
                }
            }

            @Override // com.huawei.health.pluginhealthzone.UserInfoCallback
            public void errorCallback(int i) {
                LogUtil.h("WifiDeviceAccountAddUserActivity", "getUserHuid service errorCode: ", Integer.valueOf(i));
                WifiDeviceAccountAddUserActivity.this.s.removeMessages(1);
                WifiDeviceAccountAddUserActivity.this.e();
                if (i == 3009 || i == 3010) {
                    nrh.c(WifiDeviceAccountAddUserActivity.this.f2237a, R.string.IDS_device_share_user_fail);
                    return;
                }
                if (i == 6006) {
                    nrh.c(WifiDeviceAccountAddUserActivity.this.f2237a, R.string.IDS_device_share_fail);
                    return;
                }
                if (i == 6005) {
                    LogUtil.h("WifiDeviceAccountAddUserActivity", "HMS Core is not installed.");
                    nrh.c(WifiDeviceAccountAddUserActivity.this.f2237a, R.string.IDS_device_share_fail);
                } else if (i != 1000) {
                    nrh.c(WifiDeviceAccountAddUserActivity.this.f2237a, R.string.IDS_device_share_fail);
                } else {
                    LogUtil.h("WifiDeviceAccountAddUserActivity", "HMS Core is not login again.");
                    nrh.c(BaseApplication.getContext(), R.string._2130844050_res_0x7f021992);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final exh.b bVar) {
        LogUtil.h("WifiDeviceAccountAddUserActivity", "getManagerInfo is starting。。。");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("WifiDeviceAccountAddUserActivity", "getManagerInfo userId is null");
            return;
        }
        if (accountInfo.equals(String.valueOf(bVar.a()))) {
            nrh.b(this.f2237a, R.string.IDS_device_share_self);
            e();
            return;
        }
        if (bVar == null) {
            nrh.b(this.f2237a, R.string.IDS_device_hagrid_loading_info_failed_prompt);
            LogUtil.a("WifiDeviceAccountAddUserActivity", "getManagerInfo info is null");
            e();
            return;
        }
        LogUtil.a("WifiDeviceAccountAddUserActivity", "getManagerInfo info is not null");
        LogUtil.c("WifiDeviceAccountAddUserActivity", "getManagerInfo info : ", bVar.toString());
        this.h = false;
        crx crxVar = new crx();
        this.p = crxVar;
        crxVar.e(String.valueOf(bVar.a()));
        LogUtil.a("WifiDeviceAccountAddUserActivity", "userDataResult user id: ", String.valueOf(bVar.a()));
        runOnUiThread(new Runnable() { // from class: cls
            @Override // java.lang.Runnable
            public final void run() {
                WifiDeviceAccountAddUserActivity.this.a(bVar);
            }
        });
        e();
    }

    public /* synthetic */ void a(exh.b bVar) {
        this.l.setVisibility(8);
        this.i.setVisibility(0);
        nsn.cLw_(this.k, bVar.e(), bVar.a());
        String d = bVar.d();
        String handleAccount = new UpApi(this.f2237a).handleAccount(this.e.getEditText().getText().toString());
        HealthTextView healthTextView = this.n;
        if (TextUtils.isEmpty(d)) {
            d = handleAccount;
        }
        healthTextView.setText(d);
        this.p.d(this.n.getText().toString());
        this.m.setText(handleAccount);
        this.b.setText(R.string.IDS_device_share_invite);
        this.t.setTitleText(getString(R.string.IDS_device_share_user_detail));
    }

    private boolean a() {
        LogUtil.a("WifiDeviceAccountAddUserActivity", "checkCurrentUserIsSubUser mDeviceId starting");
        if (ctq.a(this.j) == null) {
            LogUtil.h("WifiDeviceAccountAddUserActivity", "checkCurrentUserIsSubUser this device have no subUser");
            return false;
        }
        ArrayList<crx> b = ctq.a(this.j).b();
        if (b == null || b.size() <= 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList(b.size());
        Iterator<crx> it = b.iterator();
        while (it.hasNext()) {
            crx next = it.next();
            LogUtil.h("WifiDeviceAccountAddUserActivity", "checkCurrentUserIsSubUser subUser.getNickName() = ", next.d());
            arrayList.add(next.e());
        }
        if (!arrayList.contains(this.p.e())) {
            return false;
        }
        LogUtil.h("WifiDeviceAccountAddUserActivity", "checkCurrentUserIsSubUser have auth user > ", Integer.valueOf(R.string.IDS_device_hygride_auth_user));
        return true;
    }

    private void i() {
        LogUtil.h("WifiDeviceAccountAddUserActivity", "shareToSubUser starting");
        WifiDeviceShareByMainUserReq wifiDeviceShareByMainUserReq = new WifiDeviceShareByMainUserReq();
        if (a()) {
            LogUtil.h("WifiDeviceAccountAddUserActivity", "shareToSubUser is wifi subUser");
            nrh.b(this.f2237a, R.string.IDS_device_hygride_auth_user);
            return;
        }
        wifiDeviceShareByMainUserReq.setDevId(this.j);
        wifiDeviceShareByMainUserReq.setSubHuid(this.p.e());
        String legalAccountName = new UpApi(this.f2237a).getLegalAccountName();
        if (TextUtils.isEmpty(legalAccountName)) {
            legalAccountName = this.p.d();
        }
        wifiDeviceShareByMainUserReq.setNickname(legalAccountName);
        wifiDeviceShareByMainUserReq.setExpireTime(3600);
        jbs.a(this).a(wifiDeviceShareByMainUserReq, new ICloudOperationResult() { // from class: clt
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDeviceAccountAddUserActivity.this.a((CloudCommonReponse) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (z) {
            nrh.b(this.f2237a, R.string.IDS_device_share_wait);
            finish();
            return;
        }
        LogUtil.h("WifiDeviceAccountAddUserActivity", "shareToSubUser fail");
        if (cloudCommonReponse != null && cloudCommonReponse.getResultCode().intValue() == 112000090) {
            nrh.b(this.f2237a, R.string.IDS_device_share_repet);
        } else if (cloudCommonReponse != null && cloudCommonReponse.getResultCode().intValue() == 112000020) {
            nrh.b(this.f2237a, R.string.IDS_device_hygride_auth_user);
        } else {
            nrh.b(this.f2237a, R.string.IDS_device_share_fail);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CommonDialog21 commonDialog21 = this.g;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.g.dismiss();
        this.g = null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("WifiDeviceAccountAddUserActivity", "onBackPressed");
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
