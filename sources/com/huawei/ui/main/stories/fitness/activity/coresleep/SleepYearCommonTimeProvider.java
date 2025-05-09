package com.huawei.ui.main.stories.fitness.activity.coresleep;

import defpackage.fdl;
import defpackage.fdp;

/* loaded from: classes9.dex */
public class SleepYearCommonTimeProvider extends SleepTimeProvider {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepTimeProvider
    protected boolean d() {
        return true;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepTimeProvider
    protected boolean c(fdp fdpVar) {
        return fdpVar != null && ((fdl) fdpVar.c()).v() > 0;
    }
}
