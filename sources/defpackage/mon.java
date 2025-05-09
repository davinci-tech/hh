package defpackage;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnesssport.AiFitnessSportApi;
import com.huawei.pluginfitnesssport.OnCheckStandListenerWrapper;
import com.huawei.pluginfitnesssport.OnSensorListenerWrapper;
import com.huawei.pluginfitnesssport.OnSportSkeletonListenerWrapper;
import com.huawei.pluginfitnesssport.OnTrainSkeletonListenerWrapper;

/* loaded from: classes6.dex */
public class mon extends AppBundlePluginProxy<AiFitnessSportApi> {

    /* renamed from: a, reason: collision with root package name */
    private static String f15083a;
    private static String b;
    private static volatile mon c;
    private AiFitnessSportApi d;

    private mon() {
        super("AiFitnessSportProxy", f15083a, b);
        AiFitnessSportApi createPluginApi = createPluginApi();
        this.d = createPluginApi;
        Object[] objArr = new Object[2];
        objArr[0] = "the mAiFitnessSportApi is null : ";
        objArr[1] = Boolean.valueOf(createPluginApi == null);
        LogUtil.a("AiFitnessSportProxy", objArr);
    }

    public static mon d() {
        mon monVar;
        if (c == null) {
            synchronized (mon.class) {
                if (c == null) {
                    c();
                    c = new mon();
                }
                monVar = c;
            }
            return monVar;
        }
        c();
        return c;
    }

    private static void c() {
        int modelType = mwt.d().getModelType();
        if (modelType == -1) {
            LogUtil.h("AiFitnessSportProxy", "cpu platform unsupported");
            return;
        }
        if (modelType == 2) {
            LogUtil.a("AiFitnessSportProxy", "load PluginAiFitnessSportQua");
            f15083a = "PluginAiFitnessSportQua";
            b = "com.huawei.health.pluginaifitnesssportqua.AiFitnessSportQuaImpl";
        } else {
            if (modelType != 3) {
                return;
            }
            LogUtil.a("AiFitnessSportProxy", "load PluginAiFitnessSportKir");
            f15083a = "PluginAiFitnessSportKir";
            b = "com.huawei.health.pluginaifitnesssportkir.AiFitnessSportKirImpl";
        }
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(AiFitnessSportApi aiFitnessSportApi) {
        this.d = aiFitnessSportApi;
    }

    public void d(final Context context, final int i, final int i2, final BaseResponseCallback baseResponseCallback) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mpc
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context2, Intent intent) {
                return mon.this.cmB_(baseResponseCallback, context, i, i2, context2, intent);
            }
        });
    }

    /* synthetic */ boolean cmB_(BaseResponseCallback baseResponseCallback, Context context, int i, int i2, Context context2, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "initModel mAiFitnessSportApi==null");
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(-1, null);
            }
            return false;
        }
        if (baseResponseCallback == null) {
            LogUtil.a("AiFitnessSportProxy", "initModel result = ", Integer.valueOf(aiFitnessSportApi.initModel(context, i, i2)));
        } else {
            baseResponseCallback.onResponse(aiFitnessSportApi.initModel(context, i, i2), null);
        }
        return false;
    }

    public void cmQ_(final Image image) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: moz
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmF_(image, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmF_(Image image, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setImage mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setImage(image);
        return false;
    }

    public void c(final OnSportSkeletonListenerWrapper onSportSkeletonListenerWrapper) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: moy
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmL_(onSportSkeletonListenerWrapper, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmL_(OnSportSkeletonListenerWrapper onSportSkeletonListenerWrapper, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.a("R_AiFitnessSportProxy", "setSportSkeletonListener mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setSportSkeletonListener(onSportSkeletonListenerWrapper);
        return false;
    }

    public void a(final BaseResponseCallback baseResponseCallback) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mov
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmO_(baseResponseCallback, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmO_(BaseResponseCallback baseResponseCallback, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "unloadModel mAiFitnessSportApi == null");
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(-1, null);
            }
            return false;
        }
        if (baseResponseCallback == null) {
            LogUtil.a("AiFitnessSportProxy", "unloadModel result = ", Boolean.valueOf(aiFitnessSportApi.unloadModel()));
        } else {
            baseResponseCallback.onResponse(aiFitnessSportApi.unloadModel() ? 0 : -1, null);
        }
        return false;
    }

    public void b(final int i, final int i2, final int i3, final BaseResponseCallback baseResponseCallback) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: moo
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmG_(baseResponseCallback, i, i2, i3, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmG_(BaseResponseCallback baseResponseCallback, int i, int i2, int i3, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setModelInfo mAiFitnessSportApi == null");
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(-1, null);
            }
            return false;
        }
        if (baseResponseCallback == null) {
            LogUtil.a("AiFitnessSportProxy", "setModelInfo result = ", Integer.valueOf(aiFitnessSportApi.setModelInfo(i, i2, i3)));
        } else {
            baseResponseCallback.onResponse(aiFitnessSportApi.setModelInfo(i, i2, i3), null);
        }
        return false;
    }

    public void a(final int i, final int i2) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mow
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmD_(i, i2, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmD_(int i, int i2, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setCartoonView mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setCartoonView(i, i2);
        return false;
    }

    public void b(final String str, final BaseResponseCallback baseResponseCallback) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mpa
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmH_(baseResponseCallback, str, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmH_(BaseResponseCallback baseResponseCallback, String str, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setMotionTemplate mAiFitnessSportApi == null");
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(-1, null);
            }
            return false;
        }
        if (baseResponseCallback == null) {
            LogUtil.a("AiFitnessSportProxy", "setMotionTemplate result = ", Integer.valueOf(aiFitnessSportApi.setMotionTemplate(str)));
        } else {
            baseResponseCallback.onResponse(aiFitnessSportApi.setMotionTemplate(str), null);
        }
        return false;
    }

    public void d(final BaseResponseCallback baseResponseCallback) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: moq
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmJ_(baseResponseCallback, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmJ_(BaseResponseCallback baseResponseCallback, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setPoseResult mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setPoseResult(baseResponseCallback);
        return false;
    }

    public void b() {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mor
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmI_(context, intent);
            }
        });
    }

    /* synthetic */ boolean cmI_(Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setPause mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setPause();
        return false;
    }

    public void a() {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mou
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmK_(context, intent);
            }
        });
    }

    /* synthetic */ boolean cmK_(Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setResume mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setResume();
        return false;
    }

    public void c(Context context, final OnSensorListenerWrapper onSensorListenerWrapper) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mom
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context2, Intent intent) {
                return mon.this.cmC_(onSensorListenerWrapper, context2, intent);
            }
        });
    }

    /* synthetic */ boolean cmC_(OnSensorListenerWrapper onSensorListenerWrapper, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "registerSensor mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.registerSensor(context, onSensorListenerWrapper);
        return false;
    }

    public void c(Context context) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mos
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context2, Intent intent) {
                return mon.this.cmP_(context2, intent);
            }
        });
    }

    /* synthetic */ boolean cmP_(Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "unregisterSensor mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.unregisterSensor(context);
        return false;
    }

    public void b(final OnCheckStandListenerWrapper onCheckStandListenerWrapper) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mox
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmE_(onCheckStandListenerWrapper, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmE_(OnCheckStandListenerWrapper onCheckStandListenerWrapper, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setCheckStandListener mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setCheckStandListener(onCheckStandListenerWrapper);
        return false;
    }

    public void c(final BaseResponseCallback baseResponseCallback) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mpe
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmA_(baseResponseCallback, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmA_(BaseResponseCallback baseResponseCallback, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "getSportVersion mAiFitnessSportApi == null");
            if (baseResponseCallback != null) {
                baseResponseCallback.onResponse(-1, null);
            }
            return false;
        }
        if (baseResponseCallback != null) {
            baseResponseCallback.onResponse(0, Integer.valueOf(aiFitnessSportApi.getSportVersion()));
        } else {
            LogUtil.a("AiFitnessSportProxy", "getSportVersion = ", Integer.valueOf(aiFitnessSportApi.getSportVersion()));
        }
        return false;
    }

    public void a(final int i) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mot
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmN_(i, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmN_(int i, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setType mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setType(i);
        return false;
    }

    public void d(final OnTrainSkeletonListenerWrapper onTrainSkeletonListenerWrapper) {
        loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: mop
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context, Intent intent) {
                return mon.this.cmM_(onTrainSkeletonListenerWrapper, context, intent);
            }
        });
    }

    /* synthetic */ boolean cmM_(OnTrainSkeletonListenerWrapper onTrainSkeletonListenerWrapper, Context context, Intent intent) {
        AiFitnessSportApi aiFitnessSportApi = this.d;
        if (aiFitnessSportApi == null) {
            LogUtil.h("R_AiFitnessSportProxy", "setTrainSkeletonListener mAiFitnessSportApi == null");
            return false;
        }
        aiFitnessSportApi.setTrainSkeletonListener(onTrainSkeletonListenerWrapper);
        return false;
    }
}
