package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;

/* loaded from: classes.dex */
public class brh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("examinationInstitution")
    private String f480a;

    @SerializedName("isUpdateHealthReport")
    private boolean b;

    @SerializedName("examinationId")
    private int c;

    @SerializedName("examinationDate")
    private long d;

    @SerializedName("recordName")
    private String e;

    @SerializedName("reminderTime")
    private long f;

    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private String g;

    @SerializedName("remarks")
    private String j;

    public long a() {
        return this.d;
    }

    public String c() {
        return this.f480a;
    }

    public String e() {
        return this.e;
    }

    public long i() {
        return this.f;
    }

    public String b() {
        return this.j;
    }

    public int d() {
        return this.c;
    }

    public String j() {
        return this.g;
    }

    public boolean h() {
        return this.b;
    }
}
