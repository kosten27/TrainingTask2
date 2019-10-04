package task

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Table

@EqualsAndHashCode(includes='username')
class User implements Serializable {

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
	}

	static mapping = {
		table 'users'
		username column: 'username', type: 'string'
		password column: '`password`', type: 'string'
		enabled column: 'enabled', type: 'boolean'
		accountExpired column: 'account_expired', type: 'boolean'
		accountLocked column: 'account_locked', type: 'boolean'
		passwordExpired column: 'password_expired', type: 'boolean'
	}
	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	@Override
	public String toString() {
		return username;
	}
}
