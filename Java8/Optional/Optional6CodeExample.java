import java.util.Optional;

class User{
    private String email;
    User(String email) { this.email = email; }
    public String getEmail() { return this.email; }
}

// Exception with message passed manually in the code
class EmailNotFoundException extends RuntimeException{
    EmailNotFoundException(String msg){
        super(msg);
    }
}

// Exception with NO-ARG constructor → works with method reference
class EmailNotFoundException2 extends RuntimeException{
    EmailNotFoundException2(){
        super("Email missing!");
    }
}

public class Optional6CodeExample {
    public static void main(String[] args) {
        User user1 = new User("perry@email.com");
        User user2 = new User(null);

        try{
            System.out.println("User1 email: "+getEmail(user1));
        }catch(Exception e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        // User1 email: perry@email.com

        try{
            System.out.println("User2 email: "+getEmail(user2));
        }catch(Exception e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        // EXCEPTION: Email not found!!

        try{
            System.out.println("User2 email: "+getEmail2(user2));
        }catch(Exception e){
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        // EXCEPTION: Email missing!
    }

    // Example 1: Using lambda to throw exception
    public static String getEmail(User user){
        return Optional.ofNullable(user)
        .map(User::getEmail)
        .filter(email -> !email.isBlank())
        .orElseThrow(()-> new EmailNotFoundException("Email not found!!"));
    }
    
    // Example 2: Using method reference → requires no-arg constructor
    public static String getEmail2(User user){
        return Optional.ofNullable(user)
        .map(User::getEmail)
        .filter(email -> !email.isBlank())
        .orElseThrow(EmailNotFoundException2::new); // only for no arg constructors 
    }
}
