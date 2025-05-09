package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes8.dex */
public class SceneSearchResult extends BaseResponse {
    public static final Parcelable.Creator<SceneSearchResult> CREATOR = new Parcelable.Creator<SceneSearchResult>() { // from class: com.huawei.health.marketing.request.SceneSearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SceneSearchResult createFromParcel(Parcel parcel) {
            return new SceneSearchResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SceneSearchResult[] newArray(int i) {
            return new SceneSearchResult[i];
        }
    };

    @SerializedName(ParsedFieldTag.RESULTS)
    private List<SceneSearchSingleResult> results;

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SceneSearchResult(Parcel parcel) {
        super(parcel);
        this.results = parcel.createTypedArrayList(SceneSearchSingleResult.CREATOR);
    }

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.results);
    }

    public List<SceneSearchSingleResult> getResults() {
        return this.results;
    }

    public String toString() {
        return "{\"results\":" + this.results + '}';
    }
}
