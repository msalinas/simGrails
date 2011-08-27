package com.sim.catalogo

import com.rs.gral.RsGralAsentamiento

class SimCatTipoAsentamientoController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [simCatTipoAsentamientoInstanceList: SimCatTipoAsentamiento.list(params), simCatTipoAsentamientoInstanceTotal: SimCatTipoAsentamiento.count()]
    }

    def create = {
        def simCatTipoAsentamientoInstance = new SimCatTipoAsentamiento()
        simCatTipoAsentamientoInstance.properties = params
        return [simCatTipoAsentamientoInstance: simCatTipoAsentamientoInstance]
    }

    def save = {
        def simCatTipoAsentamientoInstance = new SimCatTipoAsentamiento(params)
        if (!simCatTipoAsentamientoInstance.hasErrors() && simCatTipoAsentamientoInstance.save()) {
            flash.message = "simCatTipoAsentamiento.created"
            flash.args = [simCatTipoAsentamientoInstance.id]
            flash.defaultMessage = "SimCatTipoAsentamiento ${simCatTipoAsentamientoInstance.id} created"
            redirect(action: "show", id: simCatTipoAsentamientoInstance.id)
        }
        else {
            render(view: "create", model: [simCatTipoAsentamientoInstance: simCatTipoAsentamientoInstance])
        }
    }

    def show = {
        def simCatTipoAsentamientoInstance = SimCatTipoAsentamiento.get(params.id)
        if (!simCatTipoAsentamientoInstance) {
            flash.message = "simCatTipoAsentamiento.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatTipoAsentamiento not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [simCatTipoAsentamientoInstance: simCatTipoAsentamientoInstance]
        }
    }

    def edit = {
        def simCatTipoAsentamientoInstance = SimCatTipoAsentamiento.get(params.id)
        if (!simCatTipoAsentamientoInstance) {
            flash.message = "simCatTipoAsentamiento.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatTipoAsentamiento not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [simCatTipoAsentamientoInstance: simCatTipoAsentamientoInstance]
        }
    }

    def update = {
        def simCatTipoAsentamientoInstance = SimCatTipoAsentamiento.get(params.id)
        if (simCatTipoAsentamientoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (simCatTipoAsentamientoInstance.version > version) {
                    
                    simCatTipoAsentamientoInstance.errors.rejectValue("version", "simCatTipoAsentamiento.optimistic.locking.failure", "Another user has updated this SimCatTipoAsentamiento while you were editing")
                    render(view: "edit", model: [simCatTipoAsentamientoInstance: simCatTipoAsentamientoInstance])
                    return
                }
            }
            simCatTipoAsentamientoInstance.properties = params
            if (!simCatTipoAsentamientoInstance.hasErrors() && simCatTipoAsentamientoInstance.save()) {
                flash.message = "simCatTipoAsentamiento.updated"
                flash.args = [params.id]
                flash.defaultMessage = "SimCatTipoAsentamiento ${params.id} updated"
                redirect(action: "show", id: simCatTipoAsentamientoInstance.id)
            }
            else {
                render(view: "edit", model: [simCatTipoAsentamientoInstance: simCatTipoAsentamientoInstance])
            }
        }
        else {
            flash.message = "simCatTipoAsentamiento.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "SimCatTipoAsentamiento not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def simCatTipoAsentamientoInstance = SimCatTipoAsentamiento.get(params.id)
		
		log.info "BORRAR TIPO DE ASENTAMIENTO"
		log.info simCatTipoAsentamientoInstance
		
		//VERIFICA SI EL TIPO DE ASENTAMIENTO ESTA ASIGNADO AL CATALOGO DE ASENTAMIENTO
		if (RsGralAsentamiento.findByTipoAsentamiento(simCatTipoAsentamientoInstance)){
			log.info "TIPO DE ASENTAMIENTO EXISTE EN ASENTAMIENTO"
			flash.message = "simCatTipoAsentamiento.not.deleted"
			flash.args = [params.id]
			flash.defaultMessage = "Tipo de asentamiento ${params.id} existe en los Asentamientos"
			redirect(action: "show", id: params.id)
		}else{
			log.info "TIPO DE ASENTAMIENTO NO EXISTE EN ASENTAMIENTO"
			if (simCatTipoAsentamientoInstance) {
				try {
					simCatTipoAsentamientoInstance.delete()
					flash.message = "simCatTipoAsentamiento.deleted"
					flash.args = [params.id]
					flash.defaultMessage = "SimCatTipoAsentamiento ${params.id} deleted"
					redirect(action: "list")
				}
				catch (org.springframework.dao.DataIntegrityViolationException e) {
					flash.message = "simCatTipoAsentamiento.not.deleted"
					flash.args = [params.id]
					flash.defaultMessage = "SimCatTipoAsentamiento ${params.id} could not be deleted"
					redirect(action: "show", id: params.id)
				}
			}
			else {
				flash.message = "simCatTipoAsentamiento.not.found"
				flash.args = [params.id]
				flash.defaultMessage = "SimCatTipoAsentamiento not found with id ${params.id}"
				redirect(action: "list")
			}
		}
    }
}
