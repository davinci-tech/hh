package defpackage;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwkmlmodel.Data;
import com.huawei.featureuserprofile.route.hwkmlmodel.Document;
import com.huawei.featureuserprofile.route.hwkmlmodel.ExtendedData;
import com.huawei.featureuserprofile.route.hwkmlmodel.FolderFirst;
import com.huawei.featureuserprofile.route.hwkmlmodel.FolderSec;
import com.huawei.featureuserprofile.route.hwkmlmodel.GxTrack;
import com.huawei.featureuserprofile.route.hwkmlmodel.Kml;
import com.huawei.featureuserprofile.route.hwkmlmodel.LineString;
import com.huawei.featureuserprofile.route.hwkmlmodel.Placemark;
import com.huawei.featureuserprofile.route.hwkmlmodel.Point;
import com.huawei.featureuserprofile.route.hwkmlmodel.TimeSpan;
import com.huawei.featureuserprofile.route.navigationparser.BaseParser;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class btt extends BaseParser<Kml> {
    private static final String e = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.featureuserprofile.route.navigationparser.BaseParser
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Kml parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, NavigationFileParser.KML);
        Kml kml = new Kml();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals("Document")) {
                    kml.setDocument(b(xmlPullParser));
                } else if (name.equals("Folder")) {
                    kml.setFolderFirst(a(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, NavigationFileParser.KML);
        return kml;
    }

    private Document b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Document");
        Document document = new Document();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("Folder")) {
                    document.addFolderSec(g(xmlPullParser));
                } else if (name.equals("Placemark")) {
                    FolderSec folderSec = new FolderSec();
                    ArrayList arrayList = new ArrayList();
                    folderSec.setPlaceMark(arrayList);
                    arrayList.add(h(xmlPullParser));
                    document.addFolderSec(folderSec);
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Document");
        return document;
    }

    private FolderFirst a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Folder");
        FolderFirst folderFirst = new FolderFirst();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals("ExtendedData")) {
                    folderFirst.setExtendedData(d(xmlPullParser));
                } else if (name.equals("Folder")) {
                    folderFirst.setFolderSec(g(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Folder");
        return folderFirst;
    }

    private ExtendedData d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, e, "ExtendedData");
        ExtendedData extendedData = new ExtendedData();
        ArrayList arrayList = new ArrayList();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Data")) {
                    arrayList.add(e(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "ExtendedData");
        extendedData.setData(arrayList);
        return extendedData;
    }

    private Data e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str = e;
        xmlPullParser.require(2, str, "Data");
        Data data = new Data();
        data.setName(xmlPullParser.getAttributeValue(str, "name"));
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("value")) {
                    try {
                        data.setValue(Double.valueOf(readText(xmlPullParser)));
                    } catch (NumberFormatException unused) {
                        data.setValue(Double.valueOf(0.0d));
                        LogUtil.b("KmlFileParser", "value error.");
                    }
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Data");
        return data;
    }

    private FolderSec g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Folder");
        FolderSec folderSec = new FolderSec();
        ArrayList arrayList = new ArrayList();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("Placemark")) {
                    arrayList.add(h(xmlPullParser));
                } else if (name.equals("Folder")) {
                    e(xmlPullParser, arrayList);
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Folder");
        folderSec.setPlaceMark(arrayList);
        return folderSec;
    }

    private void e(XmlPullParser xmlPullParser, List<Placemark> list) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Folder");
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Placemark")) {
                    list.add(h(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Folder");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0064, code lost:
    
        if (r3.equals("Point") == false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.featureuserprofile.route.hwkmlmodel.Placemark h(org.xmlpull.v1.XmlPullParser r9) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException, java.text.ParseException {
        /*
            r8 = this;
            java.lang.String r0 = defpackage.btt.e
            r1 = 2
            java.lang.String r2 = "Placemark"
            r9.require(r1, r0, r2)
            com.huawei.featureuserprofile.route.hwkmlmodel.Placemark r0 = new com.huawei.featureuserprofile.route.hwkmlmodel.Placemark
            r0.<init>()
        Ld:
            int r3 = r9.next()
            boolean r3 = r8.notEndTag(r3)
            r4 = 3
            if (r3 == 0) goto Ld4
            int r3 = r9.getEventType()
            if (r3 == r1) goto L1f
            goto Ld
        L1f:
            java.lang.String r3 = r9.getName()
            r3.hashCode()
            int r5 = r3.hashCode()
            java.lang.String r6 = "name"
            java.lang.String r7 = "description"
            switch(r5) {
                case -2012744745: goto L79;
                case -1724546052: goto L70;
                case 3373707: goto L67;
                case 77292912: goto L5e;
                case 81068331: goto L53;
                case 89139371: goto L48;
                case 218620067: goto L3d;
                case 1806700869: goto L32;
                default: goto L31;
            }
        L31:
            goto L84
        L32:
            java.lang.String r4 = "LineString"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L3b
            goto L84
        L3b:
            r4 = 7
            goto L85
        L3d:
            java.lang.String r4 = "ExtendedData"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L46
            goto L84
        L46:
            r4 = 6
            goto L85
        L48:
            java.lang.String r4 = "MultiGeometry"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L51
            goto L84
        L51:
            r4 = 5
            goto L85
        L53:
            java.lang.String r4 = "Track"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L5c
            goto L84
        L5c:
            r4 = 4
            goto L85
        L5e:
            java.lang.String r5 = "Point"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L85
            goto L84
        L67:
            boolean r3 = r3.equals(r6)
            if (r3 != 0) goto L6e
            goto L84
        L6e:
            r4 = r1
            goto L85
        L70:
            boolean r3 = r3.equals(r7)
            if (r3 != 0) goto L77
            goto L84
        L77:
            r4 = 1
            goto L85
        L79:
            java.lang.String r4 = "TimeSpan"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L82
            goto L84
        L82:
            r4 = 0
            goto L85
        L84:
            r4 = -1
        L85:
            switch(r4) {
                case 0: goto Lcb;
                case 1: goto Lc2;
                case 2: goto Lb9;
                case 3: goto Lb0;
                case 4: goto La7;
                case 5: goto L9e;
                case 6: goto L95;
                case 7: goto L8c;
                default: goto L88;
            }
        L88:
            r8.nextTag(r9)
            goto Ld
        L8c:
            com.huawei.featureuserprofile.route.hwkmlmodel.LineString r3 = r8.i(r9)
            r0.setLineString(r3)
            goto Ld
        L95:
            com.huawei.featureuserprofile.route.hwkmlmodel.ExtendedData r3 = r8.d(r9)
            r0.setExtendedData(r3)
            goto Ld
        L9e:
            com.huawei.featureuserprofile.route.hwkmlmodel.LineString r3 = r8.f(r9)
            r0.setLineString(r3)
            goto Ld
        La7:
            com.huawei.featureuserprofile.route.hwkmlmodel.GxTrack r3 = r8.j(r9)
            r0.setGxTrack(r3)
            goto Ld
        Lb0:
            com.huawei.featureuserprofile.route.hwkmlmodel.Point r3 = r8.l(r9)
            r0.setPoint(r3)
            goto Ld
        Lb9:
            java.lang.String r3 = r8.readContent(r9, r6)
            r0.setLapName(r3)
            goto Ld
        Lc2:
            java.lang.String r3 = r8.readTextAndChildText(r9, r7)
            r0.setDescription(r3)
            goto Ld
        Lcb:
            com.huawei.featureuserprofile.route.hwkmlmodel.TimeSpan r3 = r8.m(r9)
            r0.setTimeSpan(r3)
            goto Ld
        Ld4:
            java.lang.String r1 = defpackage.btt.e
            r9.require(r4, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.btt.h(org.xmlpull.v1.XmlPullParser):com.huawei.featureuserprofile.route.hwkmlmodel.Placemark");
    }

    private LineString f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, e, "MultiGeometry");
        LineString lineString = new LineString();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals("LineString")) {
                    lineString = i(xmlPullParser);
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "MultiGeometry");
        return lineString;
    }

    private GxTrack j(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "Track");
        GxTrack gxTrack = new GxTrack();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                name.hashCode();
                if (name.equals(RemoteMessageConst.Notification.WHEN)) {
                    gxTrack.addWhenList(super.readTime(xmlPullParser, RemoteMessageConst.Notification.WHEN));
                } else if (name.equals("coord")) {
                    gxTrack.addGxCoordinates(readText(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Track");
        return gxTrack;
    }

    private LineString i(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, e, "LineString");
        LineString lineString = new LineString();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("coordinates")) {
                    lineString.setCoordinates(readText(xmlPullParser));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "LineString");
        return lineString;
    }

    private TimeSpan m(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        xmlPullParser.require(2, e, "TimeSpan");
        TimeSpan timeSpan = new TimeSpan();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("begin")) {
                    timeSpan.setBegin(super.readTime(xmlPullParser, "begin"));
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "TimeSpan");
        return timeSpan;
    }

    private Point l(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, e, "Point");
        Point.Builder builder = new Point.Builder();
        String str = "";
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("coordinates")) {
                    str = readText(xmlPullParser);
                } else {
                    nextTag(xmlPullParser);
                }
            }
        }
        xmlPullParser.require(3, e, "Point");
        String[] split = str.split(",");
        if (split.length <= 1 || (split.length == 2 && (TextUtils.isEmpty(split[0]) || TextUtils.isEmpty(split[1])))) {
            LogUtil.h("KmlFileParser", "longitude or latitude of point is missing");
            return builder.build();
        }
        try {
            builder.setLon(Double.parseDouble(split[0]));
        } catch (NumberFormatException unused) {
            LogUtil.b("KmlFileParser", "Lon error.");
        }
        try {
            builder.setLat(Double.parseDouble(split[1]));
        } catch (NumberFormatException unused2) {
            LogUtil.b("KmlFileParser", "Lat error.");
        }
        if (split.length > 2) {
            try {
                builder.setEle(Double.parseDouble(split[2]));
            } catch (NumberFormatException unused3) {
                LogUtil.b("KmlFileParser", "Ele error.");
            }
        }
        return builder.build();
    }
}
