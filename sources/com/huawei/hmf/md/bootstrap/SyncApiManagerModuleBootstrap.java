package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.jqn;
import defpackage.jqo;
import defpackage.jqq;
import defpackage.jqs;
import defpackage.jqt;
import defpackage.jqu;
import defpackage.jqv;
import defpackage.jqw;

/* loaded from: classes4.dex */
public final class SyncApiManagerModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(jqn.class, "com.huawei.syncmgr.calendarsync.CalendarSyncApi");
        builder.add(jqo.class, "com.huawei.syncmgr.alarmdata.AlarmDataSyncApi");
        builder.add(jqs.class, "com.huawei.syncmgr.basicdata.BasicDataSyncApi");
        builder.add(jqt.class, "com.huawei.syncmgr.SyncStressDataApi");
        builder.add(jqq.class, "com.huawei.syncmgr.coresleep.CoreSleepSyncApi");
        builder.add(jqw.class, "com.huawei.syncmgr.menstrual.MenstrualSyncApi");
        builder.add(jqu.class, "com.huawei.syncmgr.periodrri.PeriodRriSyncApi");
        new ModuleProviderWrapper(new jqv(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "SyncApiManager";
    }
}
