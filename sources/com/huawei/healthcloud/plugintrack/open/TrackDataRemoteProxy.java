package com.huawei.healthcloud.plugintrack.open;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.ITrackDataReport;
import com.huawei.health.ITrackSportManager;
import com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gtx;
import defpackage.gyk;

/* loaded from: classes4.dex */
public class TrackDataRemoteProxy extends ITrackSportManager.Stub {

    /* renamed from: a, reason: collision with root package name */
    private static int f3550a;
    private static RemoteCallbackList<e> c = new RemoteCallbackList<>();
    private static gyk d;
    private static e e;
    private d b = new d();
    private gtx g;
    private TrackService j;

    public TrackDataRemoteProxy(TrackService trackService) {
        this.j = null;
        this.g = null;
        if (trackService == null) {
            throw new RuntimeException("TrackDataRemoteProxy invalid params in constructor");
        }
        this.j = trackService;
        a();
        gyk gykVar = d;
        if (gykVar != null) {
            gykVar.b(this.b);
        }
        gtx c2 = gtx.c(BaseApplication.getContext());
        this.g = c2;
        d(c2.m());
    }

    public static e getLocalToRemoteProxy() {
        return e;
    }

    public static void setLocalToRemoteProxyNull() {
        e = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(int i) {
        f3550a = i;
    }

    private static void a() {
        d = new gyk();
    }

    @Override // com.huawei.health.ITrackSportManager
    public int isTrackWorking() throws RemoteException {
        if (this.j == null || this.g == null) {
            return 0;
        }
        return f3550a;
    }

    @Override // com.huawei.health.ITrackSportManager
    public void registerDataCallback(ITrackDataReport iTrackDataReport) throws RemoteException {
        int i;
        e eVar;
        gyk gykVar = d;
        if (gykVar != null) {
            gykVar.e();
            d(d.b());
        }
        LogUtil.a("Track_TrackDataRemoteProxy", "registerDataCallback at ", Long.valueOf(System.currentTimeMillis()));
        if (iTrackDataReport != null) {
            int i2 = 0;
            try {
                i = c.beginBroadcast();
            } catch (IllegalStateException e2) {
                LogUtil.b("Track_TrackDataRemoteProxy", "beginBroadcast()", e2.getMessage());
                i = 0;
            }
            LogUtil.a("Track_TrackDataRemoteProxy", "registerDataCallback:Report client count ", Integer.valueOf(i));
            while (true) {
                if (i2 >= i) {
                    eVar = null;
                    break;
                } else {
                    if (c.getBroadcastItem(i2).asBinder() == iTrackDataReport.asBinder()) {
                        eVar = c.getBroadcastItem(i2);
                        break;
                    }
                    i2++;
                }
            }
            try {
                c.finishBroadcast();
            } catch (IllegalStateException e3) {
                LogUtil.b("Track_TrackDataRemoteProxy", "finishBroadcast()", e3.getMessage());
            }
            if (eVar == null) {
                e eVar2 = new e(iTrackDataReport);
                this.g.d(eVar2);
                gtx.e(eVar2);
                this.g.c(eVar2);
                e(eVar2, "com.huawei.health.ADD_CALLBACK_IN_INDOOR_EQUIP");
                c.register(eVar2);
            } else {
                LogUtil.b("Track_TrackDataRemoteProxy", "registerDataCallback callBack is exist");
            }
        } else {
            LogUtil.h("Track_TrackDataRemoteProxy", "registerDataCallback callback is null");
        }
        int i3 = f3550a;
        if (i3 == 0 || i3 == 3) {
            return;
        }
        this.g.p();
    }

    @Override // com.huawei.health.ITrackSportManager
    public void unRegisterDataCallback(ITrackDataReport iTrackDataReport) throws RemoteException {
        int i;
        e eVar;
        LogUtil.a("Track_TrackDataRemoteProxy", "unRegisterDataCallback");
        if (iTrackDataReport != null) {
            int i2 = 0;
            try {
                i = c.beginBroadcast();
            } catch (IllegalStateException e2) {
                LogUtil.b("Track_TrackDataRemoteProxy", "beginBroadcast()", e2.getMessage());
                i = 0;
            }
            d(0);
            LogUtil.a("Track_TrackDataRemoteProxy", "unRegisterDataCallback:Report client count ", Integer.valueOf(i));
            while (true) {
                if (i2 >= i) {
                    eVar = null;
                    break;
                } else {
                    if (c.getBroadcastItem(i2).asBinder() == iTrackDataReport.asBinder()) {
                        eVar = c.getBroadcastItem(i2);
                        break;
                    }
                    i2++;
                }
            }
            try {
                c.finishBroadcast();
            } catch (IllegalStateException e3) {
                LogUtil.b("Track_TrackDataRemoteProxy", "finishBroadcast()", e3.getMessage());
            }
            if (eVar != null) {
                this.g.b((IMapViewListener) eVar);
                gtx.a(eVar);
                this.g.b((ISportStateChangeListener) eVar);
                e(eVar, "com.huawei.health.REMOVE_CALLBACK_IN_INDOOR_EQUIP");
                c.unregister(eVar);
                LogUtil.a("Track_TrackDataRemoteProxy", "Unregister the callback on service");
            }
        }
    }

    static class e implements IMapViewListener, ISportDataFragmentListener, ISportStateChangeListener, IInterface {
        private ITrackDataReport b;

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
        public void pauseSport(int i) {
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
        public void resumeSport(int i) {
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
        public void startSport() {
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
        public void stopSport(boolean z) {
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
        public void updateSportStatusWhenLockScreen(int i) {
        }

        e(ITrackDataReport iTrackDataReport) {
            this.b = iTrackDataReport;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.IMapViewListener
        public void updateGpsStatus(int i) {
            if (TrackDataRemoteProxy.d != null) {
                TrackDataRemoteProxy.d.a(i);
                if (this.b != null) {
                    try {
                        if (TrackDataRemoteProxy.d.aWH_() != null) {
                            this.b.report(TrackDataRemoteProxy.d.aWH_());
                            return;
                        }
                        return;
                    } catch (RemoteException e) {
                        LogUtil.b("Track_TrackDataRemoteProxy", "updateGpsStatus()", e.getMessage());
                        return;
                    }
                }
                return;
            }
            LogUtil.h("Track_TrackDataRemoteProxy", "updateGpsStatus mLogicalTrackData null");
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener
        public void updateSportViewFragment(Bundle bundle) {
            if (TrackDataRemoteProxy.d != null) {
                TrackDataRemoteProxy.d.aWI_(bundle);
                try {
                    Bundle aWH_ = TrackDataRemoteProxy.d.aWH_();
                    if (aWH_ != null) {
                        this.b.report(aWH_);
                    } else {
                        LogUtil.h("Track_TrackDataRemoteProxy", "updateSportViewFragment reportBundle is null ", bundle);
                    }
                    return;
                } catch (RemoteException e) {
                    LogUtil.b("Track_TrackDataRemoteProxy", "updateSportViewFragment()", e.getMessage());
                    return;
                }
            }
            LogUtil.h("Track_TrackDataRemoteProxy", "updateSportViewFragment mLogicalTrackData null");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            ITrackDataReport iTrackDataReport = this.b;
            if (iTrackDataReport != null) {
                return iTrackDataReport.asBinder();
            }
            return null;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onStartSport() {
            TrackDataRemoteProxy.d(1);
            if (TrackDataRemoteProxy.d != null) {
                TrackDataRemoteProxy.d.c(1);
                LogUtil.a("Track_TrackDataRemoteProxy", "LocalToRemoteProxy onStartSport");
                try {
                    if (TrackDataRemoteProxy.d.aWH_() != null) {
                        this.b.report(TrackDataRemoteProxy.d.aWH_());
                        return;
                    }
                    return;
                } catch (RemoteException e) {
                    LogUtil.b("Track_TrackDataRemoteProxy", "onStartSport()", e.getMessage());
                    return;
                }
            }
            LogUtil.h("Track_TrackDataRemoteProxy", "onStartSport mLogicalTrackData null");
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onPauseSport() {
            TrackDataRemoteProxy.d(2);
            if (TrackDataRemoteProxy.d != null) {
                TrackDataRemoteProxy.d.c(2);
                LogUtil.a("Track_TrackDataRemoteProxy", "LocalToRemoteProxy onPauseSport");
                try {
                    if (TrackDataRemoteProxy.d.aWH_() != null) {
                        this.b.report(TrackDataRemoteProxy.d.aWH_());
                        return;
                    }
                    return;
                } catch (RemoteException e) {
                    LogUtil.b("Track_TrackDataRemoteProxy", "onPauseSport()", e.getMessage());
                    return;
                }
            }
            LogUtil.h("Track_TrackDataRemoteProxy", "onPauseSport mLogicalTrackData null");
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onResumeSport() {
            TrackDataRemoteProxy.d(1);
            if (TrackDataRemoteProxy.d != null) {
                TrackDataRemoteProxy.d.c(1);
                LogUtil.a("Track_TrackDataRemoteProxy", "LocalToRemoteProxy onResumeSport");
                try {
                    if (TrackDataRemoteProxy.d.aWH_() != null) {
                        this.b.report(TrackDataRemoteProxy.d.aWH_());
                        return;
                    }
                    return;
                } catch (RemoteException e) {
                    LogUtil.b("Track_TrackDataRemoteProxy", "resumeSport()", e.getMessage());
                    return;
                }
            }
            LogUtil.h("Track_TrackDataRemoteProxy", "onResumeSport mLogicalTrackData null");
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onStopSport() {
            TrackDataRemoteProxy.d(3);
            if (TrackDataRemoteProxy.d != null) {
                TrackDataRemoteProxy.d.c(3);
                LogUtil.a("Track_TrackDataRemoteProxy", "LocalToRemoteProxy onStopSport");
                try {
                    if (TrackDataRemoteProxy.d.aWH_() != null) {
                        this.b.report(TrackDataRemoteProxy.d.aWH_());
                        return;
                    }
                    return;
                } catch (RemoteException e) {
                    LogUtil.b("Track_TrackDataRemoteProxy", "onStopSport()", e.getMessage());
                    return;
                }
            }
            LogUtil.h("Track_TrackDataRemoteProxy", "onStopSport mLogicalTrackData null");
        }
    }

    public void onServiceDestroy() {
        LogUtil.a("Track_TrackDataRemoteProxy", "onServiceDestroy");
        this.j = null;
        d(3);
        c.kill();
        this.g = null;
    }

    @Override // com.huawei.health.ITrackSportManager
    public void startSport() throws RemoteException {
        d(1);
        gyk gykVar = d;
        if (gykVar != null) {
            gykVar.c(1);
        }
        LogUtil.a("Track_TrackDataRemoteProxy", "TrackDataRemoteProxy startSport");
    }

    @Override // com.huawei.health.ITrackSportManager
    public void pauseSport() throws RemoteException {
        if (f3550a == 1) {
            d(2);
            this.g.c(false, 0);
        }
        LogUtil.a("Track_TrackDataRemoteProxy", "TrackDataRemoteProxy pauseSport");
    }

    @Override // com.huawei.health.ITrackSportManager
    public void stopSport() throws RemoteException {
        if (f3550a == 2) {
            d(3);
            this.g.bv();
        }
    }

    @Override // com.huawei.health.ITrackSportManager
    public void resumeSport() throws RemoteException {
        if (f3550a == 2) {
            d(1);
            this.g.bu();
        }
        LogUtil.a("Track_TrackDataRemoteProxy", "TrackDataRemoteProxy resumeSport");
    }

    public static class d implements LocalTrackDataReport {
        @Override // com.huawei.healthcloud.plugintrack.open.LocalTrackDataReport
        public void report(Bundle bundle) {
            int i;
            try {
                i = TrackDataRemoteProxy.c.beginBroadcast();
            } catch (IllegalStateException e) {
                LogUtil.b("Track_TrackDataRemoteProxy", "beginBroadcast()", e.getMessage());
                i = 0;
            }
            LogUtil.a("Track_TrackDataRemoteProxy", "report:Report client count ", Integer.valueOf(i));
            if (bundle == null) {
                return;
            }
            for (int i2 = 0; i2 < i; i2++) {
                try {
                    ((e) TrackDataRemoteProxy.c.getBroadcastItem(i2)).b.report(bundle);
                } catch (RemoteException e2) {
                    LogUtil.b("Track_TrackDataRemoteProxy", "report()", e2.getMessage());
                }
            }
            try {
                TrackDataRemoteProxy.c.finishBroadcast();
            } catch (IllegalStateException e3) {
                LogUtil.b("Track_TrackDataRemoteProxy", "finishBroadcast()", e3.getMessage());
            }
        }
    }

    private void e(e eVar, String str) {
        Intent intent = new Intent(str);
        LogUtil.a("Track_TrackDataRemoteProxy", "send LocalBroadcast action is ", str);
        e = eVar;
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).sendBroadcast(intent);
    }
}
