package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarSportMonitoringView;
import defpackage.deb;
import defpackage.gge;
import defpackage.ggl;
import defpackage.gnm;
import defpackage.kwz;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.pyw;
import defpackage.qab;
import defpackage.qkh;
import defpackage.rdu;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class BloodSugarSportMonitoringView extends LinearLayout {
    private static float c;
    private static float d;

    /* renamed from: a, reason: collision with root package name */
    private final Context f9965a;
    private LayoutInflater b;
    private final Handler e;
    private final BloodSugarSportMonitoringView f;
    private long g;
    private int h;
    private int i;
    private LinearLayout j;

    static /* synthetic */ int c(BloodSugarSportMonitoringView bloodSugarSportMonitoringView) {
        int i = bloodSugarSportMonitoringView.i;
        bloodSugarSportMonitoringView.i = i + 1;
        return i;
    }

    public BloodSugarSportMonitoringView(Context context) {
        this(context, null);
    }

    public BloodSugarSportMonitoringView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarSportMonitoringView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 0L;
        this.i = 0;
        this.h = 0;
        this.f9965a = context;
        this.f = this;
        this.e = new b(this);
        b(context);
        b();
    }

    private void b() {
        float[] b2 = deb.b();
        if (b2.length < 2) {
            LogUtil.c("BloodSugarSportMonitoringView", "get thresholds error");
            return;
        }
        float f = b2[1];
        d = f;
        c = b2[0];
        LogUtil.d("BloodSugarSportMonitoringView", "get thresholds mThresholdMin:", Float.valueOf(f), ",mThresholdMax:", Float.valueOf(c));
    }

    public void a() {
        this.g = 0L;
        this.i = 0;
    }

    private void b(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        this.b = from;
        from.inflate(R.layout.layout_blood_sugar_sport_container_view, this);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.sport_blood_sugar_title);
        this.j = (LinearLayout) findViewById(R.id.layout_sport_view);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        healthSubHeader.setHeadTitleText(this.f9965a.getString(R$string.IDS_hw_show_healthdata_monitoring));
        healthSubHeader.setPaddingStartEnd(0.0f, 0.0f);
        this.f.setVisibility(8);
    }

    public void setDate(long j) {
        long j2 = j * 60000;
        long d2 = TimeUtil.d(j2);
        if (this.g == d2) {
            return;
        }
        this.g = d2;
        this.f.setVisibility(8);
        this.j.removeAllViews();
        long b2 = TimeUtil.b(j2);
        LogUtil.d("BloodSugarSportMonitoringView", "getSportRecords startTime = ", ggl.a(new Date(d2), "yyyy-MM-dd HH:mm:ss"), ",endTime = ", ggl.a(new Date(b2), "yyyy-MM-dd HH:mm:ss"));
        pyw.e().b(d2, b2, new int[]{2108}, new a(this, d2, b2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(qab qabVar, Map<Long, Float> map) {
        long startTime = qabVar.getStartTime();
        long endTime = qabVar.getEndTime();
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        for (Map.Entry<Long, Float> entry : map.entrySet()) {
            if (entry.getKey().longValue() >= startTime && entry.getKey().longValue() <= endTime) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (linkedHashMap.size() == 0) {
            return;
        }
        qabVar.b(a(linkedHashMap));
        Message obtain = Message.obtain();
        obtain.obj = qabVar;
        obtain.what = 1;
        this.e.sendMessage(obtain);
    }

    private String a(Map<Long, Float> map) {
        if (map == null || map.size() == 0) {
            return String.valueOf(0);
        }
        Iterator<Map.Entry<Long, Float>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            float floatValue = it.next().getValue().floatValue();
            if (floatValue > d && floatValue <= c) {
                i++;
            }
        }
        String e = UnitUtil.e(new BigDecimal(i).divide(new BigDecimal(map.size()), 2, 4).multiply(BigDecimal.valueOf(100L)).doubleValue(), 2, 0);
        LogUtil.d("BloodSugarSportMonitoringView", "proportion : ", e);
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dwr_(View view, qab qabVar) {
        ((HealthTextView) view.findViewById(R.id.percentage_text)).setText(qabVar.b());
        a((HealthTextView) view.findViewById(R.id.sport_motion_distance_text), (HealthTextView) view.findViewById(R.id.sport_motion_distance_unit_text), qabVar);
        ((HealthTextView) view.findViewById(R.id.sport_start_time_text)).setText(UnitUtil.a("HH:mm", qabVar.getStartTime()));
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.heart_rate_text);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.heart_rate_unit_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.heart_rate_image);
        if (qabVar.getAvgHeartRate() == 0 && qabVar.getTotalCalories() == 0) {
            dwq_(healthTextView, healthTextView2, imageView, 8);
        } else if (qabVar.getAvgHeartRate() > 0) {
            dwq_(healthTextView, healthTextView2, imageView, 0);
            healthTextView.setText(String.valueOf(UnitUtil.e(qabVar.getAvgHeartRate(), 1, 0)));
            healthTextView2.setText(R$string.IDS_main_watch_heart_rate_unit_string);
            if (e()) {
                imageView.setBackground(nrz.cKn_(BaseApplication.e(), R.mipmap._2131820823_res_0x7f110117));
            } else {
                imageView.setBackgroundResource(R.mipmap._2131820823_res_0x7f110117);
            }
        } else {
            dwq_(healthTextView, healthTextView2, imageView, 0);
            healthTextView.setText(UnitUtil.e(qabVar.getTotalCalories() / 1000.0d, 1, 0));
            healthTextView2.setText(R$string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit);
            if (e()) {
                imageView.setBackground(nrz.cKn_(BaseApplication.e(), R.mipmap._2131820824_res_0x7f110118));
            } else {
                imageView.setBackgroundResource(R.mipmap._2131820824_res_0x7f110118);
            }
        }
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.sport_duration_text);
        healthTextView3.setText(UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(qabVar.getTotalTime())));
        c(healthTextView, healthTextView3, healthTextView2);
        dws_(view, qabVar);
    }

    private static void dwq_(HealthTextView healthTextView, HealthTextView healthTextView2, ImageView imageView, int i) {
        healthTextView.setVisibility(i);
        healthTextView2.setVisibility(i);
        imageView.setVisibility(i);
    }

    private static void dws_(View view, final qab qabVar) {
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.sport_type_image_bg);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.sport_type_image);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.sport_duration_image);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.right_arrow_image);
        Drawable dMy_ = rdu.dMy_(context, qabVar.getSportType());
        imageView.setBackground(nrf.cJH_(context.getDrawable(R.drawable._2131430592_res_0x7f0b0cc0), rdu.b(context, qabVar.getSportType())));
        if (e()) {
            imageView2.setBackground(nrz.cKm_(context, dMy_));
            imageView3.setImageDrawable(nrz.cKn_(context, R.mipmap._2131820900_res_0x7f110164));
            imageView4.setBackground(nrz.cKn_(context, R.mipmap._2131820657_res_0x7f110071));
        } else {
            imageView2.setBackground(dMy_);
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: pzx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BloodSugarSportMonitoringView.dwp_(qab.this, view2);
            }
        });
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", 1);
        hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, Long.valueOf(qabVar.getStartTime()));
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_SPORT_2040139.value();
        hashMap.put("click", "1");
        gge.e(value, hashMap);
    }

    public static /* synthetic */ void dwp_(qab qabVar, View view) {
        a(qabVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void c(final HealthTextView healthTextView, final HealthTextView healthTextView2, final HealthTextView healthTextView3) {
        healthTextView3.post(new Runnable() { // from class: pzv
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarSportMonitoringView.e(HealthTextView.this, healthTextView, healthTextView2);
            }
        });
    }

    public static /* synthetic */ void e(HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
        if (healthTextView == null) {
            LogUtil.c("BloodSugarSportMonitoringView", "setAutoWidthTextView textView is null");
            return;
        }
        Layout layout = healthTextView.getLayout();
        if (layout == null) {
            LogUtil.c("BloodSugarSportMonitoringView", "setAutoWidthTextView layout is null");
            c(healthTextView2, healthTextView3, healthTextView);
            return;
        }
        int lineCount = layout.getLineCount();
        if (lineCount <= 0 || lineCount <= healthTextView2.getMaxLines()) {
            return;
        }
        float textSize = healthTextView.getTextSize();
        float e = nrr.e(BaseApplication.e(), 1.0f);
        float e2 = nrr.e(BaseApplication.e(), 9.0f);
        float f = textSize - e;
        LogUtil.d("BloodSugarSportMonitoringView", "setAutoWidthTextView nowTextSize is: ", Float.valueOf(f));
        if (f >= e2) {
            healthTextView2.setTextSize(0, f);
            healthTextView3.setTextSize(0, f);
            healthTextView.setTextSize(0, f);
            c(healthTextView2, healthTextView3, healthTextView);
        }
    }

    private static void a(HealthTextView healthTextView, HealthTextView healthTextView2, qab qabVar) {
        int sportType = qabVar.getSportType();
        if (sportType != 262) {
            if (sportType == 265) {
                healthTextView.setText(UnitUtil.e(qabVar.getTotalCalories() / 1000.0d, 1, 0));
                healthTextView2.setText(R$string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit);
                return;
            } else if (sportType != 266) {
                String distanceUnit = getDistanceUnit();
                healthTextView.setText(UnitUtil.e(new BigDecimal(qabVar.getTotalDistance()).divide(new BigDecimal(1000), 2, 4).doubleValue(), 1, 2));
                healthTextView2.setText(distanceUnit);
                return;
            }
        }
        String e = e(qabVar.getTotalDistance());
        healthTextView.setText(UnitUtil.e(qabVar.getTotalDistance(), 1, 2));
        healthTextView2.setText(e);
    }

    private static String getDistanceUnit() {
        if (UnitUtil.h()) {
            return BaseApplication.e().getResources().getString(R$string.IDS_band_data_sport_distance_unit_en);
        }
        return BaseApplication.e().getResources().getString(R$string.IDS_band_data_sport_distance_unit);
    }

    private static String e(int i) {
        if (UnitUtil.h()) {
            return BaseApplication.e().getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, i);
        }
        return BaseApplication.e().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
    }

    private static void a(qab qabVar) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(qabVar.getStartTime(), qabVar.getEndTime());
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarSportMonitoringView.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj instanceof SparseArray) {
                    BloodSugarSportMonitoringView.c(obj);
                } else {
                    LogUtil.c("BloodSugarSportMonitoringView", "goToSportDetail object not SparseArray");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Object obj) {
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.c("BloodSugarSportMonitoringView", "goToSportDetail map none");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.addAll(dwo_(sparseArray));
        if (arrayList.isEmpty()) {
            LogUtil.c("BloodSugarSportMonitoringView", "goToSportDetail healthDataList isEmpty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
        if (hiHealthData == null) {
            LogUtil.c("BloodSugarSportMonitoringView", "goToSportDetail hiHealthData is null");
            return;
        }
        MotionPathSimplify e = kwz.e(hiHealthData);
        String e2 = kwz.e(hiHealthData, e);
        if (e2 == null) {
            LogUtil.c("BloodSugarSportMonitoringView", "goToSportDetail fileUrl is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", e);
        bundle.putString("contentpath", e2);
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) TrackDetailActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(268435456);
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", 0);
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_SPORT_MONITORING_2040133.value();
        hashMap.put("click", "1");
        gge.e(value, hashMap);
        gnm.aPB_(BaseApplication.e(), intent);
    }

    private static List<HiHealthData> dwo_(SparseArray<Object> sparseArray) {
        Object obj = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        if (obj instanceof List) {
            try {
                return (List) obj;
            } catch (ClassCastException unused) {
                LogUtil.e("BloodSugarSportMonitoringView", "filterHiHealthDataList, is not List<HiHealthData>");
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    public static boolean e() {
        return LanguageUtil.bc(BaseApplication.e());
    }

    /* loaded from: classes9.dex */
    static class b extends BaseHandler<BloodSugarSportMonitoringView> {
        b(BloodSugarSportMonitoringView bloodSugarSportMonitoringView) {
            super(bloodSugarSportMonitoringView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dwt_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodSugarSportMonitoringView bloodSugarSportMonitoringView, Message message) {
            if (message.what == 1 && (message.obj instanceof qab)) {
                qab qabVar = (qab) message.obj;
                if (qabVar.c() != bloodSugarSportMonitoringView.g) {
                    return;
                }
                View inflate = nsn.s() ? bloodSugarSportMonitoringView.b.inflate(R.layout.item_sport_bloodsugar_large, (ViewGroup) null) : bloodSugarSportMonitoringView.b.inflate(R.layout.item_sport_bloodsugar, (ViewGroup) null);
                BloodSugarSportMonitoringView.c(bloodSugarSportMonitoringView);
                if (bloodSugarSportMonitoringView.i == bloodSugarSportMonitoringView.h) {
                    inflate.setPadding(0, 0, 0, 0);
                }
                BloodSugarSportMonitoringView.dwr_(inflate, qabVar);
                bloodSugarSportMonitoringView.j.addView(inflate);
                bloodSugarSportMonitoringView.setVisibility(0);
            }
            if (message.what == 2) {
                bloodSugarSportMonitoringView.setVisibility(8);
            }
        }
    }

    public static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final long f9966a;
        private final long c;
        private final WeakReference<BloodSugarSportMonitoringView> d;

        a(BloodSugarSportMonitoringView bloodSugarSportMonitoringView, long j, long j2) {
            this.d = new WeakReference<>(bloodSugarSportMonitoringView);
            this.c = j;
            this.f9966a = j2;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, final Object obj) {
            if (this.d.get() == null || !(obj instanceof List) || ((List) obj).isEmpty()) {
                return;
            }
            qkh.c().b(this.c, this.f9966a, new IBaseResponseCallback() { // from class: pzu
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj2) {
                    BloodSugarSportMonitoringView.a.this.a(obj, i2, obj2);
                }
            });
        }

        public /* synthetic */ void a(Object obj, int i, Object obj2) {
            BloodSugarSportMonitoringView bloodSugarSportMonitoringView = this.d.get();
            if (bloodSugarSportMonitoringView != null && (obj2 instanceof List)) {
                List list = (List) obj2;
                bloodSugarSportMonitoringView.h = list.size();
                if (list.isEmpty()) {
                    return;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (HiHealthData hiHealthData : (List) obj) {
                    if (hiHealthData != null) {
                        linkedHashMap.put(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf(hiHealthData.getFloatValue()));
                    }
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    bloodSugarSportMonitoringView.d((qab) it.next(), linkedHashMap);
                }
            }
        }
    }
}
