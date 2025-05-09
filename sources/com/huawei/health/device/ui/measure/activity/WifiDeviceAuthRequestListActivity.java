package com.huawei.health.device.ui.measure.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.activity.WifiDeviceAuthRequestListActivity;
import com.huawei.health.device.ui.measure.adapter.SubUserAuthListAdapter;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAuthorizeByMainUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceSubUserAuthMsg;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.cud;
import defpackage.jbs;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class WifiDeviceAuthRequestListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f2240a;
    private RelativeLayout b;
    private Context c;
    private RelativeLayout d;
    private SubUserAuthListAdapter.AuthButtonClickCallback e = new SubUserAuthListAdapter.AuthButtonClickCallback() { // from class: cma
        @Override // com.huawei.health.device.ui.measure.adapter.SubUserAuthListAdapter.AuthButtonClickCallback
        public final void onAuthButtonClick(WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, boolean z) {
            WifiDeviceAuthRequestListActivity.this.e(wifiDeviceSubUserAuthMsg, z);
        }
    };
    private ListView i;

    public /* synthetic */ void e(WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, boolean z) {
        if (nsn.o()) {
            LogUtil.h("WifiDeviceAuthRequestListActivity", "onAuthButtonClick click too fast.");
        } else {
            LogUtil.a("WifiDeviceAuthRequestListActivity", "reply auth:", wifiDeviceSubUserAuthMsg.getSubHuid(), Boolean.valueOf(z));
            d(this.c, wifiDeviceSubUserAuthMsg, this.f2240a, z);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        setContentView(R.layout.activity_auth_request_list);
        c();
        if (b()) {
            d();
        }
    }

    private void c() {
        this.i = (ListView) findViewById(R.id.list_view);
        this.d = (RelativeLayout) findViewById(R.id.no_message_layout);
        this.b = (RelativeLayout) findViewById(R.id.no_network_layout);
        if (CommonUtil.aa(this.c)) {
            return;
        }
        a();
    }

    private boolean b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("WifiDeviceAuthRequestListActivity", "intent null,return");
            return false;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            LogUtil.h("WifiDeviceAuthRequestListActivity", "intent null,return");
            return false;
        }
        String string = extras.getString("dev_id");
        this.f2240a = string;
        if (!TextUtils.isEmpty(string)) {
            return true;
        }
        LogUtil.h("WifiDeviceAuthRequestListActivity", "bundle.getString(BUNDLE_KEY_DEV_ID) return empty");
        return false;
    }

    private void d(final Context context, WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, String str, final boolean z) {
        WifiDeviceAuthorizeByMainUserReq wifiDeviceAuthorizeByMainUserReq = new WifiDeviceAuthorizeByMainUserReq();
        wifiDeviceAuthorizeByMainUserReq.setSubHuid(wifiDeviceSubUserAuthMsg.getSubHuid());
        wifiDeviceAuthorizeByMainUserReq.setDevId(str);
        wifiDeviceAuthorizeByMainUserReq.setIntent(z ? 1 : 2);
        jbs.a(context).a(wifiDeviceAuthorizeByMainUserReq, new ICloudOperationResult() { // from class: clx
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z2) {
                WifiDeviceAuthRequestListActivity.this.e(context, z, (CloudCommonReponse) obj, str2, z2);
            }
        });
    }

    public /* synthetic */ void e(Context context, boolean z, CloudCommonReponse cloudCommonReponse, String str, boolean z2) {
        LogUtil.a("WifiDeviceAuthRequestListActivity", "getRequestAuthorizationDetail request result: ", Boolean.valueOf(z2));
        cud.e(context, cloudCommonReponse.getResultCode().intValue(), z2, z);
        d();
    }

    private void d(final List<WifiDeviceSubUserAuthMsg> list) {
        runOnUiThread(new Runnable() { // from class: clv
            @Override // java.lang.Runnable
            public final void run() {
                WifiDeviceAuthRequestListActivity.this.a(list);
            }
        });
    }

    public /* synthetic */ void a(List list) {
        if (koq.b(list)) {
            LogUtil.h("WifiDeviceAuthRequestListActivity", "refreshListView return fail: subUserAuthMsgs is empty");
            a();
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg = (WifiDeviceSubUserAuthMsg) it.next();
            if (wifiDeviceSubUserAuthMsg.getStatus() <= 4) {
                arrayList.add(wifiDeviceSubUserAuthMsg);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("WifiDeviceAuthRequestListActivity", "refreshListView return fail: tmpSubUserAuthMsgs is empty");
            a();
        } else {
            Collections.sort(arrayList);
            this.i.setAdapter((ListAdapter) new SubUserAuthListAdapter(this.c, arrayList, this.e));
            this.i.setVisibility(0);
        }
    }

    private void d() {
        WifiDeviceGetSubUserAuthMsgReq wifiDeviceGetSubUserAuthMsgReq = new WifiDeviceGetSubUserAuthMsgReq();
        wifiDeviceGetSubUserAuthMsgReq.setDevId(this.f2240a);
        jbs.a(this.c).c(wifiDeviceGetSubUserAuthMsgReq, new ICloudOperationResult() { // from class: cme
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDeviceAuthRequestListActivity.this.a((CloudCommonReponse) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        LogUtil.a("WifiDeviceAuthRequestListActivity", "wifiDeviceGetSubUserAuthMsg request result: ", Boolean.valueOf(z));
        if (z) {
            WifiDeviceGetSubUserAuthMsgRsp wifiDeviceGetSubUserAuthMsgRsp = (WifiDeviceGetSubUserAuthMsgRsp) new Gson().fromJson(str, WifiDeviceGetSubUserAuthMsgRsp.class);
            if (wifiDeviceGetSubUserAuthMsgRsp == null) {
                LogUtil.h("WifiDeviceAuthRequestListActivity", "wifiDeviceGetSubUserAuthMsg return fail: authMsgRsp is null");
                a();
                return;
            } else {
                d(wifiDeviceGetSubUserAuthMsgRsp.getAuthMsgs());
                return;
            }
        }
        a();
    }

    private void a() {
        runOnUiThread(new Runnable() { // from class: clz
            @Override // java.lang.Runnable
            public final void run() {
                WifiDeviceAuthRequestListActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        this.i.setVisibility(8);
        if (!CommonUtil.aa(this.c)) {
            this.b.setVisibility(0);
            this.d.setVisibility(8);
        } else {
            this.b.setVisibility(8);
            this.d.setVisibility(0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        if (intent == null) {
            LogUtil.b("WifiDeviceAuthRequestListActivity", "intent is null");
        } else {
            super.onNewIntent(intent);
            d();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
