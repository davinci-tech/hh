package com.huawei.openalliance.ad.constant;

import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.openalliance.ad.inter.data.AdEventType;
import com.huawei.openalliance.ad.utils.bl;
import com.huawei.operation.beans.TitleBean;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public enum EventType {
    RESPONSE(TrackConstants$Opers.RESPONSE, 3),
    SHOW("imp", 1),
    PHYIMP(InterstitialKeys.RPT_PHYIMP, 3),
    SHOWSTART(InterstitialKeys.RPT_SHOW_START, 3),
    CLICK("click", 2),
    CLOSE("userclose", 3),
    WEBOPEN("webopen", 3),
    WEBCLOSE("webclose", 3),
    WEBLOADFINISH("webloadfinish", 3),
    SWIPEUP("swipeup", 3),
    REMOVE("remove", 3),
    SHARE(TitleBean.RIGHT_BTN_TYPE_SHARE, 3),
    FAVORITE("favorite", 3),
    LINKEDCONTINUEPLAY("linkedContinuePlay", 3),
    VIDEOPLAYEND("playEnd", 3),
    VIDEOPLAYSTART("playStart", 3),
    VIDEOPLAYPAUSE(InterstitialKeys.RPT_PLAY_PAUSE, 3),
    VIDEOPLAYRESUME(InterstitialKeys.RPT_PLAY_RESUME, 3),
    APPOPEN("appOpen", 3),
    APPDOWNLOAD("download", 3),
    APPINSTALL(JsbMapKeyNames.H5_TEXT_DOWNLOAD_INSTALL, 3),
    APPINSTALLSTART("installStart", 3),
    APPINSTALLFAIL("installFail", 3),
    APPDOWNLOADSTART("downloadstart", 3),
    APPDOWNLOADCANCEL("downloadCancel", 3),
    APPDOWNLOADPAUSE("downloadPause", 3),
    APPDOWNLOADRESUME("downloadResume", 3),
    APPDOWNLOADFAIL("downloadFail", 3),
    ANALYSIS(TrackConstants$Events.EXCEPTION, 4),
    INTENTSUCCESS(AdEventType.INTENTSUCCESS, 3),
    INTENTFAIL("intentFail", 3),
    ADPRECHECK("adPreCheck", 3),
    SOUNDCLICKON("soundClickOn", 3),
    SOUNDCLICKOFF("soundClickOff", 3),
    ADREWARDED("adRewarded", 3),
    SERVE("serve", 3),
    ADLOADED("adLoaded", 3),
    REPLAY("rePlay", 3),
    PLAYBTNSTART("playBtnStart", 3),
    PLAYBTNPAUSE("playBtnPause", 3),
    REPEATEDIMP("repeatedImp", 3),
    REPEATEDCLICK("repeatedClick", 3),
    POSITIVEFEEDBACK("positiveFeedback", 3),
    VASTFIRSTQURAT("vastFirstQuart", 3),
    VASTMIDPOINT("vastMidPoint", 3),
    VASTTHIRDQUART("vastThirdQuart", 3),
    VASTPLAYSTART("vastPlayStart", 3),
    VASTPLAYCOMPLETE("vastPlayComplete", 3),
    EASTEREGGEND("easterEggEnd", 3),
    EASTEREGGCLOSE("easterEggClose", 3),
    EASTEREGGSHOW("easterEggImp", 3),
    FAOPENSUCCESS("faOpenSuccess", 3),
    FAOPENFAIL("faOpenFail", 3),
    INTERACTEND("interactEnd", 3),
    INTERACTSHOW("interactImp", 3),
    SUPPLEMENTIMP("supplementImp", 1),
    EASTEREGGCLICK(ParamConstants.CallbackMethod.ON_EASTER_EGG_CLICK, 3),
    PRAISE("praise", 4),
    VIDEOTIME("playTime", 3),
    SHOWEND("showstop", 3),
    SKIP("skip", 3);

    private static final Map<String, String> EVENT_LIST = new HashMap();
    private static final int EVENT_START_INDEX = 801;
    private final int category;
    private final String event;

    /* loaded from: classes9.dex */
    public interface Category {
        public static final int ANALYSIS_EVENT = 4;
        public static final int CLICK_EVENT = 2;
        public static final int IMP_EVENT = 1;
        public static final int NORMAL_EVENT = 3;
    }

    public String value() {
        return this.event;
    }

    public int getCategory() {
        return this.category;
    }

    public static String getEventTypeIndex(String str) {
        Map<String, String> map = EVENT_LIST;
        return !bl.a(map) ? map.get(str) : "";
    }

    EventType(String str, int i) {
        this.event = str;
        this.category = i;
    }

    static {
        int i = 801;
        for (EventType eventType : values()) {
            EVENT_LIST.put(eventType.value(), String.valueOf(i));
            i++;
        }
    }
}
