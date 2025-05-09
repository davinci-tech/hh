package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.marketing.request.GlobalSearchApi;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.KnowledgeSearchHighlight;
import com.huawei.health.marketing.request.KnowledgeSearchResultWrapper;
import com.huawei.health.marketing.request.KnowledgeSearchSingleResult;
import com.huawei.health.marketing.request.MaterialSearchReqBody;
import com.huawei.health.marketing.request.SceneSearchResult;
import com.huawei.health.marketing.request.SceneSearchSingleResult;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes8.dex */
public class eik {
    private static void d(final String str, final String str2, final String str3, final String str4, final CountDownLatch countDownLatch, final List<SceneSearchSingleResult> list) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: eik.2
            @Override // java.lang.Runnable
            public void run() {
                eik.c(str, 0, 20, str2, str3, str4, new UiCallback<List<SceneSearchSingleResult>>() { // from class: eik.2.1
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str5) {
                        LogUtil.b("MaterialSearchRequestUtil", "search scene queryText = ", str, ", productRegion = ", str2, " failed, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str5);
                        countDownLatch.countDown();
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<SceneSearchSingleResult> list2) {
                        if (koq.c(list2)) {
                            list.addAll(list2);
                        }
                        countDownLatch.countDown();
                    }
                });
            }
        });
    }

    private static void a(final String str, final String str2, final CountDownLatch countDownLatch, final List<KnowledgeSearchSingleResult> list) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: eik.3
            @Override // java.lang.Runnable
            public void run() {
                final boolean[] zArr = {false};
                final int[] iArr = {0};
                do {
                    eik.d(str, iArr[0], 20, str2, new UiCallback<List<KnowledgeSearchSingleResult>>() { // from class: eik.3.3
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i, String str3) {
                            LogUtil.b("MaterialSearchRequestUtil", "search knowledge queryText = ", str, " failed, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str3);
                            zArr[0] = false;
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(List<KnowledgeSearchSingleResult> list2) {
                            if (koq.c(list2)) {
                                list.addAll(list2);
                                int[] iArr2 = iArr;
                                int i = iArr2[0] + 1;
                                iArr2[0] = i;
                                zArr[0] = i < 5;
                                return;
                            }
                            LogUtil.a("MaterialSearchRequestUtil", "searchKnowledge done, no more data searched!");
                            zArr[0] = false;
                        }
                    });
                } while (zArr[0]);
                countDownLatch.countDown();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0076, code lost:
    
        if (r9.await(com.huawei.hms.network.httpclient.util.PreConnectManager.CONNECT_INTERNAL, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(final java.lang.String r11, final com.huawei.basefitnessadvice.callback.UiCallback<com.huawei.health.marketing.request.GlobalSearchResult> r12) {
        /*
            java.lang.String r0 = "MaterialSearchRequestUtil"
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            android.os.Looper r2 = android.os.Looper.myLooper()
            if (r1 != r2) goto L19
            com.huawei.haf.threadpool.ThreadPoolManager r0 = com.huawei.haf.threadpool.ThreadPoolManager.d()
            eik$5 r1 = new eik$5
            r1.<init>()
            r0.execute(r1)
            return
        L19:
            java.lang.String r1 = a()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L25
            r2 = 2
            goto L26
        L25:
            r2 = 3
        L26:
            java.util.concurrent.CountDownLatch r9 = new java.util.concurrent.CountDownLatch
            r9.<init>(r2)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L4d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "device_"
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r4 = r2.toString()
            java.lang.String r5 = "device"
            r2 = r11
            r3 = r1
            r6 = r9
            r7 = r10
            d(r2, r3, r4, r5, r6, r7)
        L4d:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r4 = "health_help_all"
            java.lang.String r5 = "health_help_all"
            java.lang.String r6 = "help"
            r3 = r11
            r7 = r9
            r8 = r2
            d(r3, r4, r5, r6, r7, r8)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 == 0) goto L6b
            java.lang.String r1 = "health_help_all"
        L6b:
            a(r11, r1, r9, r3)
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.InterruptedException -> L79
            r4 = 10000(0x2710, double:4.9407E-320)
            boolean r1 = r9.await(r4, r1)     // Catch: java.lang.InterruptedException -> L79
            if (r1 != 0) goto L8b
            goto L82
        L79:
            java.lang.String r1 = "search interrupted!"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L82:
            java.lang.String r1 = "search time out!"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        L8b:
            com.huawei.health.marketing.request.GlobalSearchResult r0 = new com.huawei.health.marketing.request.GlobalSearchResult
            r0.<init>()
            java.lang.String r1 = "material"
            r0.setCategoryId(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            c(r11, r10, r1)
            c(r11, r2, r1)
            a(r3, r1)
            r0.setContent(r1)
            boolean r11 = defpackage.koq.c(r1)
            if (r11 == 0) goto Lb0
            r12.onSuccess(r0)
            goto Lb6
        Lb0:
            r11 = -1
            java.lang.String r0 = "search material no content found!"
            r12.onFailure(r11, r0)
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eik.a(java.lang.String, com.huawei.basefitnessadvice.callback.UiCallback):void");
    }

    private static void a(List<KnowledgeSearchSingleResult> list, List<GlobalSearchContent> list2) {
        KnowledgeSearchHighlight highlight;
        for (KnowledgeSearchSingleResult knowledgeSearchSingleResult : list) {
            if (knowledgeSearchSingleResult != null && (highlight = knowledgeSearchSingleResult.getHighlight()) != null) {
                List<String> titleList = highlight.getTitleList();
                if (!koq.b(titleList)) {
                    String str = titleList.get(0);
                    GlobalSearchContent globalSearchContent = new GlobalSearchContent();
                    globalSearchContent.setTitle(str);
                    globalSearchContent.setId(knowledgeSearchSingleResult.getId());
                    globalSearchContent.setType(a.w);
                    list2.add(globalSearchContent);
                }
            }
        }
    }

    private static void c(String str, List<SceneSearchSingleResult> list, List<GlobalSearchContent> list2) {
        for (SceneSearchSingleResult sceneSearchSingleResult : list) {
            if (sceneSearchSingleResult != null) {
                String highlightTitle = sceneSearchSingleResult.getHighlightTitle();
                String title = !TextUtils.isEmpty(highlightTitle) ? highlightTitle : sceneSearchSingleResult.getTitle();
                String cdnUrl = sceneSearchSingleResult.getCdnUrl();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(cdnUrl)) {
                    LogUtil.b("MaterialSearchRequestUtil", "search ", str, ", but one of them empty: contentTitle = ", title, ", deeplink = ", cdnUrl);
                } else {
                    GlobalSearchContent globalSearchContent = new GlobalSearchContent();
                    globalSearchContent.setTitle(title);
                    globalSearchContent.setDeepLink(cdnUrl);
                    list2.add(globalSearchContent);
                }
            }
        }
    }

    private static MaterialSearchReqBody b(String str, int i, int i2, String str2, String str3, String str4) {
        MaterialSearchReqBody materialSearchReqBody = new MaterialSearchReqBody();
        materialSearchReqBody.setSearchWord(str);
        materialSearchReqBody.setSize(i2);
        if ("/search/v1/knowledge/search".equals(str4)) {
            materialSearchReqBody.setSite(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
            materialSearchReqBody.setStart(i + 1);
            materialSearchReqBody.setSort(2);
        } else {
            materialSearchReqBody.setDeviceName(str2);
            materialSearchReqBody.setSceneType(str3);
            materialSearchReqBody.setStart(i);
        }
        return materialSearchReqBody;
    }

    public static void c(String str, int i, int i2, String str2, String str3, String str4, UiCallback<List<SceneSearchSingleResult>> uiCallback) {
        String str5 = GRSManager.a(com.huawei.haf.application.BaseApplication.e()).getUrl("materialUrl") + "/search/v1/scene/search";
        LogUtil.a("MaterialSearchRequestUtil", "searchScene content url: ", str5);
        Map<String, String> d = d(BaseApplication.getContext(), "/search/v1/scene/search", str2);
        LogUtil.a("MaterialSearchRequestUtil", "searchScene content headers:", d);
        String e = nrv.e(b(str, i, i2, str3, str4, "/search/v1/scene/search"), new TypeToken<MaterialSearchReqBody>() { // from class: eik.1
        }.getType());
        LogUtil.a("MaterialSearchRequestUtil", "searchScene content body: ", e);
        try {
            d(((GlobalSearchApi) lqi.d().b(GlobalSearchApi.class)).searchContentForScene(str5, d, e).execute(), uiCallback);
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            LogUtil.h("MaterialSearchRequestUtil", "searchScene exception");
            uiCallback.onFailure(1, "searchScene Exception");
        }
    }

    public static void d(String str, int i, int i2, String str2, UiCallback<List<KnowledgeSearchSingleResult>> uiCallback) {
        String str3 = GRSManager.a(com.huawei.haf.application.BaseApplication.e()).getUrl("materialUrl") + "/search/v1/knowledge/search";
        LogUtil.a("MaterialSearchRequestUtil", "searchKnowledge content url: ", str3);
        Map<String, String> d = d(BaseApplication.getContext(), "/search/v1/knowledge/search", str2);
        LogUtil.a("MaterialSearchRequestUtil", "searchKnowledge content headers:", d);
        String e = nrv.e(b(str, i, i2, "", "", "/search/v1/knowledge/search"), new TypeToken<MaterialSearchReqBody>() { // from class: eik.4
        }.getType());
        LogUtil.a("MaterialSearchRequestUtil", "searchKnowledge content body: ", e);
        try {
            c(((GlobalSearchApi) lqi.d().b(GlobalSearchApi.class)).searchContentForKnowledge(str3, d, e).execute(), uiCallback);
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            LogUtil.h("MaterialSearchRequestUtil", "searchKnowledge exception");
            uiCallback.onFailure(-1, "searchKnowledge Exception");
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MaterialSearchRequestUtil", "normalizedHuid huid is null ");
            return "";
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < 16) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    private static String a() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "MaterialSearchRequestUtil");
        return (deviceInfo == null || deviceInfo.getDeviceConnectState() != 2) ? "" : deviceInfo.getHiLinkDeviceId();
    }

    private static Map<String, String> d(Context context, String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("X-Request-ID", a(!LoginInit.getInstance(context).isBrowseMode() ? LoginInit.getInstance(context).getUsetId() : "0"));
        hashMap.put("X-App-Id", BaseApplication.getAppPackage());
        hashMap.put("X-App-Version", CommonUtil.e(context).split(Constants.LINK)[0]);
        if ("/search/v1/scene/search".equals(str)) {
            hashMap.put("productRegion", str2);
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        if (LanguageUtil.h(context)) {
            language = "zh-cn";
        }
        hashMap.put("lang", language);
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        return hashMap;
    }

    private static void d(Response<SceneSearchResult> response, UiCallback<List<SceneSearchSingleResult>> uiCallback) {
        if (response.isOK()) {
            LogUtil.a("MaterialSearchRequestUtil", "resolveResponseForScene response is OK.");
            SceneSearchResult body = response.getBody();
            if (body == null) {
                uiCallback.onFailure(1, "resolveResponseForScene result is null");
                return;
            }
            int resultCode = body.getResultCode();
            if (resultCode != 0) {
                LogUtil.h("MaterialSearchRequestUtil", "resolveResponseForScene result code: ", Integer.valueOf(resultCode));
                uiCallback.onFailure(1, "resolveResponseForScene result is error");
                return;
            }
            List<SceneSearchSingleResult> results = body.getResults();
            if (koq.b(results)) {
                uiCallback.onFailure(1, "resolveResponseForScene result is empty");
                return;
            } else {
                uiCallback.onSuccess(results);
                return;
            }
        }
        uiCallback.onFailure(1, "resolveResponseForScene result is not ok, status code: " + response.getCode());
    }

    private static void c(Response<KnowledgeSearchResultWrapper> response, UiCallback<List<KnowledgeSearchSingleResult>> uiCallback) {
        if (response.isOK()) {
            LogUtil.a("MaterialSearchRequestUtil", "resolveResponseForKnowledge response is OK.");
            KnowledgeSearchResultWrapper body = response.getBody();
            if (body == null) {
                uiCallback.onFailure(-1, "resolveResponseForKnowledge result is null");
                return;
            }
            int code = body.getCode();
            if (code != 1) {
                LogUtil.h("MaterialSearchRequestUtil", "resolveResponseForKnowledge result code: ", Integer.valueOf(code));
                uiCallback.onFailure(code, "resolveResponseForKnowledge result is error");
                return;
            }
            List<KnowledgeSearchSingleResult> knowList = body.getResult().getKnowList();
            if (koq.b(knowList)) {
                uiCallback.onFailure(-1, "resolveResponseForKnowledge result is empty");
                return;
            } else {
                uiCallback.onSuccess(knowList);
                return;
            }
        }
        uiCallback.onFailure(-1, "resolveResponseForKnowledge result is not ok, status code: " + response.getCode());
    }
}
