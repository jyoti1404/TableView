package com.example.table;

import java.io.Serializable;

class Answer implements Serializable {
    private String answer;
    /*  @SerializedName("answerId")
      @Expose
      private long answerId;*/
    private long createdBy;
    /* @SerializedName("createdTs")
     @Expose
     private long createdTs;*/
    private long formId;
    private String patientId;
    private long questionId;
    private long updatedBy;
    private String dataObservationTs;
    private String questionTitle;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getDataObservationTs() {
        return dataObservationTs;
    }

    public void setDataObservationTs(String dataObservationTs) {
        this.dataObservationTs = dataObservationTs;
    }
/*@SerializedName("updatedTs")
    @Expose
    private long updatedTs;*/

   /* public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }*/

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /*public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }*/

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

   /* public long getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(long createdTs) {
        this.createdTs = createdTs;
    }*/

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

 /*   public long getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(long updatedTs) {
        this.updatedTs = updatedTs;
    }*/

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", createdBy=" + createdBy +
                ", formId=" + formId +
                ", patientId='" + patientId + '\'' +
                ", questionId=" + questionId +
                ", updatedBy=" + updatedBy +
                ", dataObservationTs='" + dataObservationTs + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                '}';
    }

}
