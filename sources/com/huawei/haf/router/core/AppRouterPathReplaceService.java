package com.huawei.haf.router.core;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.router.facade.service.PathReplaceService;
import health.compact.a.LogConfig;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
final class AppRouterPathReplaceService {
    private final PathReplaceService b = (PathReplaceService) AppRouterHelper.e(PathReplaceService.class);

    AppRouterPathReplaceService() {
    }

    String b(String str) {
        String d = AppRouterHelper.a().d(str);
        if (TextUtils.isEmpty(d)) {
            d = e(str);
        }
        if (!str.equals(d)) {
            LogUtil.c("HAF_PathReplace", "forString: ", str, " --> ", d);
        }
        return d;
    }

    Uri zM_(Uri uri) {
        Uri zR_ = AppRouterHelper.a().zR_(uri);
        if (zR_ == null) {
            zR_ = zL_(uri);
        }
        if (uri != zR_ && !uri.equals(zR_)) {
            if (LogConfig.c(1)) {
                LogUtil.d("HAF_PathReplace", "forUri: ", uri, " --> ", zR_);
            } else {
                LogUtil.c("HAF_PathReplace", "forUri: ", uri.getPath(), " --> ", zR_.getPath());
            }
        }
        return zR_;
    }

    private String e(String str) {
        PathReplaceService pathReplaceService = this.b;
        return pathReplaceService != null ? pathReplaceService.forString(str) : str;
    }

    private Uri zL_(Uri uri) {
        PathReplaceService pathReplaceService = this.b;
        return pathReplaceService != null ? pathReplaceService.forUri(uri) : uri;
    }
}
