package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.ReceiveDataInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bje implements ReceiveDataInterface {
    private Map<String, ReceiveDataInterface> b;

    private bje() {
        this.b = new ConcurrentHashMap();
    }

    public void a(String str, ReceiveDataInterface receiveDataInterface) {
        if (str == null || receiveDataInterface == null) {
            return;
        }
        this.b.put(str, receiveDataInterface);
    }

    @Override // com.huawei.devicesdk.entity.ReceiveDataInterface
    public void clearDevice(String str) {
        if (!TextUtils.isEmpty(str) && this.b.containsKey(str)) {
            this.b.remove(str).clearDevice(str);
        }
    }

    @Override // com.huawei.devicesdk.entity.ReceiveDataInterface
    public void clearReceiveDataByUuid(String str, String str2) {
        ReceiveDataInterface receiveDataInterface;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (receiveDataInterface = this.b.get(str)) == null) {
            return;
        }
        receiveDataInterface.clearReceiveDataByUuid(str, str2);
    }

    public static bje c() {
        return c.e;
    }

    static class c {
        private static bje e = new bje();
    }
}
