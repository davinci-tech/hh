package com.huawei.openalliance.ad.inter.data;

import com.huawei.openalliance.ad.utils.cz;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ShareInfo implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f7051a;
    private String b;
    private String c;
    private String d;

    public String getTitle() {
        return this.b;
    }

    public String getShareUrl() {
        return this.d;
    }

    public String getIconUrl() {
        return this.f7051a;
    }

    public String getDescription() {
        return this.c;
    }

    public ShareInfo(com.huawei.openalliance.ad.beans.metadata.ShareInfo shareInfo) {
        if (shareInfo != null) {
            this.f7051a = shareInfo.a();
            this.b = cz.c(shareInfo.b());
            this.c = cz.c(shareInfo.c());
            this.d = shareInfo.d();
        }
    }

    public ShareInfo() {
    }
}
