package com.huawei.maps.offlinedata.service.persist.dao;

import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import java.util.List;

/* loaded from: classes5.dex */
public interface c {
    OfflineDataTaskEntity a(String str, String str2);

    List<OfflineDataTaskEntity> a();

    List<OfflineDataTaskEntity> a(List<String> list);

    void a(OfflineDataTaskEntity offlineDataTaskEntity);

    void a(String str);

    void a(String str, int i, int i2);

    void a(String str, String str2, String str3);

    OfflineDataTaskEntity b(String str);

    void b();
}
