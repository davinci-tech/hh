package com.huawei.ohos.localability.base.form;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes9.dex */
public class c extends e {
    private String f;
    private int g;
    private b h;
    private Set<String> i;
    private String j;
    private com.huawei.ohos.localability.base.form.d k;

    static final class d implements Parcelable.Creator<c> {
        @Override // android.os.Parcelable.Creator
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public c[] newArray(int i) {
            if (i >= 0) {
                return new c[i];
            }
            return null;
        }
    }

    @Override // com.huawei.ohos.localability.base.form.e, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public c(Intent intent) {
        if (intent != null) {
            this.f = intent.getAction();
            this.g = intent.getFlags();
            this.j = intent.getPackage();
            if (intent.getCategories() != null) {
                this.i = new HashSet(intent.getCategories());
            }
            ComponentName component = intent.getComponent();
            if (component != null) {
                this.h = new b("", component.getPackageName(), component.getClassName());
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                com.huawei.ohos.localability.base.form.d dVar = new com.huawei.ohos.localability.base.form.d(null);
                Set<String> keySet = extras.keySet();
                if (keySet != null) {
                    for (String str : keySet) {
                        Object obj = extras.get(str);
                        if (obj != null && !(obj instanceof Serializable)) {
                            throw new IllegalArgumentException("the type or contained type is not support to transport when acquireForm : " + obj.getClass());
                        }
                        dVar.a(str, (String) obj);
                    }
                }
                this.k = dVar;
            }
        }
    }

    public c(Parcel parcel) {
        String[] strArr;
        if (parcel == null) {
            return;
        }
        this.f = parcel.readString();
        parcel.readInt();
        this.i = null;
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
            this.i = new HashSet(Arrays.asList(strArr));
        }
        this.g = parcel.readInt();
        this.h = null;
        if (parcel.readInt() != 1) {
            this.h = null;
        } else if (parcel.readInt() != 0) {
            this.h = b.d.createFromParcel(parcel);
        }
        this.k = null;
        if (parcel.readInt() == 1 && parcel.readInt() != 0) {
            this.k = com.huawei.ohos.localability.base.form.d.b.createFromParcel(parcel);
        }
        this.j = parcel.readString();
    }

    @Override // com.huawei.ohos.localability.base.form.e, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.f);
        parcel.writeInt(-1);
        parcel.writeInt(-1);
        parcel.writeInt(this.g);
        if (this.h == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(1);
            this.h.writeToParcel(parcel, 0);
        }
        if (this.k == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(1);
            parcel.writeInt(1);
            this.k.writeToParcel(parcel, 0);
        }
        parcel.writeString(this.j);
        parcel.writeInt(-1);
    }

    @Override // com.huawei.ohos.localability.base.form.e
    public int hashCode() {
        String str = this.f;
        int hashCode = str != null ? str.hashCode() : 0;
        b bVar = this.h;
        if (bVar != null) {
            hashCode += bVar.hashCode();
        }
        Set<String> set = this.i;
        if (set != null) {
            hashCode += set.hashCode();
        }
        String str2 = this.j;
        return str2 != null ? hashCode + str2.hashCode() : hashCode;
    }

    @Override // com.huawei.ohos.localability.base.form.e
    public boolean equals(Object obj) {
        if (obj instanceof c) {
            c cVar = (c) obj;
            if (Objects.equals(this.f, cVar.f) && Objects.equals(this.j, cVar.j) && Objects.equals(this.h, cVar.h) && Objects.equals(this.i, cVar.i)) {
                return true;
            }
        }
        return false;
    }
}
