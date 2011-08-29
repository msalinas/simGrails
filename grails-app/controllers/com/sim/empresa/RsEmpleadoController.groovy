package com.sim.empresa

import com.sim.regional.SimSucursal

class RsEmpleadoController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [rsEmpleadoInstanceList: RsEmpleado.list(params), rsEmpleadoInstanceTotal: RsEmpleado.count()]
    }

    def create = {
        def rsEmpleadoInstance = new RsEmpleado()
        rsEmpleadoInstance.properties = params
        return [rsEmpleadoInstance: rsEmpleadoInstance]
    }

    def save = {
        def rsEmpleadoInstance = new RsEmpleado(params)
        if (!rsEmpleadoInstance.hasErrors() && rsEmpleadoInstance.save()) {
            flash.message = "rsEmpleado.created"
            flash.args = [rsEmpleadoInstance.id]
            flash.defaultMessage = "RsEmpleado ${rsEmpleadoInstance.id} created"
            redirect(action: "show", id: rsEmpleadoInstance.id)
        }
        else {
            render(view: "create", model: [rsEmpleadoInstance: rsEmpleadoInstance])
        }
    }

    def show = {
        def rsEmpleadoInstance = RsEmpleado.get(params.id)
        if (!rsEmpleadoInstance) {
            flash.message = "rsEmpleado.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsEmpleado not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
			//OBTIENE LA SUCURSAL ASIGNADA
			log.info "SUCURSAL ASIGNADA"
			log.info rsEmpleadoInstance.sucursalPertenece
			def SucursalPertenece = SimSucursal.get(rsEmpleadoInstance.sucursalPertenece)
            return [rsEmpleadoInstance: rsEmpleadoInstance, parametroSucursalPertenece :SucursalPertenece?.nombreSucursal]
        }
    }

    def edit = {
        def rsEmpleadoInstance = RsEmpleado.get(params.id)
        if (!rsEmpleadoInstance) {
            flash.message = "rsEmpleado.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsEmpleado not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [rsEmpleadoInstance: rsEmpleadoInstance]
        }
    }

    def update = {
        def rsEmpleadoInstance = RsEmpleado.get(params.id)
        if (rsEmpleadoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (rsEmpleadoInstance.version > version) {
                    
                    rsEmpleadoInstance.errors.rejectValue("version", "rsEmpleado.optimistic.locking.failure", "Another user has updated this RsEmpleado while you were editing")
                    render(view: "edit", model: [rsEmpleadoInstance: rsEmpleadoInstance])
                    return
                }
            }
            rsEmpleadoInstance.properties = params
            if (!rsEmpleadoInstance.hasErrors() && rsEmpleadoInstance.save()) {
                flash.message = "rsEmpleado.updated"
                flash.args = [params.id]
                flash.defaultMessage = "RsEmpleado ${params.id} updated"
                redirect(action: "show", id: rsEmpleadoInstance.id)
            }
            else {
                render(view: "edit", model: [rsEmpleadoInstance: rsEmpleadoInstance])
            }
        }
        else {
            flash.message = "rsEmpleado.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsEmpleado not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def rsEmpleadoInstance = RsEmpleado.get(params.id)
        if (rsEmpleadoInstance) {
            try {
                rsEmpleadoInstance.delete()
                flash.message = "rsEmpleado.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "RsEmpleado ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "rsEmpleado.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "RsEmpleado ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "rsEmpleado.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsEmpleado not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
