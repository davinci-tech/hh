package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.BeaconStatus;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class b extends BeaconStatus.Filter {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.huawei.hms.kit.awareness.a.a.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i2) {
            return new b[i2];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final int f4796a = 64;
    private static final int b = 32;
    private static final int c = 1024;
    public static final String g = "__BEACON__ID__";
    public static final String h = "!@#$%^&*()__!@#$%^&*()__!@#$%^&*()";
    private static final String i = "#";
    private final String d;
    private final String e;
    private byte[] f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeByteArray(this.f);
    }

    public String toString() {
        if (g()) {
            return new String(this.f, StandardCharsets.UTF_8);
        }
        return this.d + "#" + this.e + "#" + new String(this.f, StandardCharsets.UTF_8);
    }

    public boolean g() {
        return this.e.length() > 32;
    }

    public String f() {
        return g() ? new String(this.f, StandardCharsets.UTF_8) : "";
    }

    public boolean e() {
        if (g()) {
            return true;
        }
        byte[] a2 = com.huawei.hms.kit.awareness.barrier.internal.f.a.a(this.f);
        boolean z = a2 != null;
        if (!z) {
            a2 = this.f;
        }
        this.f = a2;
        return z;
    }

    public boolean d() {
        return h() || i();
    }

    public byte[] c() {
        return (byte[]) this.f.clone();
    }

    public String b() {
        return this.e;
    }

    public String a() {
        return this.d;
    }

    private boolean i() {
        return g.equals(a()) && h.equals(b()) && !com.huawei.hms.kit.awareness.barrier.internal.f.c.a(c()) && c().length <= 1024;
    }

    private boolean h() {
        return !com.huawei.hms.kit.awareness.barrier.internal.f.c.a(a()) && !com.huawei.hms.kit.awareness.barrier.internal.f.c.a(b()) && a().length() <= 64 && b().length() <= 32 && c().length <= 1024;
    }

    public static b a(b bVar) {
        return bVar.g() ? new b(bVar.f()) : new b(bVar.a(), bVar.b(), com.huawei.hms.kit.awareness.barrier.internal.f.a.a(bVar.c()));
    }

    public b(String str, String str2, byte[] bArr) {
        this.d = str;
        this.e = str2;
        if (bArr != null) {
            this.f = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public b(String str, String str2) {
        this.d = str;
        this.e = str2;
        this.f = new byte[0];
    }

    public b(String str) {
        this.d = g;
        this.e = h;
        this.f = str.getBytes(StandardCharsets.UTF_8);
    }

    public b(b bVar) {
        this(bVar.d, bVar.e, bVar.f);
    }

    private b(Parcel parcel) {
        String readString = parcel.readString();
        this.d = readString == null ? "" : readString;
        String readString2 = parcel.readString();
        this.e = readString2 != null ? readString2 : "";
        byte[] createByteArray = parcel.createByteArray();
        this.f = createByteArray == null ? new byte[0] : createByteArray;
    }
}
