package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.Xml;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppLocalesStorageHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes2.dex */
class AppLocalesStorageHelper {
    static final String APPLICATION_LOCALES_RECORD_FILE = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file";
    static final String APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME = "androidx.appcompat.app.AppLocalesMetadataHolderService";
    static final String LOCALE_RECORD_ATTRIBUTE_TAG = "application_locales";
    static final String LOCALE_RECORD_FILE_TAG = "locales";
    static final String TAG = "AppLocalesStorageHelper";

    private AppLocalesStorageHelper() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        if (r3 != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0039, code lost:
    
        r2 = r4.getAttributeValue(null, androidx.appcompat.app.AppLocalesStorageHelper.LOCALE_RECORD_ATTRIBUTE_TAG);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x004a, code lost:
    
        if (r3 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String readLocales(android.content.Context r9) {
        /*
            java.lang.String r0 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = ""
            java.io.FileInputStream r3 = r9.openFileInput(r0)     // Catch: java.io.FileNotFoundException -> L71
            org.xmlpull.v1.XmlPullParser r4 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r5 = "UTF-8"
            r4.setInput(r3, r5)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            int r5 = r4.getDepth()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
        L17:
            int r6 = r4.next()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            r7 = 1
            if (r6 == r7) goto L40
            r7 = 3
            if (r6 != r7) goto L27
            int r8 = r4.getDepth()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            if (r8 <= r5) goto L40
        L27:
            if (r6 == r7) goto L17
            r7 = 4
            if (r6 != r7) goto L2d
            goto L17
        L2d:
            java.lang.String r6 = r4.getName()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            java.lang.String r7 = "locales"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
            if (r6 == 0) goto L17
            java.lang.String r5 = "application_locales"
            r6 = 0
            java.lang.String r2 = r4.getAttributeValue(r6, r5)     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L45
        L40:
            if (r3 == 0) goto L4f
            goto L4c
        L43:
            r9 = move-exception
            goto L6b
        L45:
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r4)     // Catch: java.lang.Throwable -> L43
            if (r3 == 0) goto L4f
        L4c:
            r3.close()     // Catch: java.io.IOException -> L4f
        L4f:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L67
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "Reading app Locales : Locales read from file: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file , appLocales: "
            r9.<init>(r0)
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            android.util.Log.d(r1, r9)
            goto L6a
        L67:
            r9.deleteFile(r0)
        L6a:
            return r2
        L6b:
            if (r3 == 0) goto L70
            r3.close()     // Catch: java.io.IOException -> L70
        L70:
            throw r9
        L71:
            java.lang.String r9 = "Reading app Locales : Locales record file not found: androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r1, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppLocalesStorageHelper.readLocales(android.content.Context):java.lang.String");
    }

    static void persistLocales(Context context, String str) {
        if (str.equals("")) {
            context.deleteFile(APPLICATION_LOCALES_RECORD_FILE);
            return;
        }
        try {
            FileOutputStream openFileOutput = context.openFileOutput(APPLICATION_LOCALES_RECORD_FILE, 0);
            XmlSerializer newSerializer = Xml.newSerializer();
            try {
                try {
                    newSerializer.setOutput(openFileOutput, null);
                    newSerializer.startDocument("UTF-8", true);
                    newSerializer.startTag(null, LOCALE_RECORD_FILE_TAG);
                    newSerializer.attribute(null, LOCALE_RECORD_ATTRIBUTE_TAG, str);
                    newSerializer.endTag(null, LOCALE_RECORD_FILE_TAG);
                    newSerializer.endDocument();
                    Log.d(TAG, "Storing App Locales : app-locales: " + str + " persisted successfully.");
                    if (openFileOutput == null) {
                        return;
                    }
                } catch (Throwable th) {
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e) {
                Log.w(TAG, "Storing App Locales : Failed to persist app-locales: " + str, e);
                if (openFileOutput == null) {
                    return;
                }
            }
            try {
                openFileOutput.close();
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException unused3) {
            Log.w(TAG, String.format("Storing App Locales : FileNotFoundException: Cannot open file %s for writing ", APPLICATION_LOCALES_RECORD_FILE));
        }
    }

    static void syncLocalesToFramework(Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            ComponentName componentName = new ComponentName(context, APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME);
            if (context.getPackageManager().getComponentEnabledSetting(componentName) != 1) {
                if (AppCompatDelegate.getApplicationLocales().isEmpty()) {
                    String readLocales = readLocales(context);
                    Object systemService = context.getSystemService("locale");
                    if (systemService != null) {
                        AppCompatDelegate.Api33Impl.localeManagerSetApplicationLocales(systemService, AppCompatDelegate.Api24Impl.localeListForLanguageTags(readLocales));
                    }
                }
                context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
            }
        }
    }

    static class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock = new Object();
        final Queue<Runnable> mTasks = new ArrayDeque();

        SerialExecutor(Executor executor) {
            this.mExecutor = executor;
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable runnable) {
            synchronized (this.mLock) {
                this.mTasks.add(new Runnable() { // from class: androidx.appcompat.app.AppLocalesStorageHelper$SerialExecutor$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AppLocalesStorageHelper.SerialExecutor.this.m4xd188c474(runnable);
                    }
                });
                if (this.mActive == null) {
                    scheduleNext();
                }
            }
        }

        /* renamed from: lambda$execute$0$androidx-appcompat-app-AppLocalesStorageHelper$SerialExecutor, reason: not valid java name */
        /* synthetic */ void m4xd188c474(Runnable runnable) {
            try {
                runnable.run();
            } finally {
                scheduleNext();
            }
        }

        protected void scheduleNext() {
            synchronized (this.mLock) {
                Runnable poll = this.mTasks.poll();
                this.mActive = poll;
                if (poll != null) {
                    this.mExecutor.execute(poll);
                }
            }
        }
    }
}
