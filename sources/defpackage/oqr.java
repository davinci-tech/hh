package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.List;

/* loaded from: classes6.dex */
public class oqr {

    /* renamed from: a, reason: collision with root package name */
    private Handler f15846a = new Handler(Looper.getMainLooper()) { // from class: oqr.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                super.handleMessage(message);
                if (message.what == 1080983 && message.arg1 < 5) {
                    oqr.this.c(message.arg1);
                }
            }
        }
    };
    private BroadcastReceiver c = new BroadcastReceiver() { // from class: oqr.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            if ("com.huawei.plugin.account.login".equals(action)) {
                int c = oqr.c();
                LogUtil.a("Track_InitRunCourseUtils", "ACTION_LOGIN_SUCCESSFUL getRunCourseState:", Integer.valueOf(c));
                if (c == -2 || c == -1) {
                    return;
                }
                if (c >= 0 && c <= 5) {
                    oqr.this.b(c + 1);
                    oqr.this.d(0, 0L);
                }
                if (c > 5) {
                    oqr.this.b(-2);
                    LogUtil.a("Track_InitRunCourseUtils", "Over max try times");
                    return;
                }
                return;
            }
            if ("com.huawei.plugin.account.logout".equals(action)) {
                LogUtil.a("Track_InitRunCourseUtils", "ACTION_LOGOUT_SUCCESSFUL");
                oqr.this.b(0);
            } else {
                LogUtil.a("Track_InitRunCourseUtils", "Unexpected action");
            }
        }
    };

    public void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.c, intentFilter);
    }

    public void e() {
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        if (((PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class)) == null) {
            Services.a("PluginFitnessAdvice", PluginSuggestion.class, com.huawei.haf.application.BaseApplication.e(), new Consumer<PluginSuggestion>() { // from class: oqr.1
                @Override // com.huawei.framework.servicemgr.Consumer
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void accept(PluginSuggestion pluginSuggestion) {
                    if (pluginSuggestion != null) {
                        pluginSuggestion.init(BaseApplication.getContext());
                        oqr.this.c(i);
                    }
                }
            }, true);
        } else {
            d(i);
        }
    }

    private void d(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: oqr.5
            @Override // java.lang.Runnable
            public void run() {
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi == null) {
                    LogUtil.h("Track_InitRunCourseUtils", "tryToGetList : courseApi is null.");
                } else {
                    courseApi.getCourseTopicList(0, new UiCallback<List<Topic>>() { // from class: oqr.5.2
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i2, String str) {
                            LogUtil.a("Track_InitRunCourseUtils", "getCourseTopicList() onfalure ", "errorCode:", Integer.valueOf(i2), "errorinfo", str);
                            oqr.this.d(i, 200L);
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(List<Topic> list) {
                            if (koq.b(list)) {
                                LogUtil.h("Track_InitRunCourseUtils", "initRunCourseTopic data null");
                                oqr.this.d(i, 200L);
                                return;
                            }
                            for (Topic topic : list) {
                                if (topic == null) {
                                    LogUtil.b("Track_InitRunCourseUtils", "onSuccess: topic is null");
                                } else if ("SF006".equals(topic.acquireSerialNum())) {
                                    if (koq.c(topic.acquireWorkoutList())) {
                                        LogUtil.h("Track_InitRunCourseUtils", "initRunCourseTopic exist runcourse");
                                        oqr.this.b(-1);
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, long j) {
        Handler handler = this.f15846a;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(1080983);
            obtainMessage.arg1 = i + 1;
            this.f15846a.sendMessageDelayed(obtainMessage, j);
        }
    }

    public static int c() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "RUN_COURSE_REQUEST_KEY");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.h("Track_InitRunCourseUtils", "getRunCourseState", e.getMessage());
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "RUN_COURSE_REQUEST_KEY", Integer.toString(i), (StorageParams) null);
    }
}
