package com.huawei.hms.update.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.http.HttpWiseContentHelper;
import com.huawei.hms.update.http.WiseContentUrlHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ThirdPartyMarketConfigManager {
    private static final String g = SystemUtils.getManufacturer();
    private static final ThirdPartyMarketConfigManager h = new ThirdPartyMarketConfigManager();

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f6059a;
    private volatile MarketConfig b;
    private volatile long c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private final Handler e = new Handler(Looper.getMainLooper());
    private WeakReference<MarketConfigCallback> f;

    public static class AppMarket {

        /* renamed from: a, reason: collision with root package name */
        private final String f6060a;
        private final String b;
        private final String c;

        public AppMarket(String str, String str2, String str3) {
            this.f6060a = str;
            this.b = str2;
            this.c = str3;
        }

        public String getMfr() {
            return this.c;
        }

        public String getPackageName() {
            return this.f6060a;
        }

        public String getPackageSize() {
            return this.b;
        }

        public String toString() {
            return "AppMarket{packageName='" + this.f6060a + "', packageSize='" + this.b + "', mfr='" + this.c + "'}";
        }
    }

    public static class MarketConfig {

        /* renamed from: a, reason: collision with root package name */
        private String f6061a;
        private final List<AppMarket> b = new ArrayList();

        public MarketConfig(String str) {
            a(str);
        }

        private void a(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.f6061a = jSONObject.getString("version");
                JSONArray jSONArray = jSONObject.getJSONArray("appMarket");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("MFR");
                    if (ThirdPartyMarketConfigManager.g.equalsIgnoreCase(string)) {
                        this.b.add(new AppMarket(jSONObject2.getString("packageName"), jSONObject2.getString("packageSize"), string));
                    }
                }
                HMSLog.i("ThirdPartyMarketConfigManager", "<asyncGetMarketCofig> parse MarketConfig successful");
            } catch (RuntimeException e) {
                HMSLog.e("ThirdPartyMarketConfigManager", "parse MarketConfig RuntimeException: " + e.getMessage());
            } catch (JSONException e2) {
                HMSLog.e("ThirdPartyMarketConfigManager", "parse MarketConfig JSONException: " + e2.getMessage());
            }
        }

        public List<AppMarket> getAppMarketList() {
            return this.b;
        }

        public String getVersion() {
            return this.f6061a;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            Iterator<AppMarket> it = this.b.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append(" ");
            }
            sb.append("]");
            return "MarketConfig{version='" + this.f6061a + "', appMarketList=" + sb.toString() + '}';
        }
    }

    public interface MarketConfigCallback {
        void onResult(MarketConfig marketConfig);
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ MarketConfig f6062a;

        a(MarketConfig marketConfig) {
            this.f6062a = marketConfig;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.i("ThirdPartyMarketConfigManager", "<onResult> start");
            if (this.f6062a != null) {
                HMSLog.i("ThirdPartyMarketConfigManager", "<onResult> update mCachedMarketConfig");
                ThirdPartyMarketConfigManager.this.c = SystemClock.elapsedRealtime();
                ThirdPartyMarketConfigManager.this.b = this.f6062a;
            }
            if (ThirdPartyMarketConfigManager.this.f == null) {
                HMSLog.e("ThirdPartyMarketConfigManager", "<onResult> mWeakCallback is null");
                return;
            }
            MarketConfigCallback marketConfigCallback = (MarketConfigCallback) ThirdPartyMarketConfigManager.this.f.get();
            if (marketConfigCallback == null) {
                HMSLog.e("ThirdPartyMarketConfigManager", "<onResult> configCallback is null");
            } else {
                ThirdPartyMarketConfigManager.this.f = null;
                marketConfigCallback.onResult(ThirdPartyMarketConfigManager.this.b);
            }
        }
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final ThirdPartyMarketConfigManager f6063a;
        private final Context b;

        public b(Context context, ThirdPartyMarketConfigManager thirdPartyMarketConfigManager) {
            this.b = context;
            this.f6063a = thirdPartyMarketConfigManager;
        }

        private void a(MarketConfig marketConfig) {
            this.f6063a.b();
            this.f6063a.f6059a = false;
            this.f6063a.a(marketConfig);
        }

        @Override // java.lang.Runnable
        public void run() {
            String syncGetUrl = WiseContentUrlHelper.syncGetUrl(this.b);
            if (TextUtils.isEmpty(syncGetUrl)) {
                HMSLog.e("ThirdPartyMarketConfigManager", "<DownloadConfigRunnable> get url failed.");
                a(null);
                return;
            }
            HMSLog.i("ThirdPartyMarketConfigManager", "<DownloadConfigRunnable> get url successful.");
            String syncGetContent = HttpWiseContentHelper.syncGetContent(this.b, syncGetUrl);
            if (TextUtils.isEmpty(syncGetContent)) {
                HMSLog.e("ThirdPartyMarketConfigManager", "<DownloadConfigRunnable> download failed.");
                a(null);
            } else {
                HMSLog.i("ThirdPartyMarketConfigManager", "<DownloadConfigRunnable> download successful.");
                a(new MarketConfig(syncGetContent));
            }
        }
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final ThirdPartyMarketConfigManager f6064a;

        public c(ThirdPartyMarketConfigManager thirdPartyMarketConfigManager) {
            this.f6064a = thirdPartyMarketConfigManager;
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.e("ThirdPartyMarketConfigManager", "<TimeoutRunnable> download timeout");
            this.f6064a.a((MarketConfig) null);
        }
    }

    private ThirdPartyMarketConfigManager() {
    }

    public static ThirdPartyMarketConfigManager getInstance() {
        return h;
    }

    public void asyncGetMarketConfig(Context context, Handler handler, MarketConfigCallback marketConfigCallback) {
        HMSLog.i("ThirdPartyMarketConfigManager", "<asyncGetMarketConfig> start");
        if (context == null || handler == null || marketConfigCallback == null) {
            HMSLog.e("ThirdPartyMarketConfigManager", "<asyncGetMarketConfig> param contains null");
            return;
        }
        if (c()) {
            HMSLog.i("ThirdPartyMarketConfigManager", "<asyncGetSize> CachedMarketConfig: " + this.b);
            marketConfigCallback.onResult(this.b);
            return;
        }
        if (this.f6059a) {
            HMSLog.e("ThirdPartyMarketConfigManager", "<asyncGetSize> isDownloading: " + this.f6059a);
            marketConfigCallback.onResult(null);
            return;
        }
        this.f = new WeakReference<>(marketConfigCallback);
        this.f6059a = true;
        this.e.postDelayed(new c(this), 3000L);
        handler.post(new b(context.getApplicationContext(), this));
    }

    public MarketConfig getMarketConfig() {
        HMSLog.i("ThirdPartyMarketConfigManager", "<getMarketConfig> start");
        if (!c()) {
            HMSLog.e("ThirdPartyMarketConfigManager", "<getMarketConfig> mCachedMarketConfig is null");
            return null;
        }
        HMSLog.i("ThirdPartyMarketConfigManager", "<getMarketConfig> " + this.b);
        return this.b;
    }

    private boolean c() {
        if (this.c == 0) {
            HMSLog.i("ThirdPartyMarketConfigManager", "<useCachedConfig> no CachedMarketConfig");
            return false;
        }
        boolean z = SystemClock.elapsedRealtime() - this.c > 86400000;
        HMSLog.i("ThirdPartyMarketConfigManager", "<useCachedConfig> CachedMarketConfig is expiration: " + z);
        if (z) {
            return false;
        }
        if (this.b != null && this.b.getAppMarketList().size() > 0) {
            return true;
        }
        HMSLog.i("ThirdPartyMarketConfigManager", "<useCachedConfig> CachedMarketConfig is null or list.size is empty");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.e.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MarketConfig marketConfig) {
        this.d.post(new a(marketConfig));
    }
}
