package defpackage;

import com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat;

/* loaded from: classes2.dex */
public class nl {

    /* renamed from: a, reason: collision with root package name */
    private static volatile BluetoothLeScannerCompat f15356a;

    public static boolean c() {
        return true;
    }

    public static BluetoothLeScannerCompat e() {
        if (f15356a == null) {
            if (c()) {
                f15356a = new np();
            } else {
                f15356a = new ni();
            }
        }
        return f15356a;
    }
}
