package chronic

import org.joda.time.DateTime
import org.bson.types.ObjectId

class AuthenticationService {

    static transactional = true
	
    def createToken(String username, String password) {
		// Find User
		def u = User.findByUsernameAndPassword(username, password)
		
		if (u == null) {
			return null
		}

		def token = Token.find(['createdBy':u, 'expiredOn >=':new Date(), 'status':'open'])
		
		if (token == null) {
			DateTime tokenDate = new DateTime()
			// Expire all tokens
			Token.update(['createdBy':u, 'status':'open'], { set 'status', 'expired' }, false, true)
			
			// create new token
			token = new Token()
			token.createdBy = u
			token.createdOn = tokenDate.toDate()
			token.expiredOn = tokenDate.plusMinutes(3).toDate()
			token.status = "open"
			token.save()
		}
		
		return token.id
    }
	
	def validateToken(String token) {
		def tokenObj = Token.get(token)
		
		if (tokenObj == null) return false
		
		Date currentDate = new Date()
		
		return (tokenObj.expiredOn >= currentDate && tokenObj.status.equals("open"))  
	}
}
