package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.suggestion.FitnessNavigationApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.Services;

@ApiDefine(uri = FitnessNavigationApi.class)
@Singleton
/* loaded from: classes4.dex */
public class fhv implements FitnessNavigationApi {
    private final PluginSuggestion d = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);

    @Override // com.huawei.health.suggestion.FitnessNavigationApi
    public void startMoreFitness(Context context) {
        PluginSuggestion pluginSuggestion = this.d;
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "FITNESS_COURSE");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").zF_(bundle).a(268435456).c(context);
    }

    @Override // com.huawei.health.suggestion.FitnessNavigationApi
    public void gotoFitnessPage(String str, String str2, final Context context) {
        if (!TextUtils.isEmpty(str) && str.startsWith(str2)) {
            Uri parse = Uri.parse(str.replace(str2, ""));
            final String queryParameter = parse.getQueryParameter("id");
            final String queryParameter2 = parse.getQueryParameter("version");
            LogUtil.a("SocialMessageAdapter", "gotoFitnessPage workout is ", queryParameter, " version is ", queryParameter2);
            if (!TextUtils.isEmpty(queryParameter)) {
                Services.b("PluginFitnessAdvice", PluginSuggestion.class, context, new Consumer() { // from class: fhy
                    @Override // com.huawei.framework.servicemgr.Consumer
                    public final void accept(Object obj) {
                        fhv.this.d(queryParameter, queryParameter2, context, (PluginSuggestion) obj);
                    }
                });
                return;
            }
        }
        startMoreFitness(context);
    }

    /* synthetic */ void d(String str, String str2, final Context context, PluginSuggestion pluginSuggestion) {
        pluginSuggestion.getFitWorkout(str, str2, new UiCallback<FitWorkout>() { // from class: fhv.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str3) {
                LogUtil.h("SocialMessageAdapter", "goFitnessPage onFailure " + i);
                fhv.this.startMoreFitness(context);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(FitWorkout fitWorkout) {
                if (fitWorkout != null) {
                    fhv.this.e(fitWorkout, context);
                } else {
                    LogUtil.h("SocialMessageAdapter", "goFitnessPage onSuccess data is null ");
                    fhv.this.startMoreFitness(context);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(FitWorkout fitWorkout, Context context) {
        PluginSuggestion pluginSuggestion = this.d;
        if (pluginSuggestion == null || !pluginSuggestion.isInitComplete()) {
            return;
        }
        if (fitWorkout == null) {
            LogUtil.h("SocialMessageAdapter", "start fitness failed with the null fitWorkout");
        } else {
            gge.d((String) null);
            mod.d(context, fitWorkout);
        }
    }
}
