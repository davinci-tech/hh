package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessCourseHorizontalHolder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.openservice.OpenServiceUtil;
import defpackage.asc;
import defpackage.koq;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class TopicFitnessView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f3138a;
    private a b;
    private Context c;
    private boolean d;
    private Handler e;
    private List<Topic> f;
    private int g;
    private LinearLayout h;
    private HealthRecycleView i;
    private c j;
    private ArrayList<String> k;

    public TopicFitnessView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new ArrayList<>(10);
        this.f = new ArrayList(10);
        this.d = false;
        this.f3138a = -1;
        this.g = 0;
        this.e = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.TopicFitnessView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b("Suggestion_TopicFitnessView", " msg is empty");
                    return;
                }
                super.handleMessage(message);
                if (koq.e(message.obj, Topic.class)) {
                    TopicFitnessView.this.c((List<Topic>) message.obj);
                }
            }
        };
        LayoutInflater.from(context).inflate(R.layout.sug_fragment_topic_list, this);
        e();
    }

    public void setLayoutBackground(int i) {
        this.f3138a = i;
        LinearLayout linearLayout = this.h;
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(i);
        }
        c cVar = this.j;
        if (cVar != null) {
            cVar.c(i);
        }
    }

    public void b(ArrayList<String> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("Suggestion_TopicFitnessView", "newInstance() topicNum.size = null");
            return;
        }
        this.k = arrayList;
        if (arrayList != null && arrayList.size() > 1) {
            this.d = true;
        } else {
            this.d = false;
        }
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.TopicFitnessView.2
            @Override // java.lang.Runnable
            public void run() {
                TopicFitnessView topicFitnessView = TopicFitnessView.this;
                topicFitnessView.a(topicFitnessView.g, TopicFitnessView.this.b);
            }
        });
    }

    private void e() {
        this.c = getContext();
        this.h = (LinearLayout) findViewById(R.id.my_recommend_fitness_container);
        this.i = (HealthRecycleView) findViewById(R.id.my_recommend_fitness_view);
        c cVar = new c(this.i, OpenServiceUtil.Location.WEIGHT);
        this.j = cVar;
        this.i.setAdapter(cVar);
        a();
        a aVar = new a(this);
        this.b = aVar;
        LogUtil.c("Suggestion_TopicFitnessView", "onCreateView() mMyUICallbackTopicList hashcode:", Integer.valueOf(aVar.hashCode()));
    }

    private void a() {
        if (this.d) {
            this.i.setLayoutManager(new HealthLinearLayoutManager(this.c));
        } else {
            this.i.setLayoutManager(new HealthLinearLayoutManager(this.c) { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.TopicFitnessView.4
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
                public boolean canScrollVertically() {
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, a aVar) {
        if (aVar == null) {
            LogUtil.b("Suggestion_TopicFitnessView", "getDataAndRefreshTopicView", "uiCallback is null");
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_TopicFitnessView", "getDataAndRefreshTopicView : courseApi is null.");
        } else {
            courseApi.getCourseTopicList(i, aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<Topic> list) {
        if (koq.b(list)) {
            LogUtil.b("Suggestion_TopicFitnessView", "sendRefreshTopicViewMessage(), topic is empty");
            return;
        }
        LogUtil.a("sendRefreshTopicViewMessage() topic.size = ", Integer.valueOf(list.size()));
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.obj = list;
        this.e.sendMessage(obtainMessage);
    }

    static class a extends UiCallback<List<Topic>> {
        private boolean b = true;
        TopicFitnessView d;
        WeakReference<TopicFitnessView> e;

        a(TopicFitnessView topicFitnessView) {
            WeakReference<TopicFitnessView> weakReference = new WeakReference<>(topicFitnessView);
            this.e = weakReference;
            this.d = weakReference.get();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            if (this.b) {
                return;
            }
            LogUtil.h("Suggestion_TopicFitnessView", "MyUiCallbackTopicList() onFailure mEnable=false ");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Topic> list) {
            LogUtil.a("Suggestion_TopicFitnessView", "MyUiCallbackTopicList(", Integer.valueOf(hashCode()), ") onSuccess()");
            TopicFitnessView topicFitnessView = this.d;
            if (topicFitnessView != null) {
                topicFitnessView.d(list);
            } else {
                LogUtil.a("Suggestion_TopicFitnessView", "MyUiCallbackTopicList mTopicFitnessView == null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Topic> list) {
        if (koq.b(list)) {
            LogUtil.b("Suggestion_TopicFitnessView", "refreshMyTopicView(), showRecommendTopicList is empty");
            return;
        }
        this.f.clear();
        boolean z = false;
        for (Topic topic : list) {
            if (topic != null && this.k != null && topic.acquireSerialNum() != null) {
                Iterator<String> it = this.k.iterator();
                while (it.hasNext()) {
                    if (topic.acquireSerialNum().equals(it.next())) {
                        this.f.add(topic);
                        z = true;
                    }
                }
            }
        }
        if (z) {
            this.j.e(this.f);
            LogUtil.a("Suggestion_TopicFitnessView", "showRecommendTopicList() mShowTopicList.size = ", Integer.valueOf(this.f.size()));
            int i = this.f3138a;
            if (i != -1) {
                LogUtil.a("Suggestion_TopicFitnessView", "setHolderBackground color = ", Integer.valueOf(i));
                this.j.c(this.f3138a);
                return;
            }
            return;
        }
        int i2 = this.g;
        if (i2 <= 2) {
            int i3 = i2 + 1;
            this.g = i3;
            LogUtil.a("Suggestion_TopicFitnessView", "request again pageNum = ", Integer.valueOf(i3));
            a(this.g, this.b);
            return;
        }
        LogUtil.h("Suggestion_TopicFitnessView", "can not show this page, mPageNum = ", Integer.valueOf(i2));
    }

    /* loaded from: classes8.dex */
    static class c extends RecyclerView.Adapter<FitnessCourseHorizontalHolder> {

        /* renamed from: a, reason: collision with root package name */
        private String f3140a;
        private FitnessCourseHorizontalHolder c;
        private Context d;
        private LayoutInflater e;
        private HealthRecycleView i;
        private List<Topic> h = new ArrayList(10);
        private int b = -1;

        c(HealthRecycleView healthRecycleView, String str) {
            this.i = healthRecycleView;
            this.f3140a = str;
            Context context = healthRecycleView.getContext();
            this.d = context;
            this.e = LayoutInflater.from(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: aBc_, reason: merged with bridge method [inline-methods] */
        public FitnessCourseHorizontalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            FitnessCourseHorizontalHolder fitnessCourseHorizontalHolder = new FitnessCourseHorizontalHolder(this.e.inflate(R.layout.sug_item_fitness_nomal, viewGroup, false), this.d);
            this.c = fitnessCourseHorizontalHolder;
            LogUtil.a("Suggestion_TopicFitnessView", "onCreateViewHolder() topicHolder = ", Integer.valueOf(fitnessCourseHorizontalHolder.hashCode()), ",mColor = ", Integer.valueOf(this.b));
            int i2 = this.b;
            if (i2 != -1) {
                this.c.a(i2);
            }
            return this.c;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(FitnessCourseHorizontalHolder fitnessCourseHorizontalHolder, int i) {
            if (fitnessCourseHorizontalHolder == null) {
                LogUtil.h("Suggestion_TopicFitnessView", "onBindViewHolder() holder = null");
            } else if (koq.d(this.h, i)) {
                fitnessCourseHorizontalHolder.d(this.h.get(i), this.f3140a);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.h.size();
        }

        public void e(List<Topic> list) {
            if (list == null) {
                LogUtil.h("Suggestion_TopicFitnessView", "notifyAddAll() topicList = null");
                return;
            }
            LogUtil.a("Suggestion_TopicFitnessView", "notifyAddAll() topics.SIZE = ", Integer.valueOf(list.size()));
            this.h.clear();
            this.h.addAll(list);
            notifyDataSetChanged();
        }

        public void c(int i) {
            this.b = i;
            FitnessCourseHorizontalHolder fitnessCourseHorizontalHolder = this.c;
            if (fitnessCourseHorizontalHolder != null) {
                fitnessCourseHorizontalHolder.a(i);
            }
        }
    }
}
