package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarFeedbackActivity;
import defpackage.gge;
import defpackage.gnm;
import defpackage.kpt;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.pzz;
import defpackage.qab;
import defpackage.qjv;
import defpackage.rdu;
import defpackage.scj;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class BloodSugarAnalysisSportHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9954a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private pzz f;
    private Context g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private RelativeLayout k;
    private HealthTextView l;
    private LinearLayout m;
    private HealthDivider n;
    private HealthDivider o;
    private LinearLayout q;
    private View s;

    public BloodSugarAnalysisSportHolder(View view) {
        super(view);
        this.g = view.getContext();
        this.s = view;
        this.q = (LinearLayout) view.findViewById(R.id.blood_sugar_sport_before);
        this.k = (RelativeLayout) view.findViewById(R.id.blood_sugar_sport_sport);
        this.m = (LinearLayout) view.findViewById(R.id.blood_sugar_sport_after);
        this.l = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_description);
        this.o = (HealthDivider) view.findViewById(R.id.blood_sugar_sport_des_divide);
        this.n = (HealthDivider) view.findViewById(R.id.blood_sugar_sport_after_divide);
        this.i = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_before_diff_num);
        this.h = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_before_diff_status);
        this.c = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_before_diff_period);
        this.j = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_before_dietTime);
        this.e = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_after_diff_num);
        this.d = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_after_diff_status);
        this.b = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_after_diff_period);
        this.f9954a = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_after_dietTime);
        this.q.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.m.setOnClickListener(this);
    }

    public void c(pzz pzzVar) {
        if (pzzVar == null) {
            LogUtil.h("BloodSugarAnalysisSportHolder", "BloodSugarSportBean is null!");
            return;
        }
        this.f = pzzVar;
        String a2 = pzzVar.a();
        if (a2 == null || "".equals(a2)) {
            this.l.setVisibility(8);
            this.o.setVisibility(8);
        } else {
            this.l.setVisibility(0);
            this.o.setVisibility(0);
            this.l.setText(a2);
        }
        this.i.setText(b(pzzVar.b().getFloatValue()));
        a(this.h, pzzVar.b().getType(), pzzVar.b().getFloatValue());
        e(this.c, pzzVar.b().getType());
        this.j.setText(UnitUtil.a("HH:mm", pzzVar.b().getStartTime()));
        dvO_(this.s, pzzVar.e());
        if (pzzVar.d().getFloatValue() == 0.0f || pzzVar.d().getStartTime() == 0) {
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        this.m.setVisibility(0);
        this.n.setVisibility(0);
        this.e.setText(b(pzzVar.d().getFloatValue()));
        a(this.d, pzzVar.d().getType(), pzzVar.d().getFloatValue());
        e(this.b, pzzVar.d().getType());
        this.f9954a.setText(UnitUtil.a("HH:mm", pzzVar.d().getStartTime()));
    }

    private static void dvO_(View view, qab qabVar) {
        d((HealthTextView) view.findViewById(R.id.blood_sugar_sport_sport_distance), (HealthTextView) view.findViewById(R.id.blood_sugar_sport_sport_distance_unit), qabVar);
        ((HealthTextView) view.findViewById(R.id.blood_sugar_sport_sportStart)).setText(UnitUtil.a("HH:mm", qabVar.getStartTime()));
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_heartRate);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.blood_sugar_sport_heartRate_unit);
        ImageView imageView = (ImageView) view.findViewById(R.id.blood_sugar_sport_heartRate_img);
        if (qabVar.getAvgHeartRate() == 0 && qabVar.getTotalCalories() == 0) {
            healthTextView.setVisibility(8);
            healthTextView2.setVisibility(8);
            imageView.setVisibility(8);
        } else if (qabVar.getAvgHeartRate() > 0) {
            healthTextView.setVisibility(0);
            healthTextView2.setVisibility(0);
            imageView.setVisibility(0);
            healthTextView.setText(String.valueOf(UnitUtil.e(qabVar.getAvgHeartRate(), 1, 0)));
            healthTextView2.setText(R$string.IDS_main_watch_heart_rate_unit_string);
            if (d()) {
                imageView.setBackground(nrz.cKn_(BaseApplication.e(), R.mipmap._2131820823_res_0x7f110117));
            } else {
                imageView.setBackgroundResource(R.mipmap._2131820823_res_0x7f110117);
            }
        } else {
            healthTextView.setVisibility(0);
            healthTextView2.setVisibility(0);
            imageView.setVisibility(0);
            healthTextView.setText(UnitUtil.e(qabVar.getTotalCalories() / 1000.0d, 1, 0));
            healthTextView2.setText(R$string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit);
            if (d()) {
                imageView.setBackground(nrz.cKn_(BaseApplication.e(), R.mipmap._2131820824_res_0x7f110118));
            } else {
                imageView.setBackgroundResource(R.mipmap._2131820824_res_0x7f110118);
            }
        }
        dvN_(view, qabVar);
    }

    private static void dvN_(View view, qab qabVar) {
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        ((HealthTextView) view.findViewById(R.id.blood_sugar_sport_sport_duration)).setText(UnitUtil.d((int) TimeUnit.MILLISECONDS.toSeconds(qabVar.getTotalTime())));
        ImageView imageView = (ImageView) view.findViewById(R.id.blood_sugar_sport_sport_img_bg);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.blood_sugar_sport_sport_img);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.blood_sugar_sport_sport_duration_img);
        Drawable dMy_ = rdu.dMy_(context, qabVar.getSportType());
        imageView.setBackground(nrf.cJH_(context.getDrawable(R.drawable._2131430592_res_0x7f0b0cc0), rdu.b(context, qabVar.getSportType())));
        if (d()) {
            imageView2.setBackground(nrz.cKm_(context, dMy_));
            imageView3.setImageDrawable(nrz.cKn_(context, R.mipmap._2131820900_res_0x7f110164));
        } else {
            imageView2.setBackground(dMy_);
            imageView3.setBackgroundResource(R.mipmap._2131820900_res_0x7f110164);
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", 0);
        hashMap.put(ParsedFieldTag.NPES_SPORT_TIME, Long.valueOf(qabVar.getStartTime()));
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_SPORT_2040139.value();
        hashMap.put("click", "1");
        gge.e(value, hashMap);
    }

    private static void d(HealthTextView healthTextView, HealthTextView healthTextView2, qab qabVar) {
        int sportType = qabVar.getSportType();
        if (sportType != 262) {
            if (sportType == 265) {
                healthTextView.setText(UnitUtil.e(qabVar.getTotalCalories() / 1000.0d, 1, 0));
                healthTextView2.setText(R$string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit);
                return;
            } else if (sportType != 266) {
                String a2 = a();
                healthTextView.setText(UnitUtil.e(new BigDecimal(qabVar.getTotalDistance()).divide(new BigDecimal(1000), 2, 4).doubleValue(), 1, 2));
                healthTextView2.setText(a2);
                return;
            }
        }
        String b = b(qabVar.getTotalDistance());
        healthTextView.setText(UnitUtil.e(qabVar.getTotalDistance(), 1, 2));
        healthTextView2.setText(b);
    }

    private static String b(int i) {
        if (UnitUtil.h()) {
            return BaseApplication.e().getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, i);
        }
        return BaseApplication.e().getString(R$string.IDS_fitness_data_list_activity_meter_unit);
    }

    private static String a() {
        if (UnitUtil.h()) {
            return BaseApplication.e().getResources().getString(R$string.IDS_band_data_sport_distance_unit_en);
        }
        return BaseApplication.e().getResources().getString(R$string.IDS_band_data_sport_distance_unit);
    }

    public static boolean d() {
        return LanguageUtil.bc(BaseApplication.e());
    }

    private String b(float f) {
        return UnitUtil.e(f, 1, 1) + this.g.getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol);
    }

    private void a(HealthTextView healthTextView, int i, float f) {
        try {
            int parseInt = Integer.parseInt(qjv.a(this.g, i, f).get("HEALTH_BLOOD_SUGAR_LEVEL_KEY"));
            if (parseInt != 1) {
                if (parseInt != 2) {
                    switch (parseInt) {
                        case 1001:
                            b(healthTextView, R$string.IDS_hw_show_healthdata_bloodsugar_status_too_low, R.color._2131296797_res_0x7f09021d);
                            break;
                        case 1002:
                            b(healthTextView, R$string.IDS_hw_health_show_healthdata_status_low, R.color._2131296797_res_0x7f09021d);
                            break;
                        case 1003:
                            break;
                        case 1004:
                            break;
                        case 1005:
                        case 1006:
                            b(healthTextView, R$string.IDS_hw_show_healthdata_bloodsugar_status_too_high, R.color._2131296795_res_0x7f09021b);
                            break;
                        default:
                            Object[] objArr = new Object[1];
                            objArr[0] = "no support blood type";
                            LogUtil.h("BloodSugarAnalysisSportHolder", objArr);
                            break;
                    }
                }
                b(healthTextView, R$string.IDS_hw_health_show_healthdata_status_high, R.color._2131296795_res_0x7f09021b);
            }
            b(healthTextView, R$string.IDS_hw_health_show_healthdata_status_normal, R.color._2131296799_res_0x7f09021f);
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodSugarAnalysisSportHolder", "NumberFormatException : setDiffStatusText key can not parseInt");
        }
    }

    private void b(HealthTextView healthTextView, int i, int i2) {
        healthTextView.setText(this.g.getResources().getString(i));
        healthTextView.setTextColor(this.g.getResources().getColor(i2));
    }

    private void e(HealthTextView healthTextView, int i) {
        if (i != 2106) {
            switch (i) {
                case 2008:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_breakfast));
                    break;
                case 2009:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_breakfast));
                    break;
                case 2010:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_lunch));
                    break;
                case 2011:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_lunch));
                    break;
                case 2012:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_dinner));
                    break;
                case 2013:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_after_dinner));
                    break;
                case 2014:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_before_sleep));
                    break;
                case 2015:
                    healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_early_morning));
                    break;
                default:
                    LogUtil.h("BloodSugarAnalysisSportHolder", "no support Period");
                    break;
            }
        }
        healthTextView.setText(this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_random_time));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        pzz pzzVar = this.f;
        if (pzzVar == null) {
            LogUtil.h("BloodSugarAnalysisSportHolder", "onClick mData is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view == this.k) {
                b(pzzVar.e());
            } else {
                dvM_(view);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void dvM_(View view) {
        Intent intent = new Intent();
        intent.setClass(this.g, BloodSugarFeedbackActivity.class);
        if (view == this.q) {
            intent.putExtra("bloodTimePeriod", this.f.b().getType());
            intent.putExtra("time", this.f.b().getStartTime());
            intent.putExtra("bloodNum", CommonUtil.a(String.valueOf(this.f.b().getFloatValue())));
            intent.putExtra("bloodSugarDataIsFromMeter", scj.d(this.f.b().getInt("trackdata_deviceType")));
            intent.putExtra("clientId", this.f.b().getClientId());
            intent.putExtra(ParsedFieldTag.TASK_MODIFY_TIME, this.f.b().getModifiedTime());
            e(0, AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_SPORT_FINGER_2040136.value());
        } else {
            intent.putExtra("bloodTimePeriod", this.f.d().getType());
            intent.putExtra("time", this.f.d().getStartTime());
            intent.putExtra("bloodNum", CommonUtil.a(String.valueOf(this.f.d().getFloatValue())));
            intent.putExtra("bloodSugarDataIsFromMeter", scj.d(this.f.d().getInt("trackdata_deviceType")));
            intent.putExtra("clientId", this.f.d().getClientId());
            intent.putExtra(ParsedFieldTag.TASK_MODIFY_TIME, this.f.d().getModifiedTime());
            e(1, AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_SPORT_FINGER_2040136.value());
        }
        intent.putExtra("isEdit", true);
        intent.putExtra("titleName", this.g.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_history));
        intent.putExtra("pageFrom", 1);
        intent.addFlags(268435456);
        gnm.aPB_(BaseApplication.e(), intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(int i, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("click", "1");
        gge.e(str, hashMap);
    }

    private static void b(qab qabVar) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(qabVar.getStartTime(), qabVar.getEndTime());
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarAnalysisSportHolder.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("BloodSugarAnalysisSportHolder", "goToSportDetail object not SparseArray");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("BloodSugarAnalysisSportHolder", "goToSportDetail map none");
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                arrayList.addAll(BloodSugarAnalysisSportHolder.dvL_(sparseArray));
                if (arrayList.isEmpty()) {
                    LogUtil.h("BloodSugarAnalysisSportHolder", "goToSportDetail healthDataList isEmpty");
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) arrayList.get(0);
                if (hiHealthData == null) {
                    LogUtil.h("BloodSugarAnalysisSportHolder", "goToSportDetail hiHealthData is null");
                    return;
                }
                MotionPathSimplify e = kpt.e(hiHealthData);
                String a2 = kpt.a(hiHealthData, e);
                if (a2 == null) {
                    LogUtil.h("BloodSugarAnalysisSportHolder", "goToSportDetail fileUrl is null");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("simplifyDatakey", e);
                bundle.putString("contentpath", a2);
                Intent intent = new Intent(BaseApplication.e(), (Class<?>) TrackDetailActivity.class);
                intent.putExtras(bundle);
                intent.addFlags(268435456);
                BloodSugarAnalysisSportHolder.e(1, AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_SPORT_MONITORING_2040133.value());
                gnm.aPB_(BaseApplication.e(), intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<HiHealthData> dvL_(SparseArray<Object> sparseArray) {
        Object obj = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        if (obj instanceof List) {
            try {
                return (List) obj;
            } catch (ClassCastException unused) {
                health.compact.a.util.LogUtil.e("BloodSugarAnalysisSportHolder", "filterHiHealthDataList, is not List<HiHealthData>");
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }
}
