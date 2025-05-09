package com.huawei.featureuserprofile.route.hwtcxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import java.util.List;

@Tag(SingleDailyMomentContent.ACTIVITY_TYPE)
/* loaded from: classes3.dex */
public class Activity {

    @Tag(order = 1, value = "Id")
    private String mId;

    @Tag(order = 2)
    private List<Lap> mLap;

    @Attribute("Sport")
    private String mSport;

    public Activity() {
        this("", null, "");
    }

    public Activity(String str, List<Lap> list, String str2) {
        this.mId = str;
        this.mLap = list;
        this.mSport = str2;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getId() {
        return this.mId;
    }

    public void setSport(String str) {
        this.mSport = str;
    }

    public String getSport() {
        return this.mSport;
    }

    public void setLap(List<Lap> list) {
        this.mLap = list;
    }

    public List<Lap> getLap() {
        return this.mLap;
    }
}
