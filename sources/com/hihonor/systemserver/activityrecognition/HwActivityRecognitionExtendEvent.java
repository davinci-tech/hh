package com.hihonor.systemserver.activityrecognition;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.hihonor.android.location.activityrecognition.HwActivityRecognition;

/* loaded from: classes.dex */
public class HwActivityRecognitionExtendEvent implements Parcelable {
    public static final Parcelable.Creator<HwActivityRecognitionExtendEvent> CREATOR = new Parcelable.Creator<HwActivityRecognitionExtendEvent>() { // from class: com.hihonor.systemserver.activityrecognition.HwActivityRecognitionExtendEvent.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: de_, reason: merged with bridge method [inline-methods] */
        public HwActivityRecognitionExtendEvent createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            long readLong = parcel.readLong();
            OtherParameters otherParameters = (OtherParameters) parcel.readParcelable(OtherParameters.class.getClassLoader());
            if (HwActivityRecognition.c() == 1) {
                return new HwActivityRecognitionExtendEvent(readString, readInt, readLong, otherParameters, parcel.readInt());
            }
            return new HwActivityRecognitionExtendEvent(readString, readInt, readLong, otherParameters);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HwActivityRecognitionExtendEvent[] newArray(int i) {
            return new HwActivityRecognitionExtendEvent[i];
        }
    };
    private final String mActivity;
    private int mConfidence;
    private final int mEventType;
    private final OtherParameters mOtherParams;
    private final long mTimestampNs;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HwActivityRecognitionExtendEvent(String str, int i, long j, OtherParameters otherParameters) {
        this.mActivity = str;
        this.mEventType = i;
        this.mTimestampNs = j;
        this.mOtherParams = otherParameters;
        this.mConfidence = -2;
    }

    public HwActivityRecognitionExtendEvent(String str, int i, long j, OtherParameters otherParameters, int i2) {
        this.mActivity = str;
        this.mEventType = i;
        this.mTimestampNs = j;
        this.mOtherParams = otherParameters;
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

    public OtherParameters getOtherParams() {
        return this.mOtherParams;
    }

    public int getConfidence() {
        return this.mConfidence;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mActivity);
        parcel.writeInt(this.mEventType);
        parcel.writeLong(this.mTimestampNs);
        parcel.writeParcelable(this.mOtherParams, i);
        Log.e("ARMoudle.HwActivityRecognition", "Extend ARServiceVersion : " + HwActivityRecognition.c());
        if (HwActivityRecognition.c() == 1) {
            parcel.writeInt(this.mConfidence);
        }
    }

    public String toString() {
        if (this.mOtherParams != null) {
            return String.format("Activity='%s', EventType=%s, TimestampNs=%s, Param1=%s, Param2=%s, Param3=%s, Param4=%s, Param5=%s, Confidence=%s", this.mActivity, Integer.valueOf(this.mEventType), Long.valueOf(this.mTimestampNs), Double.valueOf(this.mOtherParams.getmParam1()), Double.valueOf(this.mOtherParams.getmParam2()), Double.valueOf(this.mOtherParams.getmParam3()), Double.valueOf(this.mOtherParams.getmParam4()), this.mOtherParams.getmParam5(), Integer.valueOf(this.mConfidence));
        }
        return String.format("Activity='%s', EventType=%s, TimestampNs=%s, Confidence=%s", this.mActivity, Integer.valueOf(this.mEventType), Long.valueOf(this.mTimestampNs), Integer.valueOf(this.mConfidence));
    }
}
