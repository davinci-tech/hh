package defpackage;

import com.huawei.health.sportservice.SportLifecycle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class gxv implements SportLifecycle {
    private hiy b;
    private hiy[] d;

    /* renamed from: a, reason: collision with root package name */
    private List<hiy> f12997a = new ArrayList();
    private int e = 0;
    private boolean c = false;

    public void a(hiy hiyVar) {
        if (hiyVar == null) {
            return;
        }
        if (this.c) {
            this.f12997a.clear();
            hiy hiyVar2 = this.b;
            if (hiyVar2 != null) {
                this.d = new hiy[]{hiyVar2, hiyVar};
            }
            this.c = false;
        } else {
            this.d = null;
        }
        this.f12997a.add(hiyVar);
        this.b = hiyVar;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        this.e = 1;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        if (this.e != 1) {
            this.e = 1;
            this.c = true;
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        if (this.e != 2) {
            this.e = 2;
            this.c = false;
        }
    }

    public List<hiy> a() {
        return this.f12997a;
    }

    public hiy[] b() {
        return this.d;
    }

    public String toString() {
        return "TrackDrawGpsData{mBasePointList=" + this.f12997a.size() + ", mLastSportStatus=" + this.e + ", mLastPoint=" + this.b + ", mPauseLine=" + Arrays.toString(this.d) + ", mIsResumeSport=" + this.c + '}';
    }
}
