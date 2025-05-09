package defpackage;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.Tasks;
import java.util.concurrent.Callable;

/* loaded from: classes3.dex */
public class afs {
    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        try {
            context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("appgallery_service_homecountry"), false, new a(context, null));
        } catch (Exception unused) {
            Log.e("HomeCountryImpl", "registerHomeCountryObserver exception");
        }
    }

    static class e implements Callable<Void> {
        private final boolean b;
        private final String c;
        private final Context d;
        private final TaskCompletionSource<String> e;

        @Override // java.util.concurrent.Callable
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Void call() {
            if (this.b) {
                Log.i("HomeCountryImpl", "force homeCountry");
                agf.c(this.e, this.d, this.c);
                return null;
            }
            String b = agc.b(this.d).b();
            if (b != null) {
                Log.i("HomeCountryImpl", "homeCountry from cache");
                if (System.currentTimeMillis() - agc.b(this.d).j() < agc.b(this.d).c()) {
                    Log.i("HomeCountryImpl", "current homeCountry is valid");
                    this.e.setResult(b);
                    return null;
                }
            }
            String d = afs.d(this.d);
            if (d != null) {
                Log.i("HomeCountryImpl", "homeCountry from settings");
                afs.b(this.d);
                agc.b(this.d).a(d);
                this.e.setResult(d);
                return null;
            }
            String d2 = agc.b(this.d).d();
            if (d2 != null) {
                Log.i("HomeCountryImpl", "homeCountry from sp");
                if (System.currentTimeMillis() - agc.b(this.d).e() < agc.b(this.d).c()) {
                    Log.i("HomeCountryImpl", "current homeCountry is valid");
                    this.e.setResult(d2);
                    return null;
                }
            }
            agf.c(this.e, this.d, this.c);
            return null;
        }

        public e(TaskCompletionSource<String> taskCompletionSource, Context context, String str, boolean z) {
            this.d = context;
            this.c = str;
            this.b = z;
            this.e = taskCompletionSource;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "appgallery_service_homecountry");
        } catch (Exception unused) {
            Log.e("HomeCountryImpl", "getHomeCountryBySettings exception");
            return null;
        }
    }

    static class a extends ContentObserver {
        private final Context b;

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            Log.d("HomeCountryImpl", "homeCountry changed");
            if (uri.equals(Settings.Secure.getUriFor("appgallery_service_homecountry"))) {
                agc.b(this.b).a(afs.d(this.b));
            }
        }

        public a(Context context, Handler handler) {
            super(handler);
            this.b = context;
        }
    }

    public static Task<String> c(Context context, String str, boolean z) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (context == null) {
            taskCompletionSource.setException(new Exception("context is null"));
        } else {
            Tasks.callInBackground(new e(taskCompletionSource, context, str, z));
        }
        return taskCompletionSource.getTask();
    }
}
