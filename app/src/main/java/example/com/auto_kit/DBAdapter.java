package example.com.auto_kit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ROWID="_id";
    static final String KEY_DATE="date";
    static final String KEY_TIME="time";
    static final String KEY_METER="meter";
    static final String KEY_COST="cost";
    static final String KEY_LITRE="litre";
    static final String TAG="DBAdapter";
    static final String DATABASE_NAME="MyDB";
   // static final String DATABASE_TABLE="MyTable";
    static final int DATABASE_VERSION=1;
    //static final String DATABASE_CREATE = "create table MyTable ( _id integer primary key autoincrement, " + "date text not null, time text not null, meter text not null, cost text not null, litre text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    public DBAdapter(Context context) {
        this.context = context;
        DBHelper=new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE IF NOT EXISTS MyTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, time TEXT, meter TEXT, cost TEXT, litre TEXT)");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG,"Upgrading database from version "+oldVersion+" to "+newVersion+", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS MyTable");
            onCreate(db);
        }
    }
    public DBAdapter open() throws SQLException{
        db=DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        DBHelper.close();
    }
    public long insertEntry(String date,String time,String meter,String cost,String litre){

        ContentValues initialValues=new ContentValues();
        initialValues.put(KEY_DATE,date);
        initialValues.put(KEY_TIME,time);
        initialValues.put(KEY_METER,meter);
        initialValues.put(KEY_COST,cost);
        initialValues.put(KEY_LITRE,litre);
        return db.insert("MyTable",null,initialValues);
    }
    public Cursor getAllEntry(){

        return db.query("MyTable", new String[]{KEY_ROWID,KEY_DATE,KEY_TIME,KEY_METER,KEY_COST,KEY_LITRE},null,null,null,null,null);
    }
    public Cursor getEntry(long rowId) throws SQLException{

        Cursor mCursor=db.query(true,"MyTable",new String[]{KEY_ROWID,KEY_DATE,KEY_TIME,KEY_METER,KEY_COST,KEY_LITRE},KEY_ROWID+"="+rowId,null,null,null,null,null,null);

        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
