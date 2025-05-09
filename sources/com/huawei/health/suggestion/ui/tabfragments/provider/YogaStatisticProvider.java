package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.health.suggestion.ui.tabfragments.provider.YogaStatisticProvider;
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
public class YogaStatisticProvider extends FitnessStatisticProvider<FitnessAchieveInfoUseCase> {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 23;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        b(context, hashMap, fitnessAchieveInfoUseCase);
    }

    private void b(Context context, HashMap<String, Object> hashMap, FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        ArrayList arrayList = new ArrayList();
        hashMap.put("SECTION_STYLE", BaseSection.YOGA_STYLE);
        arrayList.add(Integer.valueOf(R.drawable.pic_yoga));
        arrayList.add(Integer.valueOf(R.drawable.pic_yoga_tahiti));
        hashMap.put("BACKGROUND", arrayList);
        hashMap.put("BUTTON_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130845613_res_0x7f021fad));
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
        d(context, hashMap);
    }

    /* renamed from: com.huawei.health.suggestion.ui.tabfragments.provider.YogaStatisticProvider$3, reason: invalid class name */
    public class AnonymousClass3 implements OnClickSectionListener {
        final /* synthetic */ Context d;

        public static /* synthetic */ void a(int i, Object obj) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        AnonymousClass3(Context context) {
            this.d = context;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            if (nsn.o()) {
                LogUtil.a("Track_Provider_Track_YogaStatisticProvider", "CLICK_EVENT_LISTENER onClick isFastClick");
                return;
            }
            if ("ACCUMULATED_DURATION_CLICK_VIEW".equals(str)) {
                if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                    LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: gff
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i, Object obj) {
                            YogaStatisticProvider.AnonymousClass3.a(i, obj);
                        }
                    }, "");
                    return;
                }
                FitnessSessionManager.SessionActivityAction c = FitnessSessionManager.e().c();
                if (c == null) {
                    LogUtil.h("Track_Provider_Track_YogaStatisticProvider", "FitnessSessionManager getSessionForActivityAction null");
                    return;
                } else {
                    c.startSportHistoryActivity(this.d, 10001, YogaStatisticProvider.this.getCourseCategory());
                    return;
                }
            }
            if ("BUTTON_TEXT".equals(str)) {
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(YogaStatisticProvider.this.getType()));
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.EVENT_CLICK_FITNESS_COURSE_MORE.value(), hashMap, 0);
                try {
                    JumpUtil.b(this.d, YogaStatisticProvider.this.getCourseCategory(), 1, "FITNESS_COURSE");
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("Track_Provider_Track_YogaStatisticProvider", "setClickListener ActivityNotFoundException.");
                }
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void d(Context context, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new AnonymousClass3(context));
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getString(R.string._2130851546_res_0x7f0236da);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_YogaStatisticProvider";
    }
}
