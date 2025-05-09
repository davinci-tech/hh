package com.huawei.health.knit.section.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class MarketingIdInfo implements Parcelable {
    public static final Parcelable.Creator<MarketingIdInfo> CREATOR = new Parcelable.Creator<MarketingIdInfo>() { // from class: com.huawei.health.knit.section.model.MarketingIdInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: agH_, reason: merged with bridge method [inline-methods] */
        public MarketingIdInfo createFromParcel(Parcel parcel) {
            return new MarketingIdInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MarketingIdInfo[] newArray(int i) {
            return new MarketingIdInfo[i];
        }
    };
    private final int mFloatingBoxId;
    private final int mPageId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MarketingIdInfo(e eVar) {
        this.mPageId = eVar.d;
        this.mFloatingBoxId = eVar.f2610a;
    }

    private MarketingIdInfo(Parcel parcel) {
        this.mPageId = parcel.readInt();
        this.mFloatingBoxId = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPageId);
        parcel.writeInt(this.mFloatingBoxId);
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private int f2610a;
        private int d;

        public e b(int i) {
            this.d = i;
            return this;
        }

        public e e(int i) {
            this.f2610a = i;
            return this;
        }

        public MarketingIdInfo a() {
            return new MarketingIdInfo(this);
        }
    }

    public int getmPageId() {
        return this.mPageId;
    }

    public int getmFloatingBoxId() {
        return this.mFloatingBoxId;
    }
}
