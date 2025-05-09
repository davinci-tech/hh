package com.amap.api.col.p0003sl;

import android.net.Uri;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public abstract class hu extends ka {
    @Override // com.amap.api.col.p0003sl.ka
    public boolean isSupportIPV6() {
        return true;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public String getIPV6URL() {
        if (TextUtils.isEmpty(getURL())) {
            return getURL();
        }
        String url = getURL();
        Uri parse = Uri.parse(url);
        if (parse.getAuthority().startsWith("dualstack-")) {
            return url;
        }
        if (parse.getAuthority().startsWith("restsdk.amap.com")) {
            return parse.buildUpon().authority("dualstack-arestapi.amap.com").build().toString();
        }
        return parse.buildUpon().authority("dualstack-" + parse.getAuthority()).build().toString();
    }
}
