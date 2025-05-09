package com.huawei.agconnect.apms.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.JsonArray;
import com.huawei.agconnect.apms.collect.type.CollectableArray;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.agconnect.apms.m0;
import com.huawei.agconnect.apms.nop;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class Session extends CollectableArray implements Parcelable {
    private boolean sampled;
    private String sessionId;
    private long sessionTime;
    private static final AgentLog LOG = AgentLogManager.getAgentLog();
    public static final Parcelable.Creator<Session> CREATOR = new abc();

    public /* synthetic */ Session(Parcel parcel, abc abcVar) {
        this(parcel);
    }

    public static Session getInstance() {
        String replaceAll = UUID.randomUUID().toString().replaceAll(Constants.LINK, "");
        Session session = new Session(replaceAll);
        AgentLog agentLog = LOG;
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[2];
        objArr[0] = session.sampled ? "sampled" : "Non sampled";
        objArr[1] = replaceAll;
        agentLog.debug(String.format(locale, "Creating a new %s Session: %s", objArr));
        return session;
    }

    @Override // com.huawei.agconnect.apms.collect.type.CollectableArray, com.huawei.agconnect.apms.collect.type.BaseCollectable, com.huawei.agconnect.apms.collect.type.Collectable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(m0.abc(this.sessionId));
        jsonArray.add(m0.abc(Boolean.valueOf(this.sampled)));
        return jsonArray;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isExpired() {
        return TimeUnit.MINUTES.toMinutes(System.currentTimeMillis() - this.sessionTime) > nop.abc().fgh;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.sessionId);
        parcel.writeLong(this.sessionTime);
    }

    private Session(String str) {
        this.sessionId = str;
        this.sessionTime = System.currentTimeMillis();
        this.sampled = false;
    }

    private Session(Parcel parcel) {
        this.sessionId = parcel.readString();
        this.sessionTime = parcel.readLong();
    }

    public class abc implements Parcelable.Creator<Session> {
        @Override // android.os.Parcelable.Creator
        public Session createFromParcel(Parcel parcel) {
            return new Session(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public Session[] newArray(int i) {
            return new Session[0];
        }
    }
}
