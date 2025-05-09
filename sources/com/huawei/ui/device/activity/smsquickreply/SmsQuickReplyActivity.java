package com.huawei.ui.device.activity.smsquickreply;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity;
import com.huawei.ui.device.adapter.smsquickreply.SimpleItemTouchHelperCallback;
import com.huawei.ui.device.adapter.smsquickreply.SmsItemMoveAdapter;
import com.huawei.ui.main.R$string;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.UnitUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class SmsQuickReplyActivity extends BaseActivity {
    private Context b;
    private HealthTextView c;
    private CustomTitleBar d;
    private LinearLayout g;
    private HwDeviceReplyPhraseEngineManager i;
    private HealthRecycleView k;
    private View n;
    private HealthScrollView o;
    private List<String> h = new ArrayList();
    private SmsItemMoveAdapter l = null;
    private boolean j = true;
    private boolean f = false;

    /* renamed from: a, reason: collision with root package name */
    private Handler f9232a = new Handler() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                return;
            }
            int i = message.what;
            if (i != 1001) {
                if (i == 1002) {
                    SmsQuickReplyActivity.this.f();
                    return;
                } else {
                    LogUtil.a("SmsQuickReplyActivity", "handler default");
                    return;
                }
            }
            SmsQuickReplyActivity.this.n.setVisibility(8);
            SmsQuickReplyActivity.this.g.setVisibility(0);
            SmsQuickReplyActivity.this.l.notifyDataSetChanged();
            if (SmsQuickReplyActivity.this.h.size() >= 15) {
                SmsQuickReplyActivity.this.c.setTextColor(SmsQuickReplyActivity.this.b.getResources().getColor(R.color._2131298172_res_0x7f09077c));
            }
        }
    };
    private BroadcastReceiver m = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("SmsQuickReplyActivity", "mLocaleChangedReceiver intent is null");
            } else if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                LogUtil.h("SmsQuickReplyActivity", "mLocaleChangedReceiver changed");
                SmsQuickReplyActivity.this.j = false;
            }
        }
    };
    private HwDeviceReplyPhraseEngineManager.Callback e = new HwDeviceReplyPhraseEngineManager.Callback() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.6
        @Override // com.huawei.hwdevice.mainprocess.mgr.smssend.HwDeviceReplyPhraseEngineManager.Callback
        public void onResponse(List<String> list) {
            SmsQuickReplyActivity.this.f9232a.removeMessages(1002);
            LogUtil.a("SmsQuickReplyActivity", "initData onResponse ", Integer.valueOf(list.size()));
            SmsQuickReplyActivity.this.h.clear();
            if (list.isEmpty()) {
                SmsQuickReplyActivity.this.h.addAll(HwDeviceReplyPhraseEngineManager.e().c());
            } else {
                SmsQuickReplyActivity.this.h.addAll(list);
            }
            SmsQuickReplyActivity.this.f9232a.sendEmptyMessage(1001);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (TextUtils.isEmpty(intent != null ? intent.getStringExtra("device_id") : null)) {
            LogUtil.h("SmsQuickReplyActivity", "onCreate currentDeviceId is empty");
            finish();
            return;
        }
        this.b = this;
        LogUtil.a("SmsQuickReplyActivity", "onCreate");
        this.i = HwDeviceReplyPhraseEngineManager.e();
        setContentView(R.layout.activity_sms_quick_reply);
        i();
        b();
        c();
        this.f = true;
    }

    private void a() {
        this.d = (CustomTitleBar) nsy.cMc_(this, R.id.setting_device_title_bar);
        this.o = (HealthScrollView) nsy.cMc_(this, R.id.sms_quick_reply_scrollview);
        this.k = (HealthRecycleView) nsy.cMc_(this, R.id.message_list);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.add_reply);
        this.n = nsy.cMc_(this, R.id.sms_quick_reply_load);
        this.g = (LinearLayout) findViewById(R.id.content_layout);
    }

    private void e() {
        this.k.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.8
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 1 && SmsQuickReplyActivity.this.l != null) {
                    SmsQuickReplyActivity.this.l.notifyDataSetChanged();
                }
                return false;
            }
        });
        this.l = new SmsItemMoveAdapter(this.b, this.h);
        this.k.setLayoutManager(new LinearLayoutManager(this.b));
        this.k.setAdapter(this.l);
        this.k.a(false);
        this.k.d(false);
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.l)).attachToRecyclerView(this.k);
    }

    private void c() {
        this.f9232a.sendEmptyMessageDelayed(1002, 3000L);
        this.i.b(this.e);
        this.i.f();
    }

    private void b() {
        a();
        e();
        this.d.setTitleText(getString(R.string._2130846706_res_0x7f0223f2));
        this.o.smoothScrollTo(0, 0);
        this.c.setText(getString(R.string._2130845231_res_0x7f021e2f).toUpperCase(Locale.ROOT));
        this.l.c(new SmsItemMoveAdapter.OnMessageOperationListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.14
            @Override // com.huawei.ui.device.adapter.smsquickreply.SmsItemMoveAdapter.OnMessageOperationListener
            public void onMessageDeleteListener(View view, int i) {
                if (!nsn.a(800)) {
                    if (SmsQuickReplyActivity.this.h.size() == 1) {
                        SmsQuickReplyActivity.this.h();
                        return;
                    } else {
                        SmsQuickReplyActivity.this.b(i);
                        return;
                    }
                }
                LogUtil.h("SmsQuickReplyActivity", "onMessageDeleteListener onClick isFastClick");
            }

            @Override // com.huawei.ui.device.adapter.smsquickreply.SmsItemMoveAdapter.OnMessageOperationListener
            public void onMessageEditListener(View view, int i) {
                if (!nsn.a(800)) {
                    SmsQuickReplyActivity.this.cSF_(i, view);
                } else {
                    LogUtil.h("SmsQuickReplyActivity", "onMessageEditListener onClick isFastClick");
                }
            }
        });
        d();
    }

    private void d() {
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.a(800)) {
                    if (SmsQuickReplyActivity.this.h.size() < 15) {
                        SmsQuickReplyActivity.this.j();
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    } else {
                        LogUtil.h("SmsQuickReplyActivity", "addButtonClick() Can't greater than 15");
                        nrh.d(SmsQuickReplyActivity.this.b, String.format(Locale.ROOT, SmsQuickReplyActivity.this.b.getString(R.string._2130845276_res_0x7f021e5c), UnitUtil.e(15.0d, 1, 0)));
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                LogUtil.h("SmsQuickReplyActivity", "mAddReply onClick isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.commonui_dialog_single_edit, (ViewGroup) null);
        final HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit);
        healthEditText.setText("");
        c(healthEditText);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.b);
        HealthButton b = builder.b();
        b.setEnabled(false);
        String e = UnitUtil.e(36.0d, 1, 0);
        if (LanguageUtil.h(this)) {
            e = UnitUtil.e(20.0d, 1, 0);
        }
        builder.a(String.format(Locale.ROOT, getString(R.string._2130845275_res_0x7f021e5b), UnitUtil.e(0.0d, 1, 0), e)).cyp_(inflate).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.c("SmsQuickReplyActivity", "showReplyDialog click cancle");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130841131_res_0x7f020e2b, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String trim = healthEditText.getText().toString().trim();
                LogUtil.a("SmsQuickReplyActivity", "showReplyDialog click message:", trim);
                if (!TextUtils.isEmpty(trim)) {
                    SmsQuickReplyActivity.this.h.add(trim);
                    SmsQuickReplyActivity.this.l.notifyDataSetChanged();
                    if (SmsQuickReplyActivity.this.h.size() >= 15) {
                        SmsQuickReplyActivity.this.c.setTextColor(SmsQuickReplyActivity.this.b.getResources().getColor(R.color._2131298172_res_0x7f09077c));
                    }
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    return;
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setOnShowListener(new DialogInterface.OnShowListener() { // from class: nyk
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                SmsQuickReplyActivity.this.cSG_(healthEditText, dialogInterface);
            }
        });
        c.setCanceledOnTouchOutside(false);
        c.show();
        b(builder, healthEditText, b);
    }

    public /* synthetic */ void cSG_(final HealthEditText healthEditText, DialogInterface dialogInterface) {
        healthEditText.postDelayed(new Runnable() { // from class: nyl
            @Override // java.lang.Runnable
            public final void run() {
                SmsQuickReplyActivity.this.a(healthEditText);
            }
        }, 100L);
    }

    public /* synthetic */ void a(HealthEditText healthEditText) {
        healthEditText.requestFocus();
        ((InputMethodManager) this.b.getSystemService("input_method")).showSoftInput(healthEditText, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cSF_(final int i, View view) {
        HealthTextView healthTextView = view instanceof HealthTextView ? (HealthTextView) view : null;
        if (healthTextView == null) {
            return;
        }
        String obj = healthTextView.getText().toString();
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.commonui_dialog_single_edit, (ViewGroup) null);
        final HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit);
        c(healthEditText);
        healthEditText.setText(obj);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.b);
        builder.a(String.format(Locale.ROOT, getString(R.string._2130845275_res_0x7f021e5b), UnitUtil.e(obj.length(), 1, 0), UnitUtil.e(e(obj) ? 20 : 36, 1, 0))).cyp_(inflate).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                LogUtil.c("SmsQuickReplyActivity", "showEditDialog click cancle");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        }).cyo_(R.string._2130841131_res_0x7f020e2b, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.15
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                String trim = healthEditText.getText().toString().trim();
                LogUtil.a("SmsQuickReplyActivity", "showEditDialog click message:", trim);
                if (!TextUtils.isEmpty(trim)) {
                    SmsQuickReplyActivity.this.h.set(i, trim);
                    SmsQuickReplyActivity.this.l.notifyDataSetChanged();
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
                    return;
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        });
        HealthButton b = builder.b();
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
        b(builder, healthEditText, b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final int i) {
        new NoTitleCustomAlertDialog.Builder(this.b).e(R.string._2130845235_res_0x7f021e33).czC_(R.string._2130841397_res_0x7f020f35, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SmsQuickReplyActivity", "showDeleteDialog click yes");
                SmsQuickReplyActivity.this.h.remove(i);
                SmsQuickReplyActivity.this.l.notifyDataSetChanged();
                if (SmsQuickReplyActivity.this.h.size() < 15) {
                    LogUtil.a("SmsQuickReplyActivity", "showDeleteDialog click yes", Integer.valueOf(SmsQuickReplyActivity.this.h.size()));
                    SmsQuickReplyActivity.this.c.setTextColor(SmsQuickReplyActivity.this.b.getResources().getColor(R.color._2131298141_res_0x7f09075d));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SmsQuickReplyActivity", "showDeleteDialog click cancle");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("SmsQuickReplyActivity", "showTimeoutDialog enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string.IDS_sms_get_device_failed);
        builder.czC_(R$string.IDS_highland_know, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SmsQuickReplyActivity", "showTimeoutDialog onClick");
                SmsQuickReplyActivity.this.j = false;
                SmsQuickReplyActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130845247_res_0x7f021e3f);
        builder.czC_(R$string.IDS_highland_know, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SmsQuickReplyActivity", "showNotDeleteDialog click cancle");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private void b(CustomAlertDialog.Builder builder, HealthEditText healthEditText, HealthButton healthButton) {
        healthEditText.addTextChangedListener(new a(builder, healthEditText, healthButton));
    }

    private void c(HealthEditText healthEditText) {
        healthEditText.setFilters(new InputFilter[]{new InputFilter() { // from class: com.huawei.ui.device.activity.smsquickreply.SmsQuickReplyActivity.9
            private Pattern e = Pattern.compile("[\\uD800\\uDC00-\\uDBFF\\uDFFF]|[[\\u2070-\\u27FF]\\uFE0F]|[\\u2070-\\u27FF]|[[\\u2900-\\u2BFF]\\uFE0F]|[\\u2900-\\u2BFF]", 64);

            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return ((TextUtils.isEmpty(charSequence.toString().trim()) && TextUtils.isEmpty(spanned.toString().trim())) || this.e.matcher(charSequence).find()) ? "" : charSequence;
            }
        }});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            if (e(str)) {
                return 20;
            }
            if (str.length() == bytes.length) {
                return 36;
            }
            String substring = str.substring(str.length() - 1);
            if (a(substring)) {
                return i;
            }
            byte[] bytes2 = substring.getBytes("UTF-8");
            LogUtil.c("SmsQuickReplyActivity", "obtainMaxLength inputBytes:", Integer.valueOf(bytes.length), " lastCharBytes:", Integer.valueOf(bytes2.length));
            return 70 / bytes2.length;
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("SmsQuickReplyActivity", "obtainMaxLength Exception");
            return i;
        }
    }

    private boolean a(String str) {
        return Pattern.compile("[ -~]").matcher(str).find();
    }

    private boolean e(String str) {
        return Pattern.compile("[一-龥|、|。|？|！|，|；|：|“|”]|‘|’|（|）|《|》|〈|〉|【|】|『|』|「|」|﹃|﹄|〔|〕|…|—|～|﹏|￥]").matcher(str).find();
    }

    private void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        BroadcastManagerUtil.bFA_(this.b, this.m, intentFilter, LocalBroadcast.c, null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("SmsQuickReplyActivity", "onDestroy");
        if (!this.f) {
            LogUtil.h("SmsQuickReplyActivity", "onDestroy mIsOnCreated is false");
            return;
        }
        this.i.b((HwDeviceReplyPhraseEngineManager.Callback) null);
        if (this.j) {
            this.i.d(this.h);
        }
        Handler handler = this.f9232a;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        unregisterReceiver(this.m);
    }

    class a implements TextWatcher {

        /* renamed from: a, reason: collision with root package name */
        private CustomAlertDialog.Builder f9236a;
        private HealthEditText c;
        private HealthButton i;
        private int h = 36;
        private String e = "";
        private String b = "";

        a(CustomAlertDialog.Builder builder, HealthEditText healthEditText, HealthButton healthButton) {
            this.f9236a = builder;
            this.c = healthEditText;
            this.i = healthButton;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.c("SmsQuickReplyActivity", "refreshContentSize beforeTextChanged");
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            LogUtil.c("SmsQuickReplyActivity", "refreshContentSize onTextChanged start:", Integer.valueOf(i), " before:", Integer.valueOf(i2), " count:", Integer.valueOf(i3), " ", charSequence.toString());
            String obj = this.c.getText().toString();
            this.b = obj;
            int b = SmsQuickReplyActivity.this.b(obj, this.h);
            this.h = b;
            LogUtil.c("SmsQuickReplyActivity", "refreshContentSize onTextChanged mMaxLength:", Integer.valueOf(b));
            if (i3 == 1 && i < i2 && this.b.length() > i3 && this.e.length() > 0) {
                this.c.setText(this.e);
                this.c.setSelection(this.e.length());
            }
            int length = this.b.length();
            int i4 = this.h;
            if (length > i4) {
                String substring = this.b.substring(0, i4);
                this.e = substring;
                this.c.setText(substring);
                this.c.setSelection(this.e.length());
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            LogUtil.c("SmsQuickReplyActivity", "refreshContentSize afterTextChanged: ", editable.toString().trim());
            int length = editable.toString().length();
            int i = this.h;
            if (length > i) {
                length = i;
            }
            if (this.e.length() > 0 && this.b.length() == this.e.length()) {
                length = this.e.length();
            }
            this.f9236a.a(String.format(Locale.ROOT, SmsQuickReplyActivity.this.getString(R.string._2130845275_res_0x7f021e5b), UnitUtil.e(length, 1, 0), UnitUtil.e(this.h, 1, 0)));
            if (this.i == null) {
                return;
            }
            if (TextUtils.isEmpty(editable.toString()) && this.i.isEnabled()) {
                this.i.setEnabled(false);
            } else if (!TextUtils.isEmpty(editable.toString()) && !this.i.isEnabled()) {
                this.i.setEnabled(true);
            } else {
                LogUtil.c("SmsQuickReplyActivity", "refreshContentSize afterTextChanged else");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
