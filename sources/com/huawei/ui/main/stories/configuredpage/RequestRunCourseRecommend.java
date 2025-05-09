package com.huawei.ui.main.stories.configuredpage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.fragment.app.FragmentManager;
import com.huawei.health.suggestion.model.RunRecommendWorkout;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.main.stories.configuredpage.RequestRunCourseRecommend;
import defpackage.ggs;
import defpackage.koq;
import defpackage.pez;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes9.dex */
public class RequestRunCourseRecommend {

    /* renamed from: a, reason: collision with root package name */
    private int f9686a;
    private FragmentManager c;
    private List<RunRecommendWorkout> j = new ArrayList();
    private List<RunRecommendWorkout> e = new ArrayList();
    private List<FitWorkout> f = new ArrayList();
    private List<FitWorkout> i = new ArrayList();
    private CountDownLatch d = new CountDownLatch(5);
    private State h = State.ACTIVE;
    private Handler b = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.configuredpage.RequestRunCourseRecommend.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                ReleaseLogUtil.c("Suggest_RequestRunCourseRecommend", "msg is null!");
                return;
            }
            super.handleMessage(message);
            if (message.what == 101) {
                ReleaseLogUtil.e("Suggest_RequestRunCourseRecommend", "add recommend data.");
                ArrayList<FitWorkout> e = RequestRunCourseRecommend.this.e();
                LogUtil.a("Suggest_RequestRunCourseRecommend", "MSG_ADD_RECOMMEND_DATA mContainerId: ", Integer.valueOf(RequestRunCourseRecommend.this.f9686a));
                if (RequestRunCourseRecommend.this.b()) {
                    pez.c().d(RequestRunCourseRecommend.this.f9686a, RequestRunCourseRecommend.this.c, e);
                }
            }
        }
    };

    public enum State {
        ACTIVE,
        INACTIVE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<FitWorkout> e() {
        Map<String, FitWorkout> d = d(this.f);
        if (ggs.e(BaseApplication.getContext())) {
            return e(d, b(this.e, this.j), this.i);
        }
        return e(d, b(this.e, e(d)), this.i);
    }

    private ArrayList<FitWorkout> e(Map<String, FitWorkout> map, List<RunRecommendWorkout> list, List<FitWorkout> list2) {
        FitWorkout fitWorkout;
        ArrayList<FitWorkout> arrayList = new ArrayList<>(list.size());
        if (koq.b(list)) {
            return arrayList;
        }
        d(list, list2);
        Collections.sort(list, new Comparator() { // from class: pfc
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return RequestRunCourseRecommend.a((RunRecommendWorkout) obj, (RunRecommendWorkout) obj2);
            }
        });
        for (RunRecommendWorkout runRecommendWorkout : list) {
            if (runRecommendWorkout != null && (fitWorkout = map.get(runRecommendWorkout.getWorkoutId())) != null) {
                arrayList.add(fitWorkout);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ int a(RunRecommendWorkout runRecommendWorkout, RunRecommendWorkout runRecommendWorkout2) {
        return runRecommendWorkout2.getWeight() - runRecommendWorkout.getWeight();
    }

    private void d(List<RunRecommendWorkout> list, List<FitWorkout> list2) {
        for (FitWorkout fitWorkout : list2) {
            if (fitWorkout != null) {
                for (RunRecommendWorkout runRecommendWorkout : list) {
                    if (runRecommendWorkout != null && runRecommendWorkout.getWorkoutId().equals(fitWorkout.acquireId())) {
                        runRecommendWorkout.setWeight(0);
                    }
                }
            }
        }
    }

    private List<RunRecommendWorkout> b(List<RunRecommendWorkout> list, List<RunRecommendWorkout> list2) {
        ArrayList arrayList = new ArrayList(6);
        arrayList.addAll(list);
        for (RunRecommendWorkout runRecommendWorkout : list2) {
            if (runRecommendWorkout != null) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        RunRecommendWorkout runRecommendWorkout2 = (RunRecommendWorkout) it.next();
                        if (runRecommendWorkout2 != null && runRecommendWorkout2.getWorkoutId().equals(runRecommendWorkout.getWorkoutId())) {
                            runRecommendWorkout2.setWeight(runRecommendWorkout2.getWeight() + 100);
                            break;
                        }
                    } else {
                        arrayList.add(runRecommendWorkout);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    private List<RunRecommendWorkout> e(Map<String, FitWorkout> map) {
        ArrayList arrayList = new ArrayList(3);
        for (FitWorkout fitWorkout : map.values()) {
            if (fitWorkout != null) {
                RunRecommendWorkout runRecommendWorkout = new RunRecommendWorkout();
                runRecommendWorkout.setWeight(1);
                runRecommendWorkout.setWorkoutId(fitWorkout.acquireId());
                arrayList.add(runRecommendWorkout);
                if (arrayList.size() == 3) {
                    break;
                }
            }
        }
        return arrayList;
    }

    private Map<String, FitWorkout> d(List<FitWorkout> list) {
        HashMap hashMap = new HashMap(list.size());
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null && hashMap.get(fitWorkout.acquireId()) == null) {
                hashMap.put(fitWorkout.acquireId(), fitWorkout);
            }
        }
        return hashMap;
    }

    public boolean b() {
        return this.h == State.ACTIVE;
    }
}
