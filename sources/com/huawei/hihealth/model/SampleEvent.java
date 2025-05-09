package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.CommonParam;
import java.util.List;

/* loaded from: classes.dex */
public class SampleEvent extends CommonParam {
    public static final Parcelable.Creator<SampleEvent> CREATOR = new Parcelable.Creator<SampleEvent>() { // from class: com.huawei.hihealth.model.SampleEvent.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwA_, reason: merged with bridge method [inline-methods] */
        public SampleEvent createFromParcel(Parcel parcel) {
            return new SampleEvent(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SampleEvent[] newArray(int i) {
            return new SampleEvent[i];
        }
    };
    public static final String SEPARATOR = "$";
    private final long eventTime;
    private final String eventType;
    private final List<MetaData> metaData;
    private final String subType;
    private final String subscriptionId;
    private final Type type;

    protected SampleEvent(Parcel parcel) {
        super(parcel);
        this.type = (Type) parcel.readSerializable();
        this.subType = parcel.readString();
        this.eventType = parcel.readString();
        this.eventTime = parcel.readLong();
        this.subscriptionId = parcel.readString();
        this.metaData = parcel.createTypedArrayList(MetaData.CREATOR);
    }

    public SampleEvent(Type type, String str, String str2, long j, List<MetaData> list) {
        this.type = type;
        this.subType = str;
        this.eventType = type + SEPARATOR + str;
        this.subscriptionId = str2;
        this.eventTime = j;
        this.metaData = list;
    }

    @Override // com.huawei.hihealth.CommonParam, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeSerializable(this.type);
        parcel.writeString(this.subType);
        parcel.writeString(this.eventType);
        parcel.writeLong(this.eventTime);
        parcel.writeString(this.subscriptionId);
        parcel.writeTypedList(this.metaData);
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public Type getType() {
        return this.type;
    }

    public String getSubType() {
        return this.subType;
    }

    public List<MetaData> getMetaData() {
        return this.metaData;
    }

    public long getEventTime() {
        return this.eventTime;
    }

    public enum Type {
        SCENARIO_GOAL_EVENT("SCENARIO_GOAL_EVENT"),
        USER_EVENT("USER_EVENT"),
        HEALTH_ALARM_EVENT("HEALTH_ALARM_EVENT"),
        SPORT_STATUS_EVENT("SPORT_STATUS_EVENT");

        private final String name;

        Type(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }
}
