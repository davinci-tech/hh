package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.beans.base.RspBean;
import java.util.List;

/* loaded from: classes5.dex */
public class ReduceDisturbRule extends RspBean {
    private static final String TAG = "ReduceDisturbRule";
    private List<Rule> ruleList;
    private long timeStamp;

    public List<Rule> a() {
        return this.ruleList;
    }
}
