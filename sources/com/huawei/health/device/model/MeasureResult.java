package com.huawei.health.device.model;

/* loaded from: classes3.dex */
public class MeasureResult {

    public enum MeasureErrorCode {
        NOT_SUPPORT,
        NO_DEVICE
    }

    public interface MeasureResultListener {
        void onMeasureDevice(String str, String str2, boolean z);

        void onMeasureFailed(MeasureErrorCode measureErrorCode);
    }
}
