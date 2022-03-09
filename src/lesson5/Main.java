package lesson5;

public class Main {

    public static void main(String[] args) {
        String fileName = "example.csv";

        AppData appDataToSave = new AppData(new String[]{"header1", "header2"});
        appDataToSave.addRandomRows(10);

        System.out.println("Try to save:\n" + appDataToSave);
        AppDataCsvManager.saveTo(fileName, appDataToSave);

        AppData loadedAppData = AppDataCsvManager.loadFrom(fileName);
        System.out.println("Loaded:\n" + loadedAppData);
    }

}
