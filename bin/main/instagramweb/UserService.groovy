package instagramweb

import grails.gorm.transactions.Transactional

@Transactional
class UserService{
    def saveInstance(instance) {
        if(instance.validate()){
           instance.save()
            return true
        }
        return false
    }
}
