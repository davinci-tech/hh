package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.qi;

/* loaded from: classes5.dex */
public class sj implements qi.a {

    /* renamed from: a, reason: collision with root package name */
    private EventRecord f7527a;

    @Override // com.huawei.openalliance.ad.qi.a
    public String a(String str) {
        if (TextUtils.isEmpty(str) || this.f7527a == null) {
            ho.a("VideoEventStrategy", "invalid para");
            return str;
        }
        if (EventType.VIDEOTIME.value().equals(this.f7527a.i())) {
            return (this.f7527a.ai() == 0 || !str.contains("__HW_VIDEO_TIME__")) ? str : str.replace("__HW_VIDEO_TIME__", String.valueOf((int) Math.ceil(this.f7527a.ai() / 1000.0d)));
        }
        ho.a("VideoEventStrategy", "event type not match playTime, is %s", this.f7527a.i());
        return str;
    }

    public sj(EventRecord eventRecord) {
        this.f7527a = eventRecord;
    }
}
