package com.huawei.ui.main.stories.route.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amap.api.location.AMapLocation;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.huawei.featureuserprofile.route.navigationparser.NavigationFileParser;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong;
import com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback;
import com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback;
import com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwcloudmodel.model.unite.RouteExtendData;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MarkPoint;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.route.GetRouteDetailReq;
import com.huawei.route.GetSingleRouteDetailRsp;
import com.huawei.route.Point;
import com.huawei.route.RouteMarkerPoint;
import com.huawei.route.RouteOutputForApp;
import com.huawei.route.RouteTrackPoint;
import com.huawei.route.RouteType;
import com.huawei.route.TrackInfoModel;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity;
import com.huawei.ui.main.stories.route.view.MapContainer;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.hag;
import defpackage.hax;
import defpackage.hjd;
import defpackage.hla;
import defpackage.hle;
import defpackage.hlg;
import defpackage.ixx;
import defpackage.knz;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import defpackage.now;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class MyRouteDetailActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private ImageButton f10445a;
    private HealthTextView aa;
    private RouteData ab;
    private RelativeLayout ac;
    private HealthButton ae;
    private LinearLayout af;
    private HealthTextView ag;
    private HealthScrollView ah;
    private RouteTransferDataHolder ai;
    private HealthTextView ak;
    private HealthTextView al;
    private View am;
    private HealthTextView an;
    private CustomTextAlertDialog ao;
    private HealthTextView aq;
    private HealthTextView ar;
    private HealthTextView as;
    private CustomTextAlertDialog b;
    private boolean d;
    private HwHealthLineChart e;
    private CustomTitleBar f;
    private View g;
    private List<IHwHealthLineDataSet> i;
    private String k;
    private int l;
    private String m;
    private HiMapHolder n;
    private String o;
    private LinearLayout q;
    private MapContainer s;
    private LinearLayout t;
    private boolean u;
    private double w;
    private double x;
    private HealthTextView y;
    private final int aj = Color.rgb(66, 150, 248);
    private int r = 0;
    private Context j = null;
    private final List<Point> ad = new ArrayList();
    private InterfaceHiMap p = null;
    private final b c = new b(this);
    private final List<hjd> z = new ArrayList();
    private final List<MarkPoint> v = new ArrayList();
    private final hax h = new hax();

    public static /* synthetic */ boolean dRf_(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_route_detail);
        this.j = this;
        this.ai = new RouteTransferDataHolder(this);
        t();
        dRe_(getIntent());
        i();
        ReleaseLogUtil.b("Track_MyTrackDetailActivity", "onCreate end");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.ac.setVisibility(0);
        HiMapHolder hiMapHolder = this.n;
        if (hiMapHolder != null) {
            hiMapHolder.b();
        }
        dRe_(intent);
    }

    private void dRe_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ruw
            @Override // java.lang.Runnable
            public final void run() {
                MyRouteDetailActivity.this.dRi_(intent);
            }
        });
    }

    public /* synthetic */ void dRi_(Intent intent) {
        this.ad.clear();
        this.z.clear();
        this.v.clear();
        dRg_(intent);
        RouteData routeData = this.ab;
        if (routeData == null || koq.b(routeData.getRoutePoints())) {
            LogUtil.a("Track_MyTrackDetailActivity", "mRouteData is error");
            finish();
        } else {
            p();
            u();
            a();
        }
    }

    private void p() {
        this.m = this.ab.getRouteName();
        this.ad.addAll(this.ab.getRoutePoints());
        Message obtain = Message.obtain();
        obtain.what = 11;
        this.c.sendMessage(obtain);
    }

    private void u() {
        this.ai.c(this.m, this.ab);
        m();
        s();
        n();
        this.x = this.ai.g().getMaxAltitude();
        this.w = this.ai.g().getMinAltitude();
        this.ai.j();
        if (koq.b(this.z) || this.ai.h() == null || this.m == null) {
            Message obtain = Message.obtain();
            obtain.what = 8;
            this.c.sendMessage(obtain);
        } else {
            Message obtain2 = Message.obtain();
            obtain2.what = 7;
            this.c.sendMessage(obtain2);
            Message obtain3 = Message.obtain();
            obtain3.what = 13;
            this.c.sendMessage(obtain3);
        }
    }

    private void m() {
        List<RouteTrackPoint> points = this.ai.g().getPoints();
        if (koq.b(points)) {
            ReleaseLogUtil.b("Track_MyTrackDetailActivity", "initMapType points == null ");
        } else {
            RouteTrackPoint routeTrackPoint = points.get(0);
            this.r = gwg.e(this.j, routeTrackPoint.getLatitude(), routeTrackPoint.getLongitude());
        }
    }

    private void s() {
        List<RouteTrackPoint> points = this.ai.g().getPoints();
        if (koq.b(points)) {
            ReleaseLogUtil.b("Track_MyTrackDetailActivity", "initTrackPoint points == null ");
            return;
        }
        int size = points.size();
        c(size);
        ReleaseLogUtil.b("Track_MyTrackDetailActivity", "initTrackPoint points size ", Integer.valueOf(size));
        ArrayList arrayList = new ArrayList(size);
        for (RouteTrackPoint routeTrackPoint : points) {
            arrayList.add(new hjd(routeTrackPoint.getLatitude(), routeTrackPoint.getLongitude()));
        }
        this.z.addAll(gwe.c(arrayList, AMapLocation.COORD_TYPE_WGS84, this.r));
    }

    private void n() {
        List<RouteMarkerPoint> markerPoints = this.ai.g().getMarkerPoints();
        if (koq.b(markerPoints)) {
            ReleaseLogUtil.b("Track_MyTrackDetailActivity", "markerPoints == null ");
            return;
        }
        ReleaseLogUtil.b("Track_MyTrackDetailActivity", "initMarkPoint markPoint size = ", Integer.valueOf(markerPoints.size()));
        for (RouteMarkerPoint routeMarkerPoint : markerPoints) {
            hjd c = gwe.c(new hjd(routeMarkerPoint.getLatitude(), routeMarkerPoint.getLongitude()), AMapLocation.COORD_TYPE_WGS84, this.r);
            MarkPoint markPoint = new MarkPoint();
            markPoint.e(routeMarkerPoint.getIndexOfTrackPoint());
            markPoint.c(routeMarkerPoint.getType());
            markPoint.d(routeMarkerPoint.getColor());
            markPoint.c(c.d);
            markPoint.a(c.b);
            markPoint.b(1);
            this.v.add(markPoint);
        }
    }

    private void c(int i) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        hashMap.put("ROUTE_DETAIL_POINT_SIZE", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.MY_ROUTE_DETAIL_ACTIVITY_EVENT.value(), hashMap, 0);
    }

    private void dRg_(Intent intent) {
        if (intent != null) {
            this.o = intent.getStringExtra("fromFlag");
            Uri zs_ = AppRouterUtils.zs_(intent);
            if (TextUtils.isEmpty(this.o) && zs_ != null) {
                this.o = zs_.getQueryParameter("fromFlag");
            }
            if ("route_flag".equals(this.o)) {
                this.ab = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getRouteData();
                Message obtain = Message.obtain();
                obtain.what = 6;
                this.c.sendMessage(obtain);
            } else if ("file_flag".equals(this.o)) {
                String stringExtra = intent.getStringExtra("filePath");
                Message obtain2 = Message.obtain();
                obtain2.what = 6;
                if (c(stringExtra)) {
                    b(stringExtra);
                    obtain2.obj = true;
                } else {
                    LogUtil.a("Track_MyTrackDetailActivity", "obtainIntent file is error");
                    obtain2.obj = false;
                }
                this.c.sendMessage(obtain2);
            } else if ("cloud_flag".equals(this.o)) {
                Message obtain3 = Message.obtain();
                obtain3.what = 6;
                this.c.sendMessage(obtain3);
                c(dRd_(intent, zs_));
            } else {
                LogUtil.a("Track_MyTrackDetailActivity", "obtainIntent = ", this.o);
            }
            String stringExtra2 = intent.getStringExtra("from");
            LogUtil.a("Track_MyTrackDetailActivity", "from: ", stringExtra2);
            if (!TextUtils.isEmpty(stringExtra2) && stringExtra2.startsWith("com.tencent.mm")) {
                this.l = 1;
            }
        }
    }

    private boolean c(String str) {
        if (str == null) {
            LogUtil.h("Track_MyTrackDetailActivity", "isMatchGpsFile filePath is null");
            return false;
        }
        if (str.contains(NavigationFileParser.GPX)) {
            this.k = NavigationFileParser.GPX;
            return true;
        }
        if (str.contains(NavigationFileParser.KML)) {
            this.k = NavigationFileParser.KML;
            return true;
        }
        if (str.contains(NavigationFileParser.TCX)) {
            this.k = NavigationFileParser.TCX;
            return true;
        }
        this.k = "unknown";
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity.b(java.lang.String):void");
    }

    private void c(long j) {
        if (j == 0) {
            LogUtil.b("Track_MyTrackDetailActivity", "routeId is incorrect.");
            finish();
            return;
        }
        if (!CommonUtil.aa(this)) {
            nsn.a((Context) this, MyRouteDetailActivity.class.getName(), getString(R$string.IDS_my_track_detail), false);
            finish();
            return;
        }
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.e());
        GetRouteDetailReq getRouteDetailReq = new GetRouteDetailReq();
        getRouteDetailReq.setType(1);
        getRouteDetailReq.setVersion(j);
        GetSingleRouteDetailRsp getSingleRouteDetailRsp = (GetSingleRouteDetailRsp) lqi.d().d(getRouteDetailReq.getSingleRouteUrl(), healthDataCloudFactory.getHeaders(), lql.b(healthDataCloudFactory.getBody(getRouteDetailReq)), GetSingleRouteDetailRsp.class);
        if (getSingleRouteDetailRsp != null && getSingleRouteDetailRsp.getResultCode().intValue() == 0 && getSingleRouteDetailRsp.getRouteData() != null) {
            this.ab = getSingleRouteDetailRsp.getRouteData();
        } else {
            LogUtil.h("Track_MyTrackDetailActivity", "query single route error.");
        }
    }

    private long dRd_(Intent intent, Uri uri) {
        long longExtra = intent.getLongExtra("routeId", 0L);
        if (longExtra != 0 || uri == null) {
            return longExtra;
        }
        try {
            return Long.parseLong(uri.getQueryParameter("routeId"));
        } catch (NumberFormatException unused) {
            LogUtil.h("Track_MyTrackDetailActivity", "there is no routeId in query params");
            return 0L;
        }
    }

    private void t() {
        this.f = (CustomTitleBar) findViewById(R.id.track_detail_title_bar);
        this.ac = (RelativeLayout) findViewById(R.id.route_loading);
        this.ah = (HealthScrollView) findViewById(R.id.scroll_view);
        this.s = (MapContainer) findViewById(R.id.map_container);
        this.n = (HiMapHolder) findViewById(R.id.track_map);
        this.y = (HealthTextView) findViewById(R.id.my_track_detail_name);
        this.ak = (HealthTextView) findViewById(R.id.track_distance);
        this.al = (HealthTextView) findViewById(R.id.track_distance_title);
        this.an = (HealthTextView) findViewById(R.id.track_time);
        this.t = (LinearLayout) findViewById(R.id.lin_climb);
        this.as = (HealthTextView) findViewById(R.id.tv_elevation_gain);
        this.aq = (HealthTextView) findViewById(R.id.tv_total_descent);
        this.ag = (HealthTextView) findViewById(R.id.route_lowest_altitude);
        this.aa = (HealthTextView) findViewById(R.id.route_highest_altitude);
        this.e = (HwHealthLineChart) findViewById(R.id.altitude_chart);
        this.ae = (HealthButton) findViewById(R.id.save_route);
        this.af = (LinearLayout) findViewById(R.id.my_award_send_to_device_layout);
        this.ac.setVisibility(0);
        this.f.setTitleText(getString(R$string.IDS_my_track_detail));
        this.f.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        this.f.setRightButtonClickable(true);
        this.f.setRightButtonVisibility(8);
        this.g = findViewById(R.id.lin_track_distance_tip);
        this.am = findViewById(R.id.lin_total_time_tip);
        this.s.setScrollView(this.ah);
        this.q = (LinearLayout) findViewById(R.id.lin_root_view_altitude);
        this.ar = (HealthTextView) findViewById(R.id.tv_altitude_unit);
        ((LinearLayout) findViewById(R.id.root_distance)).setOnTouchListener(new View.OnTouchListener() { // from class: rus
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return MyRouteDetailActivity.dRf_(view, motionEvent);
            }
        });
        ImageButton imageButton = (ImageButton) findViewById(R.id.track_btn_show_map_type_satellite);
        this.f10445a = imageButton;
        imageButton.setVisibility(8);
        if (LanguageUtil.bc(this)) {
            this.f.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430036_res_0x7f0b0a94), nsf.h(R$string.accessibility_share));
        } else {
            this.f.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R$string.accessibility_share));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        int i = this.r;
        if (i == 1) {
            k();
            this.f10445a.setVisibility(0);
        } else {
            if (i == 2) {
                g();
                return;
            }
            if (i == 3) {
                h();
            } else if (i == -1) {
                j();
            } else {
                LogUtil.h("Track_MyTrackDetailActivity", "map type not match");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Message obtain = Message.obtain();
        obtain.what = 9;
        this.c.sendMessage(obtain);
        f();
        Message obtain2 = Message.obtain();
        obtain2.what = 10;
        this.c.sendMessage(obtain2);
    }

    private void f() {
        ArrayList arrayList = new ArrayList();
        List<knz> altitudeList = this.ai.g().getAltitudeList();
        for (int i = 0; i < altitudeList.size(); i++) {
            knz knzVar = altitudeList.get(i);
            double c = knzVar.c();
            if (UnitUtil.h()) {
                c = UnitUtil.e(knzVar.c(), 1);
            }
            if (i == 0) {
                arrayList.add(new HwHealthBaseEntry(0.0f, (float) c));
            } else {
                arrayList.add(new HwHealthBaseEntry((float) ((this.ab.getRouteDistance() * i) / altitudeList.size()), (float) c));
            }
        }
        LogUtil.a("Track_MyTrackDetailActivity", "initLineChart valueSize ", Integer.valueOf(arrayList.size()));
        HwHealthLineDataSet d = d(arrayList);
        ArrayList arrayList2 = new ArrayList();
        this.i = arrayList2;
        arrayList2.add(d);
        Message obtain = Message.obtain();
        obtain.what = 5;
        this.c.sendMessage(obtain);
    }

    private HwHealthLineDataSet d(List<HwHealthBaseEntry> list) {
        float f;
        double d;
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.j, list, "line brief", "line label", "line unit");
        hwHealthLineDataSet.e(1);
        hwHealthLineDataSet.setColor(ContextCompat.getColor(this.j, R.color._2131296501_res_0x7f0900f5));
        hwHealthLineDataSet.b(ContextCompat.getColor(this.j, R.color._2131296501_res_0x7f0900f5), ContextCompat.getColor(this.j, R.color._2131296666_res_0x7f09019a), true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        if (UnitUtil.h()) {
            f = (float) UnitUtil.e(this.x, 1);
            d = UnitUtil.e(this.w, 1);
        } else {
            f = (float) this.x;
            d = this.w;
        }
        float f2 = (float) d;
        float f3 = ((f - f2) + 20.0f) / 4.0f;
        float f4 = f2 - 10.0f;
        float f5 = f4 + f3;
        float f6 = f5 + f3;
        float f7 = f6 + f3;
        hwHealthLineDataSet.setForcedLabels(new float[]{f4, f5, f6, f7, f3 + f7});
        Description description = new Description();
        description.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        if (UnitUtil.h()) {
            description.setText(getString(R$string.IDS_route_distance_miles));
        } else {
            description.setText(getString(R$string.IDS_route_distance_km));
        }
        this.e.setDescription(description);
        HwHealthYAxis axisFirstParty = this.e.getAxisFirstParty();
        axisFirstParty.enableGridDashedLine(nrr.e(this.j, 2.0f), nrr.e(this.j, 1.0f), 0.0f);
        axisFirstParty.setAxisMinimum(hwHealthLineDataSet.getYMin() - 10.0f);
        axisFirstParty.setAxisMaximum(hwHealthLineDataSet.getYMax() + 10.0f);
        XAxis xAxis = this.e.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        xAxis.setDrawAxisLine(true);
        xAxis.setAvoidFirstLastClipping(true);
        b(xAxis);
        return hwHealthLineDataSet;
    }

    private void b(XAxis xAxis) {
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity.1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            public String getFormattedValue(float f, AxisBase axisBase) {
                DecimalFormat decimalFormat;
                if (f > 1000.0d) {
                    decimalFormat = new DecimalFormat("#.#");
                } else {
                    decimalFormat = new DecimalFormat("#.##");
                }
                if (UnitUtil.h()) {
                    return decimalFormat.format(UnitUtil.e(new BigDecimal(r7).divide(BigDecimal.valueOf(1000.0d), 2, 4).floatValue(), 3));
                }
                return decimalFormat.format(new BigDecimal(r7).divide(BigDecimal.valueOf(1000.0d), 2, 4).floatValue());
            }
        });
    }

    private void g() {
        this.n.a(this.j, (Fragment) null, new SyncMapCallback() { // from class: ruk
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public final void onMapReady(InterfaceHiMap interfaceHiMap) {
                MyRouteDetailActivity.this.e(interfaceHiMap);
            }
        });
    }

    public /* synthetic */ void e(InterfaceHiMap interfaceHiMap) {
        if (interfaceHiMap == null) {
            LogUtil.b("Track_MyTrackDetailActivity", "initGoogleMap onMapReady map is null");
            finish();
        } else {
            this.p = interfaceHiMap;
            k();
            this.f10445a.setVisibility(0);
        }
    }

    private void h() {
        this.n.b(this.j, new SyncMapCallback() { // from class: run
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public final void onMapReady(InterfaceHiMap interfaceHiMap) {
                MyRouteDetailActivity.this.d(interfaceHiMap);
            }
        });
    }

    public /* synthetic */ void d(InterfaceHiMap interfaceHiMap) {
        if (interfaceHiMap == null) {
            LogUtil.h("Track_MyTrackDetailActivity", "initHmsMap onMapReady hiMap is null");
            finish();
        } else {
            this.p = interfaceHiMap;
            k();
        }
    }

    private void j() {
        this.p = this.n.bhJ_(null, this.j);
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.h.d();
        for (hjd hjdVar : this.z) {
            this.h.b(new LatLong(hjdVar.b, hjdVar.d));
        }
        Message obtain = Message.obtain();
        obtain.what = 12;
        this.c.sendMessage(obtain);
    }

    private void k() {
        ReleaseLogUtil.b("Track_MyTrackDetailActivity", "markPoint size is", Integer.valueOf(this.v.size()));
        if (koq.b(this.v)) {
            b(this.z.get(0));
            c(this.z.get(r0.size() - 1));
        } else {
            if (this.ab.getRouteType() == RouteType.DEFAULT.routeType()) {
                a(this.z.get(0));
                e(this.z.get(r0.size() - 1));
            }
            this.p.addHiHealthMarkers(gwe.a(this.v, false));
        }
        if (nrt.a(this.j)) {
            this.p.setMapShowType(3);
        } else {
            this.p.setMapShowType(0);
        }
        this.p.setZoomControlsEnabled(false);
        hle hleVar = new hle();
        hleVar.e(this.z);
        hleVar.c(this.aj);
        hleVar.a(false);
        hleVar.b(hag.a(4.0f));
        this.p.drawLines(hleVar);
        LogUtil.a("Track_MyTrackDetailActivity", "init drawLines ", Long.valueOf(System.currentTimeMillis()));
        if (this.u) {
            LogUtil.a("Track_MyTrackDetailActivity", "onSecond load new Map");
            b(R.dimen._2131363098_res_0x7f0a051a, R.dimen._2131363098_res_0x7f0a051a, R.dimen._2131363088_res_0x7f0a0510, R.dimen._2131362950_res_0x7f0a0486);
        }
        this.p.setOnMapLoadedListener(new InterfaceMapLoadedCallback() { // from class: rut
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback
            public final void onMapLoaded() {
                MyRouteDetailActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        LogUtil.a("Track_MyTrackDetailActivity", "onMapLoaded");
        b(R.dimen._2131363098_res_0x7f0a051a, R.dimen._2131363098_res_0x7f0a051a, R.dimen._2131363088_res_0x7f0a0510, R.dimen._2131362950_res_0x7f0a0486);
        this.u = true;
    }

    private void e(hjd hjdVar) {
        MarkPoint markPoint = new MarkPoint();
        markPoint.c(hjdVar.d);
        markPoint.a(hjdVar.b);
        markPoint.c(MarkPoint.MarkType.END.type());
        this.v.add(markPoint);
    }

    private void a(hjd hjdVar) {
        MarkPoint markPoint = new MarkPoint();
        markPoint.c(hjdVar.d);
        markPoint.a(hjdVar.b);
        markPoint.c(MarkPoint.MarkType.START.type());
        this.v.add(0, markPoint);
    }

    private void b(int i, int i2, int i3, int i4) {
        this.p.setPreviewStatus(new hla(this.h.e().getLatLng(), this.h.a().getLatLng()), (int) getResources().getDimension(i), (int) getResources().getDimension(i2), (int) getResources().getDimension(i3), (int) getResources().getDimension(i4));
        this.ac.setVisibility(8);
    }

    private void b(hjd hjdVar) {
        hlg hlgVar = new hlg();
        hlgVar.d(hjdVar);
        hlgVar.bhP_(new Pair<>(Float.valueOf(0.5f), Float.valueOf(0.9f)));
        hlgVar.bhQ_(BitmapFactory.decodeResource(this.j.getResources(), R.drawable._2131430368_res_0x7f0b0be0));
        this.p.addMarker(hlgVar, null);
    }

    private void c(hjd hjdVar) {
        hlg hlgVar = new hlg();
        hlgVar.d(hjdVar);
        hlgVar.bhP_(new Pair<>(Float.valueOf(0.5f), Float.valueOf(0.9f)));
        hlgVar.bhQ_(BitmapFactory.decodeResource(this.j.getResources(), R.drawable._2131430366_res_0x7f0b0bde));
        this.p.addMarker(hlgVar, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SpannableString dRc_(double d) {
        String string;
        String quantityString;
        double d2 = d / 1000.0d;
        if (UnitUtil.h()) {
            string = getString(R$string.IDS_hwh_motiontrack_show_speed_pace_mi);
            double e = UnitUtil.e(d2, 3);
            quantityString = getResources().getQuantityString(R.plurals._2130903095_res_0x7f030037, (int) e, UnitUtil.e(e, 1, 2));
        } else {
            string = getString(R$string.IDS_hwh_motiontrack_show_speed_pace_km);
            quantityString = getResources().getQuantityString(R.plurals._2130903096_res_0x7f030038, (int) d2, UnitUtil.e(d2, 1, 2));
        }
        SpannableString spannableString = new SpannableString(quantityString);
        dRh_(spannableString, string);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dRh_(SpannableString spannableString, String str) {
        int indexOf;
        if (spannableString == null || TextUtils.isEmpty(str) || (indexOf = spannableString.toString().indexOf(str)) == -1) {
            return;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7)), indexOf, str.length() + indexOf, 33);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.e(), R.color._2131299241_res_0x7f090ba9)), indexOf, str.length() + indexOf, 33);
    }

    private void i() {
        r();
        w();
        this.g.setOnClickListener(new View.OnClickListener() { // from class: rui
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRj_(view);
            }
        });
        this.am.setOnClickListener(new View.OnClickListener() { // from class: rup
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRk_(view);
            }
        });
        this.f10445a.setOnClickListener(new View.OnClickListener() { // from class: ruj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRl_(view);
            }
        });
        this.f.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: rur
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRm_(view);
            }
        });
    }

    public /* synthetic */ void dRj_(View view) {
        a(getString(this.ab.getRouteType() == RouteType.EXP.routeType() ? R$string.IDS_my_track_detail_distance_exp_tip : R$string.IDS_my_track_detail_distance_tip));
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dRk_(View view) {
        a(getString(R$string.IDS_my_track_detail_totaltime_tip));
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dRl_(View view) {
        boolean z = !this.d;
        this.d = z;
        InterfaceHiMap interfaceHiMap = this.p;
        if (interfaceHiMap != null) {
            interfaceHiMap.showSatelLiteState(true, z, nrt.a(this.j) ? 3 : 0);
        }
        this.f10445a.setBackgroundResource(this.d ? R.drawable._2131431934_res_0x7f0b11fe : R.drawable._2131431933_res_0x7f0b11fd);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dRm_(View view) {
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (this.ab == null) {
            LogUtil.c("Track_MyTrackDetailActivity", "mRouteData=null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("Track_MyTrackDetailActivity", "to RouteExportActivity");
        TrackInfoModel.Builder routeType = new TrackInfoModel.Builder().routeName(this.m).sportType(this.ab.getSportType()).sportTotalTime(this.ab.getRouteTime()).sportTotalDistance(this.ab.getRouteDistance()).points(this.ad).routeType(this.ab.getRouteType());
        if (!TextUtils.isEmpty(this.ab.getExtendData())) {
            RouteExtendData routeExtendData = (RouteExtendData) new Gson().fromJson(this.ab.getExtendData(), RouteExtendData.class);
            routeType.cumulativeClimb(routeExtendData.getCumulativeClimb()).cumulativeDecrease(routeExtendData.getCumulativeDecrease()).routeType(routeExtendData.getRouteType());
        }
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setTrackInfoModel(routeType.build());
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setRouteData(this.ab);
        AppRouter.b("/HWUserProfileMgr/RouteExportActivity").e("IS_NEED_CHANGE_POINT", false).c(this);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void w() {
        nsy.cMn_((HealthButton) findViewById(R.id.my_award_send_to_device_btn), new View.OnClickListener() { // from class: rum
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRo_(view);
            }
        });
    }

    public /* synthetic */ void dRo_(View view) {
        if (nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            q();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void r() {
        this.f.setRightButtonOnClickListener(new View.OnClickListener() { // from class: ruv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRn_(view);
            }
        });
    }

    public /* synthetic */ void dRn_(View view) {
        ArrayList arrayList = new ArrayList();
        if (!EnvironmentInfo.k()) {
            arrayList.add(getString(R$string.IDS_track_detail_trans_device));
        }
        arrayList.add(getString(R$string.IDS_track_detail_edit));
        arrayList.add(getString(R$string.IDS_track_detail_delete_route));
        new PopViewList(this.j, this.f, arrayList).e(new PopViewList.PopViewClickListener() { // from class: rul
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                MyRouteDetailActivity.this.b(i);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void b(int i) {
        if (EnvironmentInfo.k()) {
            i++;
        }
        if (i == 0) {
            q();
        } else if (i == 1) {
            v();
        } else {
            if (i != 2) {
                return;
            }
            d(2);
        }
    }

    private void v() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setRouteData(this.ab);
        AppRouter.b("/HWUserProfileMgr/EditRouteActivity").e("is_add", false).c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        RouteTransferDataHolder routeTransferDataHolder = this.ai;
        if (routeTransferDataHolder == null || routeTransferDataHolder.g() == null || this.m == null) {
            nrh.d(BaseApplication.e(), getString(R$string.IDS_track_detail_parse_error));
        } else {
            this.ai.o();
        }
    }

    private void a(String str) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.j);
        builder.e(str).cyS_(getResources().getString(R$string.IDS_my_track_detail_i_go_it), new View.OnClickListener() { // from class: ruh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRp_(view);
            }
        }).a();
        CustomTextAlertDialog a2 = builder.a();
        this.b = a2;
        a2.findViewById(R.id.custom_dialog_title_layout).setPadding(0, 0, 0, 0);
        this.b.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.b.show();
    }

    public /* synthetic */ void dRp_(View view) {
        this.b.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        CustomTextAlertDialog customTextAlertDialog = this.b;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            return;
        }
        this.b.dismiss();
    }

    private void d(final int i) {
        String string;
        String string2;
        String string3;
        d();
        if (i == 1) {
            string = getString(R$string.IDS_track_detail_route_exists);
            string2 = getString(R$string.IDS_track_detail_rename_try);
            string3 = getResources().getString(R$string.IDS_my_track_detail_rename);
        } else if (i != 2) {
            string = null;
            string2 = null;
            string3 = null;
        } else {
            string = getString(R$string.IDS_my_route_confirm_delete);
            string2 = getString(R$string.IDS_route_confirm_delete_line);
            string3 = getString(R$string.hiad_calender_delete);
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.j);
        builder.b(string).e(string2).cyS_(getResources().getString(R$string.IDS_motiontrack_show_cancel), new View.OnClickListener() { // from class: ruo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRq_(view);
            }
        }).cyV_(string3, new View.OnClickListener() { // from class: ruq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyRouteDetailActivity.this.dRr_(i, view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.ao = a2;
        a2.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.ao.show();
    }

    public /* synthetic */ void dRq_(View view) {
        this.ao.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dRr_(int i, View view) {
        a(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i) {
        if (i == 1) {
            v();
        } else if (i == 2) {
            e();
        }
        CustomTextAlertDialog customTextAlertDialog = this.ao;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
        }
    }

    private void e() {
        if (!CommonUtil.aa(this)) {
            nrh.b(this, com.huawei.health.servicesui.R$string.IDS_hw_show_set_about_privacy_connectting_error);
        } else {
            if (this.ab == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(this.ab.getRouteVersion()));
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).deleteRoutes(arrayList, new RouteResultCallBack<CloudCommonReponse>() { // from class: com.huawei.ui.main.stories.route.activity.MyRouteDetailActivity.5
                @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                    if (cloudCommonReponse.getResultCode().intValue() == 0) {
                        LogUtil.a("Track_MyTrackDetailActivity", "delete success");
                        MyRouteDetailActivity myRouteDetailActivity = MyRouteDetailActivity.this;
                        myRouteDetailActivity.setResult(-1, myRouteDetailActivity.getIntent());
                        MyRouteDetailActivity.this.finish();
                        return;
                    }
                    nrh.e(MyRouteDetailActivity.this, R$string.IDS_watchface_delete_failed);
                    LogUtil.c("Track_MyTrackDetailActivity", "delete resp resultCode:", cloudCommonReponse.getResultCode(), ",resp resultDesc:", cloudCommonReponse.getResultDesc());
                }

                @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
                public void onFailure(Throwable th) {
                    nrh.e(MyRouteDetailActivity.this, R$string.IDS_watchface_delete_failed);
                    LogUtil.b("Track_MyTrackDetailActivity", "delete onFailure:", th.getMessage());
                }
            });
        }
    }

    private void a() {
        if ("cloud_flag".equals(this.o)) {
            LogUtil.a("Track_MyTrackDetailActivity", "auto routeToDevice start.");
            Message obtain = Message.obtain();
            obtain.what = 14;
            this.c.sendMessage(obtain);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        d();
        b bVar = this.c;
        if (bVar != null) {
            bVar.removeMessages(5);
            this.c.removeMessages(6);
            this.c.removeMessages(7);
            this.c.removeMessages(8);
            this.c.removeMessages(9);
            this.c.removeMessages(10);
            this.c.removeMessages(11);
            this.c.removeMessages(12);
            this.c.removeMessages(13);
            this.c.removeMessages(14);
        }
        this.n.b();
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).clearRouteData();
    }

    /* loaded from: classes7.dex */
    public static class b extends BaseHandler<MyRouteDetailActivity> {
        b(MyRouteDetailActivity myRouteDetailActivity) {
            super(myRouteDetailActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dRt_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MyRouteDetailActivity myRouteDetailActivity, Message message) {
            switch (message.what) {
                case 5:
                    myRouteDetailActivity.e.setData(new now(myRouteDetailActivity.i));
                    myRouteDetailActivity.e.refresh();
                    break;
                case 6:
                    dRs_(myRouteDetailActivity, message);
                    break;
                case 7:
                    a(myRouteDetailActivity);
                    break;
                case 8:
                    nrh.e(BaseApplication.e(), R$string.IDS_my_route_parse_error);
                    break;
                case 9:
                    b(myRouteDetailActivity);
                    i(myRouteDetailActivity);
                    break;
                case 10:
                    myRouteDetailActivity.ah.setVisibility(0);
                    myRouteDetailActivity.ac.setVisibility(8);
                    break;
                case 11:
                    j(myRouteDetailActivity);
                    break;
                case 12:
                    myRouteDetailActivity.l();
                    break;
                case 13:
                    e(myRouteDetailActivity);
                    break;
                case 14:
                    myRouteDetailActivity.q();
                    break;
            }
        }

        private void e(final MyRouteDetailActivity myRouteDetailActivity) {
            if (myRouteDetailActivity.r == 1) {
                myRouteDetailActivity.p = myRouteDetailActivity.n.bhK_(null, myRouteDetailActivity);
            }
            if (koq.b(myRouteDetailActivity.z)) {
                LogUtil.a("Track_MyTrackDetailActivity", "mPointList is empty");
                myRouteDetailActivity.finish();
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: ruu
                    @Override // java.lang.Runnable
                    public final void run() {
                        MyRouteDetailActivity.b.d(MyRouteDetailActivity.this);
                    }
                });
            }
        }

        public static /* synthetic */ void d(MyRouteDetailActivity myRouteDetailActivity) {
            myRouteDetailActivity.o();
            myRouteDetailActivity.c();
        }

        private void j(final MyRouteDetailActivity myRouteDetailActivity) {
            myRouteDetailActivity.ae.setOnClickListener(new View.OnClickListener() { // from class: rux
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyRouteDetailActivity.b.this.dRu_(myRouteDetailActivity, view);
                }
            });
        }

        public /* synthetic */ void dRu_(final MyRouteDetailActivity myRouteDetailActivity, View view) {
            if (LoginInit.getInstance(myRouteDetailActivity).isBrowseMode()) {
                LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: rvb
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        MyRouteDetailActivity.b.this.c(myRouteDetailActivity, i, obj);
                    }
                }, "");
            } else if (Utils.l()) {
                LogUtil.a("Track_MyTrackDetailActivity", "is oversea no cloud version");
                nrh.e(BaseApplication.e(), R$string.wallet_unenable_country);
            } else {
                c(myRouteDetailActivity);
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void c(MyRouteDetailActivity myRouteDetailActivity, int i, Object obj) {
            if (i == 0) {
                c(myRouteDetailActivity);
            } else {
                LogUtil.h("Track_MyTrackDetailActivity", "browsingToLogin not login errorCode is ", Integer.valueOf(i));
            }
        }

        private void c(MyRouteDetailActivity myRouteDetailActivity) {
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setRouteData(myRouteDetailActivity.ab);
            AppRouter.b("/HWUserProfileMgr/EditRouteActivity").e("is_add", true).c("from", myRouteDetailActivity.l).c(myRouteDetailActivity);
            myRouteDetailActivity.finish();
        }

        private void a(MyRouteDetailActivity myRouteDetailActivity) {
            RouteData routeData = myRouteDetailActivity.ab;
            myRouteDetailActivity.y.setText(routeData.getRouteName());
            myRouteDetailActivity.ak.setText(myRouteDetailActivity.dRc_(routeData.getRouteDistance()));
            if (routeData.getRouteType() == RouteType.EXP.routeType()) {
                myRouteDetailActivity.al.setText(R$string.IDS_hw_sport_waypoint_mileage);
            }
            if (routeData.getRouteTime() <= 0.0d) {
                myRouteDetailActivity.an.setText(R$string.IDS_motiontrack_detail_heartrate_default);
            } else {
                myRouteDetailActivity.an.setText(UnitUtil.d((int) routeData.getRouteTime()));
            }
        }

        private void b(MyRouteDetailActivity myRouteDetailActivity) {
            if (Math.abs(myRouteDetailActivity.x - myRouteDetailActivity.w) > Double.MIN_VALUE) {
                myRouteDetailActivity.q.setVisibility(0);
                myRouteDetailActivity.ar.setText(myRouteDetailActivity.getString(R$string.IDS_fitness_data_list_activity_meter_unit));
                double c = CommonUtil.c(myRouteDetailActivity.w, 1);
                double c2 = CommonUtil.c(myRouteDetailActivity.x, 1);
                if (UnitUtil.h()) {
                    c = CommonUtil.c(UnitUtil.e(myRouteDetailActivity.w, 1), 1);
                    c2 = CommonUtil.c(UnitUtil.e(myRouteDetailActivity.x, 1), 1);
                    myRouteDetailActivity.ar.setText(myRouteDetailActivity.getString(R$string.IDS_ft));
                }
                NumberFormat numberFormat = NumberFormat.getInstance();
                String format = numberFormat.format(c);
                String format2 = numberFormat.format(c2);
                myRouteDetailActivity.ag.setText(format);
                myRouteDetailActivity.aa.setText(format2);
                return;
            }
            myRouteDetailActivity.q.setVisibility(8);
        }

        private void i(MyRouteDetailActivity myRouteDetailActivity) {
            RouteOutputForApp h = myRouteDetailActivity.ai.h();
            if (h == null || !h.getIsShowAltitude()) {
                myRouteDetailActivity.t.setVisibility(8);
                return;
            }
            RouteData routeData = myRouteDetailActivity.ab;
            double totalAscent = h.getTotalAscent();
            double totalDescent = h.getTotalDescent();
            if (!TextUtils.isEmpty(routeData.getExtendData())) {
                RouteExtendData routeExtendData = (RouteExtendData) new Gson().fromJson(routeData.getExtendData(), RouteExtendData.class);
                if (Double.compare(routeExtendData.getCumulativeClimb(), 0.0d) > 0 || Double.compare(routeExtendData.getCumulativeDecrease(), 0.0d) > 0) {
                    totalAscent = routeExtendData.getCumulativeClimb();
                    totalDescent = routeExtendData.getCumulativeDecrease();
                }
            }
            if (Double.compare(totalAscent, 0.0d) > 0 || Double.compare(totalDescent, 0.0d) > 0) {
                myRouteDetailActivity.t.setVisibility(0);
                a(myRouteDetailActivity, myRouteDetailActivity.as, totalAscent);
                a(myRouteDetailActivity, myRouteDetailActivity.aq, totalDescent);
                return;
            }
            myRouteDetailActivity.t.setVisibility(8);
        }

        private void a(MyRouteDetailActivity myRouteDetailActivity, HealthTextView healthTextView, double d) {
            String string;
            String quantityString;
            if (UnitUtil.h()) {
                double e = UnitUtil.e(d, 1);
                string = myRouteDetailActivity.getResources().getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(e));
                quantityString = myRouteDetailActivity.getResources().getQuantityString(R.plurals._2130903306_res_0x7f03010a, (int) e, UnitUtil.e(e, 1, 2));
            } else {
                string = myRouteDetailActivity.getResources().getString(R.string._2130841568_res_0x7f020fe0);
                quantityString = myRouteDetailActivity.getResources().getQuantityString(R.plurals._2130903307_res_0x7f03010b, (int) d, UnitUtil.e(d, 1, 2));
            }
            SpannableString spannableString = new SpannableString(quantityString);
            myRouteDetailActivity.dRh_(spannableString, string);
            healthTextView.setText(spannableString);
        }

        private void dRs_(MyRouteDetailActivity myRouteDetailActivity, Message message) {
            if ("route_flag".equals(myRouteDetailActivity.o) || "cloud_flag".equals(myRouteDetailActivity.o)) {
                myRouteDetailActivity.ae.setVisibility(8);
                myRouteDetailActivity.f.setRightButtonVisibility(0);
                myRouteDetailActivity.f.setRightSoftkeyVisibility(0);
                if (EnvironmentInfo.k()) {
                    return;
                }
                myRouteDetailActivity.af.setVisibility(0);
                return;
            }
            myRouteDetailActivity.f.setRightButtonVisibility(8);
            myRouteDetailActivity.f.setRightSoftkeyVisibility(8);
            myRouteDetailActivity.af.setVisibility(8);
            if (((Boolean) message.obj).booleanValue()) {
                myRouteDetailActivity.ae.setVisibility(0);
            } else {
                nrh.e(BaseApplication.e(), R$string.IDS_my_route_parse_error);
            }
            if (myRouteDetailActivity.ah.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) myRouteDetailActivity.ah.getLayoutParams()).bottomMargin = myRouteDetailActivity.getResources().getDimensionPixelOffset(R.dimen._2131363125_res_0x7f0a0535);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
