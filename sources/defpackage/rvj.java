package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.awarenessdonate.AwarenessDonateApi;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class rvj {
    private Activity d;
    private Handler e;

    public rvj(Activity activity) {
        this.d = activity;
        this.e = new a(Looper.getMainLooper(), this, activity);
    }

    public void c(final Context context) {
        String url = GRSManager.a(context).getUrl("agreementservice");
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1015);
        if (TextUtils.isEmpty(accountInfo) || TextUtils.isEmpty(url) || context == null) {
            LogUtil.h("CancelAuthInteractors", "cancelSignAgreement token or url is empty, context is null");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(118);
        arrayList.add(10009);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String country = BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        new AgrHttp().signHttpReq(accountInfo, url, false, arrayList, accountInfo2, language + "_" + country, new HttpResCallBack() { // from class: rvj.5
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public void onFinished(int i, String str) {
                if (i == 200 && sds.c(str)) {
                    LogUtil.a("CancelAuthInteractors", "cancelSignAgreement_result ", str);
                    rvj.this.d();
                    rvj.this.b(context);
                    rvj.this.e(context);
                    rvj.this.a(context);
                    rvj.this.d(context);
                    return;
                }
                LogUtil.a("CancelAuthInteractors", "cancelSignAgreement_result resultCode ", Integer.valueOf(i));
                rvj.this.e.sendEmptyMessage(10004);
            }
        });
    }

    public void a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void d(Context context) {
        if (context != null) {
            Intent intent = new Intent("com.huawei.health.user.exit");
            intent.setPackage(context.getPackageName());
            BroadcastManagerUtil.bFG_(context, intent);
        }
        LogUtil.a("CancelAuthInteractors", "overseas main exit by user");
        this.e.postDelayed(new Runnable() { // from class: rvj.4
            @Override // java.lang.Runnable
            public void run() {
                Process.killProcess(Process.myPid());
            }
        }, 1000L);
    }

    public void b(Context context) {
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_if_agree_authorize", "2", new StorageParams());
        sbc.b();
        if (Utils.l()) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud", "", (StorageParams) null);
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(context);
        int h = CommonUtil.h(loginInit.getAccountInfo(1009));
        if (!loginInit.getIsLogined() || LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            f(context);
            return;
        }
        if (h == 1) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_china", "", (StorageParams) null);
            return;
        }
        if (h == 8) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_russia", "", (StorageParams) null);
        } else if (h == 5) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_hong_kong", "", (StorageParams) null);
        } else {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_europe", "", (StorageParams) null);
        }
    }

    private void f(Context context) {
        if (context == null) {
            LogUtil.h("CancelAuthInteractors", "cancelTermsAuthorize context is null");
            return;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("CancelAuthInteractors", "cancelTermsAuthorize countryCode is empty");
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud", "", (StorageParams) null);
            return;
        }
        LogUtil.a("CancelAuthInteractors", "cancelTermsAuthorize countryCode = ", b);
        if (CommonUtil.j(context, b)) {
            if (CommonUtil.c(R.array._2130968695_res_0x7f040077, b)) {
                SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_russia", "", (StorageParams) null);
                return;
            }
            if (CommonUtil.c(R.array._2130968680_res_0x7f040068, b)) {
                SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_hong_kong", "", (StorageParams) null);
                return;
            } else if (Utils.l()) {
                SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud", "", (StorageParams) null);
                return;
            } else {
                SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_europe", "", (StorageParams) null);
                return;
            }
        }
        SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud", "", (StorageParams) null);
    }

    public void e(Context context) {
        ReleaseLogUtil.e("R_CancelAuthInteractors", "setTermsTag, key_wether_to_auth is false");
        KeyValDbManager.b(context).e("key_wether_to_auth", String.valueOf(false));
        SharedPreferenceManager.e(context, Integer.toString(10000), "key_wether_to_auth", String.valueOf(false), (StorageParams) null);
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_query_time", "", (StorageParams) null);
        SharedPreferenceManager.e(context, Integer.toString(10000), "if_first_agr_sign", "", (StorageParams) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "hw_health_show_grant_pwd", Integer.toString(0), (StorageParams) null);
        SharedPreferenceManager.e(Integer.toString(10000), "is_agr_change_user", false);
        SharedPreferences.Editor edit = context.getSharedPreferences("IDEQ_IndoorEquipConnectedActivity" + CommonUtil.e(context), 0).edit();
        edit.putBoolean("IDEQ_IndoorEquipConnectedActivityprivacyDialogConfirm" + CommonUtil.e(context), false);
        edit.apply();
        lbv.a(context, false);
        AuthorizationUtils.d();
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_agree_full_service_model", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("CancelAuthInteractors", "enter cancelFaCardDonate");
        ThreadPoolManager.d().execute(new Runnable() { // from class: rvk
            @Override // java.lang.Runnable
            public final void run() {
                rvj.c();
            }
        });
    }

    static /* synthetic */ void c() {
        AwarenessDonateApi awarenessDonateApi = (AwarenessDonateApi) Services.a("HWSmartInteractMgr", AwarenessDonateApi.class);
        if (awarenessDonateApi == null || !awarenessDonateApi.isSupportDonate()) {
            LogUtil.h("CancelAuthInteractors", "awarenessDonateApi is null or not support donate");
        } else {
            awarenessDonateApi.removeData();
        }
    }

    class a extends BaseHandler<rvj> {
        private Context e;

        private a(Looper looper, rvj rvjVar, Context context) {
            super(looper, rvjVar);
            this.e = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dRV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(rvj rvjVar, Message message) {
            if (message == null) {
                LogUtil.h("CancelAuthInteractors", "handleMessageWhenReferenceNotNull msg is null");
            } else {
                if (message.what != 10004) {
                    return;
                }
                nrh.b(this.e, R$string.IDS_deauthorization_fail);
            }
        }
    }
}
