package com.huawei.hms.maps.model.animation;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes4.dex */
public class AlphaAnimation extends Animation {
    public static final Parcelable.Creator<AlphaAnimation> CREATOR = new Parcelable.Creator<AlphaAnimation>() { // from class: com.huawei.hms.maps.model.animation.AlphaAnimation.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AlphaAnimation[] newArray(int i) {
            return new AlphaAnimation[0];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AlphaAnimation createFromParcel(Parcel parcel) {
            return new AlphaAnimation(new ParcelReader(parcel));
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

    public float getToAlpha() {
        return this.i;
    }

    public float getFromAlpha() {
        return this.h;
    }

    @Override // com.huawei.hms.maps.model.animation.Animation
    protected void a(ParcelWrite parcelWrite, int i) {
        parcelWrite.writeFloat(8, this.h);
        parcelWrite.writeFloat(9, this.i);
    }

    protected AlphaAnimation(ParcelReader parcelReader) {
        super(parcelReader);
        this.h = parcelReader.readFloat(8, 0.0f);
        this.i = parcelReader.readFloat(9, 0.0f);
    }

    public AlphaAnimation(float f, float f2) {
        this.f5017a = 1;
        this.h = Math.min(Math.max(f, 0.0f), 1.0f);
        this.i = Math.min(Math.max(f2, 0.0f), 1.0f);
    }
}
