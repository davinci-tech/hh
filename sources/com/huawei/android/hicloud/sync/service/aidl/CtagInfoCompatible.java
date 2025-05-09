package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class CtagInfoCompatible extends CtagInfo {
    public static final Parcelable.Creator<CtagInfoCompatible> CREATOR = new c();
    private int version;

    static final class c implements Parcelable.Creator<CtagInfoCompatible> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public CtagInfoCompatible[] newArray(int i) {
            return new CtagInfoCompatible[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fa_, reason: merged with bridge method [inline-methods] */
        public CtagInfoCompatible createFromParcel(Parcel parcel) {
            return new CtagInfoCompatible(parcel, (c) null);
        }
    }

    /* synthetic */ CtagInfoCompatible(Parcel parcel, c cVar) {
        this(parcel);
    }

    @Override // com.huawei.android.hicloud.sync.service.aidl.CtagInfo
    protected void readFromParcel(Parcel parcel) {
        if (parcel.readInt() >= 101) {
            super.readFromParcel(parcel);
            setSyncToken(parcel.readString());
        }
    }

    @Override // com.huawei.android.hicloud.sync.service.aidl.CtagInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        if (this.version >= 101) {
            super.writeToParcel(parcel, i);
            parcel.writeString(getSyncToken());
        }
    }

    private CtagInfoCompatible() {
        this.version = -1;
    }

    public CtagInfoCompatible(CtagInfo ctagInfo, int i) {
        super(ctagInfo);
        this.version = i;
    }

    private CtagInfoCompatible(Parcel parcel) {
        this.version = -1;
        readFromParcel(parcel);
    }
}
