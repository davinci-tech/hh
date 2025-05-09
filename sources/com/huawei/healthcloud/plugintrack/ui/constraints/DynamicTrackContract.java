package com.huawei.healthcloud.plugintrack.ui.constraints;

import androidx.lifecycle.LifecycleObserver;

/* loaded from: classes4.dex */
public interface DynamicTrackContract {

    /* loaded from: classes.dex */
    public interface Ipresenter<T extends Iview> extends LifecycleObserver {
        void judgeVipStatus();

        void switchPremiumEdition(boolean z);
    }

    /* loaded from: classes.dex */
    public interface Iview {
        void judgeVipResult(boolean z);
    }
}
