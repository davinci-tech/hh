package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class GrabStateChangedEvent extends Event implements Parcelable {
    public static final Parcelable.Creator<GrabStateChangedEvent> CREATOR = new Parcelable.Creator<GrabStateChangedEvent>() { // from class: com.huawei.android.airsharing.api.GrabStateChangedEvent.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dH_, reason: merged with bridge method [inline-methods] */
        public GrabStateChangedEvent createFromParcel(Parcel parcel) {
            return new GrabStateChangedEvent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public GrabStateChangedEvent[] newArray(int i) {
            return new GrabStateChangedEvent[i];
        }
    };
    private GrabState mGrabState;

    public GrabStateChangedEvent(int i, GrabState grabState) {
        super(i);
        this.mGrabState = grabState;
    }

    protected GrabStateChangedEvent(Parcel parcel) {
        super(parcel);
        this.mGrabState = (GrabState) parcel.readParcelable(GrabState.class.getClassLoader());
    }

    protected GrabStateChangedEvent(int i, Parcel parcel) {
        super(i);
        this.mGrabState = (GrabState) parcel.readParcelable(GrabState.class.getClassLoader());
    }

    @Override // com.huawei.android.airsharing.api.Event, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mGrabState, i);
    }

    public GrabState getGrabState() {
        return this.mGrabState;
    }
}
