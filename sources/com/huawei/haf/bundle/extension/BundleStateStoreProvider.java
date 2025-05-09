package com.huawei.haf.bundle.extension;

import com.huawei.haf.store.RemoteStoreContentProvider;

/* loaded from: classes.dex */
public final class BundleStateStoreProvider extends RemoteStoreContentProvider {
    @Override // com.huawei.haf.store.RemoteStoreContentProvider
    public String getName() {
        return "app_bundle_info";
    }
}
