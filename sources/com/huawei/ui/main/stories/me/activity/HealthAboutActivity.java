package com.huawei.ui.main.stories.me.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.HealthAboutActivity;
import defpackage.bzs;
import defpackage.moh;
import defpackage.nsn;
import defpackage.rvq;
import defpackage.sds;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.IoUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class HealthAboutActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10334a;
    private LinearLayout b;
    private HealthTextView c;
    private String f;
    private HealthTextView i;
    private HealthTextView j;
    private int e = 0;
    private long d = 0;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_show_settings_about);
        b();
    }

    public void e() {
        this.b.setVisibility(0);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.f10334a.setImageResource(R.mipmap._2131820813_res_0x7f11010d);
        } else {
            this.f10334a.setImageResource(R.mipmap._2131820914_res_0x7f110172);
        }
        String[] strArr = {"GB", "CZ", "NL", "ES", "FI", "FR", "DE", "GR", "HU", "IT", "NO", "PL", "PT", "RO", "SK"};
        String[] strArr2 = {"0800 088 6700", "00420-239-018-465", "0800-9188", "0034-900 483 303", "00358-923138998", "0033-800 972 285", "0049-800 7788 6633", "0030-2111988000", "0036-80482934", "0039-800 191 435", "0047-21629090", "0048-800811110", "0800 100 066", "0040-800400896", "00421-268-622-626"};
        String[] strArr3 = {"en", "cs", "nl", "es", "fi", "fr", "de", "el", "hu", "it", "nb", "pl", "pt", "ro", "sk"};
        int[] iArr = {R$string.IDS_hw_city_name_GB, R$string.IDS_hw_city_name_CS, R$string.IDS_hw_city_name_NL, R$string.IDS_hw_city_name_ES, R$string.IDS_hw_city_name_FI, R$string.IDS_hw_city_name_FR, R$string.IDS_hw_city_name_DE, R$string.IDS_hw_city_name_GR, R$string.IDS_hw_city_name_HU, R$string.IDS_hw_city_name_IT, R$string.IDS_hw_city_name_NO, R$string.IDS_hw_city_name_PL, R$string.IDS_hw_city_name_PT, R$string.IDS_hw_city_name_RO, R$string.IDS_hw_city_name_SK};
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 15; i++) {
            hashMap.put(strArr[i], strArr2[i]);
        }
        HashMap hashMap2 = new HashMap();
        for (int i2 = 0; i2 < 15; i2++) {
            hashMap2.put(strArr[i2], Integer.valueOf(iArr[i2]));
        }
        HashMap hashMap3 = new HashMap();
        for (int i3 = 0; i3 < 15; i3++) {
            hashMap3.put(strArr[i3], strArr3[i3]);
        }
        d(hashMap3, hashMap, hashMap2);
    }

    private void d(Map<String, String> map, Map<String, String> map2, Map<String, Integer> map3) {
        String country = Locale.getDefault().getCountry();
        LogUtil.a("HealthAboutActivity", "setHotNumber localCountry = ", country);
        String x = CommonUtil.x();
        LogUtil.a("HealthAboutActivity", "setHotNumber current language : ", x);
        if (!x.equals(map.get(country))) {
            this.b.setVisibility(8);
        }
        if (map2.get(country) != null) {
            String str = map2.get(country);
            this.f = str;
            this.j.setText(str);
        } else {
            this.b.setVisibility(8);
        }
        if (map3.get(country) != null) {
            this.c.setText(map3.get(country).intValue());
        } else {
            this.b.setVisibility(8);
        }
        if (CommonUtil.cq()) {
            this.b.setVisibility(8);
        }
    }

    public void b() {
        this.f10334a = (ImageView) findViewById(R.id.hw_show_about_app_center_img);
        this.b = (LinearLayout) findViewById(R.id.hw_show_about_app_center);
        this.j = (HealthTextView) findViewById(R.id.hw_show_about_app_center_phone);
        this.c = (HealthTextView) findViewById(R.id.hw_show_about_app_center_city);
        this.i = (HealthTextView) findViewById(R.id.hw_show_about_app_version_number);
        dNt_((ImageView) findViewById(R.id.hw_show_about_app_pic));
        if (Utils.o()) {
            e();
        } else {
            this.b.setVisibility(8);
        }
        this.i.setOnClickListener(this);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.HealthAboutActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(HealthAboutActivity.this.f)) {
                    try {
                        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(KakaConstants.SCHEME_TEL + HealthAboutActivity.this.f));
                        intent.setFlags(268435456);
                        HealthAboutActivity.this.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        LogUtil.b("HealthAboutActivity", "initView onClick ActivityNotFoundException e = ", e.getMessage());
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.hw_copyrights);
        healthTextView.setText(sds.a());
        if (nsn.r()) {
            ((HealthTextView) findViewById(R.id.hw_show_about_app_name)).setTextSize(1, 30.0f);
            healthTextView.setTextSize(1, 26.0f);
            ((HealthTextView) findViewById(R.id.hw_show_about_app_center_hot_linear)).setTextSize(1, 26.0f);
            this.j.setTextSize(1, 26.0f);
            this.c.setTextSize(1, 26.0f);
        }
        d();
    }

    private void d() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.hw_show_about_record_number);
        if (Utils.o()) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setOnClickListener(new View.OnClickListener() { // from class: rfs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HealthAboutActivity.this.dNu_(view);
                }
            });
        }
    }

    public /* synthetic */ void dNu_(View view) {
        if (nsn.o()) {
            LogUtil.h("HealthAboutActivity", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            GRSManager.a(BaseApplication.getContext()).e("recordNumberQueryUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.me.activity.HealthAboutActivity.2
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str) {
                    LogUtil.a("HealthAboutActivity", "record number query host: ", str);
                    bzs.e().launchH5Compact(BaseApplication.getContext(), str, false);
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.h("HealthAboutActivity", "onCallBackFail: ", Integer.valueOf(i));
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void dNt_(ImageView imageView) {
        if (CommonUtil.as()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (CommonUtil.b() > currentTimeMillis || currentTimeMillis > CommonUtil.e()) {
                return;
            }
            imageView.setImageResource(getResources().getIdentifier("healthlogo_hw_app_beta", "mipmap", BaseApplication.getAppPackage()));
            return;
        }
        if (CommonUtil.bu()) {
            Bitmap dNs_ = dNs_("healthbasic", "healthlogo_ic_about_app_icon_demo.webp");
            if (dNs_ == null) {
                imageView.setImageResource(R.drawable._2131428464_res_0x7f0b0470);
                return;
            } else {
                imageView.setImageBitmap(dNs_);
                return;
            }
        }
        LogUtil.h("HealthAboutActivity", "isShowBetaLogo is other version");
    }

    private void c() {
        SpannableString spannableString;
        String str;
        SpannableString spannableString2;
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.hw_show_about_app_version_number);
        healthTextView.setText(String.format(Locale.ENGLISH, getString(R$string.IDS_hw_show_version_name), CommonUtil.e(getApplicationContext())));
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.hw_show_about_service_and_policy);
        if (Utils.i()) {
            String string = getString(R$string.IDS_hwh_about_privacy_notice);
            String string2 = getResources().getString(R$string.IDS_hwh_me_about_privacy_agreement, getString(R$string.IDS_hw_show_setting_about_service_item), getString(R$string.IDS_hw_about_service_and), string);
            SpannableString spannableString3 = new SpannableString(string2);
            int length = getString(R$string.IDS_hw_show_setting_about_service_item).length();
            rvq rvqVar = new rvq(this, "HealthUserAgreement");
            if (spannableString3.length() >= length) {
                spannableString3.setSpan(rvqVar, 0, length, 17);
                spannableString3.setSpan(new TypefaceSpan(Constants.FONT), 0, length, 33);
                str = "HealthAboutActivity";
                spannableString2 = spannableString3;
            } else {
                str = "HealthAboutActivity";
                spannableString2 = spannableString3;
                LogUtil.h(str, "setPrivacy spanString:", spannableString3, ", spanString.length():", Integer.valueOf(spannableString3.length()), ", firstPosition:", Integer.valueOf(length));
            }
            int length2 = string2.length() - string.length();
            if (spannableString2.length() > length2) {
                spannableString2.setSpan(new rvq(this, "HealthPrivacy"), length2, spannableString2.toString().length(), 18);
                spannableString2.setSpan(new TypefaceSpan(Constants.FONT), length2, spannableString2.toString().length(), 33);
            } else {
                LogUtil.h(str, "setPrivacy spanString is ", spannableString2, ", spanString.length() is ", Integer.valueOf(spannableString2.length()), ", firstPosition is ", Integer.valueOf(length2));
            }
            spannableString = spannableString2;
        } else {
            spannableString = new SpannableString(getResources().getString(R$string.IDS_hw_show_setting_about_service_item));
            int length3 = getString(R$string.IDS_hw_show_setting_about_service_item).length();
            spannableString.setSpan(new rvq(this, "HealthUserAgreement"), 0, length3, 17);
            spannableString.setSpan(new TypefaceSpan(Constants.FONT), 0, length3, 33);
        }
        healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView2.setHighlightColor(getResources().getColor(android.R.color.transparent));
        healthTextView2.setText(spannableString);
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
            healthTextView2.setTextSize(1, 26.0f);
        }
    }

    private Bitmap dNs_(String str, String str2) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        Bitmap bitmap = null;
        try {
            inputStream = moh.e(str, str2);
            try {
                try {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (Exception unused) {
                    LogUtil.b("HealthAboutActivity", "getBitmap catch Exception");
                    IoUtils.e(inputStream);
                    return bitmap;
                }
            } catch (Throwable th) {
                inputStream2 = inputStream;
                th = th;
                IoUtils.e(inputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(inputStream2);
            throw th;
        }
        IoUtils.e(inputStream);
        return bitmap;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.i) {
            if (this.e == 0) {
                this.d = System.currentTimeMillis();
            }
            int i = this.e;
            if (i < 6) {
                this.e = i + 1;
                this.d = System.currentTimeMillis();
            } else {
                this.e = 0;
                StorageParams storageParams = new StorageParams();
                if (System.currentTimeMillis() - this.d <= ProfileExtendConstants.TIME_OUT) {
                    SharedPreferenceManager.e(getApplicationContext(), "developeroptions", "developerswitch", "1", storageParams);
                }
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
