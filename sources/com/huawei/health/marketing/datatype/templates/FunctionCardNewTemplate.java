package com.huawei.health.marketing.datatype.templates;

import com.huawei.health.marketing.datatype.FunctionCardContent;
import java.util.List;

/* loaded from: classes8.dex */
public class FunctionCardNewTemplate extends FunctionCardTemplate {
    private Boolean intelligentFlag;
    private Integer largeCardNum;
    private Integer smallCardNum;
    private String sortingRules;

    public FunctionCardNewTemplate(String str, Boolean bool, String str2, List<FunctionCardContent> list) {
        super(str, bool, str2, list);
    }

    public Boolean getIntelligentFlag() {
        return this.intelligentFlag;
    }

    public void setIntelligentFlag(Boolean bool) {
        this.intelligentFlag = bool;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public void setSortingRules(String str) {
        this.sortingRules = str;
    }

    public Integer getLargeCardNum() {
        return this.largeCardNum;
    }

    public void setLargeCardNum(Integer num) {
        this.largeCardNum = num;
    }

    public Integer getSmallCardNum() {
        return this.smallCardNum;
    }

    public void setSmallCardNum(Integer num) {
        this.smallCardNum = num;
    }
}
