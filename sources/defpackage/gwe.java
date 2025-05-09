package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.core.app.ActivityCompat;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.huawei.health.R;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hihealth.util.HiInChinaUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.common.util.VersionUtil;
import com.huawei.hms.maps.internal.HmsUtil;
import com.huawei.hms.maps.model.CoordinateLatLng;
import com.huawei.hms.maps.model.CoordinateType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class gwe {

    /* renamed from: a, reason: collision with root package name */
    private static final int f12968a;
    private static final int e;
    private static final hjd f;
    private static final double[] j;
    public static final SparseIntArray d = new SparseIntArray();
    private static final SparseIntArray i = new SparseIntArray();
    private static final SparseIntArray h = new SparseIntArray();
    private static final SparseIntArray b = new SparseIntArray(361);
    private static final SparseIntArray c = new SparseIntArray(361);

    public static boolean a(double d2) {
        return d2 < -180.0d || d2 > 180.0d;
    }

    public static boolean d(double d2) {
        return d2 < -90.0d || d2 > 90.0d;
    }

    static {
        double[] dArr = {90.0d, -80.0d, 0.0d};
        j = dArr;
        f = new hjd(dArr[0], dArr[1]);
        int i2 = 0;
        for (int i3 = 0; i3 <= 185; i3++) {
            int i4 = i3 + 70;
            float f2 = i3 / 185.0f;
            int round = Math.round((26.0f * f2) + 199.0f);
            int round2 = Math.round(109.0f - (f2 * 39.0f));
            b.put(Color.rgb(i4, round, round2), i2);
            c.put(i2, Color.rgb(i4, round, round2));
            i2++;
        }
        for (int i5 = 1; i5 <= 175; i5++) {
            float f3 = i5 / 175.0f;
            int round3 = Math.round(255.0f - (17.0f * f3));
            int i6 = 225 - i5;
            int round4 = Math.round(70.0f - (f3 * 20.0f));
            b.put(Color.rgb(round3, i6, round4), i2);
            c.put(i2, Color.rgb(round3, i6, round4));
            i2++;
        }
        if (BaseApplication.getContext() != null) {
            e = nsn.c(BaseApplication.getContext(), 23.0f);
            f12968a = nsn.c(BaseApplication.getContext(), 26.0f);
        } else {
            e = 90;
            f12968a = 105;
        }
        SparseIntArray sparseIntArray = d;
        sparseIntArray.put(282, R.drawable._2131428717_res_0x7f0b056d);
        sparseIntArray.put(258, R.drawable._2131428718_res_0x7f0b056e);
        sparseIntArray.put(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE, R.drawable._2131428714_res_0x7f0b056a);
        sparseIntArray.put(260, R.drawable._2131428713_res_0x7f0b0569);
        sparseIntArray.put(259, R.drawable._2131428712_res_0x7f0b0568);
        sparseIntArray.put(257, R.drawable._2131428723_res_0x7f0b0573);
        sparseIntArray.put(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM, R.drawable._2131428722_res_0x7f0b0572);
        sparseIntArray.put(217, R.drawable._2131428721_res_0x7f0b0571);
        sparseIntArray.put(218, R.drawable._2131428719_res_0x7f0b056f);
        sparseIntArray.put(219, R.drawable._2131428720_res_0x7f0b0570);
        sparseIntArray.put(222, R.drawable._2131428715_res_0x7f0b056b);
        e();
        g();
        b();
    }

    private static void b() {
        SparseIntArray sparseIntArray = h;
        sparseIntArray.put(MarkPoint.PointColor.ORANGE.color(), R.color._2131299288_res_0x7f090bd8);
        sparseIntArray.put(MarkPoint.PointColor.RED.color(), R.color._2131299289_res_0x7f090bd9);
        sparseIntArray.put(MarkPoint.PointColor.BLUE.color(), R.color._2131299285_res_0x7f090bd5);
        sparseIntArray.put(MarkPoint.PointColor.YELLOW.color(), R.color._2131299290_res_0x7f090bda);
        sparseIntArray.put(MarkPoint.PointColor.GRAY.color(), R.color._2131299286_res_0x7f090bd6);
        sparseIntArray.put(MarkPoint.PointColor.GREEN.color(), R.color._2131299287_res_0x7f090bd7);
        sparseIntArray.put(MarkPoint.PointColor.AUTO.color(), R.color._2131299288_res_0x7f090bd8);
    }

    private static void g() {
        SparseIntArray sparseIntArray = i;
        sparseIntArray.put(MarkPoint.MarkType.COMMON.type(), R.drawable._2131431891_res_0x7f0b11d3);
        sparseIntArray.put(MarkPoint.MarkType.START.type(), R.drawable._2131431899_res_0x7f0b11db);
        sparseIntArray.put(MarkPoint.MarkType.CAMPSITE.type(), R.drawable._2131431889_res_0x7f0b11d1);
        sparseIntArray.put(MarkPoint.MarkType.LEFT.type(), R.drawable._2131431893_res_0x7f0b11d5);
        sparseIntArray.put(MarkPoint.MarkType.RIGHT.type(), R.drawable._2131431896_res_0x7f0b11d8);
        sparseIntArray.put(MarkPoint.MarkType.STRAIGHT.type(), R.drawable._2131431900_res_0x7f0b11dc);
        sparseIntArray.put(MarkPoint.MarkType.DANGER.type(), R.drawable._2131431890_res_0x7f0b11d2);
        sparseIntArray.put(MarkPoint.MarkType.BEAST.type(), R.drawable._2131431885_res_0x7f0b11cd);
        sparseIntArray.put(MarkPoint.MarkType.HEALTHCARE.type(), R.drawable._2131431894_res_0x7f0b11d6);
        sparseIntArray.put(MarkPoint.MarkType.WATER.type(), R.drawable._2131431898_res_0x7f0b11da);
        sparseIntArray.put(MarkPoint.MarkType.PROVIDE.type(), R.drawable._2131431901_res_0x7f0b11dd);
        sparseIntArray.put(MarkPoint.MarkType.BRIDGE.type(), R.drawable._2131431888_res_0x7f0b11d0);
        sparseIntArray.put(MarkPoint.MarkType.RISK_AVERSION.type(), R.drawable._2131431897_res_0x7f0b11d9);
        sparseIntArray.put(MarkPoint.MarkType.TOP_MOUNTAIN.type(), R.drawable._2131431895_res_0x7f0b11d7);
        sparseIntArray.put(MarkPoint.MarkType.HOUSE.type(), R.drawable._2131431892_res_0x7f0b11d4);
        sparseIntArray.put(MarkPoint.MarkType.END.type(), R.drawable._2131431899_res_0x7f0b11db);
        sparseIntArray.put(MarkPoint.MarkType.AUTO_MARK.type(), -1);
    }

    private static void e() {
        SparseIntArray sparseIntArray = d;
        sparseIntArray.put(MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(169, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(172, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(261, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(178, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(270, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(194, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(197, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(198, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(199, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(200, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(201, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(202, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(a.z, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(a.A, R.drawable._2131428716_res_0x7f0b056c);
        sparseIntArray.put(a.M, R.drawable._2131428716_res_0x7f0b056c);
    }

    public static Marker c(AMap aMap, hjd hjdVar, int i2) {
        if (aMap == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker gaodeMap is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker latLng is null.");
            return null;
        }
        MarkerOptions zIndex = new MarkerOptions().position(d(hjdVar)).draggable(false).anchor(0.5f, 0.9f).zIndex(10.0f);
        c(i2, zIndex);
        return aMap.addMarker(zIndex);
    }

    private static void c(int i2, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), d.get(i2, R.drawable._2131428718_res_0x7f0b056e))));
    }

    public static com.huawei.hms.maps.model.Marker c(HuaweiMap huaweiMap, hjd hjdVar, int i2) {
        if (huaweiMap == null || hjdVar == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "addGoogleStartMarker googleMap or hiHealthLatLng is null.");
            return null;
        }
        com.huawei.hms.maps.model.Marker addMarker = huaweiMap.addMarker(new com.huawei.hms.maps.model.MarkerOptions().position(a(hjdVar)).draggable(false));
        if (BaseApplication.getContext() != null) {
            addMarker.setIcon(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), d.get(i2, R.drawable._2131428718_res_0x7f0b056e))));
            addMarker.setMarkerAnchor(0.5f, 0.9f);
        }
        return addMarker;
    }

    public static Marker d(AMap aMap, hjd hjdVar, String str) {
        BitmapDescriptor fromPath;
        if (aMap == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker gaodeMap is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker latLng is null.");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            fromPath = BitmapDescriptorFactory.fromResource(R.drawable._2131428710_res_0x7f0b0566);
        } else {
            fromPath = BitmapDescriptorFactory.fromPath(str);
        }
        return aMap.addMarker(new MarkerOptions().position(d(hjdVar)).draggable(false).anchor(0.5f, 0.5f).zIndex(10.0f).icon(fromPath));
    }

    public static com.huawei.hms.maps.model.Marker e(HuaweiMap huaweiMap, hjd hjdVar, String str) {
        if (huaweiMap == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "addHmsEndMarker huaweiMap is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "addHmsEndMarker hiHealthLatLng is null.");
            return null;
        }
        com.huawei.hms.maps.model.Marker addMarker = huaweiMap.addMarker(new com.huawei.hms.maps.model.MarkerOptions().position(a(hjdVar)).draggable(false).anchor(0.5f, 0.5f));
        if (BaseApplication.getContext() != null) {
            addMarker.setIcon(b(str));
        }
        return addMarker;
    }

    public static com.huawei.hms.maps.model.BitmapDescriptor b(String str) {
        if (TextUtils.isEmpty(str)) {
            return f();
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        com.huawei.hms.maps.model.BitmapDescriptor fromBitmap = decodeFile != null ? com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(decodeFile) : null;
        return fromBitmap == null ? f() : fromBitmap;
    }

    private static com.huawei.hms.maps.model.BitmapDescriptor f() {
        return com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), R.drawable._2131428710_res_0x7f0b0566));
    }

    public static Marker c(AMap aMap, hjd hjdVar, String str) {
        BitmapDescriptor fromPath;
        if (hjdVar == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker latLng is null.");
            return null;
        }
        if (aMap == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker gaodeMap is null.");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            fromPath = BitmapDescriptorFactory.fromResource(R.drawable._2131428709_res_0x7f0b0565);
        } else {
            fromPath = BitmapDescriptorFactory.fromPath(str);
        }
        return aMap.addMarker(new MarkerOptions().position(d(hjdVar)).draggable(false).anchor(0.5f, 0.5f).zIndex(10.0f).icon(fromPath));
    }

    public static com.huawei.hms.maps.model.Marker a(HuaweiMap huaweiMap, hjd hjdVar, String str) {
        if (huaweiMap == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "addMarker addHmsEndMarkerBg is null.");
            return null;
        }
        if (hjdVar == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "addMarker addHmsEndMarkerBg is null.");
            return null;
        }
        com.huawei.hms.maps.model.Marker addMarker = huaweiMap.addMarker(new com.huawei.hms.maps.model.MarkerOptions().position(a(hjdVar)).draggable(false).anchor(0.5f, 0.5f));
        if (BaseApplication.getContext() != null) {
            addMarker.setIcon(e(str));
        }
        return addMarker;
    }

    private static com.huawei.hms.maps.model.BitmapDescriptor e(String str) {
        if (TextUtils.isEmpty(str)) {
            return h();
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        com.huawei.hms.maps.model.BitmapDescriptor fromBitmap = decodeFile != null ? com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(decodeFile) : null;
        return fromBitmap == null ? h() : fromBitmap;
    }

    private static com.huawei.hms.maps.model.BitmapDescriptor h() {
        return com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), R.drawable._2131428709_res_0x7f0b0565));
    }

    public static MarkerOptions aUC_(Resources resources, LatLng latLng, String str) {
        if (latLng == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addMarker latLng is null.");
            return null;
        }
        MarkerOptions zIndex = new MarkerOptions().position(latLng).draggable(false).anchor(0.5f, 1.0f).zIndex(14.0f);
        TextPaint aUF_ = aUF_(resources, str.length());
        aUF_.getTextBounds(str, 0, str.length(), new Rect());
        Bitmap aUE_ = aUE_(resources);
        new Canvas(aUE_).drawText(str, aUE_.getWidth() / 2.0f, (((aUE_.getHeight() - aUF_.getFontMetrics().top) - aUF_.getFontMetrics().bottom) / 2.0f) - aUG_(resources, 2.0f), aUF_);
        zIndex.icon(BitmapDescriptorFactory.fromBitmap(aUE_));
        return zIndex;
    }

    public static com.huawei.hms.maps.model.Marker aUB_(Resources resources, com.huawei.hms.maps.model.LatLng latLng, String str, HuaweiMap huaweiMap) {
        if (latLng == null || str == null || huaweiMap == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "addKmMarker latLng is null.");
            return null;
        }
        com.huawei.hms.maps.model.MarkerOptions zIndex = new com.huawei.hms.maps.model.MarkerOptions().position(latLng).draggable(false).zIndex(14.0f);
        TextPaint aUF_ = aUF_(resources, str.length());
        aUF_.getTextBounds(str, 0, str.length(), new Rect());
        Bitmap aUE_ = aUE_(resources);
        new Canvas(aUE_).drawText(str, aUE_.getWidth() / 2.0f, (((aUE_.getHeight() - aUF_.getFontMetrics().top) - aUF_.getFontMetrics().bottom) / 2.0f) - aUG_(resources, 2.0f), aUF_);
        zIndex.icon(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(aUE_));
        com.huawei.hms.maps.model.Marker addMarker = huaweiMap.addMarker(zIndex);
        addMarker.setIcon(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(aUE_));
        addMarker.setMarkerAnchor(0.5f, 0.9f);
        return addMarker;
    }

    public static TextPaint aUF_(Resources resources, int i2) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        if (resources != null) {
            textPaint.setColor(resources.getColor(R.color._2131296937_res_0x7f0902a9));
            if (i2 == 1) {
                textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen._2131363644_res_0x7f0a073c));
            } else if (i2 == 2) {
                textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen._2131363644_res_0x7f0a073c));
            } else {
                textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen._2131363675_res_0x7f0a075b));
            }
        }
        return textPaint;
    }

    public static Bitmap aUE_(Resources resources) {
        if (resources != null) {
            Drawable drawable = resources.getDrawable(R.drawable._2131428711_res_0x7f0b0567);
            int i2 = e;
            int i3 = f12968a;
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            if (drawable == null) {
                return createBitmap;
            }
            drawable.setBounds(0, 0, i2, i3);
            Canvas canvas = new Canvas(createBitmap);
            drawable.draw(canvas);
            canvas.drawBitmap(createBitmap, 0.0f, 0.0f, new Paint());
            return createBitmap;
        }
        return Bitmap.createBitmap(e, f12968a, Bitmap.Config.ARGB_8888);
    }

    public static ArrayList<hjg> a(List<MarkPoint> list, boolean z) {
        hjg hjgVar;
        ArrayList<hjg> arrayList = new ArrayList<>();
        if (koq.b(list)) {
            LogUtil.h("Track_DrawMapUtils", "no markerPoints");
            return arrayList;
        }
        Context context = BaseApplication.getContext();
        for (int i2 = 0; i2 < list.size(); i2++) {
            MarkPoint markPoint = list.get(i2);
            if (markPoint == null) {
                ReleaseLogUtil.c("Track_DrawMapUtils", "markPoint is null");
            } else {
                hjd hjdVar = new hjd(markPoint.d(), markPoint.a());
                float f2 = 0.9f;
                if (i2 == 0) {
                    hjgVar = new hjg(hjdVar, R.drawable._2131428715_res_0x7f0b056b, -1);
                } else if (markPoint.c() == 1) {
                    hjgVar = new hjg(hjdVar, R.drawable._2131431887_res_0x7f0b11cf, R.drawable._2131431886_res_0x7f0b11ce, i.get(markPoint.j(), R.drawable._2131431891_res_0x7f0b11d3), context.getColor(h.get(markPoint.e(), R.color._2131296927_res_0x7f09029f)));
                } else if (markPoint.c() == 0 && i2 == list.size() - 1) {
                    hjgVar = new hjg(hjdVar, z ? R.drawable._2131431238_res_0x7f0b0f46 : R.drawable._2131428707_res_0x7f0b0563, -1);
                } else {
                    hjgVar = new hjg(hjdVar, R.drawable._2131431884_res_0x7f0b11cc, context.getColor(h.get(MarkPoint.PointColor.AUTO.color(), R.color._2131296927_res_0x7f09029f)));
                    f2 = 0.5f;
                }
                hjgVar.a(0.5f);
                hjgVar.e(f2);
                arrayList.add(hjgVar);
            }
        }
        return arrayList;
    }

    public static ArrayList<MarkerOptions> e(List<hjg> list) {
        ArrayList<MarkerOptions> arrayList = new ArrayList<>();
        if (koq.b(list)) {
            return arrayList;
        }
        for (hjg hjgVar : list) {
            if (hjgVar == null || hjgVar.bgH_() == null) {
                ReleaseLogUtil.c("Track_DrawMapUtils", "hiHealthMarker or drawable is null in buildGaodeMarker");
            } else {
                Bitmap cHF_ = nrf.cHF_(hjgVar.bgH_());
                arrayList.add(new MarkerOptions().position(e(hjgVar.b())).draggable(false).anchor(hjgVar.a(), hjgVar.c()).zIndex(10.0f).icon(BitmapDescriptorFactory.fromBitmap(cHF_)));
            }
        }
        return arrayList;
    }

    public static ArrayList<com.huawei.hms.maps.model.MarkerOptions> c(List<hjg> list) {
        ArrayList<com.huawei.hms.maps.model.MarkerOptions> arrayList = new ArrayList<>();
        if (koq.b(list)) {
            return arrayList;
        }
        for (hjg hjgVar : list) {
            if (hjgVar == null || hjgVar.bgH_() == null) {
                ReleaseLogUtil.c("Track_DrawMapUtils", "hiHealthMarker or drawable is null in buildHmsMarker");
            } else {
                arrayList.add(new com.huawei.hms.maps.model.MarkerOptions().position(g(hjgVar.b())).draggable(false).anchorMarker(hjgVar.a(), hjgVar.c()).zIndex(10.0f).icon(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(nrf.cHF_(hjgVar.bgH_()))));
            }
        }
        return arrayList;
    }

    public static ArrayList<com.google.android.gms.maps.model.MarkerOptions> d(List<hjg> list) {
        ArrayList<com.google.android.gms.maps.model.MarkerOptions> arrayList = new ArrayList<>();
        if (koq.b(list)) {
            return arrayList;
        }
        for (hjg hjgVar : list) {
            if (hjgVar == null || hjgVar.bgH_() == null) {
                ReleaseLogUtil.c("Track_DrawMapUtils", "hiHealthMarker or drawable is null in buildGoogleMarker");
            } else {
                arrayList.add(new com.google.android.gms.maps.model.MarkerOptions().position(b(hjgVar.b())).draggable(false).anchor(hjgVar.a(), hjgVar.c()).zIndex(10.0f).icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(nrf.cHF_(hjgVar.bgH_()))));
            }
        }
        return arrayList;
    }

    public static Bitmap aUI_(List<hjg> list) {
        if (koq.b(list)) {
            LogUtil.a("Track_DrawMapUtils", "hiHealthMarkerList is empty in getLastManualMarker");
            return null;
        }
        return nrf.cHF_(list.get(list.size() - 1).bgH_());
    }

    public static LatLng e(hjd hjdVar) {
        if (hjdVar == null) {
            return new LatLng(90.0d, 180.0d);
        }
        return new LatLng(hjdVar.b, hjdVar.d);
    }

    public static com.huawei.hms.maps.model.LatLng g(hjd hjdVar) {
        if (hjdVar == null) {
            return new com.huawei.hms.maps.model.LatLng(90.0d, 180.0d);
        }
        return new com.huawei.hms.maps.model.LatLng(hjdVar.b, hjdVar.d);
    }

    public static com.google.android.gms.maps.model.LatLng b(hjd hjdVar) {
        if (hjdVar == null) {
            return new com.google.android.gms.maps.model.LatLng(90.0d, 180.0d);
        }
        return new com.google.android.gms.maps.model.LatLng(hjdVar.b, hjdVar.d);
    }

    private static float aUG_(Resources resources, float f2) {
        return (f2 * resources.getDisplayMetrics().density) + 0.5f;
    }

    public static boolean c(hjd hjdVar) {
        return b(f, hjdVar);
    }

    public static boolean d(double d2, double d3) {
        return c(new hjd(d2, d3));
    }

    public static boolean b(hjd hjdVar, hjd hjdVar2) {
        return hjdVar != null && hjdVar2 != null && Math.abs(hjdVar.b - hjdVar2.b) < 1.0E-6d && Math.abs(hjdVar.d - hjdVar2.d) < 1.0E-6d;
    }

    public static List<Integer> e(float[] fArr, Integer[] numArr) {
        int i2;
        int i3;
        float f2;
        if (fArr == null || numArr == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "index pace or color type is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i4 = 0; i4 < fArr.length; i4++) {
            if (i4 > 0) {
                int i5 = i4 - 1;
                f2 = fArr[i4] - fArr[i5];
                i2 = d(numArr[i5].intValue());
                i3 = d(numArr[i4].intValue());
            } else {
                i2 = 0;
                i3 = 0;
                f2 = 0.0f;
            }
            int i6 = 0;
            for (float f3 = ((float) i4) != 0.0f ? fArr[i4 - 1] : 0.0f; f3 < fArr[i4]; f3 += 1.0f) {
                if (i2 == i3 || Math.abs(f2) <= 1.0E-6d) {
                    arrayList.add(numArr[i4]);
                } else {
                    SparseIntArray sparseIntArray = c;
                    arrayList.add(Integer.valueOf(sparseIntArray.get(((int) (((i3 - i2) / f2) * i6)) + i2, sparseIntArray.get(0))));
                    i6++;
                }
            }
        }
        return arrayList;
    }

    private static int d(int i2) {
        return b.get(i2, 0);
    }

    public static int c(float f2, int i2) {
        int i3;
        int i4;
        if (f2 <= 1.0E-6d) {
            return gwh.p;
        }
        float f3 = 1000.0f / f2;
        if (i2 != 257 && i2 != 282) {
            if (i2 == 258 || i2 == 280) {
                i4 = 12;
                i3 = 6;
            } else if (i2 != 260) {
                i4 = 30;
                i3 = 10;
            }
            return b(i4, i3, f3 * 3.6f);
        }
        i3 = 4;
        i4 = 7;
        return b(i4, i3, f3 * 3.6f);
    }

    public static int b(float f2, float f3, float f4) {
        if (f4 >= f2) {
            return gwh.p;
        }
        if (f4 <= f3) {
            return gwh.t;
        }
        return c.get((int) (((f4 - f3) / (f2 - f3)) * 360.0f));
    }

    public static List<Integer> b(float f2, float f3, int i2) {
        ArrayList arrayList;
        int c2 = c(f3, i2);
        int c3 = c(f2, i2);
        SparseIntArray sparseIntArray = b;
        int i3 = sparseIntArray.get(c2);
        int i4 = sparseIntArray.get(c3);
        if (i3 >= i4) {
            arrayList = new ArrayList((i3 - i4) + 1);
            while (i4 <= i3) {
                arrayList.add(Integer.valueOf(c.get(i4)));
                i4++;
            }
        } else {
            arrayList = new ArrayList((i4 - i3) + 1);
            while (i4 >= i3) {
                arrayList.add(Integer.valueOf(c.get(i4)));
                i4--;
            }
        }
        return arrayList;
    }

    public static double[] aUD_(Context context, Location location) {
        if (context == null || location == null) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "convertLocation context or location is null, please check");
            return new double[]{0.0d, 0.0d};
        }
        CoordinateConverter coordinateConverter = new CoordinateConverter(context);
        coordinateConverter.from(CoordinateConverter.CoordType.GPS);
        coordinateConverter.coord(d(new hjd(location.getLatitude(), location.getLongitude())));
        LatLng convert = coordinateConverter.convert();
        double[] dArr = new double[2];
        if (convert != null) {
            dArr[0] = convert.latitude;
            dArr[1] = convert.longitude;
        } else {
            dArr[0] = location.getLatitude();
            dArr[1] = location.getLongitude();
        }
        return dArr;
    }

    public static double[] c(Context context, double[] dArr) {
        if (context == null || dArr == null || dArr.length != 2) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "convertLocation context or location is null, please check");
            return new double[]{0.0d, 0.0d};
        }
        CoordinateConverter coordinateConverter = new CoordinateConverter(context);
        coordinateConverter.from(CoordinateConverter.CoordType.GPS);
        coordinateConverter.coord(d(new hjd(dArr[0], dArr[1])));
        LatLng convert = coordinateConverter.convert();
        double[] dArr2 = new double[2];
        if (convert != null) {
            dArr2[0] = convert.latitude;
            dArr2[1] = convert.longitude;
        } else {
            dArr2[0] = dArr[0];
            dArr2[1] = dArr[1];
        }
        return dArr2;
    }

    public static LatLng d(hjd hjdVar) {
        if (hjdVar == null) {
            return null;
        }
        return new LatLng(hjdVar.b, hjdVar.d);
    }

    public static hjd b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new hjd(latLng.latitude, latLng.longitude);
    }

    public static com.huawei.hms.maps.model.LatLng a(hjd hjdVar) {
        if (hjdVar == null) {
            return null;
        }
        return new com.huawei.hms.maps.model.LatLng(hjdVar.b, hjdVar.d);
    }

    public static hjd c(com.huawei.hms.maps.model.LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new hjd(latLng.latitude, latLng.longitude);
    }

    public static Location aUH_(Context context) {
        Location location = null;
        if (context == null || !a(context)) {
            return null;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(1);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        List<String> allProviders = locationManager.getAllProviders();
        if (allProviders == null) {
            return null;
        }
        if (allProviders.contains(bestProvider)) {
            try {
                location = locationManager.getLastKnownLocation(bestProvider);
            } catch (NullPointerException unused) {
                ReleaseLogUtil.c("Track_DrawMapUtils", "getLastKnowLocation NullPointerException");
            }
        }
        if (location != null || !allProviders.contains(HAWebViewInterface.NETWORK)) {
            return location;
        }
        try {
            return locationManager.getLastKnownLocation(HAWebViewInterface.NETWORK);
        } catch (NullPointerException unused2) {
            ReleaseLogUtil.c("Track_DrawMapUtils", "getLastKnowLocation NullPointerException");
            return location;
        }
    }

    private static boolean a(Context context) {
        if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 && ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return true;
        }
        ReleaseLogUtil.e("Track_DrawMapUtils", "checkSelfPermission is false");
        return false;
    }

    public static float e(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null) {
            return 0.0f;
        }
        return AMapUtils.calculateLineDistance(d(hjdVar), d(hjdVar2));
    }

    public static boolean d(Context context) {
        if (context == null) {
            ReleaseLogUtil.d("Track_DrawMapUtils", "supportHms context is null");
            return false;
        }
        if (!CommonUtil.bh()) {
            ReleaseLogUtil.e("Track_DrawMapUtils", "Is not Emui");
            return false;
        }
        int isHmsMapAvailable = VersionUtil.isHmsMapAvailable(context);
        if (isHmsMapAvailable == 2) {
            ReleaseLogUtil.e("Track_DrawMapUtils", "Rom version is not support hms");
            return false;
        }
        if (isHmsMapAvailable == 1 && gwg.j() && Utils.o()) {
            ReleaseLogUtil.e("Track_DrawMapUtils", "ForbiddenCountry with Hms below 3.0 version");
            return false;
        }
        if (isHmsMapAvailable != 0 && !CommonUtil.b(context)) {
            HmsUtil.setRepeatFlag(false);
        }
        return true;
    }

    public static void a() {
        HmsUtil.setRepeatFlag(true);
    }

    public static boolean b(Context context) {
        if (context != null) {
            return VersionUtil.isHmsMapAvailable(context) == 0;
        }
        ReleaseLogUtil.d("Track_DrawMapUtils", "isHmsAvailable context is null");
        return false;
    }

    public static boolean d() {
        LogUtil.a("Track_DrawMapUtils", "isDrawDotted GaoDe ", Integer.valueOf(c()));
        return c() == 0;
    }

    public static int c() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "pause_trackline_type");
        if (TextUtils.isEmpty(b2)) {
            return 0;
        }
        try {
            return Integer.parseInt(b2);
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_DrawMapUtils", "acquirePauseTracklineType NumberFormatException", LogAnonymous.b((Throwable) e2));
            return 0;
        }
    }

    public static hjd c(GpsPoint gpsPoint) {
        if (gpsPoint == null) {
            return new hjd(90.0d, 180.0d);
        }
        return new hjd(gpsPoint.getLatitude(), gpsPoint.getLongitude());
    }

    public static List<hjd> c(hjd hjdVar, int i2) {
        ArrayList arrayList = new ArrayList(2);
        if (hjdVar == null) {
            LogUtil.b("Track_DrawMapUtils", "center is null in getOverlayLatLng");
            return arrayList;
        }
        LatLngBounds bounds = new GroundOverlayOptions().position(d(hjdVar), i2).getBounds();
        if (bounds != null) {
            arrayList.add(b(bounds.southwest));
            arrayList.add(b(bounds.northeast));
        }
        return arrayList;
    }

    private static List<hjd> b(List<hjd> list, String str) {
        if (koq.b(list)) {
            LogUtil.h("Track_DrawMapUtils", "hiLatLngs is empty");
            return list;
        }
        CoordinateType d2 = d(str);
        if (d2 == null) {
            LogUtil.h("Track_DrawMapUtils", "coordinate is invalid");
            return list;
        }
        int size = list.size();
        CoordinateLatLng[] coordinateLatLngArr = new CoordinateLatLng[size];
        for (int i2 = 0; i2 < size; i2++) {
            coordinateLatLngArr[i2] = new CoordinateLatLng(d2, a(list.get(i2)));
        }
        CoordinateLatLng[] rectifyCoordinate = com.huawei.hms.maps.common.util.CoordinateConverter.rectifyCoordinate(coordinateLatLngArr);
        if (rectifyCoordinate == null || rectifyCoordinate.length == 0) {
            LogUtil.h("Track_DrawMapUtils", "latLngArray is empty");
            return list;
        }
        ArrayList arrayList = new ArrayList(rectifyCoordinate.length);
        for (CoordinateLatLng coordinateLatLng : rectifyCoordinate) {
            arrayList.add(c(coordinateLatLng.getLocation()));
        }
        return arrayList;
    }

    private static hjd b(hjd hjdVar, String str) {
        if (hjdVar == null) {
            LogUtil.h("Track_DrawMapUtils", "hiLatLngs is null");
            return hjdVar;
        }
        CoordinateType d2 = d(str);
        if (d2 == null) {
            LogUtil.h("Track_DrawMapUtils", "coordinate is invalid");
            return hjdVar;
        }
        CoordinateLatLng[] rectifyCoordinate = com.huawei.hms.maps.common.util.CoordinateConverter.rectifyCoordinate(new CoordinateLatLng[]{new CoordinateLatLng(d2, a(hjdVar))});
        if (rectifyCoordinate == null || rectifyCoordinate.length == 0) {
            LogUtil.h("Track_DrawMapUtils", "latLngArray is empty");
            return hjdVar;
        }
        return c(rectifyCoordinate[0].getLocation());
    }

    private static CoordinateType d(String str) {
        if (TextUtils.equals(str, AMapLocation.COORD_TYPE_WGS84)) {
            return new CoordinateType(CoordinateType.CoordinateTypeEnum.WGS84);
        }
        if (TextUtils.equals(str, AMapLocation.COORD_TYPE_GCJ02)) {
            return new CoordinateType(CoordinateType.CoordinateTypeEnum.GCJ02);
        }
        return null;
    }

    public static hjd c(hjd hjdVar, String str, int i2) {
        if (TextUtils.equals(str, AMapLocation.COORD_TYPE_WGS84)) {
            return e(hjdVar, i2);
        }
        return TextUtils.equals(str, AMapLocation.COORD_TYPE_GCJ02) ? d(hjdVar, i2) : hjdVar;
    }

    public static List<hjd> c(List<hjd> list, String str, int i2) {
        if (TextUtils.equals(str, AMapLocation.COORD_TYPE_WGS84)) {
            return e(list, i2);
        }
        return TextUtils.equals(str, AMapLocation.COORD_TYPE_GCJ02) ? b(list, i2) : list;
    }

    private static hjd e(hjd hjdVar, int i2) {
        if (hjdVar == null) {
            return hjdVar;
        }
        int b2 = ktl.b(hjdVar.b, hjdVar.d);
        if (b2 == 1 || (b2 == 3 && i2 == 1)) {
            return gwf.c(hjdVar);
        }
        return (b2 == 3 && i2 == 3) ? b(hjdVar, AMapLocation.COORD_TYPE_WGS84) : hjdVar;
    }

    private static List<hjd> e(List<hjd> list, int i2) {
        if (koq.b(list)) {
            return list;
        }
        hjd hjdVar = list.get(0);
        int b2 = ktl.b(hjdVar.b, hjdVar.d);
        if (b2 == 1 || (b2 == 3 && i2 == 1)) {
            return gwf.b(list);
        }
        return (b2 == 3 && i2 == 3) ? b(list, AMapLocation.COORD_TYPE_WGS84) : list;
    }

    private static hjd d(hjd hjdVar, int i2) {
        return (hjdVar != null && ktl.b(hjdVar.b, hjdVar.d) == 3 && i2 == 3) ? b(hjdVar, AMapLocation.COORD_TYPE_GCJ02) : hjdVar;
    }

    private static List<hjd> b(List<hjd> list, int i2) {
        if (koq.b(list)) {
            return list;
        }
        hjd hjdVar = list.get(0);
        return (ktl.b(hjdVar.b, hjdVar.d) == 3 && i2 == 3) ? b(list, AMapLocation.COORD_TYPE_GCJ02) : list;
    }

    public static MotionData d(MotionData motionData, int i2) {
        if (motionData == null) {
            return motionData;
        }
        Map<Long, double[]> d2 = d(motionData.getLbsDataMap(), i2);
        ArrayList<MarkPoint> a2 = a(motionData.requestMarkPointList(), i2);
        try {
            MotionData m531clone = motionData.m531clone();
            m531clone.setLbsDataMap(d2);
            m531clone.saveMarkPointList(a2);
            return m531clone;
        } catch (CloneNotSupportedException unused) {
            LogUtil.b("Track_DrawMapUtils", "convertCoordinateByHK error");
            return motionData;
        }
    }

    public static ArrayList<MarkPoint> a(ArrayList<MarkPoint> arrayList, int i2) {
        if (koq.b(arrayList)) {
            return arrayList;
        }
        MarkPoint markPoint = arrayList.get(0);
        if (HiInChinaUtil.c(markPoint.d(), markPoint.a()) != 3) {
            LogUtil.a("Track_DrawMapUtils", "convertLbsForMap not in hk or mc");
            return arrayList;
        }
        ArrayList<MarkPoint> arrayList2 = new ArrayList<>(arrayList.size());
        Iterator<MarkPoint> it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                MarkPoint clone = it.next().clone();
                hjd e2 = e(new hjd(clone.d(), clone.a()), i2);
                clone.a(e2.b);
                clone.c(e2.d);
                arrayList2.add(clone);
            } catch (CloneNotSupportedException unused) {
                LogUtil.b("Track_DrawMapUtils", "convertMarkPointForMap");
                return arrayList;
            }
        }
        return arrayList2;
    }

    public static Map<Long, double[]> d(Map<Long, double[]> map, int i2) {
        if (map == null) {
            return map;
        }
        double[] dArr = map.get(0L);
        if (dArr == null || dArr.length < 2) {
            LogUtil.b("Track_DrawMapUtils", "convertLbsForMap motionPathPoint is invalid");
            return map;
        }
        if (HiInChinaUtil.c(dArr[0], dArr[1]) != 3) {
            LogUtil.a("Track_DrawMapUtils", "convertLbsForMap not in hk or mc");
            return map;
        }
        if (i2 == 1) {
            return a(map);
        }
        return i2 == 3 ? d(map) : map;
    }

    private static Map<Long, double[]> d(Map<Long, double[]> map) {
        ArrayList arrayList = new ArrayList(map.size());
        CoordinateType d2 = d(AMapLocation.COORD_TYPE_WGS84);
        CoordinateLatLng[] coordinateLatLngArr = new CoordinateLatLng[map.size()];
        int i2 = 0;
        for (Map.Entry<Long, double[]> entry : map.entrySet()) {
            double[] value = entry.getValue();
            if (value.length < 2) {
                LogUtil.b("Track_DrawMapUtils", "convertLbsForHms pointMap is invalid");
                return map;
            }
            arrayList.add(entry.getKey());
            coordinateLatLngArr[i2] = new CoordinateLatLng(d2, new com.huawei.hms.maps.model.LatLng(value[0], value[1]));
            i2++;
        }
        CoordinateLatLng[] rectifyCoordinate = com.huawei.hms.maps.common.util.CoordinateConverter.rectifyCoordinate(coordinateLatLngArr);
        if (rectifyCoordinate == null || rectifyCoordinate.length == 0 || arrayList.size() != rectifyCoordinate.length) {
            LogUtil.h("Track_DrawMapUtils", "latLngArray is error");
            return map;
        }
        TreeMap treeMap = new TreeMap();
        for (int i3 = 0; i3 < rectifyCoordinate.length; i3++) {
            Long l = (Long) arrayList.get(i3);
            double[] dArr = map.get(l);
            if (dArr != null && dArr.length >= 4) {
                com.huawei.hms.maps.model.LatLng location = rectifyCoordinate[i3].getLocation();
                double[] copyOf = Arrays.copyOf(dArr, dArr.length);
                copyOf[0] = location.latitude;
                copyOf[1] = location.longitude;
                treeMap.put(l, copyOf);
            }
        }
        return treeMap;
    }

    private static Map<Long, double[]> a(Map<Long, double[]> map) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<Long, double[]> entry : map.entrySet()) {
            double[] value = entry.getValue();
            if (value != null && value.length >= 4) {
                hjd hjdVar = new hjd(value[0], value[1]);
                if (!c(hjdVar)) {
                    hjdVar = gwf.c(hjdVar);
                }
                double[] copyOf = Arrays.copyOf(value, value.length);
                copyOf[0] = hjdVar.b;
                copyOf[1] = hjdVar.d;
                treeMap.put(entry.getKey(), copyOf);
            }
        }
        return treeMap;
    }
}
