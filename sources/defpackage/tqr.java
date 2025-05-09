package defpackage;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wearengine.sensor.AsyncReadCallback;
import com.huawei.wearengine.sensor.DataResult;
import com.huawei.wearengine.sensor.Sensor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class tqr {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17351a;
    private String b;
    private Map<String, tqt> d = new ConcurrentHashMap(16);
    private int e;

    public tqr(String str, int i) {
        this.b = str;
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public void b(String str, Sensor sensor, AsyncReadCallback asyncReadCallback) {
        if (TextUtils.isEmpty(str)) {
            tos.e("AsyncReadGroup", "addCallback packageName is null or empty");
            return;
        }
        if (sensor == null) {
            tos.e("AsyncReadGroup", "addCallback sensor is null");
            return;
        }
        if (asyncReadCallback == null) {
            tos.e("AsyncReadGroup", "addCallback asyncReadCallback is null");
            return;
        }
        List<tqq> supportFrequencyList = sensor.getSupportFrequencyList();
        int a2 = tsg.a(sensor.getExtendJson());
        tqq tqqVar = null;
        tqq tqqVar2 = null;
        for (tqq tqqVar3 : supportFrequencyList) {
            if (tqqVar3.d() == this.e) {
                tqqVar2 = tqqVar3;
            }
            if (tqqVar3.d() == a2) {
                tqqVar = tqqVar3;
            }
        }
        if (tqqVar == null || tqqVar2 == null) {
            tos.e("AsyncReadGroup", "addCallback sensor frequency or deviceFrequency is null");
            return;
        }
        tos.a("AsyncReadGroup", "addCallback success");
        String c = c(str);
        this.d.put(c, new tqt(tqqVar2, tqqVar, asyncReadCallback, c));
    }

    public void d(String str) {
        tos.a("AsyncReadGroup", "enter deleteCallback");
        String c = c(str);
        tqt tqtVar = this.d.get(c);
        if (tqtVar != null) {
            tqtVar.a();
        }
        this.d.remove(c);
    }

    private String c(String str) {
        return this.b + Constants.LINK + str;
    }

    public void a(int i, DataResult dataResult) {
        for (Map.Entry<String, tqt> entry : this.d.entrySet()) {
            if (entry.getValue() != null) {
                tos.a("AsyncReadGroup", "AsyncReadGroup handlerData");
                entry.getValue().c(i, dataResult);
            }
        }
    }

    public void c(tqq tqqVar) {
        if (tqqVar == null) {
            tos.e("AsyncReadGroup", " updateCurrentFrequency deviceFqu is null");
            return;
        }
        for (Map.Entry<String, tqt> entry : this.d.entrySet()) {
            if (entry.getValue() != null) {
                tos.a("AsyncReadGroup", " updateCurrentFrequency");
                entry.getValue().a();
                entry.getValue().c(tqqVar);
            }
        }
        this.e = tqqVar.d();
    }

    public int e() {
        return this.d.size();
    }

    public boolean a(tqq tqqVar) {
        tos.a("AsyncReadGroup", "enter isNeedDownFrequency");
        if (tqqVar == null) {
            tos.e("AsyncReadGroup", " isNeedDownFrequency deviceFqu is null");
            return false;
        }
        for (Map.Entry<String, tqt> entry : this.d.entrySet()) {
            if (entry.getValue() != null && entry.getValue().e() != null && entry.getValue().e().d() < tqqVar.d()) {
                tos.a("AsyncReadGroup", "not need down frequency");
                return false;
            }
        }
        return true;
    }

    public boolean c() {
        return this.f17351a;
    }

    public void b(boolean z) {
        this.f17351a = z;
    }
}
