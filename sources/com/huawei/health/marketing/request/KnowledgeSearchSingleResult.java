package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class KnowledgeSearchSingleResult implements Parcelable {
    public static final Parcelable.Creator<KnowledgeSearchSingleResult> CREATOR = new Parcelable.Creator<KnowledgeSearchSingleResult>() { // from class: com.huawei.health.marketing.request.KnowledgeSearchSingleResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchSingleResult createFromParcel(Parcel parcel) {
            return new KnowledgeSearchSingleResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchSingleResult[] newArray(int i) {
            return new KnowledgeSearchSingleResult[i];
        }
    };

    @SerializedName("highlight")
    private KnowledgeSearchHighlight highlight;

    @SerializedName("_id")
    private String id;

    @SerializedName("num_visit")
    private String numVisit;

    @SerializedName("_type")
    private String type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnowledgeSearchSingleResult(Parcel parcel) {
        this.highlight = (KnowledgeSearchHighlight) parcel.readParcelable(KnowledgeSearchHighlight.class.getClassLoader());
        this.type = parcel.readString();
        this.id = parcel.readString();
        this.numVisit = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.highlight, i);
        parcel.writeString(this.type);
        parcel.writeString(this.id);
        parcel.writeString(this.numVisit);
    }

    public KnowledgeSearchHighlight getHighlight() {
        return this.highlight;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public String getNumVisit() {
        return this.numVisit;
    }

    public String toString() {
        return "{\"highlight\":" + this.highlight + ",\"type\":\"" + this.type + "\",\"id\":\"" + this.id + "\",\"numVisit\":\"" + this.numVisit + "\"}";
    }
}
