package defpackage;

import android.content.Context;
import android.media.Image;
import androidx.collection.SimpleArrayMap;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi;
import com.huawei.pluginsport.aiforwardflex.OnSportCodeListenerWrapper;
import com.huawei.pluginsport.aiforwardflex.OnSportSkeletonListenerWrapper;

/* loaded from: classes6.dex */
public class mwo extends AppBundlePluginProxy<AiForwardFlexApi> implements AiForwardFlexApi {
    private static String b = "PluginAiBodyPoseAlgorithm";
    private static String c = "com.huawei.health.sport.bodypose.forwardflex.AiForwardFlexImpl";
    private static volatile mwo e;
    private AiForwardFlexApi d;

    private mwo() {
        super("AiForwardFlexProxy", b, c);
        AiForwardFlexApi createPluginApi = createPluginApi();
        this.d = createPluginApi;
        Object[] objArr = new Object[2];
        objArr[0] = "the mAiForwardFlexApi is null :";
        objArr[1] = Boolean.valueOf(createPluginApi == null);
        LogUtil.a("AiForwardFlexProxy", objArr);
    }

    public static mwo d() {
        mwo mwoVar;
        if (e == null) {
            synchronized (mwo.class) {
                if (e == null) {
                    e = new mwo();
                }
                mwoVar = e;
            }
            return mwoVar;
        }
        return e;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void initialize(AiForwardFlexApi aiForwardFlexApi) {
        this.d = aiForwardFlexApi;
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public void setExamType(int i) {
        LogUtil.a("AiForwardFlexProxy", "setExamType:", Integer.valueOf(i));
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            aiForwardFlexApi.setExamType(i);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public int initModel(Context context, int i, int i2) {
        int i3;
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            i3 = aiForwardFlexApi.initModel(context, i, i2);
        } else {
            loadPlugin();
            i3 = -1;
        }
        LogUtil.a("AiForwardFlexProxy", "initModel:", Integer.valueOf(i3));
        return i3;
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public int setModelInfo(int i, int i2, int i3) {
        int i4;
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            i4 = aiForwardFlexApi.setModelInfo(i, i2, i3);
        } else {
            loadPlugin();
            i4 = -1;
        }
        LogUtil.a("AiForwardFlexProxy", "setModelInfo:", Integer.valueOf(i4));
        return i4;
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public void setSportCodeListener(OnSportCodeListenerWrapper onSportCodeListenerWrapper) {
        LogUtil.a("AiForwardFlexProxy", "setSportCodeListener");
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            aiForwardFlexApi.setSportCodeListener(onSportCodeListenerWrapper);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public void setSkeletonListener(OnSportSkeletonListenerWrapper onSportSkeletonListenerWrapper) {
        LogUtil.a("AiForwardFlexProxy", "setSkeletonListener");
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            aiForwardFlexApi.setSkeletonListener(onSportSkeletonListenerWrapper);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public void setImage(Image image) {
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            aiForwardFlexApi.setImage(image);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public void setExamStage(int i) {
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            aiForwardFlexApi.setExamStage(i);
        } else {
            loadPlugin();
        }
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public boolean unloadModel() {
        boolean z;
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            z = aiForwardFlexApi.unloadModel();
        } else {
            loadPlugin();
            z = false;
        }
        LogUtil.a("AiForwardFlexProxy", "unLoadModel:", Boolean.valueOf(z));
        return z;
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public int setSurfaceRotation(int i) {
        int i2;
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            i2 = aiForwardFlexApi.setSurfaceRotation(i);
        } else {
            loadPlugin();
            i2 = 0;
        }
        LogUtil.a("AiForwardFlexProxy", "setSurfaceRotation result:", Integer.valueOf(i2));
        return i2;
    }

    @Override // com.huawei.pluginsport.aiforwardflex.AiForwardFlexApi
    public void setValue(SimpleArrayMap<String, Object> simpleArrayMap) {
        AiForwardFlexApi aiForwardFlexApi = this.d;
        if (aiForwardFlexApi != null) {
            aiForwardFlexApi.setValue(simpleArrayMap);
        } else {
            loadPlugin();
        }
    }
}
