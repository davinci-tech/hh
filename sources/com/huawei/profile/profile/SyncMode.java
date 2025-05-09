package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes6.dex */
public enum SyncMode implements Parcelable {
    PUSH_ONLY(0),
    PULL_ONLY(1),
    PUSH_PULL(2);

    public static final Parcelable.Creator<SyncMode> CREATOR = new Parcelable.Creator<SyncMode>() { // from class: com.huawei.profile.profile.SyncMode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncMode createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt == 0) {
                return SyncMode.PUSH_ONLY;
            }
            if (readInt == 1) {
                return SyncMode.PULL_ONLY;
            }
            if (readInt == 2) {
                return SyncMode.PUSH_PULL;
            }
            Log.e(SyncMode.TAG, "invalid sync mode code: " + readInt);
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncMode[] newArray(int i) {
            return new SyncMode[i];
        }
    };
    private static final int INDEX_PULL_ONLY = 1;
    private static final int INDEX_PUSH_ONLY = 0;
    private static final int INDEX_PUSH_PULL = 2;
    private static final String TAG = "SyncMode";
    private int value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    SyncMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.value);
    }
}
