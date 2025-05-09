package com.huawei.health.manager.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes.dex */
public class DelayStartHelper {

    /* renamed from: a, reason: collision with root package name */
    private Context f2804a;
    private ExtendHandler b = null;
    private Intent d;

    public DelayStartHelper(Context context) {
        if (context != null) {
            this.f2804a = context;
            c();
        }
    }

    private void c() {
        this.b = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.health.manager.util.DelayStartHelper.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                LogUtil.a("Step_DelayStartHelper", "DelayStartHelper try it");
                if (message.what != 1001) {
                    return false;
                }
                if (message.arg1 >= 24 || DelayStartHelper.this.f2804a == null) {
                    DelayStartHelper.this.b();
                } else {
                    try {
                        DelayStartHelper.this.f2804a.startService(DelayStartHelper.this.d);
                        DelayStartHelper.this.b();
                    } catch (IllegalStateException e) {
                        LogUtil.h("Step_DelayStartHelper", " IllegalStateException ", e.getMessage());
                        DelayStartHelper.this.d(message.arg1 + 1);
                    }
                }
                return true;
            }
        }, "delayHelper");
    }

    public void alx_(Intent intent) {
        if (!CommonUtil.bh() || intent == null) {
            return;
        }
        this.d = intent;
        d(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.b == null) {
            LogUtil.a("Step_DelayStartHelper", "tryToStartAgain mExtendHandler is null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1001;
        obtain.arg1 = i;
        this.b.sendMessage(obtain, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.removeTasksAndMessages();
            this.b.quit(true);
            this.b = null;
        }
    }
}
