package com.huawei.openalliance.ad.inter.data;

import com.huawei.openalliance.ad.utils.cz;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class RewardItem implements Serializable {
    private static final long serialVersionUID = 30414300;
    private int amount;
    private String type;

    public String getType() {
        return cz.c(this.type);
    }

    public int getAmount() {
        return this.amount;
    }

    public void a(String str) {
        this.type = str;
    }

    public void a(int i) {
        this.amount = i;
    }
}
