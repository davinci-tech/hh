package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class SearchCondition {

    @SerializedName("filterConditions")
    private List<FilterCondition> mFilterConditions;

    @SerializedName("leftNavigationId")
    private int mLeftNavigationId;

    private SearchCondition(Builder builder) {
        this.mLeftNavigationId = builder.mLeftNavigationId;
        this.mFilterConditions = builder.mFilterConditions;
    }

    public static final class Builder {
        private List<FilterCondition> mFilterConditions;
        private int mLeftNavigationId;

        public Builder leftNavigationId(int i) {
            this.mLeftNavigationId = i;
            return this;
        }

        public Builder filterConditions(List<FilterCondition> list) {
            this.mFilterConditions = list;
            return this;
        }

        public SearchCondition build() {
            return new SearchCondition(this);
        }
    }

    public int getLeftNavigationId() {
        return this.mLeftNavigationId;
    }

    public List<FilterCondition> getFilterConditions() {
        return this.mFilterConditions;
    }
}
