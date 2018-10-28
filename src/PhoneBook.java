import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class PhoneBook {

    private static Map<String,String> map = new HashMap<String, String>();
    private static Scanner input = new Scanner(System.in);

    private static void newRecord() throws doubleException{
        String name;
        String phone;

            System.out.println("Введите имя");
            name = input.nextLine();
            if (map.containsKey(name)) {
                throw (new doubleException("Дубликат уже имеющегося имени"));
            } else {
                System.out.println("Введите номер телефона");
                phone = input.nextLine();
                map.put(name, phone);
            }
    }

    private static void addRecord(){
        boolean confirm = true;
        while (confirm) {
            try {
                newRecord();
            }
            catch (doubleException ex){
                System.out.println(ex.getMessage());
            }
            System.out.println("Добавить еще одну запись? (y/n)");
            String yes = input.nextLine();
            if (yes.equals("y")) {
                confirm = true;
            } else {
                confirm = false;
            }
        }
    }
    private static void writeFile() throws IOException {
        FileWriter pb = new FileWriter("phonebook.txt");
        for (Map.Entry entry: map.entrySet()){
//            System.out.print("{name: \"" + entry.getKey() + "\", phone \"" + entry.getValue() + "\"} \n");
            pb.write("{name: \"" + entry.getKey() + "\", phone: \"" + entry.getValue() + "\"} \n");
        }
        pb.close();
    }

    public static void main(String[] args) {
        addRecord();
        try {
            writeFile();
        }
        catch (IOException e){

        }
        input.close();

    }
}
class doubleException extends Exception{
    public doubleException(String message){
        super(message);
    }
}
