package com.huawei.ui.main.stories.health.model.weight.card;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.socialshare.model.SaveBitampCallback;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi;
import com.huawei.operation.js.JsInteractAddition;
import com.huawei.ui.commonui.base.Consumable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.bzs;
import defpackage.fdu;
import defpackage.ixx;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.qlc;
import defpackage.qui;
import defpackage.qve;
import defpackage.rya;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CardConstants {

    /* loaded from: classes9.dex */
    public enum CardType implements Consumable.ConsumableType {
        FASTING_CARD,
        DIET_CARD,
        INTERMITTENT_PERIOD
    }

    public static String e() {
        qui c = qve.c();
        if (c == null || !c.c()) {
            return "0";
        }
        if (c.d() == null || c.d().b() == null || c.d().b().g() > 0) {
            return "1";
        }
        qlc.b().d();
        return qlc.b().c() ? "2" : "3";
    }

    private static String c(String str) {
        String str2 = rya.d("WeightCardConstructor") + "img/" + str + ".webp";
        LogUtil.a("CardConstants", "getPicFilePath picName: ", str, ", img: ", str2);
        return CommonUtil.c(str2);
    }

    public static boolean b() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_diet_diary_status");
        return userPreference != null && TextUtils.equals("true", userPreference.getValue());
    }

    public static void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qky
            @Override // java.lang.Runnable
            public final void run() {
                CardConstants.j();
            }
        });
    }

    public static /* synthetic */ void j() {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.weight_diet_diary_status");
        hiUserPreference.setValue(String.valueOf(true));
        LogUtil.a("CardConstants", "joinDietDiary = ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference)));
        i();
    }

    private static void i() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public static BitmapDrawable dFc_(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("R_CardConstants", "context or path is null, leave getWeightShareBackground");
            return null;
        }
        BitmapDrawable cHP_ = nrf.cHP_(c(str));
        if (!LanguageUtil.bc(context)) {
            return cHP_;
        }
        LogUtil.a("CardConstants", "is RTLLanguage, getMirrorBitmapDrawable");
        return nrz.cKm_(context, cHP_);
    }

    public static List<Bitmap> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(nrf.cHR_(R.mipmap.hw_health_edit_share_photo_pic));
        arrayList.add(nrf.cHQ_(c("weight_share_background_small_1")));
        arrayList.add(nrf.cHQ_(c("weight_share_background_small_2")));
        arrayList.add(nrf.cHQ_(c("weight_share_background_small_3")));
        return arrayList;
    }

    public static List<Bitmap> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(nrf.cHQ_(c("weight_share_data_small_0")));
        arrayList.add(nrf.cHQ_(c("weight_share_data_small_1")));
        arrayList.add(nrf.cHQ_(c("weight_share_data_small_2")));
        return arrayList;
    }

    public static List<Bitmap> dFd_(Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BitmapFactory.decodeResource(resources, R.mipmap._2131821552_res_0x7f1103f0));
        arrayList.add(BitmapFactory.decodeResource(resources, R.mipmap._2131821553_res_0x7f1103f1));
        arrayList.add(BitmapFactory.decodeResource(resources, R.mipmap._2131821554_res_0x7f1103f2));
        return arrayList;
    }

    public static int c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(11);
    }

    public static void d(fdu fduVar, final Context context, AnalyticsValue analyticsValue) {
        fduVar.e(1);
        fduVar.i(false);
        fduVar.b("13");
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        fduVar.b((Map<String, Object>) hashMap);
        fduVar.c(false);
        if (CommonUtil.bu()) {
            fduVar.c(new SaveBitampCallback() { // from class: com.huawei.ui.main.stories.health.model.weight.card.CardConstants.1
                @Override // com.huawei.health.socialshare.model.SaveBitampCallback
                public void onSuccess(Uri uri) {
                    SharedPreferenceManager.e(context, JsInteractAddition.BI_ERROR_CODE_INVALID_AT, "SP_REPORT_BODY", uri.toString(), (StorageParams) null);
                }

                @Override // com.huawei.health.socialshare.model.SaveBitampCallback
                public void onFail(int i) {
                    LogUtil.h("CardConstants", "shareWeightFragment shareToLocal err = ", Integer.valueOf(i));
                }
            });
        }
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, context);
        ixx.d().d(context, analyticsValue.value(), hashMap, 0);
    }

    public static void c(Context context, String str) {
        LogUtil.a("CardConstants", "openFastingLiteH5 enter");
        if (nsn.o()) {
            LogUtil.h("CardConstants", "openFastingLiteH5 isFastClick");
            return;
        }
        if (context == null) {
            LogUtil.h("CardConstants", "openFastingLiteH5 but context = null");
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(str);
        builder.addCustomizeJsModule("tradeApi", JsTradeApi.class);
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.fasting-lite", builder);
    }

    public static void e(HealthTextView healthTextView) {
        if (healthTextView == null) {
            return;
        }
        healthTextView.setAutoTextInfo(10, 1, 1);
    }

    public static void dFe_(int i, ImageView imageView) {
        switch (i) {
            case 1:
                imageView.setImageResource(R.mipmap._2131820829_res_0x7f11011d);
                break;
            case 2:
                imageView.setImageResource(R.mipmap._2131820831_res_0x7f11011f);
                break;
            case 3:
                imageView.setImageResource(R.mipmap._2131820833_res_0x7f110121);
                break;
            case 4:
                imageView.setImageResource(R.mipmap._2131820835_res_0x7f110123);
                break;
            case 5:
                imageView.setImageResource(R.mipmap._2131820837_res_0x7f110125);
                break;
            case 6:
                imageView.setImageResource(R.mipmap._2131820839_res_0x7f110127);
                break;
            case 7:
                imageView.setImageResource(R.mipmap._2131820841_res_0x7f110129);
                break;
            case 8:
                imageView.setImageResource(R.mipmap._2131820843_res_0x7f11012b);
                break;
            case 9:
                imageView.setImageResource(R.mipmap._2131820845_res_0x7f11012d);
                break;
            default:
                LogUtil.h("CardConstants", "setBodyTypeImg type get is wrong...");
                break;
        }
    }
}
