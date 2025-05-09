package com.huawei.profile.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes9.dex */
public enum SyncResult implements Parcelable {
    ERROR(-1),
    OK(0),
    BUSY(1),
    NOT_FOUND(2),
    INVALID_ARGS(3),
    TIME_OUT(4),
    CONNECT_FAIL(5);

    public static final Parcelable.Creator<SyncResult> CREATOR = new Parcelable.Creator<SyncResult>() { // from class: com.huawei.profile.service.SyncResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncResult createFromParcel(Parcel parcel) {
            return SyncResult.get(parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SyncResult[] newArray(int i) {
            return new SyncResult[i];
        }
    };
    private static final int INDEX_RESULT_BUSY = 1;
    private static final int INDEX_RESULT_CONNECT_FAIL = 5;
    private static final int INDEX_RESULT_ERROR = -1;
    private static final int INDEX_RESULT_INVALID_ARGS = 3;
    private static final int INDEX_RESULT_NOT_FOUND = 2;
    private static final int INDEX_RESULT_OK = 0;
    private static final int INDEX_RESULT_TIME_OUT = 4;
    private static final String TAG = "SyncResult";
    private int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    SyncResult(int i) {
        this.resultCode = i;
    }

    public static SyncResult get(int i) {
        switch (i) {
            case -1:
                return ERROR;
            case 0:
                return OK;
            case 1:
                return BUSY;
            case 2:
                return NOT_FOUND;
            case 3:
                return INVALID_ARGS;
            case 4:
                return TIME_OUT;
            case 5:
                return CONNECT_FAIL;
            default:
                Log.e(TAG, "invalid result code: " + i);
                return ERROR;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.resultCode);
    }
}
