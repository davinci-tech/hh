package com.huawei.ui.main.stories.member;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.dpx;
import defpackage.dqj;
import defpackage.eab;
import defpackage.gpn;
import defpackage.koq;
import defpackage.nsa;
import defpackage.nsy;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MemberTypeSelectActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10394a;
    private HealthBottomSheet b;
    private Intent f;
    private KnitFragment h;
    private Product l;
    private HealthTextView o;
    private String p;
    private UserMemberInfo s;
    private final String g = "MEMBER_TYPE_TAG_";
    private Handler j = new d(this);
    private String r = "1000";
    private String k = "0";
    private String m = "";
    private String n = "";
    private String c = "";
    private int e = -1;
    private String d = "";
    private final View.OnClickListener i = new View.OnClickListener() { // from class: com.huawei.ui.main.stories.member.MemberTypeSelectActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LogUtil.a("MemberTypeSelectActivity", "open btn clicked");
            String str = !TextUtils.isEmpty(MemberTypeSelectActivity.this.p) ? MemberTypeSelectActivity.this.p : "1".equals(MemberTypeSelectActivity.this.k) ? "VipRate" : "VipPage";
            if (MemberTypeSelectActivity.this.s == null || MemberTypeSelectActivity.this.s.getMemberFlag() == -1) {
                if (TextUtils.isEmpty(MemberTypeSelectActivity.this.d) || TextUtils.isEmpty(MemberTypeSelectActivity.this.n)) {
                    dqj.c(MemberTypeSelectActivity.this.m, str, MemberTypeSelectActivity.this.f != null ? MemberTypeSelectActivity.this.f.getIntExtra("slidingScreens", 1) : 1);
                } else {
                    dqj.b(MemberTypeSelectActivity.this.d, MemberTypeSelectActivity.this.n);
                }
            } else {
                dqj.e(MemberTypeSelectActivity.this.m, str);
            }
            MemberTypeSelectActivity memberTypeSelectActivity = MemberTypeSelectActivity.this;
            ProductInfo b = memberTypeSelectActivity.b(dpx.e(memberTypeSelectActivity.l));
            if (b.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
                dpx.d(MemberTypeSelectActivity.this.h.getActivity(), MemberTypeSelectActivity.this.l, b);
            } else {
                dpx.Zf_(MemberTypeSelectActivity.this.h.getActivity(), b);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BaseActivity.setDisplaySideMode(this);
        setBackgroundDrawableResource(R.color._2131299066_res_0x7f090afa);
        setContentView(R.layout.activity_member_type_select);
        this.s = gpn.a();
        this.f = getIntent();
        a();
        f();
        dqj.r();
    }

    private void a() {
        Intent intent = this.f;
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("memberTypeSelectUri");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        String d2 = nsa.d(stringExtra);
        LogUtil.a("MemberTypeSelectActivity", "handleUrl url：", d2);
        dPp_(Uri.parse(d2));
    }

    private void dPp_(Uri uri) {
        if (uri == null) {
            return;
        }
        this.n = uri.getQueryParameter("payResourceType");
        this.m = uri.getQueryParameter("trigResName");
        String queryParameter = uri.getQueryParameter("name");
        this.d = queryParameter;
        dqj.b(TextUtils.isEmpty(queryParameter) ? "" : this.d);
        dqj.k(TextUtils.isEmpty(this.n) ? "" : this.n);
        Intent intent = this.f;
        String stringExtra = intent != null ? intent.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM) : "";
        String queryParameter2 = uri.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = queryParameter2;
        }
        dqj.g(stringExtra != null ? stringExtra : "");
        this.k = uri.getQueryParameter("trigResMemberPrice");
        this.p = uri.getQueryParameter("vipOpenFrom");
        this.r = uri.getQueryParameter("trigResType");
        this.c = uri.getQueryParameter("productId");
        String queryParameter3 = uri.getQueryParameter("from");
        if (TextUtils.isEmpty(queryParameter3)) {
            return;
        }
        try {
            this.e = Integer.parseInt(queryParameter3);
        } catch (NumberFormatException e) {
            LogUtil.b("MemberTypeSelectActivity", e.getMessage());
        }
    }

    private void f() {
        c();
        h();
        i();
        g();
    }

    private void c() {
        HealthBottomSheet healthBottomSheet = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.b = healthBottomSheet;
        if (healthBottomSheet == null) {
            return;
        }
        healthBottomSheet.setIndicateSafeInsetsEnabled(true);
        this.b.setForceShowIndicateEnabled(false);
        this.b.setSheetHeight(0);
        this.b.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.ui.main.stories.member.MemberTypeSelectActivity.4
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    MemberTypeSelectActivity.this.d();
                }
            }
        });
    }

    private void h() {
        findViewById(R.id.activity_member_type_select_content).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.member.MemberTypeSelectActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MemberTypeSelectActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void i() {
        KnitFragment abS_ = eab.abS_(this, 33, dPo_());
        this.h = abS_;
        if (abS_ == null) {
            LogUtil.b("MemberTypeSelectActivity", "knitFragment == null");
        } else {
            abS_.setOutHandler(this.j);
            getSupportFragmentManager().beginTransaction().replace(R.id.member_type_drag_content, this.h).commit();
        }
    }

    private void g() {
        this.o = (HealthTextView) findViewById(R.id.member_type_open_button);
        k();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.member_type_agreement_text);
        this.f10394a = healthTextView;
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void k() {
        String string;
        HealthTextView healthTextView = this.o;
        if (healthTextView == null) {
            return;
        }
        healthTextView.setOnClickListener(this.i);
        UserMemberInfo a2 = gpn.a();
        HealthTextView healthTextView2 = this.o;
        if (a2 == null || a2.getMemberFlag() == -1) {
            string = getResources().getString(R$string.IDS_vip_open_immediately);
        } else {
            string = getResources().getString(R$string.IDS_user_vip_renew);
        }
        nsy.cMr_(healthTextView2, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Product product = this.l;
        if (product == null) {
            return;
        }
        nsy.cMr_(this.f10394a, dpx.b(a(product)));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        d();
    }

    private boolean a(Product product) {
        return product != null && product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProductInfo b(ProductInfo productInfo) {
        ProductInfo productInfo2 = new ProductInfo();
        if (productInfo == null) {
            return productInfo2;
        }
        productInfo.setTrigResMemberPrice(this.k);
        String str = !TextUtils.isEmpty(this.d) ? this.d : "会员开通";
        if (!TextUtils.isEmpty(this.m)) {
            str = this.m;
        }
        productInfo.setTrigResName(str);
        productInfo.setTrigResType(this.r);
        return productInfo;
    }

    private Bundle dPo_() {
        Intent intent = this.f;
        if (intent == null) {
            return null;
        }
        int intExtra = intent.getIntExtra("MEMBER_TYPE_SELECT_INDEX", -1);
        LogUtil.a("MemberTypeSelectActivity", "getKnitExtra: ", Integer.valueOf(intExtra));
        Bundle bundle = new Bundle();
        bundle.putBoolean(KnitFragment.KEY_EXTRA_WRAP_CONTENT, true);
        bundle.putBoolean("IS_SHOW_OPEN", true);
        if (!TextUtils.isEmpty(this.c)) {
            bundle.putString("DEFAULT_PRODUCT_ID", this.c);
        }
        if (intExtra >= 0) {
            bundle.putInt("MEMBER_TYPE_SELECT_INDEX", intExtra);
        }
        return bundle;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("MemberTypeSelectActivity", "onActivityResult resultCode: ", Integer.valueOf(i2));
        if (i != 10000) {
            return;
        }
        int intExtra = intent != null ? intent.getIntExtra("shoppingResult", -1) : -1;
        LogUtil.a("MemberTypeSelectActivity", "shoppingResult: ", Integer.valueOf(intExtra));
        UserMemberInfo a2 = gpn.a();
        boolean z = a2 == null || a2.getMemberFlag() == -1;
        if (intExtra == 0) {
            Product product = this.l;
            if (product != null) {
                int i3 = dpx.a(product) ? 2 : 3;
                int i4 = this.e;
                if (i4 == -1) {
                    i4 = 8;
                }
                dqj.d(this.l.getProductName(), i4, i3, !z);
            }
            this.f.putExtra("shoppingResult", intExtra);
            d();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.b.getSheetState() == HwBottomSheet.SheetState.EXPANDED) {
            d();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b();
    }

    private void b() {
        dqj.g("");
        dqj.b("");
        dqj.k("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("MemberTypeSelectActivity", "hide");
        setResult(-1, this.f);
        finish();
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
    }

    /* loaded from: classes7.dex */
    static class d extends BaseHandler<MemberTypeSelectActivity> {
        public d(MemberTypeSelectActivity memberTypeSelectActivity) {
            super(memberTypeSelectActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dPq_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MemberTypeSelectActivity memberTypeSelectActivity, Message message) {
            if (message.what != 100) {
                return;
            }
            Object obj = message.obj;
            if (obj instanceof Bundle) {
                Bundle bundle = (Bundle) obj;
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("PRODUCTS");
                int i = bundle.getInt("MEMBER_TYPE_SELECT_INDEX", -1);
                LogUtil.a("MemberTypeSelectActivity", "update index: ", Integer.valueOf(i));
                if (koq.d(parcelableArrayList, i)) {
                    ObserverManagerUtil.c("MEMBER_TYPE_TAG_", Integer.valueOf(i));
                    memberTypeSelectActivity.l = (Product) parcelableArrayList.get(i);
                    memberTypeSelectActivity.j();
                }
                if (i < 0 || memberTypeSelectActivity.f == null) {
                    return;
                }
                memberTypeSelectActivity.f.putExtra("MEMBER_TYPE_SELECT_INDEX", i);
                memberTypeSelectActivity.setResult(-1, memberTypeSelectActivity.f);
            }
        }
    }
}
