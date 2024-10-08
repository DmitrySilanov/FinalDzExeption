import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите ваши данные через пробел: (Фамилия Имя Отчество Дата рождения(dd.MM.yyyy) Номер телефона Пол(f/m)");
            input = scanner.nextLine();
        }

        String[] parts = input.split(" ");
        if (parts.length != 6) {
            if (parts.length < 6) {
                System.err.println("Ошибка: введено меньше данных, чем требуется.");
            } else {
                System.err.println("Ошибка: введено больше данных, чем требуется.");
            }
            return;
        }

        try {
            String lastName = parts[0];
            String firstName = parts[1];
            String middleName = parts[2];
            String birthDateStr = parts[3];
            String phoneNumberStr = parts[4];
            String gender = parts[5];


            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = dateFormat.parse(birthDateStr);


            long phoneNumber = Long.parseLong(phoneNumberStr);


            if (!gender.equals("f") && !gender.equals("m")) {
                throw new IllegalArgumentException("Ошибка: неверный формат пола.");
            }


            String fileName = lastName + ".txt";
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write("Фамилия :" + lastName + ", Имя :" + firstName + ", Отчество :" + middleName + ", Дата рождения :" + birthDateStr + ", Номер телефона :" + phoneNumber + " , пол :" + gender + "\n");
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (ParseException e) {
            System.err.println("Ошибка: неверный формат даты рождения.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: неверный формат номера телефона.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}