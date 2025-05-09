package com.huawei.pluginachievement.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dotspageindicator.HealthDotsPageIndicator;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.stories.nps.interactors.mode.TypeParams;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mcv;
import defpackage.mer;
import defpackage.mjh;
import defpackage.mlu;
import health.compact.a.LanguageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SportMedalDialog extends Activity implements HealthViewPager.OnPageChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthDotsPageIndicator f8415a;
    private HealthButton b;
    private ImageView c;
    private Context e;
    private mjh f;
    private int g;
    private ImageView i;
    private PluginAchieveAdapter j;
    private HealthViewPager k;
    private List<MedalInfoDesc> h = new ArrayList(10);
    private String d = "";

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("PLGACHIEVE_SportMedalDialog", "onCreate");
        mlu.clx_(this);
        setContentView(R.layout.layout_sport_medal_layout);
        this.e = this;
        b();
        a();
    }

    private void b() {
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.sport_medal_view_page);
        this.k = healthViewPager;
        healthViewPager.setIsScroll(false);
        this.f8415a = (HealthDotsPageIndicator) findViewById(R.id.view_medal_dots_page_indicator);
        this.b = (HealthButton) findViewById(R.id.sport_medal_button);
        this.c = (ImageView) findViewById(R.id.medal_arrow_left);
        this.i = (ImageView) findViewById(R.id.medal_arrow_right);
        if (LanguageUtil.bc(this.e)) {
            this.c.setImageResource(R.drawable._2131430853_res_0x7f0b0dc5);
            this.i.setImageResource(R.drawable._2131430852_res_0x7f0b0dc4);
        } else {
            this.c.setImageResource(R.drawable._2131430852_res_0x7f0b0dc4);
            this.i.setImageResource(R.drawable._2131430853_res_0x7f0b0dc5);
        }
        ((ImageView) findViewById(R.id.medal_close_dialog_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.SportMedalDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportMedalDialog.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.SportMedalDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SportMedalDialog.this.k != null && SportMedalDialog.this.g != 0 && koq.d(SportMedalDialog.this.h, SportMedalDialog.this.g - 1)) {
                    SportMedalDialog.this.k.setCurrentItem(SportMedalDialog.this.g - 1);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.SportMedalDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SportMedalDialog.this.k != null && SportMedalDialog.this.g < 5 && koq.d(SportMedalDialog.this.h, SportMedalDialog.this.g + 1)) {
                    SportMedalDialog.this.k.setCurrentItem(SportMedalDialog.this.g + 1);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void d() {
        char c;
        if (koq.b(this.h) || koq.b(this.h, this.g)) {
            return;
        }
        String acquireMedalType = this.h.get(this.g).acquireMedalType();
        acquireMedalType.hashCode();
        int hashCode = acquireMedalType.hashCode();
        if (hashCode == 2065) {
            if (acquireMedalType.equals("A2")) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode == 2068) {
            if (acquireMedalType.equals("A5")) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode == 2099) {
            if (acquireMedalType.equals("B5")) {
                c = '\t';
            }
            c = 65535;
        } else if (hashCode == 2101) {
            if (acquireMedalType.equals("B7")) {
                c = '\n';
            }
            c = 65535;
        } else if (hashCode != 2130) {
            switch (hashCode) {
                case 65:
                    if (acquireMedalType.equals("A")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 66:
                    if (acquireMedalType.equals("B")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 67:
                    if (acquireMedalType.equals(TypeParams.SEARCH_CODE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 68:
                    if (acquireMedalType.equals("D")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 2095:
                            if (acquireMedalType.equals("B1")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2096:
                            if (acquireMedalType.equals("B2")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2097:
                            if (acquireMedalType.equals("B3")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            switch (hashCode) {
                                case 2126:
                                    if (acquireMedalType.equals("C1")) {
                                        c = 11;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 2127:
                                    if (acquireMedalType.equals("C2")) {
                                        c = '\f';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 2128:
                                    if (acquireMedalType.equals("C3")) {
                                        c = '\r';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                    }
            }
        } else {
            if (acquireMedalType.equals("C5")) {
                c = 14;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 6:
            case 11:
                mer.b(this.e).b("huaweischeme://healthapp/track?sportType=2&targetType=m&targetValue=0");
                break;
            case 1:
            case 2:
            case 3:
            case 7:
                mer.b(this.e).b("huaweischeme://healthapp/track?sportType=1&targetType=m&targetValue=0");
                break;
            case 4:
            case '\b':
            case '\f':
                mer.b(this.e).b(KakaConstants.RIDE_URL);
                break;
            case 5:
            case '\n':
            case 14:
                mer.b(this.e).b("huaweischeme://healthapp/track?sportType=283&targetType=s&targetValue=0");
                break;
            case '\t':
            case '\r':
                PluginAchieveAdapter pluginAchieveAdapter = this.j;
                if (pluginAchieveAdapter == null) {
                    LogUtil.a("PLGACHIEVE_SportMedalDialog", "pluginAchieveAdapter is null");
                    return;
                } else {
                    pluginAchieveAdapter.gotoFitness();
                    break;
                }
            default:
                LogUtil.a("PLGACHIEVE_SportMedalDialog", "pluginAchieveAdapter is no branch.");
                break;
        }
        b(acquireMedalType);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void b(String str) {
        char c;
        String valueOf = String.valueOf(258);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 2065) {
            if (str.equals("A2")) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode == 2068) {
            if (str.equals("A5")) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode == 2099) {
            if (str.equals("B5")) {
                c = '\t';
            }
            c = 65535;
        } else if (hashCode == 2101) {
            if (str.equals("B7")) {
                c = '\n';
            }
            c = 65535;
        } else if (hashCode != 2130) {
            switch (hashCode) {
                case 65:
                    if (str.equals("A")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 66:
                    if (str.equals("B")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 67:
                    if (str.equals(TypeParams.SEARCH_CODE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 68:
                    if (str.equals("D")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 2095:
                            if (str.equals("B1")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2096:
                            if (str.equals("B2")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2097:
                            if (str.equals("B3")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            switch (hashCode) {
                                case 2126:
                                    if (str.equals("C1")) {
                                        c = 11;
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 2127:
                                    if (str.equals("C2")) {
                                        c = '\f';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 2128:
                                    if (str.equals("C3")) {
                                        c = '\r';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    c = 65535;
                                    break;
                            }
                    }
            }
        } else {
            if (str.equals("C5")) {
                c = 14;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 6:
            case 11:
                valueOf = String.valueOf(258);
                break;
            case 1:
            case 2:
            case 3:
            case 7:
                valueOf = String.valueOf(257);
                break;
            case 4:
            case '\b':
            case '\f':
                valueOf = String.valueOf(259);
                break;
            case 5:
            case '\n':
            case 14:
                valueOf = String.valueOf(283);
                break;
            case '\t':
            case '\r':
                valueOf = String.valueOf(10001);
                break;
            default:
                LogUtil.a("PLGACHIEVE_SportMedalDialog", "pluginAchieveAdapter is no branch.");
                break;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("from", this.d);
        hashMap.put("to", valueOf);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MEDAL_SPORT_2041092.value(), hashMap, 0);
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("PLGACHIEVE_SportMedalDialog", "getData intent is null");
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("sport_medal_record");
        if (serializableExtra instanceof List) {
            this.h = (List) serializableExtra;
        }
        this.d = intent.getStringExtra("medal_sport_type_bi_click");
        c();
    }

    private void c() {
        if (koq.b(this.h)) {
            finish();
            return;
        }
        if (this.h.size() == 1) {
            this.c.setVisibility(8);
            this.i.setVisibility(8);
        }
        this.k.addOnPageChangeListener(this);
        mjh mjhVar = new mjh(this.h);
        this.f = mjhVar;
        this.k.setAdapter(mjhVar);
        this.k.setOffscreenPageLimit(5);
        this.j = mcv.d(this.e).getAdapter();
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.SportMedalDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportMedalDialog.this.d();
                SportMedalDialog.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f8415a.setRtlEnable(LanguageUtil.bc(this.e));
        this.f8415a.setViewPager(this.k);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.g = i;
        if (i == 0) {
            this.c.setVisibility(8);
            this.i.setVisibility(0);
        } else if (i == this.h.size() - 1) {
            this.c.setVisibility(0);
            this.i.setVisibility(8);
        } else {
            this.c.setVisibility(0);
            this.i.setVisibility(0);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
