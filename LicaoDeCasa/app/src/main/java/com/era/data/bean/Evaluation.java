package com.era.data.bean;

/**
 * Created by Edilson on 06/03/2016.
 */

//Tabela Avaliacao
public class Evaluation {

    private long eId;           //id
    private Task eTask;         //id da tarefa
    private Student eStudent;   //id do aluno/estudante
    private float eNote;        //nota
    private int eSituation;     //situacao

    public Evaluation() {
        //Construtor Vazio
    }

    public Evaluation(long eId, Task eTask, Student eStudent, float eNote, int eSituation) {
        this.eId = eId;
        this.eTask = eTask;
        this.eStudent = eStudent;
        this.eNote = eNote;
        this.eSituation = eSituation;
    }

    public long geteId() {
        return eId;
    }

    public void seteId(long eId) {
        this.eId = eId;
    }

    public Task geteTask() {
        return eTask;
    }

    public void seteTask(Task eTask) {
        this.eTask = eTask;
    }

    public Student geteStudent() {
        return eStudent;
    }

    public void seteStudent(Student eStudent) {
        this.eStudent = eStudent;
    }

    public float geteNote() {
        return eNote;
    }

    public void seteNote(float eNote) {
        this.eNote = eNote;
    }

    public int geteSituation() {
        return eSituation;
    }

    public void seteSituation(int eSituation) {
        this.eSituation = eSituation;
    }
}
