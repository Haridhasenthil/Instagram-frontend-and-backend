package instagramweb

class Post {
    
    User user
    byte[] photo
    String caption

    static constraints = {
       photo(nullable:false)
       caption(nullable:false)
    }

    static mapping = {
        photo sqlType:"LONGBLOB"
    }
    
    static hasmany = [comment : Comment , likes : Likes ]

    Post(){
        
    }

}
