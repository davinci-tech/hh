package com.huawei.health.device.ui.measure.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity;
import com.huawei.health.device.ui.measure.adapter.WifiDeviceShareAdapter;
import com.huawei.health.device.ui.measure.adapter.WifiDeviceShareMemberInfoAdapter;
import com.huawei.health.device.ui.measure.view.NoScrollListView;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceShareMemberInfoBySubUserReq;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.cfi;
import defpackage.cnw;
import defpackage.cpa;
import defpackage.cpw;
import defpackage.crw;
import defpackage.crx;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctq;
import defpackage.ctv;
import defpackage.cud;
import defpackage.ixx;
import defpackage.jbs;
import defpackage.koq;
import defpackage.nmn;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class WifiDeviceShareActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2244a;
    private CustomTitleBar aa;
    private CustomTextAlertDialog ac;
    private String ad;
    private Context b;
    private HealthSubHeader d;
    private WifiDeviceShareAdapter e;
    private ImageView f;
    private e g;
    private c h;
    private CommonDialog21 k;
    private ImageView l;
    private HealthTextView m;
    private HealthTextView n;
    private WifiDeviceShareMemberInfoAdapter o;
    private RelativeLayout p;
    private NoTitleCustomAlertDialog q;
    private CustomTextAlertDialog r;
    private NoScrollListView u;
    private HealthTextView w;
    private LinearLayout y;
    private List<crx> x = new ArrayList(16);
    private List<cnw.d> v = new ArrayList(16);
    private String i = "";
    private String s = "";
    private String t = "-1";
    private boolean c = false;
    private boolean j = false;

    static class c extends StaticHandler<WifiDeviceShareActivity> {
        c(WifiDeviceShareActivity wifiDeviceShareActivity) {
            super(wifiDeviceShareActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: Ip_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(WifiDeviceShareActivity wifiDeviceShareActivity, Message message) {
            if (wifiDeviceShareActivity == null) {
                cpw.a(false, "WifiDeviceShareActivity", "activity is null");
            }
            if (wifiDeviceShareActivity.isDestroyed() || wifiDeviceShareActivity.isFinishing()) {
                LogUtil.b("WifiDeviceShareActivity", "activity is Destroyed/Finishing");
                return;
            }
            if (message == null) {
                cpw.a(false, "WifiDeviceShareActivity", "msg is null");
                return;
            }
            cpw.a(false, "WifiDeviceShareActivity", "MyHandler what:", Integer.valueOf(message.what));
            switch (message.what) {
                case 1001:
                    wifiDeviceShareActivity.f();
                    wifiDeviceShareActivity.o();
                    break;
                case 1002:
                    wifiDeviceShareActivity.f();
                    wifiDeviceShareActivity.c();
                    break;
                case 1003:
                    wifiDeviceShareActivity.c(wifiDeviceShareActivity.b.getResources().getString(R.string._2130841415_res_0x7f020f47));
                    break;
                case 1004:
                    wifiDeviceShareActivity.f();
                    wifiDeviceShareActivity.h();
                    break;
                case 1005:
                    wifiDeviceShareActivity.b(message.arg1);
                    break;
                default:
                    cpw.d(false, "MyHandler what is other", new Object[0]);
                    break;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cpw.a(false, "WifiDeviceShareActivity", "onCreate");
        setContentView(R.layout.wifi_device_share_layout);
        this.b = this;
        this.h = new c(this);
        e eVar = new e(this);
        this.g = eVar;
        EventBus.d(eVar, 2, "sub_user_unauthorize_notification", "multi_user_auto_cancle_dialog");
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("deviceId")) {
                this.i = intent.getStringExtra("deviceId");
            }
            if (intent.hasExtra(ArkUIXConstants.FROM_TYPE) && "push".equals(intent.getStringExtra(ArkUIXConstants.FROM_TYPE))) {
                this.s = ctq.i(this.i);
            }
            if (intent.hasExtra("productId")) {
                this.s = intent.getStringExtra("productId");
            }
            if (TextUtils.isEmpty(this.s) && !TextUtils.isEmpty(this.i)) {
                this.s = ctq.i(this.i);
            }
            this.j = intent.getBooleanExtra("isSubUser", false);
        }
        k();
        g();
        i();
        h();
    }

    private void k() {
        this.l = (ImageView) findViewById(R.id.share_member_header_img);
        this.n = (HealthTextView) findViewById(R.id.share_member_header_title_tv);
        this.m = (HealthTextView) findViewById(R.id.share_member_sub_title_tv);
        this.u = (NoScrollListView) findViewById(R.id.wifi_device_share_member_view);
        this.f = (ImageView) findViewById(R.id.device_share_tint);
        if (!this.j) {
            WifiDeviceShareAdapter wifiDeviceShareAdapter = new WifiDeviceShareAdapter(this.b, this.x);
            this.e = wifiDeviceShareAdapter;
            this.u.setAdapter((ListAdapter) wifiDeviceShareAdapter);
        } else {
            WifiDeviceShareMemberInfoAdapter wifiDeviceShareMemberInfoAdapter = new WifiDeviceShareMemberInfoAdapter(this.b, this.i, this.v);
            this.o = wifiDeviceShareMemberInfoAdapter;
            this.u.setAdapter((ListAdapter) wifiDeviceShareMemberInfoAdapter);
        }
        this.f2244a = (HealthTextView) findViewById(R.id.wifi_device_share_add_member_tv);
        this.aa = (CustomTitleBar) findViewById(R.id.wifi_device_share_title);
        this.y = (LinearLayout) findViewById(R.id.authorize_request_list_entrance);
        this.d = (HealthSubHeader) findViewById(R.id.authorized_users_header);
        if (cpa.x(this.s)) {
            this.f2244a.setVisibility(0);
            if (!this.j) {
                this.d.setVisibility(0);
                this.y.setVisibility(0);
            } else {
                this.f2244a.setText(R.string.IDS_device_share_exit_device);
            }
        }
        this.aa.setTitleText(getString(R.string.IDS_device_wifi_scale_sub_user_menu));
        this.p = (RelativeLayout) findViewById(R.id.red_point_layout);
        this.w = (HealthTextView) findViewById(R.id.red_dot_msg_num);
        if (LanguageUtil.bc(this)) {
            this.f.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.f.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i > 0) {
            this.p.setVisibility(0);
            this.w.setText(UnitUtil.e(i, 1, 0));
            return;
        }
        this.p.setVisibility(4);
    }

    private void l() {
        WifiDeviceGetSubUserAuthMsgReq wifiDeviceGetSubUserAuthMsgReq = new WifiDeviceGetSubUserAuthMsgReq();
        wifiDeviceGetSubUserAuthMsgReq.setDevId(this.i);
        jbs.a(this.b).c(wifiDeviceGetSubUserAuthMsgReq, new ICloudOperationResult() { // from class: cmj
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDeviceShareActivity.this.b((CloudCommonReponse) obj, str, z);
            }
        });
    }

    public /* synthetic */ void b(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        LogUtil.a("WifiDeviceShareActivity", "wifiDeviceGetSubUserAuthMsg request result: ", Boolean.valueOf(z));
        if (z && !TextUtils.isEmpty(str)) {
            WifiDeviceGetSubUserAuthMsgRsp wifiDeviceGetSubUserAuthMsgRsp = (WifiDeviceGetSubUserAuthMsgRsp) new Gson().fromJson(str, WifiDeviceGetSubUserAuthMsgRsp.class);
            if (wifiDeviceGetSubUserAuthMsgRsp == null) {
                LogUtil.h("WifiDeviceShareActivity", "wifiDeviceGetSubUserAuthMsg return fail: authMsgRsp is null");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1005;
            obtain.arg1 = cud.a(wifiDeviceGetSubUserAuthMsgRsp.getAuthMsgs());
            this.h.sendMessage(obtain);
            return;
        }
        LogUtil.a("WifiDeviceShareActivity", "wifiDeviceGetSubUserAuthMsg request result is not success or text is Empty");
        e();
        nrh.d(this.b, getResources().getString(R.string.IDS_device_hagrid_loading_info_failed_prompt).substring(0, r2.length() - 1));
    }

    private void g() {
        this.u.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                WifiDeviceShareActivity.this.Ij_(adapterView, view, i, j);
            }
        });
        this.f2244a.setOnClickListener(this);
        this.y.setOnClickListener(this);
    }

    /* synthetic */ void Ij_(AdapterView adapterView, View view, int i, long j) {
        crx crxVar;
        cpw.a(false, "WifiDeviceShareActivity", "select wifi position = " + i);
        if (!this.j) {
            if (koq.b(this.x, i)) {
                cpw.a(false, "WifiDeviceShareActivity", "mShareMemberList data is exception");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            crxVar = this.x.get(i);
        } else if (koq.b(this.v, i)) {
            cpw.a(false, "WifiDeviceShareActivity", "mSubUserShareMemberList data is exception");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        } else {
            crxVar = new crx();
            cnw.d dVar = this.v.get(i);
            crxVar.d(dVar.a());
            crxVar.b(dVar.b());
            crxVar.e(dVar.d());
        }
        Intent intent = new Intent(this.b, (Class<?>) WifiDeviceShareAccountActivity.class);
        intent.putExtra("share_sub_user_info", crxVar);
        intent.putExtra("deviceId", this.i);
        intent.putExtra("isSubUser", this.j);
        startActivityForResult(intent, 100);
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    private void i() {
        String str;
        UserInfomation userInfo;
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        str = "";
        if (mainUser != null) {
            ctv.Mh_(mainUser.e(), this.l, nmn.cBh_(this.b.getResources(), new nmn.c(null, mainUser.i(), true)));
            String accountName = new UpApi(this.b).getAccountName();
            String h = mainUser.h();
            if (TextUtils.isEmpty(h) && !TextUtils.isEmpty(accountName)) {
                h = accountName;
            }
            if (TextUtils.isEmpty(h) && (userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo()) != null && !TextUtils.isEmpty(userInfo.getName())) {
                h = userInfo.getName();
            }
            str = h != null ? h : "";
            this.ad = str;
            this.n.setText(String.format(Locale.ENGLISH, this.b.getResources().getString(R.string._2130842543_res_0x7f0213af), str));
            str = accountName;
        }
        if (!this.j) {
            this.m.setText(this.b.getResources().getString(R.string.IDS_device_wifi_share_account_administrator));
        } else {
            this.m.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.x.clear();
        this.v.clear();
        o();
        c cVar = this.h;
        if (cVar != null) {
            cVar.sendEmptyMessageDelayed(1003, 500L);
        }
        if (!this.j) {
            b();
        } else {
            a();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        cpw.a(false, "WifiDeviceShareActivity", "resultCode = " + i2);
        if (i != 100) {
            cpw.a(false, "WifiDeviceShareActivity", "onActivityResult nothing...");
            return;
        }
        if (i2 == 12) {
            this.c = true;
            h();
        } else if (i2 == 11) {
            h();
        } else if (i2 == 10) {
            h();
        } else {
            cpw.a(false, "WifiDeviceShareActivity", "requestCode onActivityResult nothing...");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        h();
        l();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("WifiDeviceShareActivity", "onDestroy");
        super.onDestroy();
        EventBus.a(this.g, "sub_user_unauthorize_notification", "multi_user_auto_cancle_dialog");
        c cVar = this.h;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        CommonDialog21 commonDialog21 = this.k;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.k.dismiss();
        this.k = null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("WifiDeviceShareActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.wifi_device_share_add_member_tv) {
            if (!this.j) {
                r();
                p();
            } else {
                q();
            }
        } else if (id == R.id.authorize_request_list_entrance) {
            Intent intent = new Intent(this.b, (Class<?>) WifiDeviceAuthRequestListActivity.class);
            intent.putExtra("dev_id", this.i);
            try {
                this.b.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("WifiDeviceShareActivity", "onClick startActivity ActivityNotFoundException occur.");
            }
        } else {
            LogUtil.h("WifiDeviceShareActivity", "onClick unknown view ", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void r() {
        int i;
        if (!CommonUtil.aa(this.b)) {
            nrh.b(this.b, R.string._2130841884_res_0x7f02111c);
            return;
        }
        if (m()) {
            s();
            return;
        }
        if (cpa.w(this.s) || "b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(this.s)) {
            i = 10;
        } else if (cpa.ai(this.s)) {
            i = 5;
        } else {
            cpw.d(false, "WifiDeviceShareActivity", "shareWifiScaleToFriend default");
            i = 4;
        }
        if (this.x.size() >= i) {
            t();
        } else if (!cpa.ae(this.s) && !n()) {
            y();
        } else {
            d();
        }
    }

    private void s() {
        cpw.a(false, "WifiDeviceShareActivity", "showSupportMultiMemberDialog");
        CustomTextAlertDialog customTextAlertDialog = this.r;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            String string = this.b.getResources().getString(R.string.IDS_device_wifi_user_permission_dialog_title);
            String string2 = this.b.getResources().getString(R.string.IDS_device_wifi_share_account_outh_content);
            String string3 = this.b.getResources().getString(R.string._2130841130_res_0x7f020e2a);
            String string4 = this.b.getResources().getString(R.string._2130841555_res_0x7f020fd3);
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.b);
            builder.b(string).e(string2).cyV_(string4, new View.OnClickListener() { // from class: cmn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDeviceShareActivity.this.Ik_(view);
                }
            }).cyS_(string3, new View.OnClickListener() { // from class: cmt
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDeviceShareActivity.this.Il_(view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            this.r = a2;
            a2.setCancelable(false);
            this.r.show();
        }
    }

    public /* synthetic */ void Ik_(View view) {
        cpw.a(false, "WifiDeviceShareActivity", "button click setPositiveButton");
        SharedPreferenceManager.e(this.b, "wifi_weight_device", "health_is_wifi_add_member_first_" + this.i, "true", (StorageParams) null);
        r();
        this.r = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Il_(View view) {
        cpw.a(false, "WifiDeviceShareActivity", "button click setNegativeButton");
        this.r = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void q() {
        LogUtil.a("WifiDeviceShareActivity", "showSubUserExitDeviceDialog");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.q;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            LogUtil.h("WifiDeviceShareActivity", "showSubUserExitDeviceDialog mMemberNumLimitDialog isShowing");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.b);
        builder.e(R.string.IDS_device_share_exit);
        builder.czC_(R.string._2130841938_res_0x7f021152, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventBus.d(new EventBus.b("event_bus_sub_user_exit_device"));
                WifiDeviceShareActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: cmm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDeviceShareActivity.this.Im_(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        this.q = e2;
        e2.setCancelable(false);
        this.q.show();
    }

    public /* synthetic */ void Im_(View view) {
        this.q.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        cpw.a(false, "WifiDeviceShareActivity", "showMemberNumLimitDialog");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.q;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            String string = this.b.getResources().getString(R.string.IDS_device_wifi_share_account_limit_content);
            String string2 = this.b.getResources().getString(R.string.IDS_device_measureactivity_result_confirm);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.b);
            builder.e(string).czE_(string2, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WifiDeviceShareActivity.this.q = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e2 = builder.e();
            this.q = e2;
            e2.setCancelable(false);
            this.q.show();
        }
    }

    private void y() {
        cpw.a(false, "WifiDeviceShareActivity", "showSupportMultiMemberDialog");
        CustomTextAlertDialog customTextAlertDialog = this.ac;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            String string = this.b.getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade);
            String string2 = this.b.getResources().getString(R.string.IDS_device_wifi_share_account_version_update);
            String string3 = this.b.getResources().getString(R.string._2130841130_res_0x7f020e2a);
            String string4 = this.b.getResources().getString(R.string.IDS_device_manager_update_health);
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.b);
            builder.b(string).e(string2).cyV_(string4, new View.OnClickListener() { // from class: cml
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDeviceShareActivity.this.In_(view);
                }
            }).cyS_(string3, new View.OnClickListener() { // from class: cmo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WifiDeviceShareActivity.this.Io_(view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            this.ac = a2;
            a2.setCancelable(false);
            this.ac.show();
        }
    }

    public /* synthetic */ void In_(View view) {
        cpw.a(false, "WifiDeviceShareActivity", "button click setPositiveButton");
        j();
        this.ac = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Io_(View view) {
        cpw.a(false, "WifiDeviceShareActivity", "button click setNegativeButton");
        this.ac = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        WifiDeviceShareMemberInfoBySubUserReq wifiDeviceShareMemberInfoBySubUserReq = new WifiDeviceShareMemberInfoBySubUserReq();
        wifiDeviceShareMemberInfoBySubUserReq.setDevId(this.i);
        jbs.a(this).c(wifiDeviceShareMemberInfoBySubUserReq, new ICloudOperationResult() { // from class: cmk
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDeviceShareActivity.this.a((CloudCommonReponse) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        if (this.h == null) {
            LogUtil.h("WifiDeviceShareActivity", "getAuthorizeSubUserFromCloud mHandler is null");
            return;
        }
        if (z) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("members");
                this.v.clear();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    cnw.d dVar = new cnw.d();
                    dVar.d(jSONObject.getString("huid"));
                    dVar.c(jSONObject.getInt("mainUserFlag"));
                    dVar.a(jSONObject.getString("userAccount"));
                    this.v.add(dVar);
                }
            } catch (JSONException unused) {
                LogUtil.a("WifiDeviceShareActivity", "AuthorizeSubUser members is JSONException");
            }
            this.h.sendEmptyMessage(1001);
            return;
        }
        LogUtil.h("WifiDeviceShareActivity", "getMemberInfoBySubUser fail");
        this.h.sendEmptyMessage(1002);
    }

    private void b() {
        csf.e(this.i, new CommBaseCallbackInterface() { // from class: cmg
            @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface
            public final void onResult(int i, String str, Object obj) {
                WifiDeviceShareActivity.this.e(i, str, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, String str, Object obj) {
        List<crx> list;
        c cVar = this.h;
        if (cVar == null) {
            cpw.d(false, "WifiDeviceShareActivity", "getAuthorizeSubUserFromCloud mHandler is null");
            return;
        }
        if (i == 0 && (obj instanceof crw)) {
            ArrayList<crx> b = ((crw) obj).b();
            if (b != null && (list = this.x) != null) {
                list.clear();
                this.x.addAll(b);
            }
            this.h.sendEmptyMessage(1001);
            return;
        }
        cVar.sendEmptyMessage(1002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.x.clear();
        crw a2 = ctq.a(this.i);
        if (a2 != null && a2.b() != null) {
            this.x.addAll(a2.b());
        }
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        WifiDeviceShareAdapter wifiDeviceShareAdapter = this.e;
        if (wifiDeviceShareAdapter != null) {
            wifiDeviceShareAdapter.notifyDataSetChanged();
        }
        WifiDeviceShareMemberInfoAdapter wifiDeviceShareMemberInfoAdapter = this.o;
        if (wifiDeviceShareMemberInfoAdapter != null) {
            wifiDeviceShareMemberInfoAdapter.notifyDataSetChanged();
        }
    }

    private void d() {
        cpw.a(false, "WifiDeviceShareActivity", "goDeviceSharePage");
        Intent intent = new Intent(this.b, (Class<?>) WifiDeviceAddUserActivity.class);
        intent.putExtra("deviceId", this.i);
        intent.putExtra("productId", this.s);
        intent.putExtra(HwPayConstant.KEY_USER_NAME, this.ad);
        startActivity(intent);
    }

    private void j() {
        cpw.a(false, "WifiDeviceShareActivity", "goOtaUpdatePage");
        csc.d().c(this.i, false);
        Intent intent = new Intent(this.b, (Class<?>) DeviceMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("view", "WifiVersionDetails");
        bundle.putString("productId", this.s);
        intent.putExtras(bundle);
        try {
            this.b.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WifiDeviceShareActivity", "goOtaUpdatePage startActivity ActivityNotFoundException.");
        }
    }

    private boolean n() {
        String b = SharedPreferenceManager.b(this.b, "wifi_weight_device", "support_multi_account_" + this.i);
        this.t = b;
        return !TextUtils.isEmpty(b) && "1".equals(this.t);
    }

    private void p() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(this.b, AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_DEVICE_MULTIACCOUNT_SHARE_2060032.value(), hashMap, 0);
    }

    private boolean m() {
        String b = SharedPreferenceManager.b(this.b, "wifi_weight_device", "health_is_wifi_add_member_first_" + this.i);
        return "false".equals(b) || "".equals(b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("WifiDeviceShareActivity", "showLoadDataDialog error: activity is finishing");
            return;
        }
        if (this.k == null) {
            new CommonDialog21(this, R.style.app_update_dialogActivity);
            this.k = CommonDialog21.a(this);
        }
        this.k.setCancelable(false);
        this.k.e(str);
        this.k.a();
    }

    private void e() {
        CommonDialog21 commonDialog21 = this.k;
        boolean z = commonDialog21 != null && commonDialog21.isShowing();
        if (isFinishing() || !z) {
            return;
        }
        this.k.dismiss();
        this.k = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.h.removeMessages(1003);
        e();
        if (this.c) {
            this.c = false;
            Context context = this.b;
            nrh.d(context, context.getResources().getString(R.string.IDS_device_wifi_share_remove_success));
        }
    }

    static class e implements EventBus.ICallback {
        private WeakReference<WifiDeviceShareActivity> e;

        e(WifiDeviceShareActivity wifiDeviceShareActivity) {
            this.e = new WeakReference<>(wifiDeviceShareActivity);
        }

        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            WeakReference<WifiDeviceShareActivity> weakReference = this.e;
            if (weakReference == null) {
                cpw.a(false, "WifiDeviceShareActivity", "mActivity is null");
                return;
            }
            WifiDeviceShareActivity wifiDeviceShareActivity = weakReference.get();
            if (wifiDeviceShareActivity == null) {
                cpw.a(false, "WifiDeviceShareActivity", "activity is null");
                return;
            }
            if (bVar == null) {
                cpw.a(false, "WifiDeviceShareActivity", "event is null");
                return;
            }
            String e = bVar.e();
            cpw.a(false, "WifiDeviceShareActivity", "onEvent == ", e);
            if ("sub_user_unauthorize_notification".equals(e) || "multi_user_auto_cancle_dialog".equals(bVar.e())) {
                wifiDeviceShareActivity.h.sendEmptyMessage(1004);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
