package com.example.dhadotid.cumlaude.Model;

/**
 * Created by dhadotid on 03/09/2017.
 */

public class CatatanModel {
    private String id;
    private String id_user;
    private String title;
    private String catatan;
    private String created_at;

    public CatatanModel(String id, String id_user, String title, String catatan, String created_at){
        this.id = id;
        this.id_user = id_user;
        this.title = title;
        this.catatan = catatan;
        this.created_at = created_at;
    }

    public String getId(){
        return id;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getId_user() {
        return id_user;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated_at() {
        return created_at;
    }
}
