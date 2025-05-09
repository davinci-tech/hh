package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.fitness.activity.ActionTrainModeActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class fnj {

    /* renamed from: a, reason: collision with root package name */
    private final Context f12572a;
    private final String b;
    private final Map<String, Object> c;
    private AtomicAction d;
    private String e;
    private boolean f = false;
    private HealthTextView h;
    private View i;

    public fnj(Context context, String str, Map<String, Object> map) {
        this.f12572a = context;
        this.b = str;
        this.c = map;
        ReleaseLogUtil.e("Suggestion_ActionModeBottomViewHelper", "ActionModeViewHelper mActionId:", str);
    }

    public boolean b() {
        return this.f;
    }

    public void aBq_(View view) {
        this.i = view;
        this.h = (HealthTextView) view.findViewById(R.id.train_count);
        ((HealthButton) this.i.findViewById(R.id.start_ai_train)).setOnClickListener(nkx.cwY_(new View.OnClickListener() { // from class: fnn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                fnj.this.aBr_(view2);
            }
        }, this.f12572a, true, ""));
    }

    /* synthetic */ void aBr_(View view) {
        this.f = true;
        Bundle bundle = new Bundle();
        bundle.putString("actionInfo", nrv.a(this.d));
        bundle.putString("describe", this.e);
        bundle.putSerializable("biIntent", (Serializable) this.c);
        ggr.e(1, this.d, this.c);
        Context context = this.f12572a;
        if (context instanceof Activity) {
            Intent intent = new Intent(this.f12572a, (Class<?>) ActionTrainModeActivity.class);
            intent.putExtra("intentData", bundle);
            ((Activity) context).startActivityForResult(intent, 10001);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(AtomicAction atomicAction) {
        if (atomicAction == null) {
            LogUtil.h("Suggestion_ActionModeBottomViewHelper", "showAiActionView atomicAction == null");
            return;
        }
        if (c(atomicAction.getExtendPropertyInt("aiFlag"), atomicAction.getExtendPropertyInt("aiMeasurement"))) {
            this.d = atomicAction;
            this.i.setVisibility(0);
            this.h.setText(ffy.b(R.plurals._2130903477_res_0x7f0301b5, atomicAction.getExtendPropertyInt("completeTimes"), Integer.valueOf(atomicAction.getExtendPropertyInt("completeTimes"))));
            ggr.e(0, atomicAction, this.c);
            ThreadPoolManager.d().execute(new Runnable() { // from class: fnl
                @Override // java.lang.Runnable
                public final void run() {
                    fnj.this.d();
                }
            });
            return;
        }
        this.i.setVisibility(8);
    }

    public static boolean c(int i, int i2) {
        int modelType = mwt.d().getModelType();
        ReleaseLogUtil.d("Suggestion_ActionModeBottomViewHelper", "isShowAiTrain aiFlag ", Integer.valueOf(i), "aiMeasurement:", Integer.valueOf(i2), "modelType:", Integer.valueOf(modelType));
        return (i != 1 || kxe.b(i2) == -1 || modelType == -1) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (TextUtils.isEmpty(this.b)) {
            LogUtil.h("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate mActionId isEmpty");
        } else {
            ReleaseLogUtil.e("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate mActionId:", this.b);
            ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).getFitnessActionTemplate(this.b, new b(this));
        }
    }

    public static class b extends UiCallback<mms> {
        WeakReference<fnj> c;

        b(fnj fnjVar) {
            this.c = new WeakReference<>(fnjVar);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate onFailure ", Integer.valueOf(i), "errorInfo:", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(mms mmsVar) {
            if (mmsVar == null) {
                ReleaseLogUtil.d("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate date == null");
                return;
            }
            List<mmq> e = mmsVar.e();
            if (koq.b(e) || e.get(0) == null) {
                ReleaseLogUtil.d("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate aiConfigInfo == null");
                return;
            }
            fnj fnjVar = this.c.get();
            if (fnjVar != null) {
                fnjVar.e = e.get(0).e();
            } else {
                LogUtil.h("Suggestion_ActionModeBottomViewHelper", "FitnessActionTemplateCallback viewHelper == null");
            }
        }
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_ActionModeBottomViewHelper", "getAlgorithmTowards describe isEmpty true");
            return -1;
        }
        try {
            JSONObject jSONObject = new JSONArray(new JSONObject(str).optString("models")).getJSONObject(0);
            if (jSONObject == null) {
                LogUtil.h("Suggestion_ActionModeBottomViewHelper", "getAlgorithmTowards factorsJson == null");
                return -1;
            }
            int optInt = new JSONObject(jSONObject.optString("factors")).optInt("towards");
            ReleaseLogUtil.e("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate towards：", Integer.valueOf(optInt));
            return optInt;
        } catch (JSONException e) {
            ReleaseLogUtil.c("Suggestion_ActionModeBottomViewHelper", "requireFitnessActionTemplate:", LogAnonymous.b((Throwable) e));
            return -1;
        }
    }
}
