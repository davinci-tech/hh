package defpackage;

import android.os.IBinder;
import com.huawei.wearengine.ClientToken;
import com.huawei.wearengine.common.ApplicationIdManager;
import com.huawei.wearengine.core.common.ClientBinderDied;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class tor implements ApplicationIdManager {
    private ConcurrentHashMap<Integer, IBinder> c;
    private ConcurrentHashMap<ClientToken, String> e = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Integer, Map<Integer, Integer>> f17283a = new ConcurrentHashMap<>();
    private IBinder.DeathRecipient d = new IBinder.DeathRecipient() { // from class: tor.3
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tos.a("ClientManager", "BinderService binderDied enter");
            Iterator it = tor.this.e.entrySet().iterator();
            while (it.hasNext()) {
                ClientToken clientToken = (ClientToken) ((Map.Entry) it.next()).getKey();
                if (!clientToken.asBinder().pingBinder()) {
                    tor.this.e(clientToken);
                    it.remove();
                }
            }
        }
    };
    private ConcurrentHashMap<Integer, String> b = new ConcurrentHashMap<>();

    public tor(ConcurrentHashMap<Integer, IBinder> concurrentHashMap) {
        this.c = concurrentHashMap;
    }

    public void d(int i, ClientToken clientToken, String str) {
        if (clientToken != null && tot.c(str) && tot.a(i, str)) {
            this.e.put(clientToken, str);
        }
    }

    public IBinder.DeathRecipient fcX_() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ClientToken clientToken) {
        String str = this.e.get(clientToken);
        for (IBinder iBinder : this.c.values()) {
            if (iBinder instanceof ClientBinderDied) {
                ((ClientBinderDied) iBinder).handleClientBinderDied(str);
            }
        }
        clientToken.asBinder().unlinkToDeath(this.d, 0);
    }

    public void a(int i, int i2, int i3) {
        if (!this.f17283a.containsKey(Integer.valueOf(i))) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            concurrentHashMap.put(Integer.valueOf(i2), Integer.valueOf(i3));
            Map<Integer, Integer> putIfAbsent = this.f17283a.putIfAbsent(Integer.valueOf(i), concurrentHashMap);
            if (putIfAbsent == null) {
                return;
            }
            putIfAbsent.put(Integer.valueOf(i2), Integer.valueOf(i3));
            return;
        }
        Map<Integer, Integer> map = this.f17283a.get(Integer.valueOf(i));
        if (map != null) {
            map.put(Integer.valueOf(i2), Integer.valueOf(i3));
        }
    }

    public int e(int i, int i2) {
        Map<Integer, Integer> map;
        if (this.f17283a.containsKey(Integer.valueOf(i)) && (map = this.f17283a.get(Integer.valueOf(i))) != null) {
            return map.get(Integer.valueOf(i2)).intValue();
        }
        tos.a("ClientManager", "getSdkApiLevel default value");
        return 0;
    }

    @Override // com.huawei.wearengine.common.ApplicationIdManager
    public void setApplicationId(Integer num, String str) {
        this.b.put(num, str);
        tos.a("ClientManager", "set Application pid is " + num + " applicationId is " + str + " mapSize " + this.b.size());
    }

    @Override // com.huawei.wearengine.common.ApplicationIdManager
    public String getApplicationIdByPid(Integer num) {
        tos.a("ClientManager", "getApplication pid is " + num + " mapSize " + this.b.size());
        return this.b.containsKey(num) ? this.b.get(num) : "";
    }
}
