import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;


class Colors{
    public static final String RESET = "\033[0m";  // Reset
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
}
class UserInterface{

    public void greetings(){
        System.out.println(Colors.RED + "Witaj w grze Mini Master Mind");
        System.out.println("Autor: Kamil Berenhard grupa 11" + Colors.RESET);
    }
    public void menu(){
        System.out.println(Colors.BLUE);
        System.out.println("-------------------------------");
        System.out.println("----------Menu główne----------");
        System.out.println("-------------------------------" + Colors.RESET);
        System.out.println(Colors.CYAN + "---Nowa gra (wpisz - gra)------");
        System.out.println("---Zasady (wpisz - zasady)-----");
        System.out.println("---Wyjdź z gry (wpisz - exit)--" + Colors.RESET);
    }
    public void rules(){
        System.out.println(Colors.BLUE + "------------Zasady:-----------");
        System.out.println(Colors.CYAN + "1. Program losuje kody czterech kolorów spośród sześciu");
        System.out.println("UWAGA! Kolory mogą się powtarzać");
        System.out.println("Kody kolorów: " + Colors.RED + "C:czerwony " + Colors.GREEN + "Z:zielony " + Colors.BLUE + "N:niebieski " + Colors.YELLOW + "P:pomarańczowy: " + Colors.BLACK + "B:brązowy " + Colors.WHITE + "S:szary");
        System.out.println(Colors.CYAN + "2. Gracz wprowadza swój kod czterech kolorów");
        System.out.println("3. Program informuje gracza o zgodności obu kodów w sposób następujący:");
        System.out.println("Y: kolor zgadza się, lecz pozycja nie");
        System.out.println("X: zgadza się zarówno kolor jak i pozycja");
        System.out.println("4. Jeśli gracz uzyskał cztery symbole X, to gra kończy się wygraną gracza");
        System.out.println("5. Maksymalna liczba rund: 6");
        System.out.println("6. W przeciwnym wypadku następuje powrót do punktu 2." + Colors.RESET);
    }
}
class Gameplay {
    char[] answer = new char[4];
    String guess;
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    int guesses = 1;
    int black; //kolor i pozycja się zgadza
    int white; //kolor się zgadza ale pozycja nie

    public void generateAnswer(){
        Random r = new Random();
        String colors = "cznpbs";
        for (int i=0;i<4;i++){
            answer[i] = colors.charAt(r.nextInt(colors.length()));
        }
        //System.out.println(answer);
    }
    public void game() throws Exception{
        System.out.println(Colors.RED + "GRA SIĘ ZACZYNA");
        System.out.println("POWODZENIA");
        System.out.println();
        guesses = 1;
        while(guesses<=7){
            if(guesses == 7){
                System.out.println("Niestety, nie udało ci się odgadnąć kodu :( Spróbuj jeszcze raz!");
                System.out.println("Prawidłowy kod to: ");
                for(int i=0;i<4;i++){
                    System.out.print(answer[i]);
                }
                break;
            }

            System.out.println(Colors.BLUE + "RUNDA: " + guesses);
            System.out.println("Kody kolorów: " + Colors.RED + "C:czerwony " + Colors.GREEN + "Z:zielony " + Colors.BLUE + "N:niebieski " + Colors.YELLOW + "P:pomarańczowy: " + Colors.BLACK + "B:brązowy " + Colors.WHITE + "S:szary");
            System.out.println("Wprowadź kod kolorów");
            guess = input.readLine();

            if(guess.length()==4){
                for(int i=0;i<=3;i++){
                    if(guess.charAt(i)==answer[i])  black++;
                    else if(Arrays.toString(answer).contains(guess.charAt(i)+"")) white++;
                }
                if(black==4){
                    System.out.println("Gratulacje! Udało ci się odgadnąć kod!");
                    System.out.println("Liczba prób: " + guesses);
                    break;
                }
                else {
                    for(int i=0;i<black;i++) System.out.print("X");
                    for(int i=0;i<white;i++) System.out.print("Y");
                    System.out.println();
                }
                black = 0;
                white = 0;
            }
            else{
                System.out.println("Nieprawidłowy format!");
                continue;
            }
            guesses++;
            if(guesses==6){
                System.out.println("To twoja ostatnia próba. Dobrze się zastanów!");
            }
        }

    }

}


public class MasterMind {

    public static void main(String[] args) throws Exception {
        UserInterface ui = new UserInterface();
        ui.greetings();
        ui.menu();
        while(true){
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String command = input.readLine();
            if(command.equals("gra")){
                Gameplay gp = new Gameplay();
                gp.generateAnswer();
                gp.game();
                ui.menu();
            }
            if(command.equals("zasady")){
                ui.rules();
                ui.menu();
            }
            if(command.equals("exit")){
                System.exit(0);
            }
        }
    }
}
