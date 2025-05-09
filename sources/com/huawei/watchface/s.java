package com.huawei.watchface;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.api.entity.pay.PayReq;
import com.huawei.hms.support.api.entity.pay.WithholdRequest;
import com.huawei.hms.support.api.entity.pay.internal.BaseReq;
import com.huawei.hms.support.api.pay.HuaweiPay;
import com.huawei.hms.support.api.pay.PayResult;
import com.huawei.hms.support.api.pay.PayResultInfo;
import com.huawei.hms.support.api.paytask.Pay;
import com.huawei.hms.support.api.paytask.PayClient;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.DetailedCreativeType;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.cm;
import com.huawei.watchface.cn;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.CouponsAndVipPackageBean;
import com.huawei.watchface.mvp.model.datatype.CouponsByproductBean;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.MarketH5Bean;
import com.huawei.watchface.mvp.model.datatype.PayInfo;
import com.huawei.watchface.mvp.model.datatype.VipOrderBean;
import com.huawei.watchface.mvp.model.datatype.VipPackageBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryResp;
import com.huawei.watchface.mvp.model.datatype.WatchFaceOrderBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.info.VipPayInfo;
import com.huawei.watchface.mvp.model.info.VipPayInfoCoupons;
import com.huawei.watchface.mvp.model.pay.MagicJsPayResult;
import com.huawei.watchface.mvp.ui.dialog.BottomVipPayDialogFragment;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11165a = new Object();
    private static s b;
    private OperateWatchFaceCallback c;
    private Context d;
    private HuaweiApiClient e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private boolean m;
    private boolean n;
    private WatchFaceOrderBean o;
    private cn p;
    private BottomVipPayDialogFragment q;
    private bf r;
    private VipPackageBean s;
    private VipPayInfoCoupons t;
    private CouponsByproductBean u;
    private bf v;
    private HuaweiApiClient.OnConnectionFailedListener w = new HuaweiApiClient.OnConnectionFailedListener() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda1
        @Override // com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            s.this.c(connectionResult);
        }
    };
    private HuaweiApiClient.ConnectionCallbacks x = new HuaweiApiClient.ConnectionCallbacks() { // from class: com.huawei.watchface.s.1
        @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
        public void onConnected() {
            HwLog.i("HwWatchFacePayManager", "onConnected, IsConnected:" + s.this.e.isConnected());
            s.this.k();
        }

        @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            HwLog.i("HwWatchFacePayManager", "onConnectionSuspended, cause:" + i + ", IsConnected:" + s.this.e.isConnected());
            s.this.l();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(ConnectionResult connectionResult) {
        HwLog.i("HwWatchFacePayManager", "onConnectionFailed, ErrorCode:" + connectionResult.getErrorCode());
        if (connectionResult.getErrorCode() == 6) {
            a(this.f, this.g, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI));
        } else {
            l();
        }
        a(Environment.getApplicationContext()).b(connectionResult);
    }

    private s(Context context) {
        this.d = context;
    }

    public static s a(Context context) {
        s sVar;
        synchronized (f11165a) {
            if (b == null) {
                HwLog.i("HwWatchFacePayManager", "getInstance() context =" + context);
                b = new s(context.getApplicationContext());
            }
            sVar = b;
        }
        return sVar;
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.c = operateWatchFaceCallback;
    }

    public void a(PayResultInfo payResultInfo) {
        HwLog.i("HwWatchFacePayManager", "unlockCoupon: enter");
        if (payResultInfo == null || TextUtils.isEmpty(this.h) || TextUtils.isEmpty(this.k)) {
            HwLog.i("HwWatchFacePayManager", "null ==taskInfo || null == taskInfo.mOrderInfo || null == taskInfo.mCouponInfo");
        } else {
            i();
        }
    }

    private void i() {
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.s.2
            @Override // java.lang.Runnable
            public void run() {
                bz bzVar = new bz(s.this.h, s.this.k);
                HwLog.i("HwWatchFacePayManager", "unlockCoupon :  " + bzVar.c(bzVar.c()));
            }
        });
    }

    public void a() {
        HwLog.i("HwWatchFacePayManager", "unlockCoupon: enter");
        if (TextUtils.isEmpty(this.h) || TextUtils.isEmpty(this.k)) {
            HwLog.i("HwWatchFacePayManager", "null == taskInfo.mOrderInfo || null == taskInfo.mCouponInfo");
        } else {
            i();
        }
    }

    public String b() {
        return this.f;
    }

    public String c() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public void a(VipPayInfoCoupons vipPayInfoCoupons) {
        this.t = vipPayInfoCoupons;
    }

    public void a(final InstallWatchFaceBean installWatchFaceBean) {
        if (installWatchFaceBean == null) {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayVerifyInfo() watchFacePayInfo is null。");
            l();
            return;
        }
        final String productId = installWatchFaceBean.getProductId();
        final String watchFaceHiTopId = installWatchFaceBean.getWatchFaceHiTopId();
        final String version = installWatchFaceBean.getVersion();
        if (TextUtils.isEmpty(productId)) {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayVerifyInfo() productId is null。");
            l();
        } else {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.s.3
                @Override // java.lang.Runnable
                public void run() {
                    bw bwVar = new bw(productId, watchFaceHiTopId, 1);
                    WatchFaceDownloadQueryResp c = bwVar.c(bwVar.c());
                    if (c == null) {
                        HwLog.e("HwWatchFacePayManager", "getWatchFacePayVerifyInfo -- watchFaceDownloadQueryResp is null");
                        return;
                    }
                    final int intValue = c.getResultcode().intValue();
                    final String downUrl = c.getDownUrl();
                    if (intValue == 11002 && s.this.c != null) {
                        HwLog.e("HwWatchFacePayManager", "getWatchFacePayVerifyInfo -- watchFaceDownloadQueryResp is not success, ret==" + intValue);
                        bwVar.a(intValue, s.this.c);
                    }
                    BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.s.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            HwLog.i("HwWatchFacePayManager", "getWatchFacePayVerifyInfo -- resultCode: " + intValue);
                            if (intValue == 0 && !TextUtils.isEmpty(downUrl)) {
                                s.this.a(watchFaceHiTopId, version, "0");
                                return;
                            }
                            if (WatchFaceHttpUtil.a(intValue)) {
                                s.this.a(watchFaceHiTopId, version, "orderProduct_" + intValue);
                                return;
                            }
                            HwLog.e("HwWatchFacePayManager", "getWatchFacePayVerifyInfo -- watchFaceDownloadQueryResp is not success, ret==" + intValue);
                            s.this.b(installWatchFaceBean);
                        }
                    });
                }
            });
        }
    }

    public void a(BaseReq baseReq, final bf bfVar) {
        this.r = bfVar;
        HwLog.i("HwWatchFacePayManager", "payForVip ");
        VipPayInfoCoupons vipPayInfoCoupons = this.t;
        if (vipPayInfoCoupons != null) {
            ej.a(vipPayInfoCoupons.getProductCode(), this.t);
            b(this.t.getProductCode());
        }
        Context customWebViewContext = this.c.getCustomWebViewContext();
        final WebViewActivity webViewActivity = customWebViewContext instanceof WebViewActivity ? (WebViewActivity) customWebViewContext : null;
        if (webViewActivity == null) {
            HwLog.e("HwWatchFacePayManager", "payForVip --activity is null");
            bfVar.onResult(Constants.COORDINATE_FAIL_DEF, null);
            ej.a(this.j, -1, "hms支付参数为空");
            return;
        }
        this.m = true;
        if (cu.d()) {
            if (!CommonUtils.m(this.d)) {
                HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo() isNewHonor.");
                this.m = false;
            }
        } else if (HwWatchFaceApi.getInstance(this.d).isHmsLiteEnable()) {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo() isHmsLiteEnable.");
            this.m = false;
        }
        this.n = false;
        if (!this.m) {
            a(webViewActivity, baseReq);
            this.n = true;
        } else {
            bd.a().a(webViewActivity, baseReq, null, bfVar, new ResultCallback() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda0
                @Override // com.huawei.hms.support.api.client.ResultCallback
                public final void onResult(Object obj) {
                    s.this.a(webViewActivity, bfVar, (PayResult) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(WebViewActivity webViewActivity, bf bfVar, PayResult payResult) {
        Status status = payResult.getStatus();
        HwLog.i("HwWatchFacePayManager", "payForVip, status :" + status);
        if (status.getStatusCode() == 0 && this.d != null) {
            try {
                HwLog.i("HwWatchFacePayManager", "Status Code : PayStatusCodes.PAY_STATE_SUCCESS, before starting payment activity for result..");
                status.startResolutionForResult(webViewActivity, 104);
                return;
            } catch (IntentSender.SendIntentException unused) {
                HwLog.e("HwWatchFacePayManager", "payForVip SendIntentException");
                bfVar.onResult(-1, null);
                return;
            }
        }
        HwLog.i("HwWatchFacePayManager", "Status Code : Not Success, PAY_STATE_FAILED");
        bfVar.onResult(-1, null);
    }

    private void a(Activity activity, BaseReq baseReq) {
        Task<PayResult> task;
        ej.a(this.f).a("isfullsdk", "true");
        if (this.c == null) {
            HwLog.e("HwWatchFacePayManager", "payForSingleVip mOperateWatchFaceCallback is null");
            this.r.onResult(-1, null);
            return;
        }
        if (activity == null) {
            HwLog.e("HwWatchFacePayManager", "payForSingleVip -- activity is null");
            this.r.onResult(-1, null);
            ej.a(this.f, -1, "fullsdk订单参数错误");
            return;
        }
        HwLog.i("HwWatchFacePayManager", "payForSingleVip getPayClient start");
        PayClient payClient = Pay.getPayClient(activity);
        if (baseReq instanceof PayReq) {
            HwLog.i("HwWatchFacePayManager", "In PayReq");
            task = payClient.pay((PayReq) baseReq);
        } else if (baseReq instanceof WithholdRequest) {
            HwLog.i("HwWatchFacePayManager", "In WithholdRequest");
            task = payClient.addWithholdingPlan((WithholdRequest) baseReq);
        } else {
            task = null;
        }
        if (task == null) {
            HwLog.e("HwWatchFacePayManager", "payForSingleVip task is null.");
            this.r.onResult(-1, null);
            ej.a(this.f, -1, "fullsdk订单参数错误");
        } else {
            HwLog.i("HwWatchFacePayManager", "payForSingleVip getPayClient end");
            final WebViewActivity webViewActivity = (WebViewActivity) activity;
            task.addOnSuccessListener(new OnSuccessListener<PayResult>() { // from class: com.huawei.watchface.s.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(PayResult payResult) {
                    HwLog.i("HwWatchFacePayManager", "payForSingleVip -- payResult = " + GsonHelper.toJson(payResult));
                    s.this.a(payResult, webViewActivity, 105);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.watchface.s.4
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    HwLog.i("HwWatchFacePayManager", "payForSingleVip onFailure: " + HwLog.printException(exc));
                    s.this.r.onResult(-1, null);
                    ej.a(s.this.f, -1, "fullsdk订单参数错误");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitPayWatchFaceResult(str, str2, str3);
        }
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(InstallWatchFaceBean installWatchFaceBean) {
        HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo: enter");
        if (installWatchFaceBean == null) {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo() watchFacePayInfo is null");
            l();
            return;
        }
        if (!ej.d().containsKey(installWatchFaceBean.getWatchFaceHiTopId())) {
            ej.d().put(installWatchFaceBean.getWatchFaceHiTopId(), d(installWatchFaceBean));
        }
        j();
        if (c(installWatchFaceBean)) {
            return;
        }
        String watchFaceHiTopId = installWatchFaceBean.getWatchFaceHiTopId();
        String version = installWatchFaceBean.getVersion();
        String couponId = installWatchFaceBean.getCouponId();
        this.f = watchFaceHiTopId;
        this.g = version;
        this.k = couponId;
        if (a(installWatchFaceBean, watchFaceHiTopId)) {
            return;
        }
        a(installWatchFaceBean, watchFaceHiTopId, couponId);
    }

    private void a(InstallWatchFaceBean installWatchFaceBean, String str, String str2) {
        WatchFaceSignBean a2 = WatchFaceHttpUtil.a();
        String productId = installWatchFaceBean.getProductId();
        if (a2 != null && !TextUtils.isEmpty(productId)) {
            String versionCode = installWatchFaceBean.getVersionCode();
            String price = installWatchFaceBean.getPrice();
            String showDialog = installWatchFaceBean.getShowDialog();
            HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo() showDialog is: " + showDialog);
            if ("0".equals(showDialog)) {
                l();
                ej.a(this.f, -1, "提示表盘需要更新");
                a(productId, price, installWatchFaceBean.getSymbolType(), str2, versionCode);
                return;
            } else {
                if ("1".equals(showDialog)) {
                    a(productId, price, str2, versionCode);
                    return;
                }
                HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo() showDialog is unknown");
                ej.a(str, -1, "H5参数错误");
                l();
                return;
            }
        }
        e(installWatchFaceBean);
    }

    private boolean a(InstallWatchFaceBean installWatchFaceBean, String str) {
        if (HwWatchFaceApi.getInstance(this.d).isLogin() && HwWatchFaceApi.getInstance(this.d).isLoginParamSuitable()) {
            return false;
        }
        HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo() not login");
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback != null) {
            ap.a(operateWatchFaceCallback.getCustomWebViewContext()).a(2, installWatchFaceBean);
        }
        l();
        ej.a(str, -1, "华为账号未登录");
        return true;
    }

    private boolean c(InstallWatchFaceBean installWatchFaceBean) {
        this.n = false;
        boolean isThreePay = installWatchFaceBean.isThreePay();
        HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo hasHms ==" + this.m + " ,isThreePay =" + isThreePay);
        if (isThreePay) {
            if (!this.m) {
                HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo isThreePay hasHms is false ");
                this.n = true;
            }
        } else if (!this.m) {
            HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo showInstallHmsTip ");
            a(false);
            return true;
        }
        HwLog.i("HwWatchFacePayManager", "getWatchFacePayInfo hasHms ==" + this.m + " ,isThreePay =" + isThreePay + " ,isFullsdk =" + this.n);
        return false;
    }

    private void j() {
        this.m = true;
        if (cu.d()) {
            if (CommonUtils.m(this.d)) {
                return;
            }
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo() isNewHonor.");
            this.m = false;
            return;
        }
        if (HwWatchFaceApi.getInstance(this.d).isHmsLiteEnable()) {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo() isHmsLiteEnable.");
            this.m = false;
        }
    }

    private ej d(InstallWatchFaceBean installWatchFaceBean) {
        return new ej().a(System.currentTimeMillis()).l(installWatchFaceBean.getWatchFaceHiTopId()).b("0").a("hitopid", installWatchFaceBean.getWatchFaceHiTopId()).a("version", installWatchFaceBean.getVersion()).a(ParsedFieldTag.PRICE, installWatchFaceBean.getPrice()).a("screen", installWatchFaceBean.getWatchScreen()).a("couponId", installWatchFaceBean.getCouponId());
    }

    public void d() {
        HwLog.i("HwWatchFacePayManager", "doFullSdkWatchFacePay enter");
        ej.a(this.f).a("isfullsdk", "true");
        WatchFaceOrderBean watchFaceOrderBean = this.o;
        if (watchFaceOrderBean == null) {
            HwLog.w("HwWatchFacePayManager", "mWatchFaceOrderBean is null");
            l();
            ej.a(this.f, -1, "fullsdk订单参数错误");
            return;
        }
        PayReq a2 = a(watchFaceOrderBean);
        HwLog.i("HwWatchFacePayManager", "doFullSdkWatchFacePay makePayReqpayReq");
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback == null) {
            HwLog.e("HwWatchFacePayManager", "doFullSdkWatchFacePay mOperateWatchFaceCallback is null");
            return;
        }
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        final WebViewActivity webViewActivity = customWebViewContext instanceof WebViewActivity ? (WebViewActivity) customWebViewContext : null;
        if (webViewActivity == null) {
            HwLog.e("HwWatchFacePayManager", "doFullSdkWatchFacePay activity is null");
            l();
            ej.a(this.f, -1, "fullsdk获取上下文错误");
        } else {
            HwLog.i("HwWatchFacePayManager", "doFullSdkWatchFacePay getPayClient start");
            Task<PayResult> pay = Pay.getPayClient((Activity) webViewActivity).pay(a2);
            HwLog.i("HwWatchFacePayManager", "doFullSdkWatchFacePay getPayClient end");
            pay.addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda7
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    s.this.a(webViewActivity, (PayResult) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda8
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    s.this.a(exc);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(WebViewActivity webViewActivity, PayResult payResult) {
        a(payResult, webViewActivity, 103);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Exception exc) {
        HwLog.i("HwWatchFacePayManager", "doFullSdkWatchFacePay onFailure: " + HwLog.printException(exc));
        l();
        ej.a(this.f, -1, "fullsdk拉起支付失败");
    }

    public void a(final boolean z) {
        if (!z) {
            l();
        }
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback == null) {
            HwLog.w("HwWatchFacePayManager", "showInstallHmsTip() mOperateWatchFaceCallback is null");
            return;
        }
        final Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w("HwWatchFacePayManager", "showInstallHmsTip() context error");
        } else {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.s.6
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        cn a2 = new cn.a(customWebViewContext).a(DensityUtil.getStringById(R$string.IDS_hw_hms_lite_install_alert)).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.s.6.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                HwLog.i("HwWatchFacePayManager", "showInstallHmsTip() cancel.isTryOutTip");
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        }).a(R$string.IDS_hw_watchface_go_hms_install, new View.OnClickListener() { // from class: com.huawei.watchface.s.6.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                HwLog.i("HwWatchFacePayManager", "showInstallHmsTip() ok.isTryOutTip");
                                s.this.a(s.this.d, (Activity) customWebViewContext);
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        }).a();
                        a2.setCancelable(false);
                        a2.show();
                    } else {
                        cm a3 = new cm.a(customWebViewContext).a(R$string.IDS_pay_failed).b(DensityUtil.getStringById(R$string.IDS_hw_hms_lite_install_alert)).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.s.6.4
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                HwLog.i("HwWatchFacePayManager", "showInstallHmsTip() cancel");
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        }).a(R$string.IDS_hw_watchface_go_hms_install, new View.OnClickListener() { // from class: com.huawei.watchface.s.6.3
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                HwLog.i("HwWatchFacePayManager", "showInstallHmsTip() ok");
                                s.this.a(s.this.d, (Activity) customWebViewContext);
                                ViewClickInstrumentation.clickOnView(view);
                            }
                        }).a();
                        a3.setCancelable(false);
                        a3.show();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, final Activity activity) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback == null) {
            return;
        }
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w("HwWatchFacePayManager", "obtainToken() context error");
        } else {
            final ag agVar = new ag((Activity) customWebViewContext);
            agVar.a(context, new HuaweiApiClient.ConnectionCallbacks() { // from class: com.huawei.watchface.s.7
                @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
                public void onConnected() {
                    HwLog.i("HwWatchFacePayManager", "obtainToken() mApiClientConnectionCallbacks() aToken");
                    agVar.a();
                }

                @Override // com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks
                public void onConnectionSuspended(int i) {
                    HwLog.i("HwWatchFacePayManager", "obtainToken() mApiClientConnectionCallbacks() onConnectionSuspended");
                    agVar.a();
                }
            }, new HuaweiApiClient.OnConnectionFailedListener() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda11
                @Override // com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener
                public final void onConnectionFailed(ConnectionResult connectionResult) {
                    s.a(ag.this, activity, connectionResult);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(ag agVar, Activity activity, ConnectionResult connectionResult) {
        agVar.a();
        HwLog.i("HwWatchFacePayManager", "obtainToken() onConnectionFailed, ErrorCode: " + connectionResult.getErrorCode());
        if (activity == null) {
            HwLog.i("HwWatchFacePayManager", "obtainToken() activity is null, return! ");
            return;
        }
        int errorCode = connectionResult.getErrorCode();
        if (errorCode == 6) {
            ds.a(R$string.IDS_watchface_pay_failed);
        }
        HuaweiApiAvailability huaweiApiAvailability = HuaweiApiAvailability.getInstance();
        if (huaweiApiAvailability.isUserResolvableError(errorCode)) {
            HwLog.i("HwWatchFacePayManager", "obtainToken() availability.isUserResolvableError(errorCode):true ");
            huaweiApiAvailability.resolveError(activity, errorCode, 2000);
        }
    }

    private void a(String str, String str2, String str3, String str4, String str5) {
        String str6;
        String stringById = DensityUtil.getStringById(R$string.IDS_watchface_watch_pay_dialog_content);
        if (!TextUtils.isEmpty(str3)) {
            str6 = String.format(Locale.ENGLISH, stringById, str3 + str2);
        } else {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo mPaySymbolType is null!");
            str6 = "";
        }
        b(str, str2, str6, str4, str5);
    }

    private void b(final String str, final String str2, final String str3, final String str4, final String str5) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback == null) {
            HwLog.w("HwWatchFacePayManager", "getWatchFacePayInfo mOperateWatchFaceCallback is null");
            return;
        }
        final Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w("HwWatchFacePayManager", "showNoTitleCustomAlertDialog context error");
        } else {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    s.this.a(customWebViewContext, str3, str, str2, str4, str5);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Context context, String str, final String str2, final String str3, final String str4, final String str5) {
        cn.a aVar = new cn.a(context);
        aVar.a(str).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                s.lambda$onClick$hianalytics1(s.this, view);
            }
        }).a(R$string.buy_now, new View.OnClickListener() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                s.lambda$onClick$hianalytics2(s.this, str2, str3, str4, str5, view);
            }
        });
        cn a2 = aVar.a();
        this.p = a2;
        if (a2.isShowing() || ((Activity) context).isFinishing()) {
            return;
        }
        HwLog.i("HwWatchFacePayManager", "showNoTitleCustomAlertDialog start show");
        this.p.show();
    }

    private /* synthetic */ void a(View view) {
        HwLog.i("HwWatchFacePayManager", "showNoTitleCustomAlertDialog click cancel");
        this.p.dismiss();
    }

    private /* synthetic */ void a(String str, String str2, String str3, String str4, View view) {
        HwLog.i("HwWatchFacePayManager", "showNoTitleCustomAlertDialog click pay");
        a(str, str2, str3, str4);
    }

    private void e(InstallWatchFaceBean installWatchFaceBean) {
        HwLog.i("HwWatchFacePayManager", "handleNullWatchFaceSignBean() enter watchFaceSignBean is null. ");
        bo boVar = new bo();
        if (boVar.c(boVar.c()) == null) {
            HwLog.w("HwWatchFacePayManager", "handleNullWatchFaceSignBean watchFaceSignBean is null.");
            l();
        } else {
            b(installWatchFaceBean);
        }
    }

    private void a(final String str, final String str2, final String str3, final String str4) {
        HwLog.i("HwWatchFacePayManager", "startWatchFacePayThread: enter");
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b(str, str2, str3, str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2, String str3, String str4) {
        if (this.n) {
            String b2 = bd.b();
            this.l = b2;
            if (TextUtils.isEmpty(b2)) {
                HwLog.i("HwWatchFacePayManager", "mReservedInfor empty ");
                a(false);
                return;
            }
        } else {
            this.l = "";
        }
        bx bxVar = new bx(str, str2, str3, this.l, str4);
        WatchFaceOrderBean c = bxVar.c(bxVar.c());
        boolean equals = "0".equals(c.getResultCode());
        HwLog.i("HwWatchFacePayManager", "isSuccess === " + equals + " ,resultCode == " + c.getResultCode());
        ej.a(this.f).a("orderRequestTraceid", bxVar.d()).a("createOrderCode", c.getResultCode()).a(ParsedFieldTag.PRICE, str2).a("couponid", str3).a("productId", str).a("createOrderResult", c.getResultInfo());
        if (equals) {
            this.o = c;
            if (c != null) {
                HwLog.i("HwWatchFacePayManager", "startWatchFaceBatchPayThread: mWatchFaceOrderBean is not null ");
                this.h = this.o.getTradeId();
                this.k = this.o.mCouponId;
            }
            a(str);
            try {
                double a2 = dh.a(str2, 0.0d);
                HwLog.i("HwWatchFacePayManager", "amountStr : " + str2);
                boolean z = Double.compare(a2, 0.0d) == 0;
                HwLog.i("HwWatchFacePayManager", "isPayFree: " + z);
                if (z) {
                    a("0", "");
                    l();
                    ej.a(this.f).a("isPayFree", "true");
                    ej.a(this.f, 0, null);
                    return;
                }
            } catch (NumberFormatException e) {
                HwLog.i("HwWatchFacePayManager", "buyProduct : " + HwLog.printException((Exception) e));
            }
            this.o = c;
            if (c != null) {
                HwLog.i("HwWatchFacePayManager", "startWatchFaceBatchPayThread: mWatchFaceOrderBean is not null ");
                this.h = this.o.getTradeId();
                ej.a(this.f).a("tradeId", this.h).a("payRequestId", c.getRequestParams().getRequestId());
            }
            HwLog.i("HwWatchFacePayManager", "success about to selectWatchFacePay: ");
            e();
            return;
        }
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitPayWatchFaceResult(this.f, this.g, "orderProduct_" + c.getResultCode());
        }
        HwLog.i("HwWatchFacePayManager", "fail about to transmitFinishPay: ");
        ej.a(this.f, -1, "接口生成订单失败");
        l();
    }

    public void e() {
        if (this.n) {
            d();
        } else {
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        WatchFaceOrderBean watchFaceOrderBean = this.o;
        if (watchFaceOrderBean == null) {
            HwLog.w("HwWatchFacePayManager", "mWatchFaceOrderBean is null");
            l();
            ej.a(this.f, -1, "hms支付参数为空");
            return;
        }
        PayReq a2 = a(watchFaceOrderBean);
        if (this.e == null) {
            f();
        }
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback == null) {
            HwLog.e("HwWatchFacePayManager", "doWatchFacePay mOperateWatchFaceCallback is null");
            return;
        }
        Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        final WebViewActivity webViewActivity = customWebViewContext instanceof WebViewActivity ? (WebViewActivity) customWebViewContext : null;
        if (webViewActivity == null) {
            HwLog.e("HwWatchFacePayManager", "doWatchFacePay activity is null");
            l();
            ej.a(this.f, -1, "hms支付上下文为空");
        } else if (this.e.isConnected()) {
            HuaweiPay.HuaweiPayApi.pay(this.e, a2).setResultCallback(new ResultCallback<PayResult>() { // from class: com.huawei.watchface.s.8
                @Override // com.huawei.hms.support.api.client.ResultCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onResult(PayResult payResult) {
                    s.this.a(payResult, webViewActivity, 101);
                }
            });
        } else {
            HwLog.w("HwWatchFacePayManager", "Connect failed, please connect first.");
            this.e.connect(webViewActivity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(PayResult payResult, WebViewActivity webViewActivity, int i) {
        if (payResult == null) {
            HwLog.w("HwWatchFacePayManager", "doWatchFacePay payResult is null");
            ej.a(this.f, -1, "拉起支付页面参数异常");
            l();
            return;
        }
        Status status = payResult.getStatus();
        HwLog.i("HwWatchFacePayManager", "applyIssueOrderCallback, apply status :" + status);
        if (status.getStatusCode() == 0 && this.d != null) {
            try {
                status.startResolutionForResult(webViewActivity, i);
                return;
            } catch (IntentSender.SendIntentException unused) {
                HwLog.e("HwWatchFacePayManager", "doWatchFacePay SendIntentException");
                l();
                ej.a(this.f, -1, "拉起支付页面异常");
                return;
            }
        }
        l();
        ej.a(this.f, -1, "拉起支付失败，错误码：" + status.getStatusCode());
    }

    private PayReq a(WatchFaceOrderBean watchFaceOrderBean) {
        Double d;
        HwLog.i("HwWatchFacePayManager", "makePayReq enter ");
        PayReq payReq = new PayReq();
        payReq.productName = String.valueOf(watchFaceOrderBean.getRequestParams().getProductName());
        payReq.productDesc = String.valueOf(watchFaceOrderBean.getRequestParams().getProductDescription());
        payReq.merchantId = String.valueOf(watchFaceOrderBean.getRequestParams().getMerchantId());
        payReq.applicationID = String.valueOf(watchFaceOrderBean.getRequestParams().getApplicationId());
        payReq.amount = String.valueOf(watchFaceOrderBean.getRequestParams().getAmount());
        if (!TextUtils.isEmpty(payReq.amount)) {
            try {
                d = Double.valueOf(payReq.amount);
            } catch (NumberFormatException unused) {
                HwLog.e("HwWatchFacePayManager", "makePayReq NumberFormatException");
                d = null;
            }
            payReq.amount = String.format(Locale.ENGLISH, "%.2f", d);
        }
        payReq.country = String.valueOf(watchFaceOrderBean.getRequestParams().getCountry());
        payReq.requestId = String.valueOf(watchFaceOrderBean.getRequestParams().getRequestId());
        payReq.url = String.valueOf(watchFaceOrderBean.getRequestParams().getUrl());
        payReq.urlVer = String.valueOf(watchFaceOrderBean.getRequestParams().getUrlVersion());
        payReq.merchantName = String.valueOf(watchFaceOrderBean.getRequestParams().getMerchantName());
        payReq.sdkChannel = 0;
        payReq.serviceCatalog = String.valueOf(watchFaceOrderBean.getRequestParams().getServiceCatalog());
        payReq.sign = watchFaceOrderBean.getPaySign();
        if (!TextUtils.isEmpty(watchFaceOrderBean.getRequestParams().getCurrency())) {
            payReq.currency = watchFaceOrderBean.getRequestParams().getCurrency();
        }
        String c = c(watchFaceOrderBean.mCouponId);
        if (!TextUtils.isEmpty(c)) {
            payReq.extReserved = c;
        }
        if (!TextUtils.isEmpty(watchFaceOrderBean.mReservedInfor)) {
            payReq.reservedInfor = watchFaceOrderBean.mReservedInfor;
        }
        return payReq;
    }

    public String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("couponId", str);
        } catch (JSONException e) {
            HwLog.i("HwWatchFacePayManager", "generateExtReservedParam error : " + HwLog.printException((Exception) e));
        }
        return jSONObject.toString();
    }

    public void f() {
        this.e = new HuaweiApiClient.Builder(Environment.getApplicationContext()).addApi(HuaweiPay.PAY_API).addConnectionCallbacks(this.x).addOnConnectionFailedListener(this.w).build();
    }

    public void a(final String str, final String str2) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitPayWatchFaceResult(this.f, this.g, str);
        }
        HwLog.i("HwWatchFacePayManager", "start upload pay result");
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.s.9
            @Override // java.lang.Runnable
            public void run() {
                by byVar = new by(s.this.i, s.this.h, str, str2);
                if (byVar.c(byVar.a("")).intValue() == 0) {
                    HwLog.i("HwWatchFacePayManager", "upload pay result success,refresh h5");
                }
            }
        });
        if (String.valueOf(0).equals(str)) {
            t.a().b(this.f, this.g);
        }
    }

    public void b(PayResultInfo payResultInfo) {
        if (this.r != null) {
            HwLog.i("HwWatchFacePayManager", "subscriptionPayResultListener before call");
            this.r.onResult(payResultInfo.getReturnCode(), payResultInfo);
        } else {
            HwLog.i("HwWatchFacePayManager", "subscriptionPayResultListener is null");
        }
    }

    public String g() {
        WatchFaceSignBean a2 = WatchFaceHttpUtil.a();
        if (a2 == null) {
            HwLog.w("HwWatchFacePayManager", "getWatchFaceSignBean watchFaceSignBean is null");
            return "";
        }
        return dx.a().a(a2);
    }

    @Deprecated
    public void d(String str) {
        HwLog.w("HwWatchFacePayManager", "uploadWatchFacePayEvent() status: " + str);
        ej.a(this.f, dh.a(str, -1), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.c;
        if (operateWatchFaceCallback == null) {
            return;
        }
        operateWatchFaceCallback.transmitFinishPay("");
    }

    public void a(final MarketH5Bean marketH5Bean, final MagicJsPayResult magicJsPayResult, final bf bfVar) {
        this.t = null;
        HwLog.i("HwWatchFacePayManager", "payVip payReq enter");
        if (TextUtils.isEmpty(marketH5Bean.getProductId())) {
            HwLog.e("HwWatchFacePayManager", "payVip productId is null");
            bfVar.onResult(30001, null);
        } else {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    s.this.b(marketH5Bean, magicJsPayResult, bfVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(MarketH5Bean marketH5Bean, MagicJsPayResult magicJsPayResult, bf bfVar) {
        bq bqVar = new bq(marketH5Bean.getProductId());
        VipPackageBean c = bqVar.c(bqVar.a(""));
        magicJsPayResult.copyInfo(c);
        a(marketH5Bean, c, bfVar);
    }

    public void a(final VipPayInfoCoupons vipPayInfoCoupons, final bf bfVar, final bg bgVar) {
        HwLog.i("HwWatchFacePayManager", "payForVipNew payReq enter");
        if (TextUtils.isEmpty(vipPayInfoCoupons.getProductCode())) {
            HwLog.e("HwWatchFacePayManager", "payForVipNew productId is null");
            bfVar.onResult(30001, null);
        } else {
            BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    s.this.b(vipPayInfoCoupons, bfVar, bgVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(VipPayInfoCoupons vipPayInfoCoupons, bf bfVar, bg bgVar) {
        bq bqVar = new bq(vipPayInfoCoupons.getProductCode());
        bqVar.b(vipPayInfoCoupons.getSign());
        bqVar.e(vipPayInfoCoupons.getDeviceModel());
        a(vipPayInfoCoupons, bqVar.c(bqVar.a("")), bfVar, bgVar);
    }

    private void a(MarketH5Bean marketH5Bean, VipPackageBean vipPackageBean, bf bfVar) {
        if (vipPackageBean == null) {
            HwLog.e("HwWatchFacePayManager", "goTopay vipPackageBean is null");
            bfVar.onResult(30001, null);
            ds.a(DensityUtil.getStringById(R$string.membership_not_exist));
            return;
        }
        String productCode = vipPackageBean.getProductCode();
        this.j = productCode;
        ej.a(productCode, vipPackageBean);
        if (!TextUtils.isEmpty(vipPackageBean.getDiscountPrice())) {
            if (CommonUtils.i(vipPackageBean.getDiscountPrice())) {
                HwLog.i("HwWatchFacePayManager", "goToPay discount price is zero");
                a(vipPackageBean, bfVar);
                return;
            }
        } else if (CommonUtils.i(vipPackageBean.getPrice())) {
            HwLog.i("HwWatchFacePayManager", "goToPay price is zero");
            a(vipPackageBean, bfVar);
            return;
        }
        bn bnVar = new bn(vipPackageBean.getProductCode());
        CouponsByproductBean c = bnVar.c(bnVar.a(""));
        if (c == null) {
            HwLog.i("HwWatchFacePayManager", "couponsByproductBean is null");
            a(vipPackageBean, bfVar);
            return;
        }
        if (!TextUtils.equals("0", c.getResultcode())) {
            HwLog.i("HwWatchFacePayManager", "couponsByproductBean get Result code is not 0 resultCode：" + c.getResultcode());
            a(vipPackageBean, bfVar);
            return;
        }
        if (c.getCouponAndPrice() != null && c.getCouponAndPrice().size() > 0) {
            String vipCouponsUrl = HwWatchFaceApi.getInstance(this.d).getVipCouponsUrl(TextUtils.isEmpty(marketH5Bean.getFrom()) ? "campaign.h5.1005" : marketH5Bean.getFrom(), TextUtils.isEmpty(marketH5Bean.getFromSub()) ? "aop" : marketH5Bean.getFromSub());
            this.u = c;
            this.s = vipPackageBean;
            this.v = bfVar;
            ej.a(this.j).a("showH5Coupons", "true");
            a(WebViewActivity.getActivity(), vipCouponsUrl);
            return;
        }
        HwLog.i("HwWatchFacePayManager", "couponsByproductBean couponAndPrice is empty");
        a(vipPackageBean, bfVar);
    }

    public String h() {
        if (this.u == null || this.s == null) {
            HwLog.i("HwWatchFacePayManager", "mCouponsByproductBean or mVipPackageBean is null");
            return "";
        }
        return GsonHelper.toJson(new CouponsAndVipPackageBean(this.s, this.u));
    }

    private void a(VipPackageBean vipPackageBean, bf bfVar) {
        this.l = bd.b();
        bk bkVar = new bk(vipPackageBean, this.l);
        a(bkVar.c(bkVar.c()), bfVar, (String) null, true);
    }

    public void a(CouponsByproductBean.CouponAndPrice couponAndPrice) {
        if (couponAndPrice == null) {
            HwLog.i("HwWatchFacePayManager", "goToPayVipByCoupon couponAndPrice is null");
            a(this.s, this.v);
            return;
        }
        this.l = bd.b();
        VipPayInfoCoupons vipPayInfoCoupons = new VipPayInfoCoupons();
        vipPayInfoCoupons.copyInfo(this.s);
        vipPayInfoCoupons.setCouponId(couponAndPrice.getCouponMap() != null ? couponAndPrice.getCouponMap().getCouponId() : "");
        vipPayInfoCoupons.setOrderChannel(DetailedCreativeType.LONG_TEXT);
        vipPayInfoCoupons.setReportedPrice(couponAndPrice.getRealPrice());
        if (!TextUtils.isEmpty(this.s.getRelProductCode())) {
            vipPayInfoCoupons.setOrderType("2");
        } else {
            vipPayInfoCoupons.setOrderType("1");
        }
        ej.a(this.j).a("couponId", vipPayInfoCoupons.getCouponId());
        bk bkVar = new bk(vipPayInfoCoupons, this.l);
        VipOrderBean c = bkVar.c(bkVar.c());
        if (this.v == null) {
            HwLog.i("HwWatchFacePayManager", "mCouponsPayResultListener is null");
            return;
        }
        if (c != null) {
            this.h = c.getPayInfo() != null ? c.getPayInfo().getRequestId() : "";
        }
        a(c, this.v, vipPayInfoCoupons.getCouponId(), true);
    }

    private void a(VipPayInfoCoupons vipPayInfoCoupons, VipPackageBean vipPackageBean, bf bfVar, bg bgVar) {
        this.l = bd.b();
        vipPayInfoCoupons.copyInfo(vipPackageBean);
        bk bkVar = new bk(vipPayInfoCoupons, this.l);
        bkVar.b(vipPayInfoCoupons.getSign());
        bkVar.e(vipPayInfoCoupons.getDeviceModel());
        VipOrderBean c = bkVar.c(bkVar.c());
        if (bgVar != null) {
            bgVar.onResult(c, vipPayInfoCoupons);
        }
        if (c != null) {
            this.h = c.getPayInfo() != null ? c.getPayInfo().getRequestId() : "";
        }
        this.t = vipPayInfoCoupons;
        a(c, bfVar, vipPayInfoCoupons != null ? vipPayInfoCoupons.getCouponId() : null, false);
    }

    private void a(VipOrderBean vipOrderBean, bf bfVar, String str, boolean z) {
        HwLog.i("HwWatchFacePayManager", "goToPayVip  isFromMagic：" + z);
        this.k = str;
        if (vipOrderBean == null) {
            HwLog.e("HwWatchFacePayManager", "payVip vipOrderBean is null");
            bfVar.onResult(30001, null);
            ej.a(this.j, -1, "创建订单失败");
            return;
        }
        try {
            if (!TextUtils.equals(vipOrderBean.getResultcode(), "00000")) {
                if (z) {
                    a(Integer.parseInt(vipOrderBean.getResultcode()));
                }
                bfVar.onResult(Integer.parseInt(vipOrderBean.getResultcode()), null);
                ej.a(this.j, -1, "创建订单失败");
                return;
            }
            PayInfo payInfo = vipOrderBean.getPayInfo();
            if (payInfo == null) {
                HwLog.e("HwWatchFacePayManager", "payVip payInfo is null");
                bfVar.onResult(30001, null);
                ds.a(DensityUtil.getStringById(R$string.membership_not_exist));
                ej.a(this.j, -1, "创建订单失败");
                return;
            }
            ej.a(this.j).a(HwPayConstant.KEY_AMOUNT, payInfo.getAmount());
            VipPayInfo vipPayInfo = (VipPayInfo) GsonHelper.fromJson(GsonHelper.toJson(payInfo), VipPayInfo.class);
            if (vipPayInfo == null) {
                HwLog.e("HwWatchFacePayManager", "payVip payReq is null");
                bfVar.onResult(30001, null);
                ds.a(DensityUtil.getStringById(R$string.membership_not_exist));
                ej.a(this.j, -1, "创建订单失败");
                return;
            }
            if (CommonUtils.i(payInfo.getAmount())) {
                HwLog.i("HwWatchFacePayManager", "payInfo amount is zero");
                t.a().c();
                PayResultInfo payResultInfo = new PayResultInfo();
                payResultInfo.setReturnCode(0);
                payResultInfo.setAmount(payInfo.getAmount());
                bfVar.onResult(payResultInfo.getReturnCode(), payResultInfo);
                ej.a(this.j, 0, "创建成功");
                return;
            }
            HwLog.i("HwWatchFacePayManager", "goToPay  running");
            a(an.b(vipPayInfo.getTradeType()) ^ true ? vipPayInfo.getWithholdRequest(vipPayInfo) : vipPayInfo.getPayInfoReq(vipPayInfo), bfVar);
        } catch (Exception e) {
            HwLog.d("HwWatchFacePayManager", "goToPay Exception:" + HwLog.printException(e));
            ds.a(DensityUtil.getStringById(R$string.member_service_error));
        }
    }

    private void a(int i) {
        if (i != 14000) {
            if (i == 14003) {
                ds.a(DensityUtil.getStringById(R$string.vip_member_years));
                return;
            } else if (i != 14020) {
                if (i == 131013) {
                    ds.a(DensityUtil.getStringById(R$string.vip_purchase_limit));
                    return;
                } else {
                    ds.a(DensityUtil.getStringById(R$string.member_service_error));
                    return;
                }
            }
        }
        ds.a(DensityUtil.getStringById(R$string.member_takeshelf_tips));
    }

    public void a(ConnectionResult connectionResult) {
        a(connectionResult, this.j);
    }

    public void b(ConnectionResult connectionResult) {
        a(connectionResult, this.f);
    }

    private void a(ConnectionResult connectionResult, String str) {
        if (connectionResult != null) {
            ej.a(str).a("hmsConnectErrorCode", String.valueOf(connectionResult.getErrorCode())).a("hmsConnectErrorMsg", String.valueOf(connectionResult.getErrorMessage()));
        }
        ej.a(str, 30001, "Hms建连失败");
    }

    public void a(Context context, String str) {
        HwLog.i("HwWatchFacePayManager", "showPayVipDialog() enter.");
        try {
            if (context == null) {
                HwLog.i("HwWatchFacePayManager", "context is null");
                return;
            }
            if (this.q == null) {
                this.q = new BottomVipPayDialogFragment(context);
            }
            if (!(context instanceof FragmentActivity)) {
                HwLog.i("HwWatchFacePayManager", "context is not FragmentActivity");
                return;
            }
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            if (fragmentActivity.isFinishing()) {
                HwLog.i("HwWatchFacePayManager", "activity isFinishing");
                return;
            }
            this.q.a(str);
            if (!this.q.isVisible()) {
                this.q.show(fragmentActivity.getSupportFragmentManager(), "");
            } else {
                HwLog.i("HwWatchFacePayManager", "bottomVipPayDialogFragment  is visible");
            }
        } catch (Exception e) {
            HwLog.e("HwWatchFacePayManager", "showPayVipDialog  Exception:" + HwLog.printException(e));
        }
    }

    public void b(Context context) {
        HwLog.i("HwWatchFacePayManager", "dismissPayVipDialog() enter.");
        try {
            if (context == null) {
                HwLog.d("HwWatchFacePayManager", "context is null");
            } else if (this.q == null) {
                HwLog.d("HwWatchFacePayManager", "bottomVipPayDialogFragment is null");
            } else {
                BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.s$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        s.this.m();
                    }
                });
            }
        } catch (Exception e) {
            HwLog.d("HwWatchFacePayManager", "dismissPayVipDialog  Exception:" + HwLog.printException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        this.q.dismiss();
    }

    static void lambda$onClick$hianalytics2(s sVar, String str, String str2, String str3, String str4, View view) {
        sVar.a(str, str2, str3, str4, view);
        ViewClickInstrumentation.clickOnView(view);
    }

    static void lambda$onClick$hianalytics1(s sVar, View view) {
        sVar.a(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
