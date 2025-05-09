package com.huawei.ui.main.stories.health.temperature.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.activity.TemperatureWarningActivity;
import com.huawei.ui.main.stories.health.temperature.provider.TemperatureDayCardProvider;
import defpackage.eek;
import defpackage.gge;
import defpackage.jll;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qny;
import defpackage.qpg;
import defpackage.qpr;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class TemperatureDayCardProvider extends MinorProvider<qpg> {
    private static final String b = "KnitHealthData_CardSelected_" + DataInfos.TemperatureDayDetail;
    private static final int e = DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_ALARM.value();

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f10240a;
    private qpg h;
    private CustomViewDialog i;
    private List<eek> d = new ArrayList();
    private boolean g = Utils.o();
    private int c = 0;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        sectionBean.a(e(BaseApplication.getContext()));
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("TemperatureDayCardProvider", "loadData");
        if (sectionBean == null || this.h == null) {
            return;
        }
        LogUtil.a("TemperatureDayCardProvider", "sectionBean setData");
        sectionBean.e(this.h);
    }

    private HashMap<String, Object> e(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        LogUtil.a("TemperatureDayCardProvider", "generateDefaultViewMap");
        hashMap.put("REMIND_SHOW_MODEL", true);
        hashMap.put("REMIND_TEXT", context.getString(R$string.IDS_temperature_warning));
        hashMap.put("REMIND_HISTORY_TEXT", context.getString(R$string.IDS_temperature_history_warning));
        ArrayList arrayList = new ArrayList(2);
        Integer valueOf = Integer.valueOf(R.mipmap._2131821129_res_0x7f110249);
        arrayList.add(valueOf);
        arrayList.add(valueOf);
        hashMap.put("REMIND_IMG_VIEW", arrayList);
        hashMap.put("REMIND_ARROWS", Integer.valueOf(R.drawable._2131429721_res_0x7f0b0959));
        hashMap.put("LOW_REMIND_TEXT", context.getString(R$string.IDS_temperature_warning_low));
        hashMap.put("HIGH_REMIND_TEXT", context.getString(R$string.IDS_temperature_warning_high));
        return hashMap;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qpg qpgVar) {
        LogUtil.a("TemperatureDayCardProvider", "parseParams");
        this.f10240a = new WeakReference<>(context);
        this.h = qpgVar;
        Map<String, SpannableStringBuilder> a2 = qpgVar.a();
        qny qnyVar = new qny();
        if (!a2.isEmpty()) {
            LogUtil.a("TemperatureDayCardProvider", "putTemperature");
            a2.put("TEMPERATURE_MIN_MAX", a2.get("TEMPERATURE_MIN_MAX"));
            a2.put("SKIN_TEMPERATURE_MIN_MAX", a2.get("SKIN_TEMPERATURE_MIN_MAX"));
        }
        hashMap.put("TEMPERATURE_CARD_DATA", qnyVar.d(a2, nsf.h(R$string.IDS_settings_health_temperature), nsf.h(R$string.IDS_health_skin_temperature)));
        LogUtil.a("TemperatureDayCardProvider", "data type is " + qpgVar.b());
        LogUtil.a("TemperatureDayCardProvider", "remind data is empty ? " + koq.b(qpgVar.e()));
        LogUtil.a("TemperatureDayCardProvider", "is oversea " + this.g);
        if (koq.b(qpgVar.e()) || this.g || "SKIN_TEMPERATURE_MIN_MAX".equals(qpgVar.b())) {
            LogUtil.a("TemperatureDayCardProvider", "not show remind view");
            hashMap.put("IS_REMIND_SHOW", false);
        } else {
            LogUtil.a("TemperatureDayCardProvider", "show remind view");
            hashMap.put("IS_REMIND_SHOW", true);
        }
        d(hashMap, qpgVar);
        c(hashMap);
    }

    private void d(HashMap<String, Object> hashMap, qpg qpgVar) {
        List<HiHealthData> e2 = qpgVar.e();
        if (koq.b(e2)) {
            return;
        }
        this.d.clear();
        Collections.sort(e2, new Comparator() { // from class: qpl
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((HiHealthData) obj2).getEndTime(), ((HiHealthData) obj).getEndTime());
                return compare;
            }
        });
        if (e2.size() > 2) {
            e2 = e2.subList(0, 2);
        }
        this.d.addAll(d(e2));
        hashMap.put("REMIND_DATA", this.d);
    }

    private void c(Map<String, Object> map) {
        final Context context = this.f10240a.get();
        if (context == null) {
            LogUtil.h("TemperatureDayCardProvider", "setClickListener: context is null");
        } else {
            map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureDayCardProvider.2
                @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
                public void onClick(int i) {
                }

                @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
                public void onClick(int i, int i2) {
                }

                @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
                public void onClick(int i, String str) {
                }

                @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
                public void onClick(String str) {
                    try {
                        if ("TEMPERATURE_SELECT".equals(str)) {
                            ObserverManagerUtil.c(TemperatureDayCardProvider.b, "TEMPERATURE_MIN_MAX");
                            return;
                        }
                        if ("SKIN_TEMPERATURE_SELECT".equals(str)) {
                            ObserverManagerUtil.c(TemperatureDayCardProvider.b, "SKIN_TEMPERATURE_MIN_MAX");
                            return;
                        }
                        if ("REMIND_WARNING_TIP_IMG".equals(str)) {
                            if (nsn.o()) {
                                LogUtil.b("TemperatureDayCardProvider", "remind warning tip imp isFastClick");
                                return;
                            } else {
                                gge.e(AnalyticsValue.TEMPERATURE_REMIND_TIPS_2060077.value());
                                TemperatureDayCardProvider.this.e();
                                return;
                            }
                        }
                        if ("REMIND_TITLE".equals(str)) {
                            if (nsn.o()) {
                                LogUtil.b("TemperatureDayCardProvider", "remind title isFastClick");
                                return;
                            } else {
                                gge.e(AnalyticsValue.TEMPERATURE_REMIND_2060078.value());
                                context.startActivity(new Intent(context, (Class<?>) TemperatureWarningActivity.class));
                                return;
                            }
                        }
                        if ("REMIND_HISTORY_VIEW".equals(str)) {
                            if (nsn.o()) {
                                LogUtil.b("TemperatureDayCardProvider", "remind histroy text isFastClick");
                                return;
                            } else {
                                gge.e(AnalyticsValue.TEMPERATURE_REMIND_2060078.value());
                                context.startActivity(new Intent(context, (Class<?>) TemperatureWarningActivity.class));
                                return;
                            }
                        }
                        LogUtil.a("TemperatureDayCardProvider", "useless click");
                    } catch (ActivityNotFoundException e2) {
                        LogUtil.b("TemperatureDayCardProvider", "ActivityNotFoundException", e2.getMessage());
                    }
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private List<eek> d(List<HiHealthData> list) {
        String string;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("TemperatureDayCardProvider", "convert list is empty");
            return arrayList;
        }
        Context context = BaseApplication.getContext();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                eek eekVar = new eek(hiHealthData.getStartTime(), hiHealthData.getEndTime());
                eekVar.a(hiHealthData.getType());
                long endTime = hiHealthData.getEndTime() - 600000;
                long endTime2 = hiHealthData.getEndTime();
                eekVar.c(DateUtils.formatDateRange(context, endTime, endTime2, 137));
                eekVar.b(DateUtils.formatDateRange(context, endTime, endTime2, 153));
                if (hiHealthData.getType() == e) {
                    String e2 = UnitUtil.e(qpr.d(37.2f), 1, 1);
                    if (UnitUtil.d()) {
                        string = BaseApplication.getContext().getString(com.huawei.health.servicesui.R$string.IDS_temp_over_suspected_celsius_unit, e2);
                    } else {
                        string = BaseApplication.getContext().getString(com.huawei.health.servicesui.R$string.IDS_temp_over_suspected_fahrenheit_unit, e2);
                    }
                } else {
                    String a2 = a(hiHealthData.getMetaData());
                    if (!a2.isEmpty()) {
                        if (UnitUtil.d()) {
                            string = context.getResources().getString(R$string.IDS_settings_health_temperature_unit, a2);
                        } else {
                            string = context.getResources().getString(R$string.IDS_temp_unit_fahrenheit, a2);
                        }
                    }
                }
                eekVar.a(string);
                arrayList.add(eekVar);
            }
        }
        return arrayList;
    }

    private static String a(String str) {
        String string;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TemperatureDayCardProvider", "getCelsiusRange metaData is empty");
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            float f = 0.0f;
            float f2 = 0.0f;
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj = jSONArray.get(i);
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (i == 0) {
                        f = intValue;
                    }
                    float f3 = intValue;
                    f = Math.min(f, f3);
                    f2 = Math.max(f2, f3);
                } else {
                    LogUtil.h("TemperatureDayCardProvider", "convert no Integer");
                }
            }
            if (f == 0.0f || f2 == 0.0f) {
                LogUtil.h("TemperatureDayCardProvider", "max or min error");
                return "";
            }
            String e2 = e(f / 10.0f);
            String e3 = e(f2 / 10.0f);
            if (UnitUtil.d()) {
                string = BaseApplication.getContext().getString(R$string.IDS_temperature_warning_celsius);
            } else {
                string = BaseApplication.getContext().getString(R$string.IDS_temperature_warning_fahrenheit);
            }
            return String.format(Locale.ENGLISH, string, e2, e3);
        } catch (JSONException unused) {
            LogUtil.b("TemperatureDayCardProvider", "convert JSONException");
            return "";
        }
    }

    public static String e(float f) {
        if (f == 0.0f) {
            return String.valueOf(f);
        }
        if (UnitUtil.d()) {
            return UnitUtil.e(f, 1, 1);
        }
        return UnitUtil.e((f * 1.8f) + 32.0f, 1, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.b("TemperatureDayCardProvider", "start showTipDialog");
        final Context context = this.f10240a.get();
        if (context == null) {
            LogUtil.h("TemperatureDayCardProvider", "showTipDialog: context is null");
            return;
        }
        HealthTextView healthTextView = new HealthTextView(context);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        String str = context.getString(R$string.IDS_temperature_warning_tip) + " ";
        String string = context.getString(R$string.IDS_temperature_warning_set);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str);
        if (jll.c()) {
            LogUtil.b("TemperatureDayCardProvider", "is Device Sport TemperatureMain");
            spannableStringBuilder.append((CharSequence) string);
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.huawei.ui.main.stories.health.temperature.provider.TemperatureDayCardProvider.4
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (TemperatureDayCardProvider.this.i != null) {
                        Intent intent = new Intent();
                        intent.setClassName(context, "com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity");
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e2) {
                            LogUtil.b("TemperatureDayCardProvider", "ActivityNotFoundException", e2.getMessage());
                        }
                        TemperatureDayCardProvider.this.i.cancel();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(ContextCompat.getColor(context, R.color._2131296651_res_0x7f09018b));
                    textPaint.setUnderlineText(false);
                }
            }, str.length(), spannableStringBuilder.length(), 17);
        }
        healthTextView.setText(spannableStringBuilder);
        healthTextView.setTextAppearance(context, R.style.CustomDialog_message);
        CustomViewDialog e2 = new CustomViewDialog.Builder(context).czg_(healthTextView).cze_(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: qpn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureDayCardProvider.this.dGW_(view);
            }
        }).e();
        this.i = e2;
        e2.show();
    }

    public /* synthetic */ void dGW_(View view) {
        CustomViewDialog customViewDialog = this.i;
        if (customViewDialog != null) {
            customViewDialog.cancel();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qpg qpgVar) {
        LogUtil.a("TemperatureDayCardProvider", "onSetSectionBeanData");
        if (qpgVar.a().isEmpty() || this.g) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(qpgVar);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "TemperatureDayCardProvider";
    }
}
