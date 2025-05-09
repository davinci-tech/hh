package com.huawei.health.baseapi.hiaiengine;

import android.content.Context;
import android.graphics.Bitmap;

/* loaded from: classes8.dex */
public interface HiAiFaceDetectorApi {
    String getFaceAttributesInfo(Bitmap bitmap);

    void initFaceAttributesDetector(Context context);

    void initVisionBase(Context context, HiAiVersionBaseListener hiAiVersionBaseListener);

    void releaseFaceAttributes();
}
