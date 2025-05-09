package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class GlobalSearchResult implements Parcelable {
    public static final Parcelable.Creator<GlobalSearchResult> CREATOR = new Parcelable.Creator<GlobalSearchResult>() { // from class: com.huawei.health.marketing.request.GlobalSearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalSearchResult createFromParcel(Parcel parcel) {
            return new GlobalSearchResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GlobalSearchResult[] newArray(int i) {
            return new GlobalSearchResult[i];
        }
    };
    private String categoryId;
    private List<GlobalSearchContent> content;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GlobalSearchResult(Parcel parcel) {
        this.categoryId = parcel.readString();
        if (this.content == null) {
            this.content = new ArrayList();
        }
        parcel.readTypedList(this.content, GlobalSearchContent.CREATOR);
    }

    public GlobalSearchResult() {
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public List<GlobalSearchContent> getContent() {
        return this.content;
    }

    public void setContent(List<GlobalSearchContent> list) {
        this.content = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.categoryId);
        parcel.writeTypedList(this.content);
    }
}
