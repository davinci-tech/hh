package com.huawei.health.arkuix.engine;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Process;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.haf.common.os.FileFilterUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import defpackage.msp;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class StageDelegateProxy extends AppBundlePluginProxy<StageDelegateApi> implements StageDelegateApi {
    public static final String MODULE_NAME = "PluginArkUIXEngine";
    private static final String PLUGIN_CLASS_NAME = "com.huawei.hwarkuix.oHProEngine.OHProBridgeStageDelegateApiImpl";
    private static final String TAG = "R_PluginArkUiEngineProxy";
    private static volatile StageDelegateProxy sStageDelegateProxy;
    private boolean mIsInitEngineLibFiles;
    private StageDelegateApi mStageDelegateApi;

    protected StageDelegateProxy() {
        super(TAG, MODULE_NAME, PLUGIN_CLASS_NAME);
        this.mIsInitEngineLibFiles = false;
        this.mStageDelegateApi = createPluginApi();
    }

    public static StageDelegateProxy getInstance() {
        if (sStageDelegateProxy == null) {
            synchronized (StageDelegateProxy.class) {
                if (sStageDelegateProxy == null) {
                    sStageDelegateProxy = new StageDelegateProxy();
                }
            }
        }
        return sStageDelegateProxy;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.mStageDelegateApi != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public void initialize(StageDelegateApi stageDelegateApi) {
        this.mStageDelegateApi = stageDelegateApi;
        initStageApplication(BaseApplication.vZ_());
    }

    @Override // com.huawei.health.arkuix.engine.StageDelegateApi
    public void initStageApplication(Application application) {
        if (isPluginAvaiable()) {
            initEngineLibFiles();
            this.mStageDelegateApi.initStageApplication(application);
        } else {
            deferredInstallPlugin();
        }
    }

    @Override // com.huawei.health.arkuix.engine.StageDelegateApi
    public void changeConfiguration(Configuration configuration) {
        if (isPluginAvaiable()) {
            this.mStageDelegateApi.changeConfiguration(configuration);
        }
    }

    private void initEngineLibFiles() {
        if (this.mIsInitEngineLibFiles) {
            LogUtil.a(TAG, "initEngineLibFiles has done, return");
            return;
        }
        File a2 = FileUtils.a(AppInfoUtils.f("arkui-x/libs"));
        File file = new File(a2, Process.is64Bit() ? "arm64-v8a" : "armeabi-v7a");
        File file2 = new File(file, SecurityUtils.d(String.valueOf(AppInfoUtils.d())));
        this.mIsInitEngineLibFiles = true;
        if (file2.exists()) {
            return;
        }
        initEngineLibFiles(a2, file, file2);
    }

    private void initEngineLibFiles(File file, File file2, File file3) {
        File file4;
        synchronized (this) {
            if (file3.exists()) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            InputStream inputStream = null;
            try {
                try {
                    FileUtils.e(file2);
                    String str = Process.is64Bit() ? "arkui-x-libs-64.zip" : "arkui-x-libs-32.zip";
                    file4 = new File(file, str);
                    try {
                        inputStream = BaseApplication.e().getAssets().open(str);
                        long a2 = FileUtils.a(inputStream, file4);
                        msp.c(file4.getAbsolutePath(), file.getAbsolutePath());
                        setEngineLibFilesReadOnly(file2);
                        FileUtils.e(file3, true);
                        LogUtil.c(TAG, "decode from assets, times=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", size=", Long.valueOf(a2), ", fileSize=", Long.valueOf(file4.length()));
                    } catch (IOException e) {
                        e = e;
                        FileUtils.e(file2);
                        LogUtil.e(TAG, "initEngineLibFiles fail. ex=", LogUtil.a(e));
                        FileUtils.d(inputStream);
                        FileUtils.d(file4);
                    }
                } catch (Throwable th) {
                    th = th;
                    FileUtils.d(inputStream);
                    FileUtils.d(file4);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                file4 = null;
            } catch (Throwable th2) {
                th = th2;
                file4 = null;
                FileUtils.d(inputStream);
                FileUtils.d(file4);
                throw th;
            }
            FileUtils.d(inputStream);
            FileUtils.d(file4);
        }
    }

    private void setEngineLibFilesReadOnly(File file) {
        File[] listFiles = file.listFiles(new FileFilterUtils.FileExtensionCollectFilter(".so"));
        if (CollectionUtils.b(listFiles)) {
            return;
        }
        for (File file2 : listFiles) {
            FileUtils.f(new File(file, file2.getName()));
        }
    }
}
