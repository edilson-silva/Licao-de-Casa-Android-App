package com.era.data.bean;

/**
 * Created by Edilson on 06/03/2016.
 */
public class SchoolCourse {

    private long scId;          // id da Ecola_Curso no BD
    private School csSchool;    // Objeto School para pegar o id da da Escola
    private Course csCourse;    // Objeto Course para pegar o id da do Curso

    public SchoolCourse() {
        // Construtor Vazio
    }

    public SchoolCourse(long scId, School csSchool, Course csCourse) {
        this.scId = scId;
        this.csSchool = csSchool;
        this.csCourse = csCourse;
    }

    public long getScId() {
        return scId;
    }

    public void setScId(long scId) {
        this.scId = scId;
    }

    public School getCsSchool() {
        return csSchool;
    }

    public void setCsSchool(School csSchool) {
        this.csSchool = csSchool;
    }

    public Course getCsCourse() {
        return csCourse;
    }

    public void setCsCourse(Course csCourse) {
        this.csCourse = csCourse;
    }
}
