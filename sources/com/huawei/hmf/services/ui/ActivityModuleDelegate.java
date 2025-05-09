package com.huawei.hmf.services.ui;

import android.app.Activity;
import android.content.Intent;
import com.huawei.hmf.annotation.ActivityDefine;
import com.huawei.hmf.repository.ComponentRepository;
import com.huawei.hmf.services.ui.internal.ActivityData;

/* loaded from: classes9.dex */
public class ActivityModuleDelegate {
    private static final String TAG = "ActivityModuleDelegate";
    private Activity activity;

    private ActivityModuleDelegate(Activity activity) {
        this.activity = activity;
    }

    public <T> T getProtocol() {
        Intent intent = this.activity.getIntent();
        if (intent == null) {
            return null;
        }
        ActivityData activityData = new ActivityData(intent);
        String moduleName = activityData.getModuleName();
        if (moduleName != null) {
            ComponentRepository.getRepository().lookup(moduleName);
        }
        return (T) activityData.getProtocol((ActivityDefine) this.activity.getClass().getAnnotation(ActivityDefine.class));
    }

    public static ActivityModuleDelegate create(Activity activity) {
        return new ActivityModuleDelegate(activity);
    }
}
