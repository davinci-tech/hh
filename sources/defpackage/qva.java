package defpackage;

import com.google.gson.annotations.SerializedName;
import defpackage.qvf;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class qva {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("fastingLiteMode")
    private quv f16603a;

    @SerializedName("endTime")
    private long b;

    @SerializedName("startTime")
    private long c;

    @SerializedName("fastingLitePhases")
    private List<a> d;

    @SerializedName("note")
    private String e;

    public qva(qvf qvfVar) {
        this.e = "";
        this.f16603a = qvfVar.h();
        this.c = qvfVar.m();
        this.b = qvfVar.i();
        this.e = qvfVar.j();
        if (this.d == null) {
            this.d = new ArrayList();
        }
    }

    public quv a() {
        return this.f16603a;
    }

    public long d() {
        return this.c;
    }

    public long b() {
        return this.b;
    }

    public void e(String str) {
        this.e = str;
    }

    public List<a> e() {
        return this.d;
    }

    public void h() {
        this.c /= 1000;
        this.b /= 1000;
        if (koq.b(this.d)) {
            return;
        }
        for (a aVar : this.d) {
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    public void c() {
        a aVar;
        long j = this.b;
        if (j <= 0 || j > System.currentTimeMillis()) {
            this.b = System.currentTimeMillis();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.d.size() && (aVar = this.d.get(i)) != null && aVar.b < this.b; i++) {
            if (aVar.c > 0 && aVar.c <= this.b) {
                arrayList.add(aVar);
            } else {
                aVar.c = this.b;
                arrayList.add(aVar);
                break;
            }
        }
        this.d = arrayList;
    }

    public void c(List<qvf.d> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return;
        }
        for (qvf.d dVar : list) {
            if (dVar != null) {
                arrayList.add(new a(dVar));
            }
        }
        this.d = arrayList;
    }

    public String toString() {
        return "FastingLiteRecord{mFastingLiteMode=" + this.f16603a + ", mStartTime=" + this.c + ", mEndTime=" + this.b + ", mNote='" + this.e + "', mfastingLitePhases=" + this.d + '}';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class a {

        @SerializedName("startTime")
        private long b;

        @SerializedName("endTime")
        private long c;

        @SerializedName("recordId")
        private String d;

        @SerializedName("isEating")
        private boolean e;

        private a(qvf.d dVar) {
            this.b = dVar.f();
            this.c = dVar.c();
            this.e = dVar.g();
            this.d = dVar.b() == 0 ? "" : String.valueOf(dVar.b());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.b /= 1000;
            this.c /= 1000;
        }

        public String toString() {
            return "FastingLitePhase{mIsEating=" + this.e + ", mStartTime=" + this.b + ", mEndTime=" + this.c + ", mRecordId=" + this.d + '}';
        }
    }
}
