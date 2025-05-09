package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.CommonParam;
import java.util.Objects;

/* loaded from: classes.dex */
public class Subscriber extends CommonParam {
    public static final Parcelable.Creator<Subscriber> CREATOR = new Parcelable.Creator<Subscriber>() { // from class: com.huawei.hihealth.model.Subscriber.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwD_, reason: merged with bridge method [inline-methods] */
        public Subscriber createFromParcel(Parcel parcel) {
            return new Subscriber(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Subscriber[] newArray(int i) {
            return new Subscriber[i];
        }
    };
    private String appId;
    private ReceiverFilter filter;
    private int lastNotifyDate;
    private int mode;
    private String secret;
    private String subscriberId;

    protected Subscriber(Parcel parcel) {
        super(parcel);
        this.appId = parcel.readString();
        this.subscriberId = parcel.readString();
        this.mode = parcel.readInt();
        this.secret = parcel.readString();
        this.filter = (ReceiverFilter) parcel.readParcelable(ReceiverFilter.class.getClassLoader());
    }

    public Subscriber(String str, String str2, String str3, ReceiverFilter receiverFilter) {
        this.appId = str;
        this.subscriberId = str2;
        this.mode = 2;
        this.secret = str3;
        this.filter = receiverFilter;
    }

    @Override // com.huawei.hihealth.CommonParam, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.appId);
        parcel.writeString(this.subscriberId);
        parcel.writeInt(this.mode);
        parcel.writeString(this.secret);
        parcel.writeParcelable(this.filter, i);
    }

    public int hashCode() {
        return Objects.hash(this.appId, this.subscriberId, Integer.valueOf(this.mode), this.secret, this.filter);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        return Objects.equals(this.appId, subscriber.getAppId()) && Objects.equals(this.subscriberId, subscriber.getSubscriberId()) && Objects.equals(Integer.valueOf(this.mode), Integer.valueOf(subscriber.getMode())) && Objects.equals(this.secret, subscriber.getSecret()) && Objects.equals(this.filter, subscriber.getFilter());
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getSubscriberId() {
        return this.subscriberId;
    }

    public void setSubscriberId(String str) {
        this.subscriberId = str;
    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String str) {
        this.secret = str;
    }

    public ReceiverFilter getFilter() {
        return this.filter;
    }

    public void setFilter(ReceiverFilter receiverFilter) {
        this.filter = receiverFilter;
    }

    public int getLastNotifyDate() {
        return this.lastNotifyDate;
    }

    public void setLastNotifyDate(int i) {
        this.lastNotifyDate = i;
    }
}
