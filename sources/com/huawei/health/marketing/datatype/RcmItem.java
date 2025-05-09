package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;
import java.util.Objects;

/* loaded from: classes3.dex */
public class RcmItem {

    @SerializedName("algId")
    private String algId;

    @SerializedName("itemId")
    private String itemId;

    @SerializedName(JsUtil.SCORE)
    private String score;

    public String getItemId() {
        return this.itemId;
    }

    public String getScore() {
        return this.score;
    }

    public String getAlgId() {
        return this.algId;
    }

    public void setItemId(String str) {
        this.itemId = str;
    }

    public void setScore(String str) {
        this.score = str;
    }

    public void setAlgId(String str) {
        this.algId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RcmItem)) {
            return false;
        }
        RcmItem rcmItem = (RcmItem) obj;
        return Objects.equals(getItemId(), rcmItem.getItemId()) && Objects.equals(getScore(), rcmItem.getScore()) && Objects.equals(getAlgId(), rcmItem.getAlgId());
    }

    public int hashCode() {
        return Objects.hash(getItemId(), getScore(), getAlgId());
    }

    public String toString() {
        return "RcmItem{itemId='" + this.itemId + "', score='" + this.score + "', algId='" + this.algId + "'}";
    }
}
