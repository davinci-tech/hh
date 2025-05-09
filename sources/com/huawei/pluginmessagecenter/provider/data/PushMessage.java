package com.huawei.pluginmessagecenter.provider.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PushMessage implements Parcelable {
    public static final Parcelable.Creator<PushMessage> CREATOR = new Parcelable.Creator<PushMessage>() { // from class: com.huawei.pluginmessagecenter.provider.data.PushMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PushMessage createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            PushMessage pushMessage = new PushMessage();
            pushMessage.setSessionId(parcel.readString());
            pushMessage.setExpireTime(parcel.readString());
            pushMessage.setMsgTitle(parcel.readString());
            pushMessage.setMsgContext(parcel.readString());
            pushMessage.setWebUrl(parcel.readString());
            pushMessage.setDetailID(parcel.readString());
            pushMessage.setIcon(parcel.readString());
            pushMessage.setFrom(parcel.readString());
            return pushMessage;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PushMessage[] newArray(int i) {
            return new PushMessage[i];
        }
    };
    private static final String TAG = "PushMessage";
    private String mSessionId = null;
    private String mExpireTime = null;
    private String mMsgTitle = null;
    private String mMsgContext = null;
    private String mWebUrl = null;
    private String mDetailID = null;
    private String mIcon = null;
    private String mFrom = "UOS";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setSessionId(String str) {
        this.mSessionId = str;
    }

    public void setExpireTime(String str) {
        this.mExpireTime = str;
    }

    public void setMsgTitle(String str) {
        this.mMsgTitle = str;
    }

    public void setMsgContext(String str) {
        this.mMsgContext = str;
    }

    public void setWebUrl(String str) {
        this.mWebUrl = str;
    }

    public void setDetailID(String str) {
        this.mDetailID = str;
    }

    public void setIcon(String str) {
        this.mIcon = str;
    }

    public void setFrom(String str) {
        this.mFrom = str;
    }

    public boolean fillMessage(JSONObject jSONObject) {
        try {
            this.mSessionId = jSONObject.getString("sessionId");
            this.mExpireTime = jSONObject.getString("expireTime");
            this.mMsgTitle = jSONObject.getString(CommonUtil.MSG_TITLE);
            this.mMsgContext = jSONObject.getString(CommonUtil.MSG_CONTENT);
            this.mWebUrl = jSONObject.getString("webUrl");
            this.mDetailID = jSONObject.getString("detailID");
            this.mIcon = jSONObject.getString("icon");
            this.mFrom = jSONObject.getString("from");
        } catch (JSONException e) {
            LogUtil.b(TAG, e.getMessage());
        }
        if (this.mSessionId != null && this.mExpireTime != null && this.mMsgTitle != null && this.mMsgContext != null && this.mIcon != null && this.mFrom != null && this.mWebUrl != null && this.mDetailID != null) {
            return true;
        }
        LogUtil.c(TAG, "mWebUrl = ", this.mWebUrl);
        LogUtil.c(TAG, "mDetailID = ", this.mDetailID);
        return false;
    }

    public String toString() {
        return "PushMessage{mSessionId='" + this.mSessionId + "',mExpireTime='" + this.mExpireTime + "',mMsgTitle='" + this.mMsgTitle + "',mMsgContext='" + this.mMsgContext + "',mWebUrl='" + this.mWebUrl + "',mDetailID='" + this.mDetailID + "',mIcon='" + this.mIcon + "',mFrom='" + this.mFrom + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSessionId);
        parcel.writeString(this.mExpireTime);
        parcel.writeString(this.mMsgTitle);
        parcel.writeString(this.mMsgContext);
        parcel.writeString(this.mWebUrl);
        parcel.writeString(this.mDetailID);
        parcel.writeString(this.mIcon);
        parcel.writeString(this.mFrom);
    }

    public void readFromParcel(Parcel parcel) {
        this.mSessionId = parcel.readString();
        this.mExpireTime = parcel.readString();
        this.mMsgTitle = parcel.readString();
        this.mMsgContext = parcel.readString();
        this.mWebUrl = parcel.readString();
        this.mDetailID = parcel.readString();
        this.mIcon = parcel.readString();
        this.mFrom = parcel.readString();
    }
}
