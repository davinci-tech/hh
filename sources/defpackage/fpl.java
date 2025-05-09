package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class fpl {
    private CommonDialog21 d = null;

    public void aCJ_(Activity activity, String str, int i, FitWorkout fitWorkout) {
        if (activity == null || TextUtils.isEmpty(str) || fitWorkout == null) {
            LogUtil.h("Suggestion_TrailDetailSharedHelper", "activity is null or sharedTitle is null or fitWorkout is null");
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        gge.e("1130059", hashMap);
        fdu fduVar = new fdu(2);
        fduVar.b(0);
        HashMap hashMap2 = new HashMap(10);
        hashMap2.put("click", 1);
        hashMap2.put(Constants.BI_MODULE_ID, "1140001119");
        hashMap2.put("name", str);
        hashMap2.put("resourceType", Integer.valueOf(fitWorkout.acquireCommodityFlag()));
        fduVar.b((Map<String, Object>) hashMap2);
        fduVar.b(AnalyticsValue.SHARE_1140001.value());
        fduVar.c(str);
        fduVar.a(a(fitWorkout.acquireUsers()));
        String topicPreviewPicUrl = fitWorkout.getTopicPreviewPicUrl();
        aCI_(activity);
        if (!TextUtils.isEmpty(topicPreviewPicUrl)) {
            new b(topicPreviewPicUrl, fduVar).execute(new String[0]);
        } else {
            fduVar.awp_(aCF_());
        }
        aCG_(activity, i, fitWorkout, fduVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void c(fdu fduVar) {
        if (TextUtils.isEmpty(fduVar.y())) {
            LogUtil.h("Suggestion_TrailDetailSharedHelper", "doShared, shareContent.getShareUrlContent() is empty");
        } else {
            LogUtil.a("Suggestion_TrailDetailSharedHelper", "doShared, shareContent.getShareUrlContent() = ", fduVar.y());
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, BaseApplication.e());
        }
    }

    private void aCG_(final Activity activity, final int i, final FitWorkout fitWorkout, final fdu fduVar) {
        final GrsQueryCallback grsQueryCallback = new GrsQueryCallback() { // from class: fpl.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                fpl.this.aCH_(str, activity, i, fitWorkout, fduVar);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                fpl.this.aCH_("", activity, i, fitWorkout, fduVar);
            }
        };
        asc.e().b(new Runnable() { // from class: fpp
            @Override // java.lang.Runnable
            public final void run() {
                GRSManager.a(BaseApplication.e()).e("domainContentcenterDbankcdnNew", GrsQueryCallback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aCH_(String str, Activity activity, int i, FitWorkout fitWorkout, final fdu fduVar) {
        if (fduVar.awm_() != null) {
            activity.runOnUiThread(new Runnable() { // from class: fpj
                @Override // java.lang.Runnable
                public final void run() {
                    fpl.this.e();
                }
            });
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_TrailDetailSharedHelper", "getSharedLink, url is empty");
            activity.runOnUiThread(new Runnable() { // from class: fpi
                @Override // java.lang.Runnable
                public final void run() {
                    nrh.b(BaseApplication.e(), R.string._2130842310_res_0x7f0212c6);
                }
            });
            return;
        }
        LogUtil.a("Suggestion_TrailDetailSharedHelper", "getSharedLink, success url = ", str);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (CommonUtil.cc() || CommonUtil.bc()) {
            sb.append(Constants.H5_URL_BASE_PATH_TEST);
        } else if (CommonUtil.as() || CommonUtil.aq()) {
            sb.append(Constants.H5_URL_BASE_PATH_BETA);
        } else {
            sb.append(Constants.H5_URL_BASE_PATH_RELEASE);
        }
        sb.append("com.huawei.health.h5.course/static/dist/index.html#/FitnessCourceDetail?workoutId=");
        sb.append(fitWorkout.acquireId());
        sb.append("&sex=");
        sb.append(i);
        sb.append("&isH5=true&version=");
        sb.append(fitWorkout.accquireVersion());
        fduVar.f(sb.toString());
        if (fduVar.awm_() != null) {
            activity.runOnUiThread(new Runnable() { // from class: fpk
                @Override // java.lang.Runnable
                public final void run() {
                    fpl.this.c(fduVar);
                }
            });
        }
    }

    private String a(int i) {
        double a2;
        double d;
        LogUtil.a("Suggestion_TrailDetailSharedHelper", "getShareTextContent, exerciseTimes = ", Integer.valueOf(i));
        double d2 = i;
        if (d2 > 10000.0d) {
            int i2 = 0;
            if (!LanguageUtil.p(BaseApplication.e()) && LanguageUtil.al(BaseApplication.e())) {
                double d3 = d2 / 10000.0d;
                d = UnitUtil.a(d3, 1);
                a2 = UnitUtil.a(d3, 0);
                if (Double.doubleToLongBits(d) != Double.doubleToLongBits(a2)) {
                    i2 = 1;
                    return BaseApplication.e().getResources().getQuantityString(R.plurals._2130903482_res_0x7f0301ba, i, UnitUtil.e(d, 1, i2));
                }
            } else {
                a2 = UnitUtil.a(d2 / 1000.0d, 0);
            }
            d = a2;
            return BaseApplication.e().getResources().getQuantityString(R.plurals._2130903482_res_0x7f0301ba, i, UnitUtil.e(d, 1, i2));
        }
        return BaseApplication.e().getString(R.string._2130848705_res_0x7f022bc1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap aCF_() {
        Drawable loadIcon = BaseApplication.e().getApplicationInfo().loadIcon(BaseApplication.e().getPackageManager());
        if (!(loadIcon instanceof BitmapDrawable)) {
            LogUtil.h("Suggestion_TrailDetailSharedHelper", "getAppIcon, drawable is not BitmapDrawable");
            return null;
        }
        LogUtil.a("Suggestion_TrailDetailSharedHelper", "getAppIcon, app icon");
        return ((BitmapDrawable) loadIcon).getBitmap();
    }

    private void aCI_(Activity activity) {
        LogUtil.a("Suggestion_TrailDetailSharedHelper", "showWaitingDialog: mLoadDataDialog = ", this.d);
        if (this.d != null) {
            return;
        }
        new CommonDialog21(activity, R.style.common_dialog21);
        CommonDialog21 a2 = CommonDialog21.a(activity);
        this.d = a2;
        a2.e(activity.getString(R.string._2130841415_res_0x7f020f47));
        this.d.setCancelable(false);
        this.d.a();
        this.d.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e() {
        LogUtil.a("Suggestion_TrailDetailSharedHelper", "dismissWaitingDialog, mLoadDataDialog = ", this.d);
        CommonDialog21 commonDialog21 = this.d;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.d = null;
        }
    }

    class b extends AsyncTask<String, Void, Bitmap> {
        private final String b;
        private final fdu e;

        public b(String str, fdu fduVar) {
            this.b = str;
            this.e = fduVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: aCK_, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(String... strArr) {
            return jei.bGs_(this.b);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: aCL_, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            LogUtil.a("Suggestion_TrailDetailSharedHelper", "onPostExecute，down bitmap success");
            fdu fduVar = this.e;
            if (fduVar == null) {
                LogUtil.h("Suggestion_TrailDetailSharedHelper", "down bitmap, mSharedContent is null");
                return;
            }
            if (bitmap == null) {
                bitmap = fpl.this.aCF_();
            }
            fduVar.awp_(bitmap);
            if (TextUtils.isEmpty(this.e.y())) {
                return;
            }
            fpl.this.e();
            fpl.this.c(this.e);
        }
    }
}
