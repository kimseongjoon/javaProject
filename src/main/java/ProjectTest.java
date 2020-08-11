import order.OrderedProducts;
import product.Product;
import product.Products;

import java.io.IOException;
import java.util.Scanner;

public class ProjectTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Products products = Products.getInstance();
        Product product;
        OrderedProducts orderedProducts = OrderedProducts.getInstance();

        product = new Product("반팔 티셔츠", 20000, 100, "A사");
        products.addProduct(product);
        product = new Product("컴퓨터", 1000000, 30, "B사");
        products.addProduct(product);
        product = new Product("책", 15000, 150, "C사");
        products.addProduct(product);
        product = new Product("반팔 티셔츠", 20000, 100, "E사");
        products.addProduct(product);
        product = new Product("장난감", 5000, 50, "F사");
        products.addProduct(product);
        product = new Product("화장품", 40000, 100, "G사");
        products.addProduct(product);
        product = new Product("신발", 250000, 5, "Z사");
        products.addProduct(product);

        System.out.println("쇼핑몰 프로그램입니다.");
        System.out.println("쇼핑을 종료하고자 하는 경우 Q를 입력하세요.");

        String result = "";

        while (true) {
            System.out.println();
            products.printAllProducts();

            System.out.println("원하는 상품 번호를 입력하세요.");
            System.out.print("상품 번호 : ");

            String productID = scanner.nextLine();

            if (productID.equalsIgnoreCase("Q")) {
                System.out.println("프로그램을 종료합니다.");
                return;
            } else if (!products.getProductList().containsKey(Integer.parseInt(productID))){
                System.out.println("입력한 번호에 해당하는 상품은 존재하지 않습니다.");
            }

            System.out.println("구매하고자 하는 상품 수량을 입력하세요.");
            System.out.print("상품 수량 : ");

            String purchaseQuatity = scanner.nextLine();

            if (purchaseQuatity.equalsIgnoreCase("Q")) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            orderedProducts.addOrderedProduct(Integer.parseInt(productID), Integer.parseInt(purchaseQuatity));

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
