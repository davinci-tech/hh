package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class jvb {
    private int c;
    private DeviceInfo d;
    private boolean f;
    private int g;
    private boolean j;
    private int k;
    private int l;
    private long o = OpAnalyticsConstants.H5_LOADING_DELAY;
    private long h = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f14111a = 1;
    private int i = -1;
    private List<String> b = new CopyOnWriteArrayList();
    private List<String> e = new ArrayList(16);

    public DeviceInfo c() {
        return this.d;
    }

    public void b(DeviceInfo deviceInfo) {
        this.d = deviceInfo;
    }

    public long k() {
        return this.o;
    }

    public void c(long j) {
        this.o = j;
    }

    public long i() {
        return this.h;
    }

    public void d(long j) {
        this.h = j;
    }

    public int f() {
        return this.k;
    }

    public void e(int i) {
        this.k = i;
    }

    public List<String> e() {
        return this.e;
    }

    public int b() {
        return this.f14111a;
    }

    public void d(int i) {
        this.f14111a = i;
    }

    public int h() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public boolean n() {
        return this.j;
    }

    public void d(boolean z) {
        this.j = z;
    }

    public boolean l() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int g() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public List<String> d() {
        return this.b;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwEphemerisManager_EphemerisDownloadInfo", "addDownloadFileName is empty");
            return;
        }
        if (this.i == 2 && "RXN_EE".equals(str)) {
            this.b.add("rxn_ee_gps");
            this.b.add("rxn_ee_gal");
            this.b.add("rxn_ee_bds");
            return;
        }
        this.b.add(str);
    }

    public int j() {
        return this.l;
    }

    public void f(int i) {
        this.l = i;
    }
}
