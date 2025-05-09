package defpackage;

import com.huawei.devicesdk.entity.ReceiveDataInterface;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bjg implements ReceiveDataInterface {
    private Map<String, Map<String, bja>> e;

    private bjg() {
        this.e = new ConcurrentHashMap();
    }

    public void e(String str, String str2, byte[] bArr, boolean z) {
        if (str == null || str2 == null || bArr == null) {
            LogUtil.e("SimpleReceiveDataCache", "put param is invalid");
            return;
        }
        if (!this.e.containsKey(str)) {
            this.e.put(str, new HashMap(16));
        }
        Map<String, bja> map = this.e.get(str);
        if (!map.containsKey(str2)) {
            map.put(str2, new bja(z));
        }
        bja bjaVar = map.get(str2);
        bjaVar.a(a(bjaVar.a(), bArr));
    }

    public bja b(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            return new bja(false);
        }
        if (!this.e.containsKey(str)) {
            this.e.put(str, new HashMap(16));
        }
        Map<String, bja> map = this.e.get(str);
        if (!map.containsKey(str2)) {
            map.put(str2, new bja(false));
        }
        bja bjaVar = map.get(str2);
        if (z) {
            clearReceiveDataByUuid(str, str2);
            if (map.isEmpty()) {
                this.e.remove(str);
            }
        }
        return bjaVar;
    }

    @Override // com.huawei.devicesdk.entity.ReceiveDataInterface
    public void clearDevice(String str) {
        if (str != null) {
            LogUtil.d("SimpleReceiveDataCache", "clear device receive data.");
            this.e.remove(str);
        }
    }

    @Override // com.huawei.devicesdk.entity.ReceiveDataInterface
    public void clearReceiveDataByUuid(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        LogUtil.d("SimpleReceiveDataCache", "clear device character receive data. device: ", blt.a(str), " character: ", str2);
        Map<String, bja> map = this.e.get(str);
        if (map != null) {
            map.remove(str2);
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] bArr3 = new byte[length + length2];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr2, 0, bArr3, length, length2);
        return bArr3;
    }

    public static bjg e() {
        return d.c;
    }

    static class d {
        private static bjg c = new bjg();
    }
}
