package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.android.powerkit.PowerKitConnection;
import com.huawei.android.powerkit.Sink;
import java.util.List;

/* loaded from: classes.dex */
public class abi {
    private static volatile abi d;

    /* renamed from: a, reason: collision with root package name */
    private Context f161a;
    private abk c;

    private abi(Context context, PowerKitConnection powerKitConnection) {
        this.c = null;
        this.f161a = context;
        this.c = new abk(context, powerKitConnection);
    }

    public static abi c(Context context, PowerKitConnection powerKitConnection) {
        if (d == null) {
            synchronized (abi.class) {
                if (d == null) {
                    d = new abi(context, powerKitConnection);
                }
            }
        }
        return d;
    }

    public String c() throws RemoteException {
        return this.c.e(this.f161a);
    }

    public boolean b(String str, int i, long j, String str2) throws RemoteException {
        return this.c.b(this.f161a, true, str, i, j, str2);
    }

    public boolean b(String str, int i) throws RemoteException {
        return this.c.b(this.f161a, false, str, i, -1L, null);
    }

    public boolean d(String str, int i, String str2) throws RemoteException {
        return this.c.b(this.f161a, false, str, i, -1L, str2);
    }

    public boolean e(Sink sink, int i) throws RemoteException {
        return this.c.a(sink, i);
    }

    public boolean b(Sink sink, int i) throws RemoteException {
        return this.c.d(sink, i);
    }

    public boolean b() throws RemoteException {
        return this.c.a(this.f161a);
    }

    public boolean a(List<String> list) throws RemoteException {
        return this.c.a(this.f161a, list, true);
    }

    public boolean d(List<String> list) throws RemoteException {
        return this.c.a(this.f161a, list, false);
    }
}
