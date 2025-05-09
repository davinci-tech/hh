package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealthservice.db.table.DBCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class igy extends DBCommon {
    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int delete(String str, String[] strArr) {
        return super.delete(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ void execSQL(String str, Object[] objArr) {
        super.execSQL(str, objArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ long insert(ContentValues contentValues) {
        return super.insert(contentValues);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor query(String str, String[] strArr, String str2, String str3, String str4) {
        return super.query(str, strArr, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor queryEX(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        return super.queryEX(strArr, str, strArr2, str2, str3, str4);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ Cursor rawQuery(String str, String[] strArr) {
        return super.rawQuery(str, strArr);
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public /* bridge */ /* synthetic */ int update(ContentValues contentValues, String str, String[] strArr) {
        return super.update(contentValues, str, strArr);
    }

    private igy() {
    }

    public static igy d() {
        return b.b;
    }

    static class b {
        private static final igy b = new igy();
    }

    public static String e() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("create table  IF NOT EXISTS hihealth_device(_id integer primary key not null,device_unique_code text not null,deviceName text,device_type integer,firmwareVersion text,hardwareVersion text,softwareVersion text,manufacturer text,model text,device_sn text,device_mac text,createTime text not null,prod_id text,device_udid text,sub_prod_id text)");
        return sb.toString();
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" CREATE INDEX DeviceInfoIndex ON hihealth_device(_id,device_unique_code)");
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String[] getColumns() {
        return new String[]{"_id", "device_unique_code", "deviceName", DeviceCategoryFragment.DEVICE_TYPE, "firmwareVersion", "hardwareVersion", "softwareVersion", "manufacturer", "model", PluginPayAdapter.KEY_DEVICE_INFO_SN, "device_mac", "createTime", "prod_id", PluginPayAdapter.KEY_DEVICE_INFO_UDID, "sub_prod_id"};
    }

    public static ContentValues bxD_(HiDeviceInfo hiDeviceInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_unique_code", hiDeviceInfo.getDeviceUniqueCode());
        contentValues.put("deviceName", hiDeviceInfo.getDeviceName());
        contentValues.put(DeviceCategoryFragment.DEVICE_TYPE, Integer.valueOf(hiDeviceInfo.getDeviceType()));
        contentValues.put("firmwareVersion", hiDeviceInfo.getFirmwareVersion());
        contentValues.put("hardwareVersion", hiDeviceInfo.getHardwareVersion());
        contentValues.put("softwareVersion", hiDeviceInfo.getSoftwareVersion());
        contentValues.put("manufacturer", hiDeviceInfo.getManufacturer());
        if (hiDeviceInfo.getModel() != null) {
            contentValues.put("model", hiDeviceInfo.getModel());
        }
        contentValues.put(PluginPayAdapter.KEY_DEVICE_INFO_SN, hiDeviceInfo.getDeviceSN());
        contentValues.put("device_mac", hiDeviceInfo.getDeviceMac());
        contentValues.put("createTime", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("prod_id", hiDeviceInfo.getProdId());
        contentValues.put(PluginPayAdapter.KEY_DEVICE_INFO_UDID, hiDeviceInfo.getDeviceUdid());
        contentValues.put("sub_prod_id", hiDeviceInfo.getSubProdId());
        return contentValues;
    }

    public static List<HiDeviceInfo> bxG_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBDeviceInfo", "parseDeviceInfoListCursor query is null ");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                arrayList.add(bxE_(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    public static HiDeviceInfo bxF_(Cursor cursor) {
        if (cursor == null) {
            LogUtil.h("Debug_DBDeviceInfo", "parseDeviceInfoCursor query is null");
            return null;
        }
        try {
            return cursor.moveToNext() ? bxE_(cursor) : null;
        } finally {
            cursor.close();
        }
    }

    private static HiDeviceInfo bxE_(Cursor cursor) {
        HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(cursor.getInt(cursor.getColumnIndex(DeviceCategoryFragment.DEVICE_TYPE)));
        hiDeviceInfo.setDeviceId(cursor.getInt(cursor.getColumnIndex("_id")));
        hiDeviceInfo.setDeviceUniqueCode(cursor.getString(cursor.getColumnIndex("device_unique_code")));
        hiDeviceInfo.setDeviceName(cursor.getString(cursor.getColumnIndex("deviceName")));
        hiDeviceInfo.setFirmwareVersion(cursor.getString(cursor.getColumnIndex("firmwareVersion")));
        hiDeviceInfo.setHardwareVersion(cursor.getString(cursor.getColumnIndex("hardwareVersion")));
        hiDeviceInfo.setSoftwareVersion(cursor.getString(cursor.getColumnIndex("softwareVersion")));
        hiDeviceInfo.setManufacturer(cursor.getString(cursor.getColumnIndex("manufacturer")));
        hiDeviceInfo.setModel(cursor.getString(cursor.getColumnIndex("model")));
        hiDeviceInfo.setDeviceSN(cursor.getString(cursor.getColumnIndex(PluginPayAdapter.KEY_DEVICE_INFO_SN)));
        hiDeviceInfo.setDeviceMac(cursor.getString(cursor.getColumnIndex("device_mac")));
        hiDeviceInfo.setProdId(cursor.getString(cursor.getColumnIndex("prod_id")));
        hiDeviceInfo.setDeviceUdid(cursor.getString(cursor.getColumnIndex(PluginPayAdapter.KEY_DEVICE_INFO_UDID)));
        hiDeviceInfo.setSubProdId(cursor.getString(cursor.getColumnIndex("sub_prod_id")));
        return hiDeviceInfo;
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "hihealth_device";
    }
}
