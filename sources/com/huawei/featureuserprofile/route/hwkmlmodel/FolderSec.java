package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import java.util.List;

@Tag("Folder")
/* loaded from: classes3.dex */
public class FolderSec {

    @Tag("name")
    private String mName = "Track Points";

    @Tag(order = 1)
    private List<Placemark> mPlaceMark;

    public void setPlaceMark(List<Placemark> list) {
        this.mPlaceMark = list;
    }

    public List<Placemark> getPlaceMark() {
        return this.mPlaceMark;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }
}
