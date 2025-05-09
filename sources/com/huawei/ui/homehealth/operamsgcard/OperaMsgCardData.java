package com.huawei.ui.homehealth.operamsgcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.TextGeneralTemplate;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData;
import defpackage.bzs;
import defpackage.eie;
import defpackage.eil;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrv;
import defpackage.nrz;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes6.dex */
public class OperaMsgCardData extends AbstractBaseCardData {
    private Context c;
    private Handler d;
    private List<ResourceBriefInfo> e;
    private LinearLayout f;
    private MessageCenterApi g;
    private MarketingApi h;
    private LinearLayout k;
    private OperaMsgCardViewHolder l;
    private LinearLayout m;
    private Timer o;
    private List<MessageObject> i = new ArrayList(4);

    /* renamed from: a, reason: collision with root package name */
    private int f9481a = 0;
    private boolean j = true;
    private boolean b = false;

    public OperaMsgCardData(Context context) {
        CommonUtil.u("OperaMsgCardData-OperaMSGCardData constructor enter");
        this.c = context;
        this.d = new d(this);
        CommonUtil.u("OperaMsgCardData-OperaMSGCardData constructor end");
        this.g = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        this.o = new Timer();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        refreshCardData();
        ReleaseLogUtil.e("OperaMsgCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("R_OperaMsgCardData", "onDestroy ");
        Handler handler = this.d;
        if (handler != null) {
            handler.removeMessages(0);
            this.d.removeMessages(1);
        }
        Timer timer = this.o;
        if (timer != null) {
            timer.cancel();
            this.o = null;
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        OperaMsgCardViewHolder operaMsgCardViewHolder = new OperaMsgCardViewHolder(layoutInflater.inflate(R.layout.home_item_layout_opera_msg, viewGroup, false), this.c, false);
        this.l = operaMsgCardViewHolder;
        return operaMsgCardViewHolder;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        ReleaseLogUtil.e("R_OperaMsgCardData", "refreshCardData ");
        if (Utils.l() || LoginInit.getInstance(this.c).isKidAccount()) {
            ReleaseLogUtil.d("R_OperaMsgCardData", " hide opera msg");
            this.d.sendEmptyMessage(1);
        } else if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            c();
        } else {
            e();
        }
    }

    private void e() {
        ReleaseLogUtil.e("R_OperaMsgCardData", "dealLocalMessage ");
        MarketingApi a2 = a();
        this.h = a2;
        if (a2 == null) {
            Handler handler = this.d;
            if (handler != null) {
                handler.sendEmptyMessage(1);
                return;
            }
            return;
        }
        if (this.j) {
            List<ResourceBriefInfo> b2 = b();
            this.e = b2;
            if (!koq.b(b2) && this.d != null) {
                ReleaseLogUtil.e("R_OperaMsgCardData", "return cached resource");
                dcZ_(this.e, this.d);
                this.b = true;
            }
            this.j = false;
        }
        if (!this.b) {
            h();
        } else if (this.o != null) {
            ReleaseLogUtil.e("R_OperaMsgCardData", "mTimer schedule");
            this.o.schedule(new TimerTask() { // from class: com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData.3
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ReleaseLogUtil.e("R_OperaMsgCardData", "mTimer schedule");
                    OperaMsgCardData.this.h();
                }
            }, 2000L);
        }
    }

    static class c implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {
        private MarketingApi b;
        private WeakReference<OperaMsgCardData> d;
        private Handler e;

        c(OperaMsgCardData operaMsgCardData, MarketingApi marketingApi, Handler handler) {
            this.d = new WeakReference<>(operaMsgCardData);
            this.b = marketingApi;
            this.e = handler;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            OperaMsgCardData operaMsgCardData = this.d.get();
            if (operaMsgCardData == null) {
                ReleaseLogUtil.d("R_OperaMsgCardData", "cardData is null");
                return;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "marketing2.0 handler is null = ";
            objArr[1] = Boolean.valueOf(this.e == null);
            ReleaseLogUtil.e("R_OperaMsgCardData", objArr);
            if (this.e == null) {
                return;
            }
            Map<Integer, ResourceResultInfo> filterMarketingRules = this.b.filterMarketingRules(map);
            if (filterMarketingRules == null || !filterMarketingRules.containsKey(9001)) {
                ReleaseLogUtil.d("R_OperaMsgCardData", "marketing2.0 filterResultInfoMap == null");
                operaMsgCardData.ddc_(this.e);
                return;
            }
            ResourceResultInfo resourceResultInfo = filterMarketingRules.get(9001);
            if (resourceResultInfo == null) {
                ReleaseLogUtil.d("R_OperaMsgCardData", "marketing2.0 no data");
                operaMsgCardData.ddc_(this.e);
                return;
            }
            List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
            if (koq.b(resources)) {
                ReleaseLogUtil.d("R_OperaMsgCardData", "resource list is empty");
                operaMsgCardData.ddc_(this.e);
            } else {
                ReleaseLogUtil.e("R_OperaMsgCardData", "Marketing resInfoList size = ", Integer.valueOf(resources.size()));
                operaMsgCardData.d(resources);
                operaMsgCardData.dcZ_(resources, this.e);
            }
        }
    }

    static class e implements OnFailureListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<OperaMsgCardData> f9484a;
        private Handler e;

        e(OperaMsgCardData operaMsgCardData, Handler handler) {
            this.f9484a = new WeakReference<>(operaMsgCardData);
            this.e = handler;
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            OperaMsgCardData operaMsgCardData = this.f9484a.get();
            if (operaMsgCardData == null) {
                ReleaseLogUtil.d("R_OperaMsgCardData", "cardData is null");
                return;
            }
            ReleaseLogUtil.c("R_OperaMsgCardData", "Failed to request the marketing2.0 text link data.");
            Handler handler = this.e;
            if (handler != null) {
                operaMsgCardData.ddc_(handler);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ReleaseLogUtil.e("R_OperaMsgCardData", "request resource");
        this.h.getResourceResultInfo(9001).addOnSuccessListener(TaskExecutors.immediate(), new c(this, this.h, this.d)).addOnFailureListener(new e(this, this.d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dcZ_(List<ResourceBriefInfo> list, Handler handler) {
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
                LogUtil.h("OperaMsgCardData", "resource brief info isUnValid");
            } else {
                String content = next.getContent().getContent();
                LogUtil.a("OperaMsgCardData", " marketing2.0: ", content);
                try {
                    TextGeneralTemplate textGeneralTemplate = (TextGeneralTemplate) gson.fromJson(content, TextGeneralTemplate.class);
                    if (textGeneralTemplate == null) {
                        LogUtil.a("OperaMsgCardData", "resource template parse exception");
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
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("OperaMsgCardData", "JsonSyntaxException");
                    ddc_(handler);
                    return;
                } catch (Exception unused2) {
                    LogUtil.b("OperaMsgCardData", "Exception");
                    ddc_(handler);
                    return;
                }
            }
        }
        LogUtil.a("OperaMsgCardData", "messageObjects size = ", Integer.valueOf(arrayList.size()));
        if (koq.b(arrayList)) {
            LogUtil.a("OperaMsgCardData", "messageObjects is empty");
            ddc_(handler);
            return;
        }
        LogUtil.c("OperaMsgCardData", "getOperationPositionMsg messageObjects size== ", Integer.valueOf(arrayList.size()));
        Message obtainMessage = handler.obtainMessage(0);
        obtainMessage.obj = arrayList;
        boolean sendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(obtainMessage);
        LogUtil.a("OperaMsgCardData", "queryOperationPositionMsg messageObjects result = ", Boolean.valueOf(sendMessageAtFrontOfQueue));
        if (sendMessageAtFrontOfQueue) {
            return;
        }
        handler.sendMessage(obtainMessage);
    }

    private void c() {
        ReleaseLogUtil.e("R_OperaMsgCardData", "dealOverseaMessage");
        jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData.5
            @Override // java.lang.Runnable
            public void run() {
                List<MessageObject> messages = OperaMsgCardData.this.g.getMessages(21);
                Handler handler = OperaMsgCardData.this.d;
                if (handler == null) {
                    ReleaseLogUtil.d("R_OperaMsgCardData", "dealOverseaMessage handler is null");
                    return;
                }
                if (koq.c(messages)) {
                    LogUtil.c("OperaMsgCardData", "getOperationPositionMsg msgDbObject size== ", Integer.valueOf(messages.size()));
                    Message obtainMessage = handler.obtainMessage(0);
                    obtainMessage.obj = messages;
                    boolean sendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(obtainMessage);
                    ReleaseLogUtil.e("R_OperaMsgCardData", "queryOperationPositionMsg result = ", Boolean.valueOf(sendMessageAtFrontOfQueue));
                    if (sendMessageAtFrontOfQueue) {
                        return;
                    }
                    handler.sendMessage(obtainMessage);
                    return;
                }
                ReleaseLogUtil.e("R_OperaMsgCardData", "msgDbObject is null");
                handler.sendEmptyMessage(1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddc_(Handler handler) {
        handler.sendEmptyMessage(1);
        d();
    }

    private MarketingApi a() {
        if (this.h == null) {
            this.h = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<MessageObject> list) {
        if (koq.b(list) || this.l == null) {
            LogUtil.h("OperaMsgCardData", "mOperaMSGCardViewHolder is null or no data");
            return;
        }
        if (!b(list)) {
            LogUtil.h("OperaMsgCardData", "message lists need not to update");
            return;
        }
        g();
        this.i = list;
        LogUtil.a("OperaMsgCardData", "updateOperaMsg");
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            MessageObject messageObject = list.get(i);
            if (i == 0) {
                LinearLayout linearLayout = (LinearLayout) View.inflate(this.c, R.layout.home_item_layout_opera_msg_item, null);
                this.f = linearLayout;
                ddd_(linearLayout, messageObject, i);
            }
            if (i == 1) {
                LinearLayout linearLayout2 = (LinearLayout) View.inflate(this.c, R.layout.home_item_layout_opera_msg_item, null);
                this.k = linearLayout2;
                ddd_(linearLayout2, messageObject, i);
            }
            if (i == 2) {
                LinearLayout linearLayout3 = (LinearLayout) View.inflate(this.c, R.layout.home_item_layout_opera_msg_item, null);
                this.m = linearLayout3;
                ddd_(linearLayout3, messageObject, i);
                break;
            }
            this.f9481a++;
            i++;
        }
        this.l.b.setVisibility(0);
        this.l.b.setFlipInterval(3000);
        if (this.f9481a > 1) {
            this.l.b.startFlipping();
        }
    }

    private boolean b(List<MessageObject> list) {
        if (list.size() != this.i.size()) {
            return true;
        }
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getMsgId().equals(this.i.get(i).getMsgId())) {
                z = true;
            }
        }
        return z;
    }

    static class d extends BaseHandler<OperaMsgCardData> {
        d(OperaMsgCardData operaMsgCardData) {
            super(operaMsgCardData);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ddh_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OperaMsgCardData operaMsgCardData, Message message) {
            int i = message.what;
            if (i == 0) {
                LogUtil.a("OperaMsgCardData", "show opera msg");
                if (message.obj instanceof List) {
                    operaMsgCardData.c((List<MessageObject>) message.obj);
                    return;
                } else {
                    operaMsgCardData.g();
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            LogUtil.a("OperaMsgCardData", "hide opera msg");
            if (operaMsgCardData.l != null) {
                operaMsgCardData.g();
                operaMsgCardData.l.b.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.l.b.isFlipping()) {
            this.l.b.stopFlipping();
        }
        this.l.b.removeAllViews();
        this.f9481a = 0;
        this.f = null;
        this.k = null;
        this.m = null;
    }

    private boolean c(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    private void ddd_(final View view, final MessageObject messageObject, final int i) {
        boolean z = messageObject.getPageType() == 3;
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.opera_msg_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.opera_msg_layout_icon);
        if (z) {
            ddb_(messageObject, imageView, healthTextView, view);
        } else {
            if (LanguageUtil.bc(this.c)) {
                imageView.setBackground(nrz.cKn_(this.c, R.drawable._2131430949_res_0x7f0b0e25));
            }
            healthTextView.setText(messageObject.getMsgTitle());
        }
        final boolean z2 = z;
        view.setOnClickListener(new View.OnClickListener() { // from class: onj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                OperaMsgCardData.this.ddf_(z2, messageObject, i, view, view2);
            }
        });
        this.l.b.addView(view);
        if (z) {
            c(messageObject, 0L, 1);
        } else {
            b(messageObject, AnalyticsValue.HEALTH_HOME_OPERA_POSITION_2010074);
        }
    }

    public /* synthetic */ void ddf_(boolean z, MessageObject messageObject, int i, View view, View view2) {
        if (z) {
            dde_(view2, messageObject, i);
        } else {
            dcY_(view, messageObject);
        }
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void dde_(final View view, final MessageObject messageObject, final int i) {
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
            if (browsingBiEvent != null) {
                browsingBiEvent.showFullServiceDialog(this.c);
            }
            LogUtil.h("OperaMsgCardData", "marketingMsgClick basic model, return");
            return;
        }
        boolean b2 = eie.b(messageObject.getDetailUri());
        LogUtil.a("OperaMsgCardData", "marketingMsgClick linkUrl = ", messageObject.getDetailUri());
        if (!b2) {
            dda_(view, messageObject, i);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: onl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    OperaMsgCardData.this.ddg_(view, messageObject, i, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
        }
    }

    public /* synthetic */ void ddg_(View view, MessageObject messageObject, int i, int i2, Object obj) {
        if (i2 == 0) {
            dda_(view, messageObject, i);
        } else {
            LogUtil.h("OperaMsgCardData", "marketingMsgClick errorCode = ", Integer.valueOf(i2));
        }
    }

    private void dda_(View view, MessageObject messageObject, int i) {
        eil.c(messageObject.getDetailUri(), 9001, c(messageObject), i + 1);
        Object tag = view.getTag(R.id.opera_msg_text);
        c(messageObject, tag instanceof Long ? ((Long) tag).longValue() : 0L, 2);
    }

    private ResourceBriefInfo c(MessageObject messageObject) {
        return new ResourceBriefInfo.Builder().setResourceId(messageObject.getMsgId()).setResourceName(messageObject.getMetadata()).build();
    }

    private void ddb_(MessageObject messageObject, ImageView imageView, HealthTextView healthTextView, View view) {
        if (c(this.c)) {
            if (!TextUtils.isEmpty(messageObject.getImgUri())) {
                imageView.setVisibility(4);
                try {
                    Glide.with(imageView).load(messageObject.getImgUri()).into((RequestBuilder<Drawable>) new b(imageView));
                } catch (IllegalArgumentException e2) {
                    LogUtil.b("OperaMsgCardData", " Exception ", e2.getMessage());
                }
            } else {
                imageView.setVisibility(0);
                if (LanguageUtil.bc(this.c)) {
                    imageView.setBackground(nrz.cKn_(this.c, R.drawable._2131430949_res_0x7f0b0e25));
                }
            }
        }
        healthTextView.setText(messageObject.getMsgTitle());
        healthTextView.setVisibility(messageObject.getFlag());
        view.setTag(R.id.opera_msg_text, Long.valueOf(System.currentTimeMillis()));
    }

    private void c(MessageObject messageObject, long j, int i) {
        if (messageObject == null) {
            LogUtil.h("OperaMsgCardData", "marketingBiEvent messageObject is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 9001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("resourceName", messageObject.getMetadata());
        hashMap.put("itemCardName", messageObject.getMsgTitle());
        hashMap.put("itemId", "");
        hashMap.put("pullOrder", 1);
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        }
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(this.c, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
        if (i == 2) {
            this.h = a();
            final ResourceBriefInfo resBriefInfo = messageObject.getResBriefInfo();
            if (this.h == null || resBriefInfo == null) {
                return;
            }
            ThreadPoolManager.d().execute(new Runnable() { // from class: onk
                @Override // java.lang.Runnable
                public final void run() {
                    OperaMsgCardData.this.d(resBriefInfo);
                }
            });
        }
    }

    public /* synthetic */ void d(ResourceBriefInfo resourceBriefInfo) {
        this.h.recordResourcePresent(9001, 1, resourceBriefInfo);
    }

    private void dcY_(View view, final MessageObject messageObject) {
        LogUtil.a("OperaMsgCardData", "operaMsg click");
        b(messageObject, AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075);
        if (TextUtils.isEmpty(messageObject.getDetailUri())) {
            if (messageObject.getMsgType() == 2) {
                a(messageObject);
                if (this.l.b.isFlipping()) {
                    this.l.b.stopFlipping();
                }
                this.l.b.removeView(view);
                int i = this.f9481a - 1;
                this.f9481a = i;
                if (i > 1) {
                    this.l.b.startFlipping();
                    return;
                }
                return;
            }
            return;
        }
        if (com.huawei.operation.utils.Utils.isNotSupportBrowseUrl(messageObject.getDetailUri())) {
            LoginInit.getInstance(this.c).browsingToLogin(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 == 0) {
                        OperaMsgCardData.this.d(messageObject);
                    }
                }
            }, AnalyticsValue.HEALTH_HOME_OPERA_POSITION_CLICK_2010075.value());
        } else {
            d(messageObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<ResourceBriefInfo> list) {
        LogUtil.a("OperaMsgCardData", "setResourceCache");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "CACHE_KEY_OPERA_MSG", nrv.e(list, new TypeToken<List<ResourceBriefInfo>>() { // from class: com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData.4
        }.getType()), (StorageParams) null);
    }

    private List<ResourceBriefInfo> b() {
        LogUtil.a("OperaMsgCardData", "getResourceCache");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "CACHE_KEY_OPERA_MSG");
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        try {
            return (List) nrv.c(b2, new TypeToken<List<ResourceBriefInfo>>() { // from class: com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData.1
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("OperaMsgCardData", "getResourceCache json syntax exception: ", b2);
            return null;
        }
    }

    private void d() {
        LogUtil.a("OperaMsgCardData", "clearResourceCache");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "CACHE_KEY_OPERA_MSG", (String) null, (StorageParams) null);
    }

    static class b extends DrawableImageViewTarget {
        b(ImageView imageView) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MessageObject messageObject) {
        if (messageObject.getDetailUri().startsWith("huaweischeme")) {
            a(messageObject);
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(messageObject.getDetailUri()));
            intent.setPackage(this.c.getPackageName());
            jdw.bGh_(intent, this.c);
            return;
        }
        b(messageObject);
    }

    private void b(MessageObject messageObject) {
        if (messageObject == null) {
            LogUtil.h("OperaMsgCardData", "gotoMassageDetailPage messageObject is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("msgId", messageObject.getMsgId());
        bundle.putString(com.huawei.health.messagecenter.model.CommonUtil.DETAIL_URI, messageObject.getDetailUri());
        bundle.putString("EXTRA_BI_NAME", messageObject.getMsgTitle());
        bundle.putString("EXTRA_BI_SOURCE", "HomePageFlipper");
        bundle.putString("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
        bundle.putString(MessageConstant.MESSAGE_TITLE, messageObject.getMsgTitle());
        AppRouter.b("/PluginMessageCenter/DispatchSkipEventActivity").zF_(bundle).c(this.c);
    }

    private void b(MessageObject messageObject, AnalyticsValue analyticsValue) {
        if (messageObject == null || analyticsValue == null) {
            LogUtil.h("OperaMsgCardData", "processBiEvent messageObject is null");
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

    private void a(final MessageObject messageObject) {
        if (messageObject == null || messageObject.getReadFlag() == 1) {
            LogUtil.h("OperaMsgCardData", "message is empty or status is read");
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.operamsgcard.OperaMsgCardData.8
                @Override // java.lang.Runnable
                public void run() {
                    OperaMsgCardData.this.g.setMessageRead(messageObject.getMsgId());
                }
            });
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "OperaMsgCardData";
    }
}
