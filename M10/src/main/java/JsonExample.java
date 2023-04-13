import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JsonExample {
    public void createArrayFromFile() {

        try (FileReader reader = new FileReader("file.txt")) {
            char[] buffer = new char[256];
            int c;

            while ((c = reader.read(buffer)) > 0) {
                if (c < 256) {
                    buffer = Arrays.copyOf(buffer, c);
                }
            }
            String strBuf = String.valueOf(buffer).replaceAll("[\r\n]", " ");
            String[] arrElem = strBuf.replaceAll("  ", " ").split(" ");
            ArrayList<String> names = new ArrayList();
            ArrayList<Integer> ages = new ArrayList();

            for (String elem : arrElem) {
                if ((elem.replaceAll("[a-zA-Z+]", "").length() == 0) && ((!elem.equals("name") && !elem.equals("age")))) {
                    names.add(elem);
                } else if (elem.matches("\\d+")) {
                    ages.add(Integer.valueOf(elem));
                }
            }

            ArrayList<User> users = new ArrayList<>();

            for (int i = 0; i < names.size(); i++) {
                users.add(new User(names.get(i), ages.get(i)));
            }

            try (FileWriter file = new FileWriter("user.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(users);
                file.write(json);

                file.flush();
                file.close();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }
}