import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Surveys {
    public void Survey(Statement statement) {
        // 이름과 전화번호 입력 확인
        System.out.print("이름 : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("전화번호");
        String phone_number = scanner.next();

        Commons commons = new Commons();
        String strDate = commons.getGeneratorID();
        String query = "insert into users_list (USERS_UID, PHONE, NAME)" +
                "VALUES('" + strDate + "', '" + phone_number + "', '" + name + "');";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 설문과 답항 내용 출력
        query = "SELECT * FROM questions_list ORDER BY ORDERS;";
        try {
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                System.out.print(resultset.getInt("ORDERS") + ". ");
                System.out.println(resultset.getString("QUESTIONS"));
                String uid = resultset.getString("QUESTION_UID");
                // 설문 문항에 맞는 설문 답항 출력
                query = "SELECT example_list.EXAMPLE_UID , example_list.EXAMPLE, example_list.ORDERS " +
                        " FROM answers INNER JOIN example_list " +
                        " ON answers.EXAMPLE_UID = example_list.EXAMPLE_UID " +
                        " WHERE QUESTIONS_UID = '" + uid + "' " +
                        " ORDER BY ORDERS;";
                ResultSet resultset2 = statement.executeQuery(query);
                ArrayList arrayList = new ArrayList<>();
                while (resultset2.next()) {
                    System.out.print(resultset2.getInt("ORDERS") + ". ");
                    System.out.println(resultset2.getString("EXAMPLE"));
                    arrayList.add();
                }
                // 설문자 답 받기
                System.out.println("응답 번호 : ");
                String answer = scanner.next();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

}
