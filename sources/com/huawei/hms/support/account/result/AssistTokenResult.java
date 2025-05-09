package com.huawei.hms.support.account.result;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class AssistTokenResult implements Parcelable {
    public static final Parcelable.Creator<AssistTokenResult> CREATOR = new Parcelable.Creator<AssistTokenResult>() { // from class: com.huawei.hms.support.account.result.AssistTokenResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AssistTokenResult createFromParcel(Parcel parcel) {
            return new AssistTokenResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AssistTokenResult[] newArray(int i) {
            return new AssistTokenResult[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f5946a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AssistTokenResult() {
    }

    private AssistTokenResult(Parcel parcel) {
        this.f5946a = parcel.readString();
    }

    public void setAssistToken(String str) {
        this.f5946a = str;
    }

    public String getAssistToken() {
        return this.f5946a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f5946a);
    }
}
