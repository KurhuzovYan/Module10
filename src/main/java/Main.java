public class Main {
    public static void main(String[] args) {
        InputStreamExample ise = new InputStreamExample();
        System.out.println(ise.searchCorrectNumber());

        CountOfWordsInFile cowif = new CountOfWordsInFile();
        cowif.counter();

        JsonExample je = new JsonExample();
        je.createArrayFromFile();
    }
}
