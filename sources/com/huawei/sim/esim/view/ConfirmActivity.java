package com.huawei.sim.esim.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncf;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class ConfirmActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8673a;
    private View e;
    private String g;
    private HealthEditText h;
    private CustomTitleBar i;
    private HealthTextView j;
    private HealthTextView k;
    private ImageView l;
    private int n;
    private CommonDialog21 o = null;
    private boolean m = false;
    private Handler f = new b(this);
    private IBaseResponseCallback d = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.ConfirmActivity.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("ConfirmActivity", "errorCode ", Integer.valueOf(i));
        }
    };
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.ConfirmActivity.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("ConfirmActivity", "mCodeBaseResponseCallback errorCode ", Integer.valueOf(i));
        }
    };
    private IBaseResponseCallback b = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.ConfirmActivity.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("ConfirmActivity", "mAuthBaseResponseCallback errorCode ", Integer.valueOf(i));
            Message obtain = Message.obtain();
            obtain.what = 10001;
            obtain.arg1 = i;
            obtain.obj = obj;
            if (i != 0 || !ConfirmActivity.this.m) {
                ConfirmActivity.this.f.sendMessage(obtain);
            } else {
                ConfirmActivity.this.f.sendMessageDelayed(obtain, 5000L);
            }
        }
    };
    private TextWatcher p = new TextWatcher() { // from class: com.huawei.sim.esim.view.ConfirmActivity.2
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.c("ConfirmActivity", "CharSequence ", charSequence.toString(), "; length ", Integer.valueOf(charSequence.length()));
            if (charSequence.length() > 255) {
                ConfirmActivity.this.j.setVisibility(0);
                ConfirmActivity.this.j.setText(R.string._2130847907_res_0x7f0228a3);
                ConfirmActivity.this.k.setEnabled(false);
                ConfirmActivity.this.k.setTextColor(ConfirmActivity.this.getResources().getColor(R.color._2131296314_res_0x7f09003a));
                if (LanguageUtil.bc(ConfirmActivity.this)) {
                    ConfirmActivity.this.l.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
                } else {
                    ConfirmActivity.this.l.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
                }
                ConfirmActivity.this.h.setBackgroundResource(R.drawable._2131431485_res_0x7f0b103d);
                return;
            }
            if (charSequence.length() != 0) {
                ConfirmActivity.this.k.setEnabled(true);
                ConfirmActivity.this.k.setTextColor(ConfirmActivity.this.getResources().getColor(R.color._2131296318_res_0x7f09003e));
                if (LanguageUtil.bc(ConfirmActivity.this)) {
                    ConfirmActivity.this.l.setImageResource(R.drawable._2131431476_res_0x7f0b1034);
                } else {
                    ConfirmActivity.this.l.setImageResource(R.drawable._2131431488_res_0x7f0b1040);
                }
            } else {
                ConfirmActivity.this.k.setEnabled(false);
                ConfirmActivity.this.k.setTextColor(ConfirmActivity.this.getResources().getColor(R.color._2131296314_res_0x7f09003a));
                if (LanguageUtil.bc(ConfirmActivity.this)) {
                    ConfirmActivity.this.l.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
                } else {
                    ConfirmActivity.this.l.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
                }
            }
            ConfirmActivity.this.j.setVisibility(4);
            ConfirmActivity.this.h.setBackgroundResource(R.drawable._2131431484_res_0x7f0b103c);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        nsn.cLz_(this);
        setContentView(R.layout.activity_confirm);
        Intent intent = getIntent();
        if (intent != null) {
            this.g = intent.getStringExtra("confirm_code");
        }
        e();
        ktx.e().c(this.d);
        this.m = ncf.b();
    }

    private void e() {
        this.j = (HealthTextView) findViewById(R.id.confirm_invilid_tips);
        this.h = (HealthEditText) findViewById(R.id.confirm_code);
        this.i = (CustomTitleBar) findViewById(R.id.wirless_manager_title_bar);
        this.e = findViewById(R.id.back_button_text);
        this.k = (HealthTextView) findViewById(R.id.next_button_text);
        this.l = (ImageView) findViewById(R.id.next_button);
        this.f8673a = (ImageView) findViewById(R.id.back_button);
        if (LanguageUtil.bc(this)) {
            this.l.setImageResource(R.drawable._2131431476_res_0x7f0b1034);
            this.f8673a.setImageResource(R.drawable._2131431488_res_0x7f0b1040);
        }
        c();
    }

    private void c() {
        this.i.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.ConfirmActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ConfirmActivity", "mCustomTitleBar.setLeftButtonOnClickListener");
                ktx.e().e(null, 1, ConfirmActivity.this.c, null);
                ConfirmActivity.this.startActivity(new Intent(ConfirmActivity.this, (Class<?>) EsimActivationActivity.class));
                ConfirmActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.ConfirmActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ConfirmActivity", "mBackButton.setOnClickListener");
                ktx.e().e(null, 1, ConfirmActivity.this.c, null);
                ConfirmActivity.this.startActivity(new Intent(ConfirmActivity.this, (Class<?>) EsimActivationActivity.class));
                ConfirmActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.ConfirmActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ConfirmActivity confirmActivity = ConfirmActivity.this;
                confirmActivity.g = confirmActivity.h.getText().toString();
                LogUtil.c("ConfirmActivity", "mConfirmCode: ", ConfirmActivity.this.g);
                if (ktx.e().c() == 2) {
                    ConfirmActivity.this.c(R.string._2130847908_res_0x7f0228a4);
                    ktx.e().e(ConfirmActivity.this.g, 0, ConfirmActivity.this.c, ConfirmActivity.this.b);
                } else {
                    Intent intent = new Intent(ConfirmActivity.this, (Class<?>) EsimConfirmBtFailActivity.class);
                    intent.putExtra("confirm_code", ConfirmActivity.this.g);
                    ConfirmActivity.this.startActivityForResult(intent, 0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.h.addTextChangedListener(this.p);
        LogUtil.c("ConfirmActivity", "onResume mConfirmCode ", this.g);
        String str = this.g;
        if (str != null) {
            this.h.setText(str);
            HealthEditText healthEditText = this.h;
            healthEditText.setSelection(healthEditText.getText().length());
        }
        LogUtil.c("ConfirmActivity", "CharSequence ", Integer.valueOf(this.n));
        if (this.n != 0) {
            this.k.setEnabled(false);
            this.k.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
            if (LanguageUtil.bc(this)) {
                this.l.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
            } else {
                this.l.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
            }
            this.h.setBackgroundResource(R.drawable._2131431485_res_0x7f0b103d);
            this.j.setVisibility(0);
            this.j.setText(R.string._2130847877_res_0x7f022885);
            return;
        }
        if (this.h.getText().length() == 0) {
            this.k.setEnabled(false);
            this.k.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
            if (LanguageUtil.bc(this)) {
                this.l.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
            } else {
                this.l.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
            }
        } else {
            this.k.setEnabled(true);
            this.k.setTextColor(getResources().getColor(R.color._2131296318_res_0x7f09003e));
            if (LanguageUtil.bc(this)) {
                this.l.setImageResource(R.drawable._2131431476_res_0x7f0b1034);
            } else {
                this.l.setImageResource(R.drawable._2131431488_res_0x7f0b1040);
            }
        }
        this.j.setVisibility(4);
        this.h.setBackgroundResource(R.drawable._2131431484_res_0x7f0b103c);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("ConfirmActivity", "onDestroy()");
        d();
        super.onDestroy();
        ktx.e().e(this.d);
        ktx.e().a(this.c, this.b);
        HealthEditText healthEditText = this.h;
        if (healthEditText != null) {
            healthEditText.removeTextChangedListener(this.p);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    private void e(int i) {
        LogUtil.a("ConfirmActivity", "the error code is: ", Integer.valueOf(i));
        Intent intent = new Intent(this, (Class<?>) EsimConfirmInvailActivity.class);
        intent.putExtra("confirm_error", i);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        CommonDialog21 commonDialog21 = this.o;
        if (commonDialog21 == null) {
            LogUtil.a("ConfirmActivity", "mLoadingUserInformationDialog is null");
            new CommonDialog21(this, R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.o = a2;
            a2.e(getResources().getString(i));
            this.o.setCancelable(false);
        } else {
            commonDialog21.e(getResources().getString(i));
        }
        this.o.a();
        LogUtil.a("ConfirmActivity", "mLoadingUserInformationDialog show");
    }

    private boolean b(int i) {
        CommonDialog21 commonDialog21;
        if (i != -2 || (commonDialog21 = this.o) == null || !commonDialog21.isShowing()) {
            return false;
        }
        Intent intent = new Intent(this, (Class<?>) EsimConfirmBtFailActivity.class);
        intent.putExtra("confirm_code", this.g);
        startActivityForResult(intent, 0);
        d();
        return true;
    }

    private void d() {
        if (isFinishing()) {
            return;
        }
        LogUtil.a("ConfirmActivity", "enter dismissLoadingDialog()");
        CommonDialog21 commonDialog21 = this.o;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("ConfirmActivity", "dismissLoadingDialog!");
        this.o.cancel();
        this.o = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.c("ConfirmActivity", "requestCode:", Integer.valueOf(i), "; resultCode:", Integer.valueOf(i2));
        super.onActivityResult(i, i2, intent);
        if (i2 != 1 || intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("confirm_report", 1);
        this.n = intExtra;
        if (intExtra != 0) {
            this.k.setEnabled(false);
            this.k.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
            if (LanguageUtil.bc(this)) {
                this.l.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
            } else {
                this.l.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
            }
            this.h.setBackgroundResource(R.drawable._2131431485_res_0x7f0b103d);
            this.j.setVisibility(0);
            this.j.setText(R.string._2130847877_res_0x7f022885);
            return;
        }
        this.k.setEnabled(true);
        this.k.setTextColor(getResources().getColor(R.color._2131296318_res_0x7f09003e));
        if (LanguageUtil.bc(this)) {
            this.l.setImageResource(R.drawable._2131431476_res_0x7f0b1034);
        } else {
            this.l.setImageResource(R.drawable._2131431488_res_0x7f0b1040);
        }
        this.j.setVisibility(4);
        this.h.setBackgroundResource(R.drawable._2131431484_res_0x7f0b103c);
    }

    private void a() {
        this.j.setVisibility(0);
        this.j.setText(R.string._2130847877_res_0x7f022885);
        this.h.setBackgroundResource(R.drawable._2131431485_res_0x7f0b103d);
        this.k.setEnabled(false);
        this.k.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
        if (LanguageUtil.bc(this)) {
            this.l.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
        } else {
            this.l.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        LogUtil.a("ConfirmActivity", "ConfirmActivity onKeyDown");
        if (i == 4) {
            ktx.e().e(null, 1, this.c, null);
            startActivity(new Intent(this, (Class<?>) EsimActivationActivity.class));
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    static class b extends BaseHandler<ConfirmActivity> {
        b(ConfirmActivity confirmActivity) {
            super(confirmActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: crO_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ConfirmActivity confirmActivity, Message message) {
            if (message == null) {
                LogUtil.h("ConfirmActivity", "ConfirmActivityHandler handleMessageWhenReferenceNotNull msg is null");
            } else if (message.what == 10001) {
                confirmActivity.e(message.arg1, message.obj);
            } else {
                LogUtil.h("ConfirmActivity", "ConfirmActivityHandler handleMessageWhenReferenceNotNull is default");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, Object obj) {
        ncf.c(i, true);
        if (i == 0) {
            Intent intent = new Intent(this, (Class<?>) EsimProfileSuccessActivity.class);
            intent.putExtra("confirm_status", true);
            startActivity(intent);
        } else if (i == 4) {
            Intent intent2 = new Intent(this, (Class<?>) EsimConfirmBtFailActivity.class);
            intent2.putExtra("confirm_error", "network_failed");
            intent2.putExtra("confirm_code", this.g);
            startActivityForResult(intent2, 0);
        } else if (i == 1 || i == 2) {
            this.n = 1;
            a();
        } else {
            if ((obj instanceof Integer) && b(((Integer) obj).intValue())) {
                return;
            }
            sqo.o("parseAuthCallback Invail errorCode: " + i);
            e(i);
        }
        d();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
