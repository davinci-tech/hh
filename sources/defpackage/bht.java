package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.LogUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class bht {
    private DeviceCapability d;

    private bht(DeviceCapability deviceCapability) {
        this.d = deviceCapability;
    }

    private static boolean a(LinkedHashMap<String, Boolean> linkedHashMap, DeviceCapability deviceCapability) {
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            LogUtil.a("UpdateDeviceCapability", "receiveData is empty");
            return false;
        }
        if (deviceCapability != null) {
            return true;
        }
        LogUtil.a("UpdateDeviceCapability", "deviceCapability is null");
        return false;
    }

    public static void d(LinkedHashMap<String, Boolean> linkedHashMap, DeviceCapability deviceCapability) {
        if (a(linkedHashMap, deviceCapability)) {
            bht bhtVar = new bht(deviceCapability);
            for (Map.Entry<String, Boolean> entry : linkedHashMap.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key) || !key.contains(".")) {
                    LogUtil.e("UpdateDeviceCapability", "key = ", key);
                    return;
                }
                Boolean value = entry.getValue();
                String substring = key.substring(0, key.indexOf("."));
                try {
                    int parseInt = Integer.parseInt(substring);
                    if (parseInt <= 13) {
                        bhtVar.b(substring, key, value);
                    } else if (parseInt <= 25) {
                        bhtVar.a(substring, key, value);
                    } else if (parseInt <= 35) {
                        bhtVar.e(substring, key, value);
                    } else {
                        bhtVar.c(substring, key, value);
                    }
                } catch (NumberFormatException unused) {
                    LogUtil.e("UpdateDeviceCapability", "updateDeviceCapability NumberFormatException exception");
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        switch(r0) {
            case 1567: goto L18;
            case 1568: goto L14;
            case 1569: goto L10;
            case 1570: goto L6;
            default: goto L56;
        };
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.lang.String r2, java.lang.String r3, java.lang.Boolean r4) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bht.b(java.lang.String, java.lang.String, java.lang.Boolean):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        switch(r0) {
            case 1598: goto L26;
            case 1599: goto L22;
            case 1600: goto L18;
            case 1601: goto L14;
            case 1602: goto L10;
            case 1603: goto L6;
            default: goto L52;
        };
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(java.lang.String r2, java.lang.String r3, java.lang.Boolean r4) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bht.a(java.lang.String, java.lang.String, java.lang.Boolean):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void e(String str, String str2, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1604) {
            if (str.equals("26")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 1605) {
            if (str.equals(HealthZonePushReceiver.BLOOD_SUGAR_DAWN_NOTIFY)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 1607) {
            switch (hashCode) {
                case 1631:
                    if (str.equals(HealthZonePushReceiver.PRESSURE_NOTIFY)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1632:
                    if (str.equals(HealthZonePushReceiver.CYCLE_BLOOD_OXYGEN_NOTIFY)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1633:
                    if (str.equals(HealthZonePushReceiver.BODY_TEMPERATURE_HIGH_NOTIFY)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 1634:
                    if (str.equals(HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals(HealthZonePushReceiver.SLEEP_TIME_NOTIFY)) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                b(str2, bool);
                break;
            case 1:
                as(str2, bool);
                break;
            case 2:
                z(str2, bool);
                break;
            case 3:
                ao(str2, bool);
                break;
            case 4:
                v(str2, bool);
                break;
            case 5:
                ai(str2, bool);
                break;
            case 6:
                ag(str2, bool);
                break;
            default:
                LogUtil.a("UpdateDeviceCapability", "updateExpansionCapability unknown serviceId.");
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void c(String str, String str2, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1662) {
            if (str.equals("42")) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode == 1663) {
            if (str.equals(HealthZonePushReceiver.ECG_MEASUREMENT_ABNORMAL)) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode == 1665) {
            if (str.equals("45")) {
                c = 6;
            }
            c = 65535;
        } else if (hashCode == 1666) {
            if (str.equals("46")) {
                c = 7;
            }
            c = 65535;
        } else if (hashCode != 1668) {
            switch (hashCode) {
                case 1635:
                    if (str.equals(HealthZonePushReceiver.DATA_POST_COMMENTS_NOTIFY)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1636:
                    if (str.equals(HealthZonePushReceiver.PROACTIVE_SHARING_NOTIFY)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1637:
                    if (str.equals(HealthZonePushReceiver.COMMENT_FAILED_NOTIFY)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1638:
                    if (str.equals("39")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 1691:
                            if (str.equals("50")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1692:
                            if (str.equals("51")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1693:
                            if (str.equals("52")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1694:
                            if (str.equals("53")) {
                                c = '\f';
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
            }
        } else {
            if (str.equals("48")) {
                c = '\b';
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                ak(str2, bool);
                break;
            case 1:
                ac(str2, bool);
                break;
            case 2:
                au(str2, bool);
                break;
            case 3:
                ar(str2, bool);
                break;
            case 4:
                w(str2, bool);
                break;
            case 5:
                ap(str2, bool);
                break;
            case 6:
                an(str2, bool);
                break;
            case 7:
                t(str2, bool);
                break;
            case '\b':
                af(str2, bool);
                break;
            case '\t':
                u(str2, bool);
                break;
            case '\n':
                aq(str2, bool);
                break;
            case 11:
                q(str2, bool);
                break;
            case '\f':
                at(str2, bool);
                break;
            default:
                LogUtil.a("UpdateDeviceCapability", "updateIntelligentCapability unknown serviceId.");
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void g(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 48567:
                if (str.equals("1.4")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 48570:
                if (str.equals("1.7")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 48571:
                if (str.equals("1.8")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 48572:
                if (str.equals("1.9")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1505532:
                if (str.equals("1.10")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1505569:
                if (str.equals("1.26")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1505570:
                if (str.equals("1.27")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1505572:
                if (str.equals("1.29")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1505628:
                if (str.equals("1.43")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1505633:
                if (str.equals("1.48")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.d.configureSupportTimeSetting(bool.booleanValue());
                break;
            case 1:
                this.d.configureSupportGetFirmwareVersion(bool.booleanValue());
                break;
            case 2:
                this.d.configureSupportGetBattery(bool.booleanValue());
                break;
            case 3:
                this.d.configureAutoLightScreen(bool.booleanValue());
                break;
            case 4:
                this.d.configureAvoidDisturb(bool.booleanValue());
                break;
            case 5:
                this.d.configureSupportLeftRightHandWearMode(bool.booleanValue());
                break;
            case 6:
                this.d.configureRotateSwitchScreen(bool.booleanValue());
                break;
            case 7:
                this.d.configureSupportQueryAllowDisturbContent(bool.booleanValue());
                break;
            case '\b':
                this.d.configureSupportAppId(bool.booleanValue());
                break;
            case '\t':
                this.d.configureSupportUserSetting(bool.booleanValue());
                break;
            default:
                j(str, bool);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        switch(r0) {
            case 1505538: goto L35;
            case 1505539: goto L31;
            case 1505540: goto L27;
            case 1505541: goto L23;
            default: goto L5;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
    
        switch(r0) {
            case 1505597: goto L19;
            case 1505598: goto L15;
            case 1505599: goto L11;
            case 1505600: goto L7;
            default: goto L45;
        };
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j(java.lang.String r2, java.lang.Boolean r3) {
        /*
            Method dump skipped, instructions count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bht.j(java.lang.String, java.lang.Boolean):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void f(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1505563:
                if (str.equals("1.20")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1505626:
                if (str.equals("1.41")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1505631:
                if (str.equals("1.46")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1505632:
                if (str.equals("1.47")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1505634:
                if (str.equals("1.49")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1505656:
                if (str.equals("1.50")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1505659:
                if (str.equals("1.53")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1505660:
                if (str.equals("1.54")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1505661:
                if (str.equals("1.55")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.d.configureGoldCard(bool.booleanValue());
                break;
            case 1:
                this.d.configureSupportRemoteCamera(bool.booleanValue());
                break;
            case 2:
                this.d.configureSupportHttps(bool.booleanValue());
                break;
            case 3:
                this.d.configureSupportSyncWifi(bool.booleanValue());
                break;
            case 4:
                this.d.configureSupportSettingRelated(bool.booleanValue());
                break;
            case 5:
                this.d.configureSupportZoneId(bool.booleanValue());
                break;
            case 6:
                this.d.configureSupportConnectStatus(bool.booleanValue());
                break;
            case 7:
                this.d.configureSupportSyncTime(bool.booleanValue());
                break;
            case '\b':
                this.d.configureSupportExpandCapability(bool.booleanValue());
                break;
            default:
                LogUtil.a("UpdateDeviceCapability", "setDmsOthersCapability unknown commandId.");
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void ah(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 49525:
                if (str.equals("2.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49526:
            case 49527:
            default:
                c = 65535;
                break;
            case 49528:
                if (str.equals("2.4")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 49529:
                if (str.equals("2.5")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 49530:
                if (str.equals("2.6")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49531:
                if (str.equals("2.7")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 49532:
                if (str.equals("2.8")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureMessageAlert(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSupportMessageAlertInfo(bool.booleanValue());
            return;
        }
        if (c == 2) {
            this.d.configureSupportMessageSupportInfo(bool.booleanValue());
            return;
        }
        if (c == 3) {
            this.d.configureSupportDeleteMsg(bool.booleanValue());
            return;
        }
        if (c == 4) {
            this.d.configureupportMessageCenterPushDevice(bool.booleanValue());
        } else if (c == 5) {
            this.d.configureSupportWearMessagePush(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setNotificationCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 50486:
                if (str.equals("3.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50487:
            default:
                c = 65535;
                break;
            case 50488:
                if (str.equals("3.3")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 50489:
                if (str.equals(Constants.INTER_VERSION)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureContacts(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSyncHiCall(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureSyncContacts(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setAddressBookCapability unknown commandId.");
        }
    }

    private void h(String str, Boolean bool) {
        if ("4.1".equals(str)) {
            this.d.configureSupportCallingOperationType(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setCallingCapability unknown commandId.");
        }
    }

    private void am(String str, Boolean bool) {
        if ("5.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setPingRingCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setPingRingCapability unknown commandId.");
        }
    }

    private void ad(String str, Boolean bool) {
        if ("6.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setMusicCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMusicCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void l(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 54332:
                if (str.equals("7.3")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1684282:
                if (str.equals("7.14")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1684284:
                if (str.equals("7.16")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1684287:
                if (str.equals("7.19")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1684310:
                if (str.equals("7.21")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1684311:
                if (str.equals("7.22")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1684312:
                if (str.equals("7.23")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1684313:
                if (str.equals("7.24")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1684316:
                if (str.equals("7.27")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1684317:
                if (str.equals("7.28")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.d.configureSupportSportTotal(bool.booleanValue());
                break;
            case 1:
                this.d.configureSupportThreshold(bool.booleanValue());
                break;
            case 2:
                this.d.configureReserveSync(bool.booleanValue());
                break;
            case 3:
                this.d.configureIsSupportHeartRateZone(bool.booleanValue());
                break;
            case 4:
                this.d.configureSupportGetUserInfo(bool.booleanValue());
                break;
            case 5:
                this.d.configureIsSupportCoreSleep(bool.booleanValue());
                break;
            case 6:
                this.d.configureisSupportHeartRateEnable(bool.booleanValue());
                break;
            case 7:
                this.d.configureIsSupportSendCoreSleepOutState(bool.booleanValue());
                break;
            case '\b':
                this.d.configureIsSupportQueryDeviceCoreSleepSwitch(bool.booleanValue());
                break;
            case '\t':
                this.d.configureSupportContinueHeartRate(bool.booleanValue());
                break;
            default:
                m(str, bool);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void m(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1684318:
                if (str.equals("7.29")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1684340:
                if (str.equals("7.30")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1684341:
                if (str.equals("7.31")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1684343:
                if (str.equals("7.33")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1684344:
                if (str.equals("7.34")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1684345:
                if (str.equals("7.35")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1684346:
                if (str.equals("7.36")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1684347:
                if (str.equals("7.37")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1684371:
                if (str.equals("7.40")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1684372:
                if (str.equals("7.41")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.d.configureHeartRateRaiseAlarm(bool.booleanValue());
                break;
            case 1:
                this.d.configureGetHeartRateRaiseAlarmNumber(bool.booleanValue());
                break;
            case 2:
                this.d.configureGetHighAndMiddleSport(bool.booleanValue());
                break;
            case 3:
                this.d.configureSetHrrHeartRateCapability(bool.booleanValue());
                break;
            case 4:
                this.d.configureHeartRateDownAlarm(bool.booleanValue());
                break;
            case 5:
                this.d.configureSupportRestHeartRateControls(bool.booleanValue());
                break;
            case 6:
                this.d.configureCycleBloodOxygenSwitch(bool.booleanValue());
                break;
            case 7:
                this.d.configureBloodOxygenDownRemind(bool.booleanValue());
                break;
            case '\b':
                this.d.configureRunPaceSetCapability(bool.booleanValue());
                break;
            case '\t':
                this.d.configureSupportMediumToHighStrengthPreValue(bool.booleanValue());
                break;
            default:
                n(str, bool);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void n(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 54330) {
            if (str.equals("7.1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1684278) {
            switch (hashCode) {
                case 54334:
                    if (str.equals("7.5")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 54335:
                    if (str.equals("7.6")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 54336:
                    if (str.equals("7.7")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 54337:
                    if (str.equals("7.8")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 54338:
                    if (str.equals("7.9")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("7.10")) {
                c = 6;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                if (bool != null && bool.booleanValue()) {
                    this.d.configureMotionGoalCap(1);
                    break;
                }
                break;
            case 1:
                if (bool != null && bool.booleanValue()) {
                    LogUtil.c("UpdateDeviceCapability", "setFitnessSpecialCapability support TYPE_COMPRESSED");
                    this.d.configureFitnessFrameType(0);
                    break;
                }
                break;
            case 2:
            case 3:
                this.d.configureActivityReminder(bool.booleanValue());
                break;
            case 4:
                if (bool != null && bool.booleanValue()) {
                    LogUtil.c("UpdateDeviceCapability", "setFitnessSpecialCapability support TYPE_COMMON");
                    this.d.configureFitnessFrameType(1);
                    break;
                }
                break;
            case 5:
                this.d.configureSupportSetUserInfoEncrypt(bool.booleanValue());
                break;
            case 6:
                if (bool != null) {
                    if (bool.booleanValue()) {
                        LogUtil.c("UpdateDeviceCapability", "setFitnessSpecialCapability support TYPE_SEPARATED");
                        this.d.configureFitnessFrameType(3);
                    }
                    this.d.configureSupportSampleFrame(bool.booleanValue());
                    break;
                }
                break;
            default:
                LogUtil.a("UpdateDeviceCapability", "setFitnessSpecialCapability unknown commandId.");
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 55291:
                if (str.equals("8.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 55292:
                if (str.equals("8.2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 55293:
                if (str.equals("8.3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureEventAlarm(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSmartAlarm(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureChangeAlarm(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setAlarmCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void ae(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode != 56252) {
            switch (hashCode) {
                case 1743861:
                    if (str.equals("9.11")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1743862:
                    if (str.equals("9.12")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1743863:
                    if (str.equals("9.13")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1743864:
                    if (str.equals("9.14")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1743865:
                    if (str.equals("9.15")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("9.1")) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            this.d.configureOtaUpdate(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSyncBeforeOta(bool.booleanValue());
            return;
        }
        if (c == 2) {
            this.d.configureIsSupportAutoUpdate(bool.booleanValue());
            return;
        }
        if (c == 3) {
            this.d.configureIsSupportUpdateChange(bool.booleanValue());
            return;
        }
        if (c == 4) {
            this.d.configureIsSupportNotifyDeviceNewVersion(bool.booleanValue());
        } else if (c == 5) {
            this.d.configureIsSupportDeviceRequestCheck(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setOtaCapability unknown commandId.");
        }
    }

    private void y(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1507362) {
            if (str.equals("10.1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1507370) {
            if (hashCode == 46728270 && str.equals("10.10")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("10.9")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.d.configureMaintenance(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureMaintenanceInTime(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureMaintenanceGetData(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMaintenanceCapability unknown commandId.");
        }
    }

    private void r(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("11.1")) {
            this.d.configureSupportAntiLost(bool.booleanValue());
        } else if (str.equals("11.3")) {
            this.d.configureBluetoothOffAlert(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setLinkLossCapability unknown commandId.");
        }
    }

    private void o(String str, Boolean bool) {
        if ("12.1".equals(str)) {
            this.d.configureLanguage(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setFontCapability unknown commandId.");
        }
    }

    private void al(String str, Boolean bool) {
        if ("13.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setPhoneBatteryCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setPhoneBatteryCapability unknown commandId.");
        }
    }

    private void ab(String str, Boolean bool) {
        if ("14.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setMotionCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMotionCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void av(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1512167) {
            if (str.equals("15.1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1512169) {
            switch (hashCode) {
                case 1512171:
                    if (str.equals("15.5")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1512172:
                    if (str.equals("15.6")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1512173:
                    if (str.equals("15.7")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1512174:
                    if (str.equals("15.8")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 46877225:
                            if (str.equals("15.10")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 46877226:
                            if (str.equals("15.11")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 46877227:
                            if (str.equals("15.12")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
            }
        } else {
            if (str.equals("15.3")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                this.d.configureWeatherPush(bool.booleanValue());
                break;
            case 1:
                this.d.configureSupportAtmosphere(bool.booleanValue());
                break;
            case 2:
                this.d.configureSupportUnitWeather(bool.booleanValue());
                break;
            case 3:
                this.d.configureAtmosphereSupportExpand(bool.booleanValue());
                break;
            case 4:
                this.d.configureWeatherSupportErrorCode(bool.booleanValue());
                break;
            case 5:
                this.d.configureWeatherSupportFutureInfo(bool.booleanValue());
                break;
            case 6:
                this.d.setSupportDeviceFutureWeatherCapability(bool.booleanValue());
                break;
            case 7:
                this.d.setSupportTide(bool.booleanValue());
                break;
            case '\b':
                this.d.setSupportWeatherErrorCode(bool.booleanValue());
                break;
            default:
                LogUtil.a("UpdateDeviceCapability", "setWeatherCapability unknown commandId.");
                break;
        }
    }

    private void c(String str, Boolean bool) {
        if ("16.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setCalendarCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setCalendarCapability unknown commandId.");
        }
    }

    private void aa(String str, Boolean bool) {
        if ("17.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setMusicManagementCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMusicManagementCapability unknown commandId.");
        }
    }

    private void k(String str, Boolean bool) {
        if ("18.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setFileTransferCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setFileTransferCapability unknown commandId.");
        }
    }

    private void e(String str, Boolean bool) {
        if ("19.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setBtFactoryTestCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setBtFactoryTestCapability unknown commandId.");
        }
    }

    private void x(String str, Boolean bool) {
        if ("20.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setMcuFactoryTestCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMcuFactoryTestCapability unknown commandId.");
        }
    }

    private void aj(String str, Boolean bool) {
        if ("21.1".equals(str)) {
            LogUtil.a("UpdateDeviceCapability", "setSensorCapability supportValue :", bool);
        } else {
            LogUtil.a("UpdateDeviceCapability", "setSensorCapability unknown commandId.");
        }
    }

    private void i(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1539075) {
            if (str.equals("22.1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1539077) {
            if (hashCode == 1539081 && str.equals("22.7")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("22.3")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.d.configureSupportExerciseAdvice(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSupportExerciseAdviceTime(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureSupportExerciseAdviceMonitor(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setExerciseAdviceCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void ax(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1540036:
                if (str.equals("23.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1540039:
                if (str.equals("23.4")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1540041:
                if (str.equals("23.6")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1540042:
                if (str.equals("23.7")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 47741165:
                if (str.equals("23.11")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 47741166:
                if (str.equals("23.12")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 47741170:
                if (str.equals("23.16")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 47741172:
                if (str.equals("23.18")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 47741196:
                if (str.equals("23.21")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 47741198:
                if (str.equals("23.23")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.d.configureSupportWorkout(bool.booleanValue());
                break;
            case 1:
                this.d.configureSupportWorkoutInfo(bool.booleanValue());
                break;
            case 2:
                this.d.configureSupportWorkoutReminder(bool.booleanValue());
                break;
            case 3:
                this.d.configureSupportWorkoutRecord(bool.booleanValue());
                break;
            case 4:
                this.d.configureSupportWorkoutExerciseDisplayLink(bool.booleanValue());
                break;
            case 5:
                this.d.configureSupportWorkoutRecordPaceMap(bool.booleanValue());
                break;
            case 6:
                this.d.configureSupportRunPosture(bool.booleanValue());
                break;
            case 7:
                this.d.configureSupportInformCloseOrOpen(bool.booleanValue());
                break;
            case '\b':
                this.d.configureSupportWorkoutCapabilicy(bool.booleanValue());
                break;
            case '\t':
                this.d.configureSupportWorkoutTrustHeartRate(bool.booleanValue());
                break;
            default:
                LogUtil.a("UpdateDeviceCapability", "setWorkoutCapability unknown commandId.");
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void p(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1540997:
                if (str.equals("24.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1540998:
                if (str.equals("24.2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1540999:
            case 1541003:
            case 1541004:
            default:
                c = 65535;
                break;
            case 1541000:
                if (str.equals("24.4")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1541001:
                if (str.equals("24.5")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1541002:
                if (str.equals("24.6")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1541005:
                if (str.equals("24.9")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureSupportGpsLocation(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSupportGpsData(bool.booleanValue());
            return;
        }
        if (c == 2) {
            this.d.configureSupportGpsSetParameter(bool.booleanValue());
            return;
        }
        if (c == 3) {
            this.d.configureSupportGpsPostProcessing(bool.booleanValue());
            return;
        }
        if (c == 4) {
            this.d.configureSupportGpsSearchStarOptimization(bool.booleanValue());
        } else if (c == 5) {
            this.d.setSupportActivityRecognitionStatus(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setGpsLocationCapability unknown commandId.");
        }
    }

    private void s(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("25.1")) {
            this.d.configureSupportHeartRateInfo(bool.booleanValue());
        } else if (str.equals("25.4")) {
            this.d.configureSupportStressInfo(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setHeartRateCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void b(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1542919:
                if (str.equals("26.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1542920:
            case 1542922:
            default:
                c = 65535;
                break;
            case 1542921:
                if (str.equals("26.3")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1542923:
                if (str.equals("26.5")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1542924:
                if (str.equals("26.6")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1542925:
                if (str.equals("26.7")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureSupportAccount(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.setSupportElectronicCard(bool.booleanValue());
            return;
        }
        if (c == 2) {
            this.d.configureSupportChangePhonePair(bool.booleanValue());
            return;
        }
        if (c == 3) {
            this.d.configureSupportAccountSwitch(bool.booleanValue());
        } else if (c == 4) {
            this.d.setSupportSyncAccount(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setAccountCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void as(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1543880:
                if (str.equals("27.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 47860333:
                if (str.equals("27.15")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 47860364:
                if (str.equals("27.25")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 47860365:
                if (str.equals("27.26")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureSupportPay(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.setSupportOneTouch(bool.booleanValue());
            return;
        }
        if (c == 2) {
            this.d.configureSupportWalletOpenCard(bool.booleanValue());
        } else if (c == 3) {
            this.d.configureHideWalletEntrance(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setWalletCapability unknown commandId.");
        }
    }

    private void z(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1545802) {
            if (str.equals("29.1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1545807) {
            if (hashCode == 47919910 && str.equals("29.10")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("29.6")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.d.configureSupportEsim(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSupportMultiSim(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureSupportNewEsim(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMultipleSimCapability unknown commandId.");
        }
    }

    private void ao(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1568866) {
            if (str.equals("32.1")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1568874) {
            if (hashCode == 48634894 && str.equals("32.10")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("32.9")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.d.configureSupportStress(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configruePressAutoMonitor(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureIsSupportStressAppToDevice(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setStressCapability unknown commandId.");
        }
    }

    private void v(String str, Boolean bool) {
        if ("33.1".equals(str)) {
            this.d.configureSupportMidware(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMidWareCapability unknown commandId.");
        }
    }

    private void ai(String str, Boolean bool) {
        if ("34.1".equals(str)) {
            this.d.configureSupportOneLevelMenu(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setOneLevelMenuCapability unknown commandId.");
        }
    }

    private void ag(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("35.2")) {
            this.d.configureSupportAtrialOperator(bool.booleanValue());
        } else if (str.equals("35.11")) {
            this.d.configureSupportEcgAuth(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setOneLevelRriCapability unknown commandId.");
        }
    }

    private void ak(String str, Boolean bool) {
        if ("36.1".equals(str)) {
            this.d.configureSupportPosture(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setPostureCapability unknown commandId.");
        }
    }

    private void ac(String str, Boolean bool) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1573672) {
            if (str.equals("37.2")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1573674) {
            if (hashCode == 48783853 && str.equals("37.14")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("37.4")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            this.d.configureSupportMusicControl(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureIsSupportMusicInfoList(bool.booleanValue());
        } else if (c == 2) {
            this.d.setSupportSendSwitchStatus(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMusicControlCapability unknown commandId.");
        }
    }

    private void au(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("38.2")) {
            this.d.configureSupportAutoDetectMode(bool.booleanValue());
        } else if (str.equals("38.3")) {
            this.d.configureSupportFootWear(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setWorkModeCapability unknown commandId.");
        }
    }

    private void ar(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("39.1")) {
            this.d.setSupportWatchFace(bool.booleanValue());
        } else if (str.equals("39.14")) {
            this.d.configureSupportWatchFaceAppId(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setWatchFaceCapability unknown commandId.");
        }
    }

    private void w(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("42.1")) {
            this.d.setSupportMarketFace(bool.booleanValue());
        } else if (str.equals("42.6")) {
            this.d.setSupportMarketParams(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMarketCapability unknown commandId.");
        }
    }

    private void ap(String str, Boolean bool) {
        if ("43.18".equals(str)) {
            this.d.setSupportTws(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setTwsCapability unknown commandId.");
        }
    }

    private void an(String str, Boolean bool) {
        if ("45.1".equals(str)) {
            this.d.configureSupportSleepBreathe(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setSleepBreatheCapability unknown commandId.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void t(String str, Boolean bool) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1602501:
                if (str.equals("46.1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1602502:
                if (str.equals("46.2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1602503:
                if (str.equals("46.3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.d.configureSupportHiCarDriverRemind(bool.booleanValue());
            return;
        }
        if (c == 1) {
            this.d.configureSupportMessageFeedback(bool.booleanValue());
        } else if (c == 2) {
            this.d.configureSupportWearStatus(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setHiCarCapability unknown commandId.");
        }
    }

    private void af(String str, Boolean bool) {
        if ("48.1".equals(str)) {
            this.d.configureSupportPhd(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setPhdCapability unknown commandId.");
        }
    }

    private void u(String str, Boolean bool) {
        if ("50.1".equals(str)) {
            this.d.configureSupportMenstrual(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setMenstrualCapability unknown commandId.");
        }
    }

    private void aq(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("51.1")) {
            this.d.configureSupportSosTransmission(bool.booleanValue());
        } else if (str.equals("51.2")) {
            this.d.configureSupportSendSosSms(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setSosCapability unknown commandId.");
        }
    }

    private void q(String str, Boolean bool) {
        if ("52.1".equals(str)) {
            this.d.configureSupportHiWear(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setHiWearCapability unknown commandId.");
        }
    }

    private void at(String str, Boolean bool) {
        str.hashCode();
        if (str.equals("53.3")) {
            LogUtil.c("UpdateDeviceCapability", "setWearEngineCapability KEY_WEAR_ENGINE_EVENT_NOTIFY : ", bool);
            this.d.configureSupportWearEngine(bool.booleanValue());
        } else if (str.equals("53.4")) {
            LogUtil.c("UpdateDeviceCapability", "setWearEngineCapability KEY_WEAR_ENGINE_DEVICE_SPACE : ", bool);
            this.d.configureSupportCheckDeviceSpace(bool.booleanValue());
        } else {
            LogUtil.a("UpdateDeviceCapability", "setWearEngineCapability unknown commandId.");
        }
    }
}
