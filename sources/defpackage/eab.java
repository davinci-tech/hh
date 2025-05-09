package defpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.knit.model.KnitPageGroupConfig;
import com.huawei.health.knit.model.mainpage.MainPageConfig;
import com.huawei.health.knit.model.mainpage.MainPageGroupConfig;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import health.compact.a.LogAnonymous;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public final class eab {
    /* JADX WARN: Multi-variable type inference failed */
    public static KnitPageGroupConfig a(Context context, String str) {
        Throwable th;
        InputStream inputStream;
        Closeable closeable = null;
        KnitPageGroupConfig knitPageGroupConfig = null;
        AssetManager assets = context != null ? context.getAssets() : null;
        try {
            if (assets == null) {
                return null;
            }
            try {
                inputStream = assets.open(str);
                try {
                    KnitPageGroupConfig knitPageGroupConfig2 = (KnitPageGroupConfig) ixu.e(inputStream, new TypeToken<KnitPageGroupConfig>() { // from class: eab.4
                    });
                    IoUtils.e(inputStream);
                    knitPageGroupConfig = knitPageGroupConfig2;
                    assets = inputStream;
                } catch (IOException e) {
                    e = e;
                    LogUtil.b("KnitHelper", "sport template assets load failed: ", LogAnonymous.b((Throwable) e));
                    IoUtils.e(inputStream);
                    assets = inputStream;
                    return knitPageGroupConfig;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                IoUtils.e(closeable);
                throw th;
            }
            return knitPageGroupConfig;
        } catch (Throwable th3) {
            closeable = assets;
            th = th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static MainPageGroupConfig c(Context context, String str) {
        Throwable th;
        InputStream inputStream;
        Closeable closeable = null;
        MainPageGroupConfig mainPageGroupConfig = null;
        AssetManager assets = context != null ? context.getAssets() : null;
        try {
            if (assets == null) {
                return null;
            }
            try {
                inputStream = assets.open(str);
                try {
                    MainPageGroupConfig mainPageGroupConfig2 = (MainPageGroupConfig) ixu.e(inputStream, new TypeToken<MainPageGroupConfig>() { // from class: eab.3
                    });
                    IoUtils.e(inputStream);
                    mainPageGroupConfig = mainPageGroupConfig2;
                    assets = inputStream;
                } catch (IOException e) {
                    e = e;
                    LogUtil.b("KnitHelper", "sport template assets load failed: ", LogAnonymous.b((Throwable) e));
                    IoUtils.e(inputStream);
                    assets = inputStream;
                    return mainPageGroupConfig;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                IoUtils.e(closeable);
                throw th;
            }
            return mainPageGroupConfig;
        } catch (Throwable th3) {
            closeable = assets;
            th = th3;
        }
    }

    public static KnitFragment abS_(Context context, int i, Bundle bundle) {
        MainPageGroupConfig c = c(context, "MainPageSectionsConfig.json");
        ArrayList<MainPageConfig> pagesConfig = c != null ? c.getPagesConfig() : null;
        if (pagesConfig == null) {
            return null;
        }
        for (int i2 = 0; i2 < pagesConfig.size(); i2++) {
            MainPageConfig mainPageConfig = pagesConfig.get(i2);
            if (mainPageConfig != null) {
                int pageType = mainPageConfig.getPageType();
                int resPosId = mainPageConfig.getResPosId();
                String a2 = nrv.a(mainPageConfig);
                if (pageType == i) {
                    return KnitFragment.newInstance(a2, new BasePageResTrigger(context, resPosId, null).setExtra(bundle));
                }
            }
        }
        return null;
    }
}
