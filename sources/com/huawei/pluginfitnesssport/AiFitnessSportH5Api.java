package com.huawei.pluginfitnesssport;

import android.app.Activity;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;

/* loaded from: classes9.dex */
public interface AiFitnessSportH5Api {
    void closeAiFitnessSportActivity();

    void closeCamera();

    void hideH5(int i, BaseResponseCallback baseResponseCallback);

    boolean isShowedH5();

    void openCamera();

    void setAiFitnessSportActivity(Activity activity);

    void setCheckStandCompleteCallback(BaseResponseCallback baseResponseCallback);

    void setH5ExitCallback(BaseResponseCallback baseResponseCallback);

    void setIsAllowScreenShot(boolean z);

    void setOnPauseOrResume(BaseResponseCallback baseResponseCallback);

    void setStatusBar(boolean z);

    void showH5(boolean z);
}
