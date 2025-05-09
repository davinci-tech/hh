package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

/* loaded from: classes4.dex */
public class c implements Parcelable {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.huawei.hms.kit.awareness.b.c.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i) {
            return new c[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final String f4832a = "BeaconFilterRequest";
    private final String b;
    private List<com.huawei.hms.kit.awareness.a.a.b> c;
    private e d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeList(this.c);
        parcel.writeParcelable(this.d, i);
    }

    public boolean d() {
        List<com.huawei.hms.kit.awareness.a.a.b> list;
        if (this.b == null || (list = this.c) == null || this.d == null) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4832a, "request object is invalid", new Object[0]);
            return false;
        }
        Iterator<com.huawei.hms.kit.awareness.a.a.b> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().d()) {
                com.huawei.hms.kit.awareness.b.a.c.d(f4832a, "beacon filter is invalid", new Object[0]);
                return false;
            }
        }
        return true;
    }

    public e c() {
        return this.d;
    }

    public List<com.huawei.hms.kit.awareness.a.a.b> b() {
        return Collections.unmodifiableList(this.c);
    }

    public String a() {
        return this.b;
    }

    private void e() {
        if (this.c == null) {
            this.c = new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(List list, com.huawei.hms.kit.awareness.a.a.b bVar) {
        list.add(com.huawei.hms.kit.awareness.a.a.b.a(bVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<com.huawei.hms.kit.awareness.a.a.b> list) {
        e();
        final ArrayList arrayList = new ArrayList();
        list.forEach(new Consumer() { // from class: com.huawei.hms.kit.awareness.b.c$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c.a(arrayList, (com.huawei.hms.kit.awareness.a.a.b) obj);
            }
        });
        this.c.addAll(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(e eVar) {
        this.d = eVar;
    }

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final c f4833a = new c(UUID.randomUUID().toString());

        public c a() {
            com.huawei.hms.kit.awareness.barrier.internal.f.b.a(this.f4833a.b);
            com.huawei.hms.kit.awareness.barrier.internal.f.b.a(this.f4833a.c);
            com.huawei.hms.kit.awareness.barrier.internal.f.b.a(this.f4833a.d);
            return this.f4833a;
        }

        public a a(List<com.huawei.hms.kit.awareness.a.a.b> list) {
            this.f4833a.a(list);
            return this;
        }

        public a a(e eVar) {
            this.f4833a.a(eVar);
            return this;
        }
    }

    private c(String str) {
        this.c = new ArrayList();
        this.b = str;
    }

    private c(Parcel parcel) {
        this.c = new ArrayList();
        this.b = parcel.readString();
        parcel.readList(this.c, com.huawei.hms.kit.awareness.a.a.b.class.getClassLoader());
        this.d = (e) parcel.readParcelable(e.class.getClassLoader());
    }
}
