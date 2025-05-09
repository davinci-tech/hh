package com.huawei.ui.homehealth.runcard;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nsn;
import defpackage.owp;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SportAssistBaseVoiceSettingsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int[] f9514a;
    private CustomViewDialog b;
    private CustomTitleBar d;
    private int[] k;
    private HealthSwitchButton l;
    private RelativeLayout m;
    private HealthSwitchButton r;
    private HealthSwitchButton s;
    private HealthSwitchButton t;
    private boolean g = true;
    private boolean h = true;
    private boolean f = true;
    private boolean j = true;
    private RelativeLayout n = null;
    private HealthTextView o = null;
    private ImageView i = null;
    private List<String> e = new ArrayList(10);
    private Map<String, ArrayList<String>> c = new HashMap(10);

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i) {
        return i == 0 ? 2 : 1;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "onCreate");
        setContentView(R.layout.track_sport_base_voice_settings_frag);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_track_sport_base_voice_setting);
        this.d = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportAssistBaseVoiceSettingsActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c();
        e();
    }

    private void c() {
        i();
        b();
    }

    private void e() {
        f();
        a();
    }

    private void i() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout_track_voice_interval_setting);
        this.n = relativeLayout;
        BaseActivity.cancelLayoutById(relativeLayout);
        this.n.setOnClickListener(this);
        this.o = (HealthTextView) findViewById(R.id.txt_track_voice_interval_value);
        this.i = (ImageView) findViewById(R.id.img_track_voice_interval_value);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.i.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.i.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void f() {
        this.f9514a = new int[]{500, 1000, 2000, 3000};
        this.k = new int[]{5, 10, 15, 20};
        d();
        int d = owp.d(BaseApplication.getContext());
        if (d == 0) {
            owp.i(BaseApplication.getContext(), 2);
            owp.c(BaseApplication.getContext(), 1000);
            c(2, 1);
            return;
        }
        c(owp.d(BaseApplication.getContext()), c(d));
    }

    private void b() {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.switch_track_voice_setting_distance);
        this.l = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportAssistBaseVoiceSettingsActivity.this.g = z;
                SportAssistBaseVoiceSettingsActivity.this.l.setChecked(SportAssistBaseVoiceSettingsActivity.this.g);
                SportAssistBaseVoiceSettingsActivity sportAssistBaseVoiceSettingsActivity = SportAssistBaseVoiceSettingsActivity.this;
                sportAssistBaseVoiceSettingsActivity.d("voice_distance", sportAssistBaseVoiceSettingsActivity.g);
                LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "mIsVoiceDistanceEnable is ", Boolean.valueOf(SportAssistBaseVoiceSettingsActivity.this.g));
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        HealthSwitchButton healthSwitchButton2 = (HealthSwitchButton) findViewById(R.id.switch_track_voice_setting_duration);
        this.r = healthSwitchButton2;
        healthSwitchButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportAssistBaseVoiceSettingsActivity.this.h = z;
                SportAssistBaseVoiceSettingsActivity.this.r.setChecked(SportAssistBaseVoiceSettingsActivity.this.h);
                SportAssistBaseVoiceSettingsActivity sportAssistBaseVoiceSettingsActivity = SportAssistBaseVoiceSettingsActivity.this;
                sportAssistBaseVoiceSettingsActivity.d("voice_speed_time", sportAssistBaseVoiceSettingsActivity.h);
                LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "mIsVoiceDurationEnable is ", Boolean.valueOf(SportAssistBaseVoiceSettingsActivity.this.h));
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        this.t = (HealthSwitchButton) findViewById(R.id.switch_track_voice_setting_pace);
        this.m = (RelativeLayout) findViewById(R.id.switch_track_voice_setting_pace_layout);
        this.t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportAssistBaseVoiceSettingsActivity.this.f = z;
                SportAssistBaseVoiceSettingsActivity.this.t.setChecked(SportAssistBaseVoiceSettingsActivity.this.f);
                SportAssistBaseVoiceSettingsActivity sportAssistBaseVoiceSettingsActivity = SportAssistBaseVoiceSettingsActivity.this;
                sportAssistBaseVoiceSettingsActivity.d("voice_pace", sportAssistBaseVoiceSettingsActivity.f);
                LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "mIsVoicePaceEnable is ", Boolean.valueOf(SportAssistBaseVoiceSettingsActivity.this.f));
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        HealthSwitchButton healthSwitchButton3 = (HealthSwitchButton) findViewById(R.id.switch_track_voice_setting_heart_rate);
        this.s = healthSwitchButton3;
        healthSwitchButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportAssistBaseVoiceSettingsActivity.this.j = z;
                SportAssistBaseVoiceSettingsActivity.this.s.setChecked(SportAssistBaseVoiceSettingsActivity.this.j);
                SportAssistBaseVoiceSettingsActivity sportAssistBaseVoiceSettingsActivity = SportAssistBaseVoiceSettingsActivity.this;
                sportAssistBaseVoiceSettingsActivity.d("voice_heart_rate", sportAssistBaseVoiceSettingsActivity.j);
                LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "mIsVoiceHeartRateEnable is ", Boolean.valueOf(SportAssistBaseVoiceSettingsActivity.this.j));
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void a() {
        this.g = b("voice_distance");
        this.h = b("voice_speed_time");
        this.f = b("voice_pace");
        this.j = b("voice_heart_rate");
        this.l.setChecked(this.g);
        this.r.setChecked(this.h);
        this.t.setChecked(this.f);
        this.s.setChecked(this.j);
        LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "initVoiceContentData , mIsVoiceDistanceEnable is ", Boolean.valueOf(this.g), " , mIsVoiceDurationEnable is ", Boolean.valueOf(this.h), " , mIsVoicePaceEnable is ", Boolean.valueOf(this.f), " , mIsVoiceHeartRateEnable is ", Boolean.valueOf(this.j));
    }

    private void g() {
        int d = owp.d(BaseApplication.getContext());
        int i = 0;
        int i2 = 1;
        if (d == 1) {
            int i3 = owp.i(BaseApplication.getContext());
            while (true) {
                int[] iArr = this.k;
                if (i >= iArr.length) {
                    i = 1;
                    break;
                } else if (i3 == iArr[i]) {
                    break;
                } else {
                    i++;
                }
            }
            i2 = i;
            i = 1;
        } else if (d == 2) {
            int a2 = owp.a(BaseApplication.getContext());
            int i4 = 0;
            while (true) {
                int[] iArr2 = this.f9514a;
                if (i4 >= iArr2.length) {
                    break;
                }
                if (a2 == iArr2[i4]) {
                    i2 = i4;
                    break;
                }
                i4++;
            }
        }
        b(i, i2);
    }

    private void b(int i, int i2) {
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, false});
        List<String> list = this.e;
        healthMultiNumberPicker.setDisplayedValues(0, (String[]) list.toArray(new String[list.size()]), 0);
        if (koq.b(this.e, i)) {
            LogUtil.h("Track_SportAssistBaseVoiceSettingsActivity", "buildTrackVoiceIntervalSettingDialog firstLocation is out of mDataKey");
            return;
        }
        ArrayList<String> arrayList = this.c.get(this.e.get(i));
        healthMultiNumberPicker.setDisplayedValues(1, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        healthMultiNumberPicker.a(new int[]{i, i2}, arrayList.size());
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.8
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i3, HealthMultiNumberPicker healthMultiNumberPicker2, int i4, int i5) {
                if (i3 == 0) {
                    if (i5 < SportAssistBaseVoiceSettingsActivity.this.e.size() && SportAssistBaseVoiceSettingsActivity.this.c.containsKey(SportAssistBaseVoiceSettingsActivity.this.e.get(i5))) {
                        ArrayList arrayList2 = (ArrayList) SportAssistBaseVoiceSettingsActivity.this.c.get(SportAssistBaseVoiceSettingsActivity.this.e.get(i5));
                        healthMultiNumberPicker2.setDisplayedValues(1, (String[]) arrayList2.toArray(new String[arrayList2.size()]), 1);
                    } else {
                        LogUtil.h("Track_SportAssistBaseVoiceSettingsActivity", "the voice type is not valid");
                    }
                }
            }
        });
        final CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.d(R.string._2130842146_res_0x7f021222).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                SportAssistBaseVoiceSettingsActivity.this.c(SportAssistBaseVoiceSettingsActivity.this.e(selectedLocations[0]), selectedLocations[1]);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistBaseVoiceSettingsActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                builder.b(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.b = e;
        e.show();
    }

    private void d() {
        this.e.add(getResources().getString(R.string._2130842147_res_0x7f021223));
        this.e.add(getResources().getString(R.string._2130842148_res_0x7f021224));
        ArrayList<String> arrayList = new ArrayList<>(10);
        ArrayList<String> arrayList2 = new ArrayList<>(10);
        try {
            int[] iArr = this.k;
            if (iArr != null && iArr.length > 0) {
                for (int i : iArr) {
                    arrayList.add(getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(i, 1, 0)));
                }
            }
            int[] iArr2 = this.f9514a;
            if (iArr2 != null && iArr2.length > 0) {
                for (int i2 = 0; i2 < this.f9514a.length; i2++) {
                    if (i2 == 0) {
                        arrayList2.add(getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(this.f9514a[i2] / 1000.0d, 1, 1)));
                    } else {
                        arrayList2.add(getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(this.f9514a[i2] / 1000.0d, 1, 0)));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            LogUtil.h("Track_SportAssistBaseVoiceSettingsActivity", "IndexOutOfBoundsException ", e.getMessage());
        }
        this.c.put(this.e.get(0), arrayList2);
        this.c.put(this.e.get(1), arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2) {
        owp.i(BaseApplication.getContext(), i);
        try {
            if (i == 1) {
                int[] iArr = this.k;
                if (iArr != null) {
                    if (iArr.length > i2) {
                        this.o.setText(this.c.get(this.e.get(1)).get(i2));
                    }
                    if (i2 < this.k.length && i2 >= 0) {
                        owp.g(BaseApplication.getContext(), this.k[i2]);
                    }
                    owp.g(BaseApplication.getContext(), this.k[0]);
                }
                this.m.setVisibility(8);
                return;
            }
            int[] iArr2 = this.f9514a;
            if (iArr2 != null) {
                if (iArr2.length > i2) {
                    this.o.setText(this.c.get(this.e.get(0)).get(i2));
                }
                if (i2 < this.f9514a.length && i2 >= 0) {
                    owp.c(BaseApplication.getContext(), this.f9514a[i2]);
                }
                owp.c(BaseApplication.getContext(), this.f9514a[0]);
            }
            this.m.setVisibility(0);
        } catch (IndexOutOfBoundsException e) {
            LogUtil.h("Track_SportAssistBaseVoiceSettingsActivity", "updateVoiceInterval IndexOutOfBoundsException ", e.getMessage());
        }
    }

    private int c(int i) {
        int i2 = 0;
        if (i == 2) {
            int a2 = owp.a(BaseApplication.getContext());
            while (true) {
                int[] iArr = this.f9514a;
                if (i2 >= iArr.length) {
                    break;
                }
                if (a2 == iArr[i2]) {
                    return i2;
                }
                i2++;
            }
        } else if (i == 1) {
            int i3 = owp.i(BaseApplication.getContext());
            while (true) {
                int[] iArr2 = this.k;
                if (i2 >= iArr2.length) {
                    break;
                }
                if (i3 == iArr2[i2]) {
                    return i2;
                }
                i2++;
            }
        }
        return 1;
    }

    private boolean b(String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), str);
        if (TextUtils.isEmpty(b)) {
            LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "TextUtils.isEmpty(sharedPreference)");
            return true;
        }
        try {
            return Integer.parseInt(b) == 1;
        } catch (NumberFormatException unused) {
            LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "sharedPreference NumberFormatException");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z) {
        String num;
        if (z) {
            num = Integer.toString(1);
        } else {
            num = Integer.toString(0);
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), str, num, new StorageParams());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("Track_SportAssistBaseVoiceSettingsActivity", "onClick view == null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(600)) {
            LogUtil.b("Track_SportAssistBaseVoiceSettingsActivity", "onClick is Fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        if (view.getId() == R.id.layout_track_voice_interval_setting) {
            g();
            hashMap.put("type", 2);
        }
        ixx.d().d(this, AnalyticsValue.MOTION_TRACK_1040023.value(), hashMap, 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
