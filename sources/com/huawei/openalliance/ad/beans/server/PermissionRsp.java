package com.huawei.openalliance.ad.beans.server;

import com.huawei.openalliance.ad.beans.base.RspBean;
import com.huawei.openalliance.ad.beans.metadata.Permission;
import java.util.List;

/* loaded from: classes5.dex */
public class PermissionRsp extends RspBean {
    private List<Permission> permissions;
    private int retcode = -1;

    public List<Permission> b() {
        return this.permissions;
    }

    public int a() {
        return this.retcode;
    }
}
