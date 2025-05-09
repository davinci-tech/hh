package com.huawei.hms.iapfull;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.iapfull.bean.PayResult;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseRequest;
import com.huawei.hms.iapfull.bean.WebConsumeOwnedPurchaseResponse;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyRequest;
import com.huawei.hms.iapfull.bean.WebIsEnvReadyResponse;
import com.huawei.hms.iapfull.bean.WebObtainOwnedPurchasesResponse;
import com.huawei.hms.iapfull.bean.WebOrderRequest;
import com.huawei.hms.iapfull.bean.WebOrderResp;
import com.huawei.hms.iapfull.bean.WebOwnedPurchasesRequest;
import com.huawei.hms.iapfull.bean.WebProductDetailRequest;
import com.huawei.hms.iapfull.bean.WebProductDetailResponse;
import com.huawei.hms.iapfull.bean.WebProductInfoRequest;
import com.huawei.hms.iapfull.bean.WebProductInfoResp;
import com.huawei.hms.iapfull.bean.WebProductPayRequest;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoRequest;
import com.huawei.hms.iapfull.bean.WebPurchaseInfoResp;
import com.huawei.hms.iapfull.pay.PayActivity;
import com.huawei.hms.iapfull.webpay.WebViewActivity;
import com.huawei.hms.iapfull.webpay.callback.WebPayCallback;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes4.dex */
public class a implements IIapFullAPI, IIapFullAPIVer4 {

    /* renamed from: a, reason: collision with root package name */
    private Context f4672a;

    static void a(a aVar, String str, String str2, boolean z, WebPayCallback webPayCallback) {
        aVar.getClass();
        y0.a("IapFullAPI", str + " onGrsFail: GRS fail " + str2 + ", isVer4API " + z);
        webPayCallback.onFailure(z ? 60005 : 30005, "grs fail");
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPIVer4
    public void consumeOwnedPurchase(WebConsumeOwnedPurchaseRequest webConsumeOwnedPurchaseRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webConsumeOwnedPurchaseRequest.getReservedInfor()), new f(webConsumeOwnedPurchaseRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public void getOrderDetail(WebOrderRequest webOrderRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webOrderRequest.getReservedInfor()), new c(webOrderRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public void getProductDetail(WebProductDetailRequest webProductDetailRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webProductDetailRequest.getReservedInfor()), new C0112a(webProductDetailRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public void getPurchaseInfo(WebPurchaseInfoRequest webPurchaseInfoRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webPurchaseInfoRequest.getReservedInfor()), new b(webPurchaseInfoRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPIVer4
    public void isEnvReady(WebIsEnvReadyRequest webIsEnvReadyRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webIsEnvReadyRequest.getReservedInfor()), new h(webIsEnvReadyRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPIVer4
    public void obtainOwnedPurchaseRecord(WebOwnedPurchasesRequest webOwnedPurchasesRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webOwnedPurchasesRequest.getReservedInfor()), new g(webOwnedPurchasesRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPIVer4
    public void obtainOwnedPurchases(WebOwnedPurchasesRequest webOwnedPurchasesRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webOwnedPurchasesRequest.getReservedInfor()), new e(webOwnedPurchasesRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPIVer4
    public void obtainProductInfo(WebProductInfoRequest webProductInfoRequest, WebPayCallback webPayCallback) {
        g0.a().a(this.f4672a, y0.a(webProductInfoRequest.getReservedInfor()), new d(webProductInfoRequest, webPayCallback));
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public Intent getWithholdIntent(Context context, PayRequest payRequest) {
        if (context == null || payRequest == null) {
            y0.a("IapFullAPI", "getWithholdIntent context or payRequest is null");
            return null;
        }
        payRequest.setPackageName(this.f4672a.getPackageName());
        Intent intent = new Intent();
        intent.setPackage(PayActivity.class.getName());
        intent.setClass(this.f4672a, PayActivity.class);
        intent.putExtras(payRequest.toBundle().getBundle());
        return intent;
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public Intent getProductPayIntent(Context context, WebProductPayRequest webProductPayRequest) {
        if (context == null || webProductPayRequest == null) {
            y0.a("IapFullAPI", "getProductPayIntent context or webProductPayRequest is null");
            return null;
        }
        webProductPayRequest.setPackageName(this.f4672a.getPackageName());
        Intent intent = new Intent();
        intent.setClass(this.f4672a, WebViewActivity.class);
        intent.putExtras(webProductPayRequest.toBundle().getBundle());
        return intent;
    }

    /* renamed from: com.huawei.hms.iapfull.a$a, reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    class C0112a implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebProductDetailRequest f4673a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$a$a, reason: collision with other inner class name */
        class C0113a implements k1 {
            @Override // com.huawei.hms.iapfull.k1
            public void a(WebProductDetailResponse webProductDetailResponse) {
                y0.b("IapFullAPI", "getProductDetail onSuccess");
                C0112a.this.b.onSuccess(a1.b(webProductDetailResponse));
            }

            @Override // com.huawei.hms.iapfull.k1
            public void a(int i, String str) {
                y0.b("IapFullAPI", "getProductDetail onFailed errorCode:" + i);
                C0112a.this.b.onFailure(i, str);
            }

            C0113a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "getProductDetail", str, false, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4673a, new C0113a());
        }

        C0112a(WebProductDetailRequest webProductDetailRequest, WebPayCallback webPayCallback) {
            this.f4673a = webProductDetailRequest;
            this.b = webPayCallback;
        }
    }

    /* loaded from: classes9.dex */
    class b implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebPurchaseInfoRequest f4675a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$b$a, reason: collision with other inner class name */
        class C0114a implements m1 {
            @Override // com.huawei.hms.iapfull.m1
            public void a(WebPurchaseInfoResp webPurchaseInfoResp) {
                y0.b("IapFullAPI", "getPurchaseInfo onSuccess");
                b.this.b.onSuccess(a1.b(webPurchaseInfoResp));
            }

            @Override // com.huawei.hms.iapfull.m1
            public void a(int i, String str) {
                y0.b("IapFullAPI", "getPurchaseInfo onFailed errorCode:" + i);
                b.this.b.onFailure(i, str);
            }

            C0114a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "getPurchaseInfo", str, false, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4675a, new C0114a());
        }

        b(WebPurchaseInfoRequest webPurchaseInfoRequest, WebPayCallback webPayCallback) {
            this.f4675a = webPurchaseInfoRequest;
            this.b = webPayCallback;
        }
    }

    /* loaded from: classes9.dex */
    class c implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebOrderRequest f4677a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$c$a, reason: collision with other inner class name */
        class C0115a implements j1 {
            @Override // com.huawei.hms.iapfull.j1
            public void a(WebOrderResp webOrderResp) {
                y0.b("IapFullAPI", "getOrderDetail onSuccess");
                c.this.b.onSuccess(a1.b(webOrderResp));
            }

            @Override // com.huawei.hms.iapfull.j1
            public void a(int i, String str) {
                y0.b("IapFullAPI", "getOrderDetail onFailed errorCode:" + i);
                c.this.b.onFailure(i, str);
            }

            C0115a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "getOrderDetail", str, false, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4677a, new C0115a());
        }

        c(WebOrderRequest webOrderRequest, WebPayCallback webPayCallback) {
            this.f4677a = webOrderRequest;
            this.b = webPayCallback;
        }
    }

    class d implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebProductInfoRequest f4679a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$d$a, reason: collision with other inner class name */
        class C0116a implements l1 {
            @Override // com.huawei.hms.iapfull.l1
            public void a(WebProductInfoResp webProductInfoResp) {
                y0.b("IapFullAPI", "obtainProductInfo onSuccess");
                if (webProductInfoResp != null) {
                    webProductInfoResp.convert();
                }
                d.this.b.onSuccess(a1.b(webProductInfoResp));
            }

            @Override // com.huawei.hms.iapfull.l1
            public void a(int i, String str) {
                y0.a("IapFullAPI", "obtainProductInfo onFailed errorCode:" + i);
                d.this.b.onFailure(i, str);
            }

            C0116a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "obtainProductInfo", str, true, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4679a, new C0116a());
        }

        d(WebProductInfoRequest webProductInfoRequest, WebPayCallback webPayCallback) {
            this.f4679a = webProductInfoRequest;
            this.b = webPayCallback;
        }
    }

    class e implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebOwnedPurchasesRequest f4681a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$e$a, reason: collision with other inner class name */
        class C0117a implements i1 {
            @Override // com.huawei.hms.iapfull.i1
            public void a(WebObtainOwnedPurchasesResponse webObtainOwnedPurchasesResponse) {
                y0.b("IapFullAPI", "obtainOwnedPurchases onSuccess");
                e.this.b.onSuccess(a1.b(webObtainOwnedPurchasesResponse));
            }

            @Override // com.huawei.hms.iapfull.i1
            public void a(int i, String str) {
                y0.a("IapFullAPI", "obtainOwnedPurchases onFailed errorCode:" + i);
                e.this.b.onFailure(i, str);
            }

            C0117a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "obtainOwnedPurchases", str, true, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().b(a.this.f4672a, this.f4681a, new C0117a());
        }

        e(WebOwnedPurchasesRequest webOwnedPurchasesRequest, WebPayCallback webPayCallback) {
            this.f4681a = webOwnedPurchasesRequest;
            this.b = webPayCallback;
        }
    }

    /* loaded from: classes9.dex */
    class f implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebConsumeOwnedPurchaseRequest f4683a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$f$a, reason: collision with other inner class name */
        class C0118a implements g1 {
            @Override // com.huawei.hms.iapfull.g1
            public void a(WebConsumeOwnedPurchaseResponse webConsumeOwnedPurchaseResponse) {
                y0.b("IapFullAPI", "consumeOwnedPurchase onSuccess");
                f.this.b.onSuccess(a1.b(webConsumeOwnedPurchaseResponse));
            }

            @Override // com.huawei.hms.iapfull.g1
            public void a(int i, String str) {
                y0.a("IapFullAPI", "consumeOwnedPurchase onFailed errorCode:" + i);
                f.this.b.onFailure(i, str);
            }

            C0118a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "consumeOwnedPurchase", str, true, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4683a, new C0118a());
        }

        f(WebConsumeOwnedPurchaseRequest webConsumeOwnedPurchaseRequest, WebPayCallback webPayCallback) {
            this.f4683a = webConsumeOwnedPurchaseRequest;
            this.b = webPayCallback;
        }
    }

    /* loaded from: classes9.dex */
    class g implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebOwnedPurchasesRequest f4685a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$g$a, reason: collision with other inner class name */
        class C0119a implements i1 {
            @Override // com.huawei.hms.iapfull.i1
            public void a(WebObtainOwnedPurchasesResponse webObtainOwnedPurchasesResponse) {
                y0.b("IapFullAPI", "obtainOwnedPurchaseRecord onSuccess");
                g.this.b.onSuccess(a1.b(webObtainOwnedPurchasesResponse));
            }

            @Override // com.huawei.hms.iapfull.i1
            public void a(int i, String str) {
                y0.a("IapFullAPI", "obtainOwnedPurchaseRecord onFailed errorCode:" + i);
                g.this.b.onFailure(i, str);
            }

            C0119a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "obtainOwnedPurchaseRecord", str, true, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4685a, new C0119a());
        }

        g(WebOwnedPurchasesRequest webOwnedPurchasesRequest, WebPayCallback webPayCallback) {
            this.f4685a = webOwnedPurchasesRequest;
            this.b = webPayCallback;
        }
    }

    class h implements f0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ WebIsEnvReadyRequest f4687a;
        final /* synthetic */ WebPayCallback b;

        /* renamed from: com.huawei.hms.iapfull.a$h$a, reason: collision with other inner class name */
        class C0120a implements h1 {
            @Override // com.huawei.hms.iapfull.h1
            public void a(WebIsEnvReadyResponse webIsEnvReadyResponse) {
                y0.b("IapFullAPI", "isEnvReady onSuccess");
                h.this.b.onSuccess(a1.b(webIsEnvReadyResponse));
            }

            @Override // com.huawei.hms.iapfull.h1
            public void a(int i, String str) {
                y0.a("IapFullAPI", "isEnvReady onFailed errorCode:" + i);
                h.this.b.onFailure(i, str);
            }

            C0120a() {
            }
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a(String str) {
            a.a(a.this, "isEnvReady", str, true, this.b);
        }

        @Override // com.huawei.hms.iapfull.f0
        public void a() {
            o1.a().a(a.this.f4672a, this.f4687a, new C0120a());
        }

        h(WebIsEnvReadyRequest webIsEnvReadyRequest, WebPayCallback webPayCallback) {
            this.f4687a = webIsEnvReadyRequest;
            this.b = webPayCallback;
        }
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPIVer4
    public Intent getPmsPayIntent(WebProductPayRequest webProductPayRequest) {
        Context context = this.f4672a;
        if (context == null || webProductPayRequest == null) {
            y0.a("IapFullAPI", "getPmsPayIntent appContext or webProductPayRequest is null");
            return null;
        }
        webProductPayRequest.setPackageName(context.getPackageName());
        Intent intent = new Intent();
        intent.setClass(this.f4672a, WebViewActivity.class);
        intent.putExtras(webProductPayRequest.toBundle().getBundle());
        return intent;
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public PayResult getPayResultFromIntent(Intent intent) {
        PayResult payResult = new PayResult();
        SafeBundle safeBundle = new SafeBundle(new SafeIntent(intent).getExtras());
        payResult.setReturnCode(safeBundle.getInt("returnCode"));
        payResult.setOrderID(safeBundle.getString("orderID"));
        payResult.setUserName(safeBundle.getString(HwPayConstant.KEY_USER_NAME));
        payResult.setAmount(safeBundle.getString(HwPayConstant.KEY_AMOUNT));
        payResult.setWithHoldID(safeBundle.getString("withholdID"));
        payResult.setCurrency(safeBundle.getString(HwPayConstant.KEY_CURRENCY));
        payResult.setCountry(safeBundle.getString("country"));
        payResult.setErrMsg(safeBundle.getString("errMsg"));
        payResult.setTime(safeBundle.getString("time"));
        payResult.setRequestId(safeBundle.getString("requestId"));
        payResult.setSign(safeBundle.getString(HwPayConstant.KEY_SIGN));
        payResult.setNewSign(safeBundle.getString("newSign"));
        payResult.setPayType(safeBundle.getInt("payType"));
        payResult.setSignatureAlgorithm(safeBundle.getString("signatureAlgorithm"));
        return payResult;
    }

    @Override // com.huawei.hms.iapfull.IIapFullAPI
    public Intent getPayIntent(Context context, PayRequest payRequest) {
        Context context2;
        Class<?> cls;
        if (context == null || payRequest == null) {
            y0.a("IapFullAPI", "getPayIntent context or payRequest is null");
            return null;
        }
        payRequest.setPackageName(this.f4672a.getPackageName());
        Intent intent = new Intent();
        if (IapFullAPIFactory.isWebPaySupport(payRequest.getReservedInfor(), IapFullAPIFactory.PAY_TRADE_TYPE)) {
            context2 = this.f4672a;
            cls = WebViewActivity.class;
        } else {
            context2 = this.f4672a;
            cls = PayActivity.class;
        }
        intent.setClass(context2, cls);
        intent.putExtras(payRequest.toBundle().getBundle());
        return intent;
    }

    public a(Context context) {
        if (context != null) {
            this.f4672a = context.getApplicationContext();
        }
    }
}
