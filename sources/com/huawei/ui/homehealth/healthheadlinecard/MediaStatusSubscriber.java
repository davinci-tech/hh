package com.huawei.ui.homehealth.healthheadlinecard;

import android.media.session.PlaybackState;
import defpackage.enq;

/* loaded from: classes6.dex */
public interface MediaStatusSubscriber {
    void onMediaChanged(enq enqVar, int i);

    void onPlaybackStateChanged(PlaybackState playbackState);

    void onProgressChanged(int i, int i2, float f, int i3);
}
