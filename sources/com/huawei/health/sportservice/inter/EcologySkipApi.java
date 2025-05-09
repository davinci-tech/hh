package com.huawei.health.sportservice.inter;

import android.os.Bundle;
import com.huawei.health.sportservice.datasource.SkipDataAiSource;

/* loaded from: classes4.dex */
public interface EcologySkipApi {
    void setAiRopeListener(SkipDataAiSource skipDataAiSource);

    void setBleStateListener(String str);

    void updateDataToSource(Bundle bundle);
}
