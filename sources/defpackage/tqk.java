package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.repository.AuthInfoRepositoryImpl;
import java.util.List;

/* loaded from: classes8.dex */
public class tqk extends SQLiteOpenHelper {
    private static Context d;
    private Handler b;
    private HandlerThread e;

    static class d {

        /* renamed from: a, reason: collision with root package name */
        public static final tqk f17347a = new tqk(tqk.d);
    }

    private tqk(Context context) {
        super(context, "WearEngine.db", (SQLiteDatabase.CursorFactory) null, 102);
        HandlerThread handlerThread = new HandlerThread("WearEngineSqLiteOpenHelperThread");
        this.e = handlerThread;
        handlerThread.start();
        Looper looper = this.e.getLooper();
        if (looper != null) {
            this.b = new Handler(looper);
        }
    }

    public static tqk e(Context context) {
        if (context == null) {
            return d.f17347a;
        }
        d = context.getApplicationContext();
        return d.f17347a;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        tos.b("WearEngineSqLiteOpenHelper", "onCreate DataBase");
        if (sQLiteDatabase == null) {
            tos.e("WearEngineSqLiteOpenHelper", "onCreate db is null");
            return;
        }
        sQLiteDatabase.execSQL(tqj.e());
        sQLiteDatabase.execSQL(tqi.a());
        sQLiteDatabase.execSQL(tqg.c());
        sQLiteDatabase.execSQL(tqf.e());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        tos.b("WearEngineSqLiteOpenHelper", "onUpgrade DataBase oldVersion=" + i + " newVersion=" + i2);
        if (i2 <= 101) {
            return;
        }
        feB_(sQLiteDatabase);
    }

    private void feB_(final SQLiteDatabase sQLiteDatabase) {
        Runnable runnable = new Runnable() { // from class: tqk.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    tqk.this.feC_(sQLiteDatabase);
                } catch (Exception unused) {
                    tos.e("WearEngineSqLiteOpenHelper", "onUpgrade Exception");
                }
            }
        };
        Handler handler = this.b;
        if (handler != null) {
            handler.postDelayed(runnable, 5000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void feC_(SQLiteDatabase sQLiteDatabase) {
        tos.b("WearEngineSqLiteOpenHelper", "updateAuthInfo entry");
        if (sQLiteDatabase == null) {
            tos.d("WearEngineSqLiteOpenHelper", "updateAuthInfo db == null");
            return;
        }
        List<AuthInfo> parseAuthInfo = AuthInfoRepositoryImpl.parseAuthInfo(d, sQLiteDatabase.query("tb_wear_engine_auth_info", tqj.c(), null, null, null, null, null));
        if (parseAuthInfo.isEmpty()) {
            tos.d("WearEngineSqLiteOpenHelper", "updateAuthInfo authInfoList isEmpty");
            return;
        }
        for (AuthInfo authInfo : parseAuthInfo) {
            ContentValues buildAuthInfoContentValues = AuthInfoRepositoryImpl.buildAuthInfoContentValues(authInfo);
            if (buildAuthInfoContentValues != null) {
                sQLiteDatabase.update("tb_wear_engine_auth_info", buildAuthInfoContentValues, "key =? ", new String[]{authInfo.getKey()});
            }
        }
    }
}
