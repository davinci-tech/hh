package com.huawei.openalliance.ad.views.viewpager;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public abstract class PPSAbsSavedState implements Parcelable {
    private final Parcelable b;

    /* renamed from: a, reason: collision with root package name */
    public static final PPSAbsSavedState f8165a = new PPSAbsSavedState() { // from class: com.huawei.openalliance.ad.views.viewpager.PPSAbsSavedState.1
    };
    public static final Parcelable.Creator<PPSAbsSavedState> CREATOR = new Parcelable.ClassLoaderCreator<PPSAbsSavedState>() { // from class: com.huawei.openalliance.ad.views.viewpager.PPSAbsSavedState.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PPSAbsSavedState[] newArray(int i) {
            return new PPSAbsSavedState[i];
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PPSAbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return PPSAbsSavedState.f8165a;
            }
            throw new IllegalStateException("superState should null");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PPSAbsSavedState createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, null);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.b, i);
    }

    public final Parcelable a() {
        return this.b;
    }

    protected PPSAbsSavedState(Parcelable parcelable) {
        if (parcelable == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.b = parcelable == f8165a ? null : parcelable;
    }

    protected PPSAbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.b = readParcelable == null ? f8165a : readParcelable;
    }

    private PPSAbsSavedState() {
        this.b = null;
    }
}
