package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.ui.view.CustomTrackChartTitleBar;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CustomTrackChartTitleBar extends RelativeLayout {
    private static List<SportDetailChartDataType> d;

    /* renamed from: a, reason: collision with root package name */
    private SportChartItemAdapter f3777a;
    private List<SportDetailChartDataType> b;
    private ImageView c;
    private Context e;
    private HealthRecycleView f;
    private final Map<SportDetailChartDataType, e> g;
    private int h;
    private OnLineStatusChangedListener i;
    private LinearLayout j;
    private List<e> m;
    private HealthTextView o;

    public interface OnLineStatusChangedListener {
        void onLineStatusChanged(SportDetailChartDataType sportDetailChartDataType, boolean z);
    }

    static {
        ArrayList arrayList = new ArrayList(SportDetailChartDataType.values().length);
        d = arrayList;
        arrayList.clear();
        for (SportDetailChartDataType sportDetailChartDataType : SportDetailChartDataType.values()) {
            d.add(sportDetailChartDataType);
        }
    }

    public CustomTrackChartTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        HashMap hashMap = new HashMap(SportDetailChartDataType.values().length);
        this.g = hashMap;
        this.m = new ArrayList();
        this.b = new ArrayList();
        this.h = 0;
        hashMap.put(SportDetailChartDataType.HEART_RATE, new e(R$drawable.ic_exerciserecords_heartrate_frequency, R$drawable.ic_exerciserecords_heartrate_crush, R$string.IDS_main_watch_heart_rate_string));
        hashMap.put(SportDetailChartDataType.STEP_RATE, new e(R$drawable.ic_health_exerciserecords_stride_frequency, R$drawable.ic_health_exerciserecords_stride_crush, R$string.IDS_motiontrack_detail_fm_heart_bupin));
        hashMap.put(SportDetailChartDataType.CADENCE, new e(R$drawable.ic_health_cadence, R$drawable.ic_health_cadence_press, R$string.IDS_indoor_equip_cadence));
        hashMap.put(SportDetailChartDataType.PADDLE_FREQUENCY, new e(R$drawable.ic_paddles_sel, R$drawable.ic_paddles_nor, R$string.IDS_indoor_equip_paddle_frequency));
        hashMap.put(SportDetailChartDataType.POWER, new e(R$drawable.ic_powers_sel, R$drawable.ic_powers_nor, R$string.IDS_indoor_equip_power));
        hashMap.put(SportDetailChartDataType.ALTITUDE, new e(R$drawable.ic_exerciserecords_altitudw_frequency, R$drawable.ic_exerciserecords_altitudw_crush, R$string.IDS_hwh_motiontrack_alti));
        hashMap.put(SportDetailChartDataType.SPEED_RATE, new e(R$drawable.ic_health_track_speed_rate, R$drawable.ic_health_track_speed_rate_unsel, R$string.IDS_motiontrack_show_sport_tip_icon_text_pace));
        hashMap.put(SportDetailChartDataType.REALTIME_PACE, new e(R$drawable.ic_health_track_speed_pace, R$drawable.ic_health_track_speed_pace_unsel, R$string.IDS_motiontrack_show_map_sport_peisu_1));
        a();
        this.e = context;
        d();
    }

    private void a() {
        this.g.put(SportDetailChartDataType.PULL_FREQ, new e(R$drawable.ic_health_track_swimming, R$drawable.ic_health_track_swimming_unsel, R$string.IDS_hwh_motiontrack_pull_frequence));
        this.g.put(SportDetailChartDataType.SWOLF, new e(R$drawable.ic_health_track_swolf, R$drawable.ic_health_track_swolf_unsel, R$string.IDS_hwh_motiontrack_swim_SWOLF));
        this.g.put(SportDetailChartDataType.GROUND_CONTACT_TIME, new e(R$drawable.ic_health_touch_the_ground_time, R$drawable.ic_health_touch_the_ground_time_did_not_click, R$string.IDS_bolt_touchdown_time));
        this.g.put(SportDetailChartDataType.HANG_TIME, new e(R$drawable.ic_landscape_flytime_select, R$drawable.ic_landscape_flytime, R$string.IDS_aw_version2_duration_of_passage));
        this.g.put(SportDetailChartDataType.GROUND_HANG_TIME_RATE, new e(R$drawable.ic_landscape_flypercentage_select, R$drawable.ic_landscape_flypercentage, R$string.IDS_motiontrack_ground_to_air_ratio));
        this.g.put(SportDetailChartDataType.GROUND_IMPACT_ACCELERATION, new e(R$drawable.ic_health_on_the_impact, R$drawable.ic_health_on_the_impact_did_not_click, R$string.IDS_running_posture_ground_impact_acceleration));
        this.g.put(SportDetailChartDataType.SPO2, new e(R$drawable.ic_blood_oxygen_2_sel_new, R$drawable.ic_blood_oxygen_2_nor, R$string.IDS_hw_health_blood_oxygen));
        this.g.put(SportDetailChartDataType.SKIPPING_SPEED, new e(R$drawable.ic_health_track_speed_rate, R$drawable.ic_health_track_speed_rate_unsel, R$string.IDS_indoor_skipper_rope_sport_type));
        this.g.put(SportDetailChartDataType.ACTIVE_PEAK, new e(R$drawable.ic_health_on_the_impact, R$drawable.ic_health_on_the_impact_did_not_click, R$string.IDS_bolt_shock_peak));
        this.g.put(SportDetailChartDataType.VERTICAL_OSCILLATION, new e(R$drawable.ic_health_on_the_impact, R$drawable.ic_health_on_the_impact_did_not_click, R$string.IDS_bolt_vertical_amplitude));
        this.g.put(SportDetailChartDataType.VERTICAL_RATIO, new e(R$drawable.ic_health_on_the_impact, R$drawable.ic_health_on_the_impact_did_not_click, R$string.IDS_bolt_vertical_stride_rate));
        this.g.put(SportDetailChartDataType.GC_TIME_BALANCE, new e(R$drawable.ic_health_on_the_impact, R$drawable.ic_health_on_the_impact_did_not_click, R$string.IDS_bolt_balance_left_right_touches));
        this.g.put(SportDetailChartDataType.PEAK_WEIGHT, new e(R$drawable.ic_health_peak_weight, R$drawable.ic_health_peak_weight_not_click, R$string.IDS_rowing_machine_peak_weight));
    }

    private void d() {
        ((LayoutInflater) this.e.getSystemService("layout_inflater")).inflate(R.layout.commonui_custom_titlebar_linechart, this);
        this.c = (ImageView) findViewById(R.id.btn_left);
        if (LanguageUtil.bc(this.e)) {
            LogUtil.a("Track_CustomChartTitleBar", "loadBackBtn() language rtl");
            this.c.setImageDrawable(this.e.getResources().getDrawable(R$drawable.health_navbar_rtl_back_selector));
        } else {
            LogUtil.a("Track_CustomChartTitleBar", "loadBackBtn() language ltr");
            this.c.setImageDrawable(this.e.getResources().getDrawable(R$drawable.health_navbar_back_selector));
        }
        this.o = (HealthTextView) findViewById(R.id.detail_title_text);
        this.j = (LinearLayout) findViewById(R.id.view_right);
        View findViewById = findViewById(R.id.status_bar_place_holder);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
        layoutParams.height = nsn.r(this.e);
        findViewById.setLayoutParams(layoutParams);
        b();
    }

    private void b() {
        LinearLayout linearLayout = this.j;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        if (this.h == 1) {
            this.o.setVisibility(8);
            c();
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (SportDetailChartDataType sportDetailChartDataType : d) {
            e eVar = this.g.get(sportDetailChartDataType);
            if (eVar != null && !eVar.e && eVar.b && this.h == 0) {
                arrayList.add(sportDetailChartDataType);
            }
        }
        int i = 0;
        while (i < arrayList.size()) {
            a(i == arrayList.size() - 1, (SportDetailChartDataType) arrayList.get(i), this.g.get((SportDetailChartDataType) arrayList.get(i)));
            i++;
        }
    }

    private void c() {
        this.f = new HealthRecycleView(this.e);
        this.f.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f.setLayoutManager(new LinearLayoutManager(this.e, 0, false));
        this.f.setNestedScrollingEnabled(false);
        this.f.setHasFixedSize(true);
        this.f.a(false);
        this.f.d(false);
        SportChartItemAdapter sportChartItemAdapter = new SportChartItemAdapter(this.e);
        this.f3777a = sportChartItemAdapter;
        sportChartItemAdapter.a(this.m, new IBaseResponseCallback() { // from class: hlm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                CustomTrackChartTitleBar.this.e(i, obj);
            }
        });
        this.f.setAdapter(this.f3777a);
        this.f.setItemViewCacheSize(5);
        this.j.addView(this.f);
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (koq.b(this.b, i)) {
            LogUtil.h("Track_CustomChartTitleBar", "configButtonRecycleView position is out of mChartList.", Integer.valueOf(i));
        } else {
            e(this.b.get(i));
            this.f3777a.notifyItemChanged(i);
        }
    }

    private void a(boolean z, final SportDetailChartDataType sportDetailChartDataType, e eVar) {
        float dimension;
        ImageView imageView = new ImageView(this.e);
        int c = nsn.c(this.e, 24.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(c, c);
        if (z) {
            dimension = this.e.getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a);
        } else {
            dimension = this.e.getResources().getDimension(R.dimen._2131362563_res_0x7f0a0303);
        }
        layoutParams.setMarginEnd((int) dimension);
        imageView.setLayoutParams(layoutParams);
        if (eVar.b && eVar.f3778a) {
            imageView.setImageResource(eVar.d);
        } else if (eVar.b) {
            imageView.setImageResource(eVar.h);
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.CustomTrackChartTitleBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomTrackChartTitleBar.this.e(sportDetailChartDataType);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.j.addView(imageView);
    }

    public void setTitle(String str) {
        this.o.setText(str);
    }

    public void setOnLineStatusChangedListener(OnLineStatusChangedListener onLineStatusChangedListener) {
        this.i = onLineStatusChangedListener;
    }

    static class e {
        int c;
        int d;
        int h;
        boolean e = false;

        /* renamed from: a, reason: collision with root package name */
        boolean f3778a = false;
        boolean b = false;

        e(int i, int i2, int i3) {
            this.d = i;
            this.h = i2;
            this.c = i3;
        }
    }

    public void setBaseLine(SportDetailChartDataType sportDetailChartDataType) {
        Iterator<SportDetailChartDataType> it = d.iterator();
        while (it.hasNext()) {
            e eVar = this.g.get(it.next());
            if (eVar != null) {
                eVar.e = false;
            }
        }
        e eVar2 = this.g.get(sportDetailChartDataType);
        if (eVar2 != null) {
            eVar2.e = true;
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(SportDetailChartDataType sportDetailChartDataType) {
        e eVar = this.g.get(sportDetailChartDataType);
        boolean z = eVar.f3778a;
        if (!z && e() >= 3) {
            i();
            return;
        }
        eVar.f3778a = !z;
        OnLineStatusChangedListener onLineStatusChangedListener = this.i;
        if (onLineStatusChangedListener != null) {
            onLineStatusChangedListener.onLineStatusChanged(sportDetailChartDataType, eVar.f3778a);
        }
        if (this.h == 0) {
            b();
        }
    }

    private void i() {
        String quantityString = this.e.getResources().getQuantityString(R.plurals.IDS_pluginmotiontrack_show_three_chart, 3, 3);
        Toast makeText = Toast.makeText(BaseApplication.getContext(), quantityString, 0);
        makeText.setText(quantityString);
        makeText.show();
    }

    private int e() {
        int i = 1;
        for (SportDetailChartDataType sportDetailChartDataType : SportDetailChartDataType.values()) {
            e eVar = this.g.get(sportDetailChartDataType);
            if (eVar != null && eVar.f3778a) {
                i++;
            }
        }
        return i;
    }

    public void setOnBackListener(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setIconClickable(List<SportDetailChartDataType> list) {
        if (list == null) {
            LogUtil.b("Track_CustomChartTitleBar", "setIconClickable() chartLayerTypes is null");
            return;
        }
        for (SportDetailChartDataType sportDetailChartDataType : list) {
            e eVar = this.g.get(sportDetailChartDataType);
            eVar.b = true;
            this.m.add(eVar);
            this.b.add(sportDetailChartDataType);
        }
        b();
    }

    public void setShowMode(int i) {
        this.h = i;
    }
}
