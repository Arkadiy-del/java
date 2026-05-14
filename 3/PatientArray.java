package org.example;

class PatientArray {
    private Patient[] patients;

    public PatientArray(Patient[] patients) {
        this.patients = patients;
    }

    // Вывод всех пациентов
    public void printAllPatients() {
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    // a) Список пациентов с заданным диагнозом
    public void printPatientsByDiagnosis(String diagnosis) {
        boolean found = false;

        System.out.println("\nПациенты с диагнозом: " + diagnosis);

        for (Patient patient : patients) {
            if (patient.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                System.out.println(patient);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Пациенты с таким диагнозом не найдены.");
        }
    }

    // b) Список пациентов, номер медицинской карты которых находится в заданном интервале
    public void printPatientsByMedicalCardInterval(int min, int max) {
        boolean found = false;

        System.out.println("\nПациенты с номером медицинской карты в интервале от "
                + min + " до " + max + ":");

        for (Patient patient : patients) {
            int cardNumber = patient.getMedicalCardNumber();

            if (cardNumber >= min && cardNumber <= max) {
                System.out.println(patient);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Пациенты в заданном интервале не найдены.");
        }
    }
}
