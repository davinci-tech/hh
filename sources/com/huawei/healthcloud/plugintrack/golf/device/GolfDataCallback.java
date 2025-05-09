package com.huawei.healthcloud.plugintrack.golf.device;

import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseData;
import java.util.List;

/* loaded from: classes4.dex */
public interface GolfDataCallback {
    void onReceived(List<GolfCourseData> list);
}
