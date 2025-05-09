package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionLibraryActivity;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.health.suggestion.ui.tabfragments.provider.WorkoutStatisticProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessStatisticProvider;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ixx;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class WorkoutStatisticProvider extends FitnessStatisticProvider<FitnessAchieveInfoUseCase> {
    public static /* synthetic */ void b(int i, Object obj) {
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 22;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 10001;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        d(context, hashMap, fitnessAchieveInfoUseCase);
    }

    private void d(Context context, HashMap<String, Object> hashMap, FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        hashMap.put("SECTION_STYLE", BaseSection.FITNESS_STYLE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.drawable.pic_fitness));
        arrayList.add(Integer.valueOf(R.drawable.pic_fitness_tahiti));
        hashMap.put("BACKGROUND", arrayList);
        hashMap.put("BUTTON_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130845613_res_0x7f021fad));
        hashMap.put("AUXILIARY_BUTTON_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130848535_res_0x7f022b17));
        hashMap.put("ACCUMULATED_DURATION", Integer.valueOf((int) fitnessAchieveInfoUseCase.getSumExerciseTime()));
        hashMap.put("ACCUMULATED_DURATION_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130846065_res_0x7f022171));
        hashMap.put("FITNESS_DURATION", Integer.valueOf(fitnessAchieveInfoUseCase.getTodayTime()));
        hashMap.put("FITNESS_DURATION_UNIT", BaseApplication.getContext().getResources().getString(R$string.IDS_min));
        hashMap.put("FITNESS_DURATION_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130842861_res_0x7f0214ed));
        hashMap.put("ACCUMULATED_DAYS", Integer.valueOf(fitnessAchieveInfoUseCase.getSumDays()));
        hashMap.put("ACCUMULATED_DAYS_UNIT", BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903243_res_0x7f0300cb, fitnessAchieveInfoUseCase.getSumDays()));
        hashMap.put("ACCUMULATED_DAYS_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130841574_res_0x7f020fe6));
        hashMap.put("ACCUMULATED_TIMES", Integer.valueOf(fitnessAchieveInfoUseCase.getExerciseCount()));
        hashMap.put("ACCUMULATED_TIMES_UNIT", BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, fitnessAchieveInfoUseCase.getExerciseCount()));
        hashMap.put("ACCUMULATED_TIMES_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130848531_res_0x7f022b13));
        e(context, hashMap);
    }

    private void e(final Context context, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.WorkoutStatisticProvider.5
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
                if (nsn.o()) {
                    LogUtil.a("Track_Provider_Track_WorkoutStatisticProvider", "CLICK_EVENT_LISTENER onClick isFastClick");
                    return;
                }
                if ("ACCUMULATED_DURATION_CLICK_VIEW".equals(str)) {
                    WorkoutStatisticProvider.this.d(context);
                } else if ("BUTTON_TEXT".equals(str)) {
                    WorkoutStatisticProvider.this.e(context);
                } else if ("AUXILIARY_BUTTON_TEXT".equals(str)) {
                    WorkoutStatisticProvider.this.a(context);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: gew
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    WorkoutStatisticProvider.b(i, obj);
                }
            }, "");
            return;
        }
        FitnessSessionManager.SessionActivityAction c = FitnessSessionManager.e().c();
        if (c == null) {
            LogUtil.h("Track_Provider_Track_WorkoutStatisticProvider", "FitnessSessionManager getSessionForActivityAction null");
        } else {
            c.startSportHistoryActivity(context, 10001, getCourseCategory());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        MarketingBiUtils.e(getPageType(), getSubViewTitle(), 0);
        try {
            JumpUtil.b(context, getCourseCategory(), 1, "FITNESS_COURSE");
        } catch (ActivityNotFoundException e) {
            LogUtil.h("Track_Provider_Track_WorkoutStatisticProvider", "ActivityNotFound", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) FitnessActionLibraryActivity.class);
        intent.putExtra("courseCategoryKey", getCourseCategory());
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, 10001);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_FITNESS_ACTION_LIBRARY_1130027.value(), hashMap, 0);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.h("Track_Provider_Track_WorkoutStatisticProvider", "ActivityNotFound", e.getMessage());
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_WorkoutStatisticProvider";
    }
}
