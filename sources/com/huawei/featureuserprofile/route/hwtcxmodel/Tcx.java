package com.huawei.featureuserprofile.route.hwtcxmodel;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;

@Tag("TrainingCenterDatabase")
/* loaded from: classes3.dex */
public class Tcx {

    @Tag("Activities")
    private Activities mActivities;

    @Attribute("version")
    private String mVersion = "1.0";

    @Attribute("creator")
    private String mCreator = "Health";

    @Attribute("xmlns:xsi")
    private String mXsi = "http://www.w3.org/2001/XMLSchema-instance";

    @Attribute("xmlns")
    private String mXmlns = "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2";

    @Attribute("xsi:schemaLocation")
    private String mSchemaLocation = "http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2 http://www.garmin.com/xmlschemas/TrainingCenterDatabasev2.xsd";

    public void setActivities(Activities activities) {
        this.mActivities = activities;
    }

    public Activities getActivities() {
        return this.mActivities;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String getCreator() {
        return this.mCreator;
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
}
