package com.huawei.maps.offlinedata.health.init;

import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import java.util.List;

/* loaded from: classes5.dex */
public interface OnCheckUpdatesListListener {
    void onOfflineMapsInfoResult(List<MapOfflineDataItemEntity> list);
}
