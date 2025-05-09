package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class aui extends auf {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("startTime")
    private long f241a;

    @SerializedName("endTime")
    private long b;

    @SerializedName("version")
    private long c;

    @Override // defpackage.auf, com.huawei.networkclient.IRequest
    public String getUrl() {
        return null;
    }

    public void b(long j) {
        this.c = j;
    }

    public void d(long j) {
        this.f241a = j;
    }

    public void c(long j) {
        this.b = j;
    }
}
