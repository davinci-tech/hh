package com.huawei.health.suggestion.fit.callback;

import android.os.Bundle;
import com.huawei.health.suggestion.protobuf.CourseStateProto;

/* loaded from: classes4.dex */
public interface CourseLinkageLifecycle {
    default void chapterBackward(CourseStateProto.CourseState courseState) {
    }

    default void chapterBreak(CourseStateProto.CourseState courseState) {
    }

    default void chapterForward(CourseStateProto.CourseState courseState) {
    }

    void pause(int i);

    void resume(int i);

    void start(int i, Bundle bundle);

    void stop(int i);

    default void volumeAdjust(CourseStateProto.CourseState courseState) {
    }
}
