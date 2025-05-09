package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.dnkeeper.DNKeeperManager;
import com.huawei.hms.framework.network.restclient.dnkeeper.RequestHost;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.DnsResult;
import com.huawei.openalliance.ad.ho;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class v {
    public static boolean a() {
        try {
            Class.forName("com.huawei.hms.framework.network.restclient.dnkeeper.DNKeeperManager");
            return true;
        } catch (Throwable unused) {
            ho.d("DNSUtil", "check DNKeeperManager available error");
            return false;
        }
    }

    public static void a(String str) {
        if (a()) {
            DNKeeperManager.getInstance().removeCache(str);
        }
    }

    public static void a(Context context) {
        if (a()) {
            ho.b("DNSUtil", "init dnkeeper");
            DNKeeperManager.getInstance().init(context.getApplicationContext());
        }
    }

    public static List<InetAddress> a(Context context, String str) {
        List<DnsResult.Address> addressList;
        ho.b("DNSUtil", "lookup:" + dl.a(str));
        ArrayList arrayList = new ArrayList();
        DnsResult queryIpsSync = DNKeeperManager.getInstance().queryIpsSync(new RequestHost(str));
        if (queryIpsSync != null && (addressList = queryIpsSync.getAddressList()) != null && !addressList.isEmpty()) {
            Iterator<DnsResult.Address> it = addressList.iterator();
            while (it.hasNext()) {
                String value = it.next().getValue();
                if (!TextUtils.isEmpty(value)) {
                    if (ho.a()) {
                        ho.a("DNSUtil", "ip:%s", value);
                    }
                    arrayList.add(InetAddress.getByName(value));
                }
            }
        }
        return arrayList;
    }
}
