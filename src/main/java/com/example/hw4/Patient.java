package com.example.hw4;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String healthHistory;
    private String insuranceID;
    private int[] scores;
    public Patient(String firstName, String lastName, String email, String phoneNumber, String healthHistory, String insuranceID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.healthHistory = healthHistory;
        this.phone = phoneNumber;
        this.insuranceID = insuranceID;
        this.id = generateUniqueID();
    }
    public Patient(String firstName, String lastName, String email, String phoneNumber, String healthHistory, String insuranceID, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.healthHistory = healthHistory;
        this.phone = phoneNumber;
        this.insuranceID = insuranceID;
        this.id = id;
    }
    public Patient(String firstName, String lastName, String email, String phoneNumber, String healthHistory, String insuranceID, int id, int[] scores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.healthHistory = healthHistory;
        this.phone = phoneNumber;
        this.insuranceID = insuranceID;
        this.id = id;
        this.scores = scores;
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getHealthHistory() {
        return healthHistory;
    }
    public String getInsuranceID() {
        return insuranceID;
    }
    public String getPhone() {
        return phone;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHealthHistory(String healthHistory) {
        this.healthHistory = healthHistory;
    }
    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int generateUniqueID() {
        Random rand = new Random();
        int newId;

        try {
            File db = new File("src/main/resources/com/example/hw4/data");
            if (!db.exists()) {
                db.mkdir(); // Create the directory if it doesn't exist
            }
            File file;
            do {
                newId = 10000 + rand.nextInt(90000);
                String fileName = newId + "_PatientInfo.txt";
                file = new File(db, fileName);
            } while (file.exists());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newId;
    }
    public void addPatient() {
        File db = new File("src/main/resources/com/example/hw4/data");
        String fileName = id + "_PatientInfo.txt";
        File file = new File(db, fileName);

        String patientInfo = "ID: " + id + "\n" +
                "FirstName: " + firstName + "\n" +
                "LastName: " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "HealthHistory: " + healthHistory + "\n" +
                "InsuranceID: " + insuranceID + "\n" +
                "----------";

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(patientInfo);
            System.out.println("Patient Info written to " + file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + email;
    }
    public int[] getScores() {
        return scores;
    }
    public void setScores(int[] scores) {
        this.scores = scores;
    }
}
