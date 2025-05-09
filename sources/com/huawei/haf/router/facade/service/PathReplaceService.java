package com.huawei.haf.router.facade.service;

import android.net.Uri;
import com.huawei.haf.router.facade.template.SingleServiceProvider;

/* loaded from: classes.dex */
public interface PathReplaceService extends SingleServiceProvider {
    String forString(String str);

    Uri forUri(Uri uri);
}
