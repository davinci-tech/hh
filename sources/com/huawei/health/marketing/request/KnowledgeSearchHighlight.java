package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes8.dex */
public class KnowledgeSearchHighlight implements Parcelable {
    public static final Parcelable.Creator<KnowledgeSearchHighlight> CREATOR = new Parcelable.Creator<KnowledgeSearchHighlight>() { // from class: com.huawei.health.marketing.request.KnowledgeSearchHighlight.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchHighlight createFromParcel(Parcel parcel) {
            return new KnowledgeSearchHighlight(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KnowledgeSearchHighlight[] newArray(int i) {
            return new KnowledgeSearchHighlight[i];
        }
    };

    @SerializedName("title")
    private List<String> titleList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected KnowledgeSearchHighlight(Parcel parcel) {
        this.titleList = parcel.createStringArrayList();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.titleList);
    }

    public List<String> getTitleList() {
        return this.titleList;
    }

    public String toString() {
        return "{\"titleList\":" + this.titleList + '}';
    }
}
