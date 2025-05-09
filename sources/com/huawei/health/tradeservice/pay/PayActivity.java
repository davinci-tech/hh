package com.huawei.health.tradeservice.pay;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.tradeservice.cloud.OrderCreateRsp;
import com.huawei.health.tradeservice.cloud.OrderManager;
import com.huawei.health.tradeservice.cloud.PromotionInfoRsp;
import com.huawei.health.tradeservice.pay.PayActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ScenarioInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.iap.Iap;
import com.huawei.hms.iap.IapApiException;
import com.huawei.hms.iap.entity.OrderStatusCode;
import com.huawei.hms.iap.entity.ProductInfoReq;
import com.huawei.hms.iap.entity.ProductInfoResult;
import com.huawei.hms.iap.entity.PurchaseIntentReq;
import com.huawei.hms.iap.entity.PurchaseIntentResult;
import com.huawei.hms.iap.entity.PurchaseResultInfo;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.trade.datatype.PayRequest;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.bzs;
import defpackage.gku;
import defpackage.gkx;
import defpackage.gla;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jdq;
import defpackage.nsy;
import defpackage.sqt;
import health.compact.a.CommonLibUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes4.dex */
public class PayActivity extends BaseActivity {
    private static final ArrayList<Integer> c = new ArrayList<Integer>() { // from class: com.huawei.health.tradeservice.pay.PayActivity.2
        {
            add(0);
            add(1);
            add(2);
        }
    };
    private LinearLayout ad;
    private Context f;
    private String k;
    private String n;
    private HealthProgressBar q;
    private ProductInfo v;
    private final Handler m = new b(Looper.getMainLooper(), this);
    private IPayEnvCheckCallback p = new c(this);
    private HashMap<String, String> ae = new HashMap<>(0);
    private String ab = null;
    private String s = null;
    private CountDownLatch i = null;
    private CustomAlertDialog z = null;
    private int e = 0;
    private String w = null;
    private int r = 0;
    private String t = null;
    private String u = "";
    private String ac = "";
    private String x = "";
    private String o = "";
    private String l = "";
    private String h = "";
    private String aa = "";
    private String b = "";
    private String j = "";

    /* renamed from: a, reason: collision with root package name */
    private String f3458a = "";
    private String af = "";
    private String ai = "";
    private String ag = "";
    private String d = "";
    private String y = "";
    private int g = 0;

    private void a(gku gkuVar, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("R_PayActivity", "OrderRequest = " + gkuVar.toString());
        PurchaseIntentReq purchaseIntentReq = new PurchaseIntentReq();
        purchaseIntentReq.setProductId(gkuVar.a());
        purchaseIntentReq.setPriceType(gkuVar.c());
        purchaseIntentReq.setDeveloperPayload(gkuVar.d());
        purchaseIntentReq.setSignatureAlgorithm("SHA256WithRSA/PSS");
        ReservedInfor e = gkuVar.e();
        if (e != null) {
            String e2 = g() ? gla.e() : "";
            if (!TextUtils.isEmpty(e2)) {
                LogUtil.a("R_PayActivity", "setAccountInfo");
                e.setAccountInfo(e2);
            }
            purchaseIntentReq.setReservedInfor(gkuVar.e().toString());
            LogUtil.a("R_PayActivity", "allready set ReservedInfor = ", purchaseIntentReq.getReservedInfor());
        }
        e("iapCreateRequestOrder", d(purchaseIntentReq));
        Task<PurchaseIntentResult> createPurchaseIntent = Iap.getIapClient((Activity) this, gla.c()).createPurchaseIntent(purchaseIntentReq);
        ScenarioInstrumentation.iapCreatePurchaseIntent(purchaseIntentReq);
        if (createPurchaseIntent == null) {
            LogUtil.b("R_PayActivity", "requestOrder create task failed!!");
            iBaseResponseCallback.d(1007, null);
        } else {
            createPurchaseIntent.addOnSuccessListener(new OnSuccessListener<PurchaseIntentResult>() { // from class: com.huawei.health.tradeservice.pay.PayActivity.9
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(PurchaseIntentResult purchaseIntentResult) {
                    iBaseResponseCallback.d(0, purchaseIntentResult);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.tradeservice.pay.PayActivity.8
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (exc instanceof IapApiException) {
                        IapApiException iapApiException = (IapApiException) exc;
                        Status status = iapApiException.getStatus();
                        LogUtil.b("R_PayActivity", "requestOrder createPurchaseIntent returnCode = " + iapApiException.getStatusCode() + ", status = " + status);
                    } else {
                        LogUtil.b("R_PayActivity", "requestOrder Exception： " + exc.getClass().getSimpleName());
                    }
                    iBaseResponseCallback.d(1007, null);
                }
            });
        }
    }

    private boolean g() {
        if (sqt.b()) {
            LogUtil.a("R_PayActivity", "isNeedAccountInfo isClosedWebCash");
            return false;
        }
        if (CommonUtil.z(this.f)) {
            return Build.VERSION.SDK_INT > 27;
        }
        if (CommonUtil.ay()) {
            return false;
        }
        LogUtil.a("R_PayActivity", "isNeedAccountInfo hms unAvailable");
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> int b(T t) {
        if (!(t instanceof PurchaseIntentResult)) {
            LogUtil.b("R_PayActivity", "requestPay purchaseResult dont instanceof PurchaseIntentResult");
            return 1000;
        }
        Status status = ((PurchaseIntentResult) t).getStatus();
        if (status == null) {
            LogUtil.b("R_PayActivity", "status is null !!!");
            return 1000;
        }
        if (!status.hasResolution()) {
            return 1006;
        }
        try {
            status.startResolutionForResult(this, 6666);
            this.ad.setVisibility(8);
            return 0;
        } catch (IntentSender.SendIntentException unused) {
            LogUtil.b("R_PayActivity", "startResolutionForResult failed !!!");
            return 1006;
        }
    }

    private int aNy_(Intent intent) {
        PurchaseResultInfo parsePurchaseResultInfoFromIntent = Iap.getIapClient((Activity) this, gla.c()).parsePurchaseResultInfoFromIntent(intent);
        ScenarioInstrumentation.iapParsePurchaseResultInfo(parsePurchaseResultInfoFromIntent);
        if (parsePurchaseResultInfoFromIntent == null) {
            LogUtil.b("R_PayActivity", "checkPayResult purchaseResultInfo is null");
            return 1003;
        }
        LogUtil.a("R_PayActivity", "purchaseResultInfo.getReturnCode: ", Integer.valueOf(parsePurchaseResultInfoFromIntent.getReturnCode()));
        int returnCode = parsePurchaseResultInfoFromIntent.getReturnCode();
        if (returnCode != -1) {
            if (returnCode == 0) {
                this.n = parsePurchaseResultInfoFromIntent.getInAppPurchaseData();
                this.k = parsePurchaseResultInfoFromIntent.getInAppDataSignature();
                return 0;
            }
            switch (returnCode) {
                case 60000:
                    break;
                case OrderStatusCode.ORDER_STATE_CALLS_FREQUENT /* 60004 */:
                    LogUtil.b("R_PayActivity", "ORDER_STATE_CALLS_FREQUENT, maybe try later");
                    break;
                case OrderStatusCode.ORDER_PRODUCT_OWNED /* 60051 */:
                    break;
                case OrderStatusCode.ORDER_ACCOUNT_AREA_NOT_SUPPORTED /* 60054 */:
                    LogUtil.b("R_PayActivity", "ORDER_ACCOUNT_AREA_NOT_SUPPORTED, maybe try later");
                    break;
                default:
                    LogUtil.b("R_PayActivity", "purchaseResultInfo.getReturnCode: " + parsePurchaseResultInfoFromIntent.getReturnCode());
                    break;
            }
            return 1003;
        }
        gkx.e().e("");
        return 1009;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = this;
        setContentView(R.layout.layout_inprocess);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.ad = (LinearLayout) nsy.cMc_(this, R.id.hw_trade_loading_layout);
        HealthProgressBar healthProgressBar = (HealthProgressBar) nsy.cMc_(this, R.id.hw_trade_loading_view);
        this.q = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("R_PayActivity", "transaction abort, getIntent is null");
            a(1003);
            return;
        }
        if (!CommonUtil.aa(this.f)) {
            LogUtil.b("R_PayActivity", "transaction abort, No Network.");
            a(1005);
            return;
        }
        boolean isModuleOperatedToOversea = ((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isModuleOperatedToOversea("ai-vip-oversea-001");
        boolean equals = LoginInit.getInstance(this.f).getAccountInfo(1010).equals(LoginInit.getInstance(this.f).getAccountInfo(1014));
        if (isModuleOperatedToOversea && !equals) {
            LogUtil.b("R_PayActivity", "PayActivity abort, this is a vip, but country code do not match service Country code.");
            a(1016);
        } else {
            this.ad.setVisibility(0);
            aNB_(intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            LogUtil.b("R_PayActivity", "onNewIntent intent is null !!");
            a(1003);
        } else {
            this.ad.setVisibility(0);
            aNB_(intent);
        }
    }

    private void aNB_(final Intent intent) {
        ThreadPoolManager.d().c("R_PayActivity", new Runnable() { // from class: com.huawei.health.tradeservice.pay.PayActivity.7
            @Override // java.lang.Runnable
            public void run() {
                PayActivity.this.aNA_(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aNA_(Intent intent) {
        this.ae.clear();
        String stringExtra = intent.getStringExtra("payRequest");
        this.s = stringExtra;
        if (!TextUtils.isEmpty(stringExtra)) {
            LogUtil.a("R_PayActivity", "extracting info from payRequest...");
            PayRequest payRequest = (PayRequest) HiJsonUtil.e(this.s, PayRequest.class);
            this.ab = payRequest.getActivityPath();
            this.u = payRequest.getProductId();
            this.ac = payRequest.getPurchasePolicy();
        } else {
            LogUtil.a("R_PayActivity", "extracting info from ProductInfo...");
            String stringExtra2 = intent.getStringExtra("productInfo");
            this.w = stringExtra2;
            LogUtil.a("R_PayActivity", "mProductInfoStr = ", stringExtra2);
            try {
                ProductInfo productInfo = (ProductInfo) new Gson().fromJson(this.w, ProductInfo.class);
                this.v = productInfo;
                if (productInfo == null) {
                    return;
                }
                this.u = productInfo.getProductId();
                this.ac = String.valueOf(this.v.getPurchasePolicy());
                this.x = this.v.getPromotionId();
                this.o = this.v.getDeviceSn();
                this.l = this.v.getDeviceType();
                this.h = this.v.getDeviceNumber();
                this.aa = this.v.getSalt();
                this.b = this.v.getCertChain();
                this.j = this.v.getDevicePerfPurchaseId();
                this.f3458a = this.v.getClientVersion();
                this.af = this.v.getTrigResType();
                this.ai = this.v.getTrigResName();
                this.ag = this.v.getTrigResMemberPrice();
                this.d = this.v.getAreaInfo();
                this.r = this.v.getLinkType();
                this.t = this.v.getLinkValue();
                this.y = this.v.getPromotionPolicyId();
                this.g = this.v.getDeviceCategory();
            } catch (JsonSyntaxException unused) {
                LogUtil.b("R_PayActivity", "parse (JsonSyntaxException");
                return;
            }
        }
        a();
    }

    private boolean i() {
        if (TextUtils.isEmpty(this.ac)) {
            LogUtil.b("R_PayActivity", "transaction abort, purchasePolicy is null...");
            e(1008, (int) null);
            return false;
        }
        if (TextUtils.isEmpty(this.u)) {
            LogUtil.b("R_PayActivity", "transaction abort, productId is null...");
            e(1008, (int) null);
            return false;
        }
        if (!this.ac.equals(String.valueOf(Product.SUBSCRIPTION_PURCHASE_FLAG)) || LoginInit.getInstance(this.f).isAccountConsistent()) {
            return true;
        }
        LogUtil.b("R_PayActivity", "buy subscription, account isn't consistent");
        e(1011, (int) null);
        return false;
    }

    private void k() {
        this.ae.put("productId", this.u);
        this.ae.put("purchasePolicy", this.ac);
        if (!TextUtils.isEmpty(this.x)) {
            this.ae.put("promotionId", this.x);
        }
        if (!TextUtils.isEmpty(this.o)) {
            this.ae.put("deviceSN", this.o);
        }
        if (!TextUtils.isEmpty(this.l)) {
            this.ae.put("deviceType", this.l);
        }
        if (!TextUtils.isEmpty(this.h)) {
            this.ae.put("deviceNumber", this.h);
        }
        if (!TextUtils.isEmpty(this.aa)) {
            this.ae.put("salt", this.aa);
        }
        if (!TextUtils.isEmpty(this.b)) {
            this.ae.put("certChain", this.b);
        }
        if (!TextUtils.isEmpty(this.j)) {
            this.ae.put("devicePerfPurchaseId", this.j);
        }
        if (!TextUtils.isEmpty(this.f3458a)) {
            this.ae.put("clientVersion", this.f3458a);
        }
        if (!TextUtils.isEmpty(this.af)) {
            this.ae.put("trigResType", this.af);
        }
        if (!TextUtils.isEmpty(this.ag)) {
            this.ae.put("trigResMemberPrice", this.ag);
        }
        if (!TextUtils.isEmpty(this.ai)) {
            this.ae.put("trigResName", this.ai);
        }
        if (!TextUtils.isEmpty(this.d)) {
            this.ae.put("areaInfo", this.d);
        }
        if (!TextUtils.isEmpty(this.y)) {
            this.ae.put("promotionPolicyId", this.y);
        }
        int i = this.g;
        if (i != 0) {
            this.ae.put("deviceCategory", String.valueOf(i));
        }
    }

    private void a() {
        if (!i()) {
            LogUtil.b("R_PayActivity", "isCheckItemOk transaction abort, parameters is null...");
            return;
        }
        k();
        this.i = new CountDownLatch(1);
        LogUtil.a("R_PayActivity", "transaction params checked, shopping start... mProductId: ", this.u, ", mPurchasePolicy:", this.ac);
        e();
        try {
            this.i.await();
        } catch (InterruptedException unused) {
            LogUtil.h("R_PayActivity", "shopping InterruptedException");
        }
        LogUtil.a("R_PayActivity", "shopping end: mProductId = ", this.u, " mPurchasePolicy = ", this.ac);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("R_PayActivity", "pay callback by iap.");
        super.onActivityResult(i, i2, intent);
        if (i == 6666) {
            this.ad.setVisibility(0);
            if (intent == null) {
                LogUtil.h("R_PayActivity", "onActivityResult data is null");
                e(1002, (int) null);
                return;
            }
            int aNy_ = aNy_(intent);
            if (aNy_ == 0 || aNy_ == 1008) {
                Message obtain = Message.obtain();
                obtain.what = 1003;
                obtain.arg1 = aNy_;
                this.m.sendMessage(obtain);
                return;
            }
            if (aNy_ == 1013) {
                Message obtain2 = Message.obtain();
                obtain2.what = 1009;
                obtain2.arg1 = aNy_;
                this.m.sendMessage(obtain2);
                return;
            }
            e(1002, (int) null);
            return;
        }
        LogUtil.h("R_PayActivity", "onActivityResult Unexpected requestCode = " + i);
        e(1008, (int) null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Handler handler = this.m;
        if (handler != null) {
            handler.removeMessages(1000);
            this.m.removeMessages(1005);
        }
        b();
        this.p.release();
        ThreadPoolManager.d().b("R_PayActivity", null);
        super.onDestroy();
    }

    static class b extends BaseHandler<PayActivity> {
        private b(Looper looper, PayActivity payActivity) {
            super(looper, payActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aNF_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PayActivity payActivity, Message message) {
            if (message == null) {
                LogUtil.h("R_PayActivity", "handleMessageWhenReferenceNotNull activity or message is null.");
                return;
            }
            LogUtil.a("R_PayActivity", "handleMessage message = ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 2003) {
                switch (i) {
                    case 1000:
                        payActivity.a((PayActivity) message.obj);
                        break;
                    case 1001:
                    case 1004:
                        payActivity.e(1003);
                        payActivity.a(1010);
                        break;
                    case 1002:
                        payActivity.e(1007);
                        payActivity.a(1010);
                        break;
                    case 1003:
                        LogUtil.a("R_PayActivity", "start MSG_NOTIFY_CLOUD.");
                        payActivity.c(message.arg1);
                        break;
                    case 1005:
                        a(payActivity);
                        break;
                    case 1006:
                        payActivity.a(1002);
                        break;
                    case 1007:
                        payActivity.d();
                        payActivity.e(1007);
                        payActivity.a(1010);
                        break;
                    default:
                        switch (i) {
                            case 1009:
                                break;
                            case 1010:
                                payActivity.a(1015);
                                break;
                            case 1011:
                                payActivity.a(1017);
                                break;
                            default:
                                LogUtil.h("R_PayActivity", "Unknown msg = " + message.what);
                                payActivity.a(1003);
                                break;
                        }
                }
                return;
            }
            payActivity.a(message.arg1);
        }

        private void a(PayActivity payActivity) {
            if (CommonUtil.z(payActivity.f)) {
                if (HuaweiLoginManager.checkIsInstallHuaweiAccount(payActivity.f)) {
                    if (LoginInit.getInstance(payActivity.f).getIsLogined()) {
                        payActivity.a(1014);
                        return;
                    } else {
                        LogUtil.a("R_PayActivity", "not login, browsingToLogin.");
                        LoginInit.getInstance(payActivity.f).browsingToLogin(new c(payActivity), null);
                        return;
                    }
                }
                payActivity.a(1001);
                return;
            }
            LoginInit.getInstance(payActivity.f).browsingToLogin(new c(payActivity), null);
        }

        static class c implements IBaseResponseCallback {
            private WeakReference<PayActivity> c;

            c(PayActivity payActivity) {
                this.c = new WeakReference<>(payActivity);
            }

            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                PayActivity payActivity = this.c.get();
                if (payActivity == null) {
                    LogUtil.h("R_PayActivity", "LoginedCallback activity is null");
                } else if (i == 0) {
                    payActivity.e();
                } else {
                    payActivity.a(1001);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        final String str = this.ae.get("productId");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.ae.get("purchasePolicy"))) {
            LogUtil.b("R_PayActivity", "checkProductInfo：param error!!");
            return;
        }
        try {
            final int parseInt = Integer.parseInt(this.ae.get("purchasePolicy"));
            ProductInfoReq productInfoReq = new ProductInfoReq();
            productInfoReq.setProductIds(new ArrayList<String>(str) { // from class: com.huawei.health.tradeservice.pay.PayActivity.6

                /* renamed from: a, reason: collision with root package name */
                final /* synthetic */ String f3463a;

                {
                    this.f3463a = str;
                    add(str);
                }
            });
            productInfoReq.setPriceType(parseInt);
            Task<ProductInfoResult> obtainProductInfo = Iap.getIapClient((Activity) this, gla.c()).obtainProductInfo(productInfoReq);
            if (obtainProductInfo == null) {
                LogUtil.b("R_PayActivity", "checkProductInfo：obtainProductInfo task failed!!");
            } else {
                obtainProductInfo.addOnSuccessListener(new OnSuccessListener<ProductInfoResult>() { // from class: com.huawei.health.tradeservice.pay.PayActivity.14
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(ProductInfoResult productInfoResult) {
                        if (PayActivity.this.a(productInfoResult, str, parseInt)) {
                            return;
                        }
                        LogUtil.b("R_PayActivity", "checkProductInfo：The product = " + str + " is not configured on the IAP !!!");
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.tradeservice.pay.PayActivity.15
                    @Override // com.huawei.hmf.tasks.OnFailureListener
                    public void onFailure(Exception exc) {
                        LogUtil.b("R_PayActivity", "checkProductInfo：Exception： " + exc.getClass().getSimpleName() + "The product = " + str);
                    }
                });
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("R_PayActivity", "checkProductInfo：NumberFormatException!!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(ProductInfoResult productInfoResult, String str, int i) {
        if (productInfoResult != null && productInfoResult.getProductInfoList() != null) {
            List<com.huawei.hms.iap.entity.ProductInfo> productInfoList = productInfoResult.getProductInfoList();
            if (productInfoList.size() <= 0) {
                return false;
            }
            for (com.huawei.hms.iap.entity.ProductInfo productInfo : productInfoList) {
                if (str.equals(productInfo.getProductId()) && i == productInfo.getPriceType()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.tradeservice.pay.PayActivity.13
            @Override // java.lang.Runnable
            public void run() {
                PayActivity.this.b(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i == 0) {
            OrderManager.orderReportPurchaseResult(this.ae.get("orderCode"), this.n, this.k, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.pay.PayActivity.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.h("R_PayActivity", "orderReportPurchaseResult errorCode = " + i2);
                }
            });
            h();
            e("notifyCloudInnerParams", d(this.ae.get("orderCode"), this.n, this.k));
        } else if (i == 1008) {
            OrderManager.orderCancel(this.ae.get("orderCode"), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.pay.PayActivity.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.h("R_PayActivity", "orderCancel errorCode = " + i2);
                }
            });
        } else {
            LogUtil.h("R_PayActivity", "unknown notifyMsg = " + i);
        }
        Message obtain = Message.obtain();
        obtain.what = 2003;
        obtain.arg1 = i;
        this.m.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("resultCode", String.valueOf(i));
        HashMap<String, String> hashMap = this.ae;
        if (hashMap != null && hashMap.containsKey("purchasePolicy")) {
            linkedHashMap.put("productType", this.ae.get("purchasePolicy"));
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_TRADE_80070001.value(), linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        CountDownLatch countDownLatch = this.i;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
        if (i != 1010) {
            e(i);
        }
        if (i != 0) {
            LogUtil.b("R_PayActivity", "exitPay with shoppingResult = " + i);
        }
        if (TextUtils.isEmpty(this.ab)) {
            Intent intent = new Intent();
            intent.putExtra("shoppingResult", i);
            if (i == 0) {
                intent.putExtra("orderCode", this.ae.get("orderCode"));
            }
            int i2 = this.e;
            if (i2 == 3040022 || i2 == 3130014 || i2 == 3130012) {
                intent.putExtra("shoppingResult", i2);
            }
            setResult(-1, intent);
            if (i == 1017) {
                this.ad.setVisibility(8);
                o();
                return;
            } else if (i != 0 && i != 1008 && i != 1001 && i != 1015) {
                this.ad.setVisibility(8);
                if (d(i)) {
                    return;
                }
            }
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("shoppingResult", i);
            bundle.putString("payRequest", this.s);
            if (i == 0) {
                bundle.putString("orderCode", this.ae.get("orderCode"));
            }
            AppRouter.b(this.ab).zF_(bundle).a(268435456).c(this.f);
        }
        if (i == 0 && !TextUtils.isEmpty(this.t) && this.r != 0) {
            f();
        }
        finish();
    }

    private void o() {
        CustomAlertDialog.Builder cyo_ = new CustomAlertDialog.Builder(this.f).cyp_(View.inflate(this.f, R.layout.dialog_account_error, null)).cyo_(R.string._2130846779_res_0x7f02243b, new DialogInterface.OnClickListener() { // from class: gks
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PayActivity.this.aNC_(dialogInterface, i);
            }
        });
        if (CommonUtil.h(LoginInit.getInstance(this.f).getAccountInfo(1009)) == 7) {
            cyo_.cyn_(R.string._2130839642_res_0x7f02085a, new DialogInterface.OnClickListener() { // from class: gkt
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PayActivity.this.aND_(dialogInterface, i);
                }
            });
        } else {
            cyo_.cym_(R.string._2130839642_res_0x7f02085a, R.color._2131299241_res_0x7f090ba9, new DialogInterface.OnClickListener() { // from class: gkw
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PayActivity.this.aNE_(dialogInterface, i);
                }
            });
        }
        CustomAlertDialog c2 = cyo_.c();
        c2.setCancelable(false);
        c2.show();
    }

    public /* synthetic */ void aNC_(DialogInterface dialogInterface, int i) {
        LoginInit.getInstance(this.f).logoutWhenTokenInvalid(new IBaseResponseCallback() { // from class: gkv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                CommonLibUtil.d(BaseApplication.getContext());
            }
        });
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void aND_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        finish();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void aNE_(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        finish();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void f() {
        Bundle bundle = new Bundle();
        int i = this.r;
        if (i != 1) {
            if (i == 2) {
                bundle.putString("productInfo", this.w);
                bundle.putString("orderCode", this.ae.get("orderCode"));
                AppRouter.zi_(Uri.parse(this.t)).zF_(bundle).a(268435456).c(this);
                return;
            }
            LogUtil.h("R_PayActivity", "jumpByLinkValue error linkType");
            return;
        }
        bundle.putString("url", this.t);
        bundle.putString("productInfo", this.w);
        bundle.putString("orderCode", this.ae.get("orderCode"));
        Intent createWebViewIntent = bzs.e().createWebViewIntent(this.f, bundle, null);
        if (createWebViewIntent != null) {
            gnm.aPB_(this.f, createWebViewIntent);
            LogUtil.a("R_PayActivity", "jumpByLinkValue jump to webviewActivity");
        } else {
            LogUtil.h("R_PayActivity", "jumpByLinkValue h5 intent is null");
        }
    }

    static class c implements IPayEnvCheckCallback {

        /* renamed from: a, reason: collision with root package name */
        private PayActivity f3465a;

        public c(PayActivity payActivity) {
            this.f3465a = payActivity;
        }

        @Override // com.huawei.health.tradeservice.pay.IPayEnvCheckCallback
        public void release() {
            LogUtil.a("R_PayActivity", "PayEnvCheckCallback release");
            this.f3465a = null;
        }

        @Override // com.huawei.health.tradeservice.pay.IPayEnvCheckCallback
        public void onResponse(int i) {
            LogUtil.a("R_PayActivity", "onResponse retCode:", Integer.valueOf(i));
            PayActivity payActivity = this.f3465a;
            if (payActivity == null) {
                LogUtil.h("R_PayActivity", "PayActivity is null, PayEnvCheckCallback skip onResponse");
                return;
            }
            if (i == 0) {
                payActivity.c();
                return;
            }
            if (i == 1015) {
                LogUtil.h("R_PayActivity", "user cancel updating hms core");
                this.f3465a.e(1010, (int) null);
            } else if (i == 1001) {
                LogUtil.h("R_PayActivity", "don't login, on browser");
                this.f3465a.e(1005, (int) null);
            } else if (i != 1002) {
                payActivity.e(1002, (int) null);
            } else {
                LogUtil.h("R_PayActivity", "out of service location");
                this.f3465a.e(1006, (int) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HashMap<String, String> hashMap = this.ae;
        if (hashMap == null || hashMap.size() <= 0) {
            LogUtil.b("R_PayActivity", "mResourceInfo is null, leave buyProduct");
            e(1004, (int) null);
        } else {
            boolean g = g();
            LogUtil.a("R_PayActivity", "isNeedAccountInfo:", Boolean.valueOf(g));
            gla.aNJ_(this, g, this.p);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        final HashMap<String, String> hashMap = this.ae;
        LogUtil.a("R_PayActivity", "createOrder productInfo trigResType ", hashMap.get("trigResType"), " trigResName ", hashMap.get("trigResName"), " trigResMemberPrice ", hashMap.get("trigResMemberPrice"), " areaInfo ", hashMap.get("areaInfo"));
        OrderManager.orderCreate(hashMap.get("productId"), hashMap, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.pay.PayActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("R_PayActivity", "orderCreate code: ", Integer.valueOf(i));
                if (i != 0 && i != 3040046) {
                    PayActivity.this.e = i;
                    PayActivity.this.e(1001, (int) null);
                    return;
                }
                if (obj instanceof OrderCreateRsp) {
                    PayActivity.this.e("setOrderCreateOpEvent", obj.toString());
                    OrderCreateRsp orderCreateRsp = (OrderCreateRsp) obj;
                    int resultCode = orderCreateRsp.getResultCode();
                    if (resultCode != 0 && resultCode != 3040046) {
                        LogUtil.h("R_PayActivity", "orderCreate: health cloud create order failed = " + resultCode + " " + orderCreateRsp.getResultDesc());
                        PayActivity.this.e(1001, (int) null);
                        return;
                    }
                    if (TextUtils.isEmpty(orderCreateRsp.getOrderCode())) {
                        LogUtil.h("R_PayActivity", "orderCreate: health cloud return orderCode is null !!");
                    } else {
                        hashMap.put("orderCode", orderCreateRsp.getOrderCode());
                    }
                    if (!TextUtils.isEmpty(orderCreateRsp.getPurchaseExtension())) {
                        LogUtil.a("R_PayActivity", "getPurchaseExtension(): ", orderCreateRsp.getPurchaseExtension(), "getPurchaseExtensionSignature(): ", orderCreateRsp.getPurchaseExtensionSignature());
                        hashMap.put("purchaseExtension", orderCreateRsp.getPurchaseExtension());
                        hashMap.put("purchaseExtensionSignature", orderCreateRsp.getPurchaseExtensionSignature());
                    }
                    PayActivity.this.e((Map<String, String>) hashMap);
                    return;
                }
                LogUtil.h("R_PayActivity", "orderCreate: cloud return data error");
                PayActivity.this.e(1001, (int) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final Map<String, String> map) {
        if (!map.containsKey("promotionId")) {
            LogUtil.a("R_PayActivity", "getPromotionInfo: no promotion id");
            a(map);
            return;
        }
        if (!String.valueOf(2).equals(map.get("purchasePolicy"))) {
            LogUtil.a("R_PayActivity", "getPromotionInfo: non subscription product");
            a(map);
        } else {
            OrderManager.promotionInfoQuery(map.get("productId"), map.get("promotionId"), map.get("promotionPolicyId"), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.pay.PayActivity.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof PromotionInfoRsp)) {
                        LogUtil.h("R_PayActivity", "failed !! promotionInfoQuery errorCode = " + i);
                        PayActivity.this.e(1001, (int) null);
                        return;
                    }
                    PromotionInfoRsp promotionInfoRsp = (PromotionInfoRsp) obj;
                    if (promotionInfoRsp.getResultCode() != 0) {
                        LogUtil.h("R_PayActivity", "getPromotionInfo: promotionInfoQuery failed! = " + promotionInfoRsp.getResultCode());
                        PayActivity.this.e(1001, (int) null);
                        return;
                    }
                    if (!TextUtils.isEmpty(promotionInfoRsp.getPurchaseDiscountInfo())) {
                        LogUtil.a("R_PayActivity", "promotionInfoRsp.getPurchaseDiscountInfo(): ", promotionInfoRsp.getPurchaseDiscountInfo(), "; DiscountSignature = ", promotionInfoRsp.getPurchaseDiscountSignature());
                        PayActivity payActivity = PayActivity.this;
                        payActivity.e("promotionInfoQueryData", payActivity.c(promotionInfoRsp));
                        map.put("purchaseDiscountInfo", promotionInfoRsp.getPurchaseDiscountInfo());
                        map.put("purchaseDiscountSignature", promotionInfoRsp.getPurchaseDiscountSignature());
                    } else {
                        LogUtil.h("R_PayActivity", "getPromotionInfo: PURCHASE_DISCOUNT_INFO is null");
                    }
                    PayActivity.this.a((Map<String, String>) map);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<String, String> map) {
        gku gkuVar = new gku();
        if (map.containsKey("orderCode")) {
            LogUtil.a("R_PayActivity", "iapOrderCreate AppPayLoad = ", map.get("orderCode"));
            gkuVar.c(map.get("orderCode"));
        }
        gkuVar.d(map.get("productId"));
        try {
            int parseInt = Integer.parseInt(map.get("purchasePolicy"));
            if (!c.contains(Integer.valueOf(parseInt))) {
                LogUtil.h("R_PayActivity", "purChasePolicy error, the value is  " + parseInt);
                e(1001, (int) null);
                return;
            }
            if (parseInt != 2 && !map.containsKey("orderCode")) {
                LogUtil.h("R_PayActivity", "Non-subscription product need OrderCode !!!");
                e(1001, (int) null);
            } else {
                gkuVar.d(parseInt);
                e(map, gkuVar);
                a(gkuVar, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.pay.PayActivity.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("R_PayActivity", "iapOrderCreate return");
                        if (i == 0) {
                            PayActivity.this.e(1000, (int) obj);
                        } else {
                            PayActivity.this.e(1007, (int) null);
                        }
                    }
                });
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("R_PayActivity", "NumberFormatException purChasePolicy = " + map.get("purchasePolicy"));
            e(1004, (int) null);
        }
    }

    private void e(Map<String, String> map, gku gkuVar) {
        ReservedInfor reservedInfor = new ReservedInfor();
        if (map.containsKey("purchaseExtension")) {
            OrderPurchaseExtensionInformation orderPurchaseExtensionInformation = new OrderPurchaseExtensionInformation();
            orderPurchaseExtensionInformation.setPurchaseExtension(map.get("purchaseExtension"));
            orderPurchaseExtensionInformation.setPurchaseExtensionSignature(map.get("purchaseExtensionSignature"));
            reservedInfor.setOrderPurchaseExtensionInformation(orderPurchaseExtensionInformation);
            gkuVar.a(reservedInfor);
        }
        if (map.containsKey("purchaseDiscountInfo")) {
            PromotionalOffer promotionalOffer = new PromotionalOffer();
            promotionalOffer.setPurchaseDiscountInfo(map.get("purchaseDiscountInfo"));
            promotionalOffer.setPurchaseDiscountSignature(map.get("purchaseDiscountSignature"));
            reservedInfor.setPromotionalOffer(promotionalOffer);
            gkuVar.a(reservedInfor);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void e(int i, T t) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        this.m.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void a(T t) {
        if (b((PayActivity) t) != 0) {
            e(1002, (int) null);
        }
    }

    private boolean d(int i) {
        int i2 = this.e;
        if (i2 == 3040022 || i2 == 3130014 || i2 == 3130012) {
            LogUtil.a("R_PayActivity", "not show dialog. ErrorCode = ", Integer.valueOf(i2));
            return false;
        }
        LogUtil.a("R_PayActivity", "showSystemErrorDialog() enter");
        Window window = getWindow();
        window.setFlags(AppRouterExtras.COLDSTART, AppRouterExtras.COLDSTART);
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        if (!(this.f instanceof Activity)) {
            LogUtil.h("R_PayActivity", "mContext not instanceof Activity");
            return false;
        }
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.f);
        View inflate = LayoutInflater.from(this.f).inflate(R.layout.trade_system_error_view, (ViewGroup) null);
        aNz_((TextView) inflate.findViewById(R.id.pay_error_text), i);
        builder.cyp_(inflate);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.tradeservice.pay.PayActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayActivity.this.j();
                PayActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomAlertDialog c2 = builder.c();
        this.z = c2;
        c2.setCancelable(false);
        if (((Activity) this.f).isDestroyed() || ((Activity) this.f).isFinishing()) {
            return true;
        }
        LogUtil.a("R_PayActivity", "showSystemErrorDialog success");
        this.z.show();
        return true;
    }

    private void aNz_(TextView textView, int i) {
        if (i == 1013) {
            textView.setText(R.string._2130841102_res_0x7f020e0e);
        }
        if (i == 1014) {
            textView.setText(R.string._2130844050_res_0x7f021992);
        }
        if (i == 1005) {
            textView.setText(R.string._2130841392_res_0x7f020f30);
        }
        if (this.e == 3040051) {
            textView.setText(BaseApplication.getContext().getResources().getString(R.string._2130845517_res_0x7f021f4d, 2, 4));
        }
        if (this.e == 3040009) {
            textView.setText(R.string._2130845731_res_0x7f022023);
        }
        if (this.e == 3040064 || i == 1016) {
            textView.setText(R.string._2130845732_res_0x7f022024);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("R_PayActivity", "enter dismissDialog");
        if (((Activity) this.f).isDestroyed() || ((Activity) this.f).isFinishing()) {
            return;
        }
        this.z.dismiss();
        this.z = null;
    }

    public void b() {
        CustomAlertDialog customAlertDialog = this.z;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        this.z = null;
    }

    private void h() {
        if (this.v == null) {
            LogUtil.h("R_PayActivity", "mProductInfo = null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(this.v.getResourceType()));
        hashMap.put("name", this.v.getProductName());
        hashMap.put("id", this.v.getProductId());
        hashMap.put("discountPeriod", gla.a());
        MarketingBiUtils.a(hashMap, this.v.getBiParams());
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.TRADE_PAY_SUCCESS.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        String str3 = RecordAction.ACT_ID_TAG + jdq.e(getApplicationContext(), LoginInit.getInstance(this).getAccountInfo(1011)) + ",msg=" + str2;
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(str, str3);
        LogUtil.a("R_PayActivity", "msgKey = ", str, "; eventMsg = ", str3);
    }

    private String d(PurchaseIntentReq purchaseIntentReq) {
        return "ProdId=" + purchaseIntentReq.getProductId() + ";Payload=" + purchaseIntentReq.getDeveloperPayload() + ";SignatureAlgorithm=" + purchaseIntentReq.getSignatureAlgorithm() + ";PriceType=" + purchaseIntentReq.getPriceType() + ";ReservedInfor=" + purchaseIntentReq.getReservedInfor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(PromotionInfoRsp promotionInfoRsp) {
        return "DiscountInfo()=" + promotionInfoRsp.getPurchaseDiscountInfo() + ";DiscountSignature=" + promotionInfoRsp.getPurchaseDiscountSignature();
    }

    private String d(String str, String str2, String str3) {
        return "orderCode=" + str + ";inAppPurchaseData=" + str2 + ";signature=" + str3;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
