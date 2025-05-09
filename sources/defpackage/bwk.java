package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.widget.Toast;
import com.huawei.a.a.a;
import com.huawei.harmonyos.interwork.b;
import com.huawei.harmonyos.interwork.base.ability.IAbilityStartCallback;
import com.huawei.harmonyos.interwork.base.bundle.AbilityInfo;
import com.huawei.harmonyos.interwork.base.content.Intent;
import com.huawei.health.R;
import defpackage.bwk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bwk {
    private static Handler e;

    public static int c(Intent intent) throws IllegalArgumentException, IllegalStateException, SecurityException, bwi {
        e();
        a b = bws.b();
        if (b == null) {
            throw new IllegalStateException("failed to get def access service");
        }
        try {
            return d(b.a(intent, 0, 0));
        } catch (RemoteException e2) {
            throw new bwi(e2.getMessage());
        }
    }

    public static void c(final Context context, Intent intent, IAbilityStartCallback iAbilityStartCallback) throws IllegalArgumentException, IllegalStateException {
        int i;
        if (context == null || intent == null || iAbilityStartCallback == null) {
            Log.e("DefKitLib_Ability", "startAbility: IAbilityStartCallback is null");
            throw new IllegalArgumentException("invalid param for startAbility");
        }
        e();
        a b = bws.b();
        if (b == null) {
            Log.e("DefKitLib_Ability", "startAbility: failed to get def access service");
            iAbilityStartCallback.onStartResult(7);
            return;
        }
        bwo bwoVar = new bwo(context, iAbilityStartCallback);
        try {
            i = b.a(intent, (b) bwoVar, 0, 0);
            Log.i("DefKitLib_Ability", "startAbility: result: ".concat(String.valueOf(i)));
        } catch (RemoteException e2) {
            Log.e("DefKitLib_Ability", "startAbilityForResult failed " + e2.getMessage());
            i = 7;
        }
        if (i == 0) {
            bwoVar.e();
            return;
        }
        if (e == null) {
            e = new Handler(Looper.getMainLooper());
        }
        if (i == 1204) {
            iAbilityStartCallback.onStartResult(8);
            return;
        }
        if (i == 1014) {
            iAbilityStartCallback.onStartResult(9);
            return;
        }
        if (i == 1319 || i == 1200 || i == 1203 || i == 1000 || i == 1001) {
            e.post(new Runnable() { // from class: com.huawei.harmonyos.interwork.abilitykit.AbilityManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    bwk.d(context);
                }
            });
        } else if (i == 1407 || i == 1320 || i == 1312 || i == 1311 || i == 1300) {
            e.post(new Runnable() { // from class: com.huawei.harmonyos.interwork.abilitykit.AbilityManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    bwk.a(context);
                }
            });
            iAbilityStartCallback.onStartResult(3);
            return;
        }
        iAbilityStartCallback.onStartResult(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Context context) {
        Toast.makeText(context, R.string._2130851501_res_0x7f0236ad, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Context context) {
        Toast.makeText(context, R.string._2130851467_res_0x7f02368b, 0).show();
    }

    public static List<AbilityInfo> d(Intent intent) {
        if (intent == null || intent.getElement() == null) {
            Log.e("DefKitLib_Ability", "intent and element must be specified");
            return new ArrayList(0);
        }
        a b = bws.b();
        if (b != null) {
            try {
                return b.b(intent, 0, 0);
            } catch (RemoteException unused) {
                Log.e("DefKitLib_Ability", "failed to register device state callback");
            }
        }
        return new ArrayList(0);
    }

    private static void e() throws IllegalStateException {
        int i = SystemProperties.getInt("ro.build.version.sdk", 0);
        Log.d("DefKitLib_Ability", "SDK version is ".concat(String.valueOf(i)));
        if (i >= 29) {
            return;
        }
        Log.e("DefKitLib_Ability", "SDK version min than 28, is ".concat(String.valueOf(i)));
        throw new IllegalStateException("sdk version min than 28");
    }

    private static int d(int i) throws IllegalArgumentException, IllegalStateException, SecurityException, bwi {
        if (i == 0) {
            return i;
        }
        if (i >= 1000 && i < 1100) {
            throw new IllegalArgumentException("invalid parameter, error code: ".concat(String.valueOf(i)));
        }
        if (i >= 1100 && i < 1200) {
            throw new IllegalStateException("illegal state exception, error code: ".concat(String.valueOf(i)));
        }
        if (i >= 1200 && i < 1300) {
            throw new SecurityException("security exception, error code: ".concat(String.valueOf(i)));
        }
        if (i >= 1300 && i < 1400) {
            throw new bwi("remote exception, error code: ".concat(String.valueOf(i)));
        }
        if (i >= 1400 && i < 1500) {
            throw new IllegalStateException("runtime exception, error code: ".concat(String.valueOf(i)));
        }
        throw new IllegalStateException("unknown error code: ".concat(String.valueOf(i)));
    }
}
