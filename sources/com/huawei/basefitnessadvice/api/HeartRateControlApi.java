package com.huawei.basefitnessadvice.api;

import android.util.Pair;
import com.huawei.hwbasemgr.ResponseCallback;

/* loaded from: classes3.dex */
public interface HeartRateControlApi {
    Pair<Integer, Integer> getCourseZoneHeartRateScope(Pair<Float, Float> pair, String str);

    Pair<Float, Float> getCourseZoneSpeedScope(Pair<Integer, Integer> pair, float f);

    int getCourseZoneTargetHeartRate(Pair<Integer, Integer> pair);

    float getCourseZoneTargetSpeed(int i);

    void initPersonalParam(int i, ResponseCallback responseCallback);
}
