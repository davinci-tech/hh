package com.huawei.hwidauth.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import defpackage.krm;
import defpackage.ksi;
import defpackage.ksy;

/* loaded from: classes9.dex */
public class AuthWXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI d;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        ksy.b("AuthWXEntryActivity", "AuthWXEntryActivity onCreate", true);
        requestWindowFeature(1);
        ksi.a((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        if (!(intent instanceof SafeIntent)) {
            SafeIntent safeIntent = new SafeIntent(intent);
            setIntent(safeIntent);
            intent = safeIntent;
        }
        try {
            d(this);
            bundle2 = intent.getExtras();
        } catch (Exception e) {
            ksy.d("AuthWXEntryActivity", e.getClass().getSimpleName(), true);
            bundle2 = null;
        }
        if (bundle2 == null || bundle2.isEmpty()) {
            ksy.b("AuthWXEntryActivity", "not called by weixin app, finish myself", true);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ksy.b("AuthWXEntryActivity", "AuthWXEntryActivity onNewIntent", true);
        setIntent(intent);
        this.d.handleIntent(intent, this);
    }

    private void d(Context context) {
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, ksi.k(), true);
        this.d = createWXAPI;
        createWXAPI.registerApp(ksi.k());
        this.d.handleIntent(getIntent(), this);
        ksi.a("");
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        ksy.a("AuthWXEntryActivity", "enter onReq", true);
        finish();
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        ksy.b("AuthWXEntryActivity", "enter onResp", true);
        if (baseResp instanceof SendAuth.Resp) {
            SendAuth.Resp resp = (SendAuth.Resp) baseResp;
            int i = resp.errCode;
            Intent intent = new Intent();
            intent.setAction("com.huawei.hwid.third.ACTION_WEIXIN_LOGIN_RESP");
            if (i == -4 || i == -2) {
                ksy.b("AuthWXEntryActivity", "user cancel or denied login with wechat", true);
                intent.putExtra("resultCode", 0);
            } else if (i == 0) {
                String str = resp.code;
                String str2 = resp.state;
                intent.putExtra("code", str);
                intent.putExtra("state", str2);
                intent.putExtra("resultCode", -1);
            }
            krm.e().bQr_(intent);
        }
        finish();
    }
}
