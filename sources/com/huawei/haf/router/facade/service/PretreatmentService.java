package com.huawei.haf.router.facade.service;

import android.content.Context;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.template.ServiceProvider;

/* loaded from: classes.dex */
public interface PretreatmentService extends ServiceProvider {
    boolean onPretreatment(Context context, Guidepost guidepost);
}
