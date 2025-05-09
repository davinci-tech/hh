package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class InitializationInfoEvent extends Event implements Parcelable {
    public static final Parcelable.Creator<InitializationInfoEvent> CREATOR = new Parcelable.Creator<InitializationInfoEvent>() { // from class: com.huawei.android.airsharing.api.InitializationInfoEvent.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dM_, reason: merged with bridge method [inline-methods] */
        public InitializationInfoEvent createFromParcel(Parcel parcel) {
            return new InitializationInfoEvent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public InitializationInfoEvent[] newArray(int i) {
            return new InitializationInfoEvent[i];
        }
    };
    public static final String EVENT_DESCRIPTION_INIT_FAILED = "EVENT_TYPE_PLAYER_SUBSCRIBE_FAILED";
    public static final String EVENT_DESCRIPTION_INIT_SUCCESS = "EVENT_TYPE_PLAYER_SUBSCRIBE_SUCCESS";
    public static final int EVENT_TYPE_INIT_FAILED = 1;
    public static final int EVENT_TYPE_INIT_SUCCESS = 2;
    private String mDescription;
    private int mEventType;

    public InitializationInfoEvent(int i, int i2, String str) {
        super(i);
        this.mEventType = i2;
        this.mDescription = str;
    }

    protected InitializationInfoEvent(Parcel parcel) {
        super(parcel);
        this.mEventType = parcel.readInt();
        this.mDescription = parcel.readString();
    }

    protected InitializationInfoEvent(int i, Parcel parcel) {
        super(i);
        this.mEventType = parcel.readInt();
        this.mDescription = parcel.readString();
    }

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mEventType);
        parcel.writeString(this.mDescription);
    }

    public int getEventType() {
        return this.mEventType;
    }

    public String getDescription() {
        return this.mDescription;
    }
}
