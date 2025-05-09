package com.huawei.hms.kit.awareness.donate;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class DonateRequest implements Parcelable {
    public static final Parcelable.Creator<DonateRequest> CREATOR = new Parcelable.Creator<DonateRequest>() { // from class: com.huawei.hms.kit.awareness.donate.DonateRequest.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DonateRequest[] newArray(int i) {
            return new DonateRequest[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DonateRequest createFromParcel(Parcel parcel) {
            return new DonateRequest(parcel);
        }
    };
    private String contentString;
    private String sessionString;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.sessionString);
        parcel.writeString(this.contentString);
    }

    public String getSessionString() {
        return this.sessionString;
    }

    public String getContentString() {
        return this.contentString;
    }

    public DonateRequest(String str, String str2) {
        this.sessionString = str;
        this.contentString = str2;
    }

    private DonateRequest(Parcel parcel) {
        this.sessionString = "";
        this.contentString = "";
        this.sessionString = parcel.readString();
        this.contentString = parcel.readString();
    }
}
