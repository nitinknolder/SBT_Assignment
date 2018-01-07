package Calculator;
import java.sql.*;
import java.sql.Timestamp;
import java.util.Date;
import java.io.*;
import java.util.Scanner;
public class Calc {
    public static void main(String[] args) {

       int id=0;
        float operand1, operand2, result = 0;
        String operator = null;
        Scanner input = new Scanner(System.in);

        System.out.println("KnolCalulator");
        System.out.println("1:Addition");
        System.out.println("2:Subtraction");
        System.out.println("3:Multiplication");
        System.out.println("4:Division");
        System.out.println("5:Modulus");
        System.out.println("6:Maximum");
        System.out.println("7:Minimum");
        System.out.println("8:Absolute");
        System.out.println("9:Power");

        System.out.println("Enter the first number: ");

        while (!input.hasNextFloat()) {
            input.next();
            System.out.println("You Have Entered Invalid Input");
        }
        operand1 = input.nextFloat();

        System.out.println("Enter Your Choice: ");

        operator = input.next();

        System.out.println("Enter the Second Number: ");
        operand2 = input.nextFloat();

        switch (operator) {
            case "1":
                result = operand1 + operand2;
                break;
            case "2":
                result = operand1 - operand2;
                break;
            case "3":
                result = operand1 * operand2;
                break;
            case "4":
                if (operand2 == 0) {
                    System.out.println("Cannot divide by Zero");
                    System.exit(0);
                } else {
                    result = operand1 / operand2;

                }
                break;
            case "5":
                if (operand2 == 0) {
                    System.out.println("Cannot divide by Zero");
                    System.exit(0);
                } else {
                    result = operand1 % operand2;

                }
                break;
            case "6":
                if (operand1 > operand2) {
                    System.out.println("operand1 is maximum");
                } else {
                    System.out.println("operand2 is maximum");
                }
                break;

            case "7":
                if (operand1 > operand2) {
                    System.out.println("operand1 is maximum");
                } else {
                    System.out.println("operand2 is maximum");
                }
                break;
            case "8":
                System.out.println("Enter Your Value:");
                operand1=input.nextInt();
                System.out.println(Math.abs(operand1));
                break;
            case "9":
               result= (float) Math.pow(operand1,operand2);
        System.out.println((result));
                    break;
            default:
                System.out.println("You have Entered wrong choice!!!");
                System.exit(0);

        }
        System.out.println(" " + operand1 + " " + operator + " " + operand2 + "=" + result);
        System.out.println();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator1","root","knoldus");
            Statement stmt = con.createStatement();
            System.out.println("ID Operand1 Operand2 Operator Result");
            ResultSet rs = stmt.executeQuery("SELECT * FROM calculatorr");
            while (rs.next())
                System.out.println(rs.getInt(1) + "     "+ rs.getFloat(2) + "      " + rs.getFloat(3) + "       " + rs.getString(4)+ "      " +rs.getFloat(5));

            String query = " insert into calculatorr (id,left_operand,right_operand,operator,result) "+" values (?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, id);
            preparedStmt.setFloat(2, operand1);
            preparedStmt.setFloat(3, operand2);
            preparedStmt.setString(4, operator);
            preparedStmt.setFloat(5, result);
            // execute the preparedstatement
            boolean execute = preparedStmt.execute();

            // con.close();
        }
        catch(Exception e){ System.out.println(e);}

    }

}