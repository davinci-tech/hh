package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserManager;
import android.util.Log;
import com.huawei.android.powerkit.PowerKitConnection;
import com.huawei.android.powerkit.Sink;
import com.huawei.android.powerkit.adapter.IPowerKitApi;
import com.huawei.android.powerkit.adapter.IStateSink;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* loaded from: classes2.dex */
public class abk implements IBinder.DeathRecipient {
    private Context d;
    private PowerKitConnection e;
    private abk g;
    private final HashSet<Sink> j = new HashSet<>();
    private final ArrayList<Integer> b = new ArrayList<>();
    private final HashMap<Sink, ArrayList<Integer>> i = new HashMap<>();
    private final c f = new c();
    private final Object c = new Object();
    private IPowerKitApi h = null;

    /* renamed from: a, reason: collision with root package name */
    private ServiceConnection f162a = new ServiceConnection() { // from class: abk.3
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("PowerKitApi", "Power kit service disconnected");
            abk.this.e.onServiceDisconnected();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (abk.this.c) {
                abk.this.h = IPowerKitApi.Stub.asInterface(iBinder);
                try {
                    abk.this.h.asBinder().linkToDeath(abk.this.g, 0);
                } catch (Exception unused) {
                    Log.w("PowerKitApi", "power kit linkToDeath failed ! calling pid: " + Binder.getCallingPid());
                }
            }
            Log.v("PowerKitApi", "Power kit service connected");
            abk.this.e.onServiceConnected();
        }
    };

    public abk(Context context, PowerKitConnection powerKitConnection) {
        this.d = null;
        this.g = null;
        this.e = null;
        this.g = this;
        this.e = powerKitConnection;
        this.d = context;
        b();
    }

    private boolean b() {
        boolean z = false;
        if (!((UserManager) this.d.getSystemService("user")).isSystemUser()) {
            Log.v("PowerKitApi", "not owner, power kit service not support");
            return false;
        }
        try {
            z = this.d.getApplicationContext().bindService(fF_(this.d, new Intent("com.huawei.android.powerkit.PowerKitService")), this.f162a, 1);
            Log.v("PowerKitApi", "bind power kit service, state: " + z);
            return z;
        } catch (Exception unused) {
            Log.w("PowerKitApi", "bind power kit service exception!");
            return z;
        }
    }

    private Intent fF_(Context context, Intent intent) {
        context.getPackageManager().queryIntentServices(intent, 0);
        ComponentName componentName = new ComponentName("com.huawei.powergenie", "com.huawei.android.powerkit.PowerKitService");
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    public String e(Context context) throws RemoteException {
        String powerKitVersion;
        synchronized (this.c) {
            IPowerKitApi iPowerKitApi = this.h;
            if (iPowerKitApi == null) {
                throw new RemoteException("PowerKit server is not found");
            }
            powerKitVersion = iPowerKitApi.getPowerKitVersion(context.getPackageName());
        }
        return powerKitVersion;
    }

    public boolean b(Context context, boolean z, String str, int i, long j, String str2) throws RemoteException {
        boolean applyForResourceUse;
        synchronized (this.c) {
            IPowerKitApi iPowerKitApi = this.h;
            if (iPowerKitApi == null) {
                throw new RemoteException("PowerKit server is not found");
            }
            applyForResourceUse = iPowerKitApi.applyForResourceUse(context.getPackageName(), z, str, i, j, str2);
        }
        return applyForResourceUse;
    }

    public boolean a(Sink sink, int i) throws RemoteException {
        synchronized (this.c) {
            IPowerKitApi iPowerKitApi = this.h;
            if (iPowerKitApi == null) {
                throw new RemoteException("PowerKit server is not found");
            }
            if (c(iPowerKitApi.getPowerKitVersion(this.d.getPackageName()), "10.0.0.305") && i == 51) {
                Log.i("PowerKitApi", "System PowerKit version is low, don't support application using baseline remaining");
                return false;
            }
            if (!e(sink)) {
                return false;
            }
            Log.i("PowerKitApi", "registerSink return true");
            ArrayList<Integer> arrayList = this.i.get(sink);
            if (arrayList == null) {
                ArrayList<Integer> arrayList2 = new ArrayList<>();
                arrayList2.add(Integer.valueOf(i));
                this.i.put(sink, arrayList2);
            } else {
                arrayList.add(Integer.valueOf(i));
            }
            this.b.add(Integer.valueOf(i));
            return this.h.enableStateEvent(i);
        }
    }

    private boolean e(Sink sink) {
        if (sink == null) {
            Log.e("PowerKitApi", "registerSink a null sink fail.");
            return false;
        }
        if (!this.j.contains(sink)) {
            this.j.add(sink);
            if (this.j.size() == 1) {
                c();
            }
        }
        return true;
    }

    private void c() {
        try {
            this.h.registerSink(this.f);
        } catch (RemoteException unused) {
            Log.e("PowerKitApi", "register sink transport fail.");
        }
    }

    public boolean d(Sink sink, int i) throws RemoteException {
        boolean disableStateEvent;
        synchronized (this.c) {
            if (this.h == null) {
                throw new RemoteException("PowerKit server is not found");
            }
            ArrayList<Integer> arrayList = this.i.get(sink);
            if (arrayList != null) {
                arrayList.remove(Integer.valueOf(i));
                if (arrayList.size() == 0) {
                    this.i.remove(sink);
                    c(sink);
                }
            }
            this.b.remove(Integer.valueOf(i));
            Log.i("PowerKitApi", "Disable state event, stateType: " + i);
            disableStateEvent = this.h.disableStateEvent(i);
        }
        return disableStateEvent;
    }

    private void c(Sink sink) {
        this.j.remove(sink);
        if (this.j.size() == 0) {
            a();
        }
    }

    private void a() {
        try {
            this.h.unregisterSink(this.f);
        } catch (RemoteException unused) {
            Log.e("PowerKitApi", "unregister sink transport fail.");
        }
    }

    private boolean c(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        for (int i = 0; i < split2.length; i++) {
            if (Integer.parseInt(split[i]) > Integer.parseInt(split2[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean a(Context context) throws RemoteException {
        boolean isUserSleeping;
        synchronized (this.c) {
            if (this.h == null) {
                throw new RemoteException("PowerKit server is not found");
            }
            Log.i("PowerKitApi", "isUserSleeping ! pkg: " + context.getPackageName());
            isUserSleeping = this.h.isUserSleeping(context.getPackageName());
        }
        return isUserSleeping;
    }

    public boolean a(Context context, List<String> list, boolean z) throws RemoteException {
        boolean applyForAlarmExemption;
        synchronized (this.c) {
            if (this.h == null) {
                throw new RemoteException("PowerKit server is not found");
            }
            Log.i("PowerKitApi", "Caller: " + context.getPackageName() + " actions:" + list + " apply:" + z);
            applyForAlarmExemption = this.h.applyForAlarmExemption(context.getPackageName(), list, z);
        }
        return applyForAlarmExemption;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        Log.e("PowerKitApi", "powerkit process binder was died and connecting ...");
        synchronized (this.c) {
            this.h = null;
        }
        int i = 5;
        while (i > 0) {
            i--;
            SystemClock.sleep(new Random().nextInt(2001) + 1000);
            if (b()) {
                return;
            }
        }
    }

    final class c extends IStateSink.Stub {
        private c() {
        }

        @Override // com.huawei.android.powerkit.adapter.IStateSink
        public void onStateChanged(int i, int i2, int i3, String str, int i4) {
            Log.i("PowerKitApi", "stateType:" + i + " eventType:" + i2 + " pid:" + i3 + " pkg:" + str + " uid:" + i4);
            synchronized (abk.this.c) {
                if (abk.this.j.isEmpty()) {
                    return;
                }
                Iterator it = abk.this.j.iterator();
                while (it.hasNext()) {
                    Sink sink = (Sink) it.next();
                    ArrayList arrayList = (ArrayList) abk.this.i.get(sink);
                    if (arrayList != null && arrayList.contains(Integer.valueOf(i))) {
                        sink.onStateChanged(i, i2, i3, str, i4);
                    }
                }
            }
        }

        @Override // com.huawei.android.powerkit.adapter.IStateSink
        public void onPowerOverUsing(String str, int i, long j, long j2, String str2) {
            Log.i("PowerKitApi", "onPowerOverUsing moudle:" + str + " resource:" + i + " duration:" + j + " time:" + j2 + " extend:" + str2);
            synchronized (abk.this.c) {
                if (abk.this.j.isEmpty()) {
                    return;
                }
                Iterator it = abk.this.j.iterator();
                while (it.hasNext()) {
                    Sink sink = (Sink) it.next();
                    ArrayList arrayList = (ArrayList) abk.this.i.get(sink);
                    if (arrayList != null && arrayList.contains(50)) {
                        sink.onPowerOverUsing(str, i, j, j2, str2);
                    }
                }
            }
        }

        @Override // com.huawei.android.powerkit.adapter.IStateSink
        public void onPowerBaseLineUsing(String str, String str2, long j, int i, String str3) {
            Log.i("PowerKitApi", "onPowerBaseLineUsing callingPkg:" + str + " appAndGroup:" + str2 + " usage duration:" + j + " usage Count:" + i + " extend:" + str3);
            synchronized (abk.this.c) {
                if (abk.this.j.isEmpty()) {
                    return;
                }
                Iterator it = abk.this.j.iterator();
                while (it.hasNext()) {
                    Sink sink = (Sink) it.next();
                    ArrayList arrayList = (ArrayList) abk.this.i.get(sink);
                    if (arrayList != null && arrayList.contains(51)) {
                        sink.onPowerBaseLineUsing(str, str2, j, i, str3);
                    }
                }
            }
        }
    }
}
