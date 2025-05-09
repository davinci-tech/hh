package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.qi;

/* loaded from: classes5.dex */
public class rx implements qi.a {

    /* renamed from: a, reason: collision with root package name */
    private EventRecord f7523a;

    @Override // com.huawei.openalliance.ad.qi.a
    public String a(String str) {
        if (TextUtils.isEmpty(str) || this.f7523a == null) {
            ho.a("appsFlyer", "invalid para");
            return str;
        }
        if (!EventType.CLICK.value().equals(this.f7523a.i())) {
            ho.a("appsFlyer", "event type not match %s", this.f7523a.i());
            return str;
        }
        String b = this.f7523a.b();
        if (!b(b)) {
            ho.a("appsFlyer", "click destination not match app or harmonyApp or download, is %s", b);
            return str;
        }
        if (str.indexOf("af_engagement_type") == -1) {
            ho.a("appsFlyer", "af key not exist");
            return str;
        }
        if (str.indexOf("click_to_store") != -1) {
            return str.replace("click_to_store", "click_to_download");
        }
        ho.a("appsFlyer", "af value replacement not exist");
        return str;
    }

    private boolean b(String str) {
        return "app".equals(str) || ClickDestination.HARMONY_APP.equals(str) || "download".equals(str);
    }

    public rx(EventRecord eventRecord) {
        this.f7523a = eventRecord;
    }
}
