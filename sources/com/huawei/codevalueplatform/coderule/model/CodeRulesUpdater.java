package com.huawei.codevalueplatform.coderule.model;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.codevalueplatform.coderule.bean.basebean.Rule;
import com.huawei.codevalueplatform.coderule.bean.response.CodeRulesResponse;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import defpackage.bec;
import defpackage.bee;
import defpackage.bes;
import defpackage.bew;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes3.dex */
public class CodeRulesUpdater {
    private final bee c = new bee();
    private UpdateCallback e = null;

    public interface UpdateCallback {
        void onUpdate(String str, List<Rule> list);
    }

    public List<Rule> e() {
        CodeRulesResponse a2 = a();
        if (a2 != null && a2.getRuleList() != null) {
            return a2.getRuleList();
        }
        return new ArrayList();
    }

    public void a(String str, UpdateCallback updateCallback) {
        this.e = updateCallback;
        d(str);
    }

    public void d(String str) {
        this.c.c(str);
        CodeRulesResponse a2 = a();
        if (a2 == null) {
            a2 = new CodeRulesResponse();
        }
        List<Rule> ruleList = a2.getRuleList();
        if (!d() && ruleList != null && !ruleList.isEmpty()) {
            bes.e("CodeRulesUpdater", "rule is cached");
            UpdateCallback updateCallback = this.e;
            if (updateCallback != null) {
                updateCallback.onUpdate(c(), ruleList);
                return;
            }
            return;
        }
        bes.e("CodeRulesUpdater", "rule is out of date or not cached, update");
        a(0, new ArrayList(), a2.getCacheVersion());
    }

    private boolean d() {
        long d = bew.d("last_code_rule_update_time", 0L);
        long currentTimeMillis = System.currentTimeMillis() / 3600000;
        bes.e("CodeRulesUpdater", "recordHours:" + d + " currentHours:" + currentTimeMillis);
        return currentTimeMillis - d > 168;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:16:0x003d
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1179)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private void c(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "file release failed"
            java.lang.String r1 = "CodeRulesUpdater"
            r2 = 0
            java.lang.String r3 = r8.c()     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            r7 = 0
            r6.<init>(r4, r7)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            java.lang.String r4 = "UTF-8"
            r5.<init>(r6, r4)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L32
            r3.write(r9)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            java.lang.String r9 = "file saved success"
            defpackage.bes.e(r1, r9)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            r3.close()     // Catch: java.io.IOException -> L3d
            goto L40
        L2c:
            r9 = move-exception
            goto L42
        L2e:
            r2 = r3
            goto L32
        L30:
            r9 = move-exception
            goto L41
        L32:
            java.lang.String r9 = "file write failed"
            defpackage.bes.d(r1, r9)     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L40
            r2.close()     // Catch: java.io.IOException -> L3d
            goto L40
        L3d:
            defpackage.bes.d(r1, r0)
        L40:
            return
        L41:
            r3 = r2
        L42:
            if (r3 == 0) goto L4b
            r3.close()     // Catch: java.io.IOException -> L48
            goto L4b
        L48:
            defpackage.bes.d(r1, r0)
        L4b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.codevalueplatform.coderule.model.CodeRulesUpdater.c(java.lang.String):void");
    }

    private CodeRulesResponse a() {
        String c = c();
        bes.e("CodeRulesUpdater", "read file start");
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        try {
            FileReader fileReader = new FileReader(c);
            try {
                CodeRulesResponse codeRulesResponse = (CodeRulesResponse) new Gson().fromJson((Reader) fileReader, CodeRulesResponse.class);
                bes.e("CodeRulesUpdater", "read file end");
                if (codeRulesResponse != null) {
                    if (codeRulesResponse.getRuleList() != null) {
                        fileReader.close();
                        return codeRulesResponse;
                    }
                }
                fileReader.close();
                return null;
            } catch (Throwable th) {
                try {
                    fileReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            bes.d("CodeRulesUpdater", "read file io error");
            return null;
        } catch (Exception unused2) {
            bes.d("CodeRulesUpdater", "read file unknown error");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        return bec.e().getFilesDir().getPath() + File.separator + "query_cache.json";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final List<Rule> list, final int i2) {
        d(i).enqueue(new Callback<CodeRulesResponse>() { // from class: com.huawei.codevalueplatform.coderule.model.CodeRulesUpdater.2
            @Override // com.huawei.hms.network.httpclient.Callback
            public void onResponse(Submit<CodeRulesResponse> submit, Response<CodeRulesResponse> response) {
                CodeRulesResponse body = response.getBody();
                if (body == null) {
                    bes.b("CodeRulesUpdater", "getCodeRules startIndex " + i + " interrupt");
                    if (CodeRulesUpdater.this.e != null) {
                        CodeRulesUpdater.this.e.onUpdate(CodeRulesUpdater.this.c(), list);
                        return;
                    }
                    return;
                }
                String resultCode = body.getResultCode();
                CodeRulesUpdater.this.b(i, resultCode);
                List d = CodeRulesUpdater.this.d(body, resultCode);
                Integer nextIdx = body.getNextIdx();
                bes.e("CodeRulesUpdater", "getCodeRules, nextIndex: " + nextIdx);
                ArrayList arrayList = new ArrayList(list);
                arrayList.addAll(d);
                int cacheVersion = body.getCacheVersion();
                if (nextIdx != null && cacheVersion > i2) {
                    CodeRulesUpdater.this.a(nextIdx.intValue(), arrayList, i2);
                    return;
                }
                bes.e("CodeRulesUpdater", "cloudVersion: " + cacheVersion + " localVersion: " + i2);
                if (cacheVersion > i2) {
                    bes.e("CodeRulesUpdater", "find new cloud version, update cache");
                    CodeRulesUpdater.this.c(cacheVersion, arrayList);
                } else {
                    bes.e("CodeRulesUpdater", "no new cloud version found, do not update cache, interrupt request");
                }
                if (CodeRulesUpdater.this.e != null) {
                    CodeRulesUpdater.this.e.onUpdate(CodeRulesUpdater.this.c(), arrayList);
                }
            }

            @Override // com.huawei.hms.network.httpclient.Callback
            public void onFailure(Submit<CodeRulesResponse> submit, Throwable th) {
                bes.d("CodeRulesUpdater", "getCodeRules startIndex " + i + " fail: " + th.getMessage());
                if (CodeRulesUpdater.this.e != null) {
                    CodeRulesUpdater.this.e.onUpdate(CodeRulesUpdater.this.c(), list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, List<Rule> list) {
        bew.a("last_code_rule_update_time", System.currentTimeMillis() / 3600000);
        CodeRulesResponse codeRulesResponse = new CodeRulesResponse();
        codeRulesResponse.setCacheVersion(i);
        codeRulesResponse.setRuleList(list);
        c(new Gson().toJson(codeRulesResponse));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Rule> d(CodeRulesResponse codeRulesResponse, String str) {
        List<Rule> ruleList = codeRulesResponse.getRuleList();
        if (TextUtils.equals(str, "0") && ruleList != null) {
            return (List) ruleList.stream().filter(new Predicate() { // from class: bef
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean nonNull;
                    nonNull = Objects.nonNull((Rule) obj);
                    return nonNull;
                }
            }).collect(Collectors.toList());
        }
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str) {
        String str2;
        if (TextUtils.equals(str, "00201001") || TextUtils.equals(str, "00201002") || TextUtils.equals(str, "00202001")) {
            str2 = "get rule fail";
        } else if (TextUtils.equals(str, "00203001")) {
            str2 = "rule not update";
        } else {
            str2 = TextUtils.equals(str, "0") ? "get rule success" : "unknown status";
        }
        bes.e("CodeRulesUpdater", "getCodeRules start index is " + i + ", errorCode: " + str + ", " + str2);
    }

    private Submit<CodeRulesResponse> d(int i) {
        return this.c.c(i);
    }
}
