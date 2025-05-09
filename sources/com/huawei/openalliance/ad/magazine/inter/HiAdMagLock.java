package com.huawei.openalliance.ad.magazine.inter;

import android.content.Context;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.magazine.inter.listener.MagLockListener;
import com.huawei.openalliance.ad.processor.eventbeans.MagLockEventInfo;
import java.util.Set;

/* loaded from: classes9.dex */
public interface HiAdMagLock {
    void loadAds(String[] strArr, Set<String> set, boolean z, MagLockListener magLockListener);

    void onNetworkConnected(Context context);

    void preloadAds(String[] strArr, Set<String> set, boolean z, MagLockListener magLockListener);

    void reportEvent(Context context, String str, EventType eventType, MagLockEventInfo magLockEventInfo);

    public static final class Builder {
        public final HiAdMagLock build(Context context) {
            return new a(context);
        }
    }
}
