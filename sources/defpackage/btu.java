package defpackage;

import com.huawei.featureuserprofile.route.bean.Extensions;
import com.huawei.featureuserprofile.route.hwtcxmodel.Activities;
import com.huawei.featureuserprofile.route.hwtcxmodel.Activity;
import com.huawei.featureuserprofile.route.hwtcxmodel.Lap;
import com.huawei.featureuserprofile.route.hwtcxmodel.Position;
import com.huawei.featureuserprofile.route.hwtcxmodel.Tcx;
import com.huawei.featureuserprofile.route.hwtcxmodel.Track;
import com.huawei.featureuserprofile.route.hwtcxmodel.TrackPoint;
import com.huawei.featureuserprofile.route.navigationparser.BaseParser;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class btu extends BaseParser<Tcx> {
    private static final String e = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.featureuserprofile.route.navigationparser.BaseParser
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Tcx parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "TrainingCenterDatabase");
        Tcx tcx = new Tcx();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Activities")) {
                    tcx.setActivities(d(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "TrainingCenterDatabase");
        return tcx;
    }

    private Activities d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Activities");
        Activities activities = new Activities();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals(SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    activities.setActivity(c(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Activities");
        return activities;
    }

    private Activity c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        String str = e;
        xmlPullParser.require(2, str, SingleDailyMomentContent.ACTIVITY_TYPE);
        ArrayList arrayList = new ArrayList();
        Activity activity = new Activity();
        activity.setSport(xmlPullParser.getAttributeValue(str, "Sport"));
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Lap")) {
                    arrayList.add(e(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, SingleDailyMomentContent.ACTIVITY_TYPE);
        activity.setLap(arrayList);
        return activity;
    }

    private Lap e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Lap");
        Lap.Builder builder = new Lap.Builder();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                b(xmlPullParser, builder);
            }
        }
        xmlPullParser.require(3, e, "Lap");
        return builder.build();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void b(XmlPullParser xmlPullParser, Lap.Builder builder) throws IOException, XmlPullParserException, ParseException {
        char c;
        String name = xmlPullParser.getName();
        name.hashCode();
        switch (name.hashCode()) {
            case -1738178945:
                if (name.equals("DistanceMeters")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -493138223:
                if (name.equals("CumulativeDecrease")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -203399890:
                if (name.equals("TotalTimeSeconds")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 81068331:
                if (name.equals("Track")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 184043572:
                if (name.equals("Extensions")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 231531874:
                if (name.equals("CumulativeClimb")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            try {
                builder.setDistanceMeters(Double.parseDouble(readText(xmlPullParser)));
                return;
            } catch (NumberFormatException unused) {
                LogUtil.b("TcxFileParser", "total distance error.");
                return;
            }
        }
        if (c == 1) {
            try {
                builder.setCumulativeDecrease(Double.parseDouble(readText(xmlPullParser)));
                return;
            } catch (NumberFormatException unused2) {
                LogUtil.b("TcxFileParser", "CumulativeDecrease error.");
                return;
            }
        }
        if (c == 2) {
            try {
                builder.setTotalTimeSeconds(Double.parseDouble(readText(xmlPullParser)));
            } catch (NumberFormatException unused3) {
                LogUtil.b("TcxFileParser", "total time error.");
            }
        } else {
            if (c == 3) {
                builder.setTrack(g(xmlPullParser));
                return;
            }
            if (c == 4) {
                builder.setExtensions(b(xmlPullParser));
            } else {
                if (c == 5) {
                    try {
                        builder.setCumulativeClimb(Double.parseDouble(readText(xmlPullParser)));
                        return;
                    } catch (NumberFormatException unused4) {
                        LogUtil.b("TcxFileParser", "CumulativeClimb error.");
                        return;
                    }
                }
                nextTag(xmlPullParser);
            }
        }
    }

    private Track g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Track");
        ArrayList arrayList = new ArrayList();
        Track track = new Track();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Trackpoint")) {
                    arrayList.add(j(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Track");
        track.setTrackPoint(arrayList);
        return track;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private TrackPoint j(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        char c;
        xmlPullParser.require(2, e, "Trackpoint");
        TrackPoint trackPoint = new TrackPoint();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                switch (name.hashCode()) {
                    case -144259220:
                        if (name.equals("AltitudeMeters")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 2606829:
                        if (name.equals("Time")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 184043572:
                        if (name.equals("Extensions")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 812449097:
                        if (name.equals("Position")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    try {
                        trackPoint.setAltitudeMeters(Double.valueOf(readText(xmlPullParser)).doubleValue());
                    } catch (NumberFormatException unused) {
                        trackPoint.setAltitudeMeters(0.0d);
                        LogUtil.b("TcxFileParser", "AltitudeMeters error.");
                    }
                } else if (c == 1) {
                    trackPoint.setTime(f(xmlPullParser));
                } else if (c == 2) {
                    trackPoint.setExtensions(b(xmlPullParser));
                } else if (c == 3) {
                    trackPoint.setPosition(i(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Trackpoint");
        return trackPoint;
    }

    private Position i(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, e, "Position");
        Position position = new Position();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals("LongitudeDegrees")) {
                    try {
                        position.setLongitudeDegrees(Double.valueOf(readText(xmlPullParser)).doubleValue());
                    } catch (NumberFormatException unused) {
                        position.setLongitudeDegrees(Double.valueOf(readText(xmlPullParser)).doubleValue());
                        LogUtil.b("TcxFileParser", "LongitudeDegrees error.");
                    }
                } else if (name.equals("LatitudeDegrees")) {
                    try {
                        position.setLatitudeDegrees(Double.valueOf(readText(xmlPullParser)).doubleValue());
                    } catch (NumberFormatException unused2) {
                        position.setLatitudeDegrees(Double.valueOf(readText(xmlPullParser)).doubleValue());
                        LogUtil.b("TcxFileParser", "LatitudeDegrees error.");
                    }
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Position");
        return position;
    }

    private long f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        return super.readTime(xmlPullParser, "Time");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Extensions b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        char c;
        xmlPullParser.require(2, e, "Extensions");
        Extensions extensions = new Extensions();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                switch (name.hashCode()) {
                    case -2123331325:
                        if (name.equals(Lap.ROUTE_TYPE)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1061240877:
                        if (name.equals("PointColor")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -726802935:
                        if (name.equals("PointIcon")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -726672589:
                        if (name.equals("PointMode")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -726655973:
                        if (name.equals(TrackPoint.NAME)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 219873388:
                        if (name.equals(TrackPoint.DESC)) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    extensions.addExtension(Lap.ROUTE_TYPE, Integer.valueOf(CommonUtil.h(readText(xmlPullParser))));
                } else if (c == 1) {
                    extensions.addExtension("PointColor", Integer.valueOf(CommonUtil.h(readText(xmlPullParser))));
                } else if (c == 2) {
                    extensions.addExtension("PointIcon", Integer.valueOf(CommonUtil.h(readText(xmlPullParser))));
                } else if (c == 3) {
                    extensions.addExtension("PointMode", Integer.valueOf(CommonUtil.h(readText(xmlPullParser))));
                } else if (c == 4) {
                    extensions.addExtension(TrackPoint.NAME, readText(xmlPullParser));
                } else if (c == 5) {
                    extensions.addExtension(TrackPoint.DESC, readTextAndChildText(xmlPullParser, TrackPoint.DESC));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Extensions");
        return extensions;
    }
}
