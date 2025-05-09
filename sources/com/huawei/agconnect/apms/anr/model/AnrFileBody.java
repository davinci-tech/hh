package com.huawei.agconnect.apms.anr.model;

import com.google.gson.Gson;
import com.huawei.agconnect.apms.collect.model.event.anr.AnrEvent;
import com.huawei.agconnect.apms.lmn;

/* loaded from: classes8.dex */
public class AnrFileBody extends lmn {
    private AnrEvent anrInfo;

    public AnrFileBody(AnrInfo anrInfo) {
        this.anrInfo = new AnrEvent(anrInfo);
    }

    public AnrEvent getAnrInfo() {
        return this.anrInfo;
    }

    @Override // com.huawei.agconnect.apms.lmn
    public String toJsonString() {
        return new Gson().toJson(this);
    }
}
