package instagramweb
import java.util.Base64
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import grails.web.mapping.LinkGenerator

class PostController {

static List responseFormats = ['json', 'xml']
    def userService
    LinkGenerator grailsLinkGenerator
    def postsave() { 
        def log = request.JSON
        def imageFile = request.getFile('photo')
        def imageInstance = new Post()
        def data = [:]
        if (imageFile && !imageFile.empty) {
            imageInstance.photo = imageFile.bytes
            imageInstance.caption = log.caption
            imageInstance.user = log.user
            if (imageInstance.validate()) {
               def save = userService.saveInstance(imageInstance)
                println "Post saved successfully"
                data = ["message":"true"]
            } 
            else {
                println "image upload failed"
                data = ["message":"false"]
        }
        respond data
      }
    }
    
    def show() {
        def data=[] 
        def studentIns = Post.getAll()
        studentIns.each { list ->
            def imageLink = grailsLinkGenerator.link(controller: 'post',action: 'getimage',id: list.user.id)
            Post postId = Post.get(list.id)
            def likecount = Likes.countByPost(list)
            def student = [
                "user_id": list.user.id,
                "username": list.user.username,
                "name": list.user.name,
                "email": list.user.email,
                "photo": imageLink, 
                "caption": list.caption,
                "likecount": likecount
            ]
            data << student
        }
        respond data
    }

    def getimage(Long id){
        def image = Post.get(id)
        
        if (!image) {
            response.sendError(404, 'Image not found')
            return
        }
    response.contentType = 'image/jpeg'  // Set according to the image type
    response.contentLength = post.photo.length
    response.outputStream.write(post.photo)
    response.outputStream.flush()
    response.outputStream.close()
    }

    def particularuserpost(){
        def user = request.JSON;
        def userIns = Post.findById(user.id.toLong())
        def data = []
        for(def postIns : userIns){
            def imageLink = grailsLinkGenerator.link(controller: 'post',action: 'getimage',id: postIns.user.id)
            def post = [
                "post": postIns.user.id.toLong(),
                "photo": imageLink, 
                "caption": postIns.caption
            ]
            data << post
        } 
        respond data
    }
}
