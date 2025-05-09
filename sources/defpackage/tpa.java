package defpackage;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.ReceiveResultCallback;
import com.huawei.wearengine.p2p.ReceiverCallback;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class tpa {

    /* renamed from: a, reason: collision with root package name */
    private Map<tqb, Map<String, Map<IdentityInfo, List<IdentityInfo>>>> f17294a = new ConcurrentHashMap();

    public int b(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2, tqb tqbVar) {
        if (identityInfo == null || identityInfo2 == null) {
            tos.e("ReceiverCallbackManager", "send pkgInfo is invalid");
            return 5;
        }
        if (!b(str, identityInfo.getPackageName(), identityInfo2.getPackageName(), tqbVar)) {
            tos.e("ReceiverCallbackManager", "HandleReceiverCallbackProxy parameters is invalid");
            return 5;
        }
        tos.b("ReceiverCallbackManager", "registerReceiver receiverCallbackProxy pid is:" + tqbVar.b() + ", hashcode is:" + tqbVar.d());
        tqb d = d(tqbVar);
        if (d == null) {
            if (e(str, identityInfo, identityInfo2).size() >= 10) {
                return 11;
            }
            tos.a("ReceiverCallbackManager", "registerReceiver new receiver");
            d(str, identityInfo, identityInfo2, tqbVar);
        } else {
            tos.a("ReceiverCallbackManager", "registerReceiver already has receiver");
            d(str, identityInfo, identityInfo2, this.f17294a.get(d));
        }
        tos.b("ReceiverCallbackManager", "handleReceiverCallbackProxy mDeviceReceiverCallbackMap is:" + this.f17294a.toString());
        return 0;
    }

    private boolean b(String str, String str2, String str3, tqb tqbVar) {
        return (TextUtils.isEmpty(str) || tqbVar == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) ? false : true;
    }

    public tqb d(tqb tqbVar) {
        if (tqbVar == null) {
            return null;
        }
        tos.b("ReceiverCallbackManager", "getContainReceiver receiverCallbackProxy pid is:" + tqbVar.b() + ", hashcode is:" + tqbVar.d());
        for (tqb tqbVar2 : this.f17294a.keySet()) {
            if (tqbVar2.equals(tqbVar)) {
                tos.a("ReceiverCallbackManager", "getContainReceiver get the same receiverCallbackProxy");
                return tqbVar2;
            }
        }
        tos.a("ReceiverCallbackManager", "getContainReceiver no same receiverCallbackProxy");
        return null;
    }

    private List<tqb> e(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        tos.a("ReceiverCallbackManager", "getReceiverList enter");
        ArrayList arrayList = new ArrayList();
        if (this.f17294a.isEmpty()) {
            tos.e("ReceiverCallbackManager", "getReceiverList deviceReceiverCallbackMap is null");
            return arrayList;
        }
        ScopeInfoResponse c = !toh.b(identityInfo.getPackageName(), identityInfo.getFingerPrint()) ? trf.c(tri.j(tot.a(), identityInfo.getPackageName())) : null;
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<tqb, Map<String, Map<IdentityInfo, List<IdentityInfo>>>> entry : this.f17294a.entrySet()) {
            Map<IdentityInfo, List<IdentityInfo>> map = entry.getValue().get(str);
            if (map != null) {
                IdentityInfo b = b(map.keySet(), identityInfo, c);
                if (b == null) {
                    tos.d("ReceiverCallbackManager", "getReceiverList do not have the phone destPkgInfo and continue");
                } else {
                    arrayList2.add(b.getPackageName());
                    List<IdentityInfo> list = map.get(b);
                    if (list != null) {
                        if (!c(list, identityInfo2)) {
                            tos.d("ReceiverCallbackManager", "getReceiverList do not have the watch srcPkgInfo and continue");
                        } else {
                            tos.a("ReceiverCallbackManager", "getReceiverList deviceReceiverCallbackMap hit");
                            arrayList.add(entry.getKey());
                        }
                    }
                }
            }
        }
        tos.a("ReceiverCallbackManager", "getReceiverList phonePkgNameList: " + arrayList2.toString());
        return arrayList;
    }

    private IdentityInfo b(Set<IdentityInfo> set, IdentityInfo identityInfo, ScopeInfoResponse scopeInfoResponse) {
        if (set == null) {
            return null;
        }
        for (IdentityInfo identityInfo2 : set) {
            if (TextUtils.equals(identityInfo2.getPackageName(), identityInfo.getPackageName())) {
                String fingerPrint = identityInfo2.getFingerPrint();
                String fingerPrint2 = identityInfo.getFingerPrint();
                if (TextUtils.equals(fingerPrint, fingerPrint2)) {
                    return identityInfo2;
                }
                if (scopeInfoResponse != null && (TextUtils.equals(fingerPrint2, scopeInfoResponse.getCertFingerprint()) || trf.c(fingerPrint2, scopeInfoResponse.getCertFingerprintExtra()))) {
                    return identityInfo2;
                }
            }
        }
        return null;
    }

    private IdentityInfo d(Set<IdentityInfo> set, IdentityInfo identityInfo) {
        if (set == null) {
            return null;
        }
        for (IdentityInfo identityInfo2 : set) {
            if (TextUtils.equals(identityInfo2.getPackageName(), identityInfo.getPackageName()) && TextUtils.equals(identityInfo2.getFingerPrint(), identityInfo.getFingerPrint())) {
                return identityInfo2;
            }
        }
        return null;
    }

    private boolean c(List<IdentityInfo> list, IdentityInfo identityInfo) {
        return d(new HashSet(list), identityInfo) != null;
    }

    private void d(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2, Map<String, Map<IdentityInfo, List<IdentityInfo>>> map) {
        if (!map.containsKey(str)) {
            tos.a("ReceiverCallbackManager", "addDestPkgInfo new device");
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(identityInfo2);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            concurrentHashMap.put(identityInfo, arrayList);
            map.put(str, concurrentHashMap);
            return;
        }
        tos.a("ReceiverCallbackManager", "addDestPkgInfo already has device");
        Map<IdentityInfo, List<IdentityInfo>> map2 = map.get(str);
        IdentityInfo d = d(map2.keySet(), identityInfo);
        if (d == null) {
            tos.a("ReceiverCallbackManager", "addDestPkgInfo new srcPkgInfo");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(identityInfo2);
            map2.put(identityInfo, arrayList2);
            return;
        }
        tos.a("ReceiverCallbackManager", "addDestPkgInfo already has srcPkgInfo");
        List<IdentityInfo> list = map2.get(d);
        if (!c(list, identityInfo2)) {
            tos.a("ReceiverCallbackManager", "addDestPkgInfo new destPkgInfo");
            list.add(identityInfo2);
        }
        tos.a("ReceiverCallbackManager", "addDestPkgInfo already has destPkgInfo");
    }

    private void d(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2, tqb tqbVar) {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(identityInfo2);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(identityInfo, arrayList);
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        concurrentHashMap2.put(str, concurrentHashMap);
        this.f17294a.put(tqbVar, concurrentHashMap2);
    }

    public void e(String str, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiveResultCallback receiveResultCallback) {
        tos.a("ReceiverCallbackManager", "handleReceive enter");
        if (this.f17294a.isEmpty()) {
            tos.e("ReceiverCallbackManager", "handleReceive deviceReceiverCallbackMap is null");
            return;
        }
        if (identityInfo == null || identityInfo2 == null) {
            tos.e("ReceiverCallbackManager", "handleReceive parameters is invalid");
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            tos.e("ReceiverCallbackManager", "handleReceive parameters is invalid");
            return;
        }
        if (messageParcel == null) {
            tos.e("ReceiverCallbackManager", "handleReceive parameters is invalid");
            return;
        }
        List<tqb> e = e(str, identityInfo2, identityInfo);
        if (e.isEmpty()) {
            tos.a("ReceiverCallbackManager", "handleReceive receiverList is empty");
            e(receiveResultCallback, 206);
            e(messageParcel);
            return;
        }
        e(receiveResultCallback, a.w);
        Iterator<tqb> it = e.iterator();
        while (it.hasNext()) {
            ReceiverCallback c = it.next().c();
            if (c != null) {
                try {
                    e(c, messageParcel);
                } catch (Exception unused) {
                    tos.d("ReceiverCallbackManager", "handleReceive exception");
                }
            }
        }
    }

    public void e(tqb tqbVar) {
        if (tqbVar != null) {
            this.f17294a.remove(tqbVar);
        }
        tos.a("ReceiverCallbackManager", "removeReceiverCallback deviceReceiverCallbackMap size is:" + this.f17294a.size());
    }

    public int a() {
        return this.f17294a.size();
    }

    public void e() {
        if (this.f17294a.isEmpty()) {
            tos.e("ReceiverCallbackManager", "clearDiedReceiver deviceReceiverCallbackMap is null");
            return;
        }
        Iterator<Map.Entry<tqb, Map<String, Map<IdentityInfo, List<IdentityInfo>>>>> it = this.f17294a.entrySet().iterator();
        while (it.hasNext()) {
            if (!it.next().getKey().c().asBinder().pingBinder()) {
                tos.b("ReceiverCallbackManager", "clearDiedReceiver hit died receiver and remove");
                it.remove();
            }
        }
        tos.a("ReceiverCallbackManager", "clearDiedReceiver deviceReceiverCallbackMap size is:" + this.f17294a.size());
    }

    public boolean d(String str, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        if (identityInfo == null || identityInfo2 == null) {
            tos.e("ReceiverCallbackManager", "isP2pReceiverExist IdentityInfo is null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            tos.e("ReceiverCallbackManager", "isP2pReceiverExist deviceId is null");
            return false;
        }
        if (TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            tos.e("ReceiverCallbackManager", "isP2pReceiverExist package is null");
            return false;
        }
        List<tqb> e = e(str, identityInfo, identityInfo2);
        if (e.isEmpty()) {
            tos.e("ReceiverCallbackManager", "isP2pReceiverExist receiverList is empty");
            return false;
        }
        Iterator<tqb> it = e.iterator();
        while (it.hasNext()) {
            if (it.next().c() != null) {
                tos.e("ReceiverCallbackManager", "isP2pReceiverExist return true");
                return true;
            }
        }
        tos.e("ReceiverCallbackManager", "isP2pReceiverExist return false");
        return false;
    }

    private void e(ReceiveResultCallback receiveResultCallback, int i) {
        if (receiveResultCallback == null) {
            tos.e("ReceiverCallbackManager", "notifyReceiveResult callback is null");
            return;
        }
        try {
            receiveResultCallback.onReceiveResult(i);
        } catch (RemoteException unused) {
            tos.e("ReceiverCallbackManager", "notifyReceiveResult RemoteException");
        }
    }

    private void e(MessageParcel messageParcel) {
        if (messageParcel == null) {
            tos.a("ReceiverCallbackManager", "closePfd messageParcel is null");
            return;
        }
        ParcelFileDescriptor parcelFileDescriptor = messageParcel.getParcelFileDescriptor();
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
                tos.a("ReceiverCallbackManager", "pfd from messageParcel close");
            } catch (IOException unused) {
                tos.e("ReceiverCallbackManager", "closePfd IOException");
            }
        }
    }

    private void e(ReceiverCallback receiverCallback, MessageParcel messageParcel) throws RemoteException {
        int type = messageParcel.getType();
        tos.a("ReceiverCallbackManager", "dispatchMessage, type:" + type);
        if (type == 1) {
            receiverCallback.onReceiveMessage(messageParcel.getData());
        } else if (type == 2) {
            receiverCallback.onReceiveFileMessage(messageParcel);
        } else {
            tos.e("ReceiverCallbackManager", "dispatchMessage type is invalid");
        }
    }

    void b(int i) {
        tos.a("ReceiverCallbackManager", "before removeReceiverByPid size: " + this.f17294a.size());
        if (this.f17294a.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<tqb, Map<String, Map<IdentityInfo, List<IdentityInfo>>>>> it = this.f17294a.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().b() == i) {
                it.remove();
            }
        }
        tos.a("ReceiverCallbackManager", "after removeReceiverByPid size: " + this.f17294a.size());
    }

    void b(String str) {
        tos.a("ReceiverCallbackManager", "removeReceiverByDevice before : " + this.f17294a);
        if (this.f17294a.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<tqb, Map<String, Map<IdentityInfo, List<IdentityInfo>>>>> it = this.f17294a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<tqb, Map<String, Map<IdentityInfo, List<IdentityInfo>>>> next = it.next();
            if (next.getValue() != null) {
                a(str, next.getValue());
                if (next.getValue().size() == 0) {
                    it.remove();
                }
            }
        }
        tos.a("ReceiverCallbackManager", "removeReceiverByDevice after : " + this.f17294a);
    }

    private void a(String str, Map<String, Map<IdentityInfo, List<IdentityInfo>>> map) {
        Iterator<Map.Entry<String, Map<IdentityInfo, List<IdentityInfo>>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getKey())) {
                it.remove();
            }
        }
    }
}
