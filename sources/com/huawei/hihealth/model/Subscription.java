package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.model.SampleEvent;
import java.util.Objects;

/* loaded from: classes.dex */
public class Subscription extends EventTypeInfo {
    public static final Parcelable.Creator<Subscription> CREATOR = new Parcelable.Creator<Subscription>() { // from class: com.huawei.hihealth.model.Subscription.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwE_, reason: merged with bridge method [inline-methods] */
        public Subscription createFromParcel(Parcel parcel) {
            return new Subscription(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Subscription[] newArray(int i) {
            return new Subscription[i];
        }
    };
    private String appId;
    private String detailInfo;
    private int lastNotifyDate;
    private String subscriberId;

    public Subscription(SampleEvent.Type type, String str, String str2, int i) {
        super(type, str, str2, i);
    }

    protected Subscription(Parcel parcel) {
        super(parcel);
        this.appId = parcel.readString();
        this.subscriberId = parcel.readString();
        this.detailInfo = parcel.readString();
    }

    @Override // com.huawei.hihealth.model.EventTypeInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.appId);
        parcel.writeString(this.subscriberId);
        parcel.writeString(this.detailInfo);
    }

    @Override // com.huawei.hihealth.model.EventTypeInfo
    public int hashCode() {
        return super.hashCode() + Objects.hash(this.appId, this.subscriberId, this.detailInfo);
    }

    @Override // com.huawei.hihealth.model.EventTypeInfo
    public boolean equals(Object obj) {
        if (super.equals(obj) && (obj instanceof Subscription)) {
            Subscription subscription = (Subscription) obj;
            if (Objects.equals(this.appId, subscription.getAppId()) && Objects.equals(this.subscriberId, subscription.getSubscriberId()) && Objects.equals(this.detailInfo, subscription.getDetailInfo())) {
                return true;
            }
        }
        return false;
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

    public String getDetailInfo() {
        return this.detailInfo;
    }

    public void setDetailInfo(String str) {
        this.detailInfo = str;
    }

    public int getLastNotifyDate() {
        return this.lastNotifyDate;
    }

    public void setLastNotifyDate(int i) {
        this.lastNotifyDate = i;
    }
}
