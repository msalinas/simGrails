package com.sim.prueba

import grails.converters.*

class CountryController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def ajaxGetCities = {
		def country = Country.get(params.id)
		render country?.cities as JSON
	}

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [countryInstanceList: Country.list(params), countryInstanceTotal: Country.count()]
    }

    def create = {
        def countryInstance = new Country()
        countryInstance.properties = params
        return [countryInstance: countryInstance]
    }

    def save = {
        def countryInstance = new Country(params)
        if (!countryInstance.hasErrors() && countryInstance.save()) {
            flash.message = "country.created"
            flash.args = [countryInstance.id]
            flash.defaultMessage = "Country ${countryInstance.id} created"
            redirect(action: "show", id: countryInstance.id)
        }
        else {
            render(view: "create", model: [countryInstance: countryInstance])
        }
    }

    def show = {
        def countryInstance = Country.get(params.id)
        if (!countryInstance) {
            flash.message = "country.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Country not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [countryInstance: countryInstance]
        }
    }

    def edit = {
        def countryInstance = Country.get(params.id)
        if (!countryInstance) {
            flash.message = "country.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Country not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [countryInstance: countryInstance]
        }
    }

    def update = {
        def countryInstance = Country.get(params.id)
        if (countryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (countryInstance.version > version) {
                    
                    countryInstance.errors.rejectValue("version", "country.optimistic.locking.failure", "Another user has updated this Country while you were editing")
                    render(view: "edit", model: [countryInstance: countryInstance])
                    return
                }
            }
            countryInstance.properties = params
            if (!countryInstance.hasErrors() && countryInstance.save()) {
                flash.message = "country.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Country ${params.id} updated"
                redirect(action: "show", id: countryInstance.id)
            }
            else {
                render(view: "edit", model: [countryInstance: countryInstance])
            }
        }
        else {
            flash.message = "country.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Country not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def countryInstance = Country.get(params.id)
        if (countryInstance) {
            try {
                countryInstance.delete()
                flash.message = "country.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Country ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "country.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Country ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "country.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Country not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
