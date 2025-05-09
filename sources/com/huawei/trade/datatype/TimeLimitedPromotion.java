package com.huawei.trade.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class TimeLimitedPromotion implements Parcelable {
    public static final Parcelable.Creator<TimeLimitedPromotion> CREATOR = new Parcelable.Creator<TimeLimitedPromotion>() { // from class: com.huawei.trade.datatype.TimeLimitedPromotion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeLimitedPromotion createFromParcel(Parcel parcel) {
            return new TimeLimitedPromotion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeLimitedPromotion[] newArray(int i) {
            return new TimeLimitedPromotion[i];
        }
    };
    private long microOriginPrice;
    private long promotionEndTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getMicroOriginPrice() {
        return this.microOriginPrice;
    }

    public void setMicroOriginPrice(long j) {
        this.microOriginPrice = j;
    }

    public long getPromotionEndTime() {
        return this.promotionEndTime;
    }

    public void setPromotionEndTime(long j) {
        this.promotionEndTime = j;
    }

    public String toString() {
        return "TimeLimitedPromotion{microOriginPrice=" + this.microOriginPrice + ", promotionEndTime=" + this.promotionEndTime + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.microOriginPrice);
        parcel.writeLong(this.promotionEndTime);
    }

    public TimeLimitedPromotion() {
    }

    protected TimeLimitedPromotion(Parcel parcel) {
        this.microOriginPrice = parcel.readLong();
        this.promotionEndTime = parcel.readLong();
    }
}
