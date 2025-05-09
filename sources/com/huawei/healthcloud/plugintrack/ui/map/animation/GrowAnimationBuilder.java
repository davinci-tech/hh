package com.huawei.healthcloud.plugintrack.ui.map.animation;

import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;

/* loaded from: classes4.dex */
public abstract class GrowAnimationBuilder {
    protected static final float DECELERATION_DISAPPEAR_START = 1.0f;
    protected static final float DECELERATION_DISPLAY_START = 0.0f;
    protected static final float DISPLAY_TENSION = 3.2f;
    protected static final float DISPLAY_TENSION_KM = 2.6f;
    protected static final float FRICTION_DISAPPEAR_END = 0.0f;
    public InterfaceMapCallback mAnimationCallback = null;
    protected long mDisplayFrictionAnimationDuration = 0;
    protected MarkerType mMarkerType = MarkerType.KM_MARKER;

    public enum MarkerType {
        KM_MARKER,
        BASE_MARKER,
        KEYDATA_MARKER,
        ALBUM_MARKER,
        VIDEO_MARKER
    }

    public abstract void disappearAnimation();

    public abstract void displayAnimation();

    public void setAnimationCallback(InterfaceMapCallback interfaceMapCallback) {
        this.mAnimationCallback = interfaceMapCallback;
    }

    public void setFrictionAnimationDuration(long j) {
        this.mDisplayFrictionAnimationDuration = j;
    }

    public GrowAnimationBuilder setType(MarkerType markerType) {
        this.mMarkerType = markerType;
        return this;
    }
}
