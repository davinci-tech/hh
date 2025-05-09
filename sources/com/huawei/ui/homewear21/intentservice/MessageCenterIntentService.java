package com.huawei.ui.homewear21.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HuaweiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.main.stories.nps.interactors.db.QuestionSurveyDataBase;
import com.huawei.ui.main.stories.nps.interactors.db.QuestionSurveyTable;
import defpackage.cvw;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class MessageCenterIntentService extends IntentService {
    private Gson e;

    public MessageCenterIntentService() {
        super("MigrateIntentService");
        this.e = new Gson();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        HuaweiHealthData huaweiHealthData;
        LogUtil.a("MessageCenterIntentService", "Enter onHandleIntent");
        if (intent != null) {
            try {
                huaweiHealthData = (HuaweiHealthData) this.e.fromJson(intent.getStringExtra("data"), HuaweiHealthData.class);
            } catch (JsonSyntaxException e) {
                LogUtil.a("MessageCenterIntentService", "JsonSyntaxException:" + e.getMessage());
                huaweiHealthData = null;
            }
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder("healthData:");
            sb.append(huaweiHealthData == null ? Constants.NULL : huaweiHealthData.toString());
            objArr[0] = sb.toString();
            LogUtil.a("MessageCenterIntentService", objArr);
            if (huaweiHealthData == null) {
                return;
            }
            if (1 == huaweiHealthData.getCommandType()) {
                d(huaweiHealthData);
                return;
            }
            if (2 == huaweiHealthData.getCommandType()) {
                String data = huaweiHealthData.getData();
                String data1 = huaweiHealthData.getData1();
                LogUtil.a("MessageCenterIntentService", "leo:" + data);
                LogUtil.a("MessageCenterIntentService", "isSupport:" + data1);
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), data, "" + data1, new StorageParams(0));
                return;
            }
            if (1000 == huaweiHealthData.getCommandType()) {
                LogUtil.a("MessageCenterIntentService", "Enter sendHealthDataTohealth");
                new CompatibilityInteractor().b(100, cvw.c());
            } else {
                LogUtil.a("MessageCenterIntentService", "onHandleIntent else");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.util.List] */
    private void d(HuaweiHealthData huaweiHealthData) {
        List<MessageObject> list;
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "migrate_success");
        LogUtil.a("MessageCenterIntentService", "getMessageCenterFromWear begin " + b);
        if ("true".equals(b)) {
            LogUtil.a("MessageCenterIntentService", "result true ,return");
            return;
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "migrate_wear_time");
        LogUtil.a("MessageCenterIntentService", "match time error,return:" + b2);
        if (Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), b2)) > OpAnalyticsConstants.H5_LOADING_DELAY) {
            LogUtil.a("MessageCenterIntentService", "match time error,return");
            return;
        }
        String data1 = huaweiHealthData.getData1();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "migrate_success", "true", new StorageParams(0));
        HwNpsManager.getInstance().setQuestionDetail(data1);
        String data2 = huaweiHealthData.getData2();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("npsDatabase:");
        sb.append(data2 == null ? Constants.NULL : data2);
        objArr[0] = sb.toString();
        LogUtil.a("MessageCenterIntentService", objArr);
        ArrayList arrayList = new ArrayList(10);
        try {
            arrayList = (List) this.e.fromJson(data2, new TypeToken<List<QuestionSurveyTable>>() { // from class: com.huawei.ui.homewear21.intentservice.MessageCenterIntentService.2
            }.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.a("MessageCenterIntentService", "JsonSyntaxException databaseList error:" + e.getMessage());
        }
        if (arrayList == null) {
            LogUtil.a("MessageCenterIntentService", "databaseList is null return");
            return;
        }
        LogUtil.a("MessageCenterIntentService", "databaseList size:" + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HwNpsManager.getInstance().migrateToDb((QuestionSurveyTable) it.next());
        }
        try {
            list = (List) this.e.fromJson(huaweiHealthData.getData(), new TypeToken<List<MessageObject>>() { // from class: com.huawei.ui.homewear21.intentservice.MessageCenterIntentService.5
            }.getType());
        } catch (JsonSyntaxException e2) {
            LogUtil.a("MessageCenterIntentService", "JsonSyntaxException error:" + e2.getMessage());
            list = null;
        }
        a(list);
    }

    private void a(List<MessageObject> list) {
        if (list == null) {
            LogUtil.a("MessageCenterIntentService", "messageList is null return");
            return;
        }
        for (MessageObject messageObject : list) {
            LogUtil.a("MessageCenterIntentService", "Enter messageList :" + messageObject.toString());
            MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            if (MessageConstant.NPS_MESSAGE_TYPE.equalsIgnoreCase(messageObject.getType())) {
                messageObject.setMsgId(messageCenterApi.getMessageId("nps", MessageConstant.NPS_MESSAGE_TYPE));
                messageCenterApi.insertMessage(messageObject);
                ArrayList arrayList = new ArrayList(10);
                arrayList.add(messageObject);
                messageCenterApi.insertMessages(arrayList);
            } else {
                LogUtil.a("MessageCenterIntentService", "Enter messageList2 :" + messageObject.toString());
            }
        }
        QuestionSurveyDataBase questionSurveyDataBase = new QuestionSurveyDataBase();
        HwNpsManager hwNpsManager = HwNpsManager.getInstance();
        ArrayList arrayList2 = new ArrayList(10);
        questionSurveyDataBase.getOldSurveyDateBase(hwNpsManager, arrayList2);
        if (!arrayList2.isEmpty()) {
            Iterator<QuestionSurveyTable> it = arrayList2.iterator();
            while (it.hasNext()) {
                LogUtil.a("MessageCenterIntentService", "Enter questionSurveyTables :", it.next().toString());
            }
            return;
        }
        LogUtil.a("MessageCenterIntentService", "Enter error");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        LogUtil.a("MessageCenterIntentService", "onCreate()");
        super.onCreate();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        LogUtil.a("MessageCenterIntentService", "onDestroy()");
        super.onDestroy();
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
