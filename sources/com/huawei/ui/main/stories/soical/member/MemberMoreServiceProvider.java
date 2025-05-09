package com.huawei.ui.main.stories.soical.member;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.dqj;
import defpackage.gpn;
import defpackage.njn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class MemberMoreServiceProvider extends BaseKnitDataProvider {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        ThreadPoolManager.d().execute(new d(sectionBean));
    }

    static class d implements Runnable {
        private WeakReference<SectionBean> c;

        d(SectionBean sectionBean) {
            this.c = new WeakReference<>(sectionBean);
        }

        @Override // java.lang.Runnable
        public void run() {
            VipApi vipApi = (VipApi) Services.c("vip", VipApi.class);
            if (vipApi == null) {
                LogUtil.h("MemberMoreServiceProvider", "refreshVipStateData VipApi is null");
            } else {
                vipApi.getVipTransferBenefits(new VipCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.5
                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onSuccess(Object obj) {
                        LogUtil.a("MemberMoreServiceProvider", "getVipTransferBenefits onSuccess");
                        if (obj instanceof List) {
                            d.this.b(BaseApplication.getContext(), ((List) obj).size() > 0);
                        } else {
                            d.this.b(BaseApplication.getContext(), false);
                        }
                    }

                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onFailure(int i, String str) {
                        LogUtil.a("MemberMoreServiceProvider", "getVipTransferBenefits onFailure");
                        d.this.b(BaseApplication.getContext(), false);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(final Context context, final boolean z) {
            UserMemberInfo a2 = gpn.a();
            final boolean z2 = a2 == null || a2.getNowTime() <= a2.getExpireTime();
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.4
                @Override // java.lang.Runnable
                public void run() {
                    SectionBean sectionBean = (SectionBean) d.this.c.get();
                    if (sectionBean == null) {
                        LogUtil.h("MemberMoreServiceProvider", "SectionBean is null when setData. Return.");
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    if (sectionBean.j() == 411) {
                        d.this.b(context, hashMap, z, z2);
                    } else if (sectionBean.j() == 412) {
                        d.this.e(context, hashMap);
                    } else {
                        LogUtil.a("MemberMoreServiceProvider", "invalid page id return!");
                        return;
                    }
                    sectionBean.e(hashMap);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(final Context context, Map<String, Object> map) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(context.getResources().getString(R.string._2130843618_res_0x7f0217e2));
            arrayList.add(context.getResources().getString(R.string._2130845708_res_0x7f02200c));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new AnonymousClass1(context));
            arrayList2.add(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dqj.c();
                    if (!LoginInit.getInstance(context).isBrowseMode()) {
                        d.this.a("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=MembersExchange&urlType=4");
                    } else {
                        LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.2.1
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i, Object obj) {
                                LogUtil.a("MemberMoreServiceProvider", "no login go login");
                            }
                        }, "");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            map.put("LEFT_ICON_TEXT", arrayList);
            map.put("CLICK_EVENT_LISTENER", arrayList2);
            map.put("ITEM_TITLE", context.getResources().getString(R.string._2130845699_res_0x7f022003));
            map.put("RIGHT_ICON_IMAGE", Integer.valueOf(LanguageUtil.bc(context) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202));
            map.put(CommonConstant.RETKEY.STATUS, 1);
        }

        /* renamed from: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider$d$1, reason: invalid class name */
        class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ Context d;

            AnonymousClass1(Context context) {
                this.d = context;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GRSManager.a(this.d).e("helpCustomerUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.1.3
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(final String str) {
                        if (TextUtils.isEmpty(str)) {
                            LogUtil.h("MemberMoreServiceProvider", "obtainTipsUrlDomain urlDomain is empty");
                        } else {
                            final boolean z = njn.e(AnonymousClass1.this.d) || d.this.b(AnonymousClass1.this.d);
                            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.1.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    dqj.t();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", MemberMoreServiceProvider.d(str, "#/help?cid=11069"));
                                    bundle.putInt("title", z ? R.string._2130843618_res_0x7f0217e2 : R.string._2130842101_res_0x7f0211f5);
                                    AppRouter.b("/PluginOperation/WebViewActivity").zF_(bundle).c(AnonymousClass1.this.d);
                                }
                            });
                        }
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i) {
                        LogUtil.h("MemberMoreServiceProvider", "obtainTipsUrlDomain onCallBackFail");
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(final Context context, Map<String, Object> map, boolean z, boolean z2) {
            ArrayList arrayList = new ArrayList();
            if (z2) {
                arrayList.add(context.getResources().getString(R.string._2130845700_res_0x7f022004));
            }
            arrayList.add(context.getResources().getString(R.string._2130843618_res_0x7f0217e2));
            arrayList.add(context.getResources().getString(R.string._2130845708_res_0x7f02200c));
            if (z) {
                arrayList.add(context.getResources().getString(R.string._2130846168_res_0x7f0221d8));
            }
            ArrayList arrayList2 = new ArrayList();
            if (z2) {
                arrayList2.add(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dqj.g();
                        if (!LoginInit.getInstance(context).isBrowseMode()) {
                            d.this.a("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=CustomRenewal&urlType=4");
                        } else {
                            LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.3.3
                                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                                /* renamed from: onResponse */
                                public void d(int i, Object obj) {
                                    LogUtil.a("MemberMoreServiceProvider", "no login go login");
                                }
                            }, "");
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            arrayList2.add(new AnonymousClass6(context));
            arrayList2.add(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!LoginInit.getInstance(context).isBrowseMode()) {
                        d.this.a("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&pName=com.huawei.health&path=MembersExchange&urlType=4");
                    } else {
                        LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.9.1
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i, Object obj) {
                                LogUtil.a("MemberMoreServiceProvider", "no login go login");
                            }
                        }, "");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (z) {
                arrayList2.add(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        d.this.a("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&h5pro=true&urlType=4&pName=com.huawei.health&path=VipTransfer");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            map.put("LEFT_ICON_TEXT", arrayList);
            map.put("CLICK_EVENT_LISTENER", arrayList2);
            map.put("ITEM_TITLE", context.getResources().getString(R.string._2130845699_res_0x7f022003));
            map.put("RIGHT_ICON_IMAGE", Integer.valueOf(LanguageUtil.bc(context) ? R.drawable._2131427841_res_0x7f0b0201 : R.drawable._2131427842_res_0x7f0b0202));
            map.put(CommonConstant.RETKEY.STATUS, 2);
        }

        /* renamed from: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider$d$6, reason: invalid class name */
        class AnonymousClass6 implements View.OnClickListener {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ Context f10485a;

            AnonymousClass6(Context context) {
                this.f10485a = context;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GRSManager.a(this.f10485a).e("helpCustomerUrl", new GrsQueryCallback() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.6.4
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(final String str) {
                        if (TextUtils.isEmpty(str)) {
                            LogUtil.h("MemberMoreServiceProvider", "obtainTipsUrlDomain urlDomain is empty");
                        } else {
                            final boolean z = njn.e(AnonymousClass6.this.f10485a) || d.this.b(AnonymousClass6.this.f10485a);
                            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.soical.member.MemberMoreServiceProvider.d.6.4.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    dqj.l();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("url", MemberMoreServiceProvider.d(str, "#/help?cid=11069"));
                                    bundle.putInt("title", z ? R.string._2130843618_res_0x7f0217e2 : R.string._2130842101_res_0x7f0211f5);
                                    AppRouter.b("/PluginOperation/WebViewActivity").zF_(bundle).c(AnonymousClass6.this.f10485a);
                                }
                            });
                        }
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i) {
                        LogUtil.h("MemberMoreServiceProvider", "obtainTipsUrlDomain onCallBackFail");
                    }
                });
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b(Context context) {
            if (context != null) {
                return LanguageUtil.ae(context) && "JP".equals(LoginInit.getInstance(context).getAccountInfo(1010));
            }
            LogUtil.h("MemberMoreServiceProvider", "isShowJapanCustomer() context is null");
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(str);
            }
        }
    }

    public static String d(String str, String str2) {
        String format;
        health.compact.a.util.LogUtil.d("MemberMoreServiceProvider", "getHelpCustomerUrl");
        if (CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)) == 1) {
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[2];
            objArr[0] = d();
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            format = String.format(locale, "/hwtips/help/health_help_all/%s/index.html%s", objArr);
        } else {
            format = String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=health_help_all", e());
        }
        String str3 = str + format;
        health.compact.a.util.LogUtil.b("MemberMoreServiceProvider", "getHelpCustomerUrl: url -> ", str3);
        return str3;
    }

    private static String d() {
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        String str = ("ZH".equalsIgnoreCase(language) || "BO".equalsIgnoreCase(language) || "UG".equalsIgnoreCase(language)) ? "zh-CN" : "en-US";
        health.compact.a.util.LogUtil.b("MemberMoreServiceProvider", "getDomesticLanguageTag: languageTag -> ", str);
        return str;
    }

    private static String e() {
        Configuration configuration = BaseApplication.getContext().getResources().getConfiguration();
        String str = configuration.locale.getLanguage() + Constants.LINK + configuration.locale.getCountry();
        health.compact.a.util.LogUtil.b("MemberMoreServiceProvider", "getLanguageTag: currentLanguageTag -> ", str);
        return str;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        hashMap.putAll((Map) obj);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
    }
}
