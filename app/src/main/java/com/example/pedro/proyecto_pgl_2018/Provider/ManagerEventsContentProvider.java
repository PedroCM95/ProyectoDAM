package com.example.pedro.proyecto_pgl_2018.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.SparseArray;

import com.example.pedro.proyecto_pgl_2018.ManagerEventApplication;
import com.example.pedro.proyecto_pgl_2018.database.RequestDatabase;
import com.example.pedro.proyecto_pgl_2018.manager.data.Manager;

public class ManagerEventsContentProvider extends ContentProvider {
    private final static  int REQUEST_ONE_REG = 1;
    private final static int REQUEST_ALL_REGS = 2;

    private static UriMatcher uriMatcher;
    private static SparseArray<String> mimeTypes;

    static {
        uriMatcher = new UriMatcher(0);
        mimeTypes = new SparseArray<>();

        uriMatcher.addURI(ManagerEventApplication.AUTHORITY,
                Manager.TABLE_NAME,
                REQUEST_ALL_REGS);
        uriMatcher.addURI(ManagerEventApplication.AUTHORITY,
                Manager.TABLE_NAME + "/#",
                REQUEST_ONE_REG);

        mimeTypes.put(REQUEST_ALL_REGS,
                "vnd.android.cursor.dir/vnd." + Manager.CONTENT_URI + "." + Manager.TABLE_NAME);
        mimeTypes.put(REQUEST_ONE_REG,
                "vnd.android.cursor.item/vnd." + Manager.CONTENT_URI + "." + Manager.TABLE_NAME);
    }

    RequestDatabase dbHelper;

    public ManagerEventsContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
       return mimeTypes.get(uriMatcher.match(uri));
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        dbHelper = new RequestDatabase(getContext());
        return dbHelper == null ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)){
            case REQUEST_ALL_REGS:
                qb.setTables(Manager.TABLE_NAME);
                break;
            case REQUEST_ONE_REG:
                qb.setTables(Manager.TABLE_NAME);
                break;
        }

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
