package org.example;

class Patient {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String address;
    private String phone;
    private int medicalCardNumber;
    private String diagnosis;

    // Конструктор без параметров
    public Patient() {
    }

    // Конструктор с основными параметрами
    public Patient(int id, String surname, String name, String diagnosis) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.diagnosis = diagnosis;
    }

    // Конструктор со всеми параметрами
    public Patient(int id, String surname, String name, String patronymic,
                   String address, String phone, int medicalCardNumber,
                   String diagnosis) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.address = address;
        this.phone = phone;
        this.medicalCardNumber = medicalCardNumber;
        this.diagnosis = diagnosis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setMedicalCardNumber(int medicalCardNumber) {
        this.medicalCardNumber = medicalCardNumber;
    }

    public int getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", Фамилия='" + surname + '\'' +
                ", Имя='" + name + '\'' +
                ", Отчество='" + patronymic + '\'' +
                ", Адрес='" + address + '\'' +
                ", Телефон='" + phone + '\'' +
                ", Номер медицинской карты=" + medicalCardNumber +
                ", Диагноз='" + diagnosis + '\'' +
                '}';
    }
}
