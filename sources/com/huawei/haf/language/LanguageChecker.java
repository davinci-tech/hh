package com.huawei.haf.language;

import com.huawei.haf.download.DownloadPluginCallback;
import java.io.File;
import java.util.Map;

/* loaded from: classes.dex */
public final class LanguageChecker {
    private static final String c = System.getProperty("line.separator");

    /* renamed from: a, reason: collision with root package name */
    private final LanguagePackage f2119a;
    private Map<String, String> b;
    private Map<String, String> d;
    private boolean e;
    private Map<String, String> f;

    class CheckDownloadPluginCallback implements DownloadPluginCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ LanguageChecker f2120a;
        private final DownloadPluginCallback b;
        private final boolean d;

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public boolean onDownloadPluginResult(int i, String str, int i2) {
            if (this.d != this.f2120a.e) {
                return false;
            }
            String d = this.f2120a.f2119a.d(str, null);
            if (i2 == 0) {
                File a2 = this.f2120a.f2119a.a(str);
                if (a2 != null) {
                    this.f2120a.f.put(str, a2.getName());
                } else {
                    this.f2120a.b.put(str, d);
                }
            } else {
                this.f2120a.d.put(str, d);
            }
            this.b.onDownloadPluginResult(i, str, i2);
            return true;
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void startDownloadProgress(int i) {
            if (this.d != this.f2120a.e) {
                return;
            }
            this.b.startDownloadProgress(i);
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void showDownloadProgress(int i, int i2, int i3) {
            if (this.d != this.f2120a.e) {
                return;
            }
            this.b.showDownloadProgress(i, i2, i3);
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void onDownloadResult(int i, int i2) {
            if (this.d != this.f2120a.e) {
                return;
            }
            this.b.onDownloadResult(i, i2);
        }
    }
}
