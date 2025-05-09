package com.huawei.featureuserprofile.route.hwkmlmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;

@Tag(NavigationFileParser.KML)
/* loaded from: classes3.dex */
public class Kml {

    @Ignore
    private Document mDocument;

    @Tag("Folder")
    private FolderFirst mFolderFirst;

    @Attribute("version")
    private String mVersion = "1.0";

    @Attribute("xmlns:xsi")
    private String mXsi = "http://www.w3.org/2001/XMLSchema-instance";

    @Attribute("xmlns")
    private String mXmlns = "http://earth.google.com/kml/2.1";

    @Attribute("xsi:schemaLocation")
    private String mSchemaLocation = "http://earth.google.com/kml/2.1 http://earth.google.com/kml2.1.xsd";

    public void setFolderFirst(FolderFirst folderFirst) {
        this.mFolderFirst = folderFirst;
    }

    public FolderFirst getFolderFirst() {
        return this.mFolderFirst;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getXsi() {
        return this.mXsi;
    }

    public String getXmlns() {
        return this.mXmlns;
    }

    public String getSchemaLocation() {
        return this.mSchemaLocation;
    }

    public Document getDocument() {
        return this.mDocument;
    }

    public void setDocument(Document document) {
        this.mDocument = document;
    }
}
