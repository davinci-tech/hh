package com.huawei.phoneservice.feedback.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.feedback.mvp.base.b;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackInfo;
import com.huawei.phoneservice.feedbackcommon.entity.FeedbackZipBean;
import com.huawei.phoneservice.feedbackcommon.utils.CancelInterface;
import com.huawei.phoneservice.feedbackcommon.utils.NotifyUploadFileListener;
import com.huawei.phoneservice.feedbackcommon.utils.NotifyUploadZipListener;
import com.huawei.phoneservice.feedbackcommon.utils.SubmitListener;
import com.huawei.phoneservice.feedbackcommon.utils.ZipCompressListener;
import com.huawei.phoneservice.feedbackcommon.utils.ZipUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class c<V extends b> extends a implements ZipCompressListener, NotifyUploadZipListener, NotifyUploadFileListener, SubmitListener {
    public String b;
    protected long c;
    public int d;
    public boolean e;
    public boolean f;
    public int g;
    public int h;
    public Handler i;
    public Context j;
    public Activity k;
    public volatile int l;
    public String m;
    protected List<FeedbackZipBean> n;
    public boolean o;
    protected V p;
    public CancelInterface q;

    protected abstract void a(boolean z);

    @Override // com.huawei.phoneservice.feedbackcommon.utils.ZipCompressListener
    public void zipCompressFinished(int i, String str, String str2) {
        V v;
        com.huawei.phoneservice.feedback.utils.a aVar;
        this.d = i;
        this.p.b().setFlag(this.d);
        if (i == 2) {
            ZipUtil.setSize(0L);
            ZipUtil.getSize(new File(str).listFiles());
            this.p.b().setLogsSize(ZipUtil.getSize());
            this.p.b().setZipFileName(str2);
            if (0 != this.c) {
                Handler handler = this.i;
                if (handler != null) {
                    handler.sendEmptyMessage(100);
                    return;
                }
                return;
            }
            v = this.p;
            aVar = com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_SUCCESS;
        } else {
            this.p.b().setLogsSize(0L);
            if (0 != this.c) {
                a(false);
                return;
            } else {
                v = this.p;
                aVar = com.huawei.phoneservice.feedback.utils.a.ZIP_COMPRESS_FAILED;
            }
        }
        v.a(aVar);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.NotifyUploadFileListener
    public void uploadNotify(int i, int i2, String str) {
        String a2 = a(i2, str);
        Handler handler = this.i;
        if (handler == null || a2 == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(i, a2));
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.SubmitListener
    public void submitNotify(int i, int i2, String str) {
        String a2 = a(i2, str);
        Handler handler = this.i;
        if (handler == null || a2 == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(i, a2));
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.NotifyUploadZipListener
    public void notifyUploadAgain(long j) {
        com.huawei.phoneservice.feedbackcommon.utils.b.e().zipCompressAgain(this.j, j, this);
    }

    @Override // com.huawei.phoneservice.feedbackcommon.utils.NotifyUploadZipListener
    public void notifyUpload(List<FeedbackZipBean> list, boolean z) {
        this.n = list;
        a(z);
    }

    public void a(List<String> list) {
        FeedbackInfo b;
        if (this.l != 0) {
            if (this.l == 1) {
                this.l = 2;
            }
        } else {
            if (this.f || (b = this.p.b()) == null) {
                return;
            }
            this.q = com.huawei.phoneservice.feedbackcommon.utils.b.e().submitWithCancel(this.j, this.o, list, this.n, b, this);
        }
    }

    public void a(String str, long j, String str2) {
        this.q = com.huawei.phoneservice.feedbackcommon.utils.b.e().uploadZipWithCancel(this.j, str, j, str2, this);
    }

    public void a(Context context, String str, Callback callback) {
        com.huawei.phoneservice.feedbackcommon.utils.a aVar = com.huawei.phoneservice.feedbackcommon.utils.a.f8341a;
        String sdk = j.c().getSdk("country");
        String sdk2 = j.c().getSdk(FaqConstants.FAQ_LANGUAGE);
        String sdk3 = j.c().getSdk("language");
        if (TextUtils.isEmpty(str)) {
            str = j.c().getSdk("channel");
        }
        aVar.callService(context, sdk, sdk2, sdk3, str, callback);
    }

    private String a(int i, String str) {
        Context context;
        int i2;
        if (i == -1) {
            return str;
        }
        if (i == 401) {
            context = this.j;
            i2 = R.string._2130850887_res_0x7f023447;
        } else if (i == 405) {
            context = this.j;
            i2 = R.string._2130850923_res_0x7f02346b;
        } else if (i == 1) {
            context = this.j;
            i2 = R.string._2130850861_res_0x7f02342d;
        } else if (i == 2) {
            context = this.j;
            i2 = R.string._2130850883_res_0x7f023443;
        } else {
            if (i != 3) {
                return null;
            }
            context = this.j;
            i2 = R.string._2130850880_res_0x7f023440;
        }
        return context.getString(i2);
    }

    public c(V v) {
        super(v);
        this.c = 0L;
        this.e = false;
        this.f = false;
        this.g = 0;
        this.h = 0;
        this.l = 0;
        this.n = new ArrayList(20);
        this.o = false;
        this.p = v;
    }
}
