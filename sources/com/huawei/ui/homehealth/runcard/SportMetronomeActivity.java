package com.huawei.ui.homehealth.runcard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homehealth.runcard.SportMetronomeActivity;
import defpackage.gtx;
import defpackage.gww;
import defpackage.hpg;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nsn;
import defpackage.qrp;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.ToIntFunction;

/* loaded from: classes6.dex */
public class SportMetronomeActivity extends BaseActivity implements View.OnClickListener {
    private HealthTextView ab;
    private hpg ac;
    private HealthSwitchButton ad;
    private Context b;
    private HealthCardView d;
    private HealthCardView e;
    private boolean g;
    private boolean h;
    private int i;
    private CustomTitleBar j;
    private ConstraintLayout k;
    private ConstraintLayout l;
    private LinearLayout m;
    private int n;
    private LinearLayout o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthImageView r;
    private HealthTextView s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthImageView w;
    private HealthSwitchButton x;
    private int y;
    private static final int[] c = a();

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f9523a = {R.string._2130846590_res_0x7f02237e, R.string._2130846591_res_0x7f02237f, R.string._2130846592_res_0x7f022380, R.string._2130846593_res_0x7f022381};
    private SportBeat aa = new SportBeat(false, 170, 0);
    private boolean f = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sport_metronome);
        b();
        this.b = BaseApplication.e();
        j();
        d();
        i();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            this.n = intent.getIntExtra("jump_source", 0);
            this.g = intent.getBooleanExtra("is_support_metronome", true);
        }
    }

    private void j() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ctb_metronome_title);
        this.j = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.d = (HealthCardView) findViewById(R.id.card_metronome);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_metronome_switch);
        this.m = linearLayout;
        this.v = (HealthTextView) linearLayout.findViewById(R.id.reminder_switch_title);
        this.s = (HealthTextView) this.m.findViewById(R.id.reminder_switch_description);
        this.x = (HealthSwitchButton) this.m.findViewById(R.id.reminder_switch_btn);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.layout_metronome_frequency);
        this.k = constraintLayout;
        this.t = (HealthTextView) constraintLayout.findViewById(R.id.htv_item_left_text);
        this.q = (HealthTextView) this.k.findViewById(R.id.htv_item_right_text);
        this.r = (HealthImageView) this.k.findViewById(R.id.hiv_right_arrow);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(R.id.layout_metronome_sound_effects);
        this.l = constraintLayout2;
        this.p = (HealthTextView) constraintLayout2.findViewById(R.id.htv_item_left_text);
        this.u = (HealthTextView) this.l.findViewById(R.id.htv_item_right_text);
        this.w = (HealthImageView) this.l.findViewById(R.id.hiv_right_arrow);
        this.e = (HealthCardView) findViewById(R.id.card_voice_broadcast);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layout_voice_broadcast);
        this.o = linearLayout2;
        this.ab = (HealthTextView) linearLayout2.findViewById(R.id.voice_switch_title);
        this.ad = (HealthSwitchButton) this.o.findViewById(R.id.voice_switch_btn);
        f();
    }

    private void f() {
        if (LanguageUtil.bc(this.b)) {
            this.r.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.w.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.r.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            this.w.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void d() {
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.x.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportMetronomeActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportMetronomeActivity.this.dfl_(compoundButton, z);
            }
        });
        this.ad.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportMetronomeActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportMetronomeActivity.this.dfm_(compoundButton, z);
            }
        });
        this.j.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: opp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportMetronomeActivity.this.dfn_(view);
            }
        });
    }

    /* synthetic */ void dfl_(CompoundButton compoundButton, boolean z) {
        d(z);
        this.h = z;
        if (!this.f) {
            LogUtil.a("Track_SportMetronomeActivity", "mMetronomeSwitch is not click event");
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            o();
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    /* synthetic */ void dfm_(CompoundButton compoundButton, boolean z) {
        e(z);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ void dfn_(View view) {
        k();
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        k();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0) {
            k();
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void k() {
        if (this.aa != null) {
            Intent intent = new Intent();
            intent.putExtra("sportBeatData", this.aa);
            setResult(-1, intent);
        }
    }

    private void i() {
        if (this.n == 1 || UnitUtil.h()) {
            this.j.setTitleText(getString(R.string._2130846586_res_0x7f02237a));
            this.e.setVisibility(8);
        } else {
            this.j.setTitleText(getString(R.string._2130842051_res_0x7f0211c3));
            this.e.setVisibility(0);
            this.ab.setText(getString(R.string._2130842052_res_0x7f0211c4));
            this.ad.setChecked(gww.e() == 1);
        }
        if (!this.g) {
            LogUtil.a("Track_SportMetronomeActivity", "initData, not support metronome");
            this.d.setVisibility(8);
        } else {
            SharedPreferenceManager.e(Integer.toString(20002), "metronome_page_show", true);
            b(new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        runOnUiThread(new Runnable() { // from class: opw
            @Override // java.lang.Runnable
            public final void run() {
                SportMetronomeActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        this.v.setText(getString(R.string._2130846586_res_0x7f02237a));
        this.s.setText(getString(R.string._2130846587_res_0x7f02237b));
        boolean isOpen = this.aa.isOpen();
        this.h = isOpen;
        this.x.setChecked(isOpen);
        d(this.h);
        this.t.setText(getString(R.string._2130846588_res_0x7f02237c));
        int frequency = this.aa.getFrequency();
        this.i = frequency;
        if (frequency < 10 || frequency > 240) {
            LogUtil.a("Track_SportMetronomeActivity", "initData, frequencyValue is ", Integer.valueOf(frequency));
            this.i = 170;
        }
        this.q.setText(getResources().getQuantityString(R.plurals._2130903308_res_0x7f03010c, this.i, b(this.i)));
        this.p.setText(getString(R.string._2130846589_res_0x7f02237d));
        int audio = this.aa.getAudio();
        this.y = audio;
        if (audio < 0 || audio > f9523a.length) {
            LogUtil.a("Track_SportMetronomeActivity", "initData, soundEffectsValue is ", Integer.valueOf(audio));
            this.y = 0;
        }
        this.u.setText(getString(f9523a[this.y]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        h();
    }

    private void o() {
        this.aa.setIsOpen(this.h);
        this.aa.setFrequency(this.i);
        this.aa.setAudio(this.y);
        n();
    }

    private void n() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: opx
            @Override // java.lang.Runnable
            public final void run() {
                SportMetronomeActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        String e = HiJsonUtil.e(this.aa);
        ReleaseLogUtil.e("R_Track_SportMetronomeActivity", "saveSportBeatToDb, configId is ", "900200020", ", configData is ", e);
        qrp.b("9002", "900200020", e, new a());
    }

    public static void b(final HiDataReadResultListener hiDataReadResultListener) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: oqc
            @Override // java.lang.Runnable
            public final void run() {
                qrp.e("9002", "900200020", HiDataReadResultListener.this);
            }
        });
    }

    private void d(boolean z) {
        if (z) {
            this.t.setAlpha(1.0f);
            this.q.setAlpha(1.0f);
            this.r.setAlpha(1.0f);
            this.k.setClickable(true);
            this.p.setAlpha(1.0f);
            this.u.setAlpha(1.0f);
            this.w.setAlpha(1.0f);
            this.l.setClickable(true);
            return;
        }
        this.t.setAlpha(0.4f);
        this.q.setAlpha(0.4f);
        this.r.setAlpha(0.4f);
        this.k.setClickable(false);
        this.p.setAlpha(0.4f);
        this.u.setAlpha(0.4f);
        this.w.setAlpha(0.4f);
        this.l.setClickable(false);
    }

    private void e(boolean z) {
        gtx.c(BaseApplication.e()).j(z);
        HashMap hashMap = new HashMap(16);
        if (z) {
            hashMap.put("click", 1);
        } else {
            hashMap.put("click", 0);
        }
        ixx.d().d(this.b, AnalyticsValue.MOTION_TRACK_1040023.value(), hashMap, 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h("Track_SportMetronomeActivity", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.k) {
            LogUtil.a("Track_SportMetronomeActivity", "onClick click view mLayoutMetronomeFrequency");
            a(1);
        } else if (view == this.l) {
            LogUtil.a("Track_SportMetronomeActivity", "onClick click view mLayoutMetronomeSoundEffects");
            a(2);
            g();
        } else {
            LogUtil.h("Track_SportMetronomeActivity", "onClick click view not find");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        hpg hpgVar = this.ac;
        if (hpgVar != null) {
            hpgVar.d();
            this.ac = null;
        }
    }

    private void a(final int i) {
        final HealthNumberPicker healthNumberPicker = new HealthNumberPicker(this);
        final String[] e = e(i);
        int d = d(i);
        healthNumberPicker.setDisplayedValues(e);
        healthNumberPicker.setMaxValue(e.length - 1);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setValue(d);
        healthNumberPicker.setWrapSelectorWheel(true);
        healthNumberPicker.setSelectionDivider(ContextCompat.getDrawable(this, R.drawable._2131430934_res_0x7f0b0e16));
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.topMargin = this.b.getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
        healthNumberPicker.setLayoutParams(marginLayoutParams);
        if (i == 2) {
            healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: opy
                @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
                public final void onValuePicked(int i2, int i3) {
                    SportMetronomeActivity.this.a(i2, i3);
                }
            });
        }
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setOrientation(1);
        linearLayout.addView(healthNumberPicker);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.a("").cyp_(linearLayout).cyo_(R.string._2130837648_res_0x7f020090, new DialogInterface.OnClickListener() { // from class: opv
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SportMetronomeActivity.this.dfo_(healthNumberPicker, i, e, dialogInterface, i2);
            }
        }).cyn_(R.string._2130845098_res_0x7f021daa, new DialogInterface.OnClickListener() { // from class: opu
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SportMetronomeActivity.this.dfp_(i, dialogInterface, i2);
            }
        });
        CustomAlertDialog c2 = builder.c();
        c2.setCancelable(false);
        c2.show();
    }

    public /* synthetic */ void a(int i, int i2) {
        LogUtil.a("Track_SportMetronomeActivity", "newValue is ", Integer.valueOf(i2));
        if (i != i2) {
            c(i2);
        }
    }

    public /* synthetic */ void dfo_(HealthNumberPicker healthNumberPicker, int i, String[] strArr, DialogInterface dialogInterface, int i2) {
        int value = healthNumberPicker.getValue();
        LogUtil.a("Track_SportMetronomeActivity", "showPickerView click positive button, index is ", Integer.valueOf(value));
        if (i == 2 && this.ac != null) {
            LogUtil.a("Track_SportMetronomeActivity", "mSportBeatPlayer stopBeat");
            this.ac.i();
        }
        if (value < 0 || value > strArr.length - 1) {
            LogUtil.b("Track_SportMetronomeActivity", "showPickerView index out of bounds");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
        } else {
            b(i, value);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
        }
    }

    public /* synthetic */ void dfp_(int i, DialogInterface dialogInterface, int i2) {
        LogUtil.a("Track_SportMetronomeActivity", "showPickerView click negative button");
        if (i == 2 && this.ac != null) {
            LogUtil.a("Track_SportMetronomeActivity", "mSportBeatPlayer stopBeat");
            this.ac.i();
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
    }

    private void g() {
        if (this.ac == null) {
            hpg hpgVar = new hpg(new SportBeat(true, this.i, this.y));
            this.ac = hpgVar;
            hpgVar.j();
        } else {
            this.ac.a(new SportBeat(true, this.i, this.y));
            this.ac.f();
        }
    }

    private void c(int i) {
        this.ac.h();
        this.ac.a(new SportBeat(true, this.i, i));
        this.ac.f();
    }

    private void b(int i, int i2) {
        if (i == 1) {
            int i3 = i2 + 10;
            this.i = i3;
            this.q.setText(getResources().getQuantityString(R.plurals._2130903308_res_0x7f03010c, this.i, b(i3)));
        } else {
            this.y = i2;
            this.u.setText(getString(f9523a[i2]));
        }
        o();
    }

    private String[] e(int i) {
        int i2 = 0;
        if (i == 1) {
            int length = c.length;
            String[] strArr = new String[length];
            while (i2 < length) {
                Resources resources = getResources();
                int i3 = c[i2];
                strArr[i2] = resources.getQuantityString(R.plurals._2130903308_res_0x7f03010c, i3, b(i3));
                i2++;
            }
            return strArr;
        }
        String[] strArr2 = new String[f9523a.length];
        while (true) {
            int[] iArr = f9523a;
            if (i2 >= iArr.length) {
                return strArr2;
            }
            strArr2[i2] = getString(iArr[i2]);
            i2++;
        }
    }

    private int d(int i) {
        if (i == 1) {
            return this.i - 10;
        }
        if (i == 2) {
            return this.y;
        }
        LogUtil.h("Track_SportMetronomeActivity", "getDefaultPosition, pickerType is ", Integer.valueOf(i));
        return 0;
    }

    private String b(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    private static int[] a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 10; i <= 240; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        int[] iArr = new int[arrayList.size()];
        return arrayList.stream().mapToInt(new ToIntFunction() { // from class: oqa
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int intValue;
                intValue = ((Integer) obj).intValue();
                return intValue;
            }
        }).toArray();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            if (!this.f && motionEvent.getAction() == 0) {
                this.f = true;
            }
            return super.dispatchTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            LogUtil.a("Track_SportMetronomeActivity", "dispatchTouchEvent, exception:", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    static class a implements HiDataOperateListener {
        private a() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            ReleaseLogUtil.e("Track_SportMetronomeActivity", "saveSportBeatToDb, errorCode is ", Integer.valueOf(i));
        }
    }

    static class c implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<SportMetronomeActivity> f9524a;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private c(SportMetronomeActivity sportMetronomeActivity) {
            this.f9524a = new WeakReference<>(sportMetronomeActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            SportMetronomeActivity sportMetronomeActivity = this.f9524a.get();
            if (sportMetronomeActivity == null) {
                LogUtil.h("Track_SportMetronomeActivity", "onResponse: activity is null");
                return;
            }
            if (!koq.e(obj, HiSampleConfig.class)) {
                LogUtil.h("Track_SportMetronomeActivity", "onResponse: objData is not instanceof HiSampleConfig");
                sportMetronomeActivity.m();
                return;
            }
            List list = (List) obj;
            if (!koq.b(list)) {
                sportMetronomeActivity.aa = (SportBeat) HiJsonUtil.e(((HiSampleConfig) list.get(0)).getConfigData(), SportBeat.class);
                if (sportMetronomeActivity.aa != null) {
                    ReleaseLogUtil.e("Track_SportMetronomeActivity", "onResponse: refreshMetronomeLayout mSportBeat is ", sportMetronomeActivity.aa);
                    sportMetronomeActivity.h();
                    return;
                } else {
                    LogUtil.h("Track_SportMetronomeActivity", "onResponse: activity.mSportBeat is null, loading default value");
                    sportMetronomeActivity.m();
                    return;
                }
            }
            LogUtil.h("Track_SportMetronomeActivity", "onResponse: list is empty");
            sportMetronomeActivity.m();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
