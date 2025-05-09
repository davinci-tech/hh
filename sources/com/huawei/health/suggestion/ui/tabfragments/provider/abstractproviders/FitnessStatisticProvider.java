package com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessStatisticProvider;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.cau;
import defpackage.ghd;
import defpackage.jdl;
import defpackage.koq;
import defpackage.kwy;
import defpackage.moj;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class FitnessStatisticProvider<T> extends FitnessEntranceProvider<T> {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase = new FitnessAchieveInfoUseCase();
        String a2 = cau.a(String.valueOf(getType()));
        LogUtil.a(getLogTag(), "loadDefaultData() jsonDataStr: ", a2);
        if (!TextUtils.isEmpty(a2)) {
            FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase2 = (FitnessAchieveInfoUseCase) moj.e(a2, FitnessAchieveInfoUseCase.class);
            if (fitnessAchieveInfoUseCase2 == null) {
                sectionBean.e(fitnessAchieveInfoUseCase);
                return;
            }
            if (fitnessAchieveInfoUseCase2.getTimeStamp() < jdl.t(System.currentTimeMillis()) || fitnessAchieveInfoUseCase2.getTimeStamp() > jdl.e(System.currentTimeMillis())) {
                fitnessAchieveInfoUseCase2.setTodayTime(0);
                LogUtil.a(getLogTag(), "loadDefaultData() not in the same day data");
            }
            fitnessAchieveInfoUseCase = fitnessAchieveInfoUseCase2;
        }
        LogUtil.a(getLogTag(), "loadDefaultData() data: ", fitnessAchieveInfoUseCase.toString());
        sectionBean.e(fitnessAchieveInfoUseCase);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, final SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "loadData course statistic");
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi != null) {
            recordApi.acquireDetailFitnessRecords(new kwy.a().a(0L).e(System.currentTimeMillis()).a(getCourseCategory()).d(), new IBaseResponseCallback() { // from class: gfh
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    FitnessStatisticProvider.this.m520xc472b3f5(context, sectionBean, i, obj);
                }
            });
        } else {
            LogUtil.b(getLogTag(), "cannot get recordApi");
            sectionBean.e(new FitnessAchieveInfoUseCase());
        }
    }

    /* renamed from: lambda$loadData$0$com-huawei-health-suggestion-ui-tabfragments-provider-abstractproviders-FitnessStatisticProvider, reason: not valid java name */
    public /* synthetic */ void m520xc472b3f5(Context context, SectionBean sectionBean, int i, Object obj) {
        LogUtil.a(getLogTag(), "acquireSummaryFitnessRecordByCategory errorCode:", Integer.valueOf(i));
        if (!LoginInit.getInstance(context).getIsLogined()) {
            sectionBean.e(new FitnessAchieveInfoUseCase());
            return;
        }
        if (koq.e(obj, WorkoutRecord.class)) {
            FitnessAchieveInfoUseCase a2 = ghd.a((List<WorkoutRecord>) obj);
            if (a2 == null) {
                a2 = new FitnessAchieveInfoUseCase();
            }
            if (refreshCacheAndUI(a2)) {
                sectionBean.e(a2);
            }
        }
    }

    private boolean refreshCacheAndUI(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase2 = (FitnessAchieveInfoUseCase) moj.e(cau.a(String.valueOf(getType())), FitnessAchieveInfoUseCase.class);
        if (fitnessAchieveInfoUseCase2 != null && fitnessAchieveInfoUseCase2.equals(fitnessAchieveInfoUseCase)) {
            LogUtil.a(getLogTag(), "same cache data");
            return false;
        }
        LogUtil.a(getLogTag(), "refresh cache");
        int type = getType();
        cau.d(String.valueOf(type), moj.e(fitnessAchieveInfoUseCase));
        return true;
    }
}
