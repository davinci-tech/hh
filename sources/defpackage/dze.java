package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.utils.UserInfoMockInteractor;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.dze;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes3.dex */
public class dze {

    /* renamed from: a, reason: collision with root package name */
    private static dze f11909a;
    private static final Object d = new Object();
    private boolean b;
    private final boolean c;

    private dze(Context context) {
        this.b = false;
        boolean bu = CommonUtil.bu();
        this.c = bu;
        if (bu) {
            this.b = d(context);
            a(context);
        }
    }

    public static dze b(Context context) {
        dze dzeVar;
        synchronized (d) {
            if (f11909a == null) {
                f11909a = new dze(context);
            }
            dzeVar = f11909a;
        }
        return dzeVar;
    }

    public void e(Context context) {
        LogUtil.a("Login_MainInteractorsForStoreDemo", String.format(Locale.ENGLISH, "insertDataToHiHealth::enter mIsReLaunchBeyondDays:%b mIsStoreDemoVersion:%b", Boolean.valueOf(this.b), Boolean.valueOf(this.c)));
        if (this.b && this.c) {
            LogUtil.a("Login_MainInteractorsForStoreDemo", "insertDataToHiHealth::start insert");
            sdq.a(context);
            gwx.a(7);
            scy.c().e();
            scy.c().g();
            scy.c().f();
            sci.c();
            sci.e();
            qkm.a();
            sdp.b();
            scy.c().b();
            Services.b("PluginFitnessAdvice", PluginSuggestion.class, context, new Consumer() { // from class: dzj
                @Override // com.huawei.framework.servicemgr.Consumer
                public final void accept(Object obj) {
                    ((PluginSuggestion) obj).deleteFitnessDataForStoreDemo();
                }
            });
            Services.b("PluginFitnessAdvice", PluginSuggestion.class, context, new Consumer() { // from class: dzi
                @Override // com.huawei.framework.servicemgr.Consumer
                public final void accept(Object obj) {
                    ((PluginSuggestion) obj).restoreUserDataForDemoVersion();
                }
            });
            c(context);
            sdq.d(context);
            gwx.a(30);
            scy.c().a();
            scy.c().h();
            scy.c().i();
            sci.a();
            sci.d();
            qkm.d();
            new UserInfoMockInteractor(context).e();
            mfi.a(context.getApplicationContext());
            sdp.d();
            c(context);
            this.b = false;
        }
    }

    private void c(final Context context) {
        HiHealthData hiHealthData = new HiHealthData(50001);
        long currentTimeMillis = System.currentTimeMillis();
        hiHealthData.setTimeInterval(currentTimeMillis, currentTimeMillis);
        hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
        hiHealthData.setValue(60);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(hiHealthData);
        hiDataInsertOption.setDatas(arrayList);
        HiHealthManager.d(context).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.health.interactor.MainInteractorsForStoreDemo$$ExternalSyntheticLambda0
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                dze.c(context, i, obj);
            }
        });
    }

    public static /* synthetic */ void c(Context context, int i, Object obj) {
        Intent intent = new Intent("com.huawei.hihealth.action_data_refresh");
        intent.setPackage(BaseApplication.d());
        intent.putExtra("refresh_type", 0);
        context.sendBroadcast(intent, "com.huawei.hihealth.DEFAULT_PERMISSION");
        HiBroadcastUtil.i(context);
    }

    private static boolean d(Context context) {
        int c = HiDateUtil.c(System.currentTimeMillis());
        int i = context != null ? context.getSharedPreferences("demo_share_config", 0).getInt("demo_last_open_time", 20140101) : 20140101;
        try {
        } catch (ParseException e) {
            LogUtil.b("Login_MainInteractorsForStoreDemo", "needDelDBFile parse date fail, e=", e.getMessage());
        }
        if (HiDateUtil.b(i, c, "yyyyMMdd") <= 1) {
            if (HiDateUtil.b(c, i, "yyyyMMdd") <= 1) {
                return false;
            }
        }
        return true;
    }

    private static void a(Context context) {
        if (context != null) {
            LogUtil.b("Login_MainInteractorsForStoreDemo", "update newday config");
            context.getSharedPreferences("demo_share_config", 0).edit().putInt("demo_last_open_time", HiDateUtil.c(System.currentTimeMillis())).apply();
        }
    }
}
