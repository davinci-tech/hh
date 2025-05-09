package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity;
import com.huawei.ui.main.stories.health.fragment.InvoiceFailedFragment;
import com.huawei.ui.main.stories.health.fragment.InvoiceOngoingFragment;
import com.huawei.ui.main.stories.health.fragment.InvoiceSuccessfulFragment;
import defpackage.ixx;
import defpackage.mfm;
import defpackage.mtj;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.qrg;
import defpackage.qrk;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.util.HashMap;

/* loaded from: classes.dex */
public class InvoiceApplicationActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthRadioButton f10062a;
    private HealthEditText b;
    private HealthTextView c;
    private View d;
    private HealthEditText e;
    private CustomTitleBar f;
    private Context g;
    private Fragment h;
    private InvoiceFailedFragment j;
    private InvoiceOngoingFragment k;
    private Handler l;
    private int o;
    private String p;
    private String q;
    private HealthTextView r;
    private View s;
    private HealthEditText t;
    private String u;
    private HealthRadioButton w;
    private InvoiceSuccessfulFragment x;
    private HealthButton y;
    private boolean m = true;
    private final TextWatcher v = new TextWatcher() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity.4
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.a("InvoiceApplicationActivity", "mPersonNameTextWatcher onTextChanged");
            String trim = InvoiceApplicationActivity.this.t.getText().toString().trim();
            LogUtil.a("InvoiceApplicationActivity", "mPersonNameText = ", trim);
            if (TextUtils.isEmpty(trim)) {
                InvoiceApplicationActivity invoiceApplicationActivity = InvoiceApplicationActivity.this;
                invoiceApplicationActivity.d(invoiceApplicationActivity.y);
            } else {
                InvoiceApplicationActivity invoiceApplicationActivity2 = InvoiceApplicationActivity.this;
                invoiceApplicationActivity2.a(invoiceApplicationActivity2.y);
            }
        }
    };
    private final TextWatcher i = new TextWatcher() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity.2
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.a("InvoiceApplicationActivity", "mBusinessTextWatcher onTextChanged");
            String trim = InvoiceApplicationActivity.this.b.getText().toString().trim();
            String trim2 = InvoiceApplicationActivity.this.e.getText().toString().trim();
            LogUtil.a("InvoiceApplicationActivity", "mBusinessNameText = ", trim);
            LogUtil.a("InvoiceApplicationActivity", "mBusinessTaxText = ", trim2);
            if (TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2)) {
                InvoiceApplicationActivity invoiceApplicationActivity = InvoiceApplicationActivity.this;
                invoiceApplicationActivity.d(invoiceApplicationActivity.y);
            } else {
                InvoiceApplicationActivity invoiceApplicationActivity2 = InvoiceApplicationActivity.this;
                invoiceApplicationActivity2.a(invoiceApplicationActivity2.y);
            }
        }
    };
    private final InputFilter[] n = {new InputFilter() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity.5
        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (charSequence.toString().matches("[，@&<>\"':\\[\\]$()%+\\\\/#`*;=^|,\\-{}\\s\\n]+")) {
                b();
                return "";
            }
            c();
            return null;
        }

        private void c() {
            if (InvoiceApplicationActivity.this.m) {
                InvoiceApplicationActivity.this.r.setVisibility(8);
            } else {
                InvoiceApplicationActivity.this.c.setVisibility(8);
            }
        }

        private void b() {
            if (InvoiceApplicationActivity.this.m) {
                InvoiceApplicationActivity.this.r.setVisibility(0);
            } else {
                InvoiceApplicationActivity.this.c.setVisibility(0);
            }
        }
    }};

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("InvoiceApplicationActivity", "onCreate");
        super.onCreate(bundle);
        this.g = this;
        this.l = new InVoiceActivityHandler(this);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_invoice_application);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("InvoiceApplicationActivity", "no intent passed in, failed to create invoice apply page!");
            return;
        }
        String stringExtra = intent.getStringExtra("orderCode");
        this.q = stringExtra;
        LogUtil.a("InvoiceApplicationActivity", "raw mOrderCode (not url)= ", stringExtra);
        this.p = intent.getStringExtra("orderId");
        this.u = new DecimalFormat("######0.00").format(intent.getDoubleExtra(ParsedFieldTag.PRICE, 0.0d));
        LogUtil.a("InvoiceApplicationActivity", "mOrderId = ", this.p);
        LogUtil.a("InvoiceApplicationActivity", "mPrice= ", this.u);
        d();
        b();
        dAJ_(intent);
    }

    private void dAJ_(Intent intent) {
        LogUtil.a("InvoiceApplicationActivity", "handleQueryResultPage");
        if (intent.getIntExtra(CommonUtil.PAGE_TYPE, 0) == 1) {
            qrk.dHG_(this.q, this.l);
        }
    }

    private void d() {
        LogUtil.a("InvoiceApplicationActivity", "initView");
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.invoice_application_title_bar);
        this.f = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        HealthButton healthButton = (HealthButton) findViewById(R.id.btn_summit);
        this.y = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: qdu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceApplicationActivity.this.dAR_(view);
            }
        });
        this.w = (HealthRadioButton) findViewById(R.id.person_radio_button);
        this.f10062a = (HealthRadioButton) findViewById(R.id.business_radio_button);
        c();
        e();
    }

    public /* synthetic */ void dAR_(View view) {
        j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        LogUtil.a("InvoiceApplicationActivity", "initBusinessArea");
        View findViewById = findViewById(R.id.invoice_business_area);
        this.d = findViewById;
        this.b = (HealthEditText) findViewById.findViewById(R.id.business_name_edit_text);
        this.e = (HealthEditText) this.d.findViewById(R.id.business_tax_edit_text);
        this.b.addTextChangedListener(this.i);
        this.e.addTextChangedListener(this.i);
        this.b.setText(SharedPreferenceManager.b(this.g, String.valueOf(10050), "INVOICE_BUSINESS_NAME"));
        this.e.setText(SharedPreferenceManager.b(this.g, String.valueOf(10050), "INVOICE_BUSINESS_TAX"));
        ((HealthTextView) this.d.findViewById(R.id.business_order_id_text)).setText(this.p);
        ((HealthTextView) this.d.findViewById(R.id.business_price_text)).setText("¥" + this.u);
        findViewById(R.id.business_button_area).setOnClickListener(new View.OnClickListener() { // from class: qdl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceApplicationActivity.this.dAO_(view);
            }
        });
        this.c = (HealthTextView) this.d.findViewById(R.id.business_error_prompt);
        this.b.setFilters(this.n);
    }

    public /* synthetic */ void dAO_(View view) {
        if (this.m) {
            c(false);
            HealthEditText healthEditText = this.b;
            healthEditText.setText(healthEditText.getText().toString().trim());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        LogUtil.a("InvoiceApplicationActivity", "initPersonArea");
        View findViewById = findViewById(R.id.invoice_person_area);
        this.s = findViewById;
        HealthEditText healthEditText = (HealthEditText) findViewById.findViewById(R.id.person_name_edit_text);
        this.t = healthEditText;
        healthEditText.addTextChangedListener(this.v);
        this.t.setText(SharedPreferenceManager.b(this.g, String.valueOf(10050), "INVOICE_PERSON_NAME"));
        ((HealthTextView) this.s.findViewById(R.id.person_order_id_text)).setText(this.p);
        ((HealthTextView) this.s.findViewById(R.id.person_price_text)).setText("¥" + this.u);
        findViewById(R.id.person_button_area).setOnClickListener(new View.OnClickListener() { // from class: qdt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceApplicationActivity.this.dAQ_(view);
            }
        });
        this.r = (HealthTextView) this.s.findViewById(R.id.person_error_prompt);
        this.t.setFilters(this.n);
    }

    public /* synthetic */ void dAQ_(View view) {
        if (!this.m) {
            c(true);
            HealthEditText healthEditText = this.t;
            healthEditText.setText(healthEditText.getText().toString().trim());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(boolean z) {
        LogUtil.a("InvoiceApplicationActivity", "toggleInvoiceTypeArea");
        this.w.setChecked(z);
        this.f10062a.setChecked(!z);
        this.s.setVisibility(z ? 0 : 8);
        this.d.setVisibility(z ? 8 : 0);
        this.m = !this.m;
    }

    private void b() {
        LogUtil.a("InvoiceApplicationActivity", "initListener");
        this.f.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qds
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceApplicationActivity.this.dAP_(view);
            }
        });
    }

    public /* synthetic */ void dAP_(View view) {
        if (nsn.a(500)) {
            LogUtil.a("InvoiceApplicationActivity", "click title bar left button too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void j() {
        LogUtil.a("InvoiceApplicationActivity", "showDialog");
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.g).d(R$string.IDS_invoice_verify_information).cyU_(R$string.IDS_settings_button_nps_ok, new View.OnClickListener() { // from class: qdq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceApplicationActivity.this.dAS_(view);
            }
        }).cyR_(com.huawei.ui.commonui.R$string.IDS_highland_cancel, new View.OnClickListener() { // from class: qdr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InvoiceApplicationActivity.dAK_(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dAS_(View view) {
        LogUtil.a("InvoiceApplicationActivity", "submit verify");
        a(AnalyticsValue.INVOICE_APPLY_2041099.value());
        Bundle dAM_ = dAM_();
        if (dAM_ == null) {
            LogUtil.b("InvoiceApplicationActivity", "bundle is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            qrg.dHE_(dAM_, new UiCallback<Integer>() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InvoiceApplicationActivity.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("InvoiceApplicationActivity", "apply failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Integer num) {
                    if (num.intValue() == 0) {
                        LogUtil.a("InvoiceApplicationActivity", "apply successful");
                    }
                    InvoiceApplicationActivity.this.c(num.intValue());
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static /* synthetic */ void dAK_(View view) {
        LogUtil.a("InvoiceApplicationActivity", "submit cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(String str) {
        LogUtil.a("InvoiceApplicationActivity", "invoice setBiEvent");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        ixx.d().d(this.g, str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle dAM_() {
        InvoiceOngoingFragment invoiceOngoingFragment = this.k;
        if (invoiceOngoingFragment != null && this.h == invoiceOngoingFragment) {
            LogUtil.h("InvoiceApplicationActivity", "switchToOnGoingFragment failed, cause mCurrentFragment is mOngoingFragment");
            return null;
        }
        LogUtil.a("InvoiceApplicationActivity", "switchToOnGoingFragment");
        if (this.k == null) {
            this.k = new InvoiceOngoingFragment();
        }
        Bundle dAL_ = dAL_();
        a(this.k);
        return dAL_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, int i) {
        InvoiceSuccessfulFragment invoiceSuccessfulFragment = this.x;
        if (invoiceSuccessfulFragment != null && this.h == invoiceSuccessfulFragment) {
            LogUtil.h("InvoiceApplicationActivity", "switchToSuccessFragment failed, cause mCurrentFragment is mSuccessfulFragment");
            return;
        }
        LogUtil.a("InvoiceApplicationActivity", "switchToSuccessFragment");
        if (this.x == null) {
            this.x = new InvoiceSuccessfulFragment();
        }
        d(this.x, UnitUtil.a("yyyy/MM/dd HH:mm:ss", j), i);
        a(this.x);
        a(AnalyticsValue.INVOICE_SUCCESSFUL_2041100.value());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, int i) {
        InvoiceFailedFragment invoiceFailedFragment = this.j;
        if (invoiceFailedFragment != null && this.h == invoiceFailedFragment) {
            LogUtil.h("InvoiceApplicationActivity", "switchToFailedFragment failed, cause mCurrentFragment is mFailedFragment");
            return;
        }
        LogUtil.a("InvoiceApplicationActivity", "switchToFailedFragment");
        if (this.j == null) {
            this.j = new InvoiceFailedFragment();
        }
        d(this.j, UnitUtil.a("yyyy/MM/dd HH:mm:ss", j), i);
        a(this.j);
    }

    private void a(Fragment fragment) {
        LogUtil.a("InvoiceApplicationActivity", "switchFragment");
        this.f.setTitleText(getString(com.huawei.ui.commonui.R$string.IDS_invoice_details));
        getSupportFragmentManager().beginTransaction().replace(R.id.base_invoice_card, fragment).commitAllowingStateLoss();
        this.h = fragment;
    }

    private Bundle dAL_() {
        LogUtil.a("InvoiceApplicationActivity", "setOnGoingData");
        Bundle bundle = new Bundle();
        bundle.putString("locale", mtj.e(null));
        bundle.putString("orderId", this.p);
        bundle.putString("orderCode", this.q);
        bundle.putInt("invoiceType", 3);
        bundle.putString("content", getString(R$string.IDS_invoice_commodity_details));
        bundle.putDouble(HwPayConstant.KEY_AMOUNT, nsn.j(this.u));
        StorageParams storageParams = new StorageParams();
        int i = 0;
        storageParams.d(0);
        if (this.w.isChecked()) {
            this.o = R$string.IDS_invoice_personal;
            bundle.putString("customerName", this.t.getText().toString());
            SharedPreferenceManager.e(this.g, String.valueOf(10050), "INVOICE_PERSON_NAME", this.t.getText().toString(), storageParams);
            i = 1;
        } else if (this.f10062a.isChecked()) {
            this.o = R$string.IDS_invoice_enterprise;
            bundle.putString("customerName", this.b.getText().toString());
            bundle.putString("taxNo", this.e.getText().toString());
            SharedPreferenceManager.e(this.g, String.valueOf(10050), "INVOICE_BUSINESS_NAME", this.b.getText().toString(), storageParams);
            SharedPreferenceManager.e(this.g, String.valueOf(10050), "INVOICE_BUSINESS_TAX", this.e.getText().toString(), storageParams);
            i = 2;
        } else {
            LogUtil.b("InvoiceApplicationActivity", "header is invalid");
        }
        bundle.putInt("invoiceHeader", this.o);
        bundle.putInt("customerType", i);
        this.k.setArguments(bundle);
        return bundle;
    }

    private void d(Fragment fragment, String str, int i) {
        int i2;
        LogUtil.a("InvoiceApplicationActivity", "setDataAndTime");
        Bundle bundle = new Bundle();
        if (i == 1) {
            i2 = R$string.IDS_invoice_personal;
        } else if (i == 2) {
            i2 = R$string.IDS_invoice_enterprise;
        } else {
            LogUtil.b("InvoiceApplicationActivity", "headerId is invalid");
            i2 = this.o;
        }
        bundle.putInt("invoiceHeader", i2);
        bundle.putString("orderId", this.p);
        bundle.putString("invoiceTime", str);
        bundle.putString(HwPayConstant.KEY_AMOUNT, this.u);
        bundle.putString("orderCode", this.q);
        fragment.setArguments(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthButton healthButton) {
        LogUtil.a("InvoiceApplicationActivity", "setButtonDisable");
        if (healthButton != null) {
            healthButton.setEnabled(false);
            healthButton.setClickable(false);
            healthButton.setFocusable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthButton healthButton) {
        LogUtil.a("InvoiceApplicationActivity", "setButtonEnable");
        if (healthButton != null) {
            healthButton.setEnabled(true);
            healthButton.setClickable(true);
            healthButton.setFocusable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        switch (i) {
            case 2040001:
            case 2160002:
            case 2160011:
            case 2160013:
            case 2160016:
                Context context = this.g;
                nrh.c(context, context.getResources().getString(R$string.IDS_invoice_apply_token));
                break;
            case 2160003:
                Context context2 = this.g;
                nrh.c(context2, context2.getResources().getString(R$string.IDS_invoice_apply_zero));
                break;
            case 2160004:
                Context context3 = this.g;
                nrh.c(context3, context3.getResources().getString(R$string.IDS_invoice_apply_network));
                break;
            case 2160010:
                Context context4 = this.g;
                nrh.c(context4, context4.getResources().getString(R$string.IDS_invoice_apply_expired));
                break;
        }
    }

    public Handler dAN_() {
        return this.l;
    }

    /* loaded from: classes8.dex */
    static final class InVoiceActivityHandler extends BaseHandler<InvoiceApplicationActivity> {
        public InVoiceActivityHandler(InvoiceApplicationActivity invoiceApplicationActivity) {
            super(invoiceApplicationActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dAT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InvoiceApplicationActivity invoiceApplicationActivity, Message message) {
            int i = message.what;
            int i2 = message.arg1;
            if (i == 0) {
                invoiceApplicationActivity.dAM_();
                return;
            }
            if (i == 1) {
                invoiceApplicationActivity.d(message.obj instanceof Long ? ((Long) message.obj).longValue() : 0L, i2);
            } else if (i == 2) {
                invoiceApplicationActivity.b(message.obj instanceof Long ? ((Long) message.obj).longValue() : 0L, i2);
            } else {
                LogUtil.b("InvoiceApplicationActivity", "handle msg failed, unknown message code = ", Integer.valueOf(i));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
