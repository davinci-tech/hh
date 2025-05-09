package com.huawei.hms.kit.awareness.status;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DetectedBehavior implements Parcelable {
    public static final Parcelable.Creator<DetectedBehavior> CREATOR = new Parcelable.Creator<DetectedBehavior>() { // from class: com.huawei.hms.kit.awareness.status.DetectedBehavior.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DetectedBehavior[] newArray(int i) {
            return new DetectedBehavior[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DetectedBehavior createFromParcel(Parcel parcel) {
            return new DetectedBehavior(parcel);
        }
    };
    private final int confidence;
    private final int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.confidence);
    }

    public int getType() {
        return this.type;
    }

    public int getConfidence() {
        return this.confidence;
    }

    private DetectedBehavior(Parcel parcel) {
        this.type = parcel.readInt();
        this.confidence = parcel.readInt();
    }

    public DetectedBehavior(int i, int i2) {
        this.confidence = i2;
        this.type = i;
    }
}
