package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.userlabelmgr.model.UpdateUserLabel;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dxk {
    private static dxk c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f11884a = BaseApplication.getContext();
    private UpdateUserLabel e;

    private dxk() {
    }

    public static dxk d() {
        dxk dxkVar;
        synchronized (d) {
            if (c == null) {
                c = new dxk();
            }
            dxkVar = c;
        }
        return dxkVar;
    }

    public void b() {
        LogUtil.a("ActivitiesUserLabelHelper", "enter registerCallback()");
        if (this.e != null) {
            LogUtil.a("ActivitiesUserLabelHelper", "registerCallback(), mUpdateUserLabel != null");
        } else {
            this.e = new UpdateUserLabel() { // from class: dxk.4
                @Override // com.huawei.health.userlabelmgr.model.UpdateUserLabel
                public void onUpdate() {
                    LogUtil.a("ActivitiesUserLabelHelper", "registerCallback(), onUpdate()");
                    dxk.this.f();
                }
            };
            dxw.a(this.f11884a).b(this.e);
        }
    }

    public void e() {
        LogUtil.a("ActivitiesUserLabelHelper", "enter unRegisterCallback()");
        if (this.e == null) {
            LogUtil.a("ActivitiesUserLabelHelper", "unRegisterCallback(), mUpdateUserLabel is null");
        } else {
            dxw.a(this.f11884a).b(this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        a();
        c();
    }

    private void a() {
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.h("ActivitiesUserLabelHelper", "doActivityLabel operationInteractorsApi is null");
        } else {
            operationInteractorsApi.getOperationList(this.f11884a, 1, null, new HttpResCallback() { // from class: dxk.1
                @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                public void onFinished(int i, String str) {
                    LogUtil.a("ActivitiesUserLabelHelper", "getActivities resCode = ", Integer.valueOf(i));
                    LogUtil.c("ActivitiesUserLabelHelper", "getActivities,", str);
                    if (i != 200) {
                        return;
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString("currentTime");
                        LogUtil.a("ActivitiesUserLabelHelper", "getActivities operationCurrentTime,", optString);
                        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(optString).getTime();
                        JSONArray jSONArray = jSONObject.getJSONArray("activities");
                        long j = 0;
                        int i2 = 0;
                        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                            long j2 = jSONArray.getJSONObject(i3).getLong("attendDate");
                            if (j2 >= time - 7776000000L) {
                                i2++;
                            }
                            if (j2 > j) {
                                j = j2;
                            }
                        }
                        dxo o = dxo.o();
                        if (o != null) {
                            o.e(i2, j, time);
                        }
                    } catch (ParseException unused) {
                        LogUtil.b("ActivitiesUserLabelHelper", "ParseException:");
                    } catch (JSONException e) {
                        LogUtil.b("ActivitiesUserLabelHelper", "Json data error! JSONException:", e.getMessage());
                    }
                }
            });
        }
    }

    private void c() {
        String b = SharedPreferenceManager.b(this.f11884a, Integer.toString(10015), "key_ui_login_last_time");
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split(",");
            if (split.length > 1) {
                try {
                    currentTimeMillis = Long.parseLong(split[0]);
                } catch (NumberFormatException unused) {
                    LogUtil.b("ActivitiesUserLabelHelper", "NumberFormatException");
                }
            }
        }
        dxo.o().c(currentTimeMillis);
    }
}
