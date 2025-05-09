package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.awarenessdonate.AwarenessDonateApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.main.R$string;
import health.compact.a.AuthorizationUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class rvr {

    /* renamed from: a, reason: collision with root package name */
    private rvh f16935a;
    private Activity b;
    private float c;
    private Handler d;
    private CustomAlertDialog e;

    public rvr(Activity activity) {
        this.b = activity;
    }

    public void b(Context context) {
        if (context == null) {
            return;
        }
        CustomAlertDialog customAlertDialog = this.e;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        this.f16935a = new rvh(context, this.b, null);
        View inflate = View.inflate(context, R.layout.services_custom_view_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_all);
        healthTextView.setText(context.getString(R$string.IDS_hwh_privacy_permission_total, context.getResources().getString(R$string.IDS_hwh_privacy_permission_read)));
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_one);
        healthTextView2.setText(sds.dWj_());
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_six);
        healthTextView3.setText(new SpannableString(context.getString(R$string.IDS_hwh_agreement_save_area, context.getResources().getString(R$string.IDS_hwh_agreement_china))));
        HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.hw_health_service_item_ele);
        sds.b(healthTextView4);
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
            healthTextView2.setTextSize(1, 26.0f);
            healthTextView3.setTextSize(1, 26.0f);
            healthTextView3.setTextSize(1, 26.0f);
            ((HealthTextView) inflate.findViewById(R.id.hw_health_service_item_seven)).setTextSize(1, 26.0f);
            ((HealthTextView) inflate.findViewById(R.id.hw_health_service_item_eight)).setTextSize(1, 26.0f);
            ((HealthTextView) inflate.findViewById(R.id.hw_health_service_item_eight)).setTextSize(1, 26.0f);
            healthTextView4.setTextSize(1, 26.0f);
        }
        CustomAlertDialog dSf_ = dSf_(context, inflate, (HealthScrollView) inflate.findViewById(R.id.scrollview_dialog));
        this.e = dSf_;
        dSf_.setCancelable(false);
        this.e.show();
    }

    private CustomAlertDialog dSf_(Context context, View view, HealthScrollView healthScrollView) {
        CustomAlertDialog c = new CustomAlertDialog.Builder(context).a(context.getString(R$string.IDS_hwh_privacy_change_notice)).cyp_(view).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: rvs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                rvr.dSg_(dialogInterface, i);
            }
        }).cyo_(R$string.IDS_hwh_privacy_revoke_auth, new DialogInterface.OnClickListener() { // from class: rvx
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                rvr.this.dSh_(dialogInterface, i);
            }
        }).c();
        if (nsn.aa(context)) {
            d(context);
            nsn.a(context, c);
            ViewGroup.LayoutParams layoutParams = healthScrollView.getLayoutParams();
            layoutParams.height = nsn.c(context, context.getResources().getConfiguration().screenHeightDp * 0.8f * this.c);
            healthScrollView.setLayoutParams(layoutParams);
        }
        return c;
    }

    static /* synthetic */ void dSg_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* synthetic */ void dSh_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f16935a.d();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void d(Context context) {
        float f = context.getResources().getConfiguration().screenHeightDp;
        float a2 = nsn.a(context, nsn.j());
        if (f < 0.4f * a2) {
            this.c = 0.3f;
        } else if (f > a2 * 0.6f) {
            this.c = 0.7f;
        } else {
            this.c = 0.55f;
        }
    }

    public boolean b() {
        CustomAlertDialog customAlertDialog = this.e;
        if (customAlertDialog != null) {
            return customAlertDialog.isShowing();
        }
        return false;
    }

    public void a(final Context context) {
        if (context == null) {
            LogUtil.h("PrivacyNotice", "stopHealth context is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(context, AnalyticsValue.HEALTH_CANCEL_AUTH_2040061.value(), hashMap, 0);
        ixx.d().c(context);
        knx.b(false);
        this.d = new a(Looper.getMainLooper(), this, context);
        if (!LoginInit.getInstance(context).getIsLogined()) {
            LogUtil.a("PrivacyNotice", "stopHealth logout");
            i(context);
            return;
        }
        LogUtil.a("PrivacyNotice", "stopHealth obtainToken");
        if (!CommonUtil.aa(context)) {
            nrh.b(context, R$string.IDS_device_hygride_current_network_unavailable);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rvv
                @Override // java.lang.Runnable
                public final void run() {
                    rvr.this.c(context);
                }
            });
        }
    }

    private void h(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private void g(Context context) {
        if (context != null) {
            Intent intent = new Intent("com.huawei.health.user.exit");
            intent.setPackage(BaseApplication.getAppPackage());
            BroadcastManagerUtil.bFG_(context, intent);
        }
        LogUtil.a("PrivacyNotice", "### main exit by user");
        this.d.postDelayed(new Runnable() { // from class: rvr.1
            @Override // java.lang.Runnable
            public void run() {
                Process.killProcess(Process.myPid());
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void c(final Context context) {
        String url = GRSManager.a(context).getUrl("agreementservice");
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1015);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(118);
        arrayList.add(10009);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String country = BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        new AgrHttp().signHttpReq(accountInfo, url, false, arrayList, accountInfo2, language + "_" + country, new HttpResCallBack() { // from class: rvr.2
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public void onFinished(int i, String str) {
                if (i == 200 && sds.c(str)) {
                    LogUtil.a("PrivacyNotice", "cancelSignAgr_result ", str);
                    rvr.this.c();
                    rvr.this.i(context);
                } else {
                    LogUtil.a("PrivacyNotice", "cancelSignAgr_result error", Integer.valueOf(i));
                    rvr.this.d.sendEmptyMessage(10004);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(Context context) {
        ReleaseLogUtil.e("R_PrivacyNotice", "revokeAuthorizeSuccess, key_wether_to_auth is false");
        StorageParams storageParams = new StorageParams();
        SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_china", "", storageParams);
        SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_query_time", "", storageParams);
        SharedPreferenceManager.e(context, Integer.toString(10000), "if_first_agr_sign", "", new StorageParams());
        KeyValDbManager.b(context).e("key_wether_to_auth", String.valueOf(false));
        SharedPreferenceManager.e(context, Integer.toString(10000), "key_wether_to_auth", String.valueOf(false), new StorageParams());
        gmz.d().c(11, false, (String) null, (IBaseResponseCallback) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "hw_health_show_grant_pwd", Integer.toString(0), new StorageParams());
        SharedPreferenceManager.e(Integer.toString(10000), "is_agr_change_user", false);
        SharedPreferences.Editor edit = context.getSharedPreferences("IDEQ_IndoorEquipConnectedActivity" + CommonUtil.e(context), 0).edit();
        edit.putBoolean("IDEQ_IndoorEquipConnectedActivityprivacyDialogConfirm" + CommonUtil.e(context), false);
        edit.apply();
        lbv.a(context, false);
        AuthorizationUtils.d();
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_agree_full_service_model", false);
        h(context);
        g(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("PrivacyNotice", "enter cancelFaCardDonate");
        ThreadPoolManager.d().execute(new Runnable() { // from class: rvt
            @Override // java.lang.Runnable
            public final void run() {
                rvr.a();
            }
        });
    }

    static /* synthetic */ void a() {
        AwarenessDonateApi awarenessDonateApi = (AwarenessDonateApi) Services.a("HWSmartInteractMgr", AwarenessDonateApi.class);
        if (awarenessDonateApi == null || !awarenessDonateApi.isSupportDonate()) {
            LogUtil.h("PrivacyNotice", "awarenessDonateApi is null or not support donate");
        } else {
            awarenessDonateApi.removeData();
        }
    }

    public class a extends BaseHandler<rvr> {
        private Context d;

        a(Looper looper, rvr rvrVar, Context context) {
            super(looper, rvrVar);
            this.d = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dSi_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(rvr rvrVar, Message message) {
            if (message == null) {
                LogUtil.a("PrivacyNotice", "handleMessageWhenReferenceNotNull msg is null");
            } else {
                if (message.what != 10004) {
                    return;
                }
                nrh.b(this.d, R$string.IDS_deauthorization_fail);
            }
        }
    }
}
