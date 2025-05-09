package com.huawei.appgallery.marketinstallerservice.b.a.b;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.RequestBean;
import com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.ResponseBean;
import defpackage.agr;
import defpackage.agx;
import defpackage.aha;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class b extends AsyncTask<RequestBean, Void, ResponseBean> {

    /* renamed from: a, reason: collision with root package name */
    protected a f1878a;
    protected agx b = null;
    protected com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b c;
    protected RequestBean d;

    public interface a {
        void a(b bVar);

        void b(b bVar);
    }

    public void e(ResponseBean responseBean) {
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        a aVar = this.f1878a;
        if (aVar != null) {
            aVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(ResponseBean responseBean) {
        a aVar = this.f1878a;
        if (aVar != null) {
            aVar.a(this);
        } else {
            a(responseBean);
        }
    }

    public final void e(Executor executor) {
        executeOnExecutor(executor, this.d);
    }

    protected void a(ResponseBean responseBean) {
        ResponseBean.a aVar;
        if (isCancelled() || this.c == null) {
            return;
        }
        if (responseBean == null) {
            agr.e("StoreAgent", "notifyResult, response is null");
            responseBean = this.d.getResponseBean();
            if (responseBean == null) {
                responseBean = new ResponseBean();
                aVar = ResponseBean.a.PARAM_ERROR;
            } else {
                aVar = ResponseBean.a.UNKNOWN_EXCEPTION;
            }
            responseBean.setErrCause(aVar);
            responseBean.setResponseCode(1);
        }
        this.c.a(this.d, responseBean);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public ResponseBean doInBackground(RequestBean... requestBeanArr) {
        ResponseBean c = c(this.d.getContext());
        com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b bVar = this.c;
        if (bVar != null) {
            bVar.b(this.d, c);
        }
        return c;
    }

    public final ResponseBean c(Context context) {
        ResponseBean b = b(context);
        e(b);
        return b;
    }

    private ResponseBean b(ResponseBean responseBean) {
        if (responseBean != null) {
            return responseBean;
        }
        ResponseBean responseBean2 = new ResponseBean();
        responseBean2.setResponseCode(5);
        responseBean2.setErrCause(ResponseBean.a.PARAM_ERROR);
        return responseBean2;
    }

    private ResponseBean b(Context context) {
        ResponseBean.a aVar;
        ResponseBean.a aVar2;
        int i = 5;
        ResponseBean responseBean = null;
        try {
            responseBean = this.d.getResponseBean();
        } catch (FileNotFoundException unused) {
            aVar = ResponseBean.a.RESPONSE_EXCEPTION;
            i = 7;
            c(null, i, aVar);
            return b(responseBean);
        } catch (ConnectException unused2) {
            aVar = ResponseBean.a.CONNECT_EXCEPTION;
            i = 1;
            c(null, i, aVar);
            return b(responseBean);
        } catch (SocketTimeoutException | ConnectTimeoutException unused3) {
            aVar = ResponseBean.a.CONNECT_EXCEPTION;
            i = 2;
            c(null, i, aVar);
            return b(responseBean);
        } catch (IOException unused4) {
            aVar = ResponseBean.a.IO_EXCEPTION;
            i = 1;
            c(null, i, aVar);
            return b(responseBean);
        } catch (IllegalArgumentException unused5) {
            aVar = ResponseBean.a.PARAM_ERROR;
            c(null, i, aVar);
            return b(responseBean);
        } catch (Throwable unused6) {
            aVar = ResponseBean.a.UNKNOWN_EXCEPTION;
            i = 1;
            c(null, i, aVar);
            return b(responseBean);
        }
        if (aha.d(context)) {
            String serviceUrl = this.d.getServiceUrl();
            if (!TextUtils.isEmpty(serviceUrl)) {
                e(context, responseBean, serviceUrl);
                return b(responseBean);
            }
            responseBean.setResponseCode(5);
            aVar2 = ResponseBean.a.PARAM_ERROR;
        } else {
            responseBean.setResponseCode(3);
            aVar2 = ResponseBean.a.NO_NETWORK;
        }
        responseBean.setErrCause(aVar2);
        return b(responseBean);
    }

    private void e(String str, ResponseBean responseBean) {
        StringBuilder sb;
        String message;
        try {
            responseBean.fromJson(new JSONObject(str));
            responseBean.setResponseCode(0);
        } catch (ClassNotFoundException e) {
            sb = new StringBuilder("parse json error ClassNotFoundException, json");
            message = e.getMessage();
            sb.append(message);
            agr.e("StoreAgent", sb.toString());
        } catch (IllegalAccessException e2) {
            sb = new StringBuilder("parse json error IllegalAccessException, json");
            message = e2.getMessage();
            sb.append(message);
            agr.e("StoreAgent", sb.toString());
        } catch (IllegalArgumentException e3) {
            sb = new StringBuilder("parse json error IllegalArgumentException, json");
            message = e3.getMessage();
            sb.append(message);
            agr.e("StoreAgent", sb.toString());
        } catch (InstantiationException e4) {
            sb = new StringBuilder("parse json error InstantiationException, json");
            message = e4.getMessage();
            sb.append(message);
            agr.e("StoreAgent", sb.toString());
        } catch (JSONException e5) {
            sb = new StringBuilder("parse json error JSONException, json");
            message = e5.getMessage();
            sb.append(message);
            agr.e("StoreAgent", sb.toString());
        }
    }

    private void c(ResponseBean responseBean, int i, ResponseBean.a aVar) {
        if (responseBean != null) {
            responseBean.setResponseCode(i);
            responseBean.setErrCause(aVar);
        }
        agr.e("StoreAgent", "invoke Store error method:" + this.d.getMethod());
    }

    private void e(Context context, ResponseBean responseBean, String str) {
        String genBody = this.d.genBody();
        agr.a("StoreAgent", "callStore, method:" + this.d.getMethod() + ", url:" + str);
        agx agxVar = new agx();
        this.b = agxVar;
        String str2 = new String(agxVar.c(context, str, genBody, "UTF-8"), "UTF-8");
        if (com.huawei.appgallery.marketinstallerservice.b.b.b.b.b(str2)) {
            agr.c("StoreAgent", "getServerData success");
            e(str2, responseBean);
        } else {
            agr.e("StoreAgent", "resData error,res==null or res is not JSONString!");
            responseBean.setResponseCode(1);
            responseBean.setErrCause(ResponseBean.a.JSON_ERROR);
        }
    }

    public b(RequestBean requestBean, com.huawei.appgallery.marketinstallerservice.internal.framework.storekit.bean.b bVar) {
        this.d = requestBean;
        this.c = bVar;
    }
}
