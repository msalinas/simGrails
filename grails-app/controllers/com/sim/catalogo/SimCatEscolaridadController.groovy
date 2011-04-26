package com.sim.catalogo

//import grails.plugins.springsecurity.Secured

class SimCatEscolaridadController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [simCatEscolaridadInstanceList: SimCatEscolaridad.list(params), simCatEscolaridadInstanceTotal: SimCatEscolaridad.count()]
    }
	
	//Ejemplo para implementar roles por anotaciones
	//@Secured(['ROLE_USER'])
    def create = {
        def simCatEscolaridadInstance = new SimCatEscolaridad()
        simCatEscolaridadInstance.properties = params
        return [simCatEscolaridadInstance: simCatEscolaridadInstance]
    }

    def save = {
        def simCatEscolaridadInstance = new SimCatEscolaridad(params)
        if (!simCatEscolaridadInstance.hasErrors() && simCatEscolaridadInstance.save()) {
            flash.message = "simCatEscolaridad.created"
            flash.args = [simCatEscolaridadInstance.id]
            flash.defaultMessage = "SimCatEscolaridad ${simCatEscolaridadInstance.id} created"
            redirect(action: "show", id: simCatEscolaridadInstance.id)
        }
        else {
            render(view: "create", model: [simCatEscolaridadInstance: simCatEscolaridadInstance])
        }
    }

    def show = {
        def simCatEscolaridadInstance = SimCatEscolaridad.get(params.id)
        if (!simCatEscolaridadInstance) {
            flash.message = "simCatEscolaridad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatEscolaridad not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [simCatEscolaridadInstance: simCatEscolaridadInstance]
        }
    }

    def edit = {
        def simCatEscolaridadInstance = SimCatEscolaridad.get(params.id)
        if (!simCatEscolaridadInstance) {
            flash.message = "simCatEscolaridad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatEscolaridad not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [simCatEscolaridadInstance: simCatEscolaridadInstance]
        }
    }

    def update = {
        def simCatEscolaridadInstance = SimCatEscolaridad.get(params.id)
        if (simCatEscolaridadInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (simCatEscolaridadInstance.version > version) {
                    
                    simCatEscolaridadInstance.errors.rejectValue("version", "simCatEscolaridad.optimistic.locking.failure", "Another user has updated this SimCatEscolaridad while you were editing")
                    render(view: "edit", model: [simCatEscolaridadInstance: simCatEscolaridadInstance])
                    return
                }
            }
            simCatEscolaridadInstance.properties = params
            if (!simCatEscolaridadInstance.hasErrors() && simCatEscolaridadInstance.save()) {
                flash.message = "simCatEscolaridad.updated"
                flash.args = [params.id]
                flash.defaultMessage = "SimCatEscolaridad ${params.id} updated"
                redirect(action: "show", id: simCatEscolaridadInstance.id)
            }
            else {
                render(view: "edit", model: [simCatEscolaridadInstance: simCatEscolaridadInstance])
            }
        }
        else {
            flash.message = "simCatEscolaridad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatEscolaridad not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def simCatEscolaridadInstance = SimCatEscolaridad.get(params.id)
        if (simCatEscolaridadInstance) {
            try {
                simCatEscolaridadInstance.delete()
                flash.message = "simCatEscolaridad.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "SimCatEscolaridad ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "simCatEscolaridad.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "SimCatEscolaridad ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "simCatEscolaridad.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatEscolaridad not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
