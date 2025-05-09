package com.huawei.ui.homehealth.operationcard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import com.huawei.health.marketing.datatype.templates.DailyRecommendTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.Utils;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eil;
import defpackage.eiv;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mcv;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class OperationRecycleViewAdapter extends RecyclerView.Adapter<OperationRecycleHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<MessageObject> f9487a;
    private int c;
    private Context d;
    private long e = System.currentTimeMillis();

    public OperationRecycleViewAdapter(Context context, List<MessageObject> list) {
        this.d = context;
        this.f9487a = list;
    }

    public void e(int i) {
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ddl_, reason: merged with bridge method [inline-methods] */
    public OperationRecycleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OperationRecycleHolder(LayoutInflater.from(this.d).inflate(R.layout.item_operation_card, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(OperationRecycleHolder operationRecycleHolder, int i) {
        LogUtil.c("OperationRecycleViewAdapter", "绘制界面：", Integer.valueOf(i));
        List<MessageObject> list = this.f9487a;
        if (list == null || i >= list.size() || this.f9487a.get(i) == null) {
            return;
        }
        d(operationRecycleHolder, i);
        e(operationRecycleHolder, i);
    }

    private void e(OperationRecycleHolder operationRecycleHolder, int i) {
        if (koq.b(this.f9487a, i)) {
            LogUtil.h("OperationRecycleViewAdapter", "click mList is out of bounds. position = ", Integer.valueOf(i));
            return;
        }
        MessageObject messageObject = this.f9487a.get(i);
        if (messageObject == null) {
            LogUtil.h("OperationRecycleViewAdapter", "click messageObject is null.");
            return;
        }
        Activity activity = BaseApplication.getActivity();
        BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
        if (baseActivity != null) {
            operationRecycleHolder.itemView.setOnClickListener(nkx.cwZ_(ddk_(messageObject), baseActivity, Utils.isNotSupportBrowseUrl(messageObject.getDetailUri()), AnalyticsValue.HEALTH_HOME_OPERATION_CARD_DATA_2010085.value()));
        } else {
            operationRecycleHolder.itemView.setOnClickListener(ddk_(messageObject));
        }
    }

    private View.OnClickListener ddk_(MessageObject messageObject) {
        return new a(this, messageObject);
    }

    static class a implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private MessageObject f9489a;
        private WeakReference<OperationRecycleViewAdapter> b;

        public a(OperationRecycleViewAdapter operationRecycleViewAdapter, MessageObject messageObject) {
            this.b = new WeakReference<>(operationRecycleViewAdapter);
            this.f9489a = messageObject;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            final OperationRecycleViewAdapter operationRecycleViewAdapter = this.b.get();
            if (operationRecycleViewAdapter == null) {
                LogUtil.h("OperationRecycleViewAdapter", "ViewOnClickListener adapter is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            final String detailUri = this.f9489a.getDetailUri();
            if (!health.compact.a.Utils.o() || OperationUtils.switchToMarketingTwo()) {
                operationRecycleViewAdapter.b(this.f9489a, 2);
                operationRecycleViewAdapter.a(eil.c(detailUri, this.f9489a.getCategory()), this.f9489a);
                LogUtil.a("OperationRecycleViewAdapter", "is marking 2.0");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            operationRecycleViewAdapter.d(this.f9489a);
            if (!TextUtils.isEmpty(detailUri)) {
                GRSManager.a(BaseApplication.getContext()).e("messageCenterUrl", new GrsQueryCallback() { // from class: com.huawei.ui.homehealth.operationcard.OperationRecycleViewAdapter.a.3
                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackSuccess(String str) {
                        LogUtil.c("OperationRecycleViewAdapter", "GRSManager onCallBackSuccess url = ", str);
                        operationRecycleViewAdapter.a(operationRecycleViewAdapter.b(detailUri, str + "/messageH5/html/launchFitness.html?url="), detailUri);
                    }

                    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                    public void onCallBackFail(int i) {
                        LogUtil.c("OperationRecycleViewAdapter", "GRSManager onCallBackFail code = ", Integer.valueOf(i));
                        operationRecycleViewAdapter.a(detailUri);
                    }
                });
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(MessageObject messageObject) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("msgId", messageObject.getMsgId());
        hashMap.put(CommonUtil.MSG_TITLE, messageObject.getMsgTitle());
        ixx.d().d(this.d, AnalyticsValue.HEALTH_HOME_OPERATION_CARD_DATA_2010085.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MessageObject messageObject, int i) {
        if (messageObject == null) {
            LogUtil.h("OperationRecycleViewAdapter", "biEvent messageObject is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 1001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourceName", messageObject.getMsgFrom());
        hashMap.put("itemCardName", messageObject.getMsgTitle());
        hashMap.put("itemId", "");
        hashMap.put("pullOrder", 1);
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.e));
            this.e = System.currentTimeMillis();
        }
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        ixx.d().d(this.d, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, MessageObject messageObject) {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(e(str, messageObject));
        }
    }

    private String e(String str, MessageObject messageObject) {
        if (str.contains("?")) {
            return str + "&pullfrom=1001&resourceName=" + messageObject.getMsgFrom() + "&resourceId=" + messageObject.getMsgId() + "&pullOrder=1&algId=&itemId=" + messageObject.getMsgId();
        }
        return str + "?pullfrom=1001?resourceName=" + messageObject.getMsgTitle() + "?resourceId=" + messageObject.getMsgId() + "?pullOrder=1?algId=?itemId=" + messageObject.getMsgId();
    }

    private void d(OperationRecycleHolder operationRecycleHolder, int i) {
        MessageObject messageObject = this.f9487a.get(i);
        if (operationRecycleHolder == null || messageObject == null) {
            return;
        }
        b(messageObject, 1);
        String imgBigUri = messageObject.getImgBigUri();
        if (TextUtils.isEmpty(imgBigUri)) {
            imgBigUri = messageObject.getImgUri();
        }
        String msgTitle = messageObject.getMsgTitle();
        String msgContent = messageObject.getMsgContent();
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (!TextUtils.isEmpty(imgBigUri)) {
            int dimension = (int) ((((this.d.getResources().getDisplayMetrics().widthPixels - this.d.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) - this.d.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue());
            if (this.c >= 2) {
                dimension = (nsn.h(this.d) - ((this.d.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) * 2) + nrr.b(this.d))) / 2;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimension, (dimension * 9) / 21);
            int dimension2 = (int) this.d.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8);
            int dimension3 = (int) this.d.getResources().getDimension(R.dimen._2131362363_res_0x7f0a023b);
            if (this.f9487a.size() == 1 || (this.f9487a.size() == 2 && nsn.ag(this.d))) {
                layoutParams.setMargins(0, 0, 0, dimension3);
            } else if (i == 0 || (nsn.ag(this.d) && i == 1)) {
                layoutParams.setMargins(0, 0, 0, dimension2 / 2);
            } else {
                int i2 = dimension2 / 2;
                layoutParams.setMargins(0, i2, 0, i2);
            }
            operationRecycleHolder.d.setLayoutParams(layoutParams);
            nrf.cIS_(operationRecycleHolder.d, imgBigUri, nrf.d, 3, 0);
        }
        d(msgContent, operationRecycleHolder, msgTitle);
        e(operationRecycleHolder, messageObject);
    }

    private void d(String str, OperationRecycleHolder operationRecycleHolder, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            operationRecycleHolder.f9488a.setText(str2);
        } else {
            operationRecycleHolder.f9488a.setText("");
        }
        if (!TextUtils.isEmpty(str)) {
            operationRecycleHolder.c.setVisibility(0);
            operationRecycleHolder.c.setText(str);
        } else {
            operationRecycleHolder.c.setVisibility(8);
        }
    }

    private void e(OperationRecycleHolder operationRecycleHolder, MessageObject messageObject) {
        LogUtil.a("OperationRecycleViewAdapter", "initOtherField");
        DailyRecommendTemplate a2 = OperationCardData.a(messageObject.getResBriefInfo());
        if (a2 == null) {
            return;
        }
        a(operationRecycleHolder.e, a2);
        a(operationRecycleHolder.b, a2, messageObject);
    }

    private void a(HealthTextView healthTextView, DailyRecommendTemplate dailyRecommendTemplate, MessageObject messageObject) {
        String str;
        if (healthTextView == null) {
            ReleaseLogUtil.c("OperationRecycleViewAdapter", "activityJoinNumTextView = null");
            return;
        }
        int activityJoinNum = messageObject.getActivityJoinNum();
        if (TextUtils.equals(messageObject.getCategory(), SingleDailyMomentContent.ACTIVITY_TYPE) && !TextUtils.isEmpty(dailyRecommendTemplate.getDynamicDataId()) && activityJoinNum > 0) {
            String num = Integer.toString(activityJoinNum);
            try {
                str = UnitUtil.e(activityJoinNum, 1, 0);
            } catch (Exception e) {
                e = e;
                str = "";
            }
            try {
                num = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903123_res_0x7f030053, activityJoinNum, str);
            } catch (Exception e2) {
                e = e2;
                ReleaseLogUtil.c("OperationRecycleViewAdapter", "setJoinNumberOrReadCount failed, cause exception = ", e.getMessage(), ", cause = ", e.getCause(), ", numberOfPeople = ", str, ", numOfPeople = ", Integer.valueOf(activityJoinNum));
                healthTextView.setText(num);
                healthTextView.setVisibility(0);
                return;
            }
            healthTextView.setText(num);
            healthTextView.setVisibility(0);
            return;
        }
        healthTextView.setVisibility(8);
    }

    private void a(HealthTextView healthTextView, DailyRecommendTemplate dailyRecommendTemplate) {
        if (healthTextView == null) {
            return;
        }
        eiv.d(healthTextView, (CornerTemplate) dailyRecommendTemplate, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        str.hashCode();
        if (str.equals(Constants.SUFFIX_HTML)) {
            a(str2);
        } else if (str.equals("medal")) {
            mcv.d(this.d).j(this.d);
        } else {
            a(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Intent intent = new Intent(this.d, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        this.d.startActivity(intent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MessageObject> list = this.f9487a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str, String str2) {
        String queryParameter = (TextUtils.isEmpty(str) || !str.startsWith(str2)) ? Constants.SUFFIX_HTML : Uri.parse(str.replace(str2, "")).getQueryParameter("type");
        LogUtil.c("OperationRecycleViewAdapter", "跳转类型：", queryParameter);
        return queryParameter;
    }

    public static class OperationRecycleHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9488a;
        private HealthTextView b;
        private HealthTextView c;
        private ImageView d;
        private HealthTextView e;

        public OperationRecycleHolder(View view) {
            super(view);
            this.f9488a = (HealthTextView) view.findViewById(R.id.operation_title_tv);
            this.c = (HealthTextView) view.findViewById(R.id.operation_context_tv);
            this.e = (HealthTextView) view.findViewById(R.id.operation_corner);
            this.b = (HealthTextView) view.findViewById(R.id.operation_join_num);
            this.d = (ImageView) view.findViewById(R.id.operation_img);
        }
    }
}
