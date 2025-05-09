package com.huawei.hms.maps.model.animation;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes4.dex */
public class ScaleAnimation extends Animation {
    public static final Parcelable.Creator<ScaleAnimation> CREATOR = new Parcelable.Creator<ScaleAnimation>() { // from class: com.huawei.hms.maps.model.animation.ScaleAnimation.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ScaleAnimation[] newArray(int i) {
            return new ScaleAnimation[0];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ScaleAnimation createFromParcel(Parcel parcel) {
            return new ScaleAnimation(new ParcelReader(parcel));
        }
    };
    private float h;
    private float i;
    private float j;
    private float k;

    @Override // com.huawei.hms.maps.model.animation.Animation
    public void setInterpolator(Interpolator interpolator) {
        this.f = interpolator;
    }

    @Override // com.huawei.hms.maps.model.animation.Animation
    public void setDuration(long j) {
        this.e = Math.max(j, 0L);
    }

    public float getToY() {
        return this.k;
    }

    public float getToX() {
        return this.i;
    }

    public float getFromY() {
        return this.j;
    }

    public float getFromX() {
        return this.h;
    }

    @Override // com.huawei.hms.maps.model.animation.Animation
    protected void a(ParcelWrite parcelWrite, int i) {
        parcelWrite.writeFloat(8, this.h);
        parcelWrite.writeFloat(9, this.i);
        parcelWrite.writeFloat(10, this.j);
        parcelWrite.writeFloat(11, this.k);
    }

    protected ScaleAnimation(ParcelReader parcelReader) {
        super(parcelReader);
        this.h = parcelReader.readFloat(8, 0.0f);
        this.i = parcelReader.readFloat(9, 0.0f);
        this.j = parcelReader.readFloat(10, 0.0f);
        this.k = parcelReader.readFloat(11, 0.0f);
    }

    public ScaleAnimation(float f, float f2, float f3, float f4) {
        this.f5017a = 3;
        this.b = 1;
        this.h = Math.max(f, 0.0f);
        this.i = Math.max(f2, 0.0f);
        this.j = Math.max(f3, 0.0f);
        this.k = Math.max(f4, 0.0f);
    }
}
