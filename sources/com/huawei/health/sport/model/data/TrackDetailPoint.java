package com.huawei.health.sport.model.data;

import android.text.TextUtils;

/* loaded from: classes8.dex */
public interface TrackDetailPoint {
    default int estimatedSize() {
        return 20;
    }

    void fromTrackString(String[] strArr);

    default int getMinFieldNum() {
        return 2;
    }

    void toTrackString(StringBuffer stringBuffer);

    default String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(estimatedSize());
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    default void fromTrackString(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        fromTrackString(str.split(";"));
    }
}
