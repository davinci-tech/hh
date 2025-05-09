package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.HiMapHolder;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceMapCallback;
import com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class hnq implements IMapDrawingUpdater {
    private View b;
    private RelativeLayout c;
    private HiMapHolder d;
    private Context e;
    private final int k;
    private View l;
    private SyncMapCallback o;
    private InterfaceHiMap f = null;
    private int i = 0;
    private MotionData g = null;
    private int j = -1;

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f13274a = new ArrayList();
    private boolean h = false;

    public hnq(View view, Context context, int i, RelativeLayout relativeLayout) {
        this.l = view;
        this.e = context;
        this.k = i;
        this.c = relativeLayout;
    }

    public boolean d() {
        View view = this.l;
        if (view == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "initMapView mRootView is null");
            return true;
        }
        HiMapHolder hiMapHolder = (HiMapHolder) view.findViewById(R.id.sport_track_map_holder);
        this.d = hiMapHolder;
        if (hiMapHolder == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "initMapView mHiMapHolder is null");
            return true;
        }
        int e = gwg.e(this.e);
        this.i = e;
        return a(e);
    }

    public void e(int i) {
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder != null) {
            hiMapHolder.setVisibility(i);
        }
    }

    public void b(int i) {
        HiMapHolder hiMapHolder;
        if (this.i != 1 || (hiMapHolder = this.d) == null) {
            return;
        }
        hiMapHolder.setBackgroundColor(i);
    }

    private boolean a(int i) {
        if (i == 1) {
            return g();
        }
        if (i == 2) {
            h();
            return false;
        }
        if (i == 3) {
            i();
            return false;
        }
        if (i == -1) {
            return f();
        }
        LogUtil.a("Track_TrackMainMapViewHolder", "unexpected map type");
        return false;
    }

    private boolean f() {
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "initInvalidMap mapHolder is null");
            return true;
        }
        hiMapHolder.bhJ_(null, this.e);
        this.f = this.d.getHiMap();
        l();
        j();
        if (this.f == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "mMapInterface is null");
            return true;
        }
        if (nrt.a(this.e)) {
            this.f.setMapShowType(3);
        } else {
            this.f.setMapShowType(0);
        }
        this.b = this.l.findViewById(R.id.layout_map_fade_in);
        this.f.onCreate(null, true, true);
        this.f.onMapLoaded();
        c();
        return false;
    }

    private void i() {
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "initHmsMap mapHolder is null");
        } else {
            hiMapHolder.setBackgroundColor(this.e.getResources().getColor(R.color._2131298761_res_0x7f0909c9));
            this.d.b(this.e, new SyncMapCallback() { // from class: hnq.3
                @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
                public void onMapReady(InterfaceHiMap interfaceHiMap) {
                    hnq.this.k();
                    if (hnq.this.o != null) {
                        hnq.this.o.onMapReady(interfaceHiMap);
                    }
                    if (hnq.this.d != null) {
                        hnq.this.d.setBackground(null);
                    } else {
                        LogUtil.h("Track_TrackMainMapViewHolder", "hms onMapReady: mapHolder is null");
                    }
                }
            });
        }
    }

    private void h() {
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "initGoogleMap mapHolder is null");
        } else {
            hiMapHolder.setBackgroundColor(this.e.getResources().getColor(R.color._2131298761_res_0x7f0909c9));
            this.d.a(this.e, (Fragment) null, new SyncMapCallback() { // from class: hnq.2
                @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
                public void onMapReady(InterfaceHiMap interfaceHiMap) {
                    hnq.this.k();
                    if (hnq.this.o != null) {
                        hnq.this.o.onMapReady(interfaceHiMap);
                    }
                }
            });
        }
    }

    private boolean g() {
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "initGaodeMap mapHolder is null");
            return true;
        }
        hiMapHolder.bhK_(null, this.e);
        this.f = this.d.getHiMap();
        l();
        j();
        if (this.f == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "mMapInterface is null");
            return true;
        }
        if (nrt.a(this.e)) {
            this.f.setMapShowType(3);
        } else {
            this.f.setMapShowType(0);
        }
        this.b = this.l.findViewById(R.id.layout_map_fade_in);
        this.f.onCreate(null, true, true);
        this.f.onMapLoaded();
        c();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "syncMapReload mapHolder is null");
            return;
        }
        InterfaceHiMap hiMap = hiMapHolder.getHiMap();
        this.f = hiMap;
        if (hiMap == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "syncMapReload mMapInterface is null");
            return;
        }
        hiMap.onCreate(null, true, true);
        this.f.setIsStop(false);
        l();
        j();
        if (nrt.a(this.e)) {
            this.f.setMapShowType(3);
        } else {
            this.f.setMapShowType(0);
        }
        c();
        ReleaseLogUtil.e("Track_TrackMainMapViewHolder", "mIsRecoverGoogleTrack: ", Boolean.valueOf(this.h));
        if (this.h) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(this.g);
            this.f.onMapLoaded(arrayList);
            this.h = false;
            this.g = null;
        }
    }

    private void l() {
        if (this.c != null) {
            c(nsn.c(this.e, 16.0f), 0, 0, this.c.getHeight());
        }
    }

    public void c(int i, int i2, int i3, int i4) {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.setLogoPadding(i, i2, i3, i4);
        }
    }

    public void c() {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.setMarkerIconPath(a(), e());
        }
    }

    public void e(boolean z) {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.setPointToCenterWhole(z);
        }
    }

    private void j() {
        if (this.j == -1 || this.f == null) {
            return;
        }
        ReleaseLogUtil.e("Track_TrackMainMapViewHolder", "setLineColor with isCoverExisted");
        this.f.setLineColor(this.j, true);
    }

    private String a() {
        String str;
        gxu a2;
        if (this.k != 258 || (a2 = gtv.a("SportAdSportingIconOutdoor")) == null || TextUtils.isEmpty(a2.a())) {
            str = null;
        } else {
            str = a2.a();
            MarketingBiUtils.d(13002, gtv.b(13002));
        }
        LogUtil.a("Track_TrackMainMapViewHolder", "getSportAdIconPath() iconPath: ", str);
        return str;
    }

    private String e() {
        gxu a2;
        String d = (this.k != 258 || (a2 = gtv.a("SportAdSportingIconBgOutdoor")) == null || TextUtils.isEmpty(a2.d())) ? null : a2.d();
        LogUtil.a("Track_TrackMainMapViewHolder", "getSportAdBgIconPath() bgIconPath: ", d);
        return d;
    }

    public InterfaceHiMap b() {
        return this.f;
    }

    public void b(boolean z) {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.setScreenOnOrForegrand(z);
        }
        View view = this.b;
        if (view == null) {
            LogUtil.b("Track_TrackMainMapViewHolder", "not a map type");
        } else {
            if (z) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.4f, 1.0f);
                ofFloat.setDuration(500L);
                ofFloat.start();
                return;
            }
            view.setAlpha(0.4f);
        }
    }

    public void d(MotionData motionData) {
        if (motionData == null) {
            LogUtil.b("Track_TrackMainMapViewHolder", "motionData is null");
            return;
        }
        int e = gwg.e(this.e);
        LogUtil.a("Track_TrackMainMapViewHolder", "recoverTrack auto map", Integer.valueOf(gwg.a()));
        if (e == 1) {
            if (this.f != null) {
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(motionData);
                this.f.onMapLoaded(arrayList);
                return;
            }
            return;
        }
        this.h = true;
        this.g = motionData;
    }

    public void d(int i) {
        if (i != -1) {
            this.j = i;
        }
    }

    public void e(final enc encVar) {
        if (encVar == null) {
            LogUtil.h("Track_TrackMainMapViewHolder", "hotPathDetailInfo is null");
            return;
        }
        if (encVar.g() == 1) {
            d(nsf.c(R.color._2131296651_res_0x7f09018b));
            j();
        }
        if (this.f == null) {
            LogUtil.a("Track_TrackMainMapViewHolder", "mMapInterface is not null, trigger callback");
            this.o = new SyncMapCallback() { // from class: hnp
                @Override // com.huawei.healthcloud.plugintrack.ui.map.SyncMapCallback
                public final void onMapReady(InterfaceHiMap interfaceHiMap) {
                    hnq.this.b(encVar, interfaceHiMap);
                }
            };
            return;
        }
        List<GpsPoint> k = encVar.k();
        String e = encVar.e();
        a(k, e);
        emf a2 = encVar.a();
        if (a2 != null) {
            c(a2.e(), e);
        }
    }

    /* synthetic */ void b(enc encVar, InterfaceHiMap interfaceHiMap) {
        e(encVar);
    }

    private void a(List<GpsPoint> list, String str) {
        hpu.d(list, str, this.f);
    }

    private void c(List<GpsPoint> list, String str) {
        if (koq.b(list) || list.size() == this.f13274a.size()) {
            LogUtil.h("Track_TrackMainMapViewHolder", "cpPoints is empty or repeats draw");
            return;
        }
        this.f13274a.clear();
        Bitmap cHF_ = nrf.cHF_(nsf.cKq_(R.drawable._2131431549_res_0x7f0b107d));
        Iterator<GpsPoint> it = list.iterator();
        while (it.hasNext()) {
            this.f13274a.add(Integer.valueOf(this.f.addMarker(new hlg().a(9).d(a(it.next(), str)).bhQ_(cHF_), null)));
        }
        LogUtil.a("Track_TrackMainMapViewHolder", "mRouteDrawMarkerList size: ", Integer.valueOf(this.f13274a.size()));
    }

    private hjd a(GpsPoint gpsPoint, String str) {
        return gwe.c(gwe.c(gpsPoint), str, this.i);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void drawFirstLocation(hjd hjdVar) {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.addEndMarker(hjdVar);
            this.f.animateCamera(hjdVar, 0L, (InterfaceMapCallback) null);
        } else {
            LogUtil.h("Track_TrackMainMapViewHolder", "drawFirstLocation mMapInterface is null");
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void drawRecoveryLine(hjd hjdVar, hjd hjdVar2) {
        InterfaceHiMap interfaceHiMap;
        if (hjdVar == null || hjdVar2 == null || (interfaceHiMap = this.f) == null) {
            return;
        }
        interfaceHiMap.drawLine(hjdVar, hjdVar2);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void drawPauseLine(hjd hjdVar, hjd hjdVar2) {
        InterfaceHiMap interfaceHiMap;
        if (hjdVar == null || hjdVar2 == null || (interfaceHiMap = this.f) == null) {
            return;
        }
        interfaceHiMap.drawInterrupt(hjdVar, hjdVar2);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void releaseMap() {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.onDestroy();
            this.f = null;
        }
        HiMapHolder hiMapHolder = this.d;
        if (hiMapHolder != null) {
            hiMapHolder.b();
            this.d = null;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void addEndMarker(hjd hjdVar) {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.addEndMarker(hjdVar);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void addStartMarker(hjd hjdVar) {
        InterfaceHiMap interfaceHiMap;
        if (hjdVar == null || (interfaceHiMap = this.f) == null) {
            ReleaseLogUtil.d("Track_TrackMainMapViewHolder", "addStartMarker hiHealthLatLng or mMapInterface is null");
        } else {
            interfaceHiMap.addSportStartMarker(hjdVar, this.k);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void pauseSportClear() {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.pauseSportClear();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void forceDrawLineToMap() {
        InterfaceHiMap interfaceHiMap = this.f;
        if (interfaceHiMap != null) {
            interfaceHiMap.forceDrawLine();
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void drawLineToMap(List<hiy> list) {
        InterfaceHiMap interfaceHiMap;
        if (!koq.c(list) || (interfaceHiMap = this.f) == null) {
            return;
        }
        interfaceHiMap.drawLine(list);
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapDrawingUpdater
    public void updateCpMarker(int[] iArr) {
        if (iArr == null || this.f == null) {
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == 1 && koq.d(this.f13274a, i)) {
                this.f.setMarkerIcon(this.f13274a.get(i).intValue(), nrf.cHF_(nsf.cKq_(R.drawable._2131431548_res_0x7f0b107c)));
            }
        }
    }
}
