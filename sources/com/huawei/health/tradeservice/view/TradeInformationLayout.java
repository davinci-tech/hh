package com.huawei.health.tradeservice.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.tradeservice.activity.TradeSureOrderActivity;
import com.huawei.health.tradeservice.cloud.ProductManager;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TimeLimitedPromotion;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bzs;
import defpackage.gla;
import defpackage.glc;
import defpackage.gle;
import defpackage.gpn;
import defpackage.gpo;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nla;
import defpackage.nrr;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class TradeInformationLayout extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3466a;
    private View aa;
    private HealthTextView ab;
    private HealthButton ac;
    private ProductSummary ad;
    private HealthTextView ae;
    private HealthTextView af;
    private RelativeLayout ag;
    private HealthTextView ah;
    private String ai;
    private String aj;
    private ProductSummary ak;
    private HealthTextView al;
    private String am;
    private String an;
    private int ao;
    private gle ap;
    private View aq;
    private String ar;
    private RelativeLayout b;
    private HealthTextView c;
    private HealthTextView d;
    private Map<String, String> e;
    private Context f;
    private HealthButton g;
    private HealthTextView h;
    private View i;
    private String j;
    private HealthTextView k;
    private LinearLayout l;
    private CountDownLatch m;
    private HealthDivider n;
    private String o;
    private Handler p;
    private boolean q;
    private HealthTextView r;
    private boolean s;
    private boolean t;
    private HealthTextView u;
    private boolean v;
    private TextView w;
    private View x;
    private HealthTextView y;
    private View z;

    static class d extends BaseHandler<TradeInformationLayout> {
        d(TradeInformationLayout tradeInformationLayout) {
            super(tradeInformationLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aNR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TradeInformationLayout tradeInformationLayout, Message message) {
            int i = message.what;
            if (i == 1107) {
                tradeInformationLayout.f();
                tradeInformationLayout.j();
                tradeInformationLayout.z.setVisibility(8);
                tradeInformationLayout.x.setVisibility(0);
                return;
            }
            if (i != 1108) {
                switch (i) {
                    case 1101:
                        tradeInformationLayout.i();
                        tradeInformationLayout.aa.setVisibility(0);
                        break;
                    case 1102:
                        tradeInformationLayout.aq.setVisibility(8);
                        break;
                    case 1103:
                        if (tradeInformationLayout.s) {
                            tradeInformationLayout.e(tradeInformationLayout.an, tradeInformationLayout.ao);
                            break;
                        } else {
                            tradeInformationLayout.a(tradeInformationLayout.am);
                            break;
                        }
                    default:
                        LogUtil.h("TradeInformationLayout", "handleMessageWhenReferenceNotNull not match ");
                        break;
                }
                return;
            }
            tradeInformationLayout.h();
            tradeInformationLayout.x.setVisibility(8);
            tradeInformationLayout.z.setVisibility(0);
        }
    }

    static class b implements IBaseResponseCallback {
        private int b;
        private WeakReference<TradeInformationLayout> c;

        b(TradeInformationLayout tradeInformationLayout, int i) {
            this.c = new WeakReference<>(tradeInformationLayout);
            this.b = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TradeInformationLayout tradeInformationLayout = this.c.get();
            if (tradeInformationLayout != null) {
                Handler handler = tradeInformationLayout.p;
                if (handler == null) {
                    LogUtil.h("TradeInformationLayout", "handler is null");
                    return;
                }
                int i2 = this.b;
                if (i2 == 1) {
                    tradeInformationLayout.b(i, obj);
                    return;
                }
                if (i2 != 2) {
                    return;
                }
                if (i == 0 && (obj instanceof List)) {
                    LogUtil.a("TradeInformationLayout", "getProductSummaryInfo success");
                    tradeInformationLayout.e((List<ProductSummary>) obj);
                    return;
                } else {
                    LogUtil.h("TradeInformationLayout", "getProductSummaryInfo fail errorCode = ", Integer.valueOf(i));
                    handler.sendEmptyMessage(1102);
                    return;
                }
            }
            LogUtil.h("TradeInformationLayout", "TradeInformationLayout is null");
        }
    }

    public TradeInformationLayout(Context context, String str, int i) {
        super(context);
        this.q = false;
        this.t = false;
        this.s = true;
        this.v = false;
        LogUtil.a("TradeInformationLayout", "TradeInformationLayout enter by resId");
        this.s = true;
        this.f = context;
        this.an = str;
        this.ao = i;
        if (context == null) {
            this.f = BaseApplication.getContext();
        }
        this.p = new d(this);
        c();
        if (d()) {
            LogUtil.a("TradeInformationLayout", "is kid account resource");
        } else {
            e(str, i);
        }
    }

    public TradeInformationLayout(Context context, TradeViewInfo tradeViewInfo) {
        super(context);
        this.q = false;
        this.t = false;
        this.s = true;
        this.v = false;
        LogUtil.a("TradeInformationLayout", "TradeInformationLayout enter by tradeViweInfoString");
        if (tradeViewInfo == null) {
            LogUtil.h("TradeInformationLayout", "mTradeViewInfo is null");
            return;
        }
        this.f = context;
        if (context == null) {
            this.f = BaseApplication.getContext();
        }
        this.p = new d(this);
        c();
        if (d()) {
            LogUtil.a("TradeInformationLayout", "is kid account produce");
            return;
        }
        this.e = tradeViewInfo.getBiParams();
        if (!TextUtils.isEmpty(tradeViewInfo.getResId())) {
            this.s = true;
            this.an = tradeViewInfo.getResId();
            this.ao = tradeViewInfo.getResType();
            this.j = tradeViewInfo.getButtonName();
            this.ar = tradeViewInfo.getTrigResName();
            e(this.an, this.ao);
            return;
        }
        if (!TextUtils.isEmpty(tradeViewInfo.getProductId())) {
            this.s = false;
            this.am = tradeViewInfo.getProductId();
            this.ai = tradeViewInfo.getPositionId();
            this.o = tradeViewInfo.getFromPageTitle();
            a(this.am);
            return;
        }
        LogUtil.h("TradeInformationLayout", "resId or productId is empty");
    }

    private boolean d() {
        boolean z = false;
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            this.aa.setVisibility(0);
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            z = true;
        } else {
            this.i.setVisibility(0);
            this.h.setVisibility(8);
        }
        LogUtil.a("TradeInformationLayout", "isKidAccount:", Boolean.valueOf(z));
        return z;
    }

    private void c() {
        if (nsn.p() || nsn.e(BaseApplication.getContext(), 3.5f)) {
            this.aq = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.trade_info_all_large, this);
        } else {
            this.aq = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.trade_info_all, this);
        }
        this.r = (HealthTextView) this.aq.findViewById(R.id.tv_hava_giveaway);
        this.al = (HealthTextView) this.aq.findViewById(R.id.tv_pay_price);
        this.k = (HealthTextView) this.aq.findViewById(R.id.tv_end_time);
        this.u = (HealthTextView) this.aq.findViewById(R.id.tv_lin_price);
        this.k.setMovementMethod(ScrollingMovementMethod.getInstance());
        HealthButton healthButton = (HealthButton) this.aq.findViewById(R.id.btn_submit);
        this.g = healthButton;
        healthButton.setOnClickListener(this);
        this.i = this.aq.findViewById(R.id.content_layout);
        this.h = (HealthTextView) this.aq.findViewById(R.id.tv_child);
        this.l = (LinearLayout) this.aq.findViewById(R.id.lin_countdown);
        this.n = (HealthDivider) this.aq.findViewById(R.id.divider);
        this.aa = this.aq.findViewById(R.id.layout_normal);
        this.z = this.aq.findViewById(R.id.layout_member);
        this.x = this.aq.findViewById(R.id.layout_member_and_not);
        this.ae = (HealthTextView) this.aq.findViewById(R.id.tv_not_member_price);
        this.af = (HealthTextView) this.aq.findViewById(R.id.tv_not_member_lin_price);
        this.ah = (HealthTextView) this.aq.findViewById(R.id.tv_not_member_describe);
        RelativeLayout relativeLayout = (RelativeLayout) this.aq.findViewById(R.id.rl_not_memeber);
        this.ag = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.c = (HealthTextView) this.aq.findViewById(R.id.tv_be_member_price);
        this.d = (HealthTextView) this.aq.findViewById(R.id.tv_be_member_lin_price);
        this.f3466a = (HealthTextView) this.aq.findViewById(R.id.tv_be_member_describe);
        RelativeLayout relativeLayout2 = (RelativeLayout) this.aq.findViewById(R.id.rl_be_memeber);
        this.b = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        this.ab = (HealthTextView) this.aq.findViewById(R.id.tv_member_price);
        this.w = (TextView) this.aq.findViewById(R.id.tv_member_line_price);
        this.y = (HealthTextView) this.aq.findViewById(R.id.tv_member_description);
        HealthButton healthButton2 = (HealthButton) this.aq.findViewById(R.id.btn_member_submit);
        this.ac = healthButton2;
        healthButton2.setOnClickListener(this);
        o();
        l();
    }

    private void o() {
        if (nsn.ag(this.f)) {
            ViewGroup.LayoutParams layoutParams = this.x.getLayoutParams();
            layoutParams.width = nla.b(3, 5);
            this.x.setLayoutParams(layoutParams);
            this.z.setPadding(nrr.e(this.f, 110.0f), nrr.e(this.f, 8.0f), nrr.e(this.f, 110.0f), nrr.e(this.f, 8.0f));
        }
    }

    private void l() {
        if (LanguageUtil.bc(com.huawei.haf.application.BaseApplication.e())) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(new float[]{d(22.0f), d(22.0f), 0.0f, 0.0f, 0.0f, 0.0f, d(22.0f), d(22.0f)});
            gradientDrawable.setColor(ContextCompat.getColor(this.f, R.color._2131296768_res_0x7f090200));
            this.b.setBackground(gradientDrawable);
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setShape(0);
            gradientDrawable2.setCornerRadii(new float[]{0.0f, 0.0f, d(22.0f), d(22.0f), d(22.0f), d(22.0f), 0.0f, 0.0f});
            gradientDrawable2.setColor(ContextCompat.getColor(this.f, R.color._2131298877_res_0x7f090a3d));
            this.ag.setBackground(gradientDrawable2);
            return;
        }
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(0);
        gradientDrawable3.setCornerRadii(new float[]{0.0f, 0.0f, d(22.0f), d(22.0f), d(22.0f), d(22.0f), 0.0f, 0.0f});
        gradientDrawable3.setColor(ContextCompat.getColor(this.f, R.color._2131296768_res_0x7f090200));
        this.b.setBackground(gradientDrawable3);
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setShape(0);
        gradientDrawable4.setCornerRadii(new float[]{d(22.0f), d(22.0f), 0.0f, 0.0f, 0.0f, 0.0f, d(22.0f), d(22.0f)});
        gradientDrawable4.setColor(ContextCompat.getColor(this.f, R.color._2131298877_res_0x7f090a3d));
        this.ag.setBackground(gradientDrawable4);
    }

    private void setAlignment(boolean z) {
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams.addRule(8, R.id.rl_not_memeber);
            layoutParams.addRule(6, R.id.rl_not_memeber);
            this.b.setLayoutParams(layoutParams);
            return;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.ag.getLayoutParams();
        layoutParams2.addRule(8, R.id.rl_be_memeber);
        layoutParams2.addRule(6, R.id.rl_be_memeber);
        this.ag.setLayoutParams(layoutParams2);
    }

    private static int d(float f) {
        return nsn.c(BaseApplication.getContext(), f);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("TradeInformationLayout", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.btn_submit) {
            a(this.ak);
            this.aj = "-1";
            e(AnalyticsValue.TRADE_BUY.value(), this.ak.getMicroPrice());
        } else if (id == R.id.rl_not_memeber) {
            a(this.ak);
            this.aj = "-1";
            e(AnalyticsValue.TRADE_BUY.value(), this.ak.getMicroPrice());
        } else if (id == R.id.rl_be_memeber) {
            a();
            this.aj = "1";
            e(AnalyticsValue.TRADE_BUY.value(), this.ad.getMicroPrice());
        } else if (id == R.id.btn_member_submit) {
            a(this.ad);
            this.aj = "1";
            e(AnalyticsValue.TRADE_BUY.value(), this.ad.getMicroPrice());
        } else {
            LogUtil.h("TradeInformationLayout", "onClick not match");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a() {
        String str;
        if (gpo.b()) {
            if (this.e != null) {
                str = "huaweischeme://healthapp/router/openwith?address=com.huawei.health.h5.vip?h5pro=true&urlType=4&pName=com.huawei.health&path=MemberCenter&currentBenefit=1&benefitList=%5B1%5D&pullfrom=" + this.e.get(WebViewHelp.BI_KEY_PULL_FROM) + "&resourceId=" + this.e.get("resourceId") + "&resourceName=" + this.e.get("resourceName") + "&pullOrder=" + this.e.get("pullOrder");
            } else {
                str = "huaweischeme://healthapp/router/openwith?address=com.huawei.health.h5.vip?h5pro=true&urlType=4&pName=com.huawei.health&path=MemberCenter&currentBenefit=1&benefitList=%5B1%5D";
            }
            AppRouter.zi_(Uri.parse((str + "&popUpTypeSelect=true") + "&trigResMemberPrice=1&trigResType=" + this.ao + "&trigResName=" + this.ar)).c(this.f);
            return;
        }
        H5ProLaunchOption.Builder addPath = new H5ProLaunchOption.Builder().addPath(gpn.c("from=9&trigResMemberPrice=1&trigResType=" + this.ao + "&trigResName=" + this.ar));
        Map<String, String> map = this.e;
        if (map != null) {
            addPath.addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, map.get(WebViewHelp.BI_KEY_PULL_FROM)).addGlobalBiParam("resourceId", this.e.get("resourceId")).addGlobalBiParam("resourceName", this.e.get("resourceName")).addGlobalBiParam("pullOrder", this.e.get("pullOrder"));
        }
        bzs.e().loadH5ProApp(this.f, "com.huawei.health.h5.vip", addPath);
    }

    private void a(ProductSummary productSummary) {
        if (productSummary == null) {
            LogUtil.h("TradeInformationLayout", "onClick productSummary = null");
            return;
        }
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productSummary.getProductId());
        productInfo.setProductName(productSummary.getProductName());
        productInfo.setPurchasePolicy(productSummary.getPurchasePolicy());
        productInfo.setResourceType(this.ao);
        productInfo.setLinkType(productSummary.getLinkType());
        productInfo.setLinkValue(productSummary.getLinkValue());
        Map<String, String> map = this.e;
        if (map != null) {
            productInfo.setBiParams(map);
        }
        String json = new Gson().toJson(productInfo);
        LogUtil.a("TradeInformationLayout", "skipByResourceId productInfoString = ", json);
        if (this.t) {
            Intent intent = new Intent(this.f, (Class<?>) TradeSureOrderActivity.class);
            intent.putExtra("productId", productSummary.getProductId());
            intent.putExtra("productInfo", json);
            intent.setFlags(268435456);
            BaseApplication.getContext().startActivity(intent);
            return;
        }
        d(json);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        ProductManager.getProductDetails(str, new b(this, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Object obj) {
        if (i == 0 && (obj instanceof Product)) {
            LogUtil.a("TradeInformationLayout", "getProductSummaryInfo success");
            Product product = (Product) obj;
            LogUtil.a("TradeInformationLayout", product.toString());
            this.ak = c(product);
            e(AnalyticsValue.TRADE_OFFERING_DETAIL.value(), this.ak.getMicroPrice());
            this.ao = product.getResourceType();
            LogUtil.a("TradeInformationLayout", this.ak.toString());
            this.p.sendEmptyMessage(1101);
            return;
        }
        LogUtil.h("TradeInformationLayout", "getProductSummaryInfo fail errorCode = ", Integer.valueOf(i));
        this.p.sendEmptyMessage(1102);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i) {
        this.m = new CountDownLatch(1);
        d(false);
        ProductManager.getProductSummaryInfo(str, i, new b(this, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<ProductSummary> list) {
        if (koq.b(list)) {
            LogUtil.h("TradeInformationLayout", "productSummaries is empty");
            this.aq.setVisibility(8);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ProductSummary productSummary : list) {
            if (productSummary.isMemberFlag()) {
                arrayList.add(productSummary);
            } else {
                arrayList2.add(productSummary);
            }
        }
        if (koq.b(arrayList2)) {
            this.p.sendEmptyMessage(1102);
            LogUtil.h("TradeInformationLayout", "normalProductSummaries is empty");
            return;
        }
        a(arrayList2);
        this.ak = (ProductSummary) arrayList2.get(0);
        e(AnalyticsValue.TRADE_OFFERING_DETAIL.value(), this.ak.getMicroPrice());
        if (koq.b(arrayList)) {
            this.p.sendEmptyMessage(1101);
            LogUtil.a("TradeInformationLayout", "not have memberPrice");
            return;
        }
        a(arrayList);
        try {
            LogUtil.a("TradeInformationLayout", "wait:", Boolean.valueOf(this.m.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("TradeInformationLayout", "await InterruptedException");
        }
        if (this.v) {
            this.ad = (ProductSummary) arrayList.get(0);
            this.p.sendEmptyMessage(1108);
        } else {
            this.ad = (ProductSummary) arrayList.get(0);
            this.p.sendEmptyMessage(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED);
        }
    }

    private static List<ProductSummary> a(List<ProductSummary> list) {
        Collections.sort(list, new Comparator() { // from class: gld
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(((ProductSummary) obj2).getPriority(), ((ProductSummary) obj).getPriority());
                return compare;
            }
        });
        return list;
    }

    private void d(boolean z) {
        LogUtil.a("TradeInformationLayout", "getVipInfo start");
        ThreadPoolManager.d().execute(new c(this, z));
    }

    static class c implements Runnable {
        WeakReference<TradeInformationLayout> b;
        boolean e;

        c(TradeInformationLayout tradeInformationLayout, boolean z) {
            this.b = new WeakReference<>(tradeInformationLayout);
            this.e = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new VipCallback() { // from class: com.huawei.health.tradeservice.view.TradeInformationLayout.c.4
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    if (c.this.b.get() == null) {
                        LogUtil.h("TradeInformationLayout", "trade layout is destroyed");
                        return;
                    }
                    TradeInformationLayout tradeInformationLayout = c.this.b.get();
                    if (obj == null) {
                        LogUtil.h("TradeInformationLayout", "getVipInfo onSuccess result is null");
                        tradeInformationLayout.m.countDown();
                        return;
                    }
                    if (obj instanceof UserMemberInfo) {
                        UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
                        LogUtil.c("TradeInformationLayout", "getVipInfo mUserMemberInfo = ", userMemberInfo.toString());
                        if (!c.this.e) {
                            tradeInformationLayout.v = userMemberInfo.getMemberFlag() == 1 && !gpn.d(userMemberInfo);
                        } else {
                            LogUtil.a("TradeInformationLayout", "do refresh");
                            boolean z = userMemberInfo.getMemberFlag() == 1 && !gpn.d(userMemberInfo);
                            if (tradeInformationLayout.v == z) {
                                LogUtil.a("TradeInformationLayout", "memberStatus not change");
                                return;
                            } else if (z) {
                                tradeInformationLayout.p.sendEmptyMessage(1108);
                                return;
                            } else {
                                tradeInformationLayout.p.sendEmptyMessage(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED);
                                return;
                            }
                        }
                    }
                    tradeInformationLayout.m.countDown();
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                    if (c.this.b.get() != null) {
                        c.this.b.get().m.countDown();
                    }
                    LogUtil.h("TradeInformationLayout", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                }
            });
        }
    }

    private ProductSummary c(Product product) {
        LogUtil.a("TradeInformationLayout", "setProductSummaryData");
        ProductSummary productSummary = new ProductSummary();
        productSummary.setCurrency(product.getCurrency());
        productSummary.setMicroPrice(product.getMicroPrice());
        productSummary.setNowTime(product.getNowTime());
        productSummary.setSaleTime(product.getSaleTime());
        productSummary.setPromotion(product.getPromotion());
        productSummary.setProductName(product.getProductName());
        productSummary.setProductId(this.am);
        productSummary.setPurchasePolicy(product.getPurchasePolicy());
        productSummary.setMemberPriceRetentionVisibility(product.isMemberPriceRetentionVisibility());
        productSummary.setMemberPriceRetentionDesc(product.getMemberPriceRetentionDesc());
        productSummary.setLinkType(product.getLinkType());
        productSummary.setLinkValue(product.getLinkValue());
        List<Product> giveaways = product.getGiveaways();
        if (koq.c(giveaways)) {
            Iterator<Product> it = giveaways.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (TextUtils.equals(it.next().getAttachType(), "giveaway")) {
                    productSummary.setGiveawaysExist(1);
                    break;
                }
            }
        }
        return productSummary;
    }

    private void d(String str) {
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("TradeInformationLayout", "activity = null");
        } else {
            ((PayApi) Services.a("TradeService", PayApi.class)).buyByProductId(activity, 0, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ProductSummary productSummary = this.ad;
        if (productSummary == null) {
            LogUtil.h("TradeInformationLayout", "mMemberProductSummary = null");
            this.aq.setVisibility(8);
            return;
        }
        String currency = productSummary.getCurrency();
        this.c.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845864_res_0x7f0220a8), gla.a(currency, this.ad.getMicroPrice() / 1000000.0f)));
        String priceDesc = this.ad.getPriceDesc();
        if (!TextUtils.isEmpty(priceDesc)) {
            this.f3466a.setText(priceDesc);
            this.f3466a.setVisibility(0);
        }
        if (this.ad.getPromotion() == null) {
            LogUtil.a("TradeInformationLayout", "timeLimitedPromotion is null , not need show");
            return;
        }
        this.d.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845863_res_0x7f0220a7), gla.a(currency, r2.getMicroOriginPrice() / 1000000.0f)));
        this.d.getPaint().setFlags(16);
        this.d.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        long microPrice;
        if (this.ad == null) {
            LogUtil.h("TradeInformationLayout", "mMemberProductSummary = null");
            this.aq.setVisibility(8);
            return;
        }
        if (!TextUtils.isEmpty(this.j)) {
            this.ac.setText(this.j);
        }
        String currency = this.ad.getCurrency();
        this.ab.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845864_res_0x7f0220a8), gla.a(currency, this.ad.getMicroPrice() / 1000000.0f)));
        TimeLimitedPromotion promotion = this.ak.getPromotion();
        if (promotion != null && promotion.getMicroOriginPrice() > 0) {
            microPrice = promotion.getMicroOriginPrice();
        } else {
            microPrice = this.ak.getMicroPrice();
        }
        this.w.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845863_res_0x7f0220a7), gla.a(currency, microPrice / 1000000.0f)));
        this.w.getPaint().setFlags(16);
        this.w.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ProductSummary productSummary = this.ak;
        if (productSummary == null) {
            LogUtil.h("TradeInformationLayout", "refreshNotMemberUiByResource mProductSummary = null");
            this.aq.setVisibility(8);
            return;
        }
        String currency = productSummary.getCurrency();
        this.ae.setText(gla.a(currency, this.ak.getMicroPrice() / 1000000.0f));
        String priceDesc = this.ak.getPriceDesc();
        if (!TextUtils.isEmpty(priceDesc)) {
            this.ah.setText(priceDesc);
            this.ah.setVisibility(0);
            setAlignment(true);
        } else {
            setAlignment(false);
        }
        if (this.ak.getPromotion() == null) {
            LogUtil.a("TradeInformationLayout", "timeLimitedPromotion is null , not need show");
            return;
        }
        this.af.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845863_res_0x7f0220a7), gla.a(currency, r2.getMicroOriginPrice() / 1000000.0f)));
        this.af.getPaint().setFlags(16);
        this.af.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.ak == null) {
            LogUtil.h("TradeInformationLayout", "mProductSummary = null");
            this.aq.setVisibility(8);
            return;
        }
        if (!TextUtils.isEmpty(this.j)) {
            this.g.setText(this.j);
        }
        String currency = this.ak.getCurrency();
        gla.aNL_(gla.a(currency, this.ak.getMicroPrice() / 1000000.0f), gla.b(currency), this.al);
        if (this.ak.getGiveawaysExist() == 1) {
            this.t = true;
            this.r.setVisibility(0);
        } else {
            this.t = false;
            this.r.setVisibility(8);
        }
        e(currency);
    }

    private void e(String str) {
        this.l.setVisibility(8);
        this.n.setVisibility(0);
        TimeLimitedPromotion promotion = this.ak.getPromotion();
        if (promotion != null && promotion.getMicroOriginPrice() > 0) {
            this.u.setText(gla.a(str, promotion.getMicroOriginPrice() / 1000000.0f));
            this.u.getPaint().setFlags(16);
        }
        if (this.ak.getSaleTime() - this.ak.getNowTime() > 0) {
            this.l.setVisibility(0);
            this.n.setVisibility(8);
            this.q = false;
            String a2 = UnitUtil.a("yyyy/MM/dd HH:mm:ss", this.ak.getSaleTime());
            gla.aNK_(String.format(Locale.ENGLISH, getResources().getString(R.string._2130844780_res_0x7f021c6c), a2), a2, this.k, R.color.emui_accent);
        } else {
            this.q = true;
            if (promotion == null) {
                this.g.setVisibility(0);
                LogUtil.a("TradeInformationLayout", "timeLimitedPromotion = null");
                return;
            }
            long promotionEndTime = promotion.getPromotionEndTime() - this.ak.getNowTime();
            if (promotionEndTime > 0) {
                this.l.setVisibility(0);
                this.n.setVisibility(8);
                gle gleVar = new gle(this.p, this.k, promotionEndTime);
                this.ap = gleVar;
                gleVar.c();
            }
        }
        g();
        this.g.setVisibility(0);
    }

    private void g() {
        if (this.q) {
            this.g.setClickable(true);
            LogUtil.a("TradeInformationLayout", "isCanPay is true");
        } else {
            this.g.setClickable(false);
            this.g.setBackgroundResource(R.drawable._2131431978_res_0x7f0b122a);
            this.g.setTextColor(this.f.getResources().getColor(R.color._2131297310_res_0x7f09041e));
        }
    }

    private void e(String str, long j) {
        if (this.ak == null) {
            LogUtil.h("TradeInformationLayout", "mProductSummary = null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", String.valueOf(this.ao));
        hashMap.put("name", this.ak.getProductName());
        hashMap.put(ParsedFieldTag.PRICE, String.valueOf(j / 1000000.0f));
        hashMap.put("id", this.ak.getProductId());
        MarketingBiUtils.a(hashMap, this.e);
        if (!TextUtils.isEmpty(this.ai)) {
            hashMap.put("resourcePositionId", this.ai);
        }
        if (!TextUtils.isEmpty(this.o)) {
            hashMap.put("fromPageTitle", this.o);
        }
        if (!TextUtils.isEmpty(this.aj)) {
            hashMap.put("priceType", this.aj);
        }
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), str, hashMap, 0);
    }

    public void e() {
        gle gleVar = this.ap;
        if (gleVar != null) {
            gleVar.e();
        }
    }

    public void b() {
        if (this.ak != null) {
            d(true);
        } else {
            LogUtil.a("TradeInformationLayout", "not need refresh");
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        ProductSummary productSummary;
        String accountInfo = LoginInit.getInstance(this.f).getAccountInfo(1011);
        long b2 = SharedPreferenceManager.b(String.valueOf(101010), accountInfo + "back_intercept", 0L);
        if (!this.v && (productSummary = this.ad) != null && productSummary.isMemberPriceRetentionVisibility() && !nsj.a(b2, System.currentTimeMillis())) {
            new glc(this.f, this, iBaseResponseCallback, this.ad, this.ak).show();
            gla.e(1, this.f);
            SharedPreferenceManager.e(String.valueOf(101010), accountInfo + "back_intercept", System.currentTimeMillis());
            return;
        }
        iBaseResponseCallback.d(0, null);
    }
}
