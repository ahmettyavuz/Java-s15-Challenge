import com.workintech.library.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static Library library = new Library();

    public static void main(String[] args) {

        Librarian librarian1 = new Librarian("1","doğancan","123");
        Librarian librarian2 = new Librarian("2","doğan","123");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n***********Kütüphane Sistemine Hoşgeldiniz***********");
            System.out.println("1. Kütüphaneci Sistemi");
            System.out.println("2. Okur Sistemi");
            System.out.println("3. Okur Kayıt");
            System.out.println("4. Çıkış");

            int systemChoice = scanner.nextInt();
            scanner.nextLine();

            if (systemChoice == 4) {
                break;
            }

            boolean exit = false;

            switch (systemChoice) {
                case 1:
                    System.out.println("Kütüphaneci adını girin (lütfen küçük harfle giriniz):");
                    String librarianName = scanner.nextLine();
                    System.out.println("Kütüphaneci şifresini girin:");
                    String librarianPassword = scanner.nextLine();

                    Librarian loggedInLibrarian = null;
                    if (librarian1.validate(librarianName, librarianPassword)) {
                        loggedInLibrarian = librarian1;
                    } else if (librarian2.validate(librarianName, librarianPassword)) {
                        loggedInLibrarian = librarian2;
                    } else {
                        System.out.println("Geçersiz giriş. Tekrar deneyin.");
                        break;
                    }

                    while (!exit) {
                        System.out.println("1. Yeni kitap ekle");
                        System.out.println("2. Kitap seç");
                        System.out.println("3. Kitap bilgilerini güncelle");
                        System.out.println("4. Kitap sil");
                        System.out.println("5. Kitapları Listele");
                        System.out.println("6. Okurları Listele");
                        System.out.println("7. Okur sil");
                        System.out.println("8. Okurun ödünç aldığı kitapları göster");
                        System.out.println("9. Ana Menüye Dön");
                        System.out.println("10. Çıkış");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                addBook(scanner);
                                break;
                            case 2:
                                selectBook(scanner);
                                break;
                            case 3:
                                updateBook(scanner);
                                break;
                            case 4:
                                removeBook(scanner);
                                break;

                            case 5:
                                library.listAllBooks();
                                break;
                            case 6:
                                library.listAllUsers();
                                break;
                            case 7:
                                removeUser(scanner);
                                break;
                            case 8:
                                showUserBorrowedBooks(scanner);
                                break;
                            case 9:
                                exit = true;
                                break;
                            case 10:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Geçersiz seçim. Tekrar deneyin.");
                        }
                    }
                    break;
                case 2:
                    loginUser(scanner);
                    while (!exit) {
                        System.out.println("1. Kitap seç");
                        System.out.println("2. Kategorideki tüm kitapları listele");
                        System.out.println("3. Yazara göre tüm kitapları listele");
                        System.out.println("4. Kitap ödünç al");
                        System.out.println("5. Kitap iade et");
                        System.out.println("6. Ana Menüye Dön");
                        System.out.println("7. Çıkış");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                selectBook(scanner);
                                break;
                            case 2:
                                listBooksByCategory(scanner);
                                break;
                            case 3:
                                listBooksByAuthor(scanner);
                                break;
                            case 4:
                                borrowBook(scanner);
                                break;
                            case 5:
                                returnBook(scanner);
                                break;
                            case 6:
                                exit = true;
                                break;
                            case 7:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Geçersiz seçim. Tekrar deneyin.");
                        }
                    }
                    break;
                case 3:
                    addUser(scanner);
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }

        scanner.close();
    }
    private static void loginUser(Scanner scanner) {
        while (true) {
            System.out.println("İsmini giriniz: (lütfen küçük harfle giriniz):");
            String userName = scanner.nextLine();
            System.out.println("Şifresini giriniz:");
            String userPassword = scanner.nextLine();

            boolean isValidUser = false;
            for (User user : library.getAllUsers()) {
                if (user.getName().equalsIgnoreCase(userName) && user.getPassword().equals(userPassword)) {
                    System.out.println("Okur Sistemine Giriş Başarılı. Hoşgeldin " + userName);
                    isValidUser = true;
                    break;
                }
            }

            if (isValidUser) {
                break;
            } else {
                System.out.println("Geçersiz giriş. Tekrar deneyin.");
            }
        }
    }


    private static void addBook(Scanner scanner) {
        System.out.println("Kitap ID'sini girin:");
        String bookID = scanner.nextLine();
        System.out.println("Yazar adını girin:");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName);
        System.out.println("Kitap adını girin:");
        String name = scanner.nextLine();
        System.out.println("Kategori adını girin:");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);
        System.out.println("Baskı numarasını girin:");
        String edition = scanner.nextLine();
        System.out.println("Satın alma tarihini girin (yyyy-mm-dd):");
        String dateStr = scanner.nextLine();
        LocalDate dateOfPurchase = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        Status status = Status.AVAILABLE;

        Book book = new Book(bookID, author, name, category, edition, dateOfPurchase, status);
        library.addBook(book);
        System.out.println("Kitap eklendi.");
    }

    private static void addUser(Scanner scanner) {

        System.out.println("Yeni Okur Kaydı");
        System.out.println("Id: ");
        String ID = scanner.nextLine();
        System.out.println("İsminizi girin: ");
        String name = scanner.nextLine();
        System.out.println("Şifre oluşturun: ");
        String password = scanner.nextLine();

        User newUser = new User(ID, name, password);
        library.addUser(newUser);
        System.out.println("Yeni kullanıcı kaydedildi.");


    }

    private static void selectBook(Scanner scanner) {
        System.out.println("1. ID'ye göre kitap ara");
        System.out.println("2. İsme göre kitap ara");
        System.out.println("3. Yazara göre kitap ara");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Book book = null;
        User user;

        switch (choice) {
            case 1:
                System.out.println("Kitap ID'sini girin:");
                String bookID = scanner.nextLine();
                book = library.getBook(bookID);
                break;
            case 2:
                System.out.println("Kitap adını girin:");
                String name = scanner.nextLine();
                for (Book b : library.getAllBooks()) {
                    if (b.getName().equalsIgnoreCase(name)) {
                        book = b;
                        break;
                    }
                }
                break;
            case 3:
                System.out.println("Yazar adını girin:");
                String authorName = scanner.nextLine();
                for (Book b : library.getAllBooks()) {
                    if (b.getAuthor().getName().equalsIgnoreCase(authorName)) {
                        book = b;
                        break;
                    }
                }
                break;
            default:
                System.out.println("Geçersiz seçim. Tekrar deneyin.");
        }

        if (book != null && book.getStatus()==Status.BORROWED) {
            User borrower = findBorrower(book);
            System.out.println("Kitap bulundu: " + book.getName() + " " +borrower.getName() +" isimli okur tarafından ödünç alınmış");
        } else if(book != null && book.getStatus()==Status.AVAILABLE)  {
            System.out.println("Kitap bulundu: " + book.getName());
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
    private static User findBorrower(Book book) {
        for (User user : library.getAllUsers()) {
            if (user.getBorrowedBooks().contains(book)) {
                return user;
            }
        }
        return null;
    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Güncellenecek kitabın ID'sini girin:");
        String bookID = scanner.nextLine();
        Book book = library.getBook(bookID);

        if (book != null) {
            System.out.println("Yeni kitap adını girin:");
            String name = scanner.nextLine();
            System.out.println("Yeni kategori adını girin:");
            String categoryName = scanner.nextLine();
            Category category = new Category(categoryName);
            System.out.println("Yeni baskı numarasını girin:");
            String edition = scanner.nextLine();
            System.out.println("Yeni satın alma tarihini girin (yyyy-mm-dd):");
            String dateStr = scanner.nextLine();
            LocalDate dateOfPurchase = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            Status status = book.getStatus();

            book.setName(name);
            book.setCategory(category);
            book.setEdition(edition);
            book.setDateOfPurchase(dateOfPurchase);
            book.setStatus(status);

            library.updateBook(book);
            System.out.println("Kitap güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void removeBook(Scanner scanner) {
        System.out.println("Silinecek kitabın ID'sini girin:");
        String bookID = scanner.nextLine();
        library.removeBook(bookID);
    }
    private static void removeUser(Scanner scanner) {
        System.out.println("Silinecek okurun ID'sini girin:");
        String userID = scanner.nextLine();
        library.removeUser(userID);
    }

    private static void listBooksByCategory(Scanner scanner) {
        System.out.println("Kategori adını girin:");
        String categoryName = scanner.nextLine();
        List<Book> books = library.getBooksByCategory(categoryName);

        if (!books.isEmpty()) {
            System.out.println("Kategorideki kitaplar:");
            for (Book book : books) {
                System.out.println(book.getName());
            }
        } else {
            System.out.println("Bu kategoride kitap bulunamadı.");
        }
    }
    private static void showUserBorrowedBooks(Scanner scanner) {
        System.out.println("Okurun ID'sini girin:");
        String userID = scanner.nextLine();
        User user = library.getUser(userID);

        if (user != null) {
            Set<Book> borrowedBooks = user.getBorrowedBooks();
            if (!borrowedBooks.isEmpty()) {
                System.out.println( user.getName() +" isimli okurun ödünç aldığı kitaplar:");
                for (Book book : borrowedBooks) {
                    System.out.println(book.getName() + " (ID: " + book.getBookID() + ")");
                }
            } else {
                System.out.println("Okurun ödünç aldığı kitap bulunmamaktadır.");
            }
        } else {
            System.out.println("Okur bulunamadı.");
        }
    }

    private static void listBooksByAuthor(Scanner scanner) {
        System.out.println("Yazar adını girin:");
        String authorName = scanner.nextLine();
        List<Book> books = library.getBooksByAuthor(authorName);

        if (!books.isEmpty()) {
            System.out.println("Yazarın kitapları:");
            for (Book book : books) {
                System.out.println(book.getName());
            }
        } else {
            System.out.println("Bu yazarın kitabı bulunamadı.");
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.println("Kullanıcı ID'sini girin:");
        String userID = scanner.nextLine();
        User user = library.getUser(userID);

        if (user != null) {
            System.out.println("Ödünç alınacak kitabın ID'sini girin:");
            String bookID = scanner.nextLine();
            library.issueBook(bookID, userID);
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("Kullanıcı ID'sini girin:");
        String userID = scanner.nextLine();
        User user = library.getUser(userID);

        if (user != null) {
            System.out.println("İade edilecek kitabın ID'sini girin:");
            String bookID = scanner.nextLine();
            library.returnBook(bookID, userID);
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }
}
