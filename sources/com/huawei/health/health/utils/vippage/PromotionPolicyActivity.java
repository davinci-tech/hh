package com.huawei.health.health.utils.vippage;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.PromotionPolicyActivity;
import com.huawei.health.vip.datatypes.PromotionPolicyCacheInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.trade.datatype.PromotionPolicyInfo;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.dpx;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nrv;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class PromotionPolicyActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2492a;
    private HealthTextView b;
    private Product c;
    private HealthBottomSheet d;
    private ImageView e;
    private HealthTextView f;
    private HealthTextView g;
    private RelativeLayout i;
    private PromotionPolicyInfo j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BaseActivity.setDisplaySideMode(this);
        setBackgroundDrawableResource(R.color._2131296913_res_0x7f090291);
        setContentView(R.layout.activity_vip_promotion_dialog);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("PromotionPolicyActivity", "intent is null");
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("promotionPolicyInfo");
        LogUtil.a("PromotionPolicyActivity", "promotionInfoStr = ", stringExtra);
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.h("PromotionPolicyActivity", "promotionInfoStr is empty");
            finish();
            return;
        }
        try {
            this.j = (PromotionPolicyInfo) new Gson().fromJson(stringExtra, PromotionPolicyInfo.class);
            h();
            ThreadPoolManager.d().execute(new Runnable() { // from class: dqd
                @Override // java.lang.Runnable
                public final void run() {
                    PromotionPolicyActivity.this.a();
                }
            });
            d(0);
            i();
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PromotionPolicyActivity", "parse (JsonSyntaxException");
            finish();
        }
    }

    public /* synthetic */ void a() {
        b(this.j.getProductId());
    }

    private void h() {
        this.i = (RelativeLayout) findViewById(R.id.vip_promotion_drag_view);
        this.e = (ImageView) findViewById(R.id.vip_promotion_bg);
        this.f2492a = (HealthTextView) findViewById(R.id.vip_promotion_renew_btn);
        this.g = (HealthTextView) findViewById(R.id.title);
        this.f = (HealthTextView) findViewById(R.id.sub_title);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.vip_promotion_agreement_text);
        this.b = healthTextView;
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        f();
        this.g.setText(this.j.getDialogTitle());
        this.f.setText(this.j.getDialogSubTitle());
        this.f2492a.setText(this.j.getDialogButtonName());
        this.b.setText(dpx.b(true));
        c();
        d();
    }

    private void c() {
        HealthBottomSheet healthBottomSheet = (HealthBottomSheet) findViewById(R.id.vip_promotion_sliding_layout);
        this.d = healthBottomSheet;
        if (healthBottomSheet == null) {
            return;
        }
        healthBottomSheet.setIndicateSafeInsetsEnabled(true);
        this.d.setForceShowIndicateEnabled(false);
        this.d.setSheetHeight(0);
        this.d.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.health.health.utils.vippage.PromotionPolicyActivity.2
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    PromotionPolicyActivity.this.b();
                }
            }
        });
    }

    private void d() {
        findViewById(R.id.vip_promotion_content).setOnClickListener(new View.OnClickListener() { // from class: dqe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PromotionPolicyActivity.this.Zv_(view);
            }
        });
        findViewById(R.id.vip_promotion_drag_view).setOnClickListener(new View.OnClickListener() { // from class: dqg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f2492a.setOnClickListener(new View.OnClickListener() { // from class: dqf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PromotionPolicyActivity.this.Zw_(view);
            }
        });
    }

    public /* synthetic */ void Zv_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void Zw_(View view) {
        e();
        d(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        PromotionPolicyCacheInfo b = dpx.b();
        int lastPolicyShowCount = b.getLastPolicyShowCount();
        if (TextUtils.equals(this.j.getPromotionPolicyId(), b.getPolicyId())) {
            b.setLastPolicyShowCount(lastPolicyShowCount + 1);
        } else {
            b.setLastPolicyShowCount(1);
            b.setPolicyId(this.j.getPromotionPolicyId());
        }
        b.setLastShowTime(System.currentTimeMillis());
        SharedPreferenceManager.c(Integer.toString(10000), "SHOW_PROMOTION_DIALOG", nrv.a(b));
    }

    private void e() {
        ProductInfo e = dpx.e(this.c);
        e.setProductId(this.j.getProductId());
        e.setPromotionPolicyId(this.j.getPromotionPolicyId());
        e.setPromotionId(this.j.getPromotionId());
        Product product = this.c;
        if (product == null || product.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG) {
            dpx.d(this, this.c, e);
        } else {
            dpx.Zf_(this, e);
        }
    }

    private void d(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.health.utils.vippage.PromotionPolicyActivity.5
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                hashMap.put("type", 1);
                hashMap.put("event", Integer.valueOf(i));
                ixx.d().d(PromotionPolicyActivity.this, AnalyticsValue.VIP_RENEW_REMIND_DIALOG_EVENT.value(), hashMap, 0);
            }
        });
    }

    private void j() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.health.utils.vippage.PromotionPolicyActivity.3
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("name", PromotionPolicyActivity.this.j.getDialogButtonName());
                hashMap.put("payResourceType", "弹窗到期提醒");
                hashMap.put("ActivationMode", 2);
                hashMap.put("from", 6);
                ixx.d().d(BaseApplication.e(), AnalyticsValue.VIP_RENEWAL_PAY_SUCCESS_EVENT.value(), hashMap, 0);
            }
        });
    }

    private void f() {
        String dialogPicUrl;
        if (nsn.ag(this)) {
            dialogPicUrl = this.j.getTahitiDialogPicUrl();
        } else {
            dialogPicUrl = this.j.getDialogPicUrl();
        }
        float dimensionPixelOffset = BaseApplication.e().getResources().getDimensionPixelOffset(R.dimen._2131362601_res_0x7f0a0329);
        nrf.cIK_(dialogPicUrl, this.e, dimensionPixelOffset, dimensionPixelOffset, 0.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("PromotionPolicyActivity", "hide");
        finish();
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
    }

    private void b(String str) {
        ((PayApi) Services.c("TradeService", PayApi.class)).getProductDetails(str, new d(this));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        f();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("PromotionPolicyActivity", "onActivityResult requestCode: ", Integer.valueOf(i));
        if (intent == null) {
            LogUtil.a("PromotionPolicyActivity", "onActivityResult data: ", intent);
            return;
        }
        int intExtra = intent.getIntExtra("shoppingResult", -1);
        LogUtil.a("PromotionPolicyActivity", "onActivityResult shoppingResult: ", Integer.valueOf(intExtra));
        if (intExtra != 0) {
            return;
        }
        ObserverManagerUtil.c("USER_MEMBER_INFO_UPDATE", true);
        j();
        finish();
    }

    public static class d implements IBaseResponseCallback {
        private WeakReference<PromotionPolicyActivity> b;

        d(PromotionPolicyActivity promotionPolicyActivity) {
            this.b = new WeakReference<>(promotionPolicyActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 0) {
                LogUtil.h("PromotionPolicyActivity", "getProductDetails failed, errorCode:", Integer.valueOf(i));
                return;
            }
            if (!(obj instanceof Product)) {
                LogUtil.h("PromotionPolicyActivity", "getProductDetails failed, objData:", obj);
                return;
            }
            final PromotionPolicyActivity promotionPolicyActivity = this.b.get();
            if (promotionPolicyActivity != null && !promotionPolicyActivity.isFinishing() && !promotionPolicyActivity.isDestroyed()) {
                promotionPolicyActivity.c = (Product) obj;
                HandlerExecutor.e(new Runnable() { // from class: dqc
                    @Override // java.lang.Runnable
                    public final void run() {
                        PromotionPolicyActivity.this.b.setText(dpx.b(r2.c.getPurchasePolicy() == Product.SUBSCRIPTION_PURCHASE_FLAG));
                    }
                });
                LogUtil.a("PromotionPolicyActivity", "getProductDetails success");
                return;
            }
            LogUtil.h("PromotionPolicyActivity", "getProductDetails onResponse activity is destroy, return");
        }
    }
}
