package com.huawei.hms.kit.awareness.internal.communication;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class InnerKitResponse implements Parcelable {
    public static final Parcelable.Creator<InnerKitResponse> CREATOR = new Parcelable.Creator<InnerKitResponse>() { // from class: com.huawei.hms.kit.awareness.internal.communication.InnerKitResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InnerKitResponse[] newArray(int i) {
            return new InnerKitResponse[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InnerKitResponse createFromParcel(Parcel parcel) {
            return new InnerKitResponse(parcel);
        }
    };
    public final AppInfo appInfo;
    public final String key;
    public final int statusCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.appInfo, i);
        parcel.writeString(this.key);
        parcel.writeInt(this.statusCode);
    }

    public InnerKitResponse(AppInfo appInfo, String str, int i) {
        this.appInfo = appInfo;
        this.key = str;
        this.statusCode = i;
    }

    private InnerKitResponse(Parcel parcel) {
        this.appInfo = (AppInfo) parcel.readParcelable(AppInfo.class.getClassLoader());
        this.key = parcel.readString();
        this.statusCode = parcel.readInt();
    }
}
