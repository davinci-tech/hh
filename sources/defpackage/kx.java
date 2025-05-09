package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.health.suggestion.model.FitRunPlayAudio;

/* loaded from: classes7.dex */
public class kx {

    /* renamed from: a, reason: collision with root package name */
    public SharedPreferences.Editor f14673a = null;
    public Context b;
    public SharedPreferences c;
    public String d;
    public boolean e;

    public kx(Context context, String str, String str2, boolean z, boolean z2) {
        this.c = null;
        this.e = z2;
        this.d = str2;
        this.b = context;
        if (context != null) {
            this.c = context.getSharedPreferences(str2, 0);
        }
    }

    private void b() {
        SharedPreferences sharedPreferences;
        if (this.f14673a != null || (sharedPreferences = this.c) == null) {
            return;
        }
        this.f14673a = sharedPreferences.edit();
    }

    public void c(String str, String str2) {
        if (ks.e(str) || str.equals(FitRunPlayAudio.PLAY_TYPE_T)) {
            return;
        }
        b();
        SharedPreferences.Editor editor = this.f14673a;
        if (editor != null) {
            editor.putString(str, str2);
        }
    }

    public void c(String str) {
        if (ks.e(str) || str.equals(FitRunPlayAudio.PLAY_TYPE_T)) {
            return;
        }
        b();
        SharedPreferences.Editor editor = this.f14673a;
        if (editor != null) {
            editor.remove(str);
        }
    }

    public boolean c() {
        boolean z;
        Context context;
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor editor = this.f14673a;
        if (editor != null) {
            if (!this.e && this.c != null) {
                editor.putLong(FitRunPlayAudio.PLAY_TYPE_T, currentTimeMillis);
            }
            if (!this.f14673a.commit()) {
                z = false;
                if (this.c != null && (context = this.b) != null) {
                    this.c = context.getSharedPreferences(this.d, 0);
                }
                return z;
            }
        }
        z = true;
        if (this.c != null) {
            this.c = context.getSharedPreferences(this.d, 0);
        }
        return z;
    }

    public String d(String str) {
        SharedPreferences sharedPreferences = this.c;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            if (!ks.e(string)) {
                return string;
            }
        }
        return "";
    }
}
