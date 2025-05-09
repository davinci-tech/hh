package defpackage;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.model.IHeartRateCallback;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gtq {
    private PluginSportTrackAdapter k;
    private ArrayList<HeartRateData> c = new ArrayList<>(Collections.EMPTY_LIST);
    private ArrayList<HeartRateData> l = new ArrayList<>(Collections.EMPTY_LIST);
    private int e = -1;
    private boolean n = false;

    /* renamed from: a, reason: collision with root package name */
    private HeartRateData f12930a = null;
    private int s = 0;
    private boolean m = false;
    private StringBuilder d = new StringBuilder(4);
    private int i = 0;
    private e b = null;
    private int g = -1;
    private long f = 0;
    private long j = 0;
    private long h = 0;
    private ArrayList<HeartRateData> o = new ArrayList<>(Collections.EMPTY_LIST);
    private ArrayList<HeartRateData> p = new ArrayList<>(Collections.EMPTY_LIST);

    public gtq(PluginSportTrackAdapter pluginSportTrackAdapter) {
        this.k = null;
        if (pluginSportTrackAdapter == null) {
            LogUtil.h("Track_HeartRateUtils", "HeartRateUtils adapter is null");
        } else {
            this.k = pluginSportTrackAdapter;
        }
    }

    public long c() {
        return this.f;
    }

    public HeartRateData e() {
        return this.f12930a;
    }

    public void b(int i) {
        this.s = i;
    }

    public void c(int i, long j) {
        if (j - this.f >= TimeUnit.SECONDS.toMillis(2L) && gvv.b(i)) {
            if (hab.g() && hab.i()) {
                hac.a().b.add(Integer.valueOf(i));
                hac.a().f13038a.add(Integer.valueOf(i));
                hac.a().d = i;
            }
            this.e = i;
            this.m = true;
            StringBuilder sb = this.d;
            sb.append(gwg.c(j));
            sb.append(":");
            sb.append(i);
            sb.append(";");
            this.f = j;
        }
        if (d(i)) {
            this.e = 0;
        }
        this.h = j;
        if (gvv.b(i)) {
            this.g = i;
            return;
        }
        this.g = -1;
        if (j - this.j >= TimeUnit.SECONDS.toMillis(60L)) {
            ReleaseLogUtil.e("Track_HeartRateUtils", "setHeartRateData heartRate = ", -1);
            this.j = j;
        }
    }

    public int d() {
        return this.e;
    }

    public void i() {
        LogUtil.a("Track_HeartRateUtils", "HeartRateUtils startHeartHandler");
        if (this.b == null) {
            this.b = new e(Looper.getMainLooper(), this);
        }
    }

    static class e extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<gtq> f12931a;

        e(Looper looper, gtq gtqVar) {
            super(looper);
            this.f12931a = new WeakReference<>(gtqVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("Track_HeartRateUtils", "Heart Rate Message is null");
                return;
            }
            super.handleMessage(message);
            LogUtil.a("Track_HeartRateUtils", "Heart Rate Handler handleMessage", Integer.valueOf(message.what));
            gtq gtqVar = this.f12931a.get();
            if (gtqVar == null) {
                LogUtil.a("Track_HeartRateUtils", "Heart Rate Handler manager is null");
                return;
            }
            if (message.what == 1 && (message.obj instanceof ContentValues)) {
                ContentValues contentValues = (ContentValues) message.obj;
                long longValue = ((Long) contentValues.get("time")).longValue();
                int intValue = ((Integer) contentValues.get("heartrate")).intValue();
                boolean booleanValue = ((Boolean) contentValues.get("isinvalidheartrate")).booleanValue();
                HeartRateData heartRateData = new HeartRateData();
                heartRateData.saveTime(longValue);
                heartRateData.saveHeartRate(intValue);
                if (booleanValue) {
                    gtqVar.d(heartRateData);
                } else {
                    gtqVar.f12930a = heartRateData;
                    gtqVar.c(heartRateData);
                }
            }
        }
    }

    public void d(PluginSportTrackAdapter pluginSportTrackAdapter) {
        if (pluginSportTrackAdapter == null) {
            LogUtil.h("Track_HeartRateUtils", "setAdapter adapter is null");
        } else {
            this.k = pluginSportTrackAdapter;
        }
    }

    public void g() {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.k;
        if (pluginSportTrackAdapter == null) {
            LogUtil.b("Track_HeartRateUtils", "startGetHeartRateData mPluginTrackAdapter is null!");
        } else {
            pluginSportTrackAdapter.startHeartDeviceMeasure();
        }
    }

    void b() {
        if (gwg.i(this.s)) {
            LogUtil.a("Track_HeartRateUtils", "registerHeartRate begin");
            PluginSportTrackAdapter pluginSportTrackAdapter = this.k;
            if (pluginSportTrackAdapter == null) {
                LogUtil.b("Track_HeartRateUtils", "registerHeartRate mPluginTrackAdapter is null!");
            } else {
                pluginSportTrackAdapter.regHeartRateListener(new b(), 15);
                this.n = true;
            }
        }
    }

    class b implements IHeartRateCallback {
        @Override // com.huawei.healthcloud.plugintrack.model.IHeartRateCallback
        public void onResult(int i) {
        }

        private b() {
        }

        @Override // com.huawei.healthcloud.plugintrack.model.IHeartRateCallback
        public void onChange(int i, long j) {
            gtq.this.c(i, j);
        }
    }

    private boolean d(int i) {
        return i == 0 && this.e != -1;
    }

    public void a() {
        PluginSportTrackAdapter pluginSportTrackAdapter;
        if (this.n) {
            if (gwg.i(this.s) && (pluginSportTrackAdapter = this.k) != null) {
                pluginSportTrackAdapter.unregHeartRateListener();
            }
            this.n = false;
        }
    }

    void h() {
        PluginSportTrackAdapter pluginSportTrackAdapter;
        if (!gwg.i(this.s) || (pluginSportTrackAdapter = this.k) == null) {
            return;
        }
        pluginSportTrackAdapter.stopHeartDeviceMeasure();
        LogUtil.a("Track_HeartRateUtils", "stopHeartRateDevice");
    }

    public void e(int i) {
        if (i == 2) {
            this.e = 0;
            this.f = System.currentTimeMillis();
        }
    }

    public Bundle aTD_(Bundle bundle) {
        j();
        if (bundle == null) {
            LogUtil.a("Track_HeartRateUtils", "sportData is null");
            return null;
        }
        int i = this.e;
        if (i != -1) {
            bundle.putString(IndoorEquipManagerApi.KEY_HEART_RATE, String.valueOf(i));
        } else {
            bundle.putString(IndoorEquipManagerApi.KEY_HEART_RATE, "");
        }
        return bundle;
    }

    private void j() {
        if (this.f == 0 || this.e == -1 || System.currentTimeMillis() - this.f <= TimeUnit.SECONDS.toMillis(10L)) {
            return;
        }
        this.e = 0;
    }

    public void f() {
        int i = this.i + 1;
        this.i = i;
        if (i == 5) {
            this.i = 0;
            LogUtil.a("Track_HeartRateUtils", "saveHeartRate mHeartRateStartTime = ", Long.valueOf(this.f));
            long currentTimeMillis = System.currentTimeMillis() - this.h;
            boolean z = currentTimeMillis >= 20000;
            if (this.g == -1) {
                z = true;
            }
            ContentValues contentValues = new ContentValues();
            if (z) {
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            } else if (currentTimeMillis > 5000) {
                contentValues.put("time", Long.valueOf(this.h + ((currentTimeMillis / 5000) * 5000)));
            } else {
                contentValues.put("time", Long.valueOf(this.h));
            }
            contentValues.put("heartrate", Integer.valueOf(this.g));
            contentValues.put("isinvalidheartrate", Boolean.valueOf(z));
            e(1, 0, 0, contentValues);
        }
    }

    private void e(int i, int i2, int i3, Object obj) {
        e eVar = this.b;
        if (eVar != null) {
            this.b.sendMessage(eVar.obtainMessage(i, i2, i3, obj));
        } else {
            LogUtil.a("Track_HeartRateUtils", "postRunnableToHandlerThread() mHandler is null");
        }
    }

    public void d(List<HeartRateData> list, List<HeartRateData> list2) {
        LogUtil.h("Track_HeartRateUtils", "recoveryHeartRateList");
        this.c.clear();
        this.l.clear();
        if (list != null && list.size() > 0) {
            this.c.addAll(list);
        }
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        this.l.addAll(list2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HeartRateData heartRateData) {
        if (!this.c.contains(heartRateData)) {
            this.c.add(heartRateData);
        }
        if (this.o.contains(heartRateData)) {
            return;
        }
        this.o.add(heartRateData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HeartRateData heartRateData) {
        if (!this.l.contains(heartRateData)) {
            this.l.add(heartRateData);
        }
        if (this.p.contains(heartRateData)) {
            return;
        }
        this.p.add(heartRateData);
    }

    public ArrayList<HeartRateData> d(boolean z) {
        ArrayList<HeartRateData> arrayList = new ArrayList<>(Collections.EMPTY_LIST);
        if (this.o.size() > 0) {
            arrayList.addAll(this.o);
            if (z) {
                this.o.clear();
            }
        }
        return arrayList;
    }

    public ArrayList<HeartRateData> b(boolean z) {
        ArrayList<HeartRateData> arrayList = new ArrayList<>(Collections.EMPTY_LIST);
        if (this.p.size() > 0) {
            arrayList.addAll(this.p);
            if (z) {
                this.p.clear();
            }
        }
        return arrayList;
    }
}
