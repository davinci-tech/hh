package com.huawei.health.suggestion.h5pro;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gia;

/* loaded from: classes4.dex */
public class BodySenseH5Bridge extends JsBaseModule {
    private static final String TAG = "BodySenseH5Bridge";

    @JavascriptInterface
    public void registerStatusCallback(final long j) {
        LogUtil.a(TAG, "registerStatusCallback:", Long.valueOf(j));
        final gia giaVar = new gia();
        giaVar.c();
        CoachController.d().d(CoachController.StatusSource.NEW_LINK_WEAR, new CourseLinkageLifecycle() { // from class: com.huawei.health.suggestion.h5pro.BodySenseH5Bridge.1
            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void start(int i, Bundle bundle) {
                LogUtil.a(BodySenseH5Bridge.TAG, "wear start sportType:", Integer.valueOf(i));
                BodySenseH5Bridge.this.onSuccessCallback(j, 4);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void resume(int i) {
                LogUtil.a(BodySenseH5Bridge.TAG, "wear resume sportType:", Integer.valueOf(i));
                BodySenseH5Bridge.this.onSuccessCallback(j, 1);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void pause(int i) {
                LogUtil.a(BodySenseH5Bridge.TAG, "wear onPauseCoach sportType:", Integer.valueOf(i));
                BodySenseH5Bridge.this.onSuccessCallback(j, 2);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void stop(int i) {
                LogUtil.a(BodySenseH5Bridge.TAG, "wear onPauseCoach sportType:", Integer.valueOf(i));
                BodySenseH5Bridge.this.onSuccessCallback(j, 3);
                giaVar.d();
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void chapterForward(CourseStateProto.CourseState courseState) {
                LogUtil.a(BodySenseH5Bridge.TAG, "wear onChapterForward courseState:", courseState.toString());
                BodySenseH5Bridge.this.onSuccessCallback(j, 5);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void chapterBackward(CourseStateProto.CourseState courseState) {
                LogUtil.a(BodySenseH5Bridge.TAG, "wear onChapterBackward courseState:", courseState.toString());
                BodySenseH5Bridge.this.onSuccessCallback(j, 6);
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void volumeAdjust(CourseStateProto.CourseState courseState) {
                if (courseState.getCurrentVolume() > 0) {
                    giaVar.d(courseState.getCurrentVolume());
                }
            }
        });
        CoachController.d().d(CoachController.StatusSource.APP, new CourseLinkageLifecycle() { // from class: com.huawei.health.suggestion.h5pro.BodySenseH5Bridge.2
            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void pause(int i) {
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void resume(int i) {
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void start(int i, Bundle bundle) {
            }

            @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
            public void stop(int i) {
                giaVar.d();
            }
        });
    }

    @JavascriptInterface
    public void registerWearDataCallback(final long j) {
        LogUtil.a(TAG, "registerWearDataCallback:", Long.valueOf(j));
        BodySenseManager.getInstance().setResponseCallback(new IBaseResponseCallback() { // from class: com.huawei.health.suggestion.h5pro.BodySenseH5Bridge$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BodySenseH5Bridge.this.m499x72c46ded(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$registerWearDataCallback$0$com-huawei-health-suggestion-h5pro-BodySenseH5Bridge, reason: not valid java name */
    /* synthetic */ void m499x72c46ded(long j, int i, Object obj) {
        onSuccessCallback(j, obj);
    }
}
