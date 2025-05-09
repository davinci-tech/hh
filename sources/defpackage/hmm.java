package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.motiontrack.api.ILocationChangeListener;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.runningroute.data.RouteSiteDistanceSortDialog;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.runningroute.view.IRouteDetail;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapMarkerClickCallback;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback;
import com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback;
import com.huawei.healthcloud.plugintrack.ui.view.RouteHintMvpView;
import com.huawei.healthcloud.plugintrack.ui.view.RouteHintView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.emq;
import defpackage.hmm;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class hmm {

    /* renamed from: a, reason: collision with root package name */
    private static final int f13253a;
    private static final int c;
    private static final Pair<Float, Float> e;
    private List<enf> ab;
    private SharedPreferences ac;
    private HealthBubbleLayout ae;
    private CustomTitleBar af;
    private ImageButton ah;
    private hlh ai;
    private WeakReference<ClockingRankActivity> d;
    private hjd i;
    private HealthCardView j;
    private HiMapHolder k;
    private hjd l;
    private enc n;
    private boolean s;
    private View u;
    private String v;
    private RouteHintMvpView w;
    private String x;
    private HealthCardView y;
    private RouteSiteDistanceSortDialog z;
    private InterfaceHiMap t = null;
    private List<String> g = new ArrayList();
    private float f = 14.5f;
    private Map<Integer, RouteHintView> aa = new ConcurrentHashMap();
    private float p = 14.5f;
    private float h = 14.5f;
    private int ad = 0;
    private List<Bitmap> b = new ArrayList();
    private int q = -1;
    private int r = 0;
    private boolean o = true;
    private boolean m = true;

    static {
        Float valueOf = Float.valueOf(0.5f);
        e = new Pair<>(valueOf, valueOf);
        c = Color.rgb(251, 101, 34);
        f13253a = Color.rgb(172, 172, 172);
    }

    public hmm(View view, Context context, ClockingRankActivity clockingRankActivity) {
        LogUtil.a("RunningRouteViewHolder", "RunningRouteViewHolder");
        this.u = view;
        this.d = new WeakReference<>(clockingRankActivity);
        this.k = (HiMapHolder) this.u.findViewById(R.id.running_route_map_holder);
        this.ah = (ImageButton) this.u.findViewById(R.id.sort_button);
        this.af = (CustomTitleBar) this.u.findViewById(R.id.clocking_rank_place_title_bar);
        this.z = new RouteSiteDistanceSortDialog(context);
        c(context);
        d(this.z);
        b(context);
    }

    private void c(final Context context) {
        ImageButton imageButton = (ImageButton) this.u.findViewById(R.id.location_button);
        if (imageButton != null) {
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: hmx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    hmm.this.blj_(context, view);
                }
            });
        }
    }

    /* synthetic */ void blj_(Context context, View view) {
        gzh.e();
        if (PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED) {
            c(RunningRouteUtils.a(), 14.5f);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(Context context) {
        if (r()) {
            HealthBubbleLayout healthBubbleLayout = (HealthBubbleLayout) this.u.findViewById(R.id.clocking_rank_switch_bubble);
            this.ae = healthBubbleLayout;
            healthBubbleLayout.setOnClickListener(new View.OnClickListener() { // from class: hmw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    hmm.this.blg_(view);
                }
            });
            this.j = (HealthCardView) this.u.findViewById(R.id.clocking_rank_draw_graph);
            this.y = (HealthCardView) this.u.findViewById(R.id.clocking_rank_draw_route);
            e(this.o, this.j);
            e(this.m, this.y);
            this.j.setOnClickListener(new View.OnClickListener() { // from class: hmt
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    hmm.this.blh_(view);
                }
            });
            this.y.setOnClickListener(new View.OnClickListener() { // from class: hmu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    hmm.this.bli_(view);
                }
            });
            SharedPreferences sharedPreferences = context.getSharedPreferences(Integer.toString(20002), 0);
            this.ac = sharedPreferences;
            if (sharedPreferences != null) {
                if (sharedPreferences.getBoolean("routeDetailSwitchBubble", false)) {
                    this.ae.setVisibility(8);
                } else {
                    this.ae.setVisibility(0);
                }
            }
        }
    }

    /* synthetic */ void blg_(View view) {
        m();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void blh_(View view) {
        boolean z = !this.o;
        if (!z && !this.m) {
            LogUtil.h("RunningRouteViewHolder", "Can't uncheck both");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a(z, this.j);
            m();
            b("m4");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* synthetic */ void bli_(View view) {
        boolean z = !this.m;
        if (!z && !this.o) {
            LogUtil.h("RunningRouteViewHolder", "Can't uncheck both");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            c(z, this.y);
            m();
            b("m5");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void m() {
        this.ae.setVisibility(8);
        SharedPreferences sharedPreferences = this.ac;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("routeDetailSwitchBubble", true);
            edit.apply();
        }
    }

    public void i() {
        RunningRouteUtils.b(this.d.get(), new RunningRouteUtils.GuideDialogCallBack() { // from class: hmv
            @Override // com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.GuideDialogCallBack
            public final void showRecommendedRoute() {
                hmm.this.a();
            }
        });
    }

    /* synthetic */ void a() {
        a(false, this.j);
        c(true, this.y);
    }

    private void a(boolean z, HealthCardView healthCardView) {
        if (healthCardView == null) {
            LogUtil.h("RunningRouteViewHolder", "drawButton is null in clickDrawButton");
            return;
        }
        this.o = z;
        e(z, healthCardView);
        q();
        t();
    }

    private void c(boolean z, HealthCardView healthCardView) {
        if (healthCardView == null) {
            LogUtil.h("RunningRouteViewHolder", "routeButton is null in clickRouteButton");
            return;
        }
        this.m = z;
        e(z, healthCardView);
        p();
    }

    private void q() {
        if (koq.b(this.g)) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "mCpLineIds is empty");
            return;
        }
        Iterator<String> it = this.g.iterator();
        while (it.hasNext()) {
            this.t.setLineVisible(it.next(), this.o);
        }
    }

    private void t() {
        this.t.setOverlayVisible(this.x, this.o);
    }

    private void p() {
        this.t.setLineVisible(this.v, this.m);
    }

    private void e(boolean z, HealthCardView healthCardView) {
        if (z) {
            healthCardView.setCardBackgroundColor(nsf.c(R.color._2131297831_res_0x7f090627));
        } else {
            healthCardView.setCardBackgroundColor(nsf.c(R.color._2131299014_res_0x7f090ac6));
        }
    }

    private boolean r() {
        WeakReference<ClockingRankActivity> weakReference = this.d;
        return (weakReference == null || weakReference.get() == null || !this.d.get().e()) ? false : true;
    }

    private void d(final RouteSiteDistanceSortDialog routeSiteDistanceSortDialog) {
        LogUtil.a("RunningRouteViewHolder", "initTypeSortButton");
        e();
        this.ah.setOnClickListener(new View.OnClickListener() { // from class: hmy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                hmm.this.blk_(routeSiteDistanceSortDialog, view);
            }
        });
    }

    /* synthetic */ void blk_(RouteSiteDistanceSortDialog routeSiteDistanceSortDialog, View view) {
        gzh.b();
        routeSiteDistanceSortDialog.a(o(), new RouteSiteDistanceSortDialog.OnDialogClickListener() { // from class: hmo
            @Override // com.huawei.healthcloud.plugintrack.runningroute.data.RouteSiteDistanceSortDialog.OnDialogClickListener
            public final void onDialogClick(gyq gyqVar) {
                hmm.this.c(gyqVar);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void c(gyq gyqVar) {
        a(gyqVar);
        e();
        e(false);
    }

    public void a(Context context, hjd hjdVar) {
        int e2 = gwg.e(context, hjdVar.b, hjdVar.d);
        this.r = e2;
        LogUtil.a("RunningRouteViewHolder", "initView with mapType: ", Integer.valueOf(e2));
        if (this.k != null) {
            d(context, hjdVar);
        }
    }

    private void d(Context context, hjd hjdVar) {
        int i = this.r;
        if (i == 1) {
            ReleaseLogUtil.e("RunningRouteViewHolder", "init Gaode Map");
            this.t = this.k.bhK_(null, BaseApplication.getContext());
            e(context, hjdVar);
        } else if (i == 2) {
            ReleaseLogUtil.e("RunningRouteViewHolder", "init google Map");
            b(context, hjdVar);
        } else if (i == 3) {
            ReleaseLogUtil.e("RunningRouteViewHolder", "init hms  Map");
            c(context, hjdVar);
        } else {
            if (i == -1) {
                ReleaseLogUtil.e("RunningRouteViewHolder", "init empty Map");
                this.t = this.k.bhJ_(null, context);
                e(context, hjdVar);
                return;
            }
            ReleaseLogUtil.c("RunningRouteViewHolder", "map type not match");
        }
    }

    private void b(final Context context, final hjd hjdVar) {
        this.k.a(context, (Fragment) null, new SyncMapCallback() { // from class: hmr
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public final void onMapReady(InterfaceHiMap interfaceHiMap) {
                hmm.this.a(context, hjdVar, interfaceHiMap);
            }
        });
    }

    /* synthetic */ void a(Context context, hjd hjdVar, InterfaceHiMap interfaceHiMap) {
        if (interfaceHiMap == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "initGoogleMap onMapReady map is null");
            return;
        }
        ReleaseLogUtil.e("RunningRouteViewHolder", "initGoogleMap finish");
        this.t = interfaceHiMap;
        e(context, hjdVar);
    }

    private void c(final Context context, final hjd hjdVar) {
        this.k.b(context, new SyncMapCallback() { // from class: hmq
            @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
            public final void onMapReady(InterfaceHiMap interfaceHiMap) {
                hmm.this.b(context, hjdVar, interfaceHiMap);
            }
        });
    }

    /* synthetic */ void b(Context context, hjd hjdVar, InterfaceHiMap interfaceHiMap) {
        if (interfaceHiMap == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "initHmsMap onMapReady hiMap is null");
            return;
        }
        ReleaseLogUtil.e("RunningRouteViewHolder", "initHmsMap finish");
        this.t = interfaceHiMap;
        e(context, hjdVar);
    }

    public void b(hjd hjdVar) {
        this.i = hjdVar;
        c(hjdVar, 14.5f);
    }

    private void c(hjd hjdVar, float f) {
        LogUtil.a("RunningRouteViewHolder", "changeLocation, mMpaFirstLoadSuccess: ", Boolean.valueOf(this.s), ", zoom: ", Float.valueOf(f));
        if (hjdVar != null) {
            LogUtil.a("RunningRouteViewHolder", "lat: ", Double.valueOf(hjdVar.b), ", lng: ", Double.valueOf(hjdVar.d));
        }
        e(hjdVar);
        this.p = f;
        if (this.s) {
            e(hjdVar, f);
        } else {
            this.l = hjdVar;
        }
    }

    public void j() {
        if (this.i == null) {
            LogUtil.a("RunningRouteViewHolder", "mCameraLocation is null");
        }
        a(this.i);
    }

    public void a(hjd hjdVar) {
        c(hjdVar, this.h);
    }

    private void e(hjd hjdVar, float f) {
        hlh hlhVar = new hlh();
        hlhVar.c(hjdVar).e(f).d(0.0f).b(0.0f);
        this.t.animateCamera(hlhVar, 0L, (InterfaceMapCallback) null);
    }

    public void d(hjd hjdVar) {
        if (!this.s) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "addSearchLocationMarker mMpaFirstLoadSuccess false");
            return;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), R.drawable._2131430171_res_0x7f0b0b1b);
        this.b.add(decodeResource);
        hlg hlgVar = new hlg();
        hlgVar.bhP_(e);
        hlgVar.d(hjdVar);
        hlgVar.bhQ_(decodeResource);
        int i = this.ad;
        if (i == 0) {
            this.ad = this.t.addMarker(hlgVar, null);
        } else {
            this.t.moveMarker(i, hjdVar);
        }
    }

    private void e(hjd hjdVar) {
        if (hjdVar == null) {
            LogUtil.a("RunningRouteViewHolder", "refreshCityName: latLng is null");
        } else {
            emc.d().getCityInfoWithGps(new emq.d().e(hjdVar.b).b(hjdVar.d).b(), new d(this));
        }
    }

    public void e(boolean z) {
        gyq o = o();
        if (o == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "routeInfo is null in sortTypeHotPath");
            return;
        }
        int b = o.b();
        int d2 = o.d();
        Iterator it = new HashSet(this.aa.keySet()).iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            RouteHintView routeHintView = this.aa.get(Integer.valueOf(intValue));
            if (routeHintView != null && e(b, routeHintView.getPathDistanceType()) && e(d2, routeHintView.getPathType())) {
                this.t.showMarker(intValue, null);
                z2 = true;
            } else {
                this.t.hideMarker(intValue, null);
            }
        }
        if (z) {
            gzh.b(z2, d2);
        }
    }

    private boolean e(int i, List<Integer> list) {
        if (i == 0) {
            return true;
        }
        if (!koq.c(list)) {
            return false;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (i == it.next().intValue()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c(Context context, List<enf> list) {
        Object[] objArr = new Object[2];
        objArr[0] = "drawRouterMarkerInMap, sortHotPath size: ";
        objArr[1] = list == null ? Constants.NULL : Integer.valueOf(list.size());
        LogUtil.a("RunningRouteViewHolder", objArr);
        if (!this.s) {
            this.ab = list;
            return;
        }
        if (koq.b(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            enf enfVar = list.get(i);
            if (enfVar != null && enfVar.g() != null) {
                LogUtil.a("RunningRouteViewHolder", "show path: ", enfVar.toString());
                GpsPoint g = enfVar.g();
                RouteHintView routeHintView = new RouteHintView(context);
                if ((context instanceof IRouteDetail) && this.s) {
                    routeHintView.a(enfVar, (IRouteDetail) context);
                    Bitmap blf_ = blf_(routeHintView);
                    hlg hlgVar = new hlg();
                    hlgVar.bhP_(e);
                    hlgVar.d(e(g));
                    hlgVar.bhQ_(blf_);
                    int addMarker = this.t.addMarker(hlgVar, null);
                    this.t.addMarkerClickListener(new e(this));
                    this.aa.put(Integer.valueOf(addMarker), routeHintView);
                }
            }
        }
    }

    public void d(RouteHintMvpView routeHintMvpView) {
        enc encVar;
        e(routeHintMvpView);
        if (!this.s || (encVar = this.n) == null || this.w == null) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "setMvpMark mMpaFirstLoadSuccess false or mHotPathDetailInfo is null");
            return;
        }
        List<GpsPoint> k = encVar.k();
        if (koq.b(k)) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "pathPoints is empty in setMvpMark");
            return;
        }
        LogUtil.a("RunningRouteViewHolder", "begin to setMvpMark");
        Bitmap blf_ = blf_(this.w);
        hlg hlgVar = new hlg();
        hlgVar.bhP_(e);
        hlgVar.d(e(k.get(0)));
        hlgVar.bhQ_(blf_);
        int i = this.q;
        if (i != -1) {
            LogUtil.a("RunningRouteViewHolder", "mMvpMarkId = ", Integer.valueOf(i), ", begin to delete empty marker");
            this.t.hideMarker(this.q, null);
        }
        this.q = this.t.addMarker(hlgVar, null);
        this.t.disableMarkerClick();
    }

    private void e(RouteHintMvpView routeHintMvpView) {
        if (routeHintMvpView != null) {
            this.w = routeHintMvpView;
        }
    }

    public boolean a(int i) {
        return this.aa.size() != i || this.aa.size() == 0;
    }

    private Bitmap blf_(View view) {
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    public void a(enc encVar) {
        if (encVar == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "pathDetail is null in drawPathDetailToMap");
            return;
        }
        this.n = encVar;
        f();
        e(e(this.n.o()));
    }

    private void f() {
        if (this.n == null || !this.s) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "mHotPathDetailInfo is null or map is loading");
            return;
        }
        d();
        emf a2 = this.n.a();
        if (a2 != null) {
            c(a2);
            b(a2);
        }
        c(gzi.f());
        l();
        h();
    }

    private void h() {
        List<GpsPoint> k = this.n.k();
        if (koq.b(k)) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "no path points, error");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<GpsPoint> it = k.iterator();
        while (it.hasNext()) {
            arrayList.add(e(it.next()));
        }
        emf a2 = this.n.a();
        int c2 = nsn.c(BaseApplication.getContext(), 340.0f);
        hlc hlcVar = new hlc();
        hlcVar.d(c2).c(c2);
        if (a2 == null || a2.c() == null) {
            hlcVar.a(nsn.c(BaseApplication.getContext(), 50.0f));
        } else {
            eml c3 = a2.c();
            arrayList.addAll(gwe.c(e(c3.e()), (int) c3.d()));
            hlcVar.e((float) c3.c());
        }
        hlcVar.e(arrayList);
        this.t.animateCamera(hlcVar, 0);
        this.t.scrollBy(0.0f, nsn.j() * 0.12f);
    }

    private void l() {
        LogUtil.a("RunningRouteViewHolder", "drawPathLines");
        List<GpsPoint> k = this.n.k();
        if (koq.b(k)) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "drawHotTrackPath list is null");
            return;
        }
        ArrayList arrayList = new ArrayList(k.size());
        Iterator<GpsPoint> it = k.iterator();
        while (it.hasNext()) {
            arrayList.add(e(it.next()));
        }
        hle hleVar = new hle();
        hleVar.e(arrayList);
        hleVar.c(r() ? f13253a : c);
        hleVar.a(9.0f);
        hleVar.a(false);
        hleVar.b(hag.a(4.0f));
        this.v = this.t.drawLines(hleVar);
        if (r()) {
            hjd e2 = e(k.get(0));
            hjd e3 = e(k.get(k.size() - 1));
            this.t.addMarker(new hlg().d(e2).a(13).bhQ_(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), R.drawable._2131428718_res_0x7f0b056e)), null);
            this.t.addMarker(new hlg().d(e3).a(13).bhQ_(BitmapFactory.decodeResource(BaseApplication.getContext().getResources(), R.drawable._2131428707_res_0x7f0b0563)), null);
        }
    }

    private void e(List<GpsPoint> list) {
        Bitmap cHF_ = nrf.cHF_(nsf.cKq_(R.drawable._2131431310_res_0x7f0b0f8e));
        Iterator<GpsPoint> it = list.iterator();
        while (it.hasNext()) {
            this.t.addMarker(new hlg().a(12).e(0.5f, 0.5f).d(e(it.next())).bhQ_(cHF_), null);
        }
    }

    private void b(emf emfVar) {
        List<GpsPoint> e2 = emfVar.e();
        List<emg> a2 = emfVar.a();
        if (koq.b(a2) || koq.b(e2)) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "no cp gpsPoints");
            return;
        }
        e(e2);
        for (emg emgVar : a2) {
            hle hleVar = new hle();
            hleVar.a(d(e2, emgVar.e()));
            hleVar.c(d(e2, emgVar.b()));
            hleVar.c(c);
            hleVar.a(11.0f);
            hleVar.b(hag.a(8.0f));
            this.g.add(this.t.drawLines(hleVar));
        }
    }

    private void c(emf emfVar) {
        enm m = this.n.m();
        if (m == null) {
            LogUtil.h("RunningRouteViewHolder", "no PathImageInfo");
            return;
        }
        String c2 = m.c();
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("RunningRouteViewHolder", "no ImageBackgroundUrl");
        } else if (emfVar.c() == null) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "no drawDrawBoard");
        } else {
            nrf.b(c2, new AnonymousClass3(emfVar.c()));
        }
    }

    /* renamed from: hmm$3, reason: invalid class name */
    class AnonymousClass3 extends CustomTarget<Bitmap> {
        final /* synthetic */ eml b;

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
        }

        AnonymousClass3(eml emlVar) {
            this.b = emlVar;
        }

        /* synthetic */ void bll_(Bitmap bitmap, eml emlVar) {
            hmm.this.ble_(bitmap, emlVar);
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: blm_, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(final Bitmap bitmap, Transition<? super Bitmap> transition) {
            final eml emlVar = this.b;
            HandlerExecutor.a(new Runnable() { // from class: hnc
                @Override // java.lang.Runnable
                public final void run() {
                    hmm.AnonymousClass3.this.bll_(bitmap, emlVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ble_(Bitmap bitmap, eml emlVar) {
        this.x = this.t.addOverlay(new hlj().e(e(emlVar.e())).d((float) emlVar.d()).c(10.0f).a((float) emlVar.c()).bhS_(bitmap));
    }

    private hjd e(GpsPoint gpsPoint) {
        hjd c2 = gwe.c(gpsPoint);
        enc encVar = this.n;
        return encVar != null ? gwe.c(c2, encVar.e(), this.r) : c2;
    }

    private hjd d(List<GpsPoint> list, int i) {
        if (koq.d(list, i)) {
            GpsPoint gpsPoint = list.get(i);
            if (gpsPoint.getIndex() == i) {
                return e(gpsPoint);
            }
        }
        for (GpsPoint gpsPoint2 : list) {
            if (gpsPoint2.getIndex() == i) {
                return e(gpsPoint2);
            }
        }
        return new hjd(90.0d, 180.0d);
    }

    private boolean e(final Context context, hjd hjdVar) {
        if (this.t == null) {
            ReleaseLogUtil.d("RunningRouteViewHolder", "mMapInterface is null");
            ((ClockingRankActivity) context).finish();
            return true;
        }
        if (nrt.a(BaseApplication.getContext())) {
            this.t.setMapShowType(3);
        } else {
            this.t.setMapShowType(0);
        }
        this.t.onCreate(null, false, false);
        this.t.setIsStop(false);
        this.t.onMapLoaded(hjdVar, 14.5f);
        s();
        this.t.setOnMapLoadedListener(new InterfaceMapLoadedCallback() { // from class: hmp
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapLoadedCallback
            public final void onMapLoaded() {
                hmm.this.a(context);
            }
        });
        return false;
    }

    /* synthetic */ void a(final Context context) {
        LogUtil.a("RunningRouteViewHolder", "onMapLoaded");
        this.s = true;
        HandlerExecutor.e(new Runnable() { // from class: hms
            @Override // java.lang.Runnable
            public final void run() {
                hmm.this.e(context);
            }
        });
    }

    /* synthetic */ void e(Context context) {
        hjd hjdVar = this.l;
        if (hjdVar != null) {
            c(hjdVar, this.p);
        }
        f();
        c(context, this.ab);
        d(this.w);
    }

    private void s() {
        this.t.setCameraChangeCallback(new InterfaceMapStatusChangeCallback() { // from class: hmm.5
            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback
            public void onMapStatusChange(hlh hlhVar) {
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapStatusChangeCallback
            public void onMapStatusChangeFinish(hlh hlhVar) {
                if (hmm.this.f >= hlhVar.c() || ((ClockingRankActivity) hmm.this.d.get()).b()) {
                    if (hmm.this.f > hlhVar.c() && !((ClockingRankActivity) hmm.this.d.get()).b()) {
                        if (!((ClockingRankActivity) hmm.this.d.get()).a()) {
                            gzh.h();
                        }
                        if (((ClockingRankActivity) hmm.this.d.get()).a()) {
                            ((ClockingRankActivity) hmm.this.d.get()).d(false);
                        }
                    } else {
                        LogUtil.a("RunningRouteViewHolder", "curZoom is equal with originZoom, no need to bi");
                    }
                } else {
                    gzh.f();
                }
                hmm.this.f = hlhVar.c();
                hmm.this.ai = hlhVar;
            }
        });
    }

    public void g() {
        hlh hlhVar = this.ai;
        if (hlhVar == null) {
            LogUtil.a("RunningRouteViewHolder", "mStatusChangDescription is null");
        } else {
            this.i = hlhVar.d();
            this.h = this.ai.c();
        }
    }

    public void c(hjd hjdVar) {
        if (PermissionUtil.e(this.d.get(), PermissionUtil.PermissionType.LOCATION) != PermissionUtil.PermissionResult.GRANTED) {
            LogUtil.h("RunningRouteViewHolder", "location permission is denied, cannot display user location marker");
            return;
        }
        InterfaceHiMap interfaceHiMap = this.t;
        if (interfaceHiMap != null) {
            interfaceHiMap.setMarkerIconPath(k(), n());
            this.t.addEndMarker(hjdVar, true);
        }
    }

    public void c() {
        emc.d().requestLocationUpdates(new a(this));
    }

    public void e() {
        WeakReference<ClockingRankActivity> weakReference = this.d;
        if (weakReference == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "mActivityWeakRef is null in setRouteInfo");
            return;
        }
        ClockingRankActivity clockingRankActivity = weakReference.get();
        if (clockingRankActivity != null) {
            clockingRankActivity.d();
        }
    }

    private void a(gyq gyqVar) {
        WeakReference<ClockingRankActivity> weakReference = this.d;
        if (weakReference == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "mActivityWeakRef is null in setRouteInfo");
            return;
        }
        ClockingRankActivity clockingRankActivity = weakReference.get();
        if (clockingRankActivity != null) {
            clockingRankActivity.c(gyqVar);
        }
    }

    private gyq o() {
        gyq gyqVar = new gyq();
        WeakReference<ClockingRankActivity> weakReference = this.d;
        if (weakReference == null) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "mActivityWeakRef is null in getRouteInfo");
            return gyqVar;
        }
        ClockingRankActivity clockingRankActivity = weakReference.get();
        return clockingRankActivity != null ? clockingRankActivity.c() : gyqVar;
    }

    public void d() {
        if (this.t != null) {
            LogUtil.h("RunningRouteViewHolder", "mMapInterface clear");
            this.t.clear();
        }
        this.ad = 0;
        this.aa.clear();
    }

    public void b() {
        InterfaceHiMap interfaceHiMap = this.t;
        if (interfaceHiMap != null) {
            interfaceHiMap.clear();
            this.t.onDestroy();
            this.ad = 0;
        }
        Iterator<Bitmap> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        emc.d().removeLocationUpdates();
    }

    private String k() {
        String str;
        gxu a2 = gtv.a("SportAdSportingIconOutdoor");
        if (a2 == null || TextUtils.isEmpty(a2.a())) {
            str = null;
        } else {
            str = a2.a();
            MarketingBiUtils.d(13002, gtv.b(13002));
        }
        LogUtil.a("RunningRouteViewHolder", "getSportAdIconPath() iconPath: ", str);
        return str;
    }

    private String n() {
        gxu a2 = gtv.a("SportAdSportingIconBgOutdoor");
        String d2 = (a2 == null || TextUtils.isEmpty(a2.d())) ? null : a2.d();
        LogUtil.a("RunningRouteViewHolder", "getSportAdBgIconPath() bgIconPath: ", d2);
        return d2;
    }

    private void b(String str) {
        enc encVar = this.n;
        if (encVar != null) {
            gzh.c(1, str, encVar.h(), this.n.n(), r());
        }
    }

    static class a implements ILocationChangeListener {
        private WeakReference<hmm> c;

        public a(hmm hmmVar) {
            this.c = new WeakReference<>(hmmVar);
        }

        @Override // com.huawei.health.motiontrack.api.ILocationChangeListener
        public void onLocationChange(hjd hjdVar) {
            WeakReference<hmm> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null || this.c.get().t == null) {
                return;
            }
            this.c.get().t.addEndMarker(hjdVar);
        }
    }

    static class e implements InterfaceMapMarkerClickCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<hmm> f13254a;

        public e(hmm hmmVar) {
            this.f13254a = new WeakReference<>(hmmVar);
        }

        @Override // com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapMarkerClickCallback
        public void onMarkerClick(int i) {
            RouteHintView routeHintView;
            WeakReference<hmm> weakReference = this.f13254a;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.a("RunningRouteViewHolder", "MapMarkerClickCallback weakRef is null");
                return;
            }
            hmm hmmVar = this.f13254a.get();
            if (hmmVar.d != null && hmmVar.d.get() != null) {
                if (i == -1 || !hmmVar.aa.containsKey(Integer.valueOf(i)) || (routeHintView = (RouteHintView) hmmVar.aa.get(Integer.valueOf(i))) == null) {
                    return;
                }
                ((IRouteDetail) hmmVar.d.get()).show(routeHintView.getPathId());
                hmmVar.t.clear();
                hmmVar.ad = 0;
                hmmVar.c(RunningRouteUtils.a());
                return;
            }
            LogUtil.a("RunningRouteViewHolder", "onMarkerClick: activity is destroy");
        }
    }

    static class d extends UiCallback<emp> {
        private final WeakReference<hmm> e;

        public d(hmm hmmVar) {
            this.e = new WeakReference<>(hmmVar);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("RunningRouteViewHolder", "GetCityInfoCallBack errorInfo: ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(emp empVar) {
            if (empVar == null) {
                ReleaseLogUtil.c("RunningRouteViewHolder", "GetCityInfoCallBack data is null");
                return;
            }
            HotPathCityInfo d = empVar.d();
            if (d == null) {
                ReleaseLogUtil.c("RunningRouteViewHolder", "GetCityInfoCallBack cityInfo is null");
                return;
            }
            hmm hmmVar = this.e.get();
            if (hmmVar == null || hmmVar.af == null) {
                return;
            }
            String cityName = d.getCityName();
            LogUtil.a("RunningRouteViewHolder", "GetCityInfoCallBack cityName is ", cityName);
            hmmVar.af.setTitleText(cityName);
        }
    }
}
