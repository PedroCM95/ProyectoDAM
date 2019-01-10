package com.example.pedro.proyecto_pgl_2018.Provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;



public class ManagerEventsContentProvider extends ContentProvider {

    private final static  int REQUEST_ONE_REG = 1;
    private final static int REQUEST_ALL_REGS = 2;

    private final static  int BITACORA_ONE_REG = 3;
    private final static int BITACORA_ALL_REGS = 4;

    private static final UriMatcher UriMatcher;
    private static final  SparseArray<String> mimeTypes;

    private SQLiteDatabase sqlDB;
    public DatabaseHelper dbHelper;

    private final static String DATABASE_NAME = "proyecto.db";
    private final static int DATABASE_VERSION = 71;

    public static final String TABLE_NAME = "Solicitudes";
    public static final String BITACORA_TABLE_NAME = "Bitacora";

    public static final int INVALID_URI = -1;

    static {
        UriMatcher = new UriMatcher(0);
        mimeTypes = new SparseArray<>();

        UriMatcher.addURI(ManagerEventApplication.AUTHORITY,
                TABLE_NAME,
                REQUEST_ALL_REGS);
        UriMatcher.addURI(ManagerEventApplication.AUTHORITY,
                TABLE_NAME + "/#",
                REQUEST_ONE_REG);

        mimeTypes.put(REQUEST_ALL_REGS,
                "vnd.android.cursor.dir/vnd." + ManagerEventApplication.AUTHORITY + "." + TABLE_NAME);
        mimeTypes.put(REQUEST_ONE_REG,
                "vnd.android.cursor.item/vnd." + ManagerEventApplication.AUTHORITY + "." + TABLE_NAME);


        //

        UriMatcher.addURI(ManagerEventApplication.AUTHORITY,
                BITACORA_TABLE_NAME,
                BITACORA_ALL_REGS);
        UriMatcher.addURI(ManagerEventApplication.AUTHORITY,
                BITACORA_TABLE_NAME + "/#",
                BITACORA_ONE_REG);

        mimeTypes.put(BITACORA_ALL_REGS,
                "vnd.android.cursor.dir/vnd." + ManagerEventApplication.AUTHORITY + "." + BITACORA_TABLE_NAME);
        mimeTypes.put(BITACORA_ONE_REG,
                "vnd.android.cursor.item/vnd." + ManagerEventApplication.AUTHORITY + "." + BITACORA_TABLE_NAME);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);

            db.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TABLE_NAME +
                    " ( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, " +
                    ManagerEventApplication.Solicitud.Name + " TEXT, " +
                    ManagerEventApplication.Solicitud.Lugar + " TEXT, " +
                    ManagerEventApplication.Solicitud.Queja + " TEXT );");


            db.execSQL("CREATE TABLE " + BITACORA_TABLE_NAME +
                    " ( _id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, " +
                    ManagerEventApplication.Bitacora.ID_REQUEST + " INTEGER, " +
                    ManagerEventApplication.Bitacora.OPERACION + " INTEGER );");

            //initilizeData(db);
        }

        void initilizeData(SQLiteDatabase db) {

            db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + ManagerEventApplication.Solicitud._ID + "," + ManagerEventApplication.Solicitud.Name + "," + ManagerEventApplication.Solicitud.Lugar + ","  + ManagerEventApplication.Solicitud.Queja + " )" +
                    "VALUES (1, 'Solicitud Alcantarillado', 'Calle Romanticismo', 'Estoy en desacuerdo con el problema de las calles')");
            db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + ManagerEventApplication.Solicitud._ID + "," + ManagerEventApplication.Solicitud.Name + "," + ManagerEventApplication.Solicitud.Lugar + ","  + ManagerEventApplication.Solicitud.Queja + " )" +
                    "VALUES (2, 'Solicitud Aceras', 'Calle Graciosa', 'Estoy en desacuerdo con el problema de las obras')");
            db.execSQL("INSERT INTO " + TABLE_NAME + " ( " + ManagerEventApplication.Solicitud._ID + "," + ManagerEventApplication.Solicitud.Name + "," + ManagerEventApplication.Solicitud.Lugar + ","  + ManagerEventApplication.Solicitud.Queja + " )" +
                    "VALUES (3, 'Solicitud Obras', 'Calle Telde', 'Estoy en desacuerdo con el problema de las obras de fomento')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + BITACORA_TABLE_NAME);
            onCreate(db);
        }
    }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            sqlDB = dbHelper.getWritableDatabase();

            String table = "";
            switch (UriMatcher.match(uri)) {
                case REQUEST_ONE_REG:
                    if (null == selection) selection = "";
                    selection += ManagerEventApplication.Solicitud._ID + " = " + uri.getLastPathSegment();
                    table = TABLE_NAME;
                    break;
                case REQUEST_ALL_REGS:
                    table = TABLE_NAME;
                    break;

                case BITACORA_ONE_REG:
                    if (null == selection) selection = "";
                    selection += ManagerEventApplication.Bitacora._ID + " = " + uri.getLastPathSegment();
                    table = BITACORA_TABLE_NAME;
                    break;
                case BITACORA_ALL_REGS:
                    table = BITACORA_TABLE_NAME;
                    break;
            }
           int rows = sqlDB.delete(table, selection, selectionArgs);
            if (rows > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
                return rows;
            }
            throw new SQLException("Failed to delete row into " + uri);
        }

        @Override
        public String getType(Uri uri) {
            return null;
        }

        public void resetDatabase() {
             dbHelper.close();
             dbHelper = new DatabaseHelper(getContext());
    }

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            sqlDB = dbHelper.getWritableDatabase();

            String table = "";
            switch (UriMatcher.match(uri)) {
                case REQUEST_ALL_REGS:
                    table = TABLE_NAME;
                    break;
                case BITACORA_ALL_REGS:
                    table = BITACORA_TABLE_NAME;
                    break;
            }

            long rowId = sqlDB.insert(table, "", values);

            if (rowId > 0){
                Uri rowUri = ContentUris.appendId(
                        uri.buildUpon(), rowId).build();
                getContext().getContentResolver().notifyChange(rowUri, null);
                return rowUri;
            }
            throw new SQLException("Failed to insert row into" + uri);
        }

        @Override
        public boolean onCreate() {
            dbHelper = new DatabaseHelper(getContext());
            return (dbHelper == null) ? false : true;
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection,
                            String[] selectionArgs, String sortOrder) {

            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String query = null;

            switch (UriMatcher.match(uri)) {
                case REQUEST_ONE_REG:
                    if (null == selection) selection = "";
                    selection += ManagerEventApplication.Solicitud._ID + " = " + uri.getLastPathSegment();
                    qb.setTables(TABLE_NAME);
                    break;
                case REQUEST_ALL_REGS:
                    if (TextUtils.isEmpty(sortOrder)) sortOrder = ManagerEventApplication.Solicitud._ID + " ASC";
                    qb.setTables(TABLE_NAME);
                    break;

                case BITACORA_ONE_REG:
                    if (null == selection) selection = "";
                    selection += ManagerEventApplication.Bitacora._ID + " = " + uri.getLastPathSegment();
                    qb.setTables(BITACORA_TABLE_NAME);
                    break;
                case BITACORA_ALL_REGS:
                    if (TextUtils.isEmpty(sortOrder)) sortOrder = ManagerEventApplication.Bitacora._ID + " ASC";
                    qb.setTables(BITACORA_TABLE_NAME);
                    break;
            }

            Cursor c;
            c = qb.query(db, projection, selection, selectionArgs,null, null,
                    sortOrder);
           // c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection,
                          String[] selectionArgs) {

           sqlDB = dbHelper.getWritableDatabase();

           String table = "";
            switch (UriMatcher.match(uri)) {
                case REQUEST_ONE_REG:
                    if (null == selection) selection = "";
                    selection += ManagerEventApplication.Solicitud._ID + " = " + uri.getLastPathSegment();
                    table = TABLE_NAME;
                    break;
                case REQUEST_ALL_REGS:
                   table = TABLE_NAME;
                    break;

                case BITACORA_ONE_REG:
                    if (null == selection) selection = "";
                    selection += ManagerEventApplication.Bitacora._ID + " = " + uri.getLastPathSegment();
                    table = BITACORA_TABLE_NAME;
                    break;
                case BITACORA_ALL_REGS:
                    table = BITACORA_TABLE_NAME;
                    break;
            }

            int rows = sqlDB.update(table, values, selection, selectionArgs);
            if (rows > 0) {
                getContext().getContentResolver().notifyChange(uri, null);
                return rows;
            }

            throw new SQLException("Failed to update row into " + uri);
        }
}

