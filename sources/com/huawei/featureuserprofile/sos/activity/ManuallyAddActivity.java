package com.huawei.featureuserprofile.sos.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gmx;
import defpackage.ixx;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class ManuallyAddActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f2022a;
    private ImageView b;
    private CustomTitleBar c;
    private HealthEditText d;
    private HealthEditText e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(8192);
        setContentView(R.layout.activity_manually_add);
        c();
    }

    private void c() {
        ImageView imageView = (ImageView) findViewById(R.id.manually_add_name_image);
        this.f2022a = imageView;
        imageView.setImageDrawable(getResources().getDrawable(R.drawable._2131429886_res_0x7f0b09fe));
        ImageView imageView2 = (ImageView) findViewById(R.id.manually_add_phonenumber_image);
        this.b = imageView2;
        imageView2.setImageDrawable(getResources().getDrawable(R.drawable._2131430238_res_0x7f0b0b5e));
        this.d = (HealthEditText) findViewById(R.id.manually_add_name_edit);
        this.e = (HealthEditText) findViewById(R.id.manually_add_phone_edit);
        uT_(this.b, R.drawable._2131430238_res_0x7f0b0b5e);
        a();
        this.c = (CustomTitleBar) findViewById(R.id.manually_add_titlebar);
        if (e()) {
            this.c.setRightButtonVisibility(0);
        } else {
            this.c.setRightButtonVisibility(8);
        }
        this.c.setRightButtonClickable(true);
        this.c.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.activity.ManuallyAddActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("activity", "ManuallyAddActivity");
                hashMap.put("add", 1);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MANUALLY_ADD_2041089.value(), hashMap, 0);
                String obj = ManuallyAddActivity.this.e.getText().toString();
                String obj2 = TextUtils.isEmpty(ManuallyAddActivity.this.d.getText().toString()) ? obj : ManuallyAddActivity.this.d.getText().toString();
                gmx gmxVar = new gmx();
                gmxVar.d(obj);
                gmxVar.b(obj);
                gmxVar.c(obj);
                gmxVar.d(false);
                gmxVar.a(obj2);
                Intent intent = new Intent();
                intent.putExtra("contactInfo", gmxVar);
                ManuallyAddActivity.this.setResult(1002, intent);
                ManuallyAddActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a() {
        this.e.addTextChangedListener(new TextWatcher() { // from class: com.huawei.featureuserprofile.sos.activity.ManuallyAddActivity.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                LogUtil.a("ManuallyAddActivity", "manuallyAddPhone not empty");
                if (ManuallyAddActivity.this.e()) {
                    ManuallyAddActivity.this.c.setRightButtonVisibility(0);
                } else {
                    ManuallyAddActivity.this.c.setRightButtonVisibility(8);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        return !TextUtils.isEmpty(this.e.getText());
    }

    private void uT_(ImageView imageView, int i) {
        if (LanguageUtil.bc(this)) {
            BitmapDrawable cKn_ = nrz.cKn_(this, i);
            if (cKn_ != null) {
                imageView.setImageDrawable(cKn_);
                return;
            }
            return;
        }
        imageView.setImageResource(i);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
