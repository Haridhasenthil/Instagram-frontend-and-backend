package instagramweb

class User {
    
    String username;
    String name;
    String dob;
    String email;
    String password;

    // nullable 

    // about, bio, image byte[]

    static constraints = {
        dob nullable: false
        username:false
        email:false
        password:false
    }

    static hasmany = [post:Post]

    User(){
        
    }
}
