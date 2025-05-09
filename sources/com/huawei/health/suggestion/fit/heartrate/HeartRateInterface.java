package com.huawei.health.suggestion.fit.heartrate;

import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import java.util.List;

/* loaded from: classes4.dex */
public interface HeartRateInterface {
    int getAverageHeartRate();

    List<HeartRateData> getHeartRateList();

    List<HeartRateData> getInvalidHeartRateList();

    void pushHeartRate(int i, long j);

    void saveHeartRate();
}
