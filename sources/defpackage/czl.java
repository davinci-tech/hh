package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.basefitnessadvice.api.HeartRateControlApi;
import com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper;
import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public class czl {
    private static final Map<String, dsw> e = new ConcurrentHashMap();
    private static final Map<String, dsy> c = new ConcurrentHashMap();

    protected static void d(int i, dsw dswVar) {
        LogUtil.a("HeartRateControlManager", "addHeartRateControlInfo");
        if (dswVar == null) {
            LogUtil.h("HeartRateControlManager", "heartRateControlInfo is null");
        } else {
            e.put(HeartRateControlConfigHelper.c().e(i), dswVar);
        }
    }

    public static void a(int i, final ConfigCallBack<dsw> configCallBack) {
        dsw dswVar;
        LogUtil.a("HeartRateControlManager", "requestConfigInfo");
        String e2 = HeartRateControlConfigHelper.c().e(i);
        Map<String, dsw> map = e;
        if (map.containsKey(e2) && (dswVar = map.get(e2)) != null) {
            LogUtil.a("HeartRateControlManager", "use caching");
            e(configCallBack, dswVar);
        } else {
            LogUtil.a("HeartRateControlManager", "use local or net");
            HeartRateControlConfigHelper.c().a(new HeartRateControlConfigHelper.DownloadCallBack<dsw>() { // from class: czl.5
                @Override // com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper.DownloadCallBack
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onDownloadFinish(dsw dswVar2) {
                    LogUtil.a("HeartRateControlManager", "success");
                    czl.e((ConfigCallBack<dsw>) ConfigCallBack.this, dswVar2);
                }

                @Override // com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper.DownloadCallBack
                public void onDownloadFail(int i2, String str) {
                    LogUtil.h("HeartRateControlManager", "course onDownloadFail:", Integer.valueOf(i2), ",msg:", str);
                    czl.e((ConfigCallBack<dsw>) ConfigCallBack.this, (dsw) null);
                }
            });
            HeartRateControlConfigHelper.c().c(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final ConfigCallBack<dsw> configCallBack, final dsw dswVar) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: czk
            @Override // java.lang.Runnable
            public final void run() {
                ConfigCallBack.this.onResponse(dswVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final ConfigCallBack<dsy> configCallBack, final dsy dsyVar) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: czr
            @Override // java.lang.Runnable
            public final void run() {
                ConfigCallBack.this.onResponse(dsyVar);
            }
        });
    }

    public static void a(final int i, final String str, final ConfigCallBack<FitWorkout> configCallBack) {
        LogUtil.a("HeartRateControlManager", "getCourseInfoByCourseId after requestConfigInfo");
        a(i, (ConfigCallBack<dsw>) new ConfigCallBack() { // from class: czm
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj) {
                czl.d(i, str, (dsw) obj, configCallBack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(int i, String str, dsw dswVar, ConfigCallBack<FitWorkout> configCallBack) {
        LogUtil.a("HeartRateControlManager", "getCourseInfoByCourseId");
        if (dswVar == null) {
            LogUtil.h("HeartRateControlManager", "heartRateControlInfo is null");
            e(configCallBack, (FitWorkout) null);
            return;
        }
        Iterator<dsv> it = dswVar.getGroupLists().iterator();
        dsu dsuVar = null;
        while (it.hasNext()) {
            Iterator<dsu> it2 = it.next().getCourseInfoList().iterator();
            while (true) {
                if (it2.hasNext()) {
                    dsu next = it2.next();
                    if (next.getId().equals(str)) {
                        dsuVar = next;
                        break;
                    }
                }
            }
        }
        if (dsuVar == null) {
            LogUtil.h("HeartRateControlManager", "tempCourseInfo is null");
            e(configCallBack, (FitWorkout) null);
        } else {
            a(i, dsuVar, configCallBack);
        }
    }

    private static void e(final ConfigCallBack<FitWorkout> configCallBack, final FitWorkout fitWorkout) {
        if (configCallBack != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: czq
                @Override // java.lang.Runnable
                public final void run() {
                    ConfigCallBack.this.onResponse(fitWorkout);
                }
            });
        }
    }

    private static void a(final int i, final dsu dsuVar, final ConfigCallBack<FitWorkout> configCallBack) {
        LogUtil.a("HeartRateControlManager", "buildFitWorkout");
        final FitWorkout fitWorkout = new FitWorkout();
        fitWorkout.saveId(dsuVar.getId());
        fitWorkout.saveName(d(i, dsuVar.getCourseName()));
        fitWorkout.saveCalorie(dsuVar.getCalories());
        fitWorkout.saveDescription(d(i, dsuVar.getDescription()));
        fitWorkout.saveMidPicture(b(i, dsuVar.getDetailImage()));
        fitWorkout.savePicture(b(i, dsuVar.getThumbnail()));
        fitWorkout.saveDuration(dsuVar.getTotalTime());
        fitWorkout.setUpperHeartRateAdjust(dsuVar.getUpperHeartRateAdjust());
        fitWorkout.setLowerHeartRateAdjust(dsuVar.getLowerHeartRateAdjust());
        fitWorkout.saveDifficulty(dsuVar.getDifficulty());
        fitWorkout.saveVersion(dsuVar.getVersion());
        final HeartRateControlApi heartRateControlApi = (HeartRateControlApi) Services.c("BaseSportModel", HeartRateControlApi.class);
        heartRateControlApi.initPersonalParam(i, new ResponseCallback() { // from class: czn
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                czl.d(i, dsuVar, heartRateControlApi, fitWorkout, configCallBack, i2, obj);
            }
        });
    }

    static /* synthetic */ void d(final int i, final dsu dsuVar, final HeartRateControlApi heartRateControlApi, final FitWorkout fitWorkout, final ConfigCallBack configCallBack, int i2, Object obj) {
        LogUtil.a("HeartRateControlManager", "initPersonalParam status = ", Integer.valueOf(i2));
        b(i, (ConfigCallBack<dsy>) new ConfigCallBack() { // from class: czj
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj2) {
                czl.c(dsu.this, i, heartRateControlApi, fitWorkout, configCallBack, (dsy) obj2);
            }
        });
    }

    static /* synthetic */ void c(dsu dsuVar, int i, HeartRateControlApi heartRateControlApi, FitWorkout fitWorkout, ConfigCallBack configCallBack, dsy dsyVar) {
        LogUtil.a("HeartRateControlManager", "requestGlobalConfigByType success");
        b(dsuVar, i, heartRateControlApi, fitWorkout, dsyVar);
        e((ConfigCallBack<FitWorkout>) configCallBack, fitWorkout);
    }

    private static void b(dsu dsuVar, int i, HeartRateControlApi heartRateControlApi, FitWorkout fitWorkout, dsy dsyVar) {
        LogUtil.a("HeartRateControlManager", "computerAlgorithmPara");
        ArrayList arrayList = new ArrayList();
        for (dsx dsxVar : dsuVar.getStageList()) {
            ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
            TargetConfig targetConfig = new TargetConfig();
            targetConfig.setValueL(dsxVar.getDuration());
            targetConfig.setValueType(1);
            choreographedSingleAction.setTargetConfig(targetConfig);
            AtomicAction atomicAction = new AtomicAction();
            atomicAction.setId(String.valueOf(dsxVar.getStageNo()));
            atomicAction.setName(d(i, dsxVar.getStageName()));
            choreographedSingleAction.setAction(atomicAction);
            Pair<Integer, Integer> courseZoneHeartRateScope = heartRateControlApi.getCourseZoneHeartRateScope(new Pair<>(Float.valueOf(dsxVar.getZoneMin()), Float.valueOf(dsxVar.getZoneMax())), dsxVar.getZoneType());
            TargetConfig targetConfig2 = new TargetConfig();
            targetConfig2.setValueType(TargetConfig.ValueTypes.RANGE_VALUE.getValue());
            targetConfig2.setValueL(((Integer) courseZoneHeartRateScope.first).intValue());
            targetConfig2.setValueH(((Integer) courseZoneHeartRateScope.second).intValue());
            choreographedSingleAction.setIntensityConfig(targetConfig2);
            int courseZoneTargetHeartRate = heartRateControlApi.getCourseZoneTargetHeartRate(courseZoneHeartRateScope);
            choreographedSingleAction.putExtendProperty("target_heart_rate", String.valueOf(courseZoneTargetHeartRate));
            if (i == 265) {
                choreographedSingleAction.putExtendProperty("rpm_min", String.valueOf(dsxVar.getRpmMin()));
                choreographedSingleAction.putExtendProperty("rpm_max", String.valueOf(dsxVar.getRpmMax()));
            } else {
                choreographedSingleAction.putExtendProperty("target_speed", String.valueOf(heartRateControlApi.getCourseZoneTargetSpeed(courseZoneTargetHeartRate)));
                Pair<Float, Float> courseZoneSpeedScope = heartRateControlApi.getCourseZoneSpeedScope(courseZoneHeartRateScope, dsyVar.getMaximumSafeRateLimit());
                choreographedSingleAction.putExtendProperty("target_speed_min", String.valueOf(courseZoneSpeedScope.first));
                choreographedSingleAction.putExtendProperty("target_speed_max", String.valueOf(courseZoneSpeedScope.second));
            }
            arrayList.add(choreographedSingleAction);
        }
        ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
        choreographedMultiActions.setActionList(arrayList);
        choreographedMultiActions.setRepeatTimes(1);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(choreographedMultiActions);
        fitWorkout.setCourseActions(arrayList2);
    }

    protected static void d(int i, dsy dsyVar) {
        LogUtil.a("HeartRateControlManager", "addGlobalConfig");
        if (dsyVar == null) {
            LogUtil.h("HeartRateControlManager", "speedControlConfigInfo is null");
        } else {
            c.put(HeartRateControlConfigHelper.c().d(i), dsyVar);
        }
    }

    public static void b(int i, final ConfigCallBack<dsy> configCallBack) {
        dsy dsyVar;
        String d = HeartRateControlConfigHelper.c().d(i);
        Map<String, dsy> map = c;
        if (map.containsKey(d) && (dsyVar = map.get(d)) != null) {
            b(configCallBack, dsyVar);
        } else {
            HeartRateControlConfigHelper.c().e(new HeartRateControlConfigHelper.DownloadCallBack<dsy>() { // from class: czl.2
                @Override // com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper.DownloadCallBack
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onDownloadFinish(dsy dsyVar2) {
                    czl.b((ConfigCallBack<dsy>) ConfigCallBack.this, dsyVar2);
                }

                @Override // com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper.DownloadCallBack
                public void onDownloadFail(int i2, String str) {
                    LogUtil.h("HeartRateControlManager", "config onDownloadFail:", Integer.valueOf(i2), ",msg:", str);
                    czl.b((ConfigCallBack<dsy>) ConfigCallBack.this, (dsy) null);
                }
            });
            HeartRateControlConfigHelper.c().c(i);
        }
    }

    public static String b(int i, String str) {
        StringBuilder sb = new StringBuilder(10);
        sb.append(drd.d(HeartRateControlConfigHelper.c().b(i), "heart_rate_control_configuration", (String) null) + File.separator + "heart_rate_control_configuration" + File.separator + "img" + File.separator);
        sb.append(str);
        sb.append(".png");
        return CommonUtil.c(sb.toString());
    }

    public static String d(int i, String str) {
        return TextUtils.isEmpty(str) ? "" : c(i, str);
    }

    private static String c(int i, String str) {
        InputStream inputStream;
        NodeList childNodes;
        int length;
        NamedNodeMap attributes;
        Node namedItem;
        String str2 = "";
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            dea.c(newInstance);
        } catch (ParserConfigurationException e2) {
            LogUtil.b("HeartRateControlManager", "ResourceLoader getStringFormat e:", e2.getMessage());
        }
        InputStream inputStream2 = null;
        r4 = null;
        r4 = null;
        InputStream inputStream3 = null;
        try {
            try {
                inputStream = new FileInputStream(e(i));
            } catch (Throwable th) {
                th = th;
                inputStream = inputStream2;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (ParserConfigurationException e4) {
            e = e4;
        } catch (SAXException e5) {
            e = e5;
        }
        try {
            childNodes = newInstance.newDocumentBuilder().parse(inputStream).getDocumentElement().getChildNodes();
        } catch (IOException e6) {
            e = e6;
            inputStream3 = inputStream;
            LogUtil.b("HeartRateControlManager", "getStringFormat", e.getMessage());
            IoUtils.e(inputStream3);
            inputStream2 = inputStream3;
            return str2;
        } catch (ParserConfigurationException e7) {
            e = e7;
            inputStream3 = inputStream;
            LogUtil.b("HeartRateControlManager", "getStringFormat", e.getMessage());
            IoUtils.e(inputStream3);
            inputStream2 = inputStream3;
            return str2;
        } catch (SAXException e8) {
            e = e8;
            inputStream3 = inputStream;
            LogUtil.b("HeartRateControlManager", "getStringFormat", e.getMessage());
            IoUtils.e(inputStream3);
            inputStream2 = inputStream3;
            return str2;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(inputStream);
            throw th;
        }
        if (childNodes == null) {
            IoUtils.e(inputStream);
            return "";
        }
        int i2 = 0;
        while (true) {
            length = childNodes.getLength();
            if (i2 >= length) {
                break;
            }
            Node item = childNodes.item(i2);
            if (item.getNodeType() == 1 && (attributes = item.getAttributes()) != null && (namedItem = attributes.getNamedItem("name")) != null && str != null && str.equals(namedItem.getNodeValue())) {
                str2 = item.getTextContent().replace("\\n", System.lineSeparator());
            }
            i2++;
        }
        IoUtils.e(inputStream);
        inputStream2 = length;
        return str2;
    }

    private static String e(int i) {
        String language = Locale.getDefault().getLanguage();
        String b = b(language);
        StringBuilder sb = new StringBuilder(10);
        String str = drd.d(HeartRateControlConfigHelper.c().b(i), "heart_rate_control_configuration", (String) null) + File.separator + "heart_rate_control_configuration" + File.separator + "lang" + File.separator + "strings";
        sb.append(str);
        sb.append(language);
        sb.append(Constants.LINK);
        sb.append(b);
        sb.append(WatchFaceConstant.XML_SUFFIX);
        String c2 = CommonUtil.c(sb.toString());
        if (c2 != null && new File(c2).exists()) {
            return c2;
        }
        StringBuilder sb2 = new StringBuilder(10);
        sb2.append(str);
        sb2.append(WatchFaceConstant.XML_SUFFIX);
        return CommonUtil.c(sb2.toString());
    }

    private static String b(String str) {
        String country = Locale.getDefault().getCountry();
        if (MLAsrConstants.LAN_ZH.equals(str)) {
            return LanguageUtil.m(BaseApplication.getContext()) ? "rCN" : "HK".equals(country) ? "HK" : "TW";
        }
        if ("bo".equals(str)) {
            return "CN";
        }
        LogUtil.a("HeartRateControlManager", "is other language ", country);
        return country;
    }

    public static void d() {
        LogUtil.a("HeartRateControlManager", "cleanCache");
        e.clear();
        c.clear();
    }
}
