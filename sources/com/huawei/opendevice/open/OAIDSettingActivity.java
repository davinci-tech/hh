package com.huawei.opendevice.open;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.analysis.h;
import com.huawei.openalliance.ad.beans.metadata.OaidRecord;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.m;
import com.huawei.openalliance.ad.utils.x;
import com.huawei.openalliance.ad.ve;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import defpackage.lss;
import java.util.Locale;

/* loaded from: classes9.dex */
public class OAIDSettingActivity extends BaseSettingActivity {
    private ve c;
    private TextView e;
    private gc g;

    /* renamed from: a, reason: collision with root package name */
    private Switch f8210a = null;
    private TextView d = null;
    private TextView b = null;
    private boolean j = false;
    private boolean f = false;

    public static boolean d() {
        return true;
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onResume() {
        super.onResume();
        ve veVar = this.c;
        if (veVar != null) {
            veVar.a(false);
        }
        m.a(new Runnable() { // from class: com.huawei.opendevice.open.OAIDSettingActivity.4
            @Override // java.lang.Runnable
            public void run() {
                final boolean equals = "1".equals(OAIDSettingActivity.this.g.bF());
                dj.a(new Runnable() { // from class: com.huawei.opendevice.open.OAIDSettingActivity.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        OAIDSettingActivity.this.f8210a.setChecked(equals);
                        OAIDSettingActivity.this.c.a(true);
                    }
                });
            }
        });
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            super.onBackPressed();
            ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
            return true;
        }
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        ViewClickInstrumentation.clickOnMenuItem(this, menuItem);
        return onOptionsItemSelected;
    }

    @Override // com.huawei.opendevice.open.BaseSettingActivity, com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        StringBuilder sb;
        super.onCreate(bundle);
        this.f = bz.a(this).a();
        boolean b = bz.b(this);
        this.j = b;
        ho.b("OAIDSettingActivity", "onCreate, isInnerDevice: %s, isChina: %s", Boolean.valueOf(b), Boolean.valueOf(this.f));
        if (this.j && ao.j(this)) {
            ao.b(this, Constants.OAID_SETTING_URL);
        } else {
            if (this.f) {
                try {
                    dd.b(this, 1);
                    this.g = fh.b(this);
                    e();
                    return;
                } catch (RuntimeException e) {
                    e = e;
                    sb = new StringBuilder("onCreate ");
                    sb.append(e.getClass().getSimpleName());
                    ho.c("OAIDSettingActivity", sb.toString());
                    return;
                } catch (Throwable th) {
                    e = th;
                    sb = new StringBuilder("onCreate ex: ");
                    sb.append(e.getClass().getSimpleName());
                    ho.c("OAIDSettingActivity", sb.toString());
                    return;
                }
            }
            ao.k(this);
        }
        finish();
    }

    @Override // com.huawei.opendevice.open.BaseSettingActivity, com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.opendevice.open.BaseSettingActivity, com.huawei.openalliance.ad.activity.e
    public void a() {
        setContentView(R.layout.opendevice_oaid_setting);
        this.p = (ViewGroup) findViewById(R.id.ll_content_root);
    }

    private void e() {
        ActionBar actionBar = getActionBar();
        boolean c = x.c();
        if (actionBar != null) {
            if (d()) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            if (c || !this.j) {
                actionBar.setTitle(R.string.opendevice_hw_ad_service_new);
            } else {
                actionBar.setTitle(R.string.opendevice_title_oaid);
            }
        }
        if (c || !this.j) {
            setTitle(R.string.opendevice_hw_ad_service_new);
        } else {
            setTitle(R.string.opendevice_title_oaid);
        }
        this.e = (TextView) findViewById(R.id.opendevice_all_advertisers_tv);
        String string = getString(R.string.opendevice_all_advertisers);
        if (!TextUtils.isEmpty(string)) {
            this.e.setText(string.toUpperCase(Locale.getDefault()));
        }
        Switch r0 = (Switch) findViewById(R.id.opendevice_limit_tracking_switch);
        this.f8210a = r0;
        if (!this.j) {
            r0.setTrackDrawable(getResources().getDrawable(R.drawable._2131428590_res_0x7f0b04ee));
        }
        ve veVar = new ve(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.opendevice.open.OAIDSettingActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                OAIDSettingActivity.this.d(z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        this.c = veVar;
        this.f8210a.setOnCheckedChangeListener(veVar);
        this.d = (TextView) findViewById(R.id.opendevice_limit_tracking_tv);
        this.b = (TextView) findViewById(R.id.opendevice_limit_tracking_desc_tv);
        this.d.setText(R.string.opendevice_limit_ad_tracking);
        this.b.setText(getString(R.string.opendevice_item_reset_ad_des_new));
        TextView textView = (TextView) findViewById(R.id.opendevice_oaid_privacy_tv);
        textView.setVisibility(0);
        try {
            int indexOf = getString(R.string.opendevice_privacy_desc).indexOf("%1$s");
            int color = getResources().getColor(R.color._2131297934_res_0x7f09068e);
            String string2 = getString(R.string.opendevice_ad_privacy_statement);
            SpannableString spannableString = new SpannableString(getString(R.string.opendevice_privacy_desc, new Object[]{string2}));
            if (indexOf >= 0) {
                b bVar = new b(this);
                bVar.e(PrivacyActivity.class);
                spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf, string2.length() + indexOf, 33);
                spannableString.setSpan(bVar, indexOf, string2.length() + indexOf, 33);
                spannableString.setSpan(new ForegroundColorSpan(color), indexOf, string2.length() + indexOf, 33);
            }
            textView.setText(spannableString);
            textView.setMovementMethod(new lss(color, color));
        } catch (Resources.NotFoundException unused) {
            ho.d("OAIDSettingActivity", "getResources NotFoundException");
        }
        e(HealthZonePushReceiver.COMMENT_FAILED_NOTIFY, OaidRecord.OPEN_OAID_SETTING_KEY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        ho.b("OAIDSettingActivity", "handleAnonymousIdStatusChange, isChecked: %s", Boolean.valueOf(z));
        if (this.g == null) {
            this.g = fh.b(this);
        }
        this.g.d(z);
        e(z ? "51" : HealthZonePushReceiver.DATA_POST_COMMENTS_NOTIFY, z ? OaidRecord.LIMIT_OAID_OPEN_KEY : OaidRecord.LIMIT_OAID_CLOSE_KEY);
    }

    private void e(String str, String str2) {
        a(this, str, str2);
    }

    public static void a(Context context, String str, String str2) {
        new h(context).a(str, str2);
    }
}
