package instagramweb

class LikesController {
    static List responseFormats = ['json','xml']
    def userService
    def index() { }
  
    def liked(){

    def reqIns = request.JSON;
    Likes existingIns , likeIns
    if (reqIns.comment) {
        existingIns = Likes.findByUserAndComment(User.get(reqIns.user.toLong()), Comment.get(reqIns.comment.toLong()))
    } else {
        existingIns = Likes.findByUserAndPost(User.get(reqIns.user.toLong()), Post.get(reqIns.post.toLong()))
    } 
    if (existingIns) {
        existingIns.isliked = reqIns.isliked
        likeIns = existingIns
    } else {
        likeIns = new Likes(
            user?: User.get(reqIns.user.toLong()),
            isLiked?: reqIns.isliked
        )
        if (reqIns.comment) {
            likeIns.comment = Comment.get(reqIns.comment.toLong())
        } else {
            likeIns.post = Post.get(reqIns.post.toLong())
        }
    }
    def log = userService.saveInstance(likeIns);
    def data = []
    if(log){
        data = ["message":"true"]
    } else {
        data = ["message":"false"]
    }
    respond data;   
    }
}
