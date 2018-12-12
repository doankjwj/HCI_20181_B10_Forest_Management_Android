package com.example.doannd.hci_2018_forestmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.doannd.hci_2018_forestmanagement.model.District;
import com.example.doannd.hci_2018_forestmanagement.model.History;
import com.example.doannd.hci_2018_forestmanagement.model.QA;
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
    private static final String TABLE_DISTRICT = "KhuVuc";
    private static final String TABLE_QA = "QA";


    //video
    private static final String COLUMN_VIDEO_ID = "Video_Id";
    private static final String COLUMN_VIDEO_DRONE_ID = "Video_Drone_Id";
    private static final String COLUMN_VIDEO_KHUVUC_ID = "Video_KhuVuc_Id";
    private static final String COLUMN_VIDEO_DATE = "Video_Date";
    private static final String COLUMN_VIDEO_ANALYZE = "Video_IsAnalyze";

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

    //district
    private static final String COLUMN_DISTRICT_ID = "KhuVuc_Id";
    private static final String COLUMN_DISTRICT_NHIETDO = "NhietDo";
    private static final String COLUMN_DISTRICT_DOAM = "DoAm";
    private static final String COLUMN_DISTRICT_DOBAOPHU = "DoBaoPhu";
    private static final String COLUMN_DISTRICT_NGUYCOCHAYRUNG = "NguyCoChayRung";
    private static final String COLUMN_DISTRICT_KHANANGXAMHAI = "KhaNangXamHai";
    private static final String COLUMN_DISTRICT_XULY = "IsXuLy";

    //qa
    private static final String COLUMN_QA_ID = "QA_Id";
    private static final String COLUMN_QA_CAUHOI = "CauHoi";
    private static final String COLUMN_QA_TRALOI = "TraLoi";

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
                + COLUMN_VIDEO_DATE + " DATE," + COLUMN_VIDEO_ANALYZE + " INTEGER" + ")";

        String script2 = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_USERNAME + " TEXT PRIMARY KEY," + COLUMN_USER_PASSWORD + " TEXT,"
                + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_HOTEN + " TEXT,"
                + COLUMN_USER_BIRTHDAY + " DATE" + ")";

        String script3 = "CREATE TABLE " + TABLE_HISTORY + "("
                + COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY," + COLUMN_HISTORY_ACTION + " TEXT,"
                + COLUMN_HISTORY_DATE + " TEXT," + COLUMN_HISTORY_USERNAME + " TEXT" + ")";

        String script4 = "CREATE TABLE " + TABLE_DISTRICT + "("
                + COLUMN_DISTRICT_ID + " TEXT PRIMARY KEY," + COLUMN_DISTRICT_NHIETDO + " TEXT,"
                + COLUMN_DISTRICT_DOAM + " TEXT," + COLUMN_DISTRICT_DOBAOPHU + " TEXT,"
                + COLUMN_DISTRICT_NGUYCOCHAYRUNG + " TEXT," + COLUMN_DISTRICT_KHANANGXAMHAI + " TEXT,"
                + COLUMN_DISTRICT_XULY + " INTEGER" + ")";

        String script5 = "CREATE TABLE " + TABLE_QA + "("
                + COLUMN_QA_ID + " INTEGER PRIMARY KEY," + COLUMN_QA_CAUHOI + " TEXT,"
                + COLUMN_QA_TRALOI + " TEXT" + ")";

        // Chạy lệnh tạo bảng.
        db.execSQL(script1);
        db.execSQL(script2);
        db.execSQL(script3);
        db.execSQL(script4);
        db.execSQL(script5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTRICT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QA);

        // Và tạo lại.
        onCreate(db);
    }

    // Nếu trong bảng chưa có dữ liệu,
    // Chèn vào mặc định các bản ghi.
    public void createDefaultDatabase() {
        int count = this.getVideosCount();
        if (count == 0) {
            Video video1 = new Video("VIDEO1", "Drone010", "Khu vực A1", "11/12/2018",0);
            Video video2 = new Video("VIDEO2", "Drone002", "Khu vực A5", "12/12/2018",0);
            Video video3 = new Video("VIDEO3", "Drone147", "Khu vực C3", "07/12/2018",0);
            Video video4 = new Video("VIDEO4", "Drone730", "Khu vực B2", "02/12/2018",0);
            Video video5 = new Video("VIDEO5", "Drone005", "Khu vực D4", "09/12/2018",0);
            Video video6 = new Video("VIDEO6", "Drone017", "Khu vực D1", "10/12/2018",0);
            this.addVideo(video1);
            this.addVideo(video2);
            this.addVideo(video3);
            this.addVideo(video4);
            this.addVideo(video5);
            this.addVideo(video6);
        }

        if (this.getUsersCount() == 0) {
            User user1 = new User("kiemlam1", "1", "kiemlam", "Nguyễn Đình Khiêm", "1/1/2000");
            User user2 = new User("kiemlam2", "1", "kiemlam", "Lê Trọng Tiến", "1/1/2000");
            User user3 = new User("quantrivien1", "1", "quantrivien", "Trần Huy Thái", "1/1/2000");
            User user4 = new User("kiemlam3", "1", "kiemlam", "Hoa Nguyễn", "1/1/2018");
            User user5 = new User("quantrivien2", "1", "quantrivien", "Trần Tuấn Khanh", "1/1/2000");
            User user6 = new User("kiemlam4", "1", "kiemlam", "Nguyễn Sung Túc", "1/1/2000");
            User user7 = new User("kiemlam5", "1", "kiemlam", "Nguyễn Duy Đoàn", "1/1/2000");

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

        if (this.getKhuVucCount() == 0){
            District k1=new District("Khu vực A1","28 độ C","98%","80%","1%","0%");
            District k2=new District("Khu vực A5","27 độ C","77%","90%","0.1%","1%");
            District k3=new District("Khu vực B2","21 độ C","78%","70%","1","0%");
            District k4=new District("Khu vực C3","33 độ C","95%","60%","0.1%","30%");
            District k5=new District("Khu vực D1","40 độ C","84%","75%","1%","20%");
            District k6=new District("Khu vực D4","38 độ C","83%","85%","0.1%","10%");

            this.addKhuVuc(k1);
            this.addKhuVuc(k2);
            this.addKhuVuc(k3);
            this.addKhuVuc(k4);
            this.addKhuVuc(k5);
            this.addKhuVuc(k6);
        }

        if (this.getQACount()==0){
            QA q1=new QA(1,"Phần mềm điều khiển drone có hỗ trợ đa ngôn ngữ không?","Phần mềm điều khiển drone  hỗ trợ đa ngôn ngữ, người dùng được sử dụng các ngôn ngữ tùy chọn. Hiện tại ngôn ngữ trên phần mềm là Tiếng Việt, người dung có thể chuyển đổi ngôn ngữ thông qua settings của phần mềm.");
            QA q2=new QA(2,"Làm thế nào để người chưa có kiến thức về tin học có thể sử dụng?","Phần mềm sẽ có những hướng dẫn cơ bản khi bắt đầu sử dụng lần đầu dễ cho mọi người dùng hoặc người dùng có thể liên lạc với admin thông qua email hoặc số điện thoại hiển thị trong thông tin phần mềm để được hỗ trợ.");
            QA q3=new QA(3,"Nếu mất điện thì phải làm sao?","Nếu mất điện người dùng có thể dùng điện thoại kết nối 3g hoặc sử dụng wifi của nhà khác để kết nối với drone.\n" +
                    "Drone cũng có chế độ tự tìm đường lúc người dùng không có kết nối.\n");
            QA q4=new QA(4,"Bị hacker hack quyền điều khiển phải làm sao?","Nếu bị hack người dùng cần ngắt kết nối và thông báo cho admin.\n" +
                    "Người dùng có thể cài đạt them tường lửa để tránh hacker.\n");
            QA q5=new QA(5,"Nếu xảy ra sự cố cho 1 hoặc vài drone thì phải làm sao?","Khi xảy ra sự cố các dữ liệu trước khi bị hỏng sẽ được tự động chuyển về trung tâm và các drone khác vẫn có thể hoạt động bình thường.");
            QA q6=new QA(6,"Chi phí cho hệ thống như thế nào?","Chi phí cho 1 con drone là 10tr đồng và hệ thống điều khiển thông minh(Máy chủ) sẽ có giá là 20tr đồng. Phần mềm trên smartphone sẽ miễn phí.");
            QA q7=new QA(7,"Phần mềm có dễ sử dụng không?","Vì khi người dùng không phải làm gì để điều khiển thì dễ hay là khó? Trong thực tế, toàn bộ hệ thống có thể tự động hoạt động và tương tác lẫn nhau,  người dùng không cần phải quan tâm đến nó, nó tự biết phải làm gì. Tuy nhiên đôi khi  người dùng muốn thay đổi thói quen và cần phải điều khiển thì dễ dàng nhất là chiếc smartphone bên cạnh  người dùng . Những chiếc  smartphone này rất nhỏ gọn nhưng cực kỳ mạnh mẽ, không có giới hạn nào cho chiếc  smartphone của  người dùng ,  người dùng có thể làm tất cả với những chức năng đã được ghi rõ ràng trên nó.");
            QA q8=new QA(8,"Người dùng có thể báo lỗi ở đâu?","Người dùng có thể báo lỗi thông qua email hỗ trợ hiển thị trên phần mềm hoặc thông qua nhận xét trên appstore hoặc googlestore hay trên trang chủ phần mềm.");
            QA q9=new QA(9,"Nếu có Update người dùng có được thông báo không ? nếu không update có ảnh hưởng gì không?","Người dùng sẽ được thông báo update mới nhất.\n" +
                    "Nếu không update người dùng vẫn có thể sử dụng phiên bản cũ nhưng nếu có bug thì người dùng bắt buộc phải update.\n");
            QA q10=new QA(10,"Chi phí sửa chữa cho 1 con drone có đắt không?","Tùy thuộc vào bộ phận hay hệ thống bị hỏng sẽ có những định mức giá riêng. Nếu còn bảo hành người dùng có thể được miễn phí sửa chữa.");

            this.addQA(q1);
            this.addQA(q2);
            this.addQA(q3);
            this.addQA(q4);
            this.addQA(q5);
            this.addQA(q6);
            this.addQA(q7);
            this.addQA(q8);
            this.addQA(q9);
            this.addQA(q10);
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
        values.put(COLUMN_VIDEO_ANALYZE, video.getIsAnalyze());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_VIDEO, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public Video getVideo(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_VIDEO, new String[]{COLUMN_VIDEO_ID, COLUMN_VIDEO_DRONE_ID,
                        COLUMN_VIDEO_KHUVUC_ID, COLUMN_VIDEO_DATE,COLUMN_VIDEO_ANALYZE}, COLUMN_VIDEO_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Video video = new Video(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4));
//        video.setIsAnalyze(cursor.getInt(4));
        return video;
    }

    public List<Video> getAllVideos(String sort) {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<Video> list = new ArrayList<Video>();

        String selectQuery="";
        if (sort.equals("Video_Date")){
            selectQuery = "SELECT  * FROM " + TABLE_VIDEO + " ORDER BY " + sort + " DESC";
        }
        else{
            selectQuery = "SELECT  * FROM " + TABLE_VIDEO + " ORDER BY " + sort + " ASC";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Video video = new Video(cursor.getString(0), cursor.getString(1), cursor.getString(2)
                        , cursor.getString(3),cursor.getInt(4));
//                video.setIsAnalyze(cursor.getInt(4));
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
        values.put(COLUMN_VIDEO_ANALYZE, video.getIsAnalyze());

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

    public void addKhuVuc(District khuVuc) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + khuVuc.getID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DISTRICT_ID, khuVuc.getID());
        values.put(COLUMN_DISTRICT_NHIETDO, khuVuc.getNhietDo());
        values.put(COLUMN_DISTRICT_DOAM, khuVuc.getDoAm());
        values.put(COLUMN_DISTRICT_DOBAOPHU, khuVuc.getDoBaoPhu());
        values.put(COLUMN_DISTRICT_NGUYCOCHAYRUNG, khuVuc.getNguyCoChayRung());
        values.put(COLUMN_DISTRICT_KHANANGXAMHAI, khuVuc.getKhaNangXamHai());
        values.put(COLUMN_DISTRICT_XULY, khuVuc.getXuLy());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_DISTRICT, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public int updateDistrict(District khuVuc) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + khuVuc.getID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DISTRICT_ID, khuVuc.getID());
        values.put(COLUMN_DISTRICT_NHIETDO, khuVuc.getNhietDo());
        values.put(COLUMN_DISTRICT_DOAM, khuVuc.getDoAm());
        values.put(COLUMN_DISTRICT_DOBAOPHU, khuVuc.getDoBaoPhu());
        values.put(COLUMN_DISTRICT_NGUYCOCHAYRUNG, khuVuc.getNguyCoChayRung());
        values.put(COLUMN_DISTRICT_KHANANGXAMHAI, khuVuc.getKhaNangXamHai());
        values.put(COLUMN_DISTRICT_XULY, khuVuc.getXuLy());

        // updating row
        return db.update(TABLE_DISTRICT, values, COLUMN_DISTRICT_ID + " = ?",
                new String[]{String.valueOf(khuVuc.getID())});
    }

    public District getKhuVuc(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DISTRICT, new String[]{COLUMN_DISTRICT_ID, COLUMN_DISTRICT_NHIETDO,
                        COLUMN_DISTRICT_DOAM, COLUMN_DISTRICT_DOBAOPHU, COLUMN_DISTRICT_NGUYCOCHAYRUNG,
                        COLUMN_DISTRICT_KHANANGXAMHAI, COLUMN_DISTRICT_XULY}, COLUMN_DISTRICT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        District khuVuc = new District(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4)
                ,cursor.getString(5));
        khuVuc.setXuLy(cursor.getInt(6));
        // return user
        return khuVuc;
    }

    public List<District> getAllKhuVuc() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<District> list = new ArrayList<District>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DISTRICT + " ORDER BY " + COLUMN_DISTRICT_ID + " ASC";
        ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                District khuVuc = new District(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5));
                khuVuc.setXuLy(cursor.getInt(6));
                // Thêm vào danh sách.
                list.add(khuVuc);
            } while (cursor.moveToNext());
        }

        // return user list
        return list;
    }

    public int getKhuVucCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_DISTRICT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void addQA(QA qa) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + qa.getID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QA_ID, qa.getID());
        values.put(COLUMN_QA_CAUHOI, qa.getCauHoi());
        values.put(COLUMN_QA_TRALOI, qa.getTraLoi());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_QA, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public int updateQA(QA qa) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + qa.getID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QA_ID, qa.getID());
        values.put(COLUMN_QA_CAUHOI, qa.getCauHoi());
        values.put(COLUMN_QA_TRALOI, qa.getTraLoi());

        // updating row
        return db.update(TABLE_QA, values, COLUMN_QA_ID + " = ?",
                new String[]{String.valueOf(qa.getID())});
    }

    public QA getQA(String id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QA, new String[]{COLUMN_QA_ID, COLUMN_QA_CAUHOI,
                        COLUMN_QA_TRALOI}, COLUMN_QA_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        QA qa = new QA(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2));
        // return user
        return qa;
    }

    public List<QA> getAllQA() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        List<QA> list = new ArrayList<QA>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QA + " ORDER BY " + COLUMN_QA_ID + " ASC";
        ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                QA qa = new QA(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2));
                list.add(qa);
            } while (cursor.moveToNext());
        }

        // return user list
        return list;
    }

    public int getQACount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_QA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
}
