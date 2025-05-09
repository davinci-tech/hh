package com.huawei.ui.main.stories.health.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.alipay.sdk.m.p.e;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.superrule.FireCallback;
import com.huawei.health.superrule.SuperRule;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.util.BloodSugarSuperRuleEngineUtil;
import defpackage.qra;
import health.compact.a.utils.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes7.dex */
public class BloodSugarSuperRuleEngineUtil {

    /* renamed from: a, reason: collision with root package name */
    private volatile SuperRule f10258a;
    private final Handler b = new Handler(Looper.getMainLooper());
    private final List<a> c = new LinkedList();
    private qra d;
    private final Context e;

    public interface BloodSugarFactCallBack {
        void onFactCallback(boolean z, String str, String[] strArr);
    }

    public interface LoadJsCallBack {
        void onLoad(boolean z);
    }

    public BloodSugarSuperRuleEngineUtil(Context context) {
        this.e = context.getApplicationContext();
    }

    public void d(final LoadJsCallBack loadJsCallBack) {
        if (this.f10258a == null) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qqm
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarSuperRuleEngineUtil.this.a(loadJsCallBack);
                }
            });
        } else {
            this.b.post(new Runnable() { // from class: qqn
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarSuperRuleEngineUtil.b(BloodSugarSuperRuleEngineUtil.LoadJsCallBack.this);
                }
            });
        }
    }

    public static /* synthetic */ void b(LoadJsCallBack loadJsCallBack) {
        if (loadJsCallBack != null) {
            loadJsCallBack.onLoad(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void a(final LoadJsCallBack loadJsCallBack) {
        if (this.d == null) {
            this.d = new qra();
        }
        this.d.b(new UpDataFileListener() { // from class: qqj
            @Override // com.huawei.ui.main.stories.health.util.UpDataFileListener
            public final void onFinish() {
                BloodSugarSuperRuleEngineUtil.this.c(loadJsCallBack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void c(final LoadJsCallBack loadJsCallBack) {
        final String a2 = a();
        this.b.post(new Runnable() { // from class: qqp
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarSuperRuleEngineUtil.this.e(a2, loadJsCallBack);
            }
        });
    }

    public /* synthetic */ void e(String str, LoadJsCallBack loadJsCallBack) {
        if (!StringUtils.i(str)) {
            if (loadJsCallBack != null) {
                loadJsCallBack.onLoad(false);
            }
        } else {
            if (this.f10258a == null) {
                this.f10258a = new SuperRule(this.e);
                this.f10258a.d(str);
            }
            if (loadJsCallBack != null) {
                loadJsCallBack.onLoad(true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0024 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a() {
        /*
            r4 = this;
            qra r0 = r4.d
            java.io.File r0 = r0.e()
            java.lang.String r1 = "BloodSugarSuperRuleEngineUtil"
            if (r0 == 0) goto L1d
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L14
            r2.<init>(r0)     // Catch: java.io.FileNotFoundException -> L14
            java.lang.String r0 = r4.b(r2)     // Catch: java.io.FileNotFoundException -> L14
            goto L1e
        L14:
            java.lang.String r0 = "not down BloodSugarSuperRuleFile"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
        L1d:
            r0 = 0
        L1e:
            boolean r2 = health.compact.a.utils.StringUtils.g(r0)
            if (r2 == 0) goto L3e
            android.content.Context r2 = r4.e     // Catch: java.io.IOException -> L35
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: java.io.IOException -> L35
            java.lang.String r3 = "superrule/BloodSugarAnalyze.js"
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.io.IOException -> L35
            java.lang.String r0 = r4.b(r2)     // Catch: java.io.IOException -> L35
            goto L3e
        L35:
            java.lang.String r2 = "not exists assets BloodSugarSuperRuleFile"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r2)
        L3e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.util.BloodSugarSuperRuleEngineUtil.a():java.lang.String");
    }

    private String b(InputStream inputStream) {
        String str;
        str = "";
        try {
            if (inputStream == null) {
                return "";
            }
            try {
                byte[] bArr = new byte[inputStream.available()];
                str = inputStream.read(bArr) > 0 ? new String(bArr, StandardCharsets.UTF_8) : "";
                try {
                    inputStream.close();
                } catch (IOException unused) {
                    LogUtil.b("BloodSugarSuperRuleEngineUtil", "failed to close BloodSugarSuperRuleFile");
                }
            } catch (IOException unused2) {
                LogUtil.b("BloodSugarSuperRuleEngineUtil", "failed to read BloodSugarSuperRuleFile");
            }
            return str;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused3) {
                LogUtil.b("BloodSugarSuperRuleEngineUtil", "failed to close BloodSugarSuperRuleFile");
            }
        }
    }

    public void a(int i, float f, float f2, BloodSugarFactCallBack bloodSugarFactCallBack) {
        b bVar = new b();
        bVar.e = i;
        bVar.c = f;
        bVar.d = f2;
        c(bVar, bloodSugarFactCallBack);
    }

    public void e(float f, float f2, BloodSugarFactCallBack bloodSugarFactCallBack) {
        c cVar = new c();
        cVar.c = f;
        cVar.d = f2;
        c(cVar, bloodSugarFactCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(final d dVar, final BloodSugarFactCallBack bloodSugarFactCallBack) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.b.post(new Runnable() { // from class: qqr
                @Override // java.lang.Runnable
                public final void run() {
                    BloodSugarSuperRuleEngineUtil.this.c(dVar, bloodSugarFactCallBack);
                }
            });
            return;
        }
        String json = new Gson().toJson(dVar);
        LogUtil.a("BloodSugarSuperRuleEngineUtil", "fact = ", json);
        this.c.add(new a(json, bloodSugarFactCallBack));
        if (this.c.size() == 1) {
            e(json, bloodSugarFactCallBack);
        }
    }

    private void c() {
        this.b.post(new Runnable() { // from class: qqq
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarSuperRuleEngineUtil.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        this.c.remove(0);
        if (this.c.isEmpty()) {
            return;
        }
        a aVar = this.c.get(0);
        e(aVar.b, aVar.e);
    }

    private void e(String str, final BloodSugarFactCallBack bloodSugarFactCallBack) {
        this.f10258a.b(str, new FireCallback() { // from class: qqo
            @Override // com.huawei.health.superrule.FireCallback
            public final void onReceiveValue(Object obj) {
                BloodSugarSuperRuleEngineUtil.this.c(bloodSugarFactCallBack, (String) obj);
            }
        });
    }

    public /* synthetic */ void c(BloodSugarFactCallBack bloodSugarFactCallBack, String str) {
        boolean z;
        String[] strArr;
        JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
        JsonElement jsonElement = asJsonObject.get("matched");
        if (jsonElement != null) {
            z = jsonElement.getAsBoolean();
            r1 = asJsonObject.get("suggestion") != null ? asJsonObject.get("suggestion").getAsString() : null;
            strArr = c(asJsonObject);
            LogUtil.a("BloodSugarSuperRuleEngineUtil", "suggestion = ", r1);
        } else {
            z = false;
            strArr = null;
        }
        bloodSugarFactCallBack.onFactCallback(z, r1, strArr);
        c();
    }

    private String[] c(JsonObject jsonObject) {
        if (jsonObject.get(e.n) == null) {
            return new String[0];
        }
        String asString = jsonObject.get(e.n).getAsString();
        return StringUtils.g(asString) ? new String[0] : asString.split(",");
    }

    static class a {
        public String b;
        public BloodSugarFactCallBack e;

        a(String str, BloodSugarFactCallBack bloodSugarFactCallBack) {
            this.b = str;
            this.e = bloodSugarFactCallBack;
        }
    }

    public static class d {

        @SerializedName("type")
        int b;

        @SerializedName("beforeBloodSugar")
        float c;

        @SerializedName("afterBloodSugar")
        float d;

        private d() {
        }
    }

    static class b extends d {

        @SerializedName("mealType")
        int e;

        b() {
            super();
            this.b = 0;
        }
    }

    static class c extends d {
        c() {
            super();
            this.b = 1;
        }
    }
}
