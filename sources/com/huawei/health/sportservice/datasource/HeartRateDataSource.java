package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.impl.HeartRateImpl;
import com.huawei.health.sportservice.inter.DataSourceCallback;

/* loaded from: classes8.dex */
public class HeartRateDataSource extends DataSource {
    private HeartRateImpl mHeartRateImpl;

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void destroy() {
    }

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void init() {
    }

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void registerDataCallback(DataSourceCallback dataSourceCallback) {
        HeartRateImpl heartRateImpl = this.mHeartRateImpl;
        if (heartRateImpl == null || dataSourceCallback == null) {
            return;
        }
        heartRateImpl.setHeartRateCallback(dataSourceCallback);
    }

    public void setHeartRateImpl(HeartRateImpl heartRateImpl) {
        this.mHeartRateImpl = heartRateImpl;
    }
}
