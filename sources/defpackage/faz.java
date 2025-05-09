package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.recognizekit.impl.RecognizeHelper;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class faz implements RecognizeHelper {

    /* renamed from: a, reason: collision with root package name */
    private RecognizeHelper.RecognizeListener f12415a;
    private RectF d;

    public faz(RecognizeHelper.RecognizeListener recognizeListener) {
        this.f12415a = recognizeListener;
    }

    @Override // com.huawei.health.recognizekit.impl.RecognizeHelper
    public void recognize(final Bitmap bitmap) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: faw
            @Override // java.lang.Runnable
            public final void run() {
                faz.this.auP_(bitmap);
            }
        });
    }

    /* synthetic */ void auP_(Bitmap bitmap) {
        String nutritionTableOcrResult = mph.b().getNutritionTableOcrResult(BaseApplication.e(), auO_(bitmap));
        RecognizeHelper.RecognizeListener recognizeListener = this.f12415a;
        if (recognizeListener != null) {
            recognizeListener.onRecognized(nutritionTableOcrResult);
        }
        LogUtil.c("RecognizeNutritionHelper", "recognize result:", nutritionTableOcrResult);
    }

    @Override // com.huawei.health.recognizekit.impl.RecognizeHelper
    public void release() {
        this.f12415a = null;
    }

    private Bitmap auO_(Bitmap bitmap) {
        if (bitmap == null) {
            return bitmap;
        }
        Context e = BaseApplication.e();
        if (this.d == null) {
            LogUtil.e("RecognizeNutritionHelper", "onImageAvailable getCenterRect is null");
            this.d = fba.auU_(e);
        }
        Bitmap auY_ = fba.auY_(this.d, fba.ave_(bitmap), e);
        LogUtil.c("RecognizeNutritionHelper", "onImageAvailable isSaved=", Boolean.valueOf(nrf.cJt_(auY_, fba.d)));
        return auY_;
    }

    public void auQ_(RectF rectF) {
        this.d = rectF;
    }
}
