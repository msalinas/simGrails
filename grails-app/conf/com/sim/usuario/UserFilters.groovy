package com.sim.usuario

class UserFilters {
    def springSecurityService

    def filters = {
        all(uri: '/**') {
            after = { model ->
                if (model && springSecurityService.isLoggedIn()) {
                    model['user'] = User.get(springSecurityService.principal.id)
                }
            }
        }
    }
}
