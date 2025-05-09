package defpackage;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.pluginlocation.MAGAndGPSService;
import com.huawei.health.trackprocess.api.TrackPostProcessApi;
import com.huawei.healthcloud.plugintrack.manager.TrackAltitudeMgr;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gwq;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class gwq {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12974a = new Object();
    private static volatile gwq e;
    private final Context b;
    private String d;
    private final TrackAltitudeMgr i;
    private hiy j;
    private boolean f = true;
    private boolean g = false;
    private Map<Long, double[]> c = new HashMap();

    private gwq(Context context) {
        this.b = context;
        this.i = new TrackAltitudeMgr(context);
    }

    public static gwq a() {
        gwq gwqVar;
        synchronized (f12974a) {
            if (e == null) {
                e = new gwq(BaseApplication.getContext());
            }
            gwqVar = e;
        }
        return gwqVar;
    }

    public void a(hiy hiyVar, boolean z, boolean z2) {
        if (!gwy.e(gtx.c(this.b).n()) || hiyVar.j()) {
            return;
        }
        if (this.f && !z) {
            gwy.a();
            d(hiyVar);
        } else {
            b(hiyVar, z2);
        }
        this.f = false;
    }

    public void b(hiy hiyVar, boolean z) {
        if (gwy.e(gtx.c(this.b).n())) {
            if (hiyVar == null || this.j == null) {
                Object[] objArr = new Object[4];
                objArr[0] = "updateGpsFileForAfterProcess basePoint is null ";
                objArr[1] = Boolean.valueOf(hiyVar == null);
                objArr[2] = " mLastReportBasePoint is null ";
                objArr[3] = Boolean.valueOf(this.j == null);
                LogUtil.a("Track_TrackAfterProcessGpsManager", objArr);
                this.j = hiyVar;
                return;
            }
            if ((hiyVar.i() - this.j.i()) / 1000 <= 0) {
                LogUtil.a("Track_TrackAfterProcessGpsManager", "updateGpsFileForAfterProcess time less one second return!");
                return;
            }
            if (TextUtils.isEmpty(this.d)) {
                String str = gtx.c(this.b).aj() + "_gps.bin";
                this.d = str;
                LogUtil.a("Track_TrackAfterProcessGpsManager", "updateGpsFileForAfterProcess but fileName is null mGpsFileName ", str);
            }
            LogUtil.a("Track_TrackAfterProcessGpsManager", "updateGpsFileForAfterProcess before  time ", Long.valueOf(hiyVar.i()), " lat ", Double.valueOf(hiyVar.h()), " lng ", Double.valueOf(hiyVar.f()), Long.valueOf(this.j.i()), " lat ", Double.valueOf(this.j.h()), " lng ", Double.valueOf(this.j.f()));
            double[] b = gwy.b(this.j.h(), this.j.f(), hiyVar.h(), hiyVar.f());
            gwy.b(this.d, (short) ((hiyVar.i() - this.j.i()) / 1000), (short) (hiyVar.e() * 100.0f), (float) b[0], (float) b[1], (int) hiyVar.d(), (int) hiyVar.a(), z ? 1 : 0, (short) (hiyVar.c() - this.j.c()), (short) (this.i.b() ? hiyVar.b() : -1.0f));
            this.j = new hiy(hiyVar);
        }
    }

    public hiy d() {
        return this.j;
    }

    private void d(hiy hiyVar) {
        if (hiyVar == null) {
            return;
        }
        this.j = new hiy(hiyVar);
        int[] iArr = {0, 4, 3, 3, 3, 3, 3, 3, 3};
        if (TextUtils.isEmpty(this.d)) {
            String str = gtx.c(this.b).aj() + "_gps.bin";
            this.d = str;
            LogUtil.a("Track_TrackAfterProcessGpsManager", "setGpsFileDataHead but fileName is null mGpsFileName ", str);
        }
        LogUtil.a("Track_TrackAfterProcessGpsManager", "setGpsFileDataHead  locatedTime ", Long.valueOf(hiyVar.i()), " lng ", Double.valueOf(hiyVar.f()), " lat ", Double.valueOf(hiyVar.h()));
        gwy.d(this.d, 0, (int) (hiyVar.i() / 1000), hiyVar.f(), hiyVar.h(), hiyVar.c(), (int) hiyVar.b(), iArr);
    }

    public void aVh_(Location location, int i) {
        if (gwy.e(i)) {
            MAGAndGPSService.getInstance(this.b).setGpsLocation(location);
        }
    }

    public void g() {
        if (gwy.e(gtx.c(this.b).n())) {
            this.d = gtx.c(this.b).aj() + "_gps.bin";
            a().e();
            ThreadPoolManager.d().execute(new Runnable() { // from class: gwq.2
                @Override // java.lang.Runnable
                public void run() {
                    String url = GRSManager.a(gwq.this.b).getUrl("domainHealthcommonHicloud");
                    if (TextUtils.isEmpty(url)) {
                        LogUtil.b("Track_TrackAfterProcessGpsManager", "download url is empty");
                        return;
                    }
                    String str = url + "/commonAbility/configCenter/getConfigInfo";
                    LogUtil.a("Track_TrackAfterProcessGpsManager", "download url ", str);
                    MAGAndGPSService.getInstance(gwq.this.b).start(str, new gwj());
                }
            });
        }
    }

    public void i() {
        if (gwy.e(gtx.c(this.b).n())) {
            MAGAndGPSService.getInstance(this.b).pause();
        }
    }

    public void f() {
        if (gwy.e(gtx.c(this.b).n())) {
            MAGAndGPSService.getInstance(this.b).resume();
        }
    }

    public void e(long j, int i, MotionPathSimplify motionPathSimplify, boolean z) {
        if (gwy.e(gtx.c(this.b).n())) {
            if (z && gwy.e(j, i)) {
                a().b(this.d, true, motionPathSimplify);
            }
            MAGAndGPSService.getInstance(this.b).stop();
        }
        h();
    }

    public int b(String str, boolean z, MotionPathSimplify motionPathSimplify) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_TrackAfterProcessGpsManager", "getPostProcessingTrackResult fileName is empty! ");
            return -100;
        }
        LogUtil.b("Track_TrackAfterProcessGpsManager", "getPostProcessingTrackResult fileName is ", str, " deviceType ", Integer.valueOf(motionPathSimplify.requestDeviceType()));
        jdx.b(new AnonymousClass4(str, z, motionPathSimplify));
        return -100;
    }

    /* renamed from: gwq$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ MotionPathSimplify c;
        final /* synthetic */ String d;
        final /* synthetic */ boolean e;

        AnonymousClass4(String str, boolean z, MotionPathSimplify motionPathSimplify) {
            this.d = str;
            this.e = z;
            this.c = motionPathSimplify;
        }

        @Override // java.lang.Runnable
        public void run() {
            TrackPostProcessApi trackPostProcessApi = (TrackPostProcessApi) Services.c("Module_Track_Post_Process_Service", TrackPostProcessApi.class);
            String str = this.d;
            boolean z = this.e;
            final MotionPathSimplify motionPathSimplify = this.c;
            trackPostProcessApi.getPostProcessingTrack(str, "mobile_phone", z, new IBaseResponseCallback() { // from class: gwu
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    gwq.AnonymousClass4.this.d(motionPathSimplify, i, obj);
                }
            });
        }

        /* synthetic */ void d(MotionPathSimplify motionPathSimplify, int i, Object obj) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                int size = map.size();
                LogUtil.a("Track_TrackAfterProcessGpsManager", "getPostProcessingTrackResult ", Integer.valueOf(size));
                if (size >= 2) {
                    gwq.this.c = map;
                    Iterator it = map.keySet().iterator();
                    while (it.hasNext()) {
                        double[] dArr = (double[]) map.get((Long) it.next());
                        dArr[3] = new BigDecimal(Double.toString(1000.0d)).multiply(new BigDecimal(dArr[3])).doubleValue();
                        LogUtil.a("Track_TrackAfterProcessGpsManager", "getPostProcessingTrackResult lat=", Double.valueOf(dArr[0]), ",lon=", Double.valueOf(dArr[1]), ",alt=", Double.valueOf(dArr[2]), ",time=", String.valueOf(dArr[3]));
                    }
                    gwq.this.d(motionPathSimplify, map);
                    gwq.this.g = true;
                    Intent intent = new Intent();
                    intent.setPackage(BaseApplication.getAppPackage());
                    intent.putExtra("AfterProcessTrack", (Serializable) map);
                    intent.setAction("com.huawei.health.update_process_track");
                    BaseApplication.getContext().sendBroadcast(intent, SecurityConstant.d);
                    return;
                }
                ReleaseLogUtil.c("Track_TrackAfterProcessGpsManager", "getPostProcessingTrackResult gpsInfoMap size less than 2,size=", Integer.valueOf(size));
                gwp.b(i, size);
            }
        }
    }

    public boolean b() {
        return this.g;
    }

    public Map<Long, double[]> c() {
        return this.c;
    }

    public void j() {
        ReleaseLogUtil.e("Track_TrackAfterProcessGpsManager", "resetProcessFinish");
        this.g = false;
        this.c = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MotionPathSimplify motionPathSimplify, Map<Long, double[]> map) {
        if (motionPathSimplify == null) {
            LogUtil.b("Track_TrackAfterProcessGpsManager", "saveTrackDataWhenProcessFinished motionPathSimplify == null");
            return;
        }
        MotionPath c = gwk.c(this.b, motionPathSimplify.requestSportType());
        motionPathSimplify.saveEndTime(motionPathSimplify.requestEndTime() + 1);
        if (c != null) {
            c.saveLbsDataMap(map);
            double[] dArr = c.requestLbsDataMap().get(0L);
            LogUtil.a("Track_TrackAfterProcessGpsManager", "ProcessReceiver gpsInfoMap size ", Integer.valueOf(c.requestLbsDataMap().size()), " firstPoint lat =", Double.valueOf(dArr[0]), " firstPoint long =", Double.valueOf(dArr[1]));
            LogUtil.a("Track_TrackAfterProcessGpsManager", "ProcessReceiver isSuccess ", Boolean.valueOf(gwo.a(this.b, c, "motion_path_process.txt")));
            gso.e().c().saveTrackDataWhenProcess(motionPathSimplify, "motion_path_process.txt");
        }
    }

    public void h() {
        this.j = null;
        this.f = true;
        this.d = null;
        ReleaseLogUtil.e("Track_TrackAfterProcessGpsManager", "onDestroy finished ");
    }

    /* renamed from: gwq$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            gkj.b().e(new AppBundleLauncher.InstallCallback() { // from class: gwv
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return gwq.AnonymousClass1.aVi_(context, intent);
                }
            });
        }

        static /* synthetic */ boolean aVi_(Context context, Intent intent) {
            LogUtil.a("Track_TrackAfterProcessGpsManager", "PluginLocationProxy loadPlugin success");
            return false;
        }
    }

    public void e() {
        ThreadPoolManager.d().execute(new AnonymousClass1());
    }
}
