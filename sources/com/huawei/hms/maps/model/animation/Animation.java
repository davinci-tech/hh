package com.huawei.hms.maps.model.animation;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public abstract class Animation implements Parcelable {
    public static final Parcelable.Creator<Animation> CREATOR = new Parcelable.Creator<Animation>() { // from class: com.huawei.hms.maps.model.animation.Animation.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Animation[] newArray(int i) {
            return new Animation[0];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Animation createFromParcel(Parcel parcel) {
            return Animation.b(parcel);
        }
    };
    public static final int FILL_MODE_BACKWARDS = 1;
    public static final int FILL_MODE_FORWARDS = 0;
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;

    /* renamed from: a, reason: collision with root package name */
    protected int f5017a;
    protected int b;
    protected int c;
    protected int d;
    protected long e;
    protected Interpolator f;
    protected AnimationListener g;

    public interface AnimationListener {
        void onAnimationEnd();

        void onAnimationStart();
    }

    protected abstract void a(ParcelWrite parcelWrite, int i);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public abstract void setDuration(long j);

    public abstract void setInterpolator(Interpolator interpolator);

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeInt(2, this.f5017a);
        parcelWrite.writeInt(3, this.b);
        parcelWrite.writeInt(4, this.c);
        parcelWrite.writeInt(5, this.d);
        parcelWrite.writeLong(6, this.e);
        parcelWrite.writeIBinder(7, ObjectWrapper.wrap(this.f).asBinder(), false);
        a(parcelWrite, i);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public void setRepeatMode(int i) {
        this.c = i;
    }

    public void setRepeatCount(int i) {
        this.d = i;
    }

    public void setFillMode(int i) {
        this.b = i;
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.g = animationListener;
    }

    public int getRepeatMode() {
        return this.c;
    }

    public int getRepeatCount() {
        return this.d;
    }

    public AnimationListener getListener() {
        return this.g;
    }

    public Interpolator getInterpolator() {
        return this.f;
    }

    public int getFillMode() {
        return this.b;
    }

    public long getDuration() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Animation b(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        int readInt = parcelReader.readInt(2, 0);
        if (readInt == 1) {
            return new AlphaAnimation(parcelReader);
        }
        if (readInt == 2) {
            return new RotateAnimation(parcelReader);
        }
        if (readInt == 3) {
            return new ScaleAnimation(parcelReader);
        }
        if (readInt == 4) {
            return new TranslateAnimation(parcelReader);
        }
        if (readInt != 5) {
            return null;
        }
        return new AnimationSet(parcelReader);
    }

    protected Animation(ParcelReader parcelReader) {
        this.b = 0;
        this.c = 1;
        this.d = 0;
        this.e = 250L;
        this.f5017a = parcelReader.readInt(2, 0);
        this.b = parcelReader.readInt(3, 0);
        this.c = parcelReader.readInt(4, 0);
        this.d = parcelReader.readInt(5, 0);
        this.e = parcelReader.readLong(6, 0L);
        IBinder readIBinder = parcelReader.readIBinder(7, null);
        if (readIBinder != null) {
            this.f = (Interpolator) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(readIBinder));
        }
    }

    public Animation() {
        this.b = 0;
        this.c = 1;
        this.d = 0;
        this.e = 250L;
    }
}
