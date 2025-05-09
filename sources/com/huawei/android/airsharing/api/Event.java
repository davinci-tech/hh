package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes2.dex */
public class Event implements Parcelable {
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() { // from class: com.huawei.android.airsharing.api.Event.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dF_, reason: merged with bridge method [inline-methods] */
        public Event createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            Log.d(Event.TAG, "eventId " + readInt);
            if (readInt == 2010) {
                return new InitializationInfoEvent(readInt, parcel);
            }
            if (readInt == 3014) {
                return new GrabStateChangedEvent(readInt, parcel);
            }
            if (readInt != 3600) {
                switch (readInt) {
                    case 3100:
                    case 3101:
                    case 3102:
                        break;
                    default:
                        switch (readInt) {
                            case IEventListener.EVENT_ID_HISIGHT_STATE_PLAYING /* 3104 */:
                            case IEventListener.EVENT_ID_HISIGHT_STATE_PAUSED /* 3105 */:
                            case IEventListener.EVENT_ID_HISIGHT_STATE_CONNECTING /* 3106 */:
                            case IEventListener.EVENT_ID_HISIGHT_STATE_KIT_NOT_CERTIFIED /* 3108 */:
                                break;
                            case 3107:
                                return new AuthRequestEvent(readInt, parcel);
                            default:
                                return new Event(readInt);
                        }
                }
                return new ConnectionStatusEvent(readInt, parcel);
            }
            return new ErrorInfoEvent(readInt, parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Event[] newArray(int i) {
            return new Event[i];
        }
    };
    protected static final String TAG = "AirsharingEvent";
    protected final int mEventId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Event(int i) {
        this.mEventId = i;
    }

    protected Event(Parcel parcel) {
        this.mEventId = parcel.readInt();
    }

    public int getEventId() {
        return this.mEventId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mEventId);
        }
    }
}
