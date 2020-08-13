import order.OrderedProducts;
import registerproduct.RegisteredProduct;
import registerproduct.RegisteredProducts;

import java.util.Scanner;

public class ProjectTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegisteredProducts registeredProducts = RegisteredProducts.getInstance();
        RegisteredProduct registeredProduct;
        OrderedProducts orderedProducts = OrderedProducts.getInstance();

        registeredProduct = new RegisteredProduct("반팔 티셔츠", "A사", 20000, 100);
        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("컴퓨터", "B사", 1000000, 30);
        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("책", "C사", 15000, 150);
        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("반팔 티셔츠", "E사", 20000, 100);
        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("장난감", "F사", 5000, 50);
        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("화장품", "G사", 40000, 100);
        registeredProducts.addProduct(registeredProduct);
        registeredProduct = new RegisteredProduct("신발", "Z사", 250000, 5);
        registeredProducts.addProduct(registeredProduct);

        System.out.println("쇼핑몰 프로그램입니다.");
        System.out.println("쇼핑을 종료하고자 하는 경우 Q를 입력하세요.");

        String result = "";

        while (true) {
            System.out.println();
            registeredProducts.printAllProducts();

            System.out.println("원하는 상품 번호를 입력하세요.");
            System.out.print("상품 번호 : ");

            String productID = scanner.nextLine();

            try {
                if (productID.equalsIgnoreCase("Q")) {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else if (!registeredProducts.getProductList().containsKey(Integer.parseInt(productID))){
                    System.out.println("입력한 번호에 해당하는 상품은 존재하지 않습니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("상품 번호가 잘 못 입력되었습니다. 다시 입력하세요");
                continue;
            }

            System.out.println("구매하고자 하는 상품 수량을 입력하세요.");
            System.out.print("상품 수량 : ");

            String purchaseQuatity = scanner.nextLine();

            if (purchaseQuatity.equalsIgnoreCase("Q")) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            try {
                orderedProducts.addOrderedProduct(Integer.parseInt(productID), Integer.parseInt(purchaseQuatity));
            } catch (NumberFormatException e) {
                System.out.println("상품 수량이 잘 못 입력되었습니다. 다시 입력하세요");
                continue;
            }

            System.out.println("쇼핑을 계속하고자 하는 경우 Y를 입력하고, 선택한 상품을 구매하고자 하는 경우 N을 입력하세요.");
            System.out.print("쇼핑을 계속하시겠습니까? : ");

            while (true) {
                result = scanner.nextLine();

                if (result.equalsIgnoreCase("Y")) {
                    break;
                } else if (result.equalsIgnoreCase("N")) {
                    System.out.println(orderedProducts.printOrderedProducts());
                    System.out.println("구매한 상품의 총 금액 : " + orderedProducts.calTotalPrice() + "원");
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else if (result.equalsIgnoreCase("Q")) {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else {
                    System.out.println("해당하는 문자를 다시 입력하세요. ");
                    System.out.print("입력 : ");
                }
            }
        }

    }
}
