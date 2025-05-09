package defpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwidauth.c.j;
import com.huawei.hwidauth.c.k;
import com.huawei.hwidauth.utils.a;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class kqx extends AsyncTask<k, Void, Response<ResponseBody>> {

    /* renamed from: a, reason: collision with root package name */
    private k f14553a;
    private String b;
    private j c;
    private Context d;
    private Handler e = new Handler(Looper.getMainLooper()) { // from class: kqx.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            int i = message.what;
            if (i == -2) {
                kqx.this.c.onFailure(message.arg1, (String) message.obj);
            } else if (i == -1) {
                kqx.this.c.onFailure(-1, "");
            } else if (i == 200) {
                kqx.this.c.onSuccess((String) message.obj);
            }
            super.handleMessage(message);
        }
    };

    kqx(Context context, String str, k kVar, j jVar) {
        this.d = context;
        this.b = str;
        this.f14553a = kVar;
        this.c = jVar;
    }

    public Map<String, String> d() {
        HashMap hashMap = new HashMap();
        String b = kti.b();
        if (TextUtils.isEmpty(b)) {
            b = "unkown";
        }
        hashMap.put("terminal-type", b);
        ksy.b("GwRequest", "terminal-type:" + b, false);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Response<ResponseBody> doInBackground(k... kVarArr) {
        ksy.b("GwRequest", "doInBackground", true);
        Map<String, String> d = kqe.e().d(this.d, this.b, "com.huawei.cloud.hwid");
        if (d != null) {
            String str = d.get("GwSilentCodeUrl");
            ksy.b("GwRequest", "gwSilentCodeUrl = " + str, false);
            if (TextUtils.isEmpty(str)) {
                ksy.b("GwRequest", "gwSilentCodeUrl null return", true);
                return null;
            }
            RestClient c = ksv.c(this.d, str);
            if (c == null) {
                ksy.c("GwRequest", "restClient init failed", true);
                return null;
            }
            a aVar = (a) c.create(a.class);
            ksy.b("GwRequest", "url = " + str + this.f14553a.b(), false);
            try {
                String a_ = this.f14553a.a_();
                ksy.b("GwRequest", "requestData = " + a_, false);
                return aVar.a(this.f14553a.b(), RequestBody.create(RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED, a_.getBytes("UTF-8")), d()).execute();
            } catch (IOException unused) {
                ksy.c("GwRequest", "IOException", true);
                return null;
            } catch (RuntimeException unused2) {
                ksy.c("GwRequest", "IOException", true);
                this.c.onFailure(-1, "");
                return null;
            }
        }
        ksy.b("GwRequest", "grs urlMap null", true);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(final Response<ResponseBody> response) {
        krs.a().execute(new Runnable() { // from class: kqx.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Response response2 = response;
                    if (response2 == null) {
                        ksy.b("GwRequest", "response null", true);
                        kqx.this.e.sendEmptyMessage(-1);
                    } else {
                        int code = response2.getCode();
                        if (200 == code && response.getBody() != null) {
                            String str = new String(((ResponseBody) response.getBody()).bytes(), "UTF-8");
                            Message obtain = Message.obtain();
                            obtain.what = 200;
                            obtain.obj = str;
                            kqx.this.e.sendMessage(obtain);
                        } else {
                            String str2 = new String(response.getErrorBody(), "UTF-8");
                            ksy.b("GwRequest", "errorData = ".concat(str2), false);
                            Message obtain2 = Message.obtain();
                            obtain2.what = -2;
                            obtain2.obj = str2;
                            obtain2.arg1 = code;
                            kqx.this.e.sendMessage(obtain2);
                        }
                    }
                } catch (IOException unused) {
                    ksy.c("GwRequest", "IOException", true);
                    kqx.this.e.sendEmptyMessage(-1);
                } catch (RuntimeException unused2) {
                    ksy.c("GwRequest", "RuntimeException", true);
                    kqx.this.e.sendEmptyMessage(-1);
                }
            }
        });
    }
}
