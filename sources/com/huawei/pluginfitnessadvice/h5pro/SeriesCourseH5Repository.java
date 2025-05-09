package com.huawei.pluginfitnessadvice.h5pro;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Cbk;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.PurchaseSuccessDialog;
import defpackage.ggg;
import defpackage.gnm;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = "SeriesCourse", users = {"", "", "9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class SeriesCourseH5Repository {
    private static final String COURSE_CATEGORY_KEY = "courseCategoryKey";
    private static final String INTENT_BEHAVIOR_KEY = "intent_behavior_key";
    private static final String MY_FITNESS_COURSE_ACTIVITY_CLASS_NAME = "com.huawei.health.suggestion.ui.fitness.activity.MyFitnessCourseActivity";
    private static final int ONE_TIME_PAY = 2;
    private static final String RELAX = "relax";
    private static final String SPORT = "sport";
    private static final String TAG = "R_SeriesCourseH5Repository";
    private static final String TITLE_NAME = "titleName";

    @H5ProMethod(name = "getCoachGender")
    public static void getCoachGender(SeriesCourseH5Cbk<Integer> seriesCourseH5Cbk) {
        if (seriesCourseH5Cbk == null) {
            LogUtil.h(TAG, "getCoachGender callback == null");
        } else {
            seriesCourseH5Cbk.onSuccess(Integer.valueOf(ggg.a()));
        }
    }

    public static void registerService() {
        H5ProClient.getServiceManager();
        H5ProServiceManager.getInstance().registerService(SeriesCourseH5Repository.class);
    }

    @H5ProMethod(name = "showPaySuccess")
    public static void showPaySuccess(final String str, final SeriesCourseH5Cbk<Integer> seriesCourseH5Cbk) {
        LogUtil.a(TAG, "showPaySuccess");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: mnp
                @Override // java.lang.Runnable
                public final void run() {
                    SeriesCourseH5Repository.showPaySuccess(str, seriesCourseH5Cbk);
                }
            });
            return;
        }
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.b(TAG, "topActivity is null");
            seriesCourseH5Cbk.onFailure(-1, "topActivity is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("finishCount");
            int i2 = jSONObject.getInt("totalCount");
            String string = jSONObject.getString("courseName");
            String string2 = jSONObject.getString("imageUrl");
            String quantityString = wa_.getResources().getQuantityString(R.plurals._2130903319_res_0x7f030117, i, Integer.valueOf(i), Integer.valueOf(i2));
            final String string3 = jSONObject.getString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID);
            final int i3 = jSONObject.getInt("courseBelongType");
            PurchaseSuccessDialog.Builder builder = new PurchaseSuccessDialog.Builder(wa_);
            final CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.b(TAG, "courseApi is null");
            } else {
                collectCourseWhenShowDialog(string3, i3, courseApi);
                builder.b(R$string.IDS_go_check_my_sport_course).b(string).a(string2).e(new UiCallback<Boolean>() { // from class: com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository.3
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i4, String str2) {
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Boolean bool) {
                        LogUtil.a(SeriesCourseH5Repository.TAG, "checkbox status is ", bool);
                        SeriesCourseH5Cbk.this.onSuccess(Integer.valueOf(bool.compareTo((Boolean) false)));
                    }
                }).c(quantityString).czH_(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository.2
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        SeriesCourseH5Repository.collectOrUncollectCourse(z, CourseApi.this, string3, i3);
                        ViewClickInstrumentation.clickOnView(compoundButton);
                    }
                }).czI_(new View.OnClickListener() { // from class: mnq
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SeriesCourseH5Repository.lambda$showPaySuccess$1(SeriesCourseH5Cbk.this, view);
                    }
                }).c().show();
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "buildQuestionResult JSONException");
            seriesCourseH5Cbk.onFailure(-1, "JSONException");
        }
    }

    public static /* synthetic */ void lambda$showPaySuccess$1(SeriesCourseH5Cbk seriesCourseH5Cbk, View view) {
        jumpToAllCourse(SPORT, seriesCourseH5Cbk);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void collectCourseWhenShowDialog(final String str, final int i, final CourseApi courseApi) {
        courseApi.isCourseCollected(str, new UiCallback<Boolean>() { // from class: com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                LogUtil.a(SeriesCourseH5Repository.TAG, "if collected failed");
                CourseApi.this.collectCourse(str, i, null);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Boolean bool) {
                LogUtil.a(SeriesCourseH5Repository.TAG, "if collected:", bool);
                if (bool.booleanValue()) {
                    return;
                }
                CourseApi.this.collectCourse(str, i, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void collectOrUncollectCourse(boolean z, CourseApi courseApi, String str, int i) {
        LogUtil.a(TAG, "ischecked ", Boolean.valueOf(z));
        if (!z) {
            courseApi.uncollectCourse(str, i);
        } else {
            courseApi.collectCourse(str, i, null);
        }
    }

    @H5ProMethod(name = "jumpToAllCourse")
    public static void jumpToAllCourse(String str, SeriesCourseH5Cbk<Integer> seriesCourseH5Cbk) {
        ReleaseLogUtil.e(TAG, "jumpToAllCourse:", str);
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            ReleaseLogUtil.d(TAG, "topActivity is null");
            seriesCourseH5Cbk.onFailure(-1, "topActivity is null");
            return;
        }
        if (!SPORT.equals(str) && !RELAX.equals(str)) {
            ReleaseLogUtil.d(TAG, "wrong course class");
            seriesCourseH5Cbk.onFailure(-1, "wrong course class");
            return;
        }
        String string = wa_.getString(R.string._2130845582_res_0x7f021f8e);
        if (RELAX.equals(str)) {
            string = wa_.getString(R.string._2130842597_res_0x7f0213e5);
        }
        Intent intent = new Intent();
        intent.setClassName(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), MY_FITNESS_COURSE_ACTIVITY_CLASS_NAME);
        intent.putExtra(TITLE_NAME, string);
        intent.putExtra(COURSE_CATEGORY_KEY, 4);
        intent.putExtra(INTENT_BEHAVIOR_KEY, 2);
        gnm.aPB_(wa_, intent);
        overridePendingTransition();
        seriesCourseH5Cbk.onSuccess(0);
    }

    private static void overridePendingTransition() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ instanceof Activity) {
            wa_.overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }
}
