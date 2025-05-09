package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.pluginmessagecenter.service.MessageObserverController;
import com.huawei.pluginmessagecenter.service.MessagePuller;
import com.huawei.pluginmessagecenter.util.PullMessageCallback;
import health.compact.a.DataBaseHelper;
import health.compact.a.DbManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes6.dex */
public class mrc extends HwBaseManager {
    private static final Byte[] b = new Byte[1];
    private static volatile mrc c;

    /* renamed from: a, reason: collision with root package name */
    private Context f15124a;
    private MessageObserverController d;

    private mrc(Context context) {
        super(context);
        LogUtil.a("UIDV_MessageDbProvider", "Enter MessageDbProvider");
        this.f15124a = context;
        this.d = new MessageObserverController();
        if (g()) {
            LogUtil.a("UIDV_MessageDbProvider", "MessageDB data is null!");
        }
        int b2 = DataBaseHelper.c(String.valueOf(getModuleId())).b();
        LogUtil.a("UIDV_MessageDbProvider", "newDbVersion =", 112, ", oldDbVersion=", Integer.valueOf(b2));
        if (b2 >= 100 && b2 <= 107) {
            i();
        } else {
            e();
        }
        if (!DbManager.b(String.valueOf(getModuleId()), getTableFullName("message"), CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH)) {
            LogUtil.a("UIDV_MessageDbProvider", "pageType is not exist");
            i();
        }
        LogUtil.a("UIDV_MessageDbProvider", "Leave MessageDbProvider");
    }

    public static mrc e(Context context) {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new mrc(context);
                }
            }
        }
        return c;
    }

    private boolean g() {
        LogUtil.a("UIDV_MessageDbProvider", "Enter isMessageDbNull");
        try {
            Cursor queryStorageData = queryStorageData("message", 1, null);
            boolean z = queryStorageData == null;
            coa_(queryStorageData);
            return z;
        } catch (Throwable th) {
            coa_(null);
            throw th;
        }
    }

    public String a(String str, String str2) {
        String str3;
        LogUtil.a("UIDV_MessageDbProvider", "requestMessageId module = ", str, " type = ", str2);
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        SQLiteDatabase c2 = DataBaseHelper.c(String.valueOf(getModuleId())).c();
        if (c2 == null) {
            LogUtil.a("UIDV_MessageDbProvider", "the messageCenter db is null.");
            return "";
        }
        try {
            c2.beginTransaction();
            long insertStorageData = insertStorageData("message", 1, mrg.cok_(huidOrDefault, str, str2));
            if (insertStorageData == -1) {
                LogUtil.a("UIDV_MessageDbProvider", "insert wrong");
                try {
                    if (c2.inTransaction()) {
                        c2.endTransaction();
                    }
                } catch (SQLiteFullException e) {
                    ReleaseLogUtil.c("UIDV_MessageDbProvider", "requestMessageId, SQLiteFullException: ", e.getMessage());
                }
                return "";
            }
            String str4 = "l" + insertStorageData;
            if (str2.equals(MessageConstant.GROUP_MEDAL_TYPE)) {
                StringBuffer stringBuffer = new StringBuffer(16);
                stringBuffer.append("id =? ");
                LogUtil.a("UIDV_MessageDbProvider", "deleteStorageData result = ", Integer.valueOf(deleteStorageData("message", 1, stringBuffer.toString(), new String[]{String.valueOf(insertStorageData)})));
                str3 = str4;
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("msgId", str4);
                StringBuffer stringBuffer2 = new StringBuffer(16);
                stringBuffer2.append("id =? ");
                str3 = str4;
                LogUtil.a("UIDV_MessageDbProvider", "requestMessageId result = ", Integer.valueOf(updateStorageData("message", 1, contentValues, stringBuffer2.toString(), new String[]{String.valueOf(insertStorageData)})));
            }
            c2.setTransactionSuccessful();
            try {
                if (c2.inTransaction()) {
                    c2.endTransaction();
                }
            } catch (SQLiteFullException e2) {
                ReleaseLogUtil.c("UIDV_MessageDbProvider", "requestMessageId, SQLiteFullException: ", e2.getMessage());
            }
            LogUtil.c("UIDV_MessageDbProvider", "requestMessageId module = ", str, " type = ", str2);
            return str3;
        } catch (Throwable th) {
            try {
                if (c2.inTransaction()) {
                    c2.endTransaction();
                    throw th;
                }
                throw th;
            } catch (SQLiteFullException e3) {
                ReleaseLogUtil.c("UIDV_MessageDbProvider", "requestMessageId, SQLiteFullException: ", e3.getMessage());
                throw th;
            }
        }
    }

    private void e() {
        LogUtil.a("UIDV_MessageDbProvider", "createTable | create new table: ", getTableFullName("message"));
        LogUtil.c("UIDV_MessageDbProvider", "createTable | create new table sql = ", "id integer primary key autoincrement,msgId text not null,module text,type text,metadata text,msgType integer not null check(msgType > 0),flag integer not null check(flag > -1),weight integer not null check(weight > -1),readFlag integer not null,msgTitle text not null,msgContext text,createTime integer not null,receiveTime integer not null,expireTime integer not null,imgURI text,imgBigUri text,detailUri text not null,detailUriExt text,msgFrom text,position integer not null check(position > 0),msgPosition integer not null,huid text,imei text,notified integer not null,infoClassify text,heatMapCity text,infoRecommend integer,msgUserLable text,layout integer,messageExtList text,pageType integer,imageTextSeparateSwitch integer,harmonyImageUrl text,harmonyImageSize text,reserveParamOne textreserveParamTwo textreserveParamThree text");
        createStorageDataTable("message", 1, "id integer primary key autoincrement,msgId text not null,module text,type text,metadata text,msgType integer not null check(msgType > 0),flag integer not null check(flag > -1),weight integer not null check(weight > -1),readFlag integer not null,msgTitle text not null,msgContext text,createTime integer not null,receiveTime integer not null,expireTime integer not null,imgURI text,imgBigUri text,detailUri text not null,detailUriExt text,msgFrom text,position integer not null check(position > 0),msgPosition integer not null,huid text,imei text,notified integer not null,infoClassify text,heatMapCity text,infoRecommend integer,msgUserLable text,layout integer,messageExtList text,pageType integer,imageTextSeparateSwitch integer,harmonyImageUrl text,harmonyImageSize text,reserveParamOne textreserveParamTwo textreserveParamThree text");
        LogUtil.a("UIDV_MessageDbProvider", "createTable | create table end");
    }

    private void i() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.clear();
        LogUtil.a("UIDV_MessageDbProvider", "Enter upgradeMessageCenterDb");
        Cursor cursor = null;
        try {
            cursor = queryStorageData("message", 1, null);
            coc_(arrayList, cursor);
            coa_(cursor);
            d();
            DbManager.d(this.f15124a, String.valueOf(getModuleId()), "message", 1);
            e();
            if (!arrayList.isEmpty()) {
                b(arrayList);
            }
            h();
            LogUtil.c("UIDV_MessageDbProvider", "Leave upgradeMessageCenterDb");
        } catch (Throwable th) {
            coa_(cursor);
            throw th;
        }
    }

    private void coc_(List<MessageObject> list, Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            return;
        }
        do {
            MessageObject cog_ = mrg.cog_(cursor);
            if (!mrg.a(cog_)) {
                list.add(cog_);
            }
        } while (cursor.moveToNext());
    }

    private void d(int i, MessageChangeEvent messageChangeEvent) {
        MessageObserverController messageObserverController = this.d;
        if (messageObserverController != null) {
            messageObserverController.notifyAllObservers(i, messageChangeEvent);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00fe A[Catch: all -> 0x010d, TRY_LEAVE, TryCatch #2 {all -> 0x010d, blocks: (B:19:0x005c, B:21:0x006b, B:25:0x0094, B:28:0x00fe, B:35:0x009e, B:36:0x00a8, B:40:0x00e8, B:41:0x00f3), top: B:18:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e(com.huawei.health.messagecenter.model.MessageObject r18) {
        /*
            Method dump skipped, instructions count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mrc.e(com.huawei.health.messagecenter.model.MessageObject):boolean");
    }

    private void b(int i, MessageObject messageObject) {
        if (i < 14) {
            messageObject.setModule(String.valueOf(14));
        }
        if (i == 30 || i == 31 || i == 32) {
            messageObject.setModule(String.valueOf(30));
            messageObject.setMsgPosition(11);
        }
        if (i >= 35 && i <= 39) {
            messageObject.setModule(String.valueOf(30));
            messageObject.setDetailUriExt(String.valueOf(35));
            messageObject.setMsgPosition(11);
        }
        if (i == 40 || i == 50) {
            messageObject.setMsgPosition(11);
        }
        e(i, messageObject);
        if (messageObject.getMsgPosition() != 11 || MessageConstant.NPS_MESSAGE_TYPE.equals(messageObject.getType()) || MessageConstant.ACTIVE_TARGET_TYPE.equals(messageObject.getType())) {
            return;
        }
        messageObject.setExpireTime(0L);
    }

    private void e(int i, MessageObject messageObject) {
        if (i == 60) {
            messageObject.setModule(String.valueOf(51));
            messageObject.setDetailUriExt(String.valueOf(60));
            messageObject.setMsgPosition(11);
        }
        if (i == 61) {
            messageObject.setModule(String.valueOf(51));
            messageObject.setDetailUriExt(String.valueOf(61));
            messageObject.setMsgPosition(11);
        }
        if (i >= 62 && i <= 64) {
            messageObject.setModule(String.valueOf(51));
            messageObject.setDetailUriExt(String.valueOf(62));
            messageObject.setMsgPosition(11);
        }
        if (i < 53 || i > 54) {
            return;
        }
        messageObject.setModule(String.valueOf(51));
        messageObject.setDetailUriExt(String.valueOf(i));
        messageObject.setMsgPosition(11);
    }

    /* JADX WARN: Finally extract failed */
    public boolean b(List<MessageObject> list) {
        int a2;
        boolean z;
        LogUtil.a("UIDV_MessageDbProvider", "Enter insertBatch ");
        if (list == null) {
            LogUtil.a("UIDV_MessageDbProvider", "insertBatch | MessageObject List is null.");
            return false;
        }
        ArrayList<String> arrayList = new ArrayList<>(16);
        SQLiteDatabase c2 = DataBaseHelper.c(String.valueOf(getModuleId())).c();
        boolean z2 = true;
        if (c2 != null) {
            try {
                c2.beginTransaction();
                a2 = a(list, 0, arrayList);
                c2.setTransactionSuccessful();
                try {
                    c2.endTransaction();
                } catch (SQLiteFullException e) {
                    ReleaseLogUtil.c("UIDV_MessageDbProvider", "insertMessages, SQLiteFullException: ", e.getMessage());
                    z = false;
                }
            } catch (Throwable th) {
                try {
                    c2.endTransaction();
                } catch (SQLiteFullException e2) {
                    ReleaseLogUtil.c("UIDV_MessageDbProvider", "insertMessages, SQLiteFullException: ", e2.getMessage());
                }
                throw th;
            }
        } else {
            a2 = 0;
        }
        z = true;
        if (!z) {
            z2 = z;
        } else if (a2 != list.size()) {
            z2 = false;
        }
        LogUtil.a("UIDV_MessageDbProvider", "insertBatch count = ", Integer.valueOf(a2), ", isAllInserted = ", Boolean.valueOf(z2));
        if (z2) {
            d(0, new MessageChangeEvent(arrayList, null));
        } else {
            d(-1, new MessageChangeEvent(arrayList, null));
        }
        LogUtil.a("UIDV_MessageDbProvider", "Leave insertBatch ");
        return z2;
    }

    private int a(List<MessageObject> list, int i, ArrayList<String> arrayList) {
        for (MessageObject messageObject : list) {
            if (!mrg.c(messageObject)) {
                LogUtil.a("UIDV_MessageDbProvider", "column check failed.");
            } else if (e(messageObject)) {
                i++;
                arrayList.add(messageObject.getMsgId());
            }
        }
        return i;
    }

    private void h() {
        LogUtil.a("UIDV_MessageDbProvider", "Enter insertBannerAndInfoMessage");
        long j = j();
        if (j <= 0) {
            j = f();
        }
        MessagePuller.getInstance().pullMessage(this.f15124a, j, new PullMessageCallback() { // from class: mrd
            @Override // com.huawei.pluginmessagecenter.util.PullMessageCallback
            public final void pullMessageResult(int i, List list, List list2) {
                mrc.this.e(i, list, list2);
            }
        });
    }

    /* synthetic */ void e(int i, List list, List list2) {
        LogUtil.c("UIDV_MessageDbProvider", "pullMessageResult: ", list, "result: ", Integer.valueOf(i));
        if (i != 200) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MessageObject messageObject = (MessageObject) it.next();
            if (messageObject != null && mrg.a(messageObject)) {
                arrayList.add(messageObject);
            }
        }
        LogUtil.a("UIDV_MessageDbProvider", "Leave insertBannerAndInfoMessage isSuccess :", Boolean.valueOf(b(arrayList)));
    }

    public int a(String str) {
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (TextUtils.isEmpty(huidOrDefault)) {
            LogUtil.a("UIDV_MessageDbProvider", "deleteMessageById | delete message failed huid is null!");
            return -1;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("msgId =? and huid =? ");
        int deleteStorageData = deleteStorageData("message", 1, stringBuffer.toString(), new String[]{mrg.d((Object) str), mrg.d((Object) huidOrDefault)});
        if (deleteStorageData == 0) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(str);
            d(0, new MessageChangeEvent(null, arrayList));
        }
        LogUtil.a("UIDV_MessageDbProvider", "deleteMessageById result = ", Integer.valueOf(deleteStorageData));
        return deleteStorageData;
    }

    public boolean d() {
        int deleteStorageData = deleteStorageData("message", 1, null);
        LogUtil.a("UIDV_MessageDbProvider", "deleteAllMessages | delete messages; resultCode = ", Integer.valueOf(deleteStorageData));
        return deleteStorageData == 0;
    }

    public void c() {
        LogUtil.a("UIDV_MessageDbProvider", "Enter doRefresh !");
        final long j = j();
        if (j < 0) {
            d(-1, new MessageChangeEvent(new ArrayList(16), new ArrayList(16)));
            LogUtil.a("UIDV_MessageDbProvider", "doRefresh because maxTime < 0 return!");
        } else {
            MessagePuller.getInstance().pullMessage(this.f15124a, j, new PullMessageCallback() { // from class: mqz
                @Override // com.huawei.pluginmessagecenter.util.PullMessageCallback
                public final void pullMessageResult(int i, List list, List list2) {
                    mrc.this.a(j, i, list, list2);
                }
            });
            LogUtil.a("UIDV_MessageDbProvider", "Leave doRefresh !");
        }
    }

    /* synthetic */ void a(long j, int i, List list, List list2) {
        LogUtil.c("UIDV_MessageDbProvider", "pullMessageResult:", list, "result: ", Integer.valueOf(i));
        if (i == 200) {
            LogUtil.a("UIDV_MessageDbProvider", "pullMessageResult: insertBatch(list): ", Boolean.valueOf(b((List<MessageObject>) list)));
            if (j != 0) {
                d((List<String>) list2);
                return;
            }
            return;
        }
        d(i, new MessageChangeEvent(new ArrayList(16), new ArrayList(16)));
    }

    private void d(List<String> list) {
        LogUtil.a("UIDV_MessageDbProvider", "revokeList=", list);
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String str = "G" + it.next();
                if (c(str) != null) {
                    o(str);
                }
            }
        }
    }

    private void o(String str) {
        synchronized (b) {
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append("msgId =? ");
            int deleteStorageData = deleteStorageData("message", 1, stringBuffer.toString(), new String[]{mrg.d((Object) str)});
            LogUtil.a("UIDV_MessageDbProvider", "revokeMessage id=", str, " result=", Integer.valueOf(deleteStorageData));
            if (deleteStorageData == 0) {
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(str);
                d(0, new MessageChangeEvent(null, arrayList));
            }
        }
    }

    public void b(MessageObserver messageObserver) {
        if (messageObserver == null) {
            LogUtil.a("UIDV_MessageDbProvider", "Observer object maybe not create.");
        } else {
            this.d.addObservers(messageObserver);
        }
    }

    public void c(MessageObserver messageObserver) {
        if (messageObserver == null) {
            LogUtil.a("UIDV_MessageDbProvider", "Observer object maybe not create.");
        } else {
            this.d.deleteObservers(messageObserver);
        }
    }

    public boolean d(String str) {
        LogUtil.a("UIDV_MessageDbProvider", "Enter onShowSmartCard msgId = ", str);
        if (str == null) {
            LogUtil.a("UIDV_MessageDbProvider", "onShowSmartCard(String msgId):msgId is null");
            return false;
        }
        if (c(str) == null) {
            LogUtil.a("UIDV_MessageDbProvider", "onShowSmartCard(String msgId):messageObject is null");
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(CommonUtil.READ_FLAG, (Integer) 2);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("msgId =? ");
        int updateStorageData = updateStorageData("message", 1, contentValues, stringBuffer.toString(), new String[]{mrg.d((Object) str)});
        return updateStorageData > 0 && updateStorageData != 201000;
    }

    public boolean b(String str) {
        LogUtil.a("UIDV_MessageDbProvider", "Enter onRead msgId = ", str);
        if (str == null) {
            LogUtil.a("UIDV_MessageDbProvider", "onRead(String msgId):msgId is null");
            return false;
        }
        if (!str.matches("[a-zA-Z0-9]+")) {
            LogUtil.h("UIDV_MessageDbProvider", "msgId is wrong");
            return false;
        }
        MessageObject c2 = c(str);
        if (c2 == null) {
            LogUtil.a("UIDV_MessageDbProvider", "onRead(String msgId):messageObject is null");
            return false;
        }
        int msgType = c2.getMsgType();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CommonUtil.READ_FLAG, (Integer) 1);
        if (c2.getExpireTime() != 0) {
            if (msgType == 3) {
                contentValues.put("expireTime", Long.valueOf(mrk.b(mrk.d("23:59:59"))));
            } else if (msgType != 2) {
                LogUtil.a("UIDV_MessageDbProvider", "onRead(String msgId): msgType = ", Integer.valueOf(msgType));
            } else {
                contentValues.put("expireTime", (Integer) (-1));
            }
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("msgId =? ");
        int updateStorageData = updateStorageData("message", 1, contentValues, stringBuffer.toString(), new String[]{mrg.d((Object) str)});
        if (updateStorageData <= 0 || updateStorageData == 201000) {
            return false;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(str);
        d(0, new MessageChangeEvent(arrayList, null));
        LogUtil.a("UIDV_MessageDbProvider", "Leave onRead msgId = ", str);
        return true;
    }

    public void a() {
        LogUtil.a("UIDV_MessageDbProvider", "release()");
        MessageObserverController messageObserverController = this.d;
        if (messageObserverController != null) {
            messageObserverController.clear();
        }
    }

    private long f() {
        Cursor cursor;
        long j;
        LogUtil.a("UIDV_MessageDbProvider", "Enter getMaxTime");
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (TextUtils.isEmpty(huidOrDefault)) {
            LogUtil.b("UIDV_MessageDbProvider", "getMaxTime | huid is invalid");
            return -1L;
        }
        String str = "SELECT MAX(createTime) FROM " + getTableFullName("message") + " WHERE (huid =? or huid = '' )  and msgId like 'S%' or msgId like 'G%'";
        LogUtil.c("UIDV_MessageDbProvider", "getMaxTime | sql = ", str);
        try {
            cursor = rawQueryStorageData(1, str, new String[]{mrg.d((Object) huidOrDefault)});
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        j = cursor.getLong(0);
                        coa_(cursor);
                        LogUtil.a("UIDV_MessageDbProvider", "Leave getMaxTime | getMaxTime = ", Long.valueOf(j));
                        return j;
                    }
                } catch (Throwable th) {
                    th = th;
                    coa_(cursor);
                    throw th;
                }
            }
            j = 0;
            coa_(cursor);
            LogUtil.a("UIDV_MessageDbProvider", "Leave getMaxTime | getMaxTime = ", Long.valueOf(j));
            return j;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    private long j() {
        Cursor cursor;
        long j;
        LogUtil.a("UIDV_MessageDbProvider", "Enter getLatestGetMsgTimestamp");
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (TextUtils.isEmpty(huidOrDefault)) {
            LogUtil.b("UIDV_MessageDbProvider", "getMaxTime | huid is invalid");
            return -1L;
        }
        String str = "SELECT MAX(reserveParamOne) FROM " + getTableFullName("message") + " WHERE (huid =? or huid = '' )  and msgId like 'S%' or msgId like 'G%'";
        LogUtil.c("UIDV_MessageDbProvider", "getLatestGetMsgTimestamp | sql = ", str);
        try {
            cursor = rawQueryStorageData(1, str, new String[]{mrg.d((Object) huidOrDefault)});
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        j = cursor.getLong(0);
                        coa_(cursor);
                        LogUtil.a("UIDV_MessageDbProvider", "Leave getLatestGetMsgTimestamp | getLatestGetMsgTimestamp = ", Long.valueOf(j));
                        return j;
                    }
                } catch (Throwable th) {
                    th = th;
                    coa_(cursor);
                    throw th;
                }
            }
            j = 0;
            coa_(cursor);
            LogUtil.a("UIDV_MessageDbProvider", "Leave getLatestGetMsgTimestamp | getLatestGetMsgTimestamp = ", Long.valueOf(j));
            return j;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    private List<MessageObject> d(String str, String str2, String str3) {
        LogUtil.a("UIDV_MessageDbProvider", "Enter queryAll");
        ArrayList arrayList = new ArrayList(16);
        String[] strArr = {mrg.d((Object) str), mrg.d((Object) str2)};
        String[] strArr2 = {mrg.d((Object) str), mrg.d((Object) str2), mrg.d((Object) (str2 + "_" + str3))};
        String n = n(str3);
        if (!TextUtils.isEmpty(str3)) {
            strArr = strArr2;
        }
        Cursor rawQueryStorageData = rawQueryStorageData(1, n, strArr);
        if (rawQueryStorageData == null) {
            LogUtil.h("UIDV_MessageDbProvider", "queryAll cursor == null ");
            return arrayList;
        }
        try {
            try {
                coe_(arrayList, rawQueryStorageData, !"".equals(str));
            } catch (SQLException e) {
                LogUtil.b("UIDV_MessageDbProvider", "queryAll exception : ", ExceptionUtils.d(e));
            }
            coa_(rawQueryStorageData);
            LogUtil.a("UIDV_MessageDbProvider", "Leave queryAll messageObjects.size = ", Integer.valueOf(arrayList.size()));
            return arrayList;
        } catch (Throwable th) {
            coa_(rawQueryStorageData);
            throw th;
        }
    }

    private void coe_(List<MessageObject> list, Cursor cursor, boolean z) {
        while (cursor.moveToNext()) {
            MessageObject cof_ = mrg.cof_(cursor);
            if (cof_ != null) {
                String normalize = Normalizer.normalize(cof_.getMsgId(), Normalizer.Form.NFKC);
                if (!Utils.i() && normalize != null && (normalize.startsWith(ExifInterface.LATITUDE_SOUTH) || normalize.startsWith("G"))) {
                    LogUtil.c("UIDV_MessageDbProvider", "queryAll | messageObject contains S G");
                } else if (!mrg.d(cof_)) {
                    String type = cof_.getType();
                    if (!MessageConstant.ACTIVE_TARGET_TYPE.equals(type) || mrg.b(cof_)) {
                        if (!z && CommonUtil.CLOUD_SERVICE_MESSAGE_VALUE.equals(type)) {
                            LogUtil.a("UIDV_MessageDbProvider", "queryAll | messageObject type = ", CommonUtil.CLOUD_SERVICE_MESSAGE_VALUE);
                        } else {
                            list.add(cof_);
                        }
                    }
                }
            }
        }
    }

    private String n(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("select * from ");
        sb.append(getTableFullName("message"));
        sb.append(" where ( huid =? or huid = '') and (imei =? or ");
        if (!TextUtils.isEmpty(str)) {
            sb.append("imei =? or ");
        }
        sb.append("imei is null or imei = '') order by receiveTime");
        return sb.toString();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public final Integer getModuleId() {
        return 20005;
    }

    public void h(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("UIDV_MessageDbProvider", "setNotify | msgId = ", str);
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(CommonUtil.MSG_NOTIFY, (Integer) 1);
        LogUtil.c("UIDV_MessageDbProvider", "setNotify | whereSql = ", "msgId = '" + str + "'");
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("msgId =? ");
        LogUtil.a("UIDV_MessageDbProvider", "setNotify | updateStorageData = ", Integer.valueOf(updateStorageData("message", 1, contentValues, stringBuffer.toString(), new String[]{mrg.d((Object) str)})));
    }

    public MessageObject c(String str) {
        Cursor cursor = null;
        r2 = null;
        MessageObject cof_ = null;
        try {
            Cursor rawQueryStorageData = rawQueryStorageData(1, "select * from " + getTableFullName("message") + " where msgId =? ", new String[]{mrg.d((Object) str)});
            if (rawQueryStorageData != null) {
                try {
                    if (rawQueryStorageData.moveToFirst()) {
                        cof_ = mrg.cof_(rawQueryStorageData);
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = rawQueryStorageData;
                    coa_(cursor);
                    throw th;
                }
            }
            coa_(rawQueryStorageData);
            return cof_;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public List<MessageObject> b(String str, String str2) {
        ArrayList arrayList = new ArrayList(16);
        Cursor cursor = null;
        try {
            try {
                cursor = rawQueryStorageData(1, "select * from " + getTableFullName("message") + " where module =? and type =? ", new String[]{mrg.d((Object) str), mrg.d((Object) str2)});
                cod_(arrayList, cursor);
            } catch (SQLiteException e) {
                ReleaseLogUtil.c("UIDV_MessageDbProvider", "SQLiteDatabaseCorruptException,message:", e.getMessage());
            }
            return arrayList;
        } finally {
            coa_(cursor);
        }
    }

    private void cod_(List<MessageObject> list, Cursor cursor) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MessageObject cof_ = mrg.cof_(cursor);
                if (cof_ != null) {
                    list.add(cof_);
                }
            }
        }
    }

    private void coa_(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    public List<MessageObject> e(String str, String str2, String str3, int i, int i2) {
        LogUtil.a("UIDV_MessageDbProvider", "Enter getMessageList");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("UIDV_MessageDbProvider", "getMessageList | huid is null");
            return new ArrayList();
        }
        if (str2 == null) {
            str2 = "";
        }
        if (i == 0 && i2 == 0) {
            return d(str, str2, str3);
        }
        int i3 = (i - 1) * i2;
        String[] strArr = {mrg.d((Object) str), mrg.d((Object) str2), mrg.d(Integer.valueOf(i3)), mrg.d(Integer.valueOf(i2))};
        String[] strArr2 = {mrg.d((Object) str), mrg.d((Object) str2), mrg.d((Object) (str2 + "_" + str3)), mrg.d(Integer.valueOf(i3)), mrg.d(Integer.valueOf(i2))};
        String l = l(str3);
        ArrayList arrayList = new ArrayList(16);
        Cursor cursor = null;
        try {
            try {
                if (!TextUtils.isEmpty(str3)) {
                    strArr = strArr2;
                }
                cursor = rawQueryStorageData(1, l, strArr);
                cob_(cursor, arrayList);
            } catch (SQLiteException e) {
                ReleaseLogUtil.c("UIDV_MessageDbProvider", "SQLiteDatabaseCorruptException,message:", e.getMessage());
            }
            return arrayList;
        } finally {
            coa_(cursor);
        }
    }

    private void cob_(Cursor cursor, List<MessageObject> list) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MessageObject cof_ = mrg.cof_(cursor);
                if (cof_ != null) {
                    list.add(cof_);
                }
            }
        }
    }

    private String l(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append("select * from ");
        sb.append(getTableFullName("message"));
        sb.append(" where ( huid =? or huid = '') and (imei =? or ");
        if (!TextUtils.isEmpty(str)) {
            sb.append("imei =? or ");
        }
        sb.append("imei is null or imei = '') order by weight desc, receiveTime desc limit ?,? ");
        return sb.toString();
    }

    public void e(String str) {
        if (str == null) {
            return;
        }
        LogUtil.a("UIDV_MessageDbProvider", "save uid result = ", Integer.valueOf(setSharedPreference(CommonUtil.KEY_LAST_HUID, str, new StorageParams(1))));
    }

    public void j(String str) {
        if (str == null) {
            return;
        }
        LogUtil.a("UIDV_MessageDbProvider", "save language result = ", Integer.valueOf(setSharedPreference(CommonUtil.KEY_LAST_LANGUAGE, str, new StorageParams(1))));
    }

    public void g(String str) {
        if (str == null) {
            return;
        }
        LogUtil.a("UIDV_MessageDbProvider", "save push pushToken result = ", Integer.valueOf(setSharedPreference(CommonUtil.PUSH_TOKEN, str, new StorageParams(1))));
    }

    public void i(String str) {
        if (str == null) {
            return;
        }
        LogUtil.a("UIDV_MessageDbProvider", "save push uid result = ", Integer.valueOf(setSharedPreference(CommonUtil.KEY_PUSH_HUID, str, new StorageParams(1))));
    }

    public void f(String str) {
        LogUtil.a("UIDV_MessageDbProvider", "save device result = ", Integer.valueOf(setSharedPreference(CommonUtil.KEY_CURRENT_DEVICE_ID, str, new StorageParams(1))));
    }

    public String b() {
        return getSharedPreference(CommonUtil.KEY_LAST_LANGUAGE);
    }
}
