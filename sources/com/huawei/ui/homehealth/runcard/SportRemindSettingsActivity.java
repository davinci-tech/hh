package com.huawei.ui.homehealth.runcard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.ixx;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.osl;
import defpackage.riy;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class SportRemindSettingsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f9526a;
    private HealthDivider b;
    private boolean c;
    private boolean d;
    private Context e;
    private int f;
    private boolean g;
    private int h;
    private HealthRecycleView i;
    private String k;
    private LinearLayout l;
    private String m;
    private GroupBtnSelectedAdapter n;
    private HealthSwitchButton o;
    private String q;
    private int r;
    private RelativeLayout s;
    private int t;
    private HealthTextView y;
    private boolean[] p = new boolean[7];
    private UiCallback j = new UiCallback<Map<String, String>>() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.3
        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_SportRemindSettingsActivity", "mRemindDataUiCallback onFailure");
            SportRemindSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.3.3
                @Override // java.lang.Runnable
                public void run() {
                    SportRemindSettingsActivity.this.g();
                }
            });
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<String, String> map) {
            LogUtil.a("Track_SportRemindSettingsActivity", "mRemindDataUiCallback onSuccess");
            if (map != null) {
                LogUtil.h("Track_SportRemindSettingsActivity", "onSuccess data != null ");
                SportRemindSettingsActivity.this.m = map.get("sport_remind_time");
                SportRemindSettingsActivity.this.k = map.get("sport_remind_period");
            }
            LogUtil.a("Track_SportRemindSettingsActivity", "mRemindDataUiCallback mRemindTime ", SportRemindSettingsActivity.this.m, " mRemindPeriod ", SportRemindSettingsActivity.this.k);
            SportRemindSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.3.2
                @Override // java.lang.Runnable
                public void run() {
                    SportRemindSettingsActivity.this.g();
                    SportRemindSettingsActivity.this.e();
                }
            });
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_SportRemindSettingsActivity", "onCreate");
        setContentView(R.layout.activity_sport_remind_settings);
        this.e = this;
        d();
        a();
    }

    private void d() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("sport_remind_value", 0);
        this.t = intExtra;
        LogUtil.a("Track_SportRemindSettingsActivity", "initView() intent mSportRemindValue: ", Integer.valueOf(intExtra));
        ((CustomTitleBar) findViewById(R.id.title_bar_sport_remind_setting)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportRemindSettingsActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sport_remind_time_container);
        this.s = relativeLayout;
        BaseActivity.setViewSafeRegion(false, relativeLayout);
        this.s.setOnClickListener(this);
        this.y = (HealthTextView) findViewById(R.id.sport_remind_time);
        ImageView imageView = (ImageView) findViewById(R.id.sport_remind_time_arrow);
        if (LanguageUtil.bc(this.e)) {
            imageView.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            imageView.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.sport_remind_period_layout);
        this.l = linearLayout;
        BaseActivity.setViewSafeRegion(false, linearLayout);
        this.i = (HealthRecycleView) findViewById(R.id.period_reminder_layout);
        this.b = (HealthDivider) findViewById(R.id.sport_remind_divider1);
        this.f9526a = (HealthDivider) findViewById(R.id.sport_remind_divider2);
    }

    private void a() {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.sport_remind_switch);
        this.o = healthSwitchButton;
        if (this.t == 0) {
            healthSwitchButton.setChecked(false);
            b();
        } else {
            healthSwitchButton.setChecked(true);
            osl.e((UiCallback<Map<String, String>>) this.j);
        }
        this.o.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a("Track_SportRemindSettingsActivity", "mRemindSwitch onCheckedChanged() isChecked: ", Boolean.valueOf(z));
                SportRemindSettingsActivity.this.e(z);
                if (z) {
                    SportRemindSettingsActivity.this.k = null;
                    SportRemindSettingsActivity.this.m = null;
                    SportRemindSettingsActivity.this.g();
                    SportRemindSettingsActivity.this.e();
                } else {
                    SportRemindSettingsActivity.this.b();
                    SportRemindSettingsActivity.this.c();
                    ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).deleteFitnessCard();
                }
                if (compoundButton.isPressed()) {
                    SportRemindSettingsActivity.this.g = true;
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.s.setVisibility(8);
        this.l.setVisibility(8);
        this.b.setVisibility(8);
        this.f9526a.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        f();
        i();
        this.b.setVisibility(0);
        this.f9526a.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        String num;
        if (z) {
            num = Integer.toString(1);
            this.r = 1;
            this.t = 1;
        } else {
            num = Integer.toString(0);
            this.r = 0;
            this.t = 0;
        }
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "sport_remind_is_opened", num, (StorageParams) null);
    }

    private void f() {
        int h;
        this.s.setVisibility(0);
        LogUtil.a("Track_SportRemindSettingsActivity", "remindTime ", this.m);
        if (!TextUtils.isEmpty(this.m)) {
            h = CommonUtils.h(this.m);
        } else {
            String b = SharedPreferenceManager.b(this.e, Integer.toString(20002), "sport_remind_reminder_time");
            LogUtil.a("Track_SportRemindSettingsActivity", "value ", b);
            h = !TextUtils.isEmpty(b) ? CommonUtils.h(b) : 66600;
        }
        LogUtil.a("Track_SportRemindSettingsActivity", "showRemindTimeText() remindTimeValue: ", Integer.valueOf(h));
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "sport_remind_reminder_time", Integer.toString(h), (StorageParams) null);
        this.h = h / 3600;
        this.f = (h % 3600) / 60;
        j();
    }

    private void i() {
        this.l.setVisibility(0);
        LogUtil.a("Track_SportRemindSettingsActivity", "mRemindPeriod ", this.k);
        int length = this.p.length;
        if (TextUtils.isEmpty(this.k)) {
            e(length);
        } else if (this.k.length() == 1) {
            osl.c(this.k, length, this.p);
        } else {
            osl.a(this.k, this.p);
        }
        a(new String[]{this.e.getString(R.string._2130841437_res_0x7f020f5d), this.e.getString(R.string._2130841539_res_0x7f020fc3), this.e.getString(R.string._2130841558_res_0x7f020fd6), this.e.getString(R.string._2130841538_res_0x7f020fc2), this.e.getString(R.string._2130841414_res_0x7f020f46), this.e.getString(R.string._2130841468_res_0x7f020f7c), this.e.getString(R.string._2130841537_res_0x7f020fc1)});
    }

    private void a(String[] strArr) {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.e, 4) { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.i.setLayoutManager(gridLayoutManager);
        GroupBtnSelectedAdapter groupBtnSelectedAdapter = new GroupBtnSelectedAdapter(this.e, strArr, this.p);
        this.n = groupBtnSelectedAdapter;
        groupBtnSelectedAdapter.cwI_(this.e.getResources().getDrawable(R.drawable._2131431983_res_0x7f0b122f), this.e.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.n.cwJ_(this.e.getResources().getDrawable(R.drawable._2131431982_res_0x7f0b122e), this.e.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
        this.i.setAdapter(this.n);
        if (!this.d) {
            this.d = true;
            this.i.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.2
                @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                    GridLayoutManager gridLayoutManager2 = gridLayoutManager;
                    if (gridLayoutManager2 == null || gridLayoutManager2.getSpanCount() <= 1) {
                        return;
                    }
                    int spanCount = gridLayoutManager.getSpanCount();
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                    int dimensionPixelSize = SportRemindSettingsActivity.this.e.getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
                    int dimensionPixelSize2 = SportRemindSettingsActivity.this.e.getResources().getDimensionPixelSize(R.dimen._2131362480_res_0x7f0a02b0);
                    if (childAdapterPosition < spanCount) {
                        rect.bottom = dimensionPixelSize2;
                    } else {
                        rect.top = dimensionPixelSize2;
                    }
                    int i = ((spanCount - 1) * dimensionPixelSize) / spanCount;
                    int i2 = (childAdapterPosition % spanCount) * (dimensionPixelSize - i);
                    rect.left = i2;
                    rect.right = i - i2;
                }
            });
        }
        this.n.c(new GroupBtnSelectedAdapter.OnItemClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.7
            @Override // com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter.OnItemClickListener
            public void onItemClick(View view, HealthButton healthButton, int i) {
                SportRemindSettingsActivity.this.p[i] = !SportRemindSettingsActivity.this.p[i];
                SportRemindSettingsActivity.this.n.cwF_(view, healthButton, i);
                SportRemindSettingsActivity.this.e();
                SportRemindSettingsActivity.this.c = true;
            }
        });
    }

    private void e(int i) {
        String b = SharedPreferenceManager.b(this.e, Integer.toString(20002), "sport_remind_reminder_period");
        LogUtil.a("Track_SportRemindSettingsActivity", "showRemindPeriodLayout() get remindPeriodStr: ", b);
        if (TextUtils.isEmpty(b)) {
            d(i);
        } else if (b.length() == 1) {
            osl.c(b, i, this.p);
        } else {
            osl.a(b, this.p);
        }
    }

    private void d(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 != 5) {
                this.p[i2] = true;
            } else {
                this.p[i2] = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        int i = 0;
        while (true) {
            boolean[] zArr = this.p;
            if (i >= zArr.length) {
                break;
            }
            if (zArr[i]) {
                sb.append((i + 1) + "_");
                z = false;
            }
            i++;
        }
        this.q = "";
        if (z) {
            this.o.setChecked(false);
        } else {
            String sb2 = sb.toString();
            this.q = sb2.substring(0, sb2.length() - 1);
        }
        LogUtil.a("Track_SportRemindSettingsActivity", "saveRemindPeriodData() save mSportPeriodValueStr: ", this.q);
        SharedPreferenceManager.e(this.e, Integer.toString(20002), "sport_remind_reminder_period", this.q, (StorageParams) null);
        c(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (z) {
            return;
        }
        int i = 0;
        while (true) {
            boolean[] zArr = this.p;
            if (i >= zArr.length) {
                return;
            }
            int i2 = i + 1;
            if (zArr[i]) {
                c(i2);
            } else {
                LogUtil.a("Track_SportRemindSettingsActivity", "updateRemindPeriodNotifyData() cancelReminder selectArrayDay: ", Integer.valueOf(i2));
                riy.c(i + 1001);
            }
            i = i2;
        }
    }

    private void c(int i) {
        osl.c(i, this.h, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        for (int i = 0; i < this.p.length; i++) {
            riy.c(i + 1001);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.sport_remind_time_container) {
            h();
        } else {
            LogUtil.h("Track_SportRemindSettingsActivity", "wrong view id");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        LogUtil.a("Track_SportRemindSettingsActivity", "showSportRemindTimeDialog()");
        if (!(this.e.getSystemService("layout_inflater") instanceof LayoutInflater)) {
            LogUtil.b("Track_SportRemindSettingsActivity", "showSportRemindTimeDialog() object is invalid type");
            return;
        }
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.homehealth.runcard.SportRemindSettingsActivity.6
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                SportRemindSettingsActivity.this.c = true;
                if (i != SportRemindSettingsActivity.this.h || i2 != SportRemindSettingsActivity.this.f) {
                    SportRemindSettingsActivity.this.h = i;
                    SportRemindSettingsActivity.this.f = i2;
                    int i3 = SportRemindSettingsActivity.this.h;
                    int i4 = SportRemindSettingsActivity.this.f;
                    SportRemindSettingsActivity.this.j();
                    String num = Integer.toString((i3 * 3600) + (i4 * 60));
                    LogUtil.a("Track_SportRemindSettingsActivity", "showSportRemindTimeDialog() save time: ", num);
                    SharedPreferenceManager.e(SportRemindSettingsActivity.this.e, Integer.toString(20002), "sport_remind_reminder_time", num, (StorageParams) null);
                    SportRemindSettingsActivity.this.c(false);
                    return;
                }
                LogUtil.a("Track_SportRemindSettingsActivity", "showSportRemindTimeDialog() select some time");
            }
        });
        healthTimePickerDialog.e(0, 0, 0, this.h, this.f);
        healthTimePickerDialog.b(getString(R$string.IDS_track_remind_time));
        healthTimePickerDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, this.h);
        calendar.set(12, this.f);
        this.y.setText(nsj.c(this.e, calendar.getTimeInMillis(), 1));
        calendar.clear();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        int i = (this.h * 3600) + (this.f * 60);
        HashMap hashMap = new HashMap();
        hashMap.put("sport_remind_switch_status", Integer.toString(this.t));
        hashMap.put("sport_remind_period", this.q);
        hashMap.put("sport_remind_time", Integer.toString(i));
        osl.e(hashMap);
        if (this.c || this.g) {
            ((FitnessAdviceApi) Services.c("PluginFitnessAdvice", FitnessAdviceApi.class)).donateBySportRemind(hashMap);
        }
        if (this.c) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("click", 1);
            hashMap2.put("type", 2);
            hashMap2.put("time", Integer.valueOf(i));
            hashMap2.put(TypedValues.CycleType.S_WAVE_PERIOD, this.q);
            ixx.d().d(this.e, AnalyticsValue.REMIND_SPORT_SELECT_TIME_AND_PERIOD.value(), hashMap2, 0);
        }
        if (this.g) {
            HashMap hashMap3 = new HashMap(2);
            hashMap3.put("click", 1);
            hashMap3.put("type", Integer.valueOf(this.r));
            ixx.d().d(this.e, AnalyticsValue.REMIND_SPORT_CLICK_SWITCH_COUNT.value(), hashMap3, 0);
        }
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
