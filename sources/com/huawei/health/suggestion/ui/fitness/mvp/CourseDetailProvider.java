package com.huawei.health.suggestion.ui.fitness.mvp;

import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.pluginfitnessadvice.LongVideoInfo;

/* loaded from: classes.dex */
public interface CourseDetailProvider {
    void downloadCurActionResource(int i, UiCallback uiCallback);

    int getCourseActionMediaSize(int i);

    void getLongVideo(int i, UiCallback<LongVideoInfo> uiCallback);

    boolean isCurActionResourceDownload(int i);

    boolean isFreeCourse();

    boolean isLongVideoCourse();

    boolean isRunModelCourse();
}
