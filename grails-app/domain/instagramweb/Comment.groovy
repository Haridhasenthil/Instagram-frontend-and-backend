package instagramweb

class Comment {

    User user;
    Post post;
    String comment;

    static hasMany = [likes :Likes]

    static constraints = {
        user nullable:false
        post nullable:false
        comment nullable:false
        like nullable:true,blanks:true
    }


    Comment(){

    }
}
