package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.hihealth.model.SampleEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* loaded from: classes.dex */
public class EventTypeInfo implements Parcelable {
    public static final Parcelable.Creator<EventTypeInfo> CREATOR = new Parcelable.Creator<EventTypeInfo>() { // from class: com.huawei.hihealth.model.EventTypeInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwp_, reason: merged with bridge method [inline-methods] */
        public EventTypeInfo createFromParcel(Parcel parcel) {
            try {
                return (EventTypeInfo) Class.forName(parcel.readString()).getConstructor(Parcel.class).newInstance(parcel);
            } catch (ClassNotFoundException unused) {
                Log.w(EventTypeInfo.TAG, "class not found");
                return new EventTypeInfo(parcel);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
                Log.w(EventTypeInfo.TAG, "other exception");
                return new EventTypeInfo(parcel);
            } catch (NoSuchMethodException unused3) {
                Log.w(EventTypeInfo.TAG, "no such method");
                return new EventTypeInfo(parcel);
            }
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public EventTypeInfo[] newArray(int i) {
            return new EventTypeInfo[i];
        }
    };
    private static final String TAG = "EventTypeInfo";
    private String eventType;
    private String openId;
    private int startDay;
    private String subType;
    private String subscriptionId;
    private int subscriptionMode;
    private SampleEvent.Type type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected EventTypeInfo(SampleEvent.Type type, String str, String str2, int i) {
        this.type = type;
        this.subType = str;
        this.eventType = type.getName() + SampleEvent.SEPARATOR + str;
        this.openId = str2;
        this.startDay = i;
    }

    protected EventTypeInfo(Parcel parcel) {
        this.type = (SampleEvent.Type) parcel.readSerializable();
        this.subType = parcel.readString();
        this.eventType = parcel.readString();
        this.subscriptionId = parcel.readString();
        this.startDay = parcel.readInt();
        this.subscriptionMode = parcel.readInt();
        this.openId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getClass().getName());
        parcel.writeSerializable(this.type);
        parcel.writeString(this.subType);
        parcel.writeString(this.eventType);
        parcel.writeString(this.subscriptionId);
        parcel.writeInt(this.startDay);
        parcel.writeInt(this.subscriptionMode);
        parcel.writeString(this.openId);
    }

    public int hashCode() {
        return Objects.hash(this.type.getName(), this.subType, Integer.valueOf(this.startDay), Integer.valueOf(this.subscriptionMode), this.subscriptionId, this.openId);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EventTypeInfo)) {
            return false;
        }
        EventTypeInfo eventTypeInfo = (EventTypeInfo) obj;
        return Objects.equals(this.type.getName(), eventTypeInfo.getType().getName()) && Objects.equals(this.subType, eventTypeInfo.getSubType()) && Objects.equals(Integer.valueOf(this.startDay), Integer.valueOf(eventTypeInfo.getStartDay())) && Objects.equals(Integer.valueOf(this.subscriptionMode), Integer.valueOf(eventTypeInfo.getSubscriptionMode())) && Objects.equals(this.openId, eventTypeInfo.getOpenId());
    }

    public int getStartDay() {
        return this.startDay;
    }

    public void setStartDay(int i) {
        this.startDay = i;
    }

    public SampleEvent.Type getType() {
        return this.type;
    }

    public void setType(SampleEvent.Type type) {
        this.type = type;
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String str) {
        this.subType = str;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public int getSubscriptionMode() {
        return this.subscriptionMode;
    }

    public void setSubscriptionMode(int i) {
        this.subscriptionMode = i;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }
}
