package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class RespGlobalSearchResult extends BaseResponse implements Parcelable {
    public static final Parcelable.Creator<RespGlobalSearchResult> CREATOR = new Parcelable.Creator<RespGlobalSearchResult>() { // from class: com.huawei.health.marketing.request.RespGlobalSearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RespGlobalSearchResult createFromParcel(Parcel parcel) {
            return new RespGlobalSearchResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RespGlobalSearchResult[] newArray(int i) {
            return new RespGlobalSearchResult[i];
        }
    };
    private List<GlobalSearchResult> results;

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RespGlobalSearchResult(Parcel parcel) {
        super(parcel);
        if (this.results == null) {
            this.results = new ArrayList();
        }
        parcel.readTypedList(this.results, GlobalSearchResult.CREATOR);
    }

    public List<GlobalSearchResult> getResults() {
        return this.results;
    }

    public void setResults(List<GlobalSearchResult> list) {
        this.results = list;
    }

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.results);
    }
}
