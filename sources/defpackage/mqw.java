package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.pluginmessagecenter.provider.data.MessageCenterList;
import com.huawei.pluginmessagecenter.service.MessageGenerator;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mqw extends mml {
    private static final Byte[] c = new Byte[1];
    private static volatile mqw e;

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, Integer> f15117a;
    private Context b;
    private d d = null;

    private mqw(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        init(applicationContext);
    }

    public static mqw b(Context context) {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new mqw(context);
                }
            }
        }
        return e;
    }

    private void j() {
        LogUtil.a("UIDV_PluginMessageCenter", "processSwitchUserOrLanguage");
        if (this.d == null) {
            this.d = new d();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        try {
            this.b.unregisterReceiver(this.d);
        } catch (IllegalArgumentException unused) {
            LogUtil.a("UIDV_PluginMessageCenter", "processSwitchUserOrLanguage IllegalArgument unregisterReceiver");
        }
        this.b.registerReceiver(this.d, intentFilter);
        jdx.b(new Runnable() { // from class: mqw.3
            @Override // java.lang.Runnable
            public void run() {
                String b = SharedPreferenceManager.b(mqw.this.b, Integer.toString(PrebakedEffectId.RT_FAST_MOVING), "messageLastLoginHuid");
                String huidOrDefault = LoginInit.getInstance(BaseApplication.e()).getHuidOrDefault();
                mrc.e(mqw.this.b).e(huidOrDefault);
                if (!TextUtils.isEmpty(b) && !b.equals(huidOrDefault)) {
                    LogUtil.a("UIDV_PluginMessageCenter", "processSwitchUserOrLanguage user change");
                    SharedPreferenceManager.e(mqw.this.b, Integer.toString(PrebakedEffectId.RT_FAST_MOVING), "messageLastLoginHuid", huidOrDefault, new StorageParams(1));
                    mrc.e(mqw.this.b).d();
                }
                if (TextUtils.isEmpty(b)) {
                    SharedPreferenceManager.e(mqw.this.b, Integer.toString(PrebakedEffectId.RT_FAST_MOVING), "messageLastLoginHuid", huidOrDefault, new StorageParams(1));
                }
                String b2 = mrc.e(mqw.this.b).b();
                String language = Locale.getDefault().getLanguage();
                if (!TextUtils.isEmpty(b2) && !b2.equals(language)) {
                    LogUtil.a("UIDV_PluginMessageCenter", "language change");
                    mrc.e(mqw.this.b).d();
                }
                mrc.e(mqw.this.b).j(language);
            }
        });
    }

    @Override // defpackage.mml
    public void init(Context context) {
        super.init(context);
        LogUtil.a("UIDV_PluginMessageCenter", "init");
        CommonUtil.initWearType();
        j();
        c(context);
    }

    @Override // defpackage.mml
    public void finish() {
        super.finish();
        LogUtil.a("UIDV_PluginMessageCenter", "finish");
        d dVar = this.d;
        if (dVar != null) {
            this.b.unregisterReceiver(dVar);
            this.d = null;
        }
        mrc.e(this.b).a();
    }

    public String e(String str, String str2) {
        LogUtil.a("UIDV_PluginMessageCenter", "requestMessageId");
        return MessageGenerator.getInstance(this.b).requestMessageId(str, str2);
    }

    public void c(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        jdx.b(new Runnable() { // from class: mqw.4
            @Override // java.lang.Runnable
            public void run() {
                String requestMessageId = MessageGenerator.getInstance(mqw.this.b).requestMessageId(str, str2);
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, requestMessageId);
                }
            }
        });
    }

    public boolean c(MessageObject messageObject) {
        LogUtil.a("UIDV_PluginMessageCenter", "generateMessage");
        return MessageGenerator.getInstance(this.b).generateMessage(messageObject);
    }

    public List<MessageObject> b(int i, int i2) {
        String deviceModel;
        synchronized (c) {
            LogUtil.a("UIDV_PluginMessageCenter", "getMessageList | pageNo = ", Integer.valueOf(i), "; pageSize = ", Integer.valueOf(i2));
            if (i < 0 || i2 < 0) {
                LogUtil.a("UIDV_PluginMessageCenter", "pageNo or pageSize is invalid.");
                return new ArrayList();
            }
            String str = "";
            String str2 = "";
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "UIDV_PluginMessageCenter");
            if (deviceInfo != null) {
                str = String.valueOf(deviceInfo.getProductType());
                String deviceModel2 = deviceInfo.getDeviceModel();
                String deviceModel3 = deviceInfo.getDeviceModel();
                deviceModel = deviceModel2;
                str2 = deviceModel3;
            } else {
                deviceModel = PhoneInfoUtils.getDeviceModel();
            }
            String wearType = CommonUtil.getWearType(str, str2);
            if (!TextUtils.isEmpty(deviceModel)) {
                deviceModel = deviceModel.trim();
            }
            return mrc.e(this.b).e(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011), wearType, deviceModel, i, i2);
        }
    }

    public List<MessageObject> a(String str, String str2) {
        LogUtil.a("UIDV_PluginMessageCenter", "getMessage | module = ", str, "; type = ", str2);
        return mrc.e(this.b).b(str, str2);
    }

    public int e() {
        LogUtil.a("UIDV_PluginMessageCenter", "getMessageCenterUnReadCount");
        List<MessageObject> e2 = e(i());
        int i = 0;
        if (e2 != null && !e2.isEmpty()) {
            for (MessageObject messageObject : e2) {
                if (mrs.e(messageObject) && !messageObject.getMsgId().equals("kakaMessage") && messageObject.getReadFlag() == 0 && !b(messageObject)) {
                    if (!String.valueOf(14).equals(messageObject.getModule()) || CommonUtil.isRecommendSwitchOpen(this.b)) {
                        i++;
                    } else {
                        LogUtil.h("UIDV_PluginMessageCenter", "not allow recommand");
                    }
                }
            }
        }
        return i;
    }

    private boolean j(MessageObject messageObject) {
        return (String.valueOf(18).equals(messageObject.getModule()) || String.valueOf(16).equals(messageObject.getModule())) && !mrg.d(messageObject);
    }

    private boolean d(MessageObject messageObject) {
        return String.valueOf(17).equals(messageObject.getModule()) && !mrg.d(messageObject);
    }

    private boolean e(MessageObject messageObject) {
        return String.valueOf(18).equals(messageObject.getModule()) && mrg.d(messageObject);
    }

    private boolean a(MessageObject messageObject) {
        boolean z = MessageConstant.ACTIVE_TARGET_TYPE.equals(messageObject.getType()) && !mrg.d(messageObject);
        LogUtil.a("UIDV_PluginMessageCenter", "active target message valid: ", Boolean.valueOf(z));
        return z;
    }

    private boolean b(MessageObject messageObject) {
        return !(messageObject.getExpireTime() == 0 || j(messageObject) || d(messageObject) || a(messageObject));
    }

    public List<MessageObject> c(String str) {
        LogUtil.a("UIDV_PluginMessageCenter", "getMessageCenterListMessageByModule");
        List<MessageObject> e2 = e(i());
        if (e2 == null || e2.isEmpty()) {
            return new ArrayList(8);
        }
        ArrayList arrayList = new ArrayList(e2.size());
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        for (MessageObject messageObject : e2) {
            if (mrs.e(messageObject) && str.equalsIgnoreCase(messageObject.getModule())) {
                LogUtil.c("UIDV_PluginMessageCenter", "getMessageCenterListMessageByModule list=", messageObject.toString());
                String module = messageObject.getModule();
                AchieveDataApi achieveDataApi = (AchieveDataApi) Services.c("PluginAchievement", AchieveDataApi.class);
                if (module.equals(String.valueOf(17)) && messageObject.getType().equals("unclaimedKaka") && !achieveDataApi.getKakaTaskRedDot(this.b)) {
                    LogUtil.a("UIDV_PluginMessageCenter", "no kaka to claim");
                } else if (module.equals(String.valueOf(17)) && messageObject.getType().equals("kakaExpiration") && (messageObject.getReadFlag() == 1 || System.currentTimeMillis() > messageObject.getExpireTime())) {
                    LogUtil.a("UIDV_PluginMessageCenter", "kaka expiration msg is read or is expired");
                } else if (!TextUtils.isEmpty(i(module)) && !e(messageObject) && (!module.equals(String.valueOf(90)) || accountInfo.equals(messageObject.getHuid()))) {
                    messageObject.setInfoClassify(i(module));
                    arrayList.add(messageObject);
                }
            }
        }
        if (String.valueOf(90).equals(str)) {
            Collections.sort(arrayList, new Comparator() { // from class: mrb
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(((MessageObject) obj2).getCreateTime(), ((MessageObject) obj).getCreateTime());
                    return compare;
                }
            });
        } else {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    public List<MessageObject> b(List<MessageObject> list) {
        HashMap<String, MessageCenterList> hashMap = new HashMap<>(8);
        for (MessageObject messageObject : list) {
            if (mrs.e(messageObject)) {
                LogUtil.c("UIDV_PluginMessageCenter", "getMessageCenterCategory=", messageObject.toString());
                if (String.valueOf(14).equals(messageObject.getModule()) && !CommonUtil.isRecommendSwitchOpen(this.b)) {
                    LogUtil.h("UIDV_PluginMessageCenter", "not allow recommaned");
                } else if (!e(messageObject)) {
                    if (hashMap.containsKey(messageObject.getModule())) {
                        MessageCenterList messageCenterList = hashMap.get(messageObject.getModule());
                        messageCenterList.setDate(String.valueOf(messageObject.getReceiveTime()));
                        messageCenterList.setMessageObject(messageObject);
                        int unread = messageCenterList.getUnread();
                        if (!messageObject.getMsgId().equals("kakaMessage") && messageObject.getReadFlag() == 0) {
                            messageCenterList.setUnread(unread + 1);
                        }
                        hashMap.put(messageObject.getModule(), messageCenterList);
                    } else {
                        MessageCenterList messageCenterList2 = new MessageCenterList();
                        messageCenterList2.setMessageObject(messageObject);
                        messageCenterList2.setDate(String.valueOf(messageObject.getReceiveTime()));
                        messageCenterList2.setModule(messageObject.getModule());
                        if (!messageObject.getMsgId().equals("kakaMessage") && messageObject.getReadFlag() == 0) {
                            messageCenterList2.setUnread(1);
                        } else {
                            messageCenterList2.setUnread(0);
                        }
                        hashMap.put(messageObject.getModule(), messageCenterList2);
                    }
                }
            }
        }
        return b(hashMap);
    }

    private List<MessageObject> b(HashMap<String, MessageCenterList> hashMap) {
        ArrayList arrayList = new ArrayList(8);
        Iterator<Map.Entry<String, MessageCenterList>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            MessageCenterList value = it.next().getValue();
            String i = i(value.getModule());
            if (!TextUtils.isEmpty(i)) {
                MessageObject messageObject = new MessageObject();
                messageObject.setMsgTitle(i);
                messageObject.setModule(value.getMessageObject().getModule());
                messageObject.setMsgContent(value.getMessageObject().getMsgTitle());
                messageObject.setFlag(0);
                messageObject.setMsgType(value.getMessageObject().getMsgType());
                messageObject.setWeight(value.getMessageObject().getWeight());
                messageObject.setReadFlag(value.getUnread());
                messageObject.setExpireTime(value.getMessageObject().getExpireTime());
                messageObject.setCreateTime(value.getMessageObject().getCreateTime());
                messageObject.setReceiveTime(value.getMessageObject().getReceiveTime());
                messageObject.setPosition(value.getMessageObject().getPosition());
                messageObject.setMsgPosition(11);
                messageObject.setHuid(value.getMessageObject().getHuid());
                messageObject.setType(value.getMessageObject().getType());
                LogUtil.a("UIDV_PluginMessageCenter", "getMessageCenterListMessage type=", messageObject.toString());
                arrayList.add(messageObject);
            }
        }
        Collections.sort(arrayList, new Comparator<MessageObject>() { // from class: mqw.5
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(MessageObject messageObject2, MessageObject messageObject3) {
                if (messageObject2.getReceiveTime() > messageObject3.getReceiveTime()) {
                    return -1;
                }
                return messageObject2.getReceiveTime() == messageObject3.getReceiveTime() ? 0 : 1;
            }
        });
        LogUtil.a("UIDV_PluginMessageCenter", "getMessageCenterListMessage messageObjects size=", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private String i(String str) {
        if (this.f15117a == null) {
            HashMap<String, Integer> hashMap = new HashMap<>(15);
            this.f15117a = hashMap;
            hashMap.put(String.valueOf(14), Integer.valueOf(R.string._2130843304_res_0x7f0216a8));
            this.f15117a.put(String.valueOf(30), Integer.valueOf(R.string._2130841426_res_0x7f020f52));
            this.f15117a.put(String.valueOf(40), Integer.valueOf(R.string._2130843305_res_0x7f0216a9));
            this.f15117a.put(String.valueOf(15), Integer.valueOf(R.string._2130848489_res_0x7f022ae9));
            this.f15117a.put(String.valueOf(50), Integer.valueOf(R.string._2130843306_res_0x7f0216aa));
            this.f15117a.put(String.valueOf(16), Integer.valueOf(R.string._2130843307_res_0x7f0216ab));
            this.f15117a.put(String.valueOf(17), Integer.valueOf(R.string._2130842559_res_0x7f0213bf));
            this.f15117a.put(String.valueOf(18), Integer.valueOf(R.string._2130842510_res_0x7f02138e));
            this.f15117a.put(String.valueOf(19), Integer.valueOf(R.string._2130843308_res_0x7f0216ac));
            this.f15117a.put(String.valueOf(51), Integer.valueOf(R.string._2130847845_res_0x7f022865));
            this.f15117a.put(String.valueOf(70), Integer.valueOf(R.string._2130845630_res_0x7f021fbe));
            this.f15117a.put(String.valueOf(80), Integer.valueOf(R.string._2130845025_res_0x7f021d61));
            this.f15117a.put(String.valueOf(90), Integer.valueOf(R.string._2130845356_res_0x7f021eac));
            this.f15117a.put(String.valueOf(100), Integer.valueOf(R.string._2130845609_res_0x7f021fa9));
            this.f15117a.put(MessageConstant.CERTIFICATE, Integer.valueOf(R.string._2130847595_res_0x7f02276b));
        }
        String string = this.f15117a.containsKey(str) ? this.b.getString(this.f15117a.get(str).intValue()) : "";
        LogUtil.a("UIDV_PluginMessageCenter", "getMessageCenterListMessage title=", string);
        return string;
    }

    public void b(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("UIDV_PluginMessageCenter", "getMessages | module = ", str, "; type = ", str2);
        jdx.b(new Runnable() { // from class: mqw.2
            @Override // java.lang.Runnable
            public void run() {
                List<MessageObject> b = mrc.e(mqw.this.b).b(str, str2);
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, b);
                }
            }
        });
    }

    public List<MessageObject> d(String str) {
        synchronized (c) {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList();
            }
            List<MessageObject> c2 = c();
            if (c2 != null && !c2.isEmpty()) {
                ArrayList arrayList = new ArrayList(c2.size());
                Iterator<MessageObject> it = c2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        LogUtil.a("UIDV_PluginMessageCenter", "getInfoMessageListByClassify resultMessageLists size = ", Integer.valueOf(arrayList.size()), " classify = ", str);
                        return arrayList;
                    }
                    MessageObject next = it.next();
                    boolean z = CommonUtil.WALK_INFO.equals(str) || h(next);
                    if (mrs.b(next, str) && z) {
                        arrayList.add(next);
                    }
                }
            }
            return new ArrayList();
        }
    }

    private List<MessageObject> a() {
        LogUtil.a("UIDV_PluginMessageCenter", "getFaqMessageList");
        List<MessageObject> b = b(0, 0);
        if (b == null || b.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(b.size());
        for (MessageObject messageObject : b) {
            if (messageObject != null && messageObject.getMsgPosition() == 31 && messageObject.getReadFlag() == 0) {
                arrayList.add(messageObject);
            }
        }
        return arrayList;
    }

    public List<MessageObject> b(int i) {
        LogUtil.a("UIDV_PluginMessageCenter", "getSpecificMessageList");
        if (i == 31) {
            return a();
        }
        List<MessageObject> e2 = e(i());
        if (e2 == null || e2.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(e2.size());
        for (MessageObject messageObject : e2) {
            boolean z = i == 2 && mrs.j(messageObject);
            boolean z2 = i == 26 && mrs.d(messageObject);
            boolean z3 = i == 30 && mrs.h(messageObject);
            boolean z4 = i == 29 && mrs.c(messageObject);
            boolean z5 = i == 21 && mrs.a(messageObject);
            boolean z6 = i == 11 && mrs.e(messageObject);
            boolean z7 = i == 42 && mrs.f(messageObject);
            if (z || z2 || z3 || z4 || z5 || z6 || z7) {
                arrayList.add(messageObject);
            }
        }
        LogUtil.a("UIDV_PluginMessageCenter", "getSpecificMessageList = ", Integer.valueOf(arrayList.size()));
        e(arrayList, i);
        return arrayList;
    }

    private void e(List<MessageObject> list, int i) {
        if (i == 21) {
            Collections.sort(list, new Comparator<MessageObject>() { // from class: mqw.1
                @Override // java.util.Comparator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(MessageObject messageObject, MessageObject messageObject2) {
                    if (messageObject.getReceiveTime() > messageObject2.getReceiveTime()) {
                        return -1;
                    }
                    return messageObject.getReceiveTime() == messageObject2.getReceiveTime() ? 0 : 1;
                }
            });
        }
    }

    public List<MessageObject> c() {
        synchronized (c) {
            List<MessageObject> e2 = e(i());
            if (e2 != null && !e2.isEmpty()) {
                ArrayList arrayList = new ArrayList(e2.size());
                for (MessageObject messageObject : e2) {
                    if (mrs.b(messageObject)) {
                        arrayList.add(messageObject);
                    }
                }
                LogUtil.a("UIDV_PluginMessageCenter", "getInformationTypeMessageList = ", Integer.valueOf(arrayList.size()));
                return arrayList;
            }
            return new ArrayList();
        }
    }

    public List<MessageObject> e(List<String> list) {
        synchronized (c) {
            List<MessageObject> b = b(0, 0);
            if (b != null && !b.isEmpty()) {
                ArrayList arrayList = new ArrayList(b.size());
                for (MessageObject messageObject : b) {
                    if (messageObject != null && e(messageObject, list)) {
                        arrayList.add(messageObject);
                    }
                }
                return arrayList;
            }
            return new ArrayList();
        }
    }

    public List<MessageObject> b() {
        synchronized (c) {
            List<String> i = i();
            List<MessageObject> b = b(0, 0);
            if (b != null && !b.isEmpty()) {
                ArrayList arrayList = new ArrayList(b.size());
                for (MessageObject messageObject : b) {
                    if (messageObject != null && e(messageObject, i)) {
                        if (messageObject.getModule().equals(String.valueOf(17)) && messageObject.getType().equals("unclaimedKaka")) {
                            if (((AchieveDataApi) Services.c("PluginAchievement", AchieveDataApi.class)).getKakaTaskRedDot(this.b)) {
                                arrayList.add(messageObject);
                            }
                        } else {
                            if (messageObject.getModule().equals(String.valueOf(17))) {
                                if (messageObject.getType().equals("kakaExpiration")) {
                                    if (messageObject.getReadFlag() != 1 && System.currentTimeMillis() <= messageObject.getExpireTime()) {
                                    }
                                }
                            }
                            arrayList.add(messageObject);
                        }
                    }
                }
                return arrayList;
            }
            return new ArrayList();
        }
    }

    public static boolean e(MessageObject messageObject, List<String> list) {
        boolean z = false;
        if (messageObject == null || list == null) {
            LogUtil.h("UIDV_PluginMessageCenter", "messageObject = null or userAllLabelList =null");
            return false;
        }
        synchronized (c) {
            String msgUserLabel = messageObject.getMsgUserLabel();
            boolean z2 = true;
            if (TextUtils.isEmpty(msgUserLabel)) {
                return true;
            }
            try {
                JSONObject jSONObject = new JSONObject(msgUserLabel);
                Iterator<String> keys = jSONObject.keys();
                loop0: while (true) {
                    if (!keys.hasNext()) {
                        z = true;
                        break;
                    }
                    String next = keys.next();
                    if (!TextUtils.isEmpty(next)) {
                        JSONArray jSONArray = jSONObject.getJSONArray(next);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (list.contains((String) jSONArray.get(i))) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                z2 = z;
            } catch (JSONException unused) {
                LogUtil.b("UIDV_PluginMessageCenter", "isShouldMsgRecommend JSONException");
            }
            return z2;
        }
    }

    private List<String> i() {
        String accountInfo = LoginInit.getInstance(this.b).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            return new ArrayList();
        }
        String b = SharedPreferenceManager.b(this.b, String.valueOf(20009), accountInfo + CommonUtil.USER_LABEL_STORAGE_KEY);
        if (b != null) {
            return Arrays.asList(b.split(",|#"));
        }
        return new ArrayList();
    }

    private boolean h(MessageObject messageObject) {
        return messageObject != null && messageObject.getInfoRecommend() == 1;
    }

    public boolean a(String str) {
        LogUtil.a("UIDV_PluginMessageCenter", "onRead | msgId = ", str);
        return mrc.e(this.b).b(str);
    }

    public boolean b(String str) {
        LogUtil.a("UIDV_PluginMessageCenter", "onShowSmartCard | msgId = ", str);
        return mrc.e(this.b).d(str);
    }

    public void c(final MessageObserver messageObserver) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mqw.8
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("UIDV_PluginMessageCenter", "registerMessageObserver observer = ", messageObserver);
                mrc.e(mqw.this.b).b(messageObserver);
            }
        });
    }

    public void a(final MessageObserver messageObserver) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mqw.6
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("UIDV_PluginMessageCenter", "unRegisterMessageObserver observer = ", messageObserver);
                mrc.e(mqw.this.b).c(messageObserver);
            }
        });
    }

    public boolean e(String str) {
        boolean z;
        synchronized (c) {
            LogUtil.a("UIDV_PluginMessageCenter", "deleteMessage | msgId = ", str);
            z = mrc.e(this.b).a(str) == 0;
        }
        return z;
    }

    public void d() {
        synchronized (c) {
            mrc.e(this.b).c();
        }
    }

    public void a(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("UIDV_PluginMessageCenter", "deleteMessagesByModuleAndType | module = ", str, "; type = ", str2);
        jdx.b(new Runnable() { // from class: mqw.9
            @Override // java.lang.Runnable
            public void run() {
                List<MessageObject> b = mrc.e(mqw.this.b).b(str, str2);
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, b);
                }
                Iterator<MessageObject> it = b.iterator();
                while (it.hasNext()) {
                    mqw.this.e(it.next().getMsgId());
                }
            }
        });
    }

    static class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if (intent == null || context == null) {
                return;
            }
            jdx.b(new Runnable() { // from class: mqw.d.5
                @Override // java.lang.Runnable
                public void run() {
                    String action = intent.getAction();
                    LogUtil.c("UIDV_PluginMessageCenter", "onReceive action=", action);
                    if ("android.intent.action.LOCALE_CHANGED".equals(action)) {
                        String b = mrc.e(context).b();
                        LogUtil.a("UIDV_PluginMessageCenter", "onReceive lastLanguage=", b);
                        String language = Locale.getDefault().getLanguage();
                        LogUtil.a("UIDV_PluginMessageCenter", "onReceive currentLanguage=", language);
                        mrc.e(context).j(language);
                        if (b.equals(language)) {
                            return;
                        }
                        LogUtil.a("UIDV_PluginMessageCenter", "config change");
                        mrc.e(context).d();
                    }
                }
            });
        }
    }

    public static boolean a(Context context) {
        return CommonUtil.isSystemBarNoticeSwitchOnOrDefault(context);
    }

    private static void c(Context context) {
        LogUtil.a("UIDV_PluginMessageCenter", "initSystemBarPushSwitch, now is ", Boolean.valueOf(CommonUtil.isSystemBarNoticeSwitchOnOrDefault(context)));
    }
}
