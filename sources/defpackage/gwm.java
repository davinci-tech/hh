package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class gwm {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private IRidePostureDataCallback f12972a;
    private final Context d;
    private int e;
    private PluginSportTrackAdapter h;
    private ExtendHandler j;
    private boolean g = false;
    private int o = 0;
    private int l = 0;
    private ArrayList<ffn> m = new ArrayList<>(16);
    private CopyOnWriteArrayList<ffn> k = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ffn> n = new CopyOnWriteArrayList<>();
    private int f = 0;
    private long b = 0;
    private ffn i = new ffn();

    public gwm(PluginSportTrackAdapter pluginSportTrackAdapter, Context context) {
        this.h = null;
        this.h = pluginSportTrackAdapter;
        this.d = context;
    }

    public void e(int i) {
        this.l = i;
    }

    public void d(int i) {
        this.o = i;
    }

    public void d(PluginSportTrackAdapter pluginSportTrackAdapter) {
        this.h = pluginSportTrackAdapter;
    }

    public void c(List<ffn> list) {
        LogUtil.a("Track_RidePostureUtils", "recoveryCadenceDataList");
        this.m.clear();
        if (koq.c(list)) {
            this.m.addAll(list);
        }
    }

    public void d() {
        ffn ffnVar;
        LogUtil.a("Track_RidePostureUtils", "recoveryCadenceDataList");
        this.n.clear();
        CopyOnWriteArrayList<ffn> copyOnWriteArrayList = (CopyOnWriteArrayList) gvv.a(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "saved_temp_cadence"), new TypeToken<CopyOnWriteArrayList<ffn>>() { // from class: gwm.4
        });
        if (koq.c(copyOnWriteArrayList)) {
            this.n = copyOnWriteArrayList;
        } else {
            LogUtil.h("Track_RidePostureUtils", "RidePostureData is empty");
        }
        if (koq.c(this.n)) {
            this.f = this.n.size() - 1;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "saved_maximum_cadence");
        if (TextUtils.isEmpty(b) || (ffnVar = (ffn) gvv.a(b, new TypeToken<ffn>() { // from class: gwm.2
        })) == null || ffnVar.e() == 0) {
            return;
        }
        this.i = ffnVar;
    }

    public void a(IRidePostureDataCallback iRidePostureDataCallback) {
        if (gwg.i(this.o) && this.l == 259) {
            LogUtil.a("Track_RidePostureUtils", "registerRidePosture begin");
            if (this.h == null) {
                LogUtil.b("Track_RidePostureUtils", "registerRidePosture mPluginSportTrackAdapter is null!");
                return;
            }
            this.f12972a = iRidePostureDataCallback;
            i();
            this.h.registerRidePosture(new IRidePostureDataCallback() { // from class: gwm.1
                @Override // com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback
                public void onResult() {
                }

                @Override // com.huawei.healthcloud.plugintrack.model.IRidePostureDataCallback
                public void onChange(ffn ffnVar) {
                    if (ffnVar != null) {
                        gwm.this.a(ffnVar);
                    } else {
                        LogUtil.h("Track_RidePostureUtils", "ridePostureData is null");
                    }
                }
            });
            this.g = true;
            return;
        }
        LogUtil.a("Track_RidePostureUtils", "registerRidePosture not support ridePosture");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ffn ffnVar) {
        if (this.j == null) {
            LogUtil.b("Track_RidePostureUtils", "sendRidePostureDataToThread ");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = ffnVar;
        this.j.sendMessage(obtain);
    }

    private void i() {
        LogUtil.a("Track_RidePostureUtils", "startHandlerThread enter");
        if (this.j == null) {
            this.j = HandlerCenter.yt_(new c(this), "CADENCE_HANDLER_THREAD");
        }
    }

    private void g() {
        LogUtil.a("Track_RidePostureUtils", "stopHandlerThread enter");
        ExtendHandler extendHandler = this.j;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.j.quit(false);
            this.j = null;
        }
    }

    public Bundle aUV_(Bundle bundle) {
        if (this.b != 0 && this.e != 0 && System.currentTimeMillis() - this.b > 20000) {
            this.e = 0;
        }
        if (bundle == null) {
            LogUtil.a("Track_RidePostureUtils", "sportData is null");
            return new Bundle();
        }
        int i = this.e;
        if (i != 0) {
            bundle.putString("cadenceData", String.valueOf(i));
        } else {
            bundle.putString("cadenceData", "");
        }
        return bundle;
    }

    private void c(ffn ffnVar) {
        IRidePostureDataCallback iRidePostureDataCallback;
        if (ffnVar.e() != -1) {
            this.f++;
            this.e = ffnVar.e();
            this.n.add(ffnVar);
        }
        if (ffnVar.b() == -1 || (iRidePostureDataCallback = this.f12972a) == null) {
            return;
        }
        iRidePostureDataCallback.onChange(ffnVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ffn ffnVar) {
        LogUtil.a("Track_RidePostureUtils", "enter updateRidePosture,", Long.valueOf(ffnVar.acquireTime()));
        c(ffnVar);
        if (koq.c(this.n)) {
            if (ffnVar.acquireTime() - this.n.get(r2.size() - 1).acquireTime() > 20000) {
                e();
            }
        }
        if (this.i.e() < ffnVar.e()) {
            this.i.e(ffnVar.e());
            this.i.c(ffnVar.acquireTime());
            if (this.i.e() != 0) {
                b();
            }
        }
        if (this.f == 5) {
            e();
        }
    }

    private void e() {
        synchronized (c) {
            if (koq.b(this.n)) {
                LogUtil.h("Track_RidePostureUtils", "mSaveCadenceDataBuffer is empty");
                return;
            }
            this.f = 0;
            ffn ffnVar = new ffn(this.n.get(0).acquireTime(), a(this.n));
            this.n.clear();
            if (!this.m.contains(ffnVar)) {
                this.m.add(ffnVar);
            }
            if (!this.k.contains(ffnVar)) {
                this.k.add(ffnVar);
            }
        }
    }

    public void c() {
        PluginSportTrackAdapter pluginSportTrackAdapter;
        if (this.g) {
            e();
            if (gwg.i(this.o) && (pluginSportTrackAdapter = this.h) != null) {
                pluginSportTrackAdapter.unregisterRidePosture();
            }
            this.g = false;
            g();
        }
    }

    public ArrayList<ffn> b(boolean z) {
        ArrayList<ffn> arrayList;
        synchronized (c) {
            arrayList = new ArrayList<>(16);
            if (koq.c(this.k)) {
                arrayList.addAll(this.k);
                if (z) {
                    this.k.clear();
                }
            }
        }
        return arrayList;
    }

    public void d(List<ffn> list) {
        if (koq.b(list)) {
            return;
        }
        LogUtil.a("Track_RidePostureUtils", "enter dealMaxCadence");
        long acquireTime = this.i.acquireTime();
        for (ffn ffnVar : list) {
            if (ffnVar != null && ffnVar.acquireTime() + 4000 >= acquireTime) {
                ffnVar.e(this.i.e());
                LogUtil.a("Track_RidePostureUtils", "after CadenceList");
                return;
            }
        }
        if (list.get(list.size() - 1) == null || list.get(list.size() - 1).acquireTime() >= acquireTime) {
            return;
        }
        list.get(list.size() - 1).e(this.i.e());
        LogUtil.a("Track_RidePostureUtils", "after end CadenceList");
    }

    public void a() {
        SharedPreferenceManager.e(this.d, Integer.toString(20002), "saved_temp_cadence", new Gson().toJson(this.n), new StorageParams());
    }

    public void b() {
        SharedPreferenceManager.e(this.d, Integer.toString(20002), "saved_maximum_cadence", new Gson().toJson(this.i), (StorageParams) null);
    }

    private int a(List<ffn> list) {
        int i = 0;
        if (koq.b(list)) {
            LogUtil.h("Track_RidePostureUtils", "getAverageCadence cadenceDataList is empty");
            return 0;
        }
        for (ffn ffnVar : list) {
            if (ffnVar != null) {
                i += ffnVar.e();
            }
        }
        return (int) Math.ceil(i / list.size());
    }

    static class c extends BaseHandlerCallback<gwm> {
        public c(gwm gwmVar) {
            super(gwmVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: aUW_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(gwm gwmVar, Message message) {
            if (message.what != 1) {
                return false;
            }
            ffn ffnVar = (ffn) message.obj;
            ffnVar.c((ffnVar.acquireTime() - System.currentTimeMillis()) + gtx.c(gwmVar.d).getSportDuration());
            gwmVar.d(ffnVar);
            gwmVar.b = System.currentTimeMillis();
            return true;
        }
    }

    public static List<String> b(List<ffn> list) {
        ArrayList arrayList = new ArrayList(16);
        StringBuffer stringBuffer = new StringBuffer(16);
        if (koq.b(list)) {
            return arrayList;
        }
        for (ffn ffnVar : list) {
            stringBuffer.append(MotionPath.CADENCE_TAG).append(MotionPath.CONTENT_KEY).append(ffnVar.acquireTime()).append(";").append(MotionPath.CONTENT_VALUE).append(ffnVar.e()).append(";").append(System.lineSeparator());
            arrayList.add(stringBuffer.toString());
            stringBuffer.setLength(0);
        }
        return arrayList;
    }
}
