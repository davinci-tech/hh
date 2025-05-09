package com.huawei.health.knit.section.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionOperationCardAdapter extends RecyclerView.Adapter<SectionOperationRecycleHolder> {

    /* renamed from: a, reason: collision with root package name */
    private long f2709a = System.currentTimeMillis();
    private OnClickSectionListener b;
    private List<MessageObject> c;
    private Context e;

    public SectionOperationCardAdapter(Context context, List<MessageObject> list) {
        this.e = context;
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ajr_, reason: merged with bridge method [inline-methods] */
    public SectionOperationRecycleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionOperationRecycleHolder(LayoutInflater.from(this.e).inflate(R.layout.item_operation_card, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionOperationRecycleHolder sectionOperationRecycleHolder, int i) {
        LogUtil.a("SectionOperationCardAdapter", "operation card onBindViewHolder：", Integer.valueOf(i));
        List<MessageObject> list = this.c;
        if (list == null || i >= list.size() || this.c.get(i) == null) {
            return;
        }
        b(sectionOperationRecycleHolder, i);
        c(sectionOperationRecycleHolder, i);
    }

    private void c(SectionOperationRecycleHolder sectionOperationRecycleHolder, int i) {
        if (koq.b(this.c, i)) {
            LogUtil.h("SectionOperationCardAdapter", "click mList is out of bounds. position = ", Integer.valueOf(i));
            return;
        }
        MessageObject messageObject = this.c.get(i);
        if (messageObject == null) {
            LogUtil.h("SectionOperationCardAdapter", "click messageObject is null.");
            return;
        }
        Activity activity = BaseApplication.getActivity();
        BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
        OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
        if (baseActivity != null && operationUtilsApi != null) {
            sectionOperationRecycleHolder.itemView.setOnClickListener(nkx.cwZ_(ajq_(i), baseActivity, operationUtilsApi.isNotSupportBrowseUrl(messageObject.getDetailUri()), AnalyticsValue.HEALTH_HOME_OPERATION_CARD_DATA_2010085.value()));
        } else {
            sectionOperationRecycleHolder.itemView.setOnClickListener(ajq_(i));
        }
    }

    private View.OnClickListener ajq_(final int i) {
        return new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionOperationCardAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SectionOperationCardAdapter.this.b != null) {
                    SectionOperationCardAdapter.this.b.onClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    private void c(MessageObject messageObject, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 1001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourceName", messageObject.getMsgFrom());
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.f2709a));
            this.f2709a = System.currentTimeMillis();
        }
        hashMap.put("pullOrder", Integer.valueOf(i2));
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        ixx.d().d(this.e, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    private void b(SectionOperationRecycleHolder sectionOperationRecycleHolder, int i) {
        ViewGroup.MarginLayoutParams layoutParams;
        MessageObject messageObject = this.c.get(i);
        if (messageObject == null) {
            return;
        }
        c(messageObject, 1, i + 1);
        String imgBigUri = messageObject.getImgBigUri();
        if (TextUtils.isEmpty(imgBigUri)) {
            imgBigUri = messageObject.getImgUri();
        }
        String msgTitle = messageObject.getMsgTitle();
        String msgContent = messageObject.getMsgContent();
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (!TextUtils.isEmpty(imgBigUri)) {
            int dimension = (int) ((((this.e.getResources().getDisplayMetrics().widthPixels - this.e.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) - this.e.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d)) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue());
            if (nsn.ag(this.e)) {
                dimension = (nsn.h(this.e) - ((this.e.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) * 2) + nrr.b(this.e))) / 2;
            }
            int i2 = (dimension * 9) / 21;
            if (!(sectionOperationRecycleHolder.f2711a.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
                layoutParams = new RelativeLayout.LayoutParams(dimension, i2);
            } else {
                layoutParams = (ViewGroup.MarginLayoutParams) sectionOperationRecycleHolder.f2711a.getLayoutParams();
            }
            layoutParams.width = dimension;
            layoutParams.height = i2;
            int dimension2 = (int) this.e.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8);
            int dimension3 = (int) this.e.getResources().getDimension(R.dimen._2131362363_res_0x7f0a023b);
            if (this.c.size() == 1 || (this.c.size() == 2 && nsn.ag(this.e))) {
                layoutParams.setMargins(0, 0, 0, dimension3);
            } else if (i == 0 || (nsn.ag(this.e) && i == 1)) {
                layoutParams.setMargins(0, 0, 0, dimension2 / 2);
            } else {
                int i3 = dimension2 / 2;
                layoutParams.setMargins(0, i3, 0, i3);
            }
            sectionOperationRecycleHolder.f2711a.setLayoutParams(layoutParams);
            nrf.cIS_(sectionOperationRecycleHolder.f2711a, imgBigUri, nrf.d, 3, 0);
        }
        d(msgContent, sectionOperationRecycleHolder, msgTitle);
    }

    private void d(String str, SectionOperationRecycleHolder sectionOperationRecycleHolder, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            sectionOperationRecycleHolder.c.setText(str2);
        }
        if (!TextUtils.isEmpty(str)) {
            sectionOperationRecycleHolder.e.setVisibility(0);
            sectionOperationRecycleHolder.e.setText(str);
        } else {
            sectionOperationRecycleHolder.e.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MessageObject> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void a(OnClickSectionListener onClickSectionListener) {
        this.b = onClickSectionListener;
    }

    public static class SectionOperationRecycleHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f2711a;
        private HealthTextView c;
        private HealthTextView e;

        public SectionOperationRecycleHolder(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.operation_title_tv);
            this.e = (HealthTextView) view.findViewById(R.id.operation_context_tv);
            this.f2711a = (ImageView) view.findViewById(R.id.operation_img);
        }
    }
}
