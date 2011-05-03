package com.rs.gral

import com.sim.regional.SimRegional
import com.sim.regional.SimSucursal
import com.sim.empresa.RsEmpleado

class RsGralTelefonoController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [rsGralTelefonoInstanceList: RsGralTelefono.list(params), rsGralTelefonoInstanceTotal: RsGralTelefono.count()]
    }

    def create = {
        def rsGralTelefonoInstance = new RsGralTelefono()
        rsGralTelefonoInstance.properties = params
		
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UNA REGIONAL
		if (params.simRegional){
			def simRegionalInstance = new SimRegional()
			simRegionalInstance = SimRegional.get(params.simRegional.id)
			rsGralTelefonoInstance.regional = simRegionalInstance
		}
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UNA SUCURSAL
		if (params.simSucursal){
			def simSucursalInstance = new SimSucursal()
			simSucursalInstance = SimSucursal.get(params.simSucursal.id)
			rsGralTelefonoInstance.sucursal = simSucursalInstance
		}
		// VERIFICA SI EL DOMICILIO SE ASIGNA A UN EMPLEADO
		if (params.rsEmpleado){
			def rsEmpleadoInstance = new RsEmpleado()
			rsEmpleadoInstance = RsEmpleado.get(params.rsEmpleado.id)
			rsGralTelefonoInstance.empleado = rsEmpleadoInstance
		}
		
        return [rsGralTelefonoInstance: rsGralTelefonoInstance]
    }

    def save = {
        def rsGralTelefonoInstance = new RsGralTelefono(params)
        if (!rsGralTelefonoInstance.hasErrors() && rsGralTelefonoInstance.save()) {
            flash.message = "rsGralTelefono.created"
            flash.args = [rsGralTelefonoInstance.id]
            flash.defaultMessage = "RsGralTelefono ${rsGralTelefonoInstance.id} created"
            redirect(action: "show", id: rsGralTelefonoInstance.id)
        }
        else {
            render(view: "create", model: [rsGralTelefonoInstance: rsGralTelefonoInstance])
        }
    }

    def show = {
        def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
        if (!rsGralTelefonoInstance) {
            flash.message = "rsGralTelefono.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsGralTelefono not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [rsGralTelefonoInstance: rsGralTelefonoInstance]
        }
    }

    def edit = {
        def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
        if (!rsGralTelefonoInstance) {
            flash.message = "rsGralTelefono.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsGralTelefono not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [rsGralTelefonoInstance: rsGralTelefonoInstance]
        }
    }

    def update = {
        def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
        if (rsGralTelefonoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (rsGralTelefonoInstance.version > version) {
                    
                    rsGralTelefonoInstance.errors.rejectValue("version", "rsGralTelefono.optimistic.locking.failure", "Another user has updated this RsGralTelefono while you were editing")
                    render(view: "edit", model: [rsGralTelefonoInstance: rsGralTelefonoInstance])
                    return
                }
            }
            rsGralTelefonoInstance.properties = params
            if (!rsGralTelefonoInstance.hasErrors() && rsGralTelefonoInstance.save()) {
                flash.message = "rsGralTelefono.updated"
                flash.args = [params.id]
                flash.defaultMessage = "RsGralTelefono ${params.id} updated"
                redirect(action: "show", id: rsGralTelefonoInstance.id)
            }
            else {
                render(view: "edit", model: [rsGralTelefonoInstance: rsGralTelefonoInstance])
            }
        }
        else {
            flash.message = "rsGralTelefono.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsGralTelefono not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def rsGralTelefonoInstance = RsGralTelefono.get(params.id)
        if (rsGralTelefonoInstance) {
            try {
                rsGralTelefonoInstance.delete()
                flash.message = "rsGralTelefono.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "RsGralTelefono ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "rsGralTelefono.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "RsGralTelefono ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "rsGralTelefono.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "RsGralTelefono not found with id ${params.id}"
            redirect(action: "list")
        }
    }
}
