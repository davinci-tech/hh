package com.hihonor.android.location.activityrecognition;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HwActivityRecognitionEvent implements Parcelable {
    public static final Parcelable.Creator<HwActivityRecognitionEvent> CREATOR = new Parcelable.Creator<HwActivityRecognitionEvent>() { // from class: com.hihonor.android.location.activityrecognition.HwActivityRecognitionEvent.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cO_, reason: merged with bridge method [inline-methods] */
        public HwActivityRecognitionEvent createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            long readLong = parcel.readLong();
            if (HwActivityRecognition.c() == 1) {
                return new HwActivityRecognitionEvent(readString, readInt, readLong, parcel.readInt());
            }
            return new HwActivityRecognitionEvent(readString, readInt, readLong);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HwActivityRecognitionEvent[] newArray(int i) {
            return new HwActivityRecognitionEvent[i];
        }
    };
    private final String mActivity;
    private int mConfidence;
    private final int mEventType;
    private final long mTimestampNs;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HwActivityRecognitionEvent(String str, int i, long j) {
        this.mActivity = str;
        this.mEventType = i;
        this.mTimestampNs = j;
        this.mConfidence = -2;
    }

    public HwActivityRecognitionEvent(String str, int i, long j, int i2) {
        this.mActivity = str;
        this.mEventType = i;
        this.mTimestampNs = j;
        this.mConfidence = i2;
    }

    public String getActivity() {
        return this.mActivity;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public long getTimestampNs() {
        return this.mTimestampNs;
    }

    public int getConfidence() {
        return this.mConfidence;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mActivity);
        parcel.writeInt(this.mEventType);
        parcel.writeLong(this.mTimestampNs);
        if (HwActivityRecognition.c() == 1) {
            parcel.writeInt(this.mConfidence);
        }
    }

    public String toString() {
        return String.format("Activity='%s', EventType=%s, TimestampNs=%s, Confidence=%s", this.mActivity, Integer.valueOf(this.mEventType), Long.valueOf(this.mTimestampNs), Integer.valueOf(this.mConfidence));
    }
}
