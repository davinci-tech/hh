package com.huawei.ui.commonui.linechart.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nng;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class CustomChartTitleBar extends RelativeLayout {
    private static List<ChartLayerType> c;

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8890a;
    private Context b;
    private LinearLayout d;
    private final Map<ChartLayerType, d> e;
    private HealthTextView i;

    public enum ChartLayerType {
        HEART_RATE,
        STEP_FRE,
        ALTITUDE,
        SPEED_RATE,
        REALTIME_PACE,
        PULL_FREQ,
        SWOLF,
        GROUND_CONTACT_TIME,
        HANG_TIME,
        GROUND_HANG_TIME_RATE,
        GROUND_IMPACT_ACCELERATION,
        SPO2,
        JUMP_TIME,
        JUMP_HEIGHT
    }

    /* loaded from: classes9.dex */
    public interface OnLineStatusChangedListener {
        void onLineStatusChanged(ChartLayerType chartLayerType, boolean z);
    }

    static {
        ArrayList arrayList = new ArrayList(10);
        c = arrayList;
        arrayList.add(ChartLayerType.HEART_RATE);
        c.add(ChartLayerType.STEP_FRE);
        c.add(ChartLayerType.ALTITUDE);
        c.add(ChartLayerType.SPEED_RATE);
        c.add(ChartLayerType.REALTIME_PACE);
        c.add(ChartLayerType.PULL_FREQ);
        c.add(ChartLayerType.SWOLF);
        c.add(ChartLayerType.GROUND_CONTACT_TIME);
        c.add(ChartLayerType.GROUND_IMPACT_ACCELERATION);
        c.add(ChartLayerType.SPO2);
    }

    public CustomChartTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        HashMap hashMap = new HashMap(10);
        this.e = hashMap;
        hashMap.put(ChartLayerType.HEART_RATE, new d(R$drawable.ic_exerciserecords_heartrate_frequency, R$drawable.ic_exerciserecords_heartrate_crush, R$drawable.ic_exerciserecords_heartrate_crush));
        hashMap.put(ChartLayerType.STEP_FRE, new d(R$drawable.ic_health_exerciserecords_stride_frequency, R$drawable.ic_health_exerciserecords_stride_crush, R$drawable.ic_health_exerciserecords_stride_crush));
        hashMap.put(ChartLayerType.ALTITUDE, new d(R$drawable.ic_exerciserecords_altitudw_frequency, R$drawable.ic_exerciserecords_altitudw_crush, R$drawable.ic_exerciserecords_altitudw_crush));
        hashMap.put(ChartLayerType.SPEED_RATE, new d(R$drawable.ic_health_track_speed_rate, R$drawable.ic_health_track_speed_rate_unsel, R$drawable.ic_health_track_speed_rate_unsel));
        hashMap.put(ChartLayerType.REALTIME_PACE, new d(R$drawable.ic_health_track_speed_pace, R$drawable.ic_health_track_speed_pace_unsel, R$drawable.ic_health_track_speed_pace_unsel));
        hashMap.put(ChartLayerType.PULL_FREQ, new d(R$drawable.ic_health_track_swimming, R$drawable.ic_health_track_swimming_unsel, R$drawable.ic_health_track_swimming_unsel));
        hashMap.put(ChartLayerType.SWOLF, new d(R$drawable.ic_health_track_swolf, R$drawable.ic_health_track_swolf_unsel, R$drawable.ic_health_track_swolf_unsel));
        hashMap.put(ChartLayerType.GROUND_CONTACT_TIME, new d(R$drawable.ic_health_touch_the_ground_time, R$drawable.ic_health_touch_the_ground_time_did_not_click, R$drawable.ic_health_touch_the_ground_time_did_not_click));
        hashMap.put(ChartLayerType.HANG_TIME, new d(R$drawable.ic_landscape_flytime_select, R$drawable.ic_landscape_flytime, R$drawable.ic_landscape_flytime));
        hashMap.put(ChartLayerType.GROUND_HANG_TIME_RATE, new d(R$drawable.ic_landscape_flypercentage_select, R$drawable.ic_landscape_flypercentage, R$drawable.ic_landscape_flypercentage));
        hashMap.put(ChartLayerType.GROUND_IMPACT_ACCELERATION, new d(R$drawable.ic_health_on_the_impact, R$drawable.ic_health_on_the_impact_did_not_click, R$drawable.ic_health_on_the_impact_did_not_click));
        hashMap.put(ChartLayerType.SPO2, new d(R$drawable.ic_blood_oxygen_2_sel_new, R$drawable.ic_blood_oxygen_2_nor, R$drawable.ic_blood_oxygen_2_nor));
        this.b = context;
        c();
    }

    private void c() {
        Object systemService = this.b.getSystemService("layout_inflater");
        if (systemService instanceof LayoutInflater) {
            ((LayoutInflater) systemService).inflate(R.layout.commonui_custom_titlebar_linechart, this);
            this.f8890a = (ImageView) findViewById(R.id.btn_left);
            if (nng.d(this.b)) {
                LogUtil.a("HealthChat_CustomChartTitleBar", "loadBackBtn() language rtl");
                this.f8890a.setImageDrawable(this.b.getResources().getDrawable(R$drawable.health_navbar_rtl_back_selector));
            } else {
                LogUtil.a("HealthChat_CustomChartTitleBar", "loadBackBtn() language ltr");
                this.f8890a.setImageDrawable(this.b.getResources().getDrawable(R$drawable.health_navbar_back_selector));
            }
            this.i = (HealthTextView) findViewById(R.id.detail_title_text);
            findViewById(R.id.titlebar_divider_line_height).setBackgroundColor(Color.argb(25, 0, 0, 0));
            this.d = (LinearLayout) findViewById(R.id.view_right);
            View findViewById = findViewById(R.id.status_bar_place_holder);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            layoutParams.height = a(this.b);
            findViewById.setLayoutParams(layoutParams);
            b();
        }
    }

    private void b() {
        float applyDimension;
        LinearLayout linearLayout = this.d;
        if (linearLayout == null) {
            LogUtil.h("HealthChat_CustomChartTitleBar", "refresh mResImageLayout == null");
            return;
        }
        linearLayout.removeAllViews();
        Iterator<ChartLayerType> it = c.iterator();
        while (it.hasNext()) {
            final ChartLayerType next = it.next();
            d dVar = this.e.get(next);
            if (dVar != null && !dVar.d() && dVar.b()) {
                ImageView imageView = new ImageView(this.b);
                DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
                int applyDimension2 = (int) TypedValue.applyDimension(1, 24.0f, displayMetrics);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(applyDimension2, applyDimension2);
                if (it.hasNext()) {
                    applyDimension = TypedValue.applyDimension(1, 16.0f, displayMetrics);
                } else {
                    applyDimension = TypedValue.applyDimension(1, 20.0f, displayMetrics);
                }
                layoutParams.setMarginEnd((int) applyDimension);
                imageView.setLayoutParams(layoutParams);
                if (!dVar.d || !dVar.b) {
                    if (dVar.d) {
                        imageView.setImageResource(dVar.c);
                    }
                } else {
                    imageView.setImageResource(dVar.e);
                }
                if (dVar.d) {
                    imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.linechart.utils.CustomChartTitleBar.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            CustomChartTitleBar.this.d(next);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                }
                this.d.addView(imageView);
            }
        }
        d();
    }

    public void setTitle(String str) {
        this.i.setText(str);
    }

    private void d() {
        if (this.d.getChildCount() > 0) {
            View childAt = this.d.getChildAt(r0.getChildCount() - 1);
            if (childAt == null || childAt.getVisibility() == 8) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) layoutParams).setMarginEnd((int) this.b.getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a));
            }
        }
    }

    /* loaded from: classes9.dex */
    static class d {
        private int c;
        private int e;

        /* renamed from: a, reason: collision with root package name */
        private boolean f8892a = false;
        private boolean b = false;
        private boolean d = false;

        d(int i, int i2, int i3) {
            this.e = i;
            this.c = i2;
        }

        boolean d() {
            return this.f8892a;
        }

        public boolean c() {
            return this.b;
        }

        public boolean b() {
            return this.d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ChartLayerType chartLayerType) {
        d dVar = this.e.get(chartLayerType);
        boolean c2 = dVar.c();
        if (c2 || e() < 3) {
            dVar.b = !c2;
            b();
        } else {
            a();
        }
    }

    private void a() {
        String quantityString = this.b.getResources().getQuantityString(R.plurals.IDS_pluginmotiontrack_show_three_chart, 3, 3);
        Toast makeText = Toast.makeText(BaseApplication.getContext(), quantityString, 0);
        makeText.setText(quantityString);
        makeText.show();
    }

    private int e() {
        int i = 1;
        for (ChartLayerType chartLayerType : ChartLayerType.values()) {
            d dVar = this.e.get(chartLayerType);
            if (dVar != null && dVar.b) {
                i++;
            }
        }
        return i;
    }

    public static int a(Context context) {
        if (context == null) {
            LogUtil.h("HealthChat_CustomChartTitleBar", "getStatusBarHeight context == null");
            return 0;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchFieldException | NumberFormatException e) {
            LogUtil.h("HealthChat_CustomChartTitleBar", "getStatusBarHeight:", ExceptionUtils.d(e));
            return 0;
        }
    }
}
