package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.algorithm.api.SleepMonitorAlgorithmApi;
import com.huawei.health.knit.data.KnitDataProviderGroup;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.main.R$string;
import defpackage.bzi;
import defpackage.fdk;
import defpackage.fdp;
import defpackage.nrq;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class SleepCollapsibleProviderGroup<T> extends KnitDataProviderGroup<T> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9809a;
    private int b;
    private boolean c = true;
    private boolean d;
    private boolean e;
    private Observer h;

    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        if (this.c) {
            return super.isActive(context);
        }
        return false;
    }

    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        super.loadData(context, sectionBean);
        if (this.h == null) {
            Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepCollapsibleProviderGroup.1
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    LogUtil.a("SleepCollapsibleProviderGroup", "group expand status changed");
                    if (objArr != null && objArr.length == 1) {
                        Object obj = objArr[0];
                        if (obj instanceof Boolean) {
                            SleepCollapsibleProviderGroup.this.e = ((Boolean) obj).booleanValue();
                            LogUtil.a("SleepCollapsibleProviderGroup", "group expand status changed, isExpandLastTime", Boolean.valueOf(SleepCollapsibleProviderGroup.this.e));
                        }
                    }
                }
            };
            this.h = observer;
            ObserverManagerUtil.d(observer, "group_array_clicked_label");
        }
    }

    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        ObserverManagerUtil.e(this.h, "group_array_clicked_label");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.MinorProvider
    public void onSetSectionBeanData(SectionBean sectionBean, T t) {
        LogUtil.a("SleepCollapsibleProviderGroup", "onSetSectionBeanData");
        if (!(t instanceof fdp)) {
            this.c = false;
            sectionBean.e(t);
            return;
        }
        fdp fdpVar = (fdp) t;
        if (!fdpVar.m() || (fdpVar.e() == SleepViewConstants.ViewTypeEnum.YEAR && !fdpVar.k())) {
            LogUtil.a("SleepCollapsibleProviderGroup", "data is invalid");
            this.c = false;
            sectionBean.e(t);
            return;
        }
        if ((fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH) && fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL && (fdpVar.d().q() || fdpVar.d().h() <= 0)) {
            LogUtil.a("SleepCollapsibleProviderGroup", "isManualIsNotOnThree");
            this.c = false;
            sectionBean.e(t);
        } else if ((fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH) && fdpVar.n()) {
            LogUtil.h("SleepCollapsibleProviderGroup", "only have not satisfied sleep");
            this.c = false;
            sectionBean.e(t);
        } else {
            this.c = true;
            super.onSetSectionBeanData(sectionBean, t);
            d(t);
            sectionBean.e(t);
        }
    }

    @Override // com.huawei.health.knit.data.KnitDataProviderGroup, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, T t) {
        super.parseParams(context, hashMap, t);
        hashMap.put("IS_GROUP_ARRAY_VISIBILITY", Integer.valueOf(this.b));
        hashMap.put("IS_SECTION_GROUP_EXPAND", Boolean.valueOf(this.d));
        hashMap.put("BUTTON_TEXT", context.getResources().getString(R$string.IDS_sleep_expand_btn_show_more).toUpperCase());
        hashMap.put("ITEM_BUTTON_TEXT", context.getResources().getString(R$string.IDS_sleep_expand_btn_less).toUpperCase());
        hashMap.put("IS_DATA_TYPE_DAY", Boolean.valueOf(this.f9809a));
        e(hashMap, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(HashMap<String, Object> hashMap, T t) {
        if (t instanceof fdp) {
            fdp fdpVar = (fdp) t;
            if (fdpVar.m()) {
                fdk f = fdpVar.f();
                long f2 = f.f();
                int i = 0;
                Iterator<bzi> it = ((SleepMonitorAlgorithmApi) Services.c("SleepMonitor", SleepMonitorAlgorithmApi.class)).querySleepVoiceInfo(nrq.d(f2, f.m(), -1) / 1000, nrq.d(f2, f.m(), 0) / 1000).iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    int c = it.next().a().c();
                    if (1 == c && i < 10) {
                        i++;
                    } else if (2 != c || i2 >= 10) {
                        LogUtil.a("SleepCollapsibleProviderGroup", "not dream and snore");
                    } else {
                        i2++;
                    }
                }
                hashMap.put("Snorenumber", Integer.valueOf(i));
                hashMap.put("Dreamtalknumber", Integer.valueOf(i2));
                return;
            }
        }
        LogUtil.b("SleepCollapsibleProviderGroup", "sleep data is not valid ,cannot get dream and snore data");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d(T t) {
        if (t instanceof fdp) {
            fdp fdpVar = (fdp) t;
            this.f9809a = fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY;
            fdpVar.i();
            SleepViewConstants.SleepTypeEnum sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
            boolean z = VersionControlUtil.isSupportSleepManagement() && (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL || fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) && this.f9809a;
            if (fdpVar.d().s() || fdpVar.d().q() || z) {
                this.b = 8;
                this.d = true;
                this.e = false;
                LogUtil.a("SleepCollapsibleProviderGroup", "isOversea");
                return;
            }
            this.b = 0;
            this.d = false;
            if (fdpVar.j().bb() || fdpVar.f().ad()) {
                this.b = 8;
                this.d = true;
                this.e = false;
            } else {
                boolean z2 = this.e;
                if (z2) {
                    this.d = z2;
                    this.b = 0;
                }
            }
        }
    }
}
