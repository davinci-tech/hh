package com.huawei.featureuserprofile.route.hwgpxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("extensions")
/* loaded from: classes3.dex */
public class TrackPointExtensions {

    @Ignore
    private int mHeart;

    @Tag("heartrate")
    private String mHr;

    public int getHeart() {
        return this.mHeart;
    }

    public void setHeart(int i) {
        this.mHeart = i;
        this.mHr = String.valueOf(i);
    }

    public String getHr() {
        return this.mHr;
    }
}
