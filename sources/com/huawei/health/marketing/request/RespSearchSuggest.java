package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class RespSearchSuggest extends BaseResponse implements Parcelable {
    public static final Parcelable.Creator<RespSearchSuggest> CREATOR = new Parcelable.Creator<RespSearchSuggest>() { // from class: com.huawei.health.marketing.request.RespSearchSuggest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RespSearchSuggest createFromParcel(Parcel parcel) {
            return new RespSearchSuggest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RespSearchSuggest[] newArray(int i) {
            return new RespSearchSuggest[i];
        }
    };
    private List<String> results;

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RespSearchSuggest(Parcel parcel) {
        super(parcel);
        if (this.results == null) {
            this.results = new ArrayList();
        }
        parcel.readStringList(this.results);
    }

    public List<String> getResults() {
        return this.results;
    }

    public void setResults(List<String> list) {
        this.results = list;
    }

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringList(this.results);
    }
}
