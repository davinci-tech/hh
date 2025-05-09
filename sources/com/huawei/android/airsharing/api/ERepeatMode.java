package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.xa;

/* loaded from: classes2.dex */
public enum ERepeatMode implements Parcelable {
    PLAY_IN_ORDER(0),
    REPEAT_CURRENT(1),
    REPEAT_LIST(2),
    SHUFFLE(3);

    private static final String TAG = "ERepeatMode";
    private int value;
    private static xa sLog = xa.c();
    public static final Parcelable.Creator<ERepeatMode> CREATOR = new Parcelable.Creator<ERepeatMode>() { // from class: com.huawei.android.airsharing.api.ERepeatMode.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dC_, reason: merged with bridge method [inline-methods] */
        public ERepeatMode createFromParcel(Parcel parcel) {
            try {
                return ERepeatMode.valueOf(parcel.readString());
            } catch (IllegalArgumentException | NullPointerException unused) {
                ERepeatMode.sLog.d(ERepeatMode.TAG, "invalid source");
                return ERepeatMode.PLAY_IN_ORDER;
            }
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ERepeatMode[] newArray(int i) {
            return new ERepeatMode[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    ERepeatMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(name());
        }
    }

    public boolean isValid() {
        return PLAY_IN_ORDER.getValue() == this.value || REPEAT_CURRENT.getValue() == this.value || REPEAT_LIST.getValue() == this.value || SHUFFLE.getValue() == this.value;
    }
}
