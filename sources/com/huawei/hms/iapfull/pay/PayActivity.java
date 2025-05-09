package com.huawei.hms.iapfull.pay;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hms.iapfull.a1;
import com.huawei.hms.iapfull.bean.PayRequest;
import com.huawei.hms.iapfull.bean.PayResult;
import com.huawei.hms.iapfull.e1;
import com.huawei.hms.iapfull.m0;
import com.huawei.hms.iapfull.n0;
import com.huawei.hms.iapfull.network.model.MyPayType;
import com.huawei.hms.iapfull.network.model.ReportPayResultResponse;
import com.huawei.hms.iapfull.p0;
import com.huawei.hms.iapfull.pay.g;
import com.huawei.hms.iapfull.s0;
import com.huawei.hms.iapfull.widget.actionbar.CustomActionBar;
import com.huawei.hms.iapfull.x0;
import com.huawei.hms.iapfull.y0;
import com.huawei.secure.android.common.detect.RootDetect;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;
import java.util.List;

/* loaded from: classes4.dex */
public class PayActivity extends n0 implements View.OnClickListener, p0 {
    private PayRequest b;
    private m0 c;
    private CustomActionBar d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private ListView i;
    private j j;
    private List<MyPayType> l;
    private boolean n;
    private int k = 0;
    private PayResult m = new PayResult();

    static void a(PayActivity payActivity, x0 x0Var) {
        payActivity.getClass();
        k kVar = new k();
        Bundle bundle = new Bundle();
        bundle.putString("product_name", x0Var.d());
        bundle.putString("product_price", x0Var.e());
        bundle.putString("product_currency", payActivity.b.getCurrency());
        kVar.setArguments(bundle);
        payActivity.a(kVar, R.id.door_contents_fl, true);
        com.huawei.hms.iapfull.b.a(payActivity, "iap_payment_cashier_result", payActivity.j.a(payActivity.b));
    }

    static void b(PayActivity payActivity, x0 x0Var) {
        payActivity.getClass();
        n nVar = new n();
        Bundle bundle = new Bundle();
        bundle.putString("product_name", x0Var.d());
        bundle.putString("pay_order", x0Var.b());
        bundle.putString("pay_type", x0Var.c());
        nVar.setArguments(bundle);
        payActivity.a(nVar, R.id.door_contents_fl, true);
    }

    public void c(x0 x0Var) {
        if (isFinishing() || isDestroyed()) {
            y0.a("PayActivity", "onWithholdFinish activity dead");
            return;
        }
        b();
        a(x0Var);
        this.j.a(new c(x0Var));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 0) {
            finish();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string._2130851260_res_0x7f0235bc);
        builder.setPositiveButton(R.string._2130851264_res_0x7f0235c0, new d(this));
        builder.setNegativeButton(R.string._2130851263_res_0x7f0235bf, new e(this));
        builder.create().show();
    }

    @Override // com.huawei.hms.iapfull.n0, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pay);
        if (getIntent() == null) {
            y0.a("PayActivity", "receivePayRequestFromIntent getIntent is null");
        } else {
            SafeBundle safeBundle = new SafeBundle(new SafeIntent(getIntent()).getExtras());
            PayRequest payRequest = new PayRequest();
            this.b = payRequest;
            payRequest.fromBundle(safeBundle);
        }
        CustomActionBar customActionBar = (CustomActionBar) findViewById(R.id.action_bar);
        this.d = customActionBar;
        customActionBar.setOnBackClickListener(new a());
        y0.b("PayActivity", "Agree License,obtainSystemInfoFromServer and refresh GPSLocation");
        if (!RootDetect.isRoot()) {
            c();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string._2130851278_res_0x7f0235ce);
        builder.setPositiveButton(R.string._2130851262_res_0x7f0235be, new com.huawei.hms.iapfull.pay.a(this));
        builder.setNegativeButton(R.string._2130851263_res_0x7f0235bf, new com.huawei.hms.iapfull.pay.b(this));
        builder.create().show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        String str;
        y0.b("PayActivity", "onNewIntent");
        super.onNewIntent(intent);
        if (intent == null) {
            str = "onCreateFromScheme intent is null";
        } else {
            SafeIntent safeIntent = new SafeIntent(intent);
            if ("hwid".equals(safeIntent.getScheme())) {
                Uri data = safeIntent.getData();
                if (data == null) {
                    str = "scheme getData isEmpty.";
                } else {
                    String path = data.getPath();
                    if (TextUtils.isEmpty(path)) {
                        str = "scheme getPath isEmpty.";
                    } else {
                        if ("/result".equals(path)) {
                            String encodedQuery = data.getEncodedQuery();
                            y0.b("PayActivity", "queryParams is in alipay sign result");
                            if (TextUtils.isEmpty(encodedQuery)) {
                                return;
                            }
                            this.j.a(a1.a(encodedQuery));
                            return;
                        }
                        str = "scheme getPath illegal.";
                    }
                }
            } else {
                str = "scheme illegal.";
            }
        }
        y0.b("PayActivity", str);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        y0.b("PayActivity", "start onResume");
        if (this.j == null || !this.n || getSupportFragmentManager().getFragments().size() > 0) {
            return;
        }
        y0.a("PayActivity", "hasResultShow check show pay page first time.");
        List<MyPayType> list = this.l;
        if (list == null || this.k >= list.size() || this.l.get(this.k).getPayType() != 17) {
            return;
        }
        this.n = false;
        this.j.g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        findViewById(R.id.door_contents_fl).setVisibility(8);
        findViewById(R.id.pay_layout).setVisibility(0);
        TextView textView = (TextView) findViewById(R.id.huaweipay_recharge);
        this.e = textView;
        textView.setOnClickListener(this);
        this.e.setClickable(false);
        this.f = (TextView) findViewById(R.id.b_product_fullname);
        this.g = (TextView) findViewById(R.id.b_meramount);
        this.h = (TextView) findViewById(R.id.b_pay_amount);
        this.i = (ListView) findViewById(R.id.pay_type_list);
        PayRequest payRequest = this.b;
        if (payRequest != null) {
            this.f.setText(payRequest.getProductName());
            if ("toSign".equals(this.b.getTradeType())) {
                findViewById(R.id.rl_b_meramount).setVisibility(8);
                findViewById(R.id.ll_b_pay_amount).setVisibility(8);
            } else {
                String a2 = a1.a(this, this.b.getAmount(), "string_cny_normal", this.b.getCurrency());
                this.g.setText(a2);
                this.h.setText(a2);
            }
            this.e.setText(a1.a(this, this.b.getAmount(), "string_cny_confirm", this.b.getCurrency()));
        }
        this.i.setOnItemClickListener(new com.huawei.hms.iapfull.pay.c(this));
        getWindow().setNavigationBarColor(getResources().getColor(R.color._2131298851_res_0x7f090a23));
        this.j = new j(this.b, this);
    }

    public void a(List<MyPayType> list) {
        if (list == null || list.isEmpty() || !g.a.f4750a.b()) {
            e1.a().a(this, R.string._2130851268_res_0x7f0235c4);
            this.m.setReturnCode(30011);
            this.m.setErrMsg(getString(R.string._2130851268_res_0x7f0235c4));
            Intent intent = new Intent();
            intent.putExtras(this.m.toBundle());
            setResult(-1, intent);
            finish();
            return;
        }
        this.l = list;
        m0 m0Var = this.c;
        if (m0Var != null) {
            m0Var.a(list);
            return;
        }
        m0 m0Var2 = new m0(this, this.l, 0);
        this.c = m0Var2;
        this.i.setAdapter((ListAdapter) m0Var2);
    }

    public void b(x0 x0Var) {
        if (isFinishing() || isDestroyed()) {
            y0.a("PayActivity", "onPayFinish activity dead");
            return;
        }
        b();
        a(x0Var);
        this.j.a(new b(x0Var));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        y0.b("PayActivity", "start onStop");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        y0.b("PayActivity", "start onStart");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        y0.b("PayActivity", "start onPause");
        this.n = true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        List<MyPayType> list;
        if (!a1.a(this)) {
            y0.a("PayActivity", "click: withHold net error");
            a(getString(R.string._2130851291_res_0x7f0235db), "30005");
            a(30005, getString(R.string._2130851291_res_0x7f0235db));
        } else if (view.getId() == R.id.huaweipay_recharge) {
            j jVar = this.j;
            if (jVar == null || (list = this.l) == null) {
                y0.a("PayActivity", "onClick: parameters are invalid");
            } else {
                jVar.c(list.get(this.k).getPayType());
            }
        }
    }

    public void a(boolean z) {
        this.e.setClickable(z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            r1 = 2130851285(0x7f0235d5, float:1.7307915E38)
            if (r0 == 0) goto L1a
            java.lang.String r9 = "PayActivity"
            java.lang.String r10 = "showErrorMsgDialog is null"
            com.huawei.hms.iapfull.y0.a(r9, r10)
            r9 = 30006(0x7536, float:4.2047E-41)
            java.lang.String r10 = r8.getString(r1)
            r8.a(r9, r10)
            return
        L1a:
            r10.hashCode()
            r10.hashCode()
            int r0 = r10.hashCode()
            r2 = 54
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = -1
            if (r0 == r2) goto L62
            r2 = 1677668281(0x63ff2fb9, float:9.414717E21)
            if (r0 == r2) goto L57
            switch(r0) {
                case 1677668247: goto L4c;
                case 1677668248: goto L41;
                case 1677668249: goto L36;
                default: goto L35;
            }
        L35:
            goto L6a
        L36:
            java.lang.String r0 = "900002"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L3f
            goto L6a
        L3f:
            r0 = r4
            goto L6d
        L41:
            java.lang.String r0 = "900001"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L4a
            goto L6a
        L4a:
            r0 = r5
            goto L6d
        L4c:
            java.lang.String r0 = "900000"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L55
            goto L6a
        L55:
            r0 = r6
            goto L6d
        L57:
            java.lang.String r0 = "900013"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L60
            goto L6a
        L60:
            r0 = r3
            goto L6d
        L62:
            java.lang.String r0 = "6"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L6c
        L6a:
            r0 = r7
            goto L6d
        L6c:
            r0 = 0
        L6d:
            if (r0 == 0) goto Lbd
            if (r0 == r6) goto L7c
            if (r0 == r5) goto L7c
            if (r0 == r4) goto L7c
            if (r0 == r3) goto Lbd
            java.lang.String r9 = r8.getString(r1)
            goto Lc8
        L7c:
            com.huawei.hms.iapfull.e1 r0 = com.huawei.hms.iapfull.e1.a()
            java.lang.String r9 = com.huawei.hms.iapfull.o0.a(r8, r10, r9)
            r0.a(r8, r9)
            java.util.List<com.huawei.hms.iapfull.network.model.MyPayType> r9 = r8.l
            if (r9 == 0) goto La0
            int r9 = r9.size()
            int r10 = r8.k
            if (r9 <= r10) goto La0
            java.util.List<com.huawei.hms.iapfull.network.model.MyPayType> r9 = r8.l
            java.lang.Object r9 = r9.get(r10)
            com.huawei.hms.iapfull.network.model.MyPayType r9 = (com.huawei.hms.iapfull.network.model.MyPayType) r9
            int r9 = r9.getPayType()
            goto La2
        La0:
            r9 = 17
        La2:
            com.huawei.hms.iapfull.bean.PayRequest r10 = r8.b
            java.lang.String r10 = r10.getTradeType()
            java.lang.String r0 = "toSign"
            boolean r10 = r0.equals(r10)
            if (r10 == 0) goto Lb7
            com.huawei.hms.iapfull.pay.j r10 = r8.j
            r0 = 0
            r10.a(r0, r9)
            goto Lcb
        Lb7:
            com.huawei.hms.iapfull.pay.j r10 = r8.j
            r10.b(r9)
            goto Lcb
        Lbd:
            com.huawei.hms.iapfull.e1 r0 = com.huawei.hms.iapfull.e1.a()
            java.lang.String r10 = com.huawei.hms.iapfull.o0.a(r8, r10, r9)
            r0.a(r8, r10)
        Lc8:
            r8.a(r7, r9)
        Lcb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.iapfull.pay.PayActivity.a(java.lang.String, java.lang.String):void");
    }

    public void a(PayResult payResult) {
        payResult.setTime(null);
        payResult.createPayResult(this.b);
        Intent intent = new Intent();
        intent.putExtras(payResult.toBundle());
        setResult(-1, intent);
    }

    public void a(int i) {
        this.e.setText(i);
    }

    private void b() {
        this.d.setTitle(getString(R.string._2130851277_res_0x7f0235cd));
        findViewById(R.id.door_contents_fl).setVisibility(0);
        findViewById(R.id.pay_layout).setVisibility(8);
    }

    private void a(x0 x0Var) {
        this.m.createPayResult(this.b);
        this.m.setReturnCode(0);
        this.m.setErrMsg(x0Var.a());
        this.m.setTime(x0Var.f());
        this.m.setOrderID(x0Var.b());
        if (TextUtils.isEmpty(x0Var.g())) {
            return;
        }
        this.m.setWithHoldID(x0Var.g());
    }

    class b implements s0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ x0 f4742a;

        @Override // com.huawei.hms.iapfull.s0
        public void a(String str, String str2) {
            PayActivity.this.a();
            y0.a("PayActivity", "errorCode " + str + ", desc = " + str2);
            Intent intent = new Intent();
            intent.putExtras(PayActivity.this.m.toBundle());
            PayActivity.this.setResult(-1, intent);
            PayActivity.a(PayActivity.this, this.f4742a);
        }

        @Override // com.huawei.hms.iapfull.s0
        public void a(ReportPayResultResponse reportPayResultResponse) {
            PayActivity.this.a();
            PayActivity.this.m.setSign(reportPayResultResponse.getReturnDevSign());
            PayActivity.this.m.setNewSign(reportPayResultResponse.getNewSign());
            PayActivity.this.m.setSignatureAlgorithm(reportPayResultResponse.getSignatureAlgorithm());
            Intent intent = new Intent();
            intent.putExtras(PayActivity.this.m.toBundle());
            PayActivity.this.setResult(-1, intent);
            PayActivity.a(PayActivity.this, this.f4742a);
        }

        b(x0 x0Var) {
            this.f4742a = x0Var;
        }
    }

    class c implements s0 {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ x0 f4743a;

        @Override // com.huawei.hms.iapfull.s0
        public void a(String str, String str2) {
            PayActivity.this.a();
            y0.a("PayActivity", "errorCode " + str + ", desc = " + str2);
            Intent intent = new Intent();
            intent.putExtras(PayActivity.this.m.toBundle());
            PayActivity.this.setResult(-1, intent);
            PayActivity.b(PayActivity.this, this.f4743a);
        }

        @Override // com.huawei.hms.iapfull.s0
        public void a(ReportPayResultResponse reportPayResultResponse) {
            PayActivity.this.a();
            PayActivity.this.m.setSign(reportPayResultResponse.getReturnDevSign());
            PayActivity.this.m.setNewSign(reportPayResultResponse.getNewSign());
            PayActivity.this.m.setSignatureAlgorithm(reportPayResultResponse.getSignatureAlgorithm());
            Intent intent = new Intent();
            intent.putExtras(PayActivity.this.m.toBundle());
            PayActivity.this.setResult(-1, intent);
            PayActivity.b(PayActivity.this, this.f4743a);
        }

        c(x0 x0Var) {
            this.f4743a = x0Var;
        }
    }

    class a implements CustomActionBar.OnBackClickListener {
        @Override // com.huawei.hms.iapfull.widget.actionbar.CustomActionBar.OnBackClickListener
        public void onBackClick() {
            PayActivity.this.onBackPressed();
        }

        a() {
        }
    }

    private void a(int i, String str) {
        this.m.setErrMsg(str);
        this.m.setReturnCode(i);
        Intent intent = new Intent();
        intent.putExtras(this.m.toBundle());
        setResult(-1, intent);
        finish();
    }
}
