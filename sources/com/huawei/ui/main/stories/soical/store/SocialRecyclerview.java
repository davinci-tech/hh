package com.huawei.ui.main.stories.soical.store;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageExt;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.suggestion.ui.view.BounceScrollView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ixx;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class SocialRecyclerview extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10493a;
    private boolean b;
    private List<MessageExt> c;
    private Context d;
    private MessageObject e;
    private OperationWebActivityIntentBuilderApi f;
    private HealthRecycleView g;
    private BounceScrollView h;
    private List<MessageExt> i;
    private HealthRecycleView j;
    private HealthTextView n;
    private HealthTextView o;

    public SocialRecyclerview(Context context) {
        super(context);
        this.b = false;
        b(context);
    }

    public SocialRecyclerview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        b(context);
    }

    public SocialRecyclerview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        b(context);
    }

    private void b(Context context) {
        this.d = context;
        LayoutInflater.from(context).inflate(R.layout.recycler_view_social, this);
        this.o = (HealthTextView) findViewById(R.id.social_recycler_title);
        this.n = (HealthTextView) findViewById(R.id.social_recycler_more);
        this.f10493a = (ImageView) findViewById(R.id.social_recycler_arrow);
        this.g = (HealthRecycleView) findViewById(R.id.social_recycler_view);
        ((RelativeLayout) findViewById(R.id.social_recycler_title_layout)).setOnClickListener(this);
        this.j = (HealthRecycleView) findViewById(R.id.social_recycler_view_horizontal);
        this.h = (BounceScrollView) findViewById(R.id.social_scrollView_horizontal);
        if (LanguageUtil.bc(this.d)) {
            this.f10493a.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.f10493a.setBackgroundResource(R.drawable.ic_health_list_arrow_gray);
        }
        this.g.setNestedScrollingEnabled(false);
        this.g.setHasFixedSize(true);
        this.f = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    }

    public void d(MessageObject messageObject) {
        MessageObject messageObject2;
        GridLayoutManager b2;
        this.e = messageObject;
        if (messageObject != null && TextUtils.isEmpty(messageObject.getDetailUri())) {
            this.n.setVisibility(8);
            this.f10493a.setVisibility(8);
        }
        a();
        if (!this.b || (messageObject2 = this.e) == null) {
            return;
        }
        this.o.setText(messageObject2.getMsgTitle());
        SocialMessageAdapter socialMessageAdapter = new SocialMessageAdapter(this.d, this.i, this.e.getLayout(), this.e.getMsgPosition());
        int layout = this.e.getLayout();
        if (layout != 1 && layout != 2 && layout != 3) {
            if (layout == 4) {
                setImageTextLayout(socialMessageAdapter);
                return;
            }
            this.j.setLayoutManager(new LinearLayoutManager(this.d, 0, false));
            this.j.setAdapter(socialMessageAdapter);
            this.h.setVisibility(0);
            this.g.setVisibility(8);
            return;
        }
        if (nsn.ag(this.d)) {
            if (this.e.getLayout() == 2) {
                b2 = b(4);
            } else if (this.e.getLayout() == 3) {
                b2 = b(3);
            } else {
                b2 = b(2);
            }
        } else {
            b2 = b(2);
        }
        this.g.setLayoutManager(b2);
        this.g.setAdapter(socialMessageAdapter);
        this.h.setVisibility(8);
        this.g.setVisibility(0);
    }

    private void setImageTextLayout(SocialMessageAdapter socialMessageAdapter) {
        this.g.setLayoutManager(new LinearLayoutManager(this.d) { // from class: com.huawei.ui.main.stories.soical.store.SocialRecyclerview.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.g.setAdapter(socialMessageAdapter);
        this.h.setVisibility(8);
        this.g.setVisibility(0);
    }

    private GridLayoutManager b(int i) {
        return new GridLayoutManager(this.d, i) { // from class: com.huawei.ui.main.stories.soical.store.SocialRecyclerview.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
    }

    private void a() {
        this.b = false;
        MessageObject messageObject = this.e;
        if (messageObject != null) {
            LogUtil.a("SocialRecyclerview", "开始筛选数据", messageObject.toString());
            this.c = this.e.getMessageExtList();
            if (CommonUtil.bu()) {
                this.b = true;
                this.i = this.c;
                return;
            }
            List<MessageExt> list = this.c;
            if (list != null) {
                LogUtil.a("SocialRecyclerview", "去除无效数据：", Integer.valueOf(list.size()));
                Iterator<MessageExt> it = this.c.iterator();
                long currentTimeMillis = System.currentTimeMillis();
                while (it.hasNext()) {
                    MessageExt next = it.next();
                    if (next == null || next.getBeginTime() > currentTimeMillis || next.getEndTime() < currentTimeMillis) {
                        it.remove();
                    }
                }
                if (this.c.size() > 0) {
                    LogUtil.a("SocialRecyclerview", "按权重和生效时间排序:", Integer.valueOf(this.c.size()));
                    Collections.sort(this.c, new b());
                    c();
                    return;
                }
                return;
            }
            LogUtil.h("SocialRecyclerview", "no branch!");
        }
    }

    private void c() {
        int layout = this.e.getLayout();
        int size = this.c.size();
        if (layout == 1) {
            if (size >= 2) {
                this.b = true;
                this.i = this.c.subList(0, 2);
                return;
            }
            return;
        }
        if (layout == 2) {
            if (size >= 4) {
                this.b = true;
                this.i = this.c.subList(0, 4);
                return;
            }
            return;
        }
        if (layout == 3) {
            if (size >= 6) {
                this.b = true;
                this.i = this.c.subList(0, 6);
                return;
            }
            return;
        }
        if (layout == 4) {
            this.b = true;
            this.i = this.c;
        } else if (size >= 2) {
            this.b = true;
            if (size == 2) {
                this.i = this.c.subList(0, 2);
            } else {
                this.i = this.c.subList(0, 3);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MessageObject messageObject = this.e;
        if (messageObject != null && !TextUtils.isEmpty(messageObject.getDetailUri())) {
            try {
                Context context = this.d;
                context.startActivity(this.f.builder(context, this.e.getDetailUri()).build());
            } catch (ActivityNotFoundException e) {
                LogUtil.a("SocialRecyclerview", "ActivityNotFoundException", e.getMessage());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            hashMap.put("msgId", this.e.getMsgId());
            hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.MSG_TITLE, this.e.getMsgTitle());
            if (this.e.getMsgPosition() == 27) {
                ixx.d().d(this.d, AnalyticsValue.HEALTH_WONDERFUL_EVENT_MORE_SCROLL_2020026.value(), hashMap, 0);
            } else {
                ixx.d().d(this.d, AnalyticsValue.HEALTH_UNMISSABLE_TOPIC_MORE_SCROLL_2020027.value(), hashMap, 0);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static class b implements Comparator<MessageExt>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(MessageExt messageExt, MessageExt messageExt2) {
            if (messageExt2.getWeight() > messageExt.getWeight()) {
                return 1;
            }
            if (messageExt2.getWeight() < messageExt.getWeight()) {
                return -1;
            }
            return Long.compare(messageExt2.getBeginTime(), messageExt.getBeginTime());
        }
    }

    public List<MessageExt> getShowList() {
        return this.i;
    }
}
