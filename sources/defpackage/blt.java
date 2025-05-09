package defpackage;

import android.text.TextUtils;
import android.util.LruCache;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public class blt {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f433a;
    private static final char[] b;
    private static final Map<Integer, Byte> c;
    private static final Set<Byte> d;
    private static LruCache<String, String> e;

    static {
        TreeSet treeSet = new TreeSet();
        d = treeSet;
        c = new HashMap(16);
        b = "**********".toCharArray();
        f433a = "*********************************************************".toCharArray();
        e = new LruCache<>(3);
        treeSet.add((byte) 9);
        treeSet.add((byte) 28);
        treeSet.add((byte) 40);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(new int[]{2, 1});
        arrayList.add(new int[]{2, 3});
        arrayList.add(new int[]{2, 16});
        arrayList.add(new int[]{3, 1});
        arrayList.add(new int[]{46, 2});
        arrayList.add(new int[]{49, 2});
        arrayList.add(new int[]{49, 3});
        arrayList.add(new int[]{49, 4});
        arrayList.add(new int[]{49, 5});
        arrayList.add(new int[]{51, 1});
        arrayList.add(new int[]{51, 2});
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            c.put(Integer.valueOf(e((int[]) it.next())), (byte) 1);
        }
    }

    private static int c(byte[] bArr) {
        return bArr[1] | (bArr[0] << 8);
    }

    private static int e(int[] iArr) {
        return iArr[1] | (iArr[0] << 8);
    }

    public static String a(DeviceInfo deviceInfo) {
        return bky.i() ? "" : b(deviceInfo);
    }

    public static String b(DeviceInfo deviceInfo) {
        return a(deviceInfo == null ? null : deviceInfo.getDeviceMac());
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder(" deviceUuid:");
        if (TextUtils.isEmpty(str)) {
            sb.append("");
        } else {
            sb.append(b(str));
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = e.get(str);
        if (str2 != null) {
            return str2;
        }
        int length = str.length();
        if (length <= 2) {
            return "**";
        }
        if (length <= 8) {
            return a(str, 1, (length / 3) - 1);
        }
        return a(str, 3, 4);
    }

    private static String a(String str, int i, int i2) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(str.substring(0, i));
        int i3 = length - i2;
        int i4 = i3 - i;
        if (i4 == 10) {
            sb.append(b);
        } else if (i4 == 57) {
            sb.append(f433a);
        } else {
            for (int i5 = 0; i5 < i4; i5++) {
                sb.append('*');
            }
        }
        sb.append(str.substring(i3));
        String sb2 = sb.toString();
        e.put(str, sb2);
        return sb2;
    }

    public static String b(byte[] bArr) {
        return d(bArr, false);
    }

    public static String d(byte[] bArr, boolean z) {
        if (!z && bky.i()) {
            return null;
        }
        if (bArr == null || bArr.length <= 2) {
            return blq.d(bArr);
        }
        if (bArr.length > 200 && d.contains(Byte.valueOf(bArr[0]))) {
            byte[] bArr2 = new byte[200];
            System.arraycopy(bArr, 0, bArr2, 0, 200);
            return blq.d(bArr2);
        }
        if (c.get(Integer.valueOf(c(bArr))) != null) {
            return blq.d(a(bArr));
        }
        return blq.d(bArr);
    }

    private static byte[] a(byte[] bArr) {
        try {
            byte[] a2 = iyx.a(16);
            byte[] a3 = iyx.a(16);
            byte[] c2 = iyx.c(1, bArr, a2, a3);
            if (c2 != null) {
                ByteBuffer allocate = ByteBuffer.allocate(c2.length + 34);
                allocate.put(bArr[0]);
                allocate.put(bArr[1]);
                allocate.put(a2);
                allocate.put(a3);
                allocate.put(c2);
                return allocate.array();
            }
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.e("LogContentUtil", "printEncryptHex : ", e2.getMessage());
        }
        return bArr;
    }

    public static void d(String str, byte[] bArr, Object... objArr) {
        if (bky.i()) {
            return;
        }
        LogUtil.c(str, d(objArr), blq.d(bArr));
    }

    public static void e(String str, byte[] bArr, Object... objArr) {
        if (bky.i()) {
            return;
        }
        LogUtil.a(str, d(objArr), blq.d(bArr));
    }

    public static void c(String str, byte[] bArr, Object... objArr) {
        if (bky.i() || bky.e()) {
            return;
        }
        LogUtil.d(str, d(objArr), blq.d(bArr));
    }

    private static StringBuilder d(Object... objArr) {
        StringBuilder sb = new StringBuilder(16);
        if (objArr != null) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        return sb;
    }

    public static void b(String str, byte[] bArr, Object... objArr) {
        if (bky.i()) {
            return;
        }
        if (bArr == null || bArr.length < 16) {
            LogUtil.c(str, d(objArr), blq.d(bArr));
            return;
        }
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        LogUtil.c(str, d(objArr), blq.d(bArr2));
    }
}
