package defpackage;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.sportservice.SportComponent;
import com.huawei.healthcloud.plugintrack.manager.hwhealthalgormodel.HotRouteCalculate;
import com.huawei.healthcloud.plugintrack.manager.inteface.LoadHotTrackCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import defpackage.gvd;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class gto implements SportComponent {

    /* renamed from: a, reason: collision with root package name */
    private gvh f12928a;
    private enc b;
    private LinkedHashMap<String, String> c = new LinkedHashMap<>();

    public void a(String str, final LoadHotTrackCallBack loadHotTrackCallBack) {
        LogUtil.c("Track_HotTrackDataManager", "hotPathId is ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_HotTrackDataManager", "hotPathId is null, return");
        } else {
            emc.d().getHotPathDetail(str, new UiCallback<enc>() { // from class: gto.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("Track_HotTrackDataManager", "can't get hotPathDetail & errorCode = ", Integer.valueOf(i), " errorInfo = ", str2);
                    LoadHotTrackCallBack loadHotTrackCallBack2 = loadHotTrackCallBack;
                    if (loadHotTrackCallBack2 != null) {
                        loadHotTrackCallBack2.onFailure();
                    } else {
                        LogUtil.b("Track_HotTrackDataManager", "callback is null");
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(enc encVar) {
                    if (encVar == null) {
                        LogUtil.b("Track_HotTrackDataManager", "hotPath detail is null, return");
                        return;
                    }
                    LoadHotTrackCallBack loadHotTrackCallBack2 = loadHotTrackCallBack;
                    if (loadHotTrackCallBack2 != null) {
                        loadHotTrackCallBack2.onSuccess(encVar);
                        gto.this.e(encVar);
                    } else {
                        LogUtil.b("Track_HotTrackDataManager", "callback is null");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final enc encVar) {
        a(encVar);
        ThreadPoolManager.d().execute(new Runnable() { // from class: gtn
            @Override // java.lang.Runnable
            public final void run() {
                gwo.c(BaseApplication.getContext(), enc.this, "hotPathDetailInfo.txt");
            }
        });
    }

    public void a(enc encVar) {
        LogUtil.c("Track_HotTrackDataManager", "reHotPathInfoDetailInfo");
        if (encVar == null) {
            LogUtil.b("Track_HotTrackDataManager", "HotPathDetailInfo is null, return");
            return;
        }
        this.b = encVar;
        if (encVar.g() == 1) {
            this.f12928a = new gvd();
            ReleaseLogUtil.e("Track_HotTrackDataManager", "reHotPathInfoDetailInfo with route darw");
        } else {
            this.f12928a = new HotRouteCalculate();
        }
        this.f12928a.b(encVar);
    }

    public long a(MotionPath motionPath, MotionPathSimplify motionPathSimplify, boolean z) {
        boolean z2;
        if (this.b == null) {
            ReleaseLogUtil.c("Track_HotTrackDataManager", "mHotPathDetailInfo is null, return");
            return -1L;
        }
        if (motionPathSimplify == null || motionPath == null) {
            ReleaseLogUtil.c("Track_HotTrackDataManager", "motionPathSimplify or motionPath is null, return");
            return -1L;
        }
        if (this.f12928a == null) {
            ReleaseLogUtil.c("Track_HotTrackDataManager", "mRouteCalculate is null, return");
            return -1L;
        }
        this.c.put("isNormalTrack", z ? "true" : "false");
        this.c.put("userTotalDistance", String.valueOf(motionPathSimplify.requestTotalDistance()));
        motionPathSimplify.addExtendDataMap("hotPathId", this.b.h());
        motionPathSimplify.addExtendDataMap("hotPathName", this.b.n());
        if (z) {
            z2 = this.f12928a.b(motionPath.requestLbsDataMap(), motionPathSimplify.requestTotalDistance());
            LogUtil.a("Track_HotTrackDataManager", "user has finished the hot track ", Boolean.valueOf(z2));
        } else {
            ReleaseLogUtil.e("Track_HotTrackDataManager", "not normal track & not finished");
            z2 = false;
        }
        this.c.put("isMatch", z2 ? "true" : "false");
        this.c.putAll(this.f12928a.c());
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEALTH_HOT_TRACK_DATA.value(), this.c);
        motionPathSimplify.addExtendDataMap("finishState", z2 ? "1" : "0");
        if (z2) {
            return this.b.j() + 1;
        }
        return this.b.j();
    }

    public void c(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            return;
        }
        String b = nru.b(motionPathSimplify.requestExtendDataMap(), "cpPunchState", "");
        LogUtil.a("Track_HotTrackDataManager", "stateStr is ", b);
        if (TextUtils.isEmpty(b) || b.length() < 2) {
            return;
        }
        String[] split = b.substring(1, b.length() - 1).split(",");
        if (split.length == 0) {
            return;
        }
        int[] iArr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            iArr[i] = CommonUtil.m(BaseApplication.getContext(), split[i]);
        }
        gvh gvhVar = this.f12928a;
        if (gvhVar != null) {
            gvhVar.b(iArr);
        }
    }

    public boolean c() {
        gvh gvhVar = this.f12928a;
        if (gvhVar == null) {
            return false;
        }
        if (gvhVar.b() instanceof gvd.b) {
            return !((gvd.b) r0).e();
        }
        return false;
    }

    public void aTF_(Location location) {
        gvh gvhVar = this.f12928a;
        if (gvhVar != null) {
            gvhVar.aUw_(location);
        }
    }

    public void b(MotionPathSimplify motionPathSimplify) {
        gvh gvhVar = this.f12928a;
        if (gvhVar != null) {
            Object b = gvhVar.b();
            if (b instanceof gvd.b) {
                motionPathSimplify.addExtendDataMap("cpPunchState", Arrays.toString(((gvd.b) b).b()).replaceAll(" ", ""));
                motionPathSimplify.addExtendDataMap("routeType", "2");
            }
        }
    }

    public int[] b() {
        gvh gvhVar = this.f12928a;
        if (gvhVar == null) {
            return null;
        }
        Object b = gvhVar.b();
        if (b instanceof gvd.b) {
            return ((gvd.b) b).b();
        }
        return null;
    }

    public void aTG_(Bundle bundle) {
        gvh gvhVar;
        gvd.b bVar;
        int[] b;
        if (bundle == null || (gvhVar = this.f12928a) == null) {
            return;
        }
        Object b2 = gvhVar.b();
        if (!(b2 instanceof gvd.b) || (b = (bVar = (gvd.b) b2).b()) == null) {
            return;
        }
        bundle.putInt("progreeRate", bVar.a());
        bundle.putString("extroInfo", d(bVar.c(), b.length));
        bundle.putInt("extroLocationState", bVar.d());
    }

    private String d(int i, int i2) {
        return nsf.b(R.string._2130847368_res_0x7f022688, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0));
    }

    public String e() {
        gvd.b bVar;
        int[] b;
        gvh gvhVar = this.f12928a;
        if (gvhVar != null) {
            Object b2 = gvhVar.b();
            if (!(b2 instanceof gvd.b) || (b = (bVar = (gvd.b) b2).b()) == null) {
                return "";
            }
            return bVar.c() + "/" + b.length;
        }
        return "";
    }

    @Override // com.huawei.health.sportservice.SportComponent
    public void destroy() {
        gvh gvhVar = this.f12928a;
        if (gvhVar != null) {
            gvhVar.e();
        }
        this.b = null;
        this.c.clear();
    }
}
