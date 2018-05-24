package dja.yazid.gostudeeapp.POJO;

/**
 * Created by yazid on 18/04/2018.
 */

public class Quiz {
    private String id; // id unique du document
    private String subject;
    private String description;

    public Quiz(){

    }

    public Quiz(String id, String subject, String description) {
        this.id = id;
        this.subject = subject;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
