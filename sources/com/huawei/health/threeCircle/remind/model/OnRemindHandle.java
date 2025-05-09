package com.huawei.health.threeCircle.remind.model;

import android.os.Bundle;
import defpackage.gjr;
import java.util.Map;

/* loaded from: classes4.dex */
public interface OnRemindHandle {
    void destroy();

    boolean isActiveTrigger();

    void onTrigger(Bundle bundle, Map<String, Integer> map, gjr gjrVar);

    void onTrigger(Map<String, Integer> map, gjr gjrVar);

    void registerAlarm();
}
