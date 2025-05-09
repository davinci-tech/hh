package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.featuremarketing.db.MarketingEventDbMgr;
import com.huawei.health.marketing.datatype.MarketingEventInfo;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.FloatingBoxTemplate;
import com.huawei.health.marketing.datatype.templates.PopUpCombinationTemplate;
import com.huawei.health.marketing.datatype.templates.PopUpListItemsTemplate;
import com.huawei.health.marketing.datatype.templates.PopUpMemberTemplate;
import com.huawei.health.marketing.datatype.templates.PopUpTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.HealthFloatBar;
import com.huawei.health.marketing.views.HealthFloatingShapedBox;
import com.huawei.health.marketing.views.TopBannerLayout;
import com.huawei.health.marketing.views.dialog.EquityRenewalDialog;
import com.huawei.health.marketing.views.dialog.MarketingDialogFragment;
import com.huawei.health.marketing.views.dialog.MemberRenewalDialog;
import com.huawei.health.marketing.views.dialog.ReceiveCouponDialog;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.ui.commonui.dialog.RotateDialog;
import com.huawei.ui.commonui.floatview.HealthFloatingView;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dml {

    /* renamed from: a, reason: collision with root package name */
    private final MarketingOption f11713a;
    private final Handler e = new Handler(Looper.getMainLooper());

    public dml(MarketingOption marketingOption) {
        this.f11713a = marketingOption;
    }

    public static List<View> d(Context context, Map<Integer, ResourceResultInfo> map, FragmentManager fragmentManager) {
        if (map == null) {
            LogUtil.c("MarketingResourceSetting", "ResultInfoMap is null.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, ResourceResultInfo>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            ResourceResultInfo resourceResultInfo = map.get(Integer.valueOf(intValue));
            if (resourceResultInfo == null) {
                LogUtil.c("MarketingResourceSetting", "ResourceResultInfo is null. positionId: ", Integer.valueOf(intValue));
            } else {
                List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
                if (resourceResultInfo.getTotalNum() == 0 || koq.b(resources)) {
                    LogUtil.c("MarketingResourceSetting", "resourceBriefInfoList is null. positionId: ", Integer.valueOf(intValue));
                } else {
                    Iterator<ResourceBriefInfo> it2 = resources.iterator();
                    while (it2.hasNext()) {
                        List<View> e = dlw.e(context, intValue, it2.next(), fragmentManager);
                        if (koq.c(e)) {
                            arrayList.addAll(e);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    void c(int i, Map<Integer, ResourceResultInfo> map, Map<String, ?> map2) {
        if (map == null) {
            LogUtil.c("MarketingResourceSetting", "resultInfoMap is null. no valid resource.");
            return;
        }
        for (Map.Entry<Integer, ResourceResultInfo> entry : map.entrySet()) {
            int intValue = entry.getKey().intValue();
            ResourceResultInfo value = entry.getValue();
            if (value == null) {
                LogUtil.c("MarketingResourceSetting", "ResourceResultInfo is null. positionId: ", Integer.valueOf(intValue));
            } else if (value.getTotalNum() == 0) {
                LogUtil.c("MarketingResourceSetting", "ResourceResultInfo is totalNum is 0. positionId: ", Integer.valueOf(intValue));
            } else {
                List<ResourceBriefInfo> resources = value.getResources();
                if (intValue == 1003 && a(intValue, resources)) {
                    LogUtil.c("MarketingResourceSetting", "positionId is banner");
                    return;
                } else {
                    LogUtil.c("MarketingResourceSetting", "setResource positionId: ", Integer.valueOf(intValue));
                    d(resources, intValue, i, map2);
                }
            }
        }
    }

    private boolean a(int i, List<ResourceBriefInfo> list) {
        Context context = this.f11713a.getContext();
        Map<Integer, LinearLayout> layoutMap = this.f11713a.getLayoutMap();
        if (context == null || layoutMap == null) {
            LogUtil.a("MarketingResourceSetting", "caller is dead, processMarketingBanner abort");
            return false;
        }
        LinearLayout linearLayout = layoutMap.get(Integer.valueOf(i));
        if (linearLayout == null || koq.b(list)) {
            LogUtil.c("MarketingResourceSetting", "layout or resourceBriefInfoList is null.");
            return false;
        }
        linearLayout.removeAllViews();
        linearLayout.addView(e(context, list));
        return true;
    }

    private TopBannerLayout e(Context context, List<ResourceBriefInfo> list) {
        if (context == null) {
            return null;
        }
        TopBannerLayout topBannerLayout = new TopBannerLayout(context);
        topBannerLayout.c(list);
        return topBannerLayout;
    }

    private void d(List<ResourceBriefInfo> list, int i, int i2, Map<String, ?> map) {
        Iterator<ResourceBriefInfo> it = list.iterator();
        while (it.hasNext()) {
            this.e.postDelayed(new d(it.next(), i, i2), c(r0, i2, map));
        }
    }

    private int c(ResourceBriefInfo resourceBriefInfo, int i, Map<String, ?> map) {
        int e = dnb.e(i, map, resourceBriefInfo.getMarketingRule().getTriggerEvents());
        if (e < 0) {
            return 0;
        }
        return e * 1000;
    }

    private void c(Context context, final int i, final ResourceBriefInfo resourceBriefInfo) {
        eli eliVar = new eli(context, (PopUpListItemsTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), PopUpListItemsTemplate.class));
        eliVar.a(new RotateDialog.OnVisibleListenter() { // from class: dmq
            @Override // com.huawei.ui.commonui.dialog.RotateDialog.OnVisibleListenter
            public final void onVisible(boolean z, View view) {
                dml.WL_(i, resourceBriefInfo, z, view);
            }
        });
        eliVar.d();
    }

    static /* synthetic */ void WL_(int i, ResourceBriefInfo resourceBriefInfo, boolean z, View view) {
        if (z) {
            d(i, resourceBriefInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i, ResourceBriefInfo resourceBriefInfo, int i2) {
        if (resourceBriefInfo == null) {
            return false;
        }
        Context context = this.f11713a.getContext();
        if (context == null) {
            LogUtil.a("MarketingResourceSetting", "caller is dead, no need to instantiate view...");
            return false;
        }
        int contentType = resourceBriefInfo.getContentType();
        if (b(contentType)) {
            LogUtil.c("MarketingResourceSetting", "PopUp switch is off, stop setting resource. positionId: ", Integer.valueOf(i));
            return false;
        }
        if (contentType == 1) {
            LogUtil.c("MarketingResourceSetting", "TEMPLATE_POP_UP,set dialog.");
            j(context, i, resourceBriefInfo);
            return true;
        }
        if (contentType == 2) {
            LogUtil.c("MarketingResourceSetting", "TEMPLATE_FLOATING_BOX,set floatingBox.");
            c(context, i, resourceBriefInfo, i2);
            return false;
        }
        if (contentType == 45 || contentType == 50) {
            LogUtil.c("MarketingResourceSetting", "TEMPLATE_POP_UP_MULTI,set dialog.");
            a(context, i, resourceBriefInfo);
            return true;
        }
        if (contentType == 81) {
            LogUtil.c("MarketingResourceSetting", "TEMPLATE_BOX_TEMPLATE,set floating shaped box.");
            d(context, i, resourceBriefInfo, i2);
            return false;
        }
        if (contentType == 89) {
            d(context, i, resourceBriefInfo);
            return true;
        }
        if (contentType == 109) {
            LogUtil.c("MarketingResourceSetting", "TEMPLATE_LIST_ITEMS_POP_UP,set dialog.");
            c(context, i, resourceBriefInfo);
            return true;
        }
        switch (contentType) {
            case 67:
                LogUtil.c("MarketingResourceSetting", "TEMPLATE_PROMOTION_BAR,set floatingBar.");
                b(context, i, resourceBriefInfo, i2);
                break;
            case 68:
            case 69:
                e(context, i, resourceBriefInfo);
                break;
            case 70:
                b(context, i, resourceBriefInfo);
                break;
            default:
                LogUtil.c("MarketingResourceSetting", "other contentType = ", Integer.valueOf(contentType));
                Map<Integer, LinearLayout> layoutMap = this.f11713a.getLayoutMap();
                if (layoutMap == null) {
                    LogUtil.a("MarketingResourceSetting", "layoutMap is null, abort");
                    break;
                } else {
                    b(layoutMap, i, dlw.e(context, i, resourceBriefInfo, null));
                    break;
                }
        }
        return false;
    }

    private boolean b(Map<Integer, LinearLayout> map, int i, List<View> list) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        if (map.get(Integer.valueOf(i)) == null) {
            LogUtil.a("MarketingResourceSetting", "layout is null. return.");
            return false;
        }
        LinearLayout linearLayout = map.get(Integer.valueOf(i));
        if (linearLayout == null) {
            LogUtil.a("MarketingResourceSetting", "viewGroup is null. return.");
            return false;
        }
        if (koq.b(list)) {
            LogUtil.a("MarketingResourceSetting", "views is empty. return.");
            return false;
        }
        for (View view : list) {
            if (view != null) {
                linearLayout.addView(view);
            }
        }
        return true;
    }

    private boolean b(int i) {
        if (!eil.b(i)) {
            return false;
        }
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "health_msg_switch_promotion");
        LogUtil.c("MarketingResourceSetting", "PopUp promotionRecommend = " + b);
        return "0".equals(b);
    }

    private void j(Context context, final int i, final ResourceBriefInfo resourceBriefInfo) {
        PopUpTemplate popUpTemplate = (PopUpTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), PopUpTemplate.class);
        final String linkValue = popUpTemplate.getLinkValue();
        final RotateDialog rotateDialog = new RotateDialog(context);
        rotateDialog.e(i);
        rotateDialog.e(resourceBriefInfo.getResourceId());
        rotateDialog.g(resourceBriefInfo.getResourceName());
        rotateDialog.b("");
        rotateDialog.d(resourceBriefInfo.getSmartRecommend());
        final long currentTimeMillis = System.currentTimeMillis();
        rotateDialog.czO_(new View.OnClickListener() { // from class: dmr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dml.this.WN_(linkValue, resourceBriefInfo, i, currentTimeMillis, rotateDialog, view);
            }
        });
        rotateDialog.a(new RotateDialog.OnVisibleListenter() { // from class: dmu
            @Override // com.huawei.ui.commonui.dialog.RotateDialog.OnVisibleListenter
            public final void onVisible(boolean z, View view) {
                dml.WM_(i, resourceBriefInfo, z, view);
            }
        });
        rotateDialog.d(popUpTemplate.getPicture());
        LogUtil.c("MarketingResourceSetting", "dialog.setBackground:", popUpTemplate.getPicture());
    }

    /* synthetic */ void WN_(String str, ResourceBriefInfo resourceBriefInfo, int i, long j, RotateDialog rotateDialog, View view) {
        if (!TextUtils.isEmpty(str)) {
            e(c(eil.c(str, resourceBriefInfo.getCategory()), i, resourceBriefInfo));
            b(i, resourceBriefInfo, j);
        }
        rotateDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void WM_(int i, ResourceBriefInfo resourceBriefInfo, boolean z, View view) {
        if (z) {
            d(i, resourceBriefInfo);
        }
    }

    private void a(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        Activity wa_ = context instanceof Activity ? (Activity) context : BaseApplication.wa_();
        if (wa_ instanceof FragmentActivity) {
            FragmentManager supportFragmentManager = ((FragmentActivity) wa_).getSupportFragmentManager();
            Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag("setPopUpMultiTemplate");
            if (findFragmentByTag instanceof MarketingDialogFragment) {
                supportFragmentManager.beginTransaction().remove(findFragmentByTag).commitNowAllowingStateLoss();
            }
            MarketingDialogFragment marketingDialogFragment = new MarketingDialogFragment();
            marketingDialogFragment.b(i, resourceBriefInfo);
            marketingDialogFragment.showNow(supportFragmentManager, "setPopUpMultiTemplate");
        }
    }

    private void e(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null || resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent())) {
            return;
        }
        try {
            new MemberRenewalDialog(context).a(i, resourceBriefInfo, (PopUpMemberTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), PopUpMemberTemplate.class));
        } catch (JsonSyntaxException unused) {
            LogUtil.c("MarketingResourceSetting", "PopUpMemeberTemplate JsonSyntaxException");
        }
    }

    private void d(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null || resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent())) {
            return;
        }
        try {
            new EquityRenewalDialog(context).b(i, resourceBriefInfo, (PopUpMemberTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), PopUpMemberTemplate.class));
        } catch (JsonSyntaxException unused) {
            LogUtil.c("MarketingResourceSetting", "PopUpMemeberTemplate JsonSyntaxException");
        }
    }

    private void b(Context context, int i, ResourceBriefInfo resourceBriefInfo) {
        if (context == null || resourceBriefInfo == null || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent())) {
            LogUtil.a("MarketingResourceSetting", "setPopUpCouponTemplate return");
            return;
        }
        LogUtil.c("MarketingResourceSetting", "briefInfo.getContent().getContent():", resourceBriefInfo.getContent().getContent());
        String content = resourceBriefInfo.getContent().getContent();
        LogUtil.c("MarketingResourceSetting", "combinationTemplateStr:", content);
        try {
            PopUpCombinationTemplate popUpCombinationTemplate = (PopUpCombinationTemplate) new Gson().fromJson(content, PopUpCombinationTemplate.class);
            LogUtil.c("MarketingResourceSetting", "combinationTemplate:", popUpCombinationTemplate.toString());
            new ReceiveCouponDialog(context).b(i, resourceBriefInfo, popUpCombinationTemplate);
        } catch (JsonSyntaxException unused) {
            LogUtil.c("MarketingResourceSetting", "PopUpMemeberTemplate JsonSyntaxException");
        }
    }

    private void c(final Context context, final int i, final ResourceBriefInfo resourceBriefInfo, final int i2) {
        final FloatingBoxTemplate floatingBoxTemplate = (FloatingBoxTemplate) new Gson().fromJson(resourceBriefInfo.getContent().getContent(), FloatingBoxTemplate.class);
        if (efb.d(floatingBoxTemplate)) {
            return;
        }
        final HealthFloatingView e = e(context, i, resourceBriefInfo, floatingBoxTemplate);
        final long currentTimeMillis = System.currentTimeMillis();
        e.setlistener(new View.OnClickListener() { // from class: dml.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("MarketingResourceSetting", "float view image is clicked");
                String linkValue = floatingBoxTemplate.getLinkValue();
                if (!TextUtils.isEmpty(linkValue)) {
                    OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class);
                    final String c = dml.this.c(eil.c(linkValue, resourceBriefInfo.getCategory()), i, resourceBriefInfo);
                    if (!LoginInit.getInstance(context).isBrowseMode() || !operationUtilsApi.isNotSupportBrowseUrl(c)) {
                        dml.this.e(c);
                        dml.this.b(i, resourceBriefInfo, currentTimeMillis);
                    } else {
                        LogUtil.c("MarketingResourceSetting", "floating view browse login");
                        LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: dml.1.1
                            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                            /* renamed from: onResponse */
                            public void d(int i3, Object obj) {
                                if (i3 == 0) {
                                    dml.this.e(c);
                                    dml.this.b(i, resourceBriefInfo, currentTimeMillis);
                                }
                            }
                        }, "");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e.setCloseBtnlistener(new View.OnClickListener() { // from class: dml.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("MarketingResourceSetting", "float view close button is clicked");
                MarketingBiUtils.c(i, resourceBriefInfo, currentTimeMillis);
                eil.a(i, i2, resourceBriefInfo);
                e.f();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e.a();
        d(context, i);
        d(i, resourceBriefInfo);
    }

    private void d(Context context, int i) {
        Integer num = ehu.c.get(Integer.valueOf(i));
        if (num == null || !(context instanceof Activity)) {
            return;
        }
        View findViewById = ((Activity) context).findViewById(num.intValue());
        if (findViewById instanceof HealthFloatingView) {
            LogUtil.c("MarketingResourceSetting", "find excluded floating view, positionId: ", Integer.valueOf(i), " excluded positionId: ", num);
            ((HealthFloatingView) findViewById).c();
        }
    }

    private HealthFloatingView e(Context context, int i, ResourceBriefInfo resourceBriefInfo, FloatingBoxTemplate floatingBoxTemplate) {
        LogUtil.c("MarketingResourceSetting", "floatingBoxTemplate: ", floatingBoxTemplate);
        HealthFloatingView healthFloatingView = new HealthFloatingView(context);
        healthFloatingView.setIconImage(floatingBoxTemplate.getPicture());
        healthFloatingView.setResourceId(resourceBriefInfo.getResourceId());
        healthFloatingView.setPositionId(i);
        healthFloatingView.setResourceName(resourceBriefInfo.getResourceName());
        healthFloatingView.setId(i);
        healthFloatingView.setAlgId("");
        healthFloatingView.setSmartRecommend(resourceBriefInfo.getSmartRecommend());
        return healthFloatingView;
    }

    private void b(Context context, int i, ResourceBriefInfo resourceBriefInfo, int i2) {
        HealthFloatBar healthFloatBar = new HealthFloatBar(context);
        healthFloatBar.setTypeId(i2);
        healthFloatBar.a(resourceBriefInfo, i);
        healthFloatBar.a();
    }

    private void d(Context context, int i, ResourceBriefInfo resourceBriefInfo, int i2) {
        HealthFloatingShapedBox healthFloatingShapedBox = new HealthFloatingShapedBox(context);
        healthFloatingShapedBox.setTypeId(i2);
        healthFloatingShapedBox.a(resourceBriefInfo, i);
        healthFloatingShapedBox.a();
    }

    public static void c(final int i, final int i2, final ResourceBriefInfo resourceBriefInfo) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dmt
            @Override // java.lang.Runnable
            public final void run() {
                LogUtil.c("MarketingResourceSetting", "saveMarketingEventInfo Result", Long.valueOf(MarketingEventDbMgr.b(BaseApplication.e()).d(new MarketingEventInfo.Builder().setHuId(LoginInit.getInstance(BaseApplication.e()).getHuidOrDefault()).setPositionId(i).setTypeId(i2).setResourceId(resourceBriefInfo.getResourceId()).setTriggerTime(Calendar.getInstance().getTimeInMillis()).setValue("done").setReservedField(null).build())));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        ((MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)).router(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, ResourceBriefInfo resourceBriefInfo, long j) {
        if (resourceBriefInfo == null) {
            return;
        }
        LogUtil.c("MarketingResourceSetting", "MarketingCyclesMgr set event Result", Long.valueOf(j));
        HashMap hashMap = new HashMap();
        hashMap.put("event", 2);
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public static void d(int i, ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", 1);
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("pullOrder", 1);
        hashMap.put("algId", "");
        hashMap.put("smartRecommend", Boolean.valueOf(resourceBriefInfo.getSmartRecommend()));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str, int i, ResourceBriefInfo resourceBriefInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        return sb.toString() + i + "&resourceName=" + resourceBriefInfo.getResourceName() + "&resourceId=" + resourceBriefInfo.getResourceId() + "&pullOrder=1";
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final int f11716a;
        private final ResourceBriefInfo d;
        private final int e;

        d(ResourceBriefInfo resourceBriefInfo, int i, int i2) {
            this.f11716a = i;
            this.e = i2;
            this.d = resourceBriefInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (dml.this.e(this.f11716a, this.d, this.e)) {
                dml.c(this.f11716a, this.e, this.d);
            } else {
                LogUtil.c("MarketingResourceSetting", "Setting Resource failed.");
            }
        }
    }
}
