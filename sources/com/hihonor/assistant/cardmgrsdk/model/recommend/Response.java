package com.hihonor.assistant.cardmgrsdk.model.recommend;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes8.dex */
public class Response implements Parcelable {
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() { // from class: com.hihonor.assistant.cardmgrsdk.model.recommend.Response.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Response[] newArray(int i) {
            return new Response[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Response createFromParcel(Parcel parcel) {
            return new Response(parcel);
        }
    };
    public String requestId;
    public List<PageInfo> result;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.result);
        parcel.writeString(this.requestId);
    }

    public void setResult(List<PageInfo> list) {
        this.result = list;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public List<PageInfo> getResult() {
        return this.result;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public Response(Parcel parcel) {
        this.result = parcel.createTypedArrayList(PageInfo.CREATOR);
        this.requestId = parcel.readString();
    }

    public Response() {
    }
}
