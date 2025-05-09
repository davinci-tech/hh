package com.huawei.hms.maps;

import com.huawei.hms.feature.dynamic.LifecycleDelegate;

/* loaded from: classes4.dex */
public interface MapLifecycleDelegate extends LifecycleDelegate {
    void getMapAsync(OnMapReadyCallback onMapReadyCallback);
}
