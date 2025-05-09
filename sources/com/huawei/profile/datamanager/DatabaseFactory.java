package com.huawei.profile.datamanager;

import android.content.Context;
import com.huawei.profile.utils.SensitiveUtil;
import com.huawei.profile.utils.logger.DsLog;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes6.dex */
public class DatabaseFactory {
    private static final String PROFILE_PACKAGE_NAME = "com.huawei.profile";
    private static final String TAG = "DatabaseFactory";
    private static final int WAIT_LOCK_TIME = 3000;
    private static final Lock DATABASE_LOCK = new ReentrantLock();
    private static AbstractDatabase database = null;

    public interface DatabaseCallback<T> {
        void accept(T t);
    }

    private DatabaseFactory() {
    }

    public static AbstractDatabase generateDb(Context context) {
        return generateDb(context, null);
    }

    public static AbstractDatabase generateDb(Context context, DatabaseCallback<AbstractDatabase> databaseCallback) {
        Lock lock;
        if (context == null) {
            DsLog.et(TAG, "generateDb failed context is null.", new Object[0]);
            return new FalseDatabase();
        }
        try {
            lock = DATABASE_LOCK;
        } catch (InterruptedException unused) {
            DsLog.et(TAG, " thread was interrupted: " + Thread.currentThread().getName(), new Object[0]);
        }
        if (lock.tryLock(3000L, TimeUnit.MILLISECONDS)) {
            if (database != null) {
                lock.unlock();
                return database;
            }
            if (isProfileApk(context)) {
                database = generateDbByName(context, "com.huawei.profile.datamanager.ProfileSQLiteDatabase");
            } else {
                database = generateDbByName(context, "com.huawei.profile.datamanager.SQLiteDatabaseSdk");
            }
            if (databaseCallback != null) {
                databaseCallback.accept(database);
            }
            lock.unlock();
            return database;
        }
        DsLog.wt(TAG, "get db lock timeout", new Object[0]);
        return new FalseDatabase();
    }

    private static boolean isProfileApk(Context context) {
        if (!"com.huawei.profile".equals(context.getPackageName())) {
            return false;
        }
        DsLog.it(TAG, "package name is profile, profile may installed", new Object[0]);
        return true;
    }

    private static AbstractDatabase generateDbByName(Context context, String str) {
        try {
            Object newInstance = Class.forName(str).getDeclaredConstructor(Context.class).newInstance(context);
            if (newInstance instanceof AbstractDatabase) {
                return (AbstractDatabase) newInstance;
            }
        } catch (ClassNotFoundException e) {
            DsLog.et(TAG, " ClassNotFoundException: " + e.getMessage(), new Object[0]);
        } catch (IllegalAccessException e2) {
            DsLog.et(TAG, " IllegalAccessException: " + e2.getMessage(), new Object[0]);
        } catch (IllegalArgumentException e3) {
            DsLog.et(TAG, " IllegalArgumentException: " + SensitiveUtil.getMessage(e3), new Object[0]);
        } catch (InstantiationException e4) {
            DsLog.et(TAG, " InstantiationException: " + e4.getMessage(), new Object[0]);
        } catch (NoSuchMethodException e5) {
            DsLog.et(TAG, " NoSuchMethodException: " + e5.getMessage(), new Object[0]);
        } catch (InvocationTargetException e6) {
            DsLog.et(TAG, " InvocationTargetException: " + e6.getMessage(), new Object[0]);
        }
        return new FalseDatabase();
    }
}
