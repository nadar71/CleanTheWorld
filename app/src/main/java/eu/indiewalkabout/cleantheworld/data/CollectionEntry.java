package eu.indiewalkabout.cleantheworld.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "COLLECTIONS")
public class CollectionEntry {

    @PrimaryKey(autoGenerate = true)
    private int     id;
    private int     num_of_pieces;
    private boolean isPlastic;
    @ColumnInfo(name = "INSERTED_AT")
    private Date   insertedAt;
    private String description;

    @Ignore
    public CollectionEntry(int num_of_pieces, boolean isPlastic, Date insertedAt, String description) {
        this.num_of_pieces = num_of_pieces;
        this.isPlastic     = isPlastic;
        this.insertedAt    = insertedAt;
        this.description   = description;
    }

    // Main Constructor
    public CollectionEntry(int id, int num_of_pieces, boolean isPlastic, Date insertedAt, String description) {
        this.id            = id;
        this.num_of_pieces = num_of_pieces;
        this.isPlastic     = isPlastic;
        this.insertedAt    = insertedAt;
        this.description   = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_of_pieces() {
        return num_of_pieces;
    }

    public void setNum_of_pieces(int num_of_pieces) {
        this.num_of_pieces = num_of_pieces;
    }

    public boolean isPlastic() {
        return isPlastic;
    }

    public void setPlastic(boolean plastic) {
        isPlastic = plastic;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
