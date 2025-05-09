package com.huawei.health.motiontrack.api;

import com.huawei.haf.router.facade.service.PretreatmentService;

/* loaded from: classes.dex */
public interface MotionTrackPretreatmentService extends PretreatmentService {
    int getSportType(int i);

    int getTargetType(String str);
}
