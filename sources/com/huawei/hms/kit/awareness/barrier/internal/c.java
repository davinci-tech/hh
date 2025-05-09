package com.huawei.hms.kit.awareness.barrier.internal;

import android.content.Context;
import android.os.Parcel;
import com.huawei.hms.kit.awareness.barrier.BarrierQueryRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: classes4.dex */
public final class c extends BarrierQueryRequest implements g {

    /* renamed from: a, reason: collision with root package name */
    private static final int f4858a = 0;
    private static final int b = 1;
    private final int c;
    private final List<String> d;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.g
    public int a(Context context) {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.BarrierQueryRequest, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeList(this.d);
    }

    public List<String> c() {
        return this.d;
    }

    public final boolean b() {
        return this.c == 1 && com.huawei.hms.kit.awareness.barrier.internal.f.c.a((Collection) this.d);
    }

    public static BarrierQueryRequest a(String... strArr) {
        return new c(strArr);
    }

    public static BarrierQueryRequest a(Collection<String> collection) {
        return new c(collection);
    }

    public static BarrierQueryRequest a(Parcel parcel) {
        return new c(parcel);
    }

    public static BarrierQueryRequest a() {
        return new c();
    }

    private c(String... strArr) {
        this.d = (List) Arrays.stream(strArr).distinct().collect(Collectors.toList());
        this.c = 0;
    }

    private c(Collection<String> collection) {
        this.d = (List) collection.stream().distinct().collect(Collectors.toList());
        this.c = 0;
    }

    private c(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.d = arrayList;
        this.c = parcel.readInt();
        parcel.readList(arrayList, String.class.getClassLoader());
    }

    private c() {
        this.d = new ArrayList();
        this.c = 1;
    }
}
