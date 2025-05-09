package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes7.dex */
public class ParamValidDetail {

    @SerializedName("failList")
    private List<ParamValidFailDetail> failList;

    @SerializedName("succCount")
    private int succCount;

    @SerializedName("totalCount")
    private int totalCount;

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public int getSuccCount() {
        return this.succCount;
    }

    public void setSuccCount(int i) {
        this.succCount = i;
    }

    public List<ParamValidFailDetail> getFailList() {
        return this.failList;
    }

    public void setFailList(List<ParamValidFailDetail> list) {
        this.failList = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ParamValidDetail{totalCount = ");
        stringBuffer.append(this.totalCount);
        stringBuffer.append(", succCount = ").append(this.succCount);
        stringBuffer.append(", ParamValidFailDetail = ").append(this.failList);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
