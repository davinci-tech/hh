package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ExtendedData {

    @Tag(order = 0)
    private List<Data> mData = new ArrayList();

    public List<Data> getData() {
        return this.mData;
    }

    public void setData(List<Data> list) {
        this.mData = list;
    }
}
