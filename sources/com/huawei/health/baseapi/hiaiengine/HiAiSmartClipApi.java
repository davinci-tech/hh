package com.huawei.health.baseapi.hiaiengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

/* loaded from: classes3.dex */
public interface HiAiSmartClipApi {
    int getSmartClipAbility(Context context);

    Rect getSmartCropRect(Bitmap bitmap, int[] iArr);

    void init(Context context);

    boolean isSupportSmartClip();

    void prepare(Context context, float f);

    void release();

    void startLoadPlugin(Context context, float f, HiAiLoadSmartPluginListener hiAiLoadSmartPluginListener);

    void unbindHiAiService();
}
