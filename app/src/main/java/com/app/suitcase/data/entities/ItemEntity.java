package com.app.suitcase.data.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
    * This class represents the ItemEntity table in the database.
    * It contains the following columns:
    * id: String
    * name: String
    * description: String
    * image: String
    *
    * NOTE: we will save image Path in image column
 */
@Entity(tableName = "items_table")
public class ItemEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "mark_as_purchased")
    public Boolean mark_as_purchased;

    @ColumnInfo(name = "user_id")
    public long user_id;

    /*
        * Constructor
        * @param name: String
        * @param description: String
        * @param image: String
        * @param user_id: long
        * @param mark_as_purchased: boolean
     */

    public ItemEntity(@NonNull String name, int price, @NonNull String description, @NonNull String image, boolean mark_as_purchased, long user_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.mark_as_purchased = mark_as_purchased;
        this.user_id = user_id;
    }

    public ItemEntity getItem() {
        return this;
    }

    public long getId() {
        return this.id;
    }

    public String getItemName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getImagePath() {
        return this.image;
    }
}
