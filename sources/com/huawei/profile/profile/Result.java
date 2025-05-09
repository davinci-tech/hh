package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class Result implements Parcelable {
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() { // from class: com.huawei.profile.profile.Result.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result[] newArray(int i) {
            return new Result[i];
        }
    };
    private ResultType opResult;
    private Map<String, ResultType> syncDevResults;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Result(Parcel parcel) {
        this.opResult = ResultType.valueOf(parcel.readString());
        this.syncDevResults = new HashMap();
        for (Map.Entry entry : parcel.readHashMap(Result.class.getClassLoader()).entrySet()) {
            this.syncDevResults.put(entry.getKey(), ResultType.valueOf((String) entry.getValue()));
        }
    }

    public Result() {
    }

    public ResultType getOpResult() {
        return this.opResult;
    }

    public void setOpResult(ResultType resultType) {
        this.opResult = resultType;
    }

    public Map<String, ResultType> getSyncDevResults() {
        return this.syncDevResults;
    }

    public void setSyncDevResults(Map<String, ResultType> map) {
        this.syncDevResults = map;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ResultType resultType = this.opResult;
        if (resultType == null) {
            resultType = ResultType.ERROR;
        }
        parcel.writeString(resultType.name());
        if (this.syncDevResults == null) {
            parcel.writeMap(Collections.emptyMap());
            return;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ResultType> entry : this.syncDevResults.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().name());
        }
        parcel.writeMap(hashMap);
    }
}
