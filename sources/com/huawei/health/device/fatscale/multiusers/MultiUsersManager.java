package com.huawei.health.device.fatscale.multiusers;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.util.HiZipUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.cfi;
import defpackage.gni;
import defpackage.koq;
import health.compact.a.CommonUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public enum MultiUsersManager {
    INSTANCE;

    private static final int KEY_USER_DEFAULT_COUNT = -1;
    private static final int MAX_SYNC_COUNT = 3;
    private static final int SID_ZERO_INDEX = 0;
    private static final String TAG = "PluginDevice_MultiUsersManager";
    private static final int UNCOMPRESS_USER_DATA_DEFAULT_COUNT = -1;
    private static final String UNDERLINE = "_";
    public static final int USER_TYPE_MAIN = 1;
    public static final int USER_TYPE_SUB = 2;
    private static final int USER_TYPE_SUB_OLD_KEY_MAX = 5;
    private boolean isRegUserInfoError;
    private cfi mEmptyUser;
    private boolean mIsCheckMainUser;
    private List<Integer> mSuccessList;
    private static int sPrevLastSyncDate = 0;
    private static int sSyncCount = 0;
    private static final Object LOCK = new Object();
    private cfi mMainUser = new cfi();
    private Map<String, cfi> mMultiUsersMap = Collections.synchronizedMap(new LinkedHashMap());
    private List<cfi> mMainAllUser = new ArrayList();
    private cfi mCurrentUser = new cfi();
    private transient ThreadPoolManager mThreadPool = ThreadPoolManager.e(1, 1, TAG);
    private List<Integer> mHiSubscribeTypeList = new CopyOnWriteArrayList();
    private Map<String, String> mSidList = new TreeMap();
    private transient HiSubscribeListener mSubscribeListener = new HiSubscribeListener() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.1
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            MultiUsersManager.this.mSuccessList = list;
            LogUtil.a(MultiUsersManager.TAG, "regUserInfoListener onResult successList.isEmpty:", Boolean.valueOf(koq.b(list)), " failList.isEmpty:", Boolean.valueOf(koq.b(list2)));
            if (koq.b(list)) {
                MultiUsersManager.this.isRegUserInfoError = true;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (i == 100) {
                LogUtil.a(MultiUsersManager.TAG, "USER_INFO changed");
                MultiUsersManager.this.refetchMainUserInfo();
            }
        }
    };

    MultiUsersManager() {
    }

    private void initSidList() {
        Map<String, String> map = this.mSidList;
        if (map == null || map.isEmpty()) {
            this.mSidList = new TreeMap();
        }
        this.mSidList.put("userInfo2", "");
        this.mSidList.put("userInfo3", "");
        this.mSidList.put("userInfo4", "");
        this.mSidList.put("userInfo5", "");
        this.mSidList.put("userInfo6", "");
        this.mSidList.put("userInfo7", "");
        this.mSidList.put("userInfo8", "");
        this.mSidList.put("userInfo9", "");
        this.mSidList.put("userInfo10", "");
    }

    public void initMultiUsers(cfi cfiVar) {
        if (this.isRegUserInfoError) {
            LogUtil.a(TAG, "initMultiUsers isRegUserInfoError");
            regUserInfoListener();
        }
        this.mMultiUsersMap.clear();
        initSidList();
        initMainUser(cfiVar);
        initEmptyUser(this.mMainUser);
        setMainUser(this.mMainUser);
        setCurrentUser(this.mMainUser);
        getOtherUserData(null);
    }

    private void initMainUser(cfi cfiVar) {
        UserInfomation c = gni.c();
        LogUtil.a(TAG, "initMainUser userInfomation ", c);
        if (this.mMainUser == null) {
            this.mMainUser = new cfi();
        }
        this.mMainUser.e(1);
        this.mMainUser.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        if (c != null) {
            ReleaseLogUtil.b(TAG, "initMainUser enter userInfomation");
            this.mMainUser.a((byte) (c.getGender() == 1 ? 0 : 1));
            this.mMainUser.a(c.getAge());
            this.mMainUser.b(c.getName());
            this.mMainUser.d(c.getHeight());
            this.mMainUser.b(cfiVar != null ? cfiVar.m() : c.getWeight());
            this.mMainUser.b(CommonUtils.h(c.getBirthday()));
        } else {
            if (cfiVar == null) {
                ReleaseLogUtil.a(TAG, "initMainUser user is null");
                return;
            }
            this.mMainUser.a(cfiVar.c());
            this.mMainUser.a(cfiVar.a());
            this.mMainUser.b(cfiVar.h());
            this.mMainUser.d(cfiVar.d());
            this.mMainUser.b(cfiVar.m());
            this.mMainUser.b(cfiVar.g());
        }
        LogUtil.a(TAG, "initMainUser mMainUser ", this.mMainUser);
    }

    public void initEmptyUser(cfi cfiVar) {
        if (cfiVar != null) {
            this.mEmptyUser = cfiVar;
        }
    }

    public cfi getEmptyUser() {
        return this.mEmptyUser;
    }

    private void setUserPreference(String str, String str2) {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey(str);
        hiUserPreference.setValue(str2);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).setUserPreference(hiUserPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiUserPreference getUserPreference(String str) {
        return HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).getUserPreference(str);
    }

    private String getUserPreferenceValue(String str) {
        HiUserPreference userPreference = getUserPreference(str);
        return userPreference == null ? "" : userPreference.getValue();
    }

    private void correctUserInformation() {
        cfi value;
        String userPreferenceValue = getUserPreferenceValue("custom.weight_multi_userinfo_base");
        Map<String, cfi> parseUserInformation = parseUserInformation(userPreferenceValue);
        if (parseUserInformation.size() == 0) {
            ReleaseLogUtil.a(TAG, "correctUserInformation userInfoBaseValue ", userPreferenceValue);
            return;
        }
        Map<String, cfi> parseUserInformation2 = parseUserInformation(getUserPreferenceValue("custom.weight_multi_userinfo"));
        Map<String, cfi> parseUserInformation3 = parseUserInformation(getUserPreferenceValue("custom.weight_multi_userinfo_expand"));
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, cfi> entry : parseUserInformation.entrySet()) {
            if (entry != null) {
                String key = entry.getKey();
                if (!parseUserInformation2.containsKey(key) && !parseUserInformation3.containsKey(key) && (value = entry.getValue()) != null) {
                    hashMap.put(key, value);
                }
            }
        }
        if (hashMap.size() == 0) {
            return;
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            if (entry2 != null) {
                String str = (String) entry2.getKey();
                cfi cfiVar = (cfi) entry2.getValue();
                if (!TextUtils.isEmpty(str) && cfiVar != null) {
                    if (parseUserInformation2.size() < 5) {
                        parseUserInformation2.put(str, cfiVar);
                    } else {
                        parseUserInformation3.put(str, cfiVar);
                    }
                }
            }
        }
        String userInformationForMap = getUserInformationForMap(parseUserInformation2);
        if (!TextUtils.isEmpty(userInformationForMap)) {
            setUserPreference("custom.weight_multi_userinfo", userInformationForMap);
        }
        String userInformationForMap2 = getUserInformationForMap(parseUserInformation3);
        if (TextUtils.isEmpty(userInformationForMap2)) {
            return;
        }
        setUserPreference("custom.weight_multi_userinfo_expand", userInformationForMap2);
    }

    private Map<String, cfi> parseUserInformation(String str) {
        JSONArray jSONArray;
        String str2;
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a(TAG, "parseUserInformation userInfo ", str);
            return new HashMap();
        }
        JSONArray jSONArray2 = null;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG, "parseUserInformation jsonArray exception ", ExceptionUtils.d(e));
            jSONArray = null;
        }
        if (jSONArray != null) {
            return parseJsonArrayUserInformation(jSONArray);
        }
        try {
            str2 = HiZipUtil.a(str);
        } catch (IOException e2) {
            ReleaseLogUtil.c(TAG, "parseUserInformation uncompressUserInfo exception ", ExceptionUtils.d(e2));
            syncHiOption();
            str2 = "";
        }
        LogUtil.c(TAG, "parseUserInformation uncompressUserInfo ", str2);
        if (TextUtils.isEmpty(str2)) {
            return new HashMap();
        }
        try {
            jSONArray2 = new JSONArray(str2);
        } catch (JSONException e3) {
            ReleaseLogUtil.c(TAG, "parseUserInformation array exception ", ExceptionUtils.d(e3));
        }
        if (jSONArray2 == null) {
            ReleaseLogUtil.a(TAG, "parseUserInformation array is null uncompressUserInfo ", str2);
            return new HashMap();
        }
        return parseJsonArrayUserInformation(jSONArray2);
    }

    private Map<String, cfi> parseJsonArrayUserInformation(JSONArray jSONArray) {
        cfi cfiVar;
        if (jSONArray == null) {
            ReleaseLogUtil.a(TAG, "parseJsonArrayUserInformation jsonArray is null");
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String optString = jSONArray.optString(i);
            if (TextUtils.isEmpty(optString)) {
                ReleaseLogUtil.a(TAG, "parseJsonArrayUserInformation json ", optString);
            } else {
                try {
                    cfiVar = new cfi(new JSONObject(optString));
                } catch (JSONException e) {
                    ReleaseLogUtil.c(TAG, "parseJsonArrayUserInformation exception ", ExceptionUtils.d(e));
                    cfiVar = null;
                }
                if (cfiVar == null) {
                    ReleaseLogUtil.a(TAG, "parseJsonArrayUserInformation user is null json ", optString);
                } else {
                    String i2 = cfiVar.i();
                    if (TextUtils.isEmpty(i2)) {
                        ReleaseLogUtil.a(TAG, "parseJsonArrayUserInformation uuid ", i2, " json ", optString);
                    } else {
                        if (TextUtils.isEmpty(cfiVar.j())) {
                            cfiVar.a(getSid(i2));
                        }
                        if (hashMap.get(i2) == null) {
                            hashMap.put(i2, cfiVar);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private String getUserInformationForMap(Map<String, cfi> map) {
        if (map == null) {
            ReleaseLogUtil.a(TAG, "getUserInformationForMap map is null");
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, cfi> entry : map.entrySet()) {
            if (entry != null) {
                cfi value = entry.getValue();
                if (value == null) {
                    ReleaseLogUtil.a(TAG, "getUserInformationForMap user is null entry ", entry);
                } else {
                    String a2 = value.a(true);
                    if (TextUtils.isEmpty(a2)) {
                        ReleaseLogUtil.a(TAG, "getUserInformationForMap json ", a2);
                    } else {
                        jSONArray.put(a2);
                    }
                }
            }
        }
        String jSONArray2 = jSONArray.toString();
        try {
            return HiZipUtil.d(jSONArray2);
        } catch (IOException e) {
            ReleaseLogUtil.c(TAG, "getUserInformationForMap exception ", ExceptionUtils.d(e));
            return jSONArray2;
        }
    }

    public void getOtherUserData(final IBaseResponseCallback iBaseResponseCallback) {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.6
            @Override // java.lang.Runnable
            public void run() {
                synchronized (MultiUsersManager.LOCK) {
                    MultiUsersManager.this.getOtherUserDataSuccess(iBaseResponseCallback);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getOtherUserDataSuccess(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "getOtherUserData--:", Integer.valueOf(this.mMultiUsersMap.size()));
        correctUserInformation();
        HiUserPreference userPreference = getUserPreference("custom.weight_multi_userinfo");
        initAllUser(userPreference, false);
        initAllUser(getUserPreference("custom.weight_multi_userinfo_expand"), true);
        HiUserPreference userPreference2 = getUserPreference("custom.weight_multi_userinfo_base");
        if (userPreference == null) {
            LogUtil.a(TAG, "getOtherUserData hiUserPreference is null");
        } else if (userPreference2 == null) {
            LogUtil.a(TAG, "getOtherUserData hiUserPreferenceBase is null");
            saveUserBaseData();
        } else if (!TextUtils.isEmpty(userPreference.getValue()) && TextUtils.isEmpty(userPreference2.getValue())) {
            saveUserBaseData();
        }
        refreshMainAllUser();
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshMainAllUser() {
        final List<cfi> otherUserList = getOtherUserList();
        final UserInfomation userInfomation = getUserInfomation();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.8
            @Override // java.lang.Runnable
            public void run() {
                if (MultiUsersManager.this.getCurrentUser() == null) {
                    MultiUsersManager multiUsersManager = MultiUsersManager.this;
                    multiUsersManager.setCurrentUser(multiUsersManager.mMainUser);
                }
                cfi fetchMainUser = MultiUsersManager.this.fetchMainUser(userInfomation);
                if (fetchMainUser != null) {
                    MultiUsersManager.this.mMainAllUser.clear();
                    MultiUsersManager.this.mMainAllUser.add(fetchMainUser);
                    MultiUsersManager.this.mMainAllUser.addAll(otherUserList);
                }
            }
        });
    }

    private void saveUserBaseData() {
        setUserPreference("custom.weight_multi_userinfo_base", getAllUserStringWithoutPhoto());
    }

    public void getOtherUserBaseData() {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.10
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(MultiUsersManager.TAG, " getOtherUserBaseData ");
                synchronized (MultiUsersManager.LOCK) {
                    MultiUsersManager.this.initAllUser(MultiUsersManager.this.getUserPreference("custom.weight_multi_userinfo_base"), false);
                    MultiUsersManager.this.refreshMainAllUser();
                }
            }
        });
    }

    public void initUserIfEmpty() {
        if (this.mMainUser == null) {
            refetchMainUserInfo();
        }
        if (this.mMultiUsersMap.isEmpty()) {
            getOtherUserData(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAllUser(HiUserPreference hiUserPreference, boolean z) {
        Map<String, String> map = this.mSidList;
        if (map == null || map.isEmpty()) {
            initSidList();
        }
        if (hiUserPreference != null) {
            String value = hiUserPreference.getValue();
            if (!TextUtils.isEmpty(value)) {
                parseAllUserData(value, z);
                return;
            } else {
                LogUtil.h(TAG, "initAllUser subUserStr is empty");
                this.mMultiUsersMap.clear();
                return;
            }
        }
        LogUtil.h(TAG, "initAllUser hiUserPreference is null");
    }

    private void parseAllUserData(String str, boolean z) {
        LogUtil.a(TAG, "parseAllUserData ", Integer.valueOf(this.mMultiUsersMap.size()), ",isShowExpandUser:", Boolean.valueOf(z));
        if (!z) {
            this.mMultiUsersMap.clear();
        }
        try {
            parseArrayUserData(new JSONArray(str));
        } catch (JSONException e) {
            LogUtil.b(TAG, "parseAllUserData " + e.getMessage());
            uncompressUserData(str);
        }
    }

    private void uncompressUserData(String str) {
        try {
            parseArrayUserData(new JSONArray(HiZipUtil.a(str)));
        } catch (IOException e) {
            LogUtil.b(TAG, "uncompressUserData " + e.getMessage());
            syncHiOption();
        } catch (JSONException e2) {
            LogUtil.b(TAG, "uncompressUserData json " + e2.getMessage());
        }
    }

    private void parseArrayUserData(JSONArray jSONArray) {
        try {
            int length = jSONArray.length();
            LogUtil.a(TAG, " array count ", Integer.valueOf(length));
            for (int i = 0; i < length; i++) {
                initMapAndCurrentUser(new JSONObject(jSONArray.optString(i)));
            }
        } catch (JSONException e) {
            LogUtil.b(TAG, "parseArrayUserData " + e.getMessage());
        }
    }

    private void initMapAndCurrentUser(JSONObject jSONObject) {
        cfi cfiVar = new cfi(jSONObject);
        if (cfiVar.j() == null || "".equals(cfiVar.j())) {
            String sid = getSid(cfiVar.i());
            cfiVar.a(sid);
            setUuidBySid(sid, cfiVar.i());
        } else {
            setUuidBySid(cfiVar.j(), cfiVar.i());
        }
        if (this.mMultiUsersMap.get(cfiVar.i()) == null) {
            LogUtil.a(TAG, "initMultiUsersMap put into user who UUIDOfUser is null", Integer.valueOf(this.mMultiUsersMap.size()));
            this.mMultiUsersMap.put(cfiVar.i(), cfiVar);
        }
        LogUtil.a(TAG, "initMap sidlist: " + this.mSidList.toString());
    }

    public void saveUser(final cfi cfiVar, final IBaseResponseCallback iBaseResponseCallback) {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.9
            @Override // java.lang.Runnable
            public void run() {
                if (MultiUsersManager.this.mMainUser == null) {
                    LogUtil.a(MultiUsersManager.TAG, "mainUser is null");
                    MultiUsersManager.this.refetchMainUserInfo();
                }
                String i = MultiUsersManager.this.mMainUser != null ? MultiUsersManager.this.mMainUser.i() : "";
                cfi cfiVar2 = cfiVar;
                if (cfiVar2 != null && cfiVar2.i() == null) {
                    LogUtil.a(MultiUsersManager.TAG, "user.getUUIDOfUser() is null");
                    return;
                }
                cfi cfiVar3 = cfiVar;
                if (cfiVar3 != null && !cfiVar3.i().equals(i)) {
                    synchronized (MultiUsersManager.LOCK) {
                        MultiUsersManager.this.resetMultiUserMap(cfiVar);
                        MultiUsersManager.this.refreshMainAllUser();
                    }
                }
                iBaseResponseCallback.d(0, "");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetMultiUserMap(cfi cfiVar) {
        cfi cfiVar2 = this.mMultiUsersMap.get(cfiVar.i());
        HiUserPreference userPreference = getUserPreference("custom.weight_multi_userinfo");
        if (cfiVar2 == null) {
            boolean z = parseUserCountByKey(userPreference) >= 5;
            this.mMultiUsersMap.put(cfiVar.i(), cfiVar);
            LogUtil.a(TAG, "mMultiUsersMap size is ", Integer.valueOf(this.mMultiUsersMap.size()));
            saveWeightUserData(getAllUserString(z), cfiVar, false, cfiVar.r(), z);
        } else {
            boolean z2 = !isSubToTheKey(userPreference, cfiVar.i());
            setMapUserData(cfiVar2, cfiVar);
            saveWeightUserData(getAllUserString(z2), cfiVar, false, cfiVar.r(), z2);
        }
        initAllUser(getUserPreference("custom.weight_multi_userinfo"), false);
        initAllUser(getUserPreference("custom.weight_multi_userinfo_expand"), true);
    }

    public void deleteUser(final String str, final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        this.mThreadPool.execute(new Runnable() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.7
            @Override // java.lang.Runnable
            public void run() {
                synchronized (MultiUsersManager.LOCK) {
                    boolean isSubToTheKey = MultiUsersManager.this.isSubToTheKey(MultiUsersManager.this.getUserPreference("custom.weight_multi_userinfo"), str);
                    if (MultiUsersManager.this.mMultiUsersMap.get(str) != null) {
                        MultiUsersManager.this.mMultiUsersMap.remove(str);
                    }
                    MultiUsersManager multiUsersManager = MultiUsersManager.this;
                    multiUsersManager.saveWeightUserData(multiUsersManager.getAllUserString(!isSubToTheKey), null, true, z, !isSubToTheKey);
                    MultiUsersManager.this.refreshMainAllUser();
                }
                iBaseResponseCallback.d(0, "");
            }
        });
    }

    private void setMapUserData(cfi cfiVar, cfi cfiVar2) {
        LogUtil.a(TAG, "setMapUserData");
        cfiVar.b(cfiVar2.r());
        cfiVar.a(cfiVar2.c());
        cfiVar.d(cfiVar2.d());
        cfiVar.b(cfiVar2.h());
        cfiVar.e(cfiVar2.n());
        cfiVar.b(cfiVar2.m());
        cfiVar.d(cfiVar2.k());
        cfiVar.b(cfiVar2.g());
        cfiVar.Ey_(cfiVar2.Ex_());
        cfiVar.e(cfiVar2.l());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAllUserString(boolean z) {
        String d;
        HiUserPreference userPreference = getUserPreference("custom.weight_multi_userinfo");
        HiUserPreference userPreference2 = getUserPreference("custom.weight_multi_userinfo_expand");
        LogUtil.a(TAG, "getAllUserString baseCount：", Integer.valueOf(parseUserCountByKey(userPreference)), ",expandCount:", Integer.valueOf(parseUserCountByKey(userPreference2)), ",isExpand:", Boolean.valueOf(z));
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        for (cfi cfiVar : this.mMultiUsersMap.values()) {
            if (isSubToTheKey(userPreference, cfiVar.i())) {
                jSONArray.put(cfiVar.a(true));
            } else if (isSubToTheKey(userPreference2, cfiVar.i())) {
                jSONArray2.put(cfiVar.a(true));
            } else if (!z) {
                jSONArray.put(cfiVar.a(true));
            } else {
                jSONArray2.put(cfiVar.a(true));
            }
        }
        String jSONArray3 = jSONArray.toString();
        try {
            if (!z) {
                d = HiZipUtil.d(jSONArray3);
            } else {
                d = HiZipUtil.d(jSONArray2.toString());
            }
            jSONArray3 = d;
            return jSONArray3;
        } catch (IOException e) {
            LogUtil.b(TAG, "getAllUserString " + e.getMessage());
            return jSONArray3;
        }
    }

    private String getAllUserStringWithoutPhoto() {
        JSONArray jSONArray = new JSONArray();
        Iterator<cfi> it = this.mMultiUsersMap.values().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a(false));
        }
        String jSONArray2 = jSONArray.toString();
        try {
            return HiZipUtil.d(jSONArray.toString());
        } catch (IOException e) {
            LogUtil.b(TAG, "getAllUserString " + e.getMessage());
            return jSONArray2;
        }
    }

    public void getCurrentUser(final WeightBaseResponseCallback<cfi> weightBaseResponseCallback) {
        if (weightBaseResponseCallback == null) {
            LogUtil.h(TAG, "getCurrentUser callback is null");
            return;
        }
        cfi cfiVar = this.mCurrentUser;
        if (cfiVar == null) {
            LogUtil.h(TAG, "getCurrentUser mCurrentUser is null");
            weightBaseResponseCallback.onResponse(-1, null);
            return;
        }
        if (cfiVar.s()) {
            weightBaseResponseCallback.onResponse(0, this.mCurrentUser);
            return;
        }
        LogUtil.h(TAG, "getCurrentUser: UserHeight=", Integer.valueOf(this.mCurrentUser.d()), ", UserBirthday=", Integer.valueOf(this.mCurrentUser.g()), ", UserGender=", Byte.valueOf(this.mCurrentUser.c()));
        if (getMainUser() == null || getMainUser().i() == null) {
            LogUtil.h(TAG, "mainUser is not init!");
            weightBaseResponseCallback.onResponse(-1, null);
        } else if (!getMainUser().i().equals(this.mCurrentUser.i())) {
            getOtherUserData(new IBaseResponseCallback() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.11
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        MultiUsersManager.this.setCurrentUser((cfi) MultiUsersManager.this.mMultiUsersMap.get(MultiUsersManager.this.mCurrentUser.i()));
                    } else {
                        LogUtil.h(MultiUsersManager.TAG, "getCurrentUser from map failed");
                    }
                    MultiUsersManager.this.userInfoCheck(weightBaseResponseCallback);
                }
            });
        } else {
            getUserInfo(weightBaseResponseCallback);
        }
    }

    private void getUserInfo(final WeightBaseResponseCallback<cfi> weightBaseResponseCallback) {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.13
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                LogUtil.a(MultiUsersManager.TAG, "getCurrentUser errorCode = ", Integer.valueOf(i));
                if (i == 0 && userInfomation != null) {
                    MultiUsersManager.this.mCurrentUser.a(userInfomation.getAge());
                    MultiUsersManager.this.mCurrentUser.a((byte) (userInfomation.getGender() == 1 ? 0 : 1));
                    MultiUsersManager.this.mCurrentUser.d(userInfomation.getHeight());
                    try {
                        MultiUsersManager.this.mCurrentUser.b(Integer.parseInt(userInfomation.getBirthday()));
                    } catch (NumberFormatException unused) {
                        LogUtil.h(MultiUsersManager.TAG, "getCurrentUser NumberFormatException");
                    }
                }
                MultiUsersManager.this.userInfoCheck(weightBaseResponseCallback);
            }
        });
    }

    public cfi getCurrentUser() {
        return this.mCurrentUser;
    }

    public void getCurrentUserForUserInfo(final ResponseCallback<cfi> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a(TAG, "getCurrentUserForUserInfo callback is null");
            return;
        }
        cfi cfiVar = this.mCurrentUser;
        if (cfiVar != null && cfiVar.n() != 1) {
            LogUtil.a(TAG, "getCurrentUserForUserInfo mCurrentUser ", this.mCurrentUser);
            responseCallback.onResponse(0, this.mCurrentUser);
        } else {
            HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).fetchUserData(new HiCommonListener() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.14
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    if (!koq.e(obj, HiUserInfo.class)) {
                        ReleaseLogUtil.a(MultiUsersManager.TAG, "getCurrentUserForUserInfo onSuccess object ", obj);
                        responseCallback.onResponse(-1, MultiUsersManager.this.mMainUser);
                        return;
                    }
                    List list = (List) obj;
                    if (koq.b(list)) {
                        ReleaseLogUtil.a(MultiUsersManager.TAG, "getCurrentUserForUserInfo onSuccess userInfoList ", list);
                        responseCallback.onResponse(-1, MultiUsersManager.this.mMainUser);
                        return;
                    }
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    if (hiUserInfo != null) {
                        MultiUsersManager.this.setMainUserData(hiUserInfo);
                        if (MultiUsersManager.this.mMainUser == null) {
                            ReleaseLogUtil.a(MultiUsersManager.TAG, "getCurrentUserForUserInfo onSuccess mCurrentUser is null");
                            responseCallback.onResponse(-1, null);
                            return;
                        } else {
                            LogUtil.a(MultiUsersManager.TAG, "getCurrentUserForUserInfo onSuccess userInfo ", hiUserInfo, " mMainUser ", MultiUsersManager.this.mMainUser.a(false));
                            responseCallback.onResponse(0, MultiUsersManager.this.mMainUser);
                            return;
                        }
                    }
                    ReleaseLogUtil.a(MultiUsersManager.TAG, "getCurrentUserForUserInfo onSuccess userInfo is null");
                    responseCallback.onResponse(-1, MultiUsersManager.this.mMainUser);
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    ReleaseLogUtil.a(MultiUsersManager.TAG, "getCurrentUserForUserInfo onFailure errorCode ", Integer.valueOf(i), " errorMessage ", obj);
                    responseCallback.onResponse(-1, MultiUsersManager.this.mMainUser);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void userInfoCheck(WeightBaseResponseCallback<cfi> weightBaseResponseCallback) {
        cfi cfiVar = this.mCurrentUser;
        if (cfiVar == null) {
            LogUtil.h(TAG, "mCurrentUser = null");
            weightBaseResponseCallback.onResponse(-1, null);
            return;
        }
        LogUtil.h(TAG, "mCurrentUser = ", cfiVar);
        if (this.mIsCheckMainUser && this.mCurrentUser.n() == 1) {
            weightBaseResponseCallback.onResponse(0, this.mCurrentUser);
        } else if (this.mCurrentUser.s()) {
            weightBaseResponseCallback.onResponse(0, this.mCurrentUser);
        } else {
            weightBaseResponseCallback.onResponse(-1, null);
        }
    }

    public void setIsCheckMainUser(boolean z) {
        this.mIsCheckMainUser = z;
    }

    public void setCurrentUser(cfi cfiVar) {
        if (cfiVar != null) {
            LogUtil.a(TAG, "currentUser info before ", this.mCurrentUser.a(false));
            this.mCurrentUser = cfiVar;
            LogUtil.a(TAG, "currentUser info after ", cfiVar.a(false));
        }
    }

    public cfi getMainUser() {
        cfi cfiVar = this.mMainUser;
        if (cfiVar == null || cfiVar.i() == null) {
            LogUtil.h(TAG, "getMainUser mainUser is not init!");
            cfi cfiVar2 = new cfi();
            this.mMainUser = cfiVar2;
            cfiVar2.e(1);
            this.mMainUser.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            refetchMainUserInfo();
            return this.mMainUser;
        }
        return this.mMainUser;
    }

    public void setMainUser(cfi cfiVar) {
        if (cfiVar != null) {
            this.mMainUser = cfiVar;
        } else {
            LogUtil.h(TAG, "mainUser is null");
        }
    }

    public void regUserInfoListener() {
        LogUtil.a(TAG, "regUserInfoListener");
        this.mHiSubscribeTypeList.clear();
        this.mHiSubscribeTypeList.add(100);
        HiHealthManager.d(BaseApplication.getContext()).subscribeHiHealthData(this.mHiSubscribeTypeList, this.mSubscribeListener);
    }

    public void unRegUserInfoListener() {
        if (koq.b(this.mSuccessList)) {
            LogUtil.h(TAG, "unRegUserInfoListener, mSuccessList is empty");
        } else {
            HiHealthManager.d(BaseApplication.getContext()).unSubscribeHiHealthData(this.mSuccessList, new HiUnSubscribeListener() { // from class: cfk
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a(MultiUsersManager.TAG, "unRegUserInfoListener, isSuccess is ", Boolean.valueOf(z));
                }
            });
        }
    }

    public void fetchMainUserInfoForCallback(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h(TAG, "fetchMainUserInfoForCallback callback is null");
        } else {
            HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.4
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (koq.b(list)) {
                            LogUtil.h(MultiUsersManager.TAG, "fetchMainUserInfoForCallback userInfos is empty");
                            iBaseResponseCallback.d(-1, null);
                            return;
                        }
                        HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                        if (hiUserInfo != null) {
                            MultiUsersManager.this.setCurrentUserToMainUser(hiUserInfo);
                            iBaseResponseCallback.d(0, MultiUsersManager.this.mCurrentUser);
                        } else {
                            LogUtil.h(MultiUsersManager.TAG, "fetchMainUserInfoForCallback HiUserInfo is null");
                            iBaseResponseCallback.d(-1, null);
                        }
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    LogUtil.h(MultiUsersManager.TAG, "fetchMainUserInfoForCallback failed");
                    iBaseResponseCallback.d(-1, null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refetchMainUserInfo() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.2
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a(MultiUsersManager.TAG, "refetchMainUserInfo HiCommonListener onSuccess pid = " + Process.myPid() + ",tid = " + Process.myTid());
                if (obj != null) {
                    try {
                        List list = obj instanceof List ? (List) obj : null;
                        if (koq.b(list)) {
                            LogUtil.h(MultiUsersManager.TAG, "refreshMainUserInfo onSuccess userInfos is null or size is zero");
                            return;
                        }
                        HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                        if (hiUserInfo != null) {
                            MultiUsersManager.this.setCurrentUserToMainUser(hiUserInfo);
                        } else {
                            LogUtil.h(MultiUsersManager.TAG, "refreshMainUserInfo onSuccess HiUserInfo is null");
                        }
                    } catch (ClassCastException e) {
                        LogUtil.b(MultiUsersManager.TAG, e.getMessage());
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b(MultiUsersManager.TAG, "falied to retrieve user info");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentUserToMainUser(final HiUserInfo hiUserInfo) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: cfg
            @Override // java.lang.Runnable
            public final void run() {
                MultiUsersManager.this.m135x239bb055(hiUserInfo);
            }
        });
    }

    /* renamed from: lambda$setCurrentUserToMainUser$1$com-huawei-health-device-fatscale-multiusers-MultiUsersManager, reason: not valid java name */
    public /* synthetic */ void m135x239bb055(HiUserInfo hiUserInfo) {
        setMainUserData(hiUserInfo);
        if (getCurrentUser() == null || getCurrentUser().i() == null) {
            setCurrentUser(this.mMainUser);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMainUserData(HiUserInfo hiUserInfo) {
        int gender = hiUserInfo.getGender();
        if (gender != 2 && gender != 0 && gender != 1) {
            gender = -100;
        }
        if (this.mMainUser == null) {
            cfi cfiVar = new cfi();
            this.mMainUser = cfiVar;
            cfiVar.e(1);
            this.mMainUser.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        }
        this.mMainUser.a((byte) gender);
        this.mMainUser.b(hiUserInfo.getWeight());
        this.mMainUser.d(hiUserInfo.getHeight());
        this.mMainUser.a(hiUserInfo.getAge());
        this.mMainUser.b(hiUserInfo.getBirthday());
        this.mMainUser.b(hiUserInfo.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveWeightUserData(String str, cfi cfiVar, boolean z, boolean z2, boolean z3) {
        if (!z3) {
            setUserPreference("custom.weight_multi_userinfo", str);
        } else {
            setUserPreference("custom.weight_multi_userinfo_expand", str);
        }
        setUserPreference("custom.weight_multi_userinfo_base", getAllUserStringWithoutPhoto());
        setUserStatus(cfiVar, z, z2);
    }

    private void syncHiOption() {
        int c = HiDateUtil.c(System.currentTimeMillis());
        if (sSyncCount < 3 && c == sPrevLastSyncDate) {
            realSyncHiOption();
            sSyncCount++;
        } else {
            if (c != sPrevLastSyncDate) {
                sPrevLastSyncDate = c;
                realSyncHiOption();
                sSyncCount = 1;
                return;
            }
            LogUtil.a(TAG, "one day can sync 3 time");
        }
    }

    private void realSyncHiOption() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setForceSync(true);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    private void setUserStatus(final cfi cfiVar, final boolean z, final boolean z2) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.3
            @Override // java.lang.Runnable
            public void run() {
                if (z2) {
                    if (z) {
                        MultiUsersManager multiUsersManager = MultiUsersManager.this;
                        multiUsersManager.setCurrentUser(multiUsersManager.mMainUser);
                        return;
                    }
                    cfi cfiVar2 = cfiVar;
                    if (cfiVar2 != null && cfiVar2.i().equals(MultiUsersManager.this.mCurrentUser.i())) {
                        MultiUsersManager.this.setCurrentUser(cfiVar);
                        return;
                    } else {
                        LogUtil.h(MultiUsersManager.TAG, "change user error");
                        return;
                    }
                }
                LogUtil.h(MultiUsersManager.TAG, "get userInfo error may be the user is null");
            }
        });
    }

    public cfi getSingleUserById(String str) {
        for (cfi cfiVar : this.mMainAllUser) {
            if (!TextUtils.isEmpty(str) && str.equals(cfiVar.i())) {
                return cfiVar;
            }
        }
        return null;
    }

    public List<cfi> getAllUser() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(fetchMainUser(getUserInfomation()));
        arrayList.addAll(getOtherUserList());
        return arrayList;
    }

    private List<cfi> getOtherUserList() {
        if (this.mMultiUsersMap.get(null) != null) {
            this.mMultiUsersMap.remove(null);
        }
        if (this.mMultiUsersMap.get("") != null) {
            this.mMultiUsersMap.remove("");
        }
        ArrayList arrayList = new ArrayList(this.mMultiUsersMap.values());
        Collections.sort(arrayList, new Comparator<cfi>() { // from class: com.huawei.health.device.fatscale.multiusers.MultiUsersManager.5
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(cfi cfiVar, cfi cfiVar2) {
                if (cfiVar == null || cfiVar2 == null) {
                    return 0;
                }
                return Collator.getInstance(Locale.getDefault()).compare(cfiVar.h(), cfiVar2.h());
            }
        });
        LogUtil.a(TAG, "MutiUsersManager getOtherUserList otherUserList size is ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cfi fetchMainUser(UserInfomation userInfomation) {
        cfi mainUser = getMainUser();
        if (mainUser != null && userInfomation != null) {
            if (!TextUtils.isEmpty(userInfomation.getName())) {
                mainUser.b(userInfomation.getName());
            } else {
                LogUtil.h(TAG, "MutiUsersManager getAllUser userInfo name is null");
            }
            mainUser.e(userInfomation.getPicPath());
        }
        if (mainUser != null && TextUtils.isEmpty(mainUser.h())) {
            mainUser.b(getAccountName());
        }
        return mainUser;
    }

    private UserInfomation getUserInfomation() {
        return ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
    }

    public List<cfi> getMainAllUser() {
        return this.mMainAllUser;
    }

    private int parseUserCountByKey(HiUserPreference hiUserPreference) {
        if (hiUserPreference == null) {
            return -1;
        }
        String value = hiUserPreference.getValue();
        if (TextUtils.isEmpty(value)) {
            return -1;
        }
        try {
            return new JSONArray(value).length();
        } catch (JSONException e) {
            LogUtil.b(TAG, "parseUserCountByKey_parseAllUserData " + e.getMessage());
            return uncompressUserDataCountByKey(value);
        }
    }

    private String getAccountName() {
        return new UpApi(BaseApplication.getContext()).getAccountName();
    }

    private int uncompressUserDataCountByKey(String str) {
        try {
            return new JSONArray(HiZipUtil.a(str)).length();
        } catch (IOException e) {
            LogUtil.b(TAG, "parseUserCountByKey_uncompressUserData " + e.getMessage());
            return -1;
        } catch (JSONException e2) {
            LogUtil.b(TAG, "parseUserCountByKey_uncompressUserData json " + e2.getMessage());
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSubToTheKey(HiUserPreference hiUserPreference, String str) {
        if (hiUserPreference != null) {
            String value = hiUserPreference.getValue();
            if (!TextUtils.isEmpty(value)) {
                try {
                    JSONArray jSONArray = new JSONArray(value);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        cfi cfiVar = new cfi(new JSONObject(jSONArray.optString(i)));
                        if (cfiVar.i() != null && cfiVar.i().equals(str)) {
                            return true;
                        }
                    }
                } catch (JSONException e) {
                    LogUtil.b(TAG, "parseUserCountByKey_parseAllUserData " + e.getMessage());
                    return uncompressUserDataSubToKey(value, str);
                }
            }
        }
        return false;
    }

    private boolean uncompressUserDataSubToKey(String str, String str2) {
        try {
            JSONArray jSONArray = new JSONArray(HiZipUtil.a(str));
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                cfi cfiVar = new cfi(new JSONObject(jSONArray.optString(i)));
                if (cfiVar.i() != null && cfiVar.i().equals(str2)) {
                    return true;
                }
            }
        } catch (IOException e) {
            LogUtil.b(TAG, "parseUserCountByKey_uncompressUserData " + e.getMessage());
        } catch (JSONException e2) {
            LogUtil.b(TAG, "parseUserCountByKey_uncompressUserData json " + e2.getMessage());
        }
        return false;
    }

    public String getSid(String str) {
        String str2;
        Iterator<Map.Entry<String, String>> it = this.mSidList.entrySet().iterator();
        String str3 = "_" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        while (true) {
            str2 = "";
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, String> next = it.next();
            String value = next.getValue();
            if (this.mSidList.containsValue(str)) {
                if (str.equals(value)) {
                    str2 = next.getKey() + str3;
                    break;
                }
            } else {
                if ("".equals(value)) {
                    str2 = next.getKey() + str3;
                    break;
                }
                LogUtil.h(TAG, "getSid default");
            }
        }
        LogUtil.a(TAG, "getSid, sid end");
        return str2;
    }

    public void setUuidBySid(String str, String str2) {
        String i = getMainUser().i();
        if (TextUtils.isEmpty(str2) || "0".equals(str2) || Constants.NULL.equalsIgnoreCase(str2) || str2.equals(i)) {
            LogUtil.a(TAG, "this is mainUser,uuid is null");
            return;
        }
        if (str.contains("_")) {
            str = str.substring(0, str.indexOf("_"));
        }
        Map<String, String> map = this.mSidList;
        if (map == null || map.isEmpty()) {
            initSidList();
        }
        if (this.mSidList.containsKey(str)) {
            if ("delete".equals(str2)) {
                this.mSidList.put(str, "");
            } else {
                this.mSidList.put(str, str2);
            }
        }
    }
}
