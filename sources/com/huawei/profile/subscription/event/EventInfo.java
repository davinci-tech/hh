package com.huawei.profile.subscription.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class EventInfo implements Parcelable {
    public static final Parcelable.Creator<EventInfo> CREATOR;
    public static final Set<Integer> EVENTS_SET;
    public static final int FLAG_SUBSCRIBE_EVENT_SYNC = 1;
    private static final String TAG = "EventInfo";
    protected int eventId;
    protected String extraInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        HashSet hashSet = new HashSet();
        EVENTS_SET = hashSet;
        hashSet.add(1);
        CREATOR = new Parcelable.Creator<EventInfo>() { // from class: com.huawei.profile.subscription.event.EventInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EventInfo createFromParcel(Parcel parcel) {
                return new EventInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public EventInfo[] newArray(int i) {
                return new EventInfo[i];
            }
        };
    }

    protected EventInfo(Parcel parcel) {
        if (parcel == null) {
            Log.e(TAG, "parcel is null");
        } else {
            this.eventId = parcel.readInt();
            this.extraInfo = parcel.readString();
        }
    }

    protected EventInfo() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            Log.e(TAG, "parcel is null");
            return;
        }
        parcel.writeInt(this.eventId);
        String str = this.extraInfo;
        if (str == null) {
            str = "";
        }
        parcel.writeString(str);
    }

    public int getEventId() {
        return this.eventId;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* loaded from: classes9.dex */
    public static class Builder {
        private ArrayList<EventInfo> eventInfoList;

        private Builder() {
            this.eventInfoList = new ArrayList<>();
        }

        public Builder subscribeEvent(int i) {
            EventInfo eventInfo = new EventInfo();
            eventInfo.eventId = i;
            this.eventInfoList.add(eventInfo);
            return this;
        }

        public ArrayList<EventInfo> build() {
            return this.eventInfoList;
        }
    }
}
