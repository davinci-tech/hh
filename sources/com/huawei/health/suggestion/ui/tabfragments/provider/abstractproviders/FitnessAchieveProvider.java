package com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import defpackage.cau;
import defpackage.edu;
import defpackage.gdx;
import defpackage.geb;
import defpackage.ghd;
import defpackage.ixu;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nmj;
import defpackage.nrv;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes8.dex */
public abstract class FitnessAchieveProvider extends FitnessEntranceProvider<geb> {
    private static final long LOAD_TIME_OUT = 1700;
    private static final String SPACE = " ";
    private static final String TAG = "FitnessAchieveProvider";
    protected geb mAchieveBean = new geb();

    protected abstract String getEntranceTitle();

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (geb) obj);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        geb gebVar = (geb) nrv.e(cau.a(String.valueOf(getType())), geb.class);
        if (gebVar != null) {
            LogUtil.a(getLogTag(), "use cache ", nrv.b(gebVar));
            this.mAchieveBean = gebVar;
        }
        sectionBean.e(this.mAchieveBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToSetDataAgain(SectionBean sectionBean, geb gebVar, boolean z) {
        if (z) {
            LogUtil.b(getLogTag(), "data is missing, set data again!");
            sectionBean.e(gebVar);
        }
    }

    protected List<gdx> getBeans(SectionBean sectionBean) {
        ResourceBriefInfo m = sectionBean.m();
        if (m == null) {
            LogUtil.b(getLogTag(), "loadData failed cause rbi is null!");
            return null;
        }
        ResourceContentBase content = m.getContent();
        if (content == null) {
            LogUtil.b(getLogTag(), "loadData failed cause contentBase is null!");
            return null;
        }
        String content2 = content.getContent();
        if (TextUtils.isEmpty(content2)) {
            LogUtil.b(getLogTag(), "loadData failed cause content is empty!");
            return null;
        }
        geb gebVar = (geb) ixu.e(content2, new TypeToken<geb>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider.3
        });
        this.mAchieveBean = gebVar;
        if (gebVar == null) {
            LogUtil.h(getLogTag(), "achieveBean is null");
            return null;
        }
        return gebVar.c();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, final SectionBean sectionBean) {
        final List<gdx> beans = getBeans(sectionBean);
        if (beans == null) {
            return;
        }
        final int size = koq.c(beans) ? beans.size() : 0;
        final boolean[] zArr = {false};
        ThreadPoolManager.d().execute(new Runnable() { // from class: gfd
            @Override // java.lang.Runnable
            public final void run() {
                FitnessAchieveProvider.this.m517xf93390d5(size, sectionBean, zArr, beans, context);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x011a, code lost:
    
        if (r12.await(com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider.LOAD_TIME_OUT, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L29;
     */
    /* renamed from: lambda$loadData$1$com-huawei-health-suggestion-ui-tabfragments-provider-abstractproviders-FitnessAchieveProvider, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void m517xf93390d5(int r18, final com.huawei.health.knit.section.model.SectionBean r19, final boolean[] r20, java.util.List r21, final android.content.Context r22) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider.m517xf93390d5(int, com.huawei.health.knit.section.model.SectionBean, boolean[], java.util.List, android.content.Context):void");
    }

    /* renamed from: lambda$loadData$0$com-huawei-health-suggestion-ui-tabfragments-provider-abstractproviders-FitnessAchieveProvider, reason: not valid java name */
    public /* synthetic */ void m516x18b1c8f6(SectionBean sectionBean, boolean[] zArr, CountDownLatch countDownLatch, int i, Object obj) {
        LogUtil.a(getLogTag(), "acquireSummaryFitnessRecordByCategory errorCode:", Integer.valueOf(i));
        if (koq.e(obj, WorkoutRecord.class)) {
            FitnessAchieveInfoUseCase a2 = ghd.a((List<WorkoutRecord>) obj);
            if (a2 == null) {
                a2 = new FitnessAchieveInfoUseCase();
            }
            this.mAchieveBean.e(a2);
            tryToSetDataAgain(sectionBean, this.mAchieveBean, zArr[0]);
            countDownLatch.countDown();
        }
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, geb gebVar) {
        hashMap.put("BACKGROUND", gebVar.b());
        FitnessAchieveInfoUseCase a2 = gebVar.a();
        if (a2 != null) {
            hashMap.put("LEFT_TOP_VALUE", Long.valueOf(a2.getSumExerciseTime()));
            Resources resources = context.getResources();
            hashMap.put("LEFT_BOTTOM_TEXT", resources.getString(R.string._2130845994_res_0x7f02212a) + Constants.LEFT_BRACKET_ONLY + resources.getString(R.string._2130841436_res_0x7f020f5c) + Constants.RIGHT_BRACKET_ONLY);
            hashMap.put("RIGHT_TOP_BUTTON", resources.getString(R.string._2130845613_res_0x7f021fad));
            hashMap.put("RIGHT_COMBINE_TEXT", createCombineText(a2, resources));
            hashMap.put("SECTION16_9CARD_01_BANNER_DATA", createBannerItems(gebVar));
            setClickListener(context, hashMap);
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider$4, reason: invalid class name */
    public class AnonymousClass4 extends BaseKnitDataProvider.d {
        final /* synthetic */ Context b;

        public static /* synthetic */ void a(int i, Object obj) {
        }

        AnonymousClass4(Context context) {
            this.b = context;
        }

        @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            if ("ACCUMULATED_DURATION_CLICK_VIEW".equals(str)) {
                if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                    LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: gfc
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i, Object obj) {
                            FitnessAchieveProvider.AnonymousClass4.a(i, obj);
                        }
                    }, "");
                    return;
                }
                FitnessSessionManager.SessionActivityAction c = FitnessSessionManager.e().c();
                if (c == null) {
                    LogUtil.h(FitnessAchieveProvider.this.getLogTag(), "FitnessSessionManager getSessionForActivityAction null");
                    return;
                } else {
                    c.startSportHistoryActivity(this.b, 10001, FitnessAchieveProvider.this.getCourseCategory());
                    return;
                }
            }
            if ("ITEM_BUTTON_TEXT".equals(str)) {
                Intent intent = new Intent(this.b, (Class<?>) SportAllCourseActivity.class);
                intent.putExtra("COURSE_CATEGORY_KEY", FitnessAchieveProvider.this.getCourseCategory());
                intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(FitnessAchieveProvider.this.getType()));
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.EVENT_CLICK_FITNESS_COURSE_MORE.value(), hashMap, 0);
                intent.addFlags(268435456);
                try {
                    this.b.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException e) {
                    LogUtil.b(FitnessAchieveProvider.this.getLogTag(), "ActivityNotFoundException", e.getMessage());
                    return;
                }
            }
            LogUtil.h(FitnessAchieveProvider.this.getLogTag(), "onClick subView is invalid");
        }
    }

    private void setClickListener(Context context, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new AnonymousClass4(context));
    }

    private List<edu> createBannerItems(geb gebVar) {
        ArrayList arrayList = new ArrayList();
        ArrayList<gdx> c = gebVar.c();
        if (koq.b(c)) {
            LogUtil.b(getLogTag(), "createBannerItems failed cause bannerBeanList is empty");
            return arrayList;
        }
        for (gdx gdxVar : c) {
            arrayList.add(new edu(gdxVar.i(), gdxVar.a(), gdxVar.aLG_(), gdxVar.d(), gdxVar.f(), gdxVar.aLH_()));
        }
        return arrayList;
    }

    private SpannableString createCombineText(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase, Resources resources) {
        int todayTime = fitnessAchieveInfoUseCase.getTodayTime();
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getString(R.string._2130842861_res_0x7f0214ed));
        sb.append(" ");
        int length = sb.length();
        sb.append(UnitUtil.e(todayTime, 1, 0));
        int length2 = sb.length();
        sb.append(resources.getQuantityString(R.plurals._2130903233_res_0x7f0300c1, todayTime));
        sb.append(" ");
        sb.append(" ");
        sb.append(" ");
        sb.append(" ");
        sb.append(resources.getString(R.string._2130841574_res_0x7f020fe6));
        sb.append(" ");
        int sumDays = fitnessAchieveInfoUseCase.getSumDays();
        int length3 = sb.length();
        sb.append(UnitUtil.e(sumDays, 1, 0));
        int length4 = sb.length();
        sb.append(resources.getQuantityString(R.plurals._2130903243_res_0x7f0300cb, sumDays));
        sb.append(" ");
        int exerciseCount = fitnessAchieveInfoUseCase.getExerciseCount();
        int[] iArr = {length, length3, sb.length()};
        sb.append(UnitUtil.e(exerciseCount, 1, 0));
        int[] iArr2 = {length2, length4, sb.length()};
        sb.append(resources.getQuantityString(R.plurals._2130903241_res_0x7f0300c9, exerciseCount));
        SpannableString spannableString = new SpannableString(sb.toString());
        for (int i = 0; i < 3; i++) {
            spannableString.setSpan(new nmj(Typeface.DEFAULT_BOLD), iArr[i], iArr2[i], 17);
        }
        return spannableString;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void setGroupId(int i) {
        super.setGroupId(i);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public int getGroupId() {
        return super.getGroupId();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void setExtra(Bundle bundle) {
        super.setExtra(bundle);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public Bundle getExtra() {
        return super.getExtra();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    protected String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return TAG;
    }
}
