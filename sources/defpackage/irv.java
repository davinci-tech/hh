package defpackage;

import android.content.Context;
import android.os.SystemClock;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class irv extends MessageObject {

    /* renamed from: a, reason: collision with root package name */
    private TimeUnit f13563a;
    private int b;
    private long c;
    private volatile long d = 0;
    private String e;
    private int g;

    @Override // com.huawei.health.messagecenter.model.MessageObject
    public void setMsgId(String str) {
    }

    public irv(long j, TimeUnit timeUnit) {
        this.c = j;
        this.f13563a = timeUnit;
    }

    public void a() {
        if (d()) {
            MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            LogUtil.a("TimingMessageObject", "message expired, use new MessageID");
            super.setMsgId(messageCenterApi.getMessageId(getModule(), getType()));
        }
        this.d = SystemClock.elapsedRealtime();
    }

    public void e() {
        Context context = BaseApplication.getContext();
        int i = this.g;
        setMsgTitle(i == -1 ? "" : context.getString(i));
        int i2 = this.b;
        setMsgContent(i2 != -1 ? context.getString(i2) : "");
        LogUtil.a("TimingMessageObject", "refresh message locale=", Locale.getDefault().getLanguage(), ", title=[", getMsgTitle(), "], content=[", getMsgContent(), "]");
    }

    private boolean d() {
        return this.d == 0 || SystemClock.elapsedRealtime() - this.d > this.f13563a.toMillis(this.c);
    }

    @Override // com.huawei.health.messagecenter.model.MessageObject
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.huawei.health.messagecenter.model.MessageObject
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), Long.valueOf(this.c), this.f13563a, Long.valueOf(this.d));
    }

    public void c(int i) {
        this.g = i;
    }

    public void e(int i) {
        this.b = i;
    }

    public String c() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
        setDetailUri(str);
    }
}
