import com.incra.LogEntrySeverity;
import com.incra.Profile 
import com.incra.Role 
import com.incra.User 
import com.incra.UserRole 


/**
 * The <i>PageFrameworkFilters</i> class contains code that runs before the rendering of a page, to set up
 * the page framework for navigation, security, etc.
 *  
 * @author Jeffrey Risberg
 * @since 10/16/10
 */
class PageFrameworkFilters {
	
	def pageFrameworkService
	def logEntryService
	
	def filters = {
		all(controller: '*', action: '*') {
			before = {
				// Set up session if needed, disable the page displays
				pageFrameworkService.reset(session)						
				
				String userName = params.userName;
				String userId = params.profile;				
				
				if (userId) {
					// Look up the user with this userId.  If this user doesn't exist, create one as member.
					User user = User.findByUserId(userId);
					Profile profile;
					
					if (user == null) {	
						if (userName != null) {					
							profile = new Profile(fullName: userName);
							profile.save();
							
							user = new User(userId: userId, profile: profile, password: "dummy1234");
							user.save();
							Role roleMember = Role.get(2);
							UserRole userRole = new UserRole(user: user, role: roleMember, effectiveStart: new Date());
							userRole.save();
							user.addToUserRoles(userRole);
							user.save();
						}
					}
					else {
						profile = user.profile
					}
					
					// Put that into the session, and record the login
					if (user) {
						pageFrameworkService.login(session, user, profile)
						
						List<String> parameters = new ArrayList<String>()
						logEntryService.publish(user, "Login", LogEntrySeverity.MED_HIGH, parameters)
					}
				}
			}
			after = {
			}
			afterView = {
			}
		}
	}
}
