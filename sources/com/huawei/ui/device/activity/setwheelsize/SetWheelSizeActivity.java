package com.huawei.ui.device.activity.setwheelsize;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity;
import defpackage.cvn;
import defpackage.jnq;
import defpackage.jnr;
import defpackage.jqi;
import defpackage.jrh;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes6.dex */
public class SetWheelSizeActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthNumberPicker f9222a;
    private RelativeLayout b;
    private CustomAlertDialog c;
    private Context d;
    private DeviceInfo e;
    private ImageView g;
    private HealthTextView h;
    private HealthEditText j;
    private CustomAlertDialog k;
    private RelativeLayout m;
    private String o = UnitUtil.e(1.0d, 1, 0);
    private String l = UnitUtil.e(2999.0d, 1, 0);
    private boolean n = false;
    private DialogInterface.OnClickListener i = new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity.4
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            jnr.b().e().e(0);
            int value = SetWheelSizeActivity.this.f9222a.getValue();
            if (value >= 0 && value < jnq.b().size()) {
                SetWheelSizeActivity.this.h.setText(jnq.b().get(value));
                SetWheelSizeActivity.this.i();
                SetWheelSizeActivity.this.f();
                long currentTimeMillis = System.currentTimeMillis();
                jqi.a().setSwitchSettingToDb(jrh.b(SetWheelSizeActivity.this.e.getDeviceIdentify()), jrh.c(String.valueOf(currentTimeMillis), SetWheelSizeActivity.this.c()));
            }
            SetWheelSizeActivity.this.a();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    };
    private final BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("SetWheelSizeActivity", "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                LogUtil.h("SetWheelSizeActivity", "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            LogUtil.a("SetWheelSizeActivity", "mDeviceStatusReceiver onReceive action :", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                if (deviceInfo == null || SetWheelSizeActivity.this.e == null || !deviceInfo.getDeviceIdentify().equals(SetWheelSizeActivity.this.e.getDeviceIdentify())) {
                    LogUtil.h("SetWheelSizeActivity", "mDeviceStatusReceiver not current device");
                    return;
                }
                jnr.b().a();
                LogUtil.a("SetWheelSizeActivity", "deviceInfo.getDeviceConnectState(),", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                SetWheelSizeActivity.this.c(deviceInfo);
                return;
            }
            LogUtil.h("SetWheelSizeActivity", "device status not change");
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() != 2) {
            this.b.setVisibility(0);
            this.m.setEnabled(false);
            cSp_(this.m.getLayoutParams(), false);
            e();
            a();
            return;
        }
        if (deviceInfo.getDeviceConnectState() == 2) {
            this.b.setVisibility(4);
            this.m.setEnabled(true);
            cSp_(this.m.getLayoutParams(), true);
            return;
        }
        LogUtil.h("SetWheelSizeActivity", "other status");
    }

    private void cSp_(ViewGroup.LayoutParams layoutParams, boolean z) {
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (z) {
                layoutParams2.addRule(3, R.id.rimSizeBar);
            } else {
                layoutParams2.addRule(3, R.id.find_error_layout);
            }
            this.m.setLayoutParams(layoutParams2);
        }
    }

    private void e() {
        CustomAlertDialog customAlertDialog = this.c;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            return;
        }
        this.c.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CustomAlertDialog customAlertDialog = this.k;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            return;
        }
        this.k.dismiss();
    }

    private void j() {
        BroadcastManagerUtil.bFC_(this.d, this.f, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void l() {
        try {
            LogUtil.a("SetWheelSizeActivity", "Enter unregisterBindDeviceBroadcast()!");
            unregisterReceiver(this.f);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("SetWheelSizeActivity", "unregisterBindDeviceBroadcast failed");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = this;
        setContentView(R.layout.activity_set_wheel_size);
        j();
        b();
    }

    private void b() {
        String str;
        Parcelable parcelable;
        this.m = (RelativeLayout) findViewById(R.id.rim);
        this.h = (HealthTextView) findViewById(R.id.textView_size);
        this.g = (ImageView) findViewById(R.id.imageView_arrow);
        if (LanguageUtil.bc(this)) {
            this.g.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.g.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.b = (RelativeLayout) findViewById(R.id.find_error_layout);
        this.m.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("rimSize");
            parcelable = intent.getParcelableExtra("device");
        } else {
            str = "";
            parcelable = null;
        }
        if (parcelable instanceof DeviceInfo) {
            this.e = (DeviceInfo) parcelable;
        }
        if (this.e == null) {
            LogUtil.h("SetWheelSizeActivity", "onCreate mDeviceInfo is null");
        } else {
            this.h.setText(str);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.m) {
            g();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        int e = jnr.b().e().e();
        int d = jnr.b().e().d();
        return jnr.b().e().a() + "," + e + "," + d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (jnr.b().e().e() == 0) {
            jnr.b().e().a(this.f9222a.getValue());
            jnr.b().e().b(3000);
        } else {
            jnr.b().e().a(1);
            try {
                jnr.b().e().b(Integer.parseInt(this.j.getText().toString().trim()));
            } catch (NumberFormatException unused) {
                LogUtil.b("SetWheelSizeActivity", "setSendData NumberFormatException");
                return;
            }
        }
        jnr.b().e().b(System.currentTimeMillis());
    }

    private void g() {
        if (this.k == null) {
            View inflate = getLayoutInflater().inflate(R.layout.dialog_set_wheel_size, (ViewGroup) null);
            HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.bicycle_size_picker);
            this.f9222a = healthNumberPicker;
            healthNumberPicker.setDisplayedValues((String[]) jnq.b().toArray(new String[0]));
            this.f9222a.setMinValue(0);
            this.f9222a.setMaxValue(jnq.b().size() - 1);
            this.f9222a.setWrapSelectorWheel(false);
            int d = jnr.b().e().d();
            if (jnr.b().e().e() == 0) {
                this.f9222a.setValue(d);
            } else {
                this.f9222a.setValue(9);
            }
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.custom_setting_button);
            CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this).cyp_(inflate).cyo_(R.string._2130845465_res_0x7f021f19, this.i).cyn_(R.string._2130845464_res_0x7f021f18, new DialogInterface.OnClickListener() { // from class: nyb
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    SetWheelSizeActivity.this.cSv_(dialogInterface, i);
                }
            });
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SetWheelSizeActivity.this.h();
                    SetWheelSizeActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomAlertDialog c = cyn_.c();
            this.k = c;
            c.setCancelable(false);
        }
        this.k.show();
    }

    public /* synthetic */ void cSv_(DialogInterface dialogInterface, int i) {
        a();
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void cSu_(final View view, final HealthButton healthButton) {
        final HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_target_unit);
        healthTextView.setText(this.d.getResources().getQuantityString(R.plurals._2130903341_res_0x7f03012d, 0, ""));
        healthTextView.setVisibility(8);
        this.j.addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LogUtil.a("SetWheelSizeActivity", "custom input before");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetWheelSizeActivity.this.cSs_(view, charSequence);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (!SetWheelSizeActivity.this.n) {
                    boolean z = true;
                    SetWheelSizeActivity.this.n = true;
                    String trim = editable.toString().trim();
                    if (TextUtils.isEmpty(trim)) {
                        healthTextView.setVisibility(8);
                        healthButton.setEnabled(false);
                        SetWheelSizeActivity.this.n = false;
                        return;
                    }
                    healthTextView.setVisibility(0);
                    try {
                        int parseInt = Integer.parseInt(trim);
                        if (parseInt < 1 || parseInt > 2999) {
                            z = false;
                        }
                        healthButton.setEnabled(z);
                    } catch (NumberFormatException unused) {
                        LogUtil.b("SetWheelSizeActivity", "setTextWatcher NumberFormatException");
                    }
                    SetWheelSizeActivity.this.n = false;
                    return;
                }
                LogUtil.a("SetWheelSizeActivity", "mIsFlag is true");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cSs_(View view, CharSequence charSequence) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.remind);
        if (charSequence == null || TextUtils.isEmpty(charSequence.toString())) {
            return;
        }
        try {
            int parseInt = Integer.parseInt(String.valueOf(charSequence));
            if (parseInt >= 1 && parseInt <= 2999) {
                healthTextView.setVisibility(8);
            }
            healthTextView.setVisibility(0);
        } catch (NumberFormatException unused) {
            LogUtil.b("SetWheelSizeActivity", "handleTextChanged NumberFormatException");
        }
    }

    private SpannedString cSr_() {
        SpannableString spannableString = new SpannableString(getString(R.string._2130845489_res_0x7f021f31, new Object[]{this.o, this.l}));
        if (nsn.r()) {
            spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, spannableString.length(), 33);
        }
        return new SpannedString(spannableString);
    }

    private void cSq_(View view, CustomAlertDialog.Builder builder) {
        HealthEditText healthEditText = (HealthEditText) view.findViewById(R.id.custom_target_editText);
        this.j = healthEditText;
        healthEditText.setHint(cSr_());
        this.j.setMaxLines(2);
        this.j.setHorizontallyScrolling(false);
        this.j.setInputType(131074);
        new Timer("SetWheelSizeActivity").schedule(new TimerTask() { // from class: com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Object systemService = SetWheelSizeActivity.this.j.getContext().getSystemService("input_method");
                if (systemService instanceof InputMethodManager) {
                    ((InputMethodManager) systemService).showSoftInput(SetWheelSizeActivity.this.j, 0);
                }
            }
        }, 300L);
        this.j.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        HealthButton b = builder.b();
        b.setEnabled(false);
        cSu_(view, b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.h("SetWheelSizeActivity", "showDialog Entering");
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_set_wheel_size, (ViewGroup) null);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        cSq_(inflate, builder);
        builder.e(R.string._2130845460_res_0x7f021f14).cyp_(inflate).cyn_(R.string._2130845464_res_0x7f021f18, new DialogInterface.OnClickListener() { // from class: nxy
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130845465_res_0x7f021f19, new DialogInterface.OnClickListener() { // from class: nxx
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SetWheelSizeActivity.this.cSw_(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        this.c = c;
        if (c == null || c.isShowing()) {
            return;
        }
        this.c.setCancelable(false);
        this.c.show();
    }

    public /* synthetic */ void cSw_(DialogInterface dialogInterface, int i) {
        jnr.b().e().e(1);
        try {
            this.h.setText(this.d.getResources().getQuantityString(R.plurals._2130903341_res_0x7f03012d, Integer.parseInt(this.j.getText().toString().trim()), UnitUtil.e(Integer.parseInt(this.j.getText().toString().trim()), 1, 0)));
        } catch (NumberFormatException unused) {
            LogUtil.b("SetWheelSizeActivity", "showDialog NumberFormatException");
            this.h.setText("");
        }
        i();
        f();
        jqi.a().setSwitchSettingToDb(jrh.b(this.e.getDeviceIdentify()), jrh.c(String.valueOf(System.currentTimeMillis()), c()));
        LogUtil.h("SetWheelSizeActivity", "custom set rimSize");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        jnr.b().b(this.e, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.setwheelsize.SetWheelSizeActivity.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("SetWheelSizeActivity", "onCreate sendSettingDeviceCommand onResponse errorCode,", Integer.valueOf(i));
                if (i == 100000 && (obj instanceof cvn)) {
                    cvn cvnVar = (cvn) obj;
                    int w = CommonUtil.w(HEXUtils.a(cvnVar.b()));
                    LogUtil.a("SetWheelSizeActivity", "onCreate onResponse,", Integer.valueOf(cvnVar.e()), Integer.valueOf(w));
                    if (cvnVar.e() == 1 && w == 0) {
                        LogUtil.a("SetWheelSizeActivity", "sendSettingDeviceCommand success");
                    } else {
                        LogUtil.a("SetWheelSizeActivity", "sendSettingDeviceCommand showResponseFailedDialog");
                    }
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("SetWheelSizeActivity", "onDestroy()");
        l();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
