package com.huawei.ui.homehealth.runcard.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.homehealth.runcard.utils.SportMusicUtils;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.gwh;
import defpackage.gww;
import defpackage.jdm;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.util.Map;

/* loaded from: classes9.dex */
public class SportMusicUtils {
    private Context c;

    public SportMusicUtils(Context context) {
        this.c = context.getApplicationContext();
    }

    public void b(gww gwwVar, int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (gwwVar == null || iBaseResponseCallback == null) {
            LogUtil.h("Track_SportMusicUtils", "preferenceUtil or loadMusicCallback is null.");
        } else {
            nrf.b(gwwVar.d(i), new CustomTarget<Bitmap>() { // from class: com.huawei.ui.homehealth.runcard.utils.SportMusicUtils.1
                @Override // com.bumptech.glide.request.target.Target
                /* renamed from: dgL_, reason: merged with bridge method [inline-methods] */
                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    if (bitmap == null) {
                        if (SportMusicUtils.this.c != null) {
                            iBaseResponseCallback.d(100001, null);
                            return;
                        }
                        return;
                    }
                    SportMusicUtils.this.new b(bitmap, iBaseResponseCallback).execute(new Void[0]);
                }

                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable drawable) {
                    LogUtil.a("Track_SportMusicUtils", "loadMusicElement onLoadCleared");
                }
            });
        }
    }

    class b extends AsyncTask<Void, Void, Bitmap> {

        /* renamed from: a, reason: collision with root package name */
        private IBaseResponseCallback f9583a;
        private Bitmap e;

        b(Bitmap bitmap, IBaseResponseCallback iBaseResponseCallback) {
            this.e = bitmap;
            this.f9583a = iBaseResponseCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: dgM_, reason: merged with bridge method [inline-methods] */
        public Bitmap doInBackground(Void... voidArr) {
            float e = nrr.e(SportMusicUtils.this.c, 56.0f) / Math.min(this.e.getWidth(), this.e.getHeight());
            Matrix matrix = new Matrix();
            matrix.postScale(e, e);
            Bitmap bitmap = this.e;
            Bitmap cHX_ = nrf.cHX_(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.e.getHeight(), matrix, true));
            SportMusicUtils sportMusicUtils = SportMusicUtils.this;
            return sportMusicUtils.dgH_(cHX_, sportMusicUtils.c);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: dgN_, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Bitmap bitmap) {
            this.f9583a.d(100000, bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap dgH_(Bitmap bitmap, Context context) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint dgI_ = dgI_();
        dgI_.setStyle(Paint.Style.FILL);
        dgI_.setColor(-1);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawCircle(bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f, bitmap.getWidth() / 2.0f, dgI_);
        float width = bitmap.getWidth() - nrr.e(context, 4.0f);
        float width2 = width / bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.setScale(width2, width2);
        matrix.postTranslate((bitmap.getWidth() - r1) / 2.0f, (bitmap.getHeight() - r1) / 2.0f);
        canvas.drawBitmap(bitmap, matrix, dgI_());
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable._2131430473_res_0x7f0b0c49);
        float width3 = width / decodeResource.getWidth();
        matrix.setScale(width3, width3);
        matrix.postTranslate((bitmap.getWidth() - r1) / 2.0f, (bitmap.getHeight() - r1) / 2.0f);
        canvas.drawBitmap(decodeResource, matrix, dgI_());
        return createBitmap;
    }

    private Paint dgI_() {
        return new Paint() { // from class: com.huawei.ui.homehealth.runcard.utils.SportMusicUtils.3
            {
                setAntiAlias(true);
                setDither(true);
                setFilterBitmap(true);
            }
        };
    }

    public Map<String, Object> e(Map<String, Object> map, int i) {
        if (c(BaseApplication.getActivity())) {
            return map;
        }
        try {
            Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
            intent.setFlags(268435456);
            if (gvv.a() && CommonUtil.v(gwh.s)) {
                intent.setPackage(gwh.s);
                if (gwg.i(this.c)) {
                    LogUtil.a("Track_SportMusicUtils", "gotoSportMusic MUSIC_PLAYER");
                    SportMusicController.a().d(0, i, false);
                    map.put("sportMusicType", 0);
                    map.put("click", 1);
                    map.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 0);
                    map.put("musicType", Integer.valueOf(gwg.i(this.c) ? 4 : 0));
                    map.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
                    return map;
                }
                nsn.cLM_(intent, gwh.s, this.c, nsf.h(R.string._2130842049_res_0x7f0211c1));
            }
        } catch (ActivityNotFoundException e) {
            LogUtil.h("Track_SportMusicUtils", "Not found this activity MUSIC_PLAYER ", e.getMessage());
        }
        map.put("sportMusicType", 0);
        return map;
    }

    public static boolean c(Context context) {
        boolean z = !jdm.b(context, CommonUtil.z());
        if (z) {
            b(context);
        }
        return z;
    }

    private static void b(final Context context) {
        if (!(context instanceof Activity)) {
            LogUtil.h("Track_SportMusicUtils", "showInstallHwMusic context is not activity context.");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R$string.IDS_hw_health_music_huawei_music_not_installed)).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: osk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportMusicUtils.dgJ_(view);
            }
        }).czC_(R$string.IDS_compatibility_note_open, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.utils.SportMusicUtils$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportMusicUtils.dgK_(context, view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static /* synthetic */ void dgJ_(View view) {
        LogUtil.a("Track_SportMusicUtils", "showInstallHwMusic cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dgK_(Context context, View view) {
        LogUtil.a("Track_SportMusicUtils", "showInstallHwMusic onClick");
        Intent intent = new Intent("com.huawei.appmarket.intent.action.AppDetail");
        intent.setPackage("com.huawei.appmarket");
        intent.putExtra("APP_PACKAGENAME", "com.huawei.music");
        nsn.cLe_(intent, "com.huawei.music", context);
        ViewClickInstrumentation.clickOnView(view);
    }
}
