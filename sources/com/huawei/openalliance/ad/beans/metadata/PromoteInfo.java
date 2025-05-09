package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.utils.cz;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class PromoteInfo implements Serializable {
    private static final long serialVersionUID = 9086449717970534300L;
    private String name;
    private int type;

    public int getType() {
        return this.type;
    }

    public String getName() {
        return cz.c(this.name);
    }

    public void a(String str) {
        this.name = str;
    }

    public void a(int i) {
        this.type = i;
    }
}
