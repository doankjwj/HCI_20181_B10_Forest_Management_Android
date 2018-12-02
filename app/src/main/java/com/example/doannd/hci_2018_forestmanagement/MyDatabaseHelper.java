package com.example.doannd.hci_2018_forestmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.doannd.hci_2018_forestmanagement.User;

import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Forest_Manager";


    // Tên bảng: Note.
    private static final String TABLE_VIDEO = "Video";
    private static final String TABLE_USER = "User";

    //video
    private static final String COLUMN_VIDEO_ID ="Video_Id";
    private static final String COLUMN_VIDEO_DRONE_ID ="Video_Drone_Id";
    private static final String COLUMN_VIDEO_KHUVUC_ID = "Video_KhuVuc_Id";
    private static final String COLUMN_VIDEO_DATE = "Video_Date";

    //user
    private static final String COLUMN_USER_USERNAME ="Username";
    private static final String COLUMN_USER_PASSWORD ="Password";
    private static final String COLUMN_USER_TYPE ="UserType";
    private static final String COLUMN_USER_HOTEN ="HoTen";
    private static final String COLUMN_USER_BIRTHDAY ="BirthDay";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_VIDEO + "("
                + COLUMN_VIDEO_ID + " TEXT PRIMARY KEY," //+ COLUMN_VIDEO_CODE + " TEXT,"
                + COLUMN_VIDEO_DRONE_ID + " TEXT," + COLUMN_VIDEO_KHUVUC_ID + " TEXT,"
                + COLUMN_VIDEO_DATE + " DATE" + ")";

        String script1 = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_USERNAME + " INTEGER PRIMARY KEY," + COLUMN_USER_PASSWORD + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_HOTEN + " TEXT,"
                + COLUMN_USER_BIRTHDAY + " DATE" + ")";

        // Chạy lệnh tạo bảng.
        db.execSQL(script);
        db.execSQL(script1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Và tạo lại.
        onCreate(db);
    }

    // Nếu trong bảng Note chưa có dữ liệu,
    // Trèn vào mặc định 2 bản ghi.
    public void createDefaultNotesIfNeed()  {
        int count = this.getNotesCount();
        if(count ==0 ) {
            Video video1 = new Video("VIDEO1","DRONE5","KHUVUC7","6/12/2018");
            Video video2 = new Video("VIDEO2","DRONE2","KHUVUC3","5/12/2018");
            Video video3 = new Video("VIDEO3","DRONE7","KHUVUC6","4/12/2018");
            Video video4 = new Video("VIDEO4","DRONE4","KHUVUC6","3/12/2018");
            Video video5 = new Video("VIDEO5","DRONE3","KHUVUC3","2/12/2018");
            Video video6 = new Video("VIDEO6","DRONE1","KHUVUC1","1/12/2018");
            this.addNote(video1);
            this.addNote(video2);
            this.addNote(video3);
            this.addNote(video4);
            this.addNote(video5);
            this.addNote(video6);
        }

        User user1=new User("kiemlam1","1","kiemlam","Nguyễn Đình Khiêm","1/12/2018");
        User user2=new User("kiemlam2","1","kiemlam","Nguyễn Đình Khiêm","1/12/2018");
        User user3=new User("quantrivien1","1","quantrivien","Nguyễn Đình Khiêm","1/12/2018");
        User user4=new User("kiemlam3","1","kiemlam","Nguyễn Đình Khiêm","1/12/2018");
        User user5=new User("quantrivien2","1","quantrivien","Nguyễn Đình Khiêm","1/12/2018");
        User user6=new User("kiemlam4","1","kiemlam","Nguyễn Đình Khiêm","1/12/2018");
        User user7=new User("kiemlam5","1","kiemlam","Hoa Nguyễn","1/12/2018");

        this.addUser(user1);
        this.addUser(user2);
        this.addUser(user3);
        this.addUser(user4);
        this.addUser(user5);
        this.addUser(user6);
        this.addUser(user7);
    }

    public void addUser(User user){
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + user.getUserName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, user.getUserName());
        values.put(COLUMN_USER_PASSWORD, user.getPassWord());
        values.put(COLUMN_USER_TYPE, user.getUserType());
        values.put(COLUMN_USER_HOTEN, user.getHoTen());
        values.put(COLUMN_USER_BIRTHDAY, user.getBirthDay());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_USER, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public void addNote(Video video) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + video.getVideoID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_ID, video.getVideoID());
        values.put(COLUMN_VIDEO_DRONE_ID, video.getDroneID());
        values.put(COLUMN_VIDEO_KHUVUC_ID, video.getKhuVucID());
        values.put(COLUMN_VIDEO_DATE, video.getDate());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_VIDEO, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public Video getNote(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VIDEO, new String[] { COLUMN_VIDEO_ID,COLUMN_VIDEO_DRONE_ID,
                        COLUMN_VIDEO_KHUVUC_ID, COLUMN_VIDEO_DATE }, COLUMN_VIDEO_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Video video = new Video(cursor.getString(0),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return note
        return video;
    }

    public User getUser(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] { COLUMN_USER_USERNAME,COLUMN_USER_PASSWORD,
                        COLUMN_USER_TYPE, COLUMN_USER_HOTEN, COLUMN_USER_BIRTHDAY}, COLUMN_USER_USERNAME + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4));
        // return note
        return user;
    }

    public List<Video> getAllNotes(String sort) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<Video> noteList = new ArrayList<Video>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VIDEO +" ORDER BY "+ sort + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Video note = new Video(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));

                // Thêm vào danh sách.
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
    }

    public List<User> getAllUsers() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<User> list = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4));

                // Thêm vào danh sách.
                list.add(user);
            } while (cursor.moveToNext());
        }

        // return note list
        return list;
    }

    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_VIDEO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateNote(Video video) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + video.getVideoID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_DRONE_ID, video.getDroneID());
        values.put(COLUMN_VIDEO_KHUVUC_ID, video.getKhuVucID());
        values.put(COLUMN_VIDEO_DATE, video.getDate());

        // updating row
        return db.update(TABLE_VIDEO, values, COLUMN_VIDEO_ID + " = ?",
                new String[]{String.valueOf(video.getVideoID())});
    }

    public void deleteNote(Video note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getVideoID() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VIDEO, COLUMN_VIDEO_ID + " = ?",
                new String[] { String.valueOf(note.getVideoID()) });
        db.close();
    }

    public int updateUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + user.getUserName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PASSWORD, user.getPassWord());
        values.put(COLUMN_USER_TYPE, user.getUserType());
        values.put(COLUMN_USER_HOTEN, user.getHoTen());
        values.put(COLUMN_USER_BIRTHDAY, user.getBirthDay());

        // updating row
        return db.update(TABLE_USER, values, COLUMN_USER_USERNAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
    }

    public void deleteNote(User user) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + user.getUserName() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_USER_USERNAME + " = ?",
                new String[] { String.valueOf(user.getUserName()) });
        db.close();
    }
}
