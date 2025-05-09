package com.huawei.agconnect.apms;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.agconnect.apms.anr.model.AnrFileBody;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.EventType;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class hij {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final mno bcd = new mno(".APMAnr");
    public final Handler cde;

    public class abc implements Thread.UncaughtExceptionHandler {
        public abc(hij hijVar) {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            hij.abc.warn("upload ANR info failed. " + th.getMessage());
        }
    }

    public static class bcd {
        public static final hij abc = new hij();
    }

    /* loaded from: classes8.dex */
    public static final class cde implements Runnable {
        public Context abc;

        public static class abc extends TypeToken<AnrFileBody> {
        }

        public cde(Context context) {
            this.abc = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Agent.isDisabled()) {
                return;
            }
            hij.abc.info("start upload anr files.");
            List arrayList = new ArrayList();
            try {
                arrayList = hij.bcd.abc(this.abc, false);
            } catch (Throwable th) {
                hij.abc.warn("load anr dir failed. " + th.getMessage());
            }
            for (Object obj : arrayList) {
                try {
                    if (obj instanceof File) {
                        BufferedReader bufferedReader = null;
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(new FileReader((File) obj));
                            try {
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    String readLine = bufferedReader2.readLine();
                                    if (readLine == null) {
                                        break;
                                    } else {
                                        sb.append(readLine);
                                    }
                                }
                                String sb2 = sb.toString();
                                try {
                                    bufferedReader2.close();
                                } catch (IOException unused) {
                                    hij.abc.warn("close ANR Reader failed");
                                }
                                if (sb2 != null) {
                                    try {
                                        AnrFileBody anrFileBody = (AnrFileBody) new Gson().fromJson(sb2, new abc().getType());
                                        if (anrFileBody != null) {
                                            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                                            linkedHashMap.put(HeaderType.AGENT_VERSION, anrFileBody.getAgentVersion());
                                            linkedHashMap.put(HeaderType.USER_IDENTIFIER, anrFileBody.getUserId());
                                            linkedHashMap.put("platform", anrFileBody.getPlatformInformation().toJsonString());
                                            linkedHashMap.put(EventType.USER_SETTINGS, anrFileBody.getUserSettingsInformation().toJsonString());
                                            linkedHashMap.put("device", anrFileBody.getDeviceInformation().toJsonString());
                                            linkedHashMap.put("app", anrFileBody.getApplicationInformation().toJsonString());
                                            linkedHashMap.put(EventType.ANR, anrFileBody.getAnrInfo().toJsonString());
                                            HiAnalyticsManager.getInstance().onEvent(HiAnalyticsManager.APM_EVENT_ID, linkedHashMap);
                                            hij.abc.info("upload anr info success, data size: " + linkedHashMap.toString().length());
                                            hij.abc((File) obj);
                                            HiAnalyticsManager.getInstance().onReport();
                                        }
                                    } catch (Throwable unused2) {
                                        hij.abc((File) obj);
                                    }
                                }
                            } catch (IOException unused3) {
                                bufferedReader = bufferedReader2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException unused4) {
                                        hij.abc.warn("close ANR Reader failed");
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = bufferedReader2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException unused5) {
                                        hij.abc.warn("close ANR Reader failed");
                                    }
                                }
                                throw th;
                            }
                        } catch (IOException unused6) {
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else {
                        continue;
                    }
                } catch (Throwable th4) {
                    hij.abc.warn("upload anr files failed. " + th4.getMessage());
                }
            }
            try {
                List abc2 = hij.bcd.abc(this.abc, true);
                int size = abc2.size();
                for (int i = 0; i < size - 10; i++) {
                    if (abc2.get(i) instanceof File) {
                        hij.abc((File) abc2.get(i));
                    }
                }
            } catch (Throwable unused7) {
                hij.abc.debug("delete unused files failed. ");
            }
        }
    }

    public hij() {
        HandlerThread handlerThread = new HandlerThread("upload-anr-thread");
        handlerThread.setUncaughtExceptionHandler(new abc(this));
        handlerThread.start();
        this.cde = new Handler(handlerThread.getLooper());
    }

    public static void abc(File file) {
        if (file.delete()) {
            return;
        }
        abc.info("delete anr file failed.");
    }
}
