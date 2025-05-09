package com.huawei.trade.datatype;

import java.util.List;

/* loaded from: classes9.dex */
public class FunctionAuthResult {
    private int authResult;
    private FunctionBriefInfo functionInfo;
    private List<Integer> memberTypes;

    public int getAuthResult() {
        return this.authResult;
    }

    public List<Integer> getMemberTypes() {
        return this.memberTypes;
    }

    public FunctionBriefInfo getFunctionInfo() {
        return this.functionInfo;
    }

    public void setAuthResult(int i) {
        this.authResult = i;
    }

    public void setMemberTypes(List<Integer> list) {
        this.memberTypes = list;
    }

    public void setFunctionInfo(FunctionBriefInfo functionBriefInfo) {
        this.functionInfo = functionBriefInfo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("authResult=");
        sb.append(this.authResult);
        sb.append(" memberTypes=");
        sb.append(this.memberTypes);
        sb.append(" functionInfo=");
        sb.append(this.functionInfo);
        return sb.toString();
    }
}
