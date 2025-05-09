package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionLibraryActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.edv;
import defpackage.gge;
import defpackage.koq;
import defpackage.nsj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class ActionLibraryProvider extends FitnessEntranceProvider<List<FitWorkout>> {
    private List<edv> e;

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 22;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        sectionBean.e(new ArrayList());
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<FitWorkout> list) {
        c(context, hashMap, list);
    }

    private void c(final Context context, HashMap<String, Object> hashMap, List<FitWorkout> list) {
        e();
        hashMap.put("PAGE_TYPE", Integer.valueOf(getPageType()));
        hashMap.put("TITLE", context.getString(R.string._2130848535_res_0x7f022b17));
        hashMap.put("SUBTITLE", context.getString(R.string._2130841847_res_0x7f0210f7));
        hashMap.put("SECTION1_1CARD_02_BEAN", this.e);
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.ActionLibraryProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (str.equals("SUBTITLE")) {
                    ActionLibraryProvider.this.d(context);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(ActionLibraryProvider.this.e, i)) {
                    LogUtil.b("Track_Provider_ActionLibraryProvider", "position is out of bounds");
                } else {
                    ActionLibraryProvider actionLibraryProvider = ActionLibraryProvider.this;
                    actionLibraryProvider.c(context, (edv) actionLibraryProvider.e.get(i));
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e() {
        this.e = new ArrayList(10);
        int[] iArr = {R.string._2130848541_res_0x7f022b1d, R.string._2130848542_res_0x7f022b1e, R.string._2130848543_res_0x7f022b1f, R.string._2130848548_res_0x7f022b24, R.string._2130848544_res_0x7f022b20, R.string._2130848545_res_0x7f022b21, R.string._2130848546_res_0x7f022b22, R.string._2130848547_res_0x7f022b23, R.string._2130848549_res_0x7f022b25, R.string._2130848550_res_0x7f022b26};
        int[] iArr2 = {R.drawable._2131428211_res_0x7f0b0373, R.drawable._2131428212_res_0x7f0b0374, R.drawable._2131428216_res_0x7f0b0378, R.drawable._2131428210_res_0x7f0b0372, R.drawable._2131428209_res_0x7f0b0371, R.drawable._2131428208_res_0x7f0b0370, R.drawable._2131428217_res_0x7f0b0379, R.drawable._2131428214_res_0x7f0b0376, R.drawable._2131428213_res_0x7f0b0375, R.drawable._2131428215_res_0x7f0b0377};
        int[] iArr3 = {1, 2, 16, 4, 128, 256, 32, 64, 8, 512};
        for (int i = 0; i < 10; i++) {
            edv edvVar = new edv();
            edvVar.d(iArr[i]);
            edvVar.b(iArr2[i]);
            edvVar.a(iArr3[i]);
            this.e.add(edvVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, edv edvVar) {
        MarketingBiUtils.e(getPageType(), getSubViewTitle(), 11);
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("date", nsj.d());
        hashMap.put("type", Integer.valueOf(edvVar.b()));
        gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_TYPE_1130028.value(), hashMap);
        Intent intent = new Intent(context, (Class<?>) FitnessActionTypeActivity.class);
        intent.putExtra("body_title", context.getString(edvVar.a()));
        intent.putExtra("body_title_type", edvVar.b());
        intent.setFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Track_Provider_ActionLibraryProvider", "startFitnessActionTypeActivity exception" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_LIBRARY_1130027.value(), hashMap);
        Intent intent = new Intent(context, (Class<?>) FitnessActionLibraryActivity.class);
        intent.setFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("Track_Provider_ActionLibraryProvider", "startActivityForActionLib exception" + e.getMessage());
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130851546_res_0x7f0236da);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_ActionLibraryProvider";
    }
}
