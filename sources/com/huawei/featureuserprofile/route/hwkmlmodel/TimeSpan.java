package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import defpackage.buc;

/* loaded from: classes3.dex */
public class TimeSpan {

    @Ignore
    private long mBegin;

    @Ignore
    private long mEnd;

    @Tag("begin")
    private String mStringBegin;

    @Tag(order = 1, value = "end")
    private String mStringEnd;

    public void setBegin(long j) {
        this.mBegin = j;
        this.mEnd = j;
        String longTimeToString = longTimeToString(j);
        this.mStringBegin = longTimeToString;
        this.mStringEnd = longTimeToString;
    }

    public long getBegin() {
        return this.mBegin;
    }

    private String longTimeToString(long j) {
        return buc.a(j);
    }

    public String getStringBegin() {
        return this.mStringBegin;
    }

    public String getStringEnd() {
        return this.mStringEnd;
    }
}
