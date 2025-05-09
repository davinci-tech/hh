package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dynamicchart.DynamicChart;

/* loaded from: classes6.dex */
public class nma extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private nmg f15380a;
    private nmb b;
    private nmc c;
    private nly d;
    private Bitmap e;
    private nlz f;
    private HandlerThread g;
    private int h;
    private int i;
    private SurfaceView j;
    private int k;
    private int m;

    private nma(Looper looper, HandlerThread handlerThread) {
        super(looper);
        this.h = -1;
        this.e = null;
        this.m = 40;
        this.k = HeartRateThresholdConfig.HEART_RATE_LIMIT;
        this.i = -16777216;
        this.g = handlerThread;
    }

    public static nma a() {
        HandlerThread handlerThread = new HandlerThread("DynamicChartHandler");
        handlerThread.start();
        return new nma(handlerThread.getLooper(), handlerThread);
    }

    public void b(Context context, DynamicChart dynamicChart) {
        this.j = dynamicChart;
    }

    public void d() {
        this.g.quit();
    }

    public void a(int i, int i2) {
        this.m = i;
        this.k = i2;
    }

    public void b(int i) {
        this.i = i;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            int intValue = ((Integer) message.obj).intValue();
            LogUtil.a("HiH_DynamicChartHandler", "MSG_SET_POINTS_COUNT_IN_ONESCREEN,oneScreenPointsCount = ", Integer.valueOf(intValue));
            c(intValue);
        } else if (i == 2) {
            LogUtil.a("HiH_DynamicChartHandler", "MSG_RESIZE_SURFACE_VIEW");
            e(message.arg1, message.arg2);
        } else if (i == 3) {
            LogUtil.a("HiH_DynamicChartHandler", "MSG_PUSH_NEW_DATA");
            d(((Float) message.obj).floatValue());
        } else {
            if (i == 4) {
                LogUtil.a("HiH_DynamicChartHandler", "MSG_SET_ORDINATE_Y");
                int[] iArr = (int[]) message.obj;
                a(iArr[0], iArr[1]);
                return;
            }
            super.handleMessage(message);
        }
    }

    private void c(int i) {
        this.h = i;
    }

    private void e(int i, int i2) {
        LogUtil.a("HiH_DynamicChartHandler", "handleResizeView");
        if (this.e != null) {
            LogUtil.c("HiH_DynamicChartHandler", "throw new RuntimeException");
        }
        if (this.h == -1) {
            LogUtil.c("HiH_DynamicChartHandler", "throw new RuntimeException");
        }
        this.e = Bitmap.createBitmap((int) Math.floor((i / (this.h - 1)) * 500.0f), i2, Bitmap.Config.ARGB_8888);
        new Canvas(this.e).drawColor(0);
        this.f15380a = new nmf(i, i2).c((int) nme.a(16.0f), (int) nme.a(6.0f), (int) nme.a(16.0f), (int) nme.a(6.0f)).d();
        nly nlyVar = new nly();
        this.d = nlyVar;
        nlyVar.d(0.0f, this.h - 1, this.m, this.k);
        this.d.d(this.m, this.k);
        RectF cAl_ = this.f15380a.cAl_();
        this.d.c(cAl_.left, cAl_.right, cAl_.top, cAl_.bottom);
        this.d.c();
        nmc nmcVar = new nmc(this.h, 500);
        this.c = nmcVar;
        nmcVar.a(this.m, this.k);
        this.f = new nlz(this.f15380a);
        nmb nmbVar = new nmb(this.f15380a, this.d, this.c);
        this.b = nmbVar;
        nmbVar.e(this.m, this.k);
        this.b.d(this.i);
        d(0.0f);
    }

    private void d(float f) {
        SurfaceHolder holder;
        Canvas lockCanvas;
        try {
            SurfaceView surfaceView = this.j;
            if (surfaceView == null || (lockCanvas = (holder = surfaceView.getHolder()).lockCanvas()) == null) {
                return;
            }
            nmc nmcVar = this.c;
            if (nmcVar != null) {
                nmcVar.a(f);
                lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                this.f.cAk_(lockCanvas);
                this.b.cAj_(lockCanvas, this.e);
                this.c.a();
            }
            holder.unlockCanvasAndPost(lockCanvas);
        } catch (IllegalArgumentException | IllegalStateException e) {
            LogUtil.b("HiH_DynamicChartHandler", "handlePushNewValue failed, exception = ", ExceptionUtils.d(e));
        }
    }
}
