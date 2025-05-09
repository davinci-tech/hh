package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public class caj {
    private static final HashMap<String, String> e;

    /* renamed from: a, reason: collision with root package name */
    private cai f580a;
    private cak c;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        e = hashMap;
        hashMap.put("ZH", "zh-CN");
        hashMap.put("EN", "en-US,en-GB");
        hashMap.put("TH", MLAsrConstants.LAN_TH_TH);
        hashMap.put("JA", "ja-JP");
        hashMap.put("PL", "pl-PL");
        hashMap.put("RO", "ro-RO");
        hashMap.put("TR", MLAsrConstants.LAN_TR_TR);
        hashMap.put("SV", "sv-SE");
        hashMap.put("DE", MLAsrConstants.LAN_DE_DE);
        hashMap.put("FR", MLAsrConstants.LAN_FR_FR);
        hashMap.put("ES", "es-ES,es-US");
        hashMap.put("IT", MLAsrConstants.LAN_IT_IT);
        hashMap.put("AR", "ar-EG");
        hashMap.put("RU", MLAsrConstants.LAN_RU_RU);
        hashMap.put("CS", "cs-CZ");
        hashMap.put("PT", "pt-PT");
        hashMap.put("BG", "bg-BG");
    }

    private caj() {
    }

    /* loaded from: classes3.dex */
    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final caj f582a = new caj();
    }

    public static caj a() {
        caj cajVar = d.f582a;
        cajVar.a(BaseApplication.e());
        return cajVar;
    }

    private void a(Context context) {
        if (context == null) {
            LogUtil.h("Track_SportNounManager", "loadSportNameJsonFile() context == null");
            return;
        }
        AssetManager assets = context.getAssets();
        if (this.f580a == null) {
            try {
                this.f580a = (cai) ixu.d(assets.open("SportWordConfig.json"), cai.class);
            } catch (IOException e2) {
                LogUtil.b("Track_SportNounManager", LogAnonymous.b((Throwable) e2));
            }
            Object[] objArr = new Object[2];
            objArr[0] = "mSportWordsInfo : ";
            cai caiVar = this.f580a;
            objArr[1] = caiVar == null ? r1 : caiVar.a();
            LogUtil.a("Track_SportNounManager", objArr);
        }
        if (this.c == null) {
            try {
                this.c = (cak) ixu.d(assets.open("SportNounGroup.json"), cak.class);
            } catch (IOException e3) {
                LogUtil.b("Track_SportNounManager", LogAnonymous.b((Throwable) e3));
            }
            Object[] objArr2 = new Object[2];
            objArr2[0] = "mSportNounGroupInfo : ";
            cak cakVar = this.c;
            objArr2[1] = cakVar != null ? cakVar.b() : 0;
            LogUtil.a("Track_SportNounManager", objArr2);
        }
    }

    public cal c(String str) {
        cai caiVar;
        if (TextUtils.isEmpty(str) || (caiVar = this.f580a) == null || caiVar.c() == null) {
            return null;
        }
        Iterator<cal> it = this.f580a.c().iterator();
        while (it.hasNext()) {
            cal next = it.next();
            if (next != null && str.equals(next.c())) {
                LogUtil.a("Track_SportNounManager", "wordName: ", str, ", secondSortNum: ", next.b(), ", wordNum: ", next.e());
                return next;
            }
        }
        return null;
    }

    public void e() {
        LogUtil.a("Track_SportNounManager", "jumpToMainPage()");
        GRSManager.a(BaseApplication.e()).e("helpCustomerUrl", new GrsQueryCallback() { // from class: caj.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("Track_SportNounManager", "jumpToMainPage() urlDomain: ", str);
                if (!TextUtils.isEmpty(str)) {
                    caj cajVar = caj.this;
                    cajVar.e(cajVar.d(str));
                } else {
                    LogUtil.h("Track_SportNounManager", "jumpToMainPage() urlDomain is Empty");
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("Track_SportNounManager", "jumpToMainPage() onCallBackFail() -> ", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str) {
        String format;
        LogUtil.a("Track_SportNounManager", "getMainPageUrl()");
        int m = CommonUtil.m(BaseApplication.e(), LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1009));
        LogUtil.a("Track_SportNounManager", "getMainPageUrl() siteId: ", Integer.valueOf(m));
        if (m == 1) {
            if (d().equals("zh-CN")) {
                format = String.format(Locale.ENGLISH, "/hwtips/help/health_help_all/%s/index.html#/card/ug011?cid=11080", "zh-CN");
            } else {
                format = String.format(Locale.ENGLISH, "/hwtips/help/health_help_all/%s/index.html#/card/ug011?cid=11080", "en-US");
            }
        } else {
            format = String.format(Locale.ENGLISH, "/handbook/mapJumppage/EMUI10.0/C001B001/en-US/index.html?lang=%s&groupId=ug011", d());
        }
        String str2 = str + format;
        LogUtil.a("Track_SportNounManager", "getMainPageUrl() mainPageUrl: ", str2);
        return str2;
    }

    public void a(String str) {
        LogUtil.a("Track_SportNounManager", "jumpToDetailPage() wordName: ", str);
        final cal c = c(str);
        if (c == null) {
            LogUtil.h("Track_SportNounManager", "sportWordsItem == null");
            return;
        }
        if (TextUtils.isEmpty(c.b()) || TextUtils.isEmpty(c.e())) {
            LogUtil.h("Track_SportNounManager", "getSecondSortNum or getWordNum is empty");
            return;
        }
        int m = CommonUtil.m(BaseApplication.e(), LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1009));
        LogUtil.a("Track_SportNounManager", "jumpToDetailPage() siteId: ", Integer.valueOf(m));
        if (m == 1) {
            GRSManager.a(BaseApplication.e()).e("domainKlgMapDtlUrl", new GrsQueryCallback() { // from class: caj.4
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str2) {
                    LogUtil.a("Track_SportNounManager", "jumpToDetailPage() urlDomain: ", str2);
                    if (!TextUtils.isEmpty(str2)) {
                        caj cajVar = caj.this;
                        cajVar.e(cajVar.d(str2, c));
                    } else {
                        LogUtil.h("Track_SportNounManager", "jumpToDetailPage() urlDomain is Empty");
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.h("Track_SportNounManager", "jumpToDetailPage() onCallBackFail() -> ", Integer.valueOf(i));
                }
            });
        } else {
            GRSManager.a(BaseApplication.e()).e("helpCustomerUrl", new GrsQueryCallback() { // from class: caj.5
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str2) {
                    LogUtil.a("Track_SportNounManager", "jumpToDetailPage() urlDomain: ", str2);
                    if (!TextUtils.isEmpty(str2)) {
                        caj cajVar = caj.this;
                        cajVar.e(cajVar.d(str2, c));
                    } else {
                        LogUtil.h("Track_SportNounManager", "jumpToDetailPage() urlDomain is Empty");
                    }
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.h("Track_SportNounManager", "jumpToDetailPage() onCallBackFail() -> ", Integer.valueOf(i));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str, cal calVar) {
        String format;
        LogUtil.a("Track_SportNounManager", "getDetailPageUrl()");
        int m = CommonUtil.m(BaseApplication.e(), LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1009));
        LogUtil.a("Track_SportNounManager", "getDetailPageUrl() siteId: ", Integer.valueOf(m));
        if (m == 1) {
            if (d().equals("zh-CN")) {
                format = String.format(Locale.ENGLISH, "/hwtips/topic/health_help_all/%s/%s.html?cid=11080#%s", "zh-CN", calVar.b(), calVar.e());
            } else {
                format = String.format(Locale.ENGLISH, "/hwtips/topic/health_help_all/%s/%s.html?cid=11080#%s", "en-US", calVar.b(), calVar.e());
            }
        } else {
            String str2 = calVar.a() + "";
            if (calVar.a() < 10) {
                str2 = "0" + calVar.a();
            }
            format = String.format(Locale.ENGLISH, "/handbook/mapJumppage/EMUI10.0/C001B001/en-US/index.html?lang=%s&id=%s&funNum=%s", d(), str2, calVar.b());
        }
        String str3 = str + format;
        LogUtil.a("Track_SportNounManager", "getDetailPageUrl() detailPageUrl: ", str3);
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        LogUtil.a("Track_SportNounManager", "jumpToWebViewPage() url: ", str);
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.d(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", str);
        intent.addFlags(268435456);
        jdw.bGh_(intent, BaseApplication.e());
    }

    private static String d() {
        String x = CommonUtil.x();
        String str = TextUtils.isEmpty(x) ? "" : e.get(x.toUpperCase(Locale.ENGLISH));
        LogUtil.a("Track_SportNounManager", "getLanguageTag() languageTags: ", str);
        if (TextUtils.isEmpty(str)) {
            return "en-US";
        }
        if (!str.contains(",")) {
            return str;
        }
        String u = CommonUtil.u();
        LogUtil.a("Track_SportNounManager", "getLanguageTag() currentLanguageTag: ", u);
        if (TextUtils.isEmpty(u)) {
            return "en-US";
        }
        String[] split = str.split(",");
        int i = 0;
        String str2 = split[0];
        int length = split.length;
        while (true) {
            if (i >= length) {
                break;
            }
            String str3 = split[i];
            if (TextUtils.equals(u, str3)) {
                str2 = str3;
                break;
            }
            i++;
        }
        LogUtil.a("Track_SportNounManager", "getLanguageTag() languageTag: ", str2);
        return str2;
    }
}
