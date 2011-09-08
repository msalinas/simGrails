package com.sim.producto

import com.sim.catalogo.SimCatEtapaActividad

class SimProductoEtapaActividadException extends RuntimeException {
	String mensaje
	//NO SE OCUPA POR EL MOMENTO
	//SimProductoEtapaActividad productoEtapaActividad
}

class SimProductoEtapaActividadService {

	static transactional = true

	Boolean validaEtapaActividad(SimProducto producto,SimCatEtapaActividad etapaActividad, Integer orden) {
		log.info 'Inicia validacion SimProductoEtapaActividad'
		def productoActividad = SimProductoEtapaActividad.findByProductoAndEtapaActividad(producto,etapaActividad)
		if (productoActividad){
			log.info 'Ya esta asignada la Etapa-Actividad en el Producto'
			throw new SimProductoEtapaActividadException(mensaje: "Ya esta asignada la Etapa-Actividad en el Producto")
		}

		productoActividad = SimProductoEtapaActividad.findByProductoAndOrden(producto,orden)
		if (productoActividad){
			log.info 'Ya esta asignado el orden a otra Etapa-Actividad en el Producto'
			throw new SimProductoEtapaActividadException(mensaje: "Ya esta asignado el orden a otra Etapa-Actividad en el Producto")
		}else{
			log.info 'Validacion Exitosa SimProductoEtapaActividad'
			return true
		}
	}
}
