package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.network.inner.api.DnsNetworkService;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: classes9.dex */
public interface o2 {
    public static final o2 DEFAULT = new a();

    List<InetAddress> lookup(String str) throws UnknownHostException;

    void setDnstime(int i);

    public class a implements o2 {

        /* renamed from: a, reason: collision with root package name */
        public DnsNetworkService f5396a;

        @Override // com.huawei.hms.network.embedded.o2
        public void setDnstime(int i) {
        }

        @Override // com.huawei.hms.network.embedded.o2
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (TextUtils.isEmpty(str)) {
                throw new UnknownHostException("hostname == null");
            }
            if (this.f5396a == null) {
                NetworkService service = NetworkKitInnerImpl.getInstance().getService("dns");
                if (!(service instanceof DnsNetworkService)) {
                    throw new IllegalStateException("DNS service not available");
                }
                this.f5396a = (DnsNetworkService) service;
            }
            return this.f5396a.lookup(str);
        }
    }
}
