package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class KnowledgeSearchResultWrapper implements Parcelable {
    public static final Parcelable.Creator<KnowledgeSearchResultWrapper> CREATOR = new Parcelable.Creator<KnowledgeSearchResultWrapper>() { // from class: com.huawei.health.marketing.request.KnowledgeSearchResultWrapper.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchResultWrapper createFromParcel(Parcel parcel) {
            return new KnowledgeSearchResultWrapper(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchResultWrapper[] newArray(int i) {
            return new KnowledgeSearchResultWrapper[i];
        }
    };

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private KnowledgeSearchResult result;

    @SerializedName("status")
    private String status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnowledgeSearchResultWrapper(Parcel parcel) {
        this.status = parcel.readString();
        this.code = parcel.readInt();
        this.message = parcel.readString();
        this.result = (KnowledgeSearchResult) parcel.readParcelable(KnowledgeSearchResult.class.getClassLoader());
    }

    public String getStatus() {
        return this.status;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public KnowledgeSearchResult getResult() {
        return this.result;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.status);
        parcel.writeInt(this.code);
        parcel.writeString(this.message);
        parcel.writeParcelable(this.result, i);
    }

    public String toString() {
        return "{\"status\":\"" + this.status + "\",\"code\":" + this.code + ",\"message\":\"" + this.message + "\",\"result\":" + this.result + '}';
    }
}
