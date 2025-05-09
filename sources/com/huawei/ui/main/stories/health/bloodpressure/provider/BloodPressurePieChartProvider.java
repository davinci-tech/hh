package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureDescriptionActivity;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.eeu;
import defpackage.koq;
import defpackage.nkz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qhm;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class BloodPressurePieChartProvider extends MinorProvider<qhm> {
    private DataInfos d = DataInfos.NoDataPlaceHolder;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        sectionBean.a(a(BaseApplication.getContext()));
    }

    private List<nkz> b(qhm qhmVar) {
        ArrayList arrayList = new ArrayList(16);
        c(qhmVar.e(5), arrayList);
        d(qhmVar.e(4), arrayList);
        b(qhmVar.e(3), arrayList);
        h(qhmVar.e(2), arrayList);
        a(qhmVar.e(1), arrayList);
        e(qhmVar.e(0), arrayList);
        return arrayList;
    }

    private Map<String, List<nkz>> e(qhm qhmVar) {
        HashMap hashMap = new HashMap(16);
        for (Map.Entry entry : new HashMap(qhmVar.k()).entrySet()) {
            ArrayList arrayList = new ArrayList(16);
            c(qhmVar.c((Map) entry.getValue(), 5), arrayList);
            d(qhmVar.c((Map) entry.getValue(), 4), arrayList);
            b(qhmVar.c((Map) entry.getValue(), 3), arrayList);
            h(qhmVar.c((Map) entry.getValue(), 2), arrayList);
            a(qhmVar.c((Map) entry.getValue(), 1), arrayList);
            e(qhmVar.c((Map) entry.getValue(), 0), arrayList);
            hashMap.put((String) entry.getKey(), arrayList);
        }
        return hashMap;
    }

    private void c(int i, List<nkz> list) {
        if (i != 0) {
            LogUtil.a("BloodPressurePieChartProvider", "highCount != 0");
            list.add(new nkz(eeu.c(5), i, eeu.d(5), b(5)));
        }
    }

    private void d(int i, List<nkz> list) {
        if (i != 0) {
            LogUtil.a("BloodPressurePieChartProvider", "moderateCount != 0");
            list.add(new nkz(eeu.c(4), i, eeu.d(4), b(4)));
        }
    }

    private void b(int i, List<nkz> list) {
        if (i != 0) {
            LogUtil.a("BloodPressurePieChartProvider", "mildCount != 0");
            list.add(new nkz(eeu.c(3), i, eeu.d(3), b(3)));
        }
    }

    private void h(int i, List<nkz> list) {
        if (i != 0) {
            LogUtil.a("BloodPressurePieChartProvider", "normalCount != 0");
            list.add(new nkz(eeu.c(2), i, eeu.d(2), b(2)));
        }
    }

    private void a(int i, List<nkz> list) {
        if (i != 0) {
            LogUtil.a("BloodPressurePieChartProvider", "idealCount != 0");
            list.add(new nkz(eeu.c(1), i, eeu.d(1), b(1)));
        }
    }

    private void e(int i, List<nkz> list) {
        if (i != 0) {
            LogUtil.a("BloodPressurePieChartProvider", "lowCount != 0");
            list.add(new nkz(eeu.c(0), i, eeu.d(0), b(0)));
        }
    }

    private int b(int i) {
        int c = eeu.c();
        Context context = BaseApplication.getContext();
        if (i == 0) {
            return ContextCompat.getColor(context, R.color._2131296524_res_0x7f09010c);
        }
        if (i == 1) {
            return ContextCompat.getColor(context, R.color._2131296522_res_0x7f09010a);
        }
        if (i == 2) {
            return ContextCompat.getColor(context, R.color._2131296531_res_0x7f090113);
        }
        if (i == 3) {
            if (c == 6) {
                return ContextCompat.getColor(context, R.color._2131296527_res_0x7f09010f);
            }
            if (c == 5) {
                return ContextCompat.getColor(context, R.color._2131296529_res_0x7f090111);
            }
            return ContextCompat.getColor(context, R.color._2131296520_res_0x7f090108);
        }
        if (i != 4) {
            if (i == 5) {
                return ContextCompat.getColor(context, R.color._2131296520_res_0x7f090108);
            }
            return ContextCompat.getColor(context, R.color.emui_accent);
        }
        if (c == 6) {
            return ContextCompat.getColor(context, R.color._2131296529_res_0x7f090111);
        }
        return ContextCompat.getColor(context, R.color._2131296520_res_0x7f090108);
    }

    private void d(Context context, HashMap<String, Object> hashMap, qhm qhmVar) {
        String str;
        int i;
        String str2;
        String str3;
        hashMap.put("AVE_BLOOD_PRESSURE_TYPE", qhmVar.e());
        hashMap.put("BASIC_DATA", qhmVar.h());
        hashMap.put("TIME_SHARING_BASIC_DATA", qhmVar.n());
        if (this.d == DataInfos.BloodPressureWeekDetail) {
            str = context.getString(R$string.IDS_blood_pressure_week_basic_situation);
            str2 = context.getString(R$string.IDS_blood_pressure_this_week);
            str3 = context.getString(R$string.IDS_blood_pressure_last_week);
            i = 2;
        } else if (this.d == DataInfos.BloodPressureMonthDetail) {
            str = context.getString(R$string.IDS_blood_pressure_month_basic_situation);
            str2 = context.getString(R$string.IDS_blood_pressure_this_month);
            str3 = context.getString(R$string.IDS_blood_pressure_last_month);
            i = 3;
        } else {
            LogUtil.i("BloodPressurePieChartProvider", "no header found for ", this.d);
            str = null;
            i = 1;
            str2 = null;
            str3 = null;
        }
        hashMap.put("BASIC_HEADER_TITLE", str);
        hashMap.put("TREND_DATA", qhmVar.l());
        hashMap.put("TREND_HEADER_TITLE", context.getString(R$string.IDS_blood_pressure_measure_data_trend));
        hashMap.put("TIME_SHARING_TITLE", context.getString(R$string.IDS_blood_pressure_time_sharing_fluctuation));
        hashMap.put("TREND_INDICATOR_TITLE", context.getString(R$string.IDS_weight_index));
        hashMap.put("TREND_TITLE_PRIMARY", str2);
        hashMap.put("TREND_TITLE_SECONDARY", str3);
        hashMap.put("DATA_INFO_INDEX", Integer.valueOf(i));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(final Context context, HashMap<String, Object> hashMap, qhm qhmVar) {
        hashMap.put("SECOND_LAYER_RING_CHART", b(qhmVar));
        hashMap.put("TIME_SHARING_RING_CHART", e(qhmVar));
        d(context, hashMap, qhmVar);
        double d = qhmVar.d();
        String e = d <= 0.0d ? "--" : UnitUtil.e((int) d, 1, 0);
        hashMap.put("FIRST_LAYER_RIGHT_RIGHT", BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903084_res_0x7f03002c, (int) d).replace("%d", ""));
        double a2 = qhmVar.a();
        double b = qhmVar.b();
        hashMap.put("FIRST_LAYER_LEFT_LEFT", nsf.a(R.plurals._2130903433_res_0x7f030189, UnitUtil.e(b, Locale.getDefault()), UnitUtil.e(a2, 1, 0), UnitUtil.e(b, 1, 0)));
        hashMap.put("FIRST_LAYER_RIGHT_LEFT", e);
        hashMap.put("SECOND_LAYER_RING_CHART_LEGEND_COLOR", Integer.valueOf(ContextCompat.getColor(context, R.color._2131299241_res_0x7f090ba9)));
        hashMap.put("CLICK_EVENT_LISTENER", new BaseKnitDataProvider.d() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressurePieChartProvider.2
            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (!nsn.a(500) && str.equals("THIRD_LAYER_CLICK_TEXT")) {
                    try {
                        context.startActivity(new Intent(context, (Class<?>) BloodPressureDescriptionActivity.class));
                    } catch (ActivityNotFoundException e2) {
                        LogUtil.b("BloodPressurePieChartProvider", "ActivityNotFoundException", e2.getMessage());
                    }
                }
            }
        });
    }

    private HashMap<String, Object> a(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FIRST_LAYER_LEFT_TOP", context.getString(R$string.IDS_hw_mean_blood_pressure));
        Bundle extra = getExtra();
        if (extra != null) {
            hashMap.put(KnitHealthDetailActivity.KEY_SUB_PAGE_INDEX, Integer.valueOf(extra.getInt(KnitHealthDetailActivity.KEY_SUB_PAGE_INDEX, 0)));
        }
        hashMap.put("FIRST_LAYER_RIGHT_TOP", context.getString(R$string.IDS_hw_ave_pulse_value));
        hashMap.put("SECOND_LAYER_RING_CHART_VALUE_UNIT_RES_ID", Integer.valueOf(R.plurals._2130903213_res_0x7f0300ad));
        SpannableString spannableString = new SpannableString(context.getString(R$string.IDS_hw_pressure_learn_more));
        hashMap.put("THIRD_LAYER_TEXT", BloodPressureUtil.c(0));
        hashMap.put("THIRD_LAYER_CLICK_TEXT", spannableString);
        hashMap.put("THIRD_LAYER_CLICK_TEXT_COLOR", Integer.valueOf(context.getColor(R.color._2131296532_res_0x7f090114)));
        Bundle extra2 = getExtra();
        Serializable serializable = extra2 != null ? extra2.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        DataInfos dataInfos = serializable instanceof DataInfos ? (DataInfos) serializable : DataInfos.NoDataPlaceHolder;
        this.d = dataInfos;
        hashMap.put("DATA_INFO", dataInfos);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qhm qhmVar) {
        if (koq.b(qhmVar.j())) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(qhmVar);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "BloodPressurePieChartProvider";
    }
}
