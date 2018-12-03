package com.example.doannd.hci_2018_forestmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.doannd.hci_2018_forestmanagement.model.History;
import com.example.doannd.hci_2018_forestmanagement.model.User;
import com.example.doannd.hci_2018_forestmanagement.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Phiên bản
    private static final int DATABASE_VERSION = 1;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Forest_Manager";

    // Tên bảng
    private static final String TABLE_VIDEO = "Video";
    private static final String TABLE_USER = "User";
    private static final String TABLE_HISTORY = "History";

    //video
    private static final String COLUMN_VIDEO_ID = "Video_Id";
    private static final String COLUMN_VIDEO_DRONE_ID = "Video_Drone_Id";
    private static final String COLUMN_VIDEO_KHUVUC_ID = "Video_KhuVuc_Id";
    private static final String COLUMN_VIDEO_DATE = "Video_Date";

    //user
    private static final String COLUMN_USER_USERNAME = "Username";
    private static final String COLUMN_USER_PASSWORD = "Password";
    private static final String COLUMN_USER_TYPE = "UserType";
    private static final String COLUMN_USER_HOTEN = "HoTen";
    private static final String COLUMN_USER_BIRTHDAY = "BirthDay";

    //history
    private static final String COLUMN_HISTORY_ID = "History_Id";
    private static final String COLUMN_HISTORY_ACTION = "Action";
    private static final String COLUMN_HISTORY_DATE = "Date";
    private static final String COLUMN_HISTORY_USERNAME = "Username";

    private static MyDatabaseHelper mInstance = null;
    private Context context;

    public static MyDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String script1 = "CREATE TABLE " + TABLE_VIDEO + "("
                + COLUMN_VIDEO_ID + " TEXT PRIMARY KEY," //+ COLUMN_VIDEO_CODE + " TEXT,"
                + COLUMN_VIDEO_DRONE_ID + " TEXT," + COLUMN_VIDEO_KHUVUC_ID + " TEXT,"
                + COLUMN_VIDEO_DATE + " DATE" + ")";

        String script2 = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_USERNAME + " TEXT PRIMARY KEY," + COLUMN_USER_PASSWORD + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_HOTEN + " TEXT,"
                + COLUMN_USER_BIRTHDAY + " DATE" + ")";

        String script3 = "CREATE TABLE " + TABLE_HISTORY + "("
                + COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY," + COLUMN_HISTORY_ACTION + " TEXT,"
                + COLUMN_HISTORY_DATE + " TEXT," + COLUMN_HISTORY_USERNAME + " TEXT" + ")";

        // Chạy lệnh tạo bảng.
        db.execSQL(script1);
        db.execSQL(script2);
        db.execSQL(script3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);

        // Và tạo lại.
        onCreate(db);
    }

    // Nếu trong bảng chưa có dữ liệu,
    // Chèn vào mặc định các bản ghi.
    public void createDefaultDatabase() {
        int count = this.getVideosCount();
        if (count == 0) {
            Video video1 = new Video("VIDEO1", "DRONE5", "KHUVUC7", "6/12/2018");
            Video video2 = new Video("VIDEO2", "DRONE2", "KHUVUC3", "5/12/2018");
            Video video3 = new Video("VIDEO3", "DRONE7", "KHUVUC6", "4/12/2018");
            Video video4 = new Video("VIDEO4", "DRONE4", "KHUVUC6", "3/12/2018");
            Video video5 = new Video("VIDEO5", "DRONE3", "KHUVUC3", "2/12/2018");
            Video video6 = new Video("VIDEO6", "DRONE1", "KHUVUC1", "1/12/2018");
            this.addVideo(video1);
            this.addVideo(video2);
            this.addVideo(video3);
            this.addVideo(video4);
            this.addVideo(video5);
            this.addVideo(video6);
        }

        if (this.getUsersCount() == 0) {
            User user1 = new User("kiemlam1", "1", "kiemlam", "Nguyễn Đình Khiêm", "1/12/2018");
            User user2 = new User("kiemlam2", "1", "kiemlam", "Lê Trọng Tiến", "1/12/2018");
            User user3 = new User("quantrivien1", "1", "quantrivien", "Trần Huy Thái", "1/12/2018");
            User user4 = new User("kiemlam3", "1", "kiemlam", "Hoa Nguyễn", "1/12/2018");
            User user5 = new User("quantrivien2", "1", "quantrivien", "Trần Tuấn Khanh", "1/12/2018");
            User user6 = new User("kiemlam4", "1", "kiemlam", "Nguyễn Sung Túc", "1/12/2018");
            User user7 = new User("kiemlam5", "1", "kiemlam", "Nguyễn Duy Đoàn", "1/12/2018");

            this.addUser(user1);
            this.addUser(user2);
            this.addUser(user3);
            this.addUser(user4);
            this.addUser(user5);
            this.addUser(user6);
            this.addUser(user7);
        }

        if (this.getHistoriesCount() == 0) {
            History h1 = new History("Cấu hình drone DRONE1", "1/12/2018", "kiemlam1");
            History h2 = new History("Kiểm tra khu vực KHUVUC1", "1/12/2018", "kiemlam2");
            History h3 = new History("Tạo báo cáo phân tích cho KHUVUC1", "2/12/2018", "kiemlam2");
            History h4 = new History("Tạo báo cáo phân tích cho KHUVUC2", "4/12/2018", "kiemlam3");
            History h5 = new History("Kiểm tra khu vực KHUVUC2", "2/12/2018", "kiemlam3");
            History h6 = new History("Cấu hình drone DRONE2", "5/12/2018", "kiemlam3");
            History h7 = new History("Cấu hình drone DRONE3", "1/12/2018", "kiemlam4");
            History h8 = new History("Kiểm tra khu vực KHUVUC3", "3/12/2018", "kiemlam5");
            History h9 = new History("Tạo báo cáo phân tích cho KHUVUC3", "1/12/2018", "kiemlam4");
            History h10 = new History("Cấu hình drone DRONE4", "2/12/2018", "kiemlam4");

            this.addHistory(h1);
            this.addHistory(h2);
            this.addHistory(h3);
            this.addHistory(h4);
            this.addHistory(h5);
            this.addHistory(h6);
            this.addHistory(h7);
            this.addHistory(h8);
            this.addHistory(h9);
            this.addHistory(h10);
        }
    }

    public void addVideo(Video video) {
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

    public Video getVideo(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VIDEO, new String[]{COLUMN_VIDEO_ID, COLUMN_VIDEO_DRONE_ID,
                        COLUMN_VIDEO_KHUVUC_ID, COLUMN_VIDEO_DATE}, COLUMN_VIDEO_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Video video = new Video(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return video;
    }

    public List<Video> getAllVideos(String sort) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<Video> list = new ArrayList<Video>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VIDEO + " ORDER BY " + sort + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Video video = new Video(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

                // Thêm vào danh sách.
                list.add(video);
            } while (cursor.moveToNext());
        }

        // return video list
        return list;
    }

    public int getVideosCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_VIDEO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateVideo(Video video) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + video.getVideoID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_VIDEO_DRONE_ID, video.getDroneID());
        values.put(COLUMN_VIDEO_KHUVUC_ID, video.getKhuVucID());
        values.put(COLUMN_VIDEO_DATE, video.getDate());

        // updating row
        return db.update(TABLE_VIDEO, values, COLUMN_VIDEO_ID + " = ?",
                new String[]{String.valueOf(video.getVideoID())});
    }

    public void deleteVideo(Video video) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + video.getVideoID());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VIDEO, COLUMN_VIDEO_ID + " = ?",
                new String[]{String.valueOf(video.getVideoID())});
        db.close();
    }

    public void addUser(User user) {
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

    public User getUser(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{COLUMN_USER_USERNAME, COLUMN_USER_PASSWORD,
                        COLUMN_USER_TYPE, COLUMN_USER_HOTEN, COLUMN_USER_BIRTHDAY}, COLUMN_USER_USERNAME + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return user
        return user;
    }

    public List<User> getAllUsers() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<User> list = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER + " ORDER BY " + COLUMN_USER_USERNAME + " ASC";
        ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));

                // Thêm vào danh sách.
                list.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return list;
    }

    public int getUsersCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int updateUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + user.getUserName());

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

    public void deleteUser(String userName) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + userName);

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_USER_USERNAME + " = ?",
                new String[]{String.valueOf(userName)});
        db.close();
    }

    public boolean checkExists(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_USER + " where " + COLUMN_USER_USERNAME + " = '" + userName + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public List<String> getUserType() {
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select " + COLUMN_USER_TYPE + " from " + TABLE_USER;
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            do {
                String s = cursor.getString(0);
                list.add(s);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void addHistory(History history) {
        ///Log.i(TAG, "MyDatabaseHelper.addNote ... " + history.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COLUMN_HISTORY_ID, history.getId());
        values.put(COLUMN_HISTORY_ACTION, history.getAction());
        values.put(COLUMN_HISTORY_DATE, history.getDate());
        values.put(COLUMN_HISTORY_USERNAME, history.getUsername());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_HISTORY, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public History getHistory(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[]{COLUMN_HISTORY_ID, COLUMN_HISTORY_ACTION,
                        COLUMN_HISTORY_DATE, COLUMN_HISTORY_USERNAME}, COLUMN_HISTORY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        History history = new History(cursor.getString(1), cursor.getString(2), cursor.getString(3));
        history.setId(cursor.getInt(0));

        return history;
    }

    public List<History> getHistoriesByUsername(String username) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<History> list = new ArrayList<History>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY + " WHERE " + COLUMN_HISTORY_USERNAME + " = '" + username + "'"
                + " ORDER BY " + COLUMN_HISTORY_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                History history = new History(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                history.setId(cursor.getInt(0));
                // Thêm vào danh sách.
                list.add(history);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public int getHistoriesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_HISTORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
}
