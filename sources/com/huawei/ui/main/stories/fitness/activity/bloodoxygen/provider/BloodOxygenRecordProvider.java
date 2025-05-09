package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity;
import defpackage.bkz;
import defpackage.eds;
import defpackage.efb;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jed;
import defpackage.nsn;
import defpackage.pkr;
import defpackage.rre;
import defpackage.rsn;
import defpackage.scg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public class BloodOxygenRecordProvider extends MinorProvider<pkr> {

    /* renamed from: a, reason: collision with root package name */
    pkr f9766a;
    Context b;

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        sectionBean.a(c(BaseApplication.getContext()));
    }

    private HashMap<String, Object> c(Context context) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (efb.c()) {
            hashMap.put("TITLE", context.getString(R$string.IDS_hw_health_lower_spo2_high_risk));
        } else {
            hashMap.put("TITLE", context.getString(R$string.IDS_hw_health_blood_oxygen_lower_spo2));
        }
        hashMap.put("SHOWMORE", context.getString(R$string.IDS_hw_health_blood_oxygen_more_records));
        hashMap.put("TITLE_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_assessment));
        hashMap.put("RIGHT_ICON", Integer.valueOf(R.drawable._2131427842_res_0x7f0b0202));
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_weight_percent);
        hashMap.put("LEFT_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_risk));
        hashMap.put("MIDDLE_UNIT", string);
        String string2 = BaseApplication.getContext().getResources().getString(com.huawei.health.servicesui.R$string.IDS_fitness_data_list_activity_meter_unit);
        hashMap.put("MIDDLE_TEXT", context.getString(R$string.IDS_hw_show_main_permission_blood_oxygen));
        hashMap.put("RIGHT_UNIT", string2);
        hashMap.put("RIGHT_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_elevation));
        hashMap.put("EXTRA_LEFT_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_lower_spo2));
        hashMap.put("EXTRA_RIGHT_ICON", Integer.valueOf(R.drawable._2131427842_res_0x7f0b0202));
        hashMap.put("EXTRA_BOTTOM_TEXT", context.getString(R$string.IDS_hw_health_blood_oxygen_elevation));
        hashMap.put("EXTRA_BOTTOM_UNIT", string2);
        return hashMap;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, pkr pkrVar) {
        this.f9766a = pkrVar;
        this.b = BaseApplication.getContext();
        hashMap.put("VIEW_LIST", b(this.f9766a));
        e(hashMap);
    }

    private List<eds> b(pkr pkrVar) {
        if (pkrVar == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        List<HiHealthData> a2 = pkrVar.a();
        if (a2 == null) {
            return new ArrayList();
        }
        for (HiHealthData hiHealthData : a2) {
            if (hiHealthData.getInt("bloodOxygenCardKey") == 1001) {
                if (hiHealthData.getInt("altitudeKey") == Integer.MIN_VALUE) {
                    LogUtil.a("BloodOxygenRecordProvider", "The altitude data is being synchronized");
                } else {
                    arrayList.add(e(hiHealthData));
                }
            } else if (!efb.c() || hiHealthData.getInt("altitudeKey") <= 2500) {
                eds edsVar = new eds();
                edsVar.a(1);
                int intValue = hiHealthData.getIntValue();
                edsVar.b(scg.a(this.b, hiHealthData.getLong("start_time")));
                edsVar.a(jed.b(intValue, 2, 0));
                edsVar.d(scg.c(intValue));
                arrayList.add(edsVar);
            } else if (hiHealthData.getInt("altitudeKey") == Integer.MIN_VALUE) {
                LogUtil.a("BloodOxygenRecordProvider", "The altitude data is being synchronized");
            } else {
                eds edsVar2 = new eds();
                edsVar2.a(2);
                int intValue2 = hiHealthData.getIntValue();
                edsVar2.b(scg.a(this.b, hiHealthData.getLong("start_time")));
                edsVar2.a(jed.b(intValue2, 2, 0));
                edsVar2.d(scg.c(intValue2));
                edsVar2.c(rre.e(hiHealthData.getInt("altitudeKey")));
                arrayList.add(edsVar2);
            }
        }
        return arrayList;
    }

    private void c(int i, eds edsVar) {
        String h = scg.h(i);
        if (h.equals(this.b.getString(R$string.IDS_hw_health_blood_oxygen_mild))) {
            edsVar.agC_(this.b.getResources().getDrawable(R.drawable._2131431380_res_0x7f0b0fd4));
            edsVar.d(h);
        } else if (h.equals(this.b.getString(R$string.IDS_hw_health_blood_oxygen_moderate))) {
            edsVar.agC_(this.b.getResources().getDrawable(R.drawable._2131431378_res_0x7f0b0fd2));
            edsVar.d(h);
        } else if (h.equals(this.b.getString(R$string.IDS_hw_health_blood_oxygen_severe))) {
            edsVar.agC_(this.b.getResources().getDrawable(R.drawable._2131431379_res_0x7f0b0fd3));
            edsVar.d(h);
        } else {
            edsVar.agC_(this.b.getResources().getDrawable(R.drawable._2131431377_res_0x7f0b0fd1));
            edsVar.d(h);
        }
    }

    private eds e(HiHealthData hiHealthData) {
        eds edsVar = new eds();
        edsVar.a(0);
        int i = hiHealthData.getInt("lakeLouiseScoreKey");
        int intValue = hiHealthData.getIntValue();
        long j = hiHealthData.getLong("start_time");
        edsVar.agB_(rsn.dQG_(this.b, "[\\d]", rre.e(hiHealthData.getInt("altitudeKey")), R.style.privacy_only_risk_num, R.style.privacy_only_risk_unit));
        edsVar.d(scg.c(intValue));
        edsVar.b(scg.a(this.b, j));
        edsVar.e(rre.d(intValue));
        edsVar.c(scg.b(i));
        c(i, edsVar);
        edsVar.agD_(rsn.dQG_(this.b, "[\\d]", this.b.getResources().getQuantityString(R.plurals._2130903317_res_0x7f030115, i, Integer.valueOf(i)), R.style.privacy_only_risk_num, R.style.privacy_only_risk_unit));
        return edsVar;
    }

    private void e(Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenRecordProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (nsn.a(500)) {
                    return;
                }
                if (!"SHOWMORE".equals(str)) {
                    LogUtil.a("BloodOxygenRecordProvider", "useless click");
                    return;
                }
                LogUtil.a("BloodOxygenRecordProvider", "begin to click show more");
                PageModelArgs pageModelArgs = new PageModelArgs(105, "PrivacyDataConstructor", 3, 1);
                pageModelArgs.setClassType(0);
                if (efb.c()) {
                    pageModelArgs.putInt("Subpage", 4);
                    List<HiHealthData> a2 = BloodOxygenRecordProvider.this.f9766a != null ? BloodOxygenRecordProvider.this.f9766a.a() : null;
                    if (a2 != null && a2.size() > 0) {
                        pageModelArgs.putLong("Time", a2.get(a2.size() - 1).getStartTime());
                    }
                }
                Intent intent = new Intent(BloodOxygenRecordProvider.this.b, (Class<?>) PrivacyDataModelActivity.class);
                intent.putExtra("extra_page_model_args", pageModelArgs);
                gnm.aPB_(BloodOxygenRecordProvider.this.b, intent);
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (nsn.a(500)) {
                    return;
                }
                LogUtil.a("BloodOxygenRecordProvider", "click position" + i);
                BloodOxygenRecordProvider.this.c(i);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        List<HiHealthData> a2 = this.f9766a.a();
        if (bkz.e(a2) || i < 0 || i > a2.size() - 1) {
            LogUtil.a("BloodOxygenRecordProvider", "dataList is null or position out of bounds");
            return;
        }
        HiHealthData hiHealthData = a2.get(i);
        privacyDataModel.setStartTime(hiHealthData.getLong("start_time"));
        privacyDataModel.setEndTime(hiHealthData.getEndTime());
        privacyDataModel.setModifyTime(hiHealthData.getModifiedTime());
        privacyDataModel.setClientId(hiHealthData.getClientId());
        privacyDataModel.putInt("bloodOxygenKey", hiHealthData.getIntValue());
        privacyDataModel.putInt("altitudeKey", hiHealthData.getInt("altitudeKey"));
        if (hiHealthData.getInt("lakeLouiseScoreKey") != 0) {
            b(hiHealthData);
            privacyDataModel.putInt("lakeLouiseScoreKey", hiHealthData.getInt("lakeLouiseScoreKey"));
        }
        c(hiHealthData);
        PageModelArgs pageModelArgs = new PageModelArgs();
        pageModelArgs.setDataSource(3);
        pageModelArgs.setPageType(105);
        Intent intent = new Intent();
        intent.putExtra("extra_privacy_data_model", privacyDataModel);
        intent.putExtra("extra_page_model_args", pageModelArgs);
        intent.setClass(this.b, PrivacyDetailActivity.class);
        try {
            gnm.aPB_(this.b, intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("BloodOxygenRecordProvider", "ActivityNotFoundException" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, pkr pkrVar) {
        if (bkz.e(pkrVar.a())) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(pkrVar);
        }
    }

    public void b(HiHealthData hiHealthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", 1);
        int intValue = scg.e.get(Integer.valueOf(hiHealthData.getInt("lakeLouiseScoreKey"))).intValue();
        if (intValue == 3) {
            hashMap.put("level", "3");
        } else if (intValue == 2) {
            hashMap.put("level", "2");
        } else if (intValue == 1) {
            hashMap.put("level", "1");
        } else {
            LogUtil.a("BloodOxygenRecordProvider", "cardType is invalid" + intValue);
        }
        ixx.d().d(this.b, AnalyticsValue.BLOOD_OXYGEN_ABNORMAL_DATA.value(), hashMap, 0);
        LogUtil.a("BloodOxygenRecordProvider", "BICollect", Marker.ANY_NON_NULL_MARKER + hashMap.get("level"));
    }

    public void c(HiHealthData hiHealthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("type", 2);
        int a2 = scg.a(hiHealthData.getIntValue());
        int a3 = scg.a(a2);
        if (a3 == 2) {
            hashMap.put("level", "2");
        } else if (a3 == 1) {
            hashMap.put("level", "1");
        } else {
            LogUtil.a("BloodOxygenRecordProvider", "BloodOxygenAbnormal is invalid" + a2 + Marker.ANY_NON_NULL_MARKER + a3);
        }
        ixx.d().d(this.b, AnalyticsValue.BLOOD_OXYGEN_ABNORMAL_DATA.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "BloodOxygenRecordProvider";
    }
}
