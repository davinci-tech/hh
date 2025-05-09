package com.huawei.systemserver.activityrecognition;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes6.dex */
public class HwActivityChangedEvent implements Parcelable {
    public static final Parcelable.Creator<HwActivityChangedEvent> CREATOR = new Parcelable.Creator<HwActivityChangedEvent>() { // from class: com.huawei.systemserver.activityrecognition.HwActivityChangedEvent.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: cuZ_, reason: merged with bridge method [inline-methods] */
        public HwActivityChangedEvent createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return new HwActivityChangedEvent(new HwActivityRecognitionEvent[0]);
            }
            int readInt = parcel.readInt();
            HwActivityRecognitionEvent[] hwActivityRecognitionEventArr = new HwActivityRecognitionEvent[HwActivityChangedEvent.checkActivityReportLen(readInt) ? readInt : 0];
            parcel.readTypedArray(hwActivityRecognitionEventArr, HwActivityRecognitionEvent.CREATOR);
            return new HwActivityChangedEvent(hwActivityRecognitionEventArr);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HwActivityChangedEvent[] newArray(int i) {
            return new HwActivityChangedEvent[i];
        }
    };
    private static final int MAX_ACTIVITY_REPORT_LEN = 1000;
    private static final String TAG = "ARMoudle.HwActivityChangedEvent";
    private final List<HwActivityRecognitionEvent> mActivityRecognitionEvents;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HwActivityChangedEvent(HwActivityRecognitionEvent[] hwActivityRecognitionEventArr) {
        if (hwActivityRecognitionEventArr == null) {
            throw new InvalidParameterException("Parameter 'activityRecognitionEvents' must not be null.");
        }
        this.mActivityRecognitionEvents = Arrays.asList(hwActivityRecognitionEventArr);
    }

    public Iterable<HwActivityRecognitionEvent> getActivityRecognitionEvents() {
        return this.mActivityRecognitionEvents;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        HwActivityRecognitionEvent[] hwActivityRecognitionEventArr = (HwActivityRecognitionEvent[]) this.mActivityRecognitionEvents.toArray(new HwActivityRecognitionEvent[0]);
        parcel.writeInt(hwActivityRecognitionEventArr.length);
        parcel.writeTypedArray(hwActivityRecognitionEventArr, i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[ ActivityChangedEvent:");
        for (HwActivityRecognitionEvent hwActivityRecognitionEvent : this.mActivityRecognitionEvents) {
            sb.append("\n    ");
            sb.append(hwActivityRecognitionEvent.toString());
        }
        sb.append("\n]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkActivityReportLen(int i) {
        if (i >= 0 && i <= 1000) {
            return true;
        }
        Log.e(TAG, "report activity invalid.");
        return false;
    }
}
