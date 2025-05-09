package com.huawei.health.tradeservice.activity;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.tradeservice.cloud.CardManager;
import com.huawei.health.tradeservice.cloud.OrderAudiosPackageRsq;
import com.huawei.health.tradeservice.cloud.OrderManager;
import com.huawei.health.tradeservice.cloud.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.trade.datatype.OrderDetail;
import com.huawei.trade.datatype.ProductResourceInfo;
import com.huawei.trade.datatype.ResourceSkipAddr;
import com.huawei.trade.datatype.ResourceSummary;
import com.huawei.trade.datatype.ThirdCardInfo;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gla;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.koq;
import defpackage.njn;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class TradeOrderDetailActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private List<ThirdCardInfo> f3447a;
    private HealthTextView aa;
    private HealthTextView ab;
    private HealthTextView ac;
    private int ad;
    private String af;
    private ImageView ag;
    private HealthTextView ah;
    private LinearLayout ai;
    private boolean b;
    private boolean c = false;
    private boolean d = false;
    private boolean e;
    private HealthTextView f;
    private ImageView g;
    private RelativeLayout h;
    private Context i;
    private View j;
    private Handler k;
    private CustomTitleBar l;
    private HealthTextView m;
    private List<ProductResourceInfo> n;
    private ImageView o;
    private HealthTextView p;
    private HealthTextView q;
    private String r;
    private HealthTextView s;
    private View t;
    private HealthTextView u;
    private ProductResourceInfo v;
    private HealthTextView w;
    private OrderDetail x;
    private HealthTextView y;
    private ResourceSkipAddr z;

    /* loaded from: classes4.dex */
    static class e extends BaseHandler<TradeOrderDetailActivity> {
        e(TradeOrderDetailActivity tradeOrderDetailActivity) {
            super(tradeOrderDetailActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aNu_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TradeOrderDetailActivity tradeOrderDetailActivity, Message message) {
            int i = message.what;
            if (i == 1101) {
                tradeOrderDetailActivity.g();
                return;
            }
            if (i == 1102) {
                tradeOrderDetailActivity.m();
                return;
            }
            if (i == 1104) {
                tradeOrderDetailActivity.i();
            } else if (i == 1106) {
                tradeOrderDetailActivity.n();
            } else {
                LogUtil.h("TradeOrderDetailActivity", "handleMessageWhenReferenceNotNull not match ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final ProductResourceInfo productResourceInfo) {
        ProductResourceInfo productResourceInfo2 = this.v;
        if (productResourceInfo2 == null) {
            LogUtil.h("TradeOrderDetailActivity", "TradeHandler activity.mProduct==null");
        } else {
            OrderManager.queryAudiosPackageBySeriesId(productResourceInfo2.getResourceId(), new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("TradeOrderDetailActivity", "queryAudiosPackageBySeriesId onResponse");
                    if (obj == null) {
                        LogUtil.h("TradeOrderDetailActivity", "queryAudiosPackageBySeriesId objData == null");
                        return;
                    }
                    int i2 = 0;
                    if (obj instanceof OrderAudiosPackageRsq) {
                        ArrayList<OrderAudiosPackageRsq.SleepAudioSeries> packageList = ((OrderAudiosPackageRsq) obj).getPackageList();
                        if (koq.c(packageList) && packageList.get(0).getSleepAudioSeries() != null) {
                            i2 = packageList.get(0).getSleepAudioSeries().getEnableNewUrl();
                            LogUtil.a("TradeOrderDetailActivity", "enableNewUrl : ", Integer.valueOf(i2));
                        }
                    }
                    ResourceSummary resourceSummary = new ResourceSummary();
                    resourceSummary.setResourceId(productResourceInfo.getResourceId());
                    resourceSummary.setResourceType(productResourceInfo.getResourceType());
                    if (i2 != 0) {
                        TradeOrderDetailActivity.this.z = new ResourceSkipAddr();
                        TradeOrderDetailActivity.this.z.setAddrType(1);
                        TradeOrderDetailActivity.this.z.setAddr("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&statusBarTextBlack&showStatusBar&isImmerse&path=seriesCourse&type=" + resourceSummary.getResourceType() + "&id=" + resourceSummary.getResourceId());
                        TradeOrderDetailActivity.this.k.sendEmptyMessage(1106);
                        LogUtil.a("TradeOrderDetailActivity", "getSkipUrl is new enableNewUrl");
                        return;
                    }
                    ResourceManager.getResourceSkipAddr(resourceSummary, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.1.3
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i3, Object obj2) {
                            if (i3 == 0 && (obj2 instanceof ResourceSkipAddr)) {
                                TradeOrderDetailActivity.this.z = (ResourceSkipAddr) obj2;
                                TradeOrderDetailActivity.this.k.sendEmptyMessage(1106);
                                return;
                            }
                            LogUtil.h("TradeOrderDetailActivity", "cardQueryInfo fail errorCode = ", Integer.valueOf(i3));
                        }
                    });
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (CommonUtil.bh() && (isLargerThanEmui910(CommonUtil.r()) || CommonUtil.az())) {
            getWindow().getDecorView().setSystemUiVisibility(9216);
            setStatusBarColor();
            setTranslucentStatus(getWindow());
        }
        if (nsn.p()) {
            setContentView(R.layout.activity_order_detail_large);
        } else {
            setContentView(R.layout.activity_order_detail);
        }
        getWindow().setBackgroundDrawable(null);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("TradeOrderDetailActivity", "intent = null");
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("orderCode");
        this.r = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.a("TradeOrderDetailActivity", "mOrderCode is empty");
            finish();
            return;
        }
        LogUtil.a("TradeOrderDetailActivity", "mOrderCode:", this.r);
        this.b = intent.getBooleanExtra("invoice", false);
        this.e = intent.getBooleanExtra("pageFrom", false);
        this.k = new e(this);
        this.i = this;
        f();
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e();
    }

    private void b() {
        this.l.setTitleTextColor(this.i.getResources().getColor(R.color._2131297592_res_0x7f090538));
        if (LanguageUtil.bc(this.i)) {
            this.l.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
            this.g.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            LogUtil.a("TradeOrderDetailActivity", "setLeftButtonDrawable");
            this.l.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
        }
    }

    private void f() {
        this.ab = (HealthTextView) findViewById(R.id.tv_show_quantity);
        this.t = findViewById(R.id.rl_hint);
        this.p = (HealthTextView) findViewById(R.id.tv_hint);
        this.ai = (LinearLayout) findViewById(R.id.lin_content);
        j();
        this.ah = (HealthTextView) findViewById(R.id.tv_order_status);
        this.aa = (HealthTextView) findViewById(R.id.tv_order_detail);
        this.ag = (ImageView) findViewById(R.id.img_order_detail);
        View findViewById = findViewById(R.id.lin_contact);
        this.j = findViewById;
        findViewById.setOnClickListener(this);
        this.o = (ImageView) findViewById(R.id.img_goods);
        this.ac = (HealthTextView) findViewById(R.id.tv_show_price);
        this.f = (HealthTextView) findViewById(R.id.tv_class_name);
        this.y = (HealthTextView) findViewById(R.id.tv_order_number);
        this.w = (HealthTextView) findViewById(R.id.tv_order_time);
        this.u = (HealthTextView) findViewById(R.id.tv_pay_mode);
        this.l = (CustomTitleBar) findViewById(R.id.order_detail_title);
        this.h = (RelativeLayout) findViewById(R.id.rl_class_info);
        this.g = (ImageView) findViewById(R.id.img_contact);
        this.m = (HealthTextView) findViewById(R.id.tv_discount_amount);
        this.s = (HealthTextView) findViewById(R.id.tv_lin_price);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_apply_invoice);
        this.q = healthTextView;
        healthTextView.setOnClickListener(this);
    }

    private void e() {
        if (TextUtils.isEmpty(this.r)) {
            LogUtil.h("TradeOrderDetailActivity", "mOrderCode is empty");
            m();
        } else {
            OrderManager.orderQueryDetails(this.r, new a(this));
        }
    }

    private void a() {
        CardManager.cardQueryInfo(this.r, new c(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.ab.setText(String.format(getString(R.string._2130844798_res_0x7f021c7e), 1));
        ProductResourceInfo productInfo = this.x.getProductInfo();
        this.v = productInfo;
        this.f.setText(productInfo.getProductName());
        nrf.cIU_(this.v.getProductUrl(), this.o, (int) com.huawei.haf.application.BaseApplication.e().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238));
        a(this.v.getResourceType());
        this.n = this.x.getGiveawaysInfo();
        c();
        double payPrice = this.x.getPayPrice() / 1000000.0f;
        String currency = this.x.getCurrency();
        double originPrice = this.x.getOriginPrice() / 1000000.0f;
        if (Math.abs(originPrice - payPrice) < 1.0E-6d) {
            this.s.setVisibility(8);
        } else {
            this.s.setText(gla.a(currency, originPrice));
            this.s.getPaint().setFlags(16);
        }
        double preference = this.x.getPreference() / 1000000.0f;
        if (preference < 1.0E-6d) {
            this.m.setVisibility(8);
        } else {
            this.m.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845020_res_0x7f021d5c), gla.a(currency, preference)));
        }
        gla.aNK_(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844783_res_0x7f021c6f), this.x.getOrderId()), this.x.getOrderId(), this.y, R.color._2131299236_res_0x7f090ba4);
        String d = d();
        gla.aNK_(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844787_res_0x7f021c73), d), d, this.w, R.color._2131299236_res_0x7f090ba4);
        d(this.x.getState());
        b(currency, payPrice);
        if (this.b) {
            o();
        } else {
            LogUtil.a("TradeOrderDetailActivity", "not need show invoice");
        }
        a("1");
    }

    private void o() {
        int invoiceStatus = this.x.getInvoiceStatus();
        this.ad = invoiceStatus;
        LogUtil.a("TradeOrderDetailActivity", "enter showInvoiceButton ", Integer.valueOf(invoiceStatus));
        switch (this.ad) {
            case 0:
                this.q.setText(BaseApplication.getContext().getString(R.string._2130845515_res_0x7f021f4b));
                this.q.setVisibility(0);
                break;
            case 1:
            case 2:
            case 3:
                this.q.setText(BaseApplication.getContext().getString(R.string._2130845516_res_0x7f021f4c));
                this.q.setVisibility(0);
                break;
            case 4:
            case 5:
            case 6:
                this.q.setVisibility(8);
                break;
            default:
                this.q.setVisibility(8);
                LogUtil.a("TradeOrderDetailActivity", "status not support ", Integer.valueOf(this.ad));
                break;
        }
    }

    private String d() {
        if (LanguageUtil.bt(this.i) || LanguageUtil.bp(this.i)) {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Long.valueOf(this.x.getGenTime()));
        }
        if (LanguageUtil.ai(this.i)) {
            return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Long.valueOf(this.x.getGenTime()));
        }
        return UnitUtil.a("yyyy/MM/dd HH:mm:ss", this.x.getGenTime());
    }

    private void b(String str, double d) {
        String string;
        LogUtil.a("TradeOrderDetailActivity", "prefMode:", Integer.valueOf(this.x.getPrefMode()));
        if (this.x.getPrefMode() == 2 || this.x.getPrefMode() == 4) {
            string = BaseApplication.getContext().getString(R.string._2130845203_res_0x7f021e13);
            this.ac.setText(BaseApplication.getContext().getString(R.string._2130845204_res_0x7f021e14));
        } else if (this.x.getPrefMode() == 5) {
            string = BaseApplication.getContext().getString(R.string._2130845277_res_0x7f021e5d);
            this.ac.setText(string);
        } else {
            String e2 = gla.e(this.x.getPayType());
            this.ac.setText(gla.a(str, d));
            string = e2;
        }
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("TradeOrderDetailActivity", "payType is empty");
            this.u.setVisibility(8);
        } else {
            this.u.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844960_res_0x7f021d20), string));
        }
    }

    private void a(int i) {
        if (i == 3) {
            a();
            this.c = true;
        } else {
            this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!TradeOrderDetailActivity.this.e) {
                        TradeOrderDetailActivity.this.a("2");
                        TradeOrderDetailActivity tradeOrderDetailActivity = TradeOrderDetailActivity.this;
                        tradeOrderDetailActivity.b("", tradeOrderDetailActivity.v);
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    TradeOrderDetailActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void c() {
        if (koq.c(this.n)) {
            Iterator<ProductResourceInfo> it = this.n.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().getResourceType() == 3) {
                    if (!this.c) {
                        a();
                    }
                    this.d = true;
                }
            }
            LogUtil.a("TradeOrderDetailActivity", "isGiveawayHaveCard:", Boolean.valueOf(this.d));
            if (!this.d) {
                b(this.n);
            } else {
                LogUtil.a("TradeOrderDetailActivity", "giveaway have card");
            }
        }
    }

    private void d(int i) {
        if (i == 0) {
            this.ah.setText(this.i.getString(R.string._2130844791_res_0x7f021c77));
            this.aa.setText(this.i.getString(R.string._2130844792_res_0x7f021c78));
            this.aa.setVisibility(0);
            this.ag.setImageResource(R.drawable._2131430967_res_0x7f0b0e37);
            return;
        }
        if (i == 1) {
            this.ah.setText(this.i.getString(R.string._2130844790_res_0x7f021c76));
            this.ag.setImageResource(R.drawable._2131430967_res_0x7f0b0e37);
        } else if (i == 2) {
            this.ah.setText(this.i.getString(R.string._2130845614_res_0x7f021fae));
            this.ag.setImageResource(R.drawable._2131430968_res_0x7f0b0e38);
        } else if (i == 3) {
            this.ah.setText(this.i.getString(R.string._2130845615_res_0x7f021faf));
            this.ag.setImageResource(R.drawable._2131430968_res_0x7f0b0e38);
        } else {
            LogUtil.h("TradeOrderDetailActivity", "state not match:", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.d) {
            b(this.n);
        }
        if (TextUtils.isEmpty(this.v.getResourceId())) {
            LogUtil.h("TradeOrderDetailActivity", "resourceId is empty");
            return;
        }
        for (ThirdCardInfo thirdCardInfo : this.f3447a) {
            if (this.v.getResourceId().equals(thirdCardInfo.getCardPoolId())) {
                a(thirdCardInfo);
                return;
            }
        }
    }

    private void a(final ThirdCardInfo thirdCardInfo) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.viewstub_card);
        if (viewStub == null) {
            LogUtil.h("TradeOrderDetailActivity", "showCartData viewStub = null");
            return;
        }
        viewStub.inflate();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_card_number);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.tv_card_password);
        gla.aNK_(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844785_res_0x7f021c71), thirdCardInfo.getCardNum()), thirdCardInfo.getCardNum(), healthTextView, R.color._2131299236_res_0x7f090ba4);
        gla.aNK_(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844786_res_0x7f021c72), thirdCardInfo.getCardPass()), thirdCardInfo.getCardPass(), healthTextView2, R.color._2131299236_res_0x7f090ba4);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TradeOrderDetailActivity.this.e) {
                    TradeOrderDetailActivity.this.b(thirdCardInfo.getLinkValue(), TradeOrderDetailActivity.this.v);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    TradeOrderDetailActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private void b(List<ProductResourceInfo> list) {
        LogUtil.a("TradeOrderDetailActivity", "enter addGiveawayView");
        if (koq.b(list)) {
            LogUtil.a("TradeOrderDetailActivity", "giveaways is empty");
            return;
        }
        ViewStub viewStub = (ViewStub) findViewById(R.id.viewstub_giveaway);
        if (viewStub != null) {
            LogUtil.h("TradeOrderDetailActivity", "addGiveawayView viewStub = null");
            viewStub.inflate();
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin_giveaway);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.lin_combine_product);
        linearLayout.removeAllViews();
        linearLayout2.removeAllViews();
        for (ProductResourceInfo productResourceInfo : list) {
            if (TextUtils.equals(productResourceInfo.getAttachType(), "giveaway")) {
                aNr_(productResourceInfo, linearLayout);
            } else {
                aNr_(productResourceInfo, linearLayout2);
            }
        }
    }

    private void aNr_(ProductResourceInfo productResourceInfo, LinearLayout linearLayout) {
        e("0", productResourceInfo.getProductName());
        View inflate = View.inflate(this, R.layout.trade_affiliate_item, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_affiliate);
        ((HealthTextView) inflate.findViewById(R.id.tv_affiliate_name)).setText(productResourceInfo.getProductName());
        if (!TextUtils.isEmpty(productResourceInfo.getProductUrl())) {
            imageView.setVisibility(0);
            nrf.cIU_(productResourceInfo.getProductUrl(), imageView, (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362361_res_0x7f0a0239));
        }
        ((HealthTextView) inflate.findViewById(R.id.tv_affiliate_mark)).setVisibility((TextUtils.equals(productResourceInfo.getAttachType(), "giveaway") && productResourceInfo.getShowGiveawayFlag()) ? 0 : 8);
        String[] strArr = new String[1];
        if (productResourceInfo.getResourceType() == 3) {
            aNt_(productResourceInfo, inflate, strArr);
        }
        if (!TextUtils.isEmpty(productResourceInfo.getButtonDescAsGiveaway()) && !TextUtils.isEmpty(productResourceInfo.getLinkValue())) {
            aNs_(productResourceInfo, inflate, strArr[0]);
        }
        linearLayout.addView(inflate);
    }

    private void aNt_(ProductResourceInfo productResourceInfo, View view, String[] strArr) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.tv_affiliate_card_num);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.tv_affiliate_card_password);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.tv_affiliate_card_expirate_time);
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.btn_affiliate);
        for (ThirdCardInfo thirdCardInfo : this.f3447a) {
            if (productResourceInfo.getResourceId().equals(thirdCardInfo.getCardPoolId())) {
                strArr[0] = thirdCardInfo.getCardPass();
                healthTextView.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844785_res_0x7f021c71), thirdCardInfo.getCardNum()));
                healthTextView2.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844786_res_0x7f021c72), thirdCardInfo.getCardPass()));
                healthTextView3.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130845690_res_0x7f021ffa), UnitUtil.a("yyyy/MM/dd", thirdCardInfo.getExpirateTime())));
                healthTextView3.setVisibility(0);
                LogUtil.a("TradeOrderDetailActivity", "isEnterprisePurchase", Boolean.valueOf(thirdCardInfo.isEnterprisePurchase()));
                if (thirdCardInfo.isEnterprisePurchase()) {
                    int cardStatus = thirdCardInfo.getCardStatus();
                    b(productResourceInfo, cardStatus);
                    if (cardStatus == 2) {
                        healthButton.setBackgroundResource(R.drawable.button_background_emphasize_disable);
                    }
                    healthTextView.setVisibility(8);
                    healthTextView2.setVisibility(8);
                    return;
                }
                healthTextView.setVisibility(0);
                healthTextView2.setVisibility(0);
                return;
            }
        }
    }

    private void b(ProductResourceInfo productResourceInfo, int i) {
        if (productResourceInfo == null) {
            LogUtil.h("TradeOrderDetailActivity", "product or thirdCardInfo is null");
            return;
        }
        if (i == 1) {
            productResourceInfo.setButtonDescAsGiveaway(getString(R.string._2130846715_res_0x7f0223fb));
            return;
        }
        if (i == 2) {
            productResourceInfo.setButtonDescAsGiveaway(getString(R.string._2130846693_res_0x7f0223e5));
            return;
        }
        if (i == 3) {
            productResourceInfo.setButtonDescAsGiveaway(getString(R.string._2130845204_res_0x7f021e14));
        } else if (i == 4) {
            productResourceInfo.setButtonDescAsGiveaway(getString(R.string._2130846679_res_0x7f0223d7));
        } else {
            LogUtil.a("TradeOrderDetailActivity", "other status");
        }
    }

    private void aNs_(final ProductResourceInfo productResourceInfo, View view, final String str) {
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.btn_affiliate);
        healthButton.setVisibility(0);
        healthButton.setText(productResourceInfo.getButtonDescAsGiveaway());
        int a2 = a(productResourceInfo);
        LogUtil.a("TradeOrderDetailActivity", "cardStatus:", Integer.valueOf(a2));
        if (a2 == 2) {
            return;
        }
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                TradeOrderDetailActivity.this.e("1", productResourceInfo.getProductName());
                if (!TextUtils.isEmpty(str)) {
                    TradeOrderDetailActivity.this.e(str);
                }
                TradeOrderDetailActivity.this.e(productResourceInfo.getLinkType(), productResourceInfo.getLinkValue());
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    private int a(ProductResourceInfo productResourceInfo) {
        if (koq.b(this.f3447a)) {
            LogUtil.h("TradeOrderDetailActivity", "mCardInfoList isEmpty");
            return 0;
        }
        for (ThirdCardInfo thirdCardInfo : this.f3447a) {
            if (productResourceInfo.getResourceId().equals(thirdCardInfo.getCardPoolId())) {
                if (thirdCardInfo.isEnterprisePurchase()) {
                    return thirdCardInfo.getCardStatus();
                }
                return 0;
            }
        }
        return 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || nsn.o()) {
            LogUtil.h("TradeOrderDetailActivity", "onClick view is null or isFaskClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.lin_contact) {
            a("3");
            h();
        } else if (id == R.id.tv_apply_invoice) {
            l();
        } else {
            LogUtil.a("TradeOrderDetailActivity", "onClick not match");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void l() {
        Bundle bundle = new Bundle();
        bundle.putString("orderCode", this.r);
        bundle.putString("orderId", this.x.getOrderId());
        bundle.putDouble(ParsedFieldTag.PRICE, this.x.getPayPrice() / 1000000.0f);
        bundle.putString(HwPayConstant.KEY_CURRENCY, this.x.getCurrency());
        if (this.ad == 0) {
            bundle.putInt(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 0);
        } else {
            bundle.putInt(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 1);
        }
        AppRouter.b("/Main/InvoiceApplicationActivity").zF_(bundle).c(BaseApplication.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.ai.setVisibility(8);
        this.t.setVisibility(0);
        this.p.setText(BaseApplication.getContext().getString(R.string._2130841548_res_0x7f020fcc));
    }

    private void j() {
        LinearLayout linearLayout = this.ai;
        if (linearLayout == null) {
            LogUtil.h("TradeOrderDetailActivity", "setCenterCardLayout() mTradeContent is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        int i = 0;
        if (nsn.ag(this.i)) {
            i = (int) (r1.c() + new HealthColumnSystem(this.i, 0).g() + r1.a());
        }
        if (layoutParams2 != null) {
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            this.ai.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, final ProductResourceInfo productResourceInfo) {
        LogUtil.a("TradeOrderDetailActivity", "enter getSkipUrl");
        if (!TextUtils.isEmpty(str)) {
            ResourceSkipAddr resourceSkipAddr = new ResourceSkipAddr();
            this.z = resourceSkipAddr;
            resourceSkipAddr.setAddrType(2);
            this.z.setAddr(str);
            n();
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.4
            @Override // java.lang.Runnable
            public void run() {
                TradeOrderDetailActivity.this.d(productResourceInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        MarketRouterApi marketRouterApi;
        ResourceSkipAddr resourceSkipAddr = this.z;
        if (resourceSkipAddr == null) {
            LogUtil.b("TradeOrderDetailActivity", "mResourceSkipAddr is null");
            return;
        }
        if (TextUtils.isEmpty(resourceSkipAddr.getAddr())) {
            LogUtil.a("TradeOrderDetailActivity", "mResourceSkipAddr address is empty");
            return;
        }
        LogUtil.a("TradeOrderDetailActivity", "skipToUse:addr + " + this.z.getAddr() + " type: " + this.z.getAddrType());
        int addrType = this.z.getAddrType();
        if (addrType != 1) {
            if (addrType == 2 && (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) != null) {
                marketRouterApi.router(this.z.getAddr());
                return;
            }
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(this.z.getAddr()));
            intent.addFlags(268435456);
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("TradeOrderDetailActivity", "mResourceSkipAddr: " + this.z.getAddr() + " maybe wrong");
        }
    }

    private void h() {
        GRSManager.a(this.i).e("helpCustomerUrl", new GrsQueryCallback() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.8
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    TradeOrderDetailActivity.this.af = str;
                    TradeOrderDetailActivity.this.k.post(new Runnable() { // from class: com.huawei.health.tradeservice.activity.TradeOrderDetailActivity.8.3
                        @Override // java.lang.Runnable
                        public void run() {
                            TradeOrderDetailActivity.this.k();
                        }
                    });
                } else {
                    LogUtil.h("TradeOrderDetailActivity", "obtainTipsUrlDomain urlDomain is empty");
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("TradeOrderDetailActivity", "obtainTipsUrlDomain onCallBackFail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        OperationWebActivityIntentBuilderApi operationWebActivityIntentBuilderApi = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
        Context context = this.i;
        Intent build = operationWebActivityIntentBuilderApi.builder(context, c(LoginInit.getInstance(context).getAccountInfo(1010))).build();
        Context context2 = this.i;
        build.putExtra("title", context2.getString(njn.e(context2) ? R.string._2130843618_res_0x7f0217e2 : R.string._2130842101_res_0x7f0211f5));
        this.i.startActivity(build);
    }

    private String c(String str) {
        StringBuilder sb;
        String str2;
        StringBuilder sb2;
        String str3;
        LogUtil.a("TradeOrderDetailActivity", "getHelpUrl() countryCode:", str);
        boolean equals = MLAsrConstants.LAN_ZH.equals(BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage());
        int m = CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        LogUtil.a("TradeOrderDetailActivity", "getHelpUrl() siteId:", Integer.valueOf(m));
        if (m == 1) {
            if (equals) {
                sb2 = new StringBuilder();
                sb2.append(this.af);
                str3 = "/hwtips/help/health_help_all/zh-CN/index.html#/help?cid=11069";
            } else {
                sb2 = new StringBuilder();
                sb2.append(this.af);
                str3 = "/hwtips/help/health_help_all/en-US/index.html#/help";
            }
            sb2.append(str3);
            return sb2.toString();
        }
        if (equals) {
            sb = new StringBuilder();
            sb.append(this.af);
            str2 = "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=zh-CN&devicetype=health_help_all";
        } else {
            sb = new StringBuilder();
            sb.append(this.af);
            str2 = "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=en-US&devicetype=health_help_all";
        }
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("target", str);
        ProductResourceInfo productResourceInfo = this.v;
        if (productResourceInfo != null) {
            hashMap.put("name", productResourceInfo.getProductName());
            hashMap.put("id", productResourceInfo.getProductId());
        } else {
            LogUtil.h("TradeOrderDetailActivity", "productResourceInfo = null");
        }
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.TRADE_ORDER_DETAIL.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("event", str);
        hashMap.put("freebieName", str2);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.TRADE_ORDER_DETAIL_GIVEAWAY.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str) {
        Object systemService = this.i.getSystemService("clipboard");
        if (TextUtils.isEmpty(str) || !(systemService instanceof ClipboardManager)) {
            return false;
        }
        try {
            ClipboardManager clipboardManager = (ClipboardManager) systemService;
            ClipData newPlainText = ClipData.newPlainText("Label", str);
            ClipDescription description = newPlainText.getDescription();
            PersistableBundle persistableBundle = new PersistableBundle();
            if (Build.VERSION.SDK_INT >= 33) {
                persistableBundle.putBoolean("android.content.extra.IS_SENSITIVE", true);
            } else {
                persistableBundle.putBoolean("android.content.extra.IS_SENSITIVE", true);
            }
            description.setExtras(persistableBundle);
            clipboardManager.setPrimaryClip(newPlainText);
            return true;
        } catch (SecurityException unused) {
            LogUtil.b("TradeOrderDetailActivity", "copyToClipboard securityException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TradeOrderDetailActivity", "jumpByGiveawayLink linkValue is empty");
            return;
        }
        LogUtil.a("TradeOrderDetailActivity", "jumpByGiveawayLink linkType:", Integer.valueOf(i), " linkValue:", str);
        if (i == 1) {
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(str);
                return;
            }
            return;
        }
        if (i == 2) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            jdw.bGh_(intent, this.i);
            return;
        }
        LogUtil.h("TradeOrderDetailActivity", "jumpByGiveawayLink invalid linkType");
    }

    /* loaded from: classes4.dex */
    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<TradeOrderDetailActivity> f3453a;

        a(TradeOrderDetailActivity tradeOrderDetailActivity) {
            this.f3453a = new WeakReference<>(tradeOrderDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TradeOrderDetailActivity tradeOrderDetailActivity = this.f3453a.get();
            if (tradeOrderDetailActivity == null || tradeOrderDetailActivity.isFinishing() || tradeOrderDetailActivity.isDestroyed()) {
                LogUtil.h("TradeOrderDetailActivity", "orderQueryHistory activity is invalid");
                return;
            }
            if (i == 0 && (obj instanceof OrderDetail)) {
                LogUtil.a("TradeOrderDetailActivity", "orderQueryHistory success");
                tradeOrderDetailActivity.x = (OrderDetail) obj;
                tradeOrderDetailActivity.k.sendEmptyMessage(1101);
            } else {
                LogUtil.h("TradeOrderDetailActivity", "orderQueryHistory fail errorCode = ", Integer.valueOf(i));
                tradeOrderDetailActivity.k.sendEmptyMessage(1102);
            }
        }
    }

    /* loaded from: classes4.dex */
    static class c implements IBaseResponseCallback {
        private final WeakReference<TradeOrderDetailActivity> d;

        c(TradeOrderDetailActivity tradeOrderDetailActivity) {
            this.d = new WeakReference<>(tradeOrderDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TradeOrderDetailActivity tradeOrderDetailActivity = this.d.get();
            if (tradeOrderDetailActivity == null || tradeOrderDetailActivity.isFinishing() || tradeOrderDetailActivity.isDestroyed()) {
                LogUtil.h("TradeOrderDetailActivity", "cardQueryInfo activity is invalid");
                return;
            }
            if (i == 0 && (obj instanceof List)) {
                LogUtil.a("TradeOrderDetailActivity", "cardQueryInfo success");
                tradeOrderDetailActivity.f3447a = (List) obj;
                if (!koq.b(tradeOrderDetailActivity.f3447a)) {
                    Iterator it = tradeOrderDetailActivity.f3447a.iterator();
                    while (it.hasNext()) {
                        LogUtil.a("TradeOrderDetailActivity", "thirdCardInfo:", ((ThirdCardInfo) it.next()).toString());
                    }
                    tradeOrderDetailActivity.k.sendEmptyMessage(ExceptionCode.CHECK_FILE_HASH_FAILED);
                    return;
                }
                LogUtil.h("TradeOrderDetailActivity", "mCardInfoList is Empty");
                return;
            }
            LogUtil.h("TradeOrderDetailActivity", "cardQueryInfo fail errorCode = ", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
