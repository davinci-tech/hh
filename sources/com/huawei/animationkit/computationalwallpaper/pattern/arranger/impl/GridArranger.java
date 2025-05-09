package com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.AbstractArranger;

/* loaded from: classes8.dex */
public class GridArranger extends AbstractArranger {
    public static final Parcelable.Creator<GridArranger> CREATOR = new Parcelable.Creator<GridArranger>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.GridArranger.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: ga_, reason: merged with bridge method [inline-methods] */
        public GridArranger createFromParcel(Parcel parcel) {
            return new GridArranger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public GridArranger[] newArray(int i) {
            return new GridArranger[i];
        }
    };
    private int mColumn;
    private int mRow;

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

    private GridArranger(Parcel parcel) {
        this.mRow = 1;
        this.mColumn = 1;
    }

    public void setGrid(int i, int i2) {
        this.mRow = i;
        this.mColumn = i2;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void arrange(Canvas canvas) {
        int width = getBound().width() / this.mRow;
        int height = getBound().height() / this.mColumn;
        Rect rect = new Rect();
        for (int i = 0; i < this.mRow; i++) {
            int i2 = 0;
            while (i2 < this.mColumn) {
                int i3 = i2 + 1;
                rect.set(width * i, i2 * height, (i + 1) * width, height * i3);
                getShape().setLocation(rect);
                getShape().draw(canvas);
                i2 = i3;
            }
        }
    }
}
