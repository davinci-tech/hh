package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class czc {
    private ConcurrentHashMap<String, cyz> e = new ConcurrentHashMap<>();

    public void e(String str, MessageOrStateCallback messageOrStateCallback, List<Integer> list) {
        this.e.put(str, new cyz(messageOrStateCallback, list));
    }

    public void e(String str) {
        if (str == null) {
            LogUtil.a("PDROPE_MsgStateController", "context is null, can not release resource.");
        } else {
            this.e.remove(str);
        }
    }

    public void c() {
        this.e.clear();
    }

    public boolean e() {
        ConcurrentHashMap<String, cyz> concurrentHashMap = this.e;
        return concurrentHashMap == null || concurrentHashMap.isEmpty();
    }

    public void a(String str) {
        Iterator<cyz> it = this.e.values().iterator();
        while (it.hasNext()) {
            it.next().e(str);
        }
    }

    public void Se_(int i, Bundle bundle) {
        int i2 = bundle != null ? bundle.getInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE", -1) : -1;
        for (cyz cyzVar : this.e.values()) {
            if (i2 == -1 || cyzVar.b(i2)) {
                cyzVar.Sh_(i, bundle);
            }
        }
    }

    public void Sf_(String str, int i, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int i2 = bundle != null ? bundle.getInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE", -1) : -1;
        cyz cyzVar = this.e.get(str);
        if (cyzVar != null) {
            if (i2 == -1 || cyzVar.b(i2)) {
                cyzVar.Sh_(i, bundle);
            }
        }
    }
}
