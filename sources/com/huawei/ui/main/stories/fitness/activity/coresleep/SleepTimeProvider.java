package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.fdl;
import defpackage.fdp;
import defpackage.fdq;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SleepTimeProvider extends MinorProvider<fdp> {
    private boolean d = true;

    protected boolean d() {
        return false;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.d;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepTimeProvider", "loadData");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, fdp fdpVar) {
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.YEAR) {
            this.d = c(fdpVar);
        } else {
            if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
                this.d = b(fdpVar) && fdpVar.i() != SleepViewConstants.SleepTypeEnum.MANUAL;
            } else if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH || fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK) {
                this.d = fdpVar.i() != SleepViewConstants.SleepTypeEnum.MANUAL && b(fdpVar);
            } else {
                this.d = false;
            }
        }
        sectionBean.e(this, fdpVar);
    }

    private boolean b(fdp fdpVar) {
        return (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE && (fdpVar.j().x() != 0 || fdpVar.j().aw() != 0 || fdpVar.j().aq() != 0)) || (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE && (fdpVar.f().p() != 0 || fdpVar.f().u() != 0)) || (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON && (fdpVar.c().r() != 0 || fdpVar.c().s() != 0));
    }

    protected boolean c(fdp fdpVar) {
        return ((fdq) fdpVar.j()).bp() > 0;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        hashMap.put("CHART_ITEM_BEGIN_COLOR", a(context, fdpVar));
        hashMap.put("CHART_ITEM_EDN_COLOR", b(context, fdpVar));
        hashMap.put("CHART_ITEM_TITLE", c(context, fdpVar));
        hashMap.put("CHART_ITEM_VALUE", e(fdpVar));
        if (LanguageUtil.j(BaseApplication.getContext())) {
            hashMap.put("RING_CHART_DESC", context.getString(R$string.IDS_hw_show_main_home_page_sleep_new));
        } else {
            hashMap.put("RING_CHART_DESC", "");
        }
        hashMap.put("SHOW_VALUE", true);
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepTimeProvider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[SleepViewConstants.ViewTypeEnum.values().length];
            e = iArr;
            try {
                iArr[SleepViewConstants.ViewTypeEnum.DAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private ArrayList<String> c(Context context, fdp fdpVar) {
        String string;
        String string2;
        String string3;
        if (AnonymousClass3.e[fdpVar.e().ordinal()] == 1) {
            string = context.getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep);
            string2 = context.getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep);
            string3 = context.getString(R$string.IDS_fitness_core_sleep_rem_sleep);
        } else {
            string = context.getString(R$string.IDS_details_sleep_avg_deep_sleep);
            string2 = context.getString(R$string.IDS_details_sleep_avg_light_sleep);
            string3 = context.getString(R$string.IDS_fitness_core_sleep_avg_rem_sleep);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(string);
        arrayList.add(string2);
        if (a(fdpVar)) {
            arrayList.add(string3);
        }
        return arrayList;
    }

    private ArrayList<Double> e(fdp fdpVar) {
        int x;
        int aw;
        int i = 0;
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.YEAR) {
            if (d() && ((fdl) fdpVar.c()).v() > 0) {
                x = fdpVar.c().r();
                aw = fdpVar.c().s();
            } else {
                x = fdpVar.j().x();
                aw = fdpVar.j().aw();
                i = fdpVar.j().aq();
            }
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
            x = fdpVar.f().p();
            aw = fdpVar.f().u();
        } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
            x = fdpVar.c().r();
            aw = fdpVar.c().s();
        } else {
            x = fdpVar.j().x();
            aw = fdpVar.j().aw();
            i = fdpVar.j().aq();
        }
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(Double.valueOf(x));
        arrayList.add(Double.valueOf(aw));
        if (a(fdpVar)) {
            arrayList.add(Double.valueOf(i));
        }
        return arrayList;
    }

    private boolean a(fdp fdpVar) {
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.DAY) {
            return fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE;
        }
        if (fdpVar.e() == SleepViewConstants.ViewTypeEnum.WEEK || fdpVar.e() == SleepViewConstants.ViewTypeEnum.MONTH) {
            return fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE;
        }
        return !d();
    }

    public ArrayList<Integer> a(Context context, fdp fdpVar) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298920_res_0x7f090a68)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298959_res_0x7f090a8f)));
        if (a(fdpVar)) {
            arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298950_res_0x7f090a86)));
        }
        return arrayList;
    }

    public ArrayList<Integer> b(Context context, fdp fdpVar) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298921_res_0x7f090a69)));
        arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298960_res_0x7f090a90)));
        if (a(fdpVar)) {
            arrayList.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298951_res_0x7f090a87)));
        }
        return arrayList;
    }
}
