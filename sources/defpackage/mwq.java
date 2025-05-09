package defpackage;

import android.content.Context;
import android.media.Image;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi;
import com.huawei.pluginsport.airopeskipping.OnJumpRopeListenerWrapper;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class mwq extends AppBundlePluginProxy<AiRopeSkippingApi> implements AiRopeSkippingApi {

    /* renamed from: a, reason: collision with root package name */
    private static String f15222a = "error";
    private static String c = "error";
    private static volatile mwq d;
    private AiRopeSkippingApi b;

    private mwq() {
        super("AiRopeSkippingProxy", f15222a, c);
        AiRopeSkippingApi createPluginApi = createPluginApi();
        this.b = createPluginApi;
        Object[] objArr = new Object[2];
        objArr[0] = "the mAiRopeSkippingApi is null :";
        objArr[1] = Boolean.valueOf(createPluginApi == null);
        LogUtil.a("AiRopeSkippingProxy", objArr);
    }

    public static mwq d() {
        mwq mwqVar;
        if (d == null) {
            synchronized (mwq.class) {
                if (d == null) {
                    e();
                    d = new mwq();
                }
                mwqVar = d;
            }
            return mwqVar;
        }
        e();
        return d;
    }

    private static void e() {
        if (mww.d().getModelType() == 1) {
            c = "com.huawei.sport.airopeskipping4.AiRopeSkippingImpl4";
            f15222a = "AiRopeSkippingMsPlus";
        }
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.b != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(AiRopeSkippingApi aiRopeSkippingApi) {
        this.b = aiRopeSkippingApi;
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public int initModel(String str, int i, int i2) {
        int i3;
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi != null) {
            i3 = aiRopeSkippingApi.initModel(str, i, i2);
        } else {
            LogUtil.h("AiRopeSkippingProxy", "the mAiRopeSkippingApi == null, loadPlugin again");
            loadPlugin();
            i3 = 0;
        }
        LogUtil.a("AiRopeSkippingProxy", "initModel result: ", Integer.valueOf(i3));
        return i3;
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public int initModel(Context context, int i, int i2) {
        int i3;
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi != null) {
            i3 = aiRopeSkippingApi.initModel(context, i, i2);
        } else {
            LogUtil.h("AiRopeSkippingProxy", "the mAiRopeSkippingApi == null, loadPlugin again");
            loadPlugin();
            i3 = 0;
        }
        LogUtil.a("AiRopeSkippingProxy", "initModel result: ", Integer.valueOf(i3));
        return i3;
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public int setModelInfo(int i, int i2, int i3) {
        int i4;
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi != null) {
            i4 = aiRopeSkippingApi.setModelInfo(i, i2, i3);
        } else {
            LogUtil.b("AiRopeSkippingProxy", "mAiRopeSkippingApi == null");
            i4 = -1;
        }
        LogUtil.a("AiRopeSkippingProxy", "setModelInfo result: ", Integer.valueOf(i4));
        return i4;
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public void setRopeListener(OnJumpRopeListenerWrapper onJumpRopeListenerWrapper) {
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi != null) {
            aiRopeSkippingApi.setRopeListener(onJumpRopeListenerWrapper);
        }
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public void setStage(int i) {
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi != null) {
            aiRopeSkippingApi.setStage(i);
        }
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public void setImage(Image image) {
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi != null) {
            aiRopeSkippingApi.setImage(image);
        }
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public boolean unloadModel() {
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi == null) {
            return false;
        }
        boolean unloadModel = aiRopeSkippingApi.unloadModel();
        ReleaseLogUtil.e("AiRopeSkippingProxy", "unloadModule result = ", Boolean.valueOf(unloadModel));
        return unloadModel;
    }

    @Override // com.huawei.pluginsport.airopeskipping.AiRopeSkippingApi
    public boolean reset() {
        AiRopeSkippingApi aiRopeSkippingApi = this.b;
        if (aiRopeSkippingApi == null) {
            return false;
        }
        boolean reset = aiRopeSkippingApi.reset();
        ReleaseLogUtil.e("AiRopeSkippingProxy", "unloadModule reset = ", Boolean.valueOf(reset));
        return reset;
    }

    public List<String> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("AiRopeSkippingMsPlus");
        arrayList.remove(f15222a);
        return arrayList;
    }

    public int c() {
        int modelType = mww.d().getModelType();
        LogUtil.a("AiRopeSkippingProxy", "the sModelType: ", Integer.valueOf(modelType));
        return modelType;
    }
}
