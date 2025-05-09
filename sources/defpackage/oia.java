package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.health.utils.functionsetcard.CardFlowInteractors;
import com.huawei.health.health.utils.functionsetcard.manager.OnCardChangedListener;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver;
import com.huawei.ui.homehealth.functionsetcard.manager.ICardManager;
import com.huawei.ui.homehealth.functionsetcard.manager.strategy.CardUpgradeContainer;
import com.huawei.ui.homehealth.util.FunctionCardBannerHelper;
import com.huawei.ui.homehealth.util.FunctionSetCardMarketingUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class oia implements ICardManager, OnCardChangedListener, FunctionSetCardObserver {

    /* renamed from: a, reason: collision with root package name */
    private static volatile oia f15690a;
    private FunctionCardBannerHelper d;
    private List<String> f;
    private ohz g;
    private ohz h;
    private int i;
    private c j;
    private oij k;
    private oik o;
    private ohz u;
    private FunctionSetCardMarketingUtil x;
    private oii z;
    final ReentrantReadWriteLock e = new ReentrantReadWriteLock();
    private List<FunctionSetCardObserver> l = new CopyOnWriteArrayList();
    private List<CardConfig> y = new CopyOnWriteArrayList();
    private List<CardConfig> r = new CopyOnWriteArrayList();
    private Map<String, CardConstructor> b = new ConcurrentHashMap();
    private Map<String, String> c = new HashMap();
    private SparseArray<List<String>> n = new SparseArray<>();
    private boolean s = false;
    private boolean q = false;
    private boolean v = false;
    private boolean w = false;
    private boolean t = false;
    private int p = -1;
    private BroadcastReceiver m = new BroadcastReceiver() { // from class: oia.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getIntExtra("refresh_type", -1) == 0) {
                LogUtil.a("FunctionCard_CardManager", "onReceive data sync completed");
                oia.this.s();
                oia.this.d.c(oia.this.d());
            }
        }
    };

    private oia() {
        long currentTimeMillis = System.currentTimeMillis();
        x();
        ReleaseLogUtil.e("FunctionSet_TIME", "init cost:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public static oia c() {
        synchronized (oia.class) {
            if (f15690a == null) {
                f15690a = new oia();
            }
        }
        return f15690a;
    }

    public void m() {
        ReleaseLogUtil.e("FunctionCard_CardManager", "context has been released, be careful of calling CardManager.xxx");
    }

    public static void e() {
        ReleaseLogUtil.e("FunctionCard_CardManager", "releaseInstance()");
        synchronized (oia.class) {
            if (f15690a != null) {
                f15690a.ak();
                f15690a = null;
            }
        }
    }

    private void ak() {
        ReleaseLogUtil.e("FunctionCard_CardManager", "release");
        Map<String, CardConstructor> map = this.b;
        if (map != null) {
            Iterator<CardConstructor> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            ReleaseLogUtil.e("FunctionCard_CardManager", "release PhysiologicalCycleCardConstructor broadCast over");
        }
        this.j.getLooper().quit();
        aw();
        oij oijVar = this.k;
        if (oijVar != null) {
            oijVar.release();
        }
        oii oiiVar = this.z;
        if (oiiVar != null) {
            oiiVar.release();
        }
        oik oikVar = this.o;
        if (oikVar != null) {
            oikVar.release();
        }
    }

    public void a(FunctionSetCardObserver functionSetCardObserver) {
        ReleaseLogUtil.e("FunctionCard_CardManager", "registerDataSetObserver ", functionSetCardObserver);
        if (functionSetCardObserver != null) {
            this.l.add(functionSetCardObserver);
        }
    }

    public void e(FunctionSetCardObserver functionSetCardObserver) {
        ReleaseLogUtil.e("FunctionCard_CardManager", "unregisterDataSetObserver ", functionSetCardObserver);
        if (functionSetCardObserver != null) {
            this.l.remove(functionSetCardObserver);
        }
    }

    public boolean f() {
        return this.w;
    }

    public List<CardConstructor> a() {
        LogUtil.a("FunctionCard_CardManager", "getAllShowCardConstructor start");
        List<CardConfig> j = j();
        ArrayList arrayList = new ArrayList(j.size());
        boolean o = Utils.o();
        ReleaseLogUtil.e("FunctionCard_CardManager", "getAllShowCardConstructor isOversea:", Boolean.valueOf(o));
        boolean k = efb.k();
        int i = 0;
        for (CardConfig cardConfig : j) {
            if (cardConfig == null) {
                LogUtil.b("FunctionCard_CardManager", "cardConfig is null");
            } else {
                String cardId = cardConfig.getCardId();
                if (cardId == null) {
                    LogUtil.b("FunctionCard_CardManager", "cardId is null");
                } else {
                    CardConstructor cardConstructor = this.b.get(cardId);
                    if (cardConstructor == null) {
                        LogUtil.b("FunctionCard_CardManager", "constructor is null");
                    } else {
                        if (k) {
                            if (i < 6) {
                                cardConstructor.setIsMessageLargeCard(1);
                            } else {
                                cardConstructor.setIsMessageLargeCard(0);
                            }
                            i++;
                        }
                        if (cardConstructor.isSupport(o, this.i)) {
                            arrayList.add(cardConstructor);
                        } else {
                            LogUtil.b("FunctionCard_CardManager", "notSupport constructor: ", cardConstructor);
                        }
                    }
                }
            }
        }
        ReleaseLogUtil.e("FunctionCard_CardManager", "getAllShowCardConstructor size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public List<CardConfig> g() {
        this.e.readLock().lock();
        if (koq.b(this.r)) {
            this.e.readLock().unlock();
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(this.r.size());
        for (CardConfig cardConfig : this.r) {
            if (cardConfig != null) {
                arrayList.add(new CardConfig(cardConfig));
            }
        }
        this.e.readLock().unlock();
        ReleaseLogUtil.e("FunctionCard_CardManager", "getHideCardConfigsFromCache size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public List<CardConfig> j() {
        this.e.readLock().lock();
        if (koq.b(this.y)) {
            this.e.readLock().unlock();
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(this.y.size());
        Iterator<CardConfig> it = this.y.iterator();
        while (it.hasNext()) {
            arrayList.add(new CardConfig(it.next()));
        }
        this.e.readLock().unlock();
        ReleaseLogUtil.e("FunctionCard_CardManager", "getShowCardConfigsFromCache size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public List<CardConfig> getUninitializedCardsFromCache() {
        this.e.readLock().lock();
        if (koq.b(this.r)) {
            this.e.readLock().unlock();
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(this.r.size());
        for (CardConfig cardConfig : this.r) {
            if (cardConfig != null && !cardConfig.isInitialized()) {
                arrayList.add(new CardConfig(cardConfig));
            }
        }
        this.e.readLock().unlock();
        ReleaseLogUtil.e("FunctionCard_CardManager", "getUninitializedCardsFromCache size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public void o() {
        if (this.u == null) {
            aj();
        } else {
            s();
        }
    }

    public void l() {
        FunctionCardBannerHelper functionCardBannerHelper = this.d;
        if (functionCardBannerHelper == null) {
            LogUtil.h("R_FunctionCard_CardManager", "functionCardBannerHelper is null");
        } else {
            functionCardBannerHelper.c(d());
        }
    }

    private void x() {
        z();
        ac();
        LogUtil.a("FunctionCard_CardManager", "mDefaultConfig-----");
        ae();
        b(this.h);
        af();
        ag();
        LogUtil.a("FunctionCard_CardManager", "mLastConfig-----");
        b(this.u);
        this.k = new oij(this);
        v();
        LogUtil.a("FunctionCard_CardManager", "ori mCurrentConfig-----");
        b(this.g);
        this.z = new oii(this);
        this.e.writeLock().lock();
        this.z.doStrategy();
        this.e.writeLock().unlock();
        this.o = new oik(this);
        LogUtil.a("FunctionCard_CardManager", "mCurrentConfig-----");
        b(this.g);
        ar();
        y();
        as();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final ohz ohzVar) {
        if (efb.k()) {
            this.j.post(new Runnable() { // from class: oia.8
                @Override // java.lang.Runnable
                public void run() {
                    oia.this.x.e(ohzVar.d(), new a());
                }
            });
        }
    }

    public void a(boolean z) {
        LogUtil.a("FunctionCard_CardManager", "setIsUpdateByMarketing: ", Boolean.valueOf(z));
        this.v = z;
        if (z) {
            this.w = false;
        }
        owi.d(z);
        this.x.b(z, new a());
        b(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<String> list) {
        LogUtil.a("FunctionCard_CardManager", "updateMessageCardConfig");
        if (!a(list)) {
            an();
        } else if (this.x == null) {
            LogUtil.a("FunctionCard_CardManager", "mMarketingUtil is null");
            an();
        } else {
            a(true);
            h(list, true);
        }
    }

    private void h(final List<String> list, final boolean z) {
        if (efb.k()) {
            LogUtil.a("FunctionCard_CardManager", "refreshCardByMessageOrUser, msg: " + z);
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "three_circle_edited_hide_or_show");
            String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "health_life_edited_hide_or_show");
            String name = CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName();
            if (oib.c(b2, b3)) {
                if ("threeLeafCard".equals(oun.a()) && list.contains("HEALTH_MODEL_CARD_KEY_NEW") && !list.contains(name)) {
                    list.set(list.indexOf("HEALTH_MODEL_CARD_KEY_NEW"), name);
                }
                if ("threeCircleCard".equals(oun.a()) && list.contains(name) && !list.contains("HEALTH_MODEL_CARD_KEY_NEW")) {
                    list.set(list.indexOf(name), "HEALTH_MODEL_CARD_KEY_NEW");
                }
            }
            String name2 = CardFlowInteractors.CardNameConstants.HEALTH_RECORD_CARD.getName();
            boolean a2 = SharedPreferenceManager.a(String.valueOf(10100), "health_record_auto_show", false);
            if (!z && a2 && !list.contains(name2)) {
                list.add(name2);
            }
            this.f = list;
            HandlerExecutor.a(new Runnable() { // from class: oia.7
                @Override // java.lang.Runnable
                public void run() {
                    oia.this.e.writeLock().lock();
                    oia.this.i(list, z);
                    oia.this.ao();
                    oia.this.e.writeLock().unlock();
                    if (!z) {
                        oia.this.ar();
                    }
                    oia.this.y();
                    oia.this.notifyAllCardChanged();
                }
            });
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public void replaceTwoModelCard(String str, String str2) {
        LinkedList<CardConfig> linkedList = new LinkedList<>();
        linkedList.addAll(this.g.d());
        CardConfig a2 = a(str, linkedList);
        CardConfig a3 = a(str2, linkedList);
        if (a3 == null || a2 == null) {
            return;
        }
        a2.setShow(false);
        a3.setShow(true);
        int indexOf = linkedList.indexOf(a2);
        linkedList.remove(a2);
        linkedList.remove(a3);
        if (indexOf < linkedList.size()) {
            linkedList.add(indexOf, a3);
        } else {
            linkedList.add(a3);
        }
        linkedList.add(a2);
        this.g.d(linkedList);
        ao();
        ar();
        y();
        notifyCardReplace(str, str2);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public void addTwoModelCard(int i, String str) {
        LinkedList<CardConfig> linkedList = new LinkedList<>();
        linkedList.addAll(this.g.d());
        CardConfig a2 = a(str, linkedList);
        if (a2 == null) {
            return;
        }
        linkedList.remove(a2);
        a2.setShow(true);
        if (i < linkedList.size()) {
            linkedList.add(i, a2);
        } else {
            linkedList.add(a2);
        }
        this.g.d(linkedList);
        ao();
        ar();
        y();
        notifyAllCardChanged();
    }

    private boolean p() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10100), "IS_OPEN_SMART_RECOMMEND_BY_USER");
        if (b2 != null && b2.equals(Boolean.toString(true))) {
            LogUtil.a("FunctionCard_CardManager", "recommend is opened by user");
            return false;
        }
        return this.g.e();
    }

    private void b(ohz ohzVar) {
        if (ohzVar == null) {
            LogUtil.a("FunctionCard_CardManager", "currentConfig is null");
            return;
        }
        LinkedList<CardConfig> d = ohzVar.d();
        if (koq.b(d)) {
            LogUtil.a("FunctionCard_CardManager", "cardConfigLinkedList is null");
            return;
        }
        for (int i = 0; i < d.size(); i++) {
            LogUtil.a("FunctionCard_CardManager", "currentConfig: ", Integer.valueOf(i), " ", d.get(i).getCardId(), " ", Integer.valueOf(d.get(i).getShowFlag()));
        }
    }

    private boolean a(List<String> list) {
        if (efb.e(BaseApplication.getContext())) {
            return false;
        }
        if (list == null) {
            LogUtil.a("FunctionCard_CardManager", "data is null");
            return false;
        }
        if (this.h == null) {
            return false;
        }
        if (!koq.b(list)) {
            return true;
        }
        LogUtil.a("FunctionCard_CardManager", "content size is invalid");
        return false;
    }

    private void am() {
        if (p()) {
            LogUtil.a("FunctionCard_CardManager", "hasLastUserCacheConfig");
        } else if ("0".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "personalized_recommend"))) {
            LogUtil.a("FunctionCard_CardManager", "PRIVACY_SWITCH_CLOSED");
            an();
        } else {
            this.x.d(new HiDataReadResultListener() { // from class: oia.9
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    if (obj == null) {
                        LogUtil.a("FunctionCard_CardManager", "object is null");
                        oia.this.an();
                        return;
                    }
                    LogUtil.a("FunctionCard_CardManager", "getUserRecommendSwitch errorCode: ", Integer.valueOf(i), ", object: ", obj.toString());
                    if (i != 0 || !oia.this.d(obj)) {
                        oia.this.al();
                    } else {
                        LogUtil.a("FunctionCard_CardManager", "UserRecommendSwitch closed");
                        oia.this.an();
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                    LogUtil.a("FunctionCard_CardManager", "getUserRecommendSwitch intentType ", Integer.valueOf(i), " object ", obj, " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
                    oia.this.al();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Object obj) {
        if (!(obj instanceof List)) {
            LogUtil.a("FunctionCard_CardManager", "not list");
            return false;
        }
        List list = (List) obj;
        if (list == null || list.size() == 0) {
            LogUtil.a("FunctionCard_CardManager", "list is null");
            return false;
        }
        Object obj2 = list.get(0);
        if (!(obj2 instanceof HiSampleConfig)) {
            LogUtil.a("FunctionCard_CardManager", "HiSampleConfig is null");
            return false;
        }
        String configData = ((HiSampleConfig) obj2).getConfigData();
        LogUtil.a("FunctionCard_CardManager", "data: ", configData);
        try {
            if (new JSONObject(configData).optInt("recommendOrderFlag") != 0) {
                return false;
            }
            LogUtil.a("FunctionCard_CardManager", "CLOSED");
            return true;
        } catch (JSONException unused) {
            LogUtil.a("FunctionCard_CardManager", "JSONException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        a(false);
        if (p()) {
            LogUtil.a("FunctionCard_CardManager", "hasLastUserCacheConfig");
        } else {
            this.x.e(new HiDataReadResultListener() { // from class: oia.15
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    LogUtil.a("FunctionCard_CardManager", "recoveryUserConfig errorCode: ", Integer.valueOf(i), ", object: ", obj);
                    if (i == 0) {
                        JSONObject e = oia.this.x.e(obj);
                        if (e != null) {
                            oia.this.a(e);
                        } else {
                            LogUtil.a("FunctionCard_CardManager", "config is null");
                        }
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                    LogUtil.a("FunctionCard_CardManager", "getUserConfig intentType ", Integer.valueOf(i), " object ", obj, " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        LogUtil.a("FunctionCard_CardManager", "updateCardByUserConfig");
        if (jSONObject == null) {
            LogUtil.a("FunctionCard_CardManager", "JSONObject is null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("userCardOrders");
        if (optJSONArray != null) {
            LogUtil.a("FunctionCard_CardManager", "updateCardByUserConfig : ", jSONObject.toString());
            LinkedList<CardConfig> d = this.g.d();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                if (optJSONArray.opt(i) instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                    String optString = jSONObject2.optString("cardId");
                    String optString2 = jSONObject2.optString("showFlag");
                    if (jSONObject2.has("isEdit")) {
                        int optInt = jSONObject2.optInt("isEdit");
                        CardConfig a2 = a(optString, d);
                        if (a2 != null) {
                            a2.setEditFlag(optInt);
                        }
                    }
                    if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && "1".equals(optString2)) {
                        arrayList.add(optString);
                    }
                }
            }
            LogUtil.a("FunctionCard_CardManager", "userCards: ", arrayList);
            this.w = true;
            h(arrayList, false);
            return;
        }
        LogUtil.a("FunctionCard_CardManager", "array is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        ReleaseLogUtil.e("R_FunctionCard_CardManager", "getAbStrategy");
        ThreadPoolManager.d().execute(new Runnable() { // from class: oia.13
            @Override // java.lang.Runnable
            public void run() {
                jbs.a(BaseApplication.getContext()).a("function_card", new jaf(), new ResultCallback<jae>() { // from class: oia.13.2
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(jae jaeVar) {
                        oia.this.b(jaeVar);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        ReleaseLogUtil.c("R_FunctionCard_CardManager", "get strategy error:", ExceptionUtils.d(th));
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(jae jaeVar) {
        if (jaeVar == null) {
            ReleaseLogUtil.d("R_FunctionCard_CardManager", "rsp is null.");
            au();
            return;
        }
        LogUtil.a("FunctionCard_CardManager", "rsp: ", jaeVar.toString());
        if (jaeVar.getResultCode().intValue() != 0) {
            ReleaseLogUtil.d("R_FunctionCard_CardManager", "getStrategy rsp resultCode is ", jaeVar.getResultCode());
            au();
            return;
        }
        if (koq.b(jaeVar.a()) || koq.b(jaeVar.e()) || koq.b(jaeVar.b())) {
            ReleaseLogUtil.d("R_FunctionCard_CardManager", "getStrategy rsp data empty");
            au();
            return;
        }
        String str = jaeVar.b().get(0);
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("R_FunctionCard_CardManager", "getStrategyName empty");
            au();
            return;
        }
        LogUtil.a("FunctionCard_CardManager", "json: ", nrv.a(jaeVar));
        if (str.equals("[B]")) {
            ReleaseLogUtil.e("R_FunctionCard_CardManager", "test B");
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "FUNCTION_SET_AB_SP_KEY", "[B]", (StorageParams) null);
            am();
        } else {
            au();
            ReleaseLogUtil.e("R_FunctionCard_CardManager", "test A");
        }
    }

    private void au() {
        an();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "FUNCTION_SET_AB_SP_KEY", "[A]", (StorageParams) null);
    }

    public boolean n() {
        if (this.p == -1) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "FUNCTION_SET_AB_SP_KEY");
            if (TextUtils.isEmpty(b2)) {
                this.p = 0;
            }
            this.p = b2.equals("[B]") ? 1 : 0;
        }
        return this.p == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        LogUtil.a("FunctionCard_CardManager", "all switch is opened, requestMessageCenter");
        this.x.a(new HttpResCallback() { // from class: oia.12
            @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
            public void onFinished(int i, String str) {
                LogUtil.a("FunctionCard_CardManager", "resCode: ", Integer.valueOf(i), ", response: ", str);
                if (TextUtils.isEmpty(str)) {
                    LogUtil.b("FunctionCard_CardManager", "response is null");
                    oia.this.an();
                    return;
                }
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray optJSONArray = new JSONObject(str).optJSONArray("sortedCardIds");
                    if (optJSONArray == null) {
                        LogUtil.b("FunctionCard_CardManager", "array is null");
                        oia.this.an();
                        return;
                    }
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        Object obj = optJSONArray.get(i2);
                        if (obj instanceof String) {
                            arrayList.add((String) obj);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        oia.this.d((List<String>) arrayList);
                    } else {
                        LogUtil.b("FunctionCard_CardManager", "list is null");
                        oia.this.an();
                    }
                } catch (JsonSyntaxException | JSONException unused) {
                    LogUtil.b("FunctionCard_CardManager", "JsonSyntaxException");
                    oia.this.an();
                }
            }
        });
    }

    public boolean h() {
        return this.v;
    }

    private void z() {
        int c2 = oib.c();
        this.i = c2;
        ReleaseLogUtil.e("FunctionCard_CardManager", "initGlobalEnvironment mCurrentVersionFlag: ", Integer.valueOf(c2));
    }

    private void ac() {
        HandlerThread handlerThread = new HandlerThread("card_manager_thread");
        handlerThread.start();
        this.j = new c(handlerThread.getLooper(), this);
        if (this.x == null) {
            this.x = new FunctionSetCardMarketingUtil(BaseApplication.getContext());
        }
        if (this.d == null) {
            this.d = new FunctionCardBannerHelper(BaseApplication.getContext(), new b(this));
        }
    }

    private void aj() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.m, intentFilter);
        LogUtil.a("FunctionCard_CardManager", "register success", this);
    }

    private void aw() {
        if (this.m != null) {
            try {
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.m);
                LogUtil.a("FunctionCard_CardManager", "unregister", this);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("FunctionCard_CardManager", "IllegalArgumentException unregister");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        this.j.post(new Runnable() { // from class: oia.14
            @Override // java.lang.Runnable
            public void run() {
                oia.this.e.readLock().lock();
                if (oia.this.g != null) {
                    oia oiaVar = oia.this;
                    oiaVar.c(oiaVar.g, 2);
                }
                oia.this.e.readLock().unlock();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final boolean z) {
        this.j.post(new Runnable() { // from class: oia.11
            @Override // java.lang.Runnable
            public void run() {
                oia.this.e.readLock().lock();
                if (oia.this.g != null) {
                    oia oiaVar = oia.this;
                    oiaVar.c(oiaVar.g, 2);
                }
                oia.this.e.readLock().unlock();
                if (z) {
                    oia oiaVar2 = oia.this;
                    oiaVar2.c(oiaVar2.g);
                    oia.this.a(!z);
                    return;
                }
                LogUtil.a("FunctionCard_CardManager", "edition this time not involve user data");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        List<CardConfig> j = j();
        for (int i = 0; i < j.size(); i++) {
            LogUtil.a("FunctionCard_CardManager", "showCard: " + j.get(i).getCardId());
        }
        List<CardConfig> g = g();
        for (int i2 = 0; i2 < g.size(); i2++) {
            LogUtil.a("FunctionCard_CardManager", "hideCard: " + g.get(i2).getCardId());
        }
        c(j);
        c(g);
        ReleaseLogUtil.e("FunctionCard_CardManager", "initAllCardConstructor size:", Integer.valueOf(this.b.size()));
    }

    public void c(List<CardConfig> list) {
        for (int i = 0; i < list.size(); i++) {
            CardConfig cardConfig = list.get(i);
            if (cardConfig != null) {
                String cardId = cardConfig.getCardId();
                if (!TextUtils.isEmpty(cardId)) {
                    CardConstructor cardConstructor = this.b.get(cardId);
                    if (cardConstructor == null) {
                        cardConstructor = b(cardConfig);
                        if (cardConstructor != null) {
                            this.b.put(cardId, cardConstructor);
                        }
                    }
                    b(cardId, cardConstructor);
                }
            }
        }
    }

    private CardConstructor a(String str, CardConfig cardConfig) {
        try {
            Object newInstance = Class.forName(str).getConstructor(CardConfig.class).newInstance(cardConfig);
            if (newInstance instanceof CardConstructor) {
                return (CardConstructor) newInstance;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            ReleaseLogUtil.c("FunctionCard_CardManager", "getConstructor failed, clsName", str, LogAnonymous.b(e));
            return null;
        }
    }

    private void as() {
        List<CardConfig> uninitializedCardsFromCache = getUninitializedCardsFromCache();
        if (koq.b(uninitializedCardsFromCache)) {
            ReleaseLogUtil.d("FunctionCard_CardManager", "startLongTermStrategy uninitializedCards is empty");
            return;
        }
        oik oikVar = this.o;
        if (oikVar != null) {
            oikVar.doStrategy();
        }
        oij oijVar = this.k;
        if (oijVar != null) {
            oijVar.doStrategy();
        }
        ReleaseLogUtil.e("FunctionCard_CardManager", "startLongTermStrategy end", Integer.valueOf(uninitializedCardsFromCache.size()));
    }

    private void v() {
        this.e.writeLock().lock();
        if (ah()) {
            LogUtil.a("FunctionCard_CardManager", "start type: isRestartApp");
            u();
        } else if (ai()) {
            LogUtil.a("FunctionCard_CardManager", "start type: isNewVersionUpdate");
            ad();
        } else {
            LogUtil.a("FunctionCard_CardManager", "start type: Old or same Version");
            aa();
        }
        this.e.writeLock().unlock();
        ReleaseLogUtil.e("FunctionCard_CardManager", "initCurrentConfig() end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(List<String> list, boolean z) {
        List<ohx> a2 = this.g.a();
        if (a2.size() != 4) {
            LogUtil.a("FunctionCard_CardManager", "default cardConfig group type is not 4, please check");
            return;
        }
        a2.get(3).c(list);
        LogUtil.a("FunctionCard_CardManager", "cardShowGroupList size: ", Integer.valueOf(a2.size()));
        ohz d = d(list, z);
        this.g = d;
        LinkedList<CardConfig> d2 = d.d();
        for (int i = 0; i < d2.size(); i++) {
            LogUtil.a("FunctionCard_CardManager", "UpdateByMarketing, cardId: ", d2.get(i).getCardId(), ", show: ", Boolean.valueOf(d2.get(i).isShow()));
        }
        LogUtil.a("FunctionCard_CardManager", "cardIds: ", list);
    }

    private void ad() {
        ohz ohzVar = this.h;
        if (ohzVar == null || this.u == null) {
            return;
        }
        String b2 = ohzVar.b();
        k();
        t();
        this.u.b(b2);
        this.g = this.u;
        ao();
    }

    private void t() {
        if (koq.b(this.h.d()) || koq.b(this.u.d())) {
            return;
        }
        HashSet hashSet = new HashSet();
        Iterator<CardConfig> it = this.u.d().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getCardId());
        }
        Iterator<CardConfig> it2 = this.h.d().iterator();
        while (it2.hasNext()) {
            CardConfig next = it2.next();
            if (!hashSet.contains(next.getCardId())) {
                this.u.d().add(next);
            }
        }
    }

    private void k() {
        if (koq.b(this.h.d()) || koq.b(this.u.d())) {
            return;
        }
        HashMap hashMap = new HashMap(this.h.d().size());
        Iterator<CardConfig> it = this.h.d().iterator();
        while (it.hasNext()) {
            CardConfig next = it.next();
            if (next != null) {
                hashMap.put(next.getCardId(), next);
            }
        }
        Iterator<CardConfig> it2 = this.u.d().iterator();
        while (it2.hasNext()) {
            CardConfig next2 = it2.next();
            if (next2 != null) {
                next2.update((CardConfig) hashMap.get(next2.getCardId()));
            }
        }
        if (this.n.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < this.n.size(); i++) {
            int keyAt = this.n.keyAt(i);
            arrayList.clear();
            arrayList.addAll(this.n.get(keyAt));
            ohx ohxVar = new ohx();
            ohxVar.b(keyAt);
            ohxVar.c(arrayList);
            arrayList2.add(ohxVar);
        }
        this.u.b(arrayList2);
    }

    private void aa() {
        ohz ohzVar = this.h;
        if (!q()) {
            ReleaseLogUtil.e("FunctionCard_CardManager", "initCurrentConfigWithOldVersion oldVersion");
            this.g = new CardUpgradeContainer(BaseApplication.getContext()).c(ohzVar);
            ao();
            ar();
        }
        if (ohzVar.e()) {
            return;
        }
        w();
    }

    private ohz d(List<String> list, boolean z) {
        LinkedList<CardConfig> d = this.g.d();
        if (d == null) {
            return this.g;
        }
        LinkedList<CardConfig> linkedList = new LinkedList<>();
        boolean d2 = oib.d();
        for (int i = 0; i < list.size(); i++) {
            CardConfig a2 = a(list.get(i), d);
            if (a2 != null && (!z || !"PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(a2.getCardId()) || d2)) {
                a2.setShow(true);
                linkedList.add(a2);
            }
        }
        Iterator<CardConfig> it = d.iterator();
        while (it.hasNext()) {
            CardConfig next = it.next();
            if (next != null && !linkedList.contains(next)) {
                next.setShow(false);
                linkedList.add(next);
            }
        }
        this.g.d(linkedList);
        return this.g;
    }

    private CardConfig a(String str, LinkedList<CardConfig> linkedList) {
        Iterator<CardConfig> it = linkedList.iterator();
        while (it.hasNext()) {
            CardConfig next = it.next();
            if (str.equals(next.getCardId())) {
                return next;
            }
        }
        return null;
    }

    private void w() {
        this.g = this.h;
        ao();
        ReleaseLogUtil.e("FunctionCard_CardManager", "initialization strategy start ");
        if (efb.e(BaseApplication.getContext())) {
            c(this.n.get(3), false);
        } else {
            c(this.n.get(4), false);
        }
        oij oijVar = this.k;
        if (oijVar != null) {
            oijVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        ReleaseLogUtil.e("FunctionCard_CardManager", "setMemoryByCurrentSetConfig");
        ohz ohzVar = this.g;
        if (ohzVar == null || koq.b(ohzVar.d())) {
            Object[] objArr = new Object[2];
            objArr[0] = "setMemoryByCurrentSetConfig mCurrentConfig is null ";
            objArr[1] = Boolean.valueOf(this.g == null);
            ReleaseLogUtil.d("FunctionCard_CardManager", objArr);
            return;
        }
        this.y.clear();
        this.r.clear();
        Iterator<CardConfig> it = this.g.d().iterator();
        while (it.hasNext()) {
            CardConfig next = it.next();
            if (next != null) {
                if (next.isShow()) {
                    this.y.add(next);
                } else {
                    this.r.add(next);
                }
            }
        }
    }

    private void b(String str, CardConstructor cardConstructor) {
        if (efb.k()) {
            if ((!this.v && !this.w) || koq.b(this.f) || cardConstructor == null) {
                return;
            }
            for (int i = 0; i < this.f.size(); i++) {
                String str2 = this.f.get(i);
                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                    if (i < 6) {
                        cardConstructor.setIsMessageLargeCard(1);
                    } else {
                        cardConstructor.setIsMessageLargeCard(0);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ap() {
        ReleaseLogUtil.e("FunctionCard_CardManager", "setCardConfigsByMemory");
        ohz ohzVar = this.g;
        if (ohzVar == null || ohzVar.d() == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "setCardConfigsByMemory mCurrentConfig is null ";
            objArr[1] = Boolean.valueOf(this.g == null);
            ReleaseLogUtil.d("FunctionCard_CardManager", objArr);
            return;
        }
        LinkedList<CardConfig> linkedList = new LinkedList<>();
        if (koq.c(this.y)) {
            linkedList.addAll(this.y);
        }
        if (koq.c(this.r)) {
            linkedList.addAll(this.r);
        }
        if (koq.c(linkedList)) {
            this.g.d(linkedList);
        }
    }

    private void c(List<String> list, boolean z) {
        if (list.isEmpty()) {
            return;
        }
        if (z) {
            onShowStatusChanged(list, true, -1);
        } else {
            c(list, true, -1);
        }
    }

    private void af() {
        if (this.h == null) {
            ReleaseLogUtil.c("FunctionCard_CardManager", "loadDefaultShowTypeGroups failed with null mDefaultConfig");
            return;
        }
        this.n.clear();
        for (ohx ohxVar : this.h.a()) {
            this.n.put(ohxVar.d(), ohxVar.c());
        }
    }

    private void u() {
        this.g = this.u;
        ao();
    }

    private boolean ah() {
        ohz ohzVar = this.h;
        return ohzVar != null && this.u != null && ohzVar.b().equals(this.u.b()) && a(this.h.d(), this.u.d());
    }

    private boolean a(LinkedList<CardConfig> linkedList, LinkedList<CardConfig> linkedList2) {
        if (linkedList == null && linkedList2 == null) {
            return true;
        }
        return (linkedList == null || linkedList2 == null || linkedList.size() != linkedList2.size()) ? false : true;
    }

    private boolean ai() {
        ohz ohzVar = this.h;
        boolean z = (ohzVar == null || this.u == null || (!b(ohzVar.b(), this.u.b()) && a(this.h.d(), this.u.d()))) ? false : true;
        this.t = z;
        return z;
    }

    private boolean b(String str, String str2) {
        return CommonUtil.d(str, str2) > 0;
    }

    private boolean q() {
        return (ab() || this.u == null) ? false : true;
    }

    private boolean ab() {
        return this.h == null && this.u == null;
    }

    private void ae() {
        if (this.h == null) {
            AssetManager assets = BaseApplication.getContext().getAssets();
            try {
                if (assets.open("FunctionSetCardConfig.json") != null) {
                    this.h = (ohz) ixu.d(assets.open("FunctionSetCardConfig.json"), ohz.class);
                }
            } catch (IOException e) {
                ReleaseLogUtil.c("FunctionCard_CardManager", "loadDefaultCardSetConfig failed, ", LogAnonymous.b((Throwable) e));
            }
            Object[] objArr = new Object[2];
            objArr[0] = "mDefaultConfig : ";
            ohz ohzVar = this.h;
            objArr[1] = ohzVar == null ? 0 : ohzVar.b();
            ReleaseLogUtil.e("FunctionCard_CardManager", objArr);
        }
    }

    private void ag() {
        if (this.h == null) {
            ReleaseLogUtil.c("FunctionCard_CardManager", "loadLastCardSetConfig failed with defaultConfig is null ");
            return;
        }
        ohz i = i();
        this.u = i;
        Object[] objArr = new Object[2];
        objArr[0] = "mLastConfig : ";
        objArr[1] = i == null ? 0 : i.b();
        ReleaseLogUtil.e("FunctionCard_CardManager", objArr);
    }

    public ohz i() {
        try {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10100), this.h.c());
            ohz ohzVar = !TextUtils.isEmpty(b2) ? (ohz) nrv.b(b2, ohz.class) : null;
            if (ohzVar == null) {
                String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), this.h.c());
                ohzVar = !TextUtils.isEmpty(b3) ? (ohz) nrv.b(b3, ohz.class) : null;
                SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), this.h.c());
            }
            return ohzVar;
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("FunctionCard_CardManager", "getCardConfigFromDb failed, ", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final ohz ohzVar, final int i) {
        if (ohzVar == null || i <= 0) {
            ReleaseLogUtil.d("FunctionCard_CardManager", "setCardSetConfigToDb failed with error input config:", ohzVar, "saveTryTimes： ", Integer.valueOf(i));
            return;
        }
        try {
            this.e.readLock().lock();
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10100), ohzVar.c(), nrv.a(ohzVar), (StorageParams) null);
            LogUtil.a("FunctionCard_CardManager", "setCardSetConfigToSp with config is ", ohzVar.toString());
            ohz i2 = i();
            if (i2 != null && i2.c() != null && i2.c().equals(ohzVar.c())) {
                this.e.readLock().unlock();
                return;
            }
            Object[] objArr = new Object[2];
            objArr[0] = "setCardSetConfigToDb error, get config is :";
            objArr[1] = i2 == null ? Constants.NULL : i2.toString();
            ReleaseLogUtil.c("FunctionCard_CardManager", objArr);
            c cVar = this.j;
            if (cVar != null) {
                cVar.postDelayed(new Runnable() { // from class: oia.1
                    @Override // java.lang.Runnable
                    public void run() {
                        oia.this.c(ohzVar, i - 1);
                    }
                }, TimeUnit.SECONDS.toMillis(2L));
            }
            this.e.readLock().unlock();
        } catch (JsonIOException | ConcurrentModificationException e) {
            ReleaseLogUtil.c("FunctionCard_CardManager", "setCardSetConfigToDb failed, ", LogAnonymous.b(e));
            c cVar2 = this.j;
            if (cVar2 != null) {
                cVar2.postDelayed(new Runnable() { // from class: oia.5
                    @Override // java.lang.Runnable
                    public void run() {
                        oia.this.c(ohzVar, i - 1);
                    }
                }, TimeUnit.SECONDS.toMillis(2L));
            }
        }
    }

    public CardConfig a(String str, String str2) {
        LogUtil.a(str, "getCardConfigById cardId:", str2);
        return e(str2);
    }

    private CardConfig e(String str) {
        this.e.readLock().lock();
        ArrayList<CardConfig> arrayList = new ArrayList(this.y.size() + this.r.size());
        arrayList.addAll(this.y);
        arrayList.addAll(this.r);
        for (CardConfig cardConfig : arrayList) {
            if (cardConfig != null && cardConfig.getCardId().equals(str)) {
                this.e.readLock().unlock();
                return cardConfig;
            }
        }
        this.e.readLock().unlock();
        return null;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.OnCardChangedListener
    public void onShowStatusChanged(final String str, final boolean z, final int i) {
        this.j.post(new Runnable() { // from class: oia.3
            @Override // java.lang.Runnable
            public void run() {
                oia.this.d(str, z, i);
            }
        });
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public void onShowStatusChanged(final List<String> list, final boolean z, final int i) {
        if (koq.b(list)) {
            return;
        }
        this.j.post(new Runnable() { // from class: oia.2
            @Override // java.lang.Runnable
            public void run() {
                oia.this.c((List<String>) list, z, i);
            }
        });
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public List<String> getCardIdsByGroupId(int i) {
        return this.n.get(i, Collections.emptyList());
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public ohz getCurrentConfigs() {
        return this.g;
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public ohz getDefaultConfigs() {
        return this.h;
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public List<CardConfig> getCardConfigList(boolean z) {
        if (z) {
            return j();
        }
        return g();
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.ICardManager
    public CardConstructor getCardConstructorById(String str) {
        return this.b.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, boolean z, int i) {
        ReleaseLogUtil.e("FunctionCard_CardManager", "cardId:", str, " isShow:", Boolean.valueOf(z), " source", Integer.valueOf(i));
        CardConfig e = e(str);
        if (e == null) {
            return;
        }
        if ("PHYSIOLOGICAL_CYCLE_CARD_KEY_NEW".equals(str) && f()) {
            LogUtil.a("FunctionCard_CardManager", "IsUpdateBuUser, PHYSIOLOGICAL no changed by gender");
            return;
        }
        this.e.writeLock().lock();
        if (e.isInitialized() && e.isShow() == z) {
            ReleaseLogUtil.e("FunctionCard_CardManager", "cardConfig is not changed,cardId:", e.getCardId(), " cardConfig showFlag:", Integer.valueOf(e.getShowFlag()), " changed type: ", Boolean.valueOf(z));
            this.e.writeLock().unlock();
            return;
        }
        if (i == -1 && e.isInitialized()) {
            this.e.writeLock().unlock();
            return;
        }
        e.setShow(z);
        if (i == 1) {
            e.setEditFlag(i);
            this.g.e(true);
        }
        a(e);
        ap();
        this.e.writeLock().unlock();
        ar();
        notifyCardShowStatusChanged(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<String> list, boolean z, int i) {
        Object[] objArr = new Object[6];
        objArr[0] = "cardId:";
        objArr[1] = list == null ? null : Arrays.toString(list.toArray());
        objArr[2] = " isShow:";
        objArr[3] = Boolean.valueOf(z);
        objArr[4] = " source";
        objArr[5] = Integer.valueOf(i);
        ReleaseLogUtil.e("FunctionCard_CardManager", objArr);
        if (koq.b(list)) {
            return;
        }
        this.e.writeLock().lock();
        List<CardConfig> a2 = a(list, z);
        if (koq.b(a2)) {
            this.e.writeLock().unlock();
            return;
        }
        ArrayList arrayList = new ArrayList(a2.size());
        for (CardConfig cardConfig : a2) {
            if (i != -1 || !cardConfig.isInitialized()) {
                cardConfig.setShow(z);
                arrayList.add(cardConfig.getCardId());
                a(cardConfig);
                if (i == 1) {
                    this.g.e(true);
                    cardConfig.setEditFlag(i);
                }
            }
        }
        ap();
        this.e.writeLock().unlock();
        ar();
        if (koq.c(arrayList)) {
            notifyCardShowStatusChanged(arrayList, z);
        }
    }

    private List<CardConfig> a(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (str == null) {
                LogUtil.h("FunctionCard_CardManager", "cardId is null");
            } else {
                CardConfig e = e(str);
                if (e == null) {
                    LogUtil.h("FunctionCard_CardManager", "cardConfig is null, cardId:", str);
                } else if (e.isInitialized() && e.isShow() == z) {
                    LogUtil.h("FunctionCard_CardManager", "cardConfig is not changed,cardId:", e.getCardId(), " cardConfig showFlag:", Integer.valueOf(e.getShowFlag()), " changed type: ", Boolean.valueOf(z));
                } else {
                    arrayList.add(e);
                }
            }
        }
        return arrayList;
    }

    private void a(CardConfig cardConfig) {
        if (cardConfig.isShow() && this.r.contains(cardConfig)) {
            this.r.remove(cardConfig);
            this.y.add(cardConfig);
        }
        if (cardConfig.isShow() || !this.y.contains(cardConfig)) {
            return;
        }
        this.y.remove(cardConfig);
        this.r.add(cardConfig);
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyAllCardChanged() {
        if (koq.b(this.l)) {
            return;
        }
        Iterator<FunctionSetCardObserver> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().notifyAllCardChanged();
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardShowStatusChanged(String str, boolean z) {
        if (koq.b(this.l)) {
            return;
        }
        Iterator<FunctionSetCardObserver> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().notifyCardShowStatusChanged(str, z);
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardShowStatusChanged(List<String> list, boolean z) {
        if (koq.b(this.l)) {
            return;
        }
        Iterator<FunctionSetCardObserver> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().notifyCardShowStatusChanged(list, z);
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardOrderChanged(String str) {
        if (koq.b(this.l)) {
            return;
        }
        Iterator<FunctionSetCardObserver> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().notifyCardOrderChanged(str);
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardInsert(String str) {
        if (koq.b(this.l)) {
            return;
        }
        Iterator<FunctionSetCardObserver> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().notifyCardInsert(str);
        }
    }

    @Override // com.huawei.ui.homehealth.functionsetcard.manager.FunctionSetCardObserver
    public void notifyCardReplace(String str, String str2) {
        if (koq.b(this.l)) {
            return;
        }
        Iterator<FunctionSetCardObserver> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().notifyCardReplace(str, str2);
        }
    }

    public List<CardConfig> b() {
        LinkedList linkedList = new LinkedList();
        boolean o = Utils.o();
        for (CardConfig cardConfig : j()) {
            CardConstructor cardConstructor = this.b.get(cardConfig.getCardId());
            if (cardConstructor != null && cardConstructor.isSupport(o, this.i)) {
                linkedList.add(new CardConfig(cardConfig));
            }
        }
        for (CardConfig cardConfig2 : g()) {
            CardConstructor cardConstructor2 = this.b.get(cardConfig2.getCardId());
            if (cardConstructor2 != null && cardConstructor2.isSupport(o, this.i)) {
                linkedList.add(new CardConfig(cardConfig2));
            }
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<CardConfig> r() {
        ArrayList arrayList = new ArrayList(this.y.size() + this.r.size());
        arrayList.addAll(this.y);
        arrayList.addAll(this.r);
        return arrayList;
    }

    public void e(String str, String str2) {
        ReleaseLogUtil.e(str, " startHandler：", str2, "mCardManagerHandler", this.j);
        c cVar = this.j;
        if (cVar != null) {
            cVar.c();
        }
    }

    public void d(String str, String str2) {
        ReleaseLogUtil.e(str, " resumeHandler：", str2, "mCardManagerHandler", this.j);
        c cVar = this.j;
        if (cVar != null) {
            cVar.b();
        }
    }

    public void c(String str, String str2) {
        ReleaseLogUtil.e(str, " pauseHandler：", str2, "mCardManagerHandler", this.j);
        c cVar = this.j;
        if (cVar != null) {
            cVar.a();
        }
    }

    public void e(final List<ojs> list, final boolean z) {
        Object[] objArr = new Object[2];
        objArr[0] = "save edit cards:";
        objArr[1] = list == null ? null : Arrays.toString(list.toArray());
        ReleaseLogUtil.e("FunctionCard_CardManager", objArr);
        if (z) {
            LogUtil.a("FunctionCard_CardManager", "saveEditCards, tryRequestMarketing again");
            this.d.c(d());
        }
        this.j.post(new Runnable() { // from class: oia.10
            @Override // java.lang.Runnable
            public void run() {
                oia.this.b((List<ojs>) list, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<ojs> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        this.e.writeLock().lock();
        ohz ohzVar = this.g;
        if (ohzVar != null && z) {
            ohzVar.e(true);
            arrayList.addAll(this.g.d());
        }
        this.e.writeLock().unlock();
        if (!this.v) {
            b(list, arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (ojs ojsVar : list) {
            CardConfig e = e(ojsVar.d());
            if (e != null && e.getShowFlag() != ojsVar.c()) {
                e.setShowFlag(ojsVar.c());
                e.setEditFlag(1);
            }
            arrayList2.add(e);
        }
        ArrayList arrayList3 = new ArrayList();
        for (CardConfig cardConfig : r()) {
            if (!arrayList2.contains(cardConfig)) {
                arrayList3.add(cardConfig);
            }
        }
        e(arrayList2, arrayList3);
        d(z);
        ohy.c().e();
        notifyAllCardChanged();
    }

    private void e(List<CardConfig> list, List<CardConfig> list2) {
        this.e.writeLock().lock();
        this.y.clear();
        this.r.clear();
        for (CardConfig cardConfig : list) {
            if (cardConfig.isShow()) {
                this.y.add(cardConfig);
            } else {
                this.r.add(cardConfig);
            }
        }
        for (CardConfig cardConfig2 : list2) {
            if (cardConfig2.isShow()) {
                this.y.add(cardConfig2);
            } else {
                this.r.add(cardConfig2);
            }
        }
        ap();
        this.e.writeLock().unlock();
    }

    private void b(List<ojs> list, List<CardConfig> list2) {
        int b2 = b(list2, "HEALTH_MODEL_CARD_KEY_NEW");
        int b3 = b(list2, CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName());
        int c2 = c(list, "HEALTH_MODEL_CARD_KEY_NEW");
        int c3 = c(list, CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName());
        if (b2 != c2) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "health_life_edited_hide_or_show", Integer.toString(c2), new StorageParams());
        }
        if (b3 != c3) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "three_circle_edited_hide_or_show", Integer.toString(c3), new StorageParams());
        }
    }

    private int c(List<ojs> list, String str) {
        if (koq.b(list)) {
            return -1;
        }
        for (ojs ojsVar : list) {
            if (ojsVar != null && ojsVar.d().equals(str)) {
                return ojsVar.c();
            }
        }
        return -1;
    }

    private int b(List<CardConfig> list, String str) {
        if (koq.b(list)) {
            return -1;
        }
        for (CardConfig cardConfig : list) {
            if (cardConfig != null && cardConfig.getCardId().equals(str)) {
                return cardConfig.getShowFlag();
            }
        }
        return -1;
    }

    private void e(CardConfig cardConfig) {
        if (cardConfig != null && cardConfig.getCardId().equals("HEALTH_MODEL_CARD_KEY_NEW")) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "health_life_edited_hide_or_show", Integer.toString(2), new StorageParams());
        }
        if (cardConfig == null || !cardConfig.getCardId().equals(CardFlowInteractors.CardNameConstants.THREE_CIRCLE_CARD.getName())) {
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "three_circle_edited_hide_or_show", Integer.toString(2), new StorageParams());
    }

    public void c(final CardConfig cardConfig) {
        LogUtil.a("FunctionCard_CardManager", "saveEditDeleteCard, cardId: ", cardConfig.getCardId());
        e(cardConfig);
        this.j.post(new Runnable() { // from class: oia.6
            @Override // java.lang.Runnable
            public void run() {
                new ArrayList();
                oia.this.e.writeLock().lock();
                if (oia.this.g != null) {
                    oia.this.g.e(true);
                }
                oia.this.e.writeLock().unlock();
                String cardId = cardConfig.getCardId();
                List<CardConfig> r = oia.this.r();
                for (CardConfig cardConfig2 : r) {
                    if (cardConfig2.getCardId().equals(cardId)) {
                        cardConfig2.setShowFlag(2);
                        cardConfig2.setEditFlag(1);
                    }
                }
                oia.this.e.writeLock().lock();
                oia.this.y.clear();
                oia.this.r.clear();
                for (CardConfig cardConfig3 : r) {
                    if (cardConfig3.isShow()) {
                        oia.this.y.add(cardConfig3);
                    } else {
                        oia.this.r.add(cardConfig3);
                    }
                }
                oia.this.ap();
                oia.this.e.writeLock().unlock();
                oia.this.d(true);
                oia.this.a(false);
                if (oia.this.y.size() == 0) {
                    oia.this.notifyAllCardChanged();
                }
            }
        });
    }

    public void b(boolean z) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10100), "IS_OPEN_SMART_RECOMMEND_BY_USER", Boolean.toString(z), (StorageParams) null);
        if (z) {
            aq();
        }
    }

    private void aq() {
        SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), "three_circle_edited_hide_or_show");
        SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), "health_life_edited_hide_or_show");
    }

    static class c extends BaseHandler<oia> {

        /* renamed from: a, reason: collision with root package name */
        final LinkedList<Message> f15696a;
        private volatile boolean d;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: daP_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oia oiaVar, Message message) {
        }

        c(Looper looper, oia oiaVar) {
            super(looper, oiaVar);
            this.f15696a = new LinkedList<>();
        }

        public final void a() {
            this.d = true;
        }

        public final void c() {
            this.d = false;
        }

        public final void b() {
            this.d = false;
            while (this.f15696a.size() > 0) {
                Message pollFirst = this.f15696a.pollFirst();
                if (pollFirst != null) {
                    sendMessage(pollFirst);
                }
            }
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            if (this.d) {
                Message obtain = Message.obtain();
                obtain.copyFrom(message);
                this.f15696a.add(obtain);
                return;
            }
            super.dispatchMessage(message);
        }
    }

    private CardConstructor b(CardConfig cardConfig) {
        CardConstructor a2 = a(cardConfig.getCardConstructorCls(), cardConfig);
        if (a2 != null) {
            a2.init();
            a2.setOnChangedListener(this);
        }
        return a2;
    }

    static class a implements ResponseCallback<Object> {
        private a() {
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        public void onResponse(int i, Object obj) {
            LogUtil.a("FunctionCard_CardManager", "status: ", Integer.valueOf(i), ", data: ", obj);
        }
    }

    static class b implements FunctionCardBannerHelper.IResponseCallback {
        private final WeakReference<oia> d;

        b(oia oiaVar) {
            this.d = new WeakReference<>(oiaVar);
        }

        @Override // com.huawei.ui.homehealth.util.FunctionCardBannerHelper.IResponseCallback
        public void onResponse(int i) {
            oia oiaVar = this.d.get();
            if (oiaVar == null) {
                LogUtil.a("FunctionCard_CardManager", "cardManager has been recycled, do nothing...");
            } else if (i == 0) {
                LogUtil.b("FunctionCard_CardManager", "request bottom banner view success, refresh function card...");
                oiaVar.notifyAllCardChanged();
            } else {
                LogUtil.b("FunctionCard_CardManager", "request bottom banner view failed...");
            }
        }
    }

    public View daO_(String str, int i) {
        return this.d.diX_(str, i);
    }

    public List<String> d() {
        LogUtil.a("FunctionCard_CardManager", "getCardIds");
        List<CardConfig> j = j();
        ArrayList arrayList = new ArrayList();
        for (CardConfig cardConfig : j) {
            if (cardConfig != null && cardConfig.isShow()) {
                arrayList.add(cardConfig.getCardId());
            }
        }
        return arrayList;
    }
}
