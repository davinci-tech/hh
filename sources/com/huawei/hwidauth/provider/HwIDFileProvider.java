package com.huawei.hwidauth.provider;

import android.content.Context;
import android.content.pm.ProviderInfo;
import androidx.core.content.FileProvider;
import defpackage.ksy;

/* loaded from: classes9.dex */
public class HwIDFileProvider extends FileProvider {
    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        try {
            super.attachInfo(context, providerInfo);
        } catch (IllegalArgumentException e) {
            ksy.c("HwIDFileProvider", "IllegalArgumentException. " + e.getLocalizedMessage(), true);
        }
    }
}
