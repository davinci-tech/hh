package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.health.R;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessMyCourseProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.arx;
import defpackage.ffy;
import defpackage.gdz;
import defpackage.ggm;
import defpackage.ghd;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class YogaMyCourseProvider extends FitnessMyCourseProvider {
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
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        c(context, (HashMap<String, Object>) hashMap, (List<gdz>) obj);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return LoginInit.getInstance(context).getIsLogined();
    }

    public void c(Context context, HashMap<String, Object> hashMap, List<gdz> list) {
        e(context, hashMap, list);
    }

    private void e(Context context, HashMap<String, Object> hashMap, List<gdz> list) {
        hashMap.put("PAGE_TYPE", Integer.valueOf(getPageType()));
        hashMap.put("TITLE", getSubViewTitle());
        int min = Math.min(list.size(), 6);
        List<gdz> subList = list.subList(0, min);
        if (list.size() == 0) {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", true);
            hashMap.put("NO_DATA_NAME", BaseApplication.getContext().getResources().getString(R.string._2130848514_res_0x7f022b02));
            hashMap.put("NO_DATA_CONTENT", BaseApplication.getContext().getResources().getString(R.string._2130848516_res_0x7f022b04));
            hashMap.put("NO_DATA_BUTTON", BaseApplication.getContext().getResources().getString(R.string._2130848515_res_0x7f022b03));
        } else {
            hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
            c(subList, hashMap, min);
        }
        e(context, (Map<String, Object>) hashMap, subList);
    }

    private void e(final Context context, Map<String, Object> map, final List<gdz> list) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (nsn.o()) {
                    LogUtil.a("Track_Provider_Track_YogaMyCourseProvider", "CLICK_EVENT_LISTENER onClick isFastClick");
                } else if ("MORE_CLICK_EVENT".equals(str)) {
                    YogaMyCourseProvider.this.e(context);
                } else if ("NO_DATA_BUTTON_CLICK_EVENT".equals(str)) {
                    YogaMyCourseProvider.this.b(context);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Track_Provider_Track_YogaMyCourseProvider", "list is empty or out of bounds.", Integer.valueOf(i));
                    return;
                }
                gdz gdzVar = (gdz) list.get(i);
                if (gdzVar != null) {
                    YogaMyCourseProvider.this.c(gdzVar.e(), context);
                } else {
                    LogUtil.b("Track_Provider_Track_YogaMyCourseProvider", "courseBean is null");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(FitWorkout fitWorkout, Context context) {
        MarketingBiUtils.e(getPageType(), getSubViewTitle(), 2);
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d("2");
        mod.b(context, fitWorkout, mmpVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        Intent intent = new Intent(context, (Class<?>) SportAllCourseActivity.class);
        intent.putExtra("COURSE_CATEGORY_KEY", getCourseCategory());
        intent.putExtra("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(getType()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.EVENT_CLICK_FITNESS_COURSE_MORE.value(), hashMap, 0);
        aLW_(context, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        Intent intent = new Intent(context, (Class<?>) MyFitnessCourseActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("titleName", getSubViewTitle());
        intent.putExtra("courseCategoryKey", getCourseCategory());
        MarketingBiUtils.e(getPageType(), getSubViewTitle(), 1);
        aLW_(context, intent);
    }

    private void aLW_(Context context, Intent intent) {
        if (context == null || intent == null) {
            LogUtil.h("Track_Provider_Track_YogaMyCourseProvider", "startActivity context or intent is null.");
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_Provider_Track_YogaMyCourseProvider", "startActivity ActivityNotFoundException.");
        }
    }

    private void c(List<gdz> list, Map<String, Object> map, int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        int i2 = 0;
        for (gdz gdzVar : list) {
            if (i2 >= i) {
                break;
            }
            arrayList.add(gdzVar.i());
            arrayList2.add(gdzVar.h());
            arrayList3.add(ggm.d(gdzVar.c()));
            arrayList4.add(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(gdzVar.a())));
            arrayList5.add(ghd.a(gdzVar.j()));
            arrayList6.add(getTagData(gdzVar.d()));
            i2++;
        }
        map.put("IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put(PlayInfo.KEY_DURATION, arrayList4);
        map.put("DIFFICULTY", arrayList3);
        map.put("INTERVALS", arrayList5);
        map.put("ITEM_TAG", arrayList6);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130851546_res_0x7f0236da);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessMyCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_YogaMyCourseProvider";
    }
}
