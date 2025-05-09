package com.huawei.hms.maps.model.animation;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes4.dex */
public class RotateAnimation extends Animation {
    public static final Parcelable.Creator<RotateAnimation> CREATOR = new Parcelable.Creator<RotateAnimation>() { // from class: com.huawei.hms.maps.model.animation.RotateAnimation.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RotateAnimation[] newArray(int i) {
            return new RotateAnimation[0];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RotateAnimation createFromParcel(Parcel parcel) {
            return new RotateAnimation(new ParcelReader(parcel));
        }
    };
    private float h;
    private float i;

    @Override // com.huawei.hms.maps.model.animation.Animation
    public void setInterpolator(Interpolator interpolator) {
        this.f = interpolator;
    }

    @Override // com.huawei.hms.maps.model.animation.Animation
    public void setDuration(long j) {
        this.e = Math.max(j, 0L);
    }

    public float getToDegree() {
        return this.i;
    }

    public float getFromDegree() {
        return this.h;
    }

    @Override // com.huawei.hms.maps.model.animation.Animation
    protected void a(ParcelWrite parcelWrite, int i) {
        parcelWrite.writeFloat(8, this.h);
        parcelWrite.writeFloat(9, this.i);
    }

    protected RotateAnimation(ParcelReader parcelReader) {
        super(parcelReader);
        this.h = parcelReader.readFloat(8, 0.0f);
        this.i = parcelReader.readFloat(9, 0.0f);
    }

    public RotateAnimation(float f, float f2) {
        this.f5017a = 2;
        this.h = f;
        this.i = f2;
    }
}
