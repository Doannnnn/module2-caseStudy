import org.example.utils.GetValue;

import java.util.Scanner;

public class DEmo {
    public static void main(String[] args) {
        int numbersCard = GetValue.getInt("Nhập số thẻ");
        if (numbersCard == 0){
            System.out.println("Không có thẻ nào được tạo.");;
        } else {
            for (int k = 0; k < numbersCard; k++) {
                System.out.println("ok");
            }
        }
    }
}
