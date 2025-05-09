package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("Folder")
/* loaded from: classes3.dex */
public class FolderFirst {

    @Tag("ExtendedData")
    private ExtendedData mExtendedData;

    @Tag("name")
    private String mFolderName;

    @Tag(order = 2, value = "Folder")
    private FolderSec mFolderSec;

    @Tag(order = 1, value = "Folder")
    private FolderSec mFolderSecInfo;

    public FolderFirst() {
        this("", null, null, null);
    }

    public FolderFirst(String str, ExtendedData extendedData, FolderSec folderSec, FolderSec folderSec2) {
        this.mFolderName = str;
        this.mExtendedData = extendedData;
        this.mFolderSecInfo = folderSec;
        this.mFolderSec = folderSec2;
    }

    public void setFolderSec(FolderSec folderSec) {
        this.mFolderSec = folderSec;
    }

    public FolderSec getFolderSec() {
        return this.mFolderSec;
    }

    public FolderSec getFolderSecInfo() {
        return this.mFolderSecInfo;
    }

    public void setFolderSecInfo(FolderSec folderSec) {
        this.mFolderSecInfo = folderSec;
    }

    public String getFolderName() {
        return this.mFolderName;
    }

    public void setFolderName(String str) {
        this.mFolderName = str;
    }

    public ExtendedData getExtendedData() {
        return this.mExtendedData;
    }

    public void setExtendedData(ExtendedData extendedData) {
        this.mExtendedData = extendedData;
    }
}
