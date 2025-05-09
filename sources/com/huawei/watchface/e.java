package com.huawei.watchface;

import com.huawei.watchface.mvp.model.latona.provider.ElementsProvider;
import com.huawei.watchface.mvp.model.latona.provider.ResourceResolver;
import com.huawei.watchface.utils.callback.IPackageNamePathCallback;

/* loaded from: classes9.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private ResourceResolver f10999a;
    private ElementsProvider b;

    public e(String str, String str2, String str3, IPackageNamePathCallback iPackageNamePathCallback) {
        this.f10999a = new ResourceResolver(str, str2, str3, iPackageNamePathCallback);
    }

    public ElementsProvider a(boolean z) {
        if (this.b == null) {
            this.b = new ElementsProvider(this.f10999a, z);
        }
        return this.b;
    }
}
