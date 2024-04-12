package com.example.mobilestudy.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mobilestudy.dto.Event;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mobile_study";

    private static final String EVENT_TABLE_NAME = "events";
    private static final int DATABASE_VERSION = 1;

    private static final String EVENT_COLUMN_ID = "id";
    private static final String EVENT_COLUMN_EVENT_NAME = "event_name";
    private static final String EVENT_COLUMN_EVENT_PLACE = "event_place";
    private static final String EVENT_COLUMN_EVENT_DESCRIPTION = "event_description";
    private static final String EVENT_COLUMN_EVENT_CITY = "event_city";
    private static final String EVENT_COLUMN_EVENT_IS_FAVORITE = "event_is_favorite";
    private static final String EVENT_COLUMN_EVENT_IMAGE_PREVIEW = "event_image_preview";
    private static final String EVENT_COLUMN_EVENT_TYPE = "event_type";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + EVENT_TABLE_NAME + "(" +
                EVENT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                EVENT_COLUMN_EVENT_NAME + " TEXT, " +
                EVENT_COLUMN_EVENT_PLACE + " TEXT, " +
                EVENT_COLUMN_EVENT_DESCRIPTION + " TEXT, " +
                EVENT_COLUMN_EVENT_CITY + " TEXT, " +
                EVENT_COLUMN_EVENT_IS_FAVORITE + " INTEGER, " +
                EVENT_COLUMN_EVENT_TYPE + " TEXT, " +
                EVENT_COLUMN_EVENT_IMAGE_PREVIEW + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Получение всех событий
     * @return Все события
     */
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(EVENT_TABLE_NAME, null, null, null, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(EVENT_COLUMN_ID);
                    int nameIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_NAME);
                    int placeIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_PLACE);
                    int descriptionIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_DESCRIPTION);
                    int cityIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_CITY);
                    int isFavoriteIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_IS_FAVORITE);
                    int eventTypeIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_TYPE);
                    int imagePreviewIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_IMAGE_PREVIEW);


                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String place = cursor.getString(placeIndex);
                    String description = cursor.getString(descriptionIndex);
                    String city = cursor.getString(cityIndex);
                    boolean isFavorite = cursor.getInt(isFavoriteIndex) == 1;
                    String imagePreview = cursor.getString(imagePreviewIndex);
                    String eventType = cursor.getString(eventTypeIndex);

                    events.add(new Event(id, name, place, description, imagePreview, city, eventType, isFavorite));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return events;
    }


    /**
     * Получение событий по городу и типу события
     * @param city  Город
     * @param eventType Тип события
     * @return События
     */
    public List<Event> getEventsByCityAndEventType(String city, String eventType) {
        List<Event> filteredList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = EVENT_COLUMN_EVENT_CITY + " = ? AND " + EVENT_COLUMN_EVENT_TYPE + " = ?";
        String[] selectionArgs = {city, eventType};
        Cursor cursor = db.query(EVENT_TABLE_NAME, null, selection, selectionArgs, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(EVENT_COLUMN_ID);
                    int nameIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_NAME);
                    int placeIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_PLACE);
                    int descriptionIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_DESCRIPTION);
                    int isFavoriteIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_IS_FAVORITE);
                    int imagePreviewIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_IMAGE_PREVIEW);

                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String place = cursor.getString(placeIndex);
                    String description = cursor.getString(descriptionIndex);
                    boolean isFavorite = cursor.getInt(isFavoriteIndex) == 1;
                    String imagePreview = cursor.getString(imagePreviewIndex);

                    filteredList.add(new Event(id, name, place, description, imagePreview, city, eventType, isFavorite));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return filteredList;
    }

    /**
     * Получение избранных событий
     * @return Избранные события
     */
    public List<Event> getFavoriteEvents() {
        List<Event> filteredList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = EVENT_COLUMN_EVENT_IS_FAVORITE + " = 1";

        Cursor cursor = db.query(EVENT_TABLE_NAME, null, selection, null, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(EVENT_COLUMN_ID);
                    int nameIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_NAME);
                    int placeIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_PLACE);
                    int descriptionIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_DESCRIPTION);
                    int cityIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_CITY);
                    int isFavoriteIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_IS_FAVORITE);
                    int imagePreviewIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_IMAGE_PREVIEW);
                    int eventTypeIndex = cursor.getColumnIndex(EVENT_COLUMN_EVENT_TYPE);

                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    String place = cursor.getString(placeIndex);
                    String description = cursor.getString(descriptionIndex);
                    boolean isFavorite = cursor.getInt(isFavoriteIndex) == 1;
                    String imagePreview = cursor.getString(imagePreviewIndex);
                    String city = cursor.getString(cityIndex);
                    String eventType = cursor.getString(eventTypeIndex);

                    filteredList.add(new Event(id, name, place, description, imagePreview, city, eventType, isFavorite));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return filteredList;
    }

    /**
     * Удаление события по id
     * @param id id события
     * @return Были удалены события или нет
     */
    public boolean deleteEventById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(EVENT_TABLE_NAME, EVENT_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return rowsAffected > 0;
    }

    /**
     * Удаление всех событий
     * @return Были удалены события или нет
     */
    public boolean deleteAllEvents() {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(EVENT_TABLE_NAME, null, null);
        return rowsAffected > 0;
    }

    /**
     * Добавление события
     * @param event Событие
     * @return Было добавлено событие или нет
     */
    public boolean addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_COLUMN_ID, event.getId());
        values.put(EVENT_COLUMN_EVENT_CITY, event.getCity());
        values.put(EVENT_COLUMN_EVENT_DESCRIPTION, event.getDescription());
        values.put(EVENT_COLUMN_EVENT_NAME, event.getEventName());
        values.put(EVENT_COLUMN_EVENT_IS_FAVORITE, event.getIsFavorite() ? 1 : 0);
        values.put(EVENT_COLUMN_EVENT_IMAGE_PREVIEW, event.getImagePreview());
        values.put(EVENT_COLUMN_EVENT_PLACE, event.getEventPlace());
        values.put(EVENT_COLUMN_EVENT_TYPE, event.getEventType());

        long newRowId = db.insert(EVENT_TABLE_NAME, null, values);
        return newRowId != -1;
    }

    /**
     * Обновление события по id
     * @param id id События
     * @param newEvent Новое событие
     * @return Было обновлено событие или нет
     */
    public boolean updateEventById(int id, Event newEvent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_COLUMN_ID, newEvent.getId());
        values.put(EVENT_COLUMN_EVENT_CITY, newEvent.getCity());
        values.put(EVENT_COLUMN_EVENT_DESCRIPTION, newEvent.getDescription());
        values.put(EVENT_COLUMN_EVENT_NAME, newEvent.getEventName());
        values.put(EVENT_COLUMN_EVENT_IS_FAVORITE, newEvent.getIsFavorite());
        values.put(EVENT_COLUMN_EVENT_IMAGE_PREVIEW, newEvent.getImagePreview());
        values.put(EVENT_COLUMN_EVENT_PLACE, newEvent.getEventPlace());
        values.put(EVENT_COLUMN_EVENT_PLACE, newEvent.getEventPlace());

        int rowsAffected = db.update(EVENT_TABLE_NAME, values, EVENT_COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return rowsAffected > 0;
    }


    /**
     * Обновление избранного события по id
     * @param id id События
     * @param isFavorite Флаг избранного
     */
    public void updateIsFavoriteById(int id, boolean isFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_COLUMN_EVENT_IS_FAVORITE, isFavorite ? 1 : 0);

        String selection = EVENT_COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        db.update(EVENT_TABLE_NAME, values, selection, selectionArgs);
    }
}
