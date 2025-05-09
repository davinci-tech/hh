package com.huawei.hms.framework.network.restclient.dnkeeper;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.ThreadPoolExcutorEnhance;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import com.huawei.hms.framework.network.restclient.hwhttp.dns.DnsResult;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.httpclient.HttpClient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class DNKeeperManager {
    private static final String m = "DNKeeperManager";
    private static final String n = "DNKeeperManager";
    private static volatile DNKeeperManager o = new DNKeeperManager();
    private static final int p = 8;
    private static final String q = ":";

    /* renamed from: a, reason: collision with root package name */
    private volatile HttpClient f4561a;
    private String e;
    private int f;
    private PLSharedPreferences h;
    private String k;
    private List<String> l;
    private volatile boolean b = false;
    private volatile boolean c = false;
    private volatile int d = 60000;
    private ConcurrentHashMap<String, h> g = new ConcurrentHashMap<>();
    private final ExecutorService i = new ThreadPoolExcutorEnhance(8, 16, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), ExecutorsUtils.createThreadFactory("DNKeeperManager"));
    private final Object j = new Object();

    public void setRequestIntervalFailed(int i) {
        if (i >= 30000 && i < 600000) {
            this.d = i;
            return;
        }
        Logger.w("DNKeeperManager", "the setRequestIntervalFailed, set to default:" + i);
    }

    public boolean removeCache(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Logger.v("DNKeeperManager", "removeCache host: " + str);
        h hVar = this.g.get(str);
        if (hVar != null) {
            hVar.a(true);
        }
        return true;
    }

    public DnsResult queryIpsSync(RequestHost requestHost) {
        DnsResult dnsResult = new DnsResult();
        if (requestHost == null) {
            return dnsResult;
        }
        String domainName = requestHost.getDomainName();
        Logger.i("DNKeeperManager", domainName + " query mode is single");
        e eVar = new e();
        if (!TextUtils.isEmpty(domainName) && ContextHolder.getAppContext() != null) {
            String substring = StringUtils.substring(domainName, domainName.lastIndexOf(".", domainName.lastIndexOf(".") - 1) + 1);
            List<String> list = this.l;
            if (list != null && !list.contains(substring) && !this.l.isEmpty()) {
                return dnsResult;
            }
            h hVar = this.g.get(domainName);
            if (hVar != null) {
                dnsResult = hVar.a();
                if (!f.a(dnsResult)) {
                    Logger.i("DNKeeperManager", domainName + " queryIps from Map");
                    if (hVar.d() && System.currentTimeMillis() - dnsResult.getCreateTime() > 60000) {
                        Logger.i("DNKeeperManager", "lazyUpdate domain: " + domainName);
                        a(requestHost, domainName, eVar);
                    }
                    dnsResult.setCache(1);
                    return dnsResult;
                }
            }
            PLSharedPreferences pLSharedPreferences = this.h;
            if (pLSharedPreferences != null) {
                dnsResult = f.a(pLSharedPreferences.getString(domainName));
                int status = dnsResult.getStatus();
                if (!f.a(dnsResult) && status != 2) {
                    dnsResult.setCache(1);
                    Logger.i("DNKeeperManager", domainName + " queryIps from SharePreference");
                    if (status == 1) {
                        a(requestHost, domainName, eVar);
                    }
                    return dnsResult;
                }
            }
            String domainName2 = getDomainName();
            if (TextUtils.isEmpty(domainName2)) {
                return dnsResult;
            }
            if (domainName.equals(domainName2)) {
                return a(dnsResult, domainName);
            }
            b(domainName2);
            Future a2 = a(requestHost, domainName2);
            if (a2 != null) {
                try {
                    dnsResult = (DnsResult) a2.get(this.f, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    Logger.w("DNKeeperManager", "queryIpsSync failed ", e);
                }
                if (!f.a(dnsResult)) {
                    dnsResult.setCache(0);
                    Logger.i("DNKeeperManager", domainName + " queryIps from dnkeeper service");
                }
            }
        }
        return dnsResult;
    }

    public DnsResult queryIpsFromSp(String str) {
        Logger.v("DNKeeperManager", "queryIps from SharePreference: %s", str);
        PLSharedPreferences pLSharedPreferences = this.h;
        if (pLSharedPreferences == null) {
            return null;
        }
        DnsResult a2 = f.a(pLSharedPreferences.getString(str));
        a2.setCache(1);
        return a2;
    }

    public DnsResult queryIpsFromCache(String str) {
        DnsResult dnsResult = new DnsResult();
        if (TextUtils.isEmpty(str)) {
            Logger.v("DNKeeperManager", "queryIpsFromCache domain is null");
            return dnsResult;
        }
        h hVar = this.g.get(str);
        if (hVar != null) {
            dnsResult = hVar.a();
        }
        if (f.a(dnsResult)) {
            Logger.v("DNKeeperManager", "no local data = %s", str);
        }
        dnsResult.setCache(1);
        return dnsResult;
    }

    public void queryIpsAsync(RequestHost requestHost, DNSCallback dNSCallback) {
        if (dNSCallback == null) {
            return;
        }
        Logger.v("DNKeeperManager", "queryIpsAsync future = " + this.i.submit(new b(dNSCallback, requestHost)));
    }

    public boolean isInit() {
        return this.b;
    }

    public void init(Context context, int i) {
        CheckParamUtils.checkNotNull(context, "context == null");
        Context applicationContext = context.getApplicationContext();
        NetworkKit.init(applicationContext, null);
        try {
            this.e = ContextHolder.getResourceContext().getString(R.string._2130851404_res_0x7f02364c);
        } catch (Throwable th) {
            Logger.v("DNKeeperManager", "DEFAULT_DOMAIN_NAME failed: " + th);
        }
        Logger.v("DNKeeperManager", "defaultDomain " + this.e);
        if (i < 0 || i > 10000) {
            Logger.w("DNKeeperManager", "maybe you need set a time between 0-10000");
            i = 10000;
        }
        this.f = i;
        if (this.b) {
            return;
        }
        this.b = true;
        this.i.execute(new a(applicationContext));
    }

    public void init(Context context) {
        init(context, 10000);
    }

    public h getRequestRecord(String str) {
        return this.g.get(str);
    }

    public HttpClient getHttpClient() {
        if (this.f4561a == null) {
            synchronized (this.j) {
                if (this.f4561a == null) {
                    this.f4561a = new HttpClient.Builder().build();
                }
            }
        }
        return this.f4561a;
    }

    public String getDomainName() {
        String synGetGrsUrl = new GrsClient(ContextHolder.getResourceContext(), new GrsBaseInfo()).synGetGrsUrl(com.huawei.hms.framework.network.restclient.dnkeeper.d.k, "ROOT");
        Logger.v("DNKeeperManager", "getDomainName: " + synGetGrsUrl);
        if (!TextUtils.isEmpty(synGetGrsUrl) && synGetGrsUrl.contains(":")) {
            String[] split = synGetGrsUrl.split(":");
            if (split.length == 3) {
                synGetGrsUrl = split[0] + ":" + split[1];
                this.k = split[2];
            }
        }
        if (TextUtils.isEmpty(NetworkUtil.getHost(synGetGrsUrl))) {
            PLSharedPreferences pLSharedPreferences = this.h;
            if (pLSharedPreferences != null) {
                synGetGrsUrl = pLSharedPreferences.getString(com.huawei.hms.framework.network.restclient.dnkeeper.d.e);
            }
            if (TextUtils.isEmpty(NetworkUtil.getHost(synGetGrsUrl))) {
                synGetGrsUrl = this.e;
            }
        }
        return NetworkUtil.getHost(synGetGrsUrl);
    }

    public HashMap<String, DnsResult> batchQueryIpsSync(HashSet<RequestHost> hashSet) {
        HashMap hashMap;
        e eVar = new e();
        HashMap<String, DnsResult> hashMap2 = new HashMap<>();
        HashSet<RequestHost> hashSet2 = new HashSet<>();
        HashSet<RequestHost> hashSet3 = new HashSet<>();
        String domainName = getDomainName();
        if (hashSet != null && ContextHolder.getAppContext() != null && !TextUtils.isEmpty(domainName)) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator<RequestHost> it = hashSet.iterator();
            while (true) {
                hashMap = null;
                if (!it.hasNext()) {
                    break;
                }
                RequestHost next = it.next();
                String domainName2 = next.getDomainName();
                if (!TextUtils.isEmpty(domainName2)) {
                    String substring = StringUtils.substring(domainName2, domainName2.lastIndexOf(".", domainName2.lastIndexOf(".") - 1) + 1);
                    List<String> list = this.l;
                    if (list != null && !list.contains(substring) && !this.l.isEmpty()) {
                        Logger.i("DNKeeperManager", domainName2 + " is not included in allowlist");
                    } else if (domainName2.equals(domainName)) {
                        Logger.i("DNKeeperManager", "DNKeeper domainName queryIps from SharePreference");
                        PLSharedPreferences pLSharedPreferences = this.h;
                        DnsResult a2 = pLSharedPreferences != null ? f.a(pLSharedPreferences.getString(domainName2)) : null;
                        if (f.a(a2)) {
                            a2 = f.a(a2, this.k);
                        }
                        hashMap2.put(domainName2, a2);
                    } else {
                        h hVar = this.g.get(domainName2);
                        if (hVar != null) {
                            DnsResult a3 = hVar.a();
                            if (!f.a(a3)) {
                                if (hVar.d() && currentTimeMillis - a3.getCreateTime() > 60000) {
                                    hashSet3.add(next);
                                }
                                hashMap2.put(domainName2, a3);
                            }
                        }
                        hashSet2.add(next);
                    }
                }
            }
            if (hashSet2.isEmpty()) {
                if (!hashSet3.isEmpty()) {
                    Logger.v("DNKeeperManager", "lazyUpdate domains: " + hashSet3);
                    eVar.put("trigger_type", "dns_lazy_update");
                    a(hashSet3, domainName, eVar);
                }
                Logger.i("DNKeeperManager", hashMap2.keySet().toString() + " queryIps from Map");
                return hashMap2;
            }
            hashSet2.addAll(hashSet3);
            eVar.put("trigger_type", "dns_sync_query");
            HashSet<Future> a4 = a(hashSet2, domainName, eVar);
            if (a4 != null && !a4.isEmpty()) {
                if (a4.size() != 1) {
                    Logger.i("DNKeeperManager", "queryIps from futureSet");
                    try {
                        Iterator<Future> it2 = a4.iterator();
                        while (it2.hasNext()) {
                            it2.next().get(this.f, TimeUnit.MILLISECONDS);
                        }
                    } catch (Exception e) {
                        Logger.w("DNKeeperManager", "queryIpsSync failed ", e);
                    }
                    Iterator<RequestHost> it3 = hashSet2.iterator();
                    while (it3.hasNext()) {
                        String domainName3 = it3.next().getDomainName();
                        h hVar2 = this.g.get(domainName3);
                        if (hVar2 != null) {
                            hashMap2.put(domainName3, hVar2.a());
                        }
                    }
                    Logger.v("DNKeeperManager", hashMap2.toString());
                    return hashMap2;
                }
                try {
                    hashMap = (HashMap) a4.iterator().next().get(this.f, TimeUnit.MILLISECONDS);
                } catch (Exception e2) {
                    Logger.w("DNKeeperManager", "queryIpsSync failed ", e2);
                }
                if (hashMap != null) {
                    Iterator it4 = hashMap.entrySet().iterator();
                    while (it4.hasNext()) {
                        if (f.a((DnsResult) ((Map.Entry) it4.next()).getValue())) {
                            it4.remove();
                        }
                    }
                    hashMap2.putAll(hashMap);
                    Logger.i("DNKeeperManager", hashMap2.keySet().toString() + " queryIps from dnkeeper service");
                    return hashMap2;
                }
                Logger.i("DNKeeperManager", "dnsResults is null");
            }
            Logger.i("DNKeeperManager", "queryIps from SharePreference");
            if (this.h != null) {
                Iterator<RequestHost> it5 = hashSet2.iterator();
                while (it5.hasNext()) {
                    RequestHost next2 = it5.next();
                    hashMap2.put(next2.getDomainName(), f.a(this.h.getString(next2.getDomainName())));
                }
            }
        }
        return hashMap2;
    }

    public void batchQueryIpsAsync(HashSet<RequestHost> hashSet, DNSBatchCallback dNSBatchCallback) {
        if (dNSBatchCallback == null) {
            return;
        }
        Logger.v("DNKeeperManager", "batchQueryIpsAsync future = " + this.i.submit(new d(dNSBatchCallback, hashSet)));
    }

    public static DNKeeperManager getInstance() {
        return o;
    }

    private void b(String str) {
        if (this.c) {
            return;
        }
        synchronized (DNKeeperManager.class) {
            if (this.c) {
                return;
            }
            this.c = true;
            this.i.execute(new c(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RequestHost requestHost, String str, e eVar) {
        String domainName = requestHost.getDomainName();
        h a2 = a(domainName);
        if (a(a2)) {
            return;
        }
        Set<String> a3 = a();
        HashSet<RequestHost> a4 = a(a3);
        HashMap<String, h> b2 = b(a3);
        b2.put(domainName, a2);
        a4.add(requestHost);
        synchronized (this.j) {
            Future b3 = a2.b();
            if (b3 == null) {
                b3 = a(a4, str, b2, eVar);
                Logger.i("DNKeeperManager", "future == null");
                a2.a(0L);
                a2.a(b3);
            }
            Logger.v("DNKeeperManager", "submitLazyRequest future = " + b3);
        }
    }

    private HashMap<String, h> b(Set<String> set) {
        h putIfAbsent;
        HashMap<String, h> hashMap = new HashMap<>();
        if (set != null) {
            for (String str : set) {
                h hVar = this.g.get(str);
                if (hVar == null && (putIfAbsent = this.g.putIfAbsent(str, (hVar = new h()))) != null) {
                    hVar = putIfAbsent;
                }
                hashMap.put(str, hVar);
            }
        }
        return hashMap;
    }

    private boolean a(h hVar) {
        long currentTimeMillis = System.currentTimeMillis() - hVar.c();
        if (currentTimeMillis >= this.d) {
            return false;
        }
        Logger.i("DNKeeperManager", "now - time = " + currentTimeMillis);
        return true;
    }

    private void a(RequestHost requestHost, String str, e eVar) {
        String domainName = getDomainName();
        if (TextUtils.isEmpty(domainName) || str.equals(domainName)) {
            return;
        }
        eVar.put("trigger_type", "dns_lazy_update");
        b(requestHost, domainName, eVar);
    }

    private Future a(HashSet<RequestHost> hashSet, String str, HashMap<String, h> hashMap, e eVar) {
        return this.i.submit(new com.huawei.hms.framework.network.restclient.dnkeeper.b(hashSet, str, hashMap, this.h, eVar));
    }

    private Future a(RequestHost requestHost, String str) {
        Future b2;
        h a2 = a(requestHost.getDomainName());
        if (a(a2)) {
            return null;
        }
        synchronized (this.j) {
            b2 = a2.b();
            if (b2 == null) {
                Logger.i("DNKeeperManager", "future == null");
                b2 = this.i.submit(new com.huawei.hms.framework.network.restclient.dnkeeper.c(requestHost, str, a2, this.h));
                a2.a(0L);
                a2.a(b2);
            }
            Logger.v("DNKeeperManager", "submitRequest future = " + b2);
        }
        return b2;
    }

    private Set<String> a() {
        Map<String, ?> all;
        PLSharedPreferences pLSharedPreferences = this.h;
        Set<String> keySet = (pLSharedPreferences == null || (all = pLSharedPreferences.getAll()) == null) ? null : all.keySet();
        if (keySet != null) {
            keySet.remove(com.huawei.hms.framework.network.restclient.dnkeeper.d.n);
            keySet.remove(com.huawei.hms.framework.network.restclient.dnkeeper.d.e);
        }
        return keySet;
    }

    private HashSet<RequestHost> a(Set<String> set) {
        HashSet<RequestHost> hashSet = new HashSet<>();
        if (set != null) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                RequestHost requestHost = new RequestHost(it.next());
                requestHost.enableAccelerate(true);
                requestHost.setDnsFailType("lazyUpdate");
                hashSet.add(requestHost);
            }
        }
        return hashSet;
    }

    private HashSet<Future> a(HashSet<RequestHost> hashSet, String str, e eVar) {
        HashSet<Future> hashSet2 = new HashSet<>();
        HashMap<String, h> hashMap = new HashMap<>();
        long currentTimeMillis = System.currentTimeMillis();
        Logger.v("DNKeeperManager", "requestHosts: " + Arrays.toString(hashSet.toArray()));
        Iterator<RequestHost> it = hashSet.iterator();
        boolean z = true;
        boolean z2 = true;
        while (it.hasNext()) {
            String domainName = it.next().getDomainName();
            h a2 = a(domainName);
            hashMap.put(domainName, a2);
            long c2 = currentTimeMillis - a2.c();
            if (c2 < this.d) {
                Logger.v("DNKeeperManager", "now - time = " + c2);
            } else {
                z = false;
            }
            if (a2.b() == null) {
                z2 = false;
            } else {
                hashSet2.add(a2.b());
            }
        }
        if (z) {
            Logger.i("DNKeeperManager", "request needSuppressed");
            return null;
        }
        if (!z2) {
            Logger.i("DNKeeperManager", "request use NewFuture instead of ExistedFuture");
            Future a3 = a(hashSet, str, hashMap, eVar);
            hashSet2.clear();
            hashSet2.add(a3);
            for (h hVar : hashMap.values()) {
                hVar.a(0L);
                hVar.a(a3);
            }
        }
        return hashSet2;
    }

    class b implements Callable<Void> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ DNSCallback f4563a;
        final /* synthetic */ RequestHost b;

        @Override // java.util.concurrent.Callable
        public Void call() {
            this.f4563a.onResult(DNKeeperManager.this.queryIpsSync(this.b));
            return null;
        }

        b(DNSCallback dNSCallback, RequestHost requestHost) {
            this.f4563a = dNSCallback;
            this.b = requestHost;
        }
    }

    class d implements Callable<Void> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ DNSBatchCallback f4565a;
        final /* synthetic */ HashSet b;

        @Override // java.util.concurrent.Callable
        public Void call() {
            this.f4565a.onResult(DNKeeperManager.this.batchQueryIpsSync(this.b));
            return null;
        }

        d(DNSBatchCallback dNSBatchCallback, HashSet hashSet) {
            this.f4565a = dNSBatchCallback;
            this.b = hashSet;
        }
    }

    private DnsResult a(DnsResult dnsResult, String str) {
        PLSharedPreferences pLSharedPreferences = this.h;
        if (pLSharedPreferences != null) {
            dnsResult = f.a(pLSharedPreferences.getString(str));
        }
        if (f.a(dnsResult)) {
            dnsResult = f.a(dnsResult, this.k);
        }
        dnsResult.setCache(1);
        return dnsResult;
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f4562a;

        @Override // java.lang.Runnable
        public void run() {
            DNKeeperManager.this.h = new PLSharedPreferences(this.f4562a, com.huawei.hms.framework.network.restclient.dnkeeper.d.m);
            String string = DNKeeperManager.this.h.getString(com.huawei.hms.framework.network.restclient.dnkeeper.d.n);
            if (!TextUtils.isEmpty(string)) {
                DNKeeperManager.this.l = Arrays.asList(string.split("&"));
            }
            DNKeeperManager dNKeeperManager = DNKeeperManager.this;
            dNKeeperManager.f4561a = dNKeeperManager.getHttpClient();
        }

        a(Context context) {
            this.f4562a = context;
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4564a;

        @Override // java.lang.Runnable
        public void run() {
            boolean a2 = f.a(DNKeeperManager.this.h, this.f4564a);
            Logger.v("DNKeeperManager", "checkDNKeeperIP " + a2);
            if (a2) {
                RequestHost requestHost = new RequestHost(this.f4564a);
                requestHost.enableAccelerate(true);
                e eVar = new e();
                eVar.put("trigger_type", "dns_init");
                DNKeeperManager.this.b(requestHost, this.f4564a, eVar);
            }
        }

        c(String str) {
            this.f4564a = str;
        }
    }

    private h a(String str) {
        h hVar;
        synchronized (this.j) {
            h hVar2 = this.g.get(str);
            if (hVar2 != null || (hVar = this.g.putIfAbsent(str, (hVar2 = new h()))) == null) {
                hVar = hVar2;
            }
        }
        return hVar;
    }

    private DNKeeperManager() {
    }
}
