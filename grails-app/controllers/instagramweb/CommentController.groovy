package instagramweb

class CommentController {
    static List responseFormats = ['json','xml']
    def userService

    def index() { }
    
    def usercomment(){
        def reqIns = request.JSON;
        Comment commentIns = new Comment(
            user?: reqIns.user,
            post?: reqIns.post,
            comment?: reqIns.comment
        );
        def data = [:]
        if(commentIns){
            def log = userService.saveInstance(commentIns);
            data = ["message":"true123"]
        }else{
            data = ["message":"false"]
        }
        respond data;
    }

    def particularusercommentforpost(){
        def reqIns = request.JSON;
        def post = Post.get(reqIns.post);
        println "==============================>>>>>>>>>>>>>>>>>>>>.${post}"
        def user = User.get(reqIns.user);
        println "==============================>>>>>>>>>>>>>>>>>>>>.${user}"
        def commentIns = Comment.findByPostAndUser(post,user);
        def data
        if (commentIns)
        println "==============================>>>>>>>>>>>>>>>>>>>>.${commentIns}"
         data = [
            "post": commentIns.post.id,
            "user": commentIns.user.id,
            "comment": commentIns.comment
        ];
        println"----------------------------------${data.errors}"
        respond data;  
    }
}
