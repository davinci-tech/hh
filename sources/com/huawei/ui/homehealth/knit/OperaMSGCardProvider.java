package com.huawei.ui.homehealth.knit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.gson.Gson;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.TextGeneralTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.knit.OperaMSGCardProvider;
import defpackage.bzs;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrz;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class OperaMSGCardProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private Context f9476a;
    private Handler e;
    private LinearLayout f;
    private LinearLayout g;
    private MarketingApi h;
    private MessageCenterApi j;
    private LinearLayout n;
    private SectionBean o;
    private ArrayList<View> d = new ArrayList<>();
    private List<MessageObject> b = new ArrayList(4);
    private boolean c = true;
    private HashMap<String, Object> i = new HashMap<>();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.f9476a = context;
        this.o = sectionBean;
        this.j = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        if (this.c) {
            this.e = new d(this);
            this.c = false;
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        hashMap.clear();
        hashMap.putAll(this.i);
        LogUtil.a("OperaMSGCardProvider", hashMap.toString());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("OperaMSGCardProvider", "resume");
        d();
    }

    private void d() {
        LogUtil.a("OperaMSGCardProvider", "refreshCardData");
        if (Utils.l() || LoginInit.getInstance(this.f9476a).isKidAccount()) {
            this.e.sendEmptyMessage(1);
        } else if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            c();
        } else {
            a();
        }
    }

    private void c() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.knit.OperaMSGCardProvider.1
            @Override // java.lang.Runnable
            public void run() {
                List<MessageObject> messages = OperaMSGCardProvider.this.j.getMessages(21);
                Handler handler = OperaMSGCardProvider.this.e;
                if (handler == null) {
                    return;
                }
                if (koq.c(messages)) {
                    LogUtil.c("OperaMSGCardProvider", "getOperationPositionMsg msgDbObject size== ", Integer.valueOf(messages.size()));
                    Message obtainMessage = handler.obtainMessage(0);
                    obtainMessage.obj = messages;
                    boolean sendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(obtainMessage);
                    LogUtil.a("OperaMSGCardProvider", "queryOperationPositionMsg result = ", Boolean.valueOf(sendMessageAtFrontOfQueue));
                    if (sendMessageAtFrontOfQueue) {
                        return;
                    }
                    handler.sendMessage(obtainMessage);
                    return;
                }
                LogUtil.a("OperaMSGCardProvider", "msgDbObject is null");
                handler.sendEmptyMessage(1);
            }
        });
    }

    private void a() {
        MarketingApi e2 = e();
        this.h = e2;
        if (e2 == null) {
            Handler handler = this.e;
            if (handler != null) {
                handler.sendEmptyMessage(1);
                return;
            }
            return;
        }
        e2.getResourceResultInfo(9001).addOnSuccessListener(new OnSuccessListener() { // from class: onf
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                OperaMSGCardProvider.this.b((Map) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: one
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                OperaMSGCardProvider.this.b(exc);
            }
        });
    }

    public /* synthetic */ void b(Map map) {
        Handler handler = this.e;
        Object[] objArr = new Object[2];
        objArr[0] = "marketing2.0 handler is null = ";
        objArr[1] = Boolean.valueOf(handler == null);
        LogUtil.a("OperaMSGCardProvider", objArr);
        if (handler == null) {
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = this.h.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules == null || !filterMarketingRules.containsKey(9001)) {
            LogUtil.h("OperaMSGCardProvider", "marketing2.0 filterResultInfoMap == null");
            handler.sendEmptyMessage(1);
            return;
        }
        ResourceResultInfo resourceResultInfo = filterMarketingRules.get(9001);
        if (resourceResultInfo == null) {
            LogUtil.h("OperaMSGCardProvider", "marketing2.0 no data");
            handler.sendEmptyMessage(1);
            return;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            LogUtil.h("OperaMSGCardProvider", "resource list is empty");
            handler.sendEmptyMessage(1);
        } else {
            dcQ_(resources, handler);
        }
    }

    public /* synthetic */ void b(Exception exc) {
        LogUtil.b("OperaMSGCardProvider", "Failed to request the marketing2.0 text link data.");
        Handler handler = this.e;
        if (handler != null) {
            handler.sendEmptyMessage(1);
        }
    }

    private void dcQ_(List<ResourceBriefInfo> list, Handler handler) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<ResourceBriefInfo> it = list.iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            ResourceBriefInfo next = it.next();
            if (next == null || next.getContentType() != 3 || next.getContent() == null || TextUtils.isEmpty(next.getContent().getContent())) {
                LogUtil.h("OperaMSGCardProvider", "resource brief info isUnValid");
            } else {
                String content = next.getContent().getContent();
                LogUtil.a("OperaMSGCardProvider", " marketing2.0: ", content);
                TextGeneralTemplate textGeneralTemplate = (TextGeneralTemplate) gson.fromJson(content, TextGeneralTemplate.class);
                if (textGeneralTemplate == null) {
                    LogUtil.a("OperaMSGCardProvider", "resource template parse exception");
                } else {
                    MessageObject messageObject = new MessageObject();
                    messageObject.setDetailUri(textGeneralTemplate.getLinkValue());
                    messageObject.setImgUri(textGeneralTemplate.getPicture());
                    messageObject.setMsgTitle(textGeneralTemplate.getTheme());
                    if (textGeneralTemplate.isThemeVisibility() != null && !textGeneralTemplate.isThemeVisibility().booleanValue()) {
                        i = 8;
                    }
                    messageObject.setFlag(i);
                    messageObject.setMsgContent(textGeneralTemplate.getExtend());
                    messageObject.setCreateTime(next.getEffectiveTime());
                    messageObject.setExpireTime(next.getExpirationTime());
                    messageObject.setPageType(3);
                    messageObject.setMsgId(next.getResourceId());
                    messageObject.setWeight(next.getPriority());
                    messageObject.setMetadata(next.getResourceName());
                    messageObject.setResBriefInfo(next);
                    arrayList.add(messageObject);
                }
            }
        }
        LogUtil.a("OperaMSGCardProvider", "messageObjects size = ", Integer.valueOf(arrayList.size()));
        if (koq.b(arrayList)) {
            LogUtil.a("OperaMSGCardProvider", "messageObjects is empty");
            handler.sendEmptyMessage(1);
            return;
        }
        LogUtil.c("OperaMSGCardProvider", "getOperationPositionMsg messageObjects size== ", Integer.valueOf(arrayList.size()));
        Message obtainMessage = handler.obtainMessage(0);
        obtainMessage.obj = arrayList;
        boolean sendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(obtainMessage);
        LogUtil.a("OperaMSGCardProvider", "queryOperationPositionMsg messageObjects result = ", Boolean.valueOf(sendMessageAtFrontOfQueue));
        if (sendMessageAtFrontOfQueue) {
            return;
        }
        handler.sendMessage(obtainMessage);
    }

    class d extends BaseHandler<OperaMSGCardProvider> {
        d(OperaMSGCardProvider operaMSGCardProvider) {
            super(operaMSGCardProvider);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dcU_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OperaMSGCardProvider operaMSGCardProvider, Message message) {
            OperaMSGCardProvider.this.i.clear();
            int i = message.what;
            if (i == 0) {
                LogUtil.a("OperaMSGCardProvider", "show opera msg");
                if (message.obj instanceof List) {
                    operaMSGCardProvider.b((List<MessageObject>) message.obj);
                } else {
                    operaMSGCardProvider.b();
                }
            } else if (i == 1) {
                LogUtil.a("OperaMSGCardProvider", "hide opera msg");
                operaMSGCardProvider.b();
                OperaMSGCardProvider.this.i.put("VIEW_FLIPPER_VISIBILITY", 8);
            }
            OperaMSGCardProvider.this.o.e(new Object());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<MessageObject> list) {
        if (koq.b(list)) {
            LogUtil.h("OperaMSGCardProvider", "no data");
            return;
        }
        if (!c(list)) {
            LogUtil.h("OperaMSGCardProvider", "message lists need not to update");
            return;
        }
        this.d.clear();
        b();
        this.b = list;
        LogUtil.a("OperaMSGCardProvider", "updateOperaMsg");
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            MessageObject messageObject = list.get(i);
            if (i == 0) {
                View inflate = View.inflate(this.f9476a, R.layout.home_item_layout_opera_msg_item, null);
                if (inflate instanceof LinearLayout) {
                    this.f = (LinearLayout) inflate;
                }
                dcS_(this.f, messageObject);
            }
            if (i == 1) {
                View inflate2 = View.inflate(this.f9476a, R.layout.home_item_layout_opera_msg_item, null);
                if (inflate2 instanceof LinearLayout) {
                    this.g = (LinearLayout) inflate2;
                }
                dcS_(this.g, messageObject);
            }
            if (i == 2) {
                View inflate3 = View.inflate(this.f9476a, R.layout.home_item_layout_opera_msg_item, null);
                if (inflate3 instanceof LinearLayout) {
                    this.n = (LinearLayout) inflate3;
                }
                dcS_(this.n, messageObject);
            } else {
                i++;
            }
        }
        this.i.put("ADD_VIEW", this.d);
        this.i.put("VIEW_FLIPPER_VISIBILITY", 0);
        this.i.put("FLIPPING_INTERVAL", 3000);
        if (this.d.size() > 1) {
            this.i.put("START_FLIPPING", true);
        } else {
            this.i.put("STOP_FLIPPING", true);
        }
    }

    private boolean c(List<MessageObject> list) {
        if (list.size() != this.b.size()) {
            return true;
        }
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getMsgId().equals(this.b.get(i).getMsgId())) {
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.i.put("STOP_FLIPPING", true);
        this.i.put("REMOVE_ALL_VIEWS", true);
        this.f = null;
        this.g = null;
        this.n = null;
    }

    private void dcS_(final View view, final MessageObject messageObject) {
        if (view == null || messageObject == null) {
            return;
        }
        final boolean z = messageObject.getPageType() == 3;
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.opera_msg_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.opera_msg_layout_icon);
        if (z) {
            dcR_(messageObject, imageView, healthTextView, view);
        } else {
            if (LanguageUtil.bc(this.f9476a)) {
                imageView.setBackground(nrz.cKn_(this.f9476a, R.drawable._2131430949_res_0x7f0b0e25));
            }
            healthTextView.setText(messageObject.getMsgTitle());
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: oni
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                OperaMSGCardProvider.this.dcT_(z, messageObject, view, view2);
            }
        });
        this.d.add(view);
        if (z) {
            b(messageObject, 0L, 1);
        } else {
            b(messageObject, AnalyticsValue.HEALTH_HOME_OPERA_POSITION_2010074);
        }
    }

    public /* synthetic */ void dcT_(boolean z, MessageObject messageObject, View view, View view2) {
        this.i.clear();
        if (z) {
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(messageObject.getDetailUri());
            }
            Object tag = view2.getTag(R.id.opera_msg_text);
            b(messageObject, tag instanceof Long ? ((Long) tag).longValue() : 0L, 2);
        } else {
            dcP_(view, messageObject);
        }
        this.o.e(new Object());
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void dcR_(MessageObject messageObject, ImageView imageView, HealthTextView healthTextView, View view) {
        if (d(this.f9476a)) {
            if (!TextUtils.isEmpty(messageObject.getImgUri())) {
                imageView.setVisibility(4);
                try {
                    Glide.with(imageView).load(messageObject.getImgUri()).into((RequestBuilder<Drawable>) new e(imageView));
                } catch (IllegalArgumentException e2) {
                    LogUtil.b("OperaMSGCardProvider", " Exception ", e2.getMessage());
                }
            } else {
                imageView.setVisibility(0);
                if (LanguageUtil.bc(this.f9476a)) {
                    imageView.setBackground(nrz.cKn_(this.f9476a, R.drawable._2131430949_res_0x7f0b0e25));
                }
            }
        }
        healthTextView.setText(messageObject.getMsgTitle());
        healthTextView.setVisibility(messageObject.getFlag());
        view.setTag(R.id.opera_msg_text, Long.valueOf(System.currentTimeMillis()));
    }

    static class e extends DrawableImageViewTarget {
        e(ImageView imageView) {
            super(imageView);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.bumptech.glide.request.target.DrawableImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
        public void setResource(Drawable drawable) {
            if (drawable != null) {
                Context context = ((ImageView) this.view).getContext();
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    if (activity.isFinishing() || activity.isDestroyed()) {
                        return;
                    }
                    if (LanguageUtil.bc(context)) {
                        drawable = nrz.cKm_(context, drawable);
                    }
                    ((ImageView) this.view).setImageDrawable(drawable);
                    ((ImageView) this.view).setBackground(null);
                    ((ImageView) this.view).setBackgroundTintMode(PorterDuff.Mode.SRC);
                    ((ImageView) this.view).setBackgroundTintList(null);
                    ((ImageView) this.view).setVisibility(0);
                }
            }
        }
    }

    private void b(MessageObject messageObject, long j, int i) {
        if (messageObject == null) {
            LogUtil.h("OperaMSGCardProvider", "marketingBiEvent messageObject is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 9001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("resourceName", messageObject.getMetadata());
        hashMap.put("itemCardName", messageObject.getMsgTitle());
        hashMap.put("itemId", "");
        hashMap.put("pullOrder", 1);
        hashMap.put("event", Integer.valueOf(i));
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        }
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        ixx.d().d(this.f9476a, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
        if (i == 2) {
            this.h = e();
            ResourceBriefInfo resBriefInfo = messageObject.getResBriefInfo();
            MarketingApi marketingApi = this.h;
            if (marketingApi == null || resBriefInfo == null) {
                return;
            }
            marketingApi.recordResourcePresent(9001, 1, resBriefInfo);
        }
    }

    private MarketingApi e() {
        if (this.h == null) {
            this.h = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        return this.h;
    }

    private void dcP_(View view, final MessageObject messageObject) {
        LogUtil.a("OperaMSGCardProvider", "operaMsg click");
        b(messageObject, AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075);
        if (TextUtils.isEmpty(messageObject.getDetailUri())) {
            if (messageObject.getMsgType() == 2) {
                b(messageObject);
                this.d.remove(view);
                if (this.d.size() > 1) {
                    this.i.put("START_FLIPPING", true);
                    return;
                } else {
                    this.i.put("STOP_FLIPPING", true);
                    return;
                }
            }
            return;
        }
        if (com.huawei.operation.utils.Utils.isNotSupportBrowseUrl(messageObject.getDetailUri())) {
            LoginInit.getInstance(this.f9476a).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.knit.OperaMSGCardProvider.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        OperaMSGCardProvider.this.e(messageObject);
                    }
                }
            }, AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075.value());
        } else {
            e(messageObject);
        }
    }

    private void b(final MessageObject messageObject) {
        if (messageObject == null || messageObject.getReadFlag() == 1) {
            LogUtil.h("OperaMSGCardProvider", "message is empty or status is read");
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.knit.OperaMSGCardProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    OperaMSGCardProvider.this.j.setMessageRead(messageObject.getMsgId());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MessageObject messageObject) {
        if (messageObject.getDetailUri().startsWith("huaweischeme")) {
            b(messageObject);
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(messageObject.getDetailUri()));
            intent.setPackage(this.f9476a.getPackageName());
            jdw.bGh_(intent, this.f9476a);
            return;
        }
        c(messageObject);
    }

    private void c(MessageObject messageObject) {
        if (messageObject == null) {
            LogUtil.h("OperaMSGCardProvider", "gotoMassageDetailPage messageObject is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("msgId", messageObject.getMsgId());
        bundle.putString(CommonUtil.DETAIL_URI, messageObject.getDetailUri());
        bundle.putString("EXTRA_BI_NAME", messageObject.getMsgTitle());
        bundle.putString("EXTRA_BI_SOURCE", "HomePageFlipper");
        bundle.putString("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        bundle.putString(MessageConstant.MESSAGE_TITLE, messageObject.getMsgTitle());
        AppRouter.b("/PluginMessageCenter/DispatchSkipEventActivity").zF_(bundle).c(this.f9476a);
    }

    private void b(MessageObject messageObject, AnalyticsValue analyticsValue) {
        if (messageObject == null || analyticsValue == null) {
            LogUtil.h("OperaMSGCardProvider", "processBiEvent messageObject is null");
            return;
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("type", 50001);
        hashMap.put("title", messageObject.getMsgTitle());
        if (AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075.equals(analyticsValue)) {
            hashMap.put("click", 1);
        }
        ixx.d().d(BaseApplication.getContext(), analyticsValue.value(), hashMap, 0);
    }

    private boolean d(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                return true;
            }
        }
        return false;
    }
}
