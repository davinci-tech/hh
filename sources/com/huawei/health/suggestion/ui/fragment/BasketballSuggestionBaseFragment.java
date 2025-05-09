package com.huawei.health.suggestion.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BasketballSuggestionActivity;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.BasketballAdvice;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.asc;
import defpackage.ffy;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class BasketballSuggestionBaseFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    protected Context f3228a;
    protected BasketballAdvice b;
    protected View c;
    protected HealthTextView d;
    protected HealthTextView e;
    protected HealthTextView f;
    protected HealthTextView g;
    protected HealthTextView h;
    protected HealthTextView i;
    protected ImageView j;
    private BaseRecyclerViewAdapter<Motion> m;
    private List<Motion> n = new ArrayList();

    protected void b() {
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3228a = getActivity();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.b = (BasketballAdvice) arguments.getParcelable("basketballAdvice");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_basketball_suggest_fragment, viewGroup, false);
        aFN_(inflate);
        b();
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment.2
            @Override // java.lang.Runnable
            public void run() {
                BasketballSuggestionBaseFragment.this.e();
            }
        });
    }

    public void e() {
        if (!LanguageUtil.m(arx.b()) || Utils.o()) {
            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "the oversea not support action");
            return;
        }
        BasketballAdvice basketballAdvice = this.b;
        if (basketballAdvice == null) {
            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "fragment have not init");
            return;
        }
        List<String> actionList = basketballAdvice.getActionList();
        if (koq.b(actionList)) {
            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "idList is empty");
        } else {
            a(actionList);
        }
    }

    protected void aFN_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "initLayout rootView == null");
            return;
        }
        this.h = (HealthTextView) view.findViewById(R.id.sug_item_value);
        this.g = (HealthTextView) view.findViewById(R.id.sug_item_unit);
        this.d = (HealthTextView) view.findViewById(R.id.sug_item_text);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sug_action_title);
        this.e = healthTextView;
        healthTextView.setText(getString(R$string.IDS_hwh_action_training));
        this.f = (HealthTextView) view.findViewById(R.id.sug_suggest_item_title);
        this.i = (HealthTextView) view.findViewById(R.id.sug_suggest_item_text);
        this.j = (ImageView) view.findViewById(R.id.sug_suggest_item_img);
        this.c = view.findViewById(R.id.sug_action_layout);
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.sug_action_recv);
        c();
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.m.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() { // from class: com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment.5
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter.OnItemClickListener
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                if (BasketballSuggestionBaseFragment.this.m != null) {
                    if (i >= 0 && i < BasketballSuggestionBaseFragment.this.m.getItemCount() && BasketballSuggestionBaseFragment.this.m.get(i) != null) {
                        if (((Motion) BasketballSuggestionBaseFragment.this.m.get(i)).getDescription() == null) {
                            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "mAdapter.setOnItemClickListener1: mAdapter.get(position).getDescription() == null");
                            return;
                        } else if (BasketballSuggestionBaseFragment.this.getActivity() instanceof BasketballSuggestionActivity) {
                            ((BasketballSuggestionActivity) BasketballSuggestionBaseFragment.this.getActivity()).d(i, BasketballSuggestionBaseFragment.this.n);
                            return;
                        } else {
                            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "getActivity() not instance of BasketballSuggestionActivity");
                            return;
                        }
                    }
                    LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "position out of bounds or mAdapter.get(position) == null");
                    return;
                }
                LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "mAdapter.setOnItemClickListener1: mAdapter == null");
            }
        });
        healthRecycleView.setAdapter(this.m);
        this.c.setVisibility(8);
    }

    private void c() {
        this.m = new BaseRecyclerViewAdapter<Motion>(new ArrayList(), R.layout.sug_fitness_rec_action_outof_workout) { // from class: com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment.1
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, Motion motion) {
                String d;
                if (recyclerHolder == null || motion == null) {
                    LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "holder || itemData is null");
                    return;
                }
                if (LanguageUtil.bc(BasketballSuggestionBaseFragment.this.f3228a)) {
                    d = ffy.d(BasketballSuggestionBaseFragment.this.f3228a, R.string._2130848850_res_0x7f022c52, UnitUtil.e(Math.round(motion.acquireDuration() / 1000.0f), 1, 0), UnitUtil.e(motion.acquireGroups(), 1, 0));
                } else {
                    d = ffy.d(BasketballSuggestionBaseFragment.this.f3228a, R.string._2130848849_res_0x7f022c51, UnitUtil.e(motion.acquireGroups(), 1, 0), UnitUtil.e(Math.round(motion.acquireDuration() / 1000.0f), 1, 0));
                }
                recyclerHolder.e(R.id.sug_fitness_iv_train_pic, motion.acquirePicUrl(), nrf.e).e(R.id.tv_train_title_adv, motion.acquireName()).e(R.id.tv_train_n_adv, d).e(R.id.sug_fitness_rec_gap, ffy.b(R.plurals._2130903475_res_0x7f0301b3, motion.acquireGap(), UnitUtil.e(motion.acquireGap(), 1, 0)));
                if (i != getItemCount() - 1) {
                    recyclerHolder.d(R.id.tv_train_seg_adv, 0);
                }
            }
        };
    }

    private void a(final List<String> list) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "getActions : courseApi is null.");
        } else {
            courseApi.getCourseTrainAction(list, new UiCallback<List<TrainAction>>() { // from class: com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    if (BasketballSuggestionBaseFragment.this.getActivity() instanceof BasketballSuggestionActivity) {
                        BasketballSuggestionActivity basketballSuggestionActivity = (BasketballSuggestionActivity) BasketballSuggestionBaseFragment.this.getActivity();
                        if (basketballSuggestionActivity != null) {
                            if (BasketballSuggestionBaseFragment.this.n != null && BasketballSuggestionBaseFragment.this.n.size() == list.size()) {
                                LogUtil.b("Suggestion_BasketballSuggestionBaseFragment", "DownloadUICallback onFailure,and local has success download data");
                                return;
                            } else {
                                basketballSuggestionActivity.e();
                                return;
                            }
                        }
                        LogUtil.b("Suggestion_BasketballSuggestionBaseFragment", "DownloadUICallback onFailure BasketballSuggestionActivity == null");
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<TrainAction> list2) {
                    BasketballSuggestionBaseFragment.this.c(list2, list);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<TrainAction> list, List<String> list2) {
        if (getActivity() instanceof BasketballSuggestionActivity) {
            BasketballSuggestionActivity basketballSuggestionActivity = (BasketballSuggestionActivity) getActivity();
            if (basketballSuggestionActivity == null) {
                LogUtil.b("Suggestion_BasketballSuggestionBaseFragment", "DownloadUICallback onFailure BasketballSuggestionActivity == null");
                return;
            }
            if (list == null) {
                basketballSuggestionActivity.e();
                LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "data is null");
            } else if (list.size() != list2.size()) {
                basketballSuggestionActivity.e();
                LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "partly of the data is return");
            } else {
                d(this.n, list);
                basketballSuggestionActivity.d();
                basketballSuggestionActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment.3
                    @Override // java.lang.Runnable
                    public void run() {
                        BasketballSuggestionBaseFragment.this.c.setVisibility(0);
                        BasketballSuggestionBaseFragment.this.m.refreshDataChange(BasketballSuggestionBaseFragment.this.n);
                    }
                });
            }
        }
    }

    private void d(List<Motion> list, List<TrainAction> list2) {
        LogUtil.a("Suggestion_BasketballSuggestionBaseFragment", "changeToMotion ", list);
        list.clear();
        for (TrainAction trainAction : list2) {
            if (trainAction == null) {
                LogUtil.h("Suggestion_BasketballSuggestionBaseFragment", "action = null");
            } else {
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
                if (trainAction.acquireVideos() != null && trainAction.acquireVideos().size() > 0) {
                    Video video = trainAction.acquireVideos().get(0);
                    motion.savePicUrl(video.getThumbnail());
                    motion.saveMotionPath(video.getUrl());
                    motion.setOriginLogo(video.getLogoImgUrl());
                    motion.saveLength(video.getLength());
                }
                list.add(motion);
            }
        }
        LogUtil.a("Suggestion_BasketballSuggestionBaseFragment", "changeToMotion ", list);
    }
}
