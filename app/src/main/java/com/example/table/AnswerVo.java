package com.example.table;

class AnswerVo {

    private long answerId;
    private long questionId;
    private long formId;
    private String answer;
    private String patientId;
    private long createdBy;
    private long updatedBy;
    private long createdTs;
    private long updatedTs;
    private boolean active;
    private String dataObservationTs;

    public String getDataObservationTs() {
        return dataObservationTs;
    }

    public void setDataObservationTs(String dataObservationTs) {
        this.dataObservationTs = dataObservationTs;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(long createdTs) {
        this.createdTs = createdTs;
    }

    public long getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(long updatedTs) {
        this.updatedTs = updatedTs;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
