package com.huawei.ui.main.stories.fitness.activity.coresleep.utils;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.SleepMonitorAlgorithmApi;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.bzh;
import defpackage.bzi;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class SleepShutEyeChareProvider extends BaseKnitDataProvider {
    private int c = 0;
    private int g = 0;
    private int d = 0;
    private int i = 0;
    private String f = "";
    private String e = "";
    private String b = "";
    private String h = "";
    private String k = "";
    private String j = "";

    /* renamed from: a, reason: collision with root package name */
    private boolean f9844a = false;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.a(d(BaseApplication.getContext()));
        sectionBean.e(new Object());
    }

    public HashMap<String, Object> d(Context context) {
        Iterator<bzi> it = ((SleepMonitorAlgorithmApi) Services.c("SleepMonitor", SleepMonitorAlgorithmApi.class)).querySleepVoiceInfo(0L, System.currentTimeMillis()).iterator();
        while (it.hasNext()) {
            bzh.c a2 = it.next().a();
            int c = a2.c();
            if (1 == c) {
                this.g += a2.a();
                this.i++;
            } else if (2 == c) {
                this.c += a2.a();
                this.d++;
            } else {
                LogUtil.c(getLogTag(), "not dream and snore");
            }
        }
        List<Integer> d = d(this.c);
        List<Integer> d2 = d(this.g);
        if (d.size() >= 2) {
            int intValue = d.get(0).intValue();
            int intValue2 = d.get(1).intValue();
            if (intValue == 0) {
                this.e = "";
            } else {
                this.e = context.getResources().getQuantityString(R.plurals._2130903119_res_0x7f03004f, intValue, UnitUtil.e(intValue, 1, 0));
            }
            if (intValue2 == 0 && intValue != 0) {
                this.b = "";
            } else {
                this.b = context.getResources().getQuantityString(R.plurals._2130903120_res_0x7f030050, intValue2, UnitUtil.e(intValue2, 1, 0));
            }
        }
        this.f = context.getResources().getString(R$string.IDS_sleep_total_time);
        this.f = String.format(Locale.ENGLISH, this.f, this.e + this.b);
        if (d2.size() >= 2) {
            int intValue3 = d2.get(0).intValue();
            int intValue4 = d2.get(1).intValue();
            if (intValue3 == 0) {
                this.h = "";
            } else {
                this.h = context.getResources().getQuantityString(R.plurals._2130903119_res_0x7f03004f, intValue3, UnitUtil.e(intValue3, 1, 0));
            }
            if (intValue4 == 0 && intValue3 != 0) {
                this.k = "";
            } else {
                this.k = context.getResources().getQuantityString(R.plurals._2130903120_res_0x7f030050, intValue4, UnitUtil.e(intValue4, 1, 0));
            }
        }
        this.j = context.getResources().getString(R$string.IDS_sleep_total_time);
        this.j = String.format(Locale.ENGLISH, this.j, this.h + this.k);
        HashMap<String, Object> hashMap = new HashMap<>();
        Resources resources = context.getResources();
        int i = this.d;
        String quantityString = resources.getQuantityString(R.plurals._2130903117_res_0x7f03004d, i, Integer.valueOf(i));
        Resources resources2 = context.getResources();
        int i2 = this.i;
        String quantityString2 = resources2.getQuantityString(R.plurals._2130903118_res_0x7f03004e, i2, Integer.valueOf(i2));
        hashMap.put("SHARE_DREAM_TALK", quantityString);
        hashMap.put("SHARE_SNORE", quantityString2);
        hashMap.put("SHARE_DREAM_TALK_MINTES", this.f);
        hashMap.put("SHARE_SNORE_MINTES", this.j);
        return hashMap;
    }

    public List<Integer> d(int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        if (i < 60) {
            i2 = 0;
        } else {
            i2 = i % 60;
            i /= 60;
        }
        arrayList.add(Integer.valueOf(i2));
        arrayList.add(Integer.valueOf(i));
        return arrayList;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.f9844a;
    }
}
