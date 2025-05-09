package defpackage;

import android.os.Process;
import androidx.core.internal.view.SupportMenu;
import defpackage.nc;
import java.util.Hashtable;

/* loaded from: classes8.dex */
public class mx {

    /* renamed from: a, reason: collision with root package name */
    private static Hashtable<String, int[]> f15227a = new Hashtable<>();
    private static short d;

    public static void d(String str) {
        synchronized (mx.class) {
            if (str == null) {
                nc.b.c("SenderIdManager", "registerSender, param == null");
            } else {
                f15227a.remove(str);
            }
        }
    }

    public static void a(String str) {
        synchronized (mx.class) {
            if (str == null) {
                nc.b.c("SenderIdManager", "registerSender, param == null");
                return;
            }
            nc.b.e("SenderIdManager", "registerSender groupId:" + ((int) d));
            short s = (short) (d + 1);
            d = s;
            short abs = (short) (Math.abs((int) s) % 32767);
            d = abs;
            int[] iArr = {Process.myPid(), abs << 16};
            nc.b.e("SenderIdManager", "registerSender, tidAndTime:" + str + " senderId:" + iArr[0] + "," + iArr[1]);
            f15227a.put(str, iArr);
        }
    }

    public static int[] b(String str) {
        synchronized (mx.class) {
            if (str == null) {
                nc.b.c("SenderIdManager", "getSenderId, param == null");
                return null;
            }
            int[] iArr = f15227a.get(str);
            if (iArr == null || 2 != iArr.length) {
                nc.b.c("SenderIdManager", "getSenderId, param: " + str + " fail!");
                return null;
            }
            iArr[1] = ((short) (Math.abs((int) ((short) (((short) (iArr[1] & 65535)) + 1))) % 32767)) | (iArr[1] & SupportMenu.CATEGORY_MASK);
            nc.b.e("SenderIdManager", "getSenderId, tidAndTime:" + str + " pid:" + iArr[0] + ", gid:" + (iArr[1] & SupportMenu.CATEGORY_MASK) + ", seq:" + (iArr[1] & 65535));
            return iArr;
        }
    }
}
