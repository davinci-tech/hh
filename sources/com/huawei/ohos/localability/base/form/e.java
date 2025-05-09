package com.huawei.ohos.localability.base.form;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes9.dex */
class e implements Parcelable {

    /* renamed from: a, reason: collision with root package name */
    private String f6550a;
    private int b;
    private b c;
    private Set<String> d;
    private String e;

    static final class a implements Parcelable.Creator<e> {
        @Override // android.os.Parcelable.Creator
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public e[] newArray(int i) {
            if (i >= 0) {
                return new e[i];
            }
            return null;
        }
    }

    public e(Parcel parcel) {
        b createFromParcel;
        String[] strArr;
        this.f6550a = parcel.readString();
        parcel.readInt();
        this.d = null;
        if (parcel.readInt() == 1) {
            int readInt = parcel.readInt();
            if (readInt < 0 || readInt > 512000) {
                strArr = new String[0];
            } else {
                strArr = new String[readInt];
                for (int i = 0; i < readInt; i++) {
                    strArr[i] = parcel.readString();
                }
            }
            this.d = new HashSet(Arrays.asList(strArr));
        }
        this.b = parcel.readInt();
        this.c = null;
        if (parcel.readInt() == 1) {
            createFromParcel = parcel.readInt() != 0 ? b.d.createFromParcel(parcel) : null;
            parcel.readInt();
            this.e = parcel.readString();
        }
        this.c = createFromParcel;
        parcel.readInt();
        this.e = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6550a);
        parcel.writeInt(-1);
        parcel.writeInt(-1);
        parcel.writeInt(this.b);
        if (this.c == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(1);
            this.c.writeToParcel(parcel, 0);
        }
        parcel.writeInt(-1);
        parcel.writeString(this.e);
        parcel.writeInt(-1);
    }

    public int hashCode() {
        String str = this.f6550a;
        int hashCode = str != null ? str.hashCode() : 0;
        b bVar = this.c;
        if (bVar != null) {
            hashCode += bVar.hashCode();
        }
        Set<String> set = this.d;
        if (set != null) {
            hashCode += set.hashCode();
        }
        String str2 = this.e;
        return str2 != null ? hashCode + str2.hashCode() : hashCode;
    }

    public boolean equals(Object obj) {
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (Objects.equals(this.f6550a, eVar.f6550a) && Objects.equals(this.e, eVar.e) && Objects.equals(this.c, eVar.c) && Objects.equals(this.d, eVar.d)) {
                return true;
            }
        }
        return false;
    }

    public e() {
        this.b = 0;
    }
}
