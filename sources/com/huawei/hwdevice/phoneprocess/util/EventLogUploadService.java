package com.huawei.hwdevice.phoneprocess.util;

import android.app.IntentService;
import android.content.Intent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.jgo;
import defpackage.jgr;
import defpackage.jrr;
import defpackage.kkf;

/* loaded from: classes5.dex */
public class EventLogUploadService extends IntentService {
    public EventLogUploadService() {
        super("EventLogUploadService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            LogUtil.a("EventLogUploadService", "intent is null");
            return;
        }
        jgr jgrVar = new jgr();
        jgrVar.d(intent.getStringExtra("appId"));
        jgrVar.a(intent.getStringExtra("deviceId"));
        jgrVar.b(intent.getIntExtra("iversion", 1));
        jgrVar.d(intent.getIntExtra("siteId", 1));
        jgrVar.a(intent.getIntExtra("source", 2));
        jgrVar.e(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        jgrVar.e(intent.getIntExtra("tokenType", ThirdLoginDataStorageUtil.getTokenTypeValue()));
        jgrVar.c(intent.getLongExtra("ts", 0L));
        jgo jgoVar = new jgo();
        jgoVar.d(intent.getStringExtra("x-huid"));
        jgoVar.o(intent.getStringExtra("x-version"));
        jgoVar.g(intent.getStringExtra("filePath"));
        jgoVar.b(jgrVar);
        jgoVar.a(intent.getStringExtra("countryCode"));
        jgoVar.e(intent.getStringExtra(FaqConstants.FAQ_EMUIVERSION));
        jgoVar.i(intent.getStringExtra("model"));
        jgoVar.f(intent.getStringExtra("osVersion"));
        jgoVar.j(intent.getStringExtra(FaqConstants.FAQ_ROMVERSION));
        jgoVar.h(intent.getStringExtra(FaqConstants.FAQ_SHASN));
        LogUtil.c("EventLogUploadService", "evenLogUpload ", jgoVar.toString());
        new kkf(jgoVar).run();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        LogUtil.c("EventLogUploadService", "onCreate()");
        super.onCreate();
        jrr.e().bJg_(getApplication());
    }
}
