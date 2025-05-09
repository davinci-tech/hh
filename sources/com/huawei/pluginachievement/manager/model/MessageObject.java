package com.huawei.pluginachievement.manager.model;

import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import java.util.Date;

/* loaded from: classes6.dex */
public class MessageObject {
    private static final long DEFAULT_EXPIRE_TIME = 31536000000L;
    public static final int FLAG_NO = 0;
    public static final int MSGTYPE_AFTER_SEE_HIDE = 2;
    public static final int MSGTYPE_EXPIRY_DATE_SHOW = 1;
    public static final int NOTIFIED_NO = 0;
    public static final int POSITION_PULL = 1;
    public static final int POSITION_PUSH = 2;
    public static final int READFLAG_NO = 0;
    public static final int WEIGHT_NORMAL = 0;
    private int readFlag;
    private ResourceBriefInfo resBriefInfo;
    private String msgId = "";
    private String module = "";
    private String type = "";
    private String metaData = "";
    private int msgType = 1;
    private int flag = 0;
    private int weight = 0;
    private String msgTitle = "";
    private String msgContent = "";
    private long createTime = 0;
    private long expireTime = new Date().getTime() + DEFAULT_EXPIRE_TIME;
    private String imgUri = "";
    private String detailUri = "";
    private String detailUriExt = "";
    private String msgFrom = "";
    private int position = 1;
    private String huid = "";
    private String imei = "";
    private int notified = 0;
    private int msgPosition = 11;

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String str) {
        this.module = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getMetaData() {
        return this.metaData;
    }

    public void setMetaData(String str) {
        this.metaData = str;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int i) {
        this.flag = i;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public int getReadFlag() {
        return this.readFlag;
    }

    public void setReadFlag(int i) {
        this.readFlag = i;
    }

    public void setMsgTitle(String str) {
        this.msgTitle = str;
    }

    public String getMsgTitle() {
        return this.msgTitle;
    }

    public void setMsgContent(String str) {
        this.msgContent = str;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public String getImgUri() {
        return this.imgUri;
    }

    public void setImgUri(String str) {
        this.imgUri = str;
    }

    public String getDetailUri() {
        return this.detailUri;
    }

    public void setDetailUri(String str) {
        this.detailUri = str;
    }

    public String getDetailUriExt() {
        return this.detailUriExt;
    }

    public String getMsgFrom() {
        return this.msgFrom;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setMsgPosition(int i) {
        this.msgPosition = i;
    }

    public String getHuid() {
        return this.huid;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public String getImei() {
        return this.imei;
    }

    public int getNotified() {
        return this.notified;
    }

    public void setNotified(int i) {
        this.notified = i;
    }

    public ResourceBriefInfo getResBriefInfo() {
        return this.resBriefInfo;
    }

    public void setResBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        this.resBriefInfo = resourceBriefInfo;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("MessageObject{createTime=");
        stringBuffer.append(this.createTime);
        stringBuffer.append(", msgId='").append(this.msgId).append("', module='");
        stringBuffer.append(this.module).append("', type='");
        stringBuffer.append(this.type).append("', metaData='");
        stringBuffer.append(this.metaData).append("', msgType=");
        stringBuffer.append(this.msgType);
        stringBuffer.append(", flag=").append(this.flag);
        stringBuffer.append(", weight=").append(this.weight);
        stringBuffer.append(", readFlag=").append(this.readFlag);
        stringBuffer.append(", msgTitle='").append(this.msgTitle).append("', msgContent='");
        stringBuffer.append(this.msgContent).append("', expireTime=");
        stringBuffer.append(this.expireTime);
        stringBuffer.append(", imgUri='").append(this.imgUri).append("', detailUri='");
        stringBuffer.append(this.detailUri).append("', detailUriExt='");
        stringBuffer.append(this.detailUriExt).append("', msgFrom='");
        stringBuffer.append(this.msgFrom).append("', position=");
        stringBuffer.append(this.position);
        stringBuffer.append(", huid='").append(this.huid).append("', imei='");
        stringBuffer.append(this.imei).append("', notified=");
        stringBuffer.append(this.notified);
        stringBuffer.append(", msgPosition=").append(this.msgPosition);
        stringBuffer.append(", resBriefInfo=").append(this.resBriefInfo);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
