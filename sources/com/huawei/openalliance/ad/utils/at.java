package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class at {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7594a = "com.huawei.openalliance.ad.utils.at";
    private final Context b;
    private final AppInfo c;

    @JavascriptInterface
    public void showPageDetail(int i) {
        ho.b(f7594a, "show page details type:" + i);
        if (i == 1) {
            a();
        } else {
            if (i != 2) {
                return;
            }
            b();
        }
    }

    private void b() {
        String str = f7594a;
        ho.b(str, "load permissionUrl start.");
        AppInfo appInfo = this.c;
        if (appInfo == null || TextUtils.isEmpty(appInfo.getPermissionUrl())) {
            ho.b(str, "load permission url is empty.");
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.at.2
                @Override // java.lang.Runnable
                public void run() {
                    ao.a(at.this.b, at.this.c.getPermissionUrl());
                }
            });
        }
    }

    private void a() {
        String str = f7594a;
        ho.b(str, "load privacyUrl start.");
        AppInfo appInfo = this.c;
        if (appInfo == null || TextUtils.isEmpty(appInfo.getPrivacyLink())) {
            ho.b(str, "load privacy url is empty.");
        } else {
            dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.at.1
                @Override // java.lang.Runnable
                public void run() {
                    ao.a(at.this.b, at.this.c.getPrivacyLink());
                }
            });
        }
    }

    public at(Context context, AppInfo appInfo) {
        this.b = context;
        this.c = appInfo;
    }
}
