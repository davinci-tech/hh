package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.msgcontent.NotificationMsgContent;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.connectivity.config.AUserProfile;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.impl.PersonalDataCallback;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.AchieveMessage;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.EventRecord;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.MessageReminder;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.RecentMonthRecord;
import com.huawei.pluginachievement.manager.model.RecentWeekRecord;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.manager.service.AchieveKakaCheckRecordObserver;
import com.huawei.pluginachievement.manager.service.AchieveKakaEventObserver;
import com.huawei.pluginachievement.manager.service.AchieveLevelInfoObserver;
import com.huawei.pluginachievement.manager.service.AchieveMedalResDownloadObserver;
import com.huawei.pluginachievement.manager.service.AchievePersonalDataObserver;
import com.huawei.pluginachievement.manager.service.AchieveServiceObserver;
import com.huawei.pluginachievement.manager.service.LanguageResReceiver;
import com.huawei.pluginachievement.manager.service.OnceMovementReceiver;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.up.utils.Constants;
import com.huawei.wearengine.sensor.DataResult;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class meh {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ExecutorService f14917a;
    private static long b;
    private static volatile meh d;
    private AchieveKakaEventObserver c;
    private AchieveServiceObserver e;
    private Context g;
    private volatile int h;
    private LanguageResReceiver i;
    private volatile int j;
    private OnceMovementReceiver k;
    private AUserProfile n;
    private Map<Integer, String> l = new HashMap(5);
    private Map<String, String> m = new HashMap(8);
    private int f = -1;

    public int c(int i) {
        if (i == 0) {
            return 7;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 2 || i == 3) {
            return 4;
        }
        if (i == 4 || i == 7) {
            return 7;
        }
        if (i == 8) {
            return 8;
        }
        if (i == 21 || i == 22) {
            return 6;
        }
        if (i != 24 && i != 25) {
            switch (i) {
                case 11:
                case 12:
                    return 5;
                case 13:
                case 14:
                    break;
                default:
                    return 2;
            }
        }
        return 1;
    }

    private meh(Context context) {
        Context context2 = BaseApplication.getContext();
        this.g = context2;
        b(context2);
    }

    public static meh c(Context context) {
        if (f14917a == null || f14917a.isShutdown()) {
            f14917a = Executors.newSingleThreadExecutor();
            f14917a.execute(new Runnable() { // from class: mej
                @Override // java.lang.Runnable
                public final void run() {
                    ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
                }
            });
        }
        if (d == null) {
            synchronized (meh.class) {
                if (d == null) {
                    d = new meh(context);
                }
            }
        }
        return d;
    }

    private void b(Context context) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "init()");
        d(context);
    }

    public LanguageResReceiver n() {
        return this.i;
    }

    public OnceMovementReceiver q() {
        return this.k;
    }

    public AUserProfile t() {
        return this.n;
    }

    public void ad() {
        this.n = ag();
        if (EnvironmentUtils.c()) {
            an();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        if (mds.a()) {
            f14917a.execute(new Runnable() { // from class: mem
                @Override // java.lang.Runnable
                public final void run() {
                    meh.this.an();
                }
            });
            return;
        }
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            return;
        }
        Map<String, String> info = adapter.getInfo(new String[]{"getDeviceInfo"});
        AUserProfile aUserProfile = this.n;
        if (aUserProfile != null) {
            aUserProfile.setBindDeviceType(info.get("productType"));
        }
    }

    private void ab() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "clear()");
        mee.b(this.g).a();
    }

    public static void a() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "release()");
        if (d != null) {
            d.ab();
        }
    }

    private void d(Context context) {
        mee.b(context);
        mdo.d(context);
        ad();
    }

    private void ak() {
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "registerMovementReceiver enter");
        if (this.k == null) {
            LogUtil.c("PLGACHIEVE_AchieveDataManager", "new mOnceMovementReceiver");
            this.k = new OnceMovementReceiver();
        }
        mea.b(this.g).registerBroadcast(this.k, "com.huawei.healthcloud.action.sendSportTrackDistance");
    }

    public mcz d(int i, Map<String, String> map) {
        AUserProfile aUserProfile = this.n;
        if (aUserProfile == null || TextUtils.isEmpty(aUserProfile.getHuid())) {
            ad();
        }
        AUserProfile aUserProfile2 = this.n;
        if (aUserProfile2 != null) {
            map.put("huid", aUserProfile2.getHuid());
        }
        if (!Utils.i()) {
            map.put("huid", "1");
        }
        return mee.b(this.g).b(i, map);
    }

    public boolean e(mcz mczVar) {
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "updateData(),params");
        if (mczVar == null) {
            return false;
        }
        AUserProfile aUserProfile = this.n;
        if (aUserProfile != null) {
            mczVar.setHuid(aUserProfile.getHuid());
        }
        return mee.b(this.g).a(mczVar);
    }

    public boolean c(mcz mczVar) {
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "updateData(),params");
        if (mczVar == null) {
            return false;
        }
        AUserProfile aUserProfile = this.n;
        if (aUserProfile != null) {
            mczVar.setHuid(aUserProfile.getHuid());
        }
        return mee.b(this.g).b(mczVar);
    }

    public List<mcz> b(int i, Map<String, String> map) {
        if (map == null) {
            return new ArrayList(0);
        }
        AUserProfile aUserProfile = this.n;
        if (aUserProfile != null) {
            map.put("huid", aUserProfile.getHuid());
        }
        if (!Utils.i()) {
            map.put("huid", "1");
        }
        return mee.b(this.g).d(i, map);
    }

    private void aj() {
        mff a2 = mff.a(this.g);
        if (a2 != null) {
            if (!a2.c()) {
                a2.b(this);
            }
            a2.a();
        }
    }

    public void w() {
        mdu b2 = mdu.b(this.g);
        if (b2 != null) {
            if (!b2.d()) {
                b2.b(this);
            }
            b2.a();
        }
    }

    public void d(String str) {
        if (!mcv.a()) {
            LogUtil.c("PLGACHIEVE_AchieveDataManager", "enter requestTextureRes return");
            return;
        }
        mdu b2 = mdu.b(this.g);
        if (b2 != null) {
            if (!b2.d()) {
                b2.b(this);
            }
            b2.a(str);
        }
    }

    public void y() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "enter requestTextureRes");
        mdv a2 = mdv.a(this.g);
        if (a2 != null) {
            if (!a2.c()) {
                a2.b(this);
            }
            a2.b();
        }
    }

    public void b(final CountDownLatch countDownLatch) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "Enter getTotalSteps");
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "getTotalSteps is null");
            mle.b(countDownLatch);
        } else {
            adapter.getTotalSteps(new AchieveCallback() { // from class: meh.4
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    if (obj == null) {
                        LogUtil.a("PLGACHIEVE_AchieveDataManager", "obj is null");
                        mle.b(countDownLatch);
                        return;
                    }
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "getTotalSteps.");
                    int b2 = mfg.b(String.valueOf(obj));
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("step", Integer.valueOf(b2));
                    mer.b(meh.this.g).c(String.valueOf(KakaConstants.TASK_STEPS_REACH_THREE_THOUSAND), hashMap);
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "deal dealStepsChangeTask.");
                    mle.b(countDownLatch);
                }
            });
        }
    }

    public void e(final CountDownLatch countDownLatch) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "Enter refreshRunTask");
        long currentTimeMillis = System.currentTimeMillis();
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "getTotalSteps is null");
            mle.b(countDownLatch);
        } else {
            adapter.readSingleTrackData(this.g, currentTimeMillis, currentTimeMillis, new IBaseResponseCallback() { // from class: meh.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    List list;
                    if (obj == null) {
                        LogUtil.h("PLGACHIEVE_AchieveDataManager", "readSingleTrackData is null");
                        mle.b(countDownLatch);
                        return;
                    }
                    if (koq.e(obj, HiHealthData.class)) {
                        list = (List) obj;
                        LogUtil.a("PLGACHIEVE_AchieveDataManager", "readSingleTrackData singleMovementRecords size ", Integer.valueOf(list.size()));
                    } else {
                        list = null;
                    }
                    ArrayList e = meh.this.e((List<HiHealthData>) list);
                    if (koq.b(e)) {
                        LogUtil.h("PLGACHIEVE_AchieveDataManager", "readSingleTrackData no single run track record!");
                    } else {
                        LogUtil.a("PLGACHIEVE_AchieveDataManager", "readSingleTrackData maxSingleTrack size ", Integer.valueOf(e.size()));
                        meh.this.a((ArrayList<TrackData>) e);
                    }
                    mle.b(countDownLatch);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArrayList<TrackData> arrayList) {
        mer.b(this.g).c(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<TrackData> e(List<HiHealthData> list) {
        ArrayList<TrackData> arrayList = new ArrayList<>();
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "the size of single run records is 0! ");
            return arrayList;
        }
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "single records size == ", Integer.valueOf(list.size()));
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HiHealthData next = it.next();
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(next.getMetaData(), HiTrackMetaData.class);
                int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
                int duplicated = hiTrackMetaData.getDuplicated();
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "getAbnormalTrack=", Integer.valueOf(abnormalTrack), " duplicateResult=", Integer.valueOf(duplicated));
                if (abnormalTrack == 0 && duplicated == 0) {
                    int sportDataSource = hiTrackMetaData.getSportDataSource();
                    if (sportDataSource == 2 || sportDataSource == 3) {
                        LogUtil.h("PLGACHIEVE_AchieveDataManager", "sportDataSource=", Integer.valueOf(sportDataSource));
                    } else {
                        int sportType = hiTrackMetaData.getSportType();
                        int totalDistance = hiTrackMetaData.getTotalDistance();
                        TrackData trackData = new TrackData();
                        trackData.saveDistance(totalDistance * 1.0f);
                        trackData.saveTrackTime(next.getStartTime());
                        trackData.setTotalTime(hiTrackMetaData.getTotalTime());
                        trackData.saveType(sportType);
                        trackData.saveEndTime(next.getEndTime());
                        trackData.savePaceMap(hiTrackMetaData.getPaceMap());
                        trackData.savePartTimeMap(hiTrackMetaData.getPartTimeMap());
                        trackData.saveBestPace(hiTrackMetaData.getBestPace());
                        trackData.saveSportDataSource(hiTrackMetaData.getSportDataSource());
                        if (mfg.e(trackData.acquireType())) {
                            arrayList.add(trackData);
                            break;
                        }
                    }
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h("PLGACHIEVE_AchieveDataManager", "trackMetaData is error");
            }
        }
        return arrayList;
    }

    public ArrayList<String> r() {
        long j;
        HashMap hashMap = new HashMap(2);
        ArrayList<String> arrayList = new ArrayList<>(8);
        List<mcz> b2 = b(8, hashMap);
        if (b2 == null) {
            return arrayList;
        }
        HashMap hashMap2 = new HashMap(b2.size());
        for (mcz mczVar : b2) {
            if (mczVar instanceof MedalLocation) {
                MedalLocation medalLocation = (MedalLocation) mczVar;
                if (medalLocation.acquireGainedCount() > 0) {
                    try {
                        j = Long.parseLong(medalLocation.acquireMedalGainedTime());
                    } catch (NumberFormatException unused) {
                        LogUtil.b("PLGACHIEVE_AchieveDataManager", "getData() NumberFormatException");
                        j = 0;
                    }
                    arrayList.add(medalLocation.acquireMedalID());
                    hashMap2.put(medalLocation.acquireMedalID(), Long.valueOf(j));
                }
            }
        }
        return mjm.d(arrayList, hashMap2);
    }

    public Map<String, String> s() {
        mcz d2 = d(5, new HashMap(2));
        if (d2 == null) {
            return new HashMap(0);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("timestamp", String.valueOf(((AchieveInfo) d2).getSyncTimestamp()));
        return hashMap;
    }

    public void a(int i, Map<String, String> map) {
        c(i, new JSONObject(map));
    }

    public void b(int i, Map<String, String> map, AchieveCallback achieveCallback) {
        c(i, new JSONObject(map), achieveCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        if (mds.a()) {
            f14917a.execute(new Runnable() { // from class: meq
                @Override // java.lang.Runnable
                public final void run() {
                    meh.this.ae();
                }
            });
            return;
        }
        Map<String, String> s = s();
        s.put("countryCode", LoginInit.getInstance(this.g).getAccountInfo(1010));
        b(s);
    }

    public void b(Map<String, String> map) {
        if (!b()) {
            a(0, map);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "doRefreshPersonal isFastRequest!");
        }
    }

    public static boolean b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - b < ProfileExtendConstants.TIME_OUT) {
            return true;
        }
        b = elapsedRealtime;
        return false;
    }

    public void c(int i, JSONObject jSONObject) {
        c(i, jSONObject, (AchieveCallback) null);
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void c(final int i, final JSONObject jSONObject, final AchieveCallback achieveCallback) {
        if (mds.a()) {
            f14917a.execute(new Runnable() { // from class: mep
                @Override // java.lang.Runnable
                public final void run() {
                    meh.this.c(i, jSONObject, achieveCallback);
                }
            });
            return;
        }
        if (!mcv.e(i)) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "doRefresh: isSupportRequest -> false");
            return;
        }
        if (this.n == null || this.g == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "doRefresh: : mUserProfile or mApplicationContext is null");
            return;
        }
        int c = c(i);
        this.n.setiVersion(c);
        if (TextUtils.isEmpty(this.n.getToken())) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "doRefresh: token is empty, refresh");
            PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
            if (adapter != null) {
                this.n.setToken(adapter.getInfo(new String[]{"getLoginInfo"}).get("severToken"));
            }
        }
        mdo.d(this.g).e(i, this.n, jSONObject, c, achieveCallback);
    }

    public void b(AchieveObserver achieveObserver) {
        if (achieveObserver == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "registerObserver object maybe not create.");
        } else {
            mee.b(this.g).e(achieveObserver);
        }
    }

    public void c(AchieveObserver achieveObserver) {
        if (achieveObserver == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "unregisterObserver object maybe not create.");
        } else {
            mee.b(this.g).a(achieveObserver);
        }
    }

    public void c(AchievePersonalDataObserver achievePersonalDataObserver) {
        if (achievePersonalDataObserver == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "registerPersonalDataObserver object maybe not create.");
        } else {
            mee.b(this.g).e(achievePersonalDataObserver);
        }
    }

    private AUserProfile ag() {
        AUserProfile aUserProfile = new AUserProfile();
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter != null) {
            Map<String, String> info = adapter.getInfo(new String[]{Constants.METHOD_GET_USER_INFO, "getAppInfo", "getPhoneInfo"});
            aUserProfile.setHuid(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            aUserProfile.setBindDeviceType("");
            aUserProfile.setAppVersion(info.get("grayVersion"));
            aUserProfile.setTokenType(mfg.b());
            aUserProfile.setAppId(info.get("appId"));
            aUserProfile.setDeviceType(info.get("deviceType"));
            String str = info.get("deviceId");
            if (mmb.b(str)) {
                str = "clientnull";
            }
            aUserProfile.setDeviceId(str);
            aUserProfile.setSysVersion(info.get("sysVersion"));
            aUserProfile.setAppType(mfg.b(info.get("appType")));
            aUserProfile.setiVersion(mfg.b(info.get("iversion")));
            aUserProfile.setLanguage(mfg.e());
            aUserProfile.setEnvironment(mfg.b(info.get("environment")));
            aUserProfile.setUpDeviceType(info.get("upDeviceType"));
        }
        return aUserProfile;
    }

    public MessageObject e(int i, int i2, long j) {
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgTitle(this.g.getString(R.string._2130840728_res_0x7f020c98));
        messageObject.setMsgContent(this.g.getString(R.string._2130840729_res_0x7f020c99));
        messageObject.setFlag(0);
        messageObject.setMsgType(2);
        messageObject.setWeight(0);
        messageObject.setReadFlag(0);
        messageObject.setExpireTime(b(j).getTime());
        messageObject.setCreateTime(new Date().getTime());
        messageObject.setDetailUri("messagecenter://sportReport?report_stype=0&min_report_no=" + i + "&max_report_no=" + i2);
        messageObject.setPosition(1);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(this.n.getHuid());
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "getReportMsg MessageObject=", messageObject.toString());
        return messageObject;
    }

    public MessageObject e(MedalConfigInfo medalConfigInfo) {
        String c = c(medalConfigInfo);
        MessageObject messageObject = new MessageObject();
        try {
            messageObject.setMsgTitle(this.g.getString(R.string._2130840818_res_0x7f020cf2));
            messageObject.setMsgContent(this.g.getString(R.string._2130840819_res_0x7f020cf3, c));
        } catch (IllegalFormatConversionException | MissingFormatArgumentException unused) {
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "getAchieveMedalMsg string format exception");
        }
        messageObject.setFlag(0);
        messageObject.setMsgType(2);
        messageObject.setWeight(0);
        messageObject.setReadFlag(0);
        messageObject.setExpireTime(b(medalConfigInfo.acquireEndTime()));
        messageObject.setCreateTime(new Date().getTime());
        messageObject.setDetailUri(d(medalConfigInfo));
        messageObject.setPosition(1);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(this.n.getHuid());
        messageObject.setModule("18");
        messageObject.setMetaData(messageObject.getMsgTitle());
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "getReportMsg MessageObject=", messageObject.toString());
        return messageObject;
    }

    private String d(MedalConfigInfo medalConfigInfo) {
        return "messagecenter://achieveMedal?medalId=" + medalConfigInfo.acquireMedalID();
    }

    private long b(String str) {
        long j;
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "string2Long");
            j = 0;
        }
        return j == 0 ? j : j + 259200000;
    }

    private String c(MedalConfigInfo medalConfigInfo) {
        if (medalConfigInfo == null) {
            return "";
        }
        String acquireMedalName = medalConfigInfo.acquireMedalName();
        if (!mcv.e()) {
            return acquireMedalName;
        }
        String d2 = mdn.d(medalConfigInfo.acquireMedalType(), String.valueOf(medalConfigInfo.acquireMedalLevel()));
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "getMedalInfoDescData typeLevel=", d2);
        MedalInfo medalInfo = mla.e().c(true).get(d2);
        return medalInfo != null ? medalInfo.getText() : acquireMedalName;
    }

    public MessageObject c(int i, int i2) {
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgTitle(this.g.getString(R.string._2130840727_res_0x7f020c97));
        messageObject.setMsgContent(this.g.getString(R.string._2130840730_res_0x7f020c9a));
        messageObject.setFlag(0);
        messageObject.setMsgType(2);
        messageObject.setWeight(0);
        messageObject.setReadFlag(0);
        messageObject.setExpireTime(0L);
        messageObject.setCreateTime(new Date().getTime());
        messageObject.setDetailUri("messagecenter://sportReport?report_stype=1&min_report_no=" + i + "&max_report_no=" + i2);
        messageObject.setPosition(1);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(this.n.getHuid());
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "getReportMsg MessageObject=", messageObject.toString());
        return messageObject;
    }

    private Date b(long j) {
        int i;
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date(j));
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        if (i3 == 11) {
            i2++;
            i = i3 - 11;
        } else {
            i = i3 + 1;
        }
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        calendar.set(1, i2);
        calendar.set(2, i);
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, i4);
        calendar.set(12, i5);
        calendar.set(13, i6);
        return calendar.getTime();
    }

    public void d(UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "enter processMessageRemind messages");
        MessageReminder msgReminder = userAchieveWrapper.getMsgReminder();
        if (msgReminder != null) {
            List<AchieveMessage> c = c(msgReminder.getMessages());
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "processMessageRemind messages:", c);
            if (c != null) {
                for (AchieveMessage achieveMessage : c) {
                    String str = achieveMessage.getMsgType() + achieveMessage.acquireMedalId() + achieveMessage.acquireMedalType() + achieveMessage.acquireMedalLevel();
                    boolean containsKey = this.m.containsKey(str);
                    boolean z = false;
                    boolean z2 = !c(achieveMessage.acquireMedalId(), achieveMessage.getGainTime(), false);
                    if (mlb.n(achieveMessage.acquireMedalType())) {
                        str = achieveMessage.getMsgType() + achieveMessage.acquireMedalId() + achieveMessage.acquireMedalType() + achieveMessage.acquireMedalLevel() + achieveMessage.getGainTime();
                        LogUtil.a("PLGACHIEVE_AchieveDataManager", "processMessageRemind key = ", str);
                        containsKey = this.m.containsKey(str);
                    } else {
                        z = z2;
                    }
                    boolean c2 = c(achieveMessage.acquireMedalId());
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "processMessageRemind isContainKey = ", Boolean.valueOf(containsKey), " isRepeat = ", Boolean.valueOf(z), " isOverTime = ", Boolean.valueOf(c2));
                    if (containsKey || z || c2) {
                        LogUtil.h("PLGACHIEVE_AchieveDataManager", "processMessageRemind repeat msg!");
                    } else {
                        this.m.put(str, str);
                        if (achieveMessage.getMsgType() == 1) {
                            LogUtil.a("PLGACHIEVE_AchieveDataManager", "new Version not deal level msg!");
                        } else {
                            b(achieveMessage);
                            LogUtil.a("PLGACHIEVE_AchieveDataManager", "sendMessageToDevice Remind medalId:", achieveMessage.acquireMedalId());
                            e(achieveMessage);
                        }
                    }
                }
            }
        }
        z();
    }

    private void b(final AchieveMessage achieveMessage) {
        if (achieveMessage == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "AchieveMessage msg is null!");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: meh.9
                @Override // java.lang.Runnable
                public void run() {
                    MedalLocation medalLocation = new MedalLocation();
                    medalLocation.saveGainedCount(1);
                    if (mlb.n(achieveMessage.acquireMedalType())) {
                        medalLocation.saveGainedCount(achieveMessage.acquireGainCount() != 0 ? achieveMessage.acquireGainCount() : 1);
                    }
                    medalLocation.saveMedalGainedTime(achieveMessage.getGainTime());
                    medalLocation.saveMedalID(achieveMessage.acquireMedalId());
                    meh.this.e(medalLocation);
                }
            });
        }
    }

    private void z() {
        String b2 = mct.b(this.g, "_uploadMedal");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealNotUploadMedal medal is null");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealNotUploadMedal=", b2);
        final String[] split = b2.split(",");
        f14917a.execute(new Runnable() { // from class: meh.8
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList(split.length);
                for (String str : split) {
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
                }
                List<mcz> b3 = meh.this.b(9, new HashMap(2));
                if (b3 == null) {
                    return;
                }
                for (mcz mczVar : b3) {
                    if (mczVar instanceof MedalConfigInfo) {
                        MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                        if (arrayList.contains(medalConfigInfo.acquireMedalID())) {
                            LogUtil.a("PLGACHIEVE_AchieveDataManager", "NotUploadMedal medalID:", medalConfigInfo.acquireMedalID());
                            Map<String, String> a2 = meh.this.a(medalConfigInfo.acquireMedalID(), String.valueOf(medalConfigInfo.acquireMedalLevel()));
                            a2.put("countryCode", LoginInit.getInstance(meh.this.g).getAccountInfo(1010));
                            meh.this.a(7, a2);
                        }
                    }
                }
            }
        });
    }

    public Map<String, String> a(String str, String str2) {
        return c(str, str2, System.currentTimeMillis());
    }

    public Map<String, String> c(String str, String str2, long j) {
        HashMap hashMap = new HashMap(3);
        hashMap.put(ParsedFieldTag.MEDAL_ID, str);
        hashMap.put(ParsedFieldTag.MEDAL_LEVEL, str2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        hashMap.put(ParsedFieldTag.TAKE_DATE, simpleDateFormat.format(new Date(j)));
        return hashMap;
    }

    private List<AchieveMessage> c(List<AchieveMessage> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        HashMap hashMap = new HashMap(list.size());
        ArrayList arrayList = new ArrayList(list.size());
        for (AchieveMessage achieveMessage : list) {
            String acquireMedalType = achieveMessage.acquireMedalType();
            if (hashMap.get(acquireMedalType) != null) {
                if (((Integer) hashMap.get(acquireMedalType)).intValue() < achieveMessage.acquireMedalLevel()) {
                    hashMap.put(achieveMessage.acquireMedalType(), Integer.valueOf(achieveMessage.acquireMedalLevel()));
                }
            } else {
                hashMap.put(achieveMessage.acquireMedalType(), Integer.valueOf(achieveMessage.acquireMedalLevel()));
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            int intValue = ((Integer) entry.getValue()).intValue();
            Iterator<AchieveMessage> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    AchieveMessage next = it.next();
                    if (str.equals(next.acquireMedalType()) && intValue == next.acquireMedalLevel()) {
                        arrayList.add(next);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    public void v() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "registerAchieveMentReciever() enter");
        if (this.i == null) {
            LogUtil.c("PLGACHIEVE_AchieveDataManager", "new LanguageResReceiver");
            this.i = new LanguageResReceiver();
        }
        mea.b(this.g).registerBroadcast(this.i, "android.intent.action.LOCALE_CHANGED");
        ak();
    }

    public void u() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "noticeAchieveDialog()");
        if (this.g == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "popAchieveDialog() Context is null");
        } else {
            ad();
        }
    }

    public void x() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "popAchieveDialog()");
        if (this.g == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "popAchieveDialog() Context is null");
            return;
        }
        ad();
        AchieveMedalResDownloadObserver achieveMedalResDownloadObserver = new AchieveMedalResDownloadObserver(this.g);
        AchieveLevelInfoObserver achieveLevelInfoObserver = new AchieveLevelInfoObserver(this.g);
        b(achieveMedalResDownloadObserver);
        b(achieveLevelInfoObserver);
        if (this.e == null) {
            this.e = new AchieveServiceObserver(this.g);
        }
        b(this.e);
        if (this.c == null) {
            this.c = new AchieveKakaEventObserver(this.g);
        }
        b(this.c);
        c();
        ah();
        ae();
        mes.c(this.g).a();
        a(11, new HashMap(8));
        a(13, new HashMap(8));
        am();
        mgx.e(this.g).c(this.g);
        aa();
        aj();
        b((CountDownLatch) null);
        g();
        af();
        bzv.a().syncInit();
        ehs.d().initThirdAuthorizeMgr();
        mct.b(this.g, "_medalPngStatusDownloadDoing", "done");
        mct.b(this.g, "_medalTextureStatusDownloadDoing", "done");
        mct.b(this.g, "generateMedalTime", "0");
    }

    private void ah() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "doMsgRemindRefresh enter.");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: meh.10
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "doMsgRemindRefresh doRefresh MSG_REMIND.");
                meh.this.a(4, new HashMap(8));
                mer.b(meh.this.g).d();
            }
        }, 3000L);
    }

    private void af() {
        long h = CommonUtil.h(SharedPreferenceManager.b(this.g, Integer.toString(10000), "hw_health_start_count_key"));
        if (h == 1) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "dealOverseaMedalData startCount = ", Long.valueOf(h));
            return;
        }
        ai();
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealOverseaMedalData generateMedalData.");
        mgx.e(this.g).a(this.g);
    }

    private void ai() {
        mjp.e(new AchieveCallback() { // from class: meh.6
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, Object obj) {
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "initMedalLayoutData success.");
            }
        });
    }

    public void c() {
        if (CommonUtil.bu()) {
            return;
        }
        if (TextUtils.isEmpty(mct.b(this.g, "clearReportContent"))) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "enter clearReportData()");
            SingleDayRecord singleDayRecord = new SingleDayRecord();
            TotalRecord totalRecord = new TotalRecord();
            AUserProfile aUserProfile = this.n;
            if (aUserProfile != null) {
                singleDayRecord.setHuid(aUserProfile.getHuid());
                mee.b(this.g).d(singleDayRecord);
                mee.b(this.g).d(totalRecord);
                mct.b(this.g, "clearReportContent", "done");
                return;
            }
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "clearReportData mUserProfile is null");
            return;
        }
        if (TextUtils.isEmpty(mct.b(this.g, "clearReportOverSea"))) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "enter clearReportData()");
            SingleDayRecord singleDayRecord2 = new SingleDayRecord();
            TotalRecord totalRecord2 = new TotalRecord();
            AUserProfile aUserProfile2 = this.n;
            if (aUserProfile2 != null) {
                singleDayRecord2.setHuid(aUserProfile2.getHuid());
                totalRecord2.setHuid(this.n.getHuid());
                mee.b(this.g).d(singleDayRecord2);
                mee.b(this.g).d(totalRecord2);
                mct.b(this.g, "clearReportOverSea", "done");
                return;
            }
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "clearReportData mUserProfile is null");
        }
    }

    public void f() {
        b(new AchieveServiceObserver(this.g));
        a(4, new HashMap(8));
    }

    private void am() {
        f14917a.execute(new Runnable() { // from class: meh.7
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap(8);
                if (mfg.e(meh.this.g)) {
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "saveCurrentLocalLanguage");
                    mfg.b(meh.this.g);
                    hashMap.put("countryCode", LoginInit.getInstance(meh.this.g).getAccountInfo(1010));
                    meh.this.a(8, hashMap);
                    mct.b(meh.this.g, "MedalConfigTime", "");
                    return;
                }
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "requestAllMedalConfig local language not changed");
                List<mcz> b2 = meh.this.b(9, new HashMap(2));
                if (b2 != null) {
                    boolean z = false;
                    boolean z2 = false;
                    boolean z3 = false;
                    long j = 0;
                    for (mcz mczVar : b2) {
                        if (mczVar instanceof MedalConfigInfo) {
                            MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                            j = Math.max(medalConfigInfo.acquireTimestamp(), j);
                            if ("A5".equals(medalConfigInfo.acquireMedalType())) {
                                z = true;
                            }
                            if ("D1".equals(medalConfigInfo.acquireMedalType())) {
                                z2 = true;
                            }
                            if ("B11".equals(medalConfigInfo.acquireMedalType())) {
                                z3 = true;
                            }
                        }
                    }
                    mct.b(meh.this.g, "MedalConfigTime", String.valueOf(j));
                    String d2 = meh.this.d(j, z, z2, z3);
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "requestAllMedalConfig with timestamp ", d2);
                    hashMap.put("timestamp", d2);
                    hashMap.put("countryCode", LoginInit.getInstance(meh.this.g).getAccountInfo(1010));
                    meh.this.a(8, hashMap);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(long j, boolean z, boolean z2, boolean z3) {
        long j2 = CommonUtil.cc() ? 1687968000000L : 1698768000000L;
        if (!z && j > 1675785600000L) {
            return String.valueOf(1675785600000L);
        }
        if (!z2 && j > 1687968000000L) {
            return String.valueOf(1687968000000L);
        }
        if (!z3 && j > j2) {
            return String.valueOf(j2);
        }
        return String.valueOf(j);
    }

    private void aa() {
        f14917a.execute(new Runnable() { // from class: meh.14
            @Override // java.lang.Runnable
            public void run() {
                List<mcz> b2 = meh.this.b(10, new HashMap(2));
                if (b2 == null) {
                    return;
                }
                for (mcz mczVar : b2) {
                    EventRecord eventRecord = mczVar instanceof EventRecord ? (EventRecord) mczVar : null;
                    HashMap hashMap = new HashMap(6);
                    if (eventRecord != null) {
                        LogUtil.c("PLGACHIEVE_AchieveDataManager", "medalConfigInfo=", eventRecord.toString());
                        long currentTimeMillis = System.currentTimeMillis();
                        hashMap.put("eventType", String.valueOf(eventRecord.acquireEventType()));
                        hashMap.put(MedalConstants.EVENT_KEY, eventRecord.acquireKey());
                        hashMap.put(MedalConstants.EVENT_KEYTYPE, eventRecord.acquireKeyType());
                        hashMap.put("value", "true");
                        hashMap.put("timestamp", String.valueOf(currentTimeMillis));
                        hashMap.put(ParsedFieldTag.MEDAL_ID, eventRecord.acquireMedalID());
                        if (MedalConstants.EVENT_NOT_UPLOAD.equals(eventRecord.acquireEventStatus())) {
                            meh.this.a(9, hashMap);
                        }
                    }
                }
            }
        });
    }

    public void e() {
        mei.e(d, this.g);
    }

    public void g() {
        if (ac()) {
            mer.b(this.g).b();
            f14917a.execute(new Runnable() { // from class: meh.15
                @Override // java.lang.Runnable
                public void run() {
                    MedalConfigInfo medalConfigInfo;
                    String acquireMedalType;
                    List<mcz> b2 = meh.this.b(9, new HashMap(2));
                    if (b2 == null) {
                        return;
                    }
                    for (mcz mczVar : b2) {
                        if ((mczVar instanceof MedalConfigInfo) && (acquireMedalType = (medalConfigInfo = (MedalConfigInfo) mczVar).acquireMedalType()) != null && acquireMedalType.length() >= 3) {
                            meh.this.b(medalConfigInfo);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MedalConfigInfo medalConfigInfo) {
        long j;
        long j2;
        long j3;
        long j4 = 0;
        if (mfg.e(medalConfigInfo.acquireStartTime()) && mfg.e(medalConfigInfo.acquireEndTime())) {
            try {
                j3 = Long.parseLong(medalConfigInfo.acquireStartTime());
            } catch (NumberFormatException unused) {
                j3 = 0;
            }
            try {
                j4 = Long.parseLong(medalConfigInfo.acquireEndTime());
            } catch (NumberFormatException unused2) {
                LogUtil.b("PLGACHIEVE_AchieveDataManager", "requestSportData NumberFormatException!");
                j = j3;
                j2 = j4;
                if (System.currentTimeMillis() <= 86400000 + j2) {
                    return;
                } else {
                    return;
                }
            }
            j = j3;
            j2 = j4;
        } else {
            j = 0;
            j2 = 0;
        }
        if (System.currentTimeMillis() <= 86400000 + j2 || System.currentTimeMillis() < j) {
            return;
        }
        if (!c(medalConfigInfo.acquireMedalID(), "", true)) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "requestSportData judgeMedalStatus repeat medal!");
        } else {
            c(medalConfigInfo, j, j2);
        }
    }

    private void c(MedalConfigInfo medalConfigInfo, long j, long j2) {
        final int acquireMedalUnit = medalConfigInfo.acquireMedalUnit();
        final String acquireMedalID = medalConfigInfo.acquireMedalID();
        final String acquireMedalType = medalConfigInfo.acquireMedalType();
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealPeriodSportMedal medalId == ", acquireMedalID, " medalUnit == ", Integer.valueOf(acquireMedalUnit), " medalType == ", acquireMedalType);
        if (mlb.k(acquireMedalType)) {
            return;
        }
        final int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
        if (mey.e(medalConfigInfo, this.g, j, j2)) {
            b(acquireMedalID, acquireMedalType, acquireMedalLevel);
        }
        a(medalConfigInfo, j, j2);
        if (acquireMedalUnit != 4 && acquireMedalUnit != 6) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "medalUnit Invalid");
        } else {
            mcv.d(this.g).getAdapter().toReadAggregateData(new AchieveCallback() { // from class: men
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public final void onResponse(int i, Object obj) {
                    meh.this.a(acquireMedalUnit, acquireMedalLevel, acquireMedalID, acquireMedalType, i, obj);
                }
            }, this.g, mfg.c(j, true), mfg.c(j2, false));
        }
    }

    /* synthetic */ void a(int i, int i2, String str, String str2, int i3, Object obj) {
        int i4;
        if (obj == null) {
            return;
        }
        try {
            List list = koq.e(obj, HiHealthData.class) ? (List) obj : null;
            if (list == null) {
                LogUtil.h("PLGACHIEVE_AchieveDataManager", "dataInfos is null");
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            if (i == 4) {
                i4 = hiHealthData.getInt(ParsedFieldTag.STEP_SUM);
            } else if (i == 6) {
                i4 = hiHealthData.getInt(ParsedFieldTag.CALORIES_SUM);
            } else {
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "medalUnit ！= UNIT_STEP and medalUnit ！= UNIT_CAL");
                i4 = -1;
            }
            LogUtil.c("PLGACHIEVE_AchieveDataManager", "requestSportData() dataInfos value:", Integer.valueOf(i4), "medalUnit=", Integer.valueOf(i));
            if (i4 >= i2) {
                b(str, str2, i2);
            }
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "requestSportData data ClassCastException");
        }
    }

    public void a(MedalConfigInfo medalConfigInfo, long j, long j2) {
        if (medalConfigInfo == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "dealPeriodSportMedal medalConfigInfo is null!");
            return;
        }
        int acquireActionType = medalConfigInfo.acquireActionType();
        if (acquireActionType == 11 || acquireActionType == 12 || acquireActionType == 10) {
            c(j, j2, acquireActionType, medalConfigInfo);
        }
    }

    public void c(long j, long j2, final int i, final MedalConfigInfo medalConfigInfo) {
        int i2;
        if (i == 11) {
            i2 = 258;
        } else if (i == 12) {
            i2 = 259;
        } else {
            if (i != 10) {
                LogUtil.h("PLGACHIEVE_AchieveDataManager", "dealSportMedal no achieve type!");
                return;
            }
            i2 = 257;
        }
        int i3 = i2;
        long c = mfg.c(j, true);
        long c2 = mfg.c(j2, false);
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealSportMedal startTime == ", Long.valueOf(c), " endTime == ", Long.valueOf(c2), " type == ", Integer.valueOf(i));
        mcv.d(this.g).getAdapter().getTrackListData(this.g, c, c2, i3, new AchieveCallback() { // from class: meh.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i4, final Object obj) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: meh.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Object obj2 = obj;
                        if (obj2 != null) {
                            try {
                                meh.this.d(i, medalConfigInfo, (List<HiHealthData>) (koq.e(obj2, HiHealthData.class) ? (List) obj : null));
                                return;
                            } catch (ClassCastException unused) {
                                LogUtil.b("PLGACHIEVE_AchieveDataManager", "dealRunData data ClassCastException");
                                return;
                            }
                        }
                        LogUtil.h("PLGACHIEVE_AchieveDataManager", "dealRunData obj null");
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, MedalConfigInfo medalConfigInfo, List<HiHealthData> list) {
        if (koq.b(list) || medalConfigInfo == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "computeRunTrackInfo dataInfos isEmpty || medalConfigInfo == null!");
            return;
        }
        String acquireMedalID = medalConfigInfo.acquireMedalID();
        String acquireMedalType = medalConfigInfo.acquireMedalType();
        int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealSportMedal medalId == ", acquireMedalID, " medalLevel == ", Integer.valueOf(acquireMedalLevel), " medalType == ", acquireMedalType);
        Iterator<HiHealthData> it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            HiTrackMetaData b2 = mgx.e(this.g).b(it.next().getMetaData());
            if (b2 != null) {
                i2 += b2.getTotalDistance();
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "dealSportMedal sumValue ==**", Integer.valueOf(i2), "**type== **", Integer.valueOf(i));
        if (i2 >= acquireMedalLevel * 1000) {
            b(acquireMedalID, acquireMedalType, acquireMedalLevel);
        }
    }

    public void b(String str, String str2, int i) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "enter generateMedalAndShowDialog!sendMessageToDevice, medalId=", str);
        d(str, str2, i, 0);
    }

    public void d(String str, String str2, int i, int i2) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "enter generateMedalAndShowDialog!sendMessageToDevice, medalId=", str);
        MedalLocation medalLocation = new MedalLocation();
        int i3 = i2 + 1;
        medalLocation.saveGainedCount(i3);
        medalLocation.saveMedalGainedTime(String.valueOf(System.currentTimeMillis()));
        medalLocation.saveMedalID(str);
        e(medalLocation);
        AchieveMessage achieveMessage = new AchieveMessage();
        achieveMessage.setMsgType(2);
        achieveMessage.saveMedalType(str2);
        achieveMessage.saveMedalLevel(i);
        achieveMessage.saveMedalId(str);
        achieveMessage.saveGainCount(i3);
        e(achieveMessage);
    }

    private void e(final AchieveMessage achieveMessage) {
        if (achieveMessage == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "popMedalDialog: achieveMessage is null");
            return;
        }
        String acquireMedalId = achieveMessage.acquireMedalId();
        String acquireMedalType = achieveMessage.acquireMedalType();
        if (mlb.i(acquireMedalType) && mlb.f(acquireMedalId)) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "popMedalDialog: intercept -> " + acquireMedalId);
            return;
        }
        a(acquireMedalId, acquireMedalType, new BaseResponseCallback() { // from class: meo
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            public final void onResponse(int i, Object obj) {
                meh.this.a(achieveMessage, i, (Boolean) obj);
            }
        });
    }

    /* synthetic */ void a(AchieveMessage achieveMessage, int i, Boolean bool) {
        LogUtil.h("PLGACHIEVE_AchieveDataManager", "sendMessageToDevice isSendDevice:", bool);
        if (bool.booleanValue()) {
            return;
        }
        c(achieveMessage);
    }

    private void c(AchieveMessage achieveMessage) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "intentMedalDialog medalId ", achieveMessage.acquireMedalId(), " medalLevel ", Integer.valueOf(achieveMessage.acquireMedalLevel()));
        if (met.d(achieveMessage)) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "intentMedalDialog: interceptMedal -> true");
            return;
        }
        if (!BaseApplication.isRunningForeground() || CommonUtil.x(this.g)) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "intentMedalDialog app is isRunningBackground!");
            return;
        }
        if (kwx.c()) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "intentMedalDialog app is sportOrFitting!");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.g, PersonalData.CLASS_NAME_PERSONAL_MEDAL_MESSAGE_DIALOG);
        intent.putExtra("message", achieveMessage);
        intent.addFlags(268435456);
        this.g.startActivity(intent);
    }

    private void a(String str, String str2, BaseResponseCallback<Boolean> baseResponseCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            baseResponseCallback.onResponse(0, false);
        } else {
            bzv.a().sendMessageToDevice(2, str, str2, baseResponseCallback);
            mct.b(this.g, "_achieve_gain_id_medal", str);
        }
    }

    public void d(int i, String str, long j) {
        if (!BaseApplication.isRunningForeground()) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "app is isRunningBackground!");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.g, PersonalData.CLASS_NAME_PERSONAL_REPORT_MESSAGE_DIALOG);
        intent.putExtra("dialogType", i);
        intent.putExtra("dialogValue", str);
        intent.putExtra("dialogDate", j);
        intent.addFlags(268435456);
        this.g.startActivity(intent);
    }

    public boolean c(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("medalID", str);
        mcz d2 = d(8, hashMap);
        if (d2 == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "judgeMedalStatus data == null");
            return true;
        }
        if (d2 instanceof MedalLocation) {
            MedalLocation medalLocation = (MedalLocation) d2;
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "judgeMedalStatus Medal == ", medalLocation.acquireMedalID(), " gainedCount == ", Integer.valueOf(medalLocation.acquireGainedCount()));
            if (medalLocation.acquireGainedCount() > 0) {
                if (z) {
                    LogUtil.h("PLGACHIEVE_AchieveDataManager", "judgeMedalStatus flag == ", Boolean.valueOf(z));
                    return false;
                }
                String acquireMedalGainedTime = medalLocation.acquireMedalGainedTime();
                if (!acquireMedalGainedTime.equals(str2)) {
                    LogUtil.h("PLGACHIEVE_AchieveDataManager", "judgeMedalStatus medalGainTime.equals(gainTime) medalGainTime ", acquireMedalGainedTime, " gainTime ", str2);
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<String> l() {
        ArrayList<String> p = p();
        ArrayList<String> o = koq.b(p) ? o() : p;
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "lightList sql ", Integer.valueOf(p.size()), " list ", Integer.valueOf(o.size()));
        return o;
    }

    public ArrayList<String> p() {
        ArrayList<String> arrayList = new ArrayList<>(0);
        List<mcz> b2 = b(8, new HashMap(2));
        if (b2 == null) {
            return arrayList;
        }
        for (mcz mczVar : b2) {
            if (mczVar instanceof MedalLocation) {
                MedalLocation medalLocation = (MedalLocation) mczVar;
                if (medalLocation.acquireGainedCount() > 0) {
                    arrayList.add(medalLocation.acquireMedalID());
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "getMedalLightList medalLightList == ", arrayList.toString());
        return arrayList;
    }

    public ArrayList<String> o() {
        ArrayList<String> arrayList = new ArrayList<>(0);
        JSONArray a2 = a(mct.b(BaseApplication.getContext(), "_medalGainLoadInfo"));
        if (a2 == null) {
            return arrayList;
        }
        int length = a2.length();
        for (int i = 0; i < length; i++) {
            Object opt = a2.opt(i);
            if (opt instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) opt;
                if (mdn.e(ParsedFieldTag.GAIN_COUNT, jSONObject) > 0) {
                    arrayList.add(mdn.b(ParsedFieldTag.MEDAL_ID, jSONObject));
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "getCloudMedalLightList lightList == ", arrayList.toString());
        return arrayList;
    }

    private JSONArray a(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "getCloudMedalLightList Exception:", e.getMessage());
            return null;
        }
    }

    public boolean c(String str) {
        MedalConfigInfo medalConfigInfo;
        String acquireMedalType;
        long j;
        HashMap hashMap = new HashMap(2);
        hashMap.put("medalID", str);
        mcz d2 = d(9, hashMap);
        if (d2 == null) {
            LogUtil.h("PLGACHIEVE_AchieveDataManager", "judgeMedalOverTime data == null");
            return false;
        }
        if ((d2 instanceof MedalConfigInfo) && (acquireMedalType = (medalConfigInfo = (MedalConfigInfo) d2).acquireMedalType()) != null && acquireMedalType.length() >= 3) {
            long currentTimeMillis = System.currentTimeMillis();
            String acquireEndTime = medalConfigInfo.acquireEndTime();
            if (TextUtils.isEmpty(acquireEndTime)) {
                LogUtil.h("PLGACHIEVE_AchieveDataManager", "judgeMedalOverTime endTime isEmpty");
                return false;
            }
            try {
                j = Long.parseLong(acquireEndTime);
            } catch (NumberFormatException unused) {
                LogUtil.b("PLGACHIEVE_AchieveDataManager", "isNeedShow NumberFormatException");
                j = 0;
            }
            if (currentTimeMillis - 259200000 > j) {
                LogUtil.h("PLGACHIEVE_AchieveDataManager", "isNeedShow =false");
                return true;
            }
        }
        return false;
    }

    public void j() {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateAchieveMessage()：");
        AchieveObserver achieveServiceObserver = new AchieveServiceObserver(this.g);
        this.h = -1;
        this.j = -1;
        b(achieveServiceObserver);
        HashMap hashMap = new HashMap(2);
        hashMap.put("reportNo", "0");
        mcz d2 = d(3, hashMap);
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("reportNo", "0");
        if (d2 instanceof RecentWeekRecord) {
            this.h = ((RecentWeekRecord) d2).acquireReportNo();
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateReportMessage() mLocalWeekReportNo = ", Integer.valueOf(this.h));
        } else {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateReportMessage() weekRecord is null!");
            this.h = 0;
        }
        mcz d3 = d(4, hashMap2);
        if (d3 instanceof RecentMonthRecord) {
            this.j = ((RecentMonthRecord) d3).acquireReportNo();
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateReportMessage() mLocalMonthReportNo = ", Integer.valueOf(this.j));
        } else {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateReportMessage() monthRecord is null!");
            this.j = 0;
        }
        HashMap hashMap3 = new HashMap(2);
        hashMap3.put(DBSessionCommon.COLUMN_TIME_ZONE, CommonUtil.am());
        a(2, hashMap3);
    }

    public void i() {
        long currentTimeMillis = System.currentTimeMillis();
        String b2 = mct.b(this.g, "_achieve_datastr");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        String substring = String.valueOf(new Timestamp(currentTimeMillis)).substring(0, 10);
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateKakaMessage() dataStr=", b2, " todayStr=", substring);
        if (b2.equals(substring)) {
            return;
        }
        mct.b(this.g, "_achieve_datastr", null);
        mct.b(this.g, "_achieve_flag", null);
        mct.b(this.g, "_achieve_calorie", null);
        mct.b(this.g, "_achieve_msg_id_kaka", null);
    }

    public PersonalData e(PersonalDataCallback personalDataCallback, Context context) {
        LogUtil.a("PLGACHIEVE_AchieveDataManager", "getPersonalData()");
        if (personalDataCallback == null) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "getPersonalData() (callback == null)");
            return null;
        }
        ad();
        if (!mcv.a()) {
            return PersonalData.builder().build();
        }
        AchievePersonalDataObserver achievePersonalDataObserver = new AchievePersonalDataObserver(this.g);
        mfg.c(this.l, this.g);
        achievePersonalDataObserver.c(personalDataCallback);
        c(achievePersonalDataObserver);
        Map<String, String> s = s();
        s.put("countryCode", LoginInit.getInstance(this.g).getAccountInfo(1010));
        b(s);
        return e(context);
    }

    public PersonalData e(Context context) {
        int c;
        String str;
        Map<String, String> hashMap = new HashMap<>(2);
        HashMap hashMap2 = new HashMap(2);
        hashMap2.put("reportNo", mct.e(context, "_weekReportNo", "0"));
        mcz d2 = d(3, hashMap2);
        mcz d3 = d(1, hashMap);
        mcz d4 = d(5, hashMap);
        AchieveInfo achieveInfo = d4 instanceof AchieveInfo ? (AchieveInfo) d4 : null;
        mcz d5 = d(14, hashMap);
        if (d5 instanceof AchieveUserLevelInfo) {
            c = ((AchieveUserLevelInfo) d5).acquireUserLevel();
        } else {
            c = achieveInfo != null ? mlc.c(achieveInfo.getUserReachStandardDays()) : 1;
        }
        mcz d6 = d(2, new HashMap<>(2));
        PersonalData.Builder withPersonalLevel = PersonalData.builder().withMedals(achieveInfo != null ? achieveInfo.acquireMedals() : "").withPersonalLevel(c);
        if (achieveInfo != null) {
            str = this.l.get(Integer.valueOf(achieveInfo.getUserLevel()));
        } else {
            str = this.l.get(1);
        }
        PersonalData.Builder withStepRanking = withPersonalLevel.withPersonalLevelDesc(str).withStepRanking(d2 instanceof RecentWeekRecord ? ((RecentWeekRecord) d2).acquireStepsRanking() : 0.0d);
        boolean z = d3 instanceof TotalRecord;
        return withStepRanking.withSumKakaNum(achieveInfo != null ? achieveInfo.getUserPoint() : 0).withSumDays(z ? ((TotalRecord) d3).getDays() : 0).withSumSteps(z ? ((TotalRecord) d3).getSteps() : 0.0d).withBestDaySteps(d6 instanceof SingleDayRecord ? ((SingleDayRecord) d6).getSteps() : 0).build();
    }

    public void h() {
        final long currentTimeMillis = System.currentTimeMillis();
        String b2 = mct.b(this.g, "_achieve_history_best_data");
        if (!TextUtils.isEmpty(b2)) {
            String substring = String.valueOf(new Timestamp(currentTimeMillis)).substring(0, 10);
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateHistoryBestMsg() dataStr=", b2, " todayStr=", substring);
            if (b2.equals(substring)) {
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "today allready send history best msg");
                return;
            }
        }
        if (mcv.d(this.g).getAdapter() != null) {
            mcv.d(this.g).getAdapter().getTotalSteps(new AchieveCallback() { // from class: meh.2
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    if (obj == null) {
                        LogUtil.a("PLGACHIEVE_AchieveDataManager", "obj is null");
                    } else {
                        meh.this.e(mfg.b(String.valueOf(obj)), currentTimeMillis);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, final long j) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: meh.5
            @Override // java.lang.Runnable
            public void run() {
                mcz d2 = meh.this.d(2, new HashMap(2));
                int steps = d2 instanceof SingleDayRecord ? ((SingleDayRecord) d2).getSteps() : 0;
                LogUtil.c("PLGACHIEVE_AchieveDataManager", "generateHistoryBestMsg() cur best step=", Integer.valueOf(steps));
                if (i <= steps || steps == 0) {
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateHistoryBestMsg() not more than history best");
                } else {
                    LogUtil.a("PLGACHIEVE_AchieveDataManager", "Now generateHistoryBestMsg ");
                    meh.this.a(i, j);
                }
            }
        });
    }

    public void b(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        String b2 = mct.b(this.g, "_achieve_history_best_data");
        if (!TextUtils.isEmpty(b2)) {
            String substring = String.valueOf(new Timestamp(currentTimeMillis)).substring(0, 10);
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateHistoryMostStep() dataStr=", b2, " todayStr=", substring);
            if (b2.equals(substring)) {
                LogUtil.a("PLGACHIEVE_AchieveDataManager", "today allready send history best msg");
                return;
            }
        }
        if (this.f < 0) {
            mcz d2 = d(2, new HashMap(2));
            this.f = d2 instanceof SingleDayRecord ? ((SingleDayRecord) d2).getSteps() : 0;
        }
        int i2 = this.f;
        if (i <= i2 || i2 == 0) {
            LogUtil.a("PLGACHIEVE_AchieveDataManager", "generateHistoryBestMsg() not more than history best");
        } else {
            this.f = -1;
            a(i, currentTimeMillis);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, long j) {
        AUserProfile aUserProfile = this.n;
        String huid = aUserProfile != null ? aUserProfile.getHuid() : "";
        if (mcv.d(this.g).getAdapter() != null) {
            MessageObject messageObject = new MessageObject();
            messageObject.setModule("achievement");
            messageObject.setType(NotificationMsgContent.MSG_TYPE_HISTORY_BEST_MSG);
            messageObject.setMsgTitle(this.g.getResources().getString(R.string._2130841796_res_0x7f0210c4));
            messageObject.setExpireTime(mfn.d(mfn.a("23:59:59")));
            messageObject.setDetailUri("messagecenter://historyBestMessage");
            messageObject.setHuid(huid);
            messageObject.setCreateTime(j);
            messageObject.setFlag(0);
            messageObject.setWeight(0);
            messageObject.setReadFlag(0);
            messageObject.setPosition(1);
            messageObject.setMsgType(2);
            messageObject.setNotified(0);
            messageObject.setImgUri("assets://localMessageIcon/ic_medal.png");
            messageObject.setMsgPosition(11);
            mcv.d(this.g).getAdapter().generateMessage(messageObject);
            LogUtil.c("PLGACHIEVE_AchieveDataManager", "generateHistoryBestMsg messageObject=", messageObject);
        }
        LogUtil.c("PLGACHIEVE_AchieveDataManager", "generateHistoryBestMsg messageObject ");
        mct.b(this.g, "_achieve_history_best_data", String.valueOf(new Timestamp(j)).substring(0, 10));
        if (Utils.o()) {
            return;
        }
        d(2, String.valueOf(i), System.currentTimeMillis());
    }

    private boolean ac() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String b2 = mct.b(this.g, "generateMedalTime");
        if (TextUtils.isEmpty(b2)) {
            mct.b(this.g, "generateMedalTime", String.valueOf(elapsedRealtime));
            return true;
        }
        try {
            if (elapsedRealtime - Long.parseLong(b2) <= 300000) {
                return false;
            }
            mct.b(this.g, "generateMedalTime", String.valueOf(elapsedRealtime));
            return true;
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveDataManager", "doGenerateMedal parse exception ");
            return true;
        }
    }

    public ArrayList<mfs> k() {
        return b(b(8, new HashMap(2)));
    }

    public Map<String, mfo> m() {
        HashMap hashMap = new HashMap(2);
        return med.a(b(8, hashMap), b(9, hashMap));
    }

    private ArrayList<mfs> b(List<mcz> list) {
        return med.d(list, b(9, new HashMap(2)));
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        AchieveKakaCheckRecordObserver achieveKakaCheckRecordObserver = new AchieveKakaCheckRecordObserver(this.g);
        achieveKakaCheckRecordObserver.d(iBaseResponseCallback);
        b(achieveKakaCheckRecordObserver);
        String d2 = HiDateUtil.d((String) null);
        HashMap hashMap = new HashMap(16);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        hashMap.put("timeZone", d2);
        a(20, hashMap);
    }
}
