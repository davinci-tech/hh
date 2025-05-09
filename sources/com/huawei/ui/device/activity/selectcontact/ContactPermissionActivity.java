package com.huawei.ui.device.activity.selectcontact;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nxu;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ContactPermissionActivity extends BaseActivity {
    private int b = 0;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_contact_permission);
        e();
        a();
    }

    private void e() {
        ((HealthButton) findViewById(R.id.manually_add_contact)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.selectcontact.ContactPermissionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("ContactPermissionActivity", "initView click manual add");
                Intent intent = new Intent(ContactPermissionActivity.this, (Class<?>) ManuallyAddContactActivity.class);
                intent.putExtra("com.huawei.community.action.MAX_SELECT_COUNT", ContactPermissionActivity.this.b);
                ContactPermissionActivity.this.startActivityForResult(intent, 2);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a() {
        LogUtil.a("ContactPermissionActivity", "initData enter");
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getIntExtra("com.huawei.community.action.MAX_SELECT_COUNT", 0);
        }
        b();
    }

    private void b() {
        String string = getString(R.string._2130846754_res_0x7f022422);
        String format = String.format(Locale.ENGLISH, getString(R.string._2130846753_res_0x7f022421), string);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.contact_permission_description);
        int lastIndexOf = format.lastIndexOf(string);
        if (lastIndexOf == -1) {
            ReleaseLogUtil.d("DEVMGR_ContactPermissionActivity", "initGoSetPermission index -1");
            healthTextView.setText(format);
        } else {
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new ClickableSpan() { // from class: com.huawei.ui.device.activity.selectcontact.ContactPermissionActivity.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    LogUtil.a("ContactPermissionActivity", "initGoSetPermission allow access");
                    nxu.cSe_(ContactPermissionActivity.this, 3);
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ContactPermissionActivity.this.getResources().getColor(R.color._2131299237_res_0x7f090ba5));
                    textPaint.setTypeface(Typeface.create(ContactPermissionActivity.this.getString(R.string._2130851581_res_0x7f0236fd), 0));
                    textPaint.setUnderlineText(false);
                }
            }, lastIndexOf, string.length() + lastIndexOf, 33);
            healthTextView.setText(spannableString);
            healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ReleaseLogUtil.e("DEVMGR_ContactPermissionActivity", "onActivityResult requestCode:", Integer.valueOf(i), " resultCode:", Integer.valueOf(i2));
        if (i != 2) {
            if (i == 3) {
                boolean z = PermissionUtil.e(this, PermissionUtil.PermissionType.READ_CONTACT) == PermissionUtil.PermissionResult.GRANTED;
                ReleaseLogUtil.e("DEVMGR_ContactPermissionActivity", "onActivityResult hasContactPermission:", Boolean.valueOf(z));
                if (z) {
                    finish();
                    return;
                }
                return;
            }
            LogUtil.h("ContactPermissionActivity", "onActivityResult not support requestCode:", Integer.valueOf(i));
            return;
        }
        if (intent == null) {
            ReleaseLogUtil.d("DEVMGR_ContactPermissionActivity", "onActivityResult data is null");
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra(JsUtil.ServiceType.DATA);
        if (serializableExtra == null) {
            ReleaseLogUtil.d("DEVMGR_ContactPermissionActivity", "onActivityResult resultData is null");
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra(JsUtil.ServiceType.DATA, serializableExtra);
        setResult(-1, intent2);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
