package defpackage;

import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.LogAnonymous;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eev extends JsModuleBase {
    private H5ProJsCbkInvoker<Object> c;
    private Context e;
    private Observer f;
    private static long b = jdl.e(jdl.b(System.currentTimeMillis(), -1), 20, 0);

    /* renamed from: a, reason: collision with root package name */
    private static long f11990a = jdl.e(System.currentTimeMillis(), 20, 0);
    private static int d = 0;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onMount(Context context, H5ProInstance h5ProInstance) {
        super.onMount(context, h5ProInstance);
        this.e = context;
        this.c = h5ProInstance.getJsCbkInvoker();
    }

    @JavascriptInterface
    public void initWebviewHeight(long j, int i) {
        LogUtil.a("SectionH5InteractionUtils", "initWebviewHeight enter");
        ObserverManagerUtil.c("REFRESH_H5_HEIGHT", Integer.valueOf(i));
    }

    @JavascriptInterface
    public void pullUpDialog(final long j, final int i) {
        LogUtil.a("SectionH5InteractionUtils", "pullUpDialog enter");
        HandlerExecutor.a(new Runnable() { // from class: eev.3
            @Override // java.lang.Runnable
            public void run() {
                eev.this.c(j, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final long j, final int i) {
        final JSONObject jSONObject = new JSONObject();
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
        builder.e(b(i)).czE_(e(i), new View.OnClickListener() { // from class: eev.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SectionH5InteractionUtils", "it is positive");
                try {
                    jSONObject.put(TrackConstants$Opers.RESPONSE, "ok");
                    eev.this.c.onSuccess(jSONObject, j);
                    if (i == 3) {
                        mtp.d().d(eev.this.e, "#/DreamTalkSetting");
                    }
                } catch (JSONException e) {
                    LogUtil.b("SectionH5InteractionUtils", "positive button click jsonException", LogAnonymous.b((Throwable) e));
                    eev.this.c.onFailure(-1, LogAnonymous.b((Throwable) e), j);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(a(i), new View.OnClickListener() { // from class: eev.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SectionH5InteractionUtils", "it is negative");
                try {
                    jSONObject.put(TrackConstants$Opers.RESPONSE, "cancel");
                    eev.this.c.onSuccess(jSONObject, j);
                } catch (JSONException e) {
                    LogUtil.b("SectionH5InteractionUtils", "negative button click jsonException", LogAnonymous.b((Throwable) e));
                    eev.this.c.onFailure(-1, LogAnonymous.b((Throwable) e), j);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    @JavascriptInterface
    public void acquireTimeStamp(final long j) throws JSONException {
        LogUtil.a("SectionH5InteractionUtils", "acquireTimeStamp enter");
        final JSONObject jSONObject = new JSONObject();
        if (this.f == null) {
            LogUtil.a("SectionH5InteractionUtils", "timeStampObserver is null");
            jSONObject.put("chart_start_time", b);
            jSONObject.put("chart_end_time", f11990a);
            this.c.onSuccess(jSONObject, j);
            ObserverManagerUtil.e(this.f, "CHART_START_END_TIME");
            Observer observer = new Observer() { // from class: eev.2
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    Object obj;
                    Object obj2;
                    try {
                        LogUtil.a("SectionH5InteractionUtils", "timeStampObserver: " + eev.this.f.toString());
                        if (objArr == null || objArr.length <= 1 || (obj = objArr[0]) == null || (obj2 = objArr[1]) == null || !(obj instanceof Long) || !(obj2 instanceof Long)) {
                            return;
                        }
                        LogUtil.a("SectionH5InteractionUtils", "start end time is valid");
                        long longValue = ((Long) objArr[0]).longValue();
                        long longValue2 = ((Long) objArr[1]).longValue();
                        LogUtil.a("SectionH5InteractionUtils", "startTime ", Long.valueOf(longValue), "endTime ", Long.valueOf(longValue2));
                        jSONObject.put("chart_start_time", longValue);
                        jSONObject.put("chart_end_time", longValue2);
                        eev.this.c.onSuccess(jSONObject, j);
                    } catch (JSONException e) {
                        LogUtil.b("SectionH5InteractionUtils", "acquireTimeStamp jsonException", LogAnonymous.b((Throwable) e));
                        eev.this.c.onFailure(-1, LogAnonymous.b((Throwable) e), j);
                    }
                }
            };
            this.f = observer;
            ObserverManagerUtil.d(observer, "CHART_START_END_TIME");
        }
    }

    public static void a(long j) {
        b = j;
    }

    public static void b(long j) {
        f11990a = j;
    }

    private String b(int i) {
        if (i == 1) {
            return this.e.getResources().getString(R$string.IDS_sleep_snoring_delete_confirm);
        }
        if (i != 2) {
            return i != 3 ? "" : this.e.getResources().getString(R$string.IDS_sleep_keep_audio_file_confirm);
        }
        return this.e.getResources().getString(R$string.IDS_sleep_talk_delete_confirm);
    }

    private String e(int i) {
        if (i == 1) {
            return BaseApplication.e().getString(R$string.IDS_hw_common_ui_dialog_confirm).toUpperCase(Locale.ROOT);
        }
        if (i != 2) {
            return i != 3 ? "" : this.e.getResources().getString(R$string.IDS_sleep_to_close);
        }
        return BaseApplication.e().getString(R$string.IDS_hw_common_ui_dialog_confirm).toUpperCase(Locale.ROOT);
    }

    private static String a(int i) {
        return BaseApplication.e().getString(R$string.IDS_hw_show_cancel);
    }

    @JavascriptInterface
    public static void pageType(int i) {
        d = i;
    }

    public static int e() {
        return d;
    }
}
