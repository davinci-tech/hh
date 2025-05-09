package com.huawei.health.suggestion.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity;
import com.huawei.healthcloud.plugintrack.model.JudgeRootBean;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import defpackage.arx;
import defpackage.asc;
import defpackage.ffy;
import defpackage.gge;
import defpackage.gnm;
import defpackage.gxq;
import defpackage.hjs;
import defpackage.hjt;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class PostureSuggestBaseFragment extends BaseFragment {
    protected View b;
    protected Context c;
    protected ImageView d;
    protected HealthTextView e;
    protected HealthTextView f;
    protected HealthTextView g;
    protected HealthTextView h;
    protected HealthTextView i;
    protected HealthTextView j;
    private HashMap<SportDetailChartDataType, List<gxq>> k;
    protected HealthTextView l;
    protected HealthTableWidget m;
    private BaseRecyclerViewAdapter<Motion> n;
    protected RunningPostureAdviceBase o;

    /* renamed from: a, reason: collision with root package name */
    protected int f3233a = 0;
    private List<Motion> p = new ArrayList();

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getActivity();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.o = (RunningPostureAdviceBase) arguments.getParcelable("runningPostureAdvice");
            Serializable serializable = arguments.getSerializable("runningPostureJudgeList");
            if (serializable instanceof HashMap) {
                this.k = (HashMap) serializable;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_running_posture_suggest_fragment, viewGroup, false);
        aGe_(inflate);
        e();
        return inflate;
    }

    protected void e() {
        RunningPostureAdviceBase runningPostureAdviceBase = this.o;
        if (runningPostureAdviceBase == null) {
            LogUtil.h("Suggestion_PostureFragment", "initViewData mRunningPostureAdvice is null.");
            return;
        }
        int acquireLevel = runningPostureAdviceBase.acquireLevel();
        if (acquireLevel == -1) {
            this.f.setTextColor(getResources().getColor(R.color._2131299278_res_0x7f090bce));
        } else if (acquireLevel == 0) {
            this.f.setTextColor(getResources().getColor(R.color._2131299279_res_0x7f090bcf));
        } else if (acquireLevel == 1) {
            this.f.setTextColor(getResources().getColor(R.color._2131299276_res_0x7f090bcc));
        } else if (acquireLevel == 2) {
            this.f.setTextColor(getResources().getColor(R.color._2131299273_res_0x7f090bc9));
        } else if (acquireLevel == 3) {
            this.f.setTextColor(getResources().getColor(R.color._2131299272_res_0x7f090bc8));
        }
        this.f.setText(getString(this.o.acquireLevelShortTip()));
        if (this.f3233a == 0 && this.o.acquireLevel() == 1) {
            this.j.setText(String.format(getString(this.o.acquireAdvice()), 200));
        }
        this.j.setText(String.format(getString(this.o.acquireAdvice()), 200));
        this.i.setText(getString(this.o.acquireLevelLongTip()));
    }

    protected void aGe_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_PostureFragment", "initLayout rootView == null");
            return;
        }
        this.h = (HealthTextView) view.findViewById(R.id.sug_running_posture_item_name);
        this.l = (HealthTextView) view.findViewById(R.id.sug_running_posture_item_value);
        this.g = (HealthTextView) view.findViewById(R.id.sug_running_posture_item_unit);
        this.f = (HealthTextView) view.findViewById(R.id.sug_running_posture_item_level);
        this.i = (HealthTextView) view.findViewById(R.id.sug_running_posture_item_title);
        this.j = (HealthTextView) view.findViewById(R.id.sug_running_posture_item_text);
        this.e = (HealthTextView) view.findViewById(R.id.sug_running_posture_action_title);
        this.d = (ImageView) view.findViewById(R.id.img_explain_icon);
        this.e.setText(getString(R$string.IDS_hwh_action_training));
        this.b = view.findViewById(R.id.sug_posture_action_layout);
        this.m = (HealthTableWidget) view.findViewById(R.id.pace_table_layout);
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.sug_running_posture_action_recv);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        d();
        a();
        healthRecycleView.setAdapter(this.n);
        this.b.setVisibility(8);
        this.m.setVisibility(8);
    }

    private List<PostureJudgeBean> c() {
        return ((JudgeRootBean) new Gson().fromJson(hjt.e(BaseApplication.getContext()), JudgeRootBean.class)).getJudge();
    }

    protected void c(SportDetailChartDataType sportDetailChartDataType) {
        List<PostureJudgeBean> c2 = c();
        HashMap<SportDetailChartDataType, List<gxq>> hashMap = this.k;
        if (hashMap == null || hashMap.isEmpty() || !this.k.containsKey(sportDetailChartDataType)) {
            LogUtil.h("Suggestion_PostureFragment", "mMap is wrong");
            return;
        }
        new hjs(this.c, sportDetailChartDataType, c2, this.k.get(sportDetailChartDataType)).b(this.m, hjt.e(new HealthColumnSystem(this.c), true));
    }

    private void d() {
        this.n = new BaseRecyclerViewAdapter<Motion>(new ArrayList(), R.layout.sug_fitness_rec_action_outof_workout) { // from class: com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment.2
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, Motion motion) {
                String d;
                if (recyclerHolder == null || motion == null || PostureSuggestBaseFragment.this.c == null) {
                    LogUtil.h("Suggestion_PostureFragment", "convert holder == null || itemData == null || mContext == null");
                    return;
                }
                if (LanguageUtil.bc(PostureSuggestBaseFragment.this.c)) {
                    d = ffy.d(PostureSuggestBaseFragment.this.c, R.string._2130848850_res_0x7f022c52, UnitUtil.e(Math.round(motion.acquireDuration() / 1000.0f), 1, 0), UnitUtil.e(motion.acquireGroups(), 1, 0));
                } else {
                    d = ffy.d(PostureSuggestBaseFragment.this.c, R.string._2130848849_res_0x7f022c51, UnitUtil.e(motion.acquireGroups(), 1, 0), UnitUtil.e(Math.round(motion.acquireDuration() / 1000.0f), 1, 0));
                }
                recyclerHolder.e(R.id.sug_fitness_iv_train_pic, motion.acquirePicUrl(), nrf.e).e(R.id.tv_train_title_adv, motion.acquireName()).e(R.id.tv_train_n_adv, d).e(R.id.sug_fitness_rec_gap, ffy.b(R.plurals._2130903475_res_0x7f0301b3, motion.acquireGap(), UnitUtil.e(motion.acquireGap(), 1, 0)));
                if (i != getItemCount() - 1) {
                    recyclerHolder.d(R.id.tv_train_seg_adv, 0);
                }
            }
        };
    }

    private void a() {
        this.n.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() { // from class: com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment.4
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (PostureSuggestBaseFragment.this.n.get(i) != null) {
                    if (((Motion) PostureSuggestBaseFragment.this.n.get(i)).getDescription() == null) {
                        LogUtil.h("Suggestion_PostureFragment", "mAdapter.setOnItemClickListener1: mAdapter.get(position).getDescription() == null");
                        return;
                    }
                    HashMap hashMap = new HashMap(3);
                    hashMap.put("click", "1");
                    hashMap.put("postureId", Integer.valueOf(PostureSuggestBaseFragment.this.f3233a));
                    hashMap.put("actionId", ((Motion) PostureSuggestBaseFragment.this.n.get(i)).acquireId());
                    gge.e("1130025", hashMap);
                    Intent intent = new Intent(PostureSuggestBaseFragment.this.c, (Class<?>) FitnessActionDetailActivity.class);
                    intent.putExtra("action_id", ((Motion) PostureSuggestBaseFragment.this.n.get(i)).acquireId());
                    intent.putExtra("action_version", ((Motion) PostureSuggestBaseFragment.this.n.get(i)).acquireVersion());
                    intent.setFlags(268435456);
                    gnm.aPB_(PostureSuggestBaseFragment.this.c, intent);
                    return;
                }
                LogUtil.h("Suggestion_PostureFragment", "mAdapter.setOnItemClickListener1: mAdapter.get(position) == null");
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment.5
            @Override // java.lang.Runnable
            public void run() {
                PostureSuggestBaseFragment.this.b();
            }
        });
    }

    public void b() {
        if (!LanguageUtil.m(arx.b()) || Utils.o()) {
            LogUtil.h("Suggestion_PostureFragment", "the oversea not support action");
            return;
        }
        RunningPostureAdviceBase runningPostureAdviceBase = this.o;
        if (runningPostureAdviceBase == null) {
            LogUtil.h("Suggestion_PostureFragment", "fragment have not init");
            return;
        }
        List<String> acquireActionList = runningPostureAdviceBase.acquireActionList();
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi != null) {
            courseApi.getCourseTrainAction(acquireActionList, new c(acquireActionList, this));
        }
    }

    static class c extends UiCallback<List<TrainAction>> {
        private final List<String> b;
        private final WeakReference<PostureSuggestBaseFragment> c;

        c(List<String> list, PostureSuggestBaseFragment postureSuggestBaseFragment) {
            this.c = new WeakReference<>(postureSuggestBaseFragment);
            this.b = list;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            PostureSuggestBaseFragment postureSuggestBaseFragment = this.c.get();
            if (postureSuggestBaseFragment != null) {
                postureSuggestBaseFragment.c(this.b);
            } else {
                LogUtil.h("Suggestion_PostureFragment", "CourseUiCallback onFailure fragment == null");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<TrainAction> list) {
            PostureSuggestBaseFragment postureSuggestBaseFragment = this.c.get();
            if (postureSuggestBaseFragment != null) {
                postureSuggestBaseFragment.b(list, this.b);
            } else {
                LogUtil.h("Suggestion_PostureFragment", "CourseUiCallback onSuccess fragment == null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<String> list) {
        if (!(getActivity() instanceof RuningPostureSuggestActivity)) {
            LogUtil.h("Suggestion_PostureFragment", "onGetActionFailure getActivity() !instanceof RuningPostureSuggestActivity");
            return;
        }
        RuningPostureSuggestActivity runingPostureSuggestActivity = (RuningPostureSuggestActivity) getActivity();
        if (runingPostureSuggestActivity == null) {
            LogUtil.b("Suggestion_PostureFragment", "DownloadUICallback onFailure RunningPostureSuggestActivity == null");
            return;
        }
        List<Motion> list2 = this.p;
        if (list2 != null && list != null && list2.size() == list.size()) {
            LogUtil.b("Suggestion_PostureFragment", "DownloadUICallback onFailure,and local has success download data");
        } else {
            runingPostureSuggestActivity.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<TrainAction> list, List<String> list2) {
        if (!(getActivity() instanceof RuningPostureSuggestActivity)) {
            LogUtil.h("Suggestion_PostureFragment", "onGetActionSuccess getActivity() !instanceof RuningPostureSuggestActivity");
            return;
        }
        RuningPostureSuggestActivity runingPostureSuggestActivity = (RuningPostureSuggestActivity) getActivity();
        if (runingPostureSuggestActivity == null) {
            LogUtil.b("Suggestion_PostureFragment", "DownloadUICallback onFailure RunningPostureSuggestActivity == null");
            return;
        }
        if (list == null) {
            runingPostureSuggestActivity.d();
            LogUtil.h("Suggestion_PostureFragment", "data is null");
        } else if (list2 != null && list.size() != list2.size()) {
            runingPostureSuggestActivity.d();
            LogUtil.h("Suggestion_PostureFragment", "partly of the data is return");
        } else {
            d(this.p, list);
            runingPostureSuggestActivity.b();
            runingPostureSuggestActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    PostureSuggestBaseFragment.this.b.setVisibility(0);
                    PostureSuggestBaseFragment.this.n.refreshDataChange(PostureSuggestBaseFragment.this.p);
                }
            });
        }
    }

    private void d(List<Motion> list, List<TrainAction> list2) {
        LogUtil.a("Suggestion_PostureFragment", "changeToMotion ", list);
        list.clear();
        for (TrainAction trainAction : list2) {
            Motion motion = new Motion();
            motion.setId(trainAction.acquireId());
            motion.saveName(trainAction.acquireName());
            motion.saveMotionType(trainAction.acquireMotionType());
            motion.saveDifficulty(trainAction.acquireDifficulty());
            motion.saveVersion(trainAction.acquireVersion());
            motion.setDescription(trainAction.acquireDescription());
            motion.setEquipments(trainAction.acquireEquipments());
            motion.setTrainingPoints(trainAction.acquireTrainingpoints());
            motion.setModified(trainAction.acquireModified());
            if (koq.c(trainAction.acquireVideos())) {
                Video video = trainAction.acquireVideos().get(0);
                motion.savePicUrl(video.getThumbnail());
                motion.saveMotionPath(video.getUrl());
                motion.setOriginLogo(video.getLogoImgUrl());
                motion.saveLength(video.getLength());
            }
            list.add(motion);
        }
        LogUtil.a("Suggestion_PostureFragment", "changeToMotion ", list);
    }

    protected void d(int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("chartType", Integer.valueOf(i));
        hashMap.put("dataPage", "runPostureDetails");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_CIRCLE_RING_GUIDE_1040121.value(), hashMap, 0);
    }
}
