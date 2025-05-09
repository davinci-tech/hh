package com.huawei.haf.router.facade.service;

import android.content.Context;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.template.SingleServiceProvider;

/* loaded from: classes.dex */
public interface DegradeService extends SingleServiceProvider {
    boolean onLost(Context context, Guidepost guidepost);
}
