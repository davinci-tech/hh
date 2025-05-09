package net.zetetic.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.OperationCanceledException;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Collator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import net.zetetic.database.sqlcipher.SQLiteProgram;
import net.zetetic.database.sqlcipher.SQLiteStatement;

/* loaded from: classes7.dex */
public class DatabaseUtils {
    private static final boolean DEBUG = false;
    public static final int STATEMENT_ABORT = 6;
    public static final int STATEMENT_ATTACH = 3;
    public static final int STATEMENT_BEGIN = 4;
    public static final int STATEMENT_COMMIT = 5;
    public static final int STATEMENT_DDL = 8;
    public static final int STATEMENT_OTHER = 99;
    public static final int STATEMENT_PRAGMA = 7;
    public static final int STATEMENT_SELECT = 1;
    public static final int STATEMENT_UNPREPARED = 9;
    public static final int STATEMENT_UPDATE = 2;
    private static final String TAG = "DatabaseUtils";
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Collator mColl = null;

    public static final void writeExceptionToParcel(Parcel parcel, Exception exc) {
        int i;
        boolean z = false;
        int i2 = 1;
        if (!(exc instanceof FileNotFoundException)) {
            if (exc instanceof IllegalArgumentException) {
                i = 2;
            } else if (exc instanceof UnsupportedOperationException) {
                i = 3;
            } else if (exc instanceof SQLiteAbortException) {
                i = 4;
            } else if (exc instanceof SQLiteConstraintException) {
                i = 5;
            } else if (exc instanceof SQLiteDatabaseCorruptException) {
                i = 6;
            } else if (exc instanceof SQLiteFullException) {
                i = 7;
            } else if (exc instanceof SQLiteDiskIOException) {
                i = 8;
            } else if (exc instanceof SQLiteException) {
                i = 9;
            } else if (exc instanceof OperationApplicationException) {
                i = 10;
            } else {
                if (!(exc instanceof OperationCanceledException)) {
                    parcel.writeException(exc);
                    Log.e(TAG, "Writing exception to parcel", exc);
                    return;
                }
                i2 = 11;
            }
            z = true;
            i2 = i;
        }
        parcel.writeInt(i2);
        parcel.writeString(exc.getMessage());
        if (z) {
            Log.e(TAG, "Writing exception to parcel", exc);
        }
    }

    private static final void readExceptionFromParcel(Parcel parcel, String str, int i) {
        switch (i) {
            case 2:
                throw new IllegalArgumentException(str);
            case 3:
                throw new UnsupportedOperationException(str);
            case 4:
                throw new SQLiteAbortException(str);
            case 5:
                throw new SQLiteConstraintException(str);
            case 6:
                throw new SQLiteDatabaseCorruptException(str);
            case 7:
                throw new SQLiteFullException(str);
            case 8:
                throw new SQLiteDiskIOException(str);
            case 9:
                throw new SQLiteException(str);
            case 10:
            default:
                parcel.readException(i, str);
                return;
            case 11:
                throw new OperationCanceledException(str);
        }
    }

    public static void bindObjectToProgram(SQLiteProgram sQLiteProgram, int i, Object obj) {
        if (obj == null) {
            sQLiteProgram.bindNull(i);
            return;
        }
        if ((obj instanceof Double) || (obj instanceof Float)) {
            sQLiteProgram.bindDouble(i, ((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof Number) {
            sQLiteProgram.bindLong(i, ((Number) obj).longValue());
            return;
        }
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                sQLiteProgram.bindLong(i, 1L);
                return;
            } else {
                sQLiteProgram.bindLong(i, 0L);
                return;
            }
        }
        if (obj instanceof byte[]) {
            sQLiteProgram.bindBlob(i, (byte[]) obj);
        } else {
            sQLiteProgram.bindString(i, obj.toString());
        }
    }

    public static int getTypeOfObject(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof byte[]) {
            return 4;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return 2;
        }
        return ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) ? 1 : 3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0079, code lost:
    
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007f, code lost:
    
        if (r5.moveToNext() != false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void cursorFillWindow(android.database.Cursor r5, int r6, android.database.CursorWindow r7) {
        /*
            if (r6 < 0) goto L84
            int r0 = r5.getCount()
            if (r6 < r0) goto La
            goto L84
        La:
            int r0 = r5.getPosition()
            int r1 = r5.getColumnCount()
            r7.clear()
            r7.setStartPosition(r6)
            r7.setNumColumns(r1)
            boolean r2 = r5.moveToPosition(r6)
            if (r2 == 0) goto L81
        L21:
            boolean r2 = r7.allocRow()
            if (r2 != 0) goto L28
            goto L81
        L28:
            r2 = 0
        L29:
            if (r2 >= r1) goto L79
            int r3 = r5.getType(r2)
            if (r3 == 0) goto L6c
            r4 = 1
            if (r3 == r4) goto L63
            r4 = 2
            if (r3 == r4) goto L5a
            r4 = 4
            if (r3 == r4) goto L4a
            java.lang.String r3 = r5.getString(r2)
            if (r3 == 0) goto L45
            boolean r3 = r7.putString(r3, r6, r2)
            goto L70
        L45:
            boolean r3 = r7.putNull(r6, r2)
            goto L70
        L4a:
            byte[] r3 = r5.getBlob(r2)
            if (r3 == 0) goto L55
            boolean r3 = r7.putBlob(r3, r6, r2)
            goto L70
        L55:
            boolean r3 = r7.putNull(r6, r2)
            goto L70
        L5a:
            double r3 = r5.getDouble(r2)
            boolean r3 = r7.putDouble(r3, r6, r2)
            goto L70
        L63:
            long r3 = r5.getLong(r2)
            boolean r3 = r7.putLong(r3, r6, r2)
            goto L70
        L6c:
            boolean r3 = r7.putNull(r6, r2)
        L70:
            if (r3 != 0) goto L76
            r7.freeLastRow()
            goto L81
        L76:
            int r2 = r2 + 1
            goto L29
        L79:
            int r6 = r6 + 1
            boolean r2 = r5.moveToNext()
            if (r2 != 0) goto L21
        L81:
            r5.moveToPosition(r0)
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.zetetic.database.DatabaseUtils.cursorFillWindow(android.database.Cursor, int, android.database.CursorWindow):void");
    }

    public static void appendEscapedSQLString(StringBuilder sb, String str) {
        sb.append('\'');
        if (str.indexOf(39) != -1) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '\'') {
                    sb.append('\'');
                }
                sb.append(charAt);
            }
        } else {
            sb.append(str);
        }
        sb.append('\'');
    }

    public static String sqlEscapeString(String str) {
        StringBuilder sb = new StringBuilder();
        appendEscapedSQLString(sb, str);
        return sb.toString();
    }

    public static final void appendValueToSql(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("NULL");
            return;
        }
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                sb.append('1');
                return;
            } else {
                sb.append('0');
                return;
            }
        }
        appendEscapedSQLString(sb, obj.toString());
    }

    public static String concatenateWhere(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return Constants.LEFT_BRACKET_ONLY + str + ") AND (" + str2 + Constants.RIGHT_BRACKET_ONLY;
    }

    public static String getCollationKey(String str) {
        byte[] collationKeyInBytes = getCollationKeyInBytes(str);
        try {
            return new String(collationKeyInBytes, 0, getKeyLen(collationKeyInBytes), "ISO8859_1");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getHexCollationKey(String str) {
        byte[] collationKeyInBytes = getCollationKeyInBytes(str);
        return new String(encodeHex(collationKeyInBytes), 0, getKeyLen(collationKeyInBytes) * 2);
    }

    private static char[] encodeHex(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = DIGITS;
            cArr[i] = cArr2[(b & 240) >>> 4];
            i += 2;
            cArr[i2] = cArr2[b & BaseType.Obj];
        }
        return cArr;
    }

    private static int getKeyLen(byte[] bArr) {
        if (bArr[bArr.length - 1] != 0) {
            return bArr.length;
        }
        return bArr.length - 1;
    }

    private static byte[] getCollationKeyInBytes(String str) {
        if (mColl == null) {
            Collator collator = Collator.getInstance();
            mColl = collator;
            collator.setStrength(0);
        }
        return mColl.getCollationKey(str).toByteArray();
    }

    public static void dumpCursor(Cursor cursor) {
        dumpCursor(cursor, System.out);
    }

    public static void dumpCursor(Cursor cursor, PrintStream printStream) {
        printStream.println(">>>>> Dumping cursor " + cursor);
        if (cursor != null) {
            int position = cursor.getPosition();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                dumpCurrentRow(cursor, printStream);
            }
            cursor.moveToPosition(position);
        }
        printStream.println("<<<<<");
    }

    public static void dumpCursor(Cursor cursor, StringBuilder sb) {
        sb.append(">>>>> Dumping cursor " + cursor + "\n");
        if (cursor != null) {
            int position = cursor.getPosition();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                dumpCurrentRow(cursor, sb);
            }
            cursor.moveToPosition(position);
        }
        sb.append("<<<<<\n");
    }

    public static String dumpCursorToString(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        dumpCursor(cursor, sb);
        return sb.toString();
    }

    public static void dumpCurrentRow(Cursor cursor) {
        dumpCurrentRow(cursor, System.out);
    }

    public static void dumpCurrentRow(Cursor cursor, PrintStream printStream) {
        String str;
        String[] columnNames = cursor.getColumnNames();
        printStream.println("" + cursor.getPosition() + " {");
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            try {
                str = cursor.getString(i);
            } catch (SQLiteException unused) {
                str = "<unprintable>";
            }
            printStream.println("   " + columnNames[i] + '=' + str);
        }
        printStream.println("}");
    }

    public static void dumpCurrentRow(Cursor cursor, StringBuilder sb) {
        String str;
        String[] columnNames = cursor.getColumnNames();
        sb.append("" + cursor.getPosition() + " {\n");
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            try {
                str = cursor.getString(i);
            } catch (SQLiteException unused) {
                str = "<unprintable>";
            }
            sb.append("   " + columnNames[i] + '=' + str + "\n");
        }
        sb.append("}\n");
    }

    public static String dumpCurrentRowToString(Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        dumpCurrentRow(cursor, sb);
        return sb.toString();
    }

    public static void cursorStringToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorStringToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorStringToInsertHelper(Cursor cursor, String str, InsertHelper insertHelper, int i) {
        insertHelper.bind(i, cursor.getString(cursor.getColumnIndexOrThrow(str)));
    }

    public static void cursorStringToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        contentValues.put(str2, cursor.getString(cursor.getColumnIndexOrThrow(str)));
    }

    public static void cursorIntToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorIntToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorIntToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (!cursor.isNull(columnIndex)) {
            contentValues.put(str2, Integer.valueOf(cursor.getInt(columnIndex)));
        } else {
            contentValues.put(str2, (Integer) null);
        }
    }

    public static void cursorLongToContentValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorLongToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorLongToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (!cursor.isNull(columnIndex)) {
            contentValues.put(str2, Long.valueOf(cursor.getLong(columnIndex)));
        } else {
            contentValues.put(str2, (Long) null);
        }
    }

    public static void cursorDoubleToCursorValues(Cursor cursor, String str, ContentValues contentValues) {
        cursorDoubleToContentValues(cursor, str, contentValues, str);
    }

    public static void cursorDoubleToContentValues(Cursor cursor, String str, ContentValues contentValues, String str2) {
        int columnIndex = cursor.getColumnIndex(str);
        if (!cursor.isNull(columnIndex)) {
            contentValues.put(str2, Double.valueOf(cursor.getDouble(columnIndex)));
        } else {
            contentValues.put(str2, (Double) null);
        }
    }

    public static void cursorRowToContentValues(Cursor cursor, ContentValues contentValues) {
        String[] columnNames = cursor.getColumnNames();
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            if (cursor.getType(i) == 4) {
                contentValues.put(columnNames[i], cursor.getBlob(i));
            } else {
                contentValues.put(columnNames[i], cursor.getString(i));
            }
        }
    }

    public static int cursorPickFillWindowStartPosition(int i, int i2) {
        return Math.max(i - (i2 / 3), 0);
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str) {
        return queryNumEntries(sQLiteDatabase, str, null, null);
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        return queryNumEntries(sQLiteDatabase, str, str2, null);
    }

    public static long queryNumEntries(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = " where " + str2;
        }
        return longForQuery(sQLiteDatabase, "select count(*) from " + str + str3, strArr);
    }

    public static boolean queryIsEmpty(SQLiteDatabase sQLiteDatabase, String str) {
        StringBuilder sb = new StringBuilder("select exists(select 1 from ");
        sb.append(str);
        sb.append(Constants.RIGHT_BRACKET_ONLY);
        return longForQuery(sQLiteDatabase, sb.toString(), null) == 0;
    }

    public static long longForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(str);
        try {
            return longForQuery(compileStatement, strArr);
        } finally {
            compileStatement.close();
        }
    }

    public static long longForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        sQLiteStatement.bindAllArgsAsStrings(strArr);
        return sQLiteStatement.simpleQueryForLong();
    }

    public static String stringForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(str);
        try {
            return stringForQuery(compileStatement, strArr);
        } finally {
            compileStatement.close();
        }
    }

    public static String stringForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        sQLiteStatement.bindAllArgsAsStrings(strArr);
        return sQLiteStatement.simpleQueryForString();
    }

    public static ParcelFileDescriptor blobFileDescriptorForQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        SQLiteStatement compileStatement = sQLiteDatabase.compileStatement(str);
        try {
            return blobFileDescriptorForQuery(compileStatement, strArr);
        } finally {
            compileStatement.close();
        }
    }

    public static ParcelFileDescriptor blobFileDescriptorForQuery(SQLiteStatement sQLiteStatement, String[] strArr) {
        sQLiteStatement.bindAllArgsAsStrings(strArr);
        return sQLiteStatement.simpleQueryForBlobFileDescriptor();
    }

    public static void cursorStringToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, cursor.getString(columnIndex));
    }

    public static void cursorLongToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Long.valueOf(cursor.getLong(columnIndex)));
    }

    public static void cursorShortToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Short.valueOf(cursor.getShort(columnIndex)));
    }

    public static void cursorIntToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Integer.valueOf(cursor.getInt(columnIndex)));
    }

    public static void cursorFloatToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Float.valueOf(cursor.getFloat(columnIndex)));
    }

    public static void cursorDoubleToContentValuesIfPresent(Cursor cursor, ContentValues contentValues, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            return;
        }
        contentValues.put(str, Double.valueOf(cursor.getDouble(columnIndex)));
    }

    @Deprecated
    /* loaded from: classes10.dex */
    public static class InsertHelper {
        public static final int TABLE_INFO_PRAGMA_COLUMNNAME_INDEX = 1;
        public static final int TABLE_INFO_PRAGMA_DEFAULT_INDEX = 4;
        private HashMap<String, Integer> mColumns;
        private final SQLiteDatabase mDb;
        private final String mTableName;
        private String mInsertSQL = null;
        private SQLiteStatement mInsertStatement = null;
        private SQLiteStatement mReplaceStatement = null;
        private SQLiteStatement mPreparedStatement = null;

        public InsertHelper(SQLiteDatabase sQLiteDatabase, String str) {
            this.mDb = sQLiteDatabase;
            this.mTableName = str;
        }

        private void buildSQL() throws SQLException {
            StringBuilder sb = new StringBuilder(128);
            sb.append("INSERT INTO ");
            sb.append(this.mTableName);
            sb.append(" (");
            StringBuilder sb2 = new StringBuilder(128);
            sb2.append("VALUES (");
            Cursor cursor = null;
            try {
                cursor = this.mDb.rawQuery("PRAGMA table_info(" + this.mTableName + Constants.RIGHT_BRACKET_ONLY, (String[]) null);
                this.mColumns = new HashMap<>(cursor.getCount());
                int i = 1;
                while (cursor.moveToNext()) {
                    String string = cursor.getString(1);
                    String string2 = cursor.getString(4);
                    this.mColumns.put(string, Integer.valueOf(i));
                    sb.append("'");
                    sb.append(string);
                    sb.append("'");
                    if (string2 == null) {
                        sb2.append("?");
                    } else {
                        sb2.append("COALESCE(?, ");
                        sb2.append(string2);
                        sb2.append(Constants.RIGHT_BRACKET_ONLY);
                    }
                    sb.append(i == cursor.getCount() ? ") " : ", ");
                    sb2.append(i == cursor.getCount() ? ");" : ", ");
                    i++;
                }
                sb.append((CharSequence) sb2);
                this.mInsertSQL = sb.toString();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        private SQLiteStatement getStatement(boolean z) throws SQLException {
            if (z) {
                if (this.mReplaceStatement == null) {
                    if (this.mInsertSQL == null) {
                        buildSQL();
                    }
                    this.mReplaceStatement = this.mDb.compileStatement("INSERT OR REPLACE" + this.mInsertSQL.substring(6));
                }
                return this.mReplaceStatement;
            }
            if (this.mInsertStatement == null) {
                if (this.mInsertSQL == null) {
                    buildSQL();
                }
                this.mInsertStatement = this.mDb.compileStatement(this.mInsertSQL);
            }
            return this.mInsertStatement;
        }

        private long insertInternal(ContentValues contentValues, boolean z) {
            this.mDb.beginTransactionNonExclusive();
            try {
                try {
                    SQLiteStatement statement = getStatement(z);
                    statement.clearBindings();
                    for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                        DatabaseUtils.bindObjectToProgram(statement, getColumnIndex(entry.getKey()), entry.getValue());
                    }
                    long executeInsert = statement.executeInsert();
                    this.mDb.setTransactionSuccessful();
                    return executeInsert;
                } catch (SQLException e) {
                    Log.e(DatabaseUtils.TAG, "Error inserting " + contentValues + " into table  " + this.mTableName, e);
                    this.mDb.endTransaction();
                    return -1L;
                }
            } finally {
                this.mDb.endTransaction();
            }
        }

        public int getColumnIndex(String str) {
            getStatement(false);
            Integer num = this.mColumns.get(str);
            if (num == null) {
                throw new IllegalArgumentException("column '" + str + "' is invalid");
            }
            return num.intValue();
        }

        public void bind(int i, double d) {
            this.mPreparedStatement.bindDouble(i, d);
        }

        public void bind(int i, float f) {
            this.mPreparedStatement.bindDouble(i, f);
        }

        public void bind(int i, long j) {
            this.mPreparedStatement.bindLong(i, j);
        }

        public void bind(int i, int i2) {
            this.mPreparedStatement.bindLong(i, i2);
        }

        public void bind(int i, boolean z) {
            this.mPreparedStatement.bindLong(i, z ? 1L : 0L);
        }

        public void bindNull(int i) {
            this.mPreparedStatement.bindNull(i);
        }

        public void bind(int i, byte[] bArr) {
            if (bArr == null) {
                this.mPreparedStatement.bindNull(i);
            } else {
                this.mPreparedStatement.bindBlob(i, bArr);
            }
        }

        public void bind(int i, String str) {
            if (str == null) {
                this.mPreparedStatement.bindNull(i);
            } else {
                this.mPreparedStatement.bindString(i, str);
            }
        }

        public long insert(ContentValues contentValues) {
            return insertInternal(contentValues, false);
        }

        public long execute() {
            SQLiteStatement sQLiteStatement = this.mPreparedStatement;
            if (sQLiteStatement == null) {
                throw new IllegalStateException("you must prepare this inserter before calling execute");
            }
            try {
                try {
                    return sQLiteStatement.executeInsert();
                } catch (SQLException e) {
                    Log.e(DatabaseUtils.TAG, "Error executing InsertHelper with table " + this.mTableName, e);
                    this.mPreparedStatement = null;
                    return -1L;
                }
            } finally {
                this.mPreparedStatement = null;
            }
        }

        public void prepareForInsert() {
            SQLiteStatement statement = getStatement(false);
            this.mPreparedStatement = statement;
            statement.clearBindings();
        }

        public void prepareForReplace() {
            SQLiteStatement statement = getStatement(true);
            this.mPreparedStatement = statement;
            statement.clearBindings();
        }

        public long replace(ContentValues contentValues) {
            return insertInternal(contentValues, true);
        }

        public void close() {
            SQLiteStatement sQLiteStatement = this.mInsertStatement;
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
                this.mInsertStatement = null;
            }
            SQLiteStatement sQLiteStatement2 = this.mReplaceStatement;
            if (sQLiteStatement2 != null) {
                sQLiteStatement2.close();
                this.mReplaceStatement = null;
            }
            this.mInsertSQL = null;
            this.mColumns = null;
        }
    }

    public static void createDbFromSqlStatements(Context context, String str, int i, String str2) {
        File databasePath = context.getDatabasePath(str);
        databasePath.getParentFile().mkdirs();
        SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(databasePath, (SQLiteDatabase.CursorFactory) null);
        for (String str3 : TextUtils.split(str2, ";\n")) {
            if (!TextUtils.isEmpty(str3)) {
                openOrCreateDatabase.execSQL(str3);
            }
        }
        openOrCreateDatabase.setVersion(i);
        openOrCreateDatabase.close();
    }

    public static int getSqlStatementType(String str) {
        String trim = str.trim();
        if (trim.length() < 3) {
            return 99;
        }
        String upperCase = trim.substring(0, 3).toUpperCase(Locale.ROOT);
        if (upperCase.equals("SEL")) {
            return 1;
        }
        if (upperCase.equals("INS") || upperCase.equals("UPD") || upperCase.equals("REP") || upperCase.equals("DEL")) {
            return 2;
        }
        if (upperCase.equals("ATT")) {
            return 3;
        }
        if (upperCase.equals("COM") || upperCase.equals("END")) {
            return 5;
        }
        if (upperCase.equals("ROL")) {
            return 6;
        }
        if (upperCase.equals("BEG")) {
            return 4;
        }
        if (upperCase.equals("PRA")) {
            return 7;
        }
        if (upperCase.equals("CRE") || upperCase.equals("DRO") || upperCase.equals("ALT")) {
            return 8;
        }
        return (upperCase.equals("ANA") || upperCase.equals("DET")) ? 9 : 99;
    }

    public static String[] appendSelectionArgs(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0) {
            return strArr2;
        }
        String[] strArr3 = new String[strArr.length + strArr2.length];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(strArr2, 0, strArr3, strArr.length, strArr2.length);
        return strArr3;
    }

    public static int findRowIdColumnIndex(String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i].equals("_id")) {
                return i;
            }
        }
        return -1;
    }
}
