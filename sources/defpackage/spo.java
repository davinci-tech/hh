package defpackage;

import android.text.TextUtils;
import com.huawei.unitedevice.p2p.IdentityInfo;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class spo {

    /* renamed from: a, reason: collision with root package name */
    private static final spo f17206a = new spo();
    private Map<spr, Map<IdentityInfo, List<IdentityInfo>>> d = new ConcurrentHashMap(16);

    private spo() {
    }

    public int c(IdentityInfo identityInfo, IdentityInfo identityInfo2, spr sprVar) {
        LogUtil.c("ReceiverCallbackManager", "addReceiverCallback enter");
        if (identityInfo == null || identityInfo2 == null || !a(identityInfo.getPackageName(), identityInfo2.getPackageName(), sprVar)) {
            LogUtil.a("ReceiverCallbackManager", "addReceiverCallback parameters is invalid");
            return 5;
        }
        spr c = c(sprVar);
        if (c == null) {
            int size = e(identityInfo, identityInfo2).size();
            if (size >= 10) {
                LogUtil.c("ReceiverCallbackManager", "addReceiverCallback exceed max receiver. max size: ", Integer.valueOf(size));
                return 11;
            }
            LogUtil.c("ReceiverCallbackManager", "addReceiverCallback new receiver");
            b(identityInfo, identityInfo2, sprVar);
            return 0;
        }
        LogUtil.c("ReceiverCallbackManager", "addReceiverCallback already has receiver");
        Map<IdentityInfo, List<IdentityInfo>> map = this.d.get(c);
        if (map == null) {
            map = new ConcurrentHashMap<>(16);
        }
        this.d.remove(c);
        this.d.put(sprVar, map);
        b(identityInfo, identityInfo2, map);
        return 0;
    }

    public List<spr> d(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        ArrayList arrayList = new ArrayList(16);
        if (this.d.isEmpty()) {
            LogUtil.a("ReceiverCallbackManager", "getReceiverCallback deviceReceiverCallbackMap is empty");
            return arrayList;
        }
        if (identityInfo == null || identityInfo2 == null) {
            LogUtil.a("ReceiverCallbackManager", "getReceiverCallback parameters[srcInfo, dstInfo] is invalid");
            return arrayList;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            LogUtil.a("ReceiverCallbackManager", "getReceiverCallback parameters[deviceId, packageName] is invalid");
            return arrayList;
        }
        List<spr> e = e(identityInfo2, identityInfo);
        if (e.isEmpty()) {
            LogUtil.a("ReceiverCallbackManager", "getReceiverCallback can not find receiver.");
        }
        return e;
    }

    public void a(spr sprVar) {
        if (sprVar == null) {
            LogUtil.a("ReceiverCallbackManager", "removeReceiverCallback receiver is invalid.");
        } else if (this.d.remove(sprVar) == null) {
            LogUtil.a("ReceiverCallbackManager", "remove receiver is null.");
        }
    }

    private void b(IdentityInfo identityInfo, IdentityInfo identityInfo2, spr sprVar) {
        LogUtil.c("ReceiverCallbackManager", "addNewReceiverCallback enter");
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(identityInfo2);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put(identityInfo, arrayList);
        this.d.put(sprVar, concurrentHashMap);
    }

    private void b(IdentityInfo identityInfo, IdentityInfo identityInfo2, Map<IdentityInfo, List<IdentityInfo>> map) {
        List<IdentityInfo> list = map.get(identityInfo);
        if (list == null) {
            list = new ArrayList<>(16);
            map.put(identityInfo, list);
        }
        if (!list.contains(identityInfo2)) {
            LogUtil.c("ReceiverCallbackManager", "updateIdentityInfo new dstPkgInfo");
            list.add(identityInfo2);
        } else {
            LogUtil.c("ReceiverCallbackManager", "updateIdentityInfo already has dstPkgInfo");
        }
    }

    private List<spr> e(IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        LogUtil.c("ReceiverCallbackManager", "getReceiverList enter");
        ArrayList arrayList = new ArrayList(16);
        if (this.d.isEmpty()) {
            LogUtil.a("ReceiverCallbackManager", "getReceiverList deviceReceiverCallbackMap is empty");
            return arrayList;
        }
        for (Map.Entry<spr, Map<IdentityInfo, List<IdentityInfo>>> entry : this.d.entrySet()) {
            Map<IdentityInfo, List<IdentityInfo>> value = entry.getValue();
            if (value != null) {
                LogUtil.c("ReceiverCallbackManager", "getReceiverList enter srcDestPkgMap");
                List<IdentityInfo> list = value.get(identityInfo);
                if (list == null) {
                    LogUtil.a("ReceiverCallbackManager", "getReceiverList do not have the phone dstPkgInfo and continue");
                } else if (!list.contains(identityInfo2)) {
                    LogUtil.a("ReceiverCallbackManager", "getReceiverList do not have the watch srcPkgInfo and continue");
                } else {
                    LogUtil.c("ReceiverCallbackManager", "getReceiverList deviceReceiverCallbackMap hit");
                    arrayList.add(entry.getKey());
                }
            }
        }
        return arrayList;
    }

    private spr c(spr sprVar) {
        if (sprVar == null) {
            return null;
        }
        for (spr sprVar2 : this.d.keySet()) {
            if (sprVar2.equals(sprVar)) {
                LogUtil.c("ReceiverCallbackManager", "getContainReceiver get the same receiver");
                return sprVar2;
            }
        }
        LogUtil.c("ReceiverCallbackManager", "getContainReceiver no same receiver");
        return null;
    }

    private boolean a(String str, String str2, spr sprVar) {
        return (sprVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : true;
    }

    public static spo a() {
        return f17206a;
    }
}
