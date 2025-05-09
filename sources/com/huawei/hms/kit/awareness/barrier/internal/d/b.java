package com.huawei.hms.kit.awareness.barrier.internal.d;

import android.os.Parcel;
import android.os.Parcelable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes9.dex */
public class b extends f {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final String f4867a = "BeaconParameter";
    private final List<com.huawei.hms.kit.awareness.a.a.b> b;
    private final int c;
    private String d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.b);
        parcel.writeInt(this.c);
    }

    public String toString() {
        if (this.d == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            this.b.forEach(new Consumer() { // from class: com.huawei.hms.kit.awareness.barrier.internal.d.b$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    b.a(sb, (com.huawei.hms.kit.awareness.a.a.b) obj);
                }
            });
            this.d = new String(com.huawei.hms.kit.awareness.barrier.internal.f.a.a(sb.toString().getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        }
        return this.d;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return c() && d();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof b) && hashCode() == obj.hashCode()) {
            return toString().equals(((b) obj).toString());
        }
        return false;
    }

    public int b() {
        return this.c;
    }

    public List<com.huawei.hms.kit.awareness.a.a.b> a() {
        return this.b;
    }

    private boolean d() {
        int i = this.c;
        if (i == 0 || i == 2 || i == 1) {
            return true;
        }
        com.huawei.hms.kit.awareness.b.a.c.d(f4867a, "beaconType is invalid,the type is:" + this.c, new Object[0]);
        return false;
    }

    private boolean c() {
        Iterator<com.huawei.hms.kit.awareness.a.a.b> it = this.b.iterator();
        while (it.hasNext()) {
            if (!it.next().d()) {
                com.huawei.hms.kit.awareness.b.a.c.d(f4867a, "beaconFilter is invalid.", new Object[0]);
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(StringBuilder sb, com.huawei.hms.kit.awareness.a.a.b bVar) {
        sb.append(bVar.toString());
    }

    public b(List<com.huawei.hms.kit.awareness.a.a.b> list, int i) {
        this.b = list;
        this.c = i;
    }

    private b(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        parcel.readList(arrayList, com.huawei.hms.kit.awareness.a.a.b.class.getClassLoader());
        this.c = parcel.readInt();
    }
}
