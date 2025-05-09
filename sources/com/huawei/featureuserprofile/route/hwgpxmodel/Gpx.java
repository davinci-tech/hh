package com.huawei.featureuserprofile.route.hwgpxmodel;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Attribute;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Ignore;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Tag;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.route.RouteType;
import java.util.ArrayList;
import java.util.List;

@Tag(NavigationFileParser.GPX)
/* loaded from: classes3.dex */
public class Gpx {

    @Tag("metadata")
    private Metadata mMetadata;

    @Tag(order = 1)
    private List<Track> mTracks;

    @Tag(order = 2, value = "wpt")
    private List<Wpt> mWpts;

    @Attribute("version")
    private String mVersion = "1.0";

    @Attribute("creator")
    private String mCreator = "Health";

    @Attribute("xmlns:xsi")
    private String mXsi = "http://www.w3.org/2001/XMLSchema-instance";

    @Attribute("xmlns")
    private String mXmlns = "http://www.topografix.com/GPX/1/0";

    @Attribute("xsi:schemaLocation")
    private String mSchemaLocation = "http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd";

    @Ignore
    private List<Route> mRoute = new ArrayList();

    public <T> void addMetadata(String str, T t) {
        if (this.mMetadata == null) {
            this.mMetadata = new Metadata();
        }
        this.mMetadata.addMetadata(str, t);
    }

    public void addTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("track cannot be null.");
        }
        if (this.mTracks == null) {
            this.mTracks = new ArrayList();
        }
        this.mTracks.add(track);
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

    public List<Track> getTracks() {
        return this.mTracks;
    }

    public void setTracks(List<Track> list) {
        this.mTracks = list;
    }

    public List<Wpt> getWpts() {
        return this.mWpts;
    }

    public void setWpts(List<Wpt> list) {
        this.mWpts = list;
    }

    public Metadata getMetadata() {
        return this.mMetadata;
    }

    public void setMetadata(Metadata metadata) {
        this.mMetadata = metadata;
    }

    public List<Route> getRoute() {
        return this.mRoute;
    }

    public void addRoute(Route route) {
        this.mRoute.add(route);
    }

    public void setRoute(List<Route> list) {
        this.mRoute = list;
    }

    public void addWpt(Wpt wpt) {
        if (wpt == null) {
            return;
        }
        if (this.mWpts == null) {
            this.mWpts = new ArrayList();
        }
        this.mWpts.add(wpt);
    }

    public int getRouteType() {
        if (CollectionUtils.d(this.mTracks) || this.mTracks.get(0).getExtensions() == null) {
            return RouteType.DEFAULT.routeType();
        }
        return Extensions.getInt(this.mTracks.get(0).getExtensions().getExtensionsMap(), "routeType", RouteType.DEFAULT.routeType());
    }
}
