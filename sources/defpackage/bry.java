package defpackage;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Gpx;
import com.huawei.featureuserprofile.route.hwgpxmodel.Track;
import com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment;
import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Obj2XmlSerializer;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.health.userprofilemgr.api.RouteApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.route.LbsInfo;
import com.huawei.route.Point;
import com.huawei.route.RouteType;
import com.huawei.route.TrackInfo;
import com.huawei.wearengine.sensor.DataResult;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.xmlpull.v1.XmlPullParserException;

@ApiDefine(uri = RouteApi.class)
@Singleton
/* loaded from: classes3.dex */
public class bry implements RouteApi {

    /* renamed from: a, reason: collision with root package name */
    private NavigationFileParser f489a;

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public int startParser(InputStream inputStream, String str) throws IOException, XmlPullParserException {
        NavigationFileParser navigationFileParser = new NavigationFileParser();
        this.f489a = navigationFileParser;
        try {
            return navigationFileParser.navigationParser(inputStream, str);
        } catch (ParseException unused) {
            LogUtil.a("RouteImpl", "startParser parseException");
            return 0;
        }
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public List<Point> getPoints() {
        NavigationFileParser navigationFileParser = this.f489a;
        if (navigationFileParser != null) {
            return navigationFileParser.getPoints();
        }
        return new ArrayList(0);
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public String getSportType() {
        NavigationFileParser navigationFileParser = this.f489a;
        return navigationFileParser != null ? navigationFileParser.getSportType() : "";
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public int getRouteType() {
        NavigationFileParser navigationFileParser = this.f489a;
        if (navigationFileParser != null) {
            return navigationFileParser.getRouteType();
        }
        return RouteType.DEFAULT.routeType();
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public double getSportTotalDistance() {
        NavigationFileParser navigationFileParser = this.f489a;
        if (navigationFileParser != null) {
            return navigationFileParser.getSportTotalDistance();
        }
        return 0.0d;
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public double getSportTotalTime() {
        NavigationFileParser navigationFileParser = this.f489a;
        if (navigationFileParser != null) {
            return navigationFileParser.getSportTotalTime();
        }
        return 0.0d;
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public double getCumulativeClimb() {
        NavigationFileParser navigationFileParser = this.f489a;
        if (navigationFileParser != null) {
            return navigationFileParser.getCumulativeClimb();
        }
        return 0.0d;
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public double getCumulativeDecrease() {
        NavigationFileParser navigationFileParser = this.f489a;
        if (navigationFileParser != null) {
            return navigationFileParser.getCumulativeDecrease();
        }
        return 0.0d;
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public File generateGpxFile(HiHealthData hiHealthData, String str, String str2, Map<String, Object> map, TrackInfo trackInfo) throws IOException, XmlPullParserException {
        LbsInfo lbsInfo = trackInfo.getLbsInfo();
        if (TextUtils.isEmpty(str2) || lbsInfo == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        Date date = new Date();
        Gpx gpx = new Gpx();
        date.setTime(hiHealthData.getStartTime());
        gpx.addMetadata("time", simpleDateFormat.format(date));
        TrackSegment trackSegment = new TrackSegment();
        if (koq.b(trackInfo.getHeartInfoList())) {
            e(trackInfo, simpleDateFormat, date, trackSegment);
        } else {
            c(trackInfo, simpleDateFormat, date, trackSegment);
        }
        Track track = new Track();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                track.addExtension(entry.getKey(), entry.getValue());
            }
        }
        track.setType(str);
        track.addTrackSegment(trackSegment);
        gpx.addTrack(track);
        Obj2XmlSerializer obj2XmlSerializer = new Obj2XmlSerializer();
        File file = new File(str2);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try {
                obj2XmlSerializer.toXml(gpx, bufferedOutputStream);
                bufferedOutputStream.close();
                fileOutputStream.close();
                return file;
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x009c, code lost:
    
        if (r5 >= r4.length) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x009e, code lost:
    
        r19.setTime(r4[r5]);
        r20.addTrackPoint(r2[r5], r3[r5], r18.format(r19));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bc, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b1, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.a("RouteImpl", "index isOutOfBounds");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void c(com.huawei.route.TrackInfo r17, java.text.SimpleDateFormat r18, java.util.Date r19, com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment r20) {
        /*
            r0 = r19
            java.util.List r1 = r17.getHeartInfoList()
            com.huawei.route.LbsInfo r2 = r17.getLbsInfo()
            double[] r2 = r2.getLats()
            com.huawei.route.LbsInfo r3 = r17.getLbsInfo()
            double[] r3 = r3.getLons()
            com.huawei.route.LbsInfo r4 = r17.getLbsInfo()
            long[] r4 = r4.getTimes()
            com.huawei.route.LbsInfo r5 = r17.getLbsInfo()
            boolean r5 = r5.isValid()
            if (r5 != 0) goto L29
            return
        L29:
            r5 = 0
            r6 = r5
        L2b:
            com.huawei.route.LbsInfo r7 = r17.getLbsInfo()
            int r7 = r7.getRealSize()
            if (r5 >= r7) goto Lc0
        L35:
            int r7 = r1.size()
            if (r6 >= r7) goto L9b
            int r7 = r5 + 1
            com.huawei.route.LbsInfo r8 = r17.getLbsInfo()
            int r8 = r8.getRealSize()
            if (r7 >= r8) goto L4a
            r8 = r4[r7]
            goto L4c
        L4a:
            r8 = r4[r5]
        L4c:
            java.lang.Object r10 = r1.get(r6)
            com.huawei.route.HeartInfo r10 = (com.huawei.route.HeartInfo) r10
            long r10 = r10.getTime()
            r12 = r4[r5]
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L5f
            int r6 = r6 + 1
            goto L35
        L5f:
            java.lang.Object r10 = r1.get(r6)
            com.huawei.route.HeartInfo r10 = (com.huawei.route.HeartInfo) r10
            long r10 = r10.getTime()
            int r10 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r10 > 0) goto L9b
            java.lang.Object r10 = r1.get(r6)
            com.huawei.route.HeartInfo r10 = (com.huawei.route.HeartInfo) r10
            long r10 = r10.getTime()
            int r8 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r8 >= 0) goto L9b
            r8 = r4[r5]
            r0.setTime(r8)
            r11 = r2[r5]
            r13 = r3[r5]
            java.lang.String r15 = r18.format(r19)
            java.lang.Object r5 = r1.get(r6)
            com.huawei.route.HeartInfo r5 = (com.huawei.route.HeartInfo) r5
            int r16 = r5.getHeartRate()
            r10 = r20
            r10.addTrackPoint(r11, r13, r15, r16)
            int r6 = r6 + 1
            r5 = r7
            goto L2b
        L9b:
            int r7 = r4.length
            if (r5 >= r7) goto Lb1
            r7 = r4[r5]
            r0.setTime(r7)
            r10 = r2[r5]
            r12 = r3[r5]
            java.lang.String r14 = r18.format(r19)
            r9 = r20
            r9.addTrackPoint(r10, r12, r14)
            goto Lbc
        Lb1:
            java.lang.String r7 = "index isOutOfBounds"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r8 = "RouteImpl"
            com.huawei.hwlogsmodel.LogUtil.a(r8, r7)
        Lbc:
            int r5 = r5 + 1
            goto L2b
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bry.c(com.huawei.route.TrackInfo, java.text.SimpleDateFormat, java.util.Date, com.huawei.featureuserprofile.route.hwgpxmodel.TrackSegment):void");
    }

    private static void e(TrackInfo trackInfo, SimpleDateFormat simpleDateFormat, Date date, TrackSegment trackSegment) {
        double[] lats = trackInfo.getLbsInfo().getLats();
        double[] lons = trackInfo.getLbsInfo().getLons();
        long[] times = trackInfo.getLbsInfo().getTimes();
        if (trackInfo.getLbsInfo().isValid()) {
            for (int i = 0; i < trackInfo.getLbsInfo().getRealSize(); i++) {
                if (i < times.length) {
                    date.setTime(times[i]);
                    trackSegment.addTrackPoint(lats[i], lons[i], simpleDateFormat.format(date));
                } else {
                    LogUtil.a("RouteImpl", "index isOutOfBounds");
                }
            }
            return;
        }
        LogUtil.a("RouteImpl", "lbsInfo is invalid");
    }

    @Override // com.huawei.health.userprofilemgr.api.RouteApi
    public File generateGpxFile(HiHealthData hiHealthData, String str, String str2, Map<String, Object> map, LbsInfo lbsInfo) throws IOException, XmlPullParserException {
        TrackInfo trackInfo = new TrackInfo();
        trackInfo.setLbsInfo(lbsInfo);
        return generateGpxFile(hiHealthData, str, str2, map, trackInfo);
    }
}
