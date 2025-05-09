package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.ui.main.stories.nps.NpsExternalApiImpl;
import defpackage.peu;
import defpackage.pev;
import defpackage.pey;
import defpackage.pfa;
import defpackage.pfi;
import defpackage.ppx;
import defpackage.pxk;
import defpackage.qgs;
import defpackage.qmj;
import defpackage.qnr;
import defpackage.qvq;
import defpackage.riw;
import defpackage.rtz;
import defpackage.ruz;
import defpackage.sau;

/* loaded from: classes4.dex */
public final class AboutModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(pxk.class, "com.huawei.health.main.api.FitnessDataInteractorApi");
        builder.add(ppx.class, "com.huawei.pluginsleepdetection.SleepRecordNotifyApi");
        builder.add(riw.class, "com.huawei.health.main.api.MainNavigationApi");
        builder.add(ruz.class, "com.huawei.health.heartratesetting.HeartRateSettingApi");
        builder.add(qnr.class, "com.huawei.health.sleep.SleepApi");
        builder.add(qgs.class, "com.huawei.health.bloodpressure.BloodPressureApi");
        builder.add(qmj.class, "com.huawei.health.pressure.PressureApi");
        builder.add(qvq.class, "com.huawei.health.weight.WeightApi");
        builder.add(peu.class, "com.huawei.health.vip.api.EquityApi");
        builder.add(pfa.class, "com.huawei.health.main.api.MainCommonApi");
        builder.add(pey.class, "com.huawei.ui.main.stories.about.AboutApi");
        builder.add(NpsExternalApiImpl.class, "com.huawei.health.nps.api.NpsExternalApi");
        builder.add(rtz.class, "com.huawei.health.main.api.CloudAuthApi");
        builder.add(pfi.class, "com.huawei.health.configuredpage.api.ConfiguredPageApi");
        builder.add(sau.class, "com.huawei.health.main.api.WeChatApi");
        new ModuleProviderWrapper(new pev(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "About";
    }
}
