package com.huawei.featureuserprofile.route.hwkmlmodel;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class Document {
    private List<FolderSec> mFolderSec = new ArrayList();
    private LineString mLineString;

    public void addLineString() {
    }

    public List<FolderSec> getFolderSec() {
        return this.mFolderSec;
    }

    public void setFolderSec(List<FolderSec> list) {
        this.mFolderSec = list;
    }

    public void addFolderSec(FolderSec folderSec) {
        this.mFolderSec.add(folderSec);
    }

    public LineString getLineString() {
        return this.mLineString;
    }
}
