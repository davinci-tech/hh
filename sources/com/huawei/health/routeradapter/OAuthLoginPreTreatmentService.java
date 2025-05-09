package com.huawei.health.routeradapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.routeradapter.OAuthLoginPreTreatmentService;
import com.huawei.hihealthservice.hmsauth.HmsAuthApi;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwadpaterhealthmgr.PluginOperationAdapterImpl;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.operation.PluginOperation;
import com.tencent.open.SocialOperation;
import defpackage.jbc;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class OAuthLoginPreTreatmentService implements PretreatmentService {

    /* renamed from: a, reason: collision with root package name */
    private String f2963a;
    private String b;
    private String c;

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(final Context context, Guidepost guidepost) {
        final Uri zN_ = guidepost.zN_();
        this.c = zN_.getQueryParameter("client_id");
        this.b = zN_.getQueryParameter(SocialOperation.GAME_SIGNATURE);
        this.f2963a = zN_.getQueryParameter("thirdUrlScheme");
        String queryParameter = zN_.getQueryParameter("scope");
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.f2963a) || TextUtils.isEmpty(queryParameter)) {
            avy_(context, zN_, 224);
            ReleaseLogUtil.c("Login_OAuthLoginPreTreatmentService", "onPretreatment, clientId, signature, thirdUrlScheme or scopes is empty");
            return false;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: fbj
            @Override // java.lang.Runnable
            public final void run() {
                OAuthLoginPreTreatmentService.this.avA_(zN_, context);
            }
        });
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: avz_, reason: merged with bridge method [inline-methods] */
    public void avA_(Uri uri, Context context) {
        if (!jbc.bEi_(uri)) {
            LogUtil.b("OAuthLoginPreTreatmentService", "loginToOAuth, Auth not permitted");
            avy_(context, uri, a.M);
            return;
        }
        if (!a()) {
            LogUtil.b("OAuthLoginPreTreatmentService", "loginToOAuth, check signature wrong");
            avy_(context, uri, 216);
            return;
        }
        String bEh_ = jbc.bEh_(uri);
        if (TextUtils.isEmpty(bEh_)) {
            avy_(context, uri, 217);
            LogUtil.b("OAuthLoginPreTreatmentService", "loginToOAuth, scopes are empty");
            return;
        }
        String bEf_ = jbc.bEf_(uri, bEh_, context);
        if (PluginOperation.getInstance(context).getAdapter() == null) {
            PluginOperationAdapterImpl pluginOperationAdapterImpl = PluginOperationAdapterImpl.getInstance(context);
            PluginOperation pluginOperation = PluginOperation.getInstance(context);
            pluginOperation.setAdapter(pluginOperationAdapterImpl);
            pluginOperation.init(context);
        }
        H5proUtil.jumpFromDeeplink(context, bEf_);
    }

    private boolean a() {
        LogUtil.a("OAuthLoginPreTreatmentService", "checkSignature, enter");
        boolean isValidRedirectUrl = ((HmsAuthApi) Services.c("HealthKit", HmsAuthApi.class)).isValidRedirectUrl(this.c, this.b, this.f2963a);
        if (isValidRedirectUrl) {
            SharedPreferenceManager.c("HealthKit", "thirdUrlScheme", this.f2963a);
            SharedPreferenceManager.c("HealthKit", "clientId", this.c);
        }
        return isValidRedirectUrl;
    }

    private void avy_(Context context, Uri uri, int i) {
        String queryParameter = uri.getQueryParameter("state");
        Uri.Builder buildUpon = Uri.parse(this.f2963a).buildUpon();
        if (!TextUtils.isEmpty(queryParameter)) {
            buildUpon.appendQueryParameter("state", queryParameter);
        }
        buildUpon.appendQueryParameter("errorCode", String.valueOf(i));
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(buildUpon.build().toString()));
        intent.setFlags(268435456);
        if (context == null) {
            ReleaseLogUtil.d("Login_OAuthLoginPreTreatmentService", "context is null");
            return;
        }
        ReleaseLogUtil.d("Login_OAuthLoginPreTreatmentService", "jumpToThirdApp, login failed");
        nsn.cLR_(context, intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}
