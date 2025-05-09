package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.huawei.harmonyos.interwork.b;
import com.huawei.harmonyos.interwork.base.ability.IAbilityStartCallback;
import com.huawei.health.R;

/* loaded from: classes3.dex */
final class bwo extends b.a {
    private static Handler e;

    /* renamed from: a, reason: collision with root package name */
    private Runnable f540a;
    private Context b;
    private final Object c = new Object();
    private IAbilityStartCallback d;

    public bwo(Context context, IAbilityStartCallback iAbilityStartCallback) {
        if (context == null || iAbilityStartCallback == null) {
            throw new NullPointerException("No callback specified");
        }
        this.d = iAbilityStartCallback;
        this.b = context;
    }

    public static void Ae_(Handler handler) {
        e = handler;
    }

    public final void e() {
        if (e == null) {
            Log.e("DefKitLib_AbilityStartCallback", "startCallbackTimeoutDetection: sHandler is null");
            return;
        }
        Runnable runnable = new Runnable() { // from class: bwn
            @Override // java.lang.Runnable
            public final void run() {
                bwo.this.d();
            }
        };
        this.f540a = runnable;
        e.postDelayed(runnable, this.d, 40000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        synchronized (this.c) {
            if (this.d == null) {
                Log.e("DefKitLib_AbilityStartCallback", "startCallbackTimeoutDetection: mCallback may has released");
                return;
            }
            Log.e("DefKitLib_AbilityStartCallback", "start ability callback time out, callback: " + this.d);
            this.d.onStartResult(3);
            b();
        }
    }

    private void b() {
        synchronized (this.c) {
            if (this.d != null) {
                Log.i("DefKitLib_AbilityStartCallback", "release callback: " + this.d);
                this.d = null;
            }
            if (this.b != null) {
                Log.i("DefKitLib_AbilityStartCallback", "release context: " + this.b);
                this.b = null;
            }
        }
    }

    @Override // com.huawei.harmonyos.interwork.b.a, android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (parcel == null || parcel2 == null) {
            Log.e("DefKitLib_AbilityStartCallback", "AbilityStartCallback: onTransact param invalid");
        }
        Log.d("DefKitLib_AbilityStartCallback", "AbilityStartCallback::onRemoteRequest code:".concat(String.valueOf(i)));
        if (i == 1) {
            parcel.enforceInterface("ohos.abilityshell.AbilityStartCallback");
            a(parcel.readInt());
            parcel2.writeInt(0);
            return true;
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    @Override // com.huawei.harmonyos.interwork.b
    public final void a(final int i) throws RemoteException {
        Runnable runnable;
        Handler handler = e;
        if (handler != null && (runnable = this.f540a) != null) {
            handler.removeCallbacks(runnable, this.d);
        }
        synchronized (this.c) {
            IAbilityStartCallback iAbilityStartCallback = this.d;
            if (iAbilityStartCallback == null) {
                Log.e("DefKitLib_AbilityStartCallback", "OnResult called, mCallback may has released");
                return;
            }
            iAbilityStartCallback.onStartResult(i);
            if (i != 0) {
                Log.i("DefKitLib_AbilityStartCallback", "AbilityStartCallback::OnResult errorCode:".concat(String.valueOf(i)));
                e.post(new Runnable() { // from class: bwl
                    @Override // java.lang.Runnable
                    public final void run() {
                        bwo.this.e(i);
                    }
                });
            }
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = R.string._2130851466_res_0x7f02368a;
                break;
            case 2:
                i2 = R.string._2130851465_res_0x7f023689;
                break;
            case 3:
                i2 = R.string._2130851467_res_0x7f02368b;
                break;
            case 4:
                i2 = R.string._2130851464_res_0x7f023688;
                break;
            case 5:
                i2 = R.string._2130851463_res_0x7f023687;
                break;
            case 6:
                i2 = R.string._2130851462_res_0x7f023686;
                break;
            case 7:
                i2 = R.string._2130851501_res_0x7f0236ad;
                break;
            default:
                i2 = -1;
                break;
        }
        Log.i("DefKitLib_AbilityStartCallback", "getRestId restId:".concat(String.valueOf(i2)));
        Context context = this.b;
        if (context == null || i2 == -1) {
            return;
        }
        Toast.makeText(context, i2, 0).show();
    }
}
