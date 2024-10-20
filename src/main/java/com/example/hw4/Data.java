package com.example.hw4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Data {
    public static Map<String, String> parseData(File file) {
        Map<String, String> patientInfo = new HashMap<>();
        Map<String, Integer> healthMetrics = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean testResults = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.equals("----------")) {
                    testResults = true;
                    continue;
                }
                if (!testResults) {
                    // Parse patient information
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        patientInfo.put(parts[0], parts[1]);
                    }
                } else {
                    // Parse health metrics
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        healthMetrics.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        patientInfo.putAll(healthMetrics.entrySet().stream().collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue().toString()), HashMap::putAll));

        return patientInfo;
    }

    public static Patient getPatientFromData(Map<String, String> data) {
        String firstName = data.get("FirstName");
        String lastName = data.get("LastName");
        String email = data.get("Email");
        String phone = data.get("Phone");
        String healthHistory = data.get("HealthHistory");
        String insuranceID = data.get("InsuranceID");
        String scoreLM = data.get("LM");
        String scoreLAD = data.get("LAD");
        String scoreLCX = data.get("LCX");
        String scoreRCA = data.get("RCA");
        String scorePDA = data.get("PDA");
        int id = Integer.parseInt(data.get("ID"));
        if (scoreLM != null && scoreLAD != null && scoreLCX != null && scoreRCA != null && scorePDA != null) {
            int scores[];
            int lm = Integer.parseInt(scoreLM);
            int lad = Integer.parseInt(scoreLAD);
            int lcx = Integer.parseInt(scoreLCX);
            int rca = Integer.parseInt(scoreRCA);
            int pda = Integer.parseInt(scorePDA);
            int cac = lm + lad + lcx + rca + pda;
            scores = new int[5];
            scores[0] = lm;
            scores[1] = lad;
            scores[2] = lcx;
            scores[3] = rca;
            scores[4] = pda;
            scores[5] = cac;
            Patient p = new Patient(firstName, lastName, email, phone, healthHistory, insuranceID, id, scores);
        }
        try {
            Patient p = new Patient(firstName, lastName, email, phone, healthHistory, insuranceID, id);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static File getFileFromID(int id) {
        File db = new File("src/main/resources/com/example/hw4/data");
        String fileName = id + "_PatientInfo.txt";
        File file = new File(db, fileName);

        if (file.exists()) {
            return file;
        }
        return null;
    }
}
