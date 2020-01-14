import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        //int len = readInt();
        new MainFrame((int)Math.pow(2, 2));
    }
    public static int readInt(){
        Scanner scan = new Scanner(System.in);
        int res = 0;
        while(true){
            try{
                System.out.print("Favor de ingresar la longitud: ");
                res = scan.nextInt();
            }catch(Exception e){
                System.out.println("Favor de solo ingresar valores numericos.");
                scan.nextLine();
                continue;
            }
            break;
        }
        return res;
    }
}
