package com.micai.fox.resultbean;

/**
 * Created by lq on 2018/4/20.
 */

public class BankBean {
    private String id;
    private String name;
    private int img_id;

    public BankBean(String id, String name, int img_id) {
        this.id = id;
        this.name = name;
        this.img_id = img_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
