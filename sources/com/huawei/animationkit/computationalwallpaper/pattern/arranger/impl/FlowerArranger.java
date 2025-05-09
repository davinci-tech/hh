package com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl;

import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.AbstractArranger;

/* loaded from: classes8.dex */
public class FlowerArranger extends AbstractArranger {
    public static final Parcelable.Creator<FlowerArranger> CREATOR = new Parcelable.Creator<FlowerArranger>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.FlowerArranger.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: fZ_, reason: merged with bridge method [inline-methods] */
        public FlowerArranger createFromParcel(Parcel parcel) {
            return new FlowerArranger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public FlowerArranger[] newArray(int i) {
            return new FlowerArranger[i];
        }
    };

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void arrange(Canvas canvas) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void releaseBitmap() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    private FlowerArranger(Parcel parcel) {
    }
}
