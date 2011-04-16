package com.sim.prueba

class CityController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [cityInstanceList: City.list(params), cityInstanceTotal: City.count()]
    }

    def create = {
        def cityInstance = new City()
        cityInstance.properties = params
        return [cityInstance: cityInstance]
    }

    def save = {
        def cityInstance = new City(params)
        if (!cityInstance.hasErrors() && cityInstance.save()) {
            flash.message = "city.created"
            flash.args = [cityInstance.id]
            flash.defaultMessage = "City ${cityInstance.id} created"
            redirect(action: "show", id: cityInstance.id)
        }
        else {
            render(view: "create", model: [cityInstance: cityInstance])
        }
    }

    def show = {
        def cityInstance = City.get(params.id)
        if (!cityInstance) {
            flash.message = "city.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "City not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [cityInstance: cityInstance]
        }
    }

    def edit = {
        def cityInstance = City.get(params.id)
        if (!cityInstance) {
            flash.message = "city.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "City not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [cityInstance: cityInstance]
        }
    }

    def update = {
        def cityInstance = City.get(params.id)
        if (cityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cityInstance.version > version) {
                    
                    cityInstance.errors.rejectValue("version", "city.optimistic.locking.failure", "Another user has updated this City while you were editing")
                    render(view: "edit", model: [cityInstance: cityInstance])
                    return
                }
            }
            cityInstance.properties = params
            if (!cityInstance.hasErrors() && cityInstance.save()) {
                flash.message = "city.updated"
                flash.args = [params.id]
                flash.defaultMessage = "City ${params.id} updated"
                redirect(action: "show", id: cityInstance.id)
            }
            else {
                render(view: "edit", model: [cityInstance: cityInstance])
            }
        }
        else {
            flash.message = "city.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "City not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def cityInstance = City.get(params.id)
        if (cityInstance) {
            try {
                cityInstance.delete()
                flash.message = "city.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "City ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "city.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "City ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "city.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "City not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
