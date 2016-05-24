package com.era.data.bean;

/**
 * Created by Edilson on 06/03/2016.
 */
//Tabela Curso
public class Course {

    private long cid;                // id do Curso no BD
    private String cDescription;     // descricao

    public Course() {
        // Construtor Vazio
    }

    public Course(long cid, String cDescription) {
        this.cid = cid;
        this.cDescription = cDescription;
    }

    public long getCid() { return cid; }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }
}
