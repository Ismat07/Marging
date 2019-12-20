package com.example.ismatkhanam.on_button_click;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {
    public static final String KEY_ROW_ID="_id";
    public static final String KEY_NAME="persons_name";
    public static final String KEY_HOTNESS="persons_hotness";

    private static final String DATABASE_NAME="HotOrNot_db";
    private static final String DATABASE_TABLE="people_table";
    private static final int DATABASE_VERSION=1;

    private DbHelper our_helper;
    private final Context our_context;
    private SQLiteDatabase our_database;

    public HotOrNot(Context c) {
        our_context = c;
    }

    private static class DbHelper extends SQLiteOpenHelper{


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME +
                    " TEXT NOT NULL, " + KEY_HOTNESS + " TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public HotOrNot open()throws SQLException{
        our_helper=new DbHelper(our_context);
        our_database=our_helper.getWritableDatabase();
        return this;
    }

    public void close(){
        our_helper.close();
    }

    public long create_entry(String name, String hotness) {
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_HOTNESS,hotness);
        return our_database.insert(DATABASE_TABLE,null,cv);
    }

    public String getData() {
        String[] column=new String[]{KEY_ROW_ID,KEY_NAME,KEY_HOTNESS};
        Cursor c=our_database.query(DATABASE_TABLE,column,null,null,null,null,null);
        String result="";

        int i_row=c.getColumnIndex(KEY_ROW_ID);
        int i_name=c.getColumnIndex(KEY_NAME);
        int i_hotness=c.getColumnIndex(KEY_HOTNESS);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result=result+c.getString(i_row) + " " + c.getString(i_name) + " " + c.getString(i_hotness) + "\n";
        }
        return result;
    }

    public String getName(long l)throws SQLException {
        String[] column=new String[]{KEY_ROW_ID,KEY_NAME,KEY_HOTNESS};
        Cursor c=our_database.query(DATABASE_TABLE,column,KEY_ROW_ID + "=" + l,null,null,null,null);
        if(c != null){
            c.moveToFirst();
            String name=c.getString(1);
            return name;
        }
        return null;
    }

    public String getHotness(long l)throws SQLException {
        String[] column=new String[]{KEY_ROW_ID,KEY_NAME,KEY_HOTNESS};
        Cursor c=our_database.query(DATABASE_TABLE,column,KEY_ROW_ID + "=" + l,null,null,null,null);
        if(c != null){
            c.moveToFirst();
            String hotness=c.getString(2);
            return hotness;
        }
        return null;
    }

    public void update_entry(long l_row, String m_name, String m_hotness)throws SQLException {
        ContentValues cv_update=new ContentValues();
        cv_update.put(KEY_NAME,m_name);
        cv_update.put(KEY_HOTNESS,m_hotness);
        our_database.update(DATABASE_TABLE,cv_update,KEY_ROW_ID + "=" + l_row,null);
    }

    public void delete_entry(long l_row1)throws SQLException {
        our_database.delete(DATABASE_TABLE,KEY_ROW_ID + "=" + l_row1,null);
    }
}
