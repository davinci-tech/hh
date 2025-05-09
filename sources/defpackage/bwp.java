package defpackage;

import android.os.RemoteException;
import com.huawei.harmonyos.interwork.base.ability.IInitCallBack;
import com.huawei.harmonyos.interwork.e;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
final class bwp extends e.a {
    private static final Map<String, Set<bwp>> b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private final IInitCallBack f541a;

    private bwp(IInitCallBack iInitCallBack) {
        if (iInitCallBack == null) {
            throw new NullPointerException("No callback specified");
        }
        this.f541a = iInitCallBack;
    }

    public static bwp c(String str, IInitCallBack iInitCallBack) {
        Map<String, Set<bwp>> map = b;
        synchronized (map) {
            Set<bwp> set = map.get(str);
            if (set == null) {
                HashSet hashSet = new HashSet();
                bwp bwpVar = new bwp(iInitCallBack);
                hashSet.add(bwpVar);
                map.put(str, hashSet);
                return bwpVar;
            }
            for (bwp bwpVar2 : set) {
                if (bwpVar2.f541a == iInitCallBack) {
                    return bwpVar2;
                }
            }
            bwp bwpVar3 = new bwp(iInitCallBack);
            set.add(bwpVar3);
            return bwpVar3;
        }
    }

    public static bwp d(String str, IInitCallBack iInitCallBack) {
        Map<String, Set<bwp>> map = b;
        synchronized (map) {
            Set<bwp> set = map.get(str);
            if (set == null) {
                return null;
            }
            Iterator<bwp> it = set.iterator();
            while (it.hasNext()) {
                bwp next = it.next();
                if (next.f541a == iInitCallBack) {
                    it.remove();
                    if (set.isEmpty()) {
                        b.remove(str);
                    }
                    return next;
                }
            }
            return null;
        }
    }

    @Override // com.huawei.harmonyos.interwork.e
    public final void a(String str) throws RemoteException {
        this.f541a.onInitSuccess(str);
    }

    @Override // com.huawei.harmonyos.interwork.e
    public final void a(String str, int i) throws RemoteException {
        this.f541a.onInitFailure(str, i);
    }
}
