package com.huawei.hms.network.inner.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class DnsNetworkService extends NetworkService {
    public abstract void beginEachRequest(RequestContext requestContext);

    public abstract void dnsPrefetch(String str);

    public abstract void endEachRequest(RequestContext requestContext);

    public abstract int getDnsCache();

    public abstract int getDnsStatus();

    public abstract long getDnsTtl();

    public abstract String getDnsType();

    public abstract List<InetAddress> lookup(String str) throws UnknownHostException;
}
