package instagramweb

class UserController {
    static List responseFormats = ['json', 'xml']
    def userService
    def signup(){
        def sign = request.JSON
        def signupIns = new User(
        username?: sign.username, 
        name?: sign.name,
        password?: sign.password,
        dob?: sign.DOB,
        email?: sign.email)
        def data,log
        if(signupIns.validate()){
            log = userService.saveInstance(signupIns)
            data = ["message":log]
        }else{
            data = ["message":log]
        }
        respond data
    }
    
    def login(){
       def login = request.JSON
       def loginInst = User.createCriteria().list(max: 1) {
       or {
          and {
            eq('username', login.username)
            eq('password', login.password)
        }
        eq('email', login.email)
        
        }
       }
        def data
        if(loginInst){
           data = ["message":"true"]
        }else{
           data = ["message":"false"]
        }
        respond data
    }
}
