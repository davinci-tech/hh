package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes8.dex */
public class KnowledgeSearchResult implements Parcelable {
    public static final Parcelable.Creator<KnowledgeSearchResult> CREATOR = new Parcelable.Creator<KnowledgeSearchResult>() { // from class: com.huawei.health.marketing.request.KnowledgeSearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchResult createFromParcel(Parcel parcel) {
            return new KnowledgeSearchResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchResult[] newArray(int i) {
            return new KnowledgeSearchResult[i];
        }
    };

    @SerializedName("knowList")
    private List<KnowledgeSearchSingleResult> knowList;

    @SerializedName("totalSize")
    private int totalSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnowledgeSearchResult(Parcel parcel) {
        this.totalSize = parcel.readInt();
        this.knowList = parcel.createTypedArrayList(KnowledgeSearchSingleResult.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.totalSize);
        parcel.writeTypedList(this.knowList);
    }

    public List<KnowledgeSearchSingleResult> getKnowList() {
        return this.knowList;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public static Parcelable.Creator<KnowledgeSearchResult> getCREATOR() {
        return CREATOR;
    }

    public String toString() {
        return "{\"totalSize\":" + this.totalSize + ",\"knowList\":" + this.knowList + '}';
    }
}
