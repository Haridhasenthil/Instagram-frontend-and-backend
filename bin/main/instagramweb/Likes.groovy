package instagramweb

class Likes  {
    
    User user;
    Post post;
    boolean isliked = false;

    static belongsTo = [comment:Comment,post:Post]

    static constraints = {
        user nullable:false
        post nullable:false
        comment nullable:true
        post nullable:true
    }
}
