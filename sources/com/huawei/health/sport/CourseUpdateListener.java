package com.huawei.health.sport;

import com.huawei.health.sportservice.SportComponent;
import com.huawei.health.sportservice.SportVoiceEnableListener;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;

/* loaded from: classes4.dex */
public interface CourseUpdateListener extends ISportDataCallback, SportComponent, SportVoiceEnableListener {
    void seekToAction(int i);

    void switchAction(int i);
}
