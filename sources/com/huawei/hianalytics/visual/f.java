package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.os.Bundle;
import com.huawei.hianalytics.visual.autocollect.EventType;

/* loaded from: classes4.dex */
public class f implements w {
    @Override // com.huawei.hianalytics.visual.w
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (b.a().a(EventType.APP_NOTICE)) {
            return;
        }
        i0.a().a(activity.getIntent());
    }

    @Override // com.huawei.hianalytics.visual.w
    public void onActivityStarted(Activity activity) {
        if (b.a().a(EventType.APP_NOTICE)) {
            return;
        }
        i0.a().a(activity.getIntent());
    }
}
