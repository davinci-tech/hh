package com.huawei.pluginmessagecenter.provider.data;

import com.huawei.health.messagecenter.model.MessageExt;
import java.util.List;

/* loaded from: classes6.dex */
public class CloudMessageObject {
    private int infoRecommend;
    private int isShowToNotice;
    private int layout;
    private List<MessageExt> messageExtList;
    private int msgPosition;
    private String msgUserLable;
    private int pageType;
    private int readFlag;
    private String msgId = "";
    private int msgType = 1;
    private int flag = 0;
    private int weight = 0;
    private String msgTitle = "";
    private String msgContext = "";
    private long createTime = 0;
    private String expireTime = "0";
    private String imgUri = "";
    private String imgBigUri = "";
    private String detailUri = "";
    private String from = "";
    private String position = String.valueOf(1);
    private String msgDevice = "";
    private int msgMaterial = 0;
    private String infoClassify = "";
    private String heatMapCity = "";
    private int imageTextSeparateSwitch = 0;
    private String harmonyImgUrl = "";
    private String hmImgSize = "";

    public CloudMessageObject() {
        this.msgPosition = 11;
        this.msgPosition = 11;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public String getMsgTitle() {
        return this.msgTitle;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public int getFlag() {
        return this.flag;
    }

    public String getMsgContext() {
        return this.msgContext;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getImgUri() {
        return this.imgUri;
    }

    public String getImgBigUri() {
        return this.imgBigUri;
    }

    public String getFrom() {
        return this.from;
    }

    public String getDetailUri() {
        return this.detailUri;
    }

    public String getInfoClassify() {
        return this.infoClassify;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public String getPosition() {
        return this.position;
    }

    public String getMsgDevice() {
        return this.msgDevice;
    }

    public int getMsgPosition() {
        return this.msgPosition;
    }

    public int getInfoRecommend() {
        return this.infoRecommend;
    }

    public int getMsgMaterial() {
        return this.msgMaterial;
    }

    public String getHeatMapCity() {
        return this.heatMapCity;
    }

    public String getMsgUserLabel() {
        return this.msgUserLable;
    }

    public List<MessageExt> getMessageExtList() {
        return this.messageExtList;
    }

    public int getLayout() {
        return this.layout;
    }

    public int getPageType() {
        return this.pageType;
    }

    public int getIsShowToNotice() {
        return this.isShowToNotice;
    }

    public int getImageTextSeparateSwitch() {
        return this.imageTextSeparateSwitch;
    }

    public String getHarmonyImgUrl() {
        return this.harmonyImgUrl;
    }

    public String getHmImgSize() {
        return this.hmImgSize;
    }

    public String toString() {
        return "CloudMessageObject{msgId='" + this.msgId + "', msgType=" + this.msgType + ", expireTime='" + this.expireTime + "', msgTitle='" + this.msgTitle + "', msgContext='" + this.msgContext + "', flag=" + this.flag + ", from='" + this.from + "', readFlag=" + this.readFlag + ", createTime=" + this.createTime + ", position='" + this.position + "', msgDevice='" + this.msgDevice + "', msgPosition=" + this.msgPosition + ", msgMaterial=" + this.msgMaterial + ", infoClassify='" + this.infoClassify + "', heatMapCity='" + this.heatMapCity + "', infoRecommend=" + this.infoRecommend + ", msgUserLable='" + this.msgUserLable + "', layout=" + this.layout + ", messageExtList=" + this.messageExtList + ", pageType=" + this.pageType + ", isShowToNotice=" + this.isShowToNotice + ", imageTextSeparateSwitch=" + this.imageTextSeparateSwitch + ", hmImgSize=" + this.hmImgSize + '}';
    }
}
