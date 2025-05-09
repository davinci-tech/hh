package defpackage;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwgpxmodel.Gpx;
import com.huawei.featureuserprofile.route.hwgpxmodel.Route;
import com.huawei.featureuserprofile.route.hwgpxmodel.RoutePoint;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackPoint;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.featureuserprofile.route.navigationparser.BaseParser;
import com.huawei.hwlogsmodel.LogUtil;
import com.tencent.open.SocialConstants;
import health.compact.a.CommonUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class btp extends BaseParser<Gpx> {
    private static final String c = null;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0064 A[SYNTHETIC] */
    @Override // com.huawei.featureuserprofile.route.navigationparser.BaseParser
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.featureuserprofile.route.hwgpxmodel.Gpx parse(org.xmlpull.v1.XmlPullParser r9) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException, java.text.ParseException {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = defpackage.btp.c
            r2 = 2
            java.lang.String r3 = "gpx"
            r9.require(r2, r1, r3)
            com.huawei.featureuserprofile.route.hwgpxmodel.Gpx r1 = new com.huawei.featureuserprofile.route.hwgpxmodel.Gpx
            r1.<init>()
        L12:
            int r4 = r9.next()
            boolean r4 = r8.notEndTag(r4)
            if (r4 == 0) goto L84
            int r4 = r9.getEventType()
            if (r4 == r2) goto L23
            goto L12
        L23:
            java.lang.String r4 = r9.getName()
            r4.hashCode()
            int r5 = r4.hashCode()
            r6 = 113251(0x1ba63, float:1.58698E-40)
            r7 = 1
            if (r5 == r6) goto L57
            r6 = 115117(0x1c1ad, float:1.61313E-40)
            if (r5 == r6) goto L4b
            r6 = 117947(0x1ccbb, float:1.65279E-40)
            if (r5 == r6) goto L3f
            goto L5f
        L3f:
            java.lang.String r5 = "wpt"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L49
            goto L5f
        L49:
            r4 = r2
            goto L62
        L4b:
            java.lang.String r5 = "trk"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L55
            goto L5f
        L55:
            r4 = r7
            goto L62
        L57:
            java.lang.String r5 = "rte"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L61
        L5f:
            r4 = -1
            goto L62
        L61:
            r4 = 0
        L62:
            if (r4 == 0) goto L7c
            if (r4 == r7) goto L74
            if (r4 == r2) goto L6c
            r8.nextTag(r9)
            goto L12
        L6c:
            com.huawei.featureuserprofile.route.hwgpxmodel.Wpt r4 = r8.g(r9)
            r1.addWpt(r4)
            goto L12
        L74:
            com.huawei.featureuserprofile.route.hwgpxmodel.Track r4 = r8.h(r9)
            r0.add(r4)
            goto L12
        L7c:
            com.huawei.featureuserprofile.route.hwgpxmodel.Route r4 = r8.d(r9)
            r1.addRoute(r4)
            goto L12
        L84:
            r2 = 3
            java.lang.String r4 = defpackage.btp.c
            r9.require(r2, r4, r3)
            r1.setTracks(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.btp.parse(org.xmlpull.v1.XmlPullParser):com.huawei.featureuserprofile.route.hwgpxmodel.Gpx");
    }

    private long j(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        return super.readTime(xmlPullParser, "time");
    }

    private int d(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        String str2 = c;
        xmlPullParser.require(2, str2, str);
        int h = CommonUtil.h(readText(xmlPullParser));
        xmlPullParser.require(3, str2, str);
        return h;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x007d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.featureuserprofile.route.hwgpxmodel.Track h(org.xmlpull.v1.XmlPullParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException, java.text.ParseException {
        /*
            r9 = this;
            com.huawei.featureuserprofile.route.hwgpxmodel.Track r0 = new com.huawei.featureuserprofile.route.hwgpxmodel.Track
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = defpackage.btp.c
            r3 = 2
            java.lang.String r4 = "trk"
            r10.require(r3, r2, r4)
        L13:
            int r2 = r10.next()
            boolean r2 = r9.notEndTag(r2)
            if (r2 == 0) goto L85
            int r2 = r10.getEventType()
            if (r2 == r3) goto L24
            goto L13
        L24:
            java.lang.String r2 = r10.getName()
            r2.hashCode()
            int r5 = r2.hashCode()
            r6 = -1809421292(0xffffffff94266c14, float:-8.402173E-27)
            r7 = 1
            java.lang.String r8 = "type"
            if (r5 == r6) goto L58
            r6 = -865403000(0xffffffffcc6aff88, float:-6.160336E7)
            if (r5 == r6) goto L4c
            r6 = 3575610(0x368f3a, float:5.010497E-39)
            if (r5 == r6) goto L43
            goto L60
        L43:
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L4a
            goto L60
        L4a:
            r2 = r3
            goto L63
        L4c:
            java.lang.String r5 = "trkseg"
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L56
            goto L60
        L56:
            r2 = r7
            goto L63
        L58:
            java.lang.String r5 = "extensions"
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L62
        L60:
            r2 = -1
            goto L63
        L62:
            r2 = 0
        L63:
            if (r2 == 0) goto L7d
            if (r2 == r7) goto L75
            if (r2 == r3) goto L6d
            r9.nextTag(r10)
            goto L13
        L6d:
            java.lang.String r2 = r9.readContent(r10, r8)
            r0.setType(r2)
            goto L13
        L75:
            com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment r2 = r9.f(r10)
            r1.add(r2)
            goto L13
        L7d:
            com.huawei.featureuserprofile.route.bean.Extensions r2 = r9.b(r10)
            r0.setExtensions(r2)
            goto L13
        L85:
            r2 = 3
            java.lang.String r3 = defpackage.btp.c
            r10.require(r2, r3, r4)
            r0.setTrackSegments(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.btp.h(org.xmlpull.v1.XmlPullParser):com.huawei.featureuserprofile.route.hwgpxmodel.Track");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x005d, code lost:
    
        if (r1.equals("routeType") == false) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.featureuserprofile.route.bean.Extensions b(org.xmlpull.v1.XmlPullParser r14) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r13 = this;
            com.huawei.featureuserprofile.route.bean.Extensions r0 = new com.huawei.featureuserprofile.route.bean.Extensions
            r0.<init>()
            java.lang.String r1 = defpackage.btp.c
            r2 = 2
            java.lang.String r3 = "extensions"
            r14.require(r2, r1, r3)
        Ld:
            int r1 = r14.next()
            boolean r1 = r13.notEndTag(r1)
            r4 = 3
            if (r1 == 0) goto Lb5
            int r1 = r14.getEventType()
            if (r1 == r2) goto L1f
            goto Ld
        L1f:
            java.lang.String r1 = r14.getName()
            r1.hashCode()
            int r5 = r1.hashCode()
            java.lang.String r6 = "cumulativeDecrease"
            java.lang.String r7 = "cumulativeClimb"
            java.lang.String r8 = "totalDistance"
            java.lang.String r9 = "routeType"
            java.lang.String r10 = "color"
            java.lang.String r11 = "mode"
            java.lang.String r12 = "totalTime"
            switch(r5) {
                case -577281999: goto L72;
                case 3357091: goto L69;
                case 94842723: goto L60;
                case 167668003: goto L59;
                case 314918745: goto L50;
                case 1228549506: goto L47;
                case 1960284849: goto L3e;
                default: goto L3d;
            }
        L3d:
            goto L7b
        L3e:
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L45
            goto L7b
        L45:
            r4 = 6
            goto L7c
        L47:
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L4e
            goto L7b
        L4e:
            r4 = 5
            goto L7c
        L50:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L57
            goto L7b
        L57:
            r4 = 4
            goto L7c
        L59:
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L7c
            goto L7b
        L60:
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L67
            goto L7b
        L67:
            r4 = r2
            goto L7c
        L69:
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L70
            goto L7b
        L70:
            r4 = 1
            goto L7c
        L72:
            boolean r1 = r1.equals(r12)
            if (r1 != 0) goto L79
            goto L7b
        L79:
            r4 = 0
            goto L7c
        L7b:
            r4 = -1
        L7c:
            switch(r4) {
                case 0: goto Lad;
                case 1: goto La6;
                case 2: goto L9f;
                case 3: goto L98;
                case 4: goto L90;
                case 5: goto L89;
                case 6: goto L83;
                default: goto L7f;
            }
        L7f:
            r13.nextTag(r14)
            goto Ld
        L83:
            java.lang.String r1 = "CumulativeDecrease error."
            r13.d(r14, r0, r6, r1)
            goto Ld
        L89:
            java.lang.String r1 = "CumulativeClimb error."
            r13.d(r14, r0, r7, r1)
            goto Ld
        L90:
            java.lang.String r1 = "total distance error."
            r13.d(r14, r0, r8, r1)
            goto Ld
        L98:
            java.lang.String r1 = "RouteType error."
            r13.e(r14, r0, r9, r1)
            goto Ld
        L9f:
            java.lang.String r1 = "color error."
            r13.e(r14, r0, r10, r1)
            goto Ld
        La6:
            java.lang.String r1 = "mode error."
            r13.e(r14, r0, r11, r1)
            goto Ld
        Lad:
            java.lang.String r1 = "total time error."
            r13.d(r14, r0, r12, r1)
            goto Ld
        Lb5:
            java.lang.String r1 = defpackage.btp.c
            r14.require(r4, r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.btp.b(org.xmlpull.v1.XmlPullParser):com.huawei.featureuserprofile.route.bean.Extensions");
    }

    private void d(XmlPullParser xmlPullParser, Extensions extensions, String str, String str2) throws IOException, XmlPullParserException {
        try {
            extensions.addExtension(str, Double.valueOf(readText(xmlPullParser)));
        } catch (NumberFormatException unused) {
            extensions.addExtension(str, Double.valueOf(0.0d));
            LogUtil.b("GpxFileParser", str2);
        }
    }

    private void e(XmlPullParser xmlPullParser, Extensions extensions, String str, String str2) throws IOException, XmlPullParserException {
        try {
            extensions.addExtension(str, Integer.valueOf(CommonUtil.h(readText(xmlPullParser))));
        } catch (NumberFormatException unused) {
            extensions.addExtension(str, 0);
            LogUtil.b("GpxFileParser", str2);
        }
    }

    private Route d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, c, "rte");
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("rtept")) {
                    arrayList.add(c(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, c, "rte");
        Route route = new Route();
        route.setRoutePoint(arrayList);
        return route;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Wpt g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        char c2;
        Wpt wpt = new Wpt();
        String str = c;
        xmlPullParser.require(2, str, "wpt");
        wpt.setLatitude(CommonUtil.a(xmlPullParser.getAttributeValue(str, "lat")));
        wpt.setLongitude(CommonUtil.a(xmlPullParser.getAttributeValue(str, "lon")));
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                switch (name.hashCode()) {
                    case -1809421292:
                        if (name.equals("extensions")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 100510:
                        if (name.equals("ele")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 114375:
                        if (name.equals("sym")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3079825:
                        if (name.equals(SocialConstants.PARAM_APP_DESC)) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3373707:
                        if (name.equals("name")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3560141:
                        if (name.equals("time")) {
                            c2 = 5;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                if (c2 == 0) {
                    wpt.setExtensions(b(xmlPullParser));
                } else if (c2 == 1) {
                    wpt.setElevation(a(xmlPullParser));
                } else if (c2 == 2) {
                    wpt.setSym(d(xmlPullParser, "sym"));
                } else if (c2 == 3) {
                    wpt.setDesc(readTextAndChildText(xmlPullParser, SocialConstants.PARAM_APP_DESC));
                } else if (c2 == 4) {
                    wpt.setName(readContent(xmlPullParser, "name"));
                } else if (c2 == 5) {
                    wpt.setTime(j(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, c, "wpt");
        return wpt;
    }

    private RoutePoint c(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, ParseException {
        RoutePoint routePoint = new RoutePoint();
        String str = c;
        xmlPullParser.require(2, str, "rtept");
        try {
            routePoint.setLatitude(Double.valueOf(xmlPullParser.getAttributeValue(str, "lat")).doubleValue());
        } catch (NumberFormatException unused) {
            routePoint.setLatitude(0.0d);
            LogUtil.b("GpxFileParser", "Latitude error.");
        }
        try {
            routePoint.setLongitude(Double.valueOf(xmlPullParser.getAttributeValue(c, "lon")).doubleValue());
        } catch (NumberFormatException unused2) {
            routePoint.setLongitude(0.0d);
            LogUtil.b("GpxFileParser", "Longitude error.");
        }
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals("ele")) {
                    routePoint.setElevation(a(xmlPullParser));
                } else if (name.equals("time")) {
                    routePoint.setTime(j(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, c, "rtept");
        return routePoint;
    }

    private TrackSegment f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        xmlPullParser.require(2, c, "trkseg");
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("trkpt")) {
                    arrayList.add(i(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, c, "trkseg");
        TrackSegment trackSegment = new TrackSegment();
        trackSegment.setTrackPoints(arrayList);
        return trackSegment;
    }

    private TrackPoint i(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException, ParseException {
        TrackPoint trackPoint = new TrackPoint();
        String str = c;
        xmlPullParser.require(2, str, "trkpt");
        try {
            trackPoint.setLatitude(Double.valueOf(xmlPullParser.getAttributeValue(str, "lat")).doubleValue());
        } catch (NumberFormatException unused) {
            trackPoint.setLatitude(0.0d);
            LogUtil.b("GpxFileParser", "Latitude error.");
        }
        try {
            trackPoint.setLongitude(Double.valueOf(xmlPullParser.getAttributeValue(c, "lon")).doubleValue());
        } catch (NumberFormatException unused2) {
            trackPoint.setLongitude(0.0d);
            LogUtil.b("GpxFileParser", "Longitude error.");
        }
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals("ele")) {
                    trackPoint.setElevation(a(xmlPullParser));
                } else if (name.equals("time")) {
                    trackPoint.setTime(j(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, c, "trkpt");
        return trackPoint;
    }

    private double a(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        double d;
        xmlPullParser.require(2, c, "ele");
        try {
            d = Double.valueOf(readText(xmlPullParser)).doubleValue();
        } catch (NumberFormatException unused) {
            LogUtil.b("GpxFileParser", "LongitudeDegrees error.");
            d = 0.0d;
        }
        xmlPullParser.require(3, c, "ele");
        return d;
    }
}
