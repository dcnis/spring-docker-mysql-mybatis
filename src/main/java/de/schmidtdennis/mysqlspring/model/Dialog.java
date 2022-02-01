package de.schmidtdennis.mysqlspring.model;


import java.io.Serializable;

public class Dialog implements Serializable {

    private Integer id;
    private Integer lessonId;
    private Integer dialogOrder;
    public String speaker;
    public String pinyin;
    public String chinese;
    public String english;

}
