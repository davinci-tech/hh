package defpackage;

import android.text.TextUtils;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fer {

    /* renamed from: a, reason: collision with root package name */
    private float f12474a;
    private float b;
    private ffd c;
    private float d;
    private float e;
    private final Map<String, ITargetUpdateListener> f = Collections.synchronizedMap(new HashMap());
    private boolean g = false;
    private String h = Integer.toString(20002);
    private StorageParams i = new StorageParams();
    private List<ffd> j;

    public void e(List<ffd> list) {
        LogUtil.a("Track_TargetManager", "initTargetList ");
        if (list == null || list.isEmpty()) {
            LogUtil.h("Track_TargetManager", "initTargetList input is null or empty");
            return;
        }
        this.j = list;
        SharedPreferenceManager.e(BaseApplication.getContext(), this.h, "sport_plan_target_new", CommonUtil.d(this.j), this.i);
        if (this.j.size() > 0) {
            this.c = this.j.get(0);
        }
    }

    public void a() {
        List<ffd> list = this.j;
        if (list != null && list.size() > 0) {
            this.j.remove(0);
            if (this.j.size() > 0) {
                int f = this.c.f();
                ffd ffdVar = this.j.get(0);
                this.c = ffdVar;
                if (f != ffdVar.f()) {
                    LogUtil.a("Track_TargetManager", "updateNewTargetValue() changeType");
                }
                j();
                if (this.c.f() == 200) {
                    f();
                } else {
                    d(201, "");
                }
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "sport_plan_target_new", CommonUtil.d(this.j), new StorageParams());
            } else {
                f();
            }
        } else {
            f();
        }
        LogUtil.a("Track_TargetManager", "updateNewTargetValue ");
    }

    private void f() {
        this.g = true;
        d(200, "");
    }

    private void j() {
        ffd ffdVar = this.c;
        if (ffdVar != null) {
            int f = ffdVar.f();
            if (f != 0) {
                if (f == 1) {
                    this.c.a(this.d);
                } else if (f == 2) {
                    this.c.a(this.b * 1000.0f);
                } else if (f == 4 || f == 5) {
                    this.c.a(this.e);
                } else if (f != 255) {
                    LogUtil.b("Track_TargetManager", "Wrong target");
                }
            }
            this.c.a(this.f12474a);
        }
        LogUtil.a("Track_TargetManager", "setSportCountdownTypeValue ");
    }

    private float h() {
        ffd ffdVar = this.c;
        if (ffdVar == null) {
            return 0.0f;
        }
        int f = ffdVar.f();
        if (f != 0) {
            if (f == 1) {
                this.c.d(this.d);
            } else if (f == 2) {
                this.c.d(this.b * 1000.0f);
            } else if (f == 4) {
                this.c.b(this.e, 4);
            } else if (f == 5) {
                this.c.b(this.e, 5);
            } else if (f != 255) {
                return 0.0f;
            }
            return this.c.a();
        }
        this.c.e(this.f12474a);
        return this.c.a();
    }

    public void b(float f, long j, float f2, int i) {
        this.d = f;
        this.b = f2;
        this.f12474a = j;
        this.e = i;
        if (this.g) {
            return;
        }
        float h = h();
        g();
        if (h >= 1.0f) {
            a();
        }
    }

    private void i() {
        ffd ffdVar = this.c;
        if (ffdVar != null) {
            int f = ffdVar.f();
            if (f == 0) {
                this.c.b(this.f12474a);
            } else if (f == 1) {
                this.c.b(this.d);
            } else {
                if (f != 2) {
                    return;
                }
                this.c.b(this.b * 1000.0f * 1.0f);
            }
        }
    }

    private void d(int i, String str) {
        Iterator<ITargetUpdateListener> it = this.f.values().iterator();
        while (it.hasNext()) {
            it.next().onStateUpdate(i, str);
        }
    }

    private void g() {
        Iterator<ITargetUpdateListener> it = this.f.values().iterator();
        while (it.hasNext()) {
            it.next().onTargetDataUpdate(this.c);
        }
    }

    public List<ffd> d() {
        return this.j;
    }

    public ffd b() {
        return this.c;
    }

    public void c(String str, ITargetUpdateListener iTargetUpdateListener) {
        if (TextUtils.isEmpty(str) || iTargetUpdateListener == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "registerTargetUpdateListener failed. tag: ";
            objArr[1] = str;
            objArr[2] = ". listener is null: ";
            objArr[3] = Boolean.valueOf(iTargetUpdateListener == null);
            LogUtil.b("Track_TargetManager", objArr);
            return;
        }
        LogUtil.a("Track_TargetManager", "registerTargetUpdateListener success. tag: ", str);
        this.f.put(str, iTargetUpdateListener);
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str) || !this.f.containsKey(str)) {
            LogUtil.b("Track_TargetManager", "unregisterTargetUpdateListener failed: tag is empty or tag not in map. tag: ", str);
        } else {
            LogUtil.a("Track_TargetManager", "unregisterTargetUpdateListener success. tag: ", str);
            this.f.remove(str);
        }
    }

    public float c() {
        if (this.c == null) {
            return 0.0f;
        }
        i();
        return this.c.e();
    }

    public float e() {
        ffd ffdVar = this.c;
        if (ffdVar != null) {
            return ffdVar.a();
        }
        return 0.0f;
    }
}
