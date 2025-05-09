package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.RecordData;

/* loaded from: classes3.dex */
public class epv implements RecordData {

    /* renamed from: a, reason: collision with root package name */
    private long f12152a;

    @SerializedName("actualCalorie")
    private float b;
    private long c;

    @SerializedName("actualDistance")
    private int d;
    private String e;
    private String f;
    private String g;
    private int h;
    private long i;
    private int j;

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public String getWorkoutId() {
        return this.f;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public String getRecordId() {
        return this.g;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public int getSportType() {
        return this.j;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public long getStartTime() {
        return this.i;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public long getEndTime() {
        return this.f12152a;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public long getDuration() {
        return this.c;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public int getActualDistance() {
        return this.d;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public float getActualCalorie() {
        return this.b;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public int getIsInPlan() {
        return this.h;
    }

    @Override // com.huawei.basefitnessadvice.model.intplan.RecordData
    public String getExtraInfo() {
        return this.e;
    }

    public void a(String str) {
        this.f = str;
    }

    public void b(String str) {
        this.g = str;
    }

    public void a(int i) {
        this.j = i;
    }

    public void a(long j) {
        this.i = j;
    }

    public void b(long j) {
        this.f12152a = j;
    }

    public void d(long j) {
        this.c = j;
    }

    public void d(int i) {
        this.d = i;
    }

    public void e(float f) {
        this.b = f;
    }

    public void e(int i) {
        this.h = i;
    }

    public void e(String str) {
        this.e = str;
    }

    public epv c(mob mobVar) {
        if (mobVar != null) {
            a(mobVar.n());
            b(mobVar.j());
            a(mobVar.f());
            a(mobVar.i());
            b(mobVar.a());
            d(mobVar.c());
            d(mobVar.e());
            e(mobVar.b());
            e(mobVar.h());
            e(mobVar.g());
        }
        return this;
    }
}
