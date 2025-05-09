package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("Data")
/* loaded from: classes3.dex */
public class Data {

    @Attribute("name")
    private String mName;

    @Tag("value")
    private Double mValue;

    public Data() {
        this("", Double.valueOf(0.0d));
    }

    public Data(String str, Double d) {
        this.mName = str;
        this.mValue = d;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public Double getValue() {
        return this.mValue;
    }

    public void setValue(Double d) {
        this.mValue = d;
    }
}
